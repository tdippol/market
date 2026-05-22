package com.axiante.mui.model.impl;

import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mockserver.MockServerTest;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.DimensionWatcher;
import com.axiante.mui.filter.FilterAttribute;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.MdxEntry;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.http.cookie.Cookie;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@NotThreadSafe
public class ApplicationFilterCatalogProducerTest {

    Object semaphore = new Object();
    String json = null;

    @Mock
    CookieRepository cookieRepository;

    @Mock
    TableProducer mockedTableProducer;

    @Mock
    ApplicationConfiguration mockedApplicationProperties;

    @Mock
    ConnectionCatalog mockedCatalog;

    @Spy
    @InjectMocks
    ApplicationFilterCatalogProducerImpl mockedProducer;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock    ConfigurationElement mockedElement;

    @Mock
    DimensionWatcher dimensionWatcher;

    @Mock
    HttpUtils httpUtils;

    @Test
    public void testReadEmptyStringReturnsInstance() {
        final String test = "";
        assertNotNull(mockedProducer.read(test));
        verify(mockedProducer, times(1)).read(any(JsonParser.class));
    }

    @Test
    public void testReadNullStringThrowsException() {
        final String test = null;
        exception.expect(NullPointerException.class);
        mockedProducer.read(test);
        verify(mockedProducer, times(1)).read(any(JsonParser.class));
    }

    @Test
    public void testReadNotJsonStringReturnsInstance() {
        final String test = "not a json";
        assertNotNull(mockedProducer.read(test));
        verify(mockedProducer, times(1)).read(any(JsonParser.class));
    }

    @Test
    public void testReadEmptyJsonStringReturnsEmptyData() {
        final String test = "[]";
        assertNotNull(mockedProducer.read(test));
        verify(mockedProducer, times(1)).read(any(JsonParser.class));
        assertThat(mockedProducer.getConfigurations().size(), equalTo(0));
    }

    @Test
    public void testReadEmptyObjectJsonStringReturnsEmptyConfigurationElement() {
        final String test = "[{}]";
        assertNotNull(mockedProducer.read(test));
        verify(mockedProducer, times(1)).read(any(JsonParser.class));
        assertThat(mockedProducer.getConfigurations().size(), equalTo(1));
        final ConfigurationElement e = mockedProducer.getConfigurations().get(0);
        assertThat(e.getAttributes().size(), equalTo(0));
        assertNull(e.getCode());
        assertNull(e.getColumnName());
        assertNull(e.getDescription());
        assertNull(e.getMdx());
        assertNull(e.getServer());
    }

