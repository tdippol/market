package com.axiante.mui.filter;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

public class DefaultFilterTest {

	@Spy
	DefaultFilter underTest;
	@Test(expected = NullPointerException.class)
	public void when_addFilterCalledWithNullDataThenThrowsNullPointerException() {
		underTest = new DefaultFilter();
		underTest.addFilter(null);
	}
	@Test
	public void when_addFilterCalledWithDataThenDataIsAdded() {
		underTest = new DefaultFilter();
		assertThat(underTest.getDefaults(), CoreMatchers.nullValue());
		underTest.addFilter(Mockito.mock(DefaultDimensionFilter.class));
		assertThat(underTest.getDefaults(), CoreMatchers.notNullValue());
		assertThat(underTest.getDefaults().size(), CoreMatchers.equalTo(1));
	}
}
