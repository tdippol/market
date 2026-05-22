package com.axiante.connection;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionProfileTest {

	@Spy ConnectionProfile profile;
	
	@Test public void tesGetValidationHostReturnsHostWhenNoPortSet(){
		final String expected = "http://mockedHost";
		profile.setHost(expected);
		assertThat(expected, CoreMatchers.equalTo(profile.getValidationHost()));
	}
	
	@Test public void tesGetValidationHostReturnsHostAndPortWhenPortSet(){
		final String host = "http://mockedHost";
		final String port = ":1234";
		final String expected = host+port;
		profile.setHost(host);
		profile.setPort(port);
		assertThat(expected, CoreMatchers.equalTo(profile.getValidationHost()));
	}
}
