package com.axiante.tm1.http;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.protocol.HttpContext;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Spy;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Tm1HttpClientFactoryTest {

    @Spy
    Tm1HttpClientFactory factory;
    @Rule public ExpectedException exception = ExpectedException.none();

    @Test(expected = NullPointerException.class)
    public void testWithTokenThrownExceptionWhenNull() throws ClientProtocolException, IOException {
        factory.withToken(null);
    }

    @Test
    public void testWithTokenReturnsFactory() throws ClientProtocolException, IOException {
        final Tm1HttpClientFactory test = factory.withToken("mocked token");
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.getCamPassport(), equalTo("CAMPassport mocked token"));
        assertFalse(factory.isUseCredentials());
        assertFalse(factory.isUseBasicAuth());
        assertTrue(factory.isUsePassport());

    }

    @Test
    public void testWithSslVerify() {
        boolean expected = true;
        Tm1HttpClientFactory test = factory.withSslVerify(expected);
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.isValidateSSL(), equalTo(expected));

        expected = false;
        test = factory.withSslVerify(expected);
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.isValidateSSL(), equalTo(expected));

    }

    @Test(expected = NullPointerException.class)
    public void testWithCredentialsThrowsExceptionWhenCredentialsNull() {
        factory.withCredentials(null);
    }

    @Test public void testWithTimeout() {
        double expected = -2;
        Tm1HttpClientFactory test = factory.withTimeout(expected);
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.getTimeout(), equalTo(-1));
        expected = 0;
        test = factory.withTimeout(expected);
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.getTimeout(), equalTo((int)(expected*1000)));
    }

    @Test
    public void testWithCredentials() {
        final String expected = "mocked credentials";
        final Tm1HttpClientFactory test = factory.withCredentials(expected);
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.getCamNameSpace(), equalTo("CAMNamespace " + expected));
        assertThat(factory.isUseCredentials(), equalTo(true));
        assertThat(factory.isUsePassport(), equalTo(false));
        assertThat(factory.isUseBasicAuth(), equalTo(false));
        assertNull(factory.getCamPassport());
    }

    @Test(expected = NullPointerException.class)
    public void testWithBasicAuthThrowsExceptionWhenCredentialsNull() {
        factory.withBasicAuth(null);
    }

    @Test
    public void testWithBasicAuth() {
        final String expected = "mocked credentials";
        final Tm1HttpClientFactory test = factory.withBasicAuth(expected);
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.getBasicNameSpace(), equalTo("Basic " + expected));
        assertThat(factory.isUseCredentials(), equalTo(false));
        assertThat(factory.isUsePassport(), equalTo(false));
        assertThat(factory.isUseBasicAuth(), equalTo(true));
        assertNull(factory.getCamPassport());
    }

    @Test(expected = NullPointerException.class)
    public void testWithCredentialsThrowsExceptionWhenUsernameNull() {
        factory.withCredentials(null, "password", "namespace");
    }

    @Test(expected = NullPointerException.class)
    public void testWithCredentialsThrowsExceptionWhenPasswordNull() {
        factory.withCredentials("username", null, "namespace");
    }

    @Test
    public void testWithCredentialsWhenUsernameAndPasswordAndAnyNamespace() {
        final String username = "username";
        final String password = "password";
        // flip a coin for namespace
        String namespace = null;
        String expected = null;
        final StringBuffer buf = new StringBuffer(username).append(":").append(password);
        if (Math.random() < 0.5) {
            namespace = "namespace";
            buf.append(":").append(namespace);
        }
        expected = Base64.getEncoder().encodeToString(buf.toString().getBytes());
        final Tm1HttpClientFactory test = factory.withCredentials("username", "password", namespace);
        assertNotNull(test);
        assertThat(factory, equalTo(test));
        assertThat(factory.getCamNameSpace(), equalTo("CAMNamespace " + expected));
        assertThat(factory.isUseCredentials(), equalTo(true));
        assertThat(factory.isUsePassport(), equalTo(false));
        assertThat(factory.isUseBasicAuth(), equalTo(false));
        assertNull(factory.getCamPassport());

    }

    @Test
    public void testCreateWhenSslverifyDoesNotTrustAll() {
        factory.withSslVerify(true);
        assertNotNull(factory.create());
        verify(factory, VerificationModeFactory.times(0)).getNoOpTrustStrategy();
    }

    @Test
    public void testCreateWhenNotSslverify() {
        factory.withSslVerify(false);
        assertNotNull(factory.create());
        verify(factory, VerificationModeFactory.times(1)).getNoOpVerifier();
        verify(factory, VerificationModeFactory.times(1)).getInterceptor();
        verify(factory, VerificationModeFactory.times(1)).getNoOpTrustStrategy();
    }

    @Test public void testCreateWhenTimeoutIsSet() {
        assertNotNull(factory.withTimeout(10).create());
        verify(factory, VerificationModeFactory.times(1)).getNoOpVerifier();
        verify(factory, VerificationModeFactory.times(1)).getInterceptor();
        verify(factory, VerificationModeFactory.times(1)).getNoOpTrustStrategy();
    }

    @Test public void testTrustStrategyAlwaysReturnsTrue() throws CertificateException {
        final TrustStrategy test = factory.getNoOpTrustStrategy();
        assertTrue(test.isTrusted(null, null));
        assertTrue(test.isTrusted(null, "HTTPS"));
        assertTrue(test.isTrusted(null, "SOME NON EXISTENT STRATEGY"));

        X509Certificate[] certList = new X509Certificate[] {};
        assertTrue(test.isTrusted(certList, null));
        assertTrue(test.isTrusted(certList, "HTTPS"));
        assertTrue(test.isTrusted(certList, "SOME NON EXISTENT STRATEGY"));

        final X509Certificate cert = mock(X509Certificate.class);
        certList = new X509Certificate[] {cert};
        assertTrue(test.isTrusted(certList, null));
        assertTrue(test.isTrusted(certList, "HTTPS"));
        assertTrue(test.isTrusted(certList, "SOME NON EXISTENT STRATEGY"));

        certList = new X509Certificate[] {cert, cert};
        assertTrue(test.isTrusted(certList, null));
        assertTrue(test.isTrusted(certList, "HTTPS"));
        assertTrue(test.isTrusted(certList, "SOME NON EXISTENT STRATEGY"));
    }

    @Test public void testX509HostnameVerifierAlwaysReturnsTrueWhenVerify() {
        final X509HostnameVerifier test = factory.getNoOpVerifier();
        SSLSession mockedSession = null;
        assertTrue(test.verify(null, mockedSession));
        assertTrue(test.verify("", mockedSession));
        assertTrue(test.verify("something", mockedSession));

        mockedSession = mock(SSLSession.class);
        assertTrue(test.verify(null, mockedSession));
        assertTrue(test.verify("", mockedSession));
        assertTrue(test.verify("something", mockedSession));
    }


    @Test public void testInterceptorProcessDoesNothingWhenAuthorizationSet() throws HttpException, IOException {
        final HttpRequest request = mock(HttpRequest.class);
        final HttpContext context = mock(HttpContext.class);

        when(request.containsHeader("Authorization")).thenReturn(true);

        factory.getInterceptor().process(request, context);
        verify(request, never()).addHeader(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

    }


    @Test public void testInterceptorProcessAddsHeaderWhenAuthorizationNotSet() throws HttpException, IOException {
        final HttpRequest request = mock(HttpRequest.class);
        final HttpContext context = mock(HttpContext.class);

        when(request.containsHeader("Authorization")).thenReturn(false);
        // it's an elseif ... so I can simply set everything to true and swith off one at a time
        doReturn(true).when(factory).isUsePassport();
        doReturn(true).when(factory).isUseCredentials();
        doReturn(true).when(factory).isUseBasicAuth();

        doReturn("campassport").when(factory).getCamPassport();// make sure there is something
        factory.getInterceptor().process(request, context);
        verify(request, times(1)).addHeader("Authorization", factory.getCamPassport());

        doReturn("camnamespace").when(factory).getCamNameSpace();
        doReturn(false).when(factory).isUsePassport();
        factory.getInterceptor().process(request, context);
        verify(request, times(1)).addHeader("Authorization", factory.getCamNameSpace());

        doReturn("basicauth").when(factory).getBasicNameSpace();
        doReturn(false).when(factory).isUseCredentials();
        factory.getInterceptor().process(request, context);
        verify(request, times(1)).addHeader("Authorization", factory.getBasicNameSpace());


    }

}
