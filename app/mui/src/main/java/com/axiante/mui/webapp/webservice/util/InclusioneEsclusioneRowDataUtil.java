package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.MuiInclusioneEsclusioneEntity;
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
public class InclusioneEsclusioneRowDataUtil extends AbstractRowDataUtil{

    public String createRowData(@NonNull List<MuiInclusioneEsclusioneEntity> rows) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            rows.forEach(i -> arrayNode.add(createRowNode(i)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available iniziative", ex);
            return "";
        }
    }

    private ObjectNode createRowNode(MuiInclusioneEsclusioneEntity inclusioneEsclusione) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("ID").editable(false).type(cellStringType)
                    .value(String.valueOf(inclusioneEsclusione.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("TIPO").editable(false).type(cellStringType)
                    .value(inclusioneEsclusione.getTipo().name()).build();
            map.put("tipo", cell);

            cell = DBPromoAgCell.builder().name("Promozione").editable(false).type(cellStringType)
                    .value(inclusioneEsclusione.getPromozione().getDescrizioneEstesa()).build();
            map.put("promozione", cell);

            cell = DBPromoAgCell.builder().name("Tipo Elemento").editable(false).type(cellStringType)
                    .value(inclusioneEsclusione.getTipoElemento().getDescription()).build();
            map.put("tipoElemento", cell);

            cell = DBPromoAgCell.builder().name("Codice Elemento").editable(false).type(cellStringType)
                    .value(inclusioneEsclusione.getCodiceElemento()).build();
            map.put("codiceElemento", cell);

            cell = DBPromoAgCell.builder().name("Elemento").editable(false).type(cellStringType)
                    .value(String.valueOf(inclusioneEsclusione.getElemento())).build();
            map.put("elemento", cell);

            cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellStringType)
                    .value(inclusioneEsclusione.getMeccanicaEntity().getDescrizione()).build();
            map.put("meccanica", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating row node for iniziativa with id '%d'", inclusioneEsclusione.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }
}
