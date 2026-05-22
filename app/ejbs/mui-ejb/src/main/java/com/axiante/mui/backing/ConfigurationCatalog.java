package com.axiante.mui.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.axiante.mui.business.reader.ConfigurationReader;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.utility.configuration.Configuration;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ConfigurationCatalog implements Serializable {
	private static final long serialVersionUID = 2921673834864216563L;
	@Getter
	private final Map<String, Configuration> configurations = Collections.synchronizedMap(new HashMap<>());
	@Getter
	private final HashMap<String, String> configurationFilesMap = new HashMap<>();
	@Inject
	Instance<ConfigurationReader> readerInstance;
	@Inject
	@Getter
	transient private MuiService muiService;
	private static final AtomicBoolean busy = new AtomicBoolean(false);

	@PostConstruct
	public void init() {
		update();
	}

	public boolean update() {
		if (busy.compareAndSet(false, true)) {
			boolean result = false;
			try {
				List<ConfigurationEntity> configurations = null;
				configurations = muiService.readConfigurations(ConfigurationTypes.GRID).stream()
						.filter(c -> c.getRevisionDate() == null).collect(Collectors.toList());
				for (final ConfigurationEntity configurationEntity : configurations) {
					final Map<String, Configuration> configurationMap = getReader().read(configurationEntity.getJson())
							.getConfigurations();
					for (final Configuration configuration : configurationMap.values()) {
						configuration.setFilePath(configuration.getFilePath());
						configuration.setFileLabel(configuration.getFilePath());
					}
					getConfigurations().putAll(configurationMap);
					for (final Configuration c : getConfigurations().values()) {
						getConfigurationFilesMap().put(c.getFileLabel(), c.getFilePath());
					}
				}
				result = true;
			} catch (final Exception e) {
				log.error("error reading configuration files", e);
			} finally {
				busy.set(false);
			}
			return result;
		} else {
			log.debug("Configuration catalog already being updated");
			return true;
		}
	}

	public Map<String, Configuration> readConfiguration(@NonNull final String json) {
		try {
			return getReader().read(json).getConfigurations();
		} catch (final IOException e) {
			log.error("Error reading configuration from menu node.", e);
		}
		return null;
	}

	public List<String> readAvailableGrids(@NonNull final String json) {
		try {
			return getReader().read(json).getGridList();
		} catch (final IOException e) {
			log.error("Error reading configuration from menu node.", e);
		}
		return null;
	}

	private ConfigurationReader getReader() {
		return readerInstance.get();
	}
}
