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
public class MuiReportBSId implements Serializable {
    private static final long serialVersionUID = 4026416632988853988L;

    @Column(name = "CODICE_PROMOZIONE", nullable = false)
    private String codicePromozione;

    @Column(name = "PREFISSO_BS", nullable = false)
    private String prefissoBS;

}
