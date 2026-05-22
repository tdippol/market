package com.axiante.mui.webapp.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.SessionParams;
import com.axiante.mui.filter.ConfigurationFilterCatalog;
import com.axiante.mui.filter.utils.FilterUtils;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.SlowRolesEntity;
import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.persistence.service.RolesService;
import com.axiante.mui.webapp.template.menu.MenuProducer;
import com.axiante.mui.webapp.views.AbstractMuiView;
import com.axiante.mui.webapp.views.MuiViewFactory;
import com.axiante.mui.webapp.views.MuiViewInterface;
import com.axiante.mui.webapp.views.ViewType;
import com.axiante.mui.webapp.views.content.admin.pojos.ManualDto;
import com.axiante.mui.webapp.views.filter.postfilterselection.FilterSelectedInterface;
import com.axiante.tm1.mdx.objects.MdxEntry;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.primefaces.PrimeFaces;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.json.JSONObject;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

@RunWith(MockitoJUnitRunner.class)
public class TemplateViewTest {
    @Mock
    MuiService muiService;
    @Mock
    FilterSelectedInterface filterSelected;
    @Mock
    MuiViewFactory viewFactory;
    @Mock
    ActionEvent actionEvent;
    @Mock
    MenuActionEvent menuActionEvent;
    @Mock
    HashMap<String, Object> sessionMap;
    @Mock
    Map<String, String> paramsMap;
    @Mock
    MenuProducer menuProducer;
    @Mock
    MuiToken muiToken;
    @Mock
    TableProducer tableProducer;
    @Mock
    HttpUtils httpUtils;
    @Mock
    ConnectionCatalog connectionCatalog;
    @Mock
    ConfigurationFilterCatalog configurationFilterCatalog;
    @Mock
    CookieRepository cookieRepository;
    @Mock
    UsersEntity user;
    @Mock
    RolesEntity role;
    @Mock
    AuditLogService auditLogService;
    @Mock
    ApplicationPropertiesService applicationPropertiesService;
    @Mock
    ApplicationProperties applicationProperties;

    @Mock
    RolesService rolesService;
    @Spy
    @InjectMocks
    TemplateView templateView;

    @Mock
    FilterUtils filterUtils;

    @Mock
    PrimeFaces primefaces;

    @Test
    public void testInit() throws Exception {
        doNothing().when(this.templateView).createDefaultNode();
        doNothing().when(this.templateView).prepareErrorView();
        doNothing().when(this.templateView).goToHomepage();
        doNothing().when(this.templateView).reloadMenu(any());
        doReturn(this.sessionMap).when(this.templateView).getSessionMap();
        doReturn(user).when(this.sessionMap).get(SessionParams.USER_ATTRIBUTE);
        when(user.getRoles()).thenReturn(Collections.singleton(role));
        when(role.getDocuments()).thenReturn(new HashSet<>());
        this.templateView.init();
    }

    @Test(expected = Exception.class)
    public void testInitThrowsException() throws Exception {
        this.templateView.init();
    }

