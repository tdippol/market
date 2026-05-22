package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.actions.ActionTypeEnum;
import com.axiante.mui.dbpromo.actions.ElementFieldEnum;
import com.axiante.mui.dbpromo.actions.FormEnum;
import com.axiante.mui.dbpromo.business.enumeration.ModalitaMarchioPrivato;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurazioneMeccanicheCanaleService;
import com.axiante.mui.dbpromo.persistence.service.IniziativeService;
import com.axiante.mui.dbpromo.persistence.service.MuiPlanoDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.PromoPubblicazioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.PromoRepartoMarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiTransizioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneCostiContattoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneMarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneNegozioService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.dbpromo.persistence.service.SottoscrizioneService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.dbpromo.persistence.service.TipoTerminaleCassaService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.validator.model.PromoUpdate;
import com.axiante.mui.validator.service.PromotionValidatorService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.utils.RoleMenuUtil;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.ClonaPromozioneBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.ContattiBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.IniziativaBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.MeccanicheBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.ModificaTestataBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.NegoziPromoBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.PlanoRiferimentoBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.PromozioneRiferimentoBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.RepartiBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.SottoscrizioniBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.TipoCassaBackingBean;
import com.axiante.mui.webapp.views.content.enumeration.TabEnum;
import com.axiante.mui.webapp.webservice.util.PromoShopsUtil;
import com.axiante.mui.webapp.webservice.util.PromozioneTestataHelper;
import com.axiante.mui.webapp.webservice.util.TotalizzatoreHelper;
import com.axiante.mui.webapp.webservice.validator.PromozioneTestataValidator;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.MenuItem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@MuiViewModel("schedaPromo")
@Dependent
@Slf4j
public class SchedaPromoView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -4985183993092027769L;

    @Inject
    private PromoService promoService;

    @Inject
    private IniziativeService iniziativeService;

    @Inject
    private PromoStatiTransizioneService promoStatiTransizioneService;

    @Inject
    private PromoPubblicazioneTestataService promoPubblicazioneTestataService;

    @Inject
    private StatoPromoService statoPromozioneService;

    @Inject
    private PromoShopsUtil promoShopsUtil;

    @Inject
    private PromotionValidatorService promotionValidatorService;

    @Inject
    private PromozioneTestataHelper promozioneTestataHelper;

    @Inject
    private PromozioneTestataValidator promozioneTestataValidator;

    @Inject
    private SottoscrizioneService sottoscrizioneService;

    @Inject
    private PromozioneCostiContattoService promoCostiContattoService;

    @Inject
    private CanalePromozioneService canalePromozioneService;

    private final UserFilterUtils userFilterUtils = new UserFilterUtils();

    @Getter
    @Setter
    PromozioneTestataEntity promotionSelected;

    @Getter
    Date ultimaPubblicazione;

    @Getter
    private PromozioneStatoEntity statoCorrente;

    List<PromozioneTestataEntity> promozioni;

    @Getter
    PromozioneTestataEntity promozioneCorrente;

    @Getter
    Object idPromozioneCorrente;

    @Getter
    @Setter
    Object idStatoTransizioneCorrente;

    @Getter
    @Setter
    List<PromoStatiTransizioneEntity> statiTransizione;

    @Getter
    PromoPubblicazioneTestataEntity lastPublicationDate;

    @Getter
    String lastPublicationDateStr;

    @Getter
    NegoziPromoBackingBean negoziPromoBean;

    @Getter
    PromozioneRiferimentoBackingBean promoRifBean;

    @Getter
    PlanoRiferimentoBackingBean planoRifBean;

    @Getter
    IniziativaBackingBean iniziativaBean;

    @Getter
    MeccanicheBackingBean meccaniche;

    @Getter
    SottoscrizioniBackingBean sottoscrizioniBean;

    @Inject
    @Getter
    ContattiBackingBean contattiBean;

    @Getter
    RepartiBackingBean reparti;

    @Getter
    TipoCassaBackingBean tipoCassa;

    @Getter
    ModificaTestataBackingBean editTestata;
    
    @Getter
    ClonaPromozioneBean clonaPromoBean;

    @Inject
    ConfigurazioneMeccanicheCanaleService cfgMeccanicheCanaleService;

    @Inject
    RepartoService repartoService;

    @Inject
    TipoTerminaleCassaService tipoCassaService;

    @Inject
    @Getter
    Instance<PromozioneNegozioService> promozioneNegozioServiceInstance;

    @Inject
    private Instance<OwnershipService> ownershipServiceInstance;

    @Inject
    private Instance<MuiPromoDbPromoService> selezionaPromoServiceInstance;

    @Inject
    private Instance<MuiPlanoDbPromoService> selezionaPlanoServiceInstance;

    @Inject
    Instance<TotalizzatoreHelper> totalizzatoreHelperInstance;

    @Getter
    boolean buttonPubblicazioneDisabled = false;

    @Getter
    @Setter
    private Integer activeTab = 0;

    // Flags per rendered and actionable elements
    @Getter
    private boolean tabPromoRiferimentoRendered = false;

    @Getter
    private boolean tabPlanoRiferimentoRendered = false;

    @Getter
    private boolean tabIniziativaRendered = false;

    @Getter
    private boolean btnAggiungiMeccanicheActive = false;

    @Getter
    private boolean btnEliminaAssociazionePromoActive = false;

    @Getter
    private boolean btnEliminaIniziativaActive = false;

    @Getter
    private boolean tabTipoCassaRendered = false;

    @Getter
    private boolean tabFlagRendered = false;

    @Getter
    private boolean tabAttributiRendered = false;

    @Getter
    private boolean tabRepartiRendered = false;

    @Getter
    private boolean tabModificaRendered = false;

    @Getter
    private boolean tabSottoscrizioniRendered = false;

    @Getter
    private boolean tabContattiRendered = false;

    @Getter
    private boolean btnAggiungiTipoCassaActive = false;

    @Getter
    private boolean btnAggiungiRepartiActive = false;

    @Getter
    private boolean btnUnlockActive = false;

    @Getter
    private boolean fieldOraInizioRendered = false;

    @Getter
    private boolean fieldOraFineRendered = false;

    @Getter
    private boolean fieldValorePuntoRendered = false;

    @Getter
    private boolean btnScegliPromoActive = false;

    @Getter
    private boolean btnScegliPlanoActive = false;

    @Getter
    private boolean btnScegliIniziativaActive = false;

    @Getter
    private boolean uploadNegoziRendered = false;

    @Getter
    private boolean tabEditRendered = true;

    @Getter
    private boolean tabControlliRendered = false;

    @Getter
    private boolean tabOwnerRedered = false;

    @Getter
    private boolean textBoxWarningNegozi = false;

    @Getter
    private boolean negoziCediRendered = false;

    @Getter
    private boolean negoziZonaRendered = false;

    @Getter
    private boolean tipoPuntoVenditaRendered = false;

    @Getter
    private boolean canaleConsegnaRendered = false;

    @Getter
    private final List<TabEnum> renderedTabs = new ArrayList<>();

    @Getter
    private List<PromoRepartoMarchioPrivato> repartiMarchioPrivato = new ArrayList<>();

    private final SimpleDateFormat sdf = new SimpleDateFormat("E dd-MM-yyyy");

    private List<TabItem> tabs;

    @Getter
    private List<TabItem> renderedTabItems;

    @Inject
    @Getter
    private ApplicationProperties applicationProperties;

    @Getter
    List<PromozioneMarchioPrivatoEntity> marchiPrivati;

    @Inject
    Instance<PromozioneMarchioPrivatoService> promozioneMarchioPrivatoServiceInstance;

    @Inject
    Instance<PromoRepartoMarchioPrivatoService> promoRepartoMarchioPrivatoServiceInstance;

    @Getter
    private boolean marchioPrivatoAbilitato = false;

    @Getter
    private boolean marchioPrivatoAutomatico = false;

    @Getter
    private boolean marchioPrivatoEsteso = false;

    @Getter
    @Setter
    private boolean marchioPrivatoDisabled = true;

    @Getter
    @Setter
    private boolean marchioPrivatoAutomaticoDisabled = true;

    @Getter
    private boolean differenziazioneMeccanicaRendered = false;

    @Getter
    private boolean differenziazioneMeccanicaAbilitata = false;

    @Getter
    private boolean btnClonaPromoRendered = false;

    private DateTimeUtils utils = new DateTimeUtils();
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
            log.error("Cannot retrieve idMenu from params: " + params);
        }
    }

    public void init() {
        final UsersEntity user = ((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
        readPromotions(user.getDbFilters());

        if ((promozioni != null) && (!promozioni.isEmpty())) {
            final PromozioneTestataEntity testata = promozioni.get(0);
            this.meccaniche = new MeccanicheBackingBean(promoService, cfgMeccanicheCanaleService, user.getName());
            this.sottoscrizioniBean = new SottoscrizioniBackingBean(promoService, sottoscrizioneService, user.getName());
            this.reparti = new RepartiBackingBean(promoService, repartoService, user.getName());
            this.tipoCassa = new TipoCassaBackingBean(promoService, tipoCassaService, user.getName());
            this.editTestata = new ModificaTestataBackingBean(applicationProperties.getProperty(
                    ApplicationProperties.VALORE_PUNTO_MAX_VALUE, ApplicationProperties.DEFAULT_VALORE_PUNTO_MAX_VALUE));
            this.negoziPromoBean = new NegoziPromoBackingBean(promoShopsUtil, user.getName(), getUserDto(), true);
            this.promoRifBean = new PromozioneRiferimentoBackingBean();
            this.planoRifBean = new PlanoRiferimentoBackingBean();
            this.iniziativaBean = new IniziativaBackingBean(iniziativeService);
            this.clonaPromoBean = new ClonaPromozioneBean(promoService, user.getName(), currentIdMenu);

            setPromozioneCorrente(testata);
            tabEditRendered = isUserAdmin() || ownershipServiceInstance.get().hasOwnership(testata, getUserDto().getGruppi());

            PromozioneStatoEntity lastStatus = getLastStatus(testata);
            if (lastStatus != null) {
                final StatoPromozioneEntity statoPromozioneEntity = lastStatus.getStatoPromozioneEntity();
                final List<PromoStatiTransizioneEntity> statiTransizione =
                        promoStatiTransizioneService.findAllNotAutomaticByIdAndStatus(
                                testata.getId(), statoPromozioneEntity.getId());
                setStatiTransizione(statiTransizione);
                if ((statiTransizione == null) || statiTransizione.isEmpty()) {
                    setIdStatoTransizioneCorrente(null);
                }
            }

            ultimaPubblicazione = null;
            Optional<Date> dt =
                    testata.getPromoPubblicazioneTestataEntities().stream()
                            .map(PromoPubblicazioneTestataEntity::getDataPubblicazione)
                            .max(Date::compareTo);
            if (dt.isPresent()) {
                ultimaPubblicazione = dt.get();
            } else {
                log.warn("non ci sono pubblicazioni per la testata promozionale id " + testata.getId());
            }
        }
        if (idPromozioneCorrente != null) {
            this.negoziPromoBean.setIdPromo(Long.valueOf(idPromozioneCorrente.toString()));
        }
        setGestioneButtonDisabled();
        updateDifferenziazioneMeccanica();

        log.info("view constructed");
        log.debug("view constructed");
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

    public void setGestioneButtonDisabled() {
        try {
            if (idPromozioneCorrente != null) {
                PromozioneTestataEntity promozioneTestataEntity =
                        promoService.findById(Long.valueOf(idPromozioneCorrente.toString()));
                PromozioneStatoEntity lastStatus = getLastStatus(promozioneTestataEntity);
                if (lastStatus != null) {
                    this.buttonPubblicazioneDisabled =
                            lastStatus
                                    .getStatoPromozioneEntity()
                                    .getCodiceStato()
                                    .equals(PromoStatusEnum.PROMOZIONIE_CONCLUSA.getKey());
                }
            }
        } catch (Exception e) {
            log.error(
                    String.format(
                            "Not found PromozioneTestataEntity with id %s", idPromozioneCorrente.toString()),
                    e);
        }
    }

    @Override
    public void setIdPromozioneCorrente(Object idPromozioneCorrente) {
        this.idPromozioneCorrente = idPromozioneCorrente;
        log.debug("setting id promozione corrente to " + idPromozioneCorrente);
        if (idPromozioneCorrente != null) {
            try {
                PromozioneTestataEntity promozioneTestataEntity =
                        promoService.findById(Long.valueOf(idPromozioneCorrente.toString()));
                setPromozioneCorrente(promozioneTestataEntity);
                final boolean hasOwnership =
                        isUserAdmin()
                                || ownershipServiceInstance
                                .get()
                                .hasOwnership(promozioneTestataEntity, getUserDto().getGruppi());
                boolean canBeUnlocked = hasOwnership;
                PromozioneStatoEntity lastStatus = getLastStatus(promozioneTestataEntity);
                if (lastStatus != null) {
                    final StatoPromozioneEntity statoPromozioneEntity = lastStatus.getStatoPromozioneEntity();
                    final List<PromoStatiTransizioneEntity> statiTransizione =
                            promoStatiTransizioneService.findAllNotAutomaticByIdAndStatus(
                                    promozioneTestataEntity.getId(), statoPromozioneEntity.getId());
                    setStatiTransizione(statiTransizione);
                    if ((statiTransizione == null) || statiTransizione.isEmpty()) {
                        setIdStatoTransizioneCorrente(null);
                    }
                    canBeUnlocked = canBeUnlocked && canBeEdited(statoPromozioneEntity);
                }
                this.negoziPromoBean.setIdPromo(Long.valueOf(idPromozioneCorrente.toString()));
                setGestioneButtonDisabled();
                tabEditRendered = hasOwnership;
                fieldValorePuntoRendered =
                        Boolean.TRUE.equals(
                                promozioneTestataEntity.getMuiCanalePromozione().getFlValorePuntoFragola());

                // Al cambio di una promozione, se non sono sul primo tab, forzo al primo tab
                if (activeTab > 0) {
                    activeTab = 0;
                    executeScript("PF('schedaPromo_tabs').select(" + activeTab + ")");
                }

                this.editTestata.setBtnLockedDisabled(!canBeUnlocked);
            } catch (Exception e) {
                tabEditRendered = false;
                log.error("error retrieving promozioneTestata with id " + idPromozioneCorrente, e);
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Errore",
                                "Errore nel recupero della promozione con id " + idPromozioneCorrente));
            }
        } else {
            this.negoziPromoBean.setIdPromo(null);
            setPromotionSelected(null);
            tabEditRendered = false;
        }
    }

    private boolean canBeEdited(StatoPromozioneEntity statoPromo) {
        return PromoAcl.isEditableDescription(statoPromo)
                || PromoAcl.isEditableStartDate(statoPromo)
                || PromoAcl.isEditableEndDate(statoPromo)
                || PromoAcl.isEditableNotes(statoPromo)
                || PromoAcl.isEditableValorePunto(statoPromo);
    }

    public boolean isConfirmBtnStatiDisabled() {
        return this.idStatoTransizioneCorrente == null;
    }

    public boolean getManageBtnStatiDisabled() {
        if (idPromozioneCorrente == null) {
            return true;
        }
        boolean disabled = false;
        try {
            PromozioneTestataEntity testata =
                    promoService.findById(Long.valueOf(idPromozioneCorrente.toString()));
            if (testata == null) {
                return true;
            }
            if (testata.getMuiCanalePromozione().getFlMeccanicaSingola()
                    && testata.getPromozioneMeccanicheEntities().isEmpty()) {
                disabled = true;
            } else {
                PromozioneStatoEntity lastStatus = getLastStatus(testata);
                if (lastStatus != null) {
                    if (PromoStatusEnum.PROMOZIONIE_CONCLUSA
                            .getKey()
                            .equals(lastStatus.getStatoPromozioneEntity().getCodiceStato())) {
                        disabled = true;
                    }
                }
            }
        } catch (Exception e) {
            log.error(
                    String.format(
                            "Not found PromozioneTestataEntity with id %s",
                            idPromozioneCorrente != null ? idPromozioneCorrente.toString() : "null"),
                    e);
        }
        return disabled;
    }

    public boolean getBtnPianificazioneDisabled() {
        return RoleMenuUtil.FORBIDDEN_MENU.equals(navigateTo);
    }

    public void insertNewStatus() {
        log.debug("this.idStatoTransizioneCorrente: " + this.idStatoTransizioneCorrente);
        if ((this.idStatoTransizioneCorrente == null) || (this.idPromozioneCorrente == null)) {
            // return error here!
            return;
        }
        // insert new status to promotion
        PromozioneTestataEntity promozioneTestata = getTestataById((Long) this.idPromozioneCorrente);
        PromozioneStatoEntity lastStatus = getLastStatus(promozioneTestata);
        if (lastStatus == null) {
            return;
        }

        Date now = new Date();

        PromozioneStatoEntity nextStatus =
                (PromozioneStatoEntity)
                        AuditLogFiller.fillAuditLogFields(
                                new PromozioneStatoEntity(), getCurrentUser().getName());
        nextStatus.setDataInizioStato(now);

        StatoPromozioneEntity desiredState =
                statoPromozioneService.findById(Long.parseLong((String) this.idStatoTransizioneCorrente));
        if (desiredState == null) {
            throw new RuntimeException("Stato Promozione Entity not found");
        }

        try {
            // la view si basa solo sugli stati manuali, gli stati automatici sono gestiti
            // dal backend/database
            final PromoStatiTransizioneEntity transizione =
                    promoStatiTransizioneService.findManualiByPromoAndStatuses(
                            promozioneTestata, lastStatus.getStatoPromozioneEntity(), desiredState, false);
            if (transizione == null) {
                throw new RuntimeException(
                        String.format(
                                "Cannot find transition from %s to %s",
                                lastStatus.getStatoPromozioneEntity().getCodiceStato(),
                                desiredState.getCodiceStato()));
            }
            promozioneTestata.setFlagErrore(false);

            boolean result = true;

            if ((transizione.getFlagControlli() != null) && transizione.getFlagControlli()) {
                // Execute StoredProcedure
                try {
                    result =
                            promoService.runFunctionCheckStatusTransiction(
                                    promozioneTestata.getId(),
                                    getCurrentUser().getName(),
                                    transizione.getStatoTransizione().getId());
                } catch (Exception ex) {
                    log.error(
                            String.format(
                                    "Stored procedure %s terminata con errore",
                                    Constants.SP_CONTROLLO_TRANSIZIONE_STATO),
                            ex);
                    result = false;
                }
                if (!result) {
                    nextStatus.setStatoPromozioneEntity(
                            transizione.getStatoErrore() != null
                                    ? transizione.getStatoErrore()
                                    : transizione.getStatoPromozione());
                    lastStatus.setDataFineStato(now);
                    promozioneTestata.addPromozioneStatoEntity(nextStatus);
                    promozioneTestata.setFlagErrore(true);
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    "Errore",
                                    "Procedura controllo promozione fallita."));
                }
            }

            if (result) {
                // eventuale chiamata fidaty qui
        /*
        M.F.
           La CR #5171 e' una forzatura della MUI per gestire i totalizzatori come se fossero iniziative:
            aggiungiamo una operazione bloccante per i canali che hanno un flag particolare
            ma non lo scriviamo nella configurazione del workflow. In piu' il l'operazione
            puo' essere eseguita solo in particolari situazioni di pianificazione che dipendono
            dalla configurazione della meccanica e non del canale (come invece è il workflow del
            cambio stato) e solo per un particolare cambio di stato inserito a mano nel codice.
         */

                // se il canale ha la chiamata a fidaty abilitata e il cambio di stato e' da 30 a 31
                // nb. : next status non ha lo stato finale fino alla fine, lo stato desiderato e' in
                // desiredState
                boolean isMultitransazione =
                        promozioneTestata.getPromozionePianificazioneEntities().stream()
                                .map(PromozionePianificazioneEntity::getMultiTransazione)
                                .filter(Objects::nonNull)
                                .anyMatch("multitransazione"::equalsIgnoreCase);
                if (promozioneTestata.getCanalePromozioneEntity().getFlTotalizzatori()
                        && "30".equals(lastStatus.getStatoPromozioneEntity().getCodiceStato())
                        && "31".equals(desiredState.getCodiceStato())
                        && isMultitransazione) {
                    if (promozioneTestata.getTotalizzatori().isEmpty()) {
                        if (promozioneTestata.getChecks() != null) {
                            for (CheckTestataEntity checkTestata : promozioneTestata.getChecks()) {
                                if (checkTestata != null && "Fidaty".equals(checkTestata.getAmbito())) {
                                    promozioneTestata.removeCheckTestata(checkTestata);
                                }
                            }
                            // Save testata cleaned from checks on Fidaty
                            promoService.persist(promozioneTestata, getCurrentUser().getName());
                        }
                        PromozioneTestataEntity totalizzata =
                                totalizzatoreHelperInstance
                                        .get()
                                        .handleRafRequest(promozioneTestata, getCurrentUser().getName());
                        if (totalizzata != null) {
                            // ricaricare la testata
                            promozioneTestata = totalizzata;
                            // e continuare come niente fosse
                        } else {
                            // scrivere riga di errore
                            CheckTestataEntity checkTestata = new CheckTestataEntity();
                            promozioneTestata.addCheckTestata(checkTestata);
                            checkTestata.setDescrizioneControllo("errore nella risposta del servizio fidaty");
                            checkTestata.setSeverita("Errore");
                            checkTestata.setTipoControllo("Fidaty");
                            checkTestata.setAmbito("Fidaty");
                            checkTestata.setDataInserimento(new Date(System.currentTimeMillis()));
                            checkTestata.setCodiceUtenteInserimento(getCurrentUser().getName());
                            // fermare il cambio stato
                            result = false;
                            nextStatus.setStatoPromozioneEntity(
                                    transizione.getStatoErrore() != null
                                            ? transizione.getStatoErrore()
                                            : transizione.getStatoPromozione());
                            lastStatus.setDataFineStato(now);
                            promozioneTestata.addPromozioneStatoEntity(nextStatus);
                            promozioneTestata.setFlagErrore(true);

                            log.error(
                                    String.format(
                                            "Chiamata Fidaty non riuscita per la promozione %d",
                                            promozioneTestata.getId()));
                            addMessage(
                                    null,
                                    new FacesMessage(
                                            FacesMessage.SEVERITY_ERROR,
                                            "Errore",
                                            "Totalizzatori non generati per CB multitransazione: Pubblicazione fallita."));
                        }
                        // Save testata with totalizzatori
                        promozioneTestata = promoService.persist(promozioneTestata, getCurrentUser().getName());
                    } else {
                        log.warn(
                                String.format(
                                        "Totalizzatori gia' presenti per la promozione %s",
                                        promozioneTestata.getCodicePromozione()));
                    }
                }
            }

            if (result) {
                if ((transizione.getFlagPubblica() != null) && transizione.getFlagPubblica()) {
                    // Execute StoredProcedure
                    try {
                        result =
                                promoService.runFunctionAccodamentoPubblicazioni(
                                        promozioneTestata.getId(), getCurrentUser().getName(), desiredState.getId());
                    } catch (Exception ex) {
                        log.error(
                                String.format(
                                        "Stored procedure %s terminata con errore",
                                        Constants.SP_PUBBLICA_TRANSIZIONE_STATO),
                                ex);
                        result = false;
                    }
                    if (result) {
                        // Reload testata per avere dati aggiornati che possono essere stati modificati dalla
                        // stored procedure
                        promozioneTestata = promoService.findById(promozioneTestata.getId());
                        lastStatus = getLastStatus(promozioneTestata);
                    } else {
                        addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Pubblicazione fallita."));
                    }
                }
            }
            if (result) {
                lastStatus.setDataFineStato(now);
                nextStatus.setStatoPromozioneEntity(desiredState);
                promozioneTestata.addPromozioneStatoEntity(nextStatus);
            }
            promoService.persist(promozioneTestata, getCurrentUser().getName());
            if (!result) {
                // Reset dialog
                setIdStatoTransizioneCorrente(null);
            } else {
                promotionSelected = promozioneTestata;
            }
        } catch (Exception ex) {
            log.warn(
                    String.format(
                            "Errore impostazione nuovo stato su promozione con id %d, utente %s, stato iniziale %s e stato finale %s",
                            promozioneTestata.getId(),
                            getCurrentUser().getName(),
                            lastStatus.getStatoPromozioneEntity().getCodiceStato(),
                            desiredState.getCodiceStato()),
                    ex);
            // Reset dialog
            setIdStatoTransizioneCorrente(null);
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Errore",
                            "Errore impostazione nuovo stato promozione; contattare l'assistenza tecnica."));
        }
        resetDialog();
        // #3114 - reload promozioni after changing status, to reevaluate db filters
        readPromotions(getCurrentUser().getDbFilters());
        if (promozioni.stream()
                .map(PromozioneTestataEntity::getId)
                .noneMatch(id -> idPromozioneCorrente.equals(id))) {
            final PromozioneTestataEntity current = promozioni.stream().findFirst().orElse(null);
            setIdPromozioneCorrente(current == null ? null : current.getId());
        } else {
            applyRules();
            applyTabsPosition();
        }
    }

    public void prepareClonaPromo() {
        log.info("prepareClonaPromo");
        if (promotionSelected == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Nessuna promozione selezionata"));
            return;
        }
        clonaPromoBean.prepare(promotionSelected);
    }

    @Deprecated
    public void insertNewPubblication() {
        log.debug("this.idStatoTransizioneCorrente: " + this.idStatoTransizioneCorrente);
        if (this.idPromozioneCorrente == null) {
            // return error here!
            return;
        }
        final PromozioneTestataEntity promozioneTestata =
                getTestataById((Long) this.idPromozioneCorrente);
        PromozioneStatoEntity lastStatus = getLastStatus(promozioneTestata);
        if (lastStatus == null) {
            return;
        }

        Date now = new Date();
        PromoPubblicazioneTestataEntity pubblicazioneTestataEntity =
                new PromoPubblicazioneTestataEntity();
        pubblicazioneTestataEntity.setDataInserimento(now);
        pubblicazioneTestataEntity.setDataInserimento(now);
        pubblicazioneTestataEntity.setDataPubblicazione(now);
        pubblicazioneTestataEntity.setFlagEsito(new BigDecimal(1));
        pubblicazioneTestataEntity.setDescrizione(
                lastStatus.getStatoPromozioneEntity().getDescrizione());

        pubblicazioneTestataEntity.setPromozioneTestataEntity(promozioneTestata);
        pubblicazioneTestataEntity.setStatoPromozioneEntity(lastStatus.getStatoPromozioneEntity());
        promoPubblicazioneTestataService.persist(
                pubblicazioneTestataEntity, getCurrentUser().getName());

        // update last publication
        ultimaPubblicazione = now;
    }

    private void setPromozioneCorrente(PromozioneTestataEntity promozione) {
        try {
            setPromotionSelected(promozione);
            if (this.promotionSelected != null) {
                updateStato();
                this.idPromozioneCorrente = this.promotionSelected.getId();
                this.lastPublicationDate =
                        promozione.getPromoPubblicazioneTestataEntities().stream()
                                .max(Comparator.comparing(PromoPubblicazioneTestataEntity::getDataPubblicazione))
                                .orElse(null);
                if (this.lastPublicationDate != null) {
                    ultimaPubblicazione = lastPublicationDate.getDataPubblicazione();
                    this.lastPublicationDateStr =
                            capitalize(sdf.format(this.lastPublicationDate.getDataPubblicazione()))
                                    .concat("  ")
                                    .concat(this.lastPublicationDate.getStatoPromozioneEntity().getCodiceStato())
                                    .concat(" ")
                                    .concat(this.lastPublicationDate.getStatoPromozioneEntity().getLabel());
                } else {
                    this.ultimaPubblicazione = null;
                    this.lastPublicationDateStr = null;
                }
                this.meccaniche.setPromozioneCorrente(promozione);
                this.tipoCassa.setPromozioneCorrente(promozione);
                this.reparti.setPromozioneCorrente(promozione);
                this.sottoscrizioniBean.setPromozioneCorrente(promozione);
                this.contattiBean.setPromozioneCorrente(promozione);

                MuiPromoDbPromoEntity promoRiferimento = null;
                final String codicePromoRiferimento = promozione.getCodicePromoRiferimento();
                if (codicePromoRiferimento != null) {
                    promoRiferimento = loadPromoRiferimento(codicePromoRiferimento);
                }
                this.promoRifBean.setPromoRiferimento(promoRiferimento);
                MuiPlanoDbPromoEntity planoRiferimento = null;
                final String codicePlano =
                        (promozione.getPlanogrammi() != null) && (!promozione.getPlanogrammi().isEmpty())
                                ? promozione.getPlanogrammi().iterator().next().getCodicePlano()
                                : null;
                if (codicePlano != null) {
                    planoRiferimento = loadPlanoRiferimento(codicePlano);
                }
                final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                iniziativaBean.setDataInizioPromo(sdf.format(promozione.getDataInizio()));
                iniziativaBean.setDataFinePromo(sdf.format(promozione.getDataFine()));
                iniziativaBean.setIniziativa(promozione.getIniziativa());
            } else {
                this.statoCorrente = null;
                this.idPromozioneCorrente = null;
                this.meccaniche.setPromozioneCorrente(null);
                this.tipoCassa.setPromozioneCorrente(null);
                this.reparti.setPromozioneCorrente(null);
                this.promoRifBean.setPromoRiferimento(null);
                this.sottoscrizioniBean.setPromozioneCorrente(null);
                this.contattiBean.setPromozioneCorrente(null);
            }
            updateMarchioPrivato(this.promotionSelected);
            updateEditTestata(this.promotionSelected);
            updateRepartoMarchioPrivato();
            updateDifferenziazioneMeccanica();
            applyRules();
            applyTabsPosition();
        } catch (Exception e) {
            log.error("Error setting promozione", e);
        }
    }

    private MuiPromoDbPromoEntity loadPromoRiferimento(String codicePromoRiferimento) {
        return selezionaPromoServiceInstance.get().findByCodicePromozione(codicePromoRiferimento);
    }

    private MuiPlanoDbPromoEntity loadPlanoRiferimento(String codicePlanoRiferimento) {
        return selezionaPlanoServiceInstance.get().findByIdPlano(codicePlanoRiferimento);
    }

    private void updateEditTestata(PromozioneTestataEntity testata) {
        if (testata == null) {
            this.editTestata.setDescription(null);
            this.editTestata.setStartDate(null);
            this.editTestata.setEndDate(null);
            this.editTestata.setStartHour(null);
            this.editTestata.setEndHour(null);
            this.editTestata.setNotes(null);
            this.editTestata.setValorePunto(null);
        } else {
            this.editTestata.setDescription(testata.getDescrizione());
            this.editTestata.setStartDate(testata.getDataInizio());
            this.editTestata.setEndDate(testata.getDataFine());
            this.editTestata.setStartHour(testata.getOraInizio());
            this.editTestata.setEndHour(testata.getOraFine());
            this.editTestata.setNotes(testata.getNoteMarketing());
            this.editTestata.setValorePunto(testata.getValorePunto());
        }
    }

    public void unlockEditTestata() {
        if (this.editTestata.isLocked()) {
            this.editTestata.setLocked(false);
            final CanalePromozioneEntity canale = promotionSelected.getMuiCanalePromozione();
            final StatoPromozioneEntity stato = PromoAcl.getStatoCorrente(getPromotionSelected());
            final boolean isUserAdmin = isUserAdmin();
            if ((canale == null) || (stato == null)) {
                log.error(
                        String.format(
                                "Error getting 'Canale' and 'Stato' from 'Promozione' %s [id=%d]",
                                promotionSelected.getCodicePromozione(), promotionSelected.getId()));
                this.editTestata.setDescriptionDisabled(false);
                this.editTestata.setStartDateDisabled(false);
                this.editTestata.setEndDateDisabled(false);
                this.editTestata.setStartHourDisabled(false);
                this.editTestata.setEndHourDisabled(false);
                this.editTestata.setNotesDisabled(false);
                this.editTestata.setValorePuntoDisabled(false);
                this.editTestata.setBtnDeleteDisabled(false);
            } else {
                final boolean isEditableDescription =
                        isUserAdmin
                                || applyRule(
                                ActionEnum.EDIT_DESCRIZIONE,
                                canale,
                                stato,
                                ActionTypeEnum.ACTIVE,
                                FormEnum.SCHEDA_PROMO,
                                ElementFieldEnum.FIELD_DESCRIZIONE,
                                false);
                final boolean isEditableStartDate =
                        isUserAdmin
                                || applyRule(
                                ActionEnum.EDIT_DATA_INIZIO,
                                canale,
                                stato,
                                ActionTypeEnum.ACTIVE,
                                FormEnum.SCHEDA_PROMO,
                                ElementFieldEnum.FIELD_DATA_INIZIO,
                                false);
                final boolean isEditableEndDate =
                        isUserAdmin
                                || applyRule(
                                ActionEnum.EDIT_DATA_FINE,
                                canale,
                                stato,
                                ActionTypeEnum.ACTIVE,
                                FormEnum.SCHEDA_PROMO,
                                ElementFieldEnum.FIELD_DATA_FINE,
                                false);
                final boolean isOraInizioActive =
                        isUserAdmin
                                || applyRule(
                                ActionEnum.EDIT_FASCIA_ORARIA,
                                canale,
                                stato,
                                ActionTypeEnum.ACTIVE,
                                FormEnum.SCHEDA_PROMO,
                                ElementFieldEnum.FIELD_ORA_INIZIO,
                                false);
                final boolean isOraFineActive =
                        isUserAdmin
                                || applyRule(
                                ActionEnum.EDIT_FASCIA_ORARIA,
                                canale,
                                stato,
                                ActionTypeEnum.ACTIVE,
                                FormEnum.SCHEDA_PROMO,
                                ElementFieldEnum.FIELD_ORA_FINE,
                                false);
                final boolean isEditableNotes =
                        isUserAdmin
                                || applyRule(
                                ActionEnum.EDIT_NOTE,
                                canale,
                                stato,
                                ActionTypeEnum.ACTIVE,
                                FormEnum.SCHEDA_PROMO,
                                ElementFieldEnum.FIELD_NOTE,
                                false);
                final boolean isDeletable =
                        isUserAdmin
                                || applyRule(
                                ActionEnum.BUTTON_ELIMINA,
                                canale,
                                stato,
                                ActionTypeEnum.ACTIVE,
                                FormEnum.SCHEDA_PROMO,
                                ElementFieldEnum.BUTTON_ELIMINA,
                                false);
                final boolean isEditableValorePunto =
                        (isUserAdmin()
                                || ownershipServiceInstance
                                .get()
                                .hasOwnership(promotionSelected, getUserDto().getGruppi()))
                                && PromoAcl.isEditableValorePunto(promotionSelected);
                this.editTestata.setDescriptionDisabled(!isEditableDescription);
                this.editTestata.setStartDateDisabled(!isEditableStartDate);
                this.editTestata.setEndDateDisabled(!isEditableEndDate);
                this.editTestata.setStartHourDisabled(!isOraInizioActive);
                this.editTestata.setEndHourDisabled(!isOraFineActive);
                this.editTestata.setNotesDisabled(!isEditableNotes);
                this.editTestata.setValorePuntoDisabled(!isEditableValorePunto);
                this.editTestata.setBtnDeleteDisabled(!isDeletable);
            }
        }
    }

    public void discardEditTestata() {
        updateEditTestata(getPromotionSelected());
        disableEditing();
    }

    public void saveEditTestata() {
        try {
            // Validazioni
            final PromoUpdate promo = new PromoUpdate();
            final String description =
                    this.editTestata.getDescription() != null
                            ? this.editTestata.getDescription().trim().toUpperCase()
                            : null;
            if (description != null) {
                this.editTestata.setDescription(description);
            }
            promo.setDescrizione(description);
            promo.setDataInizio(this.editTestata.getStartDate());
            promo.setDataFine(this.editTestata.getEndDate());
            promo.setOraInizio(this.editTestata.getStartHour());
            promo.setOraFine(this.editTestata.getEndHour());
            promo.setNoteMarketing(this.editTestata.getNotes());
            // Necessario per validare le date
            promo.setAnno(getPromotionSelected().getAnno());
            Set<String> messages =
                    promotionValidatorService.validateEditPromotion(promo, getPromotionSelected());
            final String message =
                    promozioneTestataValidator.validateDates(
                            getPromotionSelected(),
                            this.editTestata.getStartDate(),
                            this.editTestata.getEndDate());
            if (message != null) {
                if (messages == null) {
                    messages = new HashSet<>();
                }
                messages.add(message);
            }

            // Controllo validity valore punto
            BigDecimal valorePunto = null;
            if (Boolean.TRUE.equals(
                    getPromotionSelected().getMuiCanalePromozione().getFlValorePuntoFragola())
                    && (isUserAdmin()
                    || ownershipServiceInstance
                    .get()
                    .hasOwnership(getPromotionSelected(), getUserDto().getGruppi()))
                    && PromoAcl.isEditableValorePunto(getPromotionSelected())) {
                valorePunto = this.editTestata.getValorePunto();
                if (valorePunto != null
                        && valorePunto.compareTo(editTestata.getValorePuntoMaxValue()) > 0) {
                    messages.add("Valore punto impostato maggiore del valore consentito");
                }
            }

            // #5405: se canale "oggetti virtuali" allora controlla le date
            // Data inizio PROMOZIONE deve essere >= a data inizio INIZIATIVA
            // Data Fine PROMOZIONE deve essere <= a data fine INIZIATIVA
            if (promotionSelected.getCanalePromozioneEntity().getCodiceCanale() == 34L
                    && promotionSelected.getIniziativa() != null) {
                if (!utils.isAfter(
                        editTestata.getStartDate(), promotionSelected.getIniziativa().getDataInizio(), true)) {
                    messages.add(
                            "Data inizio promozione non valida, deve essere >= a data inizio iniziativa");
                }
                if (!utils.isBefore(
                        editTestata.getEndDate(), promotionSelected.getIniziativa().getDataFine(), true)) {
                    messages.add("Data fine promozione non valida, deve essere <= a data fine iniziativa");
                }
            }

            if ((messages == null) || messages.isEmpty()) {
                // Se sono state modificate le date, sistemo le date dei negozi e delle
                // pianificazioni
                promozioneTestataHelper.adjustDates(
                        getPromotionSelected(), this.editTestata.getStartDate(), this.editTestata.getEndDate());
                // Update entity
                promozioneTestataHelper.updateEntity(
                        getPromotionSelected(),
                        description,
                        this.editTestata.getNotes(),
                        this.editTestata.getStartDate(),
                        this.editTestata.getEndDate(),
                        this.editTestata.getStartHour(),
                        this.editTestata.getEndHour(),
                        valorePunto);
                // OK, salvo
                promoService.persist(getPromotionSelected(), getCurrentUser().getName());
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Testata aggiornata", null));
                disableEditing();
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Correggere i seguenti errori",
                                String.join("\n", messages)));
            }
        } catch (Exception ex) {
            log.warn("Error saving edited PromozioneTestata", ex);
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_FATAL,
                            "Errore",
                            "Impossibile aggiornare la Testata Promozione; contattare l'assistenza tecnica."));
        }
    }

    private void disableEditing() {
        this.editTestata.setLocked(true);
        this.editTestata.setDescriptionDisabled(true);
        this.editTestata.setStartDateDisabled(true);
        this.editTestata.setEndDateDisabled(true);
        this.editTestata.setStartHourDisabled(true);
        this.editTestata.setEndHourDisabled(true);
        this.editTestata.setNotesDisabled(true);
        this.editTestata.setValorePuntoDisabled(true);
        this.editTestata.setBtnDeleteDisabled(true);
    }

    public void deleteTestata() {
        if (getPromotionSelected() != null) {
            if (promozioneTestataHelper.delete(getPromotionSelected(), getCurrentUser().getName())) {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Promozione eliminata",
                                String.format(
                                        "Promozione %s eliminata con successo",
                                        getPromotionSelected().getDescrizioneEstesa())));
                setPromotionSelected(null);
                updateEditTestata(null);
                disableEditing();
                if ((landingActionPage != null) && (landingActionPage > 0)) {
                    executeScript("redirectTo(" + landingActionPage + ");");
                } else if (landingActionPage == null) {
                    // sono arrivato da landing ma è configurato male
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_WARN,
                                    "Attenzione",
                                    "Impossibile fare redirect alla pagina 'Visualizza Promozioni'; errore configurazione menu."));
                }
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_FATAL,
                                "Errore",
                                String.format(
                                        "Impossibile eliminare la promozione %s; contattare l'assistenza tecnica.",
                                        getPromotionSelected().getDescrizioneEstesa())));
                disableEditing();
            }
        }
    }

    public void reloadPromoCorrente() {
        if (getPromotionSelected() != null) {
            setPromozioneCorrente(getTestataById(getPromotionSelected().getId()));
        }
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTab = tv.getChildren().indexOf(event.getTab());
        log.debug("tabChanged");
    }

    private String capitalize(String str) {
        if (str == null) {
            return null;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private void updateStato() {
        if (promotionSelected != null) {
            statoCorrente =
                    promotionSelected.getPromozioneStatoEntities().stream()
                            .filter(st -> st.getDataFineStato() == null)
                            .findFirst()
                            .orElse(null);
        }
    }

    public void deleteMeccanica() {
        try {
            meccaniche.setPromozioneCorrente(getTestataById(Long.valueOf(idPromozioneCorrente.toString())));
        } catch (Exception e) {
            log.error("error reading promozioni", e);
        }
    }

    public void deleteSottoscrizione() {
        try {
            meccaniche.setPromozioneCorrente(getTestataById(Long.valueOf(idPromozioneCorrente.toString())));
        } catch (Exception e) {
            log.error("error reading promozioni", e);
        }
    }

    public void deleteReparto() {
        try {
            reparti.setPromozioneCorrente(getTestataById(Long.valueOf(idPromozioneCorrente.toString())));
        } catch (Exception e) {
            log.error("error reading promozioni", e);
        }
    }

    @Override
    public void updateView() {
        executeScript("refreshGridFilterByRadioCheck(" + this.idPromozioneCorrente + ");");
    }

    public boolean isConfirmBtnDisabled() {
        return this.idStatoTransizioneCorrente == null;
    }

    public void applyFilterToPromozioni() {
        final UsersEntity user = ((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
        readPromotions(user.getDbFilters());
        if ((idPromozioneCorrente != null)
                && (promozioni.stream()
                .filter(
                        testata ->
                                Long.valueOf(idPromozioneCorrente.toString()).equals(testata.getId()))
                .findFirst()
                .orElse(null)
                == null)) {
            setIdPromozioneCorrente(null);
            setPromotionSelected(null);
        }
        if (this.idPromozioneCorrente == null) {
            executeScript("updateSchedaTab();");
        }
    }

    public void resetDialog() {
        this.idStatoTransizioneCorrente = null;
    }

    public List<PromozioneTestataEntity> getPromozioni() {
        readPromotions(getUserDto().getUser().getDbFilters());
        return promozioni;
    }

    public void caricaDaPromo(ActionEvent e) {
        caricaDaPromo();
    }

    public void caricaDaPromo() {
        log.debug("calling P_MUI_IMPOSTA_NEGOZI");
        String message = null;
        String header = "Error";
        Severity severity = FacesMessage.SEVERITY_WARN;
        try {
            if ((getNegoziPromoBean().getSourcePromoSelected() != null)
                    && (getIdPromozioneCorrente() != null)
                    && (getCurrentUser() != null)) {
                if (importaDaPromo(getNegoziPromoBean().getSourcePromoSelected())) {
                    header = "Success!";
                    message =
                            String.format(
                                    "Importazione dei negozi dalla promozione %s alla promozione %s riuscita",
                                    getNegoziPromoBean().getSourcePromoSelected(),
                                    getPromotionSelected().getDescrizioneEstesa());
                    severity = FacesMessage.SEVERITY_INFO;
                } else {
                    message =
                            String.format(
                                    "Errore durante il lancio della stored procedure. Parametri: \npromozione corrente:%s\npromozione sorgente %s",
                                    getPromozioneCorrente().getCodicePromozione(),
                                    getNegoziPromoBean().getSourcePromoSelected());
                }
            } else {
                message = "Impossibile importare i dati: parametri mancanti";
            }
        } catch (Exception e) {
            log.error("Errore durante la chiamata al caricamento negozi", e);
            message = "Impossibile importare i dati a causa di un problema applicativo";
        } finally {
            addMessage(null, new FacesMessage(severity, header, message));
        }
    }

    public void scegliIniziativa() {
        final IniziativaEntity iniziativaSelected = iniziativaBean.getIniziativaSelected();
        if (promotionSelected != null && iniziativaSelected != null) {
            try {
                this.iniziativaBean.setIniziativa(iniziativaSelected);
                promotionSelected.setIniziativa(iniziativaSelected);
                promotionSelected = promoService.persist(promotionSelected);
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Iniziativa caricata",
                                String.format(
                                        "L'iniziativa %s e' stata caricata sulla promozione %s",
                                        iniziativaSelected.getDescrizione(), promotionSelected.getCodicePromozione())));
            } catch (Exception ex) {
                log.error(
                        String.format(
                                "Error saving iniziativa %s to testata %s",
                                iniziativaSelected.getDescrizione(), promotionSelected.getCodicePromozione()),
                        ex);
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Errore",
                                String.format(
                                        "Impossibile caricare iniziativa %s", iniziativaSelected.getDescrizione())));
            }
        } else {
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Errore", "Impossibile caricare iniziativa"));
        }
        applyRules();
    }

    public void scegliPromo() {
        final String codicePromoRif = this.promoRifBean.getSourcePromoSelected();
        if (codicePromoRif != null) {
            final MuiPromoDbPromoEntity promoRiferimento = loadPromoRiferimento(codicePromoRif);
            if ((promoRiferimento != null) && (promotionSelected != null)) {
                try {
                    if (importaDaPromo(codicePromoRif)) {
                        this.promoRifBean.setPromoRiferimento(promoRiferimento);
                        promotionSelected.setCodicePromoRiferimento(codicePromoRif);
                        promoService.persist(promotionSelected);
                        addMessage(
                                null,
                                new FacesMessage(
                                        FacesMessage.SEVERITY_INFO,
                                        "Success!",
                                        String.format("Promo di riferimento %s caricata", codicePromoRif)));
                    } else {
                        addMessage(
                                null,
                                new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR,
                                        "Error",
                                        String.format(
                                                "Impossibile caricare promo di riferimento con codice %s",
                                                codicePromoRif)));
                    }
                } catch (Exception ex) {
                    log.error(
                            String.format(
                                    "Error saving promo riferimento %s to testata %s",
                                    codicePromoRif, promotionSelected.getCodicePromozione()),
                            ex);
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    "Error",
                                    String.format(
                                            "Impossibile caricare promo di riferimento con codice %s", codicePromoRif)));
                }
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Error",
                                String.format(
                                        "Impossibile caricare promo di riferimento con codice %s", codicePromoRif)));
            }
        } else {
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Error", "Impossibile caricare promo di riferimento"));
        }
    }

    public void scegliPlano() {
        final String codicePlanoRif = this.planoRifBean.getSourcePlanoSelected();
        if (codicePlanoRif != null) {
            final MuiPlanoDbPromoEntity planoRiferimento = loadPlanoRiferimento(codicePlanoRif);
            if ((planoRiferimento != null) && (promotionSelected != null)) {
                try {

                    if (importaDaPlano(
                            planoRiferimento.getCategoriaEspositiva(),
                            planoRiferimento.getTipologia(),
                            planoRiferimento.getDimensione())) {
                        promotionSelected.addPlano(planoRiferimento);
                        promoService.persist(promotionSelected);
                        addMessage(
                                null,
                                new FacesMessage(
                                        FacesMessage.SEVERITY_INFO,
                                        "Success!",
                                        String.format("Planogramma di riferimento %s caricata", codicePlanoRif)));
                    } else {
                        addMessage(
                                null,
                                new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR,
                                        "Error",
                                        String.format(
                                                "Impossibile caricare planogramma di riferimento con codice %s",
                                                codicePlanoRif)));
                    }
                } catch (Exception ex) {
                    log.error(
                            String.format(
                                    "Error saving planogramma riferimento %s to testata %s",
                                    codicePlanoRif, promotionSelected.getCodicePromozione()),
                            ex);
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    "Error",
                                    String.format(
                                            "Impossibile caricare planogramma di riferimento con codice %s",
                                            codicePlanoRif)));
                }
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Error",
                                String.format(
                                        "Impossibile caricare planogramma di riferimento con codice %s",
                                        codicePlanoRif)));
            }
            applyRules();
        } else {
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Impossibile caricare planogramma di riferimento"));
        }
    }

    private boolean importaDaPromo(@NonNull String codicePromoRif) {
        return getPromozioneNegozioServiceInstance()
                .get()
                .importaDaPromo(
                        Long.parseLong(idPromozioneCorrente.toString()),
                        codicePromoRif,
                        null, // codiceCategoriaPlano,
                        null, // tipologiaPlano,
                        null, // dimensionePlano,
                        getCurrentUser().getName());
    }

    private boolean importaDaPlano(
            String codiceCategoriaPlano, String tipologiaPlano, String dimensionePlano) {
        return getPromozioneNegozioServiceInstance()
                .get()
                .importaDaPromo(
                        Long.parseLong(idPromozioneCorrente.toString()),
                        null,
                        codiceCategoriaPlano, // codiceCategoriaPlano,
                        tipologiaPlano, // tipologiaPlano,
                        dimensionePlano, // dimensionePlano,
                        getCurrentUser().getName());
    }

    public void prepareRemovePromoRifDialog(ActionEvent e) {
        if (getPromotionSelected() != null) {
            if (getPromotionSelected().getCodicePromoRiferimento() != null) {
                // c'e' un riferimento
                if (getPromoRifBean().getPromoRiferimento() == null) {
                    getPromoRifBean()
                            .setPromoRiferimento(
                                    loadPromoRiferimento(getPromozioneCorrente().getCodicePromoRiferimento()));
                }
            }
        }
    }

    public void prepareRemoveIniziativaDialog(ActionEvent e) {
        if (getPromotionSelected() != null && getPromotionSelected().getIniziativa() != null) {
            if (getIniziativaBean().getIniziativa() != null) {
                getIniziativaBean().setIniziativa(getPromotionSelected().getIniziativa());
            }
        }
    }

    public void removeIniziativa() {
        try {
            PromozioneTestataEntity promozione = getPromotionSelected();
            if (promozione != null) {
                if (promozione.getIniziativa() != null) {
                    final String descIniziativa = getIniziativaBean().getIniziativa().getDescrizione();
                    promozione.setIniziativa(null);
                    promozione = promoService.persist(promozione);
                    setPromotionSelected(promozione);
                    getIniziativaBean().setIniziativa(null);
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO,
                                    "Associazione rimossa",
                                    String.format("Associazione con l'iniziativa %s rimossa", descIniziativa)));
                } else {
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_WARN, "Errore", "Nessuna iniziativa associata"));
                }
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN, "Errore", "Nessuna promozione selezionata"));
            }
        } catch (Exception ex) {
            log.error("errore durante la rimozione dell'iniziativa", ex);
        }
        applyRules();
    }

    public void removeRif() {
        try {
            final PromozioneTestataEntity testata = getPromotionSelected();
            if (testata != null) {
                if (testata.getCodicePromoRiferimento() != null) {
                    // C'e' un riferimento
                    // 1. rimuovere l'associazione con la promozione di riferimento
                    // 2. rimuovere i negozi associati
                    MuiPromoDbPromoEntity promoRiferimento =
                            loadPromoRiferimento(testata.getCodicePromoRiferimento());
                    int negoziAssociati = countNegozi(testata);
                    String message =
                            String.format(
                                    "Associazione con la promozione %s rimossa; rimossi %d negozi associati",
                                    promoRiferimento.getDescrizioneEstesa(), negoziAssociati);

                    testata.setCodicePromoRiferimento(null);
                    removeNegozi(testata);
                    promoService.persist(testata);
                    setPromotionSelected(testata);
                    getPromoRifBean().setPromoRiferimento(null);
                    addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Associazione rimossa", message));
                } else {
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_WARN, "Errore", "Nessuna promozione associata"));
                }
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN, "Errore", "Nessuna promozione selezionata"));
            }
        } catch (Exception e) {
            log.error("errore durante la rimozione dell'associazione", e);
        }
        applyRules();
    }

    public void removePlanoRif() {
        try {
            String idPlano = getRequestParameterMap().get("idPlano");
            final PromozioneTestataEntity testata = getPromotionSelected();
            if ((testata != null) && (idPlano != null)) {
                final MuiPlanoDbPromoEntity planogram = loadPlanoRiferimento(idPlano);
                if ((planogram != null) && executeRemoveNegozi(testata.getId(), planogram)) {
                    testata.removePlano(planogram);
                    promoService.persist(testata);
                    setPromotionSelected(testata);
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO,
                                    "Planogram rimosso",
                                    String.format("Planogram %s rimosso correttamente", planogram.getCodicePlano())));
                } else {
                    addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    "Errore",
                                    "Errore durante la rimozione del planogram"));
                }
            } else {
                addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN, "Errore", "Nessuna promozione selezionata"));
            }
        } catch (Exception e) {
            log.error("errore durante la rimozione dell'associazione", e);
            addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Errore", "Errore durante la rimozione del planogram"));
        }
        applyRules();
    }

    private boolean executeRemoveNegozi(Long idPromozione, MuiPlanoDbPromoEntity planogram) {
        return getPromozioneNegozioServiceInstance()
                .get()
                .eliminaNegozi(
                        idPromozione,
                        planogram.getCategoriaEspositiva(),
                        planogram.getTipologia(),
                        planogram.getDimensione());
    }

    private void removeNegozi(@NonNull PromozioneTestataEntity testata) {
        // questo "schifo" serve per evitare una eccezione di concurrenza sulla lista
        // dei negozi
        PromozioneNegozioService service = promozioneNegozioServiceInstance.get();
        final List<Long> negozi =
                testata.getPromozioneNegozioEntities().stream()
                        .map(PromozioneNegozioEntity::getId)
                        .distinct()
                        .collect(Collectors.toList());
        for (Long id : negozi) {
            final PromozioneNegozioEntity negozio = service.findById(id);
            if (negozio != null) {
                testata.removePromozioneNegozioEntity(negozio);
            }
        }
    }

    private int countNegozi(@NonNull PromozioneTestataEntity testata) {
        return (testata.getPromozioneNegozioEntities() != null)
                && !testata.getPromozioneNegozioEntities().isEmpty()
                ? testata.getPromozioneNegozioEntities().size()
                : 0;
    }

    private void applyTabsPosition() {
        renderedTabs.clear();
        renderedTabs.add(TabEnum.TAB_STATI);
        renderedTabs.add(TabEnum.TAB_MECCANICHE);
        if (tabPromoRiferimentoRendered) {
            renderedTabs.add(TabEnum.TAB_PROMO_RIFERIMENTO);
        }
        if (tabPlanoRiferimentoRendered) {
            renderedTabs.add(TabEnum.TAB_PLANO_RIFERIMENTO);
        }
        if (tabIniziativaRendered) {
            renderedTabs.add(TabEnum.TAB_INIZIATIVA);
        }
        renderedTabs.add(TabEnum.TAB_NEGOZI);
        if (tabFlagRendered) {
            renderedTabs.add(TabEnum.TAB_FLAG);
        }
        if (tabAttributiRendered) {
            renderedTabs.add(TabEnum.TAB_ATTRIBUTI);
        }
        if (tabTipoCassaRendered) {
            renderedTabs.add(TabEnum.TAB_TIPO_CASSA);
        }
        if (tabRepartiRendered) {
            renderedTabs.add(TabEnum.TAB_REPARTI);
        }
        renderedTabs.add(TabEnum.TAB_PUBBLICAZIONI);
        if (tabOwnerRedered) {
            renderedTabs.add(TabEnum.TAB_OWNER);
        }
        if (tabModificaRendered) {
            renderedTabs.add(TabEnum.TAB_MODIFICA);
        }
        if (tabControlliRendered) {
            renderedTabs.add(TabEnum.TAB_CONTROLLI);
        }
        if (promotionSelected != null
                && promotionSelected.getCanalePromozioneEntity().getFlMarchioPrivato()) {
            renderedTabs.add(TabEnum.TAB_MARCHIO_PRIVATO);
        }
        if (tabSottoscrizioniRendered) {
            renderedTabs.add(TabEnum.TAB_SOTTOSCRIZIONI);
        }
        if (tabContattiRendered) {
            renderedTabs.add(TabEnum.TAB_CONTATTI);
        }
        renderedTabItems =
                renderedTabs.stream()
                        .map(this::findByName)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }

    private void updateRepartoMarchioPrivato() {
        if (promotionSelected != null
                && promotionSelected.getCanalePromozioneEntity().getFlMarchioPrivato()) {
            repartiMarchioPrivato =
                    promoRepartoMarchioPrivatoServiceInstance
                            .get()
                            .findByIdPromozione(promotionSelected.getId());
            repartiMarchioPrivato.sort(Comparator.comparing(p -> p.getReparto().getCodiceReparto()));
        } else {
            repartiMarchioPrivato.clear();
        }
    }

    public int indexOf(TabEnum tab) {
        return renderedTabs.indexOf(tab);
    }

    @Override
    public void applyRules() {
        final boolean isUserAdmin = isUserAdmin();
        applyDefaultRules();
        if (promotionSelected != null) {
            try {
                final StatoPromozioneEntity stato = PromoAcl.getStatoCorrente(promotionSelected);
                final CanalePromozioneEntity canale = promotionSelected.getMuiCanalePromozione();
                if ((canale == null) || (stato == null)) {
                    log.error(
                            String.format(
                                    "Error getting 'Canale' and 'Stato' from 'Promozione' %s [id=%d]",
                                    promotionSelected.getCodicePromozione(), promotionSelected.getId()));
                } else {
                    final boolean isOwner =
                            isUserAdmin
                                    || ownershipServiceInstance
                                    .get()
                                    .hasOwnership(promotionSelected, getUserDto().getGruppi());
                    tabPromoRiferimentoRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_PROMO_RIFERIMENTO,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_PROMO_RIFERIMENTO,
                                    tabPromoRiferimentoRendered);
                    tabPlanoRiferimentoRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_PLANO_RIFERIMENTO,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_PLANO_RIFERIMENTO,
                                    tabPlanoRiferimentoRendered);
                    tabIniziativaRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_INIZIATIVA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_INIZIATIVA,
                                    tabIniziativaRendered);
                    btnAggiungiMeccanicheActive =
                            isOwner
                                    && applyRule(
                                    ActionEnum.BUTTON_AGGIUNGI_MECCANICHE,
                                    canale,
                                    stato,
                                    ActionTypeEnum.ACTIVE,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BUTTON_AGGIUNGI_MECCANICHE,
                                    btnAggiungiMeccanicheActive);
                    tabFlagRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_FLAG_TESTATA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_FLAG,
                                    tabFlagRendered);
                    tabAttributiRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_ATTRIBUTI_TESTATA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_ATTRIBUTI,
                                    tabAttributiRendered);
                    tabTipoCassaRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_TIPO_CASSA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_TIPO_CASSA,
                                    tabTipoCassaRendered);
                    tabRepartiRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_REPARTI,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_REPARTI,
                                    tabRepartiRendered);
                    tabModificaRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_MODIFICA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_MODIFICA,
                                    tabModificaRendered);
                    btnAggiungiTipoCassaActive =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.BUTTON_AGGIUNGI_TIPO_CASSA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.ACTIVE,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BUTTON_AGGIUNGI_TIPO_CASSA,
                                    btnAggiungiTipoCassaActive);
                    btnAggiungiRepartiActive =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.BUTTON_AGGIUNGI_REPARTI,
                                    canale,
                                    stato,
                                    ActionTypeEnum.ACTIVE,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BUTTON_AGGIUNGI_REPARTI,
                                    btnAggiungiRepartiActive);
                    btnUnlockActive =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.BUTTON_UNLOCK,
                                    canale,
                                    stato,
                                    ActionTypeEnum.ACTIVE,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BUTTON_UNLOCK,
                                    btnUnlockActive);
                    fieldOraInizioRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_FASCIA_ORARIA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.FIELD_ORA_INIZIO,
                                    fieldOraInizioRendered);
                    fieldOraFineRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_FASCIA_ORARIA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.FIELD_ORA_FINE,
                                    fieldOraFineRendered);
                    btnScegliPromoActive =
                            isOwner
                                    && applyRule(
                                    ActionEnum.BUTTON_SCEGLI_PROMO,
                                    canale,
                                    stato,
                                    ActionTypeEnum.ACTIVE,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BUTTON_SCEGLI_PROMO,
                                    btnScegliPromoActive);
                    btnScegliIniziativaActive =
                            isOwner
                                    && applyRule(
                                    ActionEnum.BUTTON_SCEGLI_INIZIATIVA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.ACTIVE,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BUTTON_SCEGLI_INIZIATIVA,
                                    btnScegliIniziativaActive);
                    btnScegliPlanoActive =
                            isOwner
                                    && applyRule(
                                    ActionEnum.BUTTON_SCEGLI_PLANO,
                                    canale,
                                    stato,
                                    ActionTypeEnum.ACTIVE,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BUTTON_SCEGLI_PLANO,
                                    btnScegliPlanoActive);
                    uploadNegoziRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.UPLOAD_NEGOZI,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.BLOCK_CARICAMENTO_NEGOZI,
                                    uploadNegoziRendered);
                    tabControlliRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_CONTROLLI,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_CONTROLLI,
                                    tabControlliRendered);
                    tabOwnerRedered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_OWNER,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_OWNER,
                                    tabOwnerRedered);
                    if (getPromoRifBean().getPromoRiferimento() != null) {
                        btnEliminaAssociazionePromoActive =
                                isOwner
                                        && applyRule(
                                        ActionEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PROMO,
                                        canale,
                                        stato,
                                        ActionTypeEnum.ACTIVE,
                                        FormEnum.SCHEDA_PROMO,
                                        ElementFieldEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PROMO,
                                        btnEliminaAssociazionePromoActive);
                    } else {
                        btnEliminaAssociazionePromoActive = false;
                    }
                    if (getIniziativaBean().getIniziativa() != null) {
                        btnEliminaIniziativaActive =
                                isOwner
                                        && applyRule(
                                        ActionEnum.BUTTON_ELIMINA_ASSOCIAZIONE_INIZIATIVA,
                                        canale,
                                        stato,
                                        ActionTypeEnum.ACTIVE,
                                        FormEnum.SCHEDA_PROMO,
                                        ElementFieldEnum.BUTTON_ELIMINA_ASSOCIAZIONE_INIZIATIVA,
                                        btnEliminaIniziativaActive);
                    } else {
                        btnEliminaIniziativaActive = false;
                    }
                    textBoxWarningNegozi =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TEXTBOX_WARNING_NEGOZI,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TEXTBOX_WARNING_NEGOZI,
                                    btnAggiungiTipoCassaActive);
                    negoziCediRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_NEGOZI_CEDI,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_NEGOZI_CEDI,
                                    negoziCediRendered);
                    negoziZonaRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.TAB_NEGOZI_ZONA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.TAB_NEGOZI_ZONA,
                                    negoziZonaRendered);
                    tipoPuntoVenditaRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_NEGOZI_PUNTO_VENDITA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.NEGOZI_TIPO_PUNTO_VENDITA,
                                    tipoPuntoVenditaRendered);
                    canaleConsegnaRendered =
                            isUserAdmin
                                    || applyRule(
                                    ActionEnum.RENDER_NEGOZI_CANALE_CONSEGNA,
                                    canale,
                                    stato,
                                    ActionTypeEnum.RENDER,
                                    FormEnum.SCHEDA_PROMO,
                                    ElementFieldEnum.NEGOZI_CANALE_CONSEGNA,
                                    canaleConsegnaRendered);
                    tabSottoscrizioniRendered = "CONFIGURATORE".equalsIgnoreCase(contesto)
                            || "'CONFIGURATORE'".equalsIgnoreCase(contesto);
                    tabContattiRendered = handleTabContattiRendered();
                }
            } catch (Exception ex) {
                log.error("Error applying rules", ex);
            }
        }
    }

    @Override
    public void applyDefaultRules() {
        tabPromoRiferimentoRendered = false;
        btnAggiungiMeccanicheActive = false;
        tabTipoCassaRendered = false;
        tabFlagRendered = false;
        tabRepartiRendered = false;
        tabModificaRendered = false;
        btnAggiungiTipoCassaActive = false;
        btnAggiungiRepartiActive = false;
        btnUnlockActive = false;
        fieldOraInizioRendered = false;
        fieldOraFineRendered = false;
        btnScegliPromoActive = false;
        btnScegliIniziativaActive = false;
        uploadNegoziRendered = false;
        btnScegliPlanoActive = false;
        tabPlanoRiferimentoRendered = false;
        tabIniziativaRendered = false;
        btnEliminaAssociazionePromoActive = false;
        tabControlliRendered = false;
        tabOwnerRedered = false;
        textBoxWarningNegozi = false;
        negoziCediRendered = false;
        negoziZonaRendered = false;
        btnEliminaIniziativaActive = false;
        tabSottoscrizioniRendered = false;
        btnClonaPromoRendered = handleButtonClonaPromo();
        tabContattiRendered = false;
    }

    private boolean handleButtonClonaPromo() {
        return contesto != null && contesto.toUpperCase().trim().contains("CUST_ENG");
    }

    public String getTabStoresMessage() {
        return applicationProperties.getProperty(
                ApplicationProperties.TAB_STORES_MESSAGE, ApplicationProperties.DEFAULT_TAB_STORES_MESSAGE);
    }

    @PostConstruct
    public void createTabs() {
        tabs = new ArrayList<>();
        tabs.add(new TabItem("Stati", "tabs/statiPromo.xhtml", TabEnum.TAB_STATI));
        tabs.add(new TabItem("Meccaniche", "tabs/meccanichePromo.xhtml", TabEnum.TAB_MECCANICHE));
        tabs.add(new TabItem("Promo Riferimento", "tabs/riferimentoPromo.xhtml", TabEnum.TAB_PROMO_RIFERIMENTO));
        tabs.add(new TabItem("Planogram Rif.", "tabs/riferimentoPlano.xhtml", TabEnum.TAB_PLANO_RIFERIMENTO));
        tabs.add(new TabItem("Iniziativa", "tabs/iniziativa.xhtml", TabEnum.TAB_INIZIATIVA));
        tabs.add(new TabItem("Negozi", "tabs/negoziPromo.xhtml", TabEnum.TAB_NEGOZI));
        tabs.add(new TabItem("Flag", "tabs/flagPromo.xhtml", TabEnum.TAB_FLAG));
        tabs.add(new TabItem("Dati Aggiuntivi", "tabs/attributiPromo.xhtml", TabEnum.TAB_ATTRIBUTI));
        tabs.add(new TabItem("Tipo Cassa", "tabs/tipoCassaPromo.xhtml", TabEnum.TAB_TIPO_CASSA));
        tabs.add(new TabItem("Reparti", "tabs/repartiPromo.xhtml", TabEnum.TAB_REPARTI));
        tabs.add(new TabItem("Pubblicazioni", "tabs/pubblicazioniPromo.xhtml", TabEnum.TAB_PUBBLICAZIONI));
        tabs.add(new TabItem("Owner", "tabs/ownershipPromo.xhtml", TabEnum.TAB_OWNER));
        tabs.add(new TabItem("Modifica", "tabs/editPromo.xhtml", TabEnum.TAB_MODIFICA));
        tabs.add(new TabItem("Controlli", "tabs/controlliPromo.xhtml", TabEnum.TAB_CONTROLLI));
        tabs.add(new TabItem("Marchio Privato", "tabs/marchioPrivato.xhtml", TabEnum.TAB_MARCHIO_PRIVATO));
        tabs.add(new TabItem("Sottoscrizioni", "tabs/sottoscrizioniPromo.xhtml", TabEnum.TAB_SOTTOSCRIZIONI));
        tabs.add(new TabItem("Contatti", "tabs/contattiPromo.xhtml", TabEnum.TAB_CONTATTI));
    }

    private TabItem findByName(TabEnum name) {
        return tabs.stream().filter(t -> t.getName().equals(name)).findAny().orElse(null);
    }

    private void updateMarchioPrivato(PromozioneTestataEntity testata) {
        if (testata != null && testata.getCanalePromozioneEntity().getFlMarchioPrivato()) {
            marchiPrivati =
                    promozioneMarchioPrivatoServiceInstance.get().findByIdPromozione(testata.getId()).stream()
                            .filter(m -> m.getMarchioPrivato().getOrdineEsposizione() != null)
                            .sorted(Comparator.comparing(m -> m.getMarchioPrivato().getOrdineEsposizione()))
                            .collect(Collectors.toList());
        } else {
            marchiPrivati = new ArrayList<>();
        }
        readFlagMarchioPrivato();
    }

    // hack del male. Per qualche motivo il valore passato dal fe e' sempre false
    public void setMarchioPrivatoAbilitato(boolean abilitato) {
        this.marchioPrivatoAbilitato = !this.marchioPrivatoAbilitato;
        marchioPrivatoDisabled = !marchioPrivatoAbilitato;
        if (marchioPrivatoDisabled) {
            marchioPrivatoAutomatico = false;
            marchioPrivatoEsteso = false;
            marchioPrivatoAutomaticoDisabled = true;
        }
    }

    public void setMarchioPrivatoAutomatico(boolean automatico) {
        this.marchioPrivatoAutomatico = !this.marchioPrivatoAutomatico;
        marchioPrivatoAutomaticoDisabled = !marchioPrivatoAutomatico;
        if (marchioPrivatoAutomaticoDisabled) {
            marchioPrivatoEsteso = false;
        }
    }

    public void setMarchioPrivatoEsteso(boolean abilitato) {
        this.marchioPrivatoEsteso = !this.marchioPrivatoEsteso;
    }

    public void toggleMarchioPrivAbilitato() {
        updateFlagMarchioPrivato();
    }

    public void toggleMarchioPrivAutomatico() {
        updateFlagMarchioPrivato();
    }

    public void toggleMarchioPrivEsteso() {
        updateFlagMarchioPrivato();
    }

    public void toggleMarchioPrivato(PromozioneMarchioPrivatoEntity marchio) {
        if (marchio != null) {
            PromozioneMarchioPrivatoEntity db =
                    promozioneMarchioPrivatoServiceInstance.get().findById(marchio.getId());
            if (db != null) {
                marchio.setAttivo(!db.isAttivo());
                promozioneMarchioPrivatoServiceInstance.get().persist(marchio);
            }
        } else {
            log.error(
                    "Errore nel recupero della configurazione del marchio privato per la promozione "
                            + promotionSelected.getCodicePromozione());
        }
    }

    private void updateDifferenziazioneMeccanica() {
        this.differenziazioneMeccanicaRendered =
                promotionSelected != null
                        && promotionSelected.getCanalePromozioneEntity().getFlDifferenziazioneNegozi();
        this.differenziazioneMeccanicaAbilitata =
                promotionSelected != null && promotionSelected.getFlDifferenziazioneMeccanica();
    }

    public void setDifferenziazioneMeccanicaAbilitata(boolean differenziazioneMeccanicaAbilitata) {
        this.differenziazioneMeccanicaAbilitata = differenziazioneMeccanicaAbilitata;
        this.promotionSelected.setFlDifferenziazioneMeccanica(differenziazioneMeccanicaAbilitata);
        this.promoService.persist(this.promotionSelected);
    }

    public void toggleReparto(PromoRepartoMarchioPrivato reparto) {
        if (reparto != null) {
            PromoRepartoMarchioPrivato db =
                    promoRepartoMarchioPrivatoServiceInstance.get().findById(reparto.getId());
            if (db != null) {
                reparto.setAttivo(!db.isAttivo());
                promoRepartoMarchioPrivatoServiceInstance
                        .get()
                        .persistWithAuditLog(reparto, getCurrentUser().getName());
            }
        }
    }

    private void updateFlagMarchioPrivato() {
        if (promotionSelected != null) {
            if (marchioPrivatoAbilitato) {
                if (marchioPrivatoAutomatico) {
                    if (marchioPrivatoEsteso) {
                        promotionSelected.setFlagMarchioPrivato(ModalitaMarchioPrivato.ESTESO);
                    } else {
                        promotionSelected.setFlagMarchioPrivato(ModalitaMarchioPrivato.AUTOMATICO);
                    }
                } else {
                    promotionSelected.setFlagMarchioPrivato(ModalitaMarchioPrivato.SI);
                }
            } else {
                promotionSelected.setFlagMarchioPrivato(ModalitaMarchioPrivato.NO);
            }
            promotionSelected = promoService.persist(promotionSelected);
        }
    }

    private void readFlagMarchioPrivato() {
        marchioPrivatoAbilitato = false;
        marchioPrivatoAutomatico = false;
        marchioPrivatoEsteso = false;
        if (promotionSelected != null
                && promotionSelected.getCanalePromozioneEntity().getFlMarchioPrivato()) {
            switch (promotionSelected.getFlagMarchioPrivato()) {
                case SI:
                    marchioPrivatoEsteso = false;
                    marchioPrivatoAutomatico = false;
                    marchioPrivatoAbilitato = true;
                    break;
                case AUTOMATICO:
                    marchioPrivatoEsteso = false;
                    marchioPrivatoAutomatico = true;
                    marchioPrivatoAbilitato = true;
                    break;
                case ESTESO:
                    marchioPrivatoEsteso = true;
                    marchioPrivatoAutomatico = true;
                    marchioPrivatoAbilitato = true;
                    break;
                case NO:
                default:
                    marchioPrivatoEsteso = false;
                    marchioPrivatoAutomatico = false;
                    marchioPrivatoAbilitato = false;
                    break;
            }
        }
        marchioPrivatoDisabled = !marchioPrivatoAbilitato;
        marchioPrivatoAutomaticoDisabled = !marchioPrivatoAutomatico;
    }

    public class TabItem {
        @Getter
        String title;
        @Getter
        String content;
        @Getter
        TabEnum name;

        public TabItem(@NonNull String title, @NonNull String content, @NonNull TabEnum name) {
            this.title = title;
            this.content = content;
            this.name = name;
        }
    }

    public String getStatoStyleError() {
        StringBuilder sb = new StringBuilder("width: 100%; background-color: transparent;");
        if (statoCorrente != null) {
            final String codiceStato = statoCorrente.getStatoPromozioneEntity().getCodiceStato();
            if (PromoStatusEnum.IN_PIANIFICAZIONE_CON_ERRORI.getKey().equals(codiceStato)
                    || PromoStatusEnum.IN_ESECUZIONE_CON_ERRORI.getKey().equals(codiceStato)) {
                sb.append("color: #8B0000;");
            }
        }
        return sb.toString();
    }

    public void validateAttributoPreUpdate() {
        String handleSponsorMessage = null;
        boolean showConfirmDialog = false;
        boolean showError = false;
        try {
            Map<String, String> params = getRequestParameterMap();
            Long idAttributo = Long.parseLong(params.get("idAttributo"));
            String nomeAttributo = params.get("nomeAttributo");
            String valoreAttributo = params.get("valoreAttributo");
            if ("NUMERO_SPONSOR".equalsIgnoreCase(nomeAttributo)) {
                if (valoreAttributo != null && valoreAttributo.trim().equals("1")) {
                    handleSponsorMessage = "Il valore scelto prevede l'assenza del castelletto";
                }
                String overlappedPromo = validateOverlappedPromo(idAttributo, valoreAttributo);
                if (overlappedPromo != null) {
                    if (handleSponsorMessage != null) {
                        handleSponsorMessage += "<br/>";
                    } else {
                        handleSponsorMessage = "";
                    }
                    handleSponsorMessage += String.format("Il valore scelto viene usato anche nelle seguenti promozioni:<br/>%s",
                            overlappedPromo);
                }
                if (handleSponsorMessage != null) {
                    handleSponsorMessage += "<br/><br/>Confermi la modifica?";
                    showConfirmDialog = true;
                }
            }
        } catch (Exception ex) {
            log.error("Errore durante la validazione dell'attributo", ex);
            showError = true;
        }
        getAjax().addCallbackParam("handleSponsorMessage", handleSponsorMessage);
        getAjax().addCallbackParam("showConfirmDialog", showConfirmDialog);
        getAjax().addCallbackParam("showError", showError);
    }

    private String validateOverlappedPromo(Long idAttributo, String valoreAttributo) {
        List<PromozioneTestataEntity> overlapped = promoService.findOverlappingPromoWithAttributo(promotionSelected,
                idAttributo, valoreAttributo);
        return overlapped == null || overlapped.isEmpty()
                ? null
                : overlapped.stream()
                .map(PromozioneTestataEntity::getDescrizioneEstesa)
                .collect(Collectors.joining("<br/>"));
    }

    private PromozioneTestataEntity getTestataById(@NonNull Long id) {
        return promoService.findById(id);
    }

    private PromozioneStatoEntity getLastStatus(@NonNull PromozioneTestataEntity testata) {
        return testata.getPromozioneStatoEntities().stream()
                .filter(st -> st.getDataFineStato() == null)
                .findFirst()
                .orElse(null);
    }

    /**
     * Tab contatti visibile solo se la promozione ha il canale 'Buoni Brand' o 'Buoni Prodotto' o 'Buoni Prodotto Omaggio'
     * (rispettivamente codice canale 43, 14 e 44)
     *
     * @return true se promozione nei canali specificati, false altrimenti
     */
    private boolean handleTabContattiRendered() {
        return promotionSelected != null
                && promotionSelected.getMuiCanalePromozione() != null
                && canalePromozioneService.countByIdWithTipologiaInitialLoad(promotionSelected.getMuiCanalePromozione().getId()) > 0;
    }
}
