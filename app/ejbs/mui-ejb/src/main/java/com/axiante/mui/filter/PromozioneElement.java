package com.axiante.mui.filter;

import java.util.ArrayList;
import java.util.List;

import com.axiante.connection.ConnectionProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class PromozioneElement {
	private String code;
	private String columnName;
	private String description;
	private ConnectionProfile server;
	private String mdx;
	@Builder.Default
	private List<FilterAttribute> attributes = new ArrayList<FilterAttribute>();

	public void setServer(@NonNull ConnectionProfile server) {
		this.server = server;
	};

	public boolean containsAttributeCode(String code) {
		return attributes.parallelStream().filter(a -> a.getCode().equals(code)).findAny().orElse(null) != null;
	}

	private FilterAttribute selectedAttribute;
}