package com.axiante.mui.webapp.views.content.aggrid.actions;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class SelectedParam implements Serializable {
	private static final long serialVersionUID = 4348580259931767713L;
	String label;
	String dimension;
	String attribute;
	String paramName;
	String selectedValue;
	List<String> values;
	boolean visible;
	boolean hasPicklist;
	boolean disabled;
	String defaultValue;

}
