package com.axiante.tm1;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class TunnelConstantsTest {

	@Test(expected = NullPointerException.class) 
	public void testExecuteProcessThrowsException() {
		TunnelConstants.executeProcess(null, false);
	}
	@Test public void testExecuteProcessReturnsValue() {
		String test = TunnelConstants.executeProcess("mock", false);
		assertNotNull(test);
		assertThat(test, CoreMatchers.equalTo("Processes('mock')/tm1.Execute"));
	}
	
	@Test public void testExecuteProcessAlwaysReturnsValueIfNotNull() {
		String test = TunnelConstants.executeProcess("", false);
		assertNotNull(test);
		assertThat(test, CoreMatchers.equalTo("Processes('')/tm1.Execute"));
		String input = RandomStringUtils.randomAlphanumeric(10);
		test = TunnelConstants.executeProcess(input, false);
		assertNotNull(test);
		assertThat(test, CoreMatchers.equalTo("Processes('"+input+"')/tm1.Execute"));
		
	}

}
