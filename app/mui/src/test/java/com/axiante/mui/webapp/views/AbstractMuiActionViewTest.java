package com.axiante.mui.webapp.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.ConfigurationFilterCatalog;
import com.axiante.mui.filter.FilterAttribute;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.model.FilterProducer;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.template.TemplateView;
import com.axiante.mui.webapp.views.content.aggrid.GridView;
import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedAction;
import com.axiante.mui.webapp.views.content.aggrid.preselections.PreSelection;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.filter.Filter;
import com.axiante.tm1.mdx.objects.FilterImpl;
import com.axiante.utility.configuration.Configuration;
import com.axiante.utility.configuration.Configuration.ConfigurationBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletContext;
import org.apache.http.cookie.Cookie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;
import org.primefaces.json.JSONArray;

@RunWith(MockitoJUnitRunner.class)
public class AbstractMuiActionViewTest {
	
	public List<FilterAttribute> attributes = new ArrayList<>();
	public Map<String, String> params =new HashMap<>();
	public List<PreSelection> preselectionList = new ArrayList<PreSelection>();
	public HashMap<String, SelectedAction> availableActionsMap = new HashMap<>();
	
	@Mock
	private Configuration configuration ;
	
	@Spy
	TemplateView templateView;
	
    private final String columnDefs = "[" + "{" + "\"headerName\": \"F1\"," + "\"field\": \"F1\"," + "\"width\": 100,"
            + "\"hide\": true," + "\"rowGroup\": true," + "\"editable\": false," + "\"type\": [" + "\"TM1Element\""
            + "]" + "}," + "{" + "\"headerName\": \"F4\"," + "\"field\": \"F4\"," + "\"width\": 70," + "\"hide\": true,"
            + "\"rowGroup\": true," + "\"editable\": false," + "\"type\": [" + "\"TM1Element\"" + "]" + "}," + "{"
            + "\"headerName\": \"F3\"," + "\"field\": \"F3\"," + "\"width\": 70," + "\"hide\": true,"
            + "\"rowGroup\": true," + "\"editable\": false," + "\"type\": [" + "\"TM1Element\"" + "]" + "}," + "{"
            + "\"headerName\": \"F3\"," + "\"field\": \"F3\"," + "\"width\": 400," + "\"hide\": true,"
            + "\"rowGroup\": false," + "\"editable\": false," + "\"type\": [" + "\"TM1Element\"" + "]" + "}," + "{"
            + "\"headerName\": \"F3\"," + "\"field\": \"F3\"," + "\"width\": 400," + "\"hide\": true,"
            + "\"rowGroup\": false," + "\"editable\": false," + "\"type\": [" + "\"TM1Element\"" + "]" + "}" + "]";

    private static List<String> headerCols = new ArrayList<String>() {
        /**
         * 
         */
        private static final long serialVersionUID = -5681811244143288111L;

        {
            add("F1");
            add("F2");
            add("F4");
            add("F4");
            add("F1");
        }
    };

    @Mock
    public static AbstractMuiActionView abstractMuiActionView = Mockito.mock(AbstractMuiActionView.class);
    @Mock
    public static FilterProducer filterProducer = Mockito.mock(FilterProducer.class);
    @Mock
    public static FilterImpl filter = Mockito.mock(FilterImpl.class);
    @Spy
	public PrimeFaces primeFaces;
    @Spy
    private UsersEntity adminUser;
    @Mock
    MuiService muiService;
    @Mock
    HttpUtils httpUtils;
    
    @Spy
    @InjectMocks
    CookieRepository cookieRepository;
    
