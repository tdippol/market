package com.axiante.mui.dbpromo.actions;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Action {

    @EqualsAndHashCode.Include
    @Getter
    private ActionEnum code;

    @Getter
    private ActionTypeEnum type;

    @Singular
    @Getter
    private List<FormEnum> forms;

    @Singular
    @Getter
    private List<ElementFieldEnum> fields;
}
