package com.axiante.mui.webapp.webservice.pojo;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class HealthInventory {

	private Map<String, Long> data = new HashMap<>();
	@Getter
	private boolean status;

	public HealthInventory withData(String key, Long value) {
		data.put(key, value);
		return this;
	}

	public HealthInventory status(boolean status) {
		this.status = status;
		return this;
	}
}
