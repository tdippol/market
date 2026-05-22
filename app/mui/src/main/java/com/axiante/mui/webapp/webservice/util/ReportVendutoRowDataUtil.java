package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class ReportVendutoRowDataUtil extends AbstractRowDataUtil {

    public String createRowData(@NonNull List<ReportVendutoEntity> entities) {
        try {
            final ObjectMapper mapper = JsonUtils.getMapper();
            final ArrayNode arrayNode = mapper.createArrayNode();
            entities.stream().sorted(Comparator.comparing(e -> e.getId().getCodiceItem()))
                    .forEach(e -> arrayNode.add(createRowNode(e)));
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating rowData for report venduto", ex);
            return "";
        }
    }

    private ObjectNode createRowNode(ReportVendutoEntity entity) {
        final String cellStringType = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Codice Articolo").editable(false).type(cellStringType)
                .value(nullableValue(entity.getId().getCodiceItem())).build();
        map.put("codiceArticolo", cell);

        cell = DBPromoAgCell.builder().name("Descrizione Articolo").editable(false).type(cellStringType)
                .value(nullableValue(entity.getId().getDescrizioneItem())).build();
        map.put("descrizioneArticolo", cell);

        cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellStringType)
                .value(codeAndDescription(entity.getId().getCodiceCompratore(), entity.getId().getDescrizioneCompratore())).build();
        map.put("compratore", cell);

        cell = DBPromoAgCell.builder().name("Fornitore").editable(false).type(cellStringType)
                .value(codeAndDescription(entity.getId().getCodiceFornitore(), entity.getId().getDescrizioneFornitore())).build();
        map.put("fornitore", cell);

        cell = DBPromoAgCell.builder().name("Reparto").editable(false).type(cellStringType)
                .value(codeAndDescription(entity.getId().getCodiceReparto(), entity.getId().getDescrizioneReparto())).build();
        map.put("reparto", cell);

        cell = DBPromoAgCell.builder().name("Meccanica").editable(false).type(cellStringType)
                .value(codeAndDescription(entity.getId().getCodiceMeccanica(), entity.getId().getDescrizioneMeccanica())).build();
        map.put("meccanica", cell);

        cell = DBPromoAgCell.builder().name("Condizione Meccanica").editable(false).type(cellStringType)
                .value(nullableValue(entity.getCondizioneMeccanica())).build();
        map.put("condizioneMeccanica", cell);

        cell = DBPromoAgCell.builder().name("Misura").editable(false).type(cellStringType)
                .value(nullableValue(entity.getMisura())).build();
        map.put("misura", cell);

        cell = DBPromoAgCell.builder().name("Pezzatura").editable(false).type(cellStringType)
                .value(nullableValue(entity.getPezzatura())).build();
        map.put("pezzatura", cell);

        cell = DBPromoAgCell.builder().name("Pezzi KG").editable(false).type(cellStringType)
                .value(nullableValue(entity.getPezziKg())).build();
        map.put("pezziKg", cell);

        cell = DBPromoAgCell.builder().name("Colli").editable(false).type(cellStringType)
                .value(nullableValue(entity.getColli())).build();
        map.put("colli", cell);

        cell = DBPromoAgCell.builder().name("Venduto Promo").editable(false).type(cellStringType)
                .value(nullableValue(entity.getVendutoPromo())).build();
        map.put("vendutoPromo", cell);

        cell = DBPromoAgCell.builder().name("Sconto Erogato Totale").editable(false).type(cellStringType)
                .value(nullableValue(entity.getScontoErogatoTotale())).build();
        map.put("scontoErogatoTotale", cell);

        cell = DBPromoAgCell.builder().name("Margine Lordo").editable(false).type(cellStringType)
                .value(nullableValue(entity.getMargineLordo())).build();
        map.put("margineLordo", cell);

        cell = DBPromoAgCell.builder().name("Margine Lordo %").editable(false).type(cellStringType)
                .value(nullableValue(entity.getMargineLordoPerc())).build();
        map.put("margineLordoPerc", cell);

        cell = DBPromoAgCell.builder().name("Redenzione").editable(false).type(cellStringType)
                .value(nullableValue(entity.getRedemption())).build();
        map.put("redemption", cell);

        cell = DBPromoAgCell.builder().name("Prezzo Promo").editable(false).type(cellStringType)
                .value(nullableValue(entity.getPrezzoPromo())).build();
        map.put("prezzoPromo", cell);

        cell = DBPromoAgCell.builder().name("In Out").editable(false).type(cellStringType)
                .value(nullableValue(entity.getInOut())).build();
        map.put("inOut", cell);

        cell = DBPromoAgCell.builder().name("Iva").editable(false).type(cellStringType)
                .value(nullableValue(entity.getIva())).build();
        map.put("iva", cell);

        cell = DBPromoAgCell.builder().name("Numero Negozi").editable(false).type(cellStringType)
                .value(nullableValue(entity.getNumeroNegozi())).build();
        map.put("numeroNegozi", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    private String codeAndDescription(String code, String description) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isBlank(code)) {
            sb.append("[").append(code).append("]");
        }
        if (!StringUtils.isBlank(description)) {
            sb.append(" ").append(description);
        }
        return sb.toString().trim();
    }
}
