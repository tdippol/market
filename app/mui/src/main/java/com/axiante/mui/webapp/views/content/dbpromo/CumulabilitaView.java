package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.business.enumeration.OperazioneCumulabilita;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.dbpromo.persistence.service.CumulabilitaBuoniService;
import com.axiante.mui.dbpromo.persistence.service.CumulabilitaEsclusivitaService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.ReportBSService;
import com.axiante.mui.webapp.utils.security.CumulabilitaSecurity;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.cumulabilita.AddCumulabilitaBean;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@MuiViewModel("cumulabilita")
@Dependent
@Slf4j
public class CumulabilitaView extends BasePromoView {
    private static final long serialVersionUID = -3160187173852253779L;
    private static final String[] PREFISSI_BUONI_AUTOMATICI = {"971", "972", "973", "974"};

    @Inject
    private Instance<CumulabilitaEsclusivitaService> serviceInstance;

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    private Instance<CumulabilitaSecurity> cumulabilitaSecurityInstance;

    @Inject
    private Instance<CumulabilitaBuoniService> cumulabilitaBuoniServiceInstance;

    @Inject
    transient Instance<ReportBSService> reportBSService;

    @Getter
    private boolean btnAddBuonoDisabled = true;

    @Getter
    private Long idCumulabilitaSelected;

    @Getter
    private CumulabilitaEsclusivitaEntity cumulabilitaSelected;

    @Getter
    @Setter
    private String removeIdCumulabilita;

    @Getter
    @Setter
    private String publishIdCumulabilita;

    @Getter
    @Setter
    private String removeIdBuono;

    @Getter
    boolean cumulabilitaConfirmBtnDisabled = true;

    @Getter
    boolean buonoConfirmBtnDisabled = true;

    @Getter
    private String confirmCancelMessage;

    @Getter
    private String confirmPublishMessage;


    @Getter
    CumulabilitaType currentTab = CumulabilitaType.CUMULABILE;

    @Getter
    final CumulabilitaType[] tabs = {CumulabilitaType.CUMULABILE, CumulabilitaType.ESCUSIVA};

    final AddCumulabilitaBean dlgCumulabilitaBean = new AddCumulabilitaBean();
    final AddCumulabilitaBean dlgEsclusivitaBean = new AddCumulabilitaBean();
    private List<String> pianificazioniIdSelected = new ArrayList<>();

    @PostConstruct
    public void init() {
        initDialog();
        confirmCancelMessage = null;
        confirmPublishMessage = null;
    }

    public void initDialog() {
        getDlgBean().reset();
    }

    public void initBuonoDialog() {
        pianificazioniIdSelected.clear();
        this.buonoConfirmBtnDisabled = true;
    }

