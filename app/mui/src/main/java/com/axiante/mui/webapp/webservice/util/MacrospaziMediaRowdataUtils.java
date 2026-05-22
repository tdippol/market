package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.dto.MacrospazioWithEventsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
@Slf4j
public class MacrospaziMediaRowdataUtils {
    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public String createRowData(List<MacrospazioWithEventsDTO> macrospazi) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";
        try {
            macrospazi.forEach(m -> arrayNode.add(createRowNode(m)));
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);
        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Visualizza Promozione' JSON row data", ex);
        }
        return json;
    }
    private ObjectNode createRowNode(MacrospazioWithEventsDTO macrospazio) {
        final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
        String cellTypeString = DBPromoCellTypeEnum.STRING.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("id").editable(false)
                .type(cellTypeString).value(String.valueOf(macrospazio.getId()))
                .build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("codice").editable(false).type(cellTypeString)
                .value(macrospazio.getCodice() == null ? "" : macrospazio.getCodice()).build();
        map.put("codice", cell);

        cell = DBPromoAgCell.builder().name("descrizione").editable(false).type(cellTypeString)
                .value(macrospazio.getDescrizione() == null ? "" : macrospazio.getDescrizione()).build();
        map.put("descrizione", cell);

        cell = DBPromoAgCell.builder().name("listino").editable(false).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .dataTypeParams(DataTypeParams.builder().precision(2).build())
                .value(macrospazio.getListino() == null ? "" : macrospazio.getListino().toString()).build();
        map.put("listino", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType()).value(macrospazio.getDataInizio() == null ? ""
                        : dateTimeUtils.toExcelDate(macrospazio.getDataInizio()))

                .build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType()).value(macrospazio.getDataFine() == null ? ""
                        : dateTimeUtils.toExcelDate(macrospazio.getDataFine()))
                .build();
        map.put("dataFine", cell);

        cell = DBPromoAgCell.builder().name("deleteEnabled").editable(false)
                .type(DBPromoCellTypeEnum.BOOLEAN.getType()).value(macrospazio.getHasEvents()
                        ? String.valueOf(false) : String.valueOf(true))
                .build();
        map.put("deleteEnabled", cell);

        //editEnabled: always true for macrospazi media
        cell = DBPromoAgCell.builder().name("editEnabled").editable(false)
                .type(DBPromoCellTypeEnum.BOOLEAN.getType()).value(String.valueOf(true))
                .build();
        map.put("editEnabled", cell);
        return JsonUtils.getMapper().valueToTree(map);
    }
}
