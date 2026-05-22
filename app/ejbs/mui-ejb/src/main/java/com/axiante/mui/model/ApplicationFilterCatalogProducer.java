package com.axiante.mui.model;

import java.io.Serializable;
import java.util.List;

import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.utility.configuration.Configuration;

public interface ApplicationFilterCatalogProducer extends Serializable{
    List<ConfigurationElement> getConfigurations() ;
    ApplicationFilterCatalogProducer read(String json) ;
    String toJson(final List<ConfigurationElement> configurations);
    List<IngridFilter> inGridPicklistValues(final Configuration gridconfiguration, final List<ConfigurationElement> inGridFilters);
    String toDBPromoJson(final List<ConfigurationElement> configurations, final String pickListJson);
}
