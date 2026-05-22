package com.axiante.tm1.json;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.ForceSuppressColumnsHelper;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgGridDataProducer {
	HashMap<String, String> requiredProperties;

	public AgGridDataProducer(HashMap<String, String> requiredProperties) {
		this.requiredProperties = requiredProperties;
	}
	
	public String transform(Table table) {
		/*
		 * devo generare un array di righe
		 *     ogni riga contiene un oggetto
		 *         ogni elemento dell'oggetto e' il valore di una colonna che ha
		 *           'name' = nome colonna
		 *           'value' = oggetto {attributo:valore, ..., attributo:valore}
		 */
		final StringBuilder builder = new StringBuilder("[");
		table.stream().skip(1).forEach(row->{
			builder.append("{");
				row.stream().forEach(cell->{
					builder.append(cell.getAgGridCell()).append(",");
				});
				builder.deleteCharAt(builder.length()-1);
			builder.append("}").append(",");
		});
		builder.deleteCharAt(builder.length()-1);
		builder.append("]");
		log.debug(builder.toString());
		return builder.toString();
	}

	
	public StringBuilder transform(@NonNull final Row<Cell> row, final String tableName, final int actualDataIndex, @NonNull final ForceSuppressColumnsHelper helper) throws JsonProcessingException {
		return new StringBuilder("{")
					.append(JsonUtils.getMapper().writeValueAsString("RowKeys"))
					.append(":")
					.append(row.getRowKey())
					.append(",")
					.append(helper.updateRow(row))
					.append("}")
		;
	}

}
