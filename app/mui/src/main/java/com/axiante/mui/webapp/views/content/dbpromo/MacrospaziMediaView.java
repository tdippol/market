package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.CausaliRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoriRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.CausaliRetailService;
import com.axiante.mui.dbpromo.persistence.service.FornitoriRetailService;
import com.axiante.mui.dbpromo.persistence.service.MuiEventoRetailService;
import com.axiante.mui.dbpromo.persistence.service.MuiMacrospazioMediaService;
import com.axiante.mui.webapp.utils.EventoRetailUtils;
import com.axiante.mui.webapp.utils.MacrospaziMediaUtils;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.macrospaziMedia.EventiDialog;
import com.axiante.mui.webapp.views.content.dbpromo.macrospaziMedia.MacrospazioDialog;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.datepicker.DatePicker;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.TabChangeEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@MuiViewModel("macrospaziMedia")
@Dependent
@Slf4j
public class MacrospaziMediaView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -8216097858311259311L;

    @Inject
    private MuiMacrospazioMediaService service;

    @Inject
    private MacrospaziMediaUtils utils;

    @Inject
    private CausaliRetailService causaliRetailService;

    @Inject
    private FornitoriRetailService fornitoriRetailService;

    @Inject
    private MuiEventoRetailService eventoRetailService;

    @Getter
    private List<MuiMacrospazioMediaEntity> macrospazi;

    @Getter
    private MuiMacrospazioMediaEntity selectedMacrospazio;

    @Getter
    private Long selectedMacrospazioId;

    @Getter
    private MuiEventoRetailEntity selectedEvent;

    private final EventoRetailUtils eventoRetailUtils = new EventoRetailUtils();
    private List<CausaliRetailEntity> causaliRetail;

    @Getter
    private List<FornitoriRetailEntity> fornitoriRetail;

    @Getter
    private boolean editMacrospazio = false;
    @Getter
    private boolean editEvento = false;

    @Getter
    @Setter
    private Long dummyLong = -1L;

    @Getter
    private boolean macrospazioUsed = false;

    @Getter
    private String deleteMessage;
    @Getter
    private String deleteEventoMessage;

    @Getter
    private MuiEventoRetailEntity eventoToDelete;

    private MuiMacrospazioMediaEntity macrospazioToDelete;
    @Getter
    private boolean confirmRunning = false;

    @Getter
    private MacrospazioDialog macrospazioDialog;

    @Getter
    private EventiDialog eventiDialog;

    @Getter
    @Setter
    private Integer activeTab=0;

    final List<String> tabTitles = Arrays.asList("Anagrafica Macrospazi", "Gestione Eventi");

    @PostConstruct
    public void init() {
        macrospazi = service.findAll();
        macrospazi.sort(Comparator.comparing(MuiMacrospazioMediaEntity::getCodice));
        setSelectedMacrospazio(null);
        this.activeTab = 0;
        loadFornitoriRetail();
        eventiDialog = new EventiDialog(this, fornitoriRetail);
    }

    public List<CausaliRetailEntity> getCausaliRetail() {
        if (causaliRetail == null) {
            try {
                causaliRetail = causaliRetailService.getCausaliRetail();
            } catch (Exception e) {
                log.error("Error reading causali", e);
            }
        }
        return causaliRetail;
    }

    public void loadFornitoriRetail() {
        if (fornitoriRetail == null) {
            try {
                fornitoriRetail = fornitoriRetailService.getAllFornitoriRetailNotDeleted(new Date());
            } catch (Exception e) {
                log.error("Error reading fornitori", e);
            }
        }
    }

    protected String getEventValue(AjaxBehaviorEvent event) throws Exception {
        return (String) ((InputText) event.getSource()).getValue();
    }

    protected Double getEventDoubleValue(AjaxBehaviorEvent event) throws Exception {
        return (Double) ((InputNumber) event.getSource()).getValue();
    }

    protected Date getEventDateValue(AjaxBehaviorEvent event) throws Exception {
        return (Date) ((DatePicker) event.getSource()).getValue();
    }

    public void addMacrospazioClicked(ActionEvent e) {
        addMacrospazioClicked();
    }

    public void addMacrospazioClicked() {
        editMacrospazio = false;
        prepareMacrospazioDialog(new MuiMacrospazioMediaEntity());
    }

    public void addMacrospazio(ActionEvent e) {
        addMacrospazio();
    }

    public void addMacrospazio() {
        log.debug("add macrospazio called with mode {}", editMacrospazio ? "edit" : "add");
        try {
            MuiMacrospazioMediaEntity macrospazio = getMacrospazioDialog().getBean();
            macrospazio = fixCodiceMacrospazio(macrospazio);
            boolean canOperate = editMacrospazio ? utils.canEdit(macrospazio) : utils.canAdd(macrospazio);
            if (canOperate) {
                // check if we already have a macrospazio with same code
                if ((!editMacrospazio && service.existsByCodiceMacrospazio(macrospazio.getCodice())) ||
                        (editMacrospazio && service.existsByCodiceMacrospazio(macrospazio.getCodice(), Collections.singletonList(macrospazio.getId())))) {
                    addErrorMessage("Codice macrospazio duplicato",
                            "Esiste già un macrospazio con lo stesso codice");
                    log.debug(" macrospazio not added - duplicate code");
                    return;
                }
                // same as above but for description
                if ((!editMacrospazio && service.existsByDescrizioneMacrospazio(macrospazio.getDescrizione())) ||
                        (editMacrospazio && service.existsByDescrizioneMacrospazio(macrospazio.getDescrizione(), Collections.singletonList(macrospazio.getId())))) {
                    addErrorMessage("Descrizione macrospazio duplicata",
                            "Esiste già un macrospazio con la stessa descrizione");
                    log.debug(" macrospazio not added - duplicate description");
                    return;
                }
                setSelectedMacrospazio((MuiMacrospazioMediaEntity) service.persistWithAuditLog(macrospazio, getCurrentUser().getName()));
                addInfoMessage("Info", "Macrospazio " + (editMacrospazio ? "modificato" : "aggiunto") + " correttamente");
                updateMacrospaziList(selectedMacrospazio);
                log.debug(" macrospazio added");
            } else {
                addErrorMessage(
                        "Campi obbligatori mancanti",
                        "Codice, descrizione e listino sono obbligatori,\nle date devono essere coerenti");
                log.debug(" macrospazio not added");
            }
        } catch (Exception ex) {
            log.error("Error adding macrospazio", ex);
            addErrorMessage("Errore applicativo", "Impossibile salvare il macrospazio, contattare l'assistenza");
        }
        executeScript("reloadMacrospaziGrid();");
        // seleziona il macrospazio appena aggiunto/modificato
        getAjax().update("@(.selectedMacrospazioEventi)");
        editMacrospazio = false;
    }

    protected MuiMacrospazioMediaEntity fixCodiceMacrospazio(MuiMacrospazioMediaEntity macrospazio) {
        if (macrospazio == null || macrospazio.getCodice() == null) {
            return macrospazio;
        }
        macrospazio.setCodice(macrospazio.getCodice().trim().toUpperCase());
        if (macrospazio.getDescrizione() != null) {
            macrospazio.setDescrizione(macrospazio.getDescrizione().trim().toUpperCase());
        }
        return macrospazio;
    }

    public void editMacrospazioClicked() {
        log.info("edit macrospazio click called");
        String value = getRequestParameterMap().get("macrospazioId");

        if (value == null) {
            log.error("macrospazioId parameter is missing");
            addErrorMessage(
                    "Errore applicativo",
                    "Impossibile modificare il macrospazio, contattare l'assistenza");
            return;
        }
        Long id = Long.valueOf(value);
        MuiMacrospazioMediaEntity macrospazio = service.findById(id);
        if (macrospazio == null) {
            log.error("macrospazio with id {} not found", id);
            addErrorMessage(
                    "Errore applicativo",
                    "Impossibile modificare il macrospazio, contattare l'assistenza");
        } else {
            editMacrospazio = true;
            prepareMacrospazioDialog(macrospazio);
            log.info("macrospazio with id {} selected for edit", id);
        }
    }

    private void prepareMacrospazioDialog(MuiMacrospazioMediaEntity macrospazio) {
        macrospazioDialog = new MacrospazioDialog(macrospazio, this, eventoRetailService, utils, editMacrospazio);
    }

    /**
     * Quando si clicca sul pulsante di eliminazione del macrospazio
     * prepara il dialog di conferma eliminazione
     */
    public void deleteMacrospazioClicked() {
        log.info("delete macrospazio click called");
        String value = getRequestParameterMap().get("macrospazioId");
        if (value == null) {
            log.error("macrospazioId parameter is missing");
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare il macrospazio, contattare l'assistenza");
            return;
        }
        Long id = Long.valueOf(value);

        macrospazioToDelete = service.findById(id);
        if (macrospazioToDelete == null) {
            log.error("macrospazio with id {} not found", id);
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare il macrospazio, contattare l'assistenza");
            return;
        }
        deleteMessage = "Sei sicuro di voler eliminare il macrospazio con codice " + macrospazioToDelete.getCodice() + " ?";
    }

    /**
     * Elimina il macrospazio selezionato: evento chiamato dal dialog di conferma eliminazione
     */
    public void confirmDeleteMacrospazio() {
        log.info("delete macrospazio click called");

        if (macrospazioToDelete == null) {
            log.error("macrospazio not set before deletion");
            addErrorMessage(
                    "Errore applicativo",
                    "Impossibile eliminare il macrospazio, contattare l'assistenza");
        } else {
            Long id = macrospazioToDelete.getId();
            try {
                service.remove(macrospazioToDelete);
                macrospazi.removeIf(m -> m.getId().equals(id));
                addInfoMessage("Info", "Macrospazio eliminato correttamente");

                if (selectedMacrospazioId != null && selectedMacrospazioId.equals(id)) {
                    if (!macrospazi.isEmpty()) {
                        setSelectedMacrospazio(macrospazi.get(0));
                    } else {
                        setSelectedMacrospazio(null);
                    }
                }
                macrospazioToDelete = null;
                log.info("macrospazio with id {} deleted", id);
            } catch (Exception e) {
                log.error("Error deleting macrospazio with id {}", id, e);
                addErrorMessage("Errore applicativo",
                        "Impossibile eliminare il macrospazio, contattare l'assistenza");
            }
        }
    }

    /**
     * Annulla l'eliminazione del macrospazio selezionato: evento chiamato dal dialog di conferma eliminazione
     */
    public void abortDeleteMacrospazio() {
        macrospazioToDelete = null;
    }

    public void addRetailEventButtonClicked() {
        eventiDialog.setBean(new MuiEventoRetailEntity());
        eventiDialog.setEditing(false);
    }

    public void saveRetailEvent() {
        try {
            if ( eventiDialog.isEditing()){
                editRetailEvent();
            } else {
                addRetailEvent();
            }
            executeScript("reloadEventiGrid();");
        } catch (Exception e) {
            log.error("Error saving retail event", e);
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Errore applicativo",
                            "Impossibile salvare l'evento, contattare l'assistenza"));
        }
    }

    public void addRetailEvent() {
        MuiEventoRetailEntity evento = getEventiDialog().getBean();
        if (eventoRetailUtils.canAdd(evento)) {
            evento =
                    (MuiEventoRetailEntity)
                            eventoRetailService.persistWithAuditLog(evento, getCurrentUser().getName());
            addInfoMessage("Info", "Evento aggiunto correttamente");

            selectedEvent = evento;
            log.debug("event added to macrospazio");
        } else {
            addErrorMessage(
                    "Campi obbigatori mancanti",
                    "Causale, Fornitore, Codice, Descrizione e Valore sono obbligatori,\nle date devono essere coerenti");
            log.debug("event to added");
        }
    }

    public void editRetailEvent() {
        MuiEventoRetailEntity eventoRetail = getEventiDialog().getBean();
        MuiEventoRetailEntity original = eventoRetailService.findById(eventoRetail.getId());
        if (eventoRetailUtils.canEdit(original, eventoRetail)) {
            eventoRetail =
                    (MuiEventoRetailEntity)
                            eventoRetailService.persistWithAuditLog(
                                    eventoRetail, getCurrentUser().getName());
            addInfoMessage("Info", "Evento modificato correttamente");

            log.debug("event edited");
            selectedEvent = eventoRetail;

        } else {
            addWarningMessage(
                    "Evento non valido",
                    "Controllare la correttezza dei campi obbligatori e delle date");
            log.debug("event to added");
        }
    }

    // these are needed for the grid to interact with the view bean in order to be
    // able to select values
    public void selectEvent(Long id) {
        MuiEventoRetailEntity event = eventoRetailService.findById(id);
        if (event == null) {
            log.error("Error selecting macrospazio with id {} : it does not exist", id);
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_WARN, "Evento inesistente", "Ricaricare la pagina"));
            return;
        }
        if (getSelectedMacrospazio() != null) {
            // controlla la coerenza
            if (!event.getMacrospazio().getId().equals(getSelectedMacrospazio().getId())) {
                log.error(
                        "Error selecting macrospazio with id {} : it does not belong to macrospazio {}",
                        id,
                        getSelectedMacrospazio().getId());
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN,
                                "Evento non coerente con macrospazio selezionato",
                                "Ricaricare la pagina"));

                return;
            }
        }
        selectedEvent = event;
    }

    public void macrospazioChanged() {
        log.debug("macrospazioChanged");
    }

    public void macrospazioChanged(Long id) {
        log.debug("macrospazioChanged");
    }

    protected void updateMacrospaziList(MuiMacrospazioMediaEntity macrospazio) {
        if (macrospazi == null) {
            macrospazi = new ArrayList<>();
        }
        if (macrospazio == null || macrospazio.getId() == null) {
            log.debug("attempt to update macrospazi list with null macrospazio or a macrospazio not saved, skipping");
            return;
        }
        boolean add = true;
        for (int i = 0; i < getMacrospazi().size(); i++) {
            if (getMacrospazi().get(i).getId().equals(getSelectedMacrospazio().getId())) {
                getMacrospazi().set(i, getSelectedMacrospazio());
                add = false;
                break;
            }
        }
        if (add) macrospazi.add(macrospazio);

        macrospazi.sort(Comparator.comparing(MuiMacrospazioMediaEntity::getCodice));
    }

    public Long getIdSelectedMacrospazio() {
        if (getSelectedMacrospazio() != null) {
            return getSelectedMacrospazio().getId();
        }
        return null;
    }

    public void addEventoClicked() {
        editEvento = false;
        prepareEventoDialog(new MuiEventoRetailEntity());
    }

    public void editEventoClicked() {
        log.info("edit evento click called");
        String value = getRequestParameterMap().get("eventoId");
        editEvento = true;

        if (value == null) {
            log.error("eventoId parameter is missing");
            addErrorMessage("Errore applicativo",
                    "Impossibile modificare l'evento, contattare l'assistenza");
            return;
        }
        Long id = Long.valueOf(value);
        MuiEventoRetailEntity eventoRetail = eventoRetailService.findById(id);
        if (eventoRetail == null) {
            log.error("event with id {} not found", id);
            addErrorMessage("Errore applicativo",
                    "Impossibile modificare l'evento, contattare l'assistenza");
        } else {
            // Macrospazio is Lazy in eventoRetail, need to fetch it or the dialog will never succeed in save
            try {
                eventoRetail.getMacrospazio().getId();
                editEvento = true;
                prepareEventoDialog(eventoRetail);
                log.info("evento with id {} selected for edit", id);
            } catch (Exception e) {
                log.error("event with id {} : cannot load associated macrospazio", id);
                addErrorMessage("Errore applicativo",
                        "Impossibile caricare il macrospazio associato all'evento, contattare l'assistenza");
            }
        }
    }

    protected void prepareEventoDialog(MuiEventoRetailEntity event) {
        eventiDialog.setBean(event);
        eventiDialog.setEditing(editEvento);
        if (!editEvento) {
            eventiDialog.setMacrospazio(getSelectedMacrospazio());
        }
    }

    // default noop methods
    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }

    // for remote command parameter passing
    public void setSelectedMacrospazio() {
        String macroSpazioId = getRequestParameterMap().get("macrospazio");
        Long macrospazio = null;
        if (macroSpazioId != null) {
            try {
                macrospazio = Long.valueOf(macroSpazioId);
            } catch (NumberFormatException e) {
                log.error("Invalid macrospazio id parameter from remote command: {}", macroSpazioId, e);
            }
        }
        setSelectedMacrospazioId(macrospazio);
    }

    // in case the dropdown passes the id directly
    public void setSelectedMacrospazioId(Long macrospazio) {
        MuiMacrospazioMediaEntity m = null;
        if (macrospazio != null) {
            m = service.findById(macrospazio);
        }
        setSelectedMacrospazio(m);
    }

    // this shoud be the nice way
    public void setSelectedMacrospazio(MuiMacrospazioMediaEntity macrospazio) {
        this.selectedMacrospazio = macrospazio;
        this.selectedMacrospazioId = macrospazio != null ? macrospazio.getId() : null;
        if (this.selectedMacrospazioId != null) {
            dummyLong = this.selectedMacrospazioId;
        } else {
            dummyLong = -1L;
        }
        executeScript("reloadEventiGrid()");
        log.debug("passed macrospazio object {}", (macrospazio == null ? "null" : ""));
    }

    public void deleteEventoClicked() {
        log.info("delete evento click called");
        String value = getRequestParameterMap().get("eventoId");

        if (value == null) {
            log.error("eventoId parameter is missing");
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare il evento, contattare l'assistenza");
            return;
        }
        Long id = Long.valueOf(value);

        eventoToDelete = eventoRetailService.findById(id);
        if (eventoToDelete == null) {
            log.error("evento with id {} not found", id);
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare il evento, contattare l'assistenza");
            return;
        }
        EventoRetailDTO dto = eventoRetailService.findEagerById(id);
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtils.ITALIAN_DATE_PATTERN);
        deleteEventoMessage = "Sei sicuro di voler eliminare l'evento\n" +
                "dal " + sdf.format(dto.getDataInizio()) + " al " + sdf.format(dto.getDataFine()) + "\n"
                + "con causale " + dto.getCausaleDescription() + " e fornitore " + dto.getFornitoreDescription() + " ?";
    }

    public void confirmDeleteEvento() {
        log.info("delete evento  called");
        FacesMessage message = null;
        if (eventoToDelete == null) {
            log.error("macrospazio not set before deletion");
            message =
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Errore applicativo",
                            "Impossibile eliminare l'evento, contattare l'assistenza");
        } else {
            eventoRetailService.remove(eventoToDelete);
            // reset the evento to delete
            eventoToDelete = null;
            message =
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Info", "Evento eliminato correttamente");
        }
        dummyLong = getSelectedMacrospazioId();
        addMessage(null, message);
    }

    public void confirmMacrospaziClicked() {
        if (confirmRunning) {
            log.debug("procedure already running, skipping");
            return;
        }
        confirmRunning = true;
        getAjax().update("@(.confirmRetailMediaEvents)");
        FacesMessage message = confirmMacrospazi();
        confirmRunning = false;
        getAjax().update("@(.confirmRetailMediaEvents)");
        addMessage(null, message);
    }

    protected FacesMessage confirmMacrospazi() {
        FacesMessage message = null;
        try {
            service.confermaMacrospazi(getCurrentUser().getName());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                    "La conferma delle operazioni è andata a buon fine.");
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "La conferma delle operazioni non è andata a buon fine. Contattare l'assistenza.");
            log.error("Error confirming macrospazi media events", e);
        }

        return message;
    }

    public void onTabChange(TabChangeEvent event) {
        String tabId = event.getTab().getTitle();
        if (tabId != null) {
            int index=tabTitles.indexOf(tabId);
            if ( index == -1 || index > 1) {
                activeTab = 0;
            } else {
                activeTab = index;
            }
        }
        setSelectedMacrospazio(null);
        log.debug("tab changed to {}", activeTab);
    }

    public String getTabTile(int index) {
        if ( index <0 || index >= tabTitles.size()) {
            return "";
        }
        return tabTitles.get(index);
    }
}
