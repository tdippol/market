package com.axiante.mockserver;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Request{

	@JsonProperty("path")
	private String path;

	@JsonProperty("type")
	private String type;

	@JsonProperty("body")
	private String body;

	@JsonProperty(value = "query",required = false)
	private List<QueryParameter> query;

}