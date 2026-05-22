package com.axiante.mui.filter.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.tm1.mdx.objects.Dimension;
import com.fasterxml.jackson.core.JsonParseException;

@RunWith(MockitoJUnitRunner.class)
public class FilterUtilsTest {
	@Spy
	FilterUtilsImpl filterUtils;

	final String json = "{\n" +
			"    '0': {\n" +
			"        'Attribute_desc': 'Anno'," +
			"        'Dimension_desc': 'Promozione'," +
			"        'Attribute': 'Anno'," +
			"        'selectedValues': ['2019']," +
			"        'MDXjsonSource': 'nothing'," +
			"        'Attribute_code': 'anno'," +
			"        'ServerName': 'promo'," +
			"        'Dimension_code': 'promozione'," +
			"        'Dimension': 'Promozione'\n" +
			"    }," +
			"    '1': {\n" +
			"        'Attribute_desc': 'Canale'," +
			"        'Dimension_desc': 'Promozione'," +
			"        'Attribute': 'Canale'," +
			"        'selectedValues': ['Istituzionale']," +
			"        'MDXjsonSource': 'nothing'," +
			"        'Attribute_code': 'canale'," +
			"        'ServerName': 'promo'," +
			"        'Dimension_code': 'promozione'," +
			"        'Dimension': 'Promozione'\n" +
			"    }," +
			"    '2': {\n" +
			"        'Attribute_desc': 'Descrizione + Data'," +
			"        'Dimension_desc': 'Promozione'," +
			"        'Attribute': 'Descrizione + Data'," +
			"        'selectedValues': ['[Gio 14-11-19 -10gg] ALTRE OFFERTE + SOTTOCOSTO']," +
			"        'MDXjsonSource': 'nothing'," +
			"        'Attribute_code': 'descrizione'," +
			"        'ServerName': 'promo'," +
			"        'Dimension_code': 'promozione'," +
			"        'Dimension': 'Promozione'\n" +
			"    }\n" +
			"}";
	final String selectedJson = "{\n" +
			"    '0': {\n" +
			"        'Attribute_desc': 'Anno'," +
			"        'Dimension_desc': 'Promozione'," +
			"        'Attribute': 'Anno'," +
			"        'selectedValues': ['2019']," +
			"        'MDXjsonSource': 'nothing'," +
			"        'Attribute_code': 'anno'," +
			"        'ServerName': 'promo'," +
			"        'Dimension_code': 'promozione'," +
			"        'Dimension': 'Promozione'\n" +
			"    }," +
			"    '1': {\n" +
			"        'Attribute_desc': 'Canale'," +
			"        'Dimension_desc': 'Promozione'," +
			"        'Attribute': 'Canale'," +
			"        'selectedValues': ['Istituzionale']," +
			"        'MDXjsonSource': 'nothing'," +
			"        'Attribute_code': 'canale'," +
			"        'ServerName': 'promo'," +
			"        'Dimension_code': 'promozione'," +
			"        'Dimension': 'Promozione'\n" +
			"    }," +
			"    '2': {\n" +
			"        'Attribute_desc': 'Descrizione + Data'," +
			"        'Dimension_desc': 'Promozione'," +
			"        'Attribute': 'Descrizione + Data'," +
			"        'selectedValues': ['[Gio 14-11-19 -10gg] ALTRE OFFERTE + SOTTOCOSTO']," +
			"        'MDXjsonSource': 'nothing'," +
			"        'Attribute_code': 'descrizione'," +
			"        'ServerName': 'promo'," +
			"        'Dimension_code': 'promozione'," +
			"        'Dimension': 'Promozione'\n" +
			"    }," +
			"    '3': {\n" +
			"        'Attribute_desc': 'Descrizione'," +
			"        'Dimension_desc': 'Compratore'," +
			"        'Attribute': 'Descrizione'," +
			"        'selectedValues': ['Compratore 1']," +
			"        'MDXjsonSource': 'nothing'," +
			"        'Attribute_code': 'descrizione'," +
			"        'ServerName': 'promo'," +
			"        'Dimension_code': 'compratore'," +
			"        'Dimension': 'Compratore'\n" +
			"    }\n" +
			"}";
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testRemoveUnchangedFiltersFromStore() {
		final List<ConfigurationElement> config = new ArrayList<>();
		final ConfigurationElement c1 = mock(ConfigurationElement.class);
		final ConfigurationElement c2 = mock(ConfigurationElement.class);
		final ConfigurationElement c3 = mock(ConfigurationElement.class);
		final ConfigurationElement c4 = mock(ConfigurationElement.class);
		when(c1.getCode()).thenReturn("promozione");
		when(c2.getCode()).thenReturn("promozione");
		when(c3.getCode()).thenReturn("promozione");
		when(c4.getCode()).thenReturn("compratore");
		config.add(c1);
		config.add(c2);
		config.add(c3);
		config.add(c4);

		when(this.filterUtils.removeUnchangedFiltersFromStore(this.json.replace("\'", "\""),
				config, this.selectedJson.replace("\'", "\""))).thenCallRealMethod();
		final String test = this.filterUtils.removeUnchangedFiltersFromStore(
				this.json.replace("\'", "\""),
				config,
				this.selectedJson.replace("\'", "\""));
		final String[] result = { "promozione", "promozione", "promozione", "compratore" };
		assertNotNull(test);
		for ( final String check: result) {
			assertThat(test, CoreMatchers.containsString(check));
		}
	}

