package com.axiante.mui.webapp.template;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.backing.CellSetCache;
import com.axiante.mui.backing.ConfigurationCatalog;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.business.reader.ConfigurationReader;
import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.filter.ConfigurationFilterCatalog;
import com.axiante.mui.filter.DimensionWatch;
import com.axiante.mui.filter.DimensionWatcher;
import com.axiante.mui.filter.utils.FilterUtils;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.persistence.service.RolesService;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.mui.webapp.template.menu.MenuProducer;
import com.axiante.mui.webapp.utils.RoleMenuUtil;
import com.axiante.mui.webapp.utils.cells.CellUtils;
import com.axiante.mui.webapp.utils.preselections.PreselectionUtils;
import com.axiante.mui.webapp.views.DBPromoNavigationEnabled;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.MuiViewFactory;
import com.axiante.mui.webapp.views.MuiViewInterface;
import com.axiante.mui.webapp.views.content.admin.AbstractAdminView;
import com.axiante.mui.webapp.views.content.admin.pojos.ManualDto;
import com.axiante.mui.webapp.views.content.aggrid.params.GridParam;
import com.axiante.mui.webapp.views.content.aggrid.preselections.PreSelection;
import com.axiante.mui.webapp.views.content.dbpromo.PianificazionePianoMediaView;
import com.axiante.mui.webapp.views.content.dbpromo.SchedaFatturazioneView;
import com.axiante.mui.webapp.views.content.dbpromo.SchedaIniziativaView;
import com.axiante.mui.webapp.views.content.dbpromo.SchedaPianoMediaView;
import com.axiante.mui.webapp.views.filter.postfilterselection.FilterSelected;
import com.axiante.mui.webapp.views.filter.postfilterselection.FilterSelectedInterface;
import com.axiante.tm1.TunnelConstants;
import com.axiante.utility.configuration.Configuration;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

@Named(value = TemplateView.BEAN_NAME)
@SessionScoped
@Slf4j
public class TemplateView implements Serializable, FacesContextAware {
    @Getter

    private String implementationVersion;
    public static final String BEAN_NAME = "templateView";
    private static final long serialVersionUID = 8148280354302402676L;
    private static final String BREADCRUMB_SEPARATOR = " " + new Character((char) 8226).toString() + " ";

    @Getter
    @Setter
    private boolean suppressRows;

    @Getter
    @Setter
    private boolean suppressColumns;

    @Getter
    @Setter
    MenuModel mainMenu;

    @Getter
    UsersEntity user;

    private MuiViewInterface currentView = null;
    private MuiViewInterface errorView;

    @Getter
    final String title = "Workflow Promozionale";

    @Getter
    @Setter
    private String currentMenuPath;

    @Getter
    @Setter
    private String breadCrumb;

    private DefaultMenuItem defaultNode;

    @Getter
    private final LinkedHashMap<String, GridParam> gridParamHashMap = new LinkedHashMap<>();

    @Getter
    protected LinkedHashMap<String, PreSelection> availablePreselectionsMap = new LinkedHashMap<>();

    @Getter
    protected HashMap<String, String> preselectedGridMap = new HashMap<>();

    String currentJsonFilter;

    @Getter
    List<String> compulsoryFilters;

    @Getter
    String compulsoryFiltersMessage;

    @Inject
    transient MuiViewFactory viewFactory;

    @Inject
    transient TableProducer tableProducer;

    @Inject
    transient MuiService muiService;

    @Inject
    transient ConfigurationReader configurationReader;

    @Inject
    transient ConfigurationCatalog configurationCatalog;

    @Inject
    transient ConfigurationFilterCatalog configurationFilterCatalog;

    @Getter
    @Setter
    @Inject
    transient FilterSelectedInterface filterSelected;

    @Inject
    transient MenuProducer menuProducer;

    @Inject
    transient FilterUtils filterUtils;

    @Inject
    transient CellSetCache cellSetCache;

    @Inject
    transient ConnectionCatalog connectionCatalog;

    @Inject
    @Getter
    CookieRepository cookieRepository;

    @Setter
    @Getter
    private String version;

    @Inject
    transient DimensionWatcher versionWatcher;

    @Inject
    transient AuditLogService auditLogService;

    @Inject
    transient ApplicationConfiguration configuration;

    @Inject
    transient ApplicationPropertiesService applicationPropertiesService;

    @Inject
    transient CellUtils cellUtils;

    @Inject
    transient PreselectionUtils preselectionUtils;

    @Inject
    Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;
    private List<String> enabledConnections = null;

    @Getter
    boolean agGridView = true;

    @Getter(value = AccessLevel.PROTECTED)
    private final String logoutPage = null;

    @Inject
    transient RoleMenuUtil roleMenuUtil;

    HashSet<String> usedConnections = new HashSet<>();

    @Inject
    ApplicationProperties applicationProperties;

    @Inject
    RolesService rolesService;

    @Getter
    private List<UploadDocumentEntity> documents;

    @Getter
    private List<ManualDto> manuals;

