package com.axiante.connection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mockserver.MockServerTest;
import com.axiante.tm1.TunnelConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

@RunWith(MockitoJUnitRunner.class)
public class AbstractConnectionTest {

	@Spy
	AbstractConnection connection;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@SuppressWarnings("resource")
	@Test
	public void testConstructor() {
		assertNotNull(new AbstractConnection(null));
		assertNotNull(new AbstractConnection(mock(ConnectionProfile.class)));
	}

	@SuppressWarnings("resource")
	@Test
	public void testConstructorCallsSetProfile() {
		assertNotNull(new AbstractConnection(mock(ConnectionProfile.class)));
	}

	@Test
	public void testSetProfileThrowNullpointerExceptionWhenProfileIsNull() {
		exception.expect(NullPointerException.class);
		connection.setProfile(null);
	}

	@Test
	public void testSetProfileHandlesExceptionWhenProfileAlreadySetAndCloseError() throws IOException {
		@SuppressWarnings("resource")
		final CloseableHttpClient mockedClient = mock(CloseableHttpClient.class);
		doReturn(mockedClient).when(connection).getClient();
		doThrow(IOException.class).when(mockedClient).close();
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		connection.setProfile(mockedProfile);
		assertThat(connection.getProfile(), equalTo(mockedProfile));

	}

	@Test
	public void testSetProfileColsesClientWhenClientOpened() throws IOException {
		final CloseableHttpClient mockedClient = mock(CloseableHttpClient.class);
		connection.client = mockedClient;
		connection.setProfile(mock(ConnectionProfile.class));
		verify(mockedClient).close();
		assertNull(connection.client);

	}

	@Test
	public void testSetProfileHandlesIoExceptionWhenClosingClient() throws IOException {
		final CloseableHttpClient mockedClient = mock(CloseableHttpClient.class);
		connection.client = mockedClient;
		connection.setProfile(mock(ConnectionProfile.class));
		exception = ExpectedException.none();
		assertNull(connection.client);

	}

	@Test
	public void testSetProfileHandlesNonSlashTerminaterServiceRoot() {
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		final String expectedHost = "http://mockedHost";
		final String expectedPort = ":1234";
		final String expectedPath = "/my/path/does/not/end/with/slash";
		when(mockedProfile.getHost()).thenReturn(expectedHost);
		when(mockedProfile.getPort()).thenReturn(expectedPort);
		when(mockedProfile.getPath()).thenReturn(expectedPath);
		connection.setProfile(mockedProfile);
		final String expectedServiceRoot = expectedHost + expectedPort + expectedPath + "/";
		assertThat(connection.serviceRoot, equalTo(expectedServiceRoot));
	}

	@Test
	public void testSetHeadersThrowExceptionWhenPostIsNull() {
		exception.expect(NullPointerException.class);
		connection.setHeaders((HttpPost) null, false);
	}

	@Test
	public void testSetHeadersThrowExceptionWhenGetIsNull() {
		exception.expect(NullPointerException.class);
		connection.setHeaders((HttpGet) null, false);
	}

	@Test
	public void testSetHeadersThrowExceptionWhenPostIsNotNullAndCloseIsNull() {
		exception.expect(NullPointerException.class);
		connection.setHeaders(mock(HttpPost.class), null);
	}

	@Test
	public void testSetHeadersThrowExceptionWhenGetIsNotNullAndCloseIsNull() {
		exception.expect(NullPointerException.class);
		connection.setHeaders(mock(HttpGet.class), null);
	}

	@Test
	public void testSetHeaderSetsPostHeaderWhenCloseIsTrue() {
		final HttpPost mockedPost = mock(HttpPost.class);
		connection.setHeaders(mockedPost, true);
		verify(mockedPost, times(1)).setHeader("Connection", "close");
	}

	@Test
	public void testSetHeaderSetsGetHeaderWhenCloseIsTrue() {
		final HttpGet mockedGet = mock(HttpGet.class);
		connection.setHeaders(mockedGet, true);
		verify(mockedGet, times(1)).setHeader("Connection", "close");
	}

	@Test
	public void testSetHeaderSetsPostHeaderWhenCloseIsFalse() {
		final HttpPost mockedPost = mock(HttpPost.class);
		connection.setHeaders(mockedPost, false);
		verify(mockedPost, times(1)).setHeader("Connection", "keep-alive");
	}

	@Test
	public void testSetHeaderSetsGetHeaderWhenCloseIsFalse() {
		final HttpGet mockedGet = mock(HttpGet.class);
		connection.setHeaders(mockedGet, false);
		verify(mockedGet, times(1)).setHeader("Connection", "keep-alive");
	}

