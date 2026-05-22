package com.axiante.connection;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

@Data
@Slf4j
public class ConnectionData {
	HttpRequestBase request = null;
	CloseableHttpResponse response=null;
	
	public void close() {
		
		if ( this.response != null ) {
			if ( !this.request.isAborted() ) {
				EntityUtils.consumeQuietly(this.response.getEntity());
			}
			try {
				this.response.close();
			} catch (Exception e) {
				
			}
		}
		this.response = null;
		if ( this.request != null)
			this.request.releaseConnection();
		this.request = null;
	}
	
	public void abort() {
		if ( this.request != null && !this.request.isAborted() ) {
			try {
				this.request.abort();
			} catch (Exception e ) {
				log.error("error aborting request", e);
			}
		} else { 
			if ( this.request.isAborted() ) {
				log.warn("request already aborted outside this scope");
			} else if ( this.request == null ) {
				log.error("request has not been committed yet");
			}
		}
	}
	
}