	@Test
	public void testUnpackRowsToDimensionHandlesNullParameter() throws IOException {
		assertNull(this.filterUtils.unpackRowsToDimension(null));
	}

	@Test
	public void testUnpackRowsToDimensionThrowsJsonParseException() throws IOException {
		this.expectedEx.expect(JsonParseException.class);
		when(this.filterUtils.unpackRowsToDimension(anyString())).thenCallRealMethod();
		assertNull(this.filterUtils.unpackRowsToDimension("test"));
	}

	@Test
	public void testUnpackRowsToDimensionThrowsIoExceptionException() throws IOException {
		this.expectedEx.expect(IOException.class);
		when(this.filterUtils.unpackRowsToDimension(anyString())).thenCallRealMethod();
		assertNull(this.filterUtils.unpackRowsToDimension(this.json));
	}

	@Test
	public void testUnpackRowsToQueuedDimensionHandlesNullParameter() throws IOException {
		when(this.filterUtils.unpackRowsToQueuedDimension(null)).thenCallRealMethod();
		this.filterUtils.unpackRowsToQueuedDimension(null);
	}

	@Test
	public void testUnpackHandlesNullValues() {
		when(this.filterUtils.unpack(null, null)).thenCallRealMethod();
		assertNull(this.filterUtils.unpack(null, null));
	}

	@Test
	public void testUnpackReturnsDataWhenColsNull() throws IOException {
		when(this.filterUtils.unpack(notNull(), any())).thenCallRealMethod();
		when(this.filterUtils.unpackRows(notNull())).thenReturn(Collections.singletonList("test"));
		assertThat(this.filterUtils.unpack("something", null), CoreMatchers.hasItem("test"));
	}

	@Test
	public void testUnpackReturnsDataWhenColsError() throws IOException {
		when(this.filterUtils.unpack(notNull(), any())).thenCallRealMethod();
		when(this.filterUtils.unpackRows(notNull())).thenReturn(Collections.singletonList("test"));
		when(this.filterUtils.unpackColumns("test")).thenThrow(RuntimeException.class);
		assertThat(this.filterUtils.unpack("something", "test"), CoreMatchers.hasItem("test"));
	}

	@Test
	public void unpackRowsToQueuedDimension_withNotNullRows() throws IOException {
		String rows = "{\"Reparto\":\"REP_01\",\"Promozione\":\"2019_405\",\"Versione\":\"UFF\"}";
		final List<Dimension> dimensions = filterUtils.unpackRowsToQueuedDimension(rows);
		assertEquals(1, dimensions.size());
		assertEquals(Dimension.Type.ROWS, dimensions.get(0).getType());
		assertTrue(dimensions.get(0).getValue().contains("[Reparto].[REP_01]"));
		assertTrue(dimensions.get(0).getValue().contains("[Promozione].[2019_405]"));
		assertTrue(dimensions.get(0).getValue().contains("[Versione].[UFF]"));
	}

	@Test
	public void removeUnchangedDBPromoFiltersFromStore() {
		final String storedFiltersJson = json.replace("\'", "\"");
		final String selectedFiltersJson = selectedJson
				.replace("'Dimension_code': 'promozione'", "'Dimension_code': 'promozioneDBPromo'")
				.replace("\'", "\"");
		final String result = filterUtils.removeUnchangedDBPromoFiltersFromStore(storedFiltersJson,
				Collections.singletonList(mock(ConfigurationElement.class)), selectedFiltersJson);
		assertNotNull(result);
	}

	@Test
	public void removeUnchangedDBPromoFiltersFromStore_givenInvalidJsonFilters_shouldReturnNull() {
		assertNull(filterUtils.removeUnchangedDBPromoFiltersFromStore("foo",
				Collections.singletonList(mock(ConfigurationElement.class)), "bar"));
	}

	@Test
	public void removeUnchangedFiltersFromStore_givenInvalidJsonFilters_shouldReturnNull() {
		assertNull(filterUtils.removeUnchangedFiltersFromStore("foo",
				Collections.singletonList(mock(ConfigurationElement.class)), "bar"));
	}
}