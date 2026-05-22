package com.axiante.mui.webapp.views;

import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.primefaces.model.menu.MenuItem;

public interface MuiViewInterface extends Serializable, FacesContextAware {
	void updateView();

	void updateView(String grid);

	String getViewFile();

	String getName();

	void setConfigurationMap(Map<String, Configuration> configuration);

	Map<String, Configuration> getConfigurationMap();

	Configuration getConfiguration(String name);

	String getJsonFilter();

	List<ConfigurationElement> getAvailableFilters();

	void prepareJsonFilter();

	void setNode(MenuItem node);

	void setCurrentJsonFilter(String currentJsonFilter);

	String getCurrentJsonFilter();

	void setAdminJsonFilter(String currentJsonFilter);

	String getAdminJsonFilter();

	List<IngridFilter> getCurrentIngridJsonFilter();

	void setCurrentIngridJsonFilter(List<IngridFilter> currentIngridJsonFilter);

	boolean isDisableButtonFilters();

	void setDisableButtonFilters(boolean a);

	Boolean getSuppressStatus(String gridId, String type);

	Query prepareFilteredQuery(String grid);

	void removeSpinner(@NonNull String grid);

	void clearGridData(@NonNull String grid);

	boolean canLoadData();

	boolean canLoadData(String grid);

	List<String> getAvailableGrids();

	void setAvailableGrids(List<String> list);

	void persistIngridFilter(String grid);

	default ViewType getViewType() {
		return ViewType.MUI;
	}

	default Boolean isMuiView() {
		return getViewType().equals(ViewType.MUI);
	}

	default Boolean isAdminView() {
		return getViewType().equals(ViewType.ADMIN);
	}

	default Boolean isWelcomeView() {
		return getViewType().equals(ViewType.WELCOME);
	}

	default Boolean isPromoView() {
		return getViewType().equals(ViewType.DBPROMO);
	}

	void prepareDBPromoJsonFilter();

	void setEnabledConnections(List<String> enabledConnections);

	List<String> getEnabledConnections();

	void free();

	ConfigurationEntity getActiveConfiguration(ConfigurationTypes type);

	default boolean equals(MuiViewInterface that) {
		boolean canEqual = getViewType().equals(that.getViewType()) && getViewFile().equals(that.getViewFile())
				&& getName().equals(that.getName());
		if (canEqual && (getAvailableGrids() != null)) {
			return canEqual && getAvailableGrids().equals(that.getAvailableGrids());
		}
		return canEqual;
	}
	String getContesto();

	default String getParsedContesto() {
		String contesto = getContesto();
		if ( contesto != null ) {
			contesto = contesto.replaceAll("\'", "");
		}
		return contesto;
	}
}