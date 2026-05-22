package com.axiante.tm1.auth;

import com.axiante.connection.ConnectionProfile;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TunnelAuthenticator extends Authenticator {
	@Getter
	private ConnectionProfile profile;

	public TunnelAuthenticator(ConnectionProfile profile) {
		super();
		this.profile = profile;
	}
    public PasswordAuthentication getPasswordAuthentication() {
    	log.debug("authentication scheme : " +getRequestingScheme());
    	if ( getScheme().toLowerCase().equals("negotiate")) {
    		log.debug("logging in with domain user");
    		return (new PasswordAuthentication(getProfile().getDomain() + "\\"+ getProfile().getUsername(), getProfile().getPassword().toCharArray()));
    	} else { 
    		log.debug("logging in without domain");
    		return (new PasswordAuthentication(getProfile().getUsername(), getProfile().getPassword().toCharArray()));    				
    	}
        
    }
// needed for unit testing
    protected String getScheme() {
    	return getRequestingScheme();
    }
}
