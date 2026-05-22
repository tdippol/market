package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
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
public class CustomerJourneyRowDataUtil extends AbstractRowDataUtil {
    public String createSottoclassiRowData(@NonNull List<SottoclasseEntity> sottoclassi, List<SottoclasseCountDto> sottoclassiUsed) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            final ObjectNode objectNode = mapper.createObjectNode();
            sottoclassi.forEach(sottoclasse -> {
                boolean used = sottoclassiUsed.stream().anyMatch(sc -> sc.getCodice().equals(sottoclasse.getCodice()));
                arrayNode.add(createSottoclasseNode(sottoclasse, used));
            });
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for 'Sottoclassi'", ex);
            return "";
        }
    }

    private ObjectNode createSottoclasseNode(SottoclasseEntity sottoclasse, boolean used) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final String cellNumericType = DBPromoCellTypeEnum.NUMERIC.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellStringType)
                    .value(String.valueOf(sottoclasse.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Sottoclasse").editable(false).type(cellStringType)
                    .value(sottoclasse.getCodice()).build();
            map.put("sottoclasse", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellStringType)
                    .value(sottoclasse.getDescrizione()).build();
            map.put("descrizione", cell);

            cell = DBPromoAgCell.builder().name("Priorità").editable(true).type(cellNumericType)
                    .dataTypeParams(DataTypeParams.builder().precision(0).minLength(1).length(999).build())
                    .value(nullableValue(sottoclasse.getPriorita())).build();
            map.put("priorita", cell);

            cell = DBPromoAgCell.builder().name("Delay").editable(true).type(cellNumericType)
                    .dataTypeParams(DataTypeParams.builder().precision(0).length(999).build())
                    .value(nullableValue(sottoclasse.getDelay())).build();
            map.put("delay", cell);

            cell = DBPromoAgCell.builder().name("Attiva").editable(!used).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                    .value(boolFlagAsString(sottoclasse.getAttiva())).build();
            map.put("attiva", cell);

            cell = DBPromoAgCell.builder().name("Data Scarico").editable(false).type(DBPromoCellTypeEnum.DATE.getType())
                    .value(nullableValue(sottoclasse.getDataScarico())).build();
            map.put("dataScarico", cell);
        } catch (Exception ex) {
            log.error("Error creating row node for 'SottoclasseEntity' with id " + sottoclasse.getId(), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }
}
