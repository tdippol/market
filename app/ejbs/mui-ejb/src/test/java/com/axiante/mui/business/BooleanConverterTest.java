package com.axiante.mui.business;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class BooleanConverterTest {

	BooleanConverter converter = new BooleanConverter();

	@Test
	public void test_whenConvertingToDatabaseNullValuesAreZero() {
		assertThat(converter.convertToDatabaseColumn(null), CoreMatchers.equalTo(0));
	}
	@Test
	public void test_whenConvertingToDatabaseTrueValuesAreOne() {
		assertThat(converter.convertToDatabaseColumn(Boolean.TRUE), CoreMatchers.equalTo(1));
	}
	@Test
	public void test_whenConvertingToDatabaseFalseValuesAreZero() {
		assertThat(converter.convertToDatabaseColumn(Boolean.FALSE), CoreMatchers.equalTo(0));
	}

	@Test
	public void test_whenConvertingFromIntegerNullValuesAreFalse() {
		assertThat(converter.convertToEntityAttribute(null), CoreMatchers.equalTo(Boolean.FALSE));
	}
	@Test
	public void test_whenConvertingFromIntegerOnesValuesAreTrue() {
		assertThat(converter.convertToEntityAttribute(1), CoreMatchers.equalTo(Boolean.TRUE));
	}
	@Test
	public void test_whenConvertingFromIntegerNotOnesValuesAreFalse() {
		assertThat(converter.convertToEntityAttribute(0), CoreMatchers.equalTo(Boolean.FALSE));
		// testing a random number between 2 and 100
		int r =2 +  (int)(Math.random()*(99));
		assertThat(converter.convertToEntityAttribute(r), CoreMatchers.equalTo(Boolean.FALSE));
	}

}
