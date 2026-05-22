package com.axiante.utility.configuration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.connection.ConnectionProfile;
import com.axiante.tm1.mdx.Command;
import com.axiante.tm1.mdx.objects.Query;
import java.util.Random;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class ConfigurationTest {
	
	
	@Test public void testConfigurationEqualsItself() {
		Configuration configuration = Configuration.builder().build();
		configuration.setName("mock");
		assertTrue(configuration.equals(configuration));
	}
	
	@Test public void testConfigurationNotEqualsObjectNotConfiguration() {
		Configuration configuration = Configuration.builder().build();
		Object test = new Object();
		assertFalse(configuration.equals(test));
	}

	@Test public void testConfigurationEqualsByName() {
		Configuration configuration = createConfiguration("mock");
		Configuration test = mock(Configuration.class);
		when(test.getName()).thenReturn("mock");
		configuration.setName("not mock");
		assertFalse(configuration.equals(test));
		configuration.setName("mock");
		assertTrue(configuration.equals(test));
	}
	
	@Test public void testHashCodeEqualsItself() {
		Configuration configuration = createConfiguration("mock");
		assertThat(configuration.hashCode(), CoreMatchers.equalTo(configuration.hashCode()));
	}
	
	@Test public void testHashCodeComputedByName() {
		Configuration configuration = createConfiguration("mock");
		Configuration test = configuration.toBuilder().build();
		test.setName("some other name");
		assertThat(configuration.hashCode(), CoreMatchers.not(CoreMatchers.equalTo(test.hashCode())));
		test.setName("mock");
		assertThat(configuration.hashCode(), CoreMatchers.equalTo(test.hashCode()));
	}
	
	private Configuration createConfiguration(String name) {
		Random rand = new Random();
		Configuration configuration = Configuration.builder().build();
		configuration.setFilePath("mock");
		configuration.setFileLabel("mock");
		configuration.setName(name);
		configuration.setMdx(mock(Query.class));;
		configuration.setSkip(rand.nextBoolean());
		configuration.setLogMemory(rand.nextBoolean());;
		configuration.setLogTime(rand.nextBoolean());;
		configuration.setCommand(mock(Command.class));;
		configuration.setAutoGroupColumnDef("mock");
		configuration.setColumnDefs("mock");;
		configuration.setRowClassRules("mock");
		configuration.setGroupRowAggNodes("mock");
		configuration.setRowSuppressionEnabled(rand.nextBoolean());
		configuration.setColSuppressionEnabled(rand.nextBoolean());
		configuration.setSuppressRowClickSelection(rand.nextBoolean());
		configuration.setProfile(mock(ConnectionProfile.class)); ;
		configuration.setMaxCells(rand.nextInt());
		configuration.setMaxCellsMessage("mock");
		configuration.setPagination(rand.nextBoolean());
		configuration.setPreSelections("mock");
		configuration.setSelections("mock");
		configuration.setActions("mock");

		return configuration;
	}
}
