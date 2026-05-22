package com.axiante;

import com.axiante.connection.AbstractConnection;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.URIEncoder;
import com.axiante.tm1.Tm1Interface;
import com.axiante.tm1.TunnelConstants;
import com.axiante.tm1.json.objects.Threads;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.ws.rs.HttpMethod;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
@Slf4j
@Dependent
public class Tm1Tunnel extends AbstractConnection implements Tm1Interface {
	final int MAJOR = 0;
	final int MINOR = 1;

	public Tm1Tunnel(final ConnectionProfile profile) {
		super(profile);
	}

	/**
	 * If there is an exception (communication error) or the server responds with an
	 * empty response the function is guaranteed to return null. returns the version
	 * of TM1 or null if there is any kind of issue
	 */

	private String getStringVersion(final Double connetionTimeout, final Integer socketTimeout,
			final Integer connectionRequestTimeout, final Cookie cookie) {
		String version = null;
		try (CloseableHttpResponse response = getVersion(connetionTimeout, socketTimeout, connectionRequestTimeout,
				cookie);) {
			version = httpUtils.decodeResponse(response);
			if (version.trim().length() == 0) {
				version = null;
			}
		} catch (final ClientProtocolException e) {
			log.error("Protocol exception trying to query TM1 version", e);
		} catch (final IOException e) {
			log.error("IO exception trying to query TM1 version", e);
		}

		return version;
	}

	private int getVersion(final String version, final int PART) {
		int ret = 0;
		if (version != null) {
			final String parts[] = version.split("[.]"); // have to use a regex as "." is a reserved word
			if ((parts.length > 0) && (parts.length > PART)) {
				try {
					ret = Integer.parseInt(parts[PART]);
				} catch (final NumberFormatException e) {
					log.error("error parsing version " + version, e);
				}
			}
		}
		return ret;
	}

	@Override
	public CloseableHttpResponse getVersion(final Double connetionTimeout, final Integer socketTimeout,
			final Integer connectionRequestTimeout, final Cookie cookie) throws ClientProtocolException, IOException {
		if (cookie != null) {
			setCookie(cookie);
		}
		this.getClient(HttpMethod.GET, connetionTimeout, socketTimeout, connectionRequestTimeout);
		String uri = serviceRoot;
		uri += TunnelConstants.VERSION;
		log.debug("actual command executed : " + uri);
		HttpGet get = new HttpGet(uri);
		CloseableHttpResponse response = null;
		get = (HttpGet) this.setHeaders(get);
		if (client == null) {
			log.error("error creating client");
			return null;
		}
		response = client.execute(get);
		final int code = response.getStatusLine().getStatusCode();
		if ((code != 200) && (code != 201)) {
			log.error("\nerror submitting request\nserver response code:" + code + "\nserver response string:"
					+ httpUtils.decodeResponse(response));
		}
		return response;
	}

	/**
	 * helper method that executes an MDX and returns an HTTP response
	 * 
	 * @param header
	 * @param MDX
	 * @param token
	 * @param retrieveIdOnly
	 * @return
	 * @throws IOException
	 */
	@Override
	public CloseableHttpResponse executeMDXasHttpResponse(final String header, final String MDX, final String token,
			final boolean retrieveIdOnly, final Double connectionTimeout, final Integer socketTimeout,
			final Integer connectionRequestTimeout, final Cookie cookie) throws ClientProtocolException, IOException {
		return executeMDXasHttpResponse(header, MDX, retrieveIdOnly, connectionTimeout, socketTimeout,
				connectionRequestTimeout, cookie);
	}

	@Override
	public CloseableHttpResponse executeMDXasHttpResponse(final String header, final String MDX,
			final boolean retrieveIdOnly, final Double connectionTimeout, final Integer socketTimeout,
			final Integer connectionRequestTimeout, final Cookie cookie) throws ClientProtocolException, IOException {
		this.getClient(HttpMethod.POST, connectionTimeout, socketTimeout, connectionRequestTimeout);
		setCookie(cookie);
		String uri = serviceRoot;
		if (!retrieveIdOnly) {
			uri += TunnelConstants.ExecuteMDX + "$expand=";
		}
		uri += header;
		log.debug("actual command executed : " + uri);
		HttpPost post = new HttpPost(uri);

		CloseableHttpResponse response = null;
		post = (HttpPost) this.setHeaders(post);
		if (MDX != null) {
			log.debug("query: \n" + "{" + JsonUtils.getMapper().writeValueAsString("MDX") + ":"
					+ JsonUtils.getMapper().writeValueAsString(MDX) + "}");
			final StringEntity entity = new StringEntity(

					"{" + JsonUtils.getMapper().writeValueAsString("MDX") + ":"
							+ JsonUtils.getMapper().writeValueAsString(MDX) + "}",
					ContentType.APPLICATION_JSON);
			post.setEntity(entity);
		}
		response = client.execute(post);
		final int code = response.getStatusLine().getStatusCode();
		if ((code != 200) && (code != 201)) {
			log.error("\nerror submitting request\nserver response code:" + code + "\nserver response string:"
					+ httpUtils.decodeResponse(response));
			post.abort();
			post.releaseConnection();

		}
		return response;

	}

