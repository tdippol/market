package com.axiante.mui.webapp.views.filter;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SessionScoped
@Data
public class FiltredJsonBean implements Serializable {
	private static final long serialVersionUID = 917026857865669944L;
	/*
	 * ---------------- Upper case present a cause of FilterSystemNew-JSON return
	 * this ----------------
	 */
	@Getter
	@Setter
	@JsonProperty("Dimension")
	private String Dimension;
	@Getter
	@Setter
	@JsonProperty("Enabled")
	private Boolean enabled;
	@Getter
	@Setter
	@JsonProperty("Dimension_code")
	private String Dimension_code;
	@Getter
	@Setter
	@JsonProperty("Dimension_desc")
	private String Dimension_desc;
	@Getter
	@Setter
	@JsonProperty("MDXjsonSource")
	private String MDXjsonSource;
	@Getter
	@Setter
	@JsonProperty("Attribute")
	private String Attribute;
	@Getter
	@Setter
	@JsonProperty("Attribute_code")
	private String Attribute_code;
	@Getter
	@Setter
	@JsonProperty("Attribute_desc")
	private String Attribute_desc;
	@Getter
	@Setter
	@JsonProperty("Attribute_type")
	private String Attribute_type;
	@Getter
	@Setter
	@JsonProperty("ServerName")
	private String ServerName;
	@Getter
	@Setter
	@JsonProperty("selectedValues")
	private String[] selectedValues;

	public String getDimensionKey() {
		return Dimension + "#" + Dimension_code + "#" + Dimension_desc;
	}

	public String getAttributeKey() {
		return Attribute + "#" + Attribute_code + "#" + Attribute_desc;
	}
	
	public String[] getPrintableValues() {
		if ("datePicker".equals(getAttribute_type())) {
			String value = selectedValues[0];
			final DateTimeUtils utils = new DateTimeUtils();
			value = DateTimeUtils.getFormatoItaliano().format(utils.excelToDate(value));
			return new String[] {value};
		}
		return selectedValues;
	}

}
