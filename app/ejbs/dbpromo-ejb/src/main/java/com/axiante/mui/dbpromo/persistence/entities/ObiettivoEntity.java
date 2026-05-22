package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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
@Table(name = "MUI_OBIETTIVO", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "ObiettivoEntity.findAllByPromozione",
                query = "SELECT o FROM ObiettivoEntity o WHERE o.totalizzatore.testata.id = :idPromozione")
})
public class ObiettivoEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 526731090237990504L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_OBIETTIVO_ID_GENERATOR",
            sequenceName = "MUI_OBIETTIVO_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_OBIETTIVO_ID_GENERATOR")
    @Column(name = "ID_OBIETTIVO", unique = true, nullable = false, precision = 16)
    @JsonProperty("idObiettivo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_TOTALIZZATORE", nullable = false)
    private PianificazioneTotalizzatoriEntity totalizzatore;

    @ManyToOne
    @JoinColumn(name = "ID_PROMO_PREMIO", nullable = false)
    private PromozioneTestataEntity promozione;

    @Column(name = "TARGET", precision = 10, scale = 2)
    private BigDecimal target;

    @Column(name = "BUONO_PREMIO", precision = 10, scale = 2)
    private BigDecimal buonoPremio;

    @Column(name = "FL_ATTIVO")
    @Convert(converter = BooleanConverter.class)
    private Boolean flagAttivo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO", nullable = false)
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

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
