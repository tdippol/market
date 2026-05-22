package com.axiante.connection;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import lombok.NonNull;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicHeader;

public interface ConnectionInterface extends Closeable, Serializable{

	void setProfile(@NonNull ConnectionProfile profile) ;
	CloseableHttpResponse executeRequestAsHttpResponse(String METHOD_NAME, String uriString, BasicHeader[] headers)throws URISyntaxException, IOException ;
	@Override void close() ;
	String getCAMNamespace() ;
	boolean closeSession(Cookie cookie);
	void abort();

	default Cookie openConnection(String camPassport) throws ClientProtocolException, IOException {
		return openConnection(null,null,null,camPassport);
	}
	Cookie openConnection(Double timeout, Integer socketTimeout, Integer connectionRequestTimeout,String camPassport) throws ClientProtocolException, IOException ;

	default String performLogin() throws ClientProtocolException, IOException {
		return performLogin(null, null, null);
	}
	String performLogin(Double timeout, Integer socketTimeout, Integer connectionRequestTimeout) throws ClientProtocolException, IOException ;

}
