package com.axiante.tm1.auth;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.axiante.connection.ConnectionProfile;
import java.net.PasswordAuthentication;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TunnelAuthenticatorTest {
	TunnelAuthenticator auth ;
	ConnectionProfile profile ;
	
	@Before
	public void initTest() {
		auth = Mockito.mock(TunnelAuthenticator.class);
		profile = Mockito.mock(ConnectionProfile.class);
		
		when(profile.getDomain()).thenReturn("axiante");
		when(profile.getUsername()).thenReturn("user");
		when(profile.getPassword()).thenReturn("#####");

		when(auth.getPasswordAuthentication()).thenCallRealMethod();
		when(auth.getProfile()).thenReturn(profile);
	}

	@Test
	public void testGetPasswordAuthenticationReturnsDomainAuth() {
		when(auth.getScheme()).thenReturn("negotiate");
		PasswordAuthentication pwd = auth.getPasswordAuthentication();
		assertTrue(pwd.getUserName().contains("\\"));
		assertThat(pwd.getUserName(), CoreMatchers.equalTo(profile.getDomain() + "\\" + profile.getUsername()));
	}
	
	@Test
	public void testGetPasswordAuthenticationReturnsPassportAuth() {
		when(auth.getScheme()).thenReturn("something");
		PasswordAuthentication pwd = auth.getPasswordAuthentication();
		assertThat(pwd.getUserName(), CoreMatchers.equalTo(profile.getUsername()));
		when(auth.getScheme()).thenCallRealMethod();
	}
	@Test public void testConstructor() {
		TunnelAuthenticator test = new TunnelAuthenticator(profile);
		assertNotNull(test);
		assertThat(test.getProfile(), CoreMatchers.equalTo(profile));
	}
}
