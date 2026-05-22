package com.axiante.mui.webapp.views.content.dbpromo.data.pojo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@Builder
public class ArticoloInPromoPojo {
    private Long itemId;
    private String codiceArticolo;
    private String descrizioneArticolo;
}
