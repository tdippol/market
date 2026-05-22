package com.axiante.mui.dbpromo.business.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.service.UploadExcelService;
import com.axiante.mui.dbpromo.business.service.impl.data.ItemUpload;
import com.axiante.mui.dbpromo.business.service.impl.data.ShopItemUpload;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.GrmService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Dependent
public class UploadExcelServiceImpl implements UploadExcelService {

    private final String EXT_XLSX = "xlsx";
    private final String EXT_XLS = "xls";
    private final int FIRST_SHEET = 0;
    private final int FIRST_COLUMN = 0;

    @Inject
    private ItemService itemService;

    @Inject
    private GrmService grmService;

    @Inject
    private RepartoService repartoService;


    //TODO: set but never read
    private boolean emptyRowFound = false;

    @Override
    public Set<String> readFile(ElementType elementType, File file) throws IOException {
        Set<String> codes = new HashSet<>();
        final String ext = FilenameUtils.getExtension(file.getName());

        FileInputStream fis = new FileInputStream(file);
        Sheet sheet;
        switch (ext) {
            case EXT_XLSX:
                final XSSFWorkbook wb = new XSSFWorkbook(fis);
                sheet = wb.getSheetAt(FIRST_SHEET);
                break;
            case EXT_XLS:
                sheet = new HSSFWorkbook(fis).getSheetAt(FIRST_SHEET);
                break;
            default:
                throw new IOException("Unsupported File Type " + ext);
        }
        if (sheet != null) {
            Stream<Row> stream = StreamSupport.stream(sheet.spliterator(), false);
            codes = stream.skip(1).parallel().filter(Objects::nonNull).map(row -> row.getCell(FIRST_COLUMN))
                    .filter(Objects::nonNull).filter(cell -> cell.getCellType() == CellType.STRING.getCode())
                    .map(cell -> cell.getStringCellValue()).filter(code -> validate(elementType, code))
                    .collect(Collectors.toSet());
        }
        return codes;
    }

