package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.ObiettivoBean;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@MuiViewModel("totalizzatoriPromo")
@Dependent
@Slf4j
public class TotalizzatoriPromoView extends AbstractDBPromoNavigation {

    private static final long serialVersionUID = -4985183993092027769L;
    private final UserFilterUtils userFilterUtils = new UserFilterUtils();

    @Inject
    private PromoService promoService;

    @Inject
    private Instance<OwnershipService> ownershipServiceInstance;

    @Inject
    @Getter
    private ObiettivoBean obiettivoBean;

    @Getter
    @Setter
    PromozioneTestataEntity promotionSelected;

    @Getter
    Date ultimaPubblicazione;

    @Getter
    private PromozioneStatoEntity statoCorrente;

    List<PromozioneTestataEntity> promozioni;

    @Getter
    PromozioneTestataEntity promozioneCorrente;

    @Getter
    Object idPromozioneCorrente;

    @Getter
    Object idStatoTransizioneCorrente;

    @Getter
    PromoPubblicazioneTestataEntity lastPublicationDate;

    @Getter
    String lastPublicationDateStr;

    @Getter
    @Setter
    private Integer activeTab = 0;

    @Getter
    @Setter
    private boolean tabObiettiviPremioRendered = true;

    private final SimpleDateFormat sdf = new SimpleDateFormat("E dd-MM-yyyy");