	@Test
	public void testSetHeaderSetsGetHeaders() {
		final boolean close = new Random().nextBoolean();
		final HttpGet mockedGet = mock(HttpGet.class);
		connection.setHeaders(mockedGet, close);
		if (close) {
			verify(mockedGet, times(1)).setHeader("Connection", "close");
		} else {
			verify(mockedGet, times(1)).setHeader("Connection", "keep-alive");
		}
		verify(mockedGet, times(1)).setHeader("Content-Type", "application/json; charset=utf-8; odata.streaming=true");
		verify(mockedGet, times(1)).setHeader("Accept", "application/json;odata.metadata=none,text/plain");
		verify(mockedGet, times(1)).setHeader("TM1-SessionContext", "M.U.I.");
		verify(mockedGet, times(1)).setHeader("User-Agent", "Ax-tunnel");
	}

	@Test
	public void testSetHeaderSetsPostHeaders() {
		final boolean close = new Random().nextBoolean();
		final HttpPost mockedPost = mock(HttpPost.class);
		connection.setHeaders(mockedPost, close);
		if (close) {
			verify(mockedPost, times(1)).setHeader("Connection", "close");
		} else {
			verify(mockedPost, times(1)).setHeader("Connection", "keep-alive");
		}

		verify(mockedPost, times(1)).setHeader("Content-Type", "application/json; charset=utf-8; odata.streaming=true");
		verify(mockedPost, times(1)).setHeader("Accept", "application/json;odata.metadata=none,text/plain");
		verify(mockedPost, times(1)).setHeader("TM1-SessionContext", "M.U.I.");
		verify(mockedPost, times(1)).setHeader("User-Agent", "Ax-tunnel");
	}

	@Test
	public void testSetHeadersWithSinglePostThrowsExceptionWhenNullPost() {
		exception.expect(NullPointerException.class);
		connection.setHeaders((HttpPost) null);
	}

	@Test
	public void testSetHeadersWithSinglePostDelegates() {
		final HttpPost post = mock(HttpPost.class);
		connection.setHeaders(post);
		verify(connection, times(1)).setHeaders(post, false);
	}

	@Test
	public void testSetHeadersWithSingleGetThrowsExceptionWhenNullPost() {
		exception.expect(NullPointerException.class);
		connection.setHeaders((HttpGet) null);
	}

	@Test
	public void testSetHeadersWithSingleGetDelegates() {
		final HttpGet get = mock(HttpGet.class);
		connection.setHeaders(get);
		verify(connection, times(1)).setHeaders(get, false);
	}

	@Test
	public void testPerformLoginReturnsSuccessWhenAuthTypeEqualsNamespace()
			throws ClientProtocolException, IOException {
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		doReturn(mockedProfile).when(connection).getProfile();
		doReturn(AuthType.NAMESPACE).when(mockedProfile).getAuthType();
		assertThat(TunnelConstants.SUCCESS, equalTo(connection.performLogin()));
	}

	@Test
	public void testPerformLoginReturnsSuccessWhenAuthTypeEqualsBasic() throws ClientProtocolException, IOException {
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		doReturn(mockedProfile).when(connection).getProfile();
		doReturn(AuthType.BASIC).when(mockedProfile).getAuthType();
		assertThat(TunnelConstants.SUCCESS, equalTo(connection.performLogin()));
	}

