package com.axiante.mui.webapp.exporters.impl;

import com.axiante.mui.persistence.dto.GruppoUtenteDto;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.webapp.exporters.GroupsExporter;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoCanalePromoPojo;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoConAssociazioneExport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Dependent
public class GroupsExporterExcel implements GroupsExporter {
    private static final long serialVersionUID = -5889165733704747643L;

    @Override
    public InputStream produce(@NonNull List<GroupEntity> groups,
                               @NonNull List<GruppoCanalePromoPojo> channels,
                               @NonNull List<GruppoUtenteDto> users,
                               @NonNull List<GruppoConAssociazioneExport> buyers,
                               @NonNull List<GruppoConAssociazioneExport> reparti,
                               @NonNull List<GruppoConAssociazioneExport> grm) {
		Workbook wb = new XSSFWorkbook();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            createSheetGroups(wb, groups);
            createSheetChannels(wb, channels);
            createSheetUsers(wb, users);
            createSheet(wb, "Compratori", "Compratore", buyers);
            createSheet(wb, "Reparti", "Reparto", reparti);
            createSheet(wb, "Grm", "Grm", grm);
            wb.write(baos);
            return new ByteArrayInputStream(baos.toByteArray());
        } catch (IOException ex) {
            log.warn("Error producing export stream", ex);
        } finally {
            try {
                baos.close();
                wb.close();
            } catch (IOException ex1) {
                log.warn("Error closing stream", ex1);
            }
        }
        return null;
    }

    private void createSheetGroups(Workbook wb, List<GroupEntity> groups) {
        Sheet sheet = wb.createSheet("Gruppi");
        // Header
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Codice");
        row.createCell(1).setCellValue("Descrizione");
        for (int i = 0; i < groups.size(); i++) {
            row = sheet.createRow(i + 1);
            final GroupEntity group = groups.get(i);
            row.createCell(0).setCellValue(group.getCodiceGruppo());
            row.createCell(1).setCellValue(group.getDescrizione());
        }
        // Adjust column autoSize
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private void createSheetChannels(Workbook wb, @NonNull List<GruppoCanalePromoPojo> channels) {
        Sheet sheet = wb.createSheet("Canali");
        // Header
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Gruppo");
        row.createCell(1).setCellValue("Gruppo Promozione");
        row.createCell(2).setCellValue("Canale Promozione");
        row.createCell(3).setCellValue("Crea Promozione");
        row.createCell(4).setCellValue("Owner");
        for (int i = 0; i < channels.size(); i++) {
            row = sheet.createRow(i + 1);
            final GruppoCanalePromoPojo channel = channels.get(i);
            row.createCell(0).setCellValue(String.format("%s - %s",
                    channel.getGruppoCanale().getGruppo().getCodiceGruppo(),
                    channel.getGruppoCanale().getGruppo().getDescrizione()));
            row.createCell(1).setCellValue(String.format("%s - %s",
                    channel.getCodiceGruppoPromo(), channel.getDescGruppoPromo()));
            row.createCell(2).setCellValue(String.format("%d - %s",
                    channel.getGruppoCanale().getCanale().getCodiceCanale(),
                    channel.getGruppoCanale().getCanale().getDescrizione()));
            row.createCell(3).setCellValue(channel.getGruppoCanale().getCreate() ? "SI" : "NO");
            row.createCell(4).setCellValue(channel.getGruppoCanale().getOwner() ? "SI" : "NO");
        }
        // Adjust column autoSize
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
    }

    private void createSheetUsers(Workbook wb, List<GruppoUtenteDto> users) {
        Sheet sheet = wb.createSheet("Utenti");
        // Header
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Gruppo");
        row.createCell(1).setCellValue("Utente");
        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 1);
            final GruppoUtenteDto user = users.get(i);
            row.createCell(0).setCellValue(String.format("%s - %s",
                    user.getCodiceGruppo(), user.getDescrizioneGruppo()));
            row.createCell(1).setCellValue(concatenateCodeAndDesc(
                    user.getCodiceUtente(), user.getDescrizioneUtente()));
        }
        // Adjust column autoSize
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private void createSheet(Workbook wb, String sheetName, String headerLabel, List<GruppoConAssociazioneExport> items) {
        Sheet sheet = wb.createSheet(sheetName);
        // Header
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Gruppo");
        row.createCell(1).setCellValue(headerLabel);
        row.createCell(2).setCellValue("Tipo Accesso");
        for (int i = 0; i < items.size(); i++) {
            row = sheet.createRow(i + 1);
            final GruppoConAssociazioneExport c = items.get(i);
            row.createCell(0).setCellValue(String.format("%s - %s",
                    c.getCodiceGruppo(), c.getDescrizioneGruppo()));
            row.createCell(1).setCellValue(concatenateCodeAndDesc(
                    c.getCodiceAssociazione(), c.getDescrizioneAssociazione()));
            row.createCell(2).setCellValue(c.getTipoAccesso() != null
                    ? c.getTipoAccesso() : "");
        }
        // Adjust column autoSize
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
    }

    private String concatenateCodeAndDesc(String code, String desc) {
        if (code == null && desc == null) {
            return "";
        }
        if (code == null) {
            return desc;
        }
        if (desc == null) {
            return code;
        }
        return String.format("%s - %s", code, desc);
    }
}
