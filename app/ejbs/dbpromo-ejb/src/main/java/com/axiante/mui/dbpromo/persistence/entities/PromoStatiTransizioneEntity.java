package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_PROMO_STATI_TRANSIZIONE database table.
 */
@Entity
@Table(name = "MUI_PROMO_STATI_TRANSIZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "PromoStatiTransizioneEntity.findAll", query = "SELECT m FROM PromoStatiTransizioneEntity m"),
        @NamedQuery(name = "PromoStatiTransizioneEntity.findByIdAndStatus",
                query = "SELECT m FROM PromoStatiTransizioneEntity m WHERE m.promozioneTestataEntity.id = :promoID AND m.statoPromozione.id = :statusID"),
        @NamedQuery(name = "PromoStatiTransizioneEntity.findAllByIdAndStatusAndFlagAutomatico",
                query = "SELECT e FROM PromoStatiTransizioneEntity e WHERE e.promozioneTestataEntity.id = :promoID AND e.statoPromozione.id = :statusID AND e.flagAutomatico = :flagAutomatico"),
        @NamedQuery(name = "PromoStatiTransizioneEntity.countByPromoAndStatuses",
                query = "SELECT COUNT(e) FROM PromoStatiTransizioneEntity e WHERE e.promozioneTestataEntity = :promo AND e.statoPromozione = :fromStatus AND e.statoTransizione = :toStatus"),
        @NamedQuery(name = "PromoStatiTransizioneEntity.findByPromoAndStatuses",
                query = "SELECT e FROM PromoStatiTransizioneEntity e WHERE e.promozioneTestataEntity = :promo AND e.statoPromozione = :fromStatus AND e.statoTransizione = :toStatus"),
        @NamedQuery(name = "PromoStatiTransizioneEntity.findByPromoAndStatusesAndFlagAutomatico",
                query = "SELECT e FROM PromoStatiTransizioneEntity e WHERE e.promozioneTestataEntity = :promo AND e.statoPromozione = :fromStatus AND e.statoTransizione = :toStatus AND e.flagAutomatico = :flagAutomatico"),
        @NamedQuery(name = "PromoStatiTransizioneEntity.countByPromoAndStatusesAndFlagAutomatico",
                query = "SELECT COUNT(e) FROM PromoStatiTransizioneEntity e WHERE e.promozioneTestataEntity = :promo AND e.statoPromozione = :fromStatus AND e.statoTransizione = :toStatus AND e.flagAutomatico = :flagAutomatico"),

})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class PromoStatiTransizioneEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMO_STATI_TRANSIZIONE_ID_GENERATOR", sequenceName = "MUI_PROMO_STATI_TRANSIZ_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMO_STATI_TRANSIZIONE_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(length = 255)
    private String descrizione;

    // bi-directional many-to-one association to PromozioneTestataEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private PromozioneTestataEntity promozioneTestataEntity;

    // bi-directional many-to-one association to StatoPromozioneEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO_TRANSIZIONE", nullable = false)
    private StatoPromozioneEntity statoTransizione;

    // bi-directional many-to-one association to StatoPromozioneEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO", nullable = false)
    private StatoPromozioneEntity statoPromozione;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
    private String codiceUtenteAggiornamento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "FLAG_COMPRATORE")
    private Integer flagCompratore;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "FLAG_PUBBLICA")
    private Boolean flagPubblica;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "FLAG_CONTROLLA")
    private Boolean flagControlli;

    // bi-directional many-to-one association to StatoPromozioneEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO_ERRORE", nullable = false)
    private StatoPromozioneEntity statoErrore;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "FL_AUTOMATICO")
    private Boolean flagAutomatico;

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