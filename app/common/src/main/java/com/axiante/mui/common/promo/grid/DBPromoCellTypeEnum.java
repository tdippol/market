package com.axiante.mui.common.promo.grid;

public enum DBPromoCellTypeEnum {

	STRING("string"), NUMERIC("numeric"), DATE("date"), PICKLIST("picklist"), COMBOBOX("comboBox"),
	CHECKBOX("checkbox"), BOOLEAN("boolean"), TIME("time"), POPUP_DIALOG("popupdialog"), PICKCOLOR("pickcolor");

	private String type;

	DBPromoCellTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static DBPromoCellTypeEnum asEnum(final String string) {
		switch (string.toLowerCase()) {
			case "string":
				return STRING;
			case "numeric":
				return NUMERIC;
			case "date":
				return DATE;
			case "picklist":
				return PICKLIST;
			case "combobox":
				return COMBOBOX;
			case "checkbox":
				return CHECKBOX;
			case "boolean":
				return BOOLEAN;
			case "time":
				return TIME;
			case "popupdialog":
				return POPUP_DIALOG;
			case "pickcolor":
				return PICKCOLOR;
		}
		return null;
	}
}
