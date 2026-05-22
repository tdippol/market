package com.axiante.mui.filter;

import java.io.Serializable;
import java.util.List;

public interface ConfigurationFilterCatalog extends Serializable {
	String FILE_NAME = "config_filter.json";

	void update();

	List<ConfigurationElement> getCatalog();

	ConfigurationElement findByCodeAndAttribute(final String code, String column);
}
