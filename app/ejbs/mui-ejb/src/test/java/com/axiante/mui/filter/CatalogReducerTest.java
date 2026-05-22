package com.axiante.mui.filter;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.connection.ConnectionProfile;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CatalogReducerTest {
	@Mock
	ConfigurationFilterCatalog catalog;

	@Getter
	@InjectMocks
	CatalogReducer reducer = new CatalogReducerImpl();

	private List<FilterAttribute> addFilterAttributes(final List<FilterAttribute> attributes, final String code,
			final String columnName, final String desc) {
		final FilterAttribute filterAttribute = new FilterAttribute();
		filterAttribute.setCode(code);
		filterAttribute.setColumnName(columnName);
		filterAttribute.setDesc(desc);
		attributes.add(filterAttribute);
		return attributes;
	}

	private ConfigurationElement createConfigurationElement(final String code, final String columnName,
			final String description, final List<FilterAttribute> attributes) {
		return new ConfigurationElement(code, columnName, description, new ConnectionProfile(), "", true, attributes,
				null);
	}

	@Before
	public void setup() {
		final List<ConfigurationElement> configurationElements = new ArrayList<>();
		List<FilterAttribute> attributes = new ArrayList<>();
		attributes = addFilterAttributes(attributes, "name", "Name", "Entity Code");
		attributes = addFilterAttributes(attributes, "alias", "Alias", "Entity Descr.");
		attributes = addFilterAttributes(attributes, "country", "Country", "Country");
		attributes = addFilterAttributes(attributes, "location", "Location", "Location");
		attributes = addFilterAttributes(attributes, "currency", "Currency", "Valuta");
		configurationElements
				.add(createConfigurationElement("legalentity", "Legal Entity", "Legal Entity", attributes));
		attributes = new ArrayList<>();
		attributes = addFilterAttributes(attributes, "name", "Name", "Account Code");
		attributes = addFilterAttributes(attributes, "alias", "Alias", "Account Descr.");
		configurationElements.add(createConfigurationElement("Account", "Account", "Account", attributes));

		when(catalog.getCatalog()).thenReturn(configurationElements);
	}

	@Test
	public void testCatalogFilterRetursData() {
		assertNotNull(getReducer().filterCatalog(null));
	}

	@Test
	public void testCatalogFilterReturnsCorrectNumberOfElements() {
		final CatalogFilter mock = mock(CatalogFilter.class);
		final List<String> test = Arrays.asList(new String[] { "alias", "currency", "country", "location" });
		when(mock.getName()).thenReturn("legalentity");
		when(mock.getAttributes()).thenReturn(test);
		final List<CatalogFilter> mockedList = new ArrayList<>();
		mockedList.add(mock);
		when(reducer.filterCatalog(mockedList)).thenCallRealMethod();
		final List<ConfigurationElement> newCatalog = getReducer().filterCatalog(mockedList);
		assertNotNull(newCatalog);
		assertThat(newCatalog.size(), CoreMatchers.equalTo(mockedList.size()));
	}

	@Test
	public void testCatalogFilterReturnsCorrectDataAndAttributes() {
		final CatalogFilter mock = mock(CatalogFilter.class);
		final List<String> mockArray = Arrays.asList(new String[] { "alias", "currency", "country", "location" });
		when(mock.getName()).thenReturn("legalentity");
		when(mock.getAttributes()).thenReturn(mockArray);
		final List<CatalogFilter> mockedList = new ArrayList<>();
		mockedList.add(mock);
		assertThat(mockedList.size(), greaterThan(0));
		when(reducer.filterCatalog(mockedList)).thenCallRealMethod();
		final List<ConfigurationElement> filteredList = getReducer().filterCatalog(mockedList);
		assertNotNull(filteredList);
		assertThat(filteredList.size(), greaterThan(0));

		log.info("catalog size : " + filteredList.size());
		final ConfigurationElement test = filteredList.get(0);
		// should have only one element
		assertThat(filteredList.size(), CoreMatchers.equalTo(1));
		assertThat(test.getCode(), CoreMatchers.equalTo(mock.getName()));
		assertThat(test.getAttributes().size(), CoreMatchers.equalTo(mockArray.size()));
		final List<String> testList = test.getAttributes().stream().map(FilterAttribute::getCode)
				.collect(Collectors.toList());
		Collections.sort(testList);
		Collections.sort(mockArray);
		assertEquals(testList, mockArray);
	}

	@Test
	public void testCatalogFilterWorksWithMoreThanOneFilter() {
		final CatalogFilter mock = mock(CatalogFilter.class);
		final List<String> mockArray = Arrays.asList(new String[] { "alias", "currency", "country", "location" });
		when(mock.getName()).thenReturn("legalentity");
		when(mock.getAttributes()).thenReturn(mockArray);

		final CatalogFilter mock1 = mock(CatalogFilter.class);
		when(mock1.getName()).thenReturn("Account");
		final List<String> mock1Array = Arrays.asList(new String[] { "alias" });
		when(mock1.getAttributes()).thenReturn(mock1Array);

		final List<CatalogFilter> mockedList = new ArrayList<>();
		mockedList.add(mock);
		mockedList.add(mock1);
		when(reducer.filterCatalog(mockedList)).thenCallRealMethod();
		final List<ConfigurationElement> newCatalog = getReducer().filterCatalog(mockedList);

		assertNotNull(newCatalog);
		// should have two elements
		assertThat(newCatalog.size(), CoreMatchers.equalTo(2));

		final ConfigurationElement test = newCatalog.get(1);
		assertThat(test.getCode(), CoreMatchers.equalTo(mock1.getName()));
		assertThat(test.getAttributes().size(), CoreMatchers.equalTo(mock1Array.size()));
		final List<String> testList = test.getAttributes().stream().map(FilterAttribute::getCode)
				.collect(Collectors.toList());
		Collections.sort(testList);
		Collections.sort(mock1Array);
		assertEquals(testList, mock1Array);
	}

}
