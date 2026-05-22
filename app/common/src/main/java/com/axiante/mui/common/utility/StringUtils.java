package com.axiante.mui.common.utility;

import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import java.io.Serializable;
import java.util.regex.Pattern;
import lombok.NonNull;

public class StringUtils implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -4022199424991216050L;
	private final Pattern patternInteger = Pattern.compile("\\d+?");
	private final Pattern patternNumber = Pattern.compile("\\d+(\\.\\d{1,2})?");
	private static final String INTEGER = "Integer";
	private static final String NUMBER = "Number";

	// Regular Expression
	transient private static final String camelToSnakeRegEx = "([a-z])([A-Z]+)";
	// Replacement string
	transient private static final String replacement = "$1_$2";

	public boolean isIntegerType(String type) {
		return INTEGER.equals(type.trim());
	}

	public boolean isNumberType(String type) {
		return NUMBER.equals(type.trim());
	}

	public boolean isInteger(String strNum) {
		if (strNum == null) {
			return false;
		}
		return patternInteger.matcher(strNum).matches();
	}

	public boolean isNumber(String strNum) {
		if (strNum == null) {
			return false;
		}
		return patternNumber.matcher(strNum).matches();
	}

	public boolean isTime(String string) {
		if (string == null) {
			return false;
		}
		return DBPromoCellTypeEnum.TIME.getType().toLowerCase().equals(string.toLowerCase().trim());
	}

	public String camelToSnake(String str) {

		// Replace the given regex
		// with replacement string
		// and convert it to lower case.
		str = str.replaceAll(camelToSnakeRegEx, replacement).toLowerCase();

		// return string
		return str;
	}

	public String pad(@NonNull String string, final @NonNull Integer lenght, final @NonNull Character _char)
			throws IllegalArgumentException {
		if (lenght > 0) {
			return String.format("%" + lenght + "s", string).replace(' ', _char);
		}
		throw new IllegalArgumentException("unsupported lenght" + lenght);
	}

	public String truncate(@NonNull String string, final @NonNull Integer lenght) {
		if (string.length() > lenght) {
			return string.substring(0, lenght);
		}
		return string;
	}

	public static boolean isEmpty(final String jsonString) {
		if (jsonString == null) {
			return true;
		}
		String trimmed = jsonString.replaceAll(" ", "");
		if (trimmed.length() == 0) {
			return true;
		}
		if (trimmed.equals("{}")) {
			return true;
		}
		return false;

	}
}
