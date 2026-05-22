package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.business.enumeration.IniziativaStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.IniziativaAcl;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.IniziativeService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.EditIniziativaBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.IniziativeMessageEnum;
import com.axiante.mui.webapp.webservice.dto.RAFRequest;
import com.axiante.mui.webapp.webservice.dto.RAFResponse;
import com.axiante.mui.webapp.webservice.util.TotalizzatoreHelper;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@MuiViewModel("schedaIniziativa")
@Dependent
@Slf4j
public class SchedaIniziativaView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 704869468486557634L;

    private static final int TAB_STATI = 0;
    private static final int TAB_MODIFICA = 1;

    @Inject
    private Instance<IniziativeService> iniziativeServiceInstance;

    @Inject
    private Instance<StatoPromoService> statoPromoServiceInstance;

    @Getter
    @Setter
    private Integer activeTab = 0;

    @Getter
    private List<IniziativaEntity> iniziative;

    @Getter
    private Long idIniziativaSelected;

    @Getter
    private IniziativaEntity iniziativaSelected;

    @Getter
    private String statoIniziativaSelected;

    @Getter
    private boolean btnPubblicaIniziativaDisabled = true;

    @Getter
    private boolean btnAggiornaTotalizzatoreRendered = false;

    @Getter
    private boolean iniziativaSelectedLocked = false;

    @Getter
    private String msgConfirmPubblicaIniziativa;

    @Getter
    private String msgConfirmAggiornaTotalizzatore;

    @Getter
    private boolean btnGestioneStatoDisabled = true;

    @Getter
    @Setter
    private Long idStatoTransizione;

    @Getter
    private List<StatoPromozioneEntity> statiTransizione;

    @Getter
    private EditIniziativaBackingBean editIniziativaBean;

    @Inject
    Instance<TotalizzatoreHelper> totalizzatoreHelperInstance;

    @Inject
    Instance<AuditLogService> auditLogServiceInstance;

    public void init() {
        editIniziativaBean = new EditIniziativaBackingBean(iniziativeServiceInstance.get(), statoPromoServiceInstance.get(),
                totalizzatoreHelperInstance.get(), auditLogServiceInstance.get());
        readIniziative();
        autoselectFirstIniziativa();
        handleBtnGestioneStato();
    }

    public void setIdIniziativaSelected(Long idIniziativaSelected) {
        this.idIniziativaSelected = idIniziativaSelected;
        if (idIniziativaSelected != null) {
            this.iniziativaSelected = getIniziativaFromId(idIniziativaSelected);
            this.statoIniziativaSelected = getIniziativaStatoCorrente();
        } else {
            this.iniziativaSelected = null;
            this.statoIniziativaSelected = null;
        }
        updateBtnPubblicaIniziativa();
        updateBtnAggiornaTotalizzatore();
        editIniziativaBean.loadIniziativa(iniziativaSelected);
        handleBtnGestioneStato();
    }

    public void prepareMsgConfirmPubblicaIniziativa() {
        if (iniziativaSelected != null) {
            msgConfirmPubblicaIniziativa = String.format("Sei sicuro di voler pubblicare l'iniziativa '%d - %s' ?",
                    iniziativaSelected.getId(), iniziativaSelected.getDescrizione());
        }
    }

    public void prepareMsgConfirmAggiornaTotalizzatore() {
        if (iniziativaSelected != null) {
            msgConfirmAggiornaTotalizzatore = String.format("Sei sicuro di voler aggiornare il totalizzatore per l'iniziativa '%d - %s' ?",
                    iniziativaSelected.getId(), iniziativaSelected.getDescrizione());
        }
    }

    public void prepareDlgGestioneStatoIniziativa() {
        loadStatiTransizione();
    }

    public void confirmPubblicaIniziativa() {
        if (iniziativaSelected == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Nessuna iniziativa selezionata; impossibile pubblicare"));
            return;
        }
        try {
            savePublishedIniziativa(iniziativaSelected);
            readIniziative();
            iniziativaSelected = iniziativeServiceInstance.get().findById(iniziativaSelected.getId());
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    String.format("Iniziativa '%d - %s' pubblicata correttamente",
                            iniziativaSelected.getId(), iniziativaSelected.getDescrizione())));
            // reload iniziativa selected
            setIdIniziativaSelected(iniziativaSelected.getId());
        } catch (Exception ex) {
            log.error(String.format("Error publishing iniziativa with id %d", iniziativaSelected.getId()), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Errore pubblicazione iniziativa '%d - %s'",
                            iniziativaSelected.getId(), iniziativaSelected.getDescrizione())));
        }
    }

    public boolean isConfirmBtnStatiDisabled() {
        return this.idStatoTransizione == null;
    }

    public void confirmAggiornaTotalizzatore() {
        FacesMessage facesMessage = null;
        if (iniziativaSelected != null && canUpdateTotalizzatore(iniziativaSelected)) {
            try {
                final TotalizzatoreHelper helper = totalizzatoreHelperInstance.get();
                RAFRequest rafRequest = helper.generate(iniziativaSelected);
                auditLogServiceInstance.get()
                        .logAction(AuditLogEntity.EXTERNAL_RESOURCE, helper.getAuditPath(rafRequest), getCurrentUser().getName());
                RAFResponse response = helper.parse(helper.queryRaf(rafRequest), true);
                response.setMethod(rafRequest.getMethod());
                if (IniziativeMessageEnum.asMessageEnum(response) == null) {
                    // ok
                    iniziativaSelected = iniziativeServiceInstance.get().persist(helper.update(iniziativaSelected, response));
                    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            "Totalizzatori aggiornati con successo");
                } else {
                    // errore WS raf
                    final String msg = IniziativeMessageEnum.asMessageEnum(response).getDescrizione();
                    log.error(String.format("%s: [%d] %s", msg, response.getStato(), response.getStatusLine()));
                    facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg);
                }
            } catch (Exception ex) {
                log.error(String.format("Error updating Totalizzatore for iniziativa %d", iniziativaSelected.getId()), ex);
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile aggiornare totalizzatore per iniziativa %d - %s",
                                iniziativaSelected.getId(), iniziativaSelected.getDescrizione()));
            }
            readIniziative();
            iniziativaSelected = getIniziativaFromId(iniziativaSelected.getId());
            editIniziativaBean.loadIniziativa(iniziativaSelected);
            // update buttons
            updateBtnPubblicaIniziativa();
            updateBtnAggiornaTotalizzatore();
        } else {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile aggiornare totalizzatori");
        }
        addMessage(null, facesMessage);
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTab = tv.getChildren().indexOf(event.getTab());
        if (iniziativaSelected != null && activeTab == TAB_MODIFICA) {
            editIniziativaBean.loadIniziativa(iniziativaSelected);
        }
    }

    public void unlockEditIniziativa() {
        if (iniziativaSelected == null || !editIniziativaBean.canBeUnlocked(iniziativaSelected)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile sbloccare iniziativa selezionata"));
            return;
        }
        editIniziativaBean.unlockEditIniziativa();
        iniziativaSelectedLocked = true;
    }

    public void discardEditIniziativa() {
        editIniziativaBean.discardEditIniziativa();
        iniziativaSelectedLocked = false;
    }

    public void saveEditIniziativa() {
        if (editIniziativaBean.saveEditIniziativa()) {
            iniziativaSelectedLocked = false;
        }
        readIniziative();
        iniziativaSelected = getIniziativaFromId(iniziativaSelected.getId());
    }

    public void deleteIniziativa() {
        if (editIniziativaBean.deleteIniziativa()) {
            int i = iniziative.indexOf(iniziativaSelected);
            if (i == -1) {
                i = 0;
            }
            readIniziative();
            if (iniziative.isEmpty()) {
                setIdIniziativaSelected(null);
            } else {
                if (i == iniziative.size()) {
                    i--;
                }
                setIdIniziativaSelected(iniziative.get(i).getId());
            }
            iniziativaSelectedLocked = false;
        }
    }

    private void savePublishedIniziativa(@NonNull IniziativaEntity iniziativa) throws Exception {
        IniziativaStatoEntity lastStatus = IniziativaAcl.getLastStatus(iniziativa);
        if (lastStatus == null) {
            throw new Exception(String.format("Iniziativa with id %d does not have a valid last status",
                    iniziativa.getId()));
        }
        final StatoPromozioneEntity stato = statoPromoServiceInstance.get()
                .findByCode(IniziativaStatusEnum.PUBBLICATA.getCodice());
        if (stato == null) {
            throw new Exception(String.format("Cannot find stato with code %s",
                    IniziativaStatusEnum.PUBBLICATA.getCodice()));
        }
        insertNewStatus(iniziativa, lastStatus, stato);
    }

    private void insertNewStatus(IniziativaEntity iniziativa, IniziativaStatoEntity lastStatus, StatoPromozioneEntity stato) {
        final String usermame = getUserDto().getUsermame();
        final Date now = new Date();
        lastStatus.setDataFineStato(now);
        lastStatus.setDataAggiornamento(now);
        lastStatus.setCodiceUtenteAggiornamento(usermame);
        IniziativaStatoEntity iniziativaStato = (IniziativaStatoEntity)
                AuditLogFiller.fillAuditLogFields(new IniziativaStatoEntity(), usermame);
        iniziativaStato.setStatoPromo(stato);
        iniziativaStato.setDataInizioStato(now);
        iniziativa.addStato(iniziativaStato);
        iniziativeServiceInstance.get().persistWithAuditLog(iniziativa, usermame);
    }

    private void readIniziative() {
        iniziative = iniziativeServiceInstance.get().findAllNotCancelled();
    }

    private IniziativaEntity getIniziativaFromId(@NonNull Long id) {
        return iniziative.stream().filter(i -> id.equals(i.getId())).findFirst().orElse(null);
    }

    private void autoselectFirstIniziativa() {
        if (iniziative != null && !iniziative.isEmpty()) {
            setIdIniziativaSelected(iniziative.get(0).getId());
        }
    }

    private String getIniziativaStatoCorrente() {
        final StatoPromozioneEntity statoCorrente = IniziativaAcl.getStatoCorrente(iniziativaSelected);
        return statoCorrente == null ? "" : statoCorrente.fullDescription();
    }

    private void updateBtnPubblicaIniziativa() {
        try {
            btnPubblicaIniziativaDisabled = iniziativaSelected == null
                    || IniziativaAcl.isPublished(iniziativaSelected)
                    || !IniziativaAcl.canBePublished(iniziativaSelected);
        } catch (Exception ex) {
            log.error("Error updating button 'Pubblica Iniziativa'", ex);
            btnPubblicaIniziativaDisabled = true;
        }
    }

    public void updateBtnAggiornaTotalizzatore() {
        try {
            btnAggiornaTotalizzatoreRendered = iniziativaSelected != null && canUpdateTotalizzatore(iniziativaSelected);
        } catch (Exception ex) {
            log.error("Error rendering button 'Aggiorna Totalizzatore'", ex);
            btnAggiornaTotalizzatoreRendered = false;
        }
    }

    public void confirmNuovoStato() {
        if (iniziativaSelected == null || idStatoTransizione == null) {
            StringBuilder sb = new StringBuilder();
            if (iniziativaSelected == null) {
                sb.append("Nessuna iniziativa selezionata");
            }
            if (idStatoTransizione == null) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append("Stato transizione non impostato");
            }
            sb.append("; impossibile pubblicare");
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", sb.toString()));
            return;
        }
        try {
            saveCambioStatoIniziativa(iniziativaSelected, idStatoTransizione);
            readIniziative();
            iniziativaSelected = iniziativeServiceInstance.get().findById(iniziativaSelected.getId());
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    String.format("Iniziativa '%d - %s' pubblicata correttamente",
                            iniziativaSelected.getId(), iniziativaSelected.getDescrizione())));
            // reload iniziativa selected
            setIdIniziativaSelected(iniziativaSelected.getId());
        } catch (Exception ex) {
            log.error(String.format("Error publishing iniziativa with id %d", iniziativaSelected.getId()), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Errore pubblicazione iniziativa '%d - %s'",
                            iniziativaSelected.getId(), iniziativaSelected.getDescrizione())));
        }
    }

    private void saveCambioStatoIniziativa(@NonNull IniziativaEntity iniziativa, @NonNull Long idStatoTransizione)
            throws Exception {
        IniziativaStatoEntity lastStatus = IniziativaAcl.getLastStatus(iniziativa);
        if (lastStatus == null) {
            throw new Exception(String.format("Iniziativa with id %d does not have a valid last status",
                    iniziativa.getId()));
        }
        final StatoPromozioneEntity stato = statoPromoServiceInstance.get().findById(idStatoTransizione);
        if (stato == null) {
            throw new Exception(String.format("Cannot find stato with id %d", idStatoTransizione));
        }
        insertNewStatus(iniziativa, lastStatus, stato);
    }

    private boolean canUpdateTotalizzatore(@NonNull IniziativaEntity iniziativa) {
        return !IniziativaAcl.isPublished(iniziativa) && Boolean.FALSE.equals(iniziativa.getTotalizzatoreAllineato());
    }

    private void handleBtnGestioneStato() {
        btnGestioneStatoDisabled = iniziativaSelected == null || !canChangeStato(iniziativaSelected);
    }

    private boolean canChangeStato(IniziativaEntity iniziativa) {
        StatoPromozioneEntity statoCorrente = IniziativaAcl.getStatoCorrente(iniziativa);
        return statoCorrente != null &&
                (IniziativaStatusEnum.PUBBLICATA.getCodice().equals(statoCorrente.getCodiceStato())
                        || IniziativaStatusEnum.SBLOCCATA.getCodice().equals(statoCorrente.getCodiceStato()));
    }

    private void loadStatiTransizione() {
        if (statiTransizione == null) {
            statiTransizione = Collections.emptyList();
        } else {
            statiTransizione.clear();
        }
        if (iniziativaSelected != null) {
            final StatoPromozioneEntity statoCorrente = IniziativaAcl.getStatoCorrente(iniziativaSelected);
            if (statoCorrente != null) {
                statiTransizione = iniziativeServiceInstance.get().findStatiTransizioneConsentiti().stream()
                        .filter(s -> !Objects.equals(s.getCodiceStato(), statoCorrente.getCodiceStato()))
                        .collect(Collectors.toList());
            }
        }
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }
}
