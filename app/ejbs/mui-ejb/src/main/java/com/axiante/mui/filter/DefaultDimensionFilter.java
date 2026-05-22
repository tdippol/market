package com.axiante.mui.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@NoArgsConstructor
public class DefaultDimensionFilter {
	String name;
	String code;
	Set<DefaultAttribute> attributes;

	public void addAttribute(@NonNull final String name,@NonNull final String code, @NonNull final String value) {
		if ( getAttributes() == null ) {
			setAttributes(new HashSet<>());
		}
		getAttributes().add(new DefaultAttribute(name, code, value));
	}

	public boolean removeAttribute(String attribute) {
		DefaultAttribute att = findAttributeByCode(attribute);
		if ( att != null ) {
			return getAttributes().remove(att);
		}
		return true;
	}

	DefaultAttribute findAttributeByCode(@NonNull final String code) {
		if ( getAttributes()!= null ) {
			return getAttributes().stream().filter(a->a.code.equals(code)).findFirst().orElse(null);
		}
		return null;
	}
	DefaultAttribute findAttributeByName(@NonNull final String name) {
		if ( getAttributes() != null ) {
			return getAttributes().stream().filter(a->a.name.equals(name)).findFirst().orElse(null);
		}
		return null;
	}

	public List<JsonNode> convertToActualFilter() {
		if ( getAttributes() == null ) {
			return new ArrayList<>();
		}
		return getAttributes().stream().map(att->{
			String key = code+"$"+att.getCode();
			ObjectNode attributeNode = JsonNodeFactory.instance.objectNode();
			attributeNode.put("Dimension_code", code);
			attributeNode.put("Dimension", name );
			attributeNode.put("Attribute_code", att.getCode());
			attributeNode.put("Attribute", att.getCode());
			attributeNode.put("Attribute_desc", att.getName());
			attributeNode.put("defaultFilter", true);
			ArrayNode values = JsonNodeFactory.instance.arrayNode();
			values.add(att.getValue());
			attributeNode.set("selectedValues", values);
			ObjectNode ret = JsonNodeFactory.instance.objectNode();
			ret.set(key,attributeNode);
			return ret;
		}).collect(Collectors.toList());
	}

	@AllArgsConstructor
	@NoArgsConstructor
	public
	static class DefaultAttribute{
		@Getter
		@Setter
		private String name;
		@Getter
		@Setter
		private String code;
		@Getter
		@Setter
		private String value;
	}


}
