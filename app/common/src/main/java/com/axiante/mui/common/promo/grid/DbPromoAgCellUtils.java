package com.axiante.mui.common.promo.grid;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbPromoAgCellUtils {
	private transient static final Object semaphore = new Object();

	public static Object getValue(@NonNull final JsonNode node, Boolean withTypeRef) {
		synchronized (node) {
			return getValue(decode(node));
		}
	}

	public static String getValue(@NonNull final JsonNode node) {
		Object o = getValue(node, null);
		return o != null ? o.toString() : null;
	}

	public static DBPromoAgCell decode(@NonNull final JsonNode node) {
		synchronized (node) {
			DBPromoAgCell cell = null;
			try {
				cell = JsonUtils.getMapper().treeToValue(node, DBPromoAgCell.class);
			} catch (IOException e) {
				log.warn("the value " + node.toString() + " is not a DBPromoAgCell object", e);
			}
			return cell;
		}
	}

	public static Object getValue(final DBPromoAgCell cell, Boolean withTypeRef) {
		synchronized (semaphore) {
			if (withTypeRef == null) {
				withTypeRef = false;
			}
			if (cell != null) {
				if (withTypeRef && DBPromoCellTypeEnum.TIME.getType().equalsIgnoreCase(cell.getType())) {
					return DateTimeUtils.toTime(cell.getValue());
				}
				return cell.getValue();
			}
			return null;
		}
	}

	public static String getValue(final DBPromoAgCell cell) {
		Object o = getValue(cell, null);
		return o != null ? o.toString() : null;
	}

	public static ObjectNode putValue(@NonNull final JsonNode node, @NonNull final String key, final String value,
			@NonNull final Boolean agCell) {
		return putValue((ObjectNode) node, key, value, agCell);
	}

	public static ObjectNode putValue(@NonNull final ObjectNode node, @NonNull final String key, final String value,
			@NonNull final Boolean agCell) {
		if (agCell) {
			JsonNode n = node.get(key);
			if (n != null) {
				((ObjectNode) n).put("value", value);
			}
		} else {
			node.put(key, value);
		}
		return node;
	}

	public static ObjectNode putValueWithFieldName(@NonNull final ObjectNode node, @NonNull final String key,
			final String value, @NonNull final String fieldName) throws IOException {

		JsonNode keyNode = node.get(key);
		if (keyNode != null) {
			if ("editable".equals(fieldName)) {
				((ObjectNode) keyNode).put(fieldName, Boolean.parseBoolean(value));
			} else if ("styleParams".equals(fieldName)) {
				if (value == null) {
					((ObjectNode) keyNode).remove(fieldName);
				} else if (node.get("styleParams") != null) {
					JsonNode styleParamsNode = node.get("styleParams");
					((ObjectNode) styleParamsNode).put(fieldName, value);
				} else {
					JsonNode styleParams = JsonUtils.getMapper().readTree(value);
					((ObjectNode) keyNode).set(fieldName, styleParams);
				}

			} else {
				((ObjectNode) keyNode).put(fieldName, value);
			}
		}

		return node;
	}
}
