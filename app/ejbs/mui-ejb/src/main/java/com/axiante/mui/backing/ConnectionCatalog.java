package com.axiante.mui.backing;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.axiante.connection.AuthType;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.persistence.entity.ConnectionSetupEntity;
import com.axiante.mui.persistence.service.MuiService;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ConnectionCatalog implements Serializable {
	private static final long serialVersionUID = -809342028310438046L;
	private static final Map<String, ConnectionProfile> catalog = Collections.synchronizedMap(new HashMap<String, ConnectionProfile>());

	@Inject
	@Getter
	private MuiService muiService;

	@PostConstruct
	public void init() {
		if ( !update() ) {
			log.error("Error retrieving connection configuration");
		}
	}

	public ConnectionProfile getProfile(@NonNull final String key) {
		return catalog.get(key);
	}

	public int getSize() {
		return catalog.size();
	}

	public String avaliableConfigurations() {
		if ( getSize() == 0 ) {
			return "empty";
		}
		return catalog.entrySet().stream().map(Entry::getValue).map(ConnectionProfile::getName).collect(Collectors.joining(","));
	}

	public List<ConnectionProfile> availableConfigurationList(){
		return catalog.entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
	}


	public boolean update() {
		// can update
		synchronized (this) {
			boolean result = false;
			try {
				final Map<String, ConnectionProfile> catalog = new HashMap<>();
				for(final ConnectionSetupEntity connectionSetupEntity:getMuiService().readConnectionSetups()){
					final ConnectionProfile connectionProfile = new ConnectionProfile();
					connectionProfile.setName(connectionSetupEntity.getConnectionName());
					connectionProfile.setUsername(connectionSetupEntity.getUsername());
					connectionProfile.setPassword(connectionSetupEntity.getPassword());
					connectionProfile.setDomain(connectionSetupEntity.getDomain());
					connectionProfile.setHost(connectionSetupEntity.getHost());
					connectionProfile.setPort(":"+connectionSetupEntity.getPort());
					connectionProfile.setPath(connectionSetupEntity.getPath());
					connectionProfile.setValidatessl(connectionSetupEntity.getValidateSsl());
					connectionProfile.setAuthType(AuthType.valueOf(connectionSetupEntity.getAuthType()));
					catalog.put(connectionProfile.getName(),connectionProfile);
				}
				ConnectionCatalog.catalog.clear();
				ConnectionCatalog.catalog.putAll(catalog);
				result = true;
			} catch (final Exception e) {
				log.error("Error reading connection configuration.", e);
			} finally {
			}
			return result;
		}
	}


}
