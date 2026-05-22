package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.webservice.util.configuration.CfgConfHeaderEntityHelper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Dependent
@Slf4j
public class NumSetUtils {
    @Inject
    Instance<PromoService> promoServiceInstance;

    @Inject
    Instance<CfgConfHeaderEntityHelper> cfgConfHeaderHelperInstance;

    public String getNumSetPrecaricate(PromozioneTestataEntity testata, MeccanicheEntity meccanica,
                                       CfgConfHeaderEntity cfgConfHeader) throws Exception {
        if (!testata.getMuiCanalePromozione().getFlOverlapOffset()) {
            return getNumSet(testata, meccanica, cfgConfHeader);
        }
        CfgPianificazioneEntity cfgPianificazione = getNumSetMandatory(cfgConfHeader);
        if (cfgPianificazione == null) {
            log.error(String.format("Il campo %s non è configurato per la meccanica %s nella promozione %s",
                    PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, meccanica.getCodiceMeccanica(),
                    testata.getCodicePromozione()));
            return null;
        }
        final String lista = getListaByCfgPianificazioneAndCanale(cfgPianificazione, testata.getMuiCanalePromozione());
        if (lista == null || lista.isEmpty()) {
            log.error(String.format("Il campo %s non ha configurato una lista valida per la meccanica %s nella promozione %s",
                    PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, meccanica.getCodiceMeccanica(),
                    testata.getCodicePromozione()));
            return null;
        }
        final PicklistUtils picklistUtils = new PicklistUtils();
        final List<Integer> values = picklistUtils.defineListaCellEditorAsInts(lista);
        if (values == null || values.isEmpty()) {
            log.error(String.format("Errore recupero valori lista dalla configurazione con id %s", cfgPianificazione.getId()));
            return null;
        }
        Set<Integer> usedValues = getUsedValuesPrecaricate(testata);
        Integer numSet = usedValues.stream().max(Integer::compareTo).map(value -> value + 1).orElse(null);
        if (numSet == null) {
            return String.valueOf(values.get(0));
        }
        if (values.contains(numSet) && !usedValues.contains(numSet)) {
            return String.valueOf(numSet);
        }
        values.removeAll(usedValues);
        return values.isEmpty() ? null : String.valueOf(values.get(0));
    }

    public String getListaByCfgPianificazioneAndCanale(CfgPianificazioneEntity cfgPianificazione,
                                                       CanalePromozioneEntity canale) {
        if (Boolean.TRUE.equals(canale.getFlListaCondizionale())) {
            final String listaCondizionale = cfgPianificazione.getListaCondizionale();
            final String lista = cfgPianificazione.getLista();
            if (listaCondizionale == null || listaCondizionale.isEmpty()) {
                if (lista != null && !lista.isEmpty()) {
                    return lista;
                }
                log.error(String.format("Lista condizionale prevista per canale '%d %s' ma non configurata",
                        canale.getCodiceCanale(), canale.getDescrizione()));
                return null;
            }
            try {
                //noinspection unchecked
                Map<String, String> listaCondizionaleMap = JsonUtils.getMapper()
                        .readValue(cfgPianificazione.getListaCondizionale(), Map.class);
                if (listaCondizionaleMap.get(String.valueOf(canale.getCodiceCanale())) == null) {
                    log.error(String.format("Lista condizionale '%s' non contiene chiave per canale '%d %s'",
                            cfgPianificazione.getListaCondizionale(), canale.getCodiceCanale(), canale.getDescrizione()));
                    return null;
                }
                return listaCondizionaleMap.get(String.valueOf(canale.getCodiceCanale()));
            } catch (IOException e) {
                log.error(String.format("Lista condizionale '%s' non formattata correttamente per il canale '%d %s'",
                        cfgPianificazione.getListaCondizionale(), canale.getCodiceCanale(), canale.getDescrizione()));
                return null;
            }
        }
        return cfgPianificazione.getLista();
    }

