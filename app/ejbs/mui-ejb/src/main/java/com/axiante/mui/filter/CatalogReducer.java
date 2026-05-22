package com.axiante.mui.filter;

import java.io.Serializable;
import java.util.List;

public interface CatalogReducer extends Serializable {
	/**
	 * implementation of the method should make sure that the
	 * return type of this List is an instance of Synchronized List
	 * @param filters
	 * @return
	 */
	List<ConfigurationElement> filterCatalog(final List<CatalogFilter> filters);
	ConfigurationFilterCatalog getCatalog();
}