    @Override
    public boolean validate(final ElementType elementType, final String code) {
        if (elementType != null) {
            switch (elementType) {
                case ARTICOLO:
                    // query to find by code Articolo (Item)
                    if (itemService.findByCode(code) == null) {
                        return false;
                    }
                    break;
                case GRM:
                    // query to find by code Grm MUI_GRM
                    if (grmService.findByCode(code) == null) {
                        return false;
                    }
                    break;
                case REPARTO:
                    // query to find by code Reparto MUI_REPARTO
                    if (repartoService.findByCode(code) == null) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ItemUpload> readFileUntilEmptyRow(ElementType elementType, File file, Long buyerId, Integer maxRows) throws IOException {
        emptyRowFound = false;
        List<ItemUpload> codes = new ArrayList<>();
        final String ext = FilenameUtils.getExtension(file.getName());

        FileInputStream fis = new FileInputStream(file);
        Sheet sheet;
        switch (ext) {
            case EXT_XLSX:
                final XSSFWorkbook wb = new XSSFWorkbook(fis);
                sheet = wb.getSheetAt(FIRST_SHEET);
                break;
            case EXT_XLS:
                sheet = new HSSFWorkbook(fis).getSheetAt(FIRST_SHEET);
                break;
            default:
                throw new IOException("Unsupported File Type " + ext);
        }
        if (sheet != null) {
            Stream<Row> stream = StreamSupport.stream(sheet.spliterator(), false);
            final Iterator<String> iterator = stream.skip(1).map(row -> row.getCell(FIRST_COLUMN)).map(cell -> {
                        if (cell.getCellType() == CellType.STRING.getCode() || cell.getCellType() == CellType.BLANK.getCode()) {
                            return cell.getStringCellValue();
                        } else if (cell.getCellType() == CellType.NUMERIC.getCode()) {
                            return "" + ((int) cell.getNumericCellValue());
                        } else {
                            return null;
                        }
                    }).filter(Objects::nonNull)
                    .iterator();
            Integer rows = 0;
            if (iterator != null) {
                while (iterator.hasNext()) {
                    if (++rows > maxRows) {
                        codes.clear();
                        return null;
                    }
                    String code = iterator.next();
                    if (code.trim().isEmpty()) {
                        break;
                    }

                    codes.add(validateUpload(elementType, code, buyerId));
                }
            }
        }
        return codes;
    }

    @Override
    public ItemUpload validateUpload(ElementType elementType, String code, Long buyerId) {
        if (code == null || code.isEmpty()) {
            return new ItemUpload(code, "BLANK");
        }

        String entityId = null;
        if (elementType != null) {

            switch (elementType) {
                case ARTICOLO:
                    // query to find by code Articolo (Item)
                    ItemEntity item = itemService.findByCode(code);
                    if (item == null) {
                        return new ItemUpload(code, "STRING");
                    }

                    // MUI_ITEM fornitore Principale set to true
                    if (item.getMuiAssortimentoFornitores().stream()
                            .filter(assortimentoFornitoreEntity -> assortimentoFornitoreEntity.getFornitorePrincipale() != 0)
                            .collect(Collectors.toSet()).isEmpty()) {
                        return new ItemUpload(code, "STRING");
                    }
                    return new ItemUpload(item.getId(), buyerId,
                            item.getCodiceItem() == null ? ""
                                    : String.format("%s - %s", item.getCodiceItem(),
                                    item.getDescrizione() != null ? item.getDescrizione().toUpperCase() : ""),
                            String.valueOf(item.getId()), true);
                case GRM:
                    // query to find by code Grm MUI_GRM
                    final GrmEntity grmEntity = grmService.findByCode(code);
                    if (grmEntity == null) {
                        return new ItemUpload(code, "STRING");
                    }

                    return new ItemUpload(grmEntity.getId(), buyerId,
                            grmEntity.getCodiceGrm() == null ? ""
                                    : String.format("%s - %s", grmEntity.getCodiceGrm(),
                                    grmEntity.getDescrizione() != null ? grmEntity.getDescrizione().toUpperCase() : ""),
                            String.valueOf(grmEntity.getId()), true);
                case REPARTO:
                    // query to find by code Reparto MUI_REPARTO
                    // SOLO PER IL TIPO ELEMENTO REPARTO, i codice caricati da Excel vanno "paddati"
                    // a due digit
                    // riempendo di 0 a sinistra
                    // ( esempio , se l'utente carica 1 .. Va ricercato il valore '01' )
                    code = String.format("%02d", Integer.parseInt(code));

                    final RepartoEntity repartoEntity = repartoService.findByCode(code);
                    if (repartoEntity == null) {
                        return new ItemUpload(code, "STRING");
                    }

                    return new ItemUpload(repartoEntity.getId(), buyerId, repartoEntity.getCodiceReparto() == null ? ""
                            : String.format("%s - %s", repartoEntity.getCodiceReparto(),
                            repartoEntity.getDescrizione() != null ? repartoEntity.getDescrizione().toUpperCase() : ""),
                            String.valueOf(repartoEntity.getId()), true);
                default:
                    //TODO: TOTALE needs something ??
                    break;
            }
            return new ItemUpload(entityId, true, "STRING");

        } else {
            return new ItemUpload(entityId, true, "STRING");
        }

    }

    @Override
    public Set<ShopItemUpload> readFileUntilEmptyRow(File file, Set<String> promoNegoziSigles, Integer maxRows) throws IOException {
        emptyRowFound = false;
        Set<ShopItemUpload> codes = new HashSet<>();
        final String ext = FilenameUtils.getExtension(file.getName());

        FileInputStream fis = new FileInputStream(file);
        Sheet sheet;
        switch (ext) {
            case EXT_XLSX:
                final XSSFWorkbook wb = new XSSFWorkbook(fis);
                sheet = wb.getSheetAt(FIRST_SHEET);
                break;
            case EXT_XLS:
                sheet = new HSSFWorkbook(fis).getSheetAt(FIRST_SHEET);
                break;
            default:
                throw new IOException("Unsupported File Type " + ext);
        }

        if (sheet != null) {
            Stream<Row> stream = StreamSupport.stream(sheet.spliterator(), false);

            final Iterator<String> iterator = stream.skip(1).map(row -> row.getCell(FIRST_COLUMN))
                    .filter(Objects::nonNull)
                    .filter(cell -> cell.getCellType() == CellType.STRING.getCode()
                            || cell.getCellType() == CellType.BLANK.getCode())
                    .map(cell -> cell.getStringCellValue()).iterator();
            Integer rows = 0;
            while (iterator.hasNext()) {
                if (++rows > maxRows) {
                    // limite raggiunto.
                    codes.clear();
                    return null;
                }
                String code = iterator.next();
                if (code.trim().isEmpty()) {
                    break;
                }
                if (promoNegoziSigles.contains(code)) {
                    codes.add(new ShopItemUpload(code, true));
                } else {
                    codes.add(new ShopItemUpload(code, false));
                }
            }
        }
        return codes;
    }

}