    @PostConstruct
    public void init() {
        // set version for JS imports
        if (this.getExternalContext() != null) {
            try (InputStream inputStream = this.getExternalContext().getResourceAsStream("/META-INF/MANIFEST.MF")) {
                this.implementationVersion = new Manifest(inputStream).getMainAttributes()
                        .getValue("Implementation-Version");
            } catch (final IOException e) {
                log.error("Error retrieving application's version from MANIFEST.", e);
            }
        } else {
            log.error("error reading external context: implementationVersion will be set to TEST");
            this.implementationVersion = "test";
        }

        // prepare default objects
        this.createDefaultNode();
        this.prepareErrorView();
        // paranoia mode: this *should* set the current bean to null on a new session
        try {
            this.reloadMenu((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
        } catch (final Exception e) {
            log.error("Error creazting menu", e);
            this.addGrowl("Errore", "Errore durante la creazione del menu.", FacesMessage.SEVERITY_ERROR);
        }

        // set the passport
        final Object oPassport = this.getSessionMap().get(TunnelConstants.CAMPASSPORT);
        if (getCookieRepository() != null) {
            getCookieRepository();
//            cookieRepository.setCAMPassport(oPassport == null ? null : oPassport.toString());
            // set the keep alive for the timer
            try {
                cookieRepository.initialize(oPassport == null ? null : oPassport.toString());
            } catch (ContextNotActiveException e){
                log.error("Contesto non attivo", e);
                goToErrorPage();
            }
//            cookieRepository.keepAlive();
            // test delle connessioni abilitate
            checkConnections();
            // default view
            this.goToHomepage();
        } else {
            log.error("Cookie repository not injected");
            goToErrorPage();
        }

        // Load documents for given user with given roles
        documents = getUser().getRoles().stream().flatMap(r -> r.getDocuments().stream()).distinct()
                .sorted(Comparator.comparing(UploadDocumentEntity::getName)).collect(Collectors.toList());

        // Load manuals for given user with given roles
        manuals = getUser().getRoles().stream().filter(r -> r.getHelpFilename() != null).map(ManualDto::new)
                .sorted(Comparator.comparing(ManualDto::getRoleName)).collect(Collectors.toList());


    }

    /**
     * controlla di avere accesso alle connessioni: per ogni connessione configurata
     * tenta di accedere. Se la risposta e' positiva allora aggiunge la connessione
     * a un oggetto di sessione che indica a quali connessioni/server si ha accesso.
     */
    private void checkConnections() {
        try{
            enabledConnections = connectionCatalog.availableConfigurationList().stream()
                    .filter(conf -> (getCookieRepository().getCookie(conf) != null)).map(ConnectionProfile::getName)
                    .collect(Collectors.toList());
            if (this.filterSelected != null) {
                this.filterSelected.applyEnabledConnections(enabledConnections);
            } else {
                log.warn("Impossibile applicare l'esclusione dei filtri in base alla connessione abilitata");
            }
            int tested = connectionCatalog.availableConfigurationList() != null ? connectionCatalog.availableConfigurationList().size() : 0;
            int enabled = tested != 0 && enabledConnections != null ? enabledConnections.size() : 0;
            log.debug(String.format("tested java %d, enabled %d connections", tested, enabled));
        } catch (Exception e){
            log.error("There was an error checking the available connections ", e);
        }
    }

    public void goToHomepage() {
        final MenuActionEvent actionEvent = new MenuActionEvent(new UIMenuItem(), this.defaultNode);
        this.menuItemClicked(actionEvent);
    }

    private void goToErrorPage() {
        try {
            this.getExternalContext()
                    .redirect(this.getRequestContextPath() + "/views/logout.xhtml?faces-redirect=true");
        } catch (final Exception e) {
            log.error("error redirecting to error view ", e);
        }
    }

    public void reloadMenu(final UsersEntity usersEntity) throws Exception {
        this.setUser(usersEntity);
        this.setMainMenu(this.menuProducer.readMenu(usersEntity));
    }

    @Deprecated
    protected boolean redirectToWorkInProgress() {
        return redirectToWorkInProgress(null);
    }

    protected boolean redirectToWorkInProgress(MenuItem menuItem) {
        boolean isAdminMode = applicationPropertiesService.calculateAdminMode();

        if (isAdminMode) {
            boolean hasAccess = applicationPropertiesService.hasAccessAsAdmin(this.getUser().getRoles());
            if (menuItem == null) {
                return !hasAccess;
            }
            if (!hasAccess) {
                // non ho accesso:
                return true;
            }
            if (getUser().getRoles().stream().filter(Objects::nonNull).filter(RolesEntity::isAdmin).findFirst()
                    .isPresent()) {
                // sono admin
                return false;
            }
            // se non admin
            try {
                final Integer idMenu = Integer.valueOf(menuItem.getParams().get("idMenu").get(0));
                // Welcome page (idMenu=-1) caso particolare, posso sempre accedere
                if (idMenu == -1) {
                    return false;
                }
                MenuEntity menuEntity = muiService.findMenu(idMenu);
                return !roleMenuUtil.canViewMenu(this.getUser().getRoles(), menuEntity);
            } catch (Exception e) {
                log.error("errore recupero menu entity", e);
            }
            return true;
        }
        return false; // no admin mode: se clicchi entri
    }

    public synchronized void menuItemClicked(final ActionEvent event) {
        final MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
        final MuiViewInterface oldView = getCurrentView();
        if (this.redirectToWorkInProgress(menuItem)) {
            executeScript("window.location = 'work_in_progress.xhtml' ");
        } else {
            this.setVersion(null);
            final MuiViewInterface newView = this.viewFactory.getView(menuItem);
            newView.setEnabledConnections(enabledConnections);
            // set breadcrumb
            try {
                this.setBreadCrumb(
                        menuItem.getParams().get("path").get(0).substring(1).replaceAll("/", BREADCRUMB_SEPARATOR));
            } catch (final NullPointerException e) {
                log.error("the node " + menuItem.getValue()
                        + " is not properly configured as it is missing the path declaration", e);
            }
            if (log.isDebugEnabled()) {
                log.info("user " + getUser().getName() + " is navigating to " + getBreadCrumb());
            }
            auditLogService.logAction(AuditLogEntity.MENU_CLICK, getBreadCrumb(), getUser().getName());
            // add admin filters
            if ((newView != null) && (this.getUser() != null)) {
                newView.setAdminJsonFilter(this.getUser().getAdminFilters());
                switch (newView.getViewType()) {
                    case MUI:
                        newView.setCurrentJsonFilter(this.getUser().getFilters());
                        agGridView = true;
                        break;
                    case DBPROMO:
                        newView.setCurrentJsonFilter(this.getUser().getDbFilters());
                        agGridView = false;
                        break;
                    case ADMIN:
                        log.info("opening an admin view. let's be sure to pass the connections ....");
                        newView.setEnabledConnections(enabledConnections);
                        agGridView = true;
                        ((AbstractAdminView) newView).forceReload();
                        break;
                    default:
                        agGridView = true;
                        log.debug("this view does not need user filters");

                }
            }
            if ((this.currentView != null) && this.currentView.equals(newView)) {
                return;
            }
            this.currentView = newView;
            usedConnections.clear();
            if (this.currentView == null) {
                log.error("Error finding view for " + this.getCurrentMenuPath() + " ... falling back to error xhtml");
                this.currentView = this.errorView;
            } else {
                // all these configurations make sense only if
                // the view is actually configurable
                switch (newView.getViewType()) {
                    case ADMIN:
                        // devo aggiornare i filtri prima di aggiornare il db locale
                        this.getCurrentView().prepareJsonFilter();
                        // issue #3454
                        executeScript("refreshGlobalFilters(" + this.currentView.getJsonFilter() + ")");
                        break;
                    default:
                        try {
                            final LinkedHashMap<String, Configuration> configuration = new LinkedHashMap<>(
                                    Objects.requireNonNull(this.readConfiguration(menuItem)));

                            if (currentView.isMuiView()) {
                                // #3213
                                usedConnections.addAll(configuration.entrySet().stream().map(Entry::getValue)
                                        .map(Configuration::getProfile).map(ConnectionProfile::getName)
                                        .collect(Collectors.toSet()));
                            }
                            // Configure gridParams and Compulsory Filters
                            this.compulsoryFilters = new ArrayList<>();
                            this.compulsoryFiltersMessage = null;
                            this.gridParamHashMap.clear();
                            for (final Map.Entry<String, Configuration> entry : configuration.entrySet()) {
                                final Configuration conf = entry.getValue();
                                if (conf.getCompulsoryFilters() != null) {
                                    this.compulsoryFilters.addAll(conf.getCompulsoryFilters());
                                    this.compulsoryFiltersMessage = conf.getCompulsoryFiltersMessage();
                                    if ((this.compulsoryFiltersMessage == null)
                                            || this.compulsoryFiltersMessage.equals("")) {
                                        this.compulsoryFiltersMessage = "Necessario impostare filtri per visualizzare questa form.";
                                    }
                                }
                                final String grid = entry.getKey();
                                final GridParam gridParam = new GridParam();
                                gridParam.setColSuppressionEnabled(conf.getColSuppressionEnabled());
                                gridParam.setRowSuppressionEnabled(conf.getRowSuppressionEnabled());
                                gridParam.setHeight(conf.getHeight());
                                gridParam.setTitle(conf.getTitle());
                                gridParam.setCss(conf.getCss());
                                gridParam.setGridFilterEnabled(
                                        (conf.getGridFilters() != null) && (conf.getGridFilters().size() > 0));
                                this.gridParamHashMap.put(grid, gridParam);
                            }

                            this.currentView.setConfigurationMap(configuration);
                            this.currentView.setAvailableGrids(this.readAvailableGrids(menuItem));
                            log.debug("reloading filters for " + menuItem.getTitle());

                            // Filtri MUI
                            if (currentView.isMuiView() || currentView.isWelcomeView()) {
                                this.currentView.prepareJsonFilter();
                            }
                            // Filtri DBPROMO
                            if (currentView.isPromoView()) {
                                this.currentView.prepareDBPromoJsonFilter();
                            }

                            // show version of the end point if present #948
                            this.version = null;
                            final Configuration versionHelper = configuration.entrySet().stream().map(Entry::getValue)
                                    .filter(c -> ((c.getShowVersion() != null) && c.getShowVersion())).findAny()
                                    .orElse(null);
                            if (versionHelper != null) {
                                this.versionWatcher.checkVersion(
                                        this.getCookieRepository().getCookie(versionHelper.getProfile()),
                                        versionHelper.getProfile());
                                final DimensionWatch w = this.versionWatcher.getWatches()
                                        .get(versionHelper.getProfile().getName());
                                if (w != null) {
                                    this.version = w.getWatch().getLastSchemaUpdate();
                                } else {
                                    log.error("error reading schema version");
                                }

                            }
                            executeScript("refreshGlobalFilters(" + this.currentView.getJsonFilter() + ")");
                            log.debug("reloading preselections for " + menuItem.getTitle());
                            this.readAvailablePreselectionsMap();
                        } catch (final Exception e) {
                            log.error("error reading from configuration: " + menuItem.getUrl(), e);
                            this.currentView = this.errorView;
                        }
                }
            }

            // paranoia setting
            if (this.currentView == null) {
                this.currentView = errorView;
            }

            // swap views: free resources
            if (oldView != null) {
                oldView.free();
            }

            this.updateComponent("k_sub_header");
        }
    }

    protected void createDefaultNode() {
        this.defaultNode = new DefaultMenuItem("Welcome");
        final Map<String, List<String>> params = new HashMap<>();
        params.put("url", Collections.singletonList("welcome.xhtml"));
        params.put("bean", Collections.singletonList("welcome"));
        params.put("path", Collections.singletonList("/welcome"));
        params.put("idMenu", Collections.singletonList("-1"));
        this.defaultNode.setParams(params);
    }

    protected void prepareErrorView() {
        final DefaultMenuItem errorNode = new DefaultMenuItem("Error");
        final Map<String, List<String>> params = new HashMap<>();
        params.put("url", Collections.singletonList("error.xhtml"));
        params.put("bean", Collections.singletonList("error"));
        params.put("path", Collections.singletonList("/Error"));
        errorNode.setParams(params);
        this.errorView = this.viewFactory.getView(errorNode);
    }

    public MuiViewInterface getCurrentView() {
        return this.currentView;
    }

    public void setCurrentView(MuiViewInterface currentView) {
        this.currentView = currentView;
    }

    public void removeDimensionFilter() {
        final Map<String, String> params = this.getRequestParameterMap();
        final String dimension = params.get("dimension");
        final JSONObject filters = new JSONObject(this.getUser().getFilters());
        final JSONObject filtersDBPromo = this.getUser().getDbFilters() != null
                ? new JSONObject(this.getUser().getDbFilters())
                : new JSONObject("{}");
        for (final Iterator<String> iterator = filters.keys(); iterator.hasNext(); ) {
            final String key = iterator.next();
            final JSONObject filter = (JSONObject) filters.get(key);
            if ((dimension != null) && dimension.equals(filter.getString("Dimension"))) {
                iterator.remove();
            }
        }
        for (final Iterator<String> iterator = filtersDBPromo.keys(); iterator.hasNext(); ) {
            final String key = iterator.next();
            final JSONObject filter = (JSONObject) filtersDBPromo.get(key);
            if ((dimension != null) && dimension.equals(filter.getString("Dimension"))) {
                iterator.remove();
            }
        }
        this.updateCurrentFilters(filters.toString(), filtersDBPromo.toString());
    }

    public void updateCurrentJsonFilter(final ActionEvent e) {
        String jsonFilterd = this.getRequestParameterMap().get("jsonFilterd");
        if (jsonFilterd == null) {
            jsonFilterd = "{}";
        }
        String muiFilters = getUser().getFilters();
        String dbPromoFilters = getUser().getDbFilters();
        if (muiFilters == null) {
            muiFilters = "{}";
        }
        if (dbPromoFilters == null) {
            dbPromoFilters = "{}";
        }
        final String activeMuiFilters = filterUtils.removeUnchangedFiltersFromStore(muiFilters,
                getCurrentView().getAvailableFilters(), jsonFilterd);
        final String activePromoFilters = filterUtils.removeUnchangedDBPromoFiltersFromStore(dbPromoFilters,
                getCurrentView().getAvailableFilters(), jsonFilterd);

        this.updateCurrentFilters(activeMuiFilters, activePromoFilters);
    }

    protected void updateCurrentFilters(final String json, final String jsonDBPromo) {
        if (json != null) {
            this.filterSelected.parseJsonFilter(json);

            // issue #2530 : dopo la chiusura del filtro in db promo i dati vengono
            // cancellati
            enabledConnections = connectionCatalog.availableConfigurationList().stream()
                    .filter(conf -> (getCookieRepository().getCookie(conf) != null)).map(ConnectionProfile::getName)
                    .collect(Collectors.toList());

            this.filterSelected.applyEnabledConnections(enabledConnections);
            this.getUser().setFilters(json);
        }
        if (jsonDBPromo != null) {
            this.filterSelected.parseDBPromoJsonFilter(jsonDBPromo);
            this.getUser().setDbFilters(jsonDBPromo);
        }

        try {
            this.muiService.persistUser(this.getUser());
        } catch (final Exception ex) {
            log.error("Error saving filter status: ", ex);
            this.addGrowl("Errore", "Errore durante il salvataggio dei filtri.", FacesMessage.SEVERITY_ERROR);
        }

        final StringBuffer currentJsonFilter = new StringBuffer();
        if (!StringUtils.isEmpty(this.getUser().getFilters())) {
            currentJsonFilter
                    .append(this.getUser().getFilters().substring(0, this.getUser().getFilters().length() - 1));
        }
        if (!StringUtils.isEmpty(this.getUser().getDbFilters())) {
            if (currentJsonFilter.length() == 0) {
                currentJsonFilter.append(this.getUser().getDbFilters());
            } else {
                currentJsonFilter.append(",").append(this.getUser().getDbFilters().substring(1));
            }
        } else if (currentJsonFilter.length() > 0) {
            currentJsonFilter.append("}");
        }

        if ((json != null) && (jsonDBPromo != null)) {
            this.setCurrentJsonFilter(currentJsonFilter.toString());
            this.getCurrentView().setCurrentJsonFilter(currentJsonFilter.toString());
        }
        if (enabledConnections == null) {
            enabledConnections = new ArrayList<>();
        }
        if (this.filterSelected != null) {
            this.filterSelected.applyEnabledConnections(enabledConnections);
        }
    }

    protected Map<String, Configuration> readConfiguration(@NonNull final MenuItem node) {
        if ((node.getParams().get("idMenu") != null) && (node.getParams().get("idMenu").get(0) != null)) {
            try {
                final MenuEntity menu = this.muiService
                        .findMenu(Integer.valueOf(node.getParams().get("idMenu").get(0)));
                if (menu != null) {
                    final ConfigurationEntity configuration = menu.getConfigurations().stream()
                            .filter(c -> c.getType().equals(ConfigurationTypes.GRID))
                            .filter(c -> c.getRevisionDate() == null).findFirst().orElse(null);
                    if (configuration != null) {
                        return this.configurationCatalog.readConfiguration(configuration.getJson());
                    }
                }
            } catch (final Exception e) {
                log.error("error getting configuration for node " + node.getParams().get("path").get(0), e);
            }
        }
        return new HashMap<>();
    }

    private List<String> readAvailableGrids(@NonNull final MenuItem node) {
        final List<String> list = new ArrayList<>();
        boolean hasError = false;
        try {
            if ((node.getParams().get("idMenu") != null) && (node.getParams().get("idMenu").size() > 0)) {
                final Integer idMenu = Integer.valueOf(node.getParams().get("idMenu").get(0));
                if (idMenu == -1) {
                    // welcome page, do nothing

                } else {
                    final MenuEntity menu = this.muiService.findMenu(idMenu);
                    if (menu != null) {
                        final ConfigurationEntity configuration = menu.getConfigurations().stream()
                                .filter(c -> c.getType().equals(ConfigurationTypes.GRID))
                                .filter(c -> c.getRevisionDate() == null).findFirst().orElse(null);
                        if (configuration != null) {
                            list.addAll(this.configurationCatalog.readAvailableGrids(configuration.getJson()));
                        } else {
                            hasError = true;
                        }
                    } else {
                        hasError = true;
                    }
                }
            } else {
                hasError = true;
            }
        } catch (final Exception e) {
            log.error("error reading available grids ", e);
        } finally {
            if (hasError) {
                String path = null;
                if ((node.getParams() != null) && (node.getParams().get("path") != null)) {
                    path = node.getParams().get("path").get(0);
                }
                if ("/welcome".equals(path)) {
                    log.info("user " + getUser().getName() + " navigated to welcome page");
                } else {
                    log.error("no grid configured for node " + path);
                }
            }
        }
        return list;
    }

    public void toggleNonEmpty() {
        final Map<String, String> params = this.getRequestParameterMap();
        final String grid = params.get("grid");
        final String type = params.get("type");
        final String value = params.get("toggle");
        final boolean toggle = Boolean.valueOf(value);
        if ((grid != null) && (type != null) && (value != null)) {
            if ("columns".equals(type)) {
                this.getCurrentView().getConfiguration(grid).getMdx().getCols().setNonEmpty(toggle);
            } else if ("rows".equals(type)) {
                this.getCurrentView().getConfiguration(grid).getMdx().getRows().setNonEmpty(toggle);
            } else {
                log.error("don't know what to do !");
                return;
            }
        }
        if (this.currentView.canLoadData(grid)) {
            final String className = "columns".equals(type) ? ".settingsRadioCols" : ".settingsRadioRows";
            final String selector = "$(\".expandable_" + grid + "\").find(\"" + className + "\")";
            PrimeFaces.current().executeScript(selector + ".toggleClass(\"selected\")");
            this.currentView.updateView();
        } else {
            // remove loading spinner
            this.currentView.removeSpinner(grid);
            // rollback suppression
            final Configuration configuration = this.currentView.getConfiguration(grid); //
            if ("columns".equals(type)) {
                configuration.getMdx().getCols().setNonEmpty(!toggle);
            } else if ("rows".equals(type)) {
                configuration.getMdx().getRows().setNonEmpty(!toggle);
            } else {
                log.error("don't know what to do !");
                return;
            }
            String message = null;
            if (configuration != null) {
                message = configuration.getMaxCellsMessage();
            }

            if (message == null) {
                message = "Aggiungere filtri alla selezione attuale.";
            }

            this.currentView.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Raggiunto limite massimo di records!", message));
        }

    }

    public void getPicklistValues() {
        this.cellUtils.getPicklistValues(this.getRequestParameterMap(), this.getCurrentView());
    }

    public void updateCellsByOrdinal() {
        this.cellUtils.updateCellsByOrdinal(this.getRequestParameterMap(), this.currentView);
    }

    public void persistHiddenColumns() {
        final Map<String, String> params = this.getRequestParameterMap();
        final String gridId = params.get("grid");
        final JSONArray cols = new JSONArray(params.get("cols"));
        for (int j = 0; j < cols.length(); j++) {
            final String colId = cols.getJSONObject(j).getString("colId");
            final boolean visible = cols.getJSONObject(j).getBoolean("visible");
            final JSONArray jsonArr = new JSONArray(this.user.getHiddenCols());
            boolean gridFound = false;
            for (int i = 0; i < jsonArr.length(); i++) {
                final JSONObject jsonObj = jsonArr.getJSONObject(i);
                if (jsonObj.getString("grid").equals(gridId)) {
                    gridFound = true;
                    boolean colFound = false;
                    final JSONArray colsArr = jsonObj.getJSONArray("colId");
                    for (int k = 0; k < colsArr.length(); k++) {
                        final JSONObject column = colsArr.getJSONObject(k);
                        if (colId.equals(column.getString("name"))) {
                            column.put("visible", visible);
                            colFound = true;
                            break;
                        }
                    }
                    if (!colFound) {
                        final JSONObject column = new JSONObject();
                        column.put("name", colId);
                        column.put("visible", visible);
                        colsArr.put(column);
                    }
                }
                if ((jsonObj.getJSONArray("colId") == null) || (jsonObj.getJSONArray("colId").length() == 0)) {
                    jsonArr.remove(i);
                }
            }
            if (!gridFound) {
                final JSONObject column = new JSONObject();
                column.put("name", colId);
                column.put("visible", visible);
                final JSONArray colsArr = new JSONArray();
                colsArr.put(column);
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("grid", gridId);
                jsonObject.put("colId", colsArr);
                jsonArr.put(jsonObject);
            }
            this.user.setHiddenCols(jsonArr.toString());
            try {
                this.muiService.persistUser(this.user);
            } catch (final Exception e) {
                log.error("Impossibile salvare configurazione colonne visibili.", e);
            }
        }
    }

    protected void addGrowl(final String title, final String message, final FacesMessage.Severity type) {
        this.addMessage(null, new FacesMessage(type, title, message));
    }

    public void addErrorGrowl() {
        this.getCurrentView().setDisableButtonFilters(false);
        final Map<String, String> params = this.getRequestParameterMap();
        this.addGrowl(params.get("title") != null ? params.get("title") : "Title not found",
                params.get("message") != null ? params.get("message") : "Message not found",
                FacesMessage.SEVERITY_ERROR);
    }

    public void addInfoGrowl() {
        final Map<String, String> params = this.getRequestParameterMap();
        this.addGrowl(params.get("title") != null ? params.get("title") : "Title not found",
                params.get("message") != null ? params.get("message") : "Message not found",
                FacesMessage.SEVERITY_INFO);
    }

    public void addWarningGrowl() {
        final Map<String, String> params = this.getRequestParameterMap();
        this.addGrowl(params.get("title") != null ? params.get("title") : "Title not found",
                params.get("message") != null ? params.get("message") : "Message not found",
                FacesMessage.SEVERITY_WARN);
    }

    public void setUser(final UsersEntity user) {
        this.user = user;
        if (user != null) {
            this.currentJsonFilter = user.getFilters();
            this.filterSelected.parseJsonFilter(this.currentJsonFilter);
            // Attenzione a quali filtri mettiamo... bisogna sommare i filtri di dbpromo se
            // presenti
            if (user.getDbFilters() != null) {
                this.filterSelected.parseDBPromoJsonFilter(user.getDbFilters());
            }
        }

    }

    public boolean keepSessionAlive() {
        log.debug("keepSessionAlive();");
        return true;
    }

    public StreamedContent downloadManual(ManualDto manual) {
        byte[] content = rolesService.findById(manual.getRoleId()).getHelpData();
        return new DefaultStreamedContent(new ByteArrayInputStream(content), "application/pdf",
                manual.getFilename(), content.length);
    }

    protected void readAvailablePreselectionsMap() {
        this.preselectionUtils.setUser(this.getUser()).readAvailablePreselectionsMap(this.availablePreselectionsMap,
                new HashMap<>(this.getCurrentView().getConfigurationMap()), this.preselectedGridMap);
    }

    public List<PreSelection> getAvailablePreSelections() {
        return new ArrayList<>(new ArrayList<>(this.getAvailablePreselectionsMap().values()));
    }

    public void performPreSelection() {
        this.preselectionUtils.setUser(this.getUser()).performPreSelection(this.getRequestParameterMap(),
                this.getAvailablePreselectionsMap());
    }

    public boolean isComponentVisible(final String componentId) {
        if (componentId == null) {
            return false;
        }
        if (getUser().isAdmin()) {
            return true;
        }
        return this.validateAcl("VISIBLE", componentId);
    }

    public boolean isComponentDisabled(final String componentId) {
        if (componentId == null) {
            return true;
        }
        if (getUser().isAdmin()) {
            return false;
        }
        return this.validateAcl("DISABLED", componentId);
    }

    public boolean isComponentEditable(final String componentId) {
        if (componentId == null) {
            return false;
        }
        if (getUser().isAdmin()) {
            return true;
        }
        return this.validateAcl("EDITABLE", componentId);
    }

    private boolean validateAcl(final String validation, final String componentId) {
        try {
            final Stream<AclEntity> stream = user.getRoles().stream()
                    .flatMap(r -> r.getAcls().stream())
                    .filter(a -> componentId.equals(a.getComponent().trim()));
            switch (validation.toUpperCase()) {
                case "EDITABLE":
                    return stream.anyMatch(AclEntity::getEditable);
                case "DISABLED":
                    return stream.noneMatch(AclEntity::getEnabled);
                case "VISIBLE":
                    return stream.anyMatch(AclEntity::getVisible);
                default:
                    return false;
            }
        } catch (Exception ex) {
            log.error(String.format("Unable to get acls for user '%s'", user.getName()), ex);
        }
        return false;
    }

    public String getCurrentJsonFilter() {
        if (StringUtils.isEmpty(this.currentJsonFilter)) {
            return "{}";
        }
        return this.currentJsonFilter.replaceAll("'", "\\\\'");
    }

    public Boolean getRenderCumpulsoryFilters() {
        if ((this.compulsoryFilters != null) && (this.compulsoryFilters.size() > 0)) {
            boolean valid = true;
            if ((this.filterSelected != null) && (this.filterSelected.getDimensions().size() > 0)) {
                for (final String compulsoryFilter : this.compulsoryFilters) {
                    if (!this.filterSelected.getDimensions().contains(compulsoryFilter)) {
                        valid = false;
                    }
                }
            } else {
                valid = false;
            }
            return valid;
        }
        return true;
    }

    public void simulateMenuClick() {
        final Map<String, String> params = this.getRequestParameterMap();
        if (params.get("menuId") == null) {
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
                    "There is a configuration error for the link you clicked"));
        } else {
            try {
                final Integer menuId = Integer.parseInt(params.get("menuId"));
                if (RoleMenuUtil.FORBIDDEN_MENU.equals(menuId)) {
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
                            "You're not authorized to view the link you clicked"));
                } else {
                    final MenuEntity menuEntity = this.muiService.findMenu(menuId);
                    if (menuEntity.getExternalLink()) {
                        executeScript("window.open('" + menuEntity.getUrl() + "', '_blank').focus();");
                        return;
                    }
                    final MenuItem menuItem = this.menuProducer.createItem(menuEntity,
                            " " + this.menuProducer.findPath(menuEntity));
                    final MenuActionEvent actionEvent = new MenuActionEvent(new UIMenuItem(), menuItem);
                    this.menuItemClicked(actionEvent);
                    // DBPromo cambio view: è necessario settare la promozione selezionata nella
                    // nuova view
                    if (params.get("currentPromoId") != null) {
                        final Integer currentPromoId = Integer.parseInt(params.get("currentPromoId"));

                        if (currentView instanceof DBPromoNavigationEnabled) {
                            ((DBPromoNavigationEnabled) currentView).setIdPromozioneCorrente(currentPromoId);
                        }
                    }

                    if (params.get("currentIniziativaId") != null) {
                        if (currentView instanceof SchedaIniziativaView) {
                            final Long currentIniziativaId = Long.parseLong(params.get("currentIniziativaId"));
                            ((SchedaIniziativaView) currentView).setIdIniziativaSelected(currentIniziativaId);
                        }
                    }

                    if (params.get("currentPianoMediaId") != null) {
                        final Long currentPianoMediaId = Long.parseLong(params.get("currentPianoMediaId"));
                        if (currentView instanceof SchedaPianoMediaView) {
                            ((SchedaPianoMediaView) currentView).setIdPianoMediaSelected(currentPianoMediaId);
                            ((SchedaPianoMediaView) currentView).setActiveTab(0);
                        }
                        if (currentView instanceof PianificazionePianoMediaView) {
                            ((PianificazionePianoMediaView) currentView).setIdPianoMediaSelected(currentPianoMediaId);
                            ((PianificazionePianoMediaView) currentView).setActiveTab(0);
                        }
                    }

                    if (params.get("currentPianificazionePianoMedia") != null) {
                        if (currentView instanceof SchedaPianoMediaView) {
                            final Long idPianificazione = Long.parseLong(params.get("currentPianificazionePianoMedia"));
                            PianificazionePianoMediaEntity pianificazionePianoMediaEntity = pianificazionePianoMediaServiceInstance.get().findById(idPianificazione);
                            if (pianificazionePianoMediaEntity != null) {
                                ((SchedaPianoMediaView) currentView).setIdPianoMediaSelected(pianificazionePianoMediaEntity.getPianoMedia().getId());
                                ((SchedaPianoMediaView) currentView).setActiveTab(1);
                            }
                        }
                    }

                    this.executeScript("location.reload();");
                    // HACK: Gestione navigazione da 'Riepilogo Fatturazione' a 'Scheda Fatturazione'
                    //  dopo il reload della pagina, altrimenti abbiamo un errore js sul caricamento della griglia
                    if (params.get("currentRataId") != null && params.get("currentPromozioneId") != null) {
                        if (currentView instanceof SchedaFatturazioneView) {
                            final Long currentRataId = Long.parseLong(params.get("currentRataId"));
                            final Long currentPromozioneId = Long.parseLong(params.get("currentPromozioneId"));
                            ((SchedaFatturazioneView) currentView).setIdPromozioneAndIdRata(currentPromozioneId, currentRataId);
                        }
                    }
                }
            } catch (final Exception e) {
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
                        "There is a configuration error for the link you clicked"));
                log.error("Error opening link", e);
            }
        }
    }

    public void setCurrentJsonFilter(String json) {
        this.currentJsonFilter = json;
    }

