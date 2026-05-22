package com.axiante.mui.common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

	StringUtils stringUtils = new StringUtils();

	public static Integer integerValue = 1;
	public static Number numberValue=123.25;
	
	@Test
	public void testIsIntegerType() {
		assertTrue(stringUtils.isIntegerType(integerValue.getClass().getSimpleName()));
	}
	
	@Test
	public void testIsNumberType() {
		assertFalse(stringUtils.isNumberType(numberValue.getClass().getSimpleName()));
	}
	
	@Test
	public void testIsInteger() {
		assertTrue(stringUtils.isInteger(String.valueOf(integerValue)));
		assertFalse(stringUtils.isInteger(null));
	}
	
	@Test
	public void testIsNumber() {
		try {
		assertTrue(stringUtils.isNumber(String.valueOf(numberValue)));
		assertFalse(stringUtils.isNumber(null));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

		@Test
	public void testIsEmpty() {
		String string = "some non empty string";
		try {
			assertNotNull(string);
			assertFalse(StringUtils.isEmpty(string));
			assertTrue(StringUtils.isEmpty(null));
			assertTrue(StringUtils.isEmpty(""));
			assertTrue(StringUtils.isEmpty("{}"));
			assertFalse(string.replaceAll(" ", "").isEmpty());
		} catch (final Exception e) {
			assertNull(string);
			assertTrue(false);
		}
	}
}
