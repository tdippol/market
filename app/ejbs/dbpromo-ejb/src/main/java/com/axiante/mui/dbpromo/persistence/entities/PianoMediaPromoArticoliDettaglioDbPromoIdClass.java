package com.axiante.mui.dbpromo.persistence.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PianoMediaPromoArticoliDettaglioDbPromoIdClass implements Serializable {
    private static final long serialVersionUID = -5138559262520604251L;

    String codicePromozione;
    String codiceItem;
    String tipoItem;
    String codiceMeccanica;
    String codiceCondizione;
    String codiceSocieta;
    String codiceZona;
}
