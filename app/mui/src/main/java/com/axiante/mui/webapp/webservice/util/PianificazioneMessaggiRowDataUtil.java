package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.DescCategoriaBuoniEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
@Slf4j
public class PianificazioneMessaggiRowDataUtil extends AbstractRowDataUtil {
    public String createMessaggiRowData(@NonNull List<CastellettoMessaggiEntity> messaggi) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            messaggi.forEach(m -> arrayNode.add(createMessaggioRowNode(m)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'messaggi pianificazione'", ex);
            return "";
        }
    }

    public String createCategoriaBuoniRowData(@NonNull List<DescCategoriaBuoniEntity> categoriaBuoni) {
        return createCategoriaBuoniRowData(categoriaBuoni, null);
    }

    public String createCategoriaBuoniRowData(@NonNull List<DescCategoriaBuoniEntity> categoriaBuoni, String errorMessage) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            categoriaBuoni.forEach(cb -> arrayNode.add(createCategoriaBuoniRowNode(cb)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            if (errorMessage != null) {
                DbPromoAgCellUtils.putValue(objectNode, "errorMessage", errorMessage, false);
            }
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'categoria buoni'", ex);
            return "";
        }
    }

    public boolean updateCategoriaBuoniEntity(@NonNull DescCategoriaBuoniEntity categoriaBuoni, JsonNode jsonNode) {
        String column = jsonNode.get("column") != null ? jsonNode.get("column").asText() : null;
        String value = jsonNode.get("value") != null ? jsonNode.get("value").asText() : null;
        if (column == null) {
            log.error(String.format("Error getting column from json %s", jsonNode));
            return false;
        }
        switch (column.toUpperCase()) {
            case "CATMINUSC":
                categoriaBuoni.setCatMinusc(value);
                break;
            case "CATMAIUSC":
                categoriaBuoni.setCatMaiusc(value);
                break;
            case "CATDESCESTESA":
                categoriaBuoni.setCatDescEstesa(value);
                break;
            default:
                log.error(String.format("Column %s not managed", column));
                return false;
        }
        return true;
    }

