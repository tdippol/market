package com.axiante.tm1;

import com.axiante.connection.ConnectionInterface;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;

public interface Tm1Interface extends ConnectionInterface {
	String deleteById(String collection, String id, String token, Double timeout, Cookie cookie)
			throws URISyntaxException, ClientProtocolException, IOException;

	String executeMDX(final String header, final String MDX, final String token, Double timeout, Integer socketTimeout,
			Integer connectionRequestTimeout, Cookie cookie);

	CloseableHttpResponse executeMDXasHttpResponse(final String header, final String MDX, final String token,
			final boolean retrieveIdOnly, Double connectionTimeout, Integer socketTimeout,
			Integer connectionRequestTimeout, Cookie cookie) throws IOException;

	CloseableHttpResponse executeMDXasHttpResponse(final String header, final String MDX, final boolean retrieveIdOnly,
			Double connectionTimeout, Integer socketTimeout, Integer connectionRequestTimeout, Cookie cookie)
					throws IOException;

	CloseableHttpResponse cellPut(@NonNull final String cube, List<String> tuple, String value,
			Double connectionTimeout, Integer socketTimeout, Integer connectionRequestTimeout, Cookie cookie);

	CloseableHttpResponse executeProcess(@NonNull String processName, Map<String, String> parameters,
			Double connectionTimeout, Integer socketTimeout, Integer connectionRequestTimeout, Cookie cookie);

	@Deprecated
	CloseableHttpResponse executeProcess(@NonNull String processName, Map<String, String> parameters, String token,
			Double connectionTimeout, Integer socketTimeout, Integer connectionRequestTimeout, Cookie cookie);

	CloseableHttpResponse getVersion(Double timeout, Integer socketTimeout, Integer connectionRequestTimeout,
			final Cookie cookie) throws ClientProtocolException, IOException;

	void cancelLongOperation(@NonNull HttpPost request, Header[] headers, String token, Double connectionTimeout,
			Integer socketTimeout, Integer connectionRequestTimeout);

	void cancelLongOperation(Header[] headers, String token, Double connectionTimeout, Integer socketTimeout,
			Integer connectionRequestTimeout) throws UnsupportedEncodingException;

	void setCookie(Cookie cookie);
}
