package com.axiante.utility.configuration;

import com.axiante.connection.ConnectionProfile;
import com.axiante.tm1.mdx.Command;
import com.axiante.tm1.mdx.objects.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Configuration implements Serializable {
	private static final long serialVersionUID = 1938621111081353505L;
	private String filePath;
	private String fileLabel;
	private String name;
	private Query mdx;
	private Boolean skip;
	private Boolean logMemory;
	private Boolean logTime;
	private Command command;
	private String autoGroupColumnDef;
	private String columnDefs;
	private String rowClassRules;
	private String groupRowAggNodes;
	private Boolean rowSuppressionEnabled;
	private Boolean colSuppressionEnabled;
	private Boolean suppressRowClickSelection;
	private ConnectionProfile profile;
	private Integer maxCells;
	private String maxCellsMessage;
	private Boolean pagination;
	private String preSelections;
	private String selections;
	private String actions;
	@Builder.Default
	private Boolean alertNoDataFound = true;
	@Builder.Default
	private Boolean dynamicColumns = false;
	private DynamicColumnsSettings dynamicColumnsSettings;
	@Builder.Default
	private Boolean forceSuppression = false;
	// templating
	private Integer height;
	private String title;
	private String css;
	private List<String> compulsoryFilters;
	private String compulsoryFiltersMessage;
	private Boolean showVersion;
	// grid filters
	private Map<String, String> gridFilters;
	private Map<String, Integer> pageLinks;

	@Override
	public boolean equals(final Object o) {
		boolean ret = false;

		if (o instanceof Configuration) {
			ret = this.getName().equalsIgnoreCase(((Configuration) o).getName());
		}

		return ret;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName());
	}

	public Configuration copy() {
		final Configuration c = Configuration.builder().build();

		if (this.getFilePath() != null) {
			c.setFilePath(new String(this.getFilePath()));
		}
		if (this.getFileLabel() != null) {
			c.setFileLabel(new String(this.getFileLabel()));
		}

		if (this.getName() != null) {
			c.setName(new String(this.getName()));
		}
		if (this.getMdx() != null) {
			c.setMdx(this.getMdx().copy());
		}
		if (this.getSkip() != null) {
			c.setSkip(new Boolean(this.getSkip()));
		}
		if (this.getLogMemory() != null) {
			c.setLogMemory(new Boolean(this.getLogMemory()));
		}
		if (this.getLogTime() != null) {
			c.setLogTime(new Boolean(this.getLogMemory()));
		}
		if (this.getCommand() != null) {
			c.setCommand(this.getCommand().copy());
		}
		if (this.getAutoGroupColumnDef() != null) {
			c.setAutoGroupColumnDef(new String(this.getAutoGroupColumnDef()));
		}
		if (this.getColumnDefs() != null) {
			c.setColumnDefs(new String(this.getAutoGroupColumnDef()));
		}
		if (this.getRowClassRules() != null) {
			c.setRowClassRules(new String(this.getRowClassRules()));
		}
		if (this.getGroupRowAggNodes() != null) {
			c.setGroupRowAggNodes(new String(this.getGroupRowAggNodes()));
		}
		if (this.getRowSuppressionEnabled() != null) {
			c.setRowSuppressionEnabled(new Boolean(this.getRowSuppressionEnabled()));
		}
		if (this.getColSuppressionEnabled() != null) {
			c.setColSuppressionEnabled(new Boolean(this.getColSuppressionEnabled()));
		}
		if (this.getSuppressRowClickSelection() != null) {
			c.setSuppressRowClickSelection(new Boolean(this.getSuppressRowClickSelection()));
		}
		if (this.getProfile() != null) {
			c.setProfile(this.getProfile());
		} // this is not copied
		if (this.getMaxCells() != null) {
			c.setMaxCells(new Integer(this.getMaxCells()));
		}
		if (this.getMaxCellsMessage() != null) {
			c.setMaxCellsMessage(new String(this.getMaxCellsMessage()));
		}
		if (this.getPagination() != null) {
			c.setPagination(new Boolean(this.getPagination()));
		}
		if (this.getPreSelections() != null) {
			c.setPreSelections(new String(this.getPreSelections()));
		}
		if (this.getSelections() != null) {
			c.setSelections(new String(this.getSelections()));
		}
		if (this.getActions() != null) {
			c.setActions(new String(this.getActions()));
		}
		if (this.getAlertNoDataFound() != null) {
			c.setAlertNoDataFound(new Boolean(this.getAlertNoDataFound()));
		}
		if (this.getDynamicColumnsSettings() != null) {
			c.setDynamicColumnsSettings(this.getDynamicColumnsSettings().copy());
		}
		if (this.getDynamicColumns() != null) {
			c.setDynamicColumns(new Boolean(this.getDynamicColumns()));
		}
		if (this.getForceSuppression() != null) {
			c.setForceSuppression(new Boolean(this.getForceSuppression()));
		}
		if (this.getHeight() != null) {
			c.setHeight(new Integer(this.getHeight()));
		}
		if (this.getTitle() != null) {
			c.setTitle(new String(this.getTitle()));
		}
		if (this.getCss() != null) {
			c.setCss(new String(this.getCss()));
		}
		if (this.getCompulsoryFilters() != null) {
			c.setCompulsoryFilters(this.getCompulsoryFilters().stream().collect(Collectors.toList()));
		}
		if (this.getCompulsoryFiltersMessage() != null) {
			c.setCompulsoryFiltersMessage(new String(this.getCompulsoryFiltersMessage()));
		}
		c.setShowVersion(new Boolean(this.getShowVersion()));
		if (this.getGridFilters() != null) {
			c.setGridFilters(this.getGridFilters().entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
		}

		return c;
	}

	@Deprecated
	public boolean isDynamicColumns() {
		if (this.getDynamicColumns() != null) {
			return this.getDynamicColumns();
		}
		return false;
	}

	@Deprecated
	public boolean isShowVersion() {
		if (this.getShowVersion() != null) {
			return this.getShowVersion();
		}
		return false;
	}
	public String getActions() {
		return this.actions;
	}
}
