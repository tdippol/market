package com.axiante.mui.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.model.FilterProducer;
import com.axiante.tm1.mdx.filter.Filter;
import com.axiante.tm1.mdx.objects.FilterImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class FilterProducerImpl implements FilterProducer {
	@Override
	public List<Filter> getFilters(@NonNull String json) {
		final List<Filter> ret = new ArrayList<Filter>();
		log.debug("json to transform " + json);
		if (json.length() > 0) {
			try (JsonParser parser = JsonUtils.getFactory().createParser(json);) {
				JsonToken current = parser.nextToken();
				while (current != null && current != JsonToken.END_OBJECT) {
					ret.add(readObject(parser));
					current = parser.nextToken();
				}
			} catch (IOException e) {
				log.error("Error reading json : \n" + json + " \n Exception :", e);
			}
		}
		return ret;
	}

	protected Filter readObject(JsonParser parser) throws IOException {
		String dimension = null;
		String attribute = null;
		List<String> values = null;
		JsonToken current = parser.nextToken();
		while (current != JsonToken.END_OBJECT) {
			if (current == JsonToken.FIELD_NAME) {
				switch (parser.getCurrentName()) {
				case "Dimension":
					dimension = parser.nextTextValue();
					break;
				case "Attribute":
					attribute = parser.nextTextValue();
					break;
				case "selectedValues":
					values = readValues(parser);
					break;
				default:
					break;
				}
			}
			current = parser.nextToken();
		}
		if (values != null && values.size() > 0 && dimension != null && dimension.trim().length() > 0
				&& attribute != null && attribute.length() > 0) {

			FilterImpl f = new FilterImpl();
			f.setValues(values);
			f.setDimension(dimension);
			f.setAttribute(attribute);
			return f;
		}

		return null;
	}

	private List<String> readValues(JsonParser parser) throws IOException {
		List<String> values = new ArrayList<>();
		JsonToken current = parser.nextToken();
		String value = null;
		while (current != JsonToken.END_ARRAY) {
			value = parser.nextTextValue(); // se sono alla fine dell'array qui c'é null
			current = parser.getCurrentToken(); // dopo la lettura di prima, alla fine dell'array, c'é END_ARRAY
			if (value != null) {
				values.add(value);
			}

		}
		return values;
	}
}
