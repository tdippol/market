package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.*;
import com.axiante.mui.dbpromo.persistence.service.*;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.FatturazioneUtil;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.dtos.RataDto;
import com.axiante.mui.webapp.views.content.enumeration.TipologiaArticoliEnum;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("schedaFatturazione")
@Dependent
@Slf4j
public class SchedaFatturazioneView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -855926989945142858L;

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<FatturazioneUtil> fatturazioneUtilInstance;

    @Inject
    private Instance<FatturazioneService> fatturazioneServiceInstance;

    @Inject
    private Instance<PrestazioniService> prestazioniServiceInstance;

    @Inject
    private Instance<StatoContributiService> contributiServiceInstance;

    @Inject
    private Instance<ItemService> itemServiceInstance;

    @Getter
    private String dlgDeleteRataMessage = null;

    @Getter
    private boolean editingEnabled = false;

    @Getter
    private Long idPromozioneSelected = null;

    @Getter
    private Long idRataSelected = null;

    @Getter
    @Setter
    private String codicePrestazioneSelected = null;

    @Getter
    @Setter
    private Date dataLiquidazione = null;

    @Getter
    private Date minDataLiquidazione = null;

    @Getter
    @Setter
    private Double valore = null;

    @Getter
    private Double saldo = null;

    @Getter
    @Setter
    private String noteCompratore = null;

    @Getter
    private String noteFatturazione = null;

    @Getter
    private String statoRata = null;

    @Getter
    private List<PromozioneTestataEntity> promozioni;

    @Getter
    private List<RataDto> rate;

    @Getter
    private List<PrestazioniEntity> prestazioni;

    @Getter
    private PromozioneTestataEntity promozioneSelected;

    @Getter
    private List<Long> itemsIdSelected = new ArrayList<>();

    @Getter
    private TipologiaArticoliEnum tipologiaArticoliSelected = null;

    @Getter
    private List<TipologiaArticoliEnum> tipologiaArticoli;

    @Getter
    private boolean btnConfirmAddArticoliDisabled = true;

    @Getter
    private boolean autoreloadGrid = false;

    private UserDTO userDTO;
    private DateTimeUtils dateTimeUtils;
    private RataDto rataSelected;
    private List<ContributiStatoAnagraficaEntity> statiContributo;
    private List<Long> addItemsIdSelected = new ArrayList<>();

    public void init() {
        userDTO = getUserDto();
        dateTimeUtils = new DateTimeUtils();
        readPromozioni();
        autoSelectFirstPromozione();
        readPrestazioni();
        readStatiContributo();
        readTipologiaArticoli();
    }

    public void setIdPromozioneAndIdRata(Long idPromozione, Long idRata) {
        setIdPromozioneSelected(idPromozione);
        setIdRata(idRata, false);
    }

    private void setIdRata(Long idRata, Boolean loadGrid) {
        if (loadGrid == null) {
            loadGrid = false;
        }
        autoreloadGrid = !loadGrid;
        this.idRataSelected = idRata;
        if (rate != null && !rate.isEmpty() && idRataSelected != null) {
            rataSelected = getRataSelectedFromId(idRataSelected);
        } else {
            rataSelected = null;
        }
        if (rataSelected != null) {
            codicePrestazioneSelected = rataSelected.getCodicePrestazione();
            dataLiquidazione = rataSelected.getDataLiquidazione();
            minDataLiquidazione = calculateMinDataLiquidazione();
            valore = rataSelected.getValore();
            noteCompratore = rataSelected.getNoteCompratore();
            noteFatturazione = rataSelected.getNoteFattura();
            statoRata = lookupStatoContributo(rataSelected.getCodiceStato());
            saldo = rataSelected.getSaldo();
            if (loadGrid) {
                loadGrid();
            }
        } else {
            codicePrestazioneSelected = null;
            dataLiquidazione = null;
            minDataLiquidazione = null;
            valore = null;
            noteCompratore = null;
            noteFatturazione = null;
            statoRata = null;
            saldo = null;
        }
        updateEditingEnabled();
    }

    public void setIdPromozioneSelected(Long idPromozioneSelected) {
        if (!idPromozioneSelected.equals(this.idPromozioneSelected) && !itemsIdSelected.isEmpty()) {
            itemsIdSelected.clear();
        }
        this.idPromozioneSelected = idPromozioneSelected;
        if (promozioni != null && !promozioni.isEmpty() && idPromozioneSelected != null) {
            promozioneSelected = getPromozioneSelectedFromId(idPromozioneSelected);
            readRate();
        } else {
            promozioneSelected = null;
        }
        setIdRataSelected(null);
    }

    public void setIdRataSelected(Long idRataSelected) {
        setIdRata(idRataSelected, true);
    }

    public void itemSelected() {
        itemsIdSelected.clear();
        final String itemsIdsParam = getRequestParameterMap().get("itemsIds");
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemsIdsParam);
            if (jsonNode != null) {
                jsonNode.forEach(n -> itemsIdSelected.add(Long.parseLong(n.asText())));
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile recuperare lista articoli da eliminare"));
            }
        } catch (IOException ex) {
            log.error("Error reading selected items", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile recuperare lista articoli da eliminare"));
        }
    }

    public void prepareDialogAddArticoli() {
        resetDialogAddArticoli();
        if (tipologiaArticoli != null && !tipologiaArticoli.isEmpty()) {
            setTipologiaArticoliSelected(TipologiaArticoliEnum.OWN);
        }
    }

    public void prepareDialogDeleteRata() {
        if (rataSelected != null) {
            dlgDeleteRataMessage = String.format("Sei sicuro di voler eliminare la rata %s?", rataSelected.getLabel());
            executeScript("PF('confirmDlgDeleteRata').show()");
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile eliminare la rata selezionata"));
        }
    }

    public void deleteRata() {
        updateEditingEnabled();
        if (editingEnabled && promozioneSelected != null && rataSelected != null) {
            try {
                promozioneSelected.removeContributo(rataSelected.getEntity());
                promozioneSelected = promoServiceInstance.get().persist(promozioneSelected);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                        String.format("Rata %s eliminata dalla promozione %s", rataSelected.getLabel(),
                                promozioneSelected.getDescrizione())));
                updatePromozioniAndRate();
            } catch (Exception ex) {
                log.error(String.format("Error removing rata with id %d from promozione with id %d",
                        rataSelected.getId(), promozioneSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile eliminare la rata %s", rataSelected.getLabel())));
            }
        }
    }

    public void prepareDialogDeleteArticoli() {
        if (editingEnabled && promozioneSelected != null && rataSelected != null && itemsIdSelected != null && !itemsIdSelected.isEmpty()) {
            final String js = itemsIdSelected.size() < rataSelected.getEntity().getArticoli().size()
                    ? "PF('confirmDlgDeleteArticoli').show()"
                    : "PF('errorDlgDeleteArticoli').show()";
            executeScript(js);
        }
    }

    public void cancelDlgDeleteArticoli() {
        itemsIdSelected.clear();
        if (editingEnabled && promozioneSelected != null && rataSelected != null) {
            setIdRataSelected(rataSelected.getId());
        }
    }

    public void deleteArticoli() {
        updateEditingEnabled();
        if (editingEnabled && promozioneSelected != null && rataSelected != null
                && itemsIdSelected != null && !itemsIdSelected.isEmpty()
                && itemsIdSelected.size() < rataSelected.getEntity().getArticoli().size()) {
            try {
                final FatturazioneService fatturazioneService = fatturazioneServiceInstance.get();
                fatturazioneService.findAllContributiArticoliByIds(itemsIdSelected)
                        .forEach(a -> rataSelected.getEntity().removeArticolo(a));
                ContributiPromoEntity updated = fatturazioneService.persist(rataSelected.getEntity());
                rataSelected.setEntity(updated);
                itemsIdSelected.clear();
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                        "Articoli eliminati dalla rata selezionata"));
                setIdRataSelected(rataSelected.getId());
            } catch (Exception ex) {
                log.error(String.format("Error removing selected items (%s) from rata with id %d",
                        itemsIdSelected.stream().map(String::valueOf).collect(Collectors.joining(";")),
                        rataSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile eliminare articoli dalla rata selezionata"));
            }
        }
    }

    public void setTipologiaArticoliSelected(TipologiaArticoliEnum tipologiaArticoliSelected) {
        this.tipologiaArticoliSelected = tipologiaArticoliSelected;
        if (rataSelected != null && this.tipologiaArticoliSelected != null) {
            try {
                loadGridAddArticoli(rataSelected.getEntity(), tipologiaArticoliSelected.getValue());
            } catch (Exception ex) {
                log.error(String.format("Error loading items for rata %s", rataSelected.getLabel()), ex);
            }
        }
        addItemsIdSelected.clear();
        updateBtnConfirmAddArticoli();
    }

    public void resetDialogAddArticoli() {
        addItemsIdSelected.clear();
        updateBtnConfirmAddArticoli();
    }

    public void confirmDialogAddArticoli() {
        if (validateAddArticoli()) {
            try {
                saveContributo();
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("%d %s alla rata di fatturazione", addItemsIdSelected.size(),
                                (addItemsIdSelected.size() == 1 ? "articolo aggiunto" : "articoli aggiunti"))));
                readRate();
                setIdRataSelected(rataSelected.getId());
            } catch (Exception ex) {
                log.error(String.format("Error adding items to rata %d", rataSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile aggiungere articoli alla rata di fatturazione"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile aggiungere articoli alla rata di fatturazione"));
        }
        resetDialogAddArticoli();
    }

    public void addItemSelected() {
        addItemsIdSelected.clear();
        final String itemsIdsParam = getRequestParameterMap().get("itemsIds");
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemsIdsParam);
            if (jsonNode != null) {
                jsonNode.forEach(n -> addItemsIdSelected.add(Long.parseLong(n.asText())));
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile recuperare lista articoli selezionati"));
            }
        } catch (IOException ex) {
            log.error("Error reading selected items", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile recuperare lista articoli selezionati"));
        }
        updateBtnConfirmAddArticoli();
    }

    public void changeTipoPrestazione() {
        if (codicePrestazioneSelected != null && rataSelected != null
                && !codicePrestazioneSelected.equals(rataSelected.getCodicePrestazione())) {
            try {
                rataSelected.getEntity().setCodicePrestazione(codicePrestazioneSelected);
                ContributiPromoEntity updated = fatturazioneServiceInstance.get().persist(rataSelected.getEntity());
                rataSelected.setEntity(updated);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("Aggiornato Codice Prestazione %s sulla rata %s",
                                codicePrestazioneSelected, rataSelected.getLabel())));
            } catch (Exception ex) {
                log.error(String.format("Error updating 'codicePrestazione' %s to rata %d",
                        codicePrestazioneSelected, rataSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile aggiornare il Codice Prestazione %s sulla rata %s",
                                codicePrestazioneSelected, rataSelected.getLabel())));
            }
        }
    }

    public void changeDataLiquidazione() {
        if (rataSelected != null && validateDataLiquidazione(dataLiquidazione, rataSelected.getDataLiquidazione())) {
            final String date = new SimpleDateFormat("dd-MM-yyy").format(dataLiquidazione);
            try {
                rataSelected.getEntity().setDataLiquidazione(dataLiquidazione);
                ContributiPromoEntity updated = fatturazioneServiceInstance.get().persist(rataSelected.getEntity());
                rataSelected.setEntity(updated);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("Aggiornato Data Liquidazione %s sulla rata %s",
                                date, rataSelected.getLabel())));
            } catch (Exception ex) {
                log.error(String.format("Error updating 'dataLiquidazione' %s to rata %d",
                        date, rataSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile aggiornare la Data Liquidazione %s sulla rata %s",
                                date, rataSelected.getLabel())));
            }
        }
    }

    public void changeNoteCompratore() {
        if (rataSelected != null && noteCompratore != null
                && !noteCompratore.equals(rataSelected.getNoteCompratore())) {
            try {
                rataSelected.getEntity().setNotaCompratore(noteCompratore);
                ContributiPromoEntity updated = fatturazioneServiceInstance.get().persist(rataSelected.getEntity());
                rataSelected.setEntity(updated);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("Aggiornato Note Compratore %s sulla rata %s",
                                noteCompratore, rataSelected.getLabel())));
            } catch (Exception ex) {
                log.error(String.format("Error updating 'noteCompratore' %s to rata %d",
                        noteCompratore, rataSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile aggiornare Note Compratore %s sulla rata %s",
                                noteCompratore, rataSelected.getLabel())));
            }
        }
    }

    public void changeValore() {
        if (rataSelected != null && valore != null && !valore.equals(rataSelected.getValore())) {
            try {
                rataSelected.getEntity().setValoreApplicato(valore);
                ContributiPromoEntity updated = fatturazioneServiceInstance.get().persist(rataSelected.getEntity());
                rataSelected.setEntity(updated);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        String.format("Aggiornato Valore %.2f sulla rata %s", valore, rataSelected.getLabel())));
            } catch (Exception ex) {
                log.error(String.format("Error updating 'valore' %.2f to rata %d", valore, rataSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Impossibile aggiornare Valore %.2f sulla rata %s",
                                valore, rataSelected.getLabel())));
            }
        }
    }

    private void readPromozioni() {
        try {
            UserFilterUtils userFilterUtils = new UserFilterUtils();
            final Map<String, String> filters = userFilterUtils.createUserFiltersMapToQueryString(
                    userDTO.getUser().getDbFilters());
            promozioni = promoServiceInstance.get().findAllNotCancelled(filters, userDTO.getCanali()).stream()
                    .filter(p -> !p.getContributiPromozione().isEmpty())
                    .sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error reading promozioni", ex);
            promozioni = new ArrayList<>();
        }
    }

    private void readRate() {
        if (promozioneSelected == null) {
            rate = new ArrayList<>();
        } else {
            try {
                final Comparator<ContributiPromoEntity> sorted = Comparator.comparing(
                                (ContributiPromoEntity c) -> c.getCompratore().getCodiceCompratore())
                        .thenComparing(c -> c.getFornitore().getCodiceFornitore())
                        .thenComparing(ContributiPromoEntity::getProgressivo);
                rate = fatturazioneServiceInstance.get().findAllByPromozione(promozioneSelected).stream()
                        .sorted(sorted)
                        .map(RataDto::new)
                        .collect(Collectors.toList());
            } catch (Exception ex) {
                log.error(String.format("Error reading rate for promozione %d", promozioneSelected.getId()), ex);
                rate = new ArrayList<>();
            }
        }
    }

    private void readPrestazioni() {
        try {
            prestazioni = prestazioniServiceInstance.get().findAll().stream()
                    .sorted(Comparator.comparing(PrestazioniEntity::getDescrizionePrestazione))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error reading prestazioni", ex);
            prestazioni = new ArrayList<>();
        }
    }

    private void readStatiContributo() {
        try {
            statiContributo = contributiServiceInstance.get().findAll();
        } catch (Exception ex) {
            log.error("Error reading stati contributo", ex);
            statiContributo = new ArrayList<>();
        }
    }

    private void autoSelectFirstPromozione() {
        if (promozioni != null && !promozioni.isEmpty()) {
            setIdPromozioneSelected(promozioni.get(0).getId());
        }
    }

    private PromozioneTestataEntity getPromozioneSelectedFromId(@NonNull Long id) {
        return promozioni.stream().filter(p -> id.equals(p.getId())).findFirst().orElse(null);
    }

    private RataDto getRataSelectedFromId(@NonNull Long id) {
        return rate.stream().filter(r -> id.equals(r.getId())).findFirst().orElse(null);
    }

    private String lookupStatoContributo(String codiceContributo) {
        if (codiceContributo == null) {
            return "";
        }
        final ContributiStatoAnagraficaEntity statoContributo = statiContributo.stream()
                .filter(s -> codiceContributo.equals(s.getCodiceStatoContributo())).findFirst().orElse(null);
        return statoContributo == null ? "" : statoContributo.getDescrizioneStatoContributo();
    }

    private void updateEditingEnabled() {
        // Devo avere una rata selezionata nella dropdown
        final Date today = dateTimeUtils.getDateWithoutTime(new Date());
        editingEnabled = rataSelected != null
                // la data attuale deve essere minore o uguale alla data di liquidazione
                && dateTimeUtils.isBefore(today, rataSelected.getDataLiquidazione(), false)
                // sono admin o ho i permessi per editare
                // - owner: sicurezza in lettura sui compratori
                // - non owner: sicurezza in scrittura sui compratori
                && (userDTO.isAdmin()
                || fatturazioneUtilInstance.get().isEditable(rataSelected.getEntity(), userDTO.getGruppi())
        );
    }

    private void updatePromozioniAndRate() {
        readPromozioni();
        final List<Long> ids = promozioni.stream().map(PromozioneTestataEntity::getId).collect(Collectors.toList());
        if (ids.contains(promozioneSelected.getId())) {
            readRate();
            final ContributiPromoEntity nextRata = promozioneSelected.getContributiPromozione().stream().findFirst().orElse(null);
            setIdRataSelected(nextRata != null ? nextRata.getId() : null);
        } else {
            // Non ho altre rate sulla promozione selezionata, seleziono la prima disponibile
            autoSelectFirstPromozione();
            setIdRataSelected(null);
            readRate();
        }
    }

    private void loadGrid() {
        executeScript(String.format("loadGridFatturazioneDettaglioArticoli(%d, %d)",
                promozioneSelected.getId(), rataSelected.getId()));
    }

    private void readTipologiaArticoli() {
        tipologiaArticoli = new ArrayList<>();
        tipologiaArticoli.add(TipologiaArticoliEnum.OWN);
        tipologiaArticoli.add(TipologiaArticoliEnum.ALL);
    }

    private void loadGridAddArticoli(ContributiPromoEntity entity, String tipologiaArticoli) {
        executeScript(String.format("loadGridFatturazioneAddArticoli(%d, '%s', '%s', '%s')",
                entity.getPromozione().getId(), entity.getCompratore().getCodiceCompratore(),
                entity.getFornitore().getCodiceFornitore(), tipologiaArticoli));
    }

    private void updateBtnConfirmAddArticoli() {
        btnConfirmAddArticoliDisabled = tipologiaArticoliSelected == null || addItemsIdSelected == null
                || addItemsIdSelected.isEmpty();
    }

    private void saveContributo() {
        // Per ogni articolo selezionato creo una riga di dettaglio
        itemServiceInstance.get().findByIds(addItemsIdSelected).forEach(item -> {
            ContributiPromoArticoloEntity contributoArticolo = new ContributiPromoArticoloEntity();
            contributoArticolo.setItem(item);
            rataSelected.getEntity().addArticolo(contributoArticolo);
        });
        // Persisto la rata
        fatturazioneServiceInstance.get().persist(rataSelected.getEntity());
    }

    private boolean validateAddArticoli() {
        return rataSelected != null && addItemsIdSelected != null && !addItemsIdSelected.isEmpty();
    }

    private boolean validateDataLiquidazione(Date date, Date originalDate) {
        if (date == null || promozioneSelected == null) {
            return false;
        }
        final Date minDate = calculateMinDataLiquidazione();
        return !date.equals(originalDate) && new DateTimeUtils().isAfter(date, minDate, true);
    }

    private Date calculateMinDataLiquidazione() {
        final DateTimeUtils dateTimeUtils = new DateTimeUtils();
        final Date today = dateTimeUtils.getDateWithoutTime(new Date());
        return dateTimeUtils.isBefore(today, promozioneSelected.getDataInizio(), true)
                ? promozioneSelected.getDataInizio()
                : today;
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
