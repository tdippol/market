package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleDispositivoService;
import com.axiante.mui.dbpromo.persistence.service.MuiBottoneService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgCastellettoMessaggiComponentService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontStileService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.listener.MessaggiUpdatedListener;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Dependent
@Slf4j
public class MessaggiCastellettiBean implements Serializable, FacesContextAware, MessaggiUpdatedListener {
    private static final long serialVersionUID = 1010228348840730563L;

    @Inject
    private Instance<CastellettoMessaggiService> messaggiServiceInstance;

    @Inject
    private Instance<MuiCfgDefaultCastellettoMessaggiService> defaultMessaggiServiceInstance;

    @Inject
    Instance<ApplicationProperties> applicationPropertiesInstance;

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    Instance<MuiCfgCastellettoMessaggiComponentService> defaultComponentServices;

    @Inject
    private Instance<CfgCanaleDispositivoService> canaleDispositivoServices;

    @Inject
    Instance<MuiFontStileService> muiFontStileServices;

    @Inject
    Instance<MuiBottoneService> muiBottoneServices;

    @Inject
    @Getter
    private CastellettiBean castellettiBean;

    @Inject
    @Getter
    private CategoriaBuoniBean categoriaBuoniBean;

    @Inject
    Instance<MuiFontEntitiesService> muiFontEntitiesServicesInstance;

    @Getter
    private String codicePromo;

    @Getter
    private String meccanica;

    @Getter
    private String radice;

    @Getter
    private String progressivo;

    @Getter
    @Setter
    private Long idPianificazione;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private PromozionePianificazioneEntity pianificazione;

    @Getter
    private List<MuiFontStileEntity> fontStileList;

    @Getter
    private List<MuiBottoneEntity> muiBottoneList;

    @Getter
    private int selectedTab = 0;

    @Getter
    private boolean renderTabs = false;

    @Getter
    boolean logoMessaggiRendered;

    @Getter
    boolean castellettiRendered;

