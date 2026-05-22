package com.axiante.mockserver;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Response{

	@JsonProperty("pathToBody")
	private String pathToBody;

	@JsonProperty("status")
	private int status;

	@JsonProperty("headers")
	private List<Header> headers;

}