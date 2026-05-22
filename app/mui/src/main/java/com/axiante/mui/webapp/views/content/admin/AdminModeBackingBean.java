package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminModeBackingBean implements FacesContextAware {
	@Getter
	private boolean adminModeActive;
	@Getter
	private String userMessage;
	@Getter
	private boolean schedulerActive;
	@Getter
	private Date fromTime;
	@Getter
	private Date toTime;
	@Getter
	private boolean allDays;
	@Getter
	private String[] selectedDays;

	private final ApplicationProperties properties;

	private ApplicationPropertiesService service;

	public AdminModeBackingBean(ApplicationPropertiesService service, ApplicationProperties properties) {
		this.service = service;
		this.properties = properties;
		refresh();
	}

	private ApplicationPropertiesEntity getEntity(String key) {
		ApplicationPropertiesEntity entity = service.getPropertiesEntity(key);
		if (entity == null) {
			log.warn("Application property " + key + " not set; creating one");
			entity = new ApplicationPropertiesEntity();
			entity.setKey(key);
		}
		return entity;
	}

	public void setAdminModeActive(boolean adminModeActive) {
		ApplicationPropertiesEntity entity = getEntity(ApplicationProperties.MANUAL_ADMIN_MODE);
		if (saveProperty(entity, "" + adminModeActive)) {
			this.adminModeActive = adminModeActive;
		}
	}

	public void setAllDays(boolean allDays) {
		ApplicationPropertiesEntity entity = getEntity(ApplicationProperties.ADMIN_MODE_ALL_DAYS);
		if (saveProperty(entity, "" + allDays)) {
			this.allDays = allDays;
		}
	}

	public void setFromTime(Date fromTime) {
		ApplicationPropertiesEntity entity = getEntity(ApplicationProperties.ADMIN_MODE_FROM_TIME);
		if (saveProperty(entity, DateTimeUtils.getTimeFormat().format(fromTime))) {
			this.fromTime = fromTime;
		}
	}

	public void setSchedulerActive(boolean schedulerActive) {
		ApplicationPropertiesEntity entity = getEntity(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE);
		if (saveProperty(entity, "" + schedulerActive)) {
			this.schedulerActive = schedulerActive;
		}
	}

	public void setSelectedDays(String[] selectedDays) {
		ApplicationPropertiesEntity entity = getEntity(ApplicationProperties.ADMIN_MODE_SELECTED_DAYS);
		String days = "";
		if (selectedDays.length > 0) {
			days = Arrays.stream(selectedDays).collect(Collectors.joining(","));
		}
		if (saveProperty(entity, days)) {
			this.selectedDays = selectedDays;
		}
	}

	public void setToTime(Date toTime) {
		ApplicationPropertiesEntity entity = getEntity(ApplicationProperties.ADMIN_MODE_TO_TIME);
		if (saveProperty(entity, DateTimeUtils.getTimeFormat().format(toTime))) {
			this.toTime = toTime;
		}
	}

	public void setUserMessage(String userMessage) {
		ApplicationPropertiesEntity entity = getEntity(ApplicationProperties.ADMIN_MODE_USER_MESSAGE);
		if (saveProperty(entity, userMessage)) {
			this.userMessage = userMessage;
		}
	}

	private boolean saveProperty(ApplicationPropertiesEntity entity, String newValue) {
		boolean result = false;
		try {
			entity.setValue(newValue);
			entity = service.saveProperty(entity);
			result = true;
			updatePropertyList();
		} catch (Exception e) {
			log.error("error saving property " + entity.getKey(), e);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Impossibile salvare la properieta'",
					e.getMessage()));
		}
		return result;
	}

	public void refresh() {
		// lettura valori
		updatePropertyList();

		adminModeActive = properties.getProperty(ApplicationProperties.MANUAL_ADMIN_MODE,
				ApplicationProperties.DEFAULT_MANUAL_ADMIN_MODE);
		schedulerActive = properties.getProperty(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE,
				ApplicationProperties.DEFAULT_ADMIN_MODE_SCHEDULER_ACTIVE);
		userMessage = properties.getProperty(ApplicationProperties.ADMIN_MODE_USER_MESSAGE,
				ApplicationProperties.DEFAULT_ADMIN_MODE_USER_MESSAGE);
		try {
			fromTime = DateTimeUtils.getTimeFormat().parse(properties.getProperty(
					ApplicationProperties.ADMIN_MODE_FROM_TIME, ApplicationProperties.DEFAULT_ADMIN_MODE_FROM_TIME));
		} catch (ParseException e) {
			log.error("errore nel formato di data registrato nel db ["
					+ properties.getProperty(ApplicationProperties.ADMIN_MODE_FROM_TIME,
							ApplicationProperties.DEFAULT_ADMIN_MODE_FROM_TIME)
					+ "]! ", e);
		}
		try {
			toTime = DateTimeUtils.getTimeFormat().parse(properties.getProperty(
					ApplicationProperties.ADMIN_MODE_TO_TIME, ApplicationProperties.DEFAULT_ADMIN_MODE_TO_TIME));
		} catch (ParseException e) {
			log.error("errore nel formato di data registrato nel db ["
					+ properties.getProperty(ApplicationProperties.ADMIN_MODE_TO_TIME,
							ApplicationProperties.DEFAULT_ADMIN_MODE_TO_TIME)
					+ "]! ", e);
		}
		allDays = properties.getProperty(ApplicationProperties.ADMIN_MODE_ALL_DAYS,
				ApplicationProperties.DEFAULT_ADMIN_MODE_ALL_DAYS);
		String days = properties.getProperty(ApplicationProperties.ADMIN_MODE_SELECTED_DAYS,
				ApplicationProperties.DEFAULT_ADMIN_MODE_SELECTED_DAYS);
		if (days.length() != 0) {
			selectedDays = days.split(",");
		}
	}


	private void updatePropertyList() {
		try {
			properties.update();
		} catch (Exception e) {
			log.error("impossibile recuperare la lista dei valori", e);
		}

	}

	public boolean isAccessGranted() {
		try {
			return !service.calculateAdminMode();
		} catch (Exception e) {
			log.error("error calculating access mode",e);
			return false;
		}
	}
}
