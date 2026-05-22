package com.axiante.mui.filter.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.tm1.mdx.objects.Dimension;
import com.fasterxml.jackson.core.JsonParseException;

public interface FilterUtils extends Serializable{
	String DIMENSIONS_ATTRIBUTE = "dimensions";
	String DEFAULTS_ATTRIBUTE = "defaults";
	List<Dimension> unpackRowsToDimension(String rows) throws JsonParseException, IOException;
	List<String> unpack(String rows, String columns);
	List<Dimension> unpackRowsToQueuedDimension(String rows) throws JsonParseException, IOException;
	String removeUnchangedFiltersFromStore(final String storedFiltersJson, final List<ConfigurationElement> configFiltersList, final String selectedFiltersJson);
	String removeUnchangedDBPromoFiltersFromStore(final String storedFiltersJson, final List<ConfigurationElement> configFiltersList, final String selectedFiltersJson);

}
