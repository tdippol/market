package com.axiante.mui.business.reader;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.ConfigurationFilterCatalog;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationReaderTest {

    @Mock
    ConnectionCatalog connectionCatalog;

    @Mock
    ApplicationConfiguration applicationConfiguration;

    @Mock
    ConfigurationFilterCatalog filterCatalog;

    @Spy
    @InjectMocks
    ConfigurationReader configurationReader;

    File file = null;

    @Before
    public void initTest() {
        final ConnectionProfile connectionProfile = mock(ConnectionProfile.class);
        when(this.connectionCatalog.getProfile(any())).thenReturn(connectionProfile);
    }

    @Test
    public void testUnitilizedReaderReturnsNullConfiguration() {
        assertNull(this.configurationReader.getConfiguration("something"));
    }

    @Test
    public void testUnitilizedReaderReturnsEmptyConfigurations() {
        assertTrue(configurationReader.getConfigurations().isEmpty());
    }

    @Test(expected = IOException.class)
    public void testReadConfigurationThrowsExceptionNull() throws IOException {
        final String config = "{\n" + "\"qualcosa\":\"qualcosa\",	\"connection\": \"pippo\",\n"
                + "	\"configurations\": [{\"testing something wrong\"}}";
        this.configurationReader.read(config);
    }

    @Test
    public void testReadConfigurationReturnsData() throws IOException {
        String json = this.readConfiguration("/mui_config/account_setup.json");
        this.configurationReader.read(json);
        json = this.readConfiguration("/mui_config/dinamic_config.json");
        this.configurationReader.read(json);
        assertFalse(this.configurationReader.getConfiguration("local_vm_dinamic").getForceSuppression());
        json = this.readConfiguration("/mui_config/validation_dashboard.json");
        this.configurationReader.read(json);
        json = this.readConfiguration("/mui_config/dinamic_config_forceSuppression.json");
        this.configurationReader.read(json);
        assertTrue(this.configurationReader.getConfiguration("forceSuppression").getForceSuppression());
    }

    @Test(expected = NullPointerException.class)
    public void readPageLinks_givenNullJsonParser_shouldThrowException() throws IOException {
        configurationReader.readPageLinks(null);
    }

    @Test
    public void readPageLinks_givenInvalidJson_shouldReturnNull() throws IOException {
        final JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.nextToken()).thenReturn(JsonToken.START_ARRAY);
        assertNull(configurationReader.readPageLinks(jsonParser));
    }

    private String readConfiguration(final String filename) throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream(filename);
        final StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @Test
    public void testReadEmptyReturnsEmpty() throws Exception {
        this.configurationReader.read("");
        assertThat(this.configurationReader.getConfigurations().size(), CoreMatchers.equalTo(0));
    }

    @Test(expected = NullPointerException.class)
    public void testReadNullThrowsNullpointerException() throws IOException {
        this.configurationReader.read(null);
    }

    @SuppressWarnings("unchecked")
	@Test
    public void testIncorrectConfigurationJsonCallsReadConfiguration() throws IOException {
        String string = "{'connection':'{}','configurations':{}}";
        string = string.replace("\'", "\"");
        this.configurationReader.read(string);
        verify(this.configurationReader, times(1))
                .readConfigurations(any(JsonParser.class), any(ConnectionProfile.class), any(Boolean.class), ArgumentMatchers.isNull());
        // feature #1357
        string = "{'connection':'{}','pageLinks': {},'configurations':{}}";
        string = string.replace("\'", "\"");
        this.configurationReader.read(string);
        verify(this.configurationReader, times(1))
                .readConfigurations(any(JsonParser.class), any(ConnectionProfile.class), any(Boolean.class), any(Map.class));

    }

    @Test(expected = NullPointerException.class)
    public void testGetConfigurationThrowsNullpointerException() {
        this.configurationReader.getConfiguration(null);
    }

    @Test(expected = NullPointerException.class)
    public void readConfigurations_givenNullJsonParser_shouldThrowException() throws IOException {
        final ConnectionProfile profile = mock(ConnectionProfile.class);
        final Map<String, Integer> pageLinks = new HashMap<>();
        configurationReader.readConfigurations(null, profile, true, pageLinks);
    }

    @Test
    public void readConfigurations_givenConfigurations_shouldValidateGridFilters() throws IOException {
        final ConfigurationElement cfgElement = mock(ConfigurationElement.class);
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'gridFilters': { 'foo': 'bar', 'hello': 'world' } } ]");
        when(filterCatalog.findByCodeAndAttribute("foo", "bar")).thenReturn(cfgElement);
        when(filterCatalog.findByCodeAndAttribute("hello", "world")).thenReturn(null);
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        verify(filterCatalog, times(2)).findByCodeAndAttribute(anyString(), anyString());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        final Map<String, String> gridFilters = cfg.getGridFilters();
        assertEquals(1, gridFilters.size());
        assertEquals("bar", gridFilters.get("foo"));
        assertNull(gridFilters.get("hello"));
    }

    @Test
    public void readConfiguration_givenCommandEntry_shouldDoNothing() throws IOException {
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'command': 'foo' } ]");
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        assertNull(cfg.getCommand());
    }

    @Test
    public void readConfiguration_givenHeight_shouldReadAsInteger() throws IOException {
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'height': 42 } ]");
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        assertEquals(42, (int) cfg.getHeight());
    }

    @Test
    public void readConfiguration_givenTitle_shouldReadAsString() throws IOException {
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'title': 'Foo Bar' } ]");
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        assertEquals("Foo Bar", cfg.getTitle());
    }

    @Test
    public void readConfiguration_givenCss_shouldReadAsString() throws IOException {
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'css': 'baz' } ]");
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        assertEquals("baz", cfg.getCss());
    }

    @Test
    public void readConfiguration_givenPreselections_shouldReadAsRawObject() throws IOException {
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'preSelections': 'foobar' } ]");
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        assertEquals("foobar", cfg.getPreSelections());
    }

    @Test
    public void readConfiguration_givenCompulsoryFiltersMessage_shouldReadAsRawObject() throws IOException {
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'compulsoryFiltersMessage': 'foobar' } ]");
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        assertEquals("foobar", cfg.getCompulsoryFiltersMessage());
    }

    @Test
    public void readConfiguration_givenCompulsoryFiltersMessage_shouldReadAsList() throws IOException {
        final JsonParser parser = createFakeParser("[ { 'name': 'fake', 'compulsoryFilters': [ 'foo', 'bar' ] } ]");
        configurationReader.readConfigurations(parser, mock(ConnectionProfile.class), true, new HashMap<>());
        final Configuration cfg = configurationReader.getConfiguration("fake");
        final List<String> compulsoryFilters = cfg.getCompulsoryFilters();
        assertEquals(2, compulsoryFilters.size());
        assertTrue(compulsoryFilters.contains("foo"));
        assertTrue(compulsoryFilters.contains("bar"));
    }

    private JsonParser createFakeParser(final String s) throws IOException {
        final String json = s.replace("\'", "\"");
        return JsonUtils.getFactory().createParser(json);
    }
}