    public void selezionaPianificazioni() {
        pianificazioniIdSelected.clear();
        final String itemsIdsParam = getRequestParameterMap().get("itemSelected");
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemsIdsParam);
            if (jsonNode != null) {
                jsonNode.forEach(n -> pianificazioniIdSelected.add(n.asText()));
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile recuperare lista pianificazioni selezionate"));
            }
        } catch (IOException ex) {
            log.error("Error reading selected items", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile recuperare lista pianificazioni selezionate"));
        }
        this.buonoConfirmBtnDisabled = !isValidBuono();
    }

    public void updateIdCumulabilita() {
        final String idCumulabilita = getRequestParameterMap().get("idCumulabilita");
        if (idCumulabilita == null || idCumulabilita.trim().isEmpty()) {
            idCumulabilitaSelected = null;
        } else {
            try {
                setIdCumulabilitaSelected(Long.parseLong(idCumulabilita));
            } catch (Exception ex) {
                log.error("Unable to get rowSelected from cumulabilita grid", ex);
            }
        }
    }

    public void updateGridButtons() {
        if (idCumulabilitaSelected == null) {
            btnAddBuonoDisabled = true;
        } else {
            // Get params from remoteCommand
            final Map<String, String> params = getRequestParameterMap();
            boolean rowSelected = false;
            try {
                rowSelected = Boolean.parseBoolean(params.get("rowSelected"));
            } catch (Exception ex) {
                log.error("Unable to get rowSelected from cumulabilita grid", ex);
            }
            final CumulabilitaEsclusivitaEntity cumulabilita = getCumulabilitaFromId(String.valueOf(idCumulabilitaSelected));
            btnAddBuonoDisabled = !rowSelected || cumulabilita == null
                    || !cumulabilitaSecurityInstance.get().canAddBuono(cumulabilita);
        }
    }

    public void cancelCumulabilita() {
        final String idCumulabilita = getRequestParameterMap().get("idCumulabilita");
        String title = prepareTitle();
        try {
            final CumulabilitaEsclusivitaEntity cumulabilita = serviceInstance.get().findById(Long.parseLong(idCumulabilita));
            if (cumulabilita == null || !cumulabilitaSecurityInstance.get().canCancelCumulabilita(cumulabilita)) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore nell'annullamento della %s %s; contattare l'assistenza", title, idCumulabilita)));
            } else {
                confirmCancelMessage = String.format("Confermi l'annullamento della %s '%s'?",
                        title, cumulabilita.getDescrizione());
                setRemoveIdCumulabilita(idCumulabilita);
                executeScript("PF('cancelCumulabilita_dialog').show();");
            }
        } catch (Exception ex) {
            log.error(String.format("Error preparing dialog for cancel %s with id %s", title, idCumulabilita), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Errore nell'annullamento della %s %s; contattare l'assistenza", title, idCumulabilita)));
        }
    }

    public void publishCumulabilita() {
        final String idCumulabilita = getRequestParameterMap().get("idCumulabilita");
        String title = prepareTitle();
        try {
            final CumulabilitaEsclusivitaEntity cumulabilita = serviceInstance.get().findById(Long.parseLong(idCumulabilita));
            if (cumulabilita == null || !cumulabilitaSecurityInstance.get().canPublishCumulabilita(cumulabilita)) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore nella pubblicazione della %s %s; contattare l'assistenza",
                                title, idCumulabilita)));
            } else {
                confirmPublishMessage = String.format("Confermi la pubblicazione della %s '%s'?",
                        title, cumulabilita.getDescrizione());
                setPublishIdCumulabilita(idCumulabilita);
                executeScript("PF('publishCumulabilita_dialog').show();");
            }
        } catch (Exception ex) {
            log.error(String.format("Error preparing dialog for publish %s with id %s", title, idCumulabilita), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Errore nella pubblicazione della %s %s; contattare l'assistenza", title, idCumulabilita)));
        }
    }

    public void removeBuono() {
        final String idCumulabilita = getRequestParameterMap().get("idCumulabilita");
        final String idBuono = getRequestParameterMap().get("idBuono");
        String title = prepareTitle();
        try {
            final CumulabilitaEsclusivitaEntity cumulabilita = serviceInstance.get().findById(Long.parseLong(idCumulabilita));
            if (cumulabilita == null || !cumulabilitaSecurityInstance.get().canDeleteBuono(cumulabilita)) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Errore nell'eliminazione del buono %s dalla %s %s; contattare l'assistenza",
                                idBuono, title, idCumulabilita)));
            } else {
                confirmCancelMessage = String.format("Confermi la cancellazione del buono %s associato alla %s %s?",
                        idBuono, title, cumulabilita.getDescrizione());
                setRemoveIdCumulabilita(idCumulabilita);
                setRemoveIdBuono(idBuono);
                executeScript("PF('removeBuono_dialog').show();");
            }
        } catch (Exception ex) {
            log.error(String.format("Error preparing dialog for remove Buono with id %s from %s with id %s",
                    idBuono, title, idCumulabilita), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Errore nell'eliminazione del buono %s dalla %s %s; contattare l'assistenza",
                            idBuono, title, idCumulabilita)));
        }
    }

    private String prepareTitle() {
        String title = getCurrentTab().getTitle();
        if (title.trim().isEmpty()) {
            title = CumulabilitaType.CUMULABILE.getTitle();
        }
        return title;
    }

    public void resetCumulabilitaId() {
        setIdCumulabilitaSelected(null);
        this.btnAddBuonoDisabled = true;
    }

    public void confirmCumulabilitaDialog() {
        String title = prepareTitle();
        if (getDlgBean().isValidCumulabilita()) {
            try {
                createCumulabilita();
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                        String.format("%s '%s' creata correttamente", title,
                                getDlgBean().getDescrizione().trim().toUpperCase())));
            } catch (Exception ex) {
                log.error("Error saving CumulabilitaEntity", ex);
                String message = ex.getMessage().toLowerCase().contains("mui_cumul_escl_uk")
                        ? String.format("Errore nella creazione della %s; esiste già una Cumulabilità/Esclusivita con questo buono o date", title)
                        : String.format("Errore nella creazione della %s; contattare l'assistenza", title);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Errore nella creazione della %s; controllare i parametri impostati", title)));
        }
        getDlgBean().reset();
    }

    public void confirmBuonoDialog() {
        if (cumulabilitaSelected != null && isValidBuono()
                && cumulabilitaSecurityInstance.get().canAddBuono(cumulabilitaSelected)) {
            final Long idCumulabilita = cumulabilitaSelected.getId();
            String title = prepareTitle();
            try {
                createBuono(cumulabilitaSelected, pianificazioniIdSelected);
                serviceInstance.get().updateCumulabilita(idCumulabilita, OperazioneCumulabilita.INS_DET, getCurrentUser().getName());
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                        String.format("Buoni aggiunti correttamente alla %s %s", title, cumulabilitaSelected.getDescrizione())));
                setIdCumulabilitaSelected(cumulabilitaSelected.getId());
                executeScript("refreshRowData_buoni('" + idCumulabilita + "')");
            } catch (Exception ex) {
                log.error(String.format("Error adding Buono to %s with id %d", title, idCumulabilita), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore nella creazione del buono; contattare l'assistenza"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore nella creazione del buono; controllare i parametri impostati"));
        }
    }

    public void setIdCumulabilitaSelected(Long idCumulabilitaSelected) {
        this.idCumulabilitaSelected = idCumulabilitaSelected;
        if (idCumulabilitaSelected != null) {
            cumulabilitaSelected = getCumulabilitaFromId(String.valueOf(idCumulabilitaSelected));
        }
    }

    private void createCumulabilita() {
        if (getDlgBean().getSelectedBuono() != null) {
            CumulabilitaEsclusivitaEntity entity = getDlgBean().toCumulabilitaBean();
            entity.setTipo(currentTab);
            if ( currentTab == CumulabilitaType.CUMULABILE ) {
                addBuonoDefault(entity);
                addBuoniAutomatici(entity);
            }
            entity = (CumulabilitaEsclusivitaEntity) serviceInstance.get()
                    .persistWithAuditLog(entity, getCurrentUser().getName());
            serviceInstance.get().updateCumulabilita(entity.getId(), OperazioneCumulabilita.INS_TES, getCurrentUser().getName());
        } else {
            String message = "Buono di default mancante!\nContattare l'assistenza";
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, message));
            log.error("Missing buono default. Should be the one with codice promozione {}, prefisso bs {}",
                    getDlgBean().getCodicePromo(), getDlgBean().getPrefissoBs());
        }
    }

    private void addBuonoDefault(CumulabilitaEsclusivitaEntity entity) {
        CumulabilitaBuoniEntity buono = new CumulabilitaBuoniEntity();
        buono.setBuonoDefault(true);
        buono.setPrefissoBS(getDlgBean().getSelectedBuono().getId().getPrefissoBS());
        buono.setCodiceCanale(getDlgBean().getSelectedBuono().getCodiceCanale().toString());
        buono.setCodicePromozione(getDlgBean().getSelectedBuono().getId().getCodicePromozione());
        buono.setDescrizionePromozione(getDlgBean().getSelectedBuono().getDescrizionePromozione());
        buono.setDataInizio(getDlgBean().getSelectedBuono().getDataInizio());
        buono.setDataFine(getDlgBean().getSelectedBuono().getDataFine());
        buono.setCodiceUtenteInserimento(getCurrentUser().getName());
        buono.setDataInserimento(new Date());
        entity.addCumulabilitaBuoniEntity(buono);
    }

    private void addBuoniAutomatici( CumulabilitaEsclusivitaEntity entity ) {
        for (String prefisso : PREFISSI_BUONI_AUTOMATICI) {
            CumulabilitaBuoniEntity buono = new CumulabilitaBuoniEntity();
            buono.setBuonoDefault(false);
            buono.setPrefissoBS(prefisso);
            buono.setCodiceCanale(getDlgBean().getSelectedBuono().getCodiceCanale().toString());
            buono.setCodicePromozione(getDlgBean().getSelectedBuono().getId().getCodicePromozione());
            buono.setDescrizionePromozione(getDlgBean().getSelectedBuono().getDescrizionePromozione());
            buono.setDataInizio(getDlgBean().getSelectedBuono().getDataInizio());
            buono.setDataFine(getDlgBean().getSelectedBuono().getDataFine());
            buono.setCodiceUtenteInserimento(getCurrentUser().getName());
            buono.setDataInserimento(new Date());
            entity.addCumulabilitaBuoniEntity(buono);
        }
    }

    private void createBuono(CumulabilitaEsclusivitaEntity cumulabilita, List<String> idPianificazioni) {
        reportBSService.get().findWithChiaveIn(idPianificazioni).forEach(pianificazione -> {
            CumulabilitaBuoniEntity buono = new CumulabilitaBuoniEntity();
            buono.setBuonoDefault(false);
            buono.setPrefissoBS(pianificazione.getId().getPrefissoBS());
            buono.setCodiceCanale(pianificazione.getCodiceCanale().toString());
            buono.setCodicePromozione(pianificazione.getId().getCodicePromozione());
            buono.setDescrizionePromozione(pianificazione.getDescrizionePromozione());
            buono.setDataInizio(pianificazione.getDataInizio());
            buono.setDataFine(pianificazione.getDataFine());
            buono.setCodiceUtenteInserimento(getCurrentUser().getName());
            buono.setDataInserimento(new Date());
            cumulabilita.addCumulabilitaBuoniEntity(buono);
        });
        serviceInstance.get().persistWithAuditLog(cumulabilita, getCurrentUser().getName());
    }

    private CumulabilitaEsclusivitaEntity getCumulabilitaFromId(String id) {
        try {
            return serviceInstance.get().findById(Long.parseLong(id));
        } catch (Exception ex) {
            log.error("Error getting cumulabilita from id {}", id, ex);
            return null;
        }
    }

    private boolean isValidBuono() {
        return !pianificazioniIdSelected.isEmpty();
    }

    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }

    public void updateDialogOnTableSelection() {
        final String prefissoBS = getRequestParameterMap().get("prefissoBS");
        final String codicePromo = getRequestParameterMap().get("codicePromo");
        MuiReportBSEntity e = reportBSService.get().findById(codicePromo, prefissoBS);
        if (e != null) {
            if (log.isDebugEnabled())
                log.debug("CumulabilitaBuoniEntity found: {}", e);
            getDlgBean().setData(e);
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore nella creazione del buono; contattare l'assistenza"));
            log.error("Prefisso BS not found in database");
        }
    }

    public int getActiveIndex() {
        switch (currentTab) {
            case CUMULABILE:
                return 0;
            case ESCUSIVA:
                return 1;
        }
        log.error("Invalid tab registered: {}", currentTab);
        return -1;
    }

    public void setActiveIndex(int activeIndex) {
        if (activeIndex == 0) {
            currentTab = CumulabilitaType.CUMULABILE;
        } else if (activeIndex == 1) {
            currentTab = CumulabilitaType.ESCUSIVA;
        } else {
            log.error("Invalid tab registered: {}", activeIndex);
        }
        confirmCancelMessage = null;
        confirmPublishMessage = null;
    }

    public AddCumulabilitaBean getDlgBean() {
        switch (currentTab) {
            case ESCUSIVA:
                return dlgEsclusivitaBean;
            case CUMULABILE:
            default:
                return dlgCumulabilitaBean;
        }
    }

    public void tabChanged(TabChangeEvent event) {
        //This code was taken directly from TabViewRenderer.
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        TabView tabView = (TabView) event.getComponent();
        String activeIndexValue = params.get(tabView.getClientId(context) + "_tabindex");
        setActiveIndex(Integer.parseInt(activeIndexValue));
    }
}
