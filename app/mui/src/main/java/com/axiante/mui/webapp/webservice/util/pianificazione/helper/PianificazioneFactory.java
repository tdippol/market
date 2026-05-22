package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.TipoRigaService;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemPojo;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianificazioneFactory {

    @Inject
    Instance<TipoRigaService> tipoRigaServiceInstance;

    @Inject
    Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    Instance<PromozionePianificazioneEntityHelper> promoPianificazioneEntityHelperInstance;

    @Inject
    Instance<PianificazioneValidatorUtil> validatorUtilInstance;

    @Inject
    Instance<NumSetUtils> numSetUtils;
    /**
     * Costruzione entity PromozionePianificazione con meccanica e testata settate
     * @param promoTestataEntity testata promozione
     * @param meccanicaEntity meccanica
     * @param user utente che esegue l'operazione
     * @return una nuova PromozionePianificazioneEntity
     */
    public PromozionePianificazioneEntity buildPromozionePianificazione(PromozioneTestataEntity promoTestataEntity,
                                                                        MeccanicheEntity meccanicaEntity,
                                                                        String user) {
        PromozionePianificazioneEntity promoPianificazioneEntity = (PromozionePianificazioneEntity)
                AuditLogFiller.fillAuditLogFields(new PromozionePianificazioneEntity(), user);
        promoPianificazioneEntity.setPromozioneTestataEntity(promoTestataEntity);
        promoPianificazioneEntity.setMeccanicaEntity(meccanicaEntity);
        return promoPianificazioneEntity;
    }


    /**
     * Crea una riga pianificazione di tipo 'ELEMENTO' con promozione e meccanica associate
     * @param testataEntity promozione associata
     * @param meccanicaEntity meccanica associata
     * @param tipoElemento tipo elemento (Articolo, Grm, Reparto, Totale)
     * @param item elemento da inserire
     * @param mappedFields lista campi mappati nella entity
     * @param user utente che esegue l'inserimento
     * @return lista di righe da persistere
     */
    public PromozionePianificazioneEntity buildRigaElemento(PromozioneTestataEntity testataEntity,
                                                             MeccanicheEntity meccanicaEntity,
                                                             String tipoElemento,
                                                             ItemPojo item,
                                                             Map<String, Field> mappedFields,
                                                             String user) {
        final PromozionePianificazioneEntity entity = buildPromozionePianificazione(testataEntity, meccanicaEntity, user);
        entity.setTipoRiga(tipoRigaServiceInstance.get().findByCodiceTipo(PlanningLevelEnum.ELEMENTO.getCode()));
        entity.setTipoElemento(tipoElemento);
        if (!PromoPianificazioneEnum.TOTALE.getTipoElemento().equals(tipoElemento)) {
            entity.setElemento(item.getElementDescription());
            entity.setCodiceElemento(String.valueOf(item.getItemId()));
        }
        return fillDefaultsAndLookups(entity, testataEntity, meccanicaEntity, mappedFields, PlanningLevelEnum.ELEMENTO, false);
    }



    /**
     * Crea una riga pianificazione di tipo 'ELEMENTO' con promozione e meccanica associate
     * @param testataEntity promozione associata
     * @param meccanicaEntity meccanica associata
     * @param mappedFields lista campi mappati nella entity
     * @param user utente che esegue l'inserimento
     * @return lista di righe da persistere
     */
    public PromozionePianificazioneEntity buildRigaElementoTotale(PromozioneTestataEntity testataEntity,
                                                                   MeccanicheEntity meccanicaEntity,
                                                                   Map<String, Field> mappedFields,
                                                                   String user) {
        final PromozionePianificazioneEntity entity = buildPromozionePianificazione(testataEntity, meccanicaEntity, user);
        entity.setTipoRiga(tipoRigaServiceInstance.get().findByCodiceTipo(PlanningLevelEnum.ELEMENTO.getCode()));
        entity.setTipoElemento(PromoPianificazioneEnum.TOTALE.getTipoElemento());
        entity.setElemento(PromoPianificazioneEnum.TOTALE.getTipoElemento());
        return fillDefaultsAndLookups(entity, testataEntity, meccanicaEntity, mappedFields, PlanningLevelEnum.ELEMENTO, false);
    }


    /**
     * Crea una riga pianificazione di tipo 'RAGGRUPPAMENTO' con promozione e meccanica associate
     * @param testataEntity promozione associata
     * @param meccanicaEntity meccanica associata
     * @param numRaggr progressivo del raggruppamento da creare
     * @param mappedFields lista campi mappati nella entity
     * @param isChildOfSet true se questa riga e' figlia di una riga di tipo 'SET', false altrimenti
     * @param user utente che esegue l'inserimento
     * @return riga da persistere
     */
    public PromozionePianificazioneEntity buildRigaRaggruppamento(PromozioneTestataEntity testataEntity,
                                                                   MeccanicheEntity meccanicaEntity,
                                                                   int numRaggr,
                                                                   Map<String, Field> mappedFields,
                                                                   final boolean isChildOfSet,
                                                                   String user) {
        final PromozionePianificazioneEntity entity = buildPromozionePianificazione(testataEntity, meccanicaEntity, user);
        entity.setTipoRiga(tipoRigaServiceInstance.get().findByCodiceTipo(PlanningLevelEnum.RAGGRUPPAMENTO.getCode()));
        entity.setNumRaggruppamento(String.valueOf(numRaggr));
        return fillDefaultsAndLookups(entity, testataEntity, meccanicaEntity, mappedFields, PlanningLevelEnum.RAGGRUPPAMENTO,isChildOfSet);
    }

    /**
     * Crea una riga pianificazione di tipo 'SET' con promozione e meccanica associate
     * @param testata promozione associata
     * @param meccanica meccanica associata
     * @param cfgConfHeader header al quale aggiungere la riga
     * @param mappedFields lista campi mappati nella entity
     * @param user utente che esegue l'inserimento
     * @return riga da persistere
     */
    public PromozionePianificazioneEntity buildRigaSet(PromozioneTestataEntity testata, MeccanicheEntity meccanica,
                                                       CfgConfHeaderEntity cfgConfHeader, Map<String, Field> mappedFields,
                                                       String user) {
        final PromozionePianificazioneEntity entity = buildPromozionePianificazione(testata, meccanica, user);
        entity.setTipoRiga(tipoRigaServiceInstance.get().findByCodiceTipo(PlanningLevelEnum.SET.getCode()));
        try {
            final String codiceMeccanica = meccanica.getCodiceMeccanica();
            final String numSet = "M141".equalsIgnoreCase(codiceMeccanica) || "M142".equalsIgnoreCase(codiceMeccanica)
                    || "M143".equalsIgnoreCase(codiceMeccanica)
                    ? numSetUtils.get().getNumSetPrecaricate(testata, meccanica, cfgConfHeader)
                    : numSetUtils.get().getNumSet(testata, meccanica, cfgConfHeader);
            entity.setNumSet(numSet);
        } catch (Exception ex) {
            log.error("Errore nel recupero NUM_SET", ex);
        }
        entity.setDataInizio(testata.getDataInizio());
        entity.setDataFine(testata.getDataFine());
        fillDefaultsAndLookups(entity, testata, meccanica, mappedFields, PlanningLevelEnum.SET, false);
        return entity;
    }


    private PromozionePianificazioneEntity fillDefaultsAndLookups(final PromozionePianificazioneEntity entity,
                                                                  final PromozioneTestataEntity testataEntity,
                                                                  final MeccanicheEntity meccanicaEntity,
                                                                  final Map<String, Field> mappedFields,
                                                                  final PlanningLevelEnum livello,
                                                                  boolean isChildOfSet) {
        final Map<String, String> defaultValues = pianificazioneServiceInstance.get().getDefaultValues(testataEntity.getId(),
                meccanicaEntity.getId(), livello.getCode());
        resolveLookupValues(entity, defaultValues);
        validatorUtilInstance.get().validateDefaults(entity, defaultValues, isChildOfSet);
        promoPianificazioneEntityHelperInstance.get().invokeSetter(entity, mappedFields, defaultValues);
        return entity;
    }


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
