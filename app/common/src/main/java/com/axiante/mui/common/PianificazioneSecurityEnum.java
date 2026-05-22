package com.axiante.mui.common;

import lombok.NonNull;

public enum PianificazioneSecurityEnum {

	READ("R", false), WRITE("W", true);

	private String security;
	private boolean editable;

	PianificazioneSecurityEnum(String security, boolean editable) {
		this.security = security;
		this.editable = editable;
	}

	public String getSecurity() {
		return security;
	}

	public boolean isEditable() {
		return editable;
	}

	public static PianificazioneSecurityEnum fromString(@NonNull final String string) {
		switch (string.toUpperCase()) {
		case "R":
			return READ;
		case "W":
			return WRITE;
		default:
			return null;
		}
	}

}
