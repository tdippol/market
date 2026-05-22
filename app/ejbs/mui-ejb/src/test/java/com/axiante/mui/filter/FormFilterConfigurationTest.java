package com.axiante.mui.filter;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

public class FormFilterConfigurationTest {

	@Test(expected = NullPointerException.class)
	public void test_whenAddDefaultFilterCalledWithNullThenThrowNullPointerException() {
		new FormFilterConfiguration().addDefaultFilter(null);
	}
	@Test
	public void test_whenAddDefaultFilterCalledThenFilterIsAdded() {
		DefaultDimensionFilter f = Mockito.mock(DefaultDimensionFilter.class);
		FormFilterConfiguration test = new FormFilterConfiguration();
		assertThat(test.getDefaults(), CoreMatchers.nullValue());
		test.addDefaultFilter(f);
		assertThat(test.getDefaults().size(), CoreMatchers.equalTo(1));
	}

	@Test
	public void test_whenGetDimensionsAsJsonOnNonInitializedFilterReturnsNull() {
		FormFilterConfiguration test = new FormFilterConfiguration();
		assertThat(test.getDefaults(), CoreMatchers.nullValue());
		assertThat(test.getDimensionsAsJson(), CoreMatchers.equalTo("null"));
	}
	@Test
	public void test_whenGetDimensionsAsJsonOnInitializedFilterReturnsData() {
		FormFilterConfiguration test = new FormFilterConfiguration();
		test.addDefaultFilter(Mockito.mock(DefaultDimensionFilter.class));
		assertThat(test.getDefaults(), CoreMatchers.notNullValue());
		assertThat(test.getDimensionsAsJson(), CoreMatchers.notNullValue());
	}
	@Test
	public void test_whenFindDefaultDimensionOnNonInitalizedFilterReturnsNull() {
		FormFilterConfiguration test = new FormFilterConfiguration();
		assertThat(test.getDefaults(), CoreMatchers.nullValue());
		assertThat(test.findDefaultDimension("something"), CoreMatchers.nullValue());
	}

	@Test
	public void test_whenFindDefaultDimensionFindsDataReturnsFilter() {
		FormFilterConfiguration test = new FormFilterConfiguration();
		DefaultDimensionFilter f = Mockito.mock(DefaultDimensionFilter.class);
		String code = "code";
		Mockito.when(f.getCode()).thenReturn(code);
		assertThat(test.getDefaults(), CoreMatchers.nullValue());
		test.addDefaultFilter(f);
		assertThat(test.findDefaultDimension(code), CoreMatchers.notNullValue());
	}

	@Test
	public void test_whenInitializeAsEmptyCalledThenContainersAreEmpty() {
		//		dimensions = new HashMap<>();
		//		defaults = new HashSet<>();
		FormFilterConfiguration test = new FormFilterConfiguration();
		assertThat(test.getDimensions(),CoreMatchers.nullValue());
		assertThat(test.getDefaults(),CoreMatchers.nullValue());
		test.initializeAsEmpty();
		assertThat(test.getDimensions(),CoreMatchers.notNullValue());
		assertThat(test.getDefaults(),CoreMatchers.notNullValue());
		assertThat(test.getDimensions().size(),CoreMatchers.equalTo(0));
		assertThat(test.getDefaults().size(),CoreMatchers.equalTo(0));
	}

	@Test
	public void test_whenGetDefaultAsJsonCalledOnNotInitializedThenReturnsNullString() {
		FormFilterConfiguration test = new FormFilterConfiguration();
		assertThat(test.getDefaults(),CoreMatchers.nullValue());
		assertThat(test.getDefaultsAsJson(), CoreMatchers.equalTo("null"));
	}

	@Test
	public void test_whenGetDefaultAsJsonCalledOnInitializedThenReturnsString() {
		FormFilterConfiguration test = new FormFilterConfiguration();
		test.addDefaultFilter(Mockito.mock(DefaultDimensionFilter.class));
		assertThat(test.getDefaults(),CoreMatchers.notNullValue());
		assertThat(test.getDefaultsAsJson(), CoreMatchers.not(CoreMatchers.equalTo("null")));
	}

}