	/**
	 * Helper method that executes an MDX and returns a string as response
	 * 
	 * @param header
	 * @param MDX
	 * @param token
	 * @return
	 */
	@Override
	public String executeMDX(final String header, final String MDX, final String token, final Double connectionTimeout,
			final Integer socketTimeout, final Integer connectionRequestTimeout, final Cookie cookie) {
		final StringBuilder result = new StringBuilder(TunnelConstants.UNAUTHORIZED);
		Instant requestRead = null;
		Instant requestExecuted = null;
		if (cookie == null) {
			log.warn("attempting to connect without a cookie; this will create a new TM1 session");
		}
		try (CloseableHttpResponse response = executeMDXasHttpResponse(header, MDX, token, false, connectionTimeout,
				socketTimeout, connectionRequestTimeout, cookie);) {
			requestExecuted = Instant.now();
			if (response.getStatusLine().getStatusCode() == 201) {
				result.delete(0, result.length());
				result.append(EntityUtils.toString(response.getEntity()));
			} else if (response.getStatusLine().getStatusCode() == 200) {
				final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line;
				result.delete(0, result.length());
				while ((line = rd.readLine()) != null) {
					result.append(line).append("\n");
				}
				requestRead = Instant.now();
			} else {
				response.close();
				return TunnelConstants.UNAUTHORIZED;
			}
		} catch (final Exception e) {
			log.error("Error getting service", e);
		}
		if ((requestExecuted != null) && (requestRead != null)) {
			log.debug("time to read the response   " + Duration.between(requestExecuted, requestRead).toMillis()
					+ " milliseconds");
		}
		return result.toString().trim();
	}

	/**
	 * Helper method that deletes a cellset by its ID
	 * 
	 * @param collection
	 * @param id
	 * @param token
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Override
	public String deleteById(final String collection, final String id, final String token, final Double timeout,
			final Cookie cookie) throws URISyntaxException, ClientProtocolException, IOException {
		final HttpDelete delete = new HttpDelete();
		final String deleteCommand = collection + "('" + id + "')";
		final URI deleteURI = new URI(serviceRoot + deleteCommand);
		this.getClient(HttpMethod.DELETE, timeout);
		delete.setURI(deleteURI);
		setCookie(cookie);

		try (CloseableHttpResponse response = client.execute(delete);) {
			final int code = response.getStatusLine().getStatusCode();
			EntityUtils.consumeQuietly(response.getEntity());

			if (code == 401) {
				return TunnelConstants.UNAUTHORIZED;
			} else if (code == 200) {
				return TunnelConstants.SUCCESS;
			} else {
				return "Unhandled status " + response.getStatusLine().getStatusCode() + " : "
						+ response.getStatusLine().getReasonPhrase();

			}
		}
	}

	/**
	 * performs the update of a list of cell in a given cube give the tuple that
	 * identifies the cell and the new value
	 * 
	 * @param cube     the name of the cube to update
	 * @param passport the token for the SSO (if applicable) or
	 * 
	 *                 <pre>
	 *                 null
	 *                 </pre>
	 * 
	 * @param tuple    the list of dimensions that identify the cell (rows +
	 *                 columns)
	 * @param value    the new value for the cell
	 * @return
	 * @throws NullPointerException if the name of the cube is null
	 */
	@Override
	public CloseableHttpResponse cellPut(@NonNull final String cube, final List<String> tuple, final String value,
			final Double connectionTimeout, final Integer socketTimeout, final Integer connectionRequestTimeout,
			@NonNull final Cookie cookie) {
		setCookie(cookie);
		// if here we have a TM1 token
		this.getClient(HttpMethod.POST, connectionTimeout, socketTimeout, connectionRequestTimeout);
		String uri = serviceRoot + "Cubes('" + cube + "')/tm1.Update";
		CloseableHttpResponse response = null;
		try {
			uri = URIEncoder.encode(uri);
			HttpPost post = new HttpPost(uri);
			post = (HttpPost) this.setHeaders(post);
			final StringBuffer tupleString = new StringBuffer("{\"Cells\":[{\"Tuple@odata.bind\":");
			tupleString.append(JsonUtils.getMapper().writeValueAsString(tuple));
			tupleString.append("}],").append(JsonUtils.getMapper().writeValueAsString("Value")).append(":")
					.append(JsonUtils.getMapper().writeValueAsString(StringEscapeUtils.unescapeJava(value)))
					.append("}");

			final StringEntity entity = new StringEntity(tupleString.toString(), ContentType.APPLICATION_JSON);
			post.setEntity(entity);
			response = client.execute(post);
			final int code = response.getStatusLine().getStatusCode();
			if ((code != 200) && (code != 201) && (code != 204)) {
				log.error("\nerror submitting request\nserver response code:" + code + "\nserver response string:"
						+ httpUtils.decodeResponse(response));
			}
			return response;
		} catch (final Exception e) {
			log.error("Error getting service", e);
		}
		return null;
	}

