package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQuery(name = "ValorePuntoEntity.findWhereDate",
        query = "SELECT e FROM ValorePuntoEntity e WHERE :date BETWEEN e.dataInizio AND e.dataFine")
@Entity
@Table(name = "V_MUI_GET_VALORE_PUNTO", schema = Metadata.SCHEMA)
public class ValorePuntoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COD_PROMO")
    @EqualsAndHashCode.Include
    private String codicePromozione;

    @Column(name = "VALORE_PUNTO")
    @Digits(integer = 3, fraction = 3)
    private BigDecimal valorePunto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO")
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE")
    private Date dataFine;
}
