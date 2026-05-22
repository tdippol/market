package com.axiante.mockserver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryParameter {
	@JsonProperty("name")
	private String name;
	@JsonProperty("value")
	private String value;
	
	public String toQueryString() {
		return name + "=" + value;
	}

}
