package com.axiante.mui.webapp.views.filter.postfilterselection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.axiante.mui.webapp.views.filter.FiltredJsonBean;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;
import org.slf4j.Logger;

@Slf4j
public class FilterSelectedTest implements Serializable {

	private static final long serialVersionUID = 806968784276496079L;
	@Getter
	@Setter
	private FilterSelected filterSelected = new FilterSelected();
	@Getter
	@Setter
	private String json = "{\r\n" + "	\"0\": {\r\n" + "		\"Dimension\": \"Legal Entity\",\r\n"
			+ "		\"Dimension_code\": \"legalentit\",\r\n" + "		\"Dimension_desc\": \"Legal Entity\",\r\n"
			+ "		\"MDXjsonSource\": \"nothing\",\r\n" + "		\"Attribute\": \"Alias\",\r\n"
			+ "		\"Attribute_code\": \"alias\",\r\n" + "		\"Attribute_desc\": \"Alias\",\r\n"
			+ "		\"ServerName\": \"axiante\",\r\n" + "		\"selectedValues\": [\"E016-COTI\", \"E120-INCO\"]\r\n"
			+ "	},\r\n" + "	\"2\": {\r\n" + "		\"Dimension\": \"Legal Entity\",\r\n"
			+ "		\"Dimension_code\": \"legalentity\",\r\n" + "		\"Dimension_desc\": \"Legal Entity\",\r\n"
			+ "		\"MDXjsonSource\": \"nothing\",\r\n" + "		\"Attribute\": \"Location\",\r\n"
			+ "		\"Attribute_code\": \"location\",\r\n" + "		\"Attribute_desc\": \"Location\",\r\n"
			+ "		\"ServerName\": \"axiante\",\r\n"
			+ "		\"selectedValues\": [\"STABIO (TICINO)\", \"BIELLA\"]\r\n" + "	},\r\n" + "	\"3\": {\r\n"
			+ "		\"Dimension\": \"Legal Entity\",\r\n" + "		\"Dimension_code\": \"legalentity\",\r\n"
			+ "		\"Dimension_desc\": \"Legal Entity\",\r\n" + "		\"MDXjsonSource\": \"nothing\",\r\n"
			+ "		\"Attribute\": \"Currency\",\r\n" + "		\"Attribute_code\": \"currency\",\r\n"
			+ "		\"Attribute_desc\": \"Currency\",\r\n" + "		\"ServerName\": \"axiante\",\r\n"
			+ "		\"selectedValues\": [\"EUR\"]\r\n" + "	}\r\n" + "}";

	private List<FiltredJsonBean> parseJsonFilter(JsonFactory factory, Logger log, String json) {
		List<FiltredJsonBean> filter = new ArrayList<FiltredJsonBean>();
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
		return filter;
	}

	@Test
	public void testParseJsonFilterNotEmpty() {
		JsonFactory factory = new JsonFactory();
		List<FiltredJsonBean> filter = parseJsonFilter(factory, log, json);
		assertNotNull(filter);
	}

	@Test
	public void testGroupingByForFilterCreateNotEmptyMap() {
		JsonFactory factory = new JsonFactory();
		List<FiltredJsonBean> filter = parseJsonFilter(factory, log, json);
		Map<String, List<FiltredJsonBean>> map = new HashMap<String, List<FiltredJsonBean>>();
		map = filterSelected.groupingByForFilter(filter);
		assertNotNull(map);
	}

	@Test
	public void testJsonMappingHasCorrectkey() {
		JsonFactory factory = new JsonFactory();
		List<FiltredJsonBean> filter = parseJsonFilter(factory, log, json);
		Map<String, List<FiltredJsonBean>> map = new HashMap<String, List<FiltredJsonBean>>();
		map = filterSelected.groupingByForFilter(filter);

		assertThat(map, IsMapContaining.hasKey("Legal Entity"));
	}

	@Test
	public void testJsonMappingContentIsCorrect() {
		JsonFactory factory = new JsonFactory();
		List<FiltredJsonBean> filter = parseJsonFilter(factory, log, json);
		Map<String, List<FiltredJsonBean>> map = new HashMap<String, List<FiltredJsonBean>>();
		map = filterSelected.groupingByForFilter(filter);

		assertThat(map.get("Legal Entity").get(0).getDimension(), CoreMatchers.equalTo("Legal Entity"));
		assertThat(map.get("Legal Entity").get(0).getAttribute(), CoreMatchers.equalTo("Alias"));
		assertThat(map.get("Legal Entity").get(0).getSelectedValues()[1], CoreMatchers.equalTo("E120-INCO"));
		assertThat(map.get("Legal Entity").get(1).getServerName(), CoreMatchers.equalTo("axiante"));
		assertThat(map.get("Legal Entity").get(1).getDimension(), CoreMatchers.equalTo("Legal Entity"));
		assertThat(map.get("Legal Entity").get(1).getAttribute_code(), CoreMatchers.equalTo("location"));
		assertThat(map.get("Legal Entity").get(2).getDimension(), CoreMatchers.equalTo("Legal Entity"));
		assertThat(map.get("Legal Entity").get(2).getAttribute_code(), CoreMatchers.equalTo("currency"));
		assertThat(map.get("Legal Entity").get(2).getSelectedValues()[0], CoreMatchers.equalTo("EUR"));
	}

	@Test
	public void testListGetDimensionNotEmpty() {
		JsonFactory factory = new JsonFactory();
		List<FiltredJsonBean> filter = parseJsonFilter(factory, log, json);
		Map<String, List<FiltredJsonBean>> map = new HashMap<String, List<FiltredJsonBean>>();
		map = filterSelected.groupingByForFilter(filter);

		List<String> listDimen = map.keySet().stream().collect(Collectors.toList());

		assertNotNull(listDimen);
	}

}
