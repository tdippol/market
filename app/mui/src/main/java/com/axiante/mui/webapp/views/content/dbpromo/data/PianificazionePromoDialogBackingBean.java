package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningRadioButtonEnum;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.business.service.UploadExcelService;
import com.axiante.mui.dbpromo.business.service.impl.data.ItemUpload;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.TipoElementoService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.MeccanicheUtil;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemPojo;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Slf4j
public class PianificazionePromoDialogBackingBean implements FacesContextAware {

    @Getter
    private final UserDTO userDTO;

    @Getter
    PromozioneTestataEntity promozioneCorrente;

    @Getter
    List<MeccanicheEntity> meccaniche;

    @Getter
    Map<String, String> compratori;

    @Getter
    Map<String, String> fornitori;

    @Getter
    Map<String, String> reparti;

    @Getter
    Map<String, String> categorie;

    @Getter
    Map<String, String> grm;

    @Getter
    Map<String, String> marchioPrivato;

    @Getter
    Long idMeccanicaCorrente;

    @Getter
    @Setter
    String idCompratoreCorrente;

    @Getter
    @Setter
    String idFornitoreCorrente;

    @Getter
    @Setter
    String idRepartoCorrente;

    @Getter
    @Setter
    String idCategoriaCorrente;

    @Getter
    @Setter
    String idGrmCorrente;

    @Getter
    String idMarchioPrivCorrente;

    @Getter
    List<String> tipoElementi;

    @Getter
    String idTipoElemento;

    @Getter
    String title;

    @Getter
    String currentActiveTab;

    @Getter
    boolean confirmPianificazioneDialogBtnDisabled = true;

    @Getter
    @Setter
    boolean aggiungiPianificazioneBtnDisabled = true;

    @Getter
    private Set<ItemPojo> itemSelezionati;
    @Getter
    private String tipoElementoInLista;

    @Getter
    private String confirmDialogMessage;

    @Getter
    private String confirmSvuotamentoDialogMessage;

    @Getter
    private String meccanicaMaster;

    @Getter
    private String tipoElementoMaster;

    @Getter
    private String copiaIncollaWrongCodes;

    @Getter
    private Integer copiaIncollaValidCodes = 0;

    @Getter
    @Setter
    private Long idMasterAddPianificazione;

    @Getter
    private Long idMasterAddMeccanica;

    @Getter
    boolean addPlanningBtnDisabled = true;

    @Getter
    @Setter
    boolean meccanicaSelectDisabled = false;

    @Getter
    @Setter
    boolean tipoElementoSelectDisabled = false;

    @Getter
    @Setter
    boolean tipoElementoSelectRendered = false;

    PromoService promoservice;

    CfgPianificazioneService cfgPianificazioneService;

    UploadExcelService uploadExcelService;

    PianificazionePromoUtil pianificazionePromoUtil;

    PianificazioneService pianificazioneService;

    MarchioPrivatoService marchioPrivatoService;

    PianificazioneHelper pianificazioneHelper;

    TipoElementoService tipoElementoService;

    ConfigurationService configurationService;

    CfgConfHeaderService confHeaderService;

    MeccanicheUtil meccanicheUtil;

    PianificazioneSecurityUtil securityUtil;

    ItemService itemService;

    @Getter
    @Setter
    private String visualizzaStatus;

    byte[] fileContent;

    @Getter
    private List<ItemUpload> codes = new ArrayList<>();

    @Getter
    private Set<ItemUpload> codesUnique = new LinkedHashSet<>();

    @Getter
    private Set<ItemPojo> codesValid = new LinkedHashSet<>();

    @Getter
    private Set<ItemPojo> codesNotValid = new LinkedHashSet<>();

    private static final String TAB_SELEZIONE = "tab-selezione";
    private static final String TAB_UPLOAD = "tab-upload";
    private static final String TAB_COPIA_INCOLLA = "tab-copia-incolla";
    private static final String TOTALE = "TOTALE";
    private static final String EMPTY_CODE = " ";

    @Setter
    private Integer maxRows = ApplicationProperties.DEFAULT_MAX_CELLS;

    @Setter
    @Getter
    private boolean renderSelezionaGruppo;

    @Getter
    private String gruppoSelezionato;

    @Setter
    @Getter
    private boolean disableSelezionaGruppo;

    public PianificazionePromoDialogBackingBean(PromoService promoService,
                                                CfgPianificazioneService cfgPianificazioneService,
                                                UploadExcelService uploadExcelService,
                                                PianificazionePromoUtil pianificazionePromoUtil,
                                                PianificazioneService pianificazioneService,
                                                UserDTO userDTO,
                                                PianificazioneHelper pianificazioneHelper,
                                                TipoElementoService tipoElementoService,
                                                ConfigurationService configurationService,
                                                CfgConfHeaderService confHeaderService,
                                                MeccanicheUtil meccanicheUtil,
                                                PianificazioneSecurityUtil securityUtil,
                                                ItemService itemService,
                                                MarchioPrivatoService marchioPrivatoService) {
        this.promoservice = promoService;
        this.cfgPianificazioneService = cfgPianificazioneService;
        this.uploadExcelService = uploadExcelService;
        this.currentActiveTab = TAB_SELEZIONE;
        this.pianificazionePromoUtil = pianificazionePromoUtil;
        this.pianificazioneService = pianificazioneService;
        this.userDTO = userDTO;
        this.pianificazioneHelper = pianificazioneHelper;
        this.tipoElementoService = tipoElementoService;
        this.configurationService = configurationService;
        this.confHeaderService = confHeaderService;
        this.meccanicheUtil = meccanicheUtil;
        this.securityUtil = securityUtil;
        this.itemService = itemService;
        this.marchioPrivatoService = marchioPrivatoService;
        setVisualizzaStatus(PlanningRadioButtonEnum.TUTTO.getValue());
    }

    public void setIdMarchioPrivCorrente(String idMarchioPrivCorrente) {
        if (idMarchioPrivCorrente != null && idMarchioPrivCorrente.trim().isEmpty()) {
            idMarchioPrivCorrente = null;
        }
        this.idMarchioPrivCorrente = idMarchioPrivCorrente;
    }