	@Test
	public void testPerformLoginReturnsNullWhenServerResponds404() throws ClientProtocolException, IOException {
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.PASSPORT, false);
		doReturn(profile).when(connection).getProfile();
		doReturn(profile.getValidationHost() + "/v1/api/").when(connection).getServiceRoot();
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withMethod("GET").withPath("/v1/api/" + TunnelConstants.CUBES))
				.respond(new HttpResponse().withStatusCode(404));

		final String test = connection.performLogin();
		MockServerTest.stopServer();
		assertNull(test);
	}

	@Test
	public void testPerformLoginReturnsUnauthorizedWhenServerRespondsWithUNAuthenticateButNotLogin()
			throws ClientProtocolException, IOException {
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.PASSPORT, false);
		doReturn(profile).when(connection).getProfile();
		doReturn(profile.getValidationHost() + "/v1/api/").when(connection).getServiceRoot();
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withMethod("GET").withPath("/ibmcognos/cgi-bin/cognos.cgi")
				.withQueryStringParameter("b_action", "xts.run").withQueryStringParameter("m", "portal/close.xts")
				.withQueryStringParameter("h_CAM_action", "logon"))
				.respond(new HttpResponse().withStatusCode(401).withBody("Unauthorized"));
		client.when(new HttpRequest().withMethod("GET").withPath("/ibmcognos/cgi-bin/cognos.cgi"))
				.respond(new HttpResponse().withStatusCode(200).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));
		client.when(new HttpRequest().withMethod("GET").withPath("/v1/api/" + TunnelConstants.CUBES))
				.respond(new HttpResponse().withStatusCode(401).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));

		final String test = connection.performLogin();
		MockServerTest.stopServer();
		assertNotNull(test);
		assertThat(test, equalTo(TunnelConstants.UNAUTHORIZED));
	}

	@Test
	public void testPerformLoginReturnsPassportWhenServerRespondsWithAuthenticate()
			throws ClientProtocolException, IOException {
		final String mockedPassport = "mockedPassport";
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.PASSPORT, false);
		doReturn(profile).when(connection).getProfile();
		doReturn(profile.getValidationHost() + "/v1/api/").when(connection).getServiceRoot();
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withMethod("GET").withPath("/ibmcognos/cgi-bin/cognos.cgi")
				.withQueryStringParameter("b_action", "xts.run").withQueryStringParameter("m", "portal/close.xts")
				.withQueryStringParameter("h_CAM_action", "logon"))
				.respond(new HttpResponse().withStatusCode(200).withBody("var passport = TM1 " + mockedPassport));
		client.when(new HttpRequest().withMethod("GET").withPath("/ibmcognos/cgi-bin/cognos.cgi"))
				.respond(new HttpResponse().withStatusCode(200).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));
		client.when(new HttpRequest().withMethod("GET").withPath("/v1/api/" + TunnelConstants.CUBES))
				.respond(new HttpResponse().withStatusCode(401).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));

		final String test = connection.performLogin();
		MockServerTest.stopServer();
		assertNotNull(test);
		assertThat(test, equalTo(mockedPassport));
	}

	@Test
	public void testPerformLoginReturnsUnauthorizedWhenServerRespondsWitAuthenticateButNotLogin()
			throws ClientProtocolException, IOException {
		final ConnectionProfile profile = MockServerTest.createConnectionProfile(AuthType.PASSPORT, false);
		doReturn(profile).when(connection).getProfile();
		doReturn(profile.getValidationHost() + "/v1/api/").when(connection).getServiceRoot();
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		client.when(new HttpRequest().withMethod("GET").withPath("/ibmcognos/cgi-bin/cognos.cgi")
				.withQueryStringParameter("b_action", "xts.run").withQueryStringParameter("m", "portal/close.xts")
				.withQueryStringParameter("h_CAM_action", "logon"))

				.respond(new HttpResponse().withStatusCode(200).withBody("Unauthorized"));
		client.when(new HttpRequest().withMethod("GET").withPath("/ibmcognos/cgi-bin/cognos.cgi"))
				.respond(new HttpResponse().withStatusCode(200).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));

		client.when(new HttpRequest().withMethod("GET").withPath("/v1/api/" + TunnelConstants.CUBES))
				.respond(new HttpResponse().withStatusCode(401).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));

		final String test = connection.performLogin();
		MockServerTest.stopServer();
		assertNotNull(test);
		assertThat(test, equalTo(TunnelConstants.UNAUTHORIZED));
	}

	@Test
	public void testExecuteRequestAsHttpResponseWithFourParametersCallsDelegate()
			throws URISyntaxException, IOException {
		final String METHOD_NAME = "GET";
		final String uriString = "uri";
		final BasicHeader[] headers = new BasicHeader[] {};
		try {
			connection.executeRequestAsHttpResponse(METHOD_NAME, uriString, headers);
		} catch (final Exception ignored) {

		}
		verify(connection, times(1)).executeRequestAsHttpResponse(METHOD_NAME, uriString, headers, null);
	}

	@Test
	public void testExecuteRequestAsHttpResponseWithFiveParametersCallsDelegate()
			throws URISyntaxException, IOException {
		final String METHOD_NAME = "GET";
		final String uriString = "uri";
		final BasicHeader[] headers = new BasicHeader[] {};
		final Double timeout = 0d;
		try {
			connection.executeRequestAsHttpResponse(METHOD_NAME, uriString, headers, timeout);
		} catch (final Exception ignored) {

		}
		verify(connection, times(1)).executeRequestAsHttpResponse(METHOD_NAME, uriString, headers, timeout, null);
	}

	@Test
	public void testExecuteRequestAsHttpResponseReturnsNullWhenServerNotPresent()
			throws URISyntaxException, IOException {
		final String METHOD_NAME = "GET";
		final String uriString = "uri";
		final BasicHeader[] headers = new BasicHeader[] {};
		final Double timeout = 0d;
		final Cookie cookie = new BasicClientCookie("cookie", "cookie");

		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		doReturn(mockedProfile).when(connection).getProfile();
		doReturn(AuthType.BASIC).when(mockedProfile).getAuthType();
		doReturn("mockedAuth").when(connection).getBasicAuth();
		assertNull(connection.executeRequestAsHttpResponse(METHOD_NAME, uriString, headers, timeout, cookie));
	}

	@Test
	public void testExecuteRequestAsHttpResponseReturnsClientWhenServerResponds()
			throws URISyntaxException, IOException {
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

		final String METHOD_NAME = "GET";
		final String uriString = "uri";
		final BasicHeader[] headers = new BasicHeader[] {};
		final Double timeout = 0d;
		final Cookie cookie = new BasicClientCookie("cookie", "cookie");

		client.when(new HttpRequest().withMethod("GET").withPath("/yes" + uriString))
				.respond(new HttpResponse().withStatusCode(200).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));

		client.when(new HttpRequest().withMethod("GET").withPath("/" + uriString))
				.respond(new HttpResponse().withStatusCode(401).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));

		final ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.BASIC, false);
		doReturn(mockedProfile).when(connection).getProfile();
		doReturn("mockedAuth").when(connection).getBasicAuth();
		doReturn(mockedProfile.getValidationHost() + "/").when(connection).getServiceRoot();

		assertNotNull(
				connection.executeRequestAsHttpResponse(METHOD_NAME, "yes" + uriString, headers, timeout, cookie));
		assertNotNull(connection.executeRequestAsHttpResponse(METHOD_NAME, uriString, headers, timeout, cookie));

		MockServerTest.stopServer();

	}

	@Test
	public void testCloseSessionDelegates() {
		final Cookie cookie = new BasicClientCookie("cookie", "cookie");
		connection.closeSession(cookie);
		verify(connection, times(1)).closeSession(cookie, null);
	}

	@Test
	public void testCloseSessionReturnsTrueWhenNullCookie() {
		final Double testD = 0d;
		assertTrue(connection.closeSession(null, testD));
	}

	@Test
	public void testCloseSessionReturnsFalseWhenErrorResponseNotCookieExpired() {
		final Double testD = 0d;
		final Cookie cookie = new BasicClientCookie("cookie", "cookie");
		doReturn("http://localhost:" + MockServerTest.DEFAULT_PORT + "/").when(connection).getServiceRoot();
		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}
		assertFalse(connection.closeSession(cookie, testD));
		MockServerTest.stopServer();
	}

	@Test
	public void testCloseSessionReturnsTrueWhenCookieExpired() {
		final Double testD = 0d;
		final Cookie cookie = new BasicClientCookie("cookie", "cookie");
		doReturn("http://127.0.0.1:" + MockServerTest.DEFAULT_PORT + "/").when(connection).getServiceRoot();

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("POST").withPath("/" + TunnelConstants.SESSION_CLOSE))
				.respond(new HttpResponse().withStatusCode(401).withHeader("WWW-Authenticate",
						"CAMPassport http://localhost:8882/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace"));

		assertTrue(connection.closeSession(cookie, testD));

		MockServerTest.stopServer();
	}

	@Test
	public void testCloseSessionReturnsTrueWhenCookieEquals() {
		final Double testD = 0d;
		final Cookie cookie = new BasicClientCookie("cookie", "cookie");
		doReturn("http://127.0.0.1:" + MockServerTest.DEFAULT_PORT + "/").when(connection).getServiceRoot();

		if (!MockServerTest.isRunning()) {
			MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
		}

		final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);
		client.when(new HttpRequest().withMethod("POST").withPath("/" + TunnelConstants.SESSION_CLOSE))
				.respond(new HttpResponse().withStatusCode(200)
						.withCookie(org.mockserver.model.Cookie.cookie("cookie", "cookie")));

		assertTrue(connection.closeSession(cookie, testD));

		MockServerTest.stopServer();
	}

	@Test
	public void testCloseWhenClientNullManagesCase() {
		doReturn(null).when(connection).getClient();
		connection.close();
	}

	@Test
	public void testCloseWhenClientNotNullManagesCase() {
		@SuppressWarnings("resource")
		final CloseableHttpClient mockedClient = mock(CloseableHttpClient.class);
		doReturn(mockedClient).when(connection).getClient();
		connection.close();
	}

	@Test
	public void testCloseWhenClientThrowsExceptionManagesCase() throws IOException {
		@SuppressWarnings("resource")
		final CloseableHttpClient mockedClient = mock(CloseableHttpClient.class);
		doThrow(IOException.class).when(mockedClient).close();
		doReturn(mockedClient).when(connection).getClient();
		connection.close();
	}

	@Test
	public void testGetCAMNamespaceReturnsNullWhenNullProfile() {
		doReturn(null).when(connection).getProfile();
		assertNull(connection.getCAMNamespace());
	}

	@Test
	public void testGetCAMNamespaceReturnsBase64EncodedValueWhenProfileNotNull() {
		final String username = "test";
		final String password = "test";
		final String domain = "test";
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		doReturn(username).when(mockedProfile).getUsername();
		doReturn(password).when(mockedProfile).getPassword();
		doReturn(domain).when(mockedProfile).getDomain();
		doReturn(AuthType.NAMESPACE).when(mockedProfile).getAuthType();
		final String test = Base64.getEncoder().encodeToString((username + ":" + password + ":" + domain).getBytes());
		doReturn(mockedProfile).when(connection).getProfile();

		assertNotNull(connection.getCAMNamespace());
		assertEquals(connection.getCAMNamespace(), test);
	}

	@Test
	public void testGetCAMNamespaceReturnsNullWhenAuthTypeIsNotNamespace() {
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		doReturn(AuthType.BASIC).when(mockedProfile).getAuthType();
		doReturn(mockedProfile).when(connection).getProfile();
		assertNull(connection.getCAMNamespace());
	}

	@Test
	public void testGetBasicAuthReturnsNullWhenNullProfile() {
		doReturn(null).when(connection).getProfile();
		assertNull(connection.getBasicAuth());
	}

	@Test
	public void testGetBasicAuthReturnsBase64EncodedValueWhenProfileNotNull() {
		final String username = "test";
		final String password = "test";
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		doReturn(username).when(mockedProfile).getUsername();
		doReturn(password).when(mockedProfile).getPassword();
		doReturn(AuthType.BASIC).when(mockedProfile).getAuthType();
		final String test = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
		doReturn(mockedProfile).when(connection).getProfile();

		assertNotNull(connection.getBasicAuth());
		assertEquals(connection.getBasicAuth(), test);
	}

	@Test
	public void testGetBasicAuthNullWhenAuthTypeIsNotNamespace() {
		final ConnectionProfile mockedProfile = mock(ConnectionProfile.class);
		doReturn(AuthType.NAMESPACE).when(mockedProfile).getAuthType();
		doReturn(mockedProfile).when(connection).getProfile();
		assertNull(connection.getBasicAuth());
	}

	@Test
	public void testDecodeResponseThrowsExceptions() {
		exception.expect(NullPointerException.class);
		connection.httpUtils.decodeResponse(null);
	}

	@Test
	public void testDecodeResponseHandlesExceptions() throws IllegalStateException, IOException {
		final org.apache.http.HttpResponse mockedResponse = mock(org.apache.http.HttpResponse.class);
		final HttpEntity mockedEntity = mock(HttpEntity.class);
		doReturn(mockedEntity).when(mockedResponse).getEntity();
		doThrow(IOException.class).when(mockedEntity).getContent();
		assertNotNull(connection.httpUtils.decodeResponse(mockedResponse));
		doThrow(IllegalStateException.class).when(mockedEntity).getContent();
		assertNotNull(connection.httpUtils.decodeResponse(mockedResponse));

	}

	@Test
	public void testDecodeResponseReturnsString() throws IllegalStateException, IOException {
		final String test = "mocked response";
		final org.apache.http.HttpResponse mockedResponse = mock(org.apache.http.HttpResponse.class);
		final HttpEntity mockedEntity = mock(HttpEntity.class);
		doReturn(mockedEntity).when(mockedResponse).getEntity();
		when(mockedEntity.getContent()).thenReturn(new ByteArrayInputStream(test.getBytes()));
		final String actual = connection.httpUtils.decodeResponse(mockedResponse);
		assertNotNull(actual);
		assertThat(actual, equalTo(test));
	}

	@Test
	public void testGetClientDelegates() {
		final ConnectionProfile mocked = mock(ConnectionProfile.class);
		when(mocked.getAuthType()).thenReturn(AuthType.PASSPORT);
		when(connection.getProfile()).thenReturn(mocked);
		connection.getClient(null); // <-- only interested in mehod call, this will make the delegate method do
									// nothing
		verify(connection, times(1)).getClient(null, null);
	}
}
