package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemPojo;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianificazioneValidatorUtil {

    @Inject
    private PianificazionePromoUtil pianificazionePromoUtil;

    @Inject
    private ConfigurationService configurationService;

    @Inject
    private PlanningCommons planningCommons;

    @Inject
    private PromoService promoService;

    @Inject
    private Instance<CfgConfHeaderService> confHeaderServiceInstance;

    @Inject
    private Instance<LinkHelper> linkHelperInstance;

    /**
     * Validazione riga MUI_PROMOZIONE_PIANIFICAZIONE di tipo 'SET'
     * In MUI_PROMOZIONE_PIANIFICAZIONE conto, a parità di Promozione e Meccanica il numero di righe 'SET'
     * - Se MUI_CFG_CONF_HEADER.MAX_SET == null --> TRUE
     * - Se MUI_CFG_CONF_HEADER.MAX_SET != null --> conteggio < MAX_SET
     * @param testataEntity la promozione associata
     * @param meccanicaEntity la meccanica associata
     * @param confHeaderEntity header configurazione
     * @return true se la riga di pianificazione è valida, false altrimenti
     */
    public boolean validateSet(PromozioneTestataEntity testataEntity,
                               MeccanicheEntity meccanicaEntity,
                               CfgConfHeaderEntity confHeaderEntity) {
        if (confHeaderEntity.getMaxSet() == null) {
            return true;
        }

        final long rows = testataEntity.getPromozionePianificazioneEntities().stream()
                .filter(p -> p.getMeccanicaEntity().getId().equals(meccanicaEntity.getId())
                        && PlanningLevelEnum.SET.getCode().equals(p.getTipoRiga().getCodiceTipo()))
                .count();
        return rows < confHeaderEntity.getMaxSet();
    }

    /**
     * Validazione riga MUI_PROMOZIONE_PIANIFICAZIONE di tipo 'RAGGRUPPAMENTO'
     * Se nella promozione a parità di meccanica ho già il numero massimo di raggruppamenti consentiti,
     * non è possibile inserire una nuova riga
     * @param testataEntity la promozione associata
     * @param meccanicaEntity la meccanica associata
     * @return true se la riga di pianificazione è valida, false altrimenti
     */
    public boolean validateRaggruppamento(PromozioneTestataEntity testataEntity, MeccanicheEntity meccanicaEntity) {
        try {
            final CfgConfHeaderEntity confHeader = getHeaderFromPromozioneAndMeccanica(testataEntity, meccanicaEntity);
            if (confHeader == null) {
                return false;
            } else if (confHeader.getMaxRaggruppamento() == null) {
                return true;
            }
            final long rows = testataEntity.getPromozionePianificazioneEntities().stream()
                    .filter(p -> p.getMeccanicaEntity().getId().equals(meccanicaEntity.getId())
                            && PlanningLevelEnum.RAGGRUPPAMENTO.getCode().equals(p.getTipoRiga().getCodiceTipo())
                            && p.getParent()==null)
                    .count();
            return rows < confHeader.getMaxRaggruppamento();
        } catch (Exception ex) {
            log.error(String.format("Errore nella validazione inserimento nuova riga pianificazione di tipo 'RAGGRUPPAMENTO' " +
                    "per la promozione con id %s", testataEntity.getId()), ex);
            return false;
        }
    }

    /**
     * Validazione riga MUI_PROMOZIONE_PIANIFICAZIONE di tipo 'RAGGRUPPAMENTO OMOGENEO'
     * @param testataEntity la promozione associata
     * @param meccanicaEntity la meccanica associata
     * @param numRaggr il numero raggruppamento da controllare
     * @return true se il raggruppamento è di tipo omogeneo, false altrimenti
     */
    public boolean validateOmogeneo(PromozioneTestataEntity testataEntity, MeccanicheEntity meccanicaEntity, String numRaggr) {
        try {
            final CfgConfHeaderEntity confHeader = getHeaderFromPromozioneAndMeccanica(testataEntity, meccanicaEntity);
            if (confHeader == null) {
                log.error(String.format("Nessun header trovato per la promozione con id %s e meccanica id %s",
                        testataEntity.getId(), meccanicaEntity.getId()));
                return false;
            }
            final Set<CfgTipoElementoEntity> tipiElemento = confHeader.getTipiElemento();
            if (tipiElemento == null || tipiElemento.isEmpty()) {
                log.error(String.format("Nessun tipo elemento configurato nell'header per la promozione con id %s e meccanica id %s",
                        testataEntity.getId(), meccanicaEntity.getId()));
                return false;
            }
            if (tipiElemento.size() == 1) {
                return tipiElemento.iterator().next().getOmogeneo() == 1;
            }
            final CfgTipoElementoEntity tipoElementoEntity = tipiElemento.stream()
                    .filter(e -> String.valueOf(e.getRaggruppamento()).equals(numRaggr))
                    .findFirst()
                    .orElse(null);
            if (tipoElementoEntity == null) {
                log.error(String.format("Nessun tipo elemento configurato nell'header per la promozione con id %s "
                        + ", meccanica id %s e numero raggruppamento %s",
                        testataEntity.getId(), meccanicaEntity.getId(), numRaggr));
                return false;
            }
            return tipoElementoEntity.getOmogeneo() == 1;
        } catch (Exception ex) {
            log.error(String.format("Errore nella validazione riga tipo 'RAGGRUPPAMENTO OMOGENEO' " +
                    "per la promozione con id %s e meccanica id %s", testataEntity.getId(), meccanicaEntity.getId()), ex);
            return false;
        }
    }

    /**
     * Recupero header configurazione data una promozione testata ed una meccanica
     * @param testataEntity testata promozione
     * @param meccanicaEntity meccanica
     * @return header configurazione associato o null se non ce ne sono
     */
    public CfgConfHeaderEntity getHeaderFromPromozioneAndMeccanica(PromozioneTestataEntity testataEntity,
                                                                    MeccanicheEntity meccanicaEntity) {
        return testataEntity.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
                .filter(h -> h.getMeccanicaEntity().getId().equals(meccanicaEntity.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Validazione valore BUONO_SCONTO_PROGRESSIVO:
     * - se il buonoScontoRadice e' null, non imposto il progressivo
     * - se il default value e' contenuto nella lista, prendo quello, altrimenti il primo disponibile nella lista
     * @param pianificazioneEntity
     * @param defaultValue
     * @return
     */
    public String validateBuonoScontoProgressivo(PromozionePianificazioneEntity pianificazioneEntity,
                                                 String defaultValue,
                                                 String buonoScontoRadice) {
        if (buonoScontoRadice == null) {
            log.warn(String.format("Il campo '%s' non e' impostato; non posso determinare il progressivo",
                    PianificazioneConstants.REFERENCE_BUONO_SCONTO_RADICE));
            return null;
        }
        final CfgPianificazioneEntity cfgPianificazione = getCfgPianificazioneForField(pianificazioneEntity,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN);
        if (cfgPianificazione == null) {
            log.warn(String.format("Il campo '%s' non e' presente in configurazione per la meccanica '%s' "
                    + "nel set pianificazione '%s'; viene utilizzato il valore di default '%s'",
                    PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN,
                    pianificazioneEntity.getMeccanicaEntity().getCodiceMeccanica(),
                    pianificazioneEntity.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getDescrizione(),
                    defaultValue));
            return defaultValue;
        }
        try {
            List<Integer> range = new PicklistUtils().defineListaCellEditorAsInts(cfgPianificazione.getLista());
            final List<String> pickListValues = pianificazionePromoUtil.getCorrectFormatPickListValues(cfgPianificazione.getLista());
            final List<Integer> values = pianificazionePromoUtil.getAvailableProgressiveDiscountCodesBuoniPotenziamento(
                    pianificazioneEntity.getPromozioneTestataEntity(), Integer.parseInt(buonoScontoRadice),
                    pickListValues, range);

            final Integer defValue = Integer.parseInt(defaultValue);
            if (!values.isEmpty()) {
                return values.contains(defValue)
                        ? defaultValue
                        : String.valueOf(values.get(0));
            }
            return "";
        } catch (NumberFormatException ex) {
            log.error(String.format("Unable to validate 'BUONO SCONTO PROGRESSIVO' for pianificazione id %s "
                            + "with default value %s and 'BUONO SCONTO RADICE' %s", pianificazioneEntity.getId(),
                    defaultValue, buonoScontoRadice), ex);
        }
        return null;
    }

    /**
     * Validazione riga pianificazione in base al flag 'VALIDO_SE_RAGGRUPPAMENTO' del campo di configurazione
     * @param pianificazioneEntity riga di pianificazione
     * @param field campo da controllare
     * @return true se la riga di pianificazione segue le regole di configurazione per il dato campo, false altrimenti
     */
    public boolean validateValidoSeRaggruppamento(final PromozionePianificazioneEntity pianificazioneEntity,
                                                  final String field) {
        final CfgPianificazioneEntity cfgPianificazione = getCfgPianificazioneForField(pianificazioneEntity, field);
        if (cfgPianificazione == null) {
            // Il campo cercato non è presente in configurazione;
            // non posso recuperare il valore VALIDO_SE_RAGGRUPPAMENTO, assumo true
            log.warn(String.format("Il campo '%s' non e' presente in configurazione per la meccanica '%s' "
                            + "nel set pianificazione '%s'",
                    field,
                    pianificazioneEntity.getMeccanicaEntity().getCodiceMeccanica(),
                    pianificazioneEntity.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getDescrizione()));
            return true;
        }
        return !planningCommons.overrideConfiguration(cfgPianificazione, pianificazioneEntity);
    }

    /**
     * MG 02-09-2024: Validazione campo LINK, solo per meccanica 'M018'
     * - non può ripetersi se già presente in una pianificazione sullo stesso anno e canale, in overlap
     * @param pianificazione pianificazione corrente
     * @param valueToBeUpdated valore da controllare
     * @return true se il valore è valido, false altrimenti
     */
    public boolean validateLink(@NonNull final PromozionePianificazioneEntity pianificazione,
                                final Object valueToBeUpdated) {
        List<String> values = availableLinks(pianificazione);
        return valueToBeUpdated != null && values != null && values.contains((String) valueToBeUpdated);
    }

    private List<String> availableLinks(@NonNull final PromozionePianificazioneEntity pianificazione) {
        final List<CfgPianificazioneEntity> configurazioni = configurationService.findBySetAndMeccanicaAndCampo(
                pianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazione.getMeccanicaEntity(), PianificazioneConstants.REFERENCE_LINK);
        if (configurazioni.isEmpty()) {
            log.error(String.format("Campo '%s' non configurato per meccanica '%s'",
                    PianificazioneConstants.REFERENCE_LINK, pianificazione.getMeccanicaEntity().getCodiceMeccanica()));
            return null;
        }
        if (configurazioni.size() > 1) {
            log.warn(String.format("Il campo '%s' e' configurato %d volte per la meccanica '%s' nel canale '%s'",
                    PianificazioneConstants.REFERENCE_LINK, configurazioni.size(),
                    pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                    pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getCodiceCanale()));
        }
        CfgPianificazioneEntity configurazione = configurazioni.get(0);
        if (configurazione.getLista() == null) {
            log.error(String.format("Lista non configurata per il campo '%s' per meccanica '%s'",
                    PianificazioneConstants.REFERENCE_LINK, pianificazione.getMeccanicaEntity().getCodiceMeccanica()));
            return null;
        }
        return linkHelperInstance.get().getAvailableValues(configurazione.getLista(), pianificazione);
    }

    private CfgPianificazioneEntity getCfgPianificazioneForField(final PromozionePianificazioneEntity pianificazioneEntity,
                                                                 final String field) {
        final List<CfgPianificazioneEntity> cfgPianificazioni = configurationService.findBySetAndMeccanicaAndCampo(
                pianificazioneEntity.getPromozioneTestataEntity().getMuiCfgSetPianificazione(),
                pianificazioneEntity.getMeccanicaEntity(),
                field);
        if (cfgPianificazioni.isEmpty()) {
            return null;
        }
        if (cfgPianificazioni.size() > 1) {
            log.warn(String.format("Il campo '%s' e' configurato %d volte per la meccanica '%s' nel set pianificazione '%s'; "
                            + "viene utilizzata la prima configurazione disponibile (%s)",
                    field, cfgPianificazioni.size(),
                    pianificazioneEntity.getMeccanicaEntity().getCodiceMeccanica(),
                    pianificazioneEntity.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getDescrizione(),
                    cfgPianificazioni.get(0).getId()));
        }
        return cfgPianificazioni.get(0);
    }

    /**
     * Gestione casi particolari per i defaults
     * @param entity
     * @param defaultValues
     * @param isChildOfSet
     */
    public void validateDefaults(PromozionePianificazioneEntity entity,
                                 Map<String, String> defaultValues,
                                 final boolean isChildOfSet) {
        if (isChildOfSet) {
            defaultValues.entrySet()
                    .removeIf(entry -> !validateValidoSeRaggruppamento(entity, entry.getKey()));
        }
        if (defaultValues.containsKey(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN)) {
            String value = validateBuonoScontoProgressivo(entity,
                    defaultValues.get(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN),
                    defaultValues.get(PianificazioneConstants.REFERENCE_BUONO_SCONTO_RADICE));
            if (value != null) {
                defaultValues.put(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN, value);
            } else {
                // #3939: Nel caso il BUONO_SCONTO_RADICE non sia valorizzato, non posso determinare il valore per il
                // BUONO_SCONTO_PROGRESSIVO, quindi lo tolgo dai defaultValues
                defaultValues.remove(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN);
            }
        }
        if ("M018".equalsIgnoreCase(entity.getMeccanicaEntity().getCodiceMeccanica())
                && defaultValues.containsKey(PianificazioneConstants.REFERENCE_LINK)) {
            if (entity.getId() == null) {
                // Sto inserendo una nuova pianificazione, sovrascrivo il default con il primo valore disponibile
                List<String> values = availableLinks(entity);
                if (values == null || values.isEmpty()) {
                    log.error(String.format("Nessun valore disponibile per il campo '%s' per la pianificazione con meccanica '%s'",
                            PianificazioneConstants.REFERENCE_LINK, entity.getMeccanicaEntity().getCodiceMeccanica()));
                    defaultValues.put(PianificazioneConstants.REFERENCE_LINK, null);
                } else {
                    defaultValues.put(PianificazioneConstants.REFERENCE_LINK, values.get(0));
                }
            } else if (!validateLink(entity, defaultValues.get(PianificazioneConstants.REFERENCE_LINK))) {
                defaultValues.put(PianificazioneConstants.REFERENCE_LINK, null);
            }
        }
    }
    /**
     * Validazione elementi da inserire in base alle seguenti regole
     * - nel caso di ARTICOLO, non posso inserirlo se già presente nella promozione corrente, a qualsiasi livello
     * - nel caso di REPORTO o GRM, non posso inserirlo se già presente nella promozione con la stessa meccanica
     * - nel caso di TOTALE o ATTRIBUTO, posso inserirlo sempre
     * @param testataEntity la promozione associata
     * @param meccanicaEntity la meccanica associata
     * @param items lista di elementi da inserire
     * @param tipoElemento tipo elemento da inserire
     * @return lista di elementi da inserire, pulita da quelli non inseribili
     */
    public Set<ItemPojo> validateItems(PromozioneTestataEntity testataEntity, MeccanicheEntity meccanicaEntity,
                                       Set<ItemPojo> items, PromoPianificazioneEnum tipoElemento) {
        switch (tipoElemento) {
            case TOTALE:
            case ATTRIBUTO:
                log.debug(String.format("Nessuna validazione da applicare per il tipo '%s'", tipoElemento.getTipoElemento()));
                return items;
            case ARTICOLO:
            case GRM:
            case REPARTO:
                final CfgConfHeaderEntity confHeader = confHeaderServiceInstance.get()
                        .findByMeccanicaIdAndSetPianificazioneId(meccanicaEntity.getId(),
                                testataEntity.getMuiCfgSetPianificazione().getId());
                if (confHeader != null) {
                    final Boolean duplicaFlag = getDuplicaFlagForHeader(confHeader, tipoElemento);
                    if (duplicaFlag == null || !duplicaFlag) {
                        // Scarto elementi duplicati
                        testataEntity = promoService.findById(testataEntity.getId());
                        final List<Long> usedItems = getUsedItems(testataEntity, meccanicaEntity.getId(),
                                confHeader.getLivelloPianificazione().getCodice(), tipoElemento.getTipoElemento());
                        return items.stream().filter(i -> !usedItems.contains(i.getItemId())).collect(Collectors.toSet());
                    } else {
                        log.debug(String.format("Nessuna validazione da applicare per il tipo '%s'", tipoElemento.getTipoElemento()));
                        return items;
                    }
                } else {
                    log.error(String.format("Error getting confHeader from meccanica %d and cfgSetPianificazione %d",
                            meccanicaEntity.getId(), testataEntity.getMuiCfgSetPianificazione().getId()));
                    return null;
                }
            default:
                log.warn(String.format("Tipo elemento '%s' non gestito", tipoElemento));
                return null;
        }
    }
    public List<Long> getUsedItems(PromozioneTestataEntity testata, Long idMeccanica, String livello,
                                   String tipoElemento) {
        return testata.getPromozionePianificazioneEntities().stream()
                .filter(p -> idMeccanica.equals(p.getMeccanicaEntity().getId())
                        && (PlanningLevelEnum.ELEMENTO.getDescription().equalsIgnoreCase(livello) || p.getParent() != null)
                        && tipoElemento.equalsIgnoreCase(p.getTipoElemento()))
                .map(p -> Long.parseLong(p.getCodiceElemento()))
                .collect(Collectors.toList());
    }

    public Boolean getDuplicaFlagForHeader(@NonNull final CfgConfHeaderEntity confHeader,
                                           PromoPianificazioneEnum tipoElemento) {
        return getDuplicaFlagForHeader(confHeader, tipoElemento.getTipoElemento());
    }

    public Boolean getDuplicaFlagForHeader(@NonNull final CfgConfHeaderEntity confHeader,
                                           ElementType tipoElemento) {
        return getDuplicaFlagForHeader(confHeader, tipoElemento.getDescription());
    }

    public Boolean getDuplicaFlagForHeader(@NonNull final CfgConfHeaderEntity confHeader, String tipoElemento) {
        final ElementType elementType = ElementType.fromDescription(tipoElemento);
        if (elementType == null) {
            log.warn(String.format("Tipo elemento '%s' non gestito", tipoElemento));
            return true;
        }
        switch (elementType) {
            case REPARTO:
                return confHeader.getDuplicaReparto();
            case GRM:
                return confHeader.getDuplicaGrm();
            case ARTICOLO:
                return confHeader.getDuplicaArticolo();
            case TOTALE:
                return confHeader.getDuplicaTotale();
            default:
                log.warn(String.format("Tipo elemento '%s' non gestito", tipoElemento));
                return true;
        }
    }

}
