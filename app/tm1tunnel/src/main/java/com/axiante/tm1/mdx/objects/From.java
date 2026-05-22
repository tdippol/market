package com.axiante.tm1.mdx.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class From {
	String value;
	
	public String getCubeName() {
		if ( value.startsWith("[") && value.endsWith("]")) {
			return value.substring(value.indexOf("[")+1, value.lastIndexOf("]"));
		}
		return value;
	}
	
}
