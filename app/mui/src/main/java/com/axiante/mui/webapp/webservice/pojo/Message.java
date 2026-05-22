package com.axiante.mui.webapp.webservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	@Getter
	private String message;
	@JsonIgnore
	private static final String MESSAGE = "{\"message\":\"%s\"}";

	public Message(String format, Object... args) {
		this.message = String.format(format, args);
	}

	public String asJson() {
		if (message != null) {
			return String.format(MESSAGE, message);
		}
		return "";
	}

}
