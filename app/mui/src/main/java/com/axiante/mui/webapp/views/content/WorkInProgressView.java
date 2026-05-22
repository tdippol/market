package com.axiante.mui.webapp.views.content;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
public class WorkInProgressView implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject ApplicationPropertiesService service;
	@Getter
	String message = "Applicazione in manutenzione"; // default

	@PostConstruct
	public void init() {
		ApplicationPropertiesEntity s = null;
		try {
			s= service.getPropertiesEntity(ApplicationProperties.ADMIN_MODE_USER_MESSAGE);
			if ( s == null ) {
				log.warn("Could not get application property message ");
			} else {
				this.message = s.getValue();
			}
		} catch (Exception e) {
			log.error("Error setting custom message : ", e);
		}
	}
}
