package com.axiante.mui.webapp.views.content.dbpromo;

import static com.axiante.mui.webapp.views.util.PianoMediaExporterBuilder.CONTENT_TYPE;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.business.PianoMediaStatoUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPianificazioneDettaglioService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.PianoMediaSecurityUtils;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.business.service.PianoMediaDettaglioFactory;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.AddItemsBean;
import com.axiante.mui.webapp.views.content.dbpromo.dtos.ItemDto;
import com.axiante.mui.webapp.views.util.PianoMediaExporterBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("pianificazionePianoMedia")
@Dependent
@Slf4j
public class PianificazionePianoMediaView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -4605435360088759562L;

    private static final int TAB_PIANIFICAZIONE = 0;
    private static final int TAB_STATO_COMPRATORI = 1;

    @Inject
    private ApplicationProperties applicationProperties;

    @Inject
    private Instance<PianoMediaService> pianoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaPianificazioneDettaglioService> pianificazioneDettaglioServiceInstance;

    @Inject
    private Instance<PianoMediaSecurityUtils> securityUtilsInstance;

    @Inject
    private Instance<PianoMediaDettaglioFactory> pianoMediaDettaglioFactoryInstance;

    @Inject
    private Instance<MuiService> muiServiceInstance;

    @Inject
    @Getter
    private AddItemsBean addItemsBean;

    @Getter
    private Long idPianoMediaSelected;

    @Getter
    private PianoMediaEntity currentPianoMedia;

    @Getter
    private List<PianoMediaEntity> pianoMediaList;

    @Getter
    @Setter
    private Integer activeTab = 0;

    @Getter
    @Setter
    private boolean addItemsBtnDisabled = false;

    @Getter
    private String deleteItemConfirmDialogMessage;

    @Getter
    private Long idPianificazioneToBeDeleted;

    private StatoPromozioneEntity statoInPianificazione;

    private StatoPromozioneEntity statoSbloccoEsecuzione;

    @Inject Instance<PianoMediaExporterBuilder> exportBuilder;
    @Inject
    private StatoPromoService statoPromoService;

    @Inject
    private Instance<PianoMediaStatoUtils> pianoMediaStatoUtilsInstance;

    @Inject
    private Instance<AuditLogService> auditLogServiceInstance;

    @Getter
    @Setter
    private boolean publishBtnDisabled = false;

    @Getter
    @Setter
    private boolean publishPlanningButtons = false;

    @Getter
    @Setter
    private boolean approvazioneCompratoreButtons = false;

    @Getter
    @Setter
    private boolean approveButtonsDisabled = true;

    @Getter
    @Setter
    private boolean tabStatoCompratoriRendered = true;

    @Getter
    @Setter
    private boolean ripubblicaBtnDisabled = true;


    @Inject
    UserService userService;

    private List<String> buyersCodeSelected;

    public void init() {
        loadPianiMedia();
        autoselectFirstItem();
        updatePubishPlanningButtons();
        updateAddItemsBtn();
        updatePublishPianoMediaBtn();
        updateApprovazioneCompratoreButtons();
        updateApproveButtons();
        updateTabStatoCompratori();
    }

    @PostConstruct
    public void postConstruct() {
        try {
            statoInPianificazione = statoPromoService.findByCode("30");
        } catch (Exception ex) {
            log.error("Error retrieving stato in pianificazione", ex);
            statoInPianificazione = null;
        }
        try {
            statoSbloccoEsecuzione = statoPromoService.findByCode("410");
        } catch (Exception ex) {
            log.error("Error retrieving stato sblocco esecuzione", ex);
            statoSbloccoEsecuzione = null;
        }
    }

    public void prepareAddItemsDialog() {
        // devo rileggere il piano: potrei aver cancellato roba dal webservice e quindi ritrovarmi
        // con dati che non hanno + senso
        if (getCurrentPianoMedia() != null)
            setIdPianoMediaSelected(getCurrentPianoMedia().getId());
        addItemsBean.setUser(getUserDto());
        addItemsBean.setPianoMedia(getCurrentPianoMedia());
        addItemsBean.resetDialog();
        addItemsBean.loadPromoAvailable();
        addItemsBean.loadAllFilterOptions();
    }

    public void publishPianoMedia() {
        //TODO: riusciamo a integrare questa pubblicazione con quella da cambio stato? Tentativo in PianoMediaStatusUtils
        if (getCurrentPianoMedia() != null) {
            // devo fare refresh del piano media perche' potrei aver modificato roba...
            currentPianoMedia = pianoMediaServiceInstance.get().findById(currentPianoMedia.getId());
            try {
                final PianoMediaStatoEntity currentStato = getCurrentStato();
                if (currentStato != null && "30".equals(currentStato.getStato().getCodiceStato())
                        && securityUtilsInstance.get().canPublishPianoMedia(getCurrentUser())) {
                    // Sono nello stato di partenza corretto e posso pubblicare
                    // Imposto a 300 lo stato del piano media e delle pianificazioni associate
                    final StatoPromozioneEntity statoPubblicato = statoPromoService.findByCode("300");
                    currentPianoMedia = pianoMediaStatoUtilsInstance.get()
                            .cambiaStato(currentPianoMedia, statoPubblicato, getCurrentUser().getName());
                    currentPianoMedia = (PianoMediaEntity) pianoMediaServiceInstance.get()
                            .persistWithAuditLog(currentPianoMedia, getCurrentUser().getName());
                    setIdPianoMediaSelected(currentPianoMedia.getId());
                    // Scrivo nel audit log l'operazione
                    auditLogServiceInstance.get().logAction(AuditLogEntity.PIANO_MEDIA,
                            String.format("Pubblicazione piano media %d", currentPianoMedia.getId()),
                            getCurrentUser().getName());
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                            String.format("Piano media '%s' pubblicato", currentPianoMedia.getDescrizione())));
                } else {
                    String message = String.format("Impossibile pubblicare il piano media %s, stato %s non valido o utente non abilitato", getCurrentPianoMedia().getDescrizione(), currentStato.getStato().getCodiceStato());
                    log.error(message);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
                }
            } catch (Exception ex) {
                log.error(String.format("Error publish piano media with id '%s'", getCurrentPianoMedia().getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile pubblicare piano media '%s', contattare l'assistenza",
                                getCurrentPianoMedia().getDescrizione())));
            }
        } else {
            log.error("Nessun piano media selezionato per la pubblicazione");
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile pubblicare piano media corrente, contattare l'assistenza"));
        }
    }

    public void approvePianoMedia() {
        try {
            final StatoPromozioneEntity statoApprovato = statoPromoService.findByCode("310");
            approve(statoApprovato);
        } catch (Exception e) {
            log.error("Errore durante il recupero dello stato 310", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Workflow di approvazione non disponibile, contattare l'assistenza"));
        }
    }

    public void notApprovePianoMedia() {
        try {
            final StatoPromozioneEntity statoNonApprovato = statoPromoService.findByCode("320");
            approve(statoNonApprovato);
        } catch (Exception e) {
            log.error("Errore durante il recupero dello stato 310", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Workflow di approvazione non disponibile, contattare l'assistenza"));
        }
    }

    private void approve(@NonNull StatoPromozioneEntity stato) {
        final PianoMediaStatoEntity currentStato = getCurrentStato();
        if (currentStato != null) {
            List<PianoMediaPianificazioneDettaglioEntity> details = getPianificazioniVisibili();
            if (details == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile %sapprovare piano media %s, contattare l'assistenza",
                                stato.getCodiceStato().equals("310") ? "" : "non ", getCurrentPianoMedia().getDescrizione())));
                return;
            }
            details.forEach(d -> d.setStato(stato));
            pianificazioneDettaglioServiceInstance.get().persistInTransaction(details);
            currentPianoMedia = pianoMediaServiceInstance.get().findById(currentPianoMedia.getId());
            // a questo punto ... se sto cambiando in 320 allora vado via dritto
            boolean cambiaStatoComplessivo = !currentStato.getStato().getCodiceStato().equals("320");
            StatoPromozioneEntity statoComplessivo = null;
            if (cambiaStatoComplessivo) {
                // ho bisogno di calcolare lo stato complessivo
                statoComplessivo = pianoMediaStatoUtilsInstance.get().calcolaStato(currentPianoMedia.getDettagliPianificazione());
                if (statoComplessivo != null) {
                    currentPianoMedia = pianoMediaStatoUtilsInstance.get()
                            .cambiaStato(currentPianoMedia, statoComplessivo, getCurrentUser().getName(), false);
                    currentPianoMedia = (PianoMediaEntity) pianoMediaServiceInstance.get()
                            .persistWithAuditLog(currentPianoMedia, getCurrentUser().getName());
                }
            }
            // Scrivo nel audit log l'operazione
            auditLogServiceInstance.get().logAction(AuditLogEntity.PIANO_MEDIA,
                    String.format("Piano media %d - %sapprovazione compratore", currentPianoMedia.getId(),
                            stato.getCodiceStato().equals("310") ? "" : "non "),
                    getCurrentUser().getName());
            setIdPianoMediaSelected(currentPianoMedia.getId());
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    String.format("Piano media '%s' %sapprovato", currentPianoMedia.getDescrizione(),
                            stato.getCodiceStato().equals("310") ? "" : "non ")));
        } else {
            log.error("Nessun piano media selezionato per la non approvazione");
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    String.format("Impossibile %sapprovare piano media corrente, contattare l'assistenza",
                            stato.getCodiceStato().equals("310") ? "" : "non ")));
        }
    }

    public void ripubblicaPianificazioni() {
        if (currentPianoMedia != null && buyersCodeSelected != null && !buyersCodeSelected.isEmpty()) {
            try {
                // Recupero tutti i dettagli per i compratori
                List<PianoMediaPianificazioneDettaglioEntity> details = pianoMediaDettaglioFactoryInstance.get()
                        .createPianoMediaDettaglioDTO(currentPianoMedia).stream()
                        .filter(d -> buyersCodeSelected.contains(d.getCodiceCompratore()))
                        .map(PianoMediaDettaglioDTO::getDettaglioPianificazione)
                        .collect(Collectors.toList());
                // Setto stato 300 e persisto
                final StatoPromozioneEntity statoPubblicato = statoPromoService.findByCode("300");
                details.forEach(d -> d.setStato(statoPubblicato));
                pianificazioneDettaglioServiceInstance.get().persistInTransaction(details);
                // Scrivo nel audit log l'operazione
                final AuditLogService auditLogService = auditLogServiceInstance.get();
                buyersCodeSelected.forEach(buyer -> auditLogService
                        .logAction(AuditLogEntity.PIANO_MEDIA, String.format("Ripubblicazione piano media %d - Compratore %s",
                                        currentPianoMedia.getId(), buyer),
                                getCurrentUser().getName()));
                final String buyers = String.join(";", buyersCodeSelected);
                // Calcolo il nuovo stato della testata Piano Media e se necessario cambio stato e persisto
                currentPianoMedia = pianoMediaServiceInstance.get().findById(currentPianoMedia.getId());
                final StatoPromozioneEntity statoCalcolato = pianoMediaStatoUtilsInstance.get()
                        .calcolaStato(currentPianoMedia);
                if (!statoCalcolato.getCodiceStato().equals(getCurrentStato().getStato().getCodiceStato())) {
                    currentPianoMedia = pianoMediaStatoUtilsInstance.get()
                            .cambiaStato(currentPianoMedia, statoCalcolato, getCurrentUser().getName(), false);
                    currentPianoMedia = (PianoMediaEntity) pianoMediaServiceInstance.get()
                            .persistWithAuditLog(currentPianoMedia, getCurrentUser().getName());
                    setIdPianoMediaSelected(currentPianoMedia.getId());
                }
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                        String.format("Piano media '%s' ripubblicato per i compratori %s",
                                currentPianoMedia.getDescrizione(), buyers)));
            } catch (Exception ex) {
                log.error(String.format("Error 'Ripubblica Pianificazioni' for Piano Media with id %d",
                        currentPianoMedia.getId()), ex);
            }
        } else {
            log.error("No Piano Media or Buyer selected");
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile ripubblicare pianificazioni, contattare l'assistenza"));
        }
    }

    public void buyerSelected() {
        clearBuyersCodeSelected();
        try {
            final String buyerSelectedParam = getRequestParameterMap().get("buyerSelected");
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(buyerSelectedParam);
            if (jsonNode != null) {
                jsonNode.forEach(n -> buyersCodeSelected.add(n.asText()));
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile recuperare lista compratori selezionati"));
            }
        } catch (Exception ex) {
            log.error("Error reading selected buyers", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile recuperare lista compratori selezionati"));
        }
        updateRipubblicaBtn();
    }

    public void confirmAddItems() {
        if (validateAddingItems() && getCurrentStato() != null) {
            try {
                // Recupero i supporti configurati
                Set<PianificazionePianoMediaEntity> cfgList = currentPianoMedia.getConfigurazioniPianoMedia();
                // Per ogni item selezionato, creo una riga di pianificazione
                // #4979: lo stato di entrata dipende dallo stato corrente della testata
                // se testata in 410 anche i dettagli entrano in 410
                // altrimenti entrano in 30
                final StatoPromozioneEntity statoItems = "410".equals(getCurrentStato().getStato().getCodiceStato())
                        ? statoSbloccoEsecuzione
                        : statoInPianificazione;
                if (statoItems == null) {
                    log.error(String.format("Errore aggiunta pianificazioni al PianoMedia con id '%d'; nessun stato di destinazione impostato",
                            currentPianoMedia.getId()));
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            "Impossibile aggiungere articoli alla pianificazione, contattare l'assistenza"));
                    return;
                }
                for (ItemDto item : addItemsBean.getSelectedItems()) {
                    PianoMediaPianificazioneDettaglioEntity pianificazioneArticolo = new PianoMediaPianificazioneDettaglioEntity();
                    pianificazioneArticolo.setCodiceItem(item.getCodiceItem());
                    pianificazioneArticolo.setCodicePromozione(item.getCodicePromo());
                    pianificazioneArticolo.setTipoItem(item.getTipoItem());
                    pianificazioneArticolo.setStato(statoItems);
                    // Per ogni supporto configurato creo ed aggiungo alla pianificazione una anagrafica media
                    cfgList.forEach(c -> {
                        PianificazioneAnagraficaPianoMediaEntity p = new PianificazioneAnagraficaPianoMediaEntity();
                        p.setPianificazioneMedia(c);
                        pianificazioneArticolo.addPianificazioneAnagrafica(p);
                    });
                    currentPianoMedia.addDettagliPianificazione(pianificazioneArticolo);
                }
                StatoPromozioneEntity stato = pianoMediaStatoUtilsInstance.get().calcolaStato(currentPianoMedia);
                if (stato != null) {
                    currentPianoMedia = pianoMediaStatoUtilsInstance.get().cambiaStato(currentPianoMedia, stato, getCurrentUser().getName());
                }
                currentPianoMedia = (PianoMediaEntity) pianoMediaServiceInstance.get()
                        .persistWithAuditLog(currentPianoMedia, getCurrentUser().getName());

                setIdPianoMediaSelected(currentPianoMedia.getId());
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                        String.format("Aggiunti %d articoli in pianificazione", addItemsBean.getSelectedItems().size())));
            } catch (Exception ex) {
                log.error(String.format("Error adding pianificazione to PianoMedia with id '%d'", currentPianoMedia.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile aggiungere articoli alla pianificazione, contattare l'assistenza"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile aggiungere articoli alla pianificazione, contattare l'assistenza"));
        }
    }

    public void selectAllMediaForArticolo() {
        final Map<String, String> params = getRequestParameterMap();
        if (params.get("id") == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile completare l'azione per la pianificazione, contattare l'assistenza"));
            return;
        }
        try {
            Long idPianificazione = Long.parseLong(params.get("id"));
            currentPianoMedia.getDettagliPianificazione()
                    .stream().filter(d -> idPianificazione.equals(d.getId()))
                    .flatMap(d->d.getPianificazioniAnagrafiche().stream())
                            .forEach(a->a.setAttivo(true));
            pianoMediaServiceInstance.get().persistWithAuditLog(currentPianoMedia,getCurrentUser().getName());
            executeScript("refreshPianoMediaPianificazioni();");

        } catch (Exception ex) {
            log.error(String.format("Error parsing id '%s' as long", params.get("id")), ex);
        }
    }

    public void prepareDeleteItemMessage() {
        final Map<String, String> params = getRequestParameterMap();
        if (params.get("id") == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile eliminare articolo dalla pianificazione, contattare l'assistenza"));
            return;
        }
        try {
            deleteItemConfirmDialogMessage = String.format("Sei sicuro di voler cancellare l'elemento %s?",
                    params.get("articolo") != null ? params.get("articolo") : "corrente");
            idPianificazioneToBeDeleted = Long.parseLong(params.get("id"));
            executeScript("PF('deleteArticoloPianoMediaConfirmDialog').show();");
        } catch (Exception ex) {
            log.error(String.format("Error parsing id '%s' as long", params.get("id")), ex);
        }
    }

    public void setIdPianoMediaSelected(Long idPianoMediaSelected) {
        this.idPianoMediaSelected = idPianoMediaSelected;
        if (idPianoMediaSelected != null) {
            setCurrentPianoMedia(pianoMediaServiceInstance.get().findById(idPianoMediaSelected));
        } else {
            setCurrentPianoMedia(null);
        }
        updatePubishPlanningButtons();
        updatePublishPianoMediaBtn();
        updateApprovazioneCompratoreButtons();
        updateApproveButtons();
        clearBuyersCodeSelected();
        updateRipubblicaBtn();
    }

    public void setCurrentPianoMedia(PianoMediaEntity currentPianoMedia) {
        this.currentPianoMedia = currentPianoMedia;
        updateAddItemsBtn();
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTab = tv.getChildren().indexOf(event.getTab());
        switch (this.activeTab) {
            case TAB_PIANIFICAZIONE:
                addItemsBean.clearSelectedItems();
                break;
            case TAB_STATO_COMPRATORI:
                clearBuyersCodeSelected();
                break;
        }
    }

    public Date getDataInizio() {
        return getCurrentPianoMedia() != null ? getCurrentPianoMedia().getDataInizio() : null;
    }

    public Date getDataFine() {
        return getCurrentPianoMedia() != null ? getCurrentPianoMedia().getDataFine() : null;
    }

    public String getPromozioneRiferimento() {
        return getCurrentPianoMedia() != null ? getCurrentPianoMedia().getPromoMaster() : null;
    }

    public String getStato() {
        PianoMediaStatoEntity s = getCurrentStato();
        return s != null ? s.getStato().fullDescription() : null;
    }

    private PianoMediaStatoEntity getCurrentStato() {
        return getCurrentPianoMedia() != null ? getCurrentPianoMedia().getCurrentStatus() : null;
    }

    private void loadPianiMedia() {
        pianoMediaList = pianoMediaServiceInstance.get().findPianiMediaAccessibiliInPianificazione().stream()
                .sorted(Comparator.comparing(PianoMediaEntity::getDescrizione))
                .collect(Collectors.toList());
    }

    private void autoselectFirstItem() {
        if (!getPianoMediaList().isEmpty()) {
            if (getCurrentPianoMedia() == null) {
                setIdPianoMediaSelected(getPianoMediaList().get(0).getId());
            }
        }
    }

    private void clearBuyersCodeSelected() {
        if (buyersCodeSelected == null) {
            buyersCodeSelected = new ArrayList<>();
        } else {
            buyersCodeSelected.clear();
        }
    }

    private void updateAddItemsBtn() {
        boolean abilitato = false;
        if (getCurrentPianoMedia() != null) {
            final String codiceStato = getCurrentPianoMedia().getCurrentStatus().getStato().getCodiceStato();
            /*
             * se lo stato e' 30 o 410 posso aggiungere articoli se sono abilitato
             */
            if ("30".equals(codiceStato) || "410".equals(codiceStato)) {
                abilitato = getCurrentUser().isAdmin() || securityUtilsInstance.get().canAddItems(getCurrentUser());
            }
        }
        setAddItemsBtnDisabled(!abilitato);
    }

    private void updatePublishPianoMediaBtn() {
        boolean abilitato = false;
        if (getCurrentPianoMedia() != null && !getCurrentPianoMedia().getDettagliPianificazione().isEmpty()) {
            /*
             * se ho pianificazioni di dettaglio e lo stato e' 30 posso pubblicare se sono abilitato
             */
            final String codiceStato = getCurrentPianoMedia().getCurrentStatus().getStato().getCodiceStato();
            if ("30".equals(codiceStato)) {
                abilitato = getCurrentUser().isAdmin() || securityUtilsInstance.get().canPublishPianoMedia(getCurrentUser());
            }
        }
        setPublishBtnDisabled(!abilitato);
    }

    private boolean calculateApproveButtonsDisabled() {
        boolean disabled = true;
        if (getCurrentPianoMedia() != null
                && getCurrentPianoMedia().getDettagliPianificazione() != null
                && !getCurrentPianoMedia().getDettagliPianificazione().isEmpty()) {

            switch (getCurrentPianoMedia().getCurrentStatus().getStato().getCodiceStato()) {
                case "300":
                case "320":
                case "310":
                    disabled = calculateApproveDisabled();
                    break;
                default:
                    disabled = true;
                    break;
            }
        }
        return disabled;
    }

    private boolean calculateApproveDisabled() {
        List<PianoMediaPianificazioneDettaglioEntity> dettagli = getPianificazioniVisibili();
        // calcolo lo stato complessivo delle pianificazioni a cui ho accesso
        StatoPromozioneEntity stato = pianoMediaStatoUtilsInstance.get().calcolaStato(dettagli);
        if (stato != null) {
            // se ho uno stato posso abilitare i bottoni solo se non ho gia' fatto un azione di approvazione => stato 300
            return !stato.getCodiceStato().equals("300");
        }
        return true;
    }

    private void updateApproveButtons() {
        setApproveButtonsDisabled(calculateApproveButtonsDisabled());
    }

    private void updateRipubblicaBtn() {
        setRipubblicaBtnDisabled(buyersCodeSelected == null || buyersCodeSelected.isEmpty());
    }

    // Recupero pianificazioni visibili all'utente selezionato
    private List<PianoMediaPianificazioneDettaglioEntity> getPianificazioniVisibili() {
        List<PianoMediaDettaglioDTO> dtoList = pianoMediaDettaglioFactoryInstance.get()
                .createPianoMediaDettaglioDTO(getCurrentPianoMedia());
        // Security on view TUTTI | PROPRI
        if (!getCurrentUser().isAdmin() && securityUtilsInstance.get().canViewOwnItemsOnly(getCurrentUser())) {
            final List<String> gruppi = getUserDto().getGruppi();
            if (gruppi == null || gruppi.isEmpty()) {
                final String msg = String.format("Nessun gruppo associato all'utente '%s'; impossibile determinare compratori e di conseguenza articoli",
                        getCurrentUser().getName());
                log.error(msg);
                return null;
            }
            final List<String> buyers = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppo(gruppi);
            // Tengo solo gli articoli dei compratori associati ai gruppi dell'utente
            dtoList = dtoList.stream()
                    .filter(i -> buyers.contains(i.getCodiceCompratore()))
                    .collect(Collectors.toList());
        }
        return dtoList.stream()
                .map(PianoMediaDettaglioDTO::getDettaglioPianificazione)
                .collect(Collectors.toList());
    }

    private void updatePubishPlanningButtons() {
        if (getCurrentUser().isAdmin()) {
            setPublishPlanningButtons(true);
        } else {
            setPublishPlanningButtons(getCurrentPianoMedia() != null
                    && securityUtilsInstance.get().canWritePianificazione(getCurrentUser())
                    && !securityUtilsInstance.get().isBuyer(getCurrentUser()));
        }
    }

    private void updateApprovazioneCompratoreButtons() {
        if (getCurrentPianoMedia() != null) {
            final boolean modalitaCompratore = applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE,
                    ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE);
            setApprovazioneCompratoreButtons(
                    securityUtilsInstance.get().canApproveItems(getCurrentUser(), modalitaCompratore)
                            && pianoMediaStatoUtilsInstance.get().isApproveEnabled(getCurrentPianoMedia())
            );
        } else {
            setApprovazioneCompratoreButtons(false);
        }
    }

    private void updateTabStatoCompratori() {
        final boolean modalitaCompratore = applicationProperties.getProperty(ApplicationProperties.MEDIA_MODALITA_COMPRATORE,
                ApplicationProperties.DEFAULT_MEDIA_MODALITA_COMPRATORE);
        setTabStatoCompratoriRendered(getCurrentUser().isAdmin() ||
                (getCurrentPianoMedia() != null && securityUtilsInstance.get().canAccessCheckBuyers(getCurrentUser(), modalitaCompratore)));
    }

    private boolean validateAddingItems() {
        return currentPianoMedia != null
                && addItemsBean.getSelectedItems() != null
                && !addItemsBean.getSelectedItems().isEmpty();
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    @Override
    public void applyRules() {
        // NOOP
    }

    @Override
    public void applyDefaultRules() {
        // NOOP
    }

    public StreamedContent getExportPianificazionePianoMedia(){
         UserDTO userDTO = userService.asDto(getCurrentUser(), getContesto());

        PianoMediaExporterBuilder.PianoMediaExporter exporter = exportBuilder.get().getExporter(currentPianoMedia, userDTO != null? userDTO.getGruppi():null, getCurrentUser());
        try {
            return new DefaultStreamedContent(exporter.export(), CONTENT_TYPE, exporter.getFileName() );
        }
        catch (Exception e){
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
                    String.format("Errore durante l'export del piano media '%s' ", currentPianoMedia.getDescrizione())));
            log.error("Error while exporting piano media to excel", e);
        }
        return null;
    }
}
