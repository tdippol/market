package com.axiante.mui.webapp.views.filter.postfilterselection;

import com.axiante.mui.webapp.views.filter.FiltredJsonBean;
import java.util.List;
import java.util.Map;

public interface FilterSelectedInterface {

	void parseJsonFilter(String json);

	Map<String, List<FiltredJsonBean>> groupingByForFilter(List<FiltredJsonBean> filter);

	Map<String, List<FiltredJsonBean>> getMap();

	List<String> getDimensions();

	boolean isEnabled(String dimension);

	void applyEnabledConnections(List<String> enabledConnections);

	String getExtraCss(final String dimension);

	void parseDBPromoJsonFilter(String json);
}
