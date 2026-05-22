package com.axiante.mui.filter.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.DefaultDimensionFilter;
import com.axiante.mui.filter.DefaultFilter;
import com.axiante.mui.filter.FormFilterConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultFilterUtilsTest {

	public static void main(String[] args) throws JsonProcessingException {
		DefaultFilter def = new DefaultFilter();
		DefaultDimensionFilter element = new DefaultDimensionFilter();
		element.setName("promozione");
		element.setCode("Promozione");
		element.addAttribute("anno", "anno_code", "{current_year}");
		def.addFilter(element);

		element = new DefaultDimensionFilter();
		element.setName("reparto");
		element.setCode("Reparto");
		element.addAttribute("anno", "anno_code", "{current_year}");
		def.addFilter(element);
		log.debug(JsonUtils.getMapper().writeValueAsString(def));

		DefaultFilter filter = new DefaultFilter();
		log.debug(JsonUtils.getMapper().writeValueAsString(filter));

		Map<String, List<String>> dimensioni = new HashMap<>();
		dimensioni.put("dimensione_1", Arrays.asList("attributo_1", "attributo_n"));
		dimensioni.put("dimensione_2", Arrays.asList("attributo_1", "attributo_n"));
		dimensioni.put("dimensione_3", Arrays.asList("attributo_1", "attributo_n"));
		log.debug(JsonUtils.getMapper().writeValueAsString(dimensioni));

	}

	//	@Test
	//	public void testReadDefaultsReturnsNullWhenJsonIsWrong() {
	//		assertNull(new DefaultFilterUtils().readDefaults("'defaults':{}"));
	//	}
	//
	//	@Test(expected = NullPointerException.class)
	//	public void testReadDefaultsThrowsExceptionWhenJsonIsNull() {
	//		assertNull(new DefaultFilterUtils().readDefaults(null));
	//	}
	//
	//	@Test
	//	public void testReadDefaultsReturnsNullSetWhenJsonHasNullSet() {
	//		DefaultFilter filter = new DefaultFilterUtils().readDefaults("{'defaults':null}".replace("\'", "\""));
	//		assertNotNull(filter);
	//		assertNull(filter.getDefaults());
	//	}
	//
	//	@Test
	//	public void testReadDefaultsReturnsSetWhenJsonContainsSet() {
	//		DefaultFilter filter = new DefaultFilterUtils().readDefaults(getDefaults());
	//		assertNotNull(filter);
	//		assertNotNull(filter.getDefaults());
	//		assertThat(filter.getDefaults().size(), org.hamcrest.Matchers.is(org.hamcrest.Matchers.greaterThan(0)));
	//		assertThat(filter.getDefaults().stream().map(DefaultDimensionFilter::getName).collect(Collectors.joining(",")),
	//				CoreMatchers.containsString("promozione"));
	//	}

	@Test
	public void testSetDimensionsCorrectlyConverts() {
		final String dims = "{'dimensione_1' : [ 'attributo_1', 'attributo_n' ],'dimensione_n' : [ 'attributo_1', 'attributo_n' ]}"
				.replace("\'", "\"");
		FormFilterUtils utils = new FormFilterUtils();
		FormFilterConfiguration form = utils.computeDimensions(dims);
		assertNotNull(form.getDimensions());
		assertThat(form.getDimensions().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.getDimensions().get("dimensione_1"));
		assertNotNull(form.getDimensions().get("dimensione_n"));
		assertThat(form.getDimensions().get("dimensione_1").size(), CoreMatchers.equalTo(2));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_1"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_n"));
	}

	@Test
	public void testSetDimensionsCorrectlySets() {
		String dims = "{'dimensions':{'dimensione_1' : [ 'attributo_1', 'attributo_n' ],'dimensione_n' : [ 'attributo_1', 'attributo_n' ]}}"
				.replace("\'", "\"");
		FormFilterUtils utils = new FormFilterUtils();
		FormFilterConfiguration form = utils.computeDimensions(dims);
		assertNotNull(form.getDimensions());
		assertThat(form.getDimensions().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.getDimensions().get("dimensione_1"));
		assertNotNull(form.getDimensions().get("dimensione_n"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_1"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_n"));
		dims = "{'dimensions':{'dimensione_1' : [ 'attributo_1','attributo_n' ], 'dimensione_n' : [ 'attributo_1', 'attributo_n' ]  },'defaults':[{'name':'promozione','code':'Promozione','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]},{'name':'reparto','code':'Reparto','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]}]}"
				.replace("\'", "\"");
		form = utils.computeDimensions(dims);
		assertNotNull(form.getDimensions());
		assertThat(form.getDimensions().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.getDimensions().get("dimensione_1"));
		assertNotNull(form.getDimensions().get("dimensione_n"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_1"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_n"));
	}

	@Test
	public void testConvertDoesNotTouchDimensions() {
		final String dims = "{'dimensions':{'dimensione_1' : [ 'attributo_1', 'attributo_n' ],'dimensione_n' : [ 'attributo_1', 'attributo_n' ]}}"
				.replace("\'", "\"");
		FormFilterUtils utils = new FormFilterUtils();
		FormFilterConfiguration form = utils.computeDimensions(dims);
		assertNotNull(form.getDefaults());
		assertNotNull(form.getDimensions());
		assertThat(form.getDimensions().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.getDimensions().get("dimensione_1"));
		assertNotNull(form.getDimensions().get("dimensione_n"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_1"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_n"));
	}

	@Test
	public void testSetDefaultsCorrectlySets() {
		String json = "{'defaults':[{'name':'promozione','code':'Promozione','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]},{'name':'reparto','code':'Reparto','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]}]}"
				.replace("\'", "\"");
		FormFilterUtils utils = new FormFilterUtils();
		FormFilterConfiguration form = utils.computeDefaults(json);
		assertNotNull(form.getDefaults());
		assertThat(form.getDefaults().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.findDefaultDimension("Promozione"));
		assertNotNull(form.findDefaultDimension("Reparto"));

		assertTrue(utils.remove(form, "Promozione", "anno_code"));
		assertTrue(utils.remove(form, "Reparto", "anno_code"));

		json = "{'dimensions':{'dimensione_1' : [ 'attributo_1','attributo_n' ], 'dimensione_n' : [ 'attributo_1', 'attributo_n' ]  },'defaults':[{'name':'promozione','code':'Promozione','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]},{'name':'reparto','code':'Reparto','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]}]}"
				.replace("\'", "\"");
		form = utils.computeDefaults(json);
		assertNotNull(form.getDefaults());
		assertThat(form.getDefaults().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.findDefaultDimension("Promozione"));
		assertNotNull(form.findDefaultDimension("Reparto"));

		assertTrue(utils.remove(form, "Promozione", "anno_code"));
		assertTrue(utils.remove(form, "Reparto", "anno_code"));
	}

	@Test
	public void testConvertReturnsExpeted() {
		String json = "{'dimensions':{'dimensione_1' : [ 'attributo_1', 'attributo_n' ],'dimensione_n' : [ 'attributo_1', 'attributo_n' ]}}"
				.replace("\'", "\"");
		FormFilterUtils utils = new FormFilterUtils();
		FormFilterConfiguration form = utils.convert(json);
		assertNotNull(form);
		assertNotNull(form.getDefaults());
		assertNotNull(form.getDimensions());
		assertThat(form.getDimensions().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.getDimensions().get("dimensione_1"));
		assertNotNull(form.getDimensions().get("dimensione_n"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_1"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_n"));
		json = "{'defaults':[{'name':'promozione','code':'Promozione','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]},{'name':'reparto','code':'Reparto','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]}]}"
				.replace("\'", "\"");
		form = new FormFilterConfiguration();
		form = utils.convert(json);
		assertNotNull(form);
		assertNotNull(form.getDefaults());
		assertThat(form.getDefaults().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.findDefaultDimension("Promozione"));
		assertNotNull(form.findDefaultDimension("Reparto"));
		assertTrue(utils.remove(form, "Promozione", "anno_code"));
		assertTrue(utils.remove(form, "Reparto", "anno_code"));
		json = "{'dimensions':{'dimensione_1' : [ 'attributo_1','attributo_n' ], 'dimensione_n' : [ 'attributo_1', 'attributo_n' ]  },'defaults':[{'name':'promozione','code':'Promozione','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]},{'name':'reparto','code':'Reparto','attributes':[{'name':'anno','code':'anno_code','value':'{current_year}'}]}]}"
				.replace("\'", "\"");
		form = utils.convert(json);
		assertNotNull(form);
		assertNotNull(form.getDefaults());
		assertNotNull(form.getDimensions());
		assertThat(form.getDimensions().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.getDimensions().get("dimensione_1"));
		assertNotNull(form.getDimensions().get("dimensione_n"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_1"));
		assertTrue(form.getDimensions().get("dimensione_1").contains("attributo_n"));
		assertNotNull(form.getDefaults());
		assertThat(form.getDefaults().size(), CoreMatchers.equalTo(2));
		assertNotNull(form.findDefaultDimension("Promozione"));
		assertNotNull(form.findDefaultDimension("Reparto"));
		assertTrue(utils.remove(form, "Promozione", "anno_code"));
		assertTrue(utils.remove(form, "Reparto", "anno_code"));

	}

	String getDefaults() {
		String ret = "{'defaults':[{'name':'promozione','attributes':[{'name':'anno','value':'{current_year}'}]},{'name':'reparto','attributes':[{'name':'anno','value':'{current_year}'}]}]}"
				.replace("\'", "\"");
		return ret;
	}
}
