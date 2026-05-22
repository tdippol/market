package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
@Table(name = "MUI_CREA_PIANO_MEDIA", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "CreaPianoMediaEntity.findByUserId",
                query = "SELECT e FROM CreaPianoMediaEntity e WHERE e.userId = :userId"),
        @NamedQuery(name = "CreaPianoMediaEntity.findByUserIdAndSlotId",
                query = "SELECT e FROM CreaPianoMediaEntity e WHERE e.userId = :userId AND e.slotId = :slotId"),
        @NamedQuery(name = "CreaPianoMediaEntity.countByUserIdAndSlotId",
                query = "SELECT COUNT(e) FROM CreaPianoMediaEntity e WHERE e.userId = :userId AND e.slotId = :slotId")
})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreaPianoMediaEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 6198134963800699412L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CREA_PIANO_MEDIA_ID_GENERATOR", sequenceName = "MUI_CREA_PIANO_MEDIA_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CREA_PIANO_MEDIA_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "USER_ID", length = 50)
    private String userId;

    @Column(name = "SLOT_ID", length = 50)
    private String slotId;

    @Column(name = "DESCRIZIONE", length = 100)
    private String descrizione;

    @Column(name = "ANNO")
    private Integer anno;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO")
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE")
    private Date dataFine;

    @Column(name = "PROMO_MASTER", length = 10)
    private String promoMaster;

    @Column(name = "PROMO_SECONDARY_A", length = 10)
    private String promoSecondaryA;

    @Column(name = "PROMO_SECONDARY_B", length = 10)
    private String promoSecondaryB;

    @Column(name = "PROMO_SECONDARY_C", length = 10)
    private String promoSecondaryC;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

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
