package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.dbpromo.persistence.service.GrmService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class PromoConfigurationHelper {

    @Inject
    Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    Instance<PromoService> promoServiceInstance;

    @Inject
    Instance<CfgConfHeaderService> confHeaderServiceInstance;

    @Inject
    Instance<PianificazioneHelper> pianificazioneHelperInstance;

    @Inject
    Instance<ItemService> itemServiceInstance;

    @Inject
    Instance<GrmService> grmServiceInstance;

    @Inject
    Instance<RepartoService> repartoServiceInstance;

    @Inject
    Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Inject
    Instance<ConfigurationService> configurationServiceInstance;

    @Inject
    Instance<CfgPianificazioneEntityHelper> cfgPianificazioneEntityHelperInstance;

    public ObjectNode validate(final ElementType elementType, final String code, @NonNull final Long idPromozione,
                               @NonNull final Long idMeccanica, Boolean isUserAdmin, List<String> codiciGruppo) {
        final PromozioneTestataEntity testata;
        final ObjectNode node = JsonUtils.getMapper().createObjectNode();
        try {
            testata = promoServiceInstance.get().findById(idPromozione);
        } catch (Exception e) {
            log.error("PromozioneTestataEntity with id " + idPromozione + " not found ", e);
            return invalidNode(node, null);
        }
        if (testata == null) {
            log.error("Cannot find PromozioneTestata with id " + idPromozione);
            return invalidNode(node, null);
        }

        ComboBoxCapable element = null;
        switch (elementType) {
            case ARTICOLO:
                boolean isValid = true;
                ItemEntity item = itemServiceInstance.get().findByCode(code);
                if (item != null) {
                    if (isUserAdmin != null && !isUserAdmin) {
                        final List<String> buyerCodes = securityUtilInstance.get().getWritableCompratori(testata, codiciGruppo);
                        isValid = buyerCodes.contains(item.getCompratoreEntity().getCodiceCompratore());
                    }
                    element = isValid ? item : null;
                }
                break;
            case GRM:
                element = grmServiceInstance.get().findByCode(code);
                break;
            case REPARTO:
                // SOLO PER IL TIPO ELEMENTO REPARTO, i codice caricati da Excel vanno "paddati"
                // a due digit
                // riempendo di 0 a sinistra
                // ( esempio , se l'utente carica 1 .. Va ricercato il valore '01' )
                final String paddingCode = StringUtils.isNumeric(code) ? String.format("%02d", Integer.parseInt(code))
                        : code;
                element = repartoServiceInstance.get().findByCode(paddingCode);
                break;
            default:
                break;
        }
        if (element == null) {
            return invalidNode(node, code);
        }

        node.put("codice", element.getKey());
        node.put("idItem", element.getId());
        if (element instanceof ItemEntity) {
            final CompratoreEntity compratore = ((ItemEntity) element).getCompratoreEntity();
            if (compratore != null) {
                node.put("itemBuyerId", compratore.getId());
            }
        }
        final CfgConfHeaderEntity confHeader = confHeaderServiceInstance.get()
                .findByMeccanicaIdAndSetPianificazioneId(idMeccanica, testata.getMuiCfgSetPianificazione().getId());
        if (confHeader == null) {
            log.error(String.format("Error retrieving confHeader from idMeccanica %d", idMeccanica));
            return invalidNode(node, null);
        }
        // #4182: gestione flag duplica per canale
        Boolean duplicaFlagForCanale = pianificazioneHelperInstance.get()
                .getDuplicaFlagForCanale(testata.getMuiCanalePromozione(), elementType);
        if (!Boolean.TRUE.equals(duplicaFlagForCanale)) {
            final List<Long> usedItems = pianificazioneHelperInstance.get()
                    .getUsedItems(testata, elementType.getDescription());
            if (usedItems.contains(element.getId())) {
                // Duplicato, non valido
                node.put("descrizione", "elemento duplicato in pianificazione");
                node.put("isValid", false);
                return node;
            }
        }

        final Boolean flagDuplica = pianificazioneHelperInstance.get().getDuplicaFlagForHeader(confHeader, elementType);
        if (flagDuplica == null || !flagDuplica) {
            final List<Long> usedItems = pianificazioneHelperInstance.get().getUsedItems(testata, idMeccanica,
                    confHeader.getLivelloPianificazione().getCodice(), elementType.getDescription());
            if (usedItems.contains(element.getId())) {
                // Duplicato, non valido
                node.put("descrizione", "elemento duplicato in pianificazione");
                node.put("isValid", false);
            } else {
                // Valido
                node.put("descrizione", element.getLabel());
                node.put("isValid", true);
            }
        } else {
            // Non considero il flag, l'elemento è valido
            node.put("descrizione", element.getLabel());
            node.put("isValid", true);
        }
        return node;
    }

    private ObjectNode invalidNode(ObjectNode node, String code) {
        if (code != null) {
            node.put("codice", code);
        }
        node.put("descrizione", "");
        node.put("isValid", false);
        return node;
    }

    /**
     * Questo metodo restituisce tutte le configurazioni ordinate in base al campo
     * ORDINAMENTO della tabella MUI_CFG_PIANIFICAZIONE
     *
     * @param testata
     * @return
     */
    public List<CfgPianificazioneEntity> getConfigurazioniSorted(PromozioneTestataEntity testata) {

        List<CfgPianificazioneEntity> configurazioniSorted;

        configurazioniSorted = pianificazioneServiceInstance.get().findAllCfgPianificazioneEntitiesByPromozione(testata);

        if (configurazioniSorted != null) {
            configurazioniSorted = configurazioniSorted.stream()
                    .sorted(Comparator.comparing(CfgPianificazioneEntity::getOrdinamento,
                            Comparator.nullsLast(Comparator.naturalOrder())))
                    .filter(entity -> entity.getMuiCfgPianificazioneCampi() != null
                            && entity.getMuiCfgPianificazioneCampi().getDescrizione() != null)
                    .collect(Collectors.toList());
        } else {
            configurazioniSorted = new ArrayList<>();
        }

        return configurazioniSorted;
    }

    /**
     * Questo metodo restituisce la configurazione per ogni testata filtrata in
     * funzione di questa regola:
     * <p>
     * La lista di campi ottenuta può contenere più occorrenze per lo stesso campo.
     * per ottenere una lista univoca di campi : prendere i campi una sola volta in
     * funzione del valore MIN di MUI_CFG_PIANIFICAZIONE.ORDINAMENTO
     *
     * @param testata
     * @return
     */
    public List<CfgPianificazioneEntity> getConfigurazioniFiltrate(PromozioneTestataEntity testata) {

        final List<CfgPianificazioneEntity> configurazioniFiltrate = new ArrayList<>();

        if (testata.getPromozionePianificazioneEntities() != null
                && testata.getPromozionePianificazioneEntities().size() > 0) {
            final List<CfgPianificazioneEntity> pianificazioni = pianificazioneServiceInstance
                    .get().findAllCfgPianificazioneEntitiesByPromozione(testata);

            if (pianificazioni != null) {
                pianificazioni.stream()
                        .filter(entity -> entity.getMuiCfgPianificazioneCampi() != null
                                && entity.getMuiCfgPianificazioneCampi().getDescrizione() != null)
                        .collect(Collectors.groupingBy(entity -> entity.getMuiCfgPianificazioneCampi().getDescrizione(),
                                Collectors.mapping(entity -> entity, Collectors.toList())))
                        .forEach((k, v) -> configurazioniFiltrate
                                .add(v.stream().min(Comparator.comparing(CfgPianificazioneEntity::getOrdinamento,
                                        Comparator.nullsLast(Comparator.naturalOrder()))).get()));
            }

        }
        configurazioniFiltrate.sort(Comparator.comparing(CfgPianificazioneEntity::getOrdinamento,
                Comparator.nullsLast(Comparator.naturalOrder())));
        return configurazioniFiltrate;
    }

    /**
     * Recupero header configurazione partendo da una promozione e una meccanica
     *
     * @param testataEntity   testata promozione associata
     * @param meccanicaEntity meccanica associata
     * @return
     */
    public CfgConfHeaderEntity getHeaderFromTestataAndMeccanica(@NonNull PromozioneTestataEntity testataEntity,
                                                                @NonNull MeccanicheEntity meccanicaEntity) {
        return testataEntity.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
                .filter(h -> meccanicaEntity.getId().equals(h.getMeccanicaEntity().getId()))
                .findFirst()
                .orElse(null);
    }

    public CfgPianificazioneEntity getConfigurationForField(CfgSetPianificazioneEntity cfgSetPianificazione,
                                                            MeccanicheEntity meccanica,
                                                            String field) {
        List<CfgPianificazioneEntity> configurazioni = configurationServiceInstance.get()
                .findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanica, field);
        if (configurazioni == null || configurazioni.isEmpty()) {
            log.error(String.format("Campo '%s' non configurato per meccanica '%s'", field, meccanica.getCodiceMeccanica()));
            return null;
        }
        if (configurazioni.size() > 1) {
            log.warn(String.format("il campo '%s' e' configurato %d volte per la meccanica '%s'",
                    field, configurazioni.size(), meccanica.getCodiceMeccanica()));
        }
        return configurazioni.get(0);
    }
}
