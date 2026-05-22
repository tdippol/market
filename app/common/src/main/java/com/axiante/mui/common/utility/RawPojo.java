package com.axiante.mui.common.utility;


import java.util.regex.Matcher;
import lombok.Getter;

public class RawPojo {
	@Getter
	String json;

	private Matcher matcher = JsonUtils.jsonStringPatternFix.matcher("");

	public void setJson(String json) {
		this.json = matcher.reset(json).replaceAll("");
	}
}