    private List<IngridFilter> currentIngridJsonFilter = new ArrayList<>();

    
    @Before
	public void setup() {
		
		attributes = addFilterAttributes(attributes, "name", "Name", "Entity Code");
		attributes = addFilterAttributes(attributes, "alias", "Alias", "Entity Descr.");
		attributes = addFilterAttributes(attributes, "country", "Country", "Country");
		attributes = addFilterAttributes(attributes, "location", "Location", "Location");
		attributes = addFilterAttributes(attributes, "currency", "Currency", "Valuta");
		attributes = addFilterAttributes(attributes, "name", "Name", "Account Code");
		attributes = addFilterAttributes(attributes, "alias", "Alias", "Account Descr.");
		
		
		params.put("toggle", "checked");
		params.put("type", "type");
		params.put("reload","true");
		params.put("temporaryFilters",  "[{ \"grid\" : \"gc_AssociazionePromo_Associazioni\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"gc_AssociazionePromo_SezioniTematiche\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"none\", \"visible\" : false, \"values\" : [\"2019\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Anno\" }]");
		
		
    	configuration.setAlertNoDataFound(true);
    	configuration.setAutoGroupColumnDef("{\"cellRendererParams\":{\"suppressCount\":true},\"field\":\"Products.Caption_Default\",\"headerName\":\"Products\",\"cellClass\":\"cellClass-left-text\",\"width\":300,\"pinned\":\"left\",\"type\":[\"TM1Element\"]}");
    	configuration.setColumnDefs("[{\"headerName\":\"Version\",\"field\":\"Budgetversion.Caption_Default\",\"width\":200,\"hide\":true,\"rowGroup\":true,\"editable\":false,\"pinned\":\"left\",\"type\":[\"TM1Element\"]},{\"headerName\":\"Products\",\"field\":\"Products.Caption_Default\",\"width\":200,\"hide\":true,\"rowGroup\":false,\"editable\":false,\"pinned\":\"left\",\"type\":[\"TM1Element\"]}]");
    	configuration.setPreSelections("col1 , col2 , col3");
    	
    	abstractMuiActionView.templateView = templateView;
    	abstractMuiActionView.cookieRepository=cookieRepository;
    	
    	PreSelection firstPreselection = Mockito.mock(PreSelection.class);
    	PreSelection secondPreselection = Mockito.mock(PreSelection.class);
    	preselectionList.add(firstPreselection);
    	preselectionList.add(secondPreselection);
	}

    
    
    @Test
    public void produceTemporaryFilters() {
        Mockito.when(abstractMuiActionView.getFilterProducer()).thenReturn(filterProducer);
        Mockito.when(abstractMuiActionView.produceTemporaryFilters(any(), any())).thenCallRealMethod();
        abstractMuiActionView.selectedTemporaryFilters = new HashMap<>();
        Mockito.when(filter.getAttribute()).thenReturn("Descrizione");
        Mockito.when(filterProducer.getFilters(any())).thenReturn(new ArrayList<>(Collections.singletonList(filter)));

        final String grid = "gc_AssociazionePromo_Associazioni";
        final String temporaryFiltersJSON = "[{ \"grid\" : \"gc_AssociazionePromo_Associazioni\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"gc_AssociazionePromo_SezioniTematiche\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"none\", \"visible\" : false, \"values\" : [\"2019\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Anno\" }]";
        final HashMap<String,Filter> temporaryFilters = abstractMuiActionView.produceTemporaryFilters(temporaryFiltersJSON,grid);
        assertNotNull(temporaryFilters);
    }

    @Test
    public void suppressEmptyCols() {
        final AbstractMuiActionView abstractMuiActionView = new GridView();
        final JSONArray colDefs = new JSONArray(columnDefs);
        for (int i = 0; i < colDefs.length(); i++) {
            i = abstractMuiActionView.removeFields(headerCols, colDefs, i, true);
        }
        assertEquals(colDefs.length(), 2);
    }


	@Test
	public void testUpdateViewWithGridAndReload() throws RuntimeException{
		final String grid = "gc_AssociazionePromo_Associazioni";
		doCallRealMethod().when(abstractMuiActionView).updateView(grid, true);
		final String temporaryFiltersJSON = "[{ \"grid\" : \"gc_AssociazionePromo_Associazioni\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"gc_AssociazionePromo_SezioniTematiche\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"none\", \"visible\" : false, \"values\" : [\"2019\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Anno\" }]";
		when(abstractMuiActionView.getRequestParameterMap()).thenReturn(params);
		when(abstractMuiActionView.getFilterProducer()).thenReturn(filterProducer);
		when(filterProducer.getFilters(any())).thenReturn(new ArrayList<>(Collections.singletonList(filter)));
		final HashMap<String,HashMap<String,Filter>> temporaryFilters = new HashMap<>();
		abstractMuiActionView.selectedTemporaryFilters = temporaryFilters;
		Filter filter = Mockito.mock(Filter.class);
		HashMap<String,Filter> gridHashmap = new HashMap<String, Filter>();
		gridHashmap.put(grid, filter);
		temporaryFilters.put(grid, gridHashmap);
		doCallRealMethod().when(abstractMuiActionView).getSelectedTemporaryFilters();
		when(abstractMuiActionView.getCurrentJsonFilter()).thenReturn("temporaryFiltersJSON");
		doCallRealMethod().when(abstractMuiActionView).produceTemporaryFilters(temporaryFiltersJSON, grid);
		Ajax ajax = Mockito.mock(Ajax.class);
		Mockito.doReturn(ajax).when(abstractMuiActionView).getAjax();
		doNothing().when(ajax).addCallbackParam(anyString(), anyString());
		abstractMuiActionView.updateView(grid, true);
	}
	