    public void handleFileUpload(final FileUploadEvent event) {
        if (event != null && event.getFile() != null) {
            try {
                final File file = writeByte(event.getFile().getContents(), event.getFile().getFileName());
                fileContent = event.getFile().getContents();

                final ElementType tipoElemento = ElementType.fromDescription(idTipoElemento);
                final Long buyerId = idCompratoreCorrente != null ? Long.parseLong(idCompratoreCorrente) : null;
                this.codes = uploadExcelService.readFileUntilEmptyRow(tipoElemento, file, buyerId, maxRows);
                if (!this.codes.isEmpty()) {
                    final CfgConfHeaderEntity confHeader = confHeaderService
                            .findByMeccanicaIdAndSetPianificazioneId(idMeccanicaCorrente,
                                    promozioneCorrente.getMuiCfgSetPianificazione().getId());
                    if (confHeader != null && tipoElemento != null) {
                        // Check security
                        final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente,
                                getUserDTO().getGruppi());
                        switch (tipoElemento) {
                            case ARTICOLO:
                                codes.stream().filter(ItemUpload::isValid).forEach(code -> {
                                    if (code.getItemId() != null) {
                                        ItemEntity item = itemService.findById(code.getItemId());
                                        if (!buyerCodes.contains(item.getCompratoreEntity().getCodiceCompratore())) {
                                            code.setValid(false);
                                        }
                                    } else {
                                        code.setValid(false);
                                    }
                                });
                                break;
                            case TOTALE:
                            case REPARTO:
                            case GRM:
                                codes.stream().filter(ItemUpload::isValid).forEach(code -> {
                                    code.setValid(code.getItemId() != null);
                                });
                                break;
                            default:
                                codes.stream().filter(ItemUpload::isValid).forEach(code -> {
                                    code.setValid(Boolean.FALSE);
                                });
                                log.warn("Unhandled upload type " + tipoElemento.getDescription());
                                break;
                        }

                        // #4182: gestione flag duplica per canale
                        final Boolean duplicaFlagForCanale = pianificazioneHelper
                                .getDuplicaFlagForCanale(promozioneCorrente.getMuiCanalePromozione(), tipoElemento);
                        if (!Boolean.TRUE.equals(duplicaFlagForCanale)) {
                            final List<Long> usedItems = pianificazioneHelper
                                    .getUsedItems(promozioneCorrente, tipoElemento.getDescription());
                            codes.stream().filter(ItemUpload::isValid).forEach(code -> {
                                if (usedItems.contains(code.getItemId())) {
                                    code.setValid(false);
                                }
                            });
                        }

                        // Check if duplicated permitted for meccanica
                        final Boolean duplicaFlag = pianificazioneHelper.getDuplicaFlagForHeader(confHeader, tipoElemento);
                        if (duplicaFlag == null || !duplicaFlag) {
                            // Scarto elementi duplicati
                            final List<Long> usedItems = pianificazioneHelper.getUsedItems(promozioneCorrente,
                                    idMeccanicaCorrente, confHeader.getLivelloPianificazione().getCodice(),
                                    tipoElemento.getDescription());
                            codes.stream().filter(ItemUpload::isValid).forEach(code -> {
                                if (usedItems.contains(code.getItemId())) {
                                    code.setValid(false);
                                }
                            });
                        }
                    } else {
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento",
                                "Non e' stato possibile caricare il file; contattare l'assistenza."));
                        return;
                    }
                    // setto le variabili
                    this.codesNotValid = codes.stream().filter(item -> !item.isValid())
                            .map(item -> new ItemPojo(null, null, null, item.getDetailRowId()))
                            .collect(Collectors.toSet());
                    this.codesValid = codes
                            .stream().filter(ItemUpload::isValid).map(item -> new ItemPojo(item.getItemId(),
                                    item.getCompratoreId(), item.getElementDescription(), item.getDetailRowId()))
                            .collect(Collectors.toSet());
                    this.codesUnique = new HashSet<>(codes);
                }

