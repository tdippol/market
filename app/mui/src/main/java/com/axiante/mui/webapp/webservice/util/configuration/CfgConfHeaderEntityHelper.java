package com.axiante.mui.webapp.webservice.util.configuration;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgLivelloPianificazioneService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class CfgConfHeaderEntityHelper extends AbstractConfigurationEntityHelper {

    @Inject
    private CfgLivelloPianificazioneService livelloPianificazioneService;

    private static final List<String> FIELDS = Arrays.asList("min_set", "max_set", "min_raggr", "max_raggr",
            "unica_in_promo", "livello_pianificazione", "duplica_articolo", "duplica_reparto", "duplica_grm", "duplica_totale", "logo_messaggi", "castelletti");

    public List<String> updateEntity(@NonNull CfgConfHeaderEntity entity, @NonNull ObjectNode jsonNode) {
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

    public CfgPianificazioneEntity getConfiguredField(@NonNull CfgConfHeaderEntity cfgConfHeader,
                                                      @NonNull String campo) {
        final List<CfgPianificazioneEntity> fields = cfgConfHeader.getMuiCfgPianificaziones().stream()
                .filter(p -> p.getMuiCfgPianificazioneCampi() != null)
                .filter(p -> campo.equals(p.getMuiCfgPianificazioneCampi().getCampo()))
                .collect(Collectors.toList());
        if (fields.isEmpty()) {
            log.warn(String.format("Nessuna configurazione per il campo '%s'", campo));
            return null;
        }
        if (fields.size() > 1) {
            log.warn(String.format("Il campo '%s' e' configurato %d volte; viene presa la prima configurazione",
                    campo, fields.size()));
        }
        return fields.get(0);
    }

    private boolean invokeSetterForField(CfgConfHeaderEntity entity, String fieldName, JsonNode node) {
        try {
            switch (fieldName) {
                case "min_set":
                    final Integer minSet = getValueAsInteger(node);
                    if (minSet != null && entity.getMaxSet() != null && minSet > entity.getMaxSet()) {
                        log.error(String.format("Value '%s' for 'min_set' cannot be greater than 'max_set' (%s)",
                                minSet, entity.getMaxSet()));
                        return false;
                    }
                    entity.setMinSet(minSet);
                    return true;
                case "max_set":
                    final Integer maxSet = getValueAsInteger(node);
                    if (maxSet != null && entity.getMinSet() != null && maxSet < entity.getMinSet()) {
                        log.error(String.format("Value '%s' for 'max_set' cannot be lesser than 'min_set' (%s)",
                                maxSet, entity.getMinSet()));
                        return false;
                    }
                    entity.setMaxSet(maxSet);
                    return true;
                case "min_raggr":
                    final Integer minRaggr = getValueAsInteger(node);
                    if (minRaggr != null && entity.getMaxRaggruppamento() != null && minRaggr > entity.getMaxRaggruppamento()) {
                        log.error(String.format("Value '%s' for 'min_raggr' cannot be greater than 'max_raggr' (%s)",
                                minRaggr, entity.getMaxRaggruppamento()));
                        return false;
                    }
                    entity.setMinRaggruppamento(minRaggr);
                    return true;
                case "max_raggr":
                    final Integer maxRaggr = getValueAsInteger(node);
                    if (maxRaggr != null && entity.getMinRaggruppamento() != null && maxRaggr < entity.getMinRaggruppamento()) {
                        log.error(String.format("Value '%s' for 'max_raggr' cannot be lesser than 'min_raggr' (%s)",
                                maxRaggr, entity.getMinRaggruppamento()));
                        return false;
                    }
                    entity.setMaxRaggruppamento(maxRaggr);
                    return true;
                case "unica_in_promo":
                    final Integer unicaInPromo = getValueAsBoolean(node);
                    if (unicaInPromo != null && unicaInPromo == -1) {
                        log.error(String.format("Value '%s' for 'unica_in_promo' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setUnicaInPromo(unicaInPromo);
                    return true;
                case "livello_pianificazione":
                    final CfgLivelloPianificazioneEntity livelloPianificazioneEntity = getLivelloPianificazioneFromNodeValue(node);
                    if (livelloPianificazioneEntity == null) {
                        log.error(String.format("Value '%s' for 'livello_pianificazione' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setLivelloPianificazione(livelloPianificazioneEntity);
                    return true;
                case "duplica_articolo":
                    final Integer duplicaArticolo = getValueAsBoolean(node);
                    if (duplicaArticolo != null && duplicaArticolo == -1) {
                        log.error(String.format("Value '%s' for 'duplica_articolo' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setDuplicaArticolo(duplicaArticolo != null && duplicaArticolo.equals(1));
                    return true;
                case "duplica_reparto":
                    final Integer duplicaReparto = getValueAsBoolean(node);
                    if (duplicaReparto != null && duplicaReparto == -1) {
                        log.error(String.format("Value '%s' for 'duplica_reparto' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setDuplicaReparto(duplicaReparto != null && duplicaReparto.equals(1));
                    return true;
                case "duplica_grm":
                    final Integer duplicaGrm = getValueAsBoolean(node);
                    if (duplicaGrm != null && duplicaGrm == -1) {
                        log.error(String.format("Value '%s' for 'duplica_grm' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setDuplicaGrm(duplicaGrm != null && duplicaGrm.equals(1));
                    return true;
                case "duplica_totale":
                    final Integer duplicaTotale = getValueAsBoolean(node);
                    if (duplicaTotale != null && duplicaTotale == -1) {
                        log.error(String.format("Value '%s' for 'duplica_totale' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setDuplicaTotale(duplicaTotale != null && duplicaTotale.equals(1));
                    return true;
                case "logo_messaggi":
                    final Integer logoMessaggi = getValueAsBoolean(node);
                    if ( logoMessaggi != null && logoMessaggi == -1){
                        log.error(String.format("Value '%s' for 'logo_messaggi' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setLogoMessaggi(logoMessaggi != null && logoMessaggi.equals(1));
                    return true;
                case "castelletti":
                    final Integer castelletti = getValueAsBoolean(node);
                    if ( castelletti != null && castelletti == -1){
                        log.error(String.format("Value '%s' for 'castelletti' is not valid", node.get("value").asText()));
                        return false;
                    }
                    entity.setCastelletti(castelletti !=null && castelletti.equals(1));
                    return true;
                default:
                    log.warn(String.format("Field '%s' not defined in entity CfgConfHeaderEntity", fieldName));
                    return false;
            }
        } catch (NumberFormatException ex) {
            log.error(String.format("The format of value for node '%s' [%s] is not correct", fieldName, node.toString()), ex);
            return false;
        }
    }

    private CfgLivelloPianificazioneEntity getLivelloPianificazioneFromNodeValue(JsonNode node) {
        final String value = node.get("value").asText();
        final Long id = Long.parseLong(value);
        return livelloPianificazioneService.findById(id);
    }
}
