package com.axiante.mui.common.promo;

import com.axiante.mui.common.utility.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserFilterUtils {

	/**
	 * Questo metodo crea una mappa dai filtri dell'utente corrente in modo che
	 * possano essere applicati nella query di recupero delle promozioni
	 *
	 * @param dbFiltersJson
	 * @return
	 */
	public Map<String, String> createUserFiltersMapToQueryString(String dbFiltersJson) {
		final Map<String, String> userFiltersMap = new TreeMap<>();

		if (dbFiltersJson != null) {
			JsonNode root;
			try {
				root = JsonUtils.getMapper().readTree(dbFiltersJson);
				if (root != null) {
					Iterator<JsonNode> iterator = root.iterator();
					while (iterator.hasNext()) {
						final JsonNode node = iterator.next();
						List<JsonNode> attributes = node.findValues("Attribute");
						List<JsonNode> selectedValues = node.findValues("selectedValues");
						String attribute = attributes.get(0).asText();
//						List<JsonNode> types = node.findValues("Attribute_type");
//						String type = "dropDown";
//						if (types != null ) {
//							type = types.get(0).asText();
//						}
//						boolean singlePoint = "datePicker".equals(type);
						String value = "";
//						if ( singlePoint) {
//							value = selectedValues.get(0).asText().replace("'", "");
//						} else { 
							final StringJoiner joiner = new StringJoiner(",");
							selectedValues.get(0).forEach(filter -> joiner.add(filter.asText()));
							value = joiner.toString();
//						}
						userFiltersMap.put(attribute, value);
					}
				}
			} catch (IOException e) {
				log.error("Error map PromozioneTestataEntity into JSON object", e);
			}

		}

		return userFiltersMap;
	}
}
