package com.axiante.mui.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.mdx.filter.Filter;
import com.axiante.tm1.mdx.objects.FilterImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class IngridFilter {
	@JsonIgnore(value = true)
	private String label;
	private String dimension;
	private String attribute;
	@JsonProperty(value = "selected_values",required=true)
	private List<String> selectedValues = new ArrayList<>();
	@JsonIgnore(value = true)
	private List<String> values;
	/**
	 * serialize the object in order to make it
	 * ready for database persistence
	 * @param grid
	 * @return
	 */
	public String asJsonObject() {
		try {
			return JsonUtils.getMapper().writeValueAsString(this);
		} catch (IOException e) {
			log.error("error serializing IngridFilter", e);
			return null;
		}
	}
	/**
	 * Helper method: creates an IngridFilter from
	 * a json source of the form :
	 * <code>
	 * {
	 *  "dimension":"dimension_name",
	 *  "attribute":"attribute_name",
	 *  "selected_values":["value_one", "value_two", ..., "value_n"]
	 * }
	 * </code>
	 * Any extra value is ignored. If the parameter "selected_values" is null it is initialized to an empty array
	 * @param json
	 * @return
	 */
	public static IngridFilter fromJsonString(@NonNull final String json) {
		try {
			IngridFilter filter = JsonUtils.getMapper().readValue(json, IngridFilter.class);
			// check mandatory fields
			if (filter.getDimension() == null || filter.getAttribute() == null ) {
				return null;
			}
			return filter;
		} catch (IOException e ){
			log.error("error deserializing IngridFilter", e);
		}

		return null;
	}

	@Override
	public int hashCode() {
		if ( this.dimension != null && this.attribute != null ) {
			return Objects.hashCode(this.dimension+this.attribute);
		} else {
			throw new IllegalArgumentException("invalid instance of InGridFilter");
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		IngridFilter other = (IngridFilter) obj;
		return
				Objects.equals(this.getDimension(), other.getDimension()) &&
				Objects.equals(this.getAttribute(), other.getAttribute()) ;
	}

	public Filter asFilter() {
		FilterImpl filter = new FilterImpl();
		filter.setDimension(this.getDimension());
		filter.setAttribute(this.getAttribute());
		filter.setValues(this.getSelectedValues());

		return filter;
	}

	public String getCode() {
		final StringBuffer b = new StringBuffer("");
		if ( getDimension() != null ) {
			b.append(getDimension().replaceAll(" ", "_"));
		}
		b.append("_");
		if ( getAttribute() != null ) {
			b.append(getAttribute().replace(" ", "_") );
		}

		return b.toString();
	}
}
