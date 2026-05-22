package com.axiante.mui.webapp.utils;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.NumSetUtils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class MeccanicheUtil {

    @Inject
    private Instance<CfgPianificazioneService> cfgPianificazioneServiceInstance;

    @Inject
    private Instance<PianificazionePromoUtil> pianificazionePromoUtilInstance;

    @Inject
    private Instance<ConfigurationService> configurationServiceInstance;

    @Inject
    private Instance<NumSetUtils> numSetUtilsInstance;
    private final PicklistUtils picklistUtils = new PicklistUtils();

    /**
     * Se la promozione corrente ha gia' una riga di pianificazione allora devo controllare se la riga
     * ha la meccanica unica in promo, altrimenti vanno bene tutte.
     * Se la meccanica e' unica in promo allora l'unica possibile e' quella gia' inserita.
     * In caso contrario non e' possibile inserire le meccaniche di tipo unica in promo.
     * Le meccaniche disponibili sulla testata promozionale vengono filtrare in base al livello di pianificazione.
     * Se il canale promozionale associato alla testata consente la duplicazione del tipo elemento TOTALE e sto considerando
     * solo le meccaniche disponibili per una pianificazione con tipo elemento TOTALE, devo escludere le meccaniche
     * con tipo elemento TOTALE già utilizzate sulla stessa testata promozionale.
     *
     * @param testata          testata promozionale
     * @param canali           lista canali
     * @param planningLevel    livello pianificazione
     * @param isElementoTotale flag se sto filtrando le meccaniche disponibili in base al tipo elemento TOTALE in pianificazione
     * @return lista meccaniche disponibili
     */
    public List<MeccanicheEntity> getMeccanicheDisponibili(@NonNull PromozioneTestataEntity testata,
                                                           List<CanalePromozioneEntity> canali,
                                                           PlanningLevelEnum planningLevel,
                                                           boolean isElementoTotale) {
        List<MeccanicheEntity> disponibili = getMeccanicheDisponibili(testata, canali, planningLevel);
        if (Boolean.TRUE.equals(testata.getMuiCanalePromozione().getDuplicaTotale()) && isElementoTotale) {
            disponibili = disponibili.stream()
                    .filter(m -> isMeccanicaAddableForElementoTotale(testata, m.getCodiceMeccanica()))
                    .collect(Collectors.toList());
        }
        return disponibili;
    }

    /**
     * Se la promozione corrente ha gia' una riga di pianificazione allora devo controllare se la riga
     * ha la meccanica unica in promo, altrimenti vanno bene tutte.
     * Se la meccanica e' unica in promo allora l'unica possibile e' quella gia' inserita.
     * In caso contrario non e' possibile inserire le meccaniche di tipo unica in promo.
     * Le meccaniche disponibili sulla testata promozionale vengono filtrare in base al livello di pianificazione.
     *
     * @param testata       testata promozionale
     * @param canali        lista canali
     * @param planningLevel livello pianificazione
     * @return lista meccaniche disponibili
     */
    public List<MeccanicheEntity> getMeccanicheDisponibili(@NonNull PromozioneTestataEntity testata,
                                                           List<CanalePromozioneEntity> canali,
                                                           PlanningLevelEnum planningLevel) {
        Set<PromozioneMeccanicheEntity> meccaniche = getMeccanicheFromTestata(testata);
        if (planningLevel != null) {
            final Predicate<PromozioneMeccanicheEntity> byPlanningLevel = m -> testata.getMuiCfgSetPianificazione()
                    .getMuiCfgConfHeaders().stream()
                    .anyMatch(h -> h.getMeccanicaEntity().equals(m.getMeccanicheEntity())
                            && h.getLivelloPianificazione().getCodice().equals(planningLevel.getDescription()));
            meccaniche = meccaniche.stream().filter(byPlanningLevel).collect(Collectors.toSet());
        }
        return meccaniche.stream()
                .map(PromozioneMeccanicheEntity::getMeccanicheEntity)
                .filter(m -> isMeccanicaAddable(testata, m, canali))
                .sorted(Comparator.comparing(MeccanicheEntity::getCodiceMeccanica))
                .collect(Collectors.toList());
    }

    /**
     * Se la promozione corrente ha gia' una riga di pianificazione allora devo controllare se la riga
     * ha la meccanica unica in promo, altrimenti vanno bene tutte.
     * Se la meccanica e' unica in promo allora l'unica possibile e' quella gia' inserita.
     * In caso contrario non e' possibile inserire le meccaniche di tipo unica in promo.
     * @param testata testata promozionale
     * @param canali  lista canali
     * @return lista meccaniche disponibili
     */
    public List<MeccanicheEntity> getMeccanicheDisponibili(@NonNull PromozioneTestataEntity testata,
                                                           List<CanalePromozioneEntity> canali) {
        return getMeccanicheDisponibili(testata, canali, null);
    }

    private Set<PromozioneMeccanicheEntity> getMeccanicheFromTestata(PromozioneTestataEntity testata) {
        Set<PromozioneMeccanicheEntity> meccaniche = null;
        if (testata.getPromozionePianificazioneEntities() != null && !testata.getPromozionePianificazioneEntities().isEmpty()) {
            // Recupero meccaniche uniche presenti nell'header
            final List<MeccanicheEntity> meccanicheUniche = getMeccanicheUnicheInHeader(testata);
            if (!meccanicheUniche.isEmpty()) {
                // Controllo se sono presenti in pianificazione
                final List<MeccanicheEntity> meccanicheUnichePresenti = getMeccanicheUnicheInPianificazione(testata, meccanicheUniche);
                if (!meccanicheUnichePresenti.isEmpty()) {
                    // Tengo solo la meccanica presente
                    if (meccanicheUnichePresenti.size() > 1) {
                        // Loggo errore: non dovrebbero esserci piu' di una meccanica in pianificazione
                        log.error(String.format("Errore in pianificazione per la promozione %d: presenti le seguenti meccaniche uniche %s",
                                testata.getId(), meccanicheUnichePresenti.stream().map(MeccanicheEntity::getCodiceMeccanica)
                                        .collect(Collectors.joining(","))));
                    }
                    meccaniche = testata.getPromozioneMeccanicheEntities().stream()
                            .filter(m -> m.getMeccanicheEntity().equals(meccanicheUnichePresenti.get(0)))
                            .collect(Collectors.toSet());
                } else {
                    // Rimuovo le meccaniche uniche presenti nella lista delle meccaniche possibili
                    meccaniche = testata.getPromozioneMeccanicheEntities().stream()
                            .filter(m -> !meccanicheUniche.contains(m.getMeccanicheEntity()))
                            .collect(Collectors.toSet());
                }
            } else {
                // Non ho meccaniche uniche, vanno bene tutte
                meccaniche = testata.getPromozioneMeccanicheEntities();
            }
        } else {
            // Non ho ancora pianificazioni in testata, vanno bene tutte
            meccaniche = testata.getPromozioneMeccanicheEntities();
        }
        if (meccaniche != null) {
            final PianificazionePromoUtil pianificazionePromoUtil = pianificazionePromoUtilInstance.get();
            return meccaniche.stream()
                    .filter(m -> pianificazionePromoUtil.isMeccanicaAvailable(testata, m.getMeccanicheEntity().getCodiceMeccanica()))
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    private boolean isMeccanicaAddable(PromozioneTestataEntity testata, MeccanicheEntity meccanica,
                                       List<CanalePromozioneEntity> canali) {
        final CanalePromozioneEntity canale = testata.getCanalePromozioneEntity();
        boolean addable = true;
        final CfgPianificazioneEntity cfgBuonoSconto = getConfigurazioneForMeccanicaAndField(canale, meccanica,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN);
        if (cfgBuonoSconto != null && cfgBuonoSconto.getTipoLista() != null) {
            addable = isMeccanicaAddableForBuonoSconto(testata, cfgBuonoSconto, meccanica, canali);
        }
        final CfgPianificazioneEntity cfgNumSet = getConfigurazioneForMeccanicaAndField(canale, meccanica,
                PianificazioneConstants.REFERENCE_NUM_SET_COLUMN);
        if (cfgNumSet != null && cfgNumSet.getLista() != null) {
            addable = isMeccanicaAddableForNumSet(testata, meccanica);
        }
        return addable;
    }

    private boolean isMeccanicaAddableForBuonoSconto(PromozioneTestataEntity testata, CfgPianificazioneEntity cfg,
                                                     MeccanicheEntity meccanica, List<CanalePromozioneEntity> canali) {
        // Controllo che per ogni meccanica siano ancora disponibili dei valori in
        // pickList per il campo BUONO_SCONTO_PROGRESSIVO campagna
        List<String> pickListValues = pianificazionePromoUtilInstance.get().getCorrectFormatPickListValues(cfg.getLista());
        if (cfg.getTipoLista().startsWith("ws://")) {
            if (StringUtils.isNotBlank(cfg.getChiave())){
                // lista condizionale
                return true;
            }
            // webservice, vedi se ci sono numeri usabili nel range della lista
            final List<Integer> range = picklistUtils.defineListaCellEditorAsInts(cfg.getLista());
            final Integer buonoScontoRadice = getBuonoScontoRadice(testata, meccanica);
            final List<Integer> values = pianificazionePromoUtilInstance.get().getAvailableProgressiveDiscountCodesBuoniPotenziamento(
                    testata, buonoScontoRadice, pickListValues, range);
            return pianificazionePromoUtilInstance.get().isMeccanicaAvailable(testata, meccanica.getCodiceMeccanica())
                    && (buonoScontoRadice == null || !values.isEmpty());
        }
        return pianificazionePromoUtilInstance.get().isMeccanicaAvailable(testata, meccanica.getCodiceMeccanica())
                && pianificazionePromoUtilInstance.get().createColumnValueArray(
                        testata, pickListValues.toArray(new String[0]), PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN,
                        meccanica, canali).length > 0;
    }

    private boolean isMeccanicaAddableForNumSet(PromozioneTestataEntity testata, MeccanicheEntity meccanica) {
        // #3438: Meccanica selezionabile solo se ci sono dei valori per il campo NUM_SET disponibili
        final CfgConfHeaderEntity cfgConfHeader = testata.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
                .filter(h -> meccanica.getId().equals(h.getMeccanicaEntity().getId()))
                .findFirst()
                .orElse(null);
        if (cfgConfHeader == null) {
            log.error(String.format("Errore nel recupero confHeader per la promozione con id %s e meccanica %s",
                    testata.getId(), meccanica.getId()));
            return false;
        }
        try {
            final List<String> codiciMeccanica = Arrays.asList("M141", "M142", "M143");
            if (codiciMeccanica.contains(meccanica.getCodiceMeccanica())) {
                String numSet = numSetUtilsInstance.get().getNumSetPrecaricate(testata, meccanica, cfgConfHeader);
                if (numSet == null) {
                    final String msg = String.format("Non ci sono valori disponibili per il campo '%s' " +
                                    "per la promozione con id '%d' e meccanica '%s'",
                            PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, testata.getId(),
                            meccanica.getCodiceMeccanica());
                    log.error(msg);
                    return false;
                }
                return true;
            }
            final String numSet = numSetUtilsInstance.get().getNumSet(testata, meccanica, cfgConfHeader);
            if (numSet == null) {
                log.info(String.format("Il campo %s non e' mandatory per la promozione con id %s e meccanica %s",
                        PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, testata.getId(),
                        meccanica.getCodiceMeccanica()));
            }
            return true;
        } catch (Exception ex) {
            final String msg = String.format("Non ci sono valori disponibili per il campo '%s' " +
                            "per la promozione con id '%d' e meccanica '%s'",
                    PianificazioneConstants.REFERENCE_NUM_SET_COLUMN, testata.getId(),
                    meccanica.getCodiceMeccanica());
            log.error(msg, ex);
            return false;
        }
    }

    private boolean isMeccanicaAddableForElementoTotale(PromozioneTestataEntity testata, String codiceMeccanica) {
        return !testata.getPromozionePianificazioneEntities().stream()
                .filter(Objects::nonNull)
                .filter(p -> ElementType.TOTALE.getDescription().equals(p.getTipoElemento()))
                .map(PromozionePianificazioneEntity::getMeccanicaEntity)
                .filter(Objects::nonNull)
                .map(MeccanicheEntity::getCodiceMeccanica)
                .collect(Collectors.toList())
                .contains(codiceMeccanica);
    }

    private Integer getBuonoScontoRadice(PromozioneTestataEntity testata, final MeccanicheEntity meccanica) {
        final String defValue = configurationServiceInstance.get()
                .findBySetAndMeccanicaAndCampo(testata.getMuiCfgSetPianificazione(), meccanica,
                        PianificazioneConstants.REFERENCE_BUONO_SCONTO_RADICE)
                .stream()
                .findFirst()
                .map(CfgPianificazioneEntity::getDefValue)
                .orElse(null);
        if (defValue == null) {
            return null;
        }
        try {
            return Integer.parseInt(defValue);
        } catch (NumberFormatException ex) {
            log.warn("Error parsing default value as integer", ex);
            return null;
        }
    }

    private CfgPianificazioneEntity getConfigurazioneForMeccanicaAndField(CanalePromozioneEntity canale,
                                                                          MeccanicheEntity meccanica, String field) {
        return cfgPianificazioneServiceInstance.get()
                .findPianificazioneByCanaleMeccanica(canale, meccanica)
                .stream()
                .filter(cfg -> cfg.getMuiCfgPianificazioneCampi() != null)
                .filter(cfg -> field.equals(cfg.getMuiCfgPianificazioneCampi().getCampo()))
                .findFirst().orElse(null);
    }

    private List<MeccanicheEntity> getMeccanicheUnicheInHeader(PromozioneTestataEntity testata) {
        return testata.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
                .filter(h -> h.getUnicaInPromo() != null && h.getUnicaInPromo().equals(1))
                .map(CfgConfHeaderEntity::getMeccanicaEntity)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<MeccanicheEntity> getMeccanicheUnicheInPianificazione(PromozioneTestataEntity testata,
                                                                       List<MeccanicheEntity> meccanicheUniche) {
        return testata.getPromozionePianificazioneEntities().stream()
                .map(PromozionePianificazioneEntity::getMeccanicaEntity)
                .filter(meccanicheUniche::contains)
                .distinct()
                .collect(Collectors.toList());
    }
}
