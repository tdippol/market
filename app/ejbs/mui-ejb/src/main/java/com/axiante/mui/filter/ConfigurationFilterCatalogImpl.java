package com.axiante.mui.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.service.MuiService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class ConfigurationFilterCatalogImpl implements ConfigurationFilterCatalog {
	private static final long serialVersionUID = 4981095975335181039L;
	private final List<ConfigurationElement> catalog = Collections.synchronizedList(new ArrayList<>());
	@Inject
	transient private ApplicationFilterCatalogProducer producer;
	@Inject
	transient private MuiService muiService;

	transient private final Object semaphore = new Object();

	@PostConstruct
	private void init() {
		update();
	}

	@Override
	public void update() {
		synchronized (semaphore) {
			try {
				final ConfigurationEntity configurationEntity = muiService.findConfigurations(FILE_NAME,
						ConfigurationTypes.GLOBAL);
				if (configurationEntity != null) {
					try {
						catalog.clear();
						catalog.addAll(producer.read(configurationEntity.getJson()).getConfigurations());
					} catch (final Exception e) {
						log.error("unhandled error reading catalog", e);
					}
				} else {
					log.error("Missing configuration: " + FILE_NAME);
				}
			} catch (final Exception e) {
				log.error("error reading configuration " + FILE_NAME, e);
			}
		}
	}

	@Override
	public List<ConfigurationElement> getCatalog() {
		// log.info("returning global filter catalog");
		update();
		return catalog;
	}

	@Override
	public ConfigurationElement findByCodeAndAttribute(@NonNull final String code, @NonNull final String column) {
		return catalog.stream().filter(c -> c.getCode().equals(code)).filter(c -> c.containsAttributeCode(column))
				.findAny().orElse(null);

	}
}
