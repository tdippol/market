package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.webapp.utils.EventoRetailUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
@Slf4j
public class EventiMediaRowdataUtils {
    private DateTimeUtils dateTimeUtils = new DateTimeUtils();
    private EventoRetailUtils eventoRetailUtils = new EventoRetailUtils();

    public String createRowData(List<EventoRetailDTO> eventi) {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        String json = "";
        try {
            eventi.forEach(m -> arrayNode.add(createRowNode(m)));
            final ObjectNode node = JsonUtils.getMapper().createObjectNode();
            node.set(DbPromoConstants.ROW_DATA, arrayNode);
            json = JsonUtils.getMapper().writeValueAsString(node);
        } catch (JsonProcessingException ex) {
            log.error("Error processing 'Visualizza Promozione' JSON row data", ex);
        }
        return json;
    }
    private ObjectNode createRowNode(EventoRetailDTO evento) {
        final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
        String cellTypeString = DBPromoCellTypeEnum.STRING.getType();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("id").editable(false)
                .type(cellTypeString).value(String.valueOf(evento.getId()))
                .build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("id_macrospazio").editable(false)
                .type(cellTypeString).value(String.valueOf(evento.getMacrospazio().getId()))
                .build();
        map.put("id_macrospazio", cell);

        cell = DBPromoAgCell.builder().name("macrospazio").editable(false)
                .type(cellTypeString).value(evento.getMacrospazioString())
                .build();
        map.put("macrospazio", cell);

        cell = DBPromoAgCell.builder().name("fornitore").editable(false).type(cellTypeString)
                .value(evento.getFornitoreString() == null ? "" : evento.getFornitoreString()).build();
        map.put("fornitore", cell);

        cell = DBPromoAgCell.builder().name("Data Inizio").editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType()).value(evento.getDataInizio() == null ? ""
                        : dateTimeUtils.toExcelDate(evento.getDataInizio()))
                .build();
        map.put("dataInizio", cell);

        cell = DBPromoAgCell.builder().name("Data Fine").editable(false)
                .type(DBPromoCellTypeEnum.DATE.getType()).value(evento.getDataFine() == null ? ""
                        : dateTimeUtils.toExcelDate(evento.getDataFine()))
                .build();
        map.put("dataFine", cell);

        cell = DBPromoAgCell.builder().name("causale").editable(false).type(cellTypeString)
                .value(evento.getCausaleString() == null ? "" : evento.getCausaleString()).build();
        map.put("causale", cell);

        cell = DBPromoAgCell.builder().name("contributo").editable(false).type(DBPromoCellTypeEnum.NUMERIC.getType())
                .dataTypeParams(DataTypeParams.builder().precision(2).build())
                .value(evento.getValoreContributo() == null
                        ? ""
                        : String.valueOf(evento.getValoreContributo().doubleValue())).build();
        map.put("contributo", cell);

        cell = DBPromoAgCell.builder().name("note").editable(false).type(cellTypeString)
                .value(evento.getNote() == null ? "" : evento.getNote()).build();
        map.put("note", cell);

        cell = DBPromoAgCell.builder().name("editEnabled").editable(false).type(DBPromoCellTypeEnum.BOOLEAN.getType())
                .value(String.valueOf(eventoRetailUtils.isEditable(evento))).build();
        map.put("editEnabled", cell);

        cell = DBPromoAgCell.builder().name("deleteEnabled").editable(false).type(DBPromoCellTypeEnum.BOOLEAN.getType())
                .value(String.valueOf(eventoRetailUtils.isDeletable(evento))).build();
        map.put("deleteEnabled", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }
}
