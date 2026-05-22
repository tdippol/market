package com.axiante.connection;

import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.TunnelConstants;
import com.axiante.tm1.http.Tm1HttpClientFactory;
import com.axiante.utility.ConnectorUtilities;
import com.axiante.utility.SPNEGOUtilities;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Base64;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

@Slf4j
public class AbstractConnection implements ConnectionInterface {
    private static final long serialVersionUID = -7258862358928551578L;
    @Getter(value = AccessLevel.PROTECTED)
    protected ConnectorUtilities utilities = new ConnectorUtilities();
    @Getter(value = AccessLevel.PROTECTED)
    protected CloseableHttpClient client;
    @Getter(value = AccessLevel.PROTECTED)
    protected ConnectionProfile profile;
    @Getter(value = AccessLevel.PROTECTED)
    protected boolean validateCertificate;
    @Getter(value = AccessLevel.PROTECTED)
    protected String serviceRoot;
    @Getter(value = AccessLevel.PROTECTED)
    protected Double timeout;
    @Getter(value = AccessLevel.PROTECTED)
    protected Tm1HttpClientFactory factory = null;
    protected final Double DEFAULT_TIMEOUT = 1d;
    protected final Integer DEFAULT_SOCKET_TIMEOUT = 1;
    protected final Integer DEFAULT_CONNECTION_TIMEOUT = 1;

    protected HttpUtils httpUtils = new HttpUtils();

    /**
     * needed for unit tests
     */
    protected AbstractConnection() {
    }

    public CookieStore getCookieStore() {
        if (factory == null) {
            factory = new Tm1HttpClientFactory();
        }
        return factory.getCookieStore();
    }

    public AbstractConnection(final ConnectionProfile profile) {
        if (profile == null) {
            log.debug(
                    "Creating interface without connection profile: remember to set one before doing anything with the server!");
        } else {
            setProfile(profile);
        }
    }

    /**
     * sets a profile to be used with the connections
     *
     * @param profile: the profile to be set
     */
    @Override
    public void setProfile(@NonNull final ConnectionProfile profile) {
        if (this.getClient() != null) {
            try {
                this.getClient().close();
            } catch (final IOException e) {
                log.warn("unable to close http client, this could lead to a memory leak!", e);
            } finally {
                client = null;
            }
        }
        this.profile = profile;
        serviceRoot = getProfile().getHost() + getProfile().getPort() + getProfile().getPath();
        if (!serviceRoot.endsWith("/")) {
            serviceRoot += "/";
        }
        validateCertificate = getProfile().isValidatessl();
    }

    protected AbstractHttpMessage setHeaders(@NonNull final AbstractHttpMessage message) {
        return this.setHeaders(message, false);
    }

    protected AbstractHttpMessage setHeaders(@NonNull final AbstractHttpMessage message, @NonNull final Boolean close) {
        if (close) {
            message.setHeader("Connection", "close");
        } else {
            message.setHeader("Connection", "keep-alive");
        }
        message.setHeader("Content-Type", "application/json; charset=utf-8; odata.streaming=true");
        message.setHeader("Accept", "application/json;odata.metadata=none,text/plain");
        message.setHeader("TM1-SessionContext", "M.U.I.");
        message.setHeader("User-Agent", "Ax-tunnel");
        message.setHeader("Accept-Language", "en-US");

        return message;
    }