	/**
	 * Executes a remote process identified by
	 * 
	 * <pre>
	 * processName
	 * </pre>
	 * 
	 * given the list of parameters
	 * 
	 * <pre>
	 * parameters
	 * </pre>
	 * 
	 * @param processName the name of the remote process to launch
	 * @param parameters  the list of the parameters in the form of key=parameter
	 *                    name, value=parameter value; the list can be empty or null
	 * @param token       the token to use for the SSO (if applicable) or null;
	 * @return the result of the remote call
	 * @throws NullPointerException if the name of the process is null
	 */
	@Override
	@Deprecated
	public CloseableHttpResponse executeProcess(@NonNull final String processName, final Map<String, String> parameters,
			final String token, final Double connectionTimeout, final Integer socketTimeout,
			final Integer connectionRequestTimeout, final Cookie cookie) {
		return executeProcess(processName, parameters, connectionTimeout, socketTimeout, connectionRequestTimeout,
				cookie);
	}

	@Override
	public CloseableHttpResponse executeProcess(@NonNull final String processName, final Map<String, String> parameters,
			final Double connectionTimeout, final Integer socketTimeout, final Integer connectionRequestTimeout,
			final Cookie cookie) {
		Boolean withReturn = Boolean.FALSE;
		final String version = getStringVersion(connectionTimeout, socketTimeout, connectionRequestTimeout, cookie);
		if ((version != null)) {
			int number = getVersion(version, MAJOR);
			if (number > 10) {
				number = getVersion(version, MINOR);
				if (number > 2) {
					// at least version 11.3
					withReturn = Boolean.TRUE;
				}
			}
		}

		setCookie(cookie);

		this.getClient(HttpMethod.POST, connectionTimeout, socketTimeout, connectionRequestTimeout);
		String uri = serviceRoot;
		try {
			uri += TunnelConstants.executeProcess(processName, withReturn);
			uri = URIEncoder.encode(uri);
		} catch (final Exception e) {
			log.debug("error encoding uri : " + uri);
		}
		log.debug("actual command executed : " + uri);
		HttpPost post = new HttpPost(uri);
		CloseableHttpResponse response = null;
		try {
			post = (HttpPost) this.setHeaders(post);
			if ((parameters != null) && (parameters.size() > 0)) {
				StringBuilder jsonParams = new StringBuilder("{\"Parameters\" :[");
				for (final String key : parameters.keySet()) {
					jsonParams.append("{\"Name\" : \"").append(key).append("\", \"Value\" : \"")
							.append(parameters.get(key)).append("\"},");
				}
				jsonParams = new StringBuilder(jsonParams.substring(0, jsonParams.length() - 1));
				jsonParams.append("]}");
				final StringEntity entity = new StringEntity(jsonParams.toString(), ContentType.APPLICATION_JSON);
				post.setEntity(entity);
			}
			response = client.execute(post);
			final int code = response.getStatusLine().getStatusCode();
			if ((code != 200) && (code != 201) && (code != 204)) {
				log.error("\nerror submitting request\nserver response code:" + code + "\nserver response string:"
						+ httpUtils.decodeResponse(response));
			} else {
				if (withReturn) {
					// the response is always 201, need to parse the response and eventually close
					// it and return a dummy response;
					Tm1TunnelDummyHttpResponse errorResponse = null;
					Map<String, String> status = null;
					try {
						status = JsonUtils.parseData(httpUtils.decodeResponse(response));
					} catch (final Exception e) {
						// errore di parse, mi e' arrivata una risposta che non mi aspettavo
						log.error("Error parsing ExecuteWithReturn response", e);
					} finally {
						if (status != null) {
							final String result = status.get("ProcessExecuteStatusCode");
							if (!"CompletedSuccessfully".equals(result)) {
								errorResponse = new Tm1TunnelDummyHttpResponse();
								errorResponse.setStatusLine(null, HttpStatus.SC_METHOD_FAILURE,
										"Error executing process " + processName + ": " + result);
								// errorResponse.setStatusCode(HttpStatus.SC_METHOD_FAILURE);
								// errorResponse.setReasonPhrase("Error executing process " + processName + ": "
								// + result);
							}
						} else {
							errorResponse = new Tm1TunnelDummyHttpResponse();
							errorResponse.setStatusLine(null, HttpStatus.SC_INTERNAL_SERVER_ERROR,
									"Error reading execute process status " + serviceRoot);
							// errorResponse.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
							// errorResponse.setReasonPhrase("Error reading execute process status " +
							// serviceRoot);
						}
					}
					if (errorResponse != null) {
						return errorResponse;
					}
				}
			}

			return response;
		} catch (final SocketTimeoutException e) {
			log.error("Could not estabilish a connection to the server " + serviceRoot, e);
		} catch (final ConnectTimeoutException e) {
			log.warn("timeout waiting for a response from the server " + serviceRoot, e);
			final Tm1TunnelDummyHttpResponse timeoutResponse = new Tm1TunnelDummyHttpResponse();
			timeoutResponse.setStatusCode(HttpStatus.SC_REQUEST_TIMEOUT);
			timeoutResponse.setReasonPhrase("Timeout waiting for a response from " + serviceRoot);
			return timeoutResponse;
		} catch (final Exception e) {
			log.error("Error getting service", e);
		}

		return null;
	}

