package com.axiante.tm1.mdx.objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FilterImplTest {
	@Spy FilterImpl filter;
	
	@Test public void testGetColumn() {
		String expected = "mocked";
		filter.setDimension(expected);
		assertThat(filter.getColumn(), equalTo(expected));
	}
	
	@Test public void testGetValueAlwaysReturnsNull() {
		assertNull(filter.getValue());
		String mockedDimension = "mock";
		filter.setDimension(mockedDimension);
		assertNull(filter.getValue());
		String mockedAttribute = "mockedAttribute";
		filter.setAttribute(mockedAttribute);
		assertNull(filter.getValue());
		String mockedValue = "mockedValue";
		filter.setValues(Arrays.asList(new String[] {mockedValue}));
		assertNull(filter.getValue());
	}
	
	@Test public void testGetFilter() {
		String mockedDimension = "mock";
		String mockedAttribute = "mockedAttribute";
		String mockedValue = "mockedValue";
		filter.setDimension(mockedDimension);
		filter.setAttribute(mockedAttribute);
		filter.setValues(Arrays.asList(new String[] {mockedValue}));
		
		String test = filter.getFilter();
		assertNotNull(test);
		String expected = "(["+mockedDimension+"].["+mockedAttribute+"] = \""+mockedValue+"\")";
		assertThat(test, CoreMatchers.equalTo(expected));
		String mockedValue1 = "mockedValue1";
		filter.setValues(Arrays.asList(new String[] {mockedValue, mockedValue1}));
		expected = "(["+mockedDimension+"].["+mockedAttribute+"] = \""+mockedValue+"\" OR ["+mockedDimension+"].["+mockedAttribute+"] = \""+mockedValue1+"\")";
		test = filter.getFilter();
		assertThat(test, CoreMatchers.equalTo(expected));
	}
}
