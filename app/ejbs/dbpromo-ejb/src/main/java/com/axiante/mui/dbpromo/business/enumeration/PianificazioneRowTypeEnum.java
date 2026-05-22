package com.axiante.mui.dbpromo.business.enumeration;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

//TODO: remove. Creare metodi di utility/service per recuperare da entity
@Slf4j
public enum PianificazioneRowTypeEnum {
	// TODO: remove M e D
	@Deprecated
	MASTER("M", "MASTER"),
	@Deprecated
	DETAIL("D", "DETAIL"),
	SET("S", "SET"),
	RAGGRUPPAMENTO("R", "RAGGRUPPAMENTO"),
	ELEMENTO("E", "ELEMENTO");

	private final String typeCode;
	private final String description;

	PianificazioneRowTypeEnum(String typeCode, String description) {
		this.typeCode = typeCode;
		this.description = description;
	}

	public static PianificazioneRowTypeEnum fromCode(@NonNull String typeCode) {
		switch (typeCode.trim().toUpperCase()) {
			case "S":
				return SET;
			case "R":
				return RAGGRUPPAMENTO;
			case "E":
				return ELEMENTO;
			case "M":
				log.warn(String.format("RowType '%s' is deprecated and should not be used", MASTER.getDescription()));
				return MASTER;
			case "D":
				log.warn(String.format("RowType '%s' is deprecated and should not be used", DETAIL.getDescription()));
				return DETAIL;
			default:
				log.error(String.format("RowType '%s' is not managed", typeCode));
				return null;
		}
	}

	public String getTypeCode() {
		return typeCode;
	}

	public String getDescription() {
		return description;
	}
}
