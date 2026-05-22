package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class ReportBuonoInPromoRowDataUtil {
    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createBuonoInPromoRowData(@NonNull List<MuiReportBSEntity> entities) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
        entities.forEach(entity -> arrayNode.add(createBuonoInPromoObjectNode(entity)));
        objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
        try {
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error processing rowData for 'Buono in Promo'", ex);
        }
        return "";
    }

    private ObjectNode createBuonoInPromoObjectNode(MuiReportBSEntity entity) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final String cellTypeDate = DBPromoCellTypeEnum.DATE.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellTypeString)
                .value(String.valueOf(entity.getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("Anno").editable(false).type(cellTypeString)
                .value(entity.getAnno()).build();
        map.put("anno", cell);

        cell = DBPromoAgCell.builder().name("Canale").editable(false).type(cellTypeString)
                .value(entity.getDescrizioneCanale()).build();
        map.put("canale", cell);

        cell = DBPromoAgCell.builder().name("Promozione").editable(false).type(cellTypeString)
                .value(entity.getDescrizionePromozione()).build();
        map.put("descrizioneEstesa", cell);

        cell = DBPromoAgCell.builder().name("Gruppo Promozionale").editable(false).type(cellTypeString)
                .value(String.format("%s - %s", entity.getCodiceGruppo(), entity.getDescrizioneGruppo())).build();
        map.put("gruppo", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellTypeDate)
                .value(entity.getDataInizio() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataInizio())).build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellTypeDate)
                .value(entity.getDataFine() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataFine())).build();
        map.put("dataFine", cell);

        cell = DBPromoAgCell.builder().name("Stato Promozione").editable(false).type(cellTypeString)
                .value(String.format("%s - %s", entity.getCodiceStato(), entity.getDescrizioneStato())).build();
        map.put("stato", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }
}
