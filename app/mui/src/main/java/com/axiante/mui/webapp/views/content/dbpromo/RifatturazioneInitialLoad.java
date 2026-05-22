package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneDefaultService;
import com.axiante.mui.dbpromo.persistence.service.TipoPromoRifatturazioneService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.RifatturazioneUtil;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.dtos.CompratoreFornitoreTipologiaDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@MuiViewModel("fatturazioneInitialLoadView")
@Dependent
@Slf4j
public class RifatturazioneInitialLoad extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 9077397998948556337L;
    private static final String YES = "SI";
    private static final String NO = "NO";

    @Inject
    private Instance<PromoFatturazioneDefaultService> promoFattDefaultServiceInstance;

    @Inject
    private Instance<TipoPromoRifatturazioneService> tipoPromoRifatturazioneServiceInstance;

    @Inject
    private Instance<RifatturazioneUtil> rifatturazioneUtilInstance;

    @Getter
    private boolean btnConfirmAddDefaultDisabled = true;

    @Getter
    private List<CompratoreEntity> compratori;

    @Getter
    private List<FornitoreEntity> fornitori;

    @Getter
    private List<TipoPromoRifatturazioneEntity> tipologie;

    @Getter
    private Long idCompratoreSelected = null;

    @Getter
    private Long idFornitoreSelected = null;

    @Getter
    private Integer idTipologiaSelected = null;

    @Getter
    @Setter
    private Boolean flagRifatturazione = null;

    @Getter
    @Setter
    private BigDecimal variabileFisso = null;

    @Getter
    @Setter
    private Integer variabilePct = null;

    @Getter
    @Setter
    private Boolean flagAbbattimento = null;

    @Getter
    @Setter
    private String ncNd = null;

    @Getter
    @Setter
    private BigDecimal capMin = null;

    @Getter
    @Setter
    private BigDecimal capMax = null;

    @Getter
    @Setter
    private Boolean flagSovrapposizioni = null;

    @Getter
    private boolean inputVariabileFissoDisabled = false;

    @Getter
    private boolean selectVariabilePctDisabled = false;

    @Getter
    private boolean selectAbbattimentoDisabled = false;

    @Getter
    private boolean selectNcNdDisabled = false;

    @Getter
    private boolean inputCapMinDisabled = false;

    @Getter
    private boolean inputCapMaxDisabled = false;

    @Getter
    private boolean selectSovrapposizioniDisabled = false;

    @Getter
    private String deleteRifatturazioneInitialLoadMessage = null;

    @Getter
    private boolean editMode = false;

    @Getter
    private boolean currentSlotEmpty = false;

    @Getter
    private String currentSlotEmptyMessage = null;

    private UserDTO userDTO;
    private CompratoreEntity compratoreSelected;
    private FornitoreEntity fornitoreSelected;
    private TipoPromoRifatturazioneEntity tipologiaSelected;
    private PromoFatturazioneDefaultEntity promoFatturazioneToDelete = null;
    private PromoFatturazioneDefaultEntity promoFatturazioneToEdit = null;
    private Map<Long, List<FornitoreEntity>> fornitoriByCompratoreId;
    private List<CompratoreFornitoreTipologiaDTO> matrixCompratoreFornitoreTipologia;
    private List<Long> idsCompratori;
    private List<CompratoreEntity> compratoriAll;
    private List<TipoPromoRifatturazioneEntity> tipologieAll;

    public void init() {
        userDTO = getUserDto();
        loadCompratori();
        loadTipologie();
        preloadFornitoriAndBuildMatrix();
        updateMatrix();
    }

    public void resetDialogAddDefault() {
        editMode = false;
        promoFatturazioneToEdit = null;
        idCompratoreSelected = null;
        idFornitoreSelected = null;
        idTipologiaSelected = null;
        currentSlotEmpty = false;
        currentSlotEmptyMessage = null;
        resetFields();
        loadDropdownsDefaults();
        enableAllFields();
        filterAvailableCompratori();
    }

    public void prepareDialogAddDefault() {
        resetDialogAddDefault();
        filterAvailableCompratori();
        filterAvailableTipologie();
    }

    public void confirmDialogAddDefault() {
        if (btnConfirmAddDefaultDisabled || (editMode && promoFatturazioneToEdit == null)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione",
                    String.format("Errore in %s dati; riprovare", editMode ? "modifica" : "inserimento")));
            return;
        }
        try {
            PromoFatturazioneDefaultEntity promoFatturazioneDefault = buildPromoFatturazioneDefault(editMode ?
                    promoFatturazioneToEdit : new PromoFatturazioneDefaultEntity());
            promoFatturazioneDefault = (PromoFatturazioneDefaultEntity) promoFattDefaultServiceInstance.get()
                    .persistWithAuditLog(promoFatturazioneDefault, getUserDto().getUsermame());
            rifatturazioneUtilInstance.get().updateMatrixRow(matrixCompratoreFornitoreTipologia, promoFatturazioneDefault, true);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                    String.format("Parametri di default %s con successo\nCompratore '%s'\nFornitore '%s'\nPromozione '%s'",
                            editMode ? "modificati" : "inseriti", compratoreSelected.getCodiceCompratore(),
                            fornitoreSelected.getCodiceFornitore(), tipologiaSelected.getDescrizione())));
        } catch (Exception ex) {
            log.error(String.format("Error %s promoFatturazioneDefault", editMode ? "editing" : "creating"), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione",
                    String.format("Errore in %s dati; contattare l'assistenza", editMode ? "modifica" : "inserimento")));
        }
    }

    public boolean getAddFatturazioneDefaultBtnDisabled() {
        return matrixCompratoreFornitoreTipologia.stream().allMatch(CompratoreFornitoreTipologiaDTO::isDone);
    }

    public boolean getSelectCompratoreDisabled() {
        return compratori != null && compratori.size() == 1;
    }

    public List<YesNoChoice> getYesNoChoices() {
        return Arrays.asList(YesNoChoice.values());
    }

    public List<NcNdChoice> getNcNdChoices() {
        return Arrays.asList(NcNdChoice.values());
    }

    public List<Integer> getVariabilePctChoices() {
        return Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
    }

    public void setIdCompratoreSelected(Long idCompratoreSelected) {
        this.idCompratoreSelected = idCompratoreSelected;
        if (idCompratoreSelected != null) {
            compratoreSelected = retrieveCompratoreById(idCompratoreSelected);
            if (compratoreSelected != null) {
                loadFornitoriByIdCompratore(idCompratoreSelected);
            } else {
                log.warn(String.format("Compratore with id %d has no codiceCompratore", idCompratoreSelected));
            }
        } else {
            compratoreSelected = null;
            fornitori = null;
        }
        handleBtnConfirmAddDefault();
    }

    public void setIdFornitoreSelected(Long idFornitoreSelected) {
        this.idFornitoreSelected = idFornitoreSelected;
        fornitoreSelected = idFornitoreSelected != null ? retrieveFornitoreById(idFornitoreSelected) : null;
        prepareTipologieDropdown();
        handleBtnConfirmAddDefault();
    }

    public void setIdTipologiaSelected(Integer idTipologiaSelected) {
        this.idTipologiaSelected = idTipologiaSelected;
        tipologiaSelected = idTipologiaSelected != null ? retrieveTipologiaById(idTipologiaSelected) : null;
        handleBtnConfirmAddDefault();
    }

    public void onSelectRifatturazioneValueChange() {
        boolean disabled = !flagRifatturazione;
        if (disabled) {
            resetFields(false);
        } else {
            if (!editMode) {
                loadDropdownsDefaults(false);
            }
        }
        inputVariabileFissoDisabled = disabled;
        selectVariabilePctDisabled = disabled;
        selectAbbattimentoDisabled = disabled;
        selectNcNdDisabled = disabled;
        inputCapMinDisabled = disabled;
        inputCapMaxDisabled = disabled;
        selectSovrapposizioniDisabled = disabled;
        handleBtnConfirmAddDefault();
    }

    public void onInputVariabileFissoKeyup() {
        boolean disabled = variabileFisso != null;
        if (disabled) {
            variabilePct = null;
            capMin = null;
            capMax = null;
            if (ncNd == null) {
                flagAbbattimento = null;
            }
        }
        selectVariabilePctDisabled = disabled;
        inputCapMinDisabled = disabled;
        inputCapMaxDisabled = disabled;
        selectAbbattimentoDisabled = disabled && ncNd == null;
        handleBtnConfirmAddDefault();
    }

    public void onInputVariabileFissoClear() {
        variabileFisso = null;
        onInputVariabileFissoKeyup();
    }

    public void onSelectVariabilePctValueChange() {
        if (variabilePct != null) {
            variabileFisso = null;
        }
        inputVariabileFissoDisabled = variabilePct != null;
        handleBtnConfirmAddDefault();
    }

    public void onSelectNcNdValueChange() {
        selectAbbattimentoDisabled = variabilePct == null && (ncNd == null || variabileFisso == null);
        handleBtnConfirmAddDefault();
    }

    public void onSelectAbbattimentoValueChange() {
        handleBtnConfirmAddDefault();
    }

    public void onSelectSovrapposizioniValueChange() {
        handleBtnConfirmAddDefault();
    }

    public void onInputCapMinClear() {
        capMin = null;
        handleBtnConfirmAddDefault();
    }

    public void onInputCapMaxClear() {
        capMax = null;
        handleBtnConfirmAddDefault();
    }

    public void deleteRifatturazioneClicked() {
        String value = getRequestParameterMap().getOrDefault("id", null);
        if (value == null) {
            log.error("id parameter is missing");
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare la rifatturazione, contattare l'assistenza");
            return;
        }
        try {
            Long id = Long.parseLong(value);
            promoFatturazioneToDelete = promoFattDefaultServiceInstance.get().findById(id);
            if (promoFatturazioneToDelete == null) {
                log.error(String.format("promoFatturazioneToDelete with id %d not found", id));
                addErrorMessage("Errore applicativo",
                        "Impossibile eliminare la rifatturazione, contattare l'assistenza");
                return;
            }
            deleteRifatturazioneInitialLoadMessage = String.format(
                    "Sei sicuro di voler eliminare la seguente rifatturazione?\nCompratore '%s'\nFornitore '%s'\nPromozione '%s'",
                    promoFatturazioneToDelete.getCompratore().getCodiceCompratore(),
                    promoFatturazioneToDelete.getFornitore().getCodiceFornitore(),
                    promoFatturazioneToDelete.getTipoPromozione().getDescrizione());
        } catch (Exception ex) {
            log.error("Error deleting promoFatturazioneDefault", ex);
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare la rifatturazione, contattare l'assistenza");
        }
    }

    public void editRifatturazioneClicked() {
        String value = getRequestParameterMap().getOrDefault("id", null);
        if (value == null) {
            log.error("id parameter is missing");
            addErrorMessage("Errore applicativo",
                    "Impossibile modificare la rifatturazione, contattare l'assistenza");
            return;
        }
        try {
            Long id = Long.parseLong(value);
            promoFatturazioneToEdit = promoFattDefaultServiceInstance.get().findById(id);
            if (promoFatturazioneToEdit == null) {
                log.error(String.format("promoFatturazioneDefault with id %d not found", id));
                addErrorMessage("Errore applicativo",
                        "Impossibile modificare la rifatturazione, contattare l'assistenza");
                return;
            }
            loadFatturazione(promoFatturazioneToEdit);
        } catch (Exception ex) {
            log.error("Error editing promoFatturazioneDefault", ex);
            addErrorMessage("Errore applicativo",
                    "Impossibile modificare la rifatturazione, contattare l'assistenza");
        }
    }

    public void deleteRifatturazioneConfirmClicked() {
        if (promoFatturazioneToDelete == null) {
            log.error("promoFatturazioneToDelete is null");
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare la rifatturazione, contattare l'assistenza");
            return;
        }
        try {
            promoFattDefaultServiceInstance.get().remove(promoFatturazioneToDelete);
            rifatturazioneUtilInstance.get().updateMatrixRow(matrixCompratoreFornitoreTipologia, promoFatturazioneToDelete, false);
            addInfoMessage("Successo", "Rifatturazione eliminata con successo");
        } catch (Exception ex) {
            log.error(String.format("Error deleting promoFatturazioneDefault with id: '%d'",
                    promoFatturazioneToDelete.getId()), ex);
            addErrorMessage("Errore applicativo",
                    "Impossibile eliminare la rifatturazione, contattare l'assistenza");
        }
    }

    public void deleteRifatturazioneCancelClicked() {
        if (promoFatturazioneToDelete != null) {
            promoFatturazioneToDelete = null;
            deleteRifatturazioneInitialLoadMessage = null;
        }
    }

    public String getDlgAddDefaultTitle() {
        return editMode ? "Modifica" : "Aggiunta";
    }

    private PromoFatturazioneDefaultEntity buildPromoFatturazioneDefault(PromoFatturazioneDefaultEntity entity) {
        entity.setCompratore(compratoreSelected);
        entity.setFornitore(fornitoreSelected);
        entity.setTipoPromozione(tipologiaSelected);
        entity.setRifattura(flagRifatturazione ? YES : NO);
        entity.setVarFiss(variabileFisso);
        entity.setVarPerc(variabilePct);
        entity.setAbbattimento(flagAbbattimento == null ? null : flagAbbattimento ? YES : NO);
        entity.setNcNd(ncNd);
        entity.setCapMin(capMin);
        entity.setCapMax(capMax);
        entity.setSovrapposizioni(flagSovrapposizioni == null ? null : flagSovrapposizioni ? YES : NO);
        return entity;
    }

    private void loadFatturazione(PromoFatturazioneDefaultEntity promoFatturazioneDefault) {
        editMode = true;
        setIdCompratoreSelected(promoFatturazioneDefault.getCompratore().getId());
        setIdFornitoreSelected(promoFatturazioneDefault.getFornitore().getId());
        setIdTipologiaSelected(promoFatturazioneDefault.getTipoPromozione().getCodice());
        flagRifatturazione = YES.equals(promoFatturazioneDefault.getRifattura());
        variabileFisso = promoFatturazioneDefault.getVarFiss();
        variabilePct = promoFatturazioneDefault.getVarPerc();
        flagAbbattimento = YES.equals(promoFatturazioneDefault.getAbbattimento());
        ncNd = promoFatturazioneDefault.getNcNd();
        capMin = promoFatturazioneDefault.getCapMin();
        capMax = promoFatturazioneDefault.getCapMax();
        flagSovrapposizioni = YES.equals(promoFatturazioneDefault.getSovrapposizioni());
        onSelectRifatturazioneValueChange();
    }

    private void handleBtnConfirmAddDefault() {
        if (idCompratoreSelected == null || idFornitoreSelected == null || idTipologiaSelected == null
                || flagRifatturazione == null) {
            btnConfirmAddDefaultDisabled = true;
            return;
        }
        btnConfirmAddDefaultDisabled = flagRifatturazione ? !validWithFatturazione() : !validWithoutFatturazione();
    }

    private boolean validWithFatturazione() {
        if (variabileFisso == null && variabilePct == null) {
            return false;
        }
        return variabileFisso != null ? validForVariabileFisso() : validForVariabilePct();
    }

    private boolean validForVariabileFisso() {
        return variabilePct == null && capMin == null && capMax == null && validForRequiredFields();
    }

    private boolean validForVariabilePct() {
        return variabileFisso == null && validForRequiredFields();
    }

    private boolean validForRequiredFields() {
        return ncNd != null && !ncNd.trim().isEmpty() && flagAbbattimento != null && flagSovrapposizioni != null;
    }

    private boolean validWithoutFatturazione() {
        return variabileFisso == null && variabilePct == null && flagAbbattimento == null
                && ncNd == null && capMin == null && capMax == null && flagSovrapposizioni == null;
    }

    private void loadCompratori() {
        compratoriAll = rifatturazioneUtilInstance.get().compratoriWritableByGruppi(userDTO.getGruppi());
        idsCompratori = compratoriAll.stream().map(CompratoreEntity::getId).collect(Collectors.toList());
    }

    private void loadFornitoriByIdCompratore(Long idCompratore) {
        if (editMode) {
            fornitori = fornitoriByCompratoreId.getOrDefault(idCompratore, Collections.emptyList());
        } else {
            fornitori = matrixCompratoreFornitoreTipologia.stream()
                    .filter(m -> !m.isDone() && m.getCompratore().getId().equals(idCompratore))
                    .map(CompratoreFornitoreTipologiaDTO::getFornitore)
                    .distinct()
                    .collect(Collectors.toList());
        }
    }

    private void loadTipologie() {
        tipologieAll = tipoPromoRifatturazioneServiceInstance.get().findAll();
    }

    private void preloadFornitoriAndBuildMatrix() {
        if (compratoriAll == null || tipologieAll == null) {
            fornitoriByCompratoreId = null;
            matrixCompratoreFornitoreTipologia = null;
            return;
        }
        fornitoriByCompratoreId = rifatturazioneUtilInstance.get().fornitoriGroupedByIdCompratore(compratoriAll);
        matrixCompratoreFornitoreTipologia = rifatturazioneUtilInstance.get().createMatrixForDefaults(
                compratoriAll, fornitoriByCompratoreId, tipologieAll);
    }

    private void updateMatrix() {
        if (matrixCompratoreFornitoreTipologia == null || idsCompratori == null || idsCompratori.isEmpty()) {
            return;
        }
        List<PromoFatturazioneDefaultEntity> defaults = promoFattDefaultServiceInstance.get()
                .findAllByIdsCompratori(idsCompratori);
        rifatturazioneUtilInstance.get().updateMatrix(matrixCompratoreFornitoreTipologia, defaults);
    }

    private void filterAvailableCompratori() {
        if (matrixCompratoreFornitoreTipologia == null) {
            return;
        }
        compratori = matrixCompratoreFornitoreTipologia.stream()
                .filter(filterIncompleteTasks())
                .map(CompratoreFornitoreTipologiaDTO::getCompratore)
                .distinct()
                .collect(Collectors.toList());
        if (compratori.size() == 1) {
            setIdCompratoreSelected(compratori.get(0).getId());
        }
    }

    private void filterAvailableTipologie() {
        if (matrixCompratoreFornitoreTipologia == null) {
            return;
        }
        tipologie = matrixCompratoreFornitoreTipologia.stream()
                .filter(filterIncompleteTasks())
                .map(CompratoreFornitoreTipologiaDTO::getTipologia)
                .distinct()
                .sorted(Comparator.comparing(TipoPromoRifatturazioneEntity::getDescrizione))
                .collect(Collectors.toList());
        if (tipologie.size() == 1) {
            setIdTipologiaSelected(tipologie.get(0).getCodice());
        }
    }

    private void prepareTipologieDropdown() {
        if (idCompratoreSelected == null || idFornitoreSelected == null) {
            return;
        }
        tipologie = editMode ? tipologieAll : matrixCompratoreFornitoreTipologia.stream()
                .filter(filterIncompleteTasks())
                .filter(m -> m.getCompratore().getId().equals(idCompratoreSelected)
                        && m.getFornitore().getId().equals(idFornitoreSelected))
                .map(CompratoreFornitoreTipologiaDTO::getTipologia)
                .distinct()
                .sorted(Comparator.comparing(TipoPromoRifatturazioneEntity::getDescrizione))
                .collect(Collectors.toList());
        if (tipologie.isEmpty()) {
            currentSlotEmpty = true;
            currentSlotEmptyMessage = String.format(
                    "La combinazione compratore '%s' e fornitore '%s' è stata impostata su tutti i canali disponibili",
                    compratoreSelected.getCodiceCompratore(), fornitoreSelected.getCodiceFornitore());
        } else if (tipologie.size() == 1) {
            setIdTipologiaSelected(tipologie.get(0).getCodice());
        }
    }

    private static Predicate<CompratoreFornitoreTipologiaDTO> filterIncompleteTasks() {
        return m -> !m.isDone();
    }

    private CompratoreEntity retrieveCompratoreById(Long idCompratore) {
        return compratoriAll.stream().filter(c -> idCompratore.equals(c.getId()))
                .findFirst().orElse(null);
    }

    private FornitoreEntity retrieveFornitoreById(Long idFornitore) {
        return fornitori.stream().filter(c -> idFornitore.equals(c.getId()))
                .findFirst().orElse(null);
    }

    private TipoPromoRifatturazioneEntity retrieveTipologiaById(Integer idTipologia) {
        return tipologieAll.stream().filter(t -> idTipologia.equals(t.getCodice()))
                .findFirst().orElse(null);
    }

    private void resetFields() {
        resetFields(true);
    }

    private void resetFields(boolean flagRifatturazioneFld) {
        if (flagRifatturazioneFld) {
            flagRifatturazione = true;
        }
        variabileFisso = null;
        variabilePct = null;
        flagAbbattimento = null;
        ncNd = null;
        capMin = null;
        capMax = null;
        flagSovrapposizioni = null;
    }

    private void loadDropdownsDefaults() {
        loadDropdownsDefaults(true);
    }

    private void loadDropdownsDefaults(boolean flagRifatturazioneFld) {
        if (flagRifatturazioneFld) {
            flagRifatturazione = true;
        }
        variabilePct = null;
        ncNd = NcNdChoice.ND.label;
        flagAbbattimento = false;
        flagSovrapposizioni = false;
    }

    private void enableAllFields() {
        inputVariabileFissoDisabled = false;
        selectVariabilePctDisabled = false;
        selectAbbattimentoDisabled = false;
        selectNcNdDisabled = false;
        inputCapMinDisabled = false;
        inputCapMaxDisabled = false;
        selectSovrapposizioniDisabled = false;
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
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

    public enum YesNoChoice {
        YES("SI", true), NO("NO", false);

        @Getter
        private final String label;

        @Getter
        private final boolean value;

        YesNoChoice(String label, boolean value) {
            this.label = label;
            this.value = value;
        }
    }

    public enum NcNdChoice {
        NC("NC"), ND("ND");

        @Getter
        private final String label;

        NcNdChoice(String label) {
            this.label = label;
        }
    }
}
