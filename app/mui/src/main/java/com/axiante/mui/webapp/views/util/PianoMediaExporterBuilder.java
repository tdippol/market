package com.axiante.mui.webapp.views.util;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.PianoMediaSecurityUtils;
import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import com.axiante.mui.webapp.business.data.PianoMediaPromoArticoliDettaglioDbPromoDTO;
import com.axiante.mui.webapp.business.service.PianoMediaDettaglioFactory;
import com.axiante.mui.webapp.webservice.PianoMediaPianificazioniResource;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Dependent
public class PianoMediaExporterBuilder {
    private static final String[] STANDARD_HEADERS = {
            "Civetta",
            "Articolo",
            "Reparto",
            "Compratore",
            "Pezzi",
            "Rank Pezzi",
            "Fatturato",
            "Rank Fatturato",
            "Zone Diff",
            "Meccanica",
            "Condizione",
            "Valore Off",
            "Data Inizio",
            "Data Fine",
            "Volantino",
            "Commento"
    };
    private List<String> dinamycHeaders = new ArrayList<>();
    public static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Inject
    private Instance<PianoMediaDettaglioFactory> pianoMediaDettaglioFactoryInstance;
    @Inject
    private Instance<MuiService> muiServiceInstance;
    @Inject
    private Instance<PianoMediaSecurityUtils> securityUtilsInstance;


    public PianoMediaExporter getExporter(PianoMediaEntity pianoMedia, List<String> gruppi, UsersEntity usersEntity) {
        return new PianoMediaExporter(pianoMedia, gruppi, usersEntity);
    }


    public class PianoMediaExporter {
        private final PianoMediaEntity pianoMedia;
        private String fileName;
        private static final String EXTENSION = "xlsx";
        private Font boldFont;
        CellStyle boldStyle;
        List<String> gruppi = null;
        UsersEntity currentUser;
        public PianoMediaExporter(PianoMediaEntity pianoMedia, List<String> gruppi, UsersEntity user) {
            this.pianoMedia = pianoMedia;
            this.fileName = String.format("piano_media-%s", pianoMedia.getDescrizione());
            this.gruppi = gruppi;
            this.currentUser = user;
        }

        private File createTempFile() {
            try {
                return File.createTempFile(this.fileName, EXTENSION);
            } catch (Exception e) {
                log.error("Error creating temporary file", e);
            }
            return null;
        }

        public InputStream export() {
            File temp = createTempFile();
            Workbook wb = new XSSFWorkbook();
            boldFont = wb.createFont();
            boldFont.setBold(true);
            boldStyle = wb.createCellStyle();
            boldStyle.setFont(boldFont);


            fillMainSheet(wb);
            try (OutputStream fileOut = Files.newOutputStream(temp.toPath())) {
                wb.write(fileOut);
            } catch (IOException e) {
                log.error("Error flushing temporary data", e);
                throw new RuntimeException(e);
            }
            try {
                return new BufferedInputStream(new FileInputStream(temp));
            } catch (Exception e) {
                log.error("Error exporting piano media {}}", pianoMedia.getId(), e);
            }
            return null;
        }

        public String getFileName() {
            return String.format("%s.%s", this.fileName, EXTENSION);
        }

        private void fillMainSheet(Workbook wb) {
            Sheet sheet = wb.createSheet(WorkbookUtil.createSafeSheetName(pianoMedia.getDescrizione()));
            Row headers = createMainHeaders(sheet);
            fillSheet(sheet, headers);
        }

        private Row createMainHeaders(Sheet sheet) {
            Row headers = sheet.createRow(0);
            for (int i = 0; i < STANDARD_HEADERS.length; ++i) {
                headers.createCell(i).setCellValue(STANDARD_HEADERS[i]);
                headers.getCell(i).setCellStyle(boldStyle);
            }
            // colonne dinamiche:
            Map<Long, List<PianificazionePianoMediaEntity>> grouped = pianoMedia.getConfigurazioniPianoMedia().stream()
                    .collect(Collectors.groupingBy(e -> e.getSupportoMedia().getId()));
            grouped.forEach((idMedia, gantt) -> {
                // Ordino lista
                gantt.sort(Comparator.comparing(PianificazionePianoMediaEntity::getDataInizio));
                // Itero lista per costruire 'text' e aggiungere a 'pianificazionePiani'

                for (int i = 0; i < gantt.size(); i++) {
                    headers.createCell(headers.getLastCellNum()).setCellValue(
                            String.format("%s #%d", gantt.get(i).getSupportoMedia().getDescrizione(), i + 1)
                    );
                    headers.getCell(headers.getLastCellNum() - 1).setCellStyle(boldStyle);
                    dinamycHeaders.add(String.format("%s%d", PianoMediaPianificazioniResource.DYNAMIC_COL_RADIX, gantt.get(i).getId()));
                }
            });
            return headers;
        }