    /**
     * Recupero il valore da utilizzare per il numero set, secondo questa regola:
     * - solo se il campo NUM_SET in MUI_CFG_PIANIFICAZIONE è MANDATORY
     * - recupero il primo valore disponibile nel periodo dal campo LIST, dove periodo sono tutte le promozioni
     * che sono in overlap con quella di riferimento
     * - devo escludere anche i valori già utilizzati nella promozione stessa
     *
     * @param testata       promozione associata
     * @param meccanica     meccanica associata
     * @param cfgConfHeader header configurazione
     * @return il valore da utilizzare per NUM_SET, o null se non è MANDATORY
     * @throws Exception nel caso di errore nel recupero del valore da utilizzare
     */
    public String getNumSet(PromozioneTestataEntity testata, MeccanicheEntity meccanica,
                            CfgConfHeaderEntity cfgConfHeader) throws Exception {
        // Recupero configurazioni dalla testata + meccanica e controllo se il campo NUM_SET è mandatory
        final CfgPianificazioneEntity configurazione = getNumSetMandatory(cfgConfHeader);
        if (configurazione == null) {
            log.debug(String.format("Il campo %s non è mandatory per la meccanica %s nella promozione %s",
                    PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, meccanica.getCodiceMeccanica(),
                    testata.getCodicePromozione()));
            return null;
        }
        // #5361
        String lista = getListaByCfgPianificazioneAndCanale(configurazione, testata.getMuiCanalePromozione());
        // Se lista configurata non valida, ritorno null
        List<Integer> values = new ArrayList<>();
        try {
            values.addAll(validateLista(lista));
        } catch (Exception ex) {
            log.error(String.format("Errore validazione lista per '%s' per il campo cfgPianificazione con id '%d'",
                    lista, configurazione.getId()), ex);
        }
        if (values.isEmpty()) {
            return null;
        }
        //3421
        if (configurazione.getMuiCfgConfHeader() != null) {
            // ho un header quindi posso leggere il max num set dal database
            int max = configurazione.getMuiCfgConfHeader()
                    .getMuiCfgSetPianificazione() // recupero il set di configurazione
                    .getMuiCfgConfHeaders().stream() // per ogni tipo di meccanica
                    .flatMap(h -> h.getMuiCfgPianificaziones().stream()) // controllo se la meccanica usa quella lista
                    .filter(p -> PianificazioneConstants.REFERENCE_NUM_SET_COLUMN
                            .equals(p.getMuiCfgPianificazioneCampi().getCampo()))
                    .filter(p -> isListaInvolved(p)
                            && lista.equals(getListaByCfgPianificazioneAndCanale(p, testata.getCanalePromozioneEntity())))
                    .map(CfgPianificazioneEntity::getMuiCfgConfHeader)
                    .map(CfgConfHeaderEntity::getMeccanicaEntity) // e tengo solo le meccaniche che la usano
                    .distinct()
                    .flatMap(m -> m.getPromozionePianificazioneEntities().stream()) // filtro le promozioni sulle meccaniche
                    .filter(p -> PromoAcl.isActive(p.getPromozioneTestataEntity())) // che sono attive
                    .filter(p -> p.getNumSet() != null) // che hanno un num set
                    .map(p -> Double.valueOf(p.getNumSet()).intValue()).mapToInt(v -> v)
                    .max().orElse(-1); // e prendo il massimo

            if (max > -1) {
                // cerca max all'interno di values
                int index = values.indexOf(max);
                if ((index != -1) && (index < (values.size() - 1))) {
                    // se max e' presente e ho ancora numeri disponibili ....
                    return String.valueOf(values.get(index + 1));
                }
            }
        }
        final Set<Integer> usedValues = getUsedValues(testata, null);
        values.removeAll(usedValues);

        if (values.isEmpty()) {
            log.error(String.format("Non ci sono valori disponibili per il campo %s per la promozione %s",
                    PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, testata.getCodicePromozione()));
            return null;
        }
        return String.valueOf(values.get(0));
    }

    public boolean isListaInvolved(CfgPianificazioneEntity cfgPianificazione) {
        return (cfgPianificazione.getLista() != null && !cfgPianificazione.getLista().isEmpty())
                || (cfgPianificazione.getListaCondizionale() != null && !cfgPianificazione.getListaCondizionale().isEmpty());
    }

    public List<Integer> getAvailablePrecaricate(@NonNull PromozioneTestataEntity testata,
                                                 @NonNull CfgPianificazioneEntity configuredField) {
        final String lista = getListaByCfgPianificazioneAndCanale(configuredField, testata.getMuiCanalePromozione());
        if (!testata.getMuiCanalePromozione().getFlOverlapOffset()) {
            return getAvailable(testata, lista, null);
        }
        List<Integer> values = new ArrayList<>();
        try {
            values.addAll(validateLista(lista));
        } catch (Exception ex) {
            log.error(String.format("Errore validazione lista per '%s' per il campo cfgPianificazione con id '%d'",
                    lista, configuredField.getId()), ex);
        }
        if (values.isEmpty()) {
            return Collections.emptyList();
        }
        final Set<Integer> usedValues = getUsedValuesPrecaricate(testata);
        values.removeAll(usedValues);
        Collections.sort(values);
        return values;
    }

