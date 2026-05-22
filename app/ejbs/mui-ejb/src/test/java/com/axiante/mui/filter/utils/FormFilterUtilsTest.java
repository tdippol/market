package com.axiante.mui.filter.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.FormFilterConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@RunWith(MockitoJUnitRunner.class)
public class FormFilterUtilsTest {

	@Test
	public void testMapping() throws JsonProcessingException, IOException {
		String filter = "{'promozione': ['anno', 'canale', 'tipo', 'descrizione', 'riferimento', 'semestre', 'proximity'],	'reparto': ['descrizione'],	'scenario': ['descrizione']}"
				.replace("'", "\"");

		JsonNode node = JsonUtils.getMapper().readTree(filter);
		FormFilterUtils utils = new FormFilterUtils();
		assertNotNull(utils.computeDefaults(node));
	}

	@Test
	public void testConvert() throws JsonProcessingException, IOException {
		String filter = "{'promozione': ['anno', 'canale', 'tipo', 'descrizione', 'riferimento', 'semestre', 'proximity'],	'reparto': ['descrizione'],	'scenario': ['descrizione']}"
				.replace("'", "\"");

		JsonNode node = JsonUtils.getMapper().readTree(filter);
		FormFilterUtils utils = new FormFilterUtils();
		FormFilterConfiguration result = utils.convert(node);
		assertNotNull(result);
		assertNotNull(result.getDefaults());
		assertThat(result.getDefaults().size(), CoreMatchers.equalTo(0));
		assertThat(result.getDimensions().size(), CoreMatchers.equalTo(3));
		assertTrue(result.getDimensions().keySet().contains("promozione"));
		assertTrue(result.getDimensions().keySet().contains("reparto"));
		assertTrue(result.getDimensions().keySet().contains("scenario"));
		assertTrue(result.getDimensions().get("promozione").contains("riferimento"));
	}
}