    @Test
    public void testUpdateCurrentJsonFilter() throws Exception {
        final String storedFilters = "{\"1\":{\"Dimension\":\"Promozione\",\"Dimension_code\":\"promozione\",\"Dimension_desc\":\"Promozione\",\"MDXjsonSource\":\"nothing\",\"Attribute\":\"Canale\",\"Attribute_code\":\"canale\",\"Attribute_desc\":\"Canale\",\"ServerName\":\"esselunga_test\",\"selectedValues\":[\"Istituzionale\"]},\"4\":{\"Dimension\":\"Promozione\",\"Dimension_code\":\"promozione\",\"Dimension_desc\":\"Promozione\",\"MDXjsonSource\":\"nothing\",\"Attribute\":\"MUI_Semestre\",\"Attribute_code\":\"semestre\",\"Attribute_desc\":\"MUI_Semestre\",\"ServerName\":\"esselunga_test\",\"selectedValues\":[\"I Semestre\"]},\"5\":{\"Dimension\":\"Promozione\",\"Dimension_code\":\"promozione\",\"Dimension_desc\":\"Promozione\",\"MDXjsonSource\":\"nothing\",\"Attribute\":\"Descrizione + Data\",\"Attribute_code\":\"descrizione\",\"Attribute_desc\":\"Descrizione + Data\",\"ServerName\":\"esselunga_test\",\"selectedValues\":[\"[Lun 08-04-19 -13gg] BUONA PASQUA + SENZA GLUTINE\"]},\"10\":{\"Dimension\":\"MacroSpazio\",\"Dimension_code\":\"macrospazio\",\"Dimension_desc\":\"MacroSpazio\",\"MDXjsonSource\":\"nothing\",\"Attribute\":\"Descrizione\",\"Attribute_code\":\"descrizione\",\"Attribute_desc\":\"Descrizione\",\"ServerName\":\"esselunga_test\",\"selectedValues\":[\"Pigna Grande\",\"Pigna Media\",\"Pigna Rep Pro\"]}}";
        final String configFiltersJson = "{\"promozione\":[\"anno\",\"canale\",\"tipo\",\"riferimento\",\"semestre\", \"descrizione\"]}";
        final String selectedFilters = "{\"5\":{\"Dimension\":\"Promozione\",\"Dimension_code\":\"promozione\",\"Dimension_desc\":\"Promozione\",\"MDXjsonSource\":\"nothing\",\"Attribute\":\"Descrizione + Data\",\"Attribute_code\":\"descrizione\",\"Attribute_desc\":\"Descrizione + Data\",\"ServerName\":\"esselunga_test\",\"selectedValues\":[\"[Gio 25-07-19 -14gg] SCONTO 40% + ESTATE IN TAVOLA + SENZA GLUTINE\"]}}";
        final String expectedFilterJson = "{\"0\":{\"Attribute_desc\":\"Descrizione\",\"Dimension_desc\":\"MacroSpazio\",\"Attribute\":\"Descrizione\",\"selectedValues\":[\"Pigna Grande\",\"Pigna Media\",\"Pigna Rep Pro\"],\"MDXjsonSource\":\"nothing\",\"Attribute_code\":\"descrizione\",\"ServerName\":\"esselunga_test\",\"Dimension_code\":\"macrospazio\",\"Dimension\":\"MacroSpazio\"},\"1\":{\"Attribute_desc\":\"Descrizione + Data\",\"Dimension_desc\":\"Promozione\",\"Attribute\":\"Descrizione + Data\",\"selectedValues\":[\"[Gio 25-07-19 -14gg] SCONTO 40% + ESTATE IN TAVOLA + SENZA GLUTINE\"],\"MDXjsonSource\":\"nothing\",\"Attribute_code\":\"descrizione\",\"ServerName\":\"esselunga_test\",\"Dimension_code\":\"promozione\",\"Dimension\":\"Promozione\"}}";

        final String fixedJson = this.fixFilterJson(storedFilters, configFiltersJson, selectedFilters);

        assertEquals(fixedJson, expectedFilterJson);
    }

    @Test
    public void testGoToHomepage() throws Exception {
        doNothing().when(this.templateView).menuItemClicked(any());
        this.templateView.goToHomepage();
    }

    @Test
    public void testMenuProducer() throws Exception {
        doReturn(mock(MenuModel.class)).when(this.menuProducer).readMenu(any());
        this.templateView.reloadMenu(null);
    }

    @Test(expected = Exception.class)
    public void testMenuProducerThrowsException() throws Exception {
        doThrow(mock(Exception.class)).when(this.menuProducer).readMenu(any());
        this.templateView.reloadMenu(null);
    }

