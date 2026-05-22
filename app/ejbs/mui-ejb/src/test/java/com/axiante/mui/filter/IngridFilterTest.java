package com.axiante.mui.filter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.mdx.objects.FilterImpl;

public class IngridFilterTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test public void testAsJsonReturnsCorrectData() {
		final IngridFilter f = new IngridFilter();
		f.setDimension("test_dimension");
		f.setAttribute("test_attribute");
		f.setValues(Arrays.asList("one","two","three"));
		f.setSelectedValues(Arrays.asList("valueone","valuetwo","valuethree"));
		f.setLabel("test_label");
		final String result = f.asJsonObject();
		assertNotNull(result);
		assertThat(result, containsString("test_dimension"));
		assertThat(result, containsString("test_attribute"));
		assertThat(result, containsString("selected_values"));
		assertThat(result, containsString("[\"valueone\",\"valuetwo\",\"valuethree\"]"));
		assertThat(result, not(containsString("[\"one\",\"two\",\"three\"]")));
		assertThat(result, not(containsString("test_label")));

		//well formed
		try {
			JsonUtils.getMapper().readTree(result);
		} catch (final IOException e) {
			fail(e.getMessage());
		}
	}

	@Test public void testFromJsonStringReturnsCorrectOject() {
		final String json = "{\"grid_name\":\"test_grid\",\"dimension\":\"test_dimension\",\"attribute\":\"test_attribute\",\"selected_values\":[\"valueone\",\"valuetwo\",\"valuethree\"]}";
		final IngridFilter f = IngridFilter.fromJsonString(json);
		assertNotNull(f);
		assertThat(f.getDimension(), equalTo("test_dimension"));
		assertThat(f.getAttribute(), equalTo("test_attribute"));
		assertNull(f.getLabel());
		assertEquals(f.getSelectedValues(),Arrays.asList("valueone","valuetwo","valuethree"));
	}

	@Test public void testFromJsonReturnsNullWhenEmptyJson() {
		assertNull(IngridFilter.fromJsonString(""));
	}

	@Test public void testFromJsonThrowsExceptionWhenNullJson() {
		this.exception.expect(NullPointerException.class);
		IngridFilter.fromJsonString(null);
	}

	@Test public void testFromJsonReturnsNullWhenMissingDimensionInJson() {
		// missing dimension
		final String json = "{\"grid_name\":\"test_grid\",\"attribute\":\"test_attribute\",\"selected_values\":[\"valueone\",\"valuetwo\",\"valuethree\"]}";
		assertNull(IngridFilter.fromJsonString(json));
	}

	@Test public void testFromJsonReturnsObjectWhenEmptySelectedValues() {
		final String json = "{\"grid_name\":\"test_grid\",\"dimension\":\"test_dimension\",\"attribute\":\"test_attribute\"}";
		final IngridFilter f = IngridFilter.fromJsonString(json);
		assertNotNull(f);
		assertNotNull(f.getSelectedValues());
		assertThat(f.getSelectedValues().size(), equalTo(0));
	}

	@Test public void testFromJsonReturnsNullWhenEmptyJsonValue() {
		assertNull(IngridFilter.fromJsonString("{}"));
	}
	@Test public void testFromJsonReturnsNullWhenMissingAttributeInJson() {
		// missing dimension
		final String json = "{\"grid_name\":\"test_grid\",\"dimension\":\"test_dimension\",\"selected_values\":[\"valueone\",\"valuetwo\",\"valuethree\"]}";
		assertNull(IngridFilter.fromJsonString(json));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testHasCodeThrowsException() {
		new IngridFilter().hashCode();
	}

	@Test
	public void testHashCodeReturnsCorrectValue() {
		final String dimension = "dimension";
		final String attribute = "attribute";
		final int expected = Objects.hashCode(dimension+attribute);
		final IngridFilter f = new IngridFilter();
		f.setDimension(dimension);
		f.setAttribute(attribute);
		assertThat(f.hashCode(), equalTo(expected));
	}

	@Test
	public void testIngridFilterEqualsItself() {
		final IngridFilter f = new IngridFilter();
		assertThat(f, equalTo(f));
	}
	@Test public void testIngridFilterNotEqualsNull() {
		assertThat(new IngridFilter(), not(equalTo(null)));
	}
	@Test public void testIngridFilterNotEqualsAnotherObject() {
		assertThat(new IngridFilter(), not(equalTo(new String())));
	}
	@Test
	public void testIngridFilterEqualsIngridFilterWithSameDimensionAndAttribute() {
		final String dimension = "dimension";
		final String attribute = "attribute";
		final IngridFilter f = new IngridFilter();
		f.setDimension(dimension);
		f.setAttribute(attribute);

		final IngridFilter f1 = new IngridFilter();
		f1.setDimension(dimension);
		f1.setAttribute(attribute);

		assertThat(f, equalTo(f1));
	}

	@Test
	public void testIngridFilterNotEqualsIngridFilterWithSameDimensionAndDifferentAttribute() {
		final String dimension = "dimension";
		final String attribute = "attribute";
		final IngridFilter f = new IngridFilter();
		f.setDimension(dimension);
		f.setAttribute(attribute);

		final IngridFilter f1 = new IngridFilter();
		f1.setDimension(dimension);
		f1.setAttribute("different");

		assertThat(f, not(equalTo(f1)));
	}
	@Test
	public void testIngridFilterNotEqualsIngridFilterWithSameAttributeAndDifferentDimension() {
		final String dimension = "dimension";
		final String attribute = "attribute";
		final IngridFilter f = new IngridFilter();
		f.setDimension(dimension);
		f.setAttribute(attribute);

		final IngridFilter f1 = new IngridFilter();
		f1.setDimension("different");
		f1.setAttribute(attribute);

		assertThat(f, not(equalTo(f1)));
	}
	@Test
	public void testIngridFilterAsFilter() {
		final List<String> selectedValues = Arrays.asList("value1","value2","value3");
		final String dimension = "dimension";
		final String attribute = "attribute";
		final IngridFilter f = new IngridFilter();
		f.setDimension(dimension);
		f.setAttribute(attribute);
		f.setSelectedValues(selectedValues);

		final FilterImpl filter = (FilterImpl) f.asFilter();
		assertThat(dimension, equalTo(filter.getColumn()));
		assertThat(attribute, equalTo(filter.getAttribute()));
		assertThat(selectedValues, equalTo(filter.getValues()));
		assertNull(filter.getValue());
	}
}
