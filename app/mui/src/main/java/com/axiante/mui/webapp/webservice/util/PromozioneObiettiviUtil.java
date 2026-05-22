package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
@Slf4j
public class PromozioneObiettiviUtil extends AbstractRowDataUtil {
    public static final List<String> CODICI_MECCANICHE_PRECARICATE = Arrays.asList("M141", "M142");

    public boolean hasMeccanicaPrecaricata(@NonNull PromozioneTestataEntity promozione) {
        return promozione.getPromozionePianificazioneEntities().stream()
                .anyMatch(p -> CODICI_MECCANICHE_PRECARICATE.contains(
                        p.getMeccanicaEntity().getCodiceMeccanica()));
    }

    public String createObiettiviRowData(@NonNull List<ObiettivoEntity> obiettivi) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            obiettivi.stream()
                    .sorted(Comparator.comparing(ObiettivoEntity::getId))
                    .forEach(obiettivo -> arrayNode.add(createObiettiviRowNode(obiettivo)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'ObiettivoEntity'", ex);
            return "";
        }
    }

    private ObjectNode createObiettiviRowNode(ObiettivoEntity obiettivo) {
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final String cellTypeNumeric = DBPromoCellTypeEnum.NUMERIC.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            final PromozioneTestataEntity promozionePremio = obiettivo.getPromozione();
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellTypeString)
                    .value(String.valueOf(obiettivo.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Id Totalizzatore").editable(false).type(cellTypeString)
                    .value(String.valueOf(obiettivo.getTotalizzatore().getId())).build();
            map.put("idTotalizzatore", cell);

            cell = DBPromoAgCell.builder().name("Codice Iniziativa").editable(false).type(cellTypeString)
                    .value(getCodiceIniziativa(obiettivo.getTotalizzatore())).build();
            map.put("codiceIniziativa", cell);

            cell = DBPromoAgCell.builder().name("Codice Promo").editable(false).type(cellTypeString)
                    .value(promozionePremio != null ? promozionePremio.getCodicePromozione() : "").build();
            map.put("codicePromo", cell);

            cell = DBPromoAgCell.builder().name("Descrizione Promo").editable(false).type(cellTypeString)
                    .value(promozionePremio != null ? promozionePremio.getDescrizione() : "").build();
            map.put("descrizionePromo", cell);

            cell = DBPromoAgCell.builder().name("Target").editable(true).type(cellTypeNumeric)
                    .value(nullableValue(obiettivo.getTarget())).build();
            map.put("target", cell);

            cell = DBPromoAgCell.builder().name("Buono Premio")
                    .editable(promozionePremio != null ? !hasMeccanicaPrecaricata(promozionePremio) : Boolean.FALSE)
                    .type(cellTypeNumeric).value(nullableValue(obiettivo.getBuonoPremio())).build();
            map.put("buonoPremio", cell);

            cell = DBPromoAgCell.builder().name("Attivo").editable(true).type(DBPromoCellTypeEnum.CHECKBOX.getType())
                    .value(nullableValue(obiettivo.getFlagAttivo())).build();
            map.put("attivo", cell);

            cell = DBPromoAgCell.builder().name("RemoveEnabled").editable(false).type(cellTypeString)
                    .value(String.valueOf(Boolean.TRUE)).build();
            map.put("removeEnabled", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating JSON rowData for obiettivo with id '%d'", obiettivo.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private String getCodiceIniziativa(PianificazioneTotalizzatoriEntity totalizzatore) {
        return totalizzatore != null && totalizzatore.getTestata() != null && totalizzatore.getTestata().getAnno() != null
                && totalizzatore.getTestata().getCodicePromozione() != null
                ? String.format("%s%s", totalizzatore.getTestata().getAnno().substring(2),
                totalizzatore.getTestata().getCodicePromozione().split("_")[1])
                : "";
    }
}