    public List<Integer> getAvailablePrecaricate(@NonNull PromozioneTestataEntity testata,
                                                 @NonNull String  lista) {
        if (!testata.getMuiCanalePromozione().getFlOverlapOffset()) {
            log.debug("filtering available numset as we need to exclude overlapping");
            return getAvailable(testata, lista, null);
        }
        log.debug("no need to filter overlapping promo");
        List<Integer> values = new ArrayList<>();
        try {
            values.addAll(validateLista(lista));
        } catch (Exception ex) {
            log.error("Errore validazione lista per {} per il campo cfgPianificazione ",
                    lista, ex);
        }
        log.debug("available numset: {}", values.size());
        if (values.isEmpty()) {
            return Collections.emptyList();
        }
        final Set<Integer> usedValues = getUsedValuesPrecaricate(testata);
        values.removeAll(usedValues);
        Collections.sort(values);
        return values;
    }

    public List<Integer> getAvailable(@NonNull PromozioneTestataEntity testata, String lista,
                                      List<CanalePromozioneEntity> canali) {
        List<Integer> values = new ArrayList<>();
        try {
            values.addAll(validateLista(lista));
        } catch (Exception ex) {
            log.error(String.format("Errore validazione lista per '%s' per la promozione con id '%d'",
                    lista, testata.getId()), ex);
        }
        log.debug("avaliable numset before filtering out: {}",values.size());
        if (values.isEmpty()) {
            return Collections.emptyList();
        }
        final Set<Integer> usedValues = getUsedValues(testata, canali);
        values.removeAll(usedValues);
        Collections.sort(values);
        log.debug("available numset after filtering out: {}", values.size());
        return values;
    }

    private List<Integer> validateLista(String lista) {
        if (lista == null || lista.isEmpty()) {
            throw new RuntimeException(String.format("Lista '%s' non configurata per il campo CfgPianificazione",
                    lista));
        }
        final PicklistUtils picklistUtils = new PicklistUtils();
        final List<Integer> values = picklistUtils.defineListaCellEditorAsInts(lista);
        if (values == null || values.isEmpty()) {
            throw new RuntimeException(String.format("Lista '%s' non configurata correttamente per il campo CfgPianificazione",
                    lista));
        }
        return values;
    }

    private Set<Integer> getUsedValuesPrecaricate(PromozioneTestataEntity promozione) {
        List<String> codiciMeccanica = Arrays.asList("M141", "M142", "M143");
        // cerco tutti i NUM_SET utilizzati dalle promozione in overlap (con offset) alla corrente
        final List<PromozioneTestataEntity> overlappingPromo = promoServiceInstance.get()
                .findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozione, codiciMeccanica);
        Set<Integer> usedValues = overlappingPromo
                .stream()
                .flatMap(t -> t.getPromozionePianificazioneEntities().stream())
                .filter(Objects::nonNull)
                .map(PromozionePianificazioneEntity::getNumSet)
                .filter(Objects::nonNull)
                .distinct()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        // aggiungo tutti i NUM_SET gia' usati dalla promozione corrente
        final Set<Integer> usedInSamePromo = promozione.getPromozionePianificazioneEntities().stream()
                .filter(Objects::nonNull)
                .map(PromozionePianificazioneEntity::getNumSet)
                .filter(Objects::nonNull)
                .distinct()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        usedValues.addAll(usedInSamePromo);
        return usedValues;
    }

    private Set<Integer> getUsedValues(@NonNull final PromozioneTestataEntity testata,
                                       List<CanalePromozioneEntity> canali) {
        List<PromozioneTestataEntity> overlappingPromo = promoServiceInstance.get().findOverlappingPromo(testata, canali);
        overlappingPromo.add(testata);
        return overlappingPromo.stream().flatMap(t -> t.getPromozionePianificazioneEntities().stream())
                .filter(Objects::nonNull)
                .filter(p -> PianificazioneRowTypeEnum.SET.getTypeCode().equals(p.getTipoRiga().getCodiceTipo()))
                .map(PromozionePianificazioneEntity::getNumSet)
                .filter(Objects::nonNull)
                .distinct()
                .mapToDouble(Double::parseDouble)
                .boxed()
                .map(Double::intValue)
                .collect(Collectors.toSet());
    }

    /**
     * Recupero la configurazione relativo al campo NUM_SET nel caso sia MANDATORY
     * @param confHeaderEntity header configurazione
     * @return configurazione NUM_SET se MANDATORY, null altrimenti
     */
    private CfgPianificazioneEntity getNumSetMandatory(CfgConfHeaderEntity confHeaderEntity) {
        return confHeaderEntity.getMuiCfgPianificaziones().stream()
                .filter(Objects::nonNull)
                .filter(p -> p.getMuiCfgPianificazioneCampi() != null)
                .filter(p -> PianificazioneConstants.REFERENCE_NUM_SET_COLUMN.equals(p.getMuiCfgPianificazioneCampi().getCampo()))
                .filter(p -> "1".equals(p.getMandatory()))
                .findFirst()
                .orElse(null);
    }
}
