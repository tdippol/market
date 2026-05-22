package com.axiante.mui.filter.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.axiante.mui.common.utility.CellNameMapper;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.StreamUtils;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.ConfigurationFilterCatalog;
import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Dependent
public class FilterUtilsImpl implements FilterUtils {
	private static final long serialVersionUID = -60976995639322456L;
	@Inject
	@Getter(value = AccessLevel.PRIVATE)
	ConfigurationFilterCatalog catalog;
	@Override
	public List<Dimension> unpackRowsToQueuedDimension(final String rows) throws JsonParseException, IOException {
		List<Dimension> dimensions = null;
		Map<String, String> data = null;
		if (rows != null) {
			data = JsonUtils.parseData(rows);
		}
		if (data != null) {
			dimensions = data.entrySet().stream().map(e -> new Dimension("", "[" + CellNameMapper.agGrid2map(e.getKey()) + "].["
					+ CellNameMapper.agGrid2map(e.getValue()) + "]", Type.ROWS)).collect(Collectors.toList());
		} else {
			log.error("error parsing rowkeys");
			dimensions = null;
		}
		if (dimensions != null) {
			final Dimension dd = new Dimension("",
					"(" + dimensions.stream().map(Dimension::getValue).collect(Collectors.joining(",")) + ")",
					Type.ROWS);
			dimensions.clear();
			dimensions.add(dd);
		}
		return dimensions;
	}

	@Override
	public List<Dimension> unpackRowsToDimension(final String rows) throws JsonParseException, IOException {
		List<Dimension> dimensions = null;
		Map<String, String> data = null;
		if (rows != null) {
			data = JsonUtils.parseData(rows);
		}
		if (data != null) {
			dimensions = data.entrySet().stream().map(e -> new Dimension("", "{[" + CellNameMapper.agGrid2map(e.getKey()) + "].["
					+ CellNameMapper.agGrid2map(e.getValue()) + "]}", Type.ROWS)).collect(Collectors.toList());
		} else {
			log.error("error parsing rowkeys");
			dimensions = null;
		}
		return dimensions;
	}

	@Override
	public List<String> unpack(final String rows, final String columns) {
		List<String> dimensions = null;
		try {
			dimensions = this.unpackRows(rows);
			if ((dimensions != null) && (columns != null)) {
				try {
					dimensions.addAll(0, this.unpackColumns(columns));
				} catch (final NullPointerException npe) {
					// there was an error in the column parsing
					log.error("Error parsing columns data", npe);
					dimensions = null;
				}
			} else {
				log.error("error parsing rowkeys");
			}
		} catch (final Exception e) {
			log.error("error parsing data", e);
		}
		return dimensions;
	}

	protected List<String> unpackRows(final String rows) throws JsonParseException, IOException {
		List<String> dimensions = null;
		Map<String, String> data = null;
		if (rows != null) {
			data = JsonUtils.parseData(rows);
		}
		if (data != null) {
			dimensions = new ArrayList<>();
			dimensions = data.entrySet().stream().map(e -> ("Dimensions('" + CellNameMapper.agGrid2map(e.getKey()) + "')/Hierarchies('"
					+ CellNameMapper.agGrid2map(e.getKey()) + "')/Elements('"
					+ CellNameMapper.agGrid2map(e.getValue()) + "')")).collect(Collectors.toList());
		} else {
			log.error("error parsing rowkeys");
			dimensions = null;
		}
		return dimensions;
	}

	protected List<String> unpackColumns(@NonNull final String columns) {
		final List<String> dimensions = new ArrayList<>();
		final List<String> cols = new ArrayList<>();
		final AtomicBoolean error = new AtomicBoolean(false);
		if (columns.indexOf("$$") > -1) {
			cols.addAll(Arrays.asList(columns.split(Pattern.quote("$$"))));
		} else {
			cols.add(columns);
		}
		cols.forEach(col -> {
			final String[] vals = col.split(Pattern.quote("$"));
			if (vals.length != 2) {
				log.error("malformed column expression " + col);
				error.set(true);
				return;
			} else {
				dimensions.add("Dimensions('" + CellNameMapper.agGrid2map(vals[0]) + "')/Hierarchies('"
						+ CellNameMapper.agGrid2map(vals[0]) + "')/Elements('" + CellNameMapper.agGrid2map(vals[1])
						+ "')");
			}
		});
		if (error.get()) {
			return null;
		}
		return dimensions;
	}

	@Override
	public String removeUnchangedFiltersFromStore(final String storedFiltersJson,
			final List<ConfigurationElement> configFiltersList, final String selectedFiltersJson) {
		// filtri disponibli per la maschera
		final List<String> filterableDimensions = StreamUtils.asStream(configFiltersList.iterator())
				.map(ConfigurationElement::getCode).distinct().collect(Collectors.toList());
		final Map<String, JsonNode> map = new TreeMap<>();

		try {

			JsonNode root = JsonUtils.getMapper().readTree(storedFiltersJson);
			Iterator<JsonNode> iterator = root.iterator();
			while (iterator.hasNext()) {
				final JsonNode node = iterator.next();
				final List<String> dimensionCodes = node.findValuesAsText("Dimension_code");
				dimensionCodes.forEach(dimension -> {
					map.put(dimension, node);
				});
			}

			filterableDimensions.stream().filter(Objects::nonNull).forEach(dimension -> {
				map.remove(dimension);
			});
			// a questo punto mi sono tenuto buoni i filtri che non sono in maschera
			// corrente
			root = JsonUtils.getMapper().readTree(selectedFiltersJson);
			iterator = root.iterator();
			while (iterator.hasNext()) {
				final JsonNode node = iterator.next();
				final List<String> dimensionCodes = node.findValuesAsText("Dimension_code");
				dimensionCodes.stream().filter(dimensionCode -> !"promozioneDBPromo".equals(dimensionCode))
				/* Escludo DBPromo filter ->
				 * questo metodo è usato solo dai filtri MUI
				 */
				.forEach(dimension -> {
					map.put(dimension
							+ (node.has("Attribute_code") ? "$" + node.get("Attribute_code").asText() : ""),
							node);
				});
			}

			return JsonUtils.getMapper().writeValueAsString(map);
		} catch (final JsonProcessingException e) {
			log.error("error deserializing object", e);
			return null;
		} catch (final IOException e) {
			log.error("error calculating filter", e);
		}
		return null;
	}

	@Override
	public String removeUnchangedDBPromoFiltersFromStore(String storedFiltersJson,
			List<ConfigurationElement> configFiltersList, String selectedFiltersJson) {
		final Map<String, JsonNode> map = new TreeMap<>();
		try {
			JsonNode root = JsonUtils.getMapper().readTree(selectedFiltersJson);
			Iterator<JsonNode> iterator = root.iterator();
			while (iterator.hasNext()) {
				final JsonNode node = iterator.next();
				final List<String> dimensionCodes = node.findValuesAsText("Dimension_code");
				final String dimension = dimensionCodes.stream()
						.filter("promozioneDBPromo"::equals).findFirst().orElse(null);
				if (dimension != null) {
					map.put(dimension + (node.has("Attribute_code") ? "$" + node.get("Attribute_code").asText() : ""),
							node);
				}
			}

			return JsonUtils.getMapper().writeValueAsString(map);
		} catch (final IOException e) {
			log.error("error calculating DBPromo filter", e);
		} catch (Exception e) {

		}
		return null;
	}



	static final String CURRENT_YEAR= "\\{current_year\\}";

}