    private ObjectNode createMessaggioRowNode(CastellettoMessaggiEntity messaggio) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellCheckboxType = DBPromoCellTypeEnum.CHECKBOX.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        try {
            DBPromoAgCell cell =
                    DBPromoAgCell.builder()
                            .name("ID")
                            .editable(false)
                            .type(cellStringType)
                            .value(String.valueOf(messaggio.getId()))
                            .build();
            map.put("id", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Id Messaggio")
                            .editable(false)
                            .type(DBPromoCellTypeEnum.NUMERIC.getType())
                            .dataTypeParams(DataTypeParams.builder().precision(0).build())
                            .value(String.valueOf(messaggio.getIdMessaggio()))
                            .build();
            map.put("idMessaggio", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Sequenza Stampa")
                            .editable(false)
                            .type(cellStringType)
                            .value(String.valueOf(messaggio.getSeqStampa()))
                            .build();
            map.put("sequenzaStampa", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Testo")
                            .editable(false)
                            .type(cellStringType)
                            .value(nullableValue(messaggio.getTesto()))
                            .build();
            map.put("testo", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Taglio Carta")
                            .editable(false)
                            .type(cellCheckboxType)
                            .value(nullableValue(messaggio.getTaglioCarta()))
                            .build();
            map.put("taglioCarta", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Barcode")
                            .editable(false)
                            .type(cellCheckboxType)
                            .value(nullableValue(messaggio.getBarcode()))
                            .build();
            map.put("barcode", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Font")
                            .editable(false)
                            .type(cellStringType)
                            .value(messaggio.getFont() != null ? messaggio.getFont().getValue() : "")
                            .build();
            map.put("font", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Allineamento")
                            .editable(false)
                            .type(cellStringType)
                            .value(messaggio.getAllineamento() != null ? messaggio.getAllineamento().getValue() : "")
                            .build();
            map.put("allineamento", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Bold")
                            .editable(false)
                            .type(cellCheckboxType)
                            .value(nullableValue(messaggio.getBold()))
                            .build();
            map.put("bold", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Logo")
                            .editable(false)
                            .type(cellStringType)
                            .value(nullableValue(messaggio.getLogo()))
                            .build();
            map.put("logo", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Font Stile")
                            .editable(false)
                            .type(cellStringType)
                            .value(messaggio.getFontStile() != null ? messaggio.getFontStile().getColore() : "")
                            .build();
            map.put("fontStile", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Bottone")
                            .editable(false)
                            .type(cellStringType)
                            .value(messaggio.getBottone() != null ? nullableValue(messaggio.getBottone().getDescrizione()) : "")
                            .build();
            map.put("bottone", cell);
            cell =
                    DBPromoAgCell.builder()
                            .name("Codice")
                            .editable(false)
                            .type(cellCheckboxType)
                            .value(nullableValue(messaggio.getCodice()))
                            .build();
            map.put("codice", cell);
            cell =
                    DBPromoAgCell.builder()
                            .name("Regolamento")
                            .editable(false)
                            .type(cellStringType)
                            .value(nullableValue(messaggio.getRegolamento()))
                            .build();
            map.put("regolamento", cell);
            cell =
                    DBPromoAgCell.builder()
                            .name("Barra")
                            .editable(false)
                            .type(cellCheckboxType)
                            .value(nullableValue(messaggio.getBarra()))
                            .build();
            map.put("barra", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Variabile")
                            .editable(false)
                            .type(cellStringType)
                            .value(nullableValue(messaggio.getVariabile()))
                            .build();
            map.put("variabile", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Font Entity")
                            .editable(false)
                            .type(cellStringType)
                            .value(nullableValue(messaggio.getFontEntity()))
                            .build();
            map.put("fontEntity", cell);
        } catch (Exception ex) {
            log.error(String.format("Error row for messaggio with id %s", messaggio.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createCategoriaBuoniRowNode(DescCategoriaBuoniEntity categoriaBuoni) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID Promozione").editable(false).type(cellStringType)
                    .value(String.valueOf(categoriaBuoni.getIdPromozione())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Categoria Minuscola").editable(true).type(cellStringType)
                    .dataTypeParams(DataTypeParams.builder().length(400).build())
                    .value(nullableValue(categoriaBuoni.getCatMinusc())).build();
            map.put("catMinusc", cell);

            cell = DBPromoAgCell.builder().name("Categoria Maiuscola").editable(true).type(cellStringType)
                    .dataTypeParams(DataTypeParams.builder().length(400).build())
                    .value(nullableValue(categoriaBuoni.getCatMaiusc())).build();
            map.put("catMaiusc", cell);

            cell = DBPromoAgCell.builder().name("Categoria Descrizione Estesa").editable(true).type(cellStringType)
                    .dataTypeParams(DataTypeParams.builder().length(400).build())
                    .value(nullableValue(categoriaBuoni.getCatDescEstesa())).build();
            map.put("catDescEstesa", cell);
        } catch (Exception ex) {
            log.error(String.format("Error row for categoriaBuoni with idPromozione %s", categoriaBuoni.getIdPromozione()),
                    ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    public String createCastellettiRowData(@NonNull List<CastellettoMessaggiEntity> messaggi) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            messaggi.forEach(m -> arrayNode.add(createCastellettoRowNode(m)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'messaggi pianificazione'", ex);
            return "";
        }
    }

    private ObjectNode createCastellettoRowNode(CastellettoMessaggiEntity messaggio) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        try {
            DBPromoAgCell cell =
                    DBPromoAgCell.builder()
                            .name("ID")
                            .editable(false)
                            .type(cellStringType)
                            .value(String.valueOf(messaggio.getId()))
                            .build();
            map.put("id", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Sequenza Stampa")
                            .editable(false)
                            .type(cellStringType)
                            .value(String.valueOf(messaggio.getSeqStampa()))
                            .build();
            map.put("sequenzaStampa", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Sezione")
                            .editable(false)
                            .type(cellStringType)
                            .value(String.valueOf(messaggio.getSezione()))
                            .build();
            map.put("sezione", cell);

            cell =
                    DBPromoAgCell.builder()
                            .name("Testo")
                            .editable(false)
                            .type(cellStringType)
                            .value(nullableValue(messaggio.getTesto()))
                            .build();
            map.put("testo", cell);

        } catch (Exception ex) {
            log.error(String.format("Error row for messaggio with id %s", messaggio.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }
}
