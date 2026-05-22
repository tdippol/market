package com.axiante.mui.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.axiante.connection.ConnectionProfile;

public class ConfigurationElementTest {

	@Test(expected = NullPointerException.class)
	public void testSetNullServerThrowsException() {
		final ConfigurationElement element = ConfigurationElement.builder().build();
		element.setServer(null);
	}

	@Test public void testSetServerCompletes() {
		final ConfigurationElement element = ConfigurationElement.builder().build();
		ConnectionProfile p = mock(ConnectionProfile.class);
		element.setServer(p);
		assertEquals(p, element.getServer());
	}
	@Test public void testContainsAttributeCodeReturnsFalseWhenEmpty() {
		final ConfigurationElement element = ConfigurationElement.builder().build();
		assertFalse(element.containsAttributeCode("anything"));
	}

	@Test public void testContainsAttributeCodeReturnsTrueWhenFound() {
		final ConfigurationElement element = ConfigurationElement.builder().build();
		FilterAttribute attribute = mock(FilterAttribute.class);
		when(attribute.getCode()).thenReturn("expected");
		element.getAttributes().add(attribute);
		assertTrue(element.containsAttributeCode("expected"));
	}
	@Test public void testContainsAttributeCodeReturnsFalseWhenNotFound() {
		final ConfigurationElement element = ConfigurationElement.builder().build();
		FilterAttribute attribute = mock(FilterAttribute.class);
		when(attribute.getCode()).thenReturn("expected");
		element.getAttributes().add(attribute);
		assertFalse(element.containsAttributeCode("not expected"));
	}

}