    @Inject
    @Getter
    private ApplicationProperties applicationProperties;

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    public void init() {
        final UsersEntity user = ((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
        readPromotions(user.getDbFilters());

        if ((promozioni != null) && (!promozioni.isEmpty())) {
            final PromozioneTestataEntity testata = promozioni.get(0);
            setPromozioneCorrente(testata);
            PromozioneStatoEntity lastStatus = getLastStatus(testata);
            ultimaPubblicazione = null;
            Optional<Date> dt = testata.getPromoPubblicazioneTestataEntities().stream()
                    .map(PromoPubblicazioneTestataEntity::getDataPubblicazione).max(Date::compareTo);
            if (dt.isPresent()) {
                ultimaPubblicazione = dt.get();
            } else {
                log.warn("non ci sono pubblicazioni per la testata promozionale id " + testata.getId());
            }
        }
        log.info("view constructed");
    }

    private void readPromotions(String dbFilters) {
        try {
            promozioni = promoService
                    .findAllNotCancelled(userFilterUtils.createUserFiltersMapToQueryString(dbFilters),
                            getUserDto().getCanali())
                    .stream().sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
                    .collect(Collectors.toList());
            log.debug("promozioni recuperate ed ordinate");
        } catch (final Exception e) {
            log.error("Error reading promozioni", e);
        }
    }

    public void initCreaObiettivoDialog() {
        obiettivoBean.initCreaObiettivoDialog(promotionSelected);
    }

    @Override
    public void setIdPromozioneCorrente(Object idPromozioneCorrente) {
        this.idPromozioneCorrente = idPromozioneCorrente;
        log.debug("setting id promozione corrente to " + idPromozioneCorrente);
        if (idPromozioneCorrente != null) {
            try {
                PromozioneTestataEntity promozioneTestataEntity = promoService
                        .findById(Long.valueOf(idPromozioneCorrente.toString()));
                setPromozioneCorrente(promozioneTestataEntity);
                final boolean hasOwnership = isUserAdmin() || ownershipServiceInstance.get()
                        .hasOwnership(promozioneTestataEntity, getUserDto().getGruppi());
                PromozioneStatoEntity lastStatus = getLastStatus(promozioneTestataEntity);
                if (lastStatus != null) {
                    final StatoPromozioneEntity statoPromozioneEntity = lastStatus.getStatoPromozioneEntity();
                }
            } catch (Exception e) {
                log.error("error retrieving promozioneTestata with id " + idPromozioneCorrente, e);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore nel recupero della promozione con id " + idPromozioneCorrente));
            }
        } else {
            setPromotionSelected(null);
        }
    }

    public void updateCurrentTab() {
        final String indexParam = this.getRequestParameterMap().get("tabIndex");
        try {
            this.activeTab = Integer.valueOf(indexParam);
        } catch (Exception ex) {
            log.error("Error updating current tab from requestParameter {}", indexParam, ex);
        }
    }

    private void setPromozioneCorrente(PromozioneTestataEntity promozione) {
        try {
            setPromotionSelected(promozione);
            if (this.promotionSelected != null) {
                updateStato();
                this.idPromozioneCorrente = this.promotionSelected.getId();
                this.lastPublicationDate = promozione.getPromoPubblicazioneTestataEntities().stream()
                        .max(Comparator.comparing(PromoPubblicazioneTestataEntity::getDataPubblicazione))
                        .orElse(null);
                if (this.lastPublicationDate != null) {
                    ultimaPubblicazione = lastPublicationDate.getDataPubblicazione();
                    this.lastPublicationDateStr = capitalize(
                            sdf.format(this.lastPublicationDate.getDataPubblicazione())).concat("  ")
                            .concat(this.lastPublicationDate.getStatoPromozioneEntity().getCodiceStato()).concat(" ")
                            .concat(this.lastPublicationDate.getStatoPromozioneEntity().getLabel());
                } else {
                    this.ultimaPubblicazione = null;
                    this.lastPublicationDateStr = null;
                }
            } else {
                this.statoCorrente = null;
                this.idPromozioneCorrente = null;
            }
            applyRules();
        } catch (Exception e) {
            log.error("Error setting promozione", e);
        }
    }


    public void reloadPromoCorrente() {
        if (getPromotionSelected() != null) {
            setPromozioneCorrente(getTestataById(getPromotionSelected().getId()));
        }
    }

    private String capitalize(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private void updateStato() {
        if (promotionSelected != null) {
            statoCorrente = promotionSelected.getPromozioneStatoEntities().stream()
                    .filter(st -> st.getDataFineStato() == null).findFirst().orElse(null);
        }
    }

    private void handleTabObiettiviPremioRendered() {
        if (idPromozioneCorrente == null) {
            this.tabObiettiviPremioRendered = false;
            return;
        }
        final PromozioneTestataEntity promozione = getTestataById((long) idPromozioneCorrente);
        if (promozione == null) {
            this.tabObiettiviPremioRendered = false;
            return;
        }
        this.tabObiettiviPremioRendered = promozione.getPromozioneFlags().stream()
                .anyMatch(getMuiPromozioneFlagEntityPredicate());
    }

    private Predicate<MuiPromozioneFlagEntity> getMuiPromozioneFlagEntityPredicate() {
        return pf -> pf.getFlag() != null
                && "FLG_MISSIONI".equalsIgnoreCase(pf.getFlag().getCodice())
                && "si".equalsIgnoreCase(pf.getValore());
    }

    @Override
    public void updateView() {
        executeScript("refreshGridFilterByRadioCheck(" + this.idPromozioneCorrente + ");");
    }

    @Override
    public void applyRules() {
        handleTabObiettiviPremioRendered();
        this.activeTab = 0;
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }

    //questo viene da un remote command ... vorrei vedere chi lo chiama
    public void applyFilterToPromozioni() {
        final UsersEntity user = ((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
        readPromotions(user.getDbFilters());
        if ((idPromozioneCorrente != null) && (promozioni.stream()
                .filter(testata -> Long.valueOf(idPromozioneCorrente.toString()).equals(testata.getId())).findFirst()
                .orElse(null) == null)) {
            setIdPromozioneCorrente(null);
            setPromotionSelected(null);
        }
        if (this.idPromozioneCorrente == null) {
            executeScript("updateSchedaTab();");
        }
    }

    public List<PromozioneTestataEntity> getPromozioni() {
        readPromotions(getUserDto().getUser().getDbFilters());
        return promozioni;
    }

    public String getTabStoresMessage() {
        return applicationProperties.getProperty(ApplicationProperties.TAB_STORES_MESSAGE,
                ApplicationProperties.DEFAULT_TAB_STORES_MESSAGE);
    }

    public String getStatoStyleError() {
        StringBuilder sb = new StringBuilder("width: 100%; background-color: transparent;");
        if (statoCorrente != null) {
            final String codiceStato = statoCorrente.getStatoPromozioneEntity().getCodiceStato();
            if (PromoStatusEnum.IN_PIANIFICAZIONE_CON_ERRORI.getKey().equals(codiceStato)
                    || PromoStatusEnum.IN_ESECUZIONE_CON_ERRORI.getKey().equals(codiceStato)) {
                sb.append("color: #8B0000;");
            }
        }
        return sb.toString();
    }

    private PromozioneTestataEntity getTestataById(@NonNull Long id){
        return promoService.findById(id);
    }

    private PromozioneStatoEntity getLastStatus(@NonNull  PromozioneTestataEntity testata){
        return testata.getPromozioneStatoEntities().stream()
                .filter(st -> st.getDataFineStato() == null).findFirst().orElse(null);
    }
}
