package com.axiante.mui.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axiante.connection.ConnectionProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class ConfigurationElement implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -4350621205607668155L;
	private String code;
	private String columnName;
	private String description;
	private ConnectionProfile server;
	private String mdx;
	@Builder.Default
	private boolean available = true;
	@Builder.Default
	private List<FilterAttribute> attributes = new ArrayList<FilterAttribute>();
	private FilterAttribute selectedAttribute;

	public void setServer(@NonNull ConnectionProfile server) {
		this.server = server;
	};

	public boolean containsAttributeCode(String code) {
		return attributes.stream().filter(a -> a.getCode().equals(code)).findAny().orElse(null) != null;
	}
}