        private void fillSheet(Sheet sheet, Row headers) {
            List<PianoMediaDettaglioDTO> dettagli = pianoMediaDettaglioFactoryInstance.get()
                    .createPianoMediaDettaglioDTO(pianoMedia);

            // Security on view TUTTI | PROPRI
            if (!currentUser.isAdmin() && securityUtilsInstance.get().canViewOwnItemsOnly(currentUser)) {
                if (gruppi == null || gruppi.isEmpty()) {
                    final String msg = String.format("Nessun gruppo associato all'utente '%s'; impossibile determinare compratori e di conseguenza articoli",
                            currentUser.getName());
                    log.error(msg);
                    return ;
                }
                final List<String> buyers = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppo(gruppi);
                // Tengo solo gli articoli dei compratori associati ai gruppi dell'utente
                dettagli = dettagli.stream()
                        .filter(i -> buyers.contains(i.getCodiceCompratore()))
                        .collect(Collectors.toList());
            }


            dettagli.stream().sorted(Comparator.comparing(PianoMediaDettaglioDTO::getCodiceArticolo, Comparator.nullsLast(Comparator.reverseOrder())))
                    .forEach(dettaglio -> {
                Row row = sheet.createRow(sheet.getLastRowNum() + 1);
                createCell(row, dettaglio.isCivetta());
                createCell(row, String.format("%s-%s", dettaglio.getCodiceItem(), dettaglio.getDescrizioneArticolo()));
                createCell(row, dettaglio.getReparto());
                createCell(row, String.format("%s-%s", dettaglio.getCodiceCompratore(), dettaglio.getDescCompratore()));
                createCell(row, dettaglio.getPezzi());
                createCell(row, dettaglio.getPezzoRank());
                createCell(row, dettaglio.getFatturato());
                createCell(row, dettaglio.getFatturatoRank());
                createCell(row, dettaglio.isDifferenziato());

                if (dettaglio.isDifferenziato()) {
                    createCell(row, "");
                    createCell(row, "");
                    createCell(row, "");

                    createZonaSheet(sheet.getWorkbook(), dettaglio.getDescrizioneArticolo(), dettaglio.getDettaglioArticolo());
                } else {
                    createCell(row, dettaglio.getCodiceMeccanica());
                    createCell(row, dettaglio.getCodiceCondizione());
                    createCell(row, dettaglio.getValoreOfferta());
                }
                createCell(row, dettaglio.getDataInizio());
                createCell(row, dettaglio.getDataFine());
                if (dettaglio.isDifferenziato()) {
                    createCell(row, "");
                } else {
                    createCell(row, dettaglio.isFlVolantino());
                }
                createCell(row, dettaglio.getNoteCompratore());
                // colonne dinamiche
                String dinamicHeader = null;
                for (PianificazioneAnagraficaPianoMediaEntity anagrafica : dettaglio.getDettaglioPianificazione().getPianificazioniAnagrafiche()) {
                    dinamicHeader = String.format("%s%d", PianoMediaPianificazioniResource.DYNAMIC_COL_RADIX, anagrafica.getPianificazioneMedia().getId());
                    createCell(row, anagrafica.getAttivo(), dinamicHeader);
                }
            });
        }

        private void createCell(Row row, String value) {
            Cell c = row.createCell(getNextCellPos(row));
            if (value != null) {
                c.setCellValue(value);
            }
        }

        private void createCell(Row row, Integer value) {
            Cell c = row.createCell(getNextCellPos(row));
            if (value != null) {
                c.setCellValue(value);
            }
        }

        private void createCell(Row row, Double value) {
            Cell c = row.createCell(getNextCellPos(row));
            if (value != null) {
                c.setCellValue(value);
            }
        }

        private void createCell(Row row, Date value) {
            Cell c = row.createCell(getNextCellPos(row));
            if (value != null) {
                c.setCellValue(DateTimeUtils.getFormatoEsselunga().format(value));
            }
        }

        private void createCell(Row row, Boolean value) {
            Cell c = row.createCell(getNextCellPos(row));
            c.setCellValue(value ? "SI" : "NO");
        }

        private void createCell(Row row, Boolean value, String dinamicHeader) {
            int index = dinamycHeaders.indexOf(dinamicHeader);
            Cell c = null;
            if (index != -1) {
                c = row.createCell(PianoMediaExporterBuilder.STANDARD_HEADERS.length + index);
            } else {
                log.warn("Could not find dinamic header {}", dinamicHeader);
                c = row.createCell(getNextCellPos(row));
            }
            c.setCellValue(value ? "SI" : "NO");
        }

        private short getNextCellPos(Row row) {
            if (row.getLastCellNum() == -1) return 0;
            return row.getLastCellNum();
        }

        private void createZonaSheet(Workbook wb, String title, List<PianoMediaPromoArticoliDettaglioDbPromoDTO> dettagli) {
            Sheet diffSheet = wb.createSheet(WorkbookUtil.createSafeSheetName(title));
            createDiffSheetHeaders(diffSheet);
            for (int i = 0; i < dettagli.size(); ++i) {
                Row row = diffSheet.createRow(i + 1);
                PianoMediaPromoArticoliDettaglioDbPromoDTO dettaglio = dettagli.get(i);
                createCell(row, dettaglio.getZona());
                createCell(row, dettaglio.getCodiceMeccanica());
                createCell(row, dettaglio.getCodiceCondizione());
                createCell(row, dettaglio.getValore());
                createCell(row, dettaglio.getDataInizio());
                createCell(row, dettaglio.getDataFine());
                createCell(row, dettaglio.getFlVolantino());
            }
        }

        private void createDiffSheetHeaders(Sheet sheet) {
            Row row = sheet.createRow(0);
            createCell(row, "Zona");
            createCell(row, "Meccanica");
            createCell(row, "Condizione");
            createCell(row, "Valore Off");
            createCell(row, "Data Inizio");
            createCell(row, "Data Fine");
            createCell(row, "Volantino");

            for (short s = row.getFirstCellNum(); s < row.getLastCellNum(); ++s) {
                row.getCell(s).setCellStyle(boldStyle);
            }
        }
    }
}
