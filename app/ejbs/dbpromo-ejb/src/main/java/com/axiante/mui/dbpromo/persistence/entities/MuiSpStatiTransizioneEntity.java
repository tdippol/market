package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MUI_SP_STATI_TRANSIZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(
                name = "MuiSpStatiTransizioneEntity.findAll",
                query = "SELECT m FROM MuiSpStatiTransizioneEntity m"
        ),
        @NamedQuery(
                name = "MuiSpStatiTransizioneEntity.findByPromozioneAndFromStato",
                query = "SELECT m FROM MuiSpStatiTransizioneEntity m " +
                        "JOIN FETCH m.statoTransizione st " +
                        "WHERE m.spTestataEntity.id = :idPromozione " +
                        "AND m.statoPromozioneEntity.id = :idStato " +
                        "AND m.statoTransizione.id <> 0 " +
                        "ORDER BY st.codiceStato"
        )
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class MuiSpStatiTransizioneEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_SP_STATI_TRANSIZIONE_ID_GENERATOR",
            sequenceName = "MUI_SP_STATI_TRANSIZ_ID_SEQ"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MUI_SP_STATI_TRANSIZIONE_ID_GENERATOR"
    )
    @Column(name = "ID", unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private MuiSpTestataEntity spTestataEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO_TRANSIZIONE", nullable = false)
    private StatoPromozioneEntity statoTransizione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO", nullable = false)
    private StatoPromozioneEntity statoPromozioneEntity;

    @Column(name = "DESCRIZIONE", length = 255)
    private String descrizione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO_ERRORE")
    private StatoPromozioneEntity statoErrore;

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