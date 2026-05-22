package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("checkSovrapposizioniView")
@Dependent
@Slf4j
public class CheckSovrapposizioniView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -7977444394596863272L;

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Getter
    private List<PromozioneTestataEntity> promozioni;

    @Getter
    private Long idPromozioneCorrente;

    @Getter
    private PromozioneTestataEntity promotionSelected;

    @Getter
    private StatoPromozioneEntity statoPromozioneSelected;

    @Getter
    @Setter
    private boolean selectedYesBtnRendered = false;

    @Getter
    @Setter
    private boolean selectedNoBtnRendered = false;

    @Getter
    private String changeEccezioneDialogMsg;

    private UserDTO userDTO;
    private UserFilterUtils userFilterUtils = new UserFilterUtils();
    private List<Long> selectedIds;
    private Boolean flagAbilitaCheck = null;

    @Override
    public void setNode(final MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    public void init() {
        userDTO = getUserDto();
        readPromozioni();
        autoselectFirstPromozione();
    }

    public void setIdPromozioneCorrente(Long idPromozioneCorrente) {
        this.idPromozioneCorrente = idPromozioneCorrente;
        if (idPromozioneCorrente != null) {
            this.promotionSelected = promozioni.stream().filter(p -> idPromozioneCorrente.equals(p.getId()))
                    .findFirst().orElse(null);
        } else {
            this.promotionSelected = null;
        }
        if (this.promotionSelected != null) {
            this.statoPromozioneSelected = PromoAcl.getStatoCorrente(promotionSelected);
        } else {
            this.statoPromozioneSelected = null;
        }
        updateButtons(false);
        resetSelectedItems();
        changeEccezioneDialogMsg = null;
    }

    public void handleItemSelected() {
        final Map<String, String> params = getRequestParameterMap();
        final String itemSelectedJsonArray = params.get("itemSelected");
        resetSelectedItems();
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemSelectedJsonArray);
            if ((jsonNode != null) && (jsonNode.size() > 0)) {
                updateButtons(true);
                jsonNode.forEach(n -> selectedIds.add(n.asLong()));
            } else {
                updateButtons(false);
            }
        } catch (Exception ex) {
            log.error("Error read json item selected", ex);
            updateButtons(false);
        }
    }

    public void prepareDialogMessage() {
        final Map<String, String> params = getRequestParameterMap();
        final String flagString = params.get("flag");
        flagAbilitaCheck = null;
        if (flagString != null) {
            try {
                flagAbilitaCheck = Boolean.parseBoolean(flagString.toLowerCase());
                changeEccezioneDialogMsg = String.format(
                        "Sei sicuro di %s i controlli di sovrapposizione per gli articoli selezionati?",
                        flagAbilitaCheck ? "abilitare" : "disabilitare");
            } catch (Exception ex) {
                log.error(String.format("Error parsing param 'flag' %s as a valid boolean", flagString), ex);
                changeEccezioneDialogMsg = "Errore selezione pianificazioni";
            }
        } else {
            changeEccezioneDialogMsg = "Errore selezione pianificazioni";
        }
        executeScript("PF('changeEccezioneDialog').show();");
    }

    public void dialogCancel() {
        resetSelectedItems();
    }

    public void dialogConfirm() {
        if (flagAbilitaCheck != null && selectedIds != null && !selectedIds.isEmpty()) {
            try {
                final PianificazioneService pianificazioneService = pianificazioneServiceInstance.get();
                final List<PromozionePianificazioneEntity> pianificazioni = pianificazioneService
                        .findAllPianificazioniByIds(selectedIds);
                for (PromozionePianificazioneEntity pianificazione : pianificazioni) {
                    pianificazione.setEscludiCheckSovr(flagAbilitaCheck);
                }
                pianificazioneService.persistInTransaction(pianificazioni);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                        String.format("Check sovrapposizioni %s", flagAbilitaCheck ? "abilitato" : "disabilitato")));
            } catch (Exception ex) {
                String ids = selectedIds.stream().map(String::valueOf).collect(Collectors.joining(","));
                log.error(String.format("Error bulk update pianificazioni %s con flag %s", ids, flagAbilitaCheck), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore %s abilitazione check sovrapposizioni; contattare l'assistenza",
                                flagAbilitaCheck ? "abilitazione" : "disabilitazione")));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile modificare abilitazione check sovrapposizioni; contattare l'assistenza"));
        }
        resetSelectedItems();
    }

    private void updateButtons(boolean rendered) {
        selectedYesBtnRendered = rendered;
        selectedNoBtnRendered = rendered;
    }

    private void resetSelectedItems() {
        if (selectedIds == null) {
            selectedIds = new ArrayList<>();
        } else {
            selectedIds.clear();
        }
        flagAbilitaCheck = null;
    }

    private void readPromozioni() {
        try {
            final Map<String, String> filters = userFilterUtils.createUserFiltersMapToQueryString(
                    userDTO.getUser().getDbFilters());
            promozioni = promoServiceInstance.get().findAllNotCancelled(filters, userDTO.getCanali())
                    .stream().sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error reading promozioni", ex);
            promozioni = new ArrayList<>();
        }
    }

    private void autoselectFirstPromozione() {
        if (promozioni != null && !promozioni.isEmpty()) {
            setIdPromozioneCorrente(promozioni.get(0).getId());
        } else {
            setIdPromozioneCorrente(null);
        }
    }

    @Override
    public void applyRules() {
        // NOOP
    }

    @Override
    public void applyDefaultRules() {
        // NOOP
    }
}
