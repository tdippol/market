package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "MUI_PROMOZIONE_COSTI_CONTATTO", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "PromozioneCostiContattoEntity.countByIdPromozione",
                query = "select count(e) from PromozioneCostiContattoEntity e where e.promozione.id = :idPromozione"),
        @NamedQuery(name = "PromozioneCostiContattoEntity.findByIdPromozione",
                query = "select e from PromozioneCostiContattoEntity e where e.promozione.id = :idPromozione")
})
public class PromozioneCostiContattoEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = -9086817799840934049L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMO_COSTI_CONT_GENERATOR",
            sequenceName = "MUI_PROMO_COSTI_CONT_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMO_COSTI_CONT_GENERATOR")
    @Column(name = "ID", unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE")
    private PromozioneTestataEntity promozione;

    @Column(name = "VALORE_COSTO_CONTATTO", precision = 6, scale = 2)
    private BigDecimal valoreCostoContatto;

    @Column(name = "VALORE_RETT_COSTO_CONTATTO", precision = 6, scale = 2)
    private BigDecimal valoreRettCostoContatto;

    @Column(name = "NUMERO_CONTATTI")
    private Integer numeroContatti;

    @Column(name = "IMPORTO_TOTALE", precision = 10, scale = 2)
    private BigDecimal importoTotale;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
    private String codiceUtenteAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }
}
