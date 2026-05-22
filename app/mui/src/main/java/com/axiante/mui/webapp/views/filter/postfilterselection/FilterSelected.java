package com.axiante.mui.webapp.views.filter.postfilterselection;

import com.axiante.mui.webapp.views.filter.FiltredJsonBean;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Named
@Dependent
@Data
@Slf4j
public class FilterSelected implements Serializable, FilterSelectedInterface {

	private static final long serialVersionUID = 806968784266482079L;

	@Getter
	private Map<String, List<FiltredJsonBean>> map;
	@Getter
	private List<String> listDimen = new ArrayList<>();
	private JsonFactory factory = new JsonFactory();

	public static final String DB_PROMO_DIMENSION_NAME = "DBPromozione";

	@Override
	public void parseJsonFilter(String json) {
		List<FiltredJsonBean> filter = new ArrayList<>();
		log.debug("json with filter selected ->" + json);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try (JsonParser parser = factory.createParser(json);) {
				JsonToken jsonToken = parser.nextValue();
				while (!jsonToken.equals(JsonToken.END_OBJECT)) {
					jsonToken = parser.nextValue();
					if (!jsonToken.equals(JsonToken.END_OBJECT)) {
						filter.add(mapper.readValue(parser, FiltredJsonBean.class));
					}
				}
			}
		} catch (IOException e) {
			log.error("error reading json", e);
		}
		this.map = groupingByForFilter(filter);
	}

	@Override
	public Map<String, List<FiltredJsonBean>> groupingByForFilter(List<FiltredJsonBean> filter) {
		Map<String, List<FiltredJsonBean>> map = new HashMap<>();
		for (FiltredJsonBean bean : filter) {
			List<FiltredJsonBean> beans = map.get(bean.getDimension());
			if (beans == null) {
				beans = new ArrayList<>();
			}
			beans.add(bean);
			map.put(bean.getDimension(), beans);
		}
		return map;
	}

	@Override
	public List<String> getDimensions() {
		if (map == null) {
			log.debug("Dimension List is Null");
			return null;
		}
		return map.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public boolean isEnabled(@NonNull final String dimension) {
		List<FiltredJsonBean> beans = map.get(dimension);
		if (beans != null) {
			return beans.stream().filter(Objects::nonNull).map(FiltredJsonBean::getEnabled).filter(Objects::nonNull)
					.filter(Boolean::booleanValue).count() > 0;
		}
		return false;
	}

	@Override
	public void applyEnabledConnections(List<String> enabledConnections) {
		// fix per colorazione filtri dbPromo
		if (map == null ) {
			log.warn("filter map is null");
			return;
		}
		map.keySet().forEach(k -> {
			map.get(k).stream().filter(Objects::nonNull).forEach(filter -> {
				filter.setEnabled(DB_PROMO_DIMENSION_NAME.equalsIgnoreCase(filter.getDimension())
						|| enabledConnections.contains(filter.getServerName()));
				if ( filter.getDimension() == null ) {
					log.warn("the filter" + filter.getAttribute() + " does not have a dimension set. ");
					filter.setDimension("DEFAULT FILTER");
				}
			});
		});
	}

	@Override
	public String getExtraCss(@NonNull final String dimension) {
		if (isEnabled(dimension)) {
			return "";
		}
		return "background-color: #e24142;";
	}

	@Override
	public void parseDBPromoJsonFilter(String json) {
		if (StringUtils.isBlank(json)) {
			log.warn("DBPromo filter invalid; setted to empty json object");
			json = "{}";
		}
		log.debug("json with filter selected ->" + json);
		List<FiltredJsonBean> filter = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			try (JsonParser parser = factory.createParser(json);) {
				JsonToken jsonToken = parser.nextValue();
				while (!jsonToken.equals(JsonToken.END_OBJECT)) {
					jsonToken = parser.nextValue();
					if (!jsonToken.equals(JsonToken.END_OBJECT)) {
						filter.add(mapper.readValue(parser, FiltredJsonBean.class));
					}
				}
			}
		} catch (IOException e) {
			log.error("error reading json", e);
		}
		if (this.map != null) {
			this.map.putAll(groupingByForFilter(filter));
		} else {
			this.map = groupingByForFilter(filter);
		}
	}

}
