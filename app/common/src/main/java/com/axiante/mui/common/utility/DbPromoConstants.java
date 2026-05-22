package com.axiante.mui.common.utility;

import java.math.BigDecimal;

public class DbPromoConstants {
	public static final String COLUMN_DEF = "columnDef";
	public static final String ROW_DATA = "rowData";

	public static final String ESITO_POSITIVO = "Positivo";
	public static final String ESITO_NEGATIVO = "Negativo";
	public static final String ESITO_ATTESA = "In Attesa di Elaborazione";

	public static final String DBPROMO_STRING_DATE_FORMAT = "DDD dd/mm/yyyy HH:MM.ss";

	public static String convertEsito(final BigDecimal i) {
		if (i == null)
			return "SCONOSCIUTO";
		switch (i.intValue()) {
			case 1:
				return ESITO_POSITIVO;
			case 0:
				return ESITO_NEGATIVO;
			case -1:
				return ESITO_ATTESA;
			default:
				return "SCONOSCIUTO";
		}
	}

	public final static String ASCENDING_SQL = "ASC";
	public final static String DESCENDING_SQL = "DESC";

	public final static String PIANO_MEDIA_DEFAULT_COLOR = "1066A8";
	public final static String PIANO_MEDIA_BLACK_COLOR = "000000";

	public final static String FLAG_SCONTO_RIFATTURABILE = "FLG_SCNT_RIF";
}
