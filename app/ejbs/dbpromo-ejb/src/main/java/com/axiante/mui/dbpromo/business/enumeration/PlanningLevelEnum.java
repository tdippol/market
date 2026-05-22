package com.axiante.mui.dbpromo.business.enumeration;

import lombok.NonNull;

public enum PlanningLevelEnum {
	SET("SET", "S"),
	RAGGRUPPAMENTO("RAGGRUPPAMENTO", "R"),
	ELEMENTO("ELEMENTO", "E");

	private final String description;
	private final String code;

	PlanningLevelEnum(String description, String code) {
		this.description = description;
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}
	
	public static PlanningLevelEnum fromCode(@NonNull final String code) {
		switch (code.toUpperCase()) {
		case "S":
			return SET;
		case "R":
			return RAGGRUPPAMENTO;
		case "E":
			return ELEMENTO;
		}
		return null;
	}
	
	public static PlanningLevelEnum fromDescription(@NonNull final String description) {
		switch (description.toUpperCase()) {
		case "SET":
			return SET;
		case "RAGGRUPPAMENTO":
			return RAGGRUPPAMENTO;
		case "ELEMENTO":
			return ELEMENTO;
		}
		return null;
	}
}
