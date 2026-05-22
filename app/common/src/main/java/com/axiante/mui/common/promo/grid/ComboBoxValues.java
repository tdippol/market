package com.axiante.mui.common.promo.grid;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComboBoxValues {
    private String key;
    private String label;
    public static final ComboBoxValues BLANK = ComboBoxValues.builder().key("").label("").build();

    public ComboBoxValues() {
    };
}