	public String verify(final String passport, final Cookie cookie, @NonNull final ConnectionProfile profile,
			final Double connetionTimeout, final Integer socketTimeout, final Integer connectionRequestTimeout)
			throws ClientProtocolException, IOException {
		final HttpUtils httpUtils = new HttpUtils();
		String check = null;
		setCookie(cookie);

		try (CloseableHttpResponse response = getVersion(connetionTimeout, socketTimeout, connectionRequestTimeout,
				cookie)) {
			if (httpUtils.checkResponse(response)) {
				check = httpUtils.extractCookie(response);
			} else {
				log.warn("unable to check cookie validity: " + response.getStatusLine().getReasonPhrase());
			}
			if (response != null) {
				EntityUtils.consume(response.getEntity());
			}
		}
		return check;
	}

	@Override
	public void cancelLongOperation(@NonNull final HttpPost request, final Header[] headers, final String token,
			final Double connectionTimeout, final Integer socketTimeout, final Integer connectionRequestTimeout) {
		if (!request.isAborted()) {
			request.abort();
		}
		request.releaseConnection();

		try {
			this.cancelLongOperation(headers, token, connectionTimeout, socketTimeout, connectionRequestTimeout);
		} catch (final UnsupportedEncodingException e) {
			log.error("error canceling operation", e);
		}
	}

