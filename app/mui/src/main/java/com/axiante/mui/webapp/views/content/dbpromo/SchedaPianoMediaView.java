package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.business.PianoMediaStatoUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaStatiTransizioneService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.webapp.business.PianoMediaSecurityUtils;
import com.axiante.mui.webapp.business.service.PianoMediaHelperService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.PianoMediaEditBean;
import com.axiante.mui.webapp.webservice.util.PianoMediaFactory;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("schedaPianoMedia")
@Dependent
@Slf4j
public class SchedaPianoMediaView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 3521326746894856145L;

    @Getter
    private boolean writeSchedaFlag = true;

    @Getter
    private Long idPianoMediaSelected;

    @Getter
    private List<PianoMediaEntity> pianoMediaList;

    @Inject
    private Instance<PianoMediaService> pianoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaHelperService> pianoMediaHelperServiceInstance;

    @Getter
    private PianoMediaEntity currentPianoMedia;

    @Inject
    private Instance<PianoMediaStatiTransizioneService> pianoStatiTransizioneService;

    @Getter
    @Setter
    private List<PianoMediaStatiTransizioneEntity> statiTransizione;

    @Inject
    private Instance<StatoPromoService> statoPromozioneService;

    @Inject
    Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Inject
    private Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaFactory> pianoMediaFactoryInstance;

    @Inject
    private Instance<PianoMediaSecurityUtils> securityUtilsInstance;

    @Getter
    private boolean descrizioneEditable = false;

    @Getter
    private boolean dataInizioEditable = false;

    @Getter
    private boolean dataFineEditable = false;

    @Getter
    private boolean promoMasterEditable = false;

    @Getter
    private boolean promoSecondaryAEditable = false;

    @Getter
    private boolean promoSecondaryBEditable = false;

    @Getter
    private boolean promoSecondaryCEditable = false;

    @Getter
    private boolean buttonDeleteEnabled = false;

    @Getter
    private boolean editEnabled = false;

    @Getter
    private boolean editLocked = false;

    @Getter
    private boolean resetDisabled = false;

    @Getter
    private boolean promorifEditable = false;

    @Getter
    private boolean btnPianificazioneDisabled = true;

    private PianoMediaEditBean pianoMediaEditBean;

    @Getter
    @Setter
    Long idStatoTransizioneCorrente;

    @Getter
    @Setter
    private Integer activeTab = 0;

    @Inject
    Instance<PianoMediaStatoUtils> pianoMediaStatoUtilsInstance;
    public void init() {
        this.writeSchedaFlag = getCurrentUser().isAdmin() || securityUtilsInstance.get().canWriteScheda(getCurrentUser());
        refreshPianoMediaList();
        if (getPianoMediaList().size() != 0) {
            if (currentPianoMedia == null) {
                setIdPianoMediaSelected(getPianoMediaList().get(0).getId());
            }
            PianoMediaStatoEntity lastStatus = getCurrentStato();
            if (lastStatus != null) {
                final StatoPromozioneEntity statoPromozioneEntity = lastStatus.getStato();
                final List<PianoMediaStatiTransizioneEntity> statiTransizione = pianoStatiTransizioneService.get()
                        .findAllNotAutomaticByIdAndStatus(currentPianoMedia.getId(), statoPromozioneEntity.getId());
                setStatiTransizione(statiTransizione);
                if ((statiTransizione == null) || statiTransizione.isEmpty()) {
                    setIdStatoTransizioneCorrente(null);
                }
            }
        }
    }

    public void setIdPianoMediaSelected(Long idPianoMediaSelected) {
        this.idPianoMediaSelected = idPianoMediaSelected;
        setCurrentPianoMedia(pianoMediaServiceInstance.get().findById(idPianoMediaSelected));
        checkPianificazioneEnabled();
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

    private void refreshPianoMediaList() {
        pianoMediaList = pianoMediaServiceInstance.get().findOpenPianiMedia();
        sortPianoMediaList();
    }

    public void setCurrentPianoMedia(PianoMediaEntity currentPianoMedia) {
        this.currentPianoMedia = currentPianoMedia;
        this.idPianoMediaSelected = currentPianoMedia.getId();

        // devo ricaricare gli stati e le transizioni
        final List<PianoMediaStatiTransizioneEntity> statiTransizione = pianoStatiTransizioneService.get()
                .findAllNotAutomaticByIdAndStatus(this.currentPianoMedia.getId(),
                        getCurrentStato().getStato().getId());
        setStatiTransizione(statiTransizione);
        if ((statiTransizione == null) || statiTransizione.isEmpty()) {
            setIdStatoTransizioneCorrente(null);
        }
        // devo aggiustare l'editabilita'
        editEnabled = false;
        updateEditability();
        pianoMediaEditBean = new PianoMediaEditBean(pianoMediaPromoServiceInstance.get());
        pianoMediaEditBean.setPianoMedia(this.currentPianoMedia);
        pianoMediaEditBean.setPromoList(pianoMediaPromoServiceInstance.get()
                .findByDataFineGreaterThan(currentPianoMedia.getDataInizio()));
        resetDisabled = currentPianoMedia.getPianoMediaStati().stream()
                .filter(s -> s.getDataFineStato() == null)
                .noneMatch(s -> "10".equals(s.getStato().getCodiceStato()));
    }

    public String getPromozioneRiferimento() {
        if (currentPianoMedia != null) {
            return currentPianoMedia.getPromoMaster();
        }
        return null;
    }

    public String getAnno() {
        if (currentPianoMedia != null) {
            return currentPianoMedia.getAnno().toString();
        }
        return null;
    }

    public Date getDataInizio() {
        if (currentPianoMedia != null) {
            return currentPianoMedia.getDataInizio();
        }
        return null;
    }

    public Date getDataFine() {
        if (currentPianoMedia != null) {
            return currentPianoMedia.getDataFine();
        }
        return null;
    }

    private PianoMediaStatoEntity getCurrentStato() {
        return currentPianoMedia != null ? currentPianoMedia.getCurrentStatus() : null;
    }

    public String getStato() {
        PianoMediaStatoEntity s = getCurrentStato();
        if (s != null) {
            return s.getStato().fullDescription();
        }
        return null;
    }

    public Date getInizioStato() {
        PianoMediaStatoEntity s = getCurrentStato();
        if (s != null) {
            return s.getDataInizioStato();
        }
        return null;
    }

    public void insertNewStatus() {
        log.debug("this.idStatoTransizioneCorrente: " + this.idStatoTransizioneCorrente);
        if ((this.idStatoTransizioneCorrente == null) || (this.currentPianoMedia == null)) {
            // return error here!
            return;
        }
        // insert new status to promotion
        PianoMediaEntity pianoMedia = getPianoById(this.currentPianoMedia.getId());
        PianoMediaStatoEntity lastStatus = getCurrentStato();
        if (lastStatus == null) {
            return;
        }

        Date now = new Date();

        PianoMediaStatoEntity nextStatus = (PianoMediaStatoEntity) AuditLogFiller
                .fillAuditLogFields(new PianoMediaStatoEntity(), getCurrentUser().getName());
        nextStatus.setDataInizioStato(now);

        StatoPromozioneEntity byCode = statoPromozioneService.get()
                .findById(this.idStatoTransizioneCorrente);
        if (byCode == null) {
            throw new RuntimeException("Stato Promozione Entity not found");
        }

        try {
            // la view si basa solo sugli stati manuali, gli stati automatici sono gestiti
            // dal backend/database
            final PianoMediaStatiTransizioneEntity transizione = pianoStatiTransizioneService.get()
                    .findManualiByPianoAndStatuses(pianoMedia, lastStatus.getStato(), byCode, false);
            if (transizione == null) {
                throw new RuntimeException(String.format("Cannot find transition from %s to %s",
                        lastStatus.getStato().getCodiceStato(), byCode.getCodiceStato()));
            }
            boolean result = true;
            if ((transizione.getFlagControlli() != null) && transizione.getFlagControlli()) {
                // Execute StoredProcedure
                try {
                    //TODO: lanciare i controlli ?
                    log.warn("Nessun controllo previsto");
                } catch (Exception ex) {
                    result = false;
                }
                if (!result) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore","Errore di configurazione transizione stati: il piano media non prevede controlli di cambio stato."));
                }
            }
            if (result) {
                if ((transizione.getFlagPubblica() != null) && transizione.getFlagPubblica()) {
                    // Execute StoredProcedure
                    try {
                        //TODO: lanciare i controlli ?
                        log.warn("Pubblicazione automatica non prevista");
                    } catch (Exception ex) {
                        result = false;
                    }
                    if (!result) {
                        addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Errore di configurazione: il piano media non prevede la pubblicazione automatica"));
                    }
                }
            }
            if (result) {
                pianoMediaStatoUtilsInstance.get().cambiaStato(pianoMedia, byCode, getCurrentUser().getName());
            }
            pianoMediaServiceInstance.get().persistWithAuditLog(pianoMedia, getCurrentUser().getName());
            if (!result) {
                // Reset dialog
                setIdStatoTransizioneCorrente(null);
            } else {
                // faccio la set cosi' aggiorna la lista delle transizioni
                setCurrentPianoMedia(pianoMedia);
            }
        } catch (Exception ex) {
            log.warn(String.format(
                    "Errore impostazione nuovo stato su promozione con id %d, utente %s, stato iniziale %s e stato finale %s",
                    pianoMedia.getId(), getCurrentUser().getName(),
                    lastStatus.getStato().getCodiceStato(), byCode.getCodiceStato()), ex);
            // Reset dialog
            setIdStatoTransizioneCorrente(null);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore impostazione nuovo stato promozione; contattare l'assistenza tecnica."));
        }
        resetDialog();
        checkPianificazioneEnabled();
    }

    private PianoMediaEntity getPianoById(@NonNull Long id) {
        return pianoMediaServiceInstance.get().findById(id);
    }

    public void resetDialog() {
        this.idStatoTransizioneCorrente = null;
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTab = tv.getChildren().indexOf(event.getTab());
    }

    public void resetPianificazioneMediaBtn() {
        if (currentPianoMedia != null) {
            try {
                // Eliminazioni di tutte le pianificazioni (e controlli associati) relative al piano media selezionato
                final PianoMediaHelperService pianoMediaHelperService = pianoMediaHelperServiceInstance.get();
                pianoMediaHelperService.removeExistingChecks(currentPianoMedia);
                pianificazionePianoMediaServiceInstance.get().remove(currentPianoMedia.getConfigurazioniPianoMedia());
                // Inizializzazione secondo default (ed esecuzione controlli) del piano media selezionato
                final String username = getCurrentUser().getName();
                currentPianoMedia.setConfigurazioniPianoMedia(
                        pianoMediaFactoryInstance.get().createDefaults(currentPianoMedia, username));
                pianoMediaServiceInstance.get().persist(currentPianoMedia);
                int failedChecks = currentPianoMedia.getConfigurazioniPianoMedia().stream().map(p -> pianoMediaHelperService.executeChecks(p, username)).collect(Collectors.summingInt(Integer::intValue));
                if (failedChecks > 0) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                            String.format("Piano media %s re-inizializzato.\nRilevati %d controlli falliti", currentPianoMedia.getDescrizione(), failedChecks)));
                } else {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                            String.format("Piano media %s re-inizializzato.", currentPianoMedia.getDescrizione())));
                }
            } catch (Exception ex) {
                log.error(String.format("Error reset Piano Media with id %d", currentPianoMedia.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore reset piano media; contattare l'assistenza tecnica."));
            }
        } else {
            log.error("No PianoMedia selected");
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Nessun piano media selezionato per il reset"));
        }
    }

    private void updateEditability() {
        resetEditabilityTo(false);
        if (getCurrentStato() != null) {
            switch (getCurrentStato().getStato().getCodiceStato()) {
                case "10":
                    resetEditabilityTo(editEnabled);
                    break;
                case "30":
                    promoSecondaryAEditable = editEnabled && StringUtils.isBlank(pianoMediaEditBean.getPromoSecondaryA());
                    promoSecondaryBEditable = editEnabled && StringUtils.isBlank(pianoMediaEditBean.getPromoSecondaryB());
                    promoSecondaryCEditable = editEnabled && StringUtils.isBlank(pianoMediaEditBean.getPromoSecondaryC());
                    descrizioneEditable = editEnabled;
                    dataInizioEditable = editEnabled;
                    dataFineEditable = editEnabled;
                    break;
                case "300":
                case "310":
                case "320":
                    descrizioneEditable = editEnabled;
                    dataInizioEditable = editEnabled;
                    dataFineEditable = editEnabled;
                    break;
                case "410":
                    dataFineEditable = editEnabled;
                    promoSecondaryAEditable = editEnabled && StringUtils.isBlank(pianoMediaEditBean.getPromoSecondaryA());
                    promoSecondaryBEditable = editEnabled && StringUtils.isBlank(pianoMediaEditBean.getPromoSecondaryB());
                    promoSecondaryCEditable = editEnabled && StringUtils.isBlank(pianoMediaEditBean.getPromoSecondaryC());
                    break;
                case "500":
                case "00":
                case "400":
                    log.debug(String.format("stato %s non editabile", getCurrentStato().getStato().getCodiceStato()));
                    editLocked = true;
                    break;
                default:
                    log.error(String.format("stato %s non gestito", getCurrentStato().getStato().getCodiceStato()));
                    editLocked = true;
                    throw new RuntimeException("stato non gestito");
            }
        }
    }

    private void resetEditabilityTo(boolean value) {
        descrizioneEditable = value;
        dataInizioEditable = value;
        dataFineEditable = value;
        promoMasterEditable = value;
        promoSecondaryAEditable = value;
        promoSecondaryBEditable = value;
        promoSecondaryCEditable = value;
        buttonDeleteEnabled = value;
        promorifEditable = value;
        editLocked = false;
    }

    public void discardEditPiano() {
        pianoMediaEditBean.setPianoMedia(currentPianoMedia);
        unlockEditPiano();
    }

    public void saveEditPiano() {
        PianoMediaEntity pianoMedia = pianoMediaEditBean.getPianoMedia();
        pianoMediaServiceInstance.get().persistWithAuditLog(pianoMedia, getCurrentUser().getName());
        pianoMediaList.remove(currentPianoMedia);
        currentPianoMedia = pianoMedia;
        pianoMediaList.add(currentPianoMedia);
        sortPianoMediaList();
        unlockEditPiano();
    }

    private void sortPianoMediaList() {
        if (pianoMediaList != null) {
            pianoMediaList.sort(Comparator.comparing(PianoMediaEntity::getDescrizione));
        }
    }

    public void deletePiano() {
        //todo: questo e' da testare
        //todo: manca il controllo se il piano e' cancellabile e da chi
        try {
            PianoMediaStatoEntity lastStatus = getCurrentStato();
            final Date now = new Date();
            lastStatus.setDataFineStato(now);
            final PianoMediaStatoEntity statusCancelled = (PianoMediaStatoEntity) AuditLogFiller
                    .fillAuditLogFields(new PianoMediaStatoEntity(), getCurrentUser().getName());
            statusCancelled.setDataInizioStato(now);
            final StatoPromozioneEntity byCode = statoPromozioneService.get().findByCode(PromoStatusEnum.CANCELLATA_00.getKey());
            if (byCode == null) {
                log.error(String.format("Errore recupero stato piano media codice %s", PromoStatusEnum.CANCELLATA_00.getKey()));
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore cancellazione piano media; contattare l'assistenza tecnica."));
            }
            statusCancelled.setStato(byCode);
            currentPianoMedia.addPianoMediaStato(statusCancelled);
            pianoMediaServiceInstance.get().persistWithAuditLog(currentPianoMedia, getCurrentUser().getName());
            refreshPianoMediaList();
            if (getPianoMediaList().size() != 0) {
                setCurrentPianoMedia(getPianoMediaList().get(0));
            } else {
                setCurrentPianoMedia(null);
            }
        } catch (Exception ex) {
            log.error(String.format("Errore impostatzione stato 'CANCELLATA' su piano media con id %d", currentPianoMedia.getId()), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore cancellazione piano media; contattare l'assistenza tecnica."));
        }
    }

    public void unlockEditPiano() {
        if (editEnabled) {
            editEnabled = false;
        } else {
            editEnabled = true;
        }
        updateEditability();
    }

    public PianoMediaEditBean getPianoMediaEditBean() {
        return pianoMediaEditBean;
    }

    private void checkPianificazioneEnabled(){
        if ( getCurrentPianoMedia() != null ){
            final String codiceStato = getCurrentPianoMedia().getCurrentStatus().getStato().getCodiceStato();
            switch (codiceStato){
                case "30":
                case "300":
                case "310":
                case "320":
                case "400":
                case "410":
                    btnPianificazioneDisabled = false;
                    break;
                default:
                    btnPianificazioneDisabled = true;
            }
        } else {
            btnPianificazioneDisabled = true;
        }
    }

}
