package com.axiante.mui.common.promo.grid;

public enum DBPromoColTypeEnum {

	STRING("string"), NUMERIC("numeric"), DATE("date"), TIME("time"), CHECKBOX("checkbox");

	private String type;

	DBPromoColTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