	@Override
	public void cancelLongOperation(final Header[] headers, final String token, final Double connectionTimeout,
			final Integer socketTimeout, final Integer connectionRequestTimeout) throws UnsupportedEncodingException {
		String uriString = URLEncoder.encode(
				TunnelConstants.THREADS
						+ "?$expand=Session&$filter=Function eq 'POST /api/v1/ExecuteMDX' and State ne 'Idle'",
				StandardCharsets.UTF_8.toString());
		final HttpGet request = new HttpGet(getServiceRoot() + uriString);
		if (headers != null) {
			Arrays.asList(headers).forEach(h -> request.addHeader(h));
		}
		try {
			final HttpUtils utils = new HttpUtils();
			this.getClient(null, connectionTimeout, socketTimeout, connectionRequestTimeout);
			CloseableHttpResponse response = this.getClient().execute(request);
			if (!utils.checkResponse(response)) {
				log.error("Error getting list of active threads : [" + response.getStatusLine().getStatusCode() + "]"
						+ response.getStatusLine().getReasonPhrase());
				return;
			}
			final Threads threads = JsonUtils.getMapper().readValue(response.getEntity().getContent(), Threads.class);
			EntityUtils.consumeQuietly(response.getEntity());
			response.close();
			request.releaseConnection();
			if ((threads != null) && (threads.getValue() != null) && (threads.getValue().length > 0)) {
				// kill threads
				uriString = TunnelConstants.THREADS + "(%d)/tm1.CancelOperation";
				HttpPost post = null;
				for (final com.axiante.tm1.json.objects.Thread t : Arrays.asList(threads.getValue())) {
					post = new HttpPost(getServiceRoot() + String.format(uriString, t.getID()));
					response = this.getClient().execute(post);
					if (utils.checkResponse(response)) {
						log.debug("thread " + t.getID() + " killed");
					} else {
						log.error("error killing process <process>: [" + response.getStatusLine().getStatusCode() + "] "
								+ response.getStatusLine().getReasonPhrase());
					}
					response.close();
					post.reset();
				}
				if (post != null) {
					post.releaseConnection();
				}
			}
		} catch (final IOException e) {
			log.error("error getting the list of threads", e);
		}
	}

	class Tm1TunnelDummyHttpResponse implements CloseableHttpResponse {
		private StatusLine statusLine;
		@Getter
		private int statusCode;
		@Getter
		private String reasonPhrase;

		@Override
		public StatusLine getStatusLine() {
			return statusLine;
		}

		@Override
		public void setStatusLine(final ProtocolVersion ver, final int code, final String reason) {
			statusLine = new StatusLine() {
				@Override
				public int getStatusCode() {
					return code;
				}

				@Override
				public String getReasonPhrase() {
					return reason;
				}

				@Override
				public ProtocolVersion getProtocolVersion() {
					return ver;
				}
			};
			statusCode = code;
			reasonPhrase = reason;
		}

		@Override
		public void setStatusLine(final ProtocolVersion ver, final int code) {
			statusLine = new StatusLine() {
				@Override
				public int getStatusCode() {
					return code;
				}

				@Override
				public String getReasonPhrase() {
					return "";
				}

				@Override
				public ProtocolVersion getProtocolVersion() {
					return ver;
				}
			};
			statusCode = code;
			reasonPhrase = "";
		}

		@Override
		public void setStatusLine(final StatusLine statusline) {
			statusLine = statusline;
		}

		@Override
		public void setStatusCode(final int code) throws IllegalStateException {
			statusCode = code;
		}

		@Override
		public void setReasonPhrase(final String reason) throws IllegalStateException {
			reasonPhrase = reason;
		}

		// dummy implementations
		@Override
		public void close() throws IOException {
		}

		@Override
		public void setParams(final HttpParams params) {
		}

		@Override
		public void setHeaders(final Header[] headers) {
		}

		@Override
		public void setHeader(final String name, final String value) {
		}

		@Override
		public void setHeader(final Header header) {
		}

		@Override
		public void removeHeaders(final String name) {
		}

		@Override
		public void removeHeader(final Header header) {
		}

		@Override
		public HeaderIterator headerIterator(final String name) {
			return null;
		}

		@Override
		public HeaderIterator headerIterator() {
			return null;
		}

		@Override
		public ProtocolVersion getProtocolVersion() {
			return null;
		}

		@Override
		public HttpParams getParams() {
			return null;
		}

		@Override
		public Header getLastHeader(final String name) {
			return null;
		}

		@Override
		public Header[] getHeaders(final String name) {
			return null;
		}

		@Override
		public Header getFirstHeader(final String name) {
			return null;
		}

		@Override
		public Header[] getAllHeaders() {
			return null;
		}

		@Override
		public boolean containsHeader(final String name) {
			return false;
		}

		@Override
		public void addHeader(final String name, final String value) {

		}

		@Override
		public void addHeader(final Header header) {

		}

		@Override
		public void setLocale(final Locale loc) {
		}

		@Override
		public void setEntity(final HttpEntity entity) {
		}

		@Override
		public Locale getLocale() {
			return null;
		}

		@Override
		public HttpEntity getEntity() {
			return null;
		}
	}

}
