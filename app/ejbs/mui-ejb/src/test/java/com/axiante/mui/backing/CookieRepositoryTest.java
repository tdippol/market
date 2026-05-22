package com.axiante.mui.backing;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mockserver.MockServerTest;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.tm1.http.Tm1HttpClientFactory;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CookieRepositoryTest {
	@Mock HttpSession session;
	@Mock ApplicationConfiguration properties;

	@Mock KeepAliveScheduler keepAliveScheduler;

	@Spy
	@InjectMocks
	CookieRepository repository;
	@Mock Tm1Tunnel tunnel ;
	final Tm1HttpClientFactory dummyFactory = new Tm1HttpClientFactory();
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
	}

	@Test
	public void testGetCookieWithBasicAuth() throws ClientProtocolException, IOException {
		if ( !MockServerTest.isRunning() ) {
			MockServerTest.startServer();
		}
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.BASIC, false);
		final Cookie response = repository.getCookie(profile);
		assertNotNull(response);
		assertEquals("TM1SessionId",response.getName());
	}
	@Test
	public void testGetCookieWithNamespaceAuth() throws ClientProtocolException, IOException {
		if ( !MockServerTest.isRunning() ) {
			MockServerTest.startServer();
		}
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		final Cookie response = repository.getCookie(profile);
		assertNotNull(response);
		assertEquals("TM1SessionId",response.getName());
	}
	@Test
	public void testGetCookieMapHasCookieAndNeedsToTest() {
		final Map<ConnectionProfile, Cookie> cookieMap = new HashMap<>();
		when(repository.getCookieMap()).thenReturn(cookieMap);
		final Cookie expected = mock(Cookie.class);
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		cookieMap.put(profile, expected);
		final Cookie test = repository.getCookie(profile);
		assertNotNull(test);
		assertThat(test, equalTo(expected));
	}
	@Test
	public void testLogoutDoesNotThrowException() {
		final Map<ConnectionProfile, Cookie> cookieMap = new HashMap<>();
		when(repository.getCookieMap()).thenReturn(cookieMap);
		final Cookie expected = mock(Cookie.class);
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		cookieMap.put(profile, expected);
		when(expected.getName()).thenReturn("mocked cookies");
		doNothing().when(session).invalidate();
		repository.logout();
	}
	@Test
	public void testLogoutHandlesClientProtocolException() throws ClientProtocolException, IOException {
		final Map<ConnectionProfile, Cookie> cookieMap = new HashMap<>();
		when(repository.getCookieMap()).thenReturn(cookieMap);
		final Cookie expected = mock(Cookie.class);
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		cookieMap.put(profile, expected);
		when(expected.getName()).thenReturn("mocked cookies");
		when(repository.getTunnel(profile)).thenReturn(tunnel);
		when(tunnel.getCookieStore()).thenReturn(new BasicCookieStore());
		when(tunnel.getVersion(anyDouble(),anyInt(),anyInt(),any(Cookie.class))).thenThrow(ClientProtocolException.class);
		HttpSession session= Mockito.mock(HttpSession.class);
		repository.logout();
	}
	@Test
	public void testLogoutHandlesIOException() throws ClientProtocolException, IOException {
		final Map<ConnectionProfile, Cookie> cookieMap = new HashMap<>();
		when(repository.getCookieMap()).thenReturn(cookieMap);
		final Cookie expected = mock(Cookie.class);
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		cookieMap.put(profile, expected);
		when(expected.getName()).thenReturn("mocked cookies");
		when(repository.getTunnel(profile)).thenReturn(tunnel);
		when(tunnel.getVersion(anyDouble(),anyInt(),anyInt(),any(Cookie.class))).thenThrow(IOException.class);
		when(tunnel.getCookieStore()).thenReturn(new BasicCookieStore());
		doNothing().when(session).invalidate();
		repository.logout();
	}
	@Test
	public void testKeepAliveDoesNotThrowException() {
		final Map<ConnectionProfile, Cookie> cookieMap = new HashMap<>();
		final Cookie expected = mock(Cookie.class);
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		cookieMap.put(profile, expected);
		repository.keepAlive();
	}

	@Test
	public void testTestFacilityReturnsFalseOnError() {
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		assertThat(repository.test(profile), CoreMatchers.equalTo(false));
	}

	@Test
	public void testTestFacilityReturnsTrueOnSuccess() throws ClientProtocolException, IOException {
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.NAMESPACE, false);
		HashMap map = Mockito.mock(HashMap.class);
		when(repository.getCookieMap()).thenReturn(map);
		Cookie cookie = Mockito.mock(Cookie.class);
		when(map.get(profile)).thenReturn(cookie);
		when(repository.getTunnel(profile)).thenReturn(tunnel);
		CloseableHttpResponse myresponse = Mockito.mock(CloseableHttpResponse.class);
		StatusLine status = Mockito.mock(StatusLine.class);
		when(myresponse.getStatusLine()).thenReturn(status);
		when(status.getStatusCode()).thenReturn(200);
		when(tunnel.getVersion(anyDouble(), anyInt(), anyInt(), any(Cookie.class))).thenReturn(myresponse);
		when(myresponse.getEntity()).thenReturn(Mockito.mock(HttpEntity.class));
		assertThat(repository.test(profile), CoreMatchers.equalTo(true));
	}

	@Test
	public void testHealthCheckWithNullProperties() {
		when(repository.getProperties()).thenReturn(null);
		repository.healthCheck();
	}
	@Test
	public void testHealthCheckWithProperties() {
		when(repository.getProperties()).thenReturn(properties);
		repository.healthCheck();
	}

	@Test
	public void testKeepAliveCheckReturnsTrueWhenNoSessionInjected() {
		assertThat(repository.keepAliveCheck(),CoreMatchers.equalTo(true));
	}
	@Test
	public void testKeepAliveCheckReturnsTrueWhenNoUserInSession() {
		HttpSession session = Mockito.mock(HttpSession.class);
		assertThat(repository.keepAliveCheck(),CoreMatchers.equalTo(true));
	}

	@Test
	public void testKeepAliveCheckReturnsTrueWhenCookieInSession() {
		HttpSession session = Mockito.mock(HttpSession.class);
		Map<ConnectionProfile, Cookie> cookieMap = new HashMap<>();
		when(repository.getCookieMap()).thenReturn(cookieMap);
		Cookie cookie = Mockito.mock(Cookie.class);
		ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.BASIC, false);
		cookieMap.put(profile, cookie);
		doReturn(true).when(repository).test(profile);
		assertThat(repository.keepAliveCheck(),CoreMatchers.equalTo(true));
	}

	@Test
	public void testKeepAliveCheckReturnsTrueWhenNoCookieInSession() {
		HttpSession session = Mockito.mock(HttpSession.class);
		Map<ConnectionProfile, Cookie> cookieMap = Mockito.mock(HashMap.class);
		when(cookieMap.entrySet()).thenReturn(new HashSet<Map.Entry<ConnectionProfile,Cookie>>()); // mi serve un set vuoto
		when(repository.getCookieMap()).thenReturn(cookieMap);
		assertThat(repository.keepAliveCheck(),CoreMatchers.equalTo(true));
	}

}
