package com.axiante.mui.dbpromo.persistence.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PianoMediaPromoArticoliDbPromoIdClass implements Serializable {
    private static final long serialVersionUID = -1094790487408890226L;
    
    String codicePromozione;
    String codiceItem;
    String tipoItem;
}
