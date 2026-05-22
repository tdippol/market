package com.axiante.mui.webapp.webservice.util.configuration;

import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class CfgTipoElementoEntityHelper extends AbstractConfigurationEntityHelper {

    private static final List<String> FIELDS = Arrays.asList("raggruppamento", "totale", "reparto", "grm", "articolo",
            "attributo", "omogeneo");

    public List<String> updateEntity(@NonNull CfgTipoElementoEntity entity, @NonNull ObjectNode jsonNode) {
        final List<String> messages = new ArrayList<>();
        for (String fieldName : FIELDS) {
            final JsonNode node = jsonNode.get(fieldName);
            if (node == null) {
                final String msg = String.format("Field '%s' is not present in json", fieldName);
                log.warn(msg);
                messages.add(msg);
            } else {
                final boolean valid = invokeSetterForField(entity, fieldName, node);
                if (!valid) {
                    messages.add(String.format("Field '%s' defined as '%s' is not valid", fieldName, node.toString()));
                }
            }
        }
        return messages;
    }

    private boolean invokeSetterForField(CfgTipoElementoEntity entity, String fieldName, JsonNode node) {
        try {
            switch (fieldName) {
                case "raggruppamento":
                    final Integer raggr = getValueAsInteger(node);
                    entity.setRaggruppamento(raggr);
                    return true;
                case "totale":
                    final Integer totale = getValueAsBoolean(node);
                    if (totale != null && totale == -1) {
                        log.error(String.format("Value '%s' for 'totale' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setTotale(totale);
                    return true;
                case "reparto":
                    final Integer reparto = getValueAsBoolean(node);
                    if (reparto != null && reparto == -1) {
                        log.error(String.format("Value '%s' for 'reparto' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setReparto(reparto);
                    return true;
                case "grm":
                    final Integer grm = getValueAsBoolean(node);
                    if (grm != null && grm == -1) {
                        log.error(String.format("Value '%s' for 'grm' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setGrm(grm);
                    return true;
                case "articolo":
                    final Integer articolo = getValueAsBoolean(node);
                    if (articolo != null && articolo == -1) {
                        log.error(String.format("Value '%s' for 'articolo' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setArticolo(articolo);
                    return true;
                case "attributo":
                    final Integer attributo = getValueAsBoolean(node);
                    if (attributo != null && attributo == -1) {
                        log.error(String.format("Value '%s' for 'attributo' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setAttributo(attributo);
                    return true;
                case "omogeneo":
                    final Integer omogeneo = getValueAsBoolean(node);
                    if (omogeneo != null && omogeneo == -1) {
                        log.error(String.format("Value '%s' for 'omogeneo' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setOmogeneo(omogeneo);
                    return true;
                default:
                    log.warn(String.format("Field '%s' not defined in entity CfgTipoElementoEntity", fieldName));
                    return false;
            }
        } catch (NumberFormatException ex) {
            log.error(String.format("The format of value for node '%s' [%s] is not correct", fieldName, node.toString()), ex);
            return false;
        }
    }
}
