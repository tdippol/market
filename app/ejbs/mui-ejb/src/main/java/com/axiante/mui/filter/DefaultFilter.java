package com.axiante.mui.filter;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultFilter {
	public static final String EMPTY_DEFAULTS="{'defaults':{}}".replace("\'", "\"");
	@Getter
	Set<DefaultDimensionFilter> defaults;

	public void addFilter(@NonNull final DefaultDimensionFilter filter) {
		if ( defaults == null ) {
			defaults = new HashSet<>();
		}
		defaults.add(filter);
	}
}
