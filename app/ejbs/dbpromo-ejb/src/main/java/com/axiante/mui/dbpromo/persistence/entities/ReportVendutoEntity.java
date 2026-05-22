package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "V_MUI_REPORT_VENDUTO", schema = Metadata.SCHEMA)
@NamedQuery(name = "ReportVendutoEntity.findAllByIdPromozione",
        query = "SELECT e FROM ReportVendutoEntity e WHERE e.id.idPromozione = :idPromozione")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class ReportVendutoEntity implements Serializable {
    private static final long serialVersionUID = 6018861096218196914L;

    @EmbeddedId
    private ReportVendutoId id;

    @Column(name = "CONDIZIONE_MECCANICA", precision = 10, scale = 2)
    private BigDecimal condizioneMeccanica;

    @Column(name = "MISURA")
    private String misura;

    @Column(name = "PEZZATURA", precision = 7)
    private Long pezzatura;

    @Column(name = "PEZZI_KG", precision = 31)
    private Long pezziKg;

    @Column(name = "COLLI", precision = 10)
    private Integer colli;

    @Column(name = "VENDUTO_PROMO", precision = 31, scale = 2)
    private BigDecimal vendutoPromo;

    @Column(name = "SCONTO_EROGATO_TOTALE", precision = 31, scale = 2)
    private BigDecimal scontoErogatoTotale;

    @Column(name = "MARGINE_LORDO", precision = 31, scale = 2)
    private BigDecimal margineLordo;

    @Column(name = "MARGINE_LORDO_PERC", precision = 5, scale = 2)
    private BigDecimal margineLordoPerc;

    @Column(name = "REDEMPTION", precision = 31, scale = 2)
    private BigDecimal redemption;

    @Column(name = "PREZZO_PROMO", precision = 10, scale = 2)
    private BigDecimal prezzoPromo;

    @Column(name = "I_O", precision = 1)
    private Integer inOut;

    @Column(name = "IVA", precision = 10, scale = 2)
    private BigDecimal iva;

    @Column(name = "NUMERO_NEGOZI", precision = 10)
    private Integer numeroNegozi;
}
