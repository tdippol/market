package com.axiante.utility.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaticColumnDef {
	// campi che servono nella generazione del json
	@JsonInclude(Include.NON_NULL)
	String headerName;
	@JsonInclude(Include.NON_NULL)
	String field;
	@JsonInclude(Include.NON_NULL)
	Integer width;
	@JsonInclude(Include.NON_NULL)
	Boolean editable;
	@JsonInclude(Include.NON_NULL)
	String columnGroupShow;
	@JsonInclude(Include.NON_NULL)
	String aggFunc;
	@JsonInclude(Include.NON_NULL)
	String aggFuncParam;
	@JsonInclude(Include.NON_NULL)
	String aggFuncParam2;
	@JsonInclude(Include.NON_NULL)
	String aggFuncParam3;
	@JsonInclude(Include.NON_NULL)
	String[] type;
	@JsonInclude(Include.NON_NULL)
	Boolean hide;
	@JsonInclude(Include.NON_NULL)
	Boolean rowGroup;
	@JsonInclude(Include.NON_NULL)
	Boolean marryChildren;
	@JsonInclude(Include.NON_NULL)
	String headerClass;
	@JsonInclude(Include.NON_NULL)
	Boolean openByDefault;
	@JsonInclude(Include.NON_NULL)
	String cellClass;
	@JsonInclude(Include.NON_NULL)
	StaticColumnDef[] children;
	@JsonIgnore
	String[] customHeaders;
}
