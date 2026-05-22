package com.axiante.mui.common.promo.grid;

import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.promo.params.WebserviceParams;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class DBPromoAgCell {
	@JsonIgnore
	private String name;

	private String type;
	private Boolean nullable;

	@Setter
	private String value;

	@Setter
	private String[] picklistValues;

	@Setter
	private WebserviceParams picklistService;

	@Setter
	private Boolean editable;

	@Setter
	private DataTypeParams dataTypeParams;

	@Setter
	private List<ComboBoxValues> comboBoxValues;

	@Setter
	private WebserviceParams comboBoxService;

	@Setter
	private List<Long> hierarchy;

	@Setter
	private String formula;

	@Setter
	private String defValue;

	@Setter
	@Builder.Default
	private Boolean invisible = Boolean.FALSE;

	@Setter
	@Builder.Default
	private Boolean mandatory = Boolean.FALSE;

	@Setter
	@Builder.Default
	private Boolean warning = Boolean.FALSE;

	@Setter
	@Builder.Default
	private Boolean txtMandatory = Boolean.FALSE;

	@Setter
	@Builder.Default
	private Boolean txtWarning = Boolean.FALSE;

	@Setter
	private List<String> extraClasses;
}