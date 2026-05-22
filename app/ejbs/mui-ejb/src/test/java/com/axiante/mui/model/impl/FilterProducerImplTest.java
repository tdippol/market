package com.axiante.mui.model.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Spy;

import com.axiante.tm1.mdx.filter.Filter;

public class FilterProducerImplTest {

	@Rule public ExpectedException expect = ExpectedException.none();
	@Spy FilterProducerImpl filterProducerImpl = new FilterProducerImpl();
	
	@Test public void testGetFiltersThrowsException() {
		expect.expect(NullPointerException.class);
		filterProducerImpl.getFilters(null);
	}
	@Test public void testGetFiltersWithEmptyStringReturnsEmptyData() {
		List<Filter> test = filterProducerImpl.getFilters("");
		assertNotNull(test);
		assertThat(test.size(), equalTo(0));
	}
	
	@Test public void testGetFiltersWithWrongJsonReturnsEmptyData() {
		List<Filter> test = filterProducerImpl.getFilters("[wrong]");
		assertNotNull(test);
		assertThat(test.size(), equalTo(0));
	}
	@Test public void testGetFiltersWithEmptyObjectReturnsNullData() {
		List<Filter> test = filterProducerImpl.getFilters("{}");
		assertNotNull(test);
		assertThat(test.size(), equalTo(1));
		assertNull(test.get(0));
	}
	
	@Test public void testGetFiltersReturnsData() {
		String json = "{'Dimension':'Test','Attribute':'TestAttribute','selectedValues':['value1','value2','value3']}";
		json = json.replace("\'", "\"");
		List<Filter> test = filterProducerImpl.getFilters(json);
		assertNotNull(test);
		assertThat(test.size(), equalTo(1));
		assertNotNull(test.get(0));
		Filter f = test.get(0);
		assertThat("Test", equalTo(f.getColumn()));
		String expectedFilter = "([Test].[TestAttribute] = \"value1\" OR [Test].[TestAttribute] = \"value2\" OR [Test].[TestAttribute] = \"value3\")";
		assertThat(f.getFilter(), equalTo(expectedFilter));
	}
}
