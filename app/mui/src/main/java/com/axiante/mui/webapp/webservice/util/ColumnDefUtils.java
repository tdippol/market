package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColumnDefUtils {

	public JsonNode applyFilters(JsonNode columnDef, JsonNode hiddenCols, String gridId)
			throws JsonProcessingException {
		JsonNode configuration = getGridConfiguration(hiddenCols, gridId);
		if (configuration == null) {
			return columnDef;
		}

		return apply(configuration, columnDef);
	}

	public JsonNode applyHiddenColumns(@NonNull InputStream columnDefIs, String hiddenColumnJson, String grid) {
		try {
			if (hiddenColumnJson == null) {
				hiddenColumnJson = "[]";
			}
			return applyFilters(JsonUtils.getMapper().readTree(columnDefIs),
					JsonUtils.getMapper().readTree(hiddenColumnJson), grid);
		} catch (Exception e) {
			log.error("error applying hidden columns", e);
			try {
				columnDefIs.reset();
				return JsonUtils.getMapper().readTree(columnDefIs);
			} catch (Exception ex) {
				log.error("error trying to serialize columnDef to JSON", ex);
				return null;
			}
		}
	}

	public JsonNode applyHiddenColumns(@NonNull String columnDefIs, String hiddenColumnJson, String grid) {
		return applyHiddenColumns(new ByteArrayInputStream(columnDefIs.getBytes()), hiddenColumnJson, grid);
	}

	private JsonNode getGridConfiguration(JsonNode hiddenCols, String gridId) {
		if (hiddenCols.isArray()) {
			for (JsonNode hiddenCol : hiddenCols) {
				if (nodeContains(hiddenCol, gridId) != null) {
					return hiddenCol;
				}
			}
			return null;
		} else {
			return nodeContains(hiddenCols, gridId);
		}
	}

	private JsonNode nodeContains(JsonNode hiddenCol, String gridId) {
		JsonNode node = hiddenCol.get("grid");
		if (node != null && gridId.equals(node.asText())) {
			return hiddenCol;
		}
		return null;
	}

	private JsonNode apply(JsonNode configuration, JsonNode columnDef) {
		JsonNode configurations = configuration.get("colId");

		if (configurations == null) {
			return columnDef;
		}
		if (!configurations.isArray()) {
			return columnDef;
		}
		Map<String, Boolean> configuredFields = new HashMap<>();

		for (JsonNode columnConfiguration : configurations) {
			try {
				configuredFields.put(columnConfiguration.get("name").asText(),
						columnConfiguration.get("visible").asBoolean());
			} catch (Exception e) {
				log.error("Error reading hidden column configuration for node " + columnConfiguration.toString(), e);
			}
		}

		if (configuredFields.size() == 0) {
			return columnDef;
		}

		JsonNode columnDefs = columnDef.get("columnDef");
		if (columnDefs == null) {
			return columnDef;
		}
		if (!columnDefs.isArray()) {
			return columnDef;
		}

		JsonNode field;
		for (JsonNode column : columnDefs) {
			field = column.get("field");
			if (field != null && field.asText() != null && configuredFields.get(field.asText()) != null) {
				if (configuredFields.get(field.asText())) {
					((ObjectNode) column).replace("hide", com.fasterxml.jackson.databind.node.BooleanNode.FALSE);
				} else {
					((ObjectNode) column).replace("hide", com.fasterxml.jackson.databind.node.BooleanNode.TRUE);
				}
			}
		}
		return columnDef;
	}
}
/*
 *
 * "columnDef":[ { "headerName":"Articolo", "field":"articolo", "width":300,
 * "type": "string", "editable":false, "hide":false, "rowGroup":false,
 * "checkboxSelection":false, "headerCheckboxSelection":false,
 * "headerCheckboxSelectionFilteredOnly":false }, { "headerName":"Compratore",
 * "field":"compratore", "width":300, "type": "string", "editable":false,
 * "hide":false, "rowGroup":false, "checkboxSelection":false,
 * "headerCheckboxSelection":false, "headerCheckboxSelectionFilteredOnly":false
 * }, { "headerName":"Fornitore", "field":"fornitore", "width":300, "type":
 * "string", "editable":false, "hide":false, "rowGroup":false,
 * "checkboxSelection":false, "headerCheckboxSelection":false,
 * "headerCheckboxSelectionFilteredOnly":false }, { "headerName":"Reparto",
 * "field":"reparto", "width":200, "type": "string", "editable":false,
 * "hide":false, "rowGroup":false, "checkboxSelection":false,
 * "headerCheckboxSelection":false, "headerCheckboxSelectionFilteredOnly":false
 * }, { "headerName":"Categoria", "field":"categoria", "width":200, "type":
 * "string", "editable":false, "hide":false, "rowGroup":false,
 * "checkboxSelection":false, "headerCheckboxSelection":false,
 * "headerCheckboxSelectionFilteredOnly":false }, { "headerName":"Grm",
 * "field":"grm", "width":300, "type": "string", "editable":false, "hide":false,
 * "rowGroup":false, "checkboxSelection":false, "headerCheckboxSelection":false,
 * "headerCheckboxSelectionFilteredOnly":false }, { "headerName":"Sub Grm",
 * "field":"subGrm", "width":300, "type": "string", "editable":false,
 * "hide":false, "rowGroup":false, "checkboxSelection":false,
 * "headerCheckboxSelection":false, "headerCheckboxSelectionFilteredOnly":false
 * }, { "headerName":"Seleziona tutti", "field":"selected", "width":140, "type":
 * "checkbox", "editable":true, "hide":false, "rowGroup":false,
 * "checkboxSelection":true, "headerCheckboxSelection":true,
 * "headerCheckboxSelectionFilteredOnly":true } ]
 */