	@Test
	public void testUpdateViewWithGridAndNotReload() throws RuntimeException{
		final String grid = "gc_AssociazionePromo_Associazioni";
		doCallRealMethod().when(abstractMuiActionView).updateView(grid, false);
		final String temporaryFiltersJSON = "[{ \"grid\" : \"gc_AssociazionePromo_Associazioni\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"gc_AssociazionePromo_SezioniTematiche\", \"visible\" : true, \"values\" : [\"[2019_3] 30/40/50% +  IGP/DOP + VEGETARIANO\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Descrizione\" },{ \"grid\" : \"none\", \"visible\" : false, \"values\" : [\"2019\"], \"dimension\" : \"Promozione\", \"attribute\" : \"Anno\" }]";
		when(abstractMuiActionView.getRequestParameterMap()).thenReturn(params);
		when(abstractMuiActionView.getFilterProducer()).thenReturn(filterProducer);
		when(filterProducer.getFilters(any())).thenReturn(new ArrayList<>(Collections.singletonList(filter)));
		final HashMap<String,HashMap<String,Filter>> temporaryFilters = new HashMap<>();
		abstractMuiActionView.selectedTemporaryFilters = temporaryFilters;
		Filter filter = Mockito.mock(Filter.class);
		HashMap<String,Filter> gridHashmap = new HashMap<String, Filter>();
		gridHashmap.put(grid, filter);
		temporaryFilters.put(grid, gridHashmap);
		doCallRealMethod().when(abstractMuiActionView).getSelectedTemporaryFilters();
		when(abstractMuiActionView.getCurrentJsonFilter()).thenReturn("temporaryFiltersJSON");
		doCallRealMethod().when(abstractMuiActionView).produceTemporaryFilters(temporaryFiltersJSON, grid);
		Ajax ajax = Mockito.mock(Ajax.class);
		Mockito.doReturn(ajax).when(abstractMuiActionView).getAjax();
		doNothing().when(ajax).addCallbackParam(anyString(), anyString());
		abstractMuiActionView.updateView(grid, false);
	}
    
    @Test
    public void testUpdateViewWithGridRenderCumpolsuryFALSE() {
    	final String grid = "gc_AssociazionePromo_Associazioni";
    	doCallRealMethod().when(abstractMuiActionView).updateView(grid);
		when(abstractMuiActionView.getRequestParameterMap()).thenReturn(params);
		final HashMap<String,HashMap<String,Filter>> temporaryFilters = new HashMap<>();		
		Filter filter = Mockito.mock(Filter.class);
		HashMap<String,Filter> gridHashmap = new HashMap<String, Filter>();
		gridHashmap.put(grid, filter);
		temporaryFilters.put(grid, gridHashmap);
		abstractMuiActionView.selectedTemporaryFilters = temporaryFilters;
		when(abstractMuiActionView.getSelectedTemporaryFilters()).thenReturn(temporaryFilters);
		when(abstractMuiActionView.getConfiguration(grid)).thenReturn(configuration);
		ConfigurationBuilder configurationBuilder = Mockito.mock(ConfigurationBuilder.class);
		when(configuration.toBuilder()).thenReturn(configurationBuilder);
		when(configuration.getPreSelections()).thenReturn("col1 , col2 , col3");
		when(configurationBuilder.build()).thenReturn(configuration);
		when(abstractMuiActionView.getTemplateView()).thenReturn(templateView);
		when(templateView.getRenderCumpulsoryFilters()).thenReturn(false);
		when(templateView.getAvailablePreSelections()).thenReturn(preselectionList);
		when(abstractMuiActionView.getCurrentJsonFilter()).thenReturn("temporaryFiltersJSON");
		Ajax ajax = Mockito.mock(Ajax.class);
		Mockito.doReturn(ajax).when(abstractMuiActionView).getAjax();
		doCallRealMethod().when(abstractMuiActionView).updateView(grid,Boolean.parseBoolean(params.get("reload")));
    	abstractMuiActionView.updateView(grid);
    }
    
