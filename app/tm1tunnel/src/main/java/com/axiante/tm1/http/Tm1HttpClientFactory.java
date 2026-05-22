package com.axiante.tm1.http;

import com.axiante.tm1.TunnelConstants;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

@Slf4j
public class Tm1HttpClientFactory {
	@Getter
	private String camPassport;
	@Getter
	private final Interceptor interceptor = new Interceptor();
	@Getter
	private boolean useCredentials = false;
	@Getter
	private boolean usePassport = false;
	@Getter
	private boolean useBasicAuth = false;
	@Getter
	private String camNameSpace;
	@Getter
	private String basicNameSpace;
	@Getter
	private boolean validateSSL = false;
	@Getter
	private int timeout = -1;
	@Getter
	private int connectionRequestTimeout = -1;
	@Getter
	private int socketTimeout = -1;
	@Getter
	private final CookieStore cookieStore = new BasicCookieStore();

	public Tm1HttpClientFactory withToken(@NonNull final String token) throws ClientProtocolException, IOException {
		log.debug("using CAMPassport Authorization");
		useCredentials = false;
		useBasicAuth = false;
		usePassport = true;
		camPassport = token;
		if (!camPassport.startsWith("CAMPassport ")) {
			log.debug("adding specs to CAM token");
			camPassport = "CAMPassport " + camPassport;
		}

		return this;
	}

	public Tm1HttpClientFactory withSslVerify(final boolean verify) {
		validateSSL = verify;
		return this;
	}

	public Tm1HttpClientFactory withCredentials(@NonNull final String credentials) {
		log.debug("using CAMNamespace Authorization");
		camNameSpace = "CAMNamespace " + credentials;
		useCredentials = true;
		usePassport = false;
		camPassport = null;
		useBasicAuth = false;

		return this;
	}

	public Tm1HttpClientFactory withBasicAuth(@NonNull final String credentials) {
		log.debug("using CAMNamespace Authorization");
		basicNameSpace = "Basic " + credentials;
		useCredentials = false;
		usePassport = false;
		camPassport = null;
		useBasicAuth = true;

		return this;
	}

	public Tm1HttpClientFactory withCredentials(@NonNull final String username, @NonNull final String password,
			final String namespace) {
		return this.withCredentials(encodeCredentials(username, password, namespace));
	}

	public Tm1HttpClientFactory withBasicAuth(@NonNull final String username, @NonNull final String password) {
		return this.withBasicAuth(encodeCredentials(username, password, null));
	}

	public String encodeCredentials(@NonNull final String username, @NonNull final String password,
			final String namespace) {
		final StringBuffer buf = new StringBuffer(username).append(":").append(password);
		if (namespace != null) {
			buf.append(":").append(namespace);
		}
		return Base64.getEncoder().encodeToString(buf.toString().getBytes());
	}

	public Tm1HttpClientFactory withTimeout(final double i) {
		if (i > -1) {
			timeout = (int) (i * 1000);
		}
		return this;
	}

	public Tm1HttpClientFactory withConnectionRequestTimeout(final double i) {
		if (i > -1) {
			connectionRequestTimeout = (int) (i * 1000);
		}
		return this;
	}

	public Tm1HttpClientFactory withSocketTimeout(final double i) {
		if (i > -1) {
			socketTimeout = (int) (i * 1000);
		}
		return this;
	}

	@SuppressWarnings("resource")
	public CloseableHttpClient create() {
		CloseableHttpClient client = null;
		if (validateSSL) {
			client = HttpClientBuilder.create().addInterceptorFirst(getInterceptor()).setDefaultCookieStore(cookieStore)
					.build();
		} else {
			try {
				final RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
				if (timeout > -1) {
					requestConfigBuilder.setConnectTimeout(Math.round(timeout));
				}
				if (socketTimeout > -1) {
					requestConfigBuilder.setSocketTimeout(socketTimeout);
				}
				if (connectionRequestTimeout > -1) {
					requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
				}

				client = HttpClients.custom().setHostnameVerifier(getNoOpVerifier())
						.addInterceptorFirst(getInterceptor())
						.setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, getNoOpTrustStrategy()).build())
						.setDefaultRequestConfig(requestConfigBuilder.build()).setDefaultCookieStore(cookieStore)
						.build();
			} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
				log.error("error creating ssl context", e);