    @Override
    public Cookie openConnection(Double timeout, Integer socketTimeout, Integer connectionRequestTimeout, final String camPassport) throws ClientProtocolException, IOException {
        log.debug("creating a connection cookie for passport " + camPassport);
        if (timeout == null) {
            timeout = DEFAULT_TIMEOUT;
        }
        if (socketTimeout == null) {
            socketTimeout = DEFAULT_SOCKET_TIMEOUT;
        }
        if (connectionRequestTimeout == null) {
            connectionRequestTimeout = DEFAULT_CONNECTION_TIMEOUT;
        }
        final HttpGet post = new HttpGet(getServiceRoot() + TunnelConstants.CUBES);
        final Tm1HttpClientFactory factory;
        Cookie cookie = null;

        switch (getProfile().getAuthType()) {
            case NAMESPACE:
                factory = new Tm1HttpClientFactory().withCredentials(camPassport);
                break;
            case PASSPORT:
                factory = new Tm1HttpClientFactory().withToken(camPassport);
                break;
            case BASIC:
                factory = new Tm1HttpClientFactory().withBasicAuth(camPassport);
                break;
            case SPNEGO:
                // TODO: spnego tests
            default:
                factory = new Tm1HttpClientFactory();
                break;
        }

        try (
                CloseableHttpClient client = factory.withSslVerify(isValidateCertificate()).withConnectionRequestTimeout(connectionRequestTimeout).withTimeout(timeout).withSocketTimeout(socketTimeout).create();
                CloseableHttpResponse response = client.execute(post);) {

            if (response.getStatusLine().getStatusCode() == 404) {
                log.error("server not found");
                EntityUtils.consume(response.getEntity());
                return cookie;
            }
            if (response.getStatusLine().getStatusCode() != 200) {
                final String loginServer = utilities.canAuthenticateElseWhere(response, TunnelConstants.AUTH_HEADER);
                if (loginServer != null) {
                    /*
                     * mi aspetto [WWW-Authenticate: CAMPassport
                     * http://VMEYC01.hq.axiante.com/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace]
                     */
                    final String server = utilities.extractLoginServer(loginServer);
                    switch (getProfile().getAuthType()) {
                        case SPNEGO:
                            new SPNEGOUtilities().getToken(getProfile().getUsername(), getProfile().getDomain(), server);
                            // TODO: perform a get version and return a cookie
                            break;
                        default:
                            cookie = utilities.getCookieFromNtlmLogin(server, profile);

                    }
                }
            } else {
                this.factory = factory;
                cookie = new HttpUtils().extractCookie(getCookieStore());
            }
            EntityUtils.consume(response.getEntity());
        } catch (final UnknownHostException hne){
            if ( log.isDebugEnabled() ){
                log.error("Unable to resolve host {}", getServiceRoot() , hne);
            } else {
                log.error("Unable to resolve host {}\nerror: {}", getServiceRoot(), hne.getMessage());
            }
        } catch (final Throwable t) {
            if ( log.isDebugEnabled() ){
                log.error("Generic error opening connection to {}", getServiceRoot() , t);
            } else {
                if ( t.getCause()  != null ) {
                    log.error("Generic error opening connection to {}\nerror: {}", getServiceRoot(), t.getCause().getMessage());
                } else {
                    log.error("Generic error opening connection to {}\nerror: {}", getServiceRoot(), t.getMessage());
                }
            }
        }
        return cookie;
    }

    /**
     * tries to log-in to the server. If login is not necessary, return null
     * otherwise returns the token. If the login is not successful, returns
     * {@link TunnelConstants#UNAUTHORIZED}
     *
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Override
    public String performLogin(Double timeout, Integer socketTimeout, Integer connectionRequestTimeout) throws ClientProtocolException, IOException {
        if (timeout == null) {
            timeout = DEFAULT_TIMEOUT;
        }
        if (socketTimeout == null) {
            socketTimeout = DEFAULT_SOCKET_TIMEOUT;
        }
        if (connectionRequestTimeout == null) {
            connectionRequestTimeout = DEFAULT_CONNECTION_TIMEOUT;
        }
        final HttpGet post = new HttpGet(getServiceRoot() + TunnelConstants.CUBES);
        final Tm1HttpClientFactory factory = new Tm1HttpClientFactory();
        if ((getProfile().getAuthType() == AuthType.NAMESPACE) || (getProfile().getAuthType() == AuthType.BASIC)) {
            return TunnelConstants.SUCCESS;
        }
        try (CloseableHttpClient client = factory.withSslVerify(isValidateCertificate()).withConnectionRequestTimeout(connectionRequestTimeout).withTimeout(timeout).withSocketTimeout(socketTimeout).create();
             CloseableHttpResponse response = client.execute(post);) {

            if (response.getStatusLine().getStatusCode() == 404) {
                log.error("server not found");
                EntityUtils.consume(response.getEntity());
                return null;
            }
            if (response.getStatusLine().getStatusCode() != 200) {
                final String loginServer = utilities.canAuthenticateElseWhere(response, TunnelConstants.AUTH_HEADER);
                if (loginServer != null) {
                    /*
                     * mi aspetto [WWW-Authenticate: CAMPassport
                     * http://VMEYC01.hq.axiante.com/ibmcognos/cgi-bin/cognos.cgi, CAMNamespace]
                     */
                    final String server = utilities.extractLoginServer(loginServer);
                    String authToken = null;
                    switch (getProfile().getAuthType()) {
                        case SPNEGO:
                            authToken = new SPNEGOUtilities().getToken(getProfile().getUsername(), getProfile().getDomain(),
                                    server);
                            break;
                        default:
                            authToken = utilities.ntlmLogin(server, profile);
                            break;

                    }

