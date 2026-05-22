package com.axiante.mui.common.promo.grid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DBPromoColumnlDef {

	@Builder.Default
	private final String headerName = "";

	@Builder.Default
	private final String field = "";

	@Builder.Default
	@Setter
	private Boolean editable = true;

	@Builder.Default
	private final Integer width = 0;

	@Builder.Default
	private final Boolean hide = false;

	@Builder.Default
	private final Boolean rowGroup = false;

	@Builder.Default
	private final boolean checkboxSelection = false;

	@Builder.Default
	private final boolean headerCheckboxSelection = false;

	@Builder.Default
	private final boolean headerCheckboxSelectionFilteredOnly = false;

	@JsonProperty("type")
	@Builder.Default
	private final String columnType = DBPromoColTypeEnum.STRING.getType();

	private final String pinned;
}
