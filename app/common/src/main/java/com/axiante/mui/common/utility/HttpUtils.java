package com.axiante.mui.common.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;

@Slf4j
public class HttpUtils implements Serializable{
	public boolean checkResponse(final CloseableHttpResponse response) {
		return this.checkResponse(response, false);
	}

	public boolean checkResponse(final CloseableHttpResponse response, final boolean withMessage) {
		if (response == null) {
			if (withMessage) {
				log.warn("response is null, probably a timout exception");
			}
			return false;
		}
		final String status = "" + response.getStatusLine().getStatusCode();

		final boolean result = (status.length() == 3) && status.startsWith("2");
		if (withMessage && !result) {
			try {
				if ((response.getEntity() != null) && (response.getEntity().getContent() != null)) {
					String encoding = response.getEntity().getContentEncoding() == null ? "UTF-8"
							: response.getEntity().getContentEncoding().getValue();
					encoding = encoding == null ? "UTF-8" : encoding;
					final String message = IOUtils.toString(response.getEntity().getContent(), encoding);
					if ((message != null) && (message.trim().length() > 0)) {
						log.warn("error opening connection : " + message);
					} else {
						log.warn("error opening connection : " + response.getStatusLine().getReasonPhrase());
					}
				} else {
					log.warn("error opening connection : " + response.getStatusLine().getReasonPhrase());
				}
			} catch (IllegalStateException | IOException e) {
				log.warn("error opening connection : " + response.getStatusLine().getReasonPhrase(), e);
			}
		}
		return result;
	}

	public String extractCookie(@NonNull final HttpResponse response) {
		String cookie = null;
		if (response != null) {
			if (response.containsHeader("Set-Cookie")) {
				final Header header = response.getLastHeader("Set-Cookie");
				// TM1SessionId=OWm3DaBvJHzIg2LRzBurJg; Path=/api/; HttpOnly; Secure
				cookie = header.getValue();
			} else {
				log.debug("no cookie set by TM1");
			}
		}
		return cookie;
	}

	public Cookie extractCookie(@NonNull final CookieStore store) {
		return store.getCookies().stream().filter(c -> c.getName().equals("TM1SessionId")).findAny().orElse(null);
	}

	public String getHostName(@NonNull final String host) {
		try {
			return InetAddress.getByName(new URL(host).getHost()).getHostAddress();
		} catch (UnknownHostException | MalformedURLException e) {
			log.error("Error parsing hostname " + host, e);
		}
		return null;
	}

	public boolean isHostReachable(final String host) {
		try {
			final URL url = new URL(host);
			try (Socket s = new Socket()) {
				s.connect(new InetSocketAddress(url.getHost(),
						url.getPort() == -1 ? url.getDefaultPort() : url.getPort()));
				return true;
			} catch (final IOException ex) {
				// ignored
			}

		} catch (final Exception e) {
			log.warn("Error reaching host " + host, e);
			return false;
		}
		return false;
	}

	public String getResponseString(final CloseableHttpResponse response) {
		if ((response.getStatusLine().getStatusCode() != 200) && (response.getStatusLine().getStatusCode() != 201)) {
			String responseContent = null;
			try {
				responseContent = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name());
			} catch (ParseException | IOException e) {
				log.warn("Problems retrieving error content :" + e.getMessage(), e);
			} finally {
				if (responseContent != null) {
					log.error("Error " + response.getStatusLine().getStatusCode() + " : " + responseContent);
				} else {
					log.error("Error " + response.getStatusLine().getStatusCode());
					responseContent = response.getStatusLine().getReasonPhrase();
				}
			}
			return responseContent;
		}
		return null;

	}

	/**
	 * decodes an HTTP Response into a human read-able string
	 * 
	 * @param response
	 * @return
	 */
	public String decodeResponse(final HttpResponse response) {
		final StringBuffer buf = new StringBuffer();
		String line = "";
		try (BufferedReader rd= new BufferedReader(new InputStreamReader(response.getEntity().getContent(),StandardCharsets.UTF_8))){
			while ((line = rd.readLine()) != null) {
				buf.append(line).append("\n");
			}
			if (buf.length() > 0) {
				buf.delete(buf.length() - 1, buf.length());
			}
		} catch (IllegalStateException | IOException e) {
			log.error("error decoding response", e);
			return "";
		}
		return buf.toString();
	}

}
