package com.axiante.mui.filter;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class FilterAttribute implements Serializable {
	private static final long serialVersionUID = -3093354826003400735L;
	private String code;
	private String columnName;
	private String desc;
	private List<String> values;
	private String type = "dropdown";
}
