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
public class MuiReportArticoloId implements Serializable {
    private static final long serialVersionUID = 3203489945270958346L;

    @Column(name = "CODICE_PROMOZIONE", nullable = false)
    private String codicePromozione;

    @Column(name = "ID_ITEM", nullable = false, precision = 16)
    private Long idItem;

    @Column(name = "CODICE_ZONA", nullable = false)
    private String codiceZona;

    @Column(name = "CODICE_MECCANICA", nullable = false)
    private String codiceMeccanica;

    @Column(name = "CODICE_CONDIZIONE", nullable = false)
    private String codiceCondizione;
}
