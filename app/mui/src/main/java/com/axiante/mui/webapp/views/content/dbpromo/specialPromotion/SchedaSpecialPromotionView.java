package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatoService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpTestataService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.enumeration.TabSpecialPromotionEnum;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatiTransizioneService;

@MuiViewModel("schedaSpecialPromotionView")
@Dependent
@Slf4j
public class SchedaSpecialPromotionView extends AbstractDBPromoNavigation {

    private static final long serialVersionUID = 1L;

    @Inject
    private MuiSpTestataService muiSpTestataService;

    @Setter
    @Getter
    private MuiSpTestataEntity specialPromotionSelected;

    @Inject
    private MuiSpStatoService muiSpStatoService;



    @Getter
    private MuiSpStatoEntity statoCorrente;

    @Getter
    private List<MuiSpTestataEntity> specialPromotions;

    @Getter
    private MuiSpTestataEntity specialPromotionCorrente;

    @Getter
    private Object idSpecialPromotionCorrente;

    @Inject
    @Getter
    private ModificaSpecialPromotionBackingBean editTestata;

    @Inject
    @Getter
    private StatiSpecialPromotionBackingBean statiBean;

    @Inject
    @Getter
    private StatoCompratoreBackingBean statoCompratoreBackingBean;

    @Getter
    @Setter
    private Integer activeTab = 0;

    @Getter
    private boolean tabModificaRendered = false;

    @Getter
    private boolean tabStatiRendered = false;

    @Getter
    private boolean tabPubblicazioniRendered = false;

    @Getter
    private boolean tabRepartiAttiviRendered = false;

    @Getter
    private final List<TabSpecialPromotionEnum> renderedTabs = new ArrayList<>();

    private List<TabItem> tabs;

    @Getter
    private List<TabItem> renderedTabItems;

    @Getter
    @Setter
    private Long idStatoTransizioneCorrente;

    @Getter
    private List<StatoPromozioneEntity> statiTransizione = new ArrayList<>();

    @Inject
    @Getter
    private RepartiAttiviSpecialPromotionBackingBean repartiAttiviBean;

    @Inject
    private MuiSpStatiTransizioneService muiSpStatiTransizioneService;