				log.warn("falling back to simpler auth strategy, some certificates may be bounced anyways", e);
				client = HttpClients.custom().setHostnameVerifier(new AllowAllHostnameVerifier()).build();

			}

		}

		return client;
	}

	/**
	 * this interceptor tries to create a request that does not get a 401 response.
	 * If the cookie is present then it doesn't do anything, as the cookie rules. On
	 * the other hand, if the cookie is not present, it tries to add authentication
	 * headers based on the configuration of this factory.
	 * 
	 * @author marco
	 *
	 */
	class Interceptor implements HttpRequestInterceptor {
		@Override
		public void process(final HttpRequest request, final HttpContext context) {
			log.debug("processing interceptor " + this);
			if (!(cookieStore.getCookies().stream().filter(c -> c.getName().equals(TunnelConstants.TM1CONNECTIONCOOKIE))
					.findAny().orElse(null) != null)) {
				// no cookie set
				log.debug("no session cookie set");
				if (!request.containsHeader("Authorization")) {
					if (isUsePassport()) {
						log.debug("trying to add cam passport");
						request.addHeader("Authorization", getCamPassport());
					} else if (isUseCredentials()) {
						// use the credentials
						log.debug("trying to add cam namespace");
						request.addHeader("Authorization", getCamNameSpace());
					} else if (isUseBasicAuth()) {
						log.debug("trying to add basic auth");
						request.addHeader("Authorization", getBasicNameSpace());
					}
					if(log.isDebugEnabled()){
						if (request.getHeaders("Authorization") != null && request.getHeaders("Authorization").length > 0 ){
							try {
								log.debug("added the following Authorization headers " + Arrays.stream(request.getHeaders("Authorization")).filter(Objects::nonNull).map(s -> (s.getName() + "::" + s.getValue())).collect(Collectors.joining(",")));
							} catch (Exception e){
								log.debug("some Authorization headers are messed up");
							}
						} else {
							log.debug("no Authorization header has been added");
						}
					}
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("processing request ");
				if (request != null && request.getAllHeaders() != null) {
					for (final Header header : request.getAllHeaders()) {
						log.debug("header class [" + header.getClass().getName() + "] -> " + header.getName() + "="
								+ header.getValue());
					}
				} else {
					if (request == null) {
						log.debug("null request");
					} else {
						log.debug("no header found in request");
					}
				}
			}
		}
	}

	protected X509HostnameVerifier getNoOpVerifier() {
		return new X509HostnameVerifier() {

			@Override
			public boolean verify(final String arg0, final SSLSession arg1) {
				log.debug("verify String, SSLSession ... returning true");
				return true;
			}

			@Override
			public void verify(final String host, final String[] cns, final String[] subjectAlts) throws SSLException {
				try {
					log.debug(
							String.format("verify on \nhost: %s\ncns: %s\nsubjectAlts %s\n ... and doing nothing",
									(host != null ? host : "null"),
									(cns != null ? Arrays.stream(cns).filter(Objects::nonNull).collect(Collectors.joining(";")) : "null"),
									(subjectAlts != null ? Arrays.stream(subjectAlts).filter(Objects::nonNull).collect(Collectors.joining(";")) : "null")
							));
				} catch (Exception ignored){

				}
			}

			@Override
			public void verify(final String host, final X509Certificate cert) throws SSLException {
				log.debug("verification for host " + host + " with X509Certificate");
			}

			@Override
			public void verify(final String host, final SSLSocket ssl) throws IOException {
				log.debug("verification for host " + host + " with SSLSocket");
			}
		};
	}

	protected TrustStrategy getNoOpTrustStrategy() {
		return (chain, authType) -> {
			log.debug("trust strategy applied: doing nothing");
			return true;
		};
	}

}
