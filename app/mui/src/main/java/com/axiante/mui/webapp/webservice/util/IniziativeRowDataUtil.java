package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.utils.IniziativaAcl;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class IniziativeRowDataUtil extends AbstractRowDataUtil {

    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createIniziativeRowData(@NonNull List<IniziativaEntity> iniziative) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            iniziative.forEach(i -> arrayNode.add(createIniziativaRowNode(i)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available iniziative", ex);
            return "";
        }
    }

    public String createIniziativaStatiRowData(Set<IniziativaStatoEntity> stati) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            stati.stream().sorted(Comparator.comparing(IniziativaStatoEntity::getDataInizioStato).reversed())
                    .forEach(s -> arrayNode.add(createIniziativaStatoRowNode(s)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available stati", ex);
            return "";
        }
    }

    public String createIniziativaTotalizzatoriRowData(Collection<TotalizzatoriEntity> totalizzatori) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            totalizzatori.stream().sorted(Comparator.comparing(TotalizzatoriEntity::getDescrizione))
                    .forEach(t -> arrayNode.add(createIniziativaTotalizzatoreRowNode(t)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available totalizzatori", ex);
            return "";
        }
    }

    private ObjectNode createIniziativaRowNode(IniziativaEntity iniziativa) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellStringType)
                    .value(String.valueOf(iniziativa.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellStringType)
                    .value(iniziativa.getDescrizione()).build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("Nome Oggetto").editable(false).type(cellStringType)
                    .value(iniziativa.getOggetto()).build();
            map.put("oggetto", cell);

            cell = DBPromoAgCell.builder().name("Decimali").editable(false).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                    .value(String.valueOf(iniziativa.getFlagDecimale())).build();
            map.put("decimali", cell);

            cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellDateType)
                    .value(dateTimeUtils.toExcelDate(iniziativa.getDataInizio())).build();
            map.put("dataInizio", cell);

            cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellDateType)
                    .value(dateTimeUtils.toExcelDate(iniziativa.getDataFine())).build();
            map.put("dataFine", cell);

            cell = DBPromoAgCell.builder().name("Note").editable(false).type(cellStringType)
                    .value(nullableValue(iniziativa.getNote())).build();
            map.put("note", cell);

            final StatoPromozioneEntity statoCorrente = IniziativaAcl.getStatoCorrente(iniziativa);
            cell = DBPromoAgCell.builder().name("Stato").editable(false).type(cellStringType)
                    .value(statoCorrente == null || statoCorrente.getCodiceStato() == null || statoCorrente.getDescrizione() == null
                            ? "" : statoCorrente.fullDescription())
                    .build();
            map.put("stato", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for iniziativa with id '%d'", iniziativa.getId()), ex);
        }

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createIniziativaStatoRowNode(IniziativaStatoEntity stato) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellDateType = DBPromoCellTypeEnum.DATE.getType();
        final DataTypeParams dataTypeParams = DataTypeParams.builder()
                .dateFormat(DbPromoConstants.DBPROMO_STRING_DATE_FORMAT).build();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Stato").editable(false).type(cellStringType)
                .value(lookupStato(stato.getStatoPromo())).build();
        map.put("stato", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio Stato").editable(false).type(cellDateType)
                .value(dateTimeUtils.toExcelDate(stato.getDataInizioStato()))
                .dataTypeParams(dataTypeParams).build();
        map.put("dataInizioStato", cell);

        cell = DBPromoAgCell.builder().name("Data Fine Stato").editable(false).type(cellDateType)
                .value(dateTimeUtils.toExcelDate(stato.getDataFineStato()))
                .dataTypeParams(dataTypeParams).build();
        map.put("dataFineStato", cell);

        cell = DBPromoAgCell.builder().name("Utente").editable(false).type(cellStringType)
                .value(stato.getCodiceUtenteInserimento()).build();
        map.put("utente", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createIniziativaTotalizzatoreRowNode(TotalizzatoriEntity totalizzatore) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("ID Totalizzatore").editable(false).type(cellStringType)
                .value(nullableValue(totalizzatore.getId())).build();
        map.put("idTotalizzatore", cell);

        cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellStringType)
                .value(nullableValue(totalizzatore.getDescrizione())).build();
        map.put("descrizione", cell);

        cell = DBPromoAgCell.builder().name("Action Type").editable(false).type(cellStringType)
                .value(nullableValue(totalizzatore.getActionType())).build();
        map.put("actionType", cell);

        cell = DBPromoAgCell.builder().name("Segno").editable(false).type(cellStringType)
                .value(nullableValue(totalizzatore.getSegno())).build();
        map.put("segno", cell);

        cell = DBPromoAgCell.builder().name("Parent").editable(false).type(cellStringType)
                .value(nullableValue(totalizzatore.getIdParent())).build();
        map.put("idParent", cell);

        cell = DBPromoAgCell.builder().name("Export Vs").editable(false).type(cellStringType)
                .value(nullableValue(totalizzatore.getExportVs())).build();
        map.put("exportVs", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private String lookupStato(StatoPromozioneEntity statoPromo) {
        return statoPromo != null ? statoPromo.fullDescription() : "";
    }
}