                    if (authToken == null) {
                        log.error("error logging in");
                        EntityUtils.consume(response.getEntity());

                        return TunnelConstants.UNAUTHORIZED;
                    } else {
                        EntityUtils.consume(response.getEntity());

                        return authToken;
                    }
                }
            }
            EntityUtils.consume(response.getEntity());

        } catch (final Throwable t) {
            log.error("Generic error performing login", t);
        }
        return TunnelConstants.UNAUTHORIZED;
    }

    /**
     * Executes a request using an http method specified and returns an http
     * response
     *
     * @param METHOD_NAME
     * @param uriString
     * @param headers
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    @Override
    public CloseableHttpResponse executeRequestAsHttpResponse(final String METHOD_NAME, final String uriString,
                                                              final BasicHeader[] headers) throws URISyntaxException, IOException {
        return this.executeRequestAsHttpResponse(METHOD_NAME, uriString, headers, null);
    }

    public CloseableHttpResponse executeRequestAsHttpResponse(final String METHOD_NAME, final String uriString,
                                                              final BasicHeader[] headers, final Double timeout) throws URISyntaxException, IOException {
        return this.executeRequestAsHttpResponse(METHOD_NAME, uriString, headers, timeout, null);
    }

    public CloseableHttpResponse executeRequestAsHttpResponse(final String METHOD_NAME, final String uriString,
                                                              final BasicHeader[] headers, final Double timeout, final Cookie cookie)
            throws URISyntaxException, IOException {
        final ConnectionData data = executeRequestAsConnectionData(METHOD_NAME, uriString, null, headers, timeout,
                cookie);
        return data.getResponse();
    }

    @SuppressWarnings("resource")
    public ConnectionData executeRequestAsConnectionData(final String METHOD_NAME, final String uriString,
                                                         final String mdx, final BasicHeader[] headers, final Double timeout, final Cookie cookie)
            throws URISyntaxException, IOException {
        getClient(METHOD_NAME, timeout);
        setCookie(cookie);
        HttpRequestBase request = null;
        CloseableHttpResponse response = null;
        if (METHOD_NAME == null) {
            request = new HttpGet(getServiceRoot() + uriString);
        } else if (METHOD_NAME.equals("GET")) {
            request = new HttpGet(getServiceRoot() + uriString);
        } else if (METHOD_NAME.equals("POST")) {
            request = new HttpPost(getServiceRoot() + uriString);
            this.setHeaders(request);
        } else if (METHOD_NAME.equals("DELETE")) {
            request = new HttpDelete(getServiceRoot() + uriString);
        }
        try {
            if (headers != null) {
                for (final BasicHeader header : headers) {
                    request.setHeader(header);
                }
            }
            log.debug("submitting request " + request);
            if (mdx != null) {
                final StringEntity entity = new StringEntity(

                        "{" + JsonUtils.getMapper().writeValueAsString("MDX") + ":"
                                + JsonUtils.getMapper().writeValueAsString(mdx) + "}",
                        ContentType.APPLICATION_JSON);
                // if mdx I can only have posts
                ((HttpPost) request).setEntity(entity);
            }
            response = client.execute(request);
            log.debug("response retrieved");
        } catch (final Exception e) {
            final StringBuffer message = new StringBuffer();
            message.append("error executing request").append("\n").append("METHOD_NAME=").append(METHOD_NAME)
                    .append("\n").append("uriString=").append(uriString).append("\n");
            if (headers != null) {
                message.append("headers=");
                for (final BasicHeader h : headers) {
                    message.append("\n").append(h.getName()).append("=").append(h.getValue());
                }
            }
            message.append("timeout=").append(timeout).append("\n").append("cookie=").append(cookie).append("\n");
            log.error(message.toString(), e);
        }
        final ConnectionData data = new ConnectionData();
        data.setRequest(request);
        data.setResponse(response);
        return data;
    }
    //
    // @SuppressWarnings("resource")
    // public ConnectionData executeRequestAsConnectionData(final String
    // METHOD_NAME, final String uriString, final BasicHeader[] headers, final
    // Double timeout, final Cookie cookie)
    // throws URISyntaxException, IOException {
    // setCookie(cookie);
    //
    // HttpRequestBase request = null;
    // CloseableHttpResponse response = null;
    // if (METHOD_NAME == null) {
    // request = new HttpGet(getServiceRoot() + uriString);
    // } else if (METHOD_NAME.equals("GET")) {
    // request = new HttpGet(getServiceRoot() + uriString);
    // } else if (METHOD_NAME.equals("POST")) {
    // request = new HttpPost(getServiceRoot() + uriString);
    // this.setHeaders(request);
    // } else if (METHOD_NAME.equals("DELETE")) {
    // request = new HttpDelete(getServiceRoot() + uriString);
    // }
    // try {
    // if (headers != null) {
    // for (final BasicHeader header : headers) {
    // request.setHeader(header);
    // }
    // }
    // log.debug("submitting request " + request);
    //
    // getClient(METHOD_NAME, timeout);
    // response = client.execute(request);
    // log.debug("response retrieved");
    // } catch (final Exception e) {
    // final StringBuffer message = new StringBuffer();
    // message.append("error executing
    // request").append("\n").append("METHOD_NAME=").append(METHOD_NAME)
    // .append("\n").append("uriString=").append(uriString).append("\n");
    // if (headers != null) {
    // message.append("headers=");
    // for (final BasicHeader h : headers) {
    // message.append("\n").append(h.getName()).append("=").append(h.getValue());
    // }
    // }
    // message.append("timeout=").append(timeout).append("\n").append("cookie=").append(cookie).append("\n");
    // log.error(message.toString(), e);
    // }
    // final ConnectionData data = new ConnectionData();
    // data.setRequest(request);
    // data.setResponse(response);
    // return data;
    // }

    @Override
    public boolean closeSession(final Cookie cookie) {
        return this.closeSession(cookie, null);
    }

    public boolean closeSession(final Cookie aCookie, final Double timeout) {
        Cookie cookie = null;
        if (aCookie == null) {
            cookie = getCookieStore().getCookies().stream()
                    .filter(c -> c.getName().equals(TunnelConstants.TM1CONNECTIONCOOKIE)).findAny().orElse(null);
        } else {
            cookie = aCookie;
        }

        boolean ret = false;
        if (cookie != null) {
            final HttpRequestBase request = new HttpPost(getServiceRoot() + TunnelConstants.SESSION_CLOSE);
            if (timeout != null) {
                if (factory == null) {
                    factory = new Tm1HttpClientFactory().withTimeout(timeout);
                } else {
                    factory = factory.withTimeout(timeout);
                }
            } else {
                final CookieStore store = factory != null ? factory.getCookieStore() : null;
                factory = new Tm1HttpClientFactory(); // reset the factory
                if (store != null) {
                    store.getCookies().forEach(c -> factory.getCookieStore().addCookie(c));
                }
            }
            try (CloseableHttpClient client = factory.create();
                 final CloseableHttpResponse response = client.execute(request);) {
                if (!new HttpUtils().checkResponse(response)) {
                    // cookie expired
                    final int statusCode = response.getStatusLine().getStatusCode();
                    switch (statusCode) {
                        case 401:
                            log.warn("the cookie " + cookie + " has presumibly expired as TM1 returned an error code : ["
                                    + response.getStatusLine().getStatusCode() + "] "
                                    + response.getStatusLine().getReasonPhrase());
                            ret = true;
                            break;

                        default:
                            log.error("The server did not succeed in closing the session and returned an error : ["
                                    + response.getStatusLine().getStatusCode() + "] "
                                    + response.getStatusLine().getReasonPhrase());
                            break;
                    }
                } else {
                    ret = true;
                }
            } catch (final Exception e) {
                log.error("error closing session", e);
            }
        } else {
            ret = true;
        }
        return ret;
    }

    /**
     * Returns an HTTP client for the specified method using the specified token for
     * SSO
     *
     * @param method the HTTP method. If empty or null an HTTP GET is used
     * @return
     */
    protected CloseableHttpClient getClient(final String method) {
        return this.getClient(method, null);
    }

    protected boolean timeoutChanged(final Double timeout) {
        boolean changed = false;
        // il timeout e'cambiato ?
        if ((getTimeout() != null) && (timeout == null)) {
            // si ho tolto il timeout
            changed = true;
        } else if ((getTimeout() == null) && (timeout != null)) {
            // si ho messo il timeout
            changed = true;
        } else if ((getTimeout() != null) && (timeout != null) && (getTimeout() != timeout)) {
            // si, ho cambiato il timeout
            changed = true;
        } else {
            // no
        }
        return changed;
    }

    protected CloseableHttpClient getClient(final String method, final Double timeout) {
        return this.getClient(method, timeout, null, null);
    }

    protected CloseableHttpClient getClient(final String method, final Double timeout, final Integer socketTimeout,
                                            final Integer connectionRequestTimeout) {
        // il timeout e'cambiato ?
        if (timeoutChanged(timeout)) {
            this.timeout = timeout;
            // avevo un client aperto ?
            if (this.getClient() != null) {
                log.debug("timeout changed on tunnel");
                try {
                    client.close();
                } catch (final IOException e) {
                    log.warn("unable to close http client, this could lead to a memory leak!", e);
                } finally {
                    log.warn("timeout changed, closing the current client and all its children connection");
                    client = null;
                }
            }
        }
        if (this.getClient() == null) {
            if (getProfile().getAuthType().equals(AuthType.NAMESPACE)) {
                factory = new Tm1HttpClientFactory().withCredentials(getCAMNamespace());
            } else if (getProfile().getAuthType().equals(AuthType.BASIC)) {
                factory = new Tm1HttpClientFactory().withBasicAuth(getBasicAuth());
            } else {
                if (getCookieStore().getCookies().stream()
                        .filter(cookie -> cookie.getName().equals(TunnelConstants.TM1CONNECTIONCOOKIE)).findAny()
                        .orElse(null) == null) {
                    log.warn("CAMPassport nor CAMNamespace nor Connection Cookie provided");
                }
            }
            if (factory != null) {
                if (timeout != null) {
                    factory = factory.withTimeout(timeout);
                }
                if (socketTimeout != null) {
                    factory = factory.withSocketTimeout(socketTimeout);
                }
                if (connectionRequestTimeout != null) {
                    factory = factory.withConnectionRequestTimeout(connectionRequestTimeout);
                }
                client = factory.create();

            }
        }
        return this.getClient();
    }

    /**
     * closes the current client
     */
    @Override
    public void close() {
        if (this.getClient() != null) {
            try {
                this.getClient().close();
                client = null;
            } catch (final IOException e) {
                log.error("Error closing connection, this could lead to memory leak!", e);
            }
        }
    }

    /**
     * creates a CAM Namespace for the authentication
     *
     * @return
     */
    @Override
    public String getCAMNamespace() {
        if ((getProfile() != null) && getProfile().getAuthType().equals(AuthType.NAMESPACE)) {
            // try to generate a CAMNameSpace token
            return Base64.getEncoder().encodeToString(
                    (getProfile().getUsername() + ":" + getProfile().getPassword() + ":" + getProfile().getDomain())
                            .getBytes());
        } else {
            log.error(
                    "unable to create client: empty token or profile not present or profile passport enabled but not logged in");
            return null;
        }
    }

    /**
     * creates a CAM Namespace for the authentication
     *
     * @return
     */
    public String getBasicAuth() {
        if ((getProfile() != null) && getProfile().getAuthType().equals(AuthType.BASIC)) {
            // try to generate a CAMNameSpace token
            return Base64.getEncoder()
                    .encodeToString((getProfile().getUsername() + ":" + getProfile().getPassword()).getBytes());
        } else {
            log.error(
                    "unable to create client: empty token or profile not present or profile passport enabled but not logged in");
            return null;
        }
    }

    /**
     * decodes an HTTP Response into a human read-able string
     *
     * @param response
     * @return
     */
    @Deprecated
    protected String decodeResponse(final HttpResponse response) {
        return httpUtils.decodeResponse(response);
    }

    @Override
    public void abort() {

    }

    public void setCookie(final Cookie cookie) {
        getCookieStore().clear();
        getCookieStore().addCookie(cookie);
    }
}
