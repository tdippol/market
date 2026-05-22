package com.axiante.mui.webapp.webservice.pojo.pianificazione;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode
@Slf4j
public class ItemRiferimento {

    @Getter
    private ItemPojo item;

    @Getter
    private String codicePromozione;

    @Getter
    private String tipoItem;

    @Getter
    private String codiceMeccanica;

    @Getter
    private String offerta;

    public static ItemRiferimento fromJson(JsonNode jsonNode) {
        final Long itemId = getNodeAsLong(jsonNode, "idItem");
        final String itemDesc = String.format("%s - %s",
                getNodeAsString(jsonNode, "codiceItem"),
                getNodeAsString(jsonNode, "descrizioneItem"));
        final ItemRiferimento item = new ItemRiferimento();
        item.item = new ItemPojo(itemId, null, itemDesc, String.valueOf(itemId));
        item.codicePromozione = getNodeAsString(jsonNode, "codicePromozione");
        item.tipoItem = getNodeAsString(jsonNode, "tipoItem");
        item.codiceMeccanica = parseMeccanica(jsonNode, "meccanica");
        item.offerta = getNodeAsString(jsonNode, "offerta");
        return item;
    }

    private static String getNodeAsString(JsonNode jsonNode, String fieldName) {
        return jsonNode.get(fieldName) != null ? jsonNode.get(fieldName).asText().trim() : "";
    }

    private static Long getNodeAsLong(JsonNode jsonNode, String fieldName) {
        final String s = getNodeAsString(jsonNode, fieldName);
        return StringUtils.isBlank(s) ? null : Long.parseLong(s);
    }

    private static String parseMeccanica(JsonNode jsonNode, String fieldName) {
        final String s = getNodeAsString(jsonNode, fieldName);
        if (StringUtils.isBlank(s)) {
            return "";
        }
        try {
            return s.split("-")[0].trim();
        } catch (Exception ex) {
            log.error(String.format("Error getting 'codiceMeccanica' from string '%s'", s), ex);
            return "";
        }
    }
}