    @Test
    public void testReadDataReturnsConfigurations() throws IOException {
        final String test = readTestData();
        final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, true);
        doReturn(mockedProfile).when(mockedCatalog).getProfile(anyString());
        assertNotNull(mockedProducer.read(test));
        verify(mockedProducer, times(1)).read(any(JsonParser.class));
        assertThat(mockedProducer.getConfigurations().size() > 0, equalTo(true));
    }

    @Test
    public void testReadDataReturnsConfigurationsWhenServerIsNull() throws IOException {
        final String test = readTestData();
        doReturn(null).when(mockedCatalog).getProfile(anyString());
        assertNotNull(mockedProducer.read(test));
        verify(mockedProducer, times(1)).read(any(JsonParser.class));
        assertThat(mockedProducer.getConfigurations().size() > 0, equalTo(true));
    }

    @Test
    public void read_givenNullJsonParser_shouldThrowException() {
        exception.expect(NullPointerException.class);
        mockedProducer.read((JsonParser) null);
    }

    @Test
    public void testToJsonThrowsExceptionWhenElementListIsNull() {
        exception.expect(NullPointerException.class);
        mockedProducer.toJson(null);
    }

    @Test
    public void testToJsonWhenHostUnreachableReturnsEmptyData() {
        synchronized (semaphore) {
            if (MockServerTest.isRunning()) {
                MockServerTest.stopServer();
            }
            final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, true);
            when(mockedElement.getServer()).thenReturn(mockedProfile);

            final List<ConfigurationElement> list = new ArrayList<>();
            list.add(mockedElement);
            assertThat(mockedProducer.toJson(list), equalTo("[]"));
        }
    }

    @Test
    public void testToJsonWhenHostReachableAndNoAndNoTokenCookieReturnsData() {
        synchronized (semaphore) {
            final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, true);

            when(mockedElement.getCode()).thenReturn("mockedCode");
            when(mockedElement.getColumnName()).thenReturn("mockedName");
            when(mockedElement.getDescription()).thenReturn("mockedDescription");
            when(mockedElement.getServer()).thenReturn(mockedProfile);
            when(mockedElement.getMdx()).thenReturn("mockedMDX");
            when(httpUtils.isHostReachable(anyString())).thenReturn(true);

            final String expected = "[{'DIM_code':'mockedCode','DIM_columnName':'mockedName','DIM_description':'mockedDescription','ENDPOINT_serverName':'null','MDX_jsonSource':'mockedMDX','list_ATTR':[],'jsonData':[]}]"
                    .replace("\'", "\"");
            final List<ConfigurationElement> list = new ArrayList<>();
            list.add(mockedElement);
            final Table mockedTable = mock(Table.class);
            when(mockedTable.isError()).thenReturn(true);
            doReturn(mockedTable).when(mockedTableProducer).produceTable(any(Configuration.class),
                    any(Query.class), nullable(String.class),
                    ArgumentMatchers.anyBoolean(), nullable(Cookie.class), nullable(Double.class));

            if (!MockServerTest.isRunning()) {
                MockServerTest.startServer();
            }

            assertThat(mockedProducer.toJson(list), equalTo(expected));

            if (MockServerTest.isRunning()) {
                MockServerTest.stopServer();
            }
        }
    }

    @Test
    public void testToJsonWhenHostReachableAndCookieReturnsData() {
        synchronized (semaphore) {
            final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, true);

            when(mockedElement.getCode()).thenReturn("mockedCode");
            when(mockedElement.getColumnName()).thenReturn("mockedName");
            when(mockedElement.getDescription()).thenReturn("mockedDescription");
            when(mockedElement.getServer()).thenReturn(mockedProfile);
            when(mockedElement.getMdx()).thenReturn("mockedMDX");
            when(httpUtils.isHostReachable(anyString())).thenReturn(true);

            final String expected = "[{'DIM_code':'mockedCode','DIM_columnName':'mockedName','DIM_description':'mockedDescription','ENDPOINT_serverName':'null','MDX_jsonSource':'mockedMDX','list_ATTR':[],'jsonData':[]}]"
                    .replace("\'", "\"");
            final List<ConfigurationElement> list = new ArrayList<>();
            list.add(mockedElement);
            final Table mockedTable = mock(Table.class);
            when(mockedTable.isError()).thenReturn(true);
            doReturn(mockedTable).when(mockedTableProducer).produceTable(any(Configuration.class),
                    any(Query.class), nullable(String.class),
                    ArgumentMatchers.anyBoolean(), nullable(Cookie.class), nullable(Double.class));
            if (!MockServerTest.isRunning()) {
                MockServerTest.startServer();
            }

            assertThat(mockedProducer.toJson(list), equalTo(expected));

            if (MockServerTest.isRunning()) {
                MockServerTest.stopServer();
            }
        }
    }

    @Test
    public void testToJsonWhenHostReachableAndMuiTokenReturnsData() throws IllegalAccessException {
        synchronized (semaphore) {
            final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, true);

            when(mockedElement.getCode()).thenReturn("mockedCode");
            when(mockedElement.getColumnName()).thenReturn("mockedName");
            when(mockedElement.getDescription()).thenReturn("mockedDescription");
            when(mockedElement.getServer()).thenReturn(mockedProfile);
            when(mockedElement.getMdx()).thenReturn("mockedMDX");
            when(httpUtils.isHostReachable(anyString())).thenReturn(true);

            final List<ConfigurationElement> list = new ArrayList<>();
            list.add(mockedElement);
            final Table mockedTable = mock(Table.class);
            when(mockedTable.isError()).thenReturn(false);
            doReturn(mockedTable).when(mockedTableProducer).produceTable(any(Configuration.class),
                    any(Query.class), nullable(String.class),
                    ArgumentMatchers.anyBoolean(), nullable(Cookie.class), nullable(Double.class));
            if (!MockServerTest.isRunning()) {
                MockServerTest.startServer();
            }

            final Row<Cell> mockedHeaderRow = new Row<>();
            final Cell header = Cell.createHeader("mocked cell header");
            mockedHeaderRow.add(header);
            final Row<Cell> mockedRow = new Row<>();
            final Cell cell = mock(Cell.class);
            mockedRow.add(cell);

            final List<Row<Cell>> temp = new ArrayList<>();
            temp.add(mockedRow);

            when(mockedTable.getHeaders()).thenReturn(mockedHeaderRow);
            when(mockedTable.getData()).thenReturn(temp.stream());

            when(mockedTable.map(mockedHeaderRow)).thenReturn(Arrays.asList(new String[] { "mocked cell header" }));
            when(mockedTable.map(mockedRow)).thenReturn(Arrays.asList(new String[] { "mocked value" }));

            final String expected = "[{'DIM_code':'mockedCode','DIM_columnName':'mockedName','DIM_description':'mockedDescription','ENDPOINT_serverName':'null','MDX_jsonSource':'mockedMDX','list_ATTR':[],'jsonData':[{'mocked cell header':'mocked value'}]}]"
                    .replace("\'", "\"");

            assertThat(mockedProducer.toJson(list), equalTo(expected));

            if (MockServerTest.isRunning()) {
                MockServerTest.stopServer();
            }
        }
    }

    @Test
    public void testTJsonStringValuethrowsException() {
        exception.expect(NullPointerException.class);
        mockedProducer.toJsonStringValue(null, null);
    }

    @Test
    public void testTJsonStringValueReturnsNullStringWhenNull() {
        final ObjectMapper m = mock(ObjectMapper.class);
        assertThat(mockedProducer.toJsonStringValue(m, null), equalTo("null"));
    }

    @Test
    public void testTJsonStringValueReturnsNullWhenException() throws JsonProcessingException {
        final ObjectMapper m = mock(ObjectMapper.class);
        Mockito.doThrow(JsonProcessingException.class).when(m).writeValueAsString(nullable(String.class));
        assertThat(mockedProducer.toJsonStringValue(m, ""), equalTo(null));
        assertThat(mockedProducer.toJsonStringValue(m, "something"), equalTo(null));
    }

    @Test
    public void testTJsonStringValueReturnsDataWhenNotNull() {
        final ObjectMapper m = JsonUtils.getMapper();
        final String test = "'some string'";
        assertThat(mockedProducer.toJsonStringValue(m, test.replace("\'", "")), equalTo(test.replace("\'", "\"")));
    }

    @Test
    public void testToJsonWithoutConfigReturnsEmptyArray() {
        final List<ConfigurationElement> mockedList = new ArrayList<>();
        mockedList.add(null);
        final String expect = "[]";
        assertThat(mockedProducer.toJson(mockedList), equalTo(expect));
    }

    @Test
    public void testToJsonWhenHostNotReachableReturnsEmptyArray() {
        final List<ConfigurationElement> mockedList = new ArrayList<>();
        //		ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE,"192.0.2.0", true);
        //		// according to RFC3330 this address should not be routed to anything
        //		
        //		when(mockedElement.getServer()).thenReturn(mockedProfile);

        final String expect = "[]";
        assertThat(mockedProducer.toJson(mockedList), equalTo(expect));
    }

    @Test
    public void testFilterAttributeToJsonThrowsNPE() {
        exception.expect(NullPointerException.class);
        mockedProducer.filterAttributeToJson.apply(null);
    }

    @Test
    public void testFilterAttributeToJsonReturnsData() {
        final FilterAttribute test = mock (FilterAttribute.class);
        when(test.getCode()).thenReturn("mockedCode");
        when(test.getColumnName()).thenReturn("mockedColumnName");
        when(test.getDesc()).thenReturn("mockedDesc");
        when(test.getType()).thenReturn("dropdown");
        //		when(test.getValues()).thenReturn(Arrays.asList(new String[] {"mockedValue1","mockedValue2"}));
        final String expect = "{'ATTR_code':'mockedCode','ATTR_columnName':'mockedColumnName','ATTR_desc':'mockedDesc','ATTR_type':'dropdown'}".replace("\'", "\"");
        assertThat(mockedProducer.filterAttributeToJson.apply(test),equalTo(expect));
    }

    @Test
    public void inGridPicklistValues_givenDimension_shouldReturnFilters() throws IOException {
        // Arrange
        final Configuration configuration = mock(Configuration.class);
        final Cookie cookie = mock(Cookie.class);
        final Query mdx = mock(Query.class);
        final Query mdxCopied = mock(Query.class);
        final MdxEntry rows = mock(MdxEntry.class);
        final Row<Cell> row = createRow();
        final Table table = spy(Table.class);
        table.addRow(row);
        List<Dimension> dimensions = createDimensions();
        when(httpUtils.isHostReachable(anyString())).thenReturn(true);
        when(cookieRepository.getCookie(any(ConnectionProfile.class))).thenReturn(cookie);
        when(configuration.getMdx()).thenReturn(mdx);
        when(mdx.copy()).thenReturn(mdxCopied);
        when(mdxCopied.getRows()).thenReturn(rows);
        when(rows.getDimensions()).thenReturn(dimensions);
        when(mockedTableProducer.produceTable(any(Configuration.class), any(Query.class), eq(null),
                eq(true), any(Cookie.class), any(Double.class)))
                .thenReturn(table);
        // Act
        final List<IngridFilter> filters = mockedProducer.inGridPicklistValues(configuration, createConfigurationElements());
        // Assert
        assertNotNull(filters);
        assertEquals(1, filters.size());
    }

    @Test
    public void inGridPicklistValues_givenNoDimension_shouldReturnFilters() throws IOException {
        // Arrange
        final Configuration configuration = mock(Configuration.class);
        final Cookie cookie = mock(Cookie.class);
        final Query mdx = mock(Query.class);
        final Query mdxCopied = mock(Query.class);
        final MdxEntry rows = mock(MdxEntry.class);
        final MdxEntry cols = mock(MdxEntry.class);
        final Row<Cell> row = createRow();
        final Table table = spy(Table.class);
        table.addRow(row);
        List<Dimension> dimensions = createDimensions();
        when(httpUtils.isHostReachable(anyString())).thenReturn(true);
        when(cookieRepository.getCookie(any(ConnectionProfile.class))).thenReturn(cookie);
        when(configuration.getMdx()).thenReturn(mdx);
        when(mdx.copy()).thenReturn(mdxCopied);
        when(mdxCopied.getRows()).thenReturn(rows);
        when(rows.getDimensions()).thenReturn(Collections.emptyList());
        when(mdxCopied.getCols()).thenReturn(cols);
        when(cols.getDimensions()).thenReturn(dimensions);
        when(mockedTableProducer.produceTable(any(Configuration.class), any(Query.class), eq(null),
                eq(true), any(Cookie.class), any(Double.class)))
                .thenReturn(table);
        // Act
        final List<IngridFilter> filters = mockedProducer.inGridPicklistValues(configuration, createConfigurationElements());
        // Assert
        assertNotNull(filters);
        assertEquals(1, filters.size());
    }

    @Test
    public void toDBPromoJson_givenValidConfiguration_shoulReturnJsonString() {
        // Arrange
        final List<ConfigurationElement> configurations = createConfigurationElements();
        configurations.add(ConfigurationElement.builder().code("promozioneDBPromo")
                .columnName("col").description("desc").mdx("mdx").build());
        // Act
        final String dbPromoJson = mockedProducer.toDBPromoJson(configurations, "[]");
        // Assert
        assertNotNull(dbPromoJson);
        final DocumentContext cxt = JsonPath.parse(dbPromoJson);
        assertEquals("promozioneDBPromo", cxt.read("$[0].DIM_code", String.class));
        assertEquals("col", cxt.read("$[0].DIM_columnName", String.class));
        assertEquals("desc", cxt.read("$[0].DIM_description", String.class));
        assertEquals("null", cxt.read("$[0].ENDPOINT_serverName", String.class));
        assertEquals("mdx", cxt.read("$[0].MDX_jsonSource", String.class));
    }

    @Test
    public void toDBPromoJson_givenValidConfiguration_shoulReturnEmptyJson() {
        // Arrange
        final List<ConfigurationElement> configurations = createConfigurationElements();
        // Act
        final String dbPromoJson = mockedProducer.toDBPromoJson(configurations, "[]");
        // Assert
        assertNotNull(dbPromoJson);
        assertEquals("[]", dbPromoJson);
    }

    @Test
    public void toDBPromoJson_givenNullPicklist_shoulReturnJsonStringWithNullJsonData() {
        // Arrange
        final List<ConfigurationElement> configurations = createConfigurationElements();
        configurations.add(ConfigurationElement.builder().code("promozioneDBPromo")
                .columnName("col").description("desc").mdx("mdx").build());
        // Act
        final String dbPromoJson = mockedProducer.toDBPromoJson(configurations, null);
        // Assert
        assertNotNull(dbPromoJson);
        assertNull(JsonPath.read(dbPromoJson, "$[0].jsonData"));
    }

    @Test
    public void toDBPromoJson_givenEmptyPicklist_shoulReturnJsonStringWithEmptyJsonData() {
        // Arrange
        final List<ConfigurationElement> configurations = createConfigurationElements();
        configurations.add(ConfigurationElement.builder().code("promozioneDBPromo")
                .columnName("col").description("desc").mdx("mdx").build());
        // Act
        final String dbPromoJson = mockedProducer.toDBPromoJson(configurations, "");
        // Assert
        assertNotNull(dbPromoJson);
        assertTrue(((JSONArray) JsonPath.read(dbPromoJson, "$[0].jsonData")).isEmpty());
    }

    private Row<Cell> createRow() {
        final Row<Cell> row = new Row<>();
        final Cell cell = new Cell();
        cell.setValue("cell-value");
        cell.setPickList(Arrays.asList("foo", "bar"));
        cell.setName("cell1");
        cell.setHasPickList(true);
        row.add(cell);
        final Cell cell2 = new Cell();
        cell2.setValue("filter-col");
        cell2.setName("cell2");
        row.add(cell2);
        return row;
    }

    private List<Dimension> createDimensions() {
        final List<Dimension> list = new ArrayList<>();
        list.add(new Dimension("col", "value", Dimension.Type.ROWS));
        return list;
    }

    private List<ConfigurationElement> createConfigurationElements() {
        final ConnectionProfile profile = new ConnectionProfile();
        profile.setName("profile");
        profile.setHost("127.0.0.1");
        profile.setPort("8080");
        final FilterAttribute filterAttribute = new FilterAttribute();
        filterAttribute.setCode("filter-code");
        filterAttribute.setColumnName("filter-col");
        filterAttribute.setDesc("filter-desc");
        filterAttribute.setValues(Arrays.asList("foo", "bar"));
        final List<ConfigurationElement> list = new ArrayList<>();
        final ConfigurationElement element = ConfigurationElement.builder().code("code")
                .columnName("col").description("desc").server(profile).mdx("mdx")
                .selectedAttribute(filterAttribute)
                .build();
        list.add(element);
        return list;
    }

    private String readTestData() throws IOException {
        final String file = "/config_filter.json";
        final String ret = IOUtils.toString(this.getClass().getResourceAsStream(file), Charset.forName("UTF-8"));
        return ret;
    }
}
