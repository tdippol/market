package com.axiante.mui.backing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Instance;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.business.reader.ConfigurationReader;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.utility.configuration.Configuration;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationCatalogTest {
	@Mock
	Instance<ConfigurationReader> configurationReaderInstance;
	@Mock
	ConfigurationReader configurationReader;
	@Mock
	MuiService service;
	@Spy
	@InjectMocks
	ConfigurationCatalog configurationCatalog;

	@Rule
	public ExpectedException ex = ExpectedException.none();
	@Before
	public void setUp() throws Exception {
		final Map<String, Configuration> configurationMap = new HashMap<>();
		configurationMap.put("testConf", Configuration.builder().build());
		configurationMap.put("testConf2", Configuration.builder().build());

		when(configurationReaderInstance.get()).thenReturn(configurationReader);
		when(configurationReader.read(anyString())).thenReturn(configurationReader);
		when(configurationReader.getConfigurations()).thenReturn(configurationMap);
		final List<ConfigurationEntity> list = new ArrayList<>();
		ConfigurationEntity entity = createEntity(1, ConfigurationTypes.GRID);
		entity.setPath("/Admin/Gestione_Applicazione");
		entity.setJson("{\"connection\": \"promo\",\"configurations\":{\"name\": \"gridContainer\",\"logMemory\": true,\n"
				+ "\"logTime\": true,\"skip\": true}]}");
		list.add(entity);
		entity = createEntity(2, ConfigurationTypes.GRID);
		entity.setPath("/Admin/Gestione_Ruoli");
		entity.setJson("{\"connection\": \"promo\",\"configurations\": [{\"name\": \"gridContainer\",\"logMemory\": true,\n"
				+ "\"logTime\": true,\"skip\": true}]}");

		list.add(entity);
		when(service.readConfigurations(ConfigurationTypes.GRID)).thenReturn(list);
	}

	@Test
	public void init() {
		configurationCatalog.init();
		verify(configurationCatalog, times(1)).update();
	}

	@Test
	public void testUpdate() {
		assertTrue(configurationCatalog.update());
	}

	@Test
	public void testUpdateReturnsFalse() throws Exception {
		when(service.readConfigurations(ConfigurationTypes.GRID)).thenReturn(null);
		assertFalse(configurationCatalog.update());
	}

	@Test
	public void testReadConfigurationThrowsException() {
		ex.expect(NullPointerException.class);
		configurationCatalog.readConfiguration(null);
	}
	@Test public void testReadConfigurationReturnsData() {
		final Map<String, Configuration> test = configurationCatalog.readConfiguration("mocked json");
		assertNotNull(test);
		assertEquals(test, configurationReader.getConfigurations()); // test that it returns the exact map that we expect
	}
	@Test public void testReadConfigurationHandlesException() throws IOException {
		Mockito.doThrow(IOException.class).when(configurationReader).read(anyString());
		assertNull(configurationCatalog.readConfiguration("mocked string"));
	}
	private ConfigurationEntity createEntity(final Integer id, final ConfigurationTypes type) {
		final ConfigurationEntity e = new ConfigurationEntity();
		e.setIdConfiguration(id);
		e.setType(type);
		return e;
	}
}