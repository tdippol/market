package com.axiante.mui.dbpromo.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
public class MuiPlanoArticoliDbPromoId implements Serializable {
    private static final long serialVersionUID = -8991752844885899662L;

    @Column(name = "ID_PLANO", nullable = false)
    private String idPlano;

    @Column(name = "CODICE_ITEM", nullable = false, insertable = false, updatable = false)
    private Long codiceItem;

    @Column(name = "TIPO_ITEM", nullable = false)
    private String tipoItem;
}