    @Test
    public void testMenuItemClickedWithRoleAdminViewTypeMUI() throws Exception {
        final MenuItem menuItem = mock(MenuItem.class);
        doReturn(menuItem).when(this.menuActionEvent).getMenuItem();
        final Map<String, List<String>> params = new HashMap<>();
        params.put("path", Collections.singletonList("mock"));
        final Map<String, Configuration> configurationMap = new HashMap<>();
        final Configuration configuration = mock(Configuration.class);
        configurationMap.put("a", configuration);
        configurationMap.put("b", configuration);
        configurationMap.put("c", configuration);
        final UsersEntity user = mock(UsersEntity.class);
        doReturn(user).when(this.templateView).getUser();
        RolesEntity adminRole = Mockito.mock(RolesEntity.class);
        when(user.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(adminRole)));
        when(applicationPropertiesService.calculateAdminMode()).thenReturn(true);
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        ViewType mui = ViewType.MUI;
        Map<String, String> requestParameterMap = new HashMap<>();
        requestParameterMap.put("jsonFilterd", null);
        this.templateView.menuItemClicked(this.menuActionEvent);
    }

    @Test
    public void testMenuItemClickedWithRoleAdminViewTypeADMIN() throws Exception {
        final MenuItem menuItem = mock(MenuItem.class);
        doReturn(menuItem).when(this.menuActionEvent).getMenuItem();
        final Map<String, List<String>> params = new HashMap<>();
        params.put("path", Collections.singletonList("mock"));
        final Map<String, Configuration> configurationMap = new HashMap<>();
        final Configuration configuration = mock(Configuration.class);
        configurationMap.put("a", configuration);
        configurationMap.put("b", configuration);
        configurationMap.put("c", configuration);
        final UsersEntity user = mock(UsersEntity.class);
        doReturn(user).when(this.templateView).getUser();
        RolesEntity adminRole = Mockito.mock(RolesEntity.class);
        when(user.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(adminRole)));
        when(applicationPropertiesService.calculateAdminMode()).thenReturn(true);
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        ViewType mui = ViewType.ADMIN;
        Map<String, String> requestParameterMap = new HashMap<>();
        requestParameterMap.put("jsonFilterd", null);
        // when(templateView.getRequestParameterMap()).thenReturn(requestParameterMap);
        // when(templateView.getCurrentView()).thenReturn(newView);
        // when(newView.getActiveConfiguration(ConfigurationTypes.FILTER)).thenReturn(null);
        this.templateView.menuItemClicked(this.menuActionEvent);
    }

    @Test
    public void testMenuItemClickedWithRoleAdminViewTypeDBPROMO() throws Exception {
        final MenuItem menuItem = mock(MenuItem.class);
        doReturn(menuItem).when(this.menuActionEvent).getMenuItem();
        final Map<String, List<String>> params = new HashMap<>();
        params.put("path", Collections.singletonList("mock"));
        params.put("idMenu", Arrays.asList("1", "2", "3"));
        final Map<String, Configuration> configurationMap = new HashMap<>();
        final Configuration configuration = mock(Configuration.class);
        configurationMap.put("a", configuration);
        configurationMap.put("b", configuration);
        configurationMap.put("c", configuration);
        final UsersEntity user = mock(UsersEntity.class);
        doReturn(user).when(this.templateView).getUser();
        RolesEntity adminRole = Mockito.mock(RolesEntity.class);
        when(user.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(adminRole)));
        when(applicationPropertiesService.calculateAdminMode()).thenReturn(true);
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        ViewType mui = ViewType.DBPROMO;
        Map<String, String> requestParameterMap = new HashMap<>();
        requestParameterMap.put("jsonFilterd", null);
        MenuEntity menuEntity = Mockito.mock(MenuEntity.class);
        Set<ConfigurationEntity> configurationEntity = new HashSet<>();
        this.templateView.menuItemClicked(this.menuActionEvent);
    }

    @Test
    public void testMenuItemClickedUSERRole() throws Exception {
        final MenuItem menuItem = mock(MenuItem.class);
        doReturn(menuItem).when(this.menuActionEvent).getMenuItem();
        final UsersEntity user = mock(UsersEntity.class);
        doReturn(user).when(this.templateView).getUser();
        RolesEntity adminRole = Mockito.mock(RolesEntity.class);
        when(user.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(adminRole)));

        when(applicationPropertiesService.calculateAdminMode()).thenReturn(true);
        this.templateView.menuItemClicked(this.menuActionEvent);
    }

    @Test
    public void removeDimensionFilter() throws Exception {
        final UsersEntity user = mock(UsersEntity.class);
        doReturn(user).when(this.templateView).getUser();
        doReturn(this.paramsMap).when(this.templateView).getRequestParameterMap();
        doReturn("Promozione").when(this.paramsMap).get("dimension");
        doReturn(
                "{\"0\":{\"Attribute_desc\":\"Descrizione + Data\",\"Dimension_desc\":\"Promozione\",\"Attribute\":\"Descrizione + Data\",\"selectedValues\":[\"[Lun 04-11-19 -10gg] 30/40/50% + MAIALE + OLIO NOVELLO\"],\"MDXjsonSource\":\"nothing\",\"Attribute_code\":\"descrizione\",\"ServerName\":\"promo\",\"Dimension_code\":\"promozione\",\"Dimension\":\"Promozione\"}}")
                .when(user).getFilters();
        AbstractMuiView view = mock(AbstractMuiView.class);
        ConfigurationEntity configuration = mock(ConfigurationEntity.class);
        JsonNode node = JsonUtils.getMapper().createObjectNode();
        doNothing().when(this.templateView).updateCurrentFilters(anyString(), anyString());
        this.templateView.removeDimensionFilter();
    }

    @Test
    public void updateCurrentJsonFilterWithActionEvent() throws Exception {
        final UsersEntity user = mock(UsersEntity.class);
        doReturn(user).when(this.templateView).getUser();
        doReturn(this.paramsMap).when(this.templateView).getRequestParameterMap();
        doReturn("{\"1\":{\"Dimension\":\"Promozione\",\"Dimension_code\":\"promozione\","
                + "\"Dimension_desc\":\"Promozione\",\"MDXjsonSource\":\"nothing\","
                + "\"Attribute\":\"Canale\",\"Attribute_code\":\"canale\","
                + "\"Attribute_desc\":\"Canale\",\"ServerName\":\"esselunga_test\","
                + "\"selectedValues\":[\"Istituzionale\"]},\"4\":{\"Dimension\":\"Promozione\","
                + "\"Dimension_code\":\"promozione\",\"Dimension_desc\":\"Promozione\","
                + "\"MDXjsonSource\":\"nothing\",\"Attribute\":\"MUI_Semestre\","
                + "\"Attribute_code\":\"semestre\",\"Attribute_desc\":\"MUI_Semestre\","
                + "\"ServerName\":\"esselunga_test\",\"selectedValues\":[\"I Semestre\"]},"
                + "\"5\":{\"Dimension\":\"Promozione\",\"Dimension_code\":\"promozione\","
                + "\"Dimension_desc\":\"Promozione\",\"MDXjsonSource\":\"nothing\","
                + "\"Attribute\":\"Descrizione + Data\",\"Attribute_code\":\"descrizione\","
                + "\"Attribute_desc\":\"Descrizione + Data\",\"ServerName\":\"esselunga_test\","
                + "\"selectedValues\":[\"[Lun 08-04-19 -13gg] BUONA PASQUA + SENZA GLUTINE\"]},"
                + "\"10\":{\"Dimension\":\"MacroSpazio\",\"Dimension_code\":\"macrospazio\","
                + "\"Dimension_desc\":\"MacroSpazio\",\"MDXjsonSource\":\"nothing\","
                + "\"Attribute\":\"Descrizione\",\"Attribute_code\":\"descrizione\","
                + "\"Attribute_desc\":\"Descrizione\",\"ServerName\":\"esselunga_test\","
                + "\"selectedValues\":[\"Pigna Grande\",\"Pigna Media\",\"Pigna Rep Pro\"]}}").when(this.paramsMap)
                .get("jsonFilterd");
        doReturn("fixed").when(this.filterUtils).removeUnchangedFiltersFromStore(any(), any(), any());
        final MuiViewInterface i = mock(MuiViewInterface.class);
        // when(i.isMuiView()).thenReturn(false);
        doReturn(i).when(this.templateView).getCurrentView();
//		when(i.isPromoView()).thenReturn(true);
        ConfigurationEntity conf = mock(ConfigurationEntity.class);
//		when(i.getActiveConfiguration(any(ConfigurationTypes.class))).thenReturn(conf);
//		when(conf.getJson()).thenReturn("{\"promozione\":[\"anno\",\"canale\",\"tipo\",\"riferimento\",\"semestre\", \"descrizione\"]}");
        doReturn(
                "{\"0\":{\"Attribute_desc\":\"Descrizione + Data\",\"Dimension_desc\":\"Promozione\",\"Attribute\":\"Descrizione + Data\","
                        + "\"selectedValues\":[\"[Lun 04-11-19 -10gg] 30/40/50% + MAIALE + OLIO NOVELLO\"],\"MDXjsonSource\":\"nothing\","
                        + "\"Attribute_code\":\"descrizione\",\"ServerName\":\"promo\",\"Dimension_code\":\"promozione\",\"Dimension\":\"Promozione\"}}")
                .when(user).getFilters();
        doReturn("{\"5\":{\"Dimension\":\"Promozione\",\"Dimension_code\":\"promozione\","
                + "\"Dimension_desc\":\"Promozione\",\"MDXjsonSource\":\"nothing\","
                + "\"Attribute\":\"Descrizione + Data\",\"Attribute_code\":\"descrizione\","
                + "\"Attribute_desc\":\"Descrizione + Data\",\"ServerName\":\"esselunga_test\","
                + "\"selectedValues\":[\"[Gio 25-07-19 -14gg] SCONTO 40% + ESTATE IN TAVOLA + SENZA GLUTINE\"]}}")
                .when(user).getDbFilters();
        this.templateView.updateCurrentJsonFilter(this.actionEvent);
    }

    @Test
    public void updateCurrentJsonFilters() throws Exception {
        final UsersEntity user = mock(UsersEntity.class);
        doReturn(user).when(this.templateView).getUser();
        final String json = "filters";
        doNothing().when(this.filterSelected).parseJsonFilter(json);
        doReturn(null).when(this.muiService).persistUser(any());
        this.templateView.updateCurrentFilters("filters", null);
    }

    @Test(expected = Exception.class)
    public void updateCurrentJsonFiltersThrowsException() throws Exception {
        mock(UsersEntity.class);
        verify(this.templateView, times(1)).addGrowl(anyString(), anyString(), FacesMessage.SEVERITY_ERROR);
        this.templateView.updateCurrentFilters("filters", null);
    }

    @Test
    public void testToggleNonEmptyNotDefinedType() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "type");
        params.put("grid", "gridId");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        this.templateView.toggleNonEmpty();
    }

    @Test
    public void testToggleNonEmptyRows() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "rows");
        params.put("grid", "1");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        MuiViewInterface currentView = Mockito.mock(MuiViewInterface.class);
        when(templateView.getCurrentView()).thenReturn(currentView);
        Configuration config = Mockito.mock(Configuration.class);
        when(currentView.getConfiguration(anyString())).thenReturn(config);
        Query mdx = Mockito.mock(Query.class);
        when(config.getMdx()).thenReturn(mdx);
        MdxEntry rows = Mockito.mock(MdxEntry.class);
        when(mdx.getRows()).thenReturn(rows);
        when(currentView.canLoadData(anyString())).thenReturn(false);
        templateView.setCurrentView(currentView);
        doNothing().when(currentView).removeSpinner(anyString());
        this.templateView.toggleNonEmpty();
    }

    @Test
    public void testToggleNonEmptyColumns() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        MuiViewInterface currentView = Mockito.mock(MuiViewInterface.class);
        when(templateView.getCurrentView()).thenReturn(currentView);
        Configuration config = Mockito.mock(Configuration.class);
        when(currentView.getConfiguration(anyString())).thenReturn(config);
        Query mdx = Mockito.mock(Query.class);
        when(config.getMdx()).thenReturn(mdx);
        MdxEntry cols = Mockito.mock(MdxEntry.class);
        when(mdx.getCols()).thenReturn(cols);
        when(currentView.canLoadData(anyString())).thenReturn(false);
        templateView.setCurrentView(currentView);
        doNothing().when(currentView).removeSpinner(anyString());
        this.templateView.toggleNonEmpty();
    }

    @Test
    public void testPersistHiddenColumnsWithGridFound() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        params.put("cols", "[{ \"colId\" : \"slotId\", \"visible\" : \"false\" }]");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        String hiddenCols = "[{\"grid\":\"grid1\", \"colId\": [{\"name\":\"grm\",\"visible\":true }, {\"name\":\"subGrm\",\"visible\": false }]},"
                + "{\"grid\":\"1\", \"colId\": [{\"name\":\"grm\",\"visible\":true }, {\"name\":\"subGrm\",\"visible\": false }]}]";
        when(this.user.getHiddenCols()).thenReturn(hiddenCols);
        this.templateView.persistHiddenColumns();

    }

    @Test
    public void testPersistHiddenColumnsWIthGridNotFound() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        params.put("cols", "[{ \"colId\" : \"slotId\", \"visible\" : \"false\" }]");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        String hiddenCols = "[{\"grid\":\"2\", \"colId\": [{\"name\":\"grm\",\"visible\":true }, {\"name\":\"subGrm\",\"visible\": false }]}]";
        when(this.user.getHiddenCols()).thenReturn(hiddenCols);
        this.templateView.persistHiddenColumns();

    }

    @Test
    public void testAddErrorGrowl() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        params.put("cols", "[{ \"colId\" : \"slotId\", \"visible\" : \"false\" }]");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        MuiViewInterface currentView = Mockito.mock(MuiViewInterface.class);
        when(templateView.getCurrentView()).thenReturn(currentView);
        this.templateView.addErrorGrowl();
    }

    @Test
    public void testAddInfoGrowl() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        params.put("cols", "[{ \"colId\" : \"slotId\", \"visible\" : \"false\" }]");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        this.templateView.addInfoGrowl();
    }

    @Test
    public void testAddWarningGrowl() {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        params.put("cols", "[{ \"colId\" : \"slotId\", \"visible\" : \"false\" }]");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        this.templateView.addWarningGrowl();
    }

    @Test
    public void testKeepSessionAlive() {
        assertTrue(templateView.keepSessionAlive());
    }

    @Test
    public void testDownloadManual() {
        final ManualDto manual = mock(ManualDto.class);
        SlowRolesEntity e = mock(SlowRolesEntity.class);
        when(rolesService.findById(anyInt())).thenReturn(e);
        when(e.getHelpData()).thenReturn("This is a Foo manual".getBytes());
        when(manual.getFilename()).thenReturn("manual.pdf");
        assertNotNull(templateView.downloadManual(manual));
    }

    @Test
    public void testSimulateMenuClick() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        params.put("cols", "[{ \"colId\" : \"slotId\", \"visible\" : \"false\" }]");
        params.put("path", "/Admin/Gestione Utenti");
        params.put("menuId", "32");
        params.put("url", "admin/gestioneUtenti.xhtml");
        when(templateView.getRequestParameterMap()).thenReturn(params);
        MenuEntity menuEntity = Mockito.mock(MenuEntity.class);
        when(muiService.findMenu(anyInt())).thenReturn(menuEntity);
        when(menuEntity.getExternalLink()).thenReturn(false);
        templateView.simulateMenuClick();
    }

    @Test
    public void testSimulateMenuClickWithMenuItem() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("toggle", "checked");
        params.put("type", "columns");
        params.put("grid", "1");
        params.put("cols", "[{ \"colId\" : \"slotId\", \"visible\" : \"false\" }]");
        params.put("path", "/Admin/Gestione Utenti");
        params.put("menuId", "32");
        params.put("url", "admin/gestioneUtenti.xhtml");
        Map<String, List<String>> menuItemParams = new HashMap<>();
        List<String> pathsList = new ArrayList<>();
        pathsList.add("/Admin/Gestione Utenti");
        menuItemParams.put("path", pathsList);
        when(templateView.getRequestParameterMap()).thenReturn(params);
        MenuEntity menuEntity = Mockito.mock(MenuEntity.class);
        when(muiService.findMenu(anyInt())).thenReturn(menuEntity);
        when(menuEntity.getExternalLink()).thenReturn(false);
        DefaultMenuItem menuItem = Mockito.mock(DefaultMenuItem.class);
        when(menuProducer.createItem(any(MenuEntity.class), anyString())).thenReturn(menuItem);
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        when(viewFactory.getView(menuItem)).thenReturn(newView);
        when(menuItem.getParams()).thenReturn(menuItemParams);
        ViewType adminViewType = ViewType.ADMIN;
        when(newView.getViewType()).thenReturn(adminViewType);
        templateView.simulateMenuClick();
    }

    @Test
    public void testInexistentDownloadDocument() {
        UploadDocumentEntity document = Mockito.mock(UploadDocumentEntity.class);
        when(document.getName()).thenReturn("Downloaded Document");
        assertNull(templateView.downloadDocument(document));
    }

    @Test
    public void testIsConnectionAvailableADMIN() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        when(templateView.getCurrentView()).thenReturn(newView);
        ViewType mui = ViewType.ADMIN;
        when(newView.getViewType()).thenReturn(mui);
        assertTrue(templateView.isConnectionAvailable("test"));
    }

    @Test
    public void testIsConnectionAvailableWELCOME() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        when(templateView.getCurrentView()).thenReturn(newView);
        ViewType mui = ViewType.WELCOME;
        when(newView.getViewType()).thenReturn(mui);
        assertTrue(templateView.isConnectionAvailable("test"));
    }

    @Test
    public void testIsConnectionAvailableDBPROMO() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        when(templateView.getCurrentView()).thenReturn(newView);
        ViewType mui = ViewType.DBPROMO;
        when(newView.getViewType()).thenReturn(mui);
        assertTrue(templateView.isConnectionAvailable("DBPromozione"));
    }

    @Test
    public void testIsConnectionAvailableMUI() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        when(templateView.getCurrentView()).thenReturn(newView);
        ViewType mui = ViewType.MUI;
        when(newView.getViewType()).thenReturn(mui);
        assertFalse(templateView.isConnectionAvailable("MUI"));
    }

    @Test
    public void testIsConnectionAvailableWithConnectionsDBPromozione() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        templateView.usedConnections.add("1");
        templateView.usedConnections.add("2");
        templateView.usedConnections.add("3");
        when(templateView.getCurrentView()).thenReturn(newView);
        when(newView.isMuiView()).thenReturn(true);
        assertFalse(templateView.isConnectionAvailable("DBPromozione"));
    }

    @Test
    public void testIsConnectionAvailableMUIWithConnections() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        templateView.usedConnections.add("1");
        templateView.usedConnections.add("2");
        templateView.usedConnections.add("3");
        when(templateView.getCurrentView()).thenReturn(newView);
        assertFalse(templateView.isConnectionAvailable("MUI"));
    }

    @Test
    public void testIsConnectionAvailableDBPromozioneReturnsTRUEWhenViewIsNOtMUI() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        templateView.usedConnections.add("1");
        templateView.usedConnections.add("2");
        templateView.usedConnections.add("3");
        when(templateView.getCurrentView()).thenReturn(newView);
        when(newView.isMuiView()).thenReturn(false);
        assertTrue(templateView.isConnectionAvailable("DBPromozione"));
    }

    @Test
    public void testIsConnectionAvailableDBPromozioneReturnsFALSE() {
        MuiViewInterface newView = Mockito.mock(MuiViewInterface.class);
        templateView.usedConnections.add("1");
        templateView.usedConnections.add("2");
        templateView.usedConnections.add("3");
        when(templateView.getCurrentView()).thenReturn(newView);
        when(newView.isMuiView()).thenReturn(true);
        assertFalse(templateView.isConnectionAvailable("DBPromozione"));
    }

    @Test
    public void isComponentVisible_givenNullComponentId_shouldReturnFalse() {
        assertFalse(templateView.isComponentVisible(null));
    }

    @Test
    public void isComponentVisible_givenAdminUser_shouldReturnTrue() {
        when(user.isAdmin()).thenReturn(true);
        assertTrue(templateView.isComponentVisible("foo"));
    }

    @Test
    public void isComponentVisible_givenUserUserWithoutRoleForComponent_shouldReturnFalse() {
        when(user.isAdmin()).thenReturn(false);
        final AclEntity acl1 = mockAcl("foobar", null, null, true);
        final AclEntity acl2 = mockAcl("bar", null, null, false);
        final AclEntity acl3 = mockAcl("baz", null, null, false);
        final RolesEntity role1 = mockRole(acl1, acl2);
        final RolesEntity role2 = mockRole(acl3);
        when(user.getRoles()).thenReturn(new HashSet<>(Arrays.asList(role1, role2)));
        assertFalse(templateView.isComponentVisible("foo"));
    }

    @Test
    public void isComponentVisible_givenUserUserWithAtLeastOneRoleForComponentWithVisibleFlagTrue_shouldReturnTrue_otherwiseFalse() {
        when(user.isAdmin()).thenReturn(false);
        final AclEntity acl1 = mockAcl("foo", null, null, true);
        final AclEntity acl2 = mockAcl("bar", null, null, false);
        final AclEntity acl3 = mockAcl("foo", null, null, false);
        final AclEntity acl4 = mockAcl("bar", null, null, false);
        final RolesEntity role1 = mockRole(acl1, acl2);
        final RolesEntity role2 = mockRole(acl3, acl4);
        when(user.getRoles()).thenReturn(new HashSet<>(Arrays.asList(role1, role2)));
        assertTrue(templateView.isComponentVisible("foo"));
        assertFalse(templateView.isComponentVisible("bar"));
    }

    @Test
    public void isComponentDisabled_givenNullComponentId_shouldReturnTrue() {
        assertTrue(templateView.isComponentDisabled(null));
    }

    @Test
    public void isComponentDisabled_givenAdminUser_shouldReturnFalse() {
        when(user.isAdmin()).thenReturn(true);
        assertFalse(templateView.isComponentDisabled("foo"));
    }

    @Test
    public void isComponentDisabled_givenUserUserWithoutRoleForComponent_shouldReturnTrue() {
        when(user.isAdmin()).thenReturn(false);
        final AclEntity acl1 = mockAcl("foobar", null, false, null);
        final AclEntity acl2 = mockAcl("bar", null, true, null);
        final AclEntity acl3 = mockAcl("baz", null, true, null);
        final RolesEntity role1 = mockRole(acl1, acl2);
        final RolesEntity role2 = mockRole(acl3);
        when(user.getRoles()).thenReturn(new HashSet<>(Arrays.asList(role1, role2)));
        assertTrue(templateView.isComponentDisabled("foo"));
    }

    @Test
    public void isComponentDisabled_givenUserUserWithAtLeastOneRoleForComponentWithEnabledFlagTrue_shouldReturnFalse_otherwiseTrue() {
        when(user.isAdmin()).thenReturn(false);
        final AclEntity acl1 = mockAcl("foo", null, true, null);
        final AclEntity acl2 = mockAcl("bar", null, false, null);
        final AclEntity acl3 = mockAcl("foo", null, false, null);
        final AclEntity acl4 = mockAcl("bar", null, false, null);
        final RolesEntity role1 = mockRole(acl1, acl2);
        final RolesEntity role2 = mockRole(acl3, acl4);
        when(user.getRoles()).thenReturn(new HashSet<>(Arrays.asList(role1, role2)));
        assertFalse(templateView.isComponentDisabled("foo"));
        assertTrue(templateView.isComponentDisabled("bar"));
    }

    @Test
    public void isComponentEditable_givenNullComponentId_shouldReturnFalse() {
        assertFalse(templateView.isComponentEditable(null));
    }

    @Test
    public void isComponentEditable_givenAdminUser_shouldReturnTrue() {
        when(user.isAdmin()).thenReturn(true);
        assertTrue(templateView.isComponentEditable("foo"));
    }

    @Test
    public void isComponentEditable_givenUserUserWithoutRoleForComponent_shouldReturnFalse() {
        when(user.isAdmin()).thenReturn(false);
        final AclEntity acl1 = mockAcl("foobar", false, null, null);
        final AclEntity acl2 = mockAcl("bar", true, null, null);
        final AclEntity acl3 = mockAcl("baz", true, null, null);
        final RolesEntity role1 = mockRole(acl1, acl2);
        final RolesEntity role2 = mockRole(acl3);
        when(user.getRoles()).thenReturn(new HashSet<>(Arrays.asList(role1, role2)));
        assertFalse(templateView.isComponentEditable("foo"));
    }

    @Test
    public void isComponentEditable_givenUserUserWithAtLeastOneRoleForComponentWithditableFlagTrue_shouldReturnTrue_otherwiseFalse() {
        when(user.isAdmin()).thenReturn(false);
        final AclEntity acl1 = mockAcl("foo", true, null, null);
        final AclEntity acl2 = mockAcl("bar", false, null, null);
        final AclEntity acl3 = mockAcl("foo", false, null, null);
        final AclEntity acl4 = mockAcl("bar", false, null, null);
        final RolesEntity role1 = mockRole(acl1, acl2);
        final RolesEntity role2 = mockRole(acl3, acl4);
        when(user.getRoles()).thenReturn(new HashSet<>(Arrays.asList(role1, role2)));
        assertTrue(templateView.isComponentEditable("foo"));
        assertFalse(templateView.isComponentEditable("bar"));
    }

    private RolesEntity mockRole(AclEntity... acls) {
        final RolesEntity role = mock(RolesEntity.class);
        when(role.getAcls()).thenReturn(Arrays.asList(acls));
        return role;
    }

    private AclEntity mockAcl(String componentId, Boolean editable, Boolean enabled, Boolean visible) {
        final AclEntity acl = mock(AclEntity.class);
        when(acl.getComponent()).thenReturn(componentId);
        lenient().when(acl.getEditable()).thenReturn(editable);
        lenient().when(acl.getEnabled()).thenReturn(enabled);
        lenient().when(acl.getVisible()).thenReturn(visible);
        return acl;
    }

    private String fixFilterJson(final String storedFiltersJson, final String configFiltersJson,
            final String selectedFiltersJson) {
        final JSONObject fixedFiters = new JSONObject();
        final Map<String, String> configFiltersMap = new HashMap<>();
        final JSONObject configFilters = new JSONObject(configFiltersJson);
        final Iterator<String> itr = configFilters.keys();
        while (itr.hasNext()) {
            final String key = itr.next();
            configFiltersMap.put(key, key);
        }
        final JSONObject filters = new JSONObject(storedFiltersJson);
        for (final Iterator<String> iterator = filters.keys(); iterator.hasNext();) {
            final String key = iterator.next();
            final JSONObject filter = (JSONObject) filters.get(key);
            final String dimensionCode = filter.getString("Dimension_code");
            if (configFiltersMap.get(dimensionCode) != null) {
                iterator.remove();
            }
        }
        for (final Iterator<String> iterator = filters.keys(); iterator.hasNext();) {
            final String key = iterator.next();
            final JSONObject value = (JSONObject) filters.get(key);
            fixedFiters.put(String.valueOf(fixedFiters.length()), value);
        }
        final JSONObject selectedFilters = new JSONObject(selectedFiltersJson);
        for (final Iterator<String> iterator = selectedFilters.keys(); iterator.hasNext();) {
            final String key = iterator.next();
            final JSONObject value = (JSONObject) selectedFilters.get(key);
            fixedFiters.put(String.valueOf(fixedFiters.length()), value);
        }
        return fixedFiters.toString();
    }

}