    @Inject
    PlanningCommons planningCommons;

    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PROTECTED)
    private List<MessaggiBean> messaggiBeans;

    @Setter(AccessLevel.PROTECTED)
    private List<CfgCanaleDispositivoEntity> dispositivi;

    static final String WALLET_ELEMENT_SEPARATOR = "\\|\\|";
    private PendingReason pendingReason = null;
    private Integer tabIndexToGo;

    public void refreshHeaderMessaggiHeader() {
        try {
            setPianificazione(null);
            final Map<String, String> params = getRequestParameterMap();
            // Load pianificazione from idPianificzione passed as requestParameter
            idPianificazione = Long.parseLong(params.get("idPianificazione"));
            setPianificazione(
                    pianificazioneServiceInstance.get().getPromoPianificazoneById(idPianificazione));
            if (getPianificazione() == null) {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Errore",
                                "Impossibile caricare dialog Messaggi e Castelletti"));
                return;
            }
            // Populate header fields
            codicePromo = getPianificazione().getPromozioneTestataEntity().getCodicePromozione();
            meccanica =
                    getPianificazione().getMeccanicaEntity() != null
                            ? String.format(
                            "%s - %s",
                            getPianificazione().getMeccanicaEntity().getCodiceMeccanica(),
                            getPianificazione().getMeccanicaEntity().getDescrizione())
                            : "";
            radice =
                    getPianificazione().getBuonoScontoRadice() != null
                            ? String.valueOf(getPianificazione().getBuonoScontoRadice())
                            : "";
            progressivo =
                    getPianificazione().getBuonoScontoProgressivo() != null
                            ? String.valueOf(getPianificazione().getBuonoScontoProgressivo())
                            : "";
            setIdPianificazione(getPianificazione().getId());
            castellettiBean.setPianificazione(getPianificazione());
            categoriaBuoniBean.setPromozione(getPianificazione().getPromozioneTestataEntity());

            prepareTabs(
                    planningCommons.isPlanningEditableCellAndRow(
                            getPianificazione().getPromozioneTestataEntity()));
            // Open dialog
            executeScript("PF('logoMessaggioDialog').show();");
        } catch (Exception ex) {
            log.error("Error getting 'idPianificazione' from requestParameterMap", ex);
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Errore",
                            "Impossibile caricare dialog Messaggi e Castelletti"));
        }
    }

    // MG-2026-01-13: prevent dialog close with pending changes
    public void onDlgLogoMessaggioClosed() {
        if (getMessaggiBean() == null) {
            executeScript("PF('logoMessaggioDialog').hide();");
        } else if (getMessaggiBean().isPendingChanges()) {
            pendingReason = PendingReason.CLOSE_BUTTON;
            executeScript("PF('dlgPendingChanges').show();");
        } else {
            executeScript("PF('logoMessaggioDialog').hide();");
        }
    }

    public void handleTabChanged() {
        boolean pendingChanges = getMessaggiBean().isPendingChanges();
        if (!pendingChanges) {
            getMessaggiBean().setMessaggioSelected(null);
            getMessaggiBean().clearForm();
        }
        getAjax().addCallbackParam("canChangeTab", !pendingChanges);
    }

    public void prepareDispositiviTabChange() {
        pendingReason = PendingReason.DISPOSITIVI_TAB;
        tabIndexToGo = retrieveTabIndexToGo();
    }

    public void prepareOuterTabChange() {
        pendingReason = PendingReason.OUTER_TAB;
        tabIndexToGo = retrieveTabIndexToGo();
    }

    public void prepareDispositiviRowIndex() {
        pendingReason = PendingReason.ROW_SELECTION;
    }

    private Integer retrieveTabIndexToGo() {
        Map<String, String> params = getRequestParameterMap();
        if (params.containsKey("index")) {
            return Integer.parseInt(params.get("index"));
        }
        log.warn("Cannot retrieve tabIndexToGo from request");
        return 0;
    }

    public void discardChanges() {
        getMessaggiBean().clearForm();
        navigateTo();
    }

    public void saveChanges() {
        getMessaggiBean().updateMessaggio();
        navigateTo();
    }

    private void navigateTo() {
        if (PendingReason.CLOSE_BUTTON.equals(pendingReason)) {
            executeScript("PF('dlgPendingChanges').hide();PF('logoMessaggioDialog').hide();");
        } else if (PendingReason.OUTER_TAB.equals(pendingReason)) {
            executeScript(String.format("PF('dlgPendingChanges').hide();PF('outerTab').select(%d);", tabIndexToGo));
        } else if (PendingReason.DISPOSITIVI_TAB.equals(pendingReason)) {
            executeScript(String.format("PF('dlgPendingChanges').hide();PF('dispositiviTab').select(%d);", tabIndexToGo));
        } else if (PendingReason.ROW_SELECTION.equals(pendingReason)) {
            executeScript("PF('dlgPendingChanges').hide();changeRow()");
        } else {
            executeScript("PF('dlgPendingChanges').hide();");
        }
    }

    // legge la lista dei dispositivi configurati
    // la incrocia con quelli selezionati in pianificazione
    // il risultato e' la lista dei dispositivi possibili per cui inserire messaggi
    private void prepareTabs(boolean editable) {
        this.selectedTab = 0;
        checkEnabledFunctions();
        if (logoMessaggiRendered) {
            // 1. leggo la lista di mui_cfg_canale_dispositivo
            // 2. la incrocio con il campo WALLET della riga di pianificazione
            // 3. ognuno di questi e' un tab
            String wallet = getPianificazione().getCanaleDispositivo();
            if (wallet != null && !wallet.trim().isEmpty()) {
                List<String> dis = Arrays.asList(wallet.split(WALLET_ELEMENT_SEPARATOR));
                dispositivi = new ArrayList<>();
                canaleDispositivoServices.get().findAll().stream()
                        .filter(s -> dis.contains(s.getCodice()))
                        .sorted(Comparator.comparing(CfgCanaleDispositivoEntity::getDescrizione))
                        .forEach(s -> dispositivi.add(s));
                if (dispositivi.isEmpty()) {
                    log.warn(
                            "Nessun dispositivo configurabile. il campo WALLET contiene i seguenti valori : {}",
                            wallet);
                }

                prepareBeans(editable);
            } else {
                dispositivi = Collections.emptyList();
                log.warn("Il campo WALLET e' vuoto, nessun dispositivo configurabile");
            }
            renderTabs = !dispositivi.isEmpty();
            prepareFontStileList();
            prepareMuiBottoneList();
            fetchMessaggi();
        }
    }

    private void checkEnabledFunctions() {
        castellettiRendered = false;
        logoMessaggiRendered = false;
        if (getPianificazione() == null) {
            log.warn("pianificazione is null: disabling rendered tabs");
            return;
        }
        CfgConfHeaderEntity configurazione =
                getPianificazione()
                        .getPromozioneTestataEntity()
                        .getMuiCfgSetPianificazione()
                        .getMuiCfgConfHeaders()
                        .stream()
                        .filter(
                                c ->
                                        c.getMeccanicaEntity()
                                                .getId()
                                                .equals(getPianificazione().getMeccanicaEntity().getId()))
                        .findAny()
                        .orElse(null);
        if (configurazione != null) {
            castellettiRendered = configurazione.getCastelletti();
            logoMessaggiRendered = configurazione.getLogoMessaggi();
        } else {
            log.warn("missing configuration for meccanica {}", meccanica);
        }
    }

    private void prepareFontStileList() {
        fontStileList = muiFontStileServices.get().findAll();
    }

    private void prepareMuiBottoneList() {
        muiBottoneList = muiBottoneServices.get().findAll();
    }

    private void prepareBeans(boolean editable) {
        if (dispositivi != null && !dispositivi.isEmpty()) {
            messaggiBeans = new ArrayList<>();
            dispositivi.forEach(
                    d -> {
                        MessaggiBean m =
                                new MessaggiBean(
                                        getPianificazione(),
                                        editable,
                                        d,
                                        messaggiServiceInstance.get(),
                                        defaultMessaggiServiceInstance.get(),
                                        applicationPropertiesInstance.get(),
                                        defaultComponentServices.get(),
                                        muiFontEntitiesServicesInstance.get());
                        m.setCodicePromo(codicePromo);
                        m.clearForm();
                        messaggiBeans.add(m);
                    });
        } else {
            log.warn("Nessun messaggio inseribile per la pianificazione {}", getPianificazione().getId());
            messaggiBeans = Collections.emptyList();
        }
        messaggiBeans.forEach(m -> m.addMessaggiUpdateListener(this));
    }

    public MessaggiBean getMessaggiBean() {
        if (messaggiBeans != null && !messaggiBeans.isEmpty()) {
            return messaggiBeans.get(selectedTab);
        }
        log.error("no message bean present");
        return null;
    }

    public String getDispositiviList() {
        if (dispositivi != null) {
            return dispositivi.stream()
                    .map(CfgCanaleDispositivoEntity::getCodice)
                    .collect(Collectors.joining(","));
        } else {
            log.warn("Nessun dispositivo valido trovato per la pianificazione {}", getPianificazione().getId());
        }
        return "";
    }

    public void updateActiveIndex() {
        try {
            final String index = getRequestParameterMap().get("index");
            this.selectedTab = JsonUtils.getMapper().readTree(index).asInt();
        } catch (Exception ex) {
            log.error("Error reading selected row", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore selezione messaggio; contattare l'assistenza"));
        }
    }

    public void fetchMessaggi() {
        // prendi il tab selezionato
        // e ritorna tutti i messaggi presenti per quel device
        log.debug("fetch messaggi for tab {}", selectedTab);
    }

    @Override
    public void onMessaggiUpdated() {
        // CastellettoMessaggiEntity.findByIdPianificazione
    }

    protected void prepareMessaggi(Collection<CastellettoMessaggiEntity> collection) {
        if (collection != null && !collection.isEmpty()) {
            log.debug("ci sono messaggi");
        } else {
            log.debug("no messaggi");
        }
    }


    public List<String> getDispositivi() {
        if (dispositivi != null) {
            return dispositivi.stream()
                    .map(CfgCanaleDispositivoEntity::getDescrizione)
                    .sorted()
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
        fetchMessaggi();
    }

    enum PendingReason {
        CLOSE_BUTTON, OUTER_TAB, DISPOSITIVI_TAB, ROW_SELECTION;
    }
}
