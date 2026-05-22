package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
@Slf4j
public class SottoscrizioniRowDataUtil extends AbstractRowDataUtil {
    public String createSottoscrizioniRowData(@NonNull List<SottoscrizioneEntity> sottoscrizioni) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            sottoscrizioni.stream()
                    .sorted(Comparator.comparing(SottoscrizioneEntity::getCodice))
                    .forEach(sottoscrizione -> arrayNode.add(createSottoscrizioneRowNode(sottoscrizione)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'SottoscrizioneEntity'", ex);
            return "";
        }
    }

    private ObjectNode createSottoscrizioneRowNode(SottoscrizioneEntity sottoscrizione) {
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellTypeString)
                    .value(String.valueOf(sottoscrizione.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Codice").editable(false).type(cellTypeString)
                    .value(nullableValue(sottoscrizione.getCodice())).build();
            map.put("codice", cell);

            cell = DBPromoAgCell.builder().name("Descrizione").editable(false).type(cellTypeString)
                    .value(nullableValue(sottoscrizione.getDescrizione())).build();
            map.put("descrizione", cell);

            boolean deletable = sottoscrizione.getPromozioni() == null || sottoscrizione.getPromozioni().isEmpty();
            cell = DBPromoAgCell.builder().name("RemoveEnabled").editable(false).type(cellTypeString)
                    .value(String.valueOf(deletable)).build();
            map.put("removeEnabled", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating JSON rowData for sottoscrizione with id '%d'",
                    sottoscrizione.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }
}
