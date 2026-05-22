package com.axiante.mui.filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Spy;

import com.axiante.mui.filter.DefaultDimensionFilter.DefaultAttribute;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DefaultDimensionFilterTest {
	@Spy
	DefaultDimensionFilter tested;

	@Test(expected = NullPointerException.class)
	public void test_whenAddAttributeHasNullValueThenThrowsException() {
		tested=new DefaultDimensionFilter();
		tested.addAttribute("someName", "someCode", null);
	}
	@Test(expected = NullPointerException.class)
	public void test_whenAddAttributeHasNullCodeThenThrowsException() {
		tested=new DefaultDimensionFilter();
		tested.addAttribute("someName", null, "someValue");
	}
	@Test(expected = NullPointerException.class)
	public void test_whenAddAttributeHasNullNameThenThrowsException() {
		tested=new DefaultDimensionFilter();
		tested.addAttribute(null, "someCode", "someValue");
	}
	@Test
	public void test_whenAddThenElementIsAdded() {
		tested=new DefaultDimensionFilter();
		final int initialSize= tested.getAttributes() == null?0:
			tested.getAttributes().size();
		tested.addAttribute("name", "code", "value");
		assertThat(tested.getAttributes(), CoreMatchers.notNullValue());
		assertThat(tested.getAttributes().size(), equalTo(initialSize+1));
	}
	@Test
	public void test_removeAttributeReturnsTrueIfAttributeNotInList() {
		tested=new DefaultDimensionFilter();
		// per costruzione non ci sono attibuti
		assertThat(tested.getAttributes(), CoreMatchers.nullValue());
		assertTrue(tested.removeAttribute("something"));
	}
	@Test
	public void test_removeAttributeReturnsTrueIfAttributeInList() {
		tested=new DefaultDimensionFilter();
		// per costruzione non ci sono attibuti
		assertThat(tested.getAttributes(), CoreMatchers.nullValue());
		tested.addAttribute("name", "code", "value");
		assertThat(tested.getAttributes(), CoreMatchers.notNullValue());
		assertThat(tested.getAttributes().size(), equalTo(1));
		assertTrue(tested.removeAttribute("code"));
		assertThat(tested.getAttributes(), CoreMatchers.notNullValue());
		assertThat(tested.getAttributes().size(), equalTo(0));
	}

	@Test
	public void test_whenNullListFindAttributeByCodeReturnsNull() {
		tested=new DefaultDimensionFilter();
		assertThat(tested.findAttributeByCode("code"), CoreMatchers.nullValue());
	}

	@Test
	public void test_whenEmptyListFindAttributeByCodeReturnsNull() {
		tested=new DefaultDimensionFilter();
		tested.setAttributes(new HashSet<DefaultDimensionFilter.DefaultAttribute>());
		assertThat(tested.findAttributeByCode("code"), CoreMatchers.nullValue());
	}
	@Test
	public void test_whenAttributeCodeIsPresentThenFindAttributeRetunsTheAttribute() {
		tested=new DefaultDimensionFilter();
		tested.addAttribute("name","code","value");
		DefaultAttribute found = tested.findAttributeByCode("code");
		assertThat(found, CoreMatchers.notNullValue());
		assertThat(found.getCode(),CoreMatchers.equalTo("code"));
	}
	@Test
	public void test_whenAttributeCodeIsNotPresentThenFindAttributeRetunsNull() {
		tested=new DefaultDimensionFilter();
		tested.addAttribute("name","code","value");
		DefaultAttribute found = tested.findAttributeByCode("code1");
		assertThat(found, CoreMatchers.nullValue());
	}


	@Test
	public void test_whenNullListFindAttributeByNameReturnsNull() {
		tested=new DefaultDimensionFilter();
		assertThat(tested.findAttributeByName("name"), CoreMatchers.nullValue());
	}

	@Test
	public void test_whenEmptyListFindAttributeByNameReturnsNull() {
		tested=new DefaultDimensionFilter();
		tested.setAttributes(new HashSet<DefaultDimensionFilter.DefaultAttribute>());
		assertThat(tested.findAttributeByName("name"), CoreMatchers.nullValue());
	}
	@Test
	public void test_whenAttributeNameIsPresentThenFindAttributeRetunsTheAttribute() {
		tested=new DefaultDimensionFilter();
		tested.addAttribute("name","code","value");
		DefaultAttribute found = tested.findAttributeByName("name");
		assertThat(found, CoreMatchers.notNullValue());
		assertThat(found.getName(),CoreMatchers.equalTo("name"));
	}
	@Test
	public void test_whenAttributeNameIsNotPresentThenFindAttributeRetunsNull() {
		tested=new DefaultDimensionFilter();
		tested.addAttribute("name","code","value");
		DefaultAttribute found = tested.findAttributeByName("name1");
		assertThat(found, CoreMatchers.nullValue());
	}

	@Test
	public void test_whenNullAttributeListThenConvertReturnsEmptyList() {
		tested=new DefaultDimensionFilter();
		assertThat(tested.getAttributes(), CoreMatchers.nullValue());
		List<JsonNode> result = tested.convertToActualFilter();
		assertThat(result,CoreMatchers.notNullValue());
		assertThat(result.size(),CoreMatchers.equalTo(0));
	}
	@Test
	public void test_whenEmptyAttributeListThenConvertReturnsEmptyList() {
		tested=new DefaultDimensionFilter();
		tested.setAttributes(new HashSet<DefaultDimensionFilter.DefaultAttribute>());
		assertThat(tested.getAttributes(), CoreMatchers.notNullValue());
		List<JsonNode> result = tested.convertToActualFilter();
		assertThat(result,CoreMatchers.notNullValue());
		assertThat(result.size(),CoreMatchers.equalTo(0));
	}

	@Test
	public void test_whenAttributeListThenConvertReturnsList() {
		tested=new DefaultDimensionFilter();
		tested.setName("test");
		tested.setCode("testCode");
		tested.addAttribute("name", "code", "value");
		assertThat(tested.getAttributes(), CoreMatchers.notNullValue());
		List<JsonNode> result = tested.convertToActualFilter();
		assertThat(result,CoreMatchers.notNullValue());
		assertThat(result.size(),CoreMatchers.equalTo(tested.getAttributes().size()));
		ObjectNode test = (ObjectNode)((ObjectNode)result.get(0)).get("testCode$code");
		assertThat(test, CoreMatchers.notNullValue());
		assertThat(test.get("Dimension_code").asText(),CoreMatchers.equalTo("testCode"));
		assertThat(test.get("Attribute_code").asText(),CoreMatchers.equalTo("code"));
		assertThat(test.get("Attribute_desc").asText(),CoreMatchers.equalTo("name"));
		assertThat(test.get("selectedValues").isArray(),CoreMatchers.equalTo(true));


	}

}
