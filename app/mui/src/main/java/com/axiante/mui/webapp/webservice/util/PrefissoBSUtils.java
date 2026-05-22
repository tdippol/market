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
public class PrefissoBSUtils {

    //TODO: @Mauro Giacomini: cancellami perche' sono la copia (aggiustata) di ReportBuonoInPromoRowDataUtil, ma se prendevo il file pari pari poi
    // ti si incasinava il mondo: non potevo fare la cherry pick di tutta la commit

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

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Anno").editable(false).type(cellTypeString)
                .value(entity.getAnno()).build();
        map.put("anno", cell);

        cell = DBPromoAgCell.builder().name("Codice Canale").editable(false).type(cellTypeString)
                .value(entity.getCodiceCanale().toString()).invisible(true).build();
        map.put("codice_canale", cell);

        cell = DBPromoAgCell.builder().name("Canale").editable(false).type(cellTypeString)
                .value(entity.getDescrizioneCanale()).build();
        map.put("canale", cell);

        cell = DBPromoAgCell.builder().name("Codice Gruppo").editable(false).type(cellTypeString)
                .value(entity.getCodiceGruppo()).invisible(true).build();
        map.put("codice_gruppo", cell);

        cell = DBPromoAgCell.builder().name("Gruppo").editable(false).type(cellTypeString)
                .value(entity.getDescrizioneGruppo()).build();
        map.put("gruppo", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false).type(cellTypeDate)
                .value(entity.getDataInizio() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataInizio())).build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("InnerDataInizio").editable(false).type(cellTypeDate).invisible(true)
                .value(entity.getDataInizio() == null ? "" : DateTimeUtils.getFormatoEsselunga().format(entity.getDataInizio())).build();
        map.put("innerDataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false).type(cellTypeDate)
                .value(entity.getDataFine() == null ? "" : dateTimeUtils.toExcelDate(entity.getDataFine())).build();
        map.put("dataFine", cell);

        cell = DBPromoAgCell.builder().name("InnerDataFine").editable(false).type(cellTypeDate).invisible(true)
                .value(entity.getDataFine() == null ? "" : DateTimeUtils.getFormatoEsselunga().format(entity.getDataFine())).build();
        map.put("innerDataFine", cell);

        cell = DBPromoAgCell.builder().name("Codice Promozione").editable(false).type(cellTypeString)
                .value(entity.getId().getCodicePromozione()).build();
        map.put("codice_promozione", cell);

        cell = DBPromoAgCell.builder().name("Promozione").editable(false).type(cellTypeString)
                .value(entity.getDescrizionePromozione()).build();
        map.put("promozione", cell);

        cell = DBPromoAgCell.builder().name("Prefisso BS").editable(false).type(cellTypeString)
                .value(entity.getId().getPrefissoBS()).build();
        map.put("prefisso_bs", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }
}