//    private CookieRepository getCookieRepository() {
//        return getCookieRepository(false);
//    }
//
//    private CookieRepository getCookieRepository(boolean reload) {
//        if (reload) {
//            this.cookieRepository = instantiateOne();
//        } else if (cookieRepository == null) {
//            if (this.cookieRepositoryInstance != null) {
//                this.cookieRepository = cookieRepositoryInstance.get();
//                this.cookieRepository.healthCheck(); // needed as CDI proxies the class
//            }
//        }
//        return cookieRepository;
//    }
//
//    private CookieRepository instantiateOne() {
//        if (this.cookieRepositoryInstance != null) {
//            this.cookieRepository = cookieRepositoryInstance.get();
//            this.cookieRepository.healthCheck(); // needed as CDI proxies the class
//        } else {
//            log.error("CookieRepository not injected !");
//        }
//        return cookieRepository;
//    }

    public boolean isConnectionAvailable(String dimension) {
        // #3213
        if (usedConnections.size() == 0) {
            switch (getCurrentView().getViewType()) {
                case ADMIN:
                case WELCOME:
                    return true;
                case DBPROMO:
                    return FilterSelected.DB_PROMO_DIMENSION_NAME.equals(dimension);
                default:
                    return false;
            }
        }
        if (dimension != null) {
            if (!getCurrentView().isMuiView()) {
                // includi solo dbpromo
                return FilterSelected.DB_PROMO_DIMENSION_NAME.equals(dimension);
            }
            switch (dimension) {
                case FilterSelected.DB_PROMO_DIMENSION_NAME:
                    // includi DB_PROMO_DIMENSION_NAME sse dbpromo view
                    return false;
                default:
                    if (filterSelected.getMap().get(dimension) != null) {
                        // mui promo view e filtro non dbpromo se ho una dimensione da cercare allora la
                        // cerco
                        return filterSelected.getMap().get(dimension).stream()
                                .filter(f -> usedConnections.contains(f.getServerName())
                                        // FiltredJsonBean::getEnabled
                                ).findAny().isPresent();
                    }
                    // #3355
                    // sto validando l'accesso per una dimensione non configurata nell'elenco dei
                    // filtri possibili.
                    log.warn(String.format(
                            "Richiesta di verifica connessione per dimensione non presente (%s): verificare la configurazione dei filtri",
                            dimension));
                    return false;
            }
        }
        // odd ... da qualche parte ho un filtro con dimensione null...
        log.warn("Richiesta di verifica connessione per dimensione \"null\": verificare la configurazione dei filtri");
        return false;
    }

    public StreamedContent downloadDocument(UploadDocumentEntity document) {
        final String filename = applicationProperties.getProperty(ApplicationProperties.DOCUMENT_DATA_PATH,
                ApplicationProperties.DOCUMENT_DATA_PATH_DEFAULT) + File.separator + document.getName();
        final File file = new File(filename);
        if (!file.exists()) {
            final String msg = String.format("Il file '%s' richiesto non è presente", document.getName());
            log.error(msg);
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));
            return null;
        }
        try {
            auditLogService.logAction(AuditLogEntity.DOWNLOAD_CLICK, document.getName(), getUser().getName());
            return new DefaultStreamedContent(new FileInputStream(file), null, document.getName());
        } catch (Exception ex) {
            final String msg = String.format("Errore nel recupero del file '%s' richiesto", document.getName());
            log.error(msg, ex);
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));
        }
        return null;
    }

    public void pollMuiContext() {
        // callback per settare il contesto nel front-end
        executeScript(String.format("writeMuiContext('%s')", getCurrentView().getContesto()));
    }

}
