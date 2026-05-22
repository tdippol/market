package com.axiante.mui.backing;

import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.MuiService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

@Singleton
@Slf4j
public class ApplicationProperties implements Serializable {
	private static final long serialVersionUID = 2414265686413651707L;

	@Inject
	@Getter
	transient private MuiService muiService;

	@Getter
	final private static HashMap<String, String> properties = new HashMap<>();

	public static final String TOKEN_SPAWN = "TOKEN_SPAWN";
	public static final String AUTH_SERVER = "AUTH_SERVER";
	public static final String MAX_CELLS = "MAX_CELLS";
	// the time to establish the connection with the remote host
	public static final String CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT";
	// the time waiting for data – after establishing the connection; maximum time
	// of inactivity between two data packets
	public static final String SOCKET_TIMEOUT = "SOCKET_TIMEOUT";
	// the time to wait for a connection from the connection manager/pool
	public static final String CONNECTION_MANAGER_TIMEOUT = "CONNECTION_MANAGER_TIMEOUT";
	public static final String TM1_OPERATION_TIMEOUT = "TM1_OPERATION_TIMEOUT";
	public static final String GRID_HEIGHT = "GRID_HEIGHT";
	public static final String ADMIN_MODE = "ADMIN_MODE";
	public static final String MANUAL_ADMIN_MODE = "MANUAL_ADMIN_MODE";
	public static final String ADMIN_MODE_ALL_DAYS = "ADMIN_MODE_ALL_DAYS";
	public static final String ADMIN_MODE_FROM_TIME = "ADMIN_MODE_FROM_TIME";
	public static final String ADMIN_MODE_TO_TIME = "ADMIN_MODE_TO_TIME";
	public static final String ADMIN_MODE_SCHEDULER_ACTIVE = "ADMIN_MODE_SCHEDULER_ACTIVE";
	public static final String ADMIN_MODE_SELECTED_DAYS = "ADMIN_MODE_SELECTED_DAYS";
	public static final String ADMIN_MODE_USER_MESSAGE = "ADMIN_MODE_USER_MESSAGE";
	public static final String DOCUMENT_DATA_PATH = "DOCUMENT_DATA_PATH";
	public static final String CUMULABILITA_PATH = "PATH_CUMULABILITA";
	public static final String STORED_PROCEDURE_TIMEOUT = "STORED_PROCEDURE_TIMEOUT";

	public static final String MAX_UPLOAD_RECORDS = "MAX_UPLOAD_RECORDS";
	// #4074
	public static final String TAB_STORES_MESSAGE = "TAB_STORES_MESSAGE";
	public static final String VALORE_PUNTO_MAX_VALUE = "VALORE_PUNTO_MAX_VALUE";
	
	public static final Double DEFAULT_CONNECTION_TIMEOUT = 5d;
	public static final Integer DEFAULT_MAX_CELLS = 0;
	public static final Integer DEFAULT_TOKEN_SPAWN = 600;
	public static final Integer DEFAULT_TM1_OPERATION_TIMEOUT = 30;
	public static final Integer DEFAULT_SOCKET_TIMEOUT = 5;
	public static final Integer DEFAULT_CONNECTION_MANAGER_TIMEOUT = 5;

	public static final Integer DEFAULT_GRID_HEIGHT = 65;

	public static final Boolean DEFAULT_MANUAL_ADMIN_MODE = Boolean.FALSE;
	public static final Boolean DEFAULT_ADMIN_MODE = Boolean.FALSE;
	public static final Boolean DEFAULT_ADMIN_MODE_ALL_DAYS = Boolean.TRUE;
	public static final String DEFAULT_ADMIN_MODE_FROM_TIME = "22:00";
	public static final String DEFAULT_ADMIN_MODE_TO_TIME = "06:00";
	public static final Boolean DEFAULT_ADMIN_MODE_SCHEDULER_ACTIVE = Boolean.FALSE;
	public static final String DEFAULT_ADMIN_MODE_SELECTED_DAYS = "0,1,2,3,4,5,6";
	public static final String DEFAULT_ADMIN_MODE_USER_MESSAGE = "L'applicazione e' momentaneamente in manutenzione; si prega di riprovare piu' tardi";
	public static final String DOCUMENT_DATA_PATH_DEFAULT = "/wlsmnt/muidoc";

	public static final String UPDATE_UUID = "UPDATE_UUID";
	public static final String GIORNI_SELEZIONE_PROMO = "GIORNI_SELEZIONE_PROMO";
	public static final Integer DEFAULT_GIORNI_SELEZIONE_PROMO = 90;
	// Valore default per STORED_PROCEDURE_TIMEOUT
	public static final Integer DEFAULT_STORED_PROCEDURE_TIMEOUT = 30;
	// Valore default per TAB_STORES_MESSAGE
	public static final String DEFAULT_TAB_STORES_MESSAGE = "Si ricorda l'inclusione e l'esclusione dei negozi Web";

