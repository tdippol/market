package com.axiante.utility;

import com.axiante.connection.ConnectionProfile;
import com.axiante.tm1.TunnelConstants;
import com.axiante.tm1.auth.TunnelAuthenticator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.cookie.Cookie;

@Slf4j
public class ConnectorUtilities {
    public String extractLoginServer(final String challengeResponse) {
        return challengeResponse.split(", ")[0].split(" ")[1];
    }

    /**
     * returns the token id if logged in
     * 
     * @param server
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String ntlmLogin(final String server, final ConnectionProfile profile) throws ClientProtocolException, IOException {
        Authenticator.setDefault(new TunnelAuthenticator(profile));
        final String path = server + TunnelConstants.AUTH_ACTION;
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        final URL url = new URL(path);
        final InputStream ins = url.openConnection().getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ins));) {
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.contains("var passport =")) {
                    final String data[] = str.split(" ");
                    return data[4];// .replaceAll("\"", "");
                }
            }
            return null;
        }
    }

    public String canAuthenticateElseWhere(final HttpResponse response, final String authHeader) {
        final Header[] headers = response.getHeaders(authHeader);
        for (final Header header : headers) {
            log.debug("checking \n" + header + " :: " + header.getValue());
            if (header.getValue().contains("CAMPassport")) {
                return header.getValue();
            }
        }
        return null;
    }


    /**
     * returns the token id if logged in
     * 
     * @param server
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Cookie getCookieFromNtlmLogin(final String server, final ConnectionProfile profile) throws ClientProtocolException, IOException {
        log.debug(String.format("trying to extract TM1 session cookie for connection profile %s on server %s", profile.getName(), server));
        Authenticator.setDefault(new TunnelAuthenticator(profile));
        final String path = server + TunnelConstants.AUTH_ACTION;
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        final URL url = new URL(path);
        log.debug(String.format("opening connection to %s", path));
        final URLConnection connection = url.openConnection();
        if (connection != null ) {
            return getCookie(connection);
        } else  {
            log.debug("connection not established, quitting");
            return null;
        }
    }


    private Cookie getCookie(final URLConnection conn) {
        log.debug("extracting cookie from url connection");
        final Map<String, List<String>> headerFields = conn.getHeaderFields();
        final Set<String> headerFieldsSet = headerFields.keySet();
        final Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
        final List<HttpCookie> cookies = new ArrayList<>();
        while (hearerFieldsIter.hasNext()) {
            final String headerFieldKey = hearerFieldsIter.next();
            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                log.debug(String.format("found cookie for %s", headerFieldKey));
                headerFields.get(headerFieldKey).forEach(
                        h-> cookies.addAll(HttpCookie.parse(h))
                        );
            }
        }
        return convert(cookies.stream().filter(c->c.getName().toLowerCase().contains("tm1")).findAny().orElse(null));
    }


    private Cookie convert(final HttpCookie cookie) {
        if (cookie == null ) {
            log.debug("nothing to convert");
            return null;
        }
        log.debug("converting http cookie for TM1 session");
        final Cookie c = new Cookie() {
            private final HttpCookie _cookie = cookie;
            @Override
            public boolean isSecure() {
                return _cookie.getSecure();
            }

            @Override
            public boolean isPersistent() {
                return false;
            }

            @Override
            public boolean isExpired(final Date date) {
                return _cookie.hasExpired();
            }

            @Override
            public int getVersion() {
                return _cookie.getVersion();
            }

            @Override
            public String getValue() {
                return _cookie.getValue();
            }

            @Override
            public int[] getPorts() {
            	if (cookie.getPortlist() != null ) {
            		String[] portString = cookie.getPortlist().split(",");
            		if ( portString.length > 0 ) {
            			try {
            				int[] ports = new int[portString.length];
            				for(int i = 0; i < portString.length; ++i) {
            					ports[i] = new Integer(portString[i]).intValue();
            				}
            				return ports;
            			} catch (Exception e) {
            				log.error("error converting port list " + cookie.getPortlist(), e);
            			}
            		}
            	}
                return null;
            }

            @Override
            public String getPath() {
                return _cookie.getPath();
            }

            @Override
            public String getName() {
                return _cookie.getName();
            }

            @Override
            public Date getExpiryDate() {
            	if ( cookie.getMaxAge() > -1 ) {
            		return new Date(cookie.getMaxAge()*1000);
            	}
                return null;
            }

            @Override
            public String getDomain() {
                return _cookie.getDomain();
            }

            @Override
            public String getCommentURL() {
                return _cookie.getCommentURL();
            }

            @Override
            public String getComment() {
                return _cookie.getComment();
            }
        };

        return c;
    }
}
