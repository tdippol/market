package com.axiante.tm1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.utility.ConnectorUtilities;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.junit.Test;

public class ConnectorUtilitiesTest {
//WWW-Authenticate: CAMPassport http://tmonetest.mil.esselunga.net/ibmcognos/cgi-bin/cognosisapi.dll, CAMNamespace :: CAMPassport http://tmonetest.mil.esselunga.net/ibmcognos/cgi-bin/cognosisapi.dll, CAMNamespace
	final String authHeader = "WWW-Authenticate: CAMPassport http://tmonetest.mil.esselunga.net/ibmcognos/cgi-bin/cognosisapi.dll, CAMNamespace";
	final String authValue = "CAMPassport http://tmonetest.mil.esselunga.net/ibmcognos/cgi-bin/cognosisapi.dll, CAMNamespace";
	
	@Test
	public void testCanAuthenticateElsewhereReturnsString() {
		HttpResponse response = mock(HttpResponse.class);
		Header header = mock(Header.class);
		Header[] headers = new Header[] {header};
		when(response.getHeaders(TunnelConstants.AUTH_HEADER)).thenReturn(headers);
		when(header.getName()).thenReturn(authHeader);
		when(header.getValue()).thenReturn(authValue);

		ConnectorUtilities utilities = new ConnectorUtilities();
		String res = utilities.canAuthenticateElseWhere(response, TunnelConstants.AUTH_HEADER);
		assertThat(res, equalTo(authValue));
	}
	
	@Test
	public void testCanAuthenticateElsewhereReturnsNull() {
		HttpResponse response = mock(HttpResponse.class);
		Header header = mock(Header.class);
		Header[] headers = new Header[] {header};
		when(response.getHeaders(TunnelConstants.AUTH_HEADER)).thenReturn(headers);
		when(header.getName()).thenReturn(authHeader);
		when(header.getValue()).thenReturn("SOMETHINGELSE http://tmonetest.mil.esselunga.net/ibmcognos/cgi-bin/cognosisapi.dll, CAMNamespace");

		ConnectorUtilities utilities = new ConnectorUtilities();
		String res = utilities.canAuthenticateElseWhere(response, TunnelConstants.AUTH_HEADER);
		assertThat(res, equalTo(null));
	}
	@Test
	public void testExtractLoginServerReturnsString() {
		ConnectorUtilities utilities = new ConnectorUtilities();
		String res = utilities.extractLoginServer(authValue);
		assertThat(res, equalTo("http://tmonetest.mil.esselunga.net/ibmcognos/cgi-bin/cognosisapi.dll"));
		
	}
}
