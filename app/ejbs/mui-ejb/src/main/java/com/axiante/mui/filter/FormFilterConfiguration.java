package com.axiante.mui.filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.axiante.mui.common.utility.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class FormFilterConfiguration {

	Map<String,List<String>> dimensions;
	Set<DefaultDimensionFilter> defaults;

	public void addDefaultFilter(@NonNull final DefaultDimensionFilter filter) {
		if ( defaults == null ) {
			defaults = new HashSet<>();
		}
		defaults.add(filter);
	}

	@JsonIgnore
	public String getDimensionsAsJson() {
		try {
			return JsonUtils.getMapper().writeValueAsString(dimensions);
		} catch (Exception e) {
			log.error("error converting dimensions to json", e);
		}
		return null;
	}

	@JsonIgnore
	public String getDefaultsAsJson() {
		try {
			return JsonUtils.getMapper().writeValueAsString(defaults);
		} catch (Exception e) {
			log.error("error converting dimensions to json", e);
		}
		return null;
	}

	public DefaultDimensionFilter findDefaultDimension(@NonNull final String dimension) {
		if ( defaults != null ) {
			return defaults.stream().filter(d->dimension.equals(d.getCode())).findFirst().orElse(null);
		}
		return null;
	}

	@JsonIgnore
	public FormFilterConfiguration initializeAsEmpty() {
		dimensions = new HashMap<>();
		defaults = new HashSet<>();
		return this;
	}

}
