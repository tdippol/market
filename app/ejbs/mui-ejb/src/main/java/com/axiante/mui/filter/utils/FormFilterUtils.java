package com.axiante.mui.filter.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.DefaultDimensionFilter;
import com.axiante.mui.filter.FormFilterConfiguration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormFilterUtils {
	public boolean remove(@NonNull final FormFilterConfiguration from, @NonNull final String dimension,
			@NonNull final String attribute) {
		DefaultDimensionFilter d = findDefaultDimension(dimension, from);
		if (d != null) {
			return d.removeAttribute(attribute);
		}
		return false;
	}

	private DefaultDimensionFilter findDefaultDimension(@NonNull final String dimension,
			@NonNull final FormFilterConfiguration in) {
		if (in.getDefaults() != null) {
			return in.getDefaults().stream().filter(d -> d.getCode().equals(dimension)).findFirst().orElse(null);
		}
		return null;
	}

	public FormFilterConfiguration purgeDefaults(@NonNull final FormFilterConfiguration from) {
		List<DefaultDimensionFilter> removeable = from.getDefaults().stream().filter(d -> d.getAttributes().size() == 0)
				.collect(Collectors.toList());
		from.getDefaults().removeAll(removeable);
		return from;
	}

	public FormFilterConfiguration computeDimensions(String json) {
		try {
			return computeDimensions(JsonUtils.getMapper().readTree(json));
		} catch (Exception e) {
			log.error("error converting json to dimensions", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@JsonIgnore
	public FormFilterConfiguration computeDimensions(JsonNode n) {
		try {
			FormFilterConfiguration form = new FormFilterConfiguration();
			Map<String, List<String>> result;
			JsonNode node = get(FilterUtils.DIMENSIONS_ATTRIBUTE, n);
			if (node != null) {
				// il json e' gia' una dimensione
				result = JsonUtils.getMapper().convertValue(node, new TypeReference<Map<String, List<String>>>() {
				});
			} else {
				result = JsonUtils.getMapper().convertValue(n, Map.class);
				if (result != null) {
					result.remove(FilterUtils.DEFAULTS_ATTRIBUTE);
				}
			}
			form.setDimensions(result);
			form.setDefaults(new HashSet<DefaultDimensionFilter>());
			return form;
		} catch (Exception e) {
			log.error("error converting json to dimensions", e);
		}
		return null;
	}

	/**
	 * converte il json dei default in oggetti java
	 * 
	 * @param json
	 * @return
	 */
	@JsonIgnore
	public FormFilterConfiguration computeDefaults(String json) {
		try {
			return computeDefaults(JsonUtils.getMapper().readTree(json));
		} catch (Exception e) {
			log.error("error converting json to dimensions", e);
		}
		return null;
	}

	/**
	 * converte il json dei default in oggetti java
	 * 
	 * @param json
	 * @return
	 */
	@JsonIgnore
	public FormFilterConfiguration computeDefaults(JsonNode n) {
		try {
			FormFilterConfiguration form = new FormFilterConfiguration();
			Set<DefaultDimensionFilter> result;
			JsonNode node = get(FilterUtils.DEFAULTS_ATTRIBUTE, n);

			if ((node != null)) {
				// il json e' gia' un defaults
				result = JsonUtils.getMapper().convertValue(node, new TypeReference<Set<DefaultDimensionFilter>>() {
				});
			} else {
				// Se non c'e' il nodo "defaults" allora lo devo creare vuoto
				// result = JsonUtils.getMapper().convertValue(n, Set.class);
				result = new HashSet<DefaultDimensionFilter>();
			}
			form.setDefaults(result);
			form.setDimensions(new HashMap<>());
			return form;
		} catch (Exception e) {
			log.error("error converting json to dimensions", e);
		}
		return null;
	}

	public FormFilterConfiguration convert(String json) {
		if ((json != null) && "{}".equals(json.trim())) {
			// configurazione vuota
			return new FormFilterConfiguration().initializeAsEmpty();
		}
		try {
			return convert(JsonUtils.getMapper().readTree(json));
		} catch (IOException e) {
			log.error("error reading json", e);
		}
		return null;
	}

	public FormFilterConfiguration convert(JsonNode n) {
		FormFilterConfiguration dims = computeDimensions(n);
		FormFilterConfiguration defs = computeDefaults(n);
		if (dims != null) {
			if (defs != null) {
				dims.setDefaults(defs.getDefaults());
				defs = null;
			} else {
				dims.setDefaults(new HashSet<DefaultDimensionFilter>());
			}
			return dims;
		}
		return defs;
	}

	private JsonNode get(@NonNull final String attribute, @NonNull final JsonNode from) {
		JsonNode node = from.get(attribute);
		if (node != null) {
			if (node.isNull()) {
				return null;
			}
			if (node.isObject() && !node.fields().hasNext()) {
				return null;
			}
			return node;
		}
		return null;
	}

}
