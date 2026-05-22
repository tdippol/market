package com.axiante.utility;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mockserver.MockServerTest;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.http.client.ClientProtocolException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

@RunWith(MockitoJUnitRunner.class)
public class ConnectorUtilitiesTest {
    @Spy ConnectorUtilities utilities = new ConnectorUtilities();
    ConnectionProfile mockedProfile = MockServerTest.createConnectionProfile(AuthType.BASIC, false);
    @Rule public ExpectedException exception = ExpectedException.none();
    @Mock org.apache.http.HttpResponse mockedResponse;

    @Test public void testExtractLoginServerReturnsCorrectData() {
        final String input = "[WWW-Authenticate:CAMPassport http://myserver/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace]";
        final String result = utilities.extractLoginServer(input);
        assertNotNull(result);
        assertThat(result, equalTo("http://myserver/ibmcognos/cgi-bin/cognos.cgi"));
    }

    @Test  
    public void testExtractLoginServerThrowsException() {
        exception.expect(NullPointerException.class);
        utilities.extractLoginServer(null);
    }

    @Test public void testNtlmLoginThrowsMalformedUrlException() throws ClientProtocolException, IOException {
        exception.expect(MalformedURLException.class);
        utilities.ntlmLogin("/", mockedProfile);
    }
    @Test public void testNtlmLoginThrowsMalformedUrlExceptionWhenNoProtocolSpecified() throws ClientProtocolException, IOException {
        exception.expect(MalformedURLException.class);
        exception.expectMessage(containsString("no protocol: localhost?b_action=xts.run&m=portal/close.xts&h_CAM_action=logon"));
        utilities.ntlmLogin("localhost", mockedProfile);
    }

    @Test public void testNtlmLoginWhenServerReturnsNoBodyThenReturnsNull() throws ClientProtocolException, IOException {
        if(!MockServerTest.isRunning()) {
            MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
        }

        final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

        client.when(new HttpRequest().withPath("/")).respond(
                new HttpResponse()
                .withStatusCode(200)
                .withHeader(new Header("Set-Cookie", "TM1Cookie pippo"))
                );


        final String test = utilities.ntlmLogin("http://localhost:"+MockServerTest.DEFAULT_PORT, mockedProfile);
        MockServerTest.stopServer();
        assertNull(test);
    }

    @Test public void testNtlmLoginWhenServerReturnsNoPassportInBodyThenReturnsNull() throws ClientProtocolException, IOException {
        if(!MockServerTest.isRunning()) {
            MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
        }

        final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

        client.when(new HttpRequest().withPath("/")).respond(
                new HttpResponse()
                .withStatusCode(200)
                .withHeader(new Header("Set-Cookie", "TM1Cookie pippo"))
                .withBody("some unexpected body")
                );


        final String test = utilities.ntlmLogin("http://localhost:"+MockServerTest.DEFAULT_PORT, mockedProfile);
        MockServerTest.stopServer();
        assertNull(test);
    }

    @Test public void testNtlmLoginReturnsData() throws ClientProtocolException, IOException {
        final String expectedPassport = "mockedpassport";
        if(!MockServerTest.isRunning()) {
            MockServerTest.startServer(MockServerTest.DEFAULT_PORT, false);
        }

        final MockServerClient client = new MockServerClient("127.0.0.1", MockServerTest.DEFAULT_PORT);

        client.when(new HttpRequest().withPath("/")).respond(
                new HttpResponse()
                .withStatusCode(200)
                .withHeader(new Header("Set-Cookie", "TM1Cookie pippo"))
                .withBody("var passport = Passport "+expectedPassport)
                );


        final String test = utilities.ntlmLogin("http://localhost:"+MockServerTest.DEFAULT_PORT, mockedProfile);
        MockServerTest.stopServer();
        assertNotNull(test);
        assertThat(test, equalTo(expectedPassport));
    }

    @Test public void testCanAuthenticateElseWhereThrowsExceptionWhenResponseIsNull(){
        exception.expect(NullPointerException.class);
        utilities.canAuthenticateElseWhere(null, "some header");
    }

    @Test public void testCanAuthenticateElseWhereThrowsExceptionWhenAuthHeaderIsNull(){
        exception.expect(NullPointerException.class);
        utilities.canAuthenticateElseWhere(mockedResponse, null);
    }

    @Test public void testCanAuthenticateElseWhereReturnsNullWhenNoCamPassportReturned(){
        final org.apache.http.Header mockedHeader = mock(org.apache.http.Header.class);
        when(mockedHeader.getValue()).thenReturn("mocked value");

        when(mockedResponse.getHeaders(ArgumentMatchers.anyString())).thenReturn(new org.apache.http.Header[] {mockedHeader});
        assertNull(utilities.canAuthenticateElseWhere(mockedResponse, "mocked header"));
    }

    @Test public void testCanAuthenticateElseWhereReturnsValueWhenCamPassportReturned(){
        final org.apache.http.Header mockedHeader = mock(org.apache.http.Header.class);
        when(mockedHeader.getValue()).thenReturn("CAMPassport expected");

        when(mockedResponse.getHeaders(ArgumentMatchers.anyString())).thenReturn(new org.apache.http.Header[] {mockedHeader});
        final String test = utilities.canAuthenticateElseWhere(mockedResponse, "mocked header");
        assertNotNull(test);
        assertThat(test,equalTo("CAMPassport expected"));

    }


}