	public static final Integer DEFAULT_MAX_UPLOAD_RECORDS = 2000;

	public static final String LOGOS_DATA_PATH="LOGOS_DATA_PATH";
	public static final String DEFAULT_LOGOS_DATA_PATH="/resources/images/logos";
	public static final String LOGIN_IMAGE = "logo-login.png";
	public static final String TOP_IMAGE = "logo-top.png";
	public static final String FAVICON_NAME = "favicon.ico";

	public static final String WEBSERVICE_TOTALIZZATORI = "WEBSERVICE_TOTALIZZATORI";
	public static final String HOST_TOTALIZZATORI = "HOST_TOTALIZZATORI";
	public static final String AUTH_TOTALIZZATORI = "AUTH_TOTALIZZATORI";

	public static final String DEFAULT_WEBSERVICE_TOTALIZZATORI = "/raf/resources/iniziativa";
	public static final String DEFAULT_HOST_TOTALIZZATORI = "intralxejbt.mil.esselunga.net";
	public static final String DEFAULT_AUTH_TOTALIZZATORI = "";
	public static final BigDecimal DEFAULT_VALORE_PUNTO_MAX_VALUE = new BigDecimal("0.999");

	//PIANO MEDIA
	// Canale Media #4816
	public static final String CANALE_PIANO_MEDIA = "CANALE_PIANO_MEDIA";
	public static final Integer DEFAULT_CANALE_PIANO_MEDIA = null;
	// Modalita' Compratore #4820
	public static final String MEDIA_MODALITA_COMPRATORE = "MEDIA_MODALITA_COMPRATORE";
	public static final Boolean DEFAULT_MEDIA_MODALITA_COMPRATORE = Boolean.FALSE;

	public static final String MESSAGGI_LOGO_DATA_PATH_DEFAULT = "/wlsmnt/muidoc";
	public static final String MESSAGGI_LOGO_DATA_PATH = "MESSAGGI_LOGO_DATA_PATH";

    public static final String MESSAGGI_LOCAL_IMG_PATH = "MESSAGGI_LOCAL_IMG_PATH";
    public static final String DEFAULT_MESSAGGI_LOCAL_IMG_PATH = "/wlsmnt/muidoc/messaggi";
	public static final String GIORNI_CUTOFF_FATT_SINGOLA_ATTIVITA = "GIORNI_CUTOFF_FATT_SINGOLA_ATTIVITA";
	public static final Integer DEFAULT_GIORNI_CUTOFF_FATT_SINGOLA_ATTIVITA = 7;

	@PostConstruct
	public void init() {
		this.update();
	}

	public String getProperty(final String key) {
		String ret = properties.get(key);
		if ( "null".equalsIgnoreCase(ret))
			ret = null;
		return  ret;
	}

	public String getProperty(final String key, final String defaultValue) {
		return getProperty(key) != null ? properties.get(key) : defaultValue;
	}

	public Double getProperty(final String key, final Double defaultValue) {
		final String prop = getProperty(key);
		if (prop == null) {
			return defaultValue;
		} else {
			try {
				return new Double(prop);
			} catch (final Exception e) {
				log.warn("invalid value set for property " + key, e);
				return defaultValue;
			}
		}
	}

	public Integer getProperty(final String key, final Integer defaultValue) {
		final String prop = getProperty(key);
		if (prop == null) {
			return defaultValue;
		} else {
			try {
				return Integer.valueOf(prop);
			} catch (final Exception e) {
				log.warn("invalid value set for property " + key, e);
				return defaultValue;
			}
		}
	}

	public Long getProperty(final String key, final Long defaultValue) {
		final String prop = getProperty(key);
		if (prop == null) {
			return defaultValue;
		} else {
			try {
				return Long.valueOf(prop);
			} catch (final Exception e) {
				log.warn("invalid value set for property " + key, e);
				return defaultValue;
			}
		}
	}

	public Boolean getProperty(final String key, final Boolean defaultValue) {
		final String prop = getProperty(key);
		if (prop == null) {
			return defaultValue;
		} else {
			return Boolean.parseBoolean(prop);
		}
	}

	public BigDecimal getProperty(final String key, final BigDecimal defaultValue) {
		final String prop = getProperty(key);
		if (prop == null) {
			return defaultValue;
		}
		try {
			return new BigDecimal(prop);
		} catch (Exception ex) {
			log.warn("invalid value set for property " + key, ex);
			return defaultValue;
		}
	}

	public boolean update() {
		try {
			getMuiService().refreshApplicationProperties();
			for (final ApplicationPropertiesEntity applicationPropertiesEntity : this.getMuiService()
					.readApplicationProperties()) {
				properties.put(applicationPropertiesEntity.getKey(), applicationPropertiesEntity.getValue());
			}
			log.debug("properties loaded");
			return true;
		} catch (final Exception e) {
			log.error("Error reading application properties.", e);
		}
		return false;
	}
}
