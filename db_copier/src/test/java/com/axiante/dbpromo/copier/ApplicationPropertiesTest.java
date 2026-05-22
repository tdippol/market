package com.axiante.dbpromo.copier;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ApplicationPropertiesTest {

	@Test
	public void testApplicationPropertiesIsLoaded() {
		assertNotNull(ApplicationProperties.getInstance());
		assertNotNull(ApplicationProperties.getInstance().getProperties());
		assertNotNull(ApplicationProperties.getInstance().getProperties().get("configurazione"));
	}
}