    private Long currentIdMenu = 0L;

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        retrieveCurrentIdMenu(node.getParams());
        applyDefaultRules();
        init();
    }

    private void retrieveCurrentIdMenu(Map<String, List<String>> params) {
        String idMenu = params.get("idMenu") != null ? params.get("idMenu").get(0) : null;
        if (idMenu != null) {
            currentIdMenu = Long.valueOf(idMenu);
        } else {
            log.error("Cannot retrieve idMenu from params: {}", params);
        }
    }

    public void init() {

        createTabs();
        activeTab = 0;

        this.editTestata = new ModificaSpecialPromotionBackingBean();
        this.statiBean = new StatiSpecialPromotionBackingBean();

        loadSpecialPromotions();
        loadSpecialPromotionAttiva();

        applyRules();
        applyTabsPosition();
    }

    public int indexOf(String tabName) {
        return renderedTabs.indexOf(TabSpecialPromotionEnum.valueOf(tabName));
    }

    private void loadSpecialPromotionAttiva() {
        MuiSpTestataEntity testataAttiva = muiSpTestataService.findActive();

        if (testataAttiva == null) {
            setSpecialPromotionCorrente(null);
            this.statoCorrente = null;
            return;
        }

        MuiSpTestataEntity testataPerHeader =
                muiSpTestataService.findByIdForHeader(testataAttiva.getId());

        MuiSpTestataEntity testataFinale =
                testataPerHeader != null ? testataPerHeader : testataAttiva;

        setSpecialPromotionCorrente(testataFinale);
        loadHeaderState(testataFinale);
    }

    public void setIdSpecialPromotionCorrente(Object idSpecialPromotionCorrente) {
        this.idSpecialPromotionCorrente = idSpecialPromotionCorrente;

        if (idSpecialPromotionCorrente != null) {
            try {
                Long id = Long.valueOf(idSpecialPromotionCorrente.toString());

                MuiSpTestataEntity testata =
                        muiSpTestataService.findById(id);
                setSpecialPromotionCorrente(testata);
                MuiSpTestataEntity testataHeader = muiSpTestataService.findByIdForHeader(id);
                loadHeaderState(testataHeader != null ? testataHeader : testata);

            } catch (Exception e) {
                log.error("Errore nel recupero della Special Promotion con id {}", idSpecialPromotionCorrente, e);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore nel recupero della Special Promotion"));
            }
        } else {
            setSpecialPromotionSelected(null);
            this.specialPromotionCorrente = null;
            this.statoCorrente = null;
            if (repartiAttiviBean != null) {
                repartiAttiviBean.loadData(null);
            }
            if (statoCompratoreBackingBean != null) {
                statoCompratoreBackingBean.clear();
            }

            updateEditTestata(null);
            disableEditing();
        }
    }

    private void setSpecialPromotionCorrente(MuiSpTestataEntity testata) {
        try {
            setSpecialPromotionSelected(testata);
            this.specialPromotionCorrente = testata;
            this.idSpecialPromotionCorrente = testata != null ? testata.getId() : null;

            if (repartiAttiviBean != null) {
                repartiAttiviBean.loadData(testata != null ? testata.getId() : null);
            }

            updateEditTestata(testata);
            updateStatiBean(testata);

            disableEditing();

            applyRules();
            applyTabsPosition();

        } catch (Exception e) {
            log.error("Errore nel set della Special Promotion corrente", e);
        }
    }

    private void updateEditTestata(MuiSpTestataEntity testata) {
        if (editTestata == null) {
            editTestata = new ModificaSpecialPromotionBackingBean();
        }

        if (testata == null) {
            editTestata.setDescription(null);
            editTestata.setStartDate(null);
            editTestata.setEndDate(null);
            editTestata.setReminderDate(null);
        } else {
            editTestata.setDescription(testata.getDescrizione());
            editTestata.setStartDate(testata.getDataInizio());
            editTestata.setEndDate(testata.getDataFine());
            editTestata.setReminderDate(testata.getDataReminderInsParam());
        }
    }

    public void unlockEditTestata() {
        if (editTestata != null && editTestata.isLocked()) {
            editTestata.setLocked(false);
        }
    }

    public void discardEditTestata() {
        updateEditTestata(getSpecialPromotionSelected());
        disableEditing();
    }

    public void saveEditTestata() {
        try {

            if (editTestata.getStartDate() != null && editTestata.getEndDate() != null &&
                    editTestata.getStartDate().after(editTestata.getEndDate())) {

                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Errore validazione",
                        "La Data Inizio non può essere successiva alla Data Fine"));

                return;
            }

            if (editTestata.getReminderDate() != null && editTestata.getEndDate() != null
                    && editTestata.getReminderDate().after(editTestata.getEndDate())) {

                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Errore validazione",
                        "La Data Reminder non può essere successiva alla Data Fine"));

                return;
            }

            if (getSpecialPromotionSelected() == null || getSpecialPromotionSelected().getId() == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Nessuna Special Promotion attiva selezionata"));
                return;
            }

            MuiSpTestataEntity entity =
                    muiSpTestataService.findById(getSpecialPromotionSelected().getId());

            entity.setDescrizione(editTestata.getDescription());
            entity.setDataInizio(editTestata.getStartDate());
            entity.setDataFine(editTestata.getEndDate());
            entity.setDataReminderInsParam(editTestata.getReminderDate());

            entity = muiSpTestataService.update(entity);

            setSpecialPromotionCorrente(entity);

            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Testata aggiornata", null));

            disableEditing();

        } catch (Exception ex) {
            log.warn("Error saving edited MuiSpTestataEntity", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
                    "Impossibile aggiornare la Special Promotion"));
        }
    }

    private void disableEditing() {
        if (editTestata != null) {
            editTestata.setLocked(true);
        }
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTab = tv.getChildren().indexOf(event.getTab());
    }

    private void applyTabsPosition() {
        renderedTabs.clear();

        if (tabRepartiAttiviRendered) renderedTabs.add(TabSpecialPromotionEnum.TAB_REPARTI_ATTIVI);
        if (tabModificaRendered) renderedTabs.add(TabSpecialPromotionEnum.TAB_MODIFICA);
        if (tabStatiRendered) renderedTabs.add(TabSpecialPromotionEnum.TAB_STATI);
        if (tabPubblicazioniRendered) renderedTabs.add(TabSpecialPromotionEnum.TAB_STATO_COMPRATORE);

        renderedTabItems = renderedTabs.stream()
                .map(this::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void applyRules() {
        applyDefaultRules();
        tabModificaRendered = true;
        tabStatiRendered = true;
        tabPubblicazioniRendered = true;
        tabRepartiAttiviRendered = true;
    }

    @Override
    public void applyDefaultRules() {
        tabModificaRendered = false;
        tabStatiRendered = false;
        tabPubblicazioniRendered = false;
        tabRepartiAttiviRendered = false;
    }

    public void createTabs() {
        tabs = new ArrayList<>();
        tabs.add(new TabItem("Reparti Attivi", "tabs/repartiAttivi.xhtml", TabSpecialPromotionEnum.TAB_REPARTI_ATTIVI));
        tabs.add(new TabItem("Modifica", "tabs/modifica.xhtml", TabSpecialPromotionEnum.TAB_MODIFICA));
        tabs.add(new TabItem("Stati", "tabs/stati.xhtml", TabSpecialPromotionEnum.TAB_STATI));
        tabs.add(new TabItem("Stato Compratore", "tabs/stato_compratore.xhtml", TabSpecialPromotionEnum.TAB_STATO_COMPRATORE));
    }

    private TabItem findByName(TabSpecialPromotionEnum name) {
        return tabs.stream().filter(t -> t.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void updateView() {}

    public class TabItem {
        @Getter
        String title;

        @Getter
        String content;

        @Getter
        TabSpecialPromotionEnum name;

        public TabItem(@NonNull String title, @NonNull String content, @NonNull TabSpecialPromotionEnum name) {
            this.title = title;
            this.content = content;
            this.name = name;
        }
    }

    public TabSpecialPromotionEnum getTabStati() {
        return TabSpecialPromotionEnum.TAB_STATI;
    }

    private void updateStatiBean(MuiSpTestataEntity testata) {
        if (statiBean == null) {
            statiBean = new StatiSpecialPromotionBackingBean();
        }

        if (testata == null || statoCorrente == null || statoCorrente.getStatoPromozioneEntity() == null) {
            statiBean.setStato(null);
            statiBean.setDataInizioStato(null);
            statiBean.setDataFineStato(null);
            statiBean.setUtente(null);
            return;
        }

        statiBean.setStato(
                statoCorrente.getStatoPromozioneEntity().getCodiceStato() + " - " +
                        statoCorrente.getStatoPromozioneEntity().getDescrizione()
        );

        statiBean.setDataInizioStato(statoCorrente.getDataInizioStato());
        statiBean.setDataFineStato(statoCorrente.getDataFineStato());
        statiBean.setUtente(statoCorrente.getCodiceUtenteInserimento());
    }

    public void deleteTestata() {
        try {
            if (getSpecialPromotionSelected() == null || getSpecialPromotionSelected().getId() == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Nessuna Special Promotion selezionata"));
                return;
            }

            Long idPromozione = getSpecialPromotionSelected().getId();
            String username = getCurrentUser().getName();

            muiSpStatoService.setAttivazioneTestata(idPromozione, 0, username);
            muiSpStatoService.closeCurrentAndInsertNewState(idPromozione, 0L, username);

            MuiSpTestataEntity testata = muiSpTestataService.findById(idPromozione);
            setSpecialPromotionCorrente(testata);

            MuiSpTestataEntity testataHeader = muiSpTestataService.findByIdForHeader(idPromozione);
            loadHeaderState(testataHeader != null ? testataHeader : testata);

            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Special Promotion cancellata", null));

        } catch (Exception ex) {
            log.error("Errore in cancellazione logica MuiSpTestataEntity", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore",
                    "Impossibile cancellare la Special Promotion"));
        }
    }

    private void loadHeaderState(MuiSpTestataEntity testata) {
        if (testata != null && testata.getSpStatoEntities() != null) {
            this.statoCorrente = testata.getSpStatoEntities().stream()
                    .filter(st -> st.getDataFineStato() == null)
                    .findFirst()
                    .orElse(null);
        } else {
            this.statoCorrente = null;
        }
        if (statoCompratoreBackingBean != null) {
            statoCompratoreBackingBean.loadData(this.specialPromotionCorrente, this.statoCorrente);
        }

        updateStatiBean(testata);
    }

    private void loadSpecialPromotions() {
        specialPromotions = muiSpTestataService.findAllForVisualizza();
    }

    public void insertNewStatus() {

        if (specialPromotionCorrente == null || idStatoTransizioneCorrente == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Dati mancanti"));
            return;
        }

        try {
            Long idPromozione = Long.valueOf(idSpecialPromotionCorrente.toString());
            Date now = new Date();

            MuiSpTestataEntity testata = muiSpTestataService.findById(idPromozione);
            if (testata == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Special Promotion non trovata"));
                return;
            }

            MuiSpStatoEntity statoAttuale = muiSpStatoService.findCurrentByPromozioneId(idPromozione);
            if (statoAttuale != null) {
                statoAttuale.setDataFineStato(now);
                statoAttuale.setDataAggiornamento(now);
                statoAttuale.setCodiceUtenteAggiornamento(getCurrentUser().getName());
                muiSpStatoService.update(statoAttuale);
            }

            StatoPromozioneEntity statoSelezionato =
                    muiSpStatoService.findStatoPromozioneById(idStatoTransizioneCorrente);

            if (statoSelezionato == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Stato selezionato non trovato"));
                return;
            }

            MuiSpStatoEntity nuovo = new MuiSpStatoEntity();
            nuovo.setSpTestataEntity(testata);
            nuovo.setStatoPromozioneEntity(statoSelezionato);
            nuovo.setDataInizioStato(now);
            nuovo.setDataFineStato(null);
            nuovo.setDataInserimento(now);
            nuovo.setCodiceUtenteInserimento(getCurrentUser().getName());
            nuovo.setUuid(com.axiante.mui.common.utility.AxUUID.randomUUID().toString());

            muiSpStatoService.save(nuovo);

            MuiSpTestataEntity testataHeader = muiSpTestataService.findByIdForHeader(idPromozione);
            loadHeaderState(testataHeader != null ? testataHeader : testata);

            idStatoTransizioneCorrente = null;

            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    "Stato aggiornato correttamente"));

        } catch (Exception e) {
            log.error("Errore nel cambio stato della Special Promotion", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile aggiornare lo stato"));
        }
    }

    public void prepareGestioneStato() {
        loadStatiTransizione();
    }

    private void loadStatiTransizione() {
        if (statoCorrente == null || statoCorrente.getStatoPromozioneEntity() == null || idSpecialPromotionCorrente == null) {
            statiTransizione = new ArrayList<StatoPromozioneEntity>();
            return;
        }

        Long idPromozione = Long.valueOf(idSpecialPromotionCorrente.toString());
        Long idStato = statoCorrente.getStatoPromozioneEntity().getId();

        List<MuiSpStatiTransizioneEntity> transizioni =
                muiSpStatiTransizioneService.findByPromozioneAndFromStato(idPromozione, idStato);

        statiTransizione = transizioni.stream()
                .map(MuiSpStatiTransizioneEntity::getStatoTransizione)
                .collect(Collectors.toList());
    }

    public void confirmCambioStato() {

        if (specialPromotionCorrente == null || idStatoTransizioneCorrente == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Seleziona uno stato"));
            return;
        }

        try {
            Long idPromozione = specialPromotionCorrente.getId();
            String username = getCurrentUser().getName();

            muiSpStatoService.closeCurrentAndInsertNewState(
                    idPromozione,
                    idStatoTransizioneCorrente,
                    username
            );

            MuiSpTestataEntity testata = muiSpTestataService.findById(idPromozione);
            setSpecialPromotionCorrente(testata);

            MuiSpTestataEntity testataHeader = muiSpTestataService.findByIdForHeader(idPromozione);
            loadHeaderState(testataHeader != null ? testataHeader : testata);

            idStatoTransizioneCorrente = null;

            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Stato aggiornato", null));

        } catch (Exception e) {
            log.error("Errore cambio stato", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Errore", "Impossibile aggiornare lo stato"));
        }
    }

    @Override
    public void setIdPromozioneCorrente(Object idPromozioneCorrente) {
        this.idPromozioneCorrente = idPromozioneCorrente;
        setIdSpecialPromotionCorrente(idPromozioneCorrente);
    }
}