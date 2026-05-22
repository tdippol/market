package com.axiante.mui.webapp.utils.silentActions;

import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import lombok.Data;

@Data
public class SilentParam {
	@JsonProperty("Dim_code")
	String dimCode;
	@JsonProperty("Dim_columnName")
	String dimColumnName;
	@JsonProperty("Attr_code")
	String attributeCode;
	@JsonProperty("Attr_columnName")
	String attributeColumnName;
	@JsonProperty("avaiableValues")
	String[] availableValues;

	public SelectedParam toSelectedParam() {
//	    String label;
//	    String dimension;
//	    String attribute;
//	    String paramName;
//	    String selectedValue;
//	    List<String> values;
//	    boolean visible;
//	    boolean hasPicklist;
//	    boolean disabled;
//	    String defaultValue;

		final SelectedParam p = new SelectedParam();
		p.setDimension(getDimColumnName());
		p.setAttribute(getAttributeColumnName());
		p.setValues(Arrays.asList(getAvailableValues()));
		return p;
	}
}
