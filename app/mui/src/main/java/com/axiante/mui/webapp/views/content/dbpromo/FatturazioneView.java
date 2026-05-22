package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PrestazioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.FatturazioneService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.PrestazioniService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.FatturazioneUtil;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.enumeration.TipologiaArticoliEnum;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
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
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("fatturazione")
@Dependent
@Slf4j
public class FatturazioneView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 4268547478562574052L;

    private static final String MAX_CONTRIBUTI_MSG_FORMAT = "Numero massimo di rate raggiunto per la promozione '%s', compratore '%s' e fornitore '%s'";
    private static final String NO_CONTRIBUTI_MSG_FORMAT = "La promozione '%s' sul canale '%d - %s' non permette la crazione di rate";

    @Inject
    private Instance<FatturazioneUtil> fatturazioneUtilInstance;

    @Inject
    private Instance<PrestazioniService> prestazioniServiceInstance;

    @Inject
    private Instance<ItemService> itemServiceInstance;

    @Inject
    private Instance<FatturazioneService> fatturazioneServiceInstance;

    @Getter
    private String errorMessage = null;

    @Getter
    private Long idPromozioneSelected = null;

    @Getter
    private Long idCompratoreSelected = null;

    @Getter
    private Long idFornitoreSelected = null;

    @Getter
    private String codicePrestazioneSelected = null;

    @Getter
    private Date dataLiquidazione = null;

    @Getter
    private Date minDataLiquidazione = null;

    @Getter
    private Double inputValore = null;

    @Getter
    private String noteCompratore = null;

    @Getter
    private TipologiaArticoliEnum tipologiaArticoliSelected = null;

    @Getter
    private PromozioneTestataEntity promozioneSelected;

    @Getter
    private List<PromozioneTestataEntity> promozioni;

    @Getter
    private List<CompratoreEntity> compratori;

    @Getter
    private List<FornitoreEntity> fornitori;

    @Getter
    private List<PrestazioniEntity> prestazioni;

    @Getter
    private List<TipologiaArticoliEnum> tipologiaArticoli;

    @Getter
    private boolean selectPrestazioneDisabled = true;

    @Getter
    private boolean dataLiquidazioneDisabled = true;

    @Getter
    private boolean btnConfirmAddFatturazioneDisabled = true;

    @Getter
    private boolean maxProgressivoReachedFlag = false;

    private UserDTO userDTO;
    private CompratoreEntity compratoreSelected;
    private FornitoreEntity fornitoreSelected;
    private PrestazioniEntity prestazioneSelected;
    private boolean dataLiquidazioneValid = false;
    private List<Long> itemsIdSelected = new ArrayList<>();

    public void init() {
        userDTO = getUserDto();
        readPromozioni();
        readPrestazioni();
        readTipologiaArticoli();
    }

    public void prepareDialogAddFatturazione() {
        resetDialogAddFatturazione();
        if (prestazioni != null && !prestazioni.isEmpty()) {
            setCodicePrestazioneSelected(prestazioni.get(0).getCodicePrestazione());
        }
        setTipologiaArticoliSelected(TipologiaArticoliEnum.OWN);
    }

    public void resetDialogAddFatturazione() {
        errorMessage = null;
        setIdPromozioneSelected(null);
        setIdCompratoreSelected(null);
        setIdFornitoreSelected(null);
        setDataLiquidazione(null);
        inputValore = null;
        noteCompratore = null;
        compratori = new ArrayList<>();
        fornitori = new ArrayList<>();
        selectPrestazioneDisabled = true;
        dataLiquidazioneDisabled = true;
        dataLiquidazioneValid = false;
        maxProgressivoReachedFlag = false;
        itemsIdSelected.clear();
    }

    public void confirmDialogAddFatturazione() {
        if (validate()) {
            try {
                saveContributo();
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        "Nuova rata di fatturazione creata correttamente"));
            } catch (Exception ex) {
                log.error(String.format("Error saving ContributiPromoEntity for promozione %d, compratore %d and fornitore %d",
                        promozioneSelected.getId(), compratoreSelected.getId(), fornitoreSelected.getId()), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile creare nuova rata di fatturazione"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile creare nuova rata di fatturazione"));
        }
        resetDialogAddFatturazione();
    }

    private void saveContributo() {
        // Creo la rata
        final ContributiPromoEntity contributo = new ContributiPromoEntity();
        contributo.setPromozione(promozioneSelected);
        contributo.setCompratore(compratoreSelected);
        contributo.setFornitore(fornitoreSelected);
        contributo.setCodicePrestazione(prestazioneSelected.getCodicePrestazione());
        contributo.setValoreApplicato(inputValore != null ? inputValore : 0d);
        contributo.setDataLiquidazione(dataLiquidazione);
        if (noteCompratore != null && !noteCompratore.trim().isEmpty()) {
            contributo.setNotaCompratore(noteCompratore.trim());
        }
        contributo.setProgressivo(fatturazioneUtilInstance.get()
                .nextProgressivo(promozioneSelected, compratoreSelected, fornitoreSelected));
        contributo.setCodiceStato("1");
        // Per ogni articolo selezionato creo una riga di dettaglio
        itemServiceInstance.get().findByIds(itemsIdSelected).forEach(item -> {
            ContributiPromoArticoloEntity contributoArticolo = new ContributiPromoArticoloEntity();
            contributoArticolo.setItem(item);
            contributo.addArticolo(contributoArticolo);
        });
        // Persisto la rata
        fatturazioneServiceInstance.get().persist(contributo);
    }

    public void setIdPromozioneSelected(Long idPromozioneSelected) {
        this.idPromozioneSelected = idPromozioneSelected;
        if (promozioni != null && !promozioni.isEmpty() && idPromozioneSelected != null) {
            promozioneSelected = getPromozioneSelectedFromId(idPromozioneSelected);
            if (maxProgressivoReached(promozioneSelected.getCanalePromozioneEntity())) {
                errorMessage = String.format(NO_CONTRIBUTI_MSG_FORMAT,
                        promozioneSelected.getDescrizioneEstesa(),
                        promozioneSelected.getCanalePromozioneEntity().getCodiceCanale(),
                        promozioneSelected.getCanalePromozioneEntity().getDescrizione());
                maxProgressivoReachedFlag = true;
                selectPrestazioneDisabled = true;
                dataLiquidazioneDisabled = true;
                dataLiquidazioneValid = false;
            } else {
                errorMessage = null;
                maxProgressivoReachedFlag = false;
                readCompratori();
            }
        } else {
            promozioneSelected = null;
        }
        setIdCompratoreSelected(null);
        updateBtnConfirmAddFatturazione();
        //TODO: aggiornare la lista delle prestazioni in base alla promozione selezionata
        readPrestazioni();
    }

    public void setIdCompratoreSelected(Long idCompratoreSelected) {
        this.idCompratoreSelected = idCompratoreSelected;
        if (compratori != null && !compratori.isEmpty() && idCompratoreSelected != null) {
            compratoreSelected = getCompratoreSelectedFromId(idCompratoreSelected);
            readFornitori();
        } else {
            compratoreSelected = null;
        }
        setIdFornitoreSelected(null);
        updateBtnConfirmAddFatturazione();
    }

    public void setIdFornitoreSelected(Long idFornitoreSelected) {
        this.idFornitoreSelected = idFornitoreSelected;
        if (fornitori != null && !fornitori.isEmpty() && idFornitoreSelected != null) {
            fornitoreSelected = getFornitoreSelectedFromId(idFornitoreSelected);
        } else {
            fornitoreSelected = null;
            codicePrestazioneSelected = null;
            dataLiquidazione = null;
            minDataLiquidazione = null;
            inputValore = null;
            noteCompratore = null;
            tipologiaArticoliSelected = null;
            selectPrestazioneDisabled = true;
            dataLiquidazioneDisabled = true;
            if (!maxProgressivoReachedFlag) {
                errorMessage = null;
            }
        }
        if (fornitoreSelected != null) {
            if (maxProgressivoReached()) {
                errorMessage = String.format(MAX_CONTRIBUTI_MSG_FORMAT,
                        promozioneSelected == null ? "-" : promozioneSelected.getDescrizioneEstesa(),
                        compratoreSelected == null ? "-" : compratoreSelected.getFullDescription(),
                        fornitoreSelected == null ? "-" : fornitoreSelected.getFullDescription());
                selectPrestazioneDisabled = true;
                dataLiquidazioneDisabled = true;
                dataLiquidazioneValid = false;
            } else {
                errorMessage = null;
                if (promozioneSelected != null && compratoreSelected != null) {
                    selectPrestazioneDisabled = false;
                    dataLiquidazioneDisabled = false;
                    if (prestazioni != null && !prestazioni.isEmpty()) {
                        setCodicePrestazioneSelected(prestazioni.get(0).getCodicePrestazione());
                    }
                    setTipologiaArticoliSelected(TipologiaArticoliEnum.OWN);
                    dataLiquidazioneValid = checkDataLiquidazione(dataLiquidazione);
                    minDataLiquidazione = calculateMinDataLiquidazione();
                    loadGrid();
                }
            }
        }
        itemsIdSelected.clear();
        updateBtnConfirmAddFatturazione();
    }

    public void setCodicePrestazioneSelected(String codicePrestazioneSelected) {
        this.codicePrestazioneSelected = codicePrestazioneSelected;
        if (prestazioni != null && !prestazioni.isEmpty() && codicePrestazioneSelected != null) {
            prestazioneSelected = getPrestazioneSelectedFromCode(codicePrestazioneSelected);
        } else {
            prestazioneSelected = null;
        }
        updateBtnConfirmAddFatturazione();
    }

    public void setDataLiquidazione(Date dataLiquidazione) {
        this.dataLiquidazione = dataLiquidazione;
        dataLiquidazioneValid = checkDataLiquidazione(dataLiquidazione);
        updateBtnConfirmAddFatturazione();
    }

    public void setInputValore(Double inputValore) {
        this.inputValore = inputValore;
        updateBtnConfirmAddFatturazione();
    }

    public void setNoteCompratore(String noteCompratore) {
        this.noteCompratore = noteCompratore;
        updateBtnConfirmAddFatturazione();
    }

    public void setTipologiaArticoliSelected(TipologiaArticoliEnum tipologiaArticoliSelected) {
        this.tipologiaArticoliSelected = tipologiaArticoliSelected;
        if (promozioneSelected != null && compratoreSelected != null && fornitoreSelected != null
                && this.tipologiaArticoliSelected != null) {
            loadGrid();
        }
        itemsIdSelected.clear();
        updateBtnConfirmAddFatturazione();
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
                        "Impossibile recuperare lista articoli selezionati"));
            }
        } catch (IOException ex) {
            log.error("Error reading selected items", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile recuperare lista articoli selezionati"));
        }
        updateBtnConfirmAddFatturazione();
    }

    private void readPromozioni() {
        try {
            promozioni = fatturazioneUtilInstance.get().readPromozioniWithWritableItems(userDTO).stream()
                    .sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error reading promozioni", ex);
            promozioni = new ArrayList<>();
        }
    }

    private void readCompratori() {
        if (promozioneSelected == null) {
            compratori = new ArrayList<>();
        } else {
            try {
                compratori = fatturazioneUtilInstance.get().readCompratoriWithWritableItems(promozioneSelected, userDTO);
            } catch (Exception ex) {
                log.error(String.format("Error reading compratori for promozione %d", promozioneSelected.getId()), ex);
                compratori = new ArrayList<>();
            }
        }
    }

    private void readFornitori() {
        if (compratoreSelected == null) {
            fornitori = new ArrayList<>();
        } else {
            try {
                fornitori = fatturazioneUtilInstance.get()
                        .readFornitoriWithPlannedItems(promozioneSelected, compratoreSelected.getCodiceCompratore());
            } catch (Exception ex) {
                log.error(String.format("Error reading fornitori for promozione %d and compratore %s",
                        promozioneSelected.getId(), compratoreSelected.getCodiceCompratore()), ex);
                fornitori = new ArrayList<>();
            }
        }
    }

    private void readPrestazioni() {
        if ( getPromozioneSelected() != null ){
            String codiceGruppo = promozioneSelected.getCanalePromozioneEntity().getGruppoPromozioneEntity().getCodiceGruppo();
            if ( codiceGruppo != null ){
                try {
                    prestazioni = prestazioniServiceInstance.get().findByCodiceGruppo(codiceGruppo).stream()
                            .sorted(Comparator.comparing(PrestazioniEntity::getDescrizionePrestazione))
                            .collect(Collectors.toList());
                } catch (Exception ex) {
                    log.error("Error reading prestazioni", ex);
                    prestazioni = new ArrayList<>();
                }
            } else {
                log.error("Il canale selezionato non ha un gruppo associato: impossibile recuperare le prestazioni");
                prestazioni = new ArrayList<>();
            }
        } else {
            log.warn("nessuna promozione selezionata: impossibile recuperare le prestazioni");
            prestazioni = new ArrayList<>();
        }
    }

    private void readTipologiaArticoli() {
        tipologiaArticoli = new ArrayList<>();
        tipologiaArticoli.add(TipologiaArticoliEnum.OWN);
        tipologiaArticoli.add(TipologiaArticoliEnum.ALL);
    }

    private PromozioneTestataEntity getPromozioneSelectedFromId(@NonNull Long id) {
        return promozioni.stream().filter(p -> id.equals(p.getId())).findFirst().orElse(null);
    }

    private CompratoreEntity getCompratoreSelectedFromId(@NonNull Long id) {
        return compratori.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    private FornitoreEntity getFornitoreSelectedFromId(@NonNull Long id) {
        return fornitori.stream().filter(f -> id.equals(f.getId())).findFirst().orElse(null);
    }

    private PrestazioniEntity getPrestazioneSelectedFromCode(String codice) {
        return prestazioni.stream().filter(p -> codice.equals(p.getCodicePrestazione())).findFirst().orElse(null);
    }

    private boolean maxProgressivoReached(@NonNull CanalePromozioneEntity canale) {
        return canale.getMaxProgressivo() != null && canale.getMaxProgressivo().equals(0L);
    }

    private boolean maxProgressivoReached() {
        if (promozioneSelected != null && compratoreSelected != null && fornitoreSelected != null) {
            return fatturazioneUtilInstance.get().maxProgressivoReached(promozioneSelected, compratoreSelected, fornitoreSelected);
        }
        return false;
    }

    private boolean checkDataLiquidazione(Date date) {
        if (date == null || promozioneSelected == null) {
            return false;
        }
        return new DateTimeUtils().isAfter(date, calculateMinDataLiquidazione(), true);
    }

    private Date calculateMinDataLiquidazione() {
        final DateTimeUtils dateTimeUtils = new DateTimeUtils();
        final Date today = dateTimeUtils.getDateWithoutTime(new Date());
        return dateTimeUtils.isBefore(today, promozioneSelected.getDataInizio(), true)
                ? promozioneSelected.getDataInizio()
                : today;
    }

    private void updateBtnConfirmAddFatturazione() {
        btnConfirmAddFatturazioneDisabled = promozioneSelected == null || compratoreSelected == null || fornitoreSelected == null
                || prestazioneSelected == null || !dataLiquidazioneValid || itemsIdSelected.isEmpty();
    }

    private void loadGrid() {
        executeScript(String.format("loadGridFatturazioneArticoli(%d, '%s', '%s', '%s')",
                promozioneSelected.getId(), compratoreSelected.getCodiceCompratore(),
                fornitoreSelected.getCodiceFornitore(), tipologiaArticoliSelected.getValue()));
    }

    private boolean validate() {
        return promozioneSelected != null && compratoreSelected != null && fornitoreSelected != null
                && prestazioneSelected != null && dataLiquidazioneValid && !itemsIdSelected.isEmpty();
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
