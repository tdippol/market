package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class LookupUtils {
    @Inject
    Instance<PianificazioneService> pianificazioneServiceInstance;

    public void resolveLookupValues(PromozionePianificazioneEntity entity, Map<String, String> defaultValues) {
        final Map<String, String> lookupValues = defaultValues.entrySet().stream()
                .filter(e -> e.getValue() != null && e.getValue().startsWith("{") && e.getValue().endsWith("}"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (Map.Entry<String, String> entry : lookupValues.entrySet()) {
            final String fieldName = entry.getKey();
            try {
                final String[] split = parseLookupDefaultValue(entry.getValue());
                String value = null;
                Long id = null;
                Object valore = null;
                switch (fieldName) {
                    case PianificazioneConstants.REFERENCE_VALORE:
                    case  PianificazioneConstants.REFERENCE_PREZZO_BUDGET_COLUMN:
                        id = Long.parseLong(entity.getCodiceElemento());
                        valore = pianificazioneServiceInstance.get()
                                .findValueByAttribute(split[0], split[1], "id", id);
                        if (valore != null) {
                            value = String.valueOf(valore);
                        }
                        break;
                    case PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN:
                    case PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN:
                        id = entity.getPromozioneTestataEntity().getId();
                        valore = pianificazioneServiceInstance.get()
                                .findValueByAttribute(split[0], split[1], "id", id);
                        if (valore != null) {
                            value = new DateTimeUtils().getFormatoItaliano().format(valore);
                        }
                        break;
                    default:
                        log.warn(String.format("Default value for field '%s' not managed", fieldName));
                        break;
                }
                if (value != null) {
                    defaultValues.put(fieldName, value);
                } else {
                    log.error(String.format("Unable to resolve lookup value '%s' for field '%s'",
                            entry.getValue(), fieldName));
                }
            } catch (Exception ex) {
                log.error(String.format("Error tryning to resolve lookup value '%s' for field '%s'",
                        entry.getValue(), fieldName), ex);
            }
        }
    }

    private String[] parseLookupDefaultValue(String value) {
        final String v = value.trim();
        final String[] split = v.substring(v.indexOf("{") + 1, v.indexOf("}")).split("\\.");
        if (split.length != 2) {
            throw new IllegalArgumentException(String.format("Lookup value '%s' is malformed", v));
        }
        return split;
    }
}
