package com.axiante.mui.webapp.views.content.dbpromo.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class ItemDto {
    private String codiceItem;
    private String codicePromo;
    private String tipoItem;
}
