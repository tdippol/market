package com.axiante.connection;

import java.io.Serializable;
import lombok.Data;

@Data
public class ConnectionProfile implements Serializable{
	String name;
	String username;
	String password;
	String domain;
	String host;
	String port;
	String path;
	boolean validatessl;
	AuthType authType;
	
	public String getValidationHost() {
		if (port != null && port.trim().length() > 1) {
			return host+port;
		} else { 
			return host;
		}
	}
}
