package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.actions.ActionTypeEnum;
import com.axiante.mui.dbpromo.actions.ElementFieldEnum;
import com.axiante.mui.dbpromo.actions.FormEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningRadioButtonEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.business.service.UploadExcelService;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.CfgInclusioniEsclusioniService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.dbpromo.persistence.service.GrmService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.MeccanicaService;
import com.axiante.mui.dbpromo.persistence.service.MuiInclusioneEsclusioneService;
import com.axiante.mui.dbpromo.persistence.service.MuiPlanoDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiTransizioneService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.dbpromo.persistence.service.TipoElementoService;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.MeccanicheUtil;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.utils.RoleMenuUtil;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.CastellettoMessaggi;
import com.axiante.mui.webapp.views.content.dbpromo.data.InclusioneEsclusioneBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.MessaggiCastellettiBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.PianificazionePlanoRiferimentoBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.PianificazionePromoDialogBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.PianificazionePromoRiferimentoBackingBean;
import com.axiante.mui.webapp.views.content.enumeration.TabEnum;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.QueryTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@MuiViewModel("pianificazionePromo")
@Dependent
@Slf4j
public class PianificazionePromoView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -4985183993092027769L;
    private static final String INCLUSIONE_ESCLUSIONE_TAB_CLASS_NONE = "none";
    private static final String INCLUSIONE_ESCLUSIONE_TAB_CLASS_ORANGE = "#FF5F15 !important";

    @Inject
    @Getter
    private MessaggiCastellettiBean messaggiCastellettiBean;

    @Inject
    private Instance<MuiService> muiServiceInstance;

    @Inject
    private PromoService promoService;

    @Inject
    private PianificazioneService pianificazioneService;

    @Inject
    private CfgPianificazioneService cfgPianificazioneService;

    @Inject
    private PromoStatiTransizioneService promoStatiTransizioneService;

    @Inject
    private UploadExcelService uploadExcelService;

    @Inject
    private PlanningCommons planningCommons;

    @Inject
    private PianificazionePromoUtil pianificazionePromoUtil;

    @Inject
    private PianificazioneHelper pianificazioneHelper;

    @Inject
    private TipoElementoService tipoElementoService;

    @Inject
    private CfgConfHeaderService confHeaderService;

    @Inject
    private ConfigurationService configurationService;

    @Inject
    private MuiPromoDbPromoService muiPromoDbPromoService;

    @Inject
    private MuiPlanoDbPromoService muiPlanoDbPromoService;

    @Inject
    private MeccanicheUtil meccanicheUtil;

    @Inject
    private PianificazioneSecurityUtil securityUtil;

    @Inject
    private ItemService itemService;

    @Inject
    private RepartoService repartoService;

    @Inject
    private GrmService grmService;

    @Inject
    private MeccanicaService meccanicaService;

    @Inject
    private MarchioPrivatoService marchioPrivatoService;

    @Inject
    private Instance<ApplicationProperties> applicationPropertiesInstance;

    private UserFilterUtils userFilterUtils = new UserFilterUtils();

    @Inject
    @Getter
    UploadComplementari uploadComplementari;

    @Getter
    PromozioneTestataEntity promotionSelected;

    @Getter
    StatoPromozioneEntity statoPromozioneSelected;

    @Getter
    List<PromozioneTestataEntity> promozioni;

    @Getter
    PromozioneTestataEntity promozioneCorrente;

    @Getter
    Object idPromozioneCorrente;

    @Getter
    Object idStatoTransizioneCorrente;

    @Getter
    PianificazionePromoDialogBackingBean pianificazionePromoDialogBackingBean;

    @Getter
    PianificazionePromoRiferimentoBackingBean promoRiferimentoBean;

    @Getter
    PianificazionePlanoRiferimentoBackingBean planoRiferimentoBean;

    @Getter
    InclusioneEsclusioneBean inclusioneEsclusioneBean;

    @Getter
    boolean addPlanningBtnDisabled = true;

    @Getter
    private List<CompratoreEntity> compratori = new ArrayList<>();

    @Getter
    boolean btnConfirmPromoDisabled = false;

    @Getter
    String confirmForBuyerMsg;

    @Getter
    private List<Long> buyersIds = new ArrayList<>();

    @Getter
    boolean btnPianificaDaRiferimentoRendered = false;

    @Getter
    boolean btnPianificaDaPlanoRendered = false;

    @Getter
    boolean btnNuovaPianificazioneRendered = false;

    @Getter
    boolean btnConfermaPianificazioneRendered = false;

    @Getter
    boolean tabStoricoUploadRendered = false;

    @Getter
    boolean tabCompratoriRendered = false;

    @Getter
    boolean tabControlliRendered = false;

    @Getter
    boolean tabSovrapposizioniRendered = false;

    @Getter
    boolean tabInclEsclRendered = false;

    @Getter
    boolean btnDeleteSelectedRendered = false;

    private List<Long> deleteSelectedIds;

    @Getter
    private List<TabEnum> renderedTabs = new ArrayList<>();

    @Getter
    private Long numeroArticoliPianificati = 0L;

    @Inject
    transient AuditLogService auditLogService;

    @Inject
    private MuiInclusioneEsclusioneService inclusioneEsclusioneService;

    @Inject
    private CfgInclusioniEsclusioniService cfgInclusioniEsclusioniService;

    private static final int MAX_RIGHE_CANCELLABILI = 999;

    private String inclusioneEsclusioneTabClass = INCLUSIONE_ESCLUSIONE_TAB_CLASS_NONE;

    @Override
    public void setNode(final MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    @Override
    public void applyRules() {
        final boolean isUserAdmin = isUserAdmin();
        applyDefaultRules();
        if (promotionSelected != null) {
            try {
                final StatoPromozioneEntity stato = PromoAcl.getStatoCorrente(promotionSelected);
                final CanalePromozioneEntity canale = promotionSelected.getMuiCanalePromozione();
                if (canale == null || stato == null) {
                    log.error(
                            String.format(
                                    "Error getting 'Canale' and 'Stato' from 'Promozione' %s [id=%d]",
                                    promotionSelected.getCodicePromozione(), promotionSelected.getId()));
                } else {
                    btnPianificaDaRiferimentoRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_BTN_PIANIFICA_DA_RIF,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.BUTTON_PIANIFICA_DA_RIF,
                                    btnPianificaDaRiferimentoRendered);
                    btnPianificaDaPlanoRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_BTN_PIANIFICA_DA_PLANO,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.BUTTON_PIANIFICA_DA_PLANO,
                                    btnPianificaDaPlanoRendered);
                    btnNuovaPianificazioneRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_BTN_NUOVA_PIANIFICAZIONE,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.BUTTON_NUOVA_PIANIFICAZIONE,
                                    btnNuovaPianificazioneRendered);
                    btnConfermaPianificazioneRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_BTN_CONFERMA_PIANIFICAZIONE,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.BUTTON_CONFERMA_PIANIFICAZIONE,
                                    btnConfermaPianificazioneRendered);
                    tabStoricoUploadRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_STORICO_UPLOAD,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.TAB_STORICO_UPLOAD,
                                    tabStoricoUploadRendered);
                    tabCompratoriRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_COMPRATORI,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.TAB_COMPRATORI,
                                    tabCompratoriRendered);
                    tabControlliRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_CONTROLLI_PIANIFICAZIONE,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.TAB_CONTROLLI_PIANIFICAZIONE,
                                    tabControlliRendered);
                    tabSovrapposizioniRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.PIANIFICAZIONE,
                                    ElementFieldEnum.TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE,
                                    tabSovrapposizioniRendered);
                    tabInclEsclRendered = applyRuleInclusioniEsclusioni();
                }
            } catch (Exception ex) {
                log.error("Error applying rules", ex);
            }
        }
    }

    private boolean applyRuleInclusioniEsclusioni() {
        return contesto != null &&
                (contesto.toUpperCase().trim().contains("CONFIGURATORE") ||
                        contesto.toUpperCase().trim().contains("CUST_ENG"));
    }

    @Override
    public void applyDefaultRules() {
        btnPianificaDaRiferimentoRendered = false;
        btnPianificaDaPlanoRendered = false;
        btnNuovaPianificazioneRendered = false;
        btnConfermaPianificazioneRendered = false;
        tabStoricoUploadRendered = false;
        tabCompratoriRendered = false;
        tabControlliRendered = false;
        tabSovrapposizioniRendered = false;
        tabInclEsclRendered = false;
    }

    private void applyTabsPosition() {
        renderedTabs.clear();
        renderedTabs.add(TabEnum.TAB_PIANIFICAZIONE);
        if (tabStoricoUploadRendered) {
            renderedTabs.add(TabEnum.TAB_COMPLEMENTARI);
        }
        if (tabCompratoriRendered) {
            renderedTabs.add(TabEnum.TAB_COMPRATORI);
        }
        if (tabControlliRendered) {
            renderedTabs.add(TabEnum.TAB_CONTROLLI_PIANIFICAZIONE);
        }
        if (tabSovrapposizioniRendered) {
            renderedTabs.add(TabEnum.TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE);
        }
        if (tabInclEsclRendered) {
            renderedTabs.add(TabEnum.TAB_INCLUSIONI_ESCLUSIONI);
        }
    }

    public int indexOf(TabEnum tab) {
        return renderedTabs.indexOf(tab);
    }

    public void init() {
        this.pianificazionePromoDialogBackingBean =
                new PianificazionePromoDialogBackingBean(
                        promoService,
                        cfgPianificazioneService,
                        uploadExcelService,
                        pianificazionePromoUtil,
                        pianificazioneService,
                        getUserDto(),
                        pianificazioneHelper,
                        tipoElementoService,
                        configurationService,
                        confHeaderService,
                        meccanicheUtil,
                        securityUtil,
                        itemService,
                        marchioPrivatoService);
        this.pianificazionePromoDialogBackingBean.setMaxRows(
                applicationPropertiesInstance
                        .get()
                        .getProperty(
                                ApplicationProperties.MAX_UPLOAD_RECORDS,
                                ApplicationProperties.DEFAULT_MAX_UPLOAD_RECORDS));
        this.promoRiferimentoBean =
                new PianificazionePromoRiferimentoBackingBean(
                        muiPromoDbPromoService, pianificazioneHelper, meccanicheUtil, getUserDto());
        this.planoRiferimentoBean =
                new PianificazionePlanoRiferimentoBackingBean(
                        muiPlanoDbPromoService, pianificazioneHelper, meccanicheUtil, getUserDto());
        this.inclusioneEsclusioneBean = new InclusioneEsclusioneBean(inclusioneEsclusioneService,
                cfgInclusioniEsclusioniService, itemService, repartoService, grmService,
                meccanicaService, getUserDto());
        this.uploadComplementari.init();
        final UsersEntity user = ((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
        readPromotions(user.getDbFilters());
        if ((promozioni != null) && (!promozioni.isEmpty())) {
            final PromozioneTestataEntity promozioneTestataEntity = promozioni.get(0);
            setPromozioneCorrente(promozioneTestataEntity);

            this.statoPromozioneSelected = PromoAcl.getStatoCorrente(promozioneTestataEntity);
            if (this.statoPromozioneSelected != null) {
                final List<PromoStatiTransizioneEntity> statiTransizione =
                        promoStatiTransizioneService.findByIdAndStatus(
                                promozioneTestataEntity.getId(), this.statoPromozioneSelected.getId());
                if ((statiTransizione == null) || statiTransizione.isEmpty()) {
                    setIdStatoTransizioneCorrente(null);
                }
            }

            addPlanningBtnDisabled =
                    !planningCommons.isPlanningEditableCellAndRow(promozioneTestataEntity);
            this.pianificazionePromoDialogBackingBean.setAggiungiPianificazioneBtnDisabled(
                    addPlanningBtnDisabled);
        }

        this.pianificazionePromoDialogBackingBean.readMeccaniche();
        this.uploadComplementari.setUser(getCurrentUser().getName());
        readCompratori();
        updateBtnConfirmPromo();
        log.debug("view constructed");
    }

    public void selezionaItems() {
        final Map<String, String> params = getRequestParameterMap();
        final String itemSelectedJsonArray = params.get("itemSelected");
        if (deleteSelectedIds == null) {
            deleteSelectedIds = new ArrayList<>();
        } else {
            deleteSelectedIds.clear();
        }
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemSelectedJsonArray);
            if ((jsonNode != null) && (jsonNode.size() > 0)) {
                btnDeleteSelectedRendered = true;
                jsonNode.forEach(n -> deleteSelectedIds.add(n.asLong()));
            } else {
                btnDeleteSelectedRendered = false;
            }
        } catch (Exception ex) {
            log.error("Error read json item selected", ex);
            btnDeleteSelectedRendered = false;
        }
    }

    public void deleteSelectedItems() {
        if (promotionSelected != null && deleteSelectedIds != null && !deleteSelectedIds.isEmpty()) {
            try {
                List<Long> daCancellare = null;
                if (deleteSelectedIds.size() > MAX_RIGHE_CANCELLABILI) {
                    daCancellare = deleteSelectedIds.subList(0, MAX_RIGHE_CANCELLABILI);
                } else {
                    daCancellare = deleteSelectedIds;
                }
                final List<PromozionePianificazioneEntity> pianificazioni =
                        pianificazioneService.findAllPianificazioniByIds(daCancellare);
                final UserDTO userDto = getUserDto();
                final boolean userAdmin = isUserAdmin();
                final AtomicInteger count = new AtomicInteger(0);
                pianificazioni.forEach(
                        p -> {
                            final boolean deletable =
                                    userAdmin || securityUtil.isDeletable(p, userDto.getGruppi());
                            if (!deletable) {
                                log.error(
                                        String.format(
                                                "Setted security cannot permit to delete pianificazione id %d", p.getId()));
                            } else {
                                try {
                                    pianificazionePromoUtil.delete(p, userDto.getUser().getName());
                                    count.incrementAndGet();
                                } catch (Exception ex) {
                                    log.error(String.format("Error deleting pianificazione id %d", p.getId()), ex);
                                }
                            }
                        });
                if (count.intValue() == daCancellare.size()) {
                    if (daCancellare.size() != deleteSelectedIds.size()) {
                        addMessage(
                                null,
                                new FacesMessage(
                                        FacesMessage.SEVERITY_WARN,
                                        "Successo",
                                        "Il numero massimo di righe di pianificazione ("
                                                + MAX_RIGHE_CANCELLABILI
                                                + ") sono state eliminate. Ripetere l'operazione per cancellare le righe rimanenti"));
                    } else {
                        addMessage(
                                null,
                                new FacesMessage(
                                        FacesMessage.SEVERITY_INFO,
                                        "Successo",
                                        "Righe pianificazione eliminate dalla promozione"));
                    }
                } else {
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_WARN,
                                    "Attenzione",
                                    "Alcune righe pianificazione non sono state eliminate dalla promozione"));
                }
            } catch (Exception ex) {
                log.error(
                        String.format(
                                "Error removing selected items (%s) from testata with id %d",
                                deleteSelectedIds.stream().map(String::valueOf).collect(Collectors.joining(";")),
                                promotionSelected.getId()),
                        ex);
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Errore",
                                "Impossibile eliminare righe pianificazione selezionate"));
            }
            deleteSelectedIds.clear();
            btnDeleteSelectedRendered = false;
            reloadTestata();
        }
    }

    private void updateBtnConfirmPromo() {
        this.btnConfirmPromoDisabled =
                promotionSelected == null
                        || compratori.isEmpty()
                        || (promotionSelected.getPromozionePianificazioneEntities().stream()
                        .noneMatch(
                                p ->
                                        PlanningLevelEnum.ELEMENTO
                                                .getCode()
                                                .equals(p.getTipoRiga().getCodiceTipo()))
                        && promotionSelected.getCheckCompratori().isEmpty());
    }

    private void readCompratori() {
        this.compratori.clear();
        try {
            if (getUserDto().isAdmin()) {
                // vede tutto
                this.compratori.addAll(promoService.findAllBuyers());
            } else {
                final List<String> gruppi = getUserDto().getGruppi();
                if (gruppi == null || gruppi.isEmpty()) {
                    log.warn(
                            String.format("User %s has no groups associated", getUserDto().getUser().getName()));
                } else {
                    final List<String> buyerCodes =
                            muiServiceInstance
                                    .get()
                                    .findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(
                                            gruppi, PianificazioneSecurityEnum.WRITE);
                    if (buyerCodes == null || buyerCodes.isEmpty()) {
                        log.warn(
                                String.format(
                                        "User %s with groups %s has no buyers associated",
                                        getUserDto().getUser().getName(), String.join(",", gruppi)));
                    } else {
                        this.compratori.addAll(promoService.findAllBuyersByCodes(buyerCodes));
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Error reading compratori", ex);
        }
    }

    private void readPromotions(String dbFilters) {
        try {
            promozioni =
                    promoService
                            .findAllNotCancelled(
                                    userFilterUtils.createUserFiltersMapToQueryString(dbFilters),
                                    getUserDto().getCanali())
                            .stream()
                            .sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
                            .collect(Collectors.toList());
            log.debug("promozioni recuperate ed ordinate");
        } catch (final Exception e) {
            log.error("Error reading promozioni", e);
        }
    }

    @Override
    public void setIdPromozioneCorrente(Object idPromozioneCorrente) {
        this.idPromozioneCorrente = idPromozioneCorrente;
        this.pianificazionePromoDialogBackingBean.setIdMasterAddPianificazione(null);
        log.debug("setting id promozione corrente to " + idPromozioneCorrente);
        if (idPromozioneCorrente != null) {
            try {
                PromozioneTestataEntity promozioneTestataEntity =
                        promoService.findById(Long.valueOf(idPromozioneCorrente.toString()));
                setPromozioneCorrente(promozioneTestataEntity);
                addPlanningBtnDisabled =
                        !planningCommons.isPlanningEditableCellAndRow(promozioneTestataEntity);
                this.pianificazionePromoDialogBackingBean.setAggiungiPianificazioneBtnDisabled(
                        addPlanningBtnDisabled);

                if (this.pianificazionePromoDialogBackingBean.getVisualizzaStatus() == null) {
                    this.pianificazionePromoDialogBackingBean.setVisualizzaStatus(
                            PlanningRadioButtonEnum.TUTTO.getValue());
                }
            } catch (Exception e) {
                log.error("error retrieving promozioneTestata with id " + idPromozioneCorrente, e);
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Errore",
                                "Errore nel recupero della promozione con id " + idPromozioneCorrente));
            }
        } else {
            setPromozioneCorrente(null);
            this.pianificazionePromoDialogBackingBean.setAggiungiPianificazioneBtnDisabled(true);
            addPlanningBtnDisabled = true;
        }
    }

    public void setIdStatoTransizioneCorrente(Object idStatoTransizioneCorrente) {
        this.idStatoTransizioneCorrente = idStatoTransizioneCorrente;
    }

    public void setPromotionSelected(PromozioneTestataEntity promotionSelected) {
        this.promotionSelected = promotionSelected;
        applyRules();
        applyTabsPosition();
    }

    private void setPromozioneCorrente(PromozioneTestataEntity promozioneTestataEntity) {
        setPromotionSelected(promozioneTestataEntity);
        setStatoPromozioneSelected(promozioneTestataEntity);
        promoRiferimentoBean.setPromozioneCorrente(promozioneTestataEntity);
        planoRiferimentoBean.setPromozioneCorrente(promozioneTestataEntity);
        inclusioneEsclusioneBean.setPromozioneCorrente(promozioneTestataEntity);
        if (this.promotionSelected != null) {
            this.idPromozioneCorrente = this.promotionSelected.getId();
            this.pianificazionePromoDialogBackingBean.setPromozioneCorrente(promozioneTestataEntity);
            this.pianificazionePromoDialogBackingBean.readMeccaniche();
            this.numeroArticoliPianificati = contaArticoli(promotionSelected);
        } else {
            this.idPromozioneCorrente = null;
            this.pianificazionePromoDialogBackingBean.setPromozioneCorrente(null);
        }
    }

    private Long contaArticoli(PromozioneTestataEntity testata) {
        return pianificazioneService.countArticoli(testata);
    }

    public void updateContaArticoli() {
        if (this.promotionSelected != null) {
            this.numeroArticoliPianificati = contaArticoli(promotionSelected);
        }
    }

    private void setStatoPromozioneSelected(PromozioneTestataEntity testata) {
        if (testata != null) {
            this.statoPromozioneSelected = PromoAcl.getStatoCorrente(testata);
        } else {
            this.statoPromozioneSelected = null;
        }
    }

    public void applyFilterToPromozioni() {
        final UsersEntity user = ((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
        readPromotions(user.getDbFilters());
        if ((idPromozioneCorrente != null)
                && (promozioni.stream()
                .filter(
                        testata -> new Long(idPromozioneCorrente.toString()).equals(testata.getId()))
                .findFirst()
                .orElse(null)
                == null)) {
            setIdPromozioneCorrente(null);
            setPromotionSelected(null);
            this.pianificazionePromoDialogBackingBean.setVisualizzaStatus(
                    PlanningRadioButtonEnum.TUTTO.getValue());
        }
        if (this.idPromozioneCorrente == null) {
            executeScript("updatePianificazioneTab();");
        }
    }

    public List<CastellettoMessaggi> getCastelletti() {
        log.info("creazione castelletto messaggi");
        if (promotionSelected != null) {
            try {
                List<PromozionePianificazioneEntity> sets =
                        pianificazioneService.findAllSetByPromozione(promotionSelected);
                return sets.stream().map(CastellettoMessaggi::new).collect(Collectors.toList());
            } catch (Exception e) {
                log.error("Errore durante il recupero del castelletto messaggi", e);
            }
        }
        return new ArrayList<>();
    }

    public boolean getBtnPianificazioneDisabled() {
        return RoleMenuUtil.FORBIDDEN_MENU.equals(navigateTo);
    }

    public void openConfirmPianificazioneDialog() {
        if (compratori.size() == 1) {
            final CompratoreEntity compratore = compratori.get(0);
            this.confirmForBuyerMsg =
                    String.format("[%s] %s", compratore.getCodiceCompratore(), compratore.getDescrizione());
            executeScript("PF('confirmPlanningSingleBuyer').show()");
        } else if (compratori.size() > 1) {
            executeScript("PF('confirmPianificazioneDialog').show()");
        } else {
            log.error("Nessun compratore presente per confermare la pianificazione");
        }
    }

    /**
     * quick and dirty
     */
    private void refreshPromozione() {
        Long idPromozione = promotionSelected.getId();
        promotionSelected = promoService.findById(idPromozione);
        promozioneCorrente = promotionSelected;
    }

    public void confirmPianificazione() {
        refreshPromozione(); // ci sono i webservices che si incatricchiano con la mia promo in sessione
        // Recupero stati transizione automatici
        final List<PromoStatiTransizioneEntity> transizioni =
                promoStatiTransizioneService.findAllAutomaticByIdAndStatus(
                        promotionSelected.getId(), statoPromozioneSelected.getId());
        if (transizioni.size() > 1) {
            log.error(
                    String.format(
                            "Cannot execute automatic transition from status '%s' for testata with id %d; found %d transitions, expected one",
                            statoPromozioneSelected.fullDescription(),
                            promotionSelected.getId(),
                            transizioni.size()));
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Errore",
                            "Impossibile determinare la transizione da eseguire"));
            return;
        } else if (transizioni.size() == 0) {
            log.warn(
                    String.format(
                            "No automatic transition from status '%s' for testata with id %d",
                            statoPromozioneSelected.fullDescription(), promotionSelected.getId()));
        }
        final PromoStatiTransizioneEntity transizione =
                transizioni.size() == 1 ? transizioni.get(0) : null;
        if (compratori.isEmpty()) {
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Errore",
                            "Impossibile confermare la pianificazione; nessun compratore presente"));
            return;
        }
        String listaCompratori = null;
        if (compratori.size() == 1) {
            listaCompratori = compratori.get(0).getId().toString();
        } else {
            if (!buyersIds.isEmpty()) {
                listaCompratori =
                        compratori.stream()
                                .filter(c -> buyersIds.contains(c.getId()))
                                .map(c -> c.getId())
                                .map(i -> i.toString())
                                .collect(Collectors.joining(","));
            }
        }
        auditLogService.logAction(
                AuditLogEntity.BUTTON_CLICK,
                String.format(
                        "Conferma Pianificazione per promo %s",
                        promotionSelected == null ? "empty" : promotionSelected.getCodicePromozione()),
                getCurrentUser().getName());
        boolean statusChanged = true;
        final boolean success = executeStoredProcedure(promotionSelected.getId(), listaCompratori);
        boolean stillInError = false;
        if (success) {
            if (listaCompratori != null) {
                final List<String> exclude = Arrays.asList(listaCompratori.split(","));
                stillInError =
                        promozioneCorrente.getCheckPianificazioni().stream()
                                .filter(c -> "errore".equalsIgnoreCase(c.getSeverita())) // stato errore
                                .filter(c -> c.getId() != null) // per un compratore
                                .filter(c -> !exclude.contains(c.getId())) // non in lista
                                .findAny()
                                .isPresent();
            }
            // No error, set stato destinazione if transizione
            if (!stillInError && transizione != null) {
                statusChanged = changeStatus(promotionSelected, transizione.getStatoTransizione());
            }
            addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pianificazione Confermata", null));
        } else {
            // Some buyers in error, set stato errore if transizione
            if (transizione != null) {
                statusChanged = changeStatus(promotionSelected, transizione.getStatoErrore());
            }
            final String msg =
                    promotionSelected.getPromozionePianificazioneEntities().isEmpty()
                            ? "Controllare gli errori riportati in testata"
                            : "Controllare la lista degli articoli in errore";
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
        }
        if (transizione != null) {
            if (statusChanged) {
                // Reload promozioni, rules and tabs
                readPromotions(getCurrentUser().getDbFilters());
                applyRules();
                applyTabsPosition();
                if (!stillInError) {
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO,
                                    "Cambio Stato",
                                    String.format(
                                            "Cambiato stato in '%s' per la promozione corrente",
                                            success
                                                    ? transizione.getStatoTransizione().fullDescription()
                                                    : transizione.getStatoErrore().fullDescription())));
                }
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Errore",
                                "Errore nel cambio stato; contattare l'assistenza tecnica"));
            }
        }
    }

    private boolean changeStatus(
            PromozioneTestataEntity testata, StatoPromozioneEntity statoDestinazione) {
        final PromozioneStatoEntity lastStatus =
                testata.getPromozioneStatoEntities().stream()
                        .filter(s -> s.getDataFineStato() == null)
                        .findFirst()
                        .orElse(null);
        if (lastStatus == null) {
            log.error(String.format("Cannot find lastStatus for testata with id %d", testata.getId()));
            return false;
        }
        Date now = new Date();
        final String username = getCurrentUser().getName();
        PromozioneStatoEntity nextStatus =
                (PromozioneStatoEntity)
                        AuditLogFiller.fillAuditLogFields(new PromozioneStatoEntity(), username);
        lastStatus.setDataFineStato(now);
        nextStatus.setDataInizioStato(now);
        nextStatus.setStatoPromozioneEntity(statoDestinazione);
        testata.addPromozioneStatoEntity(nextStatus);
        try {
            promoService.persist(testata, username);
        } catch (Exception ex) {
            log.error(
                    String.format(
                            "Error changing status to '%s' for testata with id %s",
                            statoDestinazione.fullDescription(), testata.getId()),
                    ex);
            return false;
        }
        return true;
    }

    public void selectBuyers() {
        final String buyersIdsParam = getRequestParameterMap().get("buyersIds");
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(buyersIdsParam);
            if (jsonNode != null) {
                buyersIds.clear();
                jsonNode.forEach(n -> buyersIds.add(Long.parseLong(n.asText())));
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "Errore", "Impossibile recuperare lista compratori"));
            }
        } catch (Exception ex) {
            log.error("Error getting buyers ids from request", ex);
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Errore", "Impossibile recuperare lista compratori"));
        }
    }

    private boolean executeStoredProcedure(Long idPromozione, String idCompratori) {
        boolean result = true;
        try {
            final Integer timeout =
                    applicationPropertiesInstance
                            .get()
                            .getProperty(
                                    ApplicationProperties.STORED_PROCEDURE_TIMEOUT,
                                    ApplicationProperties.DEFAULT_STORED_PROCEDURE_TIMEOUT);
            result =
                    promoService.runFunctionExportPianificazioni(
                            idPromozione, idCompratori, getCurrentUser().getName(), timeout);
        } catch (QueryTimeoutException ex) {
            log.error("Timeout exceeded", ex);
            result = false;
        } catch (Exception ex) {
            log.error(
                    String.format(
                            "Stored procedure %s terminata con errore", Constants.SP_EXPORT_PIANIFICAZIONE),
                    ex);
            result = false;
        }
        return result;
    }

    public void confirmPromoRiferimento() {
        promoRiferimentoBean.confirmPromoRiferimento();
        reloadTestata();
    }

    public void confirmPlanoRiferimento() {
        planoRiferimentoBean.confirmPlanoRiferimento();
        reloadTestata();
    }

    public void preparePromoRifDialog() {
        reloadTestata();
        promoRiferimentoBean.setPromozioneCorrente(getPromotionSelected());
        promoRiferimentoBean.prepareDialog();
    }

    public void preparePlanoRifDialog() {
        reloadTestata();
        planoRiferimentoBean.setPromozioneCorrente(getPromotionSelected());
        planoRiferimentoBean.prepareDialog();
    }

    public void confirmAddPianificazione() {
        final boolean isSaved = pianificazionePromoDialogBackingBean.confirmPianificazione();
        if (isSaved) {
            reloadTestata();
        }
    }

    public void enabledAddPlanningBtn() {
        if (pianificazionePromoDialogBackingBean != null) {
            pianificazionePromoDialogBackingBean.enabledAddPlanningBtn();
        } else {
            log.error("pianificazionePromoDialogBackingBean is null");
        }
        reloadTestata();
    }

    private void reloadTestata() {
        if (idPromozioneCorrente != null) {
            final PromozioneTestataEntity updatedTestata =
                    promoService.findById((long) idPromozioneCorrente);
            if (updatedTestata != null) {
                setPromozioneCorrente(updatedTestata);
                updateBtnConfirmPromo();
            }
        } else {
            log.error("idPromozioneCorrente is null");
        }
    }

    public String getStatoStyleError() {
        StringBuilder sb = new StringBuilder("width: 100%; background-color: transparent;");
        if (statoPromozioneSelected != null) {
            final String codiceStato = statoPromozioneSelected.getCodiceStato();
            if (PromoStatusEnum.IN_PIANIFICAZIONE_CON_ERRORI.getKey().equals(codiceStato)
                    || PromoStatusEnum.IN_ESECUZIONE_CON_ERRORI.getKey().equals(codiceStato)) {
                sb.append("color: #8B0000;");
            }
        }
        return sb.toString();
    }

    public void clearSelectedItems() {
        if (deleteSelectedIds != null && !deleteSelectedIds.isEmpty()) {
            deleteSelectedIds = null;
        }
        btnDeleteSelectedRendered = false;
    }

    public String getInclusioneEsclusioneTabClass() {
        if (promotionSelected == null) {
            return INCLUSIONE_ESCLUSIONE_TAB_CLASS_NONE;
        }
        int inclusioniEsclusioniCount = inclusioneEsclusioneService.findByPromozione(promotionSelected.getId()).size();
        return inclusioniEsclusioniCount > 0
                ? INCLUSIONE_ESCLUSIONE_TAB_CLASS_ORANGE
                : INCLUSIONE_ESCLUSIONE_TAB_CLASS_NONE;
    }
}