                if (this.codesValid != null && !this.codesValid.isEmpty()) {
                    setConfirmPianificazioneDialogBtnDisabled(false);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Il File può essere caricato", ""));
                } else {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Nessun codice valido trovato all'interno del file", ""));
                }
            } catch (final Exception e) {
                log.error("Error handling file upload", e);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento", ""));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento",
                    "Non e' stato possibile caricare il file"));
        }
    }

    private File writeByte(byte[] bytes, String filename) {
        try {
            // Initialize a pointer
            // in file using OutputStream
            final File file = new File(filename);
            final OutputStream os = new FileOutputStream(file);

            // Starts writing the bytes in it
            os.write(bytes);
            os.flush();

            // Close the file
            os.close();
            return file;
        } catch (final Exception e) {
            throw new RuntimeException("write file error");
        }
    }

    public void onTabChange(TabChangeEvent event) {
        this.currentActiveTab = event.getTab().getId();
        log.debug("tab id = " + currentActiveTab);
        this.idCompratoreCorrente = null;
        this.idFornitoreCorrente = null;
        this.idRepartoCorrente = null;
        this.idCategoriaCorrente = null;
        this.idGrmCorrente = null;
        this.idMarchioPrivCorrente = null;
        setConfirmPianificazioneDialogBtnDisabled(true);
        this.itemSelezionati = new HashSet<>();
        this.copiaIncollaValidCodes = 0;
        this.copiaIncollaWrongCodes = null;
        this.codes = null;
        this.codesUnique = null;
        this.codesValid = null;
        this.codesNotValid = null;
    }

    public void readMeccaniche() {
        this.meccaniche = promozioneCorrente != null && promozioneCorrente.getPromozioneMeccanicheEntities() != null
                ? meccanicheUtil.getMeccanicheDisponibili(promozioneCorrente, getUserDTO().getCanali())
                : new LinkedList<>();

        // Se non ci sono meccaniche disabilitare il commandButton "Inserisci Nuova Pianificazione"
        addPlanningBtnDisabled = this.meccaniche.isEmpty();

        this.tipoElementi = new LinkedList<>();
    }

    private void readCompratori() {
        readCompratori(null);
    }

    private void readCompratori(String codiceGruppoSelezionato) {
        this.compratori = new LinkedHashMap<>();
        List<CompratoreEntity> buyers = new ArrayList<>();
        if (codiceGruppoSelezionato == null) {
            if (isUserAdmin()) {
                buyers.addAll(promoservice.findAllBuyers());
            } else {
                final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, userDTO.getGruppi());
                buyers.addAll(promoservice.findAllBuyersByCodes(buyerCodes));
            }
        } else if (userDTO.getGruppi().contains(codiceGruppoSelezionato)) {
            final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, Collections.singletonList(codiceGruppoSelezionato));
            buyers.addAll(promoservice.findAllBuyersByCodes(buyerCodes));
        }
        buyers.stream().sorted(Comparator.comparing(CompratoreEntity::getCodiceCompratore))
                .forEach(compratoreEntity ->
                        this.compratori.put(String.valueOf(compratoreEntity.getId()), String.format("[%s] %s",
                                compratoreEntity.getCodiceCompratore(), compratoreEntity.getDescrizione())));
    }

    private void readFornitori() {
        readFornitori(null);
    }

    private void readFornitori(String codiceGruppoSelezionato) {
        this.fornitori = new LinkedHashMap<>();
        List<FornitoreEntity> fornitori = new ArrayList<>();
        if (codiceGruppoSelezionato == null) {
            if (isUserAdmin()) {
                fornitori.addAll(promoservice.findAllFornitori());
            } else {
                final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, userDTO.getGruppi());
                fornitori.addAll(promoservice.findAllFornitoriByCodiciCompratore(buyerCodes));
            }
        } else if (userDTO.getGruppi().contains(codiceGruppoSelezionato)) {
            final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, Collections.singletonList(codiceGruppoSelezionato));
            fornitori.addAll(promoservice.findAllFornitoriByCodiciCompratore(buyerCodes));
        }
        fornitori.stream().sorted(Comparator.comparing(FornitoreEntity::getCodiceFornitore))
                .forEach(fornitoreEntity -> this.fornitori.put(String.valueOf(fornitoreEntity.getId()),
                        fornitoreEntity.getCodiceFornitore() == null ? ""
                                : String.format("[%s] %s", fornitoreEntity.getCodiceFornitore(),
                                fornitoreEntity.getDescrizione() != null
                                        ? fornitoreEntity.getDescrizione().toUpperCase()
                                        : "")));
    }

    private void readReparti() {
        readReparti(null);
    }

    private void readReparti(String codiceGruppoSelezionato) {
        this.reparti = new LinkedHashMap<>();
        List<RepartoEntity> reparti = new ArrayList<>();
        if (codiceGruppoSelezionato == null) {
            if (isUserAdmin()) {
                reparti.addAll(pianificazioneService.getAllReparti());
            } else {
                final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, userDTO.getGruppi());
                reparti.addAll(pianificazioneService.findAllRepartiByCodiciCompratore(buyerCodes));
            }
        } else if (userDTO.getGruppi().contains(codiceGruppoSelezionato)) {
            final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, Collections.singletonList(codiceGruppoSelezionato));
            reparti.addAll(pianificazioneService.findAllRepartiByCodiciCompratore(buyerCodes));
        }
        reparti.stream().sorted(Comparator.comparing(RepartoEntity::getCodiceReparto))
                .forEach(repartoEntity -> this.reparti.put(String.valueOf(repartoEntity.getId()),
                        repartoEntity.getCodiceReparto() == null ? ""
                                : String.format("[%s] %s", repartoEntity.getCodiceReparto(),
                                repartoEntity.getDescrizione() != null
                                        ? repartoEntity.getDescrizione().toUpperCase()
                                        : "")));
    }

    private void readCategorie() {
        readCategorie(null);
    }

    private void readCategorie(String codiceGruppoSelezionato) {
        this.categorie = new LinkedHashMap<>();
        List<CategoriaEntity> categorie = new ArrayList<>();
        if (codiceGruppoSelezionato == null) {
            if (isUserAdmin()) {
                categorie.addAll(promoservice.findAllCategorie());
            } else {
                final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, userDTO.getGruppi());
                categorie.addAll(promoservice.findAllCategorieByCodiciCompratore(buyerCodes));
            }
        } else if (userDTO.getGruppi().contains(codiceGruppoSelezionato)) {
            final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, Collections.singletonList(codiceGruppoSelezionato));
            categorie.addAll(promoservice.findAllCategorieByCodiciCompratore(buyerCodes));
        }
        categorie.stream().sorted(Comparator.comparing(CategoriaEntity::getCodiceCategoria))
                .forEach(categoriaEntity -> this.categorie.put(String.valueOf(categoriaEntity.getId()),
                        String.format("[%s] %s", categoriaEntity.getCodiceCategoria(),
                                categoriaEntity.getDescrizione() != null
                                        ? categoriaEntity.getDescrizione().toUpperCase()
                                        : "")));
    }

    private void readGrm() {
        readGrm(null);
    }

    private void readGrm(String codiceGruppoSelezionato) {
        this.grm = new LinkedHashMap<>();
        List<GrmEntity> grms = new ArrayList<>();
        if (codiceGruppoSelezionato == null) {
            if (isUserAdmin()) {
                grms.addAll(pianificazioneService.getAllGrmEntity());
            } else {
                final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, userDTO.getGruppi());
                grms.addAll(pianificazioneService.findAllGrmByCodiciCompratore(buyerCodes));
            }
        } else if (userDTO.getGruppi().contains(codiceGruppoSelezionato)) {
            final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, Collections.singletonList(codiceGruppoSelezionato));
            grms.addAll(pianificazioneService.findAllGrmByCodiciCompratore(buyerCodes));
        }
        grms.stream().sorted(Comparator.comparing(GrmEntity::getCodiceGrm))
                .forEach(grmEntity -> this.grm.put(String.valueOf(grmEntity.getId()),
                        grmEntity.getCodiceGrm() == null ? ""
                                : String.format("[GRM_%s] %s", grmEntity.getCodiceGrm(),
                                grmEntity.getDescrizione() != null
                                        ? grmEntity.getDescrizione().toUpperCase()
                                        : "")));
    }

    private void readMarchioPrivato() {
        readMarchioPrivato(null);
    }

    private void readMarchioPrivato(String codiceGruppoSelezionato) {
        this.marchioPrivato = new LinkedHashMap<>();
        List<MarchioPrivatoEntity> marchiPrivati = new ArrayList<>();
        if (codiceGruppoSelezionato == null) {
            if (isUserAdmin()) {
                marchiPrivati.addAll(marchioPrivatoService.findAll());
            } else {
                final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, userDTO.getGruppi());
                if (buyerCodes == null || buyerCodes.isEmpty()) {
                    log.warn(String.format("Nessun codice compratore in write recuperato per user %s (id: %d)",
                            userDTO.getUsermame(), userDTO.getUser().getId()));
                } else {
                    final List<String> codiciMarchioPrivato = itemService.findCodiceMarchioPrivatoByCompratori(buyerCodes);
                    marchiPrivati.addAll(marchioPrivatoService.findByCodici(codiciMarchioPrivato));
                }
            }
        } else if (userDTO.getGruppi().contains(codiceGruppoSelezionato)) {
            final List<String> buyerCodes = securityUtil.getWritableCompratori(promozioneCorrente, Collections.singletonList(codiceGruppoSelezionato));
            if (buyerCodes == null || buyerCodes.isEmpty()) {
                log.warn(String.format("Nessun codice compratore in write recuperato per user %s (id: %d)",
                        userDTO.getUsermame(), userDTO.getUser().getId()));
            } else {
                final List<String> codiciMarchioPrivato = itemService.findCodiceMarchioPrivatoByCompratori(buyerCodes);
                marchiPrivati.addAll(marchioPrivatoService.findByCodici(codiciMarchioPrivato));
            }
        }
        marchiPrivati.stream().sorted(Comparator.comparing(MarchioPrivatoEntity::getKey))
                .forEach(m -> this.marchioPrivato.put(m.getKey(), String.format("[%s] %s", m.getKey(), m.getLabel())));
    }

    private void readTipoElementoFromMeccanica() {
        readTipoElementoFromMeccanica(true);
    }
    private void readTipoElementoFromMeccanica(boolean callbackJs) {
        try {
            if (idMasterAddPianificazione == null) {
                this.tipoElementi = tipoElementoService.findTipoElemento(this.idMeccanicaCorrente,
                        this.promozioneCorrente.getMuiCfgSetPianificazione().getId());
            } else {
                final PromozionePianificazioneEntity pianificazioneEntity = this.promozioneCorrente
                        .getPromozionePianificazioneEntities().stream()
                        .filter(p -> idMasterAddPianificazione.equals(p.getId())).findFirst().orElse(null);
                this.tipoElementi = pianificazioneHelper.findTipoElementiForPianificazione(pianificazioneEntity);
            }
        } catch (final Exception ex) {
            log.error(String.format("Error finding list of 'TIPO ELEMENTO' with meccanicaId=%d and promozioneId=%d",
                    this.idMeccanicaCorrente, this.promozioneCorrente.getId()), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore configurazione promozione selezionata, contattare l'assistenza tecnica"));
            return;
        }
        // la gestione della duplicazione del totale e' diversa : tutto o niente
        if (pianificazioneHelper.duplicaTotaleShouldRemoveTipoElementoTotale(idMeccanicaCorrente, promozioneCorrente)) {
            this.tipoElementi.remove(ElementType.TOTALE.name());
        }
        this.idTipoElemento = null;
        this.idCompratoreCorrente = null;
        setConfirmPianificazioneDialogBtnDisabled(true);
        this.itemSelezionati = new HashSet<>();
        this.tipoElementoInLista = null;
        updateTipoElementoComboBox(callbackJs);
        executeScript("hideTabView()");
    }

    public void prepareDialog() {
        if (this.codes != null) {
            this.codes.clear();
        } else {
            this.codes = new ArrayList<>();
        }
        if (this.codesUnique != null) {
            this.codesUnique.clear();
        } else {
            this.codesUnique = new LinkedHashSet<>();
        }
        if (this.codesValid != null) {
            this.codesValid.clear();
        } else {
            this.codesValid = new LinkedHashSet<>();
        }
        if (this.codesNotValid != null) {
            this.codesNotValid.clear();
        } else {
            this.codesNotValid = new LinkedHashSet<>();
        }

        setMeccanicaSelectDisabled(false);
        setTipoElementoSelectDisabled(false);

        updatePromozioneCorrenteAfterAddOrRemovePianificazione();
        readMeccaniche();
        // Controllo se dalla meccaniche appena caricate devo escludere quelle che hanno raggiunto MaxSet
        // solo se idMasterAddPianificazione==null
        // sono nelle situazione: sto creando una nuova pianificazione e NON aggiungendo ad una esistente
        if (idMasterAddPianificazione == null) {
            this.meccaniche = this.meccaniche.stream()
                    .filter(m -> pianificazioneHelper.hasMeccanicaSetSlots(promozioneCorrente, m))
                    .collect(Collectors.toList());
        }

        this.idMeccanicaCorrente = null;
        resetTabElementi();
        setConfirmPianificazioneDialogBtnDisabled(true);
        this.itemSelezionati = new HashSet<>();
        this.tipoElementoInLista = null;
        this.idTipoElemento = null;
        updateSelezioneGrid();
        updateMeccanicaComboBox();
        updateTipoElementoComboBox();
        this.currentActiveTab = TAB_SELEZIONE;

        prepareSelezioneGruppo(null);
    }

    /**
     * prepara il dialog per l'inserimento di una riga di pianificazione
     */
    public void addPlanningRow() {
        currentActiveTab = TAB_SELEZIONE;
        boolean refreshDropdowns = false;
        final Map<String, String> params = getRequestParameterMap();
        final Long idMasterPromoPianificazione = params.get("idPromoPianificazione") != null
                && !params.get("idPromoPianificazione").isEmpty()
                ? Long.parseLong(params.get("idPromoPianificazione"))
                : 0;
        final PromozionePianificazioneEntity pianificazioneMaster = this.promozioneCorrente
                .getPromozionePianificazioneEntities().stream()
                .filter(p -> idMasterPromoPianificazione.equals(p.getId())).findFirst().orElse(null);
        if (pianificazioneMaster == null) {
            log.warn(String.format("Pianificazione con id %s non trovata", idMasterPromoPianificazione));
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione",
                    "Errore aggiunta pianificazione; contattare l'assistenza tecnica"));
            return;
        } else if (pianificazioneMaster.getMeccanicaEntity() == null) {
            log.warn(String.format("Meccanica associata alla pianificazione con id %s non trovata",
                    idMasterPromoPianificazione));
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione",
                    "Errore aggiunta pianificazione; contattare l'assistenza tecnica"));
            return;
        }
        this.idMasterAddPianificazione = idMasterPromoPianificazione;

        resetTabElementi(pianificazioneMaster.getCodiceGruppo());
        setIdMeccanicaCorrente(pianificazioneMaster.getMeccanicaEntity().getId(), false);
        setMeccanicaSelectDisabled(true);
        setConfirmPianificazioneDialogBtnDisabled(true);
        // Tipo elemento omogeneo all'interno del raggruppamento, disabilito la select
        // 'Tipo elemento'
        if (PlanningLevelEnum.RAGGRUPPAMENTO.getCode().equals(pianificazioneMaster.getTipoRiga().getCodiceTipo())
                && pianificazioneHelper.isTipoElementoOmogeneo(pianificazioneMaster)) {
            final PromozionePianificazioneEntity firstChild = pianificazioneMaster.getChildren().stream().findFirst()
                    .orElse(null);
            if (firstChild != null) {
                setIdTipoElemento(firstChild.getTipoElemento(), false);
                setTipoElementoSelectDisabled(true);
            } else {
                setIdTipoElemento(null, false);
                setTipoElementoSelectDisabled(false);
            }
            refreshDropdowns = true;
        } else {
            setIdTipoElemento(null, false);
            setTipoElementoSelectDisabled(false);
        }
        updateTipoElementoComboBox(false);
        setTipoElementoSelectRendered(true);
        prepareSelezioneGruppo(pianificazioneMaster.getCodiceGruppo(), refreshDropdowns, false);
        executeScript(prepareJsCall());
        executeScript("PF('pianificazionePromoDialog').show();");
    }

    public StreamedContent getDownloadTemplate() {
        final ElementType tipoElemento = ElementType.fromDescription(idTipoElemento);
        final StringBuffer filename = new StringBuffer("caricamento_");
        switch (tipoElemento) {
            case ARTICOLO:
                filename.append("articoli");
                break;
            case REPARTO:
                filename.append("reparti");
                break;
            case GRM:
                filename.append("grm");
                break;
        }
        filename.append("_template.xltx");
        try {
            final InputStream stream = this.getClass().getClassLoader()
                    .getResourceAsStream("TEMPLATE/" + filename.toString());
            if (stream == null) {
                log.error(String.format("Error getting template %s", filename.toString()));
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Non e' stato possibile scaricare il template; contattare l'assistenza tecnica"));
                return null;
            }
            return new DefaultStreamedContent(stream,
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.template",
                    filename.toString());
        } catch (Exception ex) {
            log.error(String.format("Error getting template %s", filename), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Non e' stato possibile scaricare il template; contattare l'assistenza tecnica"));
            return null;
        }
    }

    private void updateTipoElementoComboBox(){
        updateTipoElementoComboBox(true);
    }
    private void updateTipoElementoComboBox(boolean callbackJs) {
        if (this.tipoElementi.size() == 1) {
            setIdTipoElemento(this.tipoElementi.get(0),callbackJs);
            setTipoElementoSelectDisabled(true);
        } else {
            setTipoElementoSelectDisabled(false);
        }
    }

    private void updateMeccanicaComboBox() {
        if (this.meccaniche.size() == 1) {
            setIdMeccanicaCorrente(this.meccaniche.get(0).getId());
            setMeccanicaSelectDisabled(true);
        }
    }

    public void setPromozioneCorrente(PromozioneTestataEntity promozioneCorrente) {
        this.promozioneCorrente = promozioneCorrente;
        this.title = this.promozioneCorrente != null && this.promozioneCorrente.getDescrizioneEstesa() != null
                ? this.promozioneCorrente.getDescrizioneEstesa()
                : "";
        this.idCompratoreCorrente = null;
        this.idFornitoreCorrente = null;
        this.idCategoriaCorrente = null;
        this.idRepartoCorrente = null;
        this.idGrmCorrente = null;
        this.idMarchioPrivCorrente = null;
        this.idTipoElemento = null;
        if (this.promozioneCorrente == null) {
            addPlanningBtnDisabled = true;
        }
    }

    public void setIdTipoElemento(String idTipoElemento) {
        setIdTipoElemento(idTipoElemento, true);
    }
    private void setIdTipoElemento (String idTipoElemento, boolean callbackJs) {
        this.idTipoElemento = idTipoElemento;

        setConfirmPianificazioneDialogBtnDisabled(!TOTALE.equalsIgnoreCase(this.idTipoElemento));
        // Reset filtri articolo
        this.idCompratoreCorrente = null;
        this.idFornitoreCorrente = null;
        this.idCategoriaCorrente = null;
        this.idRepartoCorrente = null;
        this.idGrmCorrente = null;
        this.idMarchioPrivCorrente = null;
        itemSelezionati = new HashSet<>();
        if ( callbackJs )
            executeScript(prepareJsCall());
    }

    public void updateSelezioneGrid() {
        executeScript(prepareJsCall());
    }

    private String prepareJsCall() {
        return String.format("updateSelezioneGrid('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s)",
                idTipoElemento, idCompratoreCorrente, idFornitoreCorrente, idRepartoCorrente, idCategoriaCorrente,
                idGrmCorrente, idMarchioPrivCorrente, gruppoSelezionato, convertCollectionToJsonArray(itemSelezionati));
    }

    public void showTabView() {
        itemSelezionati = new HashSet<>();
        if (idMeccanicaCorrente != null && idTipoElemento != null && !idTipoElemento.equals(TOTALE)) {
            executeScript("renderTabView('" + idTipoElemento + "')");
        }
    }

    public void setIdMeccanicaCorrente(Long idMeccanicaCorrente) {
        setIdMeccanicaCorrente(idMeccanicaCorrente, true);
    }

    private void setIdMeccanicaCorrente(Long idMeccanicaCorrente, boolean callbackJs) {
        this.idMeccanicaCorrente = idMeccanicaCorrente;
        setIdTipoElemento(null, callbackJs);
        setGruppoSelezionato(null, callbackJs);
        setRenderSelezionaGruppo(false);
        if (this.idMeccanicaCorrente == null) {
            setConfirmPianificazioneDialogBtnDisabled(true);
        } else if (this.idMasterAddPianificazione == null) {
            if ( canAddSelezionaGruppo(idMeccanicaCorrente) )
                inizializzaSelezioneGruppo();
            final boolean isLevelSet = handleTipoElementoVisibility(idMeccanicaCorrente);
            if (!isLevelSet) {
                readTipoElementoFromMeccanica(callbackJs);
            } else {
                executeScript("hideTabView()");
            }
        } else {
            readTipoElementoFromMeccanica(callbackJs);
        }
    }

    public void setConfirmPianificazioneDialogBtnDisabled(boolean confirmPianificazioneDialogBtnDisabled) {
        if (confirmPianificazioneDialogBtnDisabled != this.confirmPianificazioneDialogBtnDisabled) {
            log.debug("changing confirm button status to "
                    + (confirmPianificazioneDialogBtnDisabled ? " disabled " : " enabled "));
        }
        this.confirmPianificazioneDialogBtnDisabled = confirmPianificazioneDialogBtnDisabled;
    }

    public boolean confirmPianificazione() {
        final MeccanicheEntity meccanica = getMeccanicaFromId(idMeccanicaCorrente);
        if (meccanica == null) {
            final String msg = String.format("La meccanica con id %s non è associata alla promozione con id %s",
                    idMeccanicaCorrente, promozioneCorrente != null ? promozioneCorrente.getId() : null);
            log.warn(msg);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore salvataggio pianificazione promozione selezionata, contattare l'assistenza tecnica"));
            return false;
        }
        // controlla che in qualche modo non stia inserendo una meccanica a totale quando non posso
        if (pianificazioneHelper.duplicaTotaleShouldRemoveTipoElementoTotale(idMeccanicaCorrente, promozioneCorrente) && ElementType.TOTALE.equals(idTipoElemento)) {
            final String message = String.format("La configurazione del canale %s non permette l'iserimento del TOTALE per la meccanica %s", promozioneCorrente.getMuiCanalePromozione().getDescrizione(), meccanica.getCodiceMeccanica());
            log.error(message);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    message + "\nContattare l'assistenza tecnica"));
            return false;

        }
        final Set<ItemPojo> items = TAB_UPLOAD.equals(currentActiveTab) ? codesValid : itemSelezionati;
        boolean isSaved = false;
        if (idMasterAddPianificazione == null) {
            final MeccanicheEntity meccanicaEntity = getMeccanicaFromId(idMeccanicaCorrente);
            isSaved = pianificazioneHelper.savePromoPianificazione(promozioneCorrente, meccanicaEntity, items,
                    idTipoElemento, getCurrentUser().getName(), gruppoSelezionato);
        } else {
            final PromozionePianificazioneEntity pianificazioneMaster = pianificazioneService
                    .getPromoPianificazoneById(idMasterAddPianificazione);
            if (pianificazioneMaster != null) {
                isSaved = pianificazioneHelper.savePromoAddPianificazione(pianificazioneMaster, items, idTipoElemento,
                        getCurrentUser().getName(), gruppoSelezionato);
            } else {
                log.warn(String.format("Pianificazione con id %s non trovata", idMasterAddPianificazione));
            }
        }

        if (isSaved) {
            addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", "Pianificazione promozione salvata"));
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore salvataggio pianificazione promozione selezionata, contattare l'assistenza tecnica"));
        }
        resetDialog();
        // 1. rileggere la testata
        promozioneCorrente = promoservice.findById(promozioneCorrente.getId());
        // 2. dobbiamo aggiornare data ultima modifica della testata
        try {
            promozioneCorrente = promoservice.persist(promozioneCorrente, userDTO.getUsermame());
        } catch (Exception e) {
            log.error("Errore durante l'aggiornamento della data ultima modifica per la promozione " + promozioneCorrente.getCodicePromozione());
        }
        return isSaved;
    }

    public void resetDialog() {
        this.idMeccanicaCorrente = 0L;
        this.idCompratoreCorrente = null;
        this.idFornitoreCorrente = null;
        this.idCategoriaCorrente = null;
        this.idRepartoCorrente = null;
        this.idGrmCorrente = null;
        this.idMarchioPrivCorrente = null;
        this.idTipoElemento = null;
        this.confirmPianificazioneDialogBtnDisabled = true;
        this.tipoElementoInLista = null;
        this.itemSelezionati = new HashSet<>();
        this.idMasterAddPianificazione = null;
        this.idMasterAddMeccanica = null;
        this.meccanicaMaster = null;
        this.tipoElementoMaster = null;
    }

    private void handleCopyPasteTab(final JsonNode data) {
        if (data.isArray()) {
            itemSelezionati.clear();

            for (final JsonNode node : data) {
                if (node.get("isValidCode") != null && node.get("isValidCode").asBoolean()) {
                    // elemento validato

                    itemSelezionati.add(new ItemPojo(node.get("idItem").asLong(),
                            node.get("itemBuyerId") != null ? node.get("itemBuyerId").asLong() : null,
                            node.get("descrizione") != null && node.get("descrizione").get("value") != null
                                    ? node.get("descrizione").get("value").asText()
                                    : null,
                            node.get("idItem").asText()));
                }
            }
        }
    }

    public void selezionaItems() {
        final Map<String, String> params = getRequestParameterMap();
        final String itemSelectedJsonArray = params.get("itemSelected");
        /**
         * se copia e incolla, fatti i cazzi tuoi e ti arriva un array con i rowdata
         */
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemSelectedJsonArray);

            if (jsonNode != null) {
                if (TAB_SELEZIONE.equals(currentActiveTab)) {
                    if (ElementType.GRM.name().equalsIgnoreCase(idTipoElemento)
                            || ElementType.REPARTO.name().equalsIgnoreCase(idTipoElemento)) {
                        // Da frontend sono passati solo gli item selected, quindi la lista deve essere
                        // ripopolata
                        itemSelezionati = new HashSet<>();
                        jsonNode.forEach(this::selezionaSingleItem);
                    } else if (ElementType.ARTICOLO.name().equalsIgnoreCase(idTipoElemento)
                            || ElementType.ATTRIBUTO.name().equalsIgnoreCase(idTipoElemento)) {
                        // Da frontend sono ritornati tutti gli item filtrati, i selected vanno aggiunti
                        // i deselected eliminati dai selezionati
                        // E' necessario mantenere lo storico di tutti gli item selezionati in funzione
                        // dei filtri
                        if (itemSelezionati != null) {
                            itemSelezionati.clear();
                        } else {
                            itemSelezionati = new HashSet<>();
                        }
                        jsonNode.forEach(this::selezionaSingleItem);
                    }
                } else if (TAB_COPIA_INCOLLA.equals(currentActiveTab)) {
                    handleCopyPasteTab(jsonNode);
                }
            }

            // confirm Btn
            if (TAB_SELEZIONE.equals(currentActiveTab)) {
                this.confirmPianificazioneDialogBtnDisabled = itemSelezionati == null || itemSelezionati.isEmpty();
            } else if (TAB_COPIA_INCOLLA.equals(currentActiveTab)) {
                this.confirmPianificazioneDialogBtnDisabled = (itemSelezionati.size() <= 0);
            }
            executeScript("refreshBtnConfitmJS()");

        } catch (final IOException e) {
            log.error("Error read json item selected", e);
        }

    }

    private void selezionaSingleItem(JsonNode jsonNode) {

        if (jsonNode.size() == 2 && TAB_COPIA_INCOLLA.equals(currentActiveTab)) {

            final String validCodes = jsonNode.get("validCodes") != null ? jsonNode.get("validCodes").asText() : "";
            if (!validCodes.isEmpty()) {
                copiaIncollaValidCodes = validCodes.contains(";") ? validCodes.split(";").length : 1;
                filterValidCodeCopiaIncolla(validCodes);
            } else {
                itemSelezionati.clear();
                copiaIncollaValidCodes = 0;
            }

            copiaIncollaWrongCodes = jsonNode.get("wrongCodes") != null
                    && !EMPTY_CODE.equals(jsonNode.get("wrongCodes").asText())
                    ? jsonNode.get("wrongCodes").asText()
                    : null;

        } else {

            if (TAB_COPIA_INCOLLA.equals(currentActiveTab)) {
                final String validCodes = jsonNode.get("validCodes") != null ? jsonNode.get("validCodes").asText() : "";
                if (!validCodes.isEmpty()) {
                    copiaIncollaValidCodes = validCodes.contains(";") ? validCodes.split(";").length : 1;
                    filterValidCodeCopiaIncolla(validCodes);
                } else {
                    itemSelezionati.clear();
                    copiaIncollaValidCodes = 0;
                }
            }

            final String id = jsonNode.get("idItem") != null ? jsonNode.get("idItem").asText() : "";
            final boolean isItemSelected = jsonNode.get("selected").asBoolean();
            final String elementDescription = jsonNode.get("elementDescription") != null
                    ? jsonNode.get("elementDescription").asText()
                    : "";

            if (TAB_COPIA_INCOLLA.equalsIgnoreCase(this.currentActiveTab)) {
                if (ElementType.ARTICOLO.name().equalsIgnoreCase(idTipoElemento)) {
                    idCompratoreCorrente = jsonNode.get("itemBuyerId") != null ? jsonNode.get("itemBuyerId").asText()
                            : null;
                }

                copiaIncollaWrongCodes = jsonNode.get("wrongCodes") != null
                        && !EMPTY_CODE.equals(jsonNode.get("wrongCodes").asText()) ? jsonNode.get("wrongCodes").asText()
                        : null;
            }
            if (id != null) {
                final Long key = Long.parseLong(id);
                itemSelezionati.add(new ItemPojo(key, null, elementDescription, id));
            } else {
                log.error("missing parameters: id: null; selected: " + isItemSelected);
            }
        }
    }

    private void filterValidCodeCopiaIncolla(String validCodes) {
        final List<ItemPojo> tmpList = itemSelezionati.stream().collect(Collectors.toList());
        tmpList.forEach(item -> {
            if (!validCodes.contains(item.getElementDescription().split("-")[0].trim())) {
                itemSelezionati.remove(item);
            }
        });
    }

    private String convertCollectionToJsonArray(Set<ItemPojo> collection) {
        if (collection != null) {
            return new StringBuffer().append("[")
                    .append(collection.stream().map(ItemPojo::toJson).collect(Collectors.joining(","))).append("]")
                    .toString();
        } else {
            return "[]";
        }
    }

    public void defineDialogMessage() {

        final Map<String, String> params = getRequestParameterMap();

        final String tipoElemento = params.get("tipoElemento") != null ? params.get("tipoElemento") : "";
        final Boolean isMaster = Boolean.parseBoolean(params.get("master"));
        final Integer masterDetails = params.get("masterDetails") != null && !params.get("masterDetails").isEmpty()
                ? Integer.parseInt(params.get("masterDetails"))
                : 0;
        final String nomeElemento = params.get("nomeElemento") != null ? params.get("nomeElemento") : "";
        final String descrizioneMeccanica = params.get("descrizioneMeccanica") != null
                ? params.get("descrizioneMeccanica")
                : "";
        final String numRaggruppamento = params.get("numRaggruppamento") != null ? params.get("numRaggruppamento") : "";
        final Integer minRaggruppamento = params.get("minRaggruppamento") != null ? Integer.parseInt(params.get("minRaggruppamento")) : 0;
        final Integer fratelliDelMaster = params.get("fratelliDelMaster") != null ? Integer.parseInt(params.get("fratelliDelMaster")) : 0;
        final String tipoRiga = params.get("tipoRiga") != null ? params.get("tipoRiga") : "";

        final PlanningLevelEnum tipoRigaEnum = PlanningLevelEnum.fromDescription(tipoRiga);
        if (tipoRigaEnum != null) {
            switch (tipoRigaEnum) {
                case ELEMENTO:
                    confirmDialogMessage = !masterDetails.equals(1) || minRaggruppamento.equals(fratelliDelMaster)
                            ? String.format("Sei sicuro di voler cancellare l'elemento %s con meccanica %s ?",
                            nomeElemento, descrizioneMeccanica)
                            : String.format("La cancellazione dell'elemento %s con meccanica %s" + " comporta la cancellazione del raggruppamento %s. Vuoi continuare ?",
                            nomeElemento, descrizioneMeccanica, numRaggruppamento);
                    break;
                case RAGGRUPPAMENTO:
                    confirmDialogMessage = isMaster
                            ? "Sei sicuro di voler cancellare il raggruppamento e tutti i suoi elementi ?"
                            : !masterDetails.equals(1)
                            ? String.format("Sei sicuro di voler cancellare l'elemento %s con meccanica %s ?",
                            nomeElemento, descrizioneMeccanica)
                            : "La cancellazione dell'elemento < NOME ELEMENTO > con meccanica < DESCRIZIONE MECCANICA >"
                            + " comporta la cancellazione del raggruppamento < NUM_RAGGRUPPAMENTO >. Vuoi continuare ?";
                    break;
                case SET:
                    confirmDialogMessage = isMaster
                            ? String.format("Sei sicuro di voler cancellare la riga master %s e tutte le righe di dettaglio ?",
                            tipoElemento)
                            : masterDetails > 1
                            ? String.format("Sei sicuro di voler cancellare la riga %s ?", nomeElemento)
                            : "Stai per cancellare l'ultimo elemento di dettaglio: "
                            + "la cancellazione comporta anche la rimozione della riga master. Vuoi proseguire ?";
                    break;
                default:
                    log.warn(String.format("Tipo riga <%s> non riconosciuto", tipoRiga));
                    break;
            }

            executeScript("PF('deletePlanningRowGlobalConfirmDialog').show();");
        }
    }

    public void defineSvuotamentoDialogMessage() {

        final Map<String, String> params = getRequestParameterMap();

        final String tipoRiga = params.get("tipoRiga") != null ? params.get("tipoRiga") : "";

        if (PlanningLevelEnum.RAGGRUPPAMENTO.getDescription().equals(tipoRiga)) {
            confirmSvuotamentoDialogMessage = "Sei sicuro di voler cancellare tutti gli elementi del raggruppamento ?";
        }

        executeScript("PF('emptyPlanningRowGlobalConfirmDialog').show();");
    }

    public void enabledAddPlanningBtn() {
        updatePromozioneCorrenteAfterAddOrRemovePianificazione();
        readMeccaniche();
    }

    public void updatePromozioneCorrenteAfterAddOrRemovePianificazione() {
        try {
            setPromozioneCorrente(
                    promozioneCorrente != null ? this.promoservice.findById(promozioneCorrente.getId()) : null);
        } catch (final Exception e) {
            log.error("error retrieving promozioneTestata with id " + promozioneCorrente.getId(), e);
        }
    }

    // Variabile necessaria per mantenere correttamente lo stato dei radio button al
    // cambio di tab e
    // se non sono modificati (this.visualizzaStatus == null)
    private String cambioTabStatus = PlanningRadioButtonEnum.TUTTO.getValue();

    public void onTabPlanningChange(TabChangeEvent event) {
        this.cambioTabStatus = this.visualizzaStatus != null ? this.visualizzaStatus : this.cambioTabStatus;
        this.visualizzaStatus = this.visualizzaStatus != null ? this.visualizzaStatus : this.cambioTabStatus;
    }

    /**
     * Gestione pulsante Conferma e visibilità Tipo Elemento al cambio della
     * meccanica selezionata; - se la meccanica è un 'SET', abilito il pulsante e
     * nascondo la select 'Tipo Elemento' - altrimenti, disabilito il pulsante e
     * mostro la select
     *
     * @param idMeccanicaCorrente id meccanica selezionata
     */
    private boolean handleTipoElementoVisibility(Long idMeccanicaCorrente) {
        boolean isLevelSet = false;
        final MeccanicheEntity meccanicaEntity = getMeccanicaFromId(idMeccanicaCorrente);
        if (meccanicaEntity != null) {
            isLevelSet = pianificazioneHelper.isMeccanicaOnLevelSet(meccanicaEntity,
                    promozioneCorrente.getMuiCfgSetPianificazione().getId());
            setConfirmPianificazioneDialogBtnDisabled(!isLevelSet);
            setTipoElementoSelectRendered(!isLevelSet);
        }
        return isLevelSet;
    }

    /**
     * Ritorna una MeccanicaEntity dalla lista delle meccaniche caricate partendo
     * dal id
     *
     * @param idMeccanicaCorrente id meccanica da cercare
     * @return meccanica con il dato id, altrimenti null
     */
    private MeccanicheEntity getMeccanicaFromId(Long idMeccanicaCorrente) {
        if (idMeccanicaCorrente == null) {
            return null;
        }
        return this.meccaniche.stream().filter(m -> m.getId().equals(idMeccanicaCorrente)).findFirst().orElse(null);
    }

    private void resetTabElementi() {
        resetTabElementi(null);
    }
    private void resetTabElementi(String gruppoSelezionato) {
        readCompratori(gruppoSelezionato);
        readFornitori(gruppoSelezionato);
        readReparti(gruppoSelezionato);
        readCategorie(gruppoSelezionato);
        readGrm(gruppoSelezionato);
        readMarchioPrivato(gruppoSelezionato);
        this.idCompratoreCorrente = null;
        this.idFornitoreCorrente = null;
        this.idRepartoCorrente = null;
        this.idCategoriaCorrente = null;
        this.idGrmCorrente = null;
        this.idMarchioPrivCorrente = null;
    }

    private void prepareSelezioneGruppo(String gruppo) {
        prepareSelezioneGruppo(gruppo, true, true);
    }
    private void prepareSelezioneGruppo(String gruppo, boolean refreshDropdown, boolean callbackJs) {
        if (promozioneCorrente != null && promozioneCorrente.getCanalePromozioneEntity().getFlSicurezzaSet()) {
            setGruppoSelezionato(gruppo, callbackJs);
            setDisableSelezionaGruppo(true);
            setRenderSelezionaGruppo(false);
            if ( refreshDropdown) {
                // se e' attiva la sicurezza, filtro le selezioni
                // filtro i compratori
                readCompratori(gruppo);
                // filtro i fornitori
                readFornitori(gruppo);
                // filtro i reparti
                readReparti(gruppo);
                // filtro le categorie
                readCategorie(gruppo);
                // filtro i grm
                readGrm(gruppo);
            }
        }
    }

    private void inizializzaSelezioneGruppo() {
        // qui devo leggere il parametro del canale ed eventualmente abilitare
        // la dropdown dei gruppi utente e disabilitare il conferma finche' non e'
        // selezionato almeno un gruppo

        //TODO: verificare se devo filtrare le dropdown
        if (getPromozioneCorrente().getCanalePromozioneEntity().getFlSicurezzaSet()) {
            if (!getUserDTO().getGruppi().isEmpty()) {
                prepareSelezioneGruppo(getUserDTO().getGruppi().get(0));
                if (getUserDTO().getGruppi().size() != 1) {
                    setRenderSelezionaGruppo(true);
                    setDisableSelezionaGruppo(false);
                }
            }
        }
    }

    private boolean canAddSelezionaGruppo(Long idMeccanica) {
        boolean result = false;

        final MeccanicheEntity meccanicaEntity = getMeccanicaFromId(idMeccanicaCorrente);
        if (meccanicaEntity != null) {
            result =
                    pianificazioneHelper.isMeccanicaOnLevelSet(meccanicaEntity, promozioneCorrente.getMuiCfgSetPianificazione().getId()) ||
                            pianificazioneHelper.isMeccanicaOnLevelRaggruppamento(meccanicaEntity, promozioneCorrente.getMuiCfgSetPianificazione().getId());
        }
        return result;
    }

    public void setGruppoSelezionato(String gruppoSelezionato){
        setGruppoSelezionato(gruppoSelezionato, true);
    }
    public void setGruppoSelezionato(String gruppoSelezionato, boolean callbackJs){
        this.gruppoSelezionato = gruppoSelezionato;
        // il valore e' visibile e sto lavorando con un reparto allora devo filtrare le dropdown
        if ( this.gruppoSelezionato != null && this.renderSelezionaGruppo &&
            pianificazioneHelper.isMeccanicaOnLevelRaggruppamento(getMeccanicaFromId(idMeccanicaCorrente), promozioneCorrente.getMuiCfgSetPianificazione().getId())
        ){
            if (
                    callbackJs && (
                        idTipoElemento != null ||
                        idCompratoreCorrente != null ||
                        idFornitoreCorrente != null ||
                        idRepartoCorrente != null  ||
                        idCategoriaCorrente != null ||
                        idGrmCorrente != null ||
                        idMarchioPrivCorrente != null
                    )
            ) {
                executeScript(prepareJsCall());
            }
        }

    }

    public boolean getUploadTabRendered() {
        if (idTipoElemento == null) {
            return false;
        }
        return !idTipoElemento.equalsIgnoreCase(ElementType.ATTRIBUTO.name());
    }

    public boolean getCopyPasteTabRendered() {
        if (idTipoElemento == null) {
            return false;
        }
        return !idTipoElemento.equalsIgnoreCase(ElementType.ATTRIBUTO.name());
    }
}
