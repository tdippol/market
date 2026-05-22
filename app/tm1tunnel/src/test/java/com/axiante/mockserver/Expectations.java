package com.axiante.mockserver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Expectations{

	@JsonProperty("request")
	private Request request;

	@JsonProperty("response")
	private Response response;

	@JsonProperty("name")
	private String name;
}