	@Test
    public void testUpdateViewWithGridRenderCumpolsuryTRUE() {
    	final String grid = "gc_AssociazionePromo_Associazioni";
    	doCallRealMethod().when(abstractMuiActionView).updateView(grid);
		when(abstractMuiActionView.getRequestParameterMap()).thenReturn(params);
		final HashMap<String,HashMap<String,Filter>> temporaryFilters = new HashMap<>();		
		Filter filter = Mockito.mock(Filter.class);
		HashMap<String,Filter> gridHashmap = new HashMap<String, Filter>();
		gridHashmap.put(grid, filter);
		temporaryFilters.put(grid, gridHashmap);
		abstractMuiActionView.selectedTemporaryFilters = temporaryFilters;
		when(abstractMuiActionView.getSelectedTemporaryFilters()).thenReturn(temporaryFilters);
		when(abstractMuiActionView.getConfiguration(grid)).thenReturn(configuration);
		ConfigurationBuilder configurationBuilder = Mockito.mock(ConfigurationBuilder.class);
		when(configuration.toBuilder()).thenReturn(configurationBuilder);
		when(configuration.getPreSelections()).thenReturn("col1 , col2 , col3");
		Map<String, Integer> pageLinks = new HashMap<String, Integer>();
		pageLinks.put("page1", 1);
		pageLinks.put("page2", 2);
		when(configuration.getPageLinks()).thenReturn(pageLinks);
		when(configurationBuilder.build()).thenReturn(configuration);
		when(abstractMuiActionView.getTemplateView()).thenReturn(templateView);
		ExternalContext externalContext = Mockito.mock(ExternalContext.class);
		when(abstractMuiActionView.getExternalContext()).thenReturn(externalContext);
		ServletContext contextObject = Mockito.mock(ServletContext.class);
		when(externalContext.getContext()).thenReturn(contextObject);
		when((contextObject).getContextPath()).thenReturn("context_path");
		when(templateView.getRenderCumpulsoryFilters()).thenReturn(true);
		when(templateView.getAvailablePreSelections()).thenReturn(preselectionList);
		when(abstractMuiActionView.getCurrentJsonFilter()).thenReturn("temporaryFiltersJSON");
		when(abstractMuiActionView.getHttpUtils()).thenReturn(httpUtils);
		TableProducer tableProducer = Mockito.mock(TableProducer.class);
		when(abstractMuiActionView.getUtils()).thenReturn(tableProducer);
		Cookie cookie = Mockito.mock(Cookie.class);
		when(cookieRepository.getCookie(any(ConnectionProfile.class))).thenReturn(cookie);
		MuiToken muiToken = Mockito.mock(MuiToken.class);
		when(tableProducer.generateCellset(configuration, null, cookie)).thenReturn(muiToken);
		ConnectionProfile profile = Mockito.mock(ConnectionProfile.class);
		when(configuration.getProfile()).thenReturn(profile);
		when(muiToken.getConfiguration()).thenReturn(configuration);
		when(muiToken.getCellsetId()).thenReturn("CellsSet1234");
		when(configuration.getMdx()).thenReturn(null);
		when(profile.getValidationHost()).thenReturn("Valid Host");		
		when(httpUtils.isHostReachable("Valid Host")).thenReturn(true);
		when(configuration.getActions()).thenReturn("[{\"componentId\":\"comp1\" , \"componentLabel\":\"Componente 1\" , \"process\":\"nullProcess\",\"suppress_dialog\":false, \"silent\":false , \"custom_message\":\"Custom Message\" , \"custom_message_title\":\"Custom Message Title\" , \"custom_message_level\":\"Custom Message Level\""
				+ ", \"params\":[{\"dimension\":\"1000\" , \"attribute\":\"fieldLength\" , \"paramName\":\"fieldLength\"}]}]");
		Ajax ajax = Mockito.mock(Ajax.class);
		Table table=Mockito.mock(Table.class);
		when(tableProducer.produceTableHeaders(muiToken, null, "CellsSet1234")).thenReturn(table);
		Row<Cell> header = new Row<Cell>();
		Cell cell1 = Mockito.mock(Cell.class);
		header.add(cell1);
		when(table.getHeaders()).thenReturn(header);
		Map<String, Row<Cell>> currentHeadeers = new HashMap<String, Row<Cell>>();
		currentHeadeers.put("header1", header);
		when(abstractMuiActionView.getGridCurrentHeaders()).thenReturn(currentHeadeers);
		when(cell1.getName()).thenReturn("cell1");
		String colDef="[{\"headerName\":\"Version\",\"field\":\"Budgetversion.Caption_Default\",\"width\":200,\"hide\":true,\"rowGroup\":true,\"editable\":false,\"pinned\":\"left\",\"type\":[\"TM1Element\"]}]";
		when(configuration.getColumnDefs()).thenReturn(colDef);
		when(abstractMuiActionView.getAvailableActionsMap()).thenReturn(availableActionsMap);
		Mockito.doReturn(ajax).when(abstractMuiActionView).getAjax();
		doCallRealMethod().when(abstractMuiActionView).updateView(grid,Boolean.parseBoolean(params.get("reload")));
    	abstractMuiActionView.updateView(grid);
    }
    
    @Test
    public void testUpdateView() {
    	doCallRealMethod().when(abstractMuiActionView).updateView();
		final HashMap<String,HashMap<String,Filter>> temporaryFilters = new HashMap<>();
		abstractMuiActionView.selectedTemporaryFilters = temporaryFilters;
    	abstractMuiActionView.updateView();
    }
    
    @Test
    public void testApplyIngridFiltersWithSuccess() {
    	
    	abstractMuiActionView.setCurrentIngridJsonFilter(currentIngridJsonFilter);
    	final String grid = "gc_AssociazionePromo_Associazioni";
    	when(abstractMuiActionView.getCurrentUser()).thenReturn(adminUser);
    	abstractMuiActionView.muiService=muiService;
    	doCallRealMethod().when(abstractMuiActionView).applyIngridFilters(grid);
    	abstractMuiActionView.applyIngridFilters(grid);
    }
    
    @Test
    public void testApplyIngridFiltersWithErrorMessage() {
    	
    	abstractMuiActionView.setCurrentIngridJsonFilter(currentIngridJsonFilter);
    	final String grid = "gc_AssociazionePromo_Associazioni";
    	when(abstractMuiActionView.getCurrentUser()).thenReturn(adminUser);
    	doCallRealMethod().when(abstractMuiActionView).applyIngridFilters(grid);
    	abstractMuiActionView.applyIngridFilters(grid);
    }
    
    @Test
    public void testGetIngridPicklistValues() {
    	Map<String, Configuration> configurationMap = new HashMap<String, Configuration>();
    	
    	Map<String,String> gridFilter=new HashMap<String, String>();
    	gridFilter.put("filter1", "filter1");
    	gridFilter.put("filter2", "filter2");
    	configurationMap.put("configuration", configuration);
    	final String grid = "gc_AssociazionePromo_Associazioni";
    	when(abstractMuiActionView.getConfiguration(anyString())).thenReturn(configuration);
    	when(configuration.copy()).thenReturn(configuration);
    	when(configuration.getGridFilters()).thenReturn(gridFilter);
    	doCallRealMethod().when(abstractMuiActionView).getIngridPicklistValues(grid);
    	CatalogReducer catalogReducer = Mockito.mock(CatalogReducer.class);
    	ConfigurationFilterCatalog catalog = Mockito.mock(ConfigurationFilterCatalog.class);
    	when(catalogReducer.getCatalog()).thenReturn(catalog);
    	ConfigurationElement configurationElement = Mockito.mock(ConfigurationElement.class);
    	when(catalog.findByCodeAndAttribute(anyString(), anyString())).thenReturn(configurationElement);
    	when(configurationElement.getAttributes()).thenReturn(attributes);
    	when(abstractMuiActionView.getCatalogReducer()).thenReturn(catalogReducer);
    	
    	abstractMuiActionView.getIngridPicklistValues(grid);
    }
    
    private List<FilterAttribute> addFilterAttributes(final List<FilterAttribute> attributes, final String code,
		final String columnName, final String desc) {
		final FilterAttribute filterAttribute = new FilterAttribute();
		filterAttribute.setCode(code);
		filterAttribute.setColumnName(columnName);
		filterAttribute.setDesc(desc);
		attributes.add(filterAttribute);
		return attributes;
	}
}