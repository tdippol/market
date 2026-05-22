package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import com.axiante.mui.validator.model.Promotion;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MUI_SP_TESTATA", schema = Metadata.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(
                name = "MuiSpTestataEntity.findAllForVisualizza",
                query = "SELECT DISTINCT t FROM MuiSpTestataEntity t " +
                        "JOIN FETCH t.canalePromozioneEntity c " +
                        "JOIN FETCH t.spStatoEntities s " +
                        "JOIN FETCH s.statoPromozioneEntity sp " +
                        "WHERE s.dataFineStato IS NULL " +
                        "AND sp.id <> 0 " +
                        "ORDER BY t.attiva DESC, t.dataInizio"
        ),
        @NamedQuery(
                name = "MuiSpTestataEntity.countByCodicePromozione",
                query = "SELECT COUNT(t) FROM MuiSpTestataEntity t WHERE t.codicePromozione = :codicePromozione"
        ),
        @NamedQuery(
                name = "MuiSpTestataEntity.findByCodicePromozione",
                query = "SELECT t FROM MuiSpTestataEntity t WHERE t.codicePromozione = :codicePromozione"
        ),
        @NamedQuery(
                name = "MuiSpTestataEntity.deactivateOtherActive",
                query = "UPDATE MuiSpTestataEntity t " +
                        "SET t.attiva = 0 " +
                        "WHERE t.attiva = 1 AND t.id <> :id"
        ),
        @NamedQuery(
                name = "MuiSpTestataEntity.findActive",
                query = "SELECT t FROM MuiSpTestataEntity t WHERE t.attiva = 1"
        ),
        @NamedQuery(
                name = "MuiSpTestataEntity.findByIdWithDetails",
                query = "SELECT DISTINCT t FROM MuiSpTestataEntity t " +
                        "JOIN FETCH t.canalePromozioneEntity c " +
                        "JOIN FETCH c.gruppoPromozioneEntity g " +
                        "LEFT JOIN FETCH t.spStatoEntities s " +
                        "LEFT JOIN FETCH s.statoPromozioneEntity sp " +
                        "WHERE t.id = :id"
        )
})
public class MuiSpTestataEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_SP_TESTATA_ID_GENERATOR",
            sequenceName = "MUI_SP_TESTATA_ID_SEQ")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MUI_SP_TESTATA_ID_GENERATOR")
    @Column(name = "ID", unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "CODICE_PROMOZIONE", nullable = false, length = 10)
    private String codicePromozione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CANALE", nullable = false)
    private CanalePromozioneEntity canalePromozioneEntity;

    @Column(name = "ANNO", length = 4)
    private String anno;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataFine;

    @Column(name = "DESCRIZIONE", length = 100)
    private String descrizione;

    @Column(name = "DESCRIZIONE_ESTESA", length = 500)
    private String descrizioneEstesa;

    @Column(name = "ATTIVA", nullable = false, precision = 1)
    private Integer attiva;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_REMINDER_INS_PARAM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataReminderInsParam;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 50)
    private String codiceUtenteAggiornamento;

    @Column(name = "UUID", length = 32)
    private String uuid;

    @OneToMany(
            mappedBy = "spTestataEntity",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<MuiSpStatoEntity> spStatoEntities = new HashSet<>();


    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }


    public CanalePromozioneEntity getCanalePromozioneEntity() {
        return canalePromozioneEntity;
    }

    public void setCanalePromozioneEntity(CanalePromozioneEntity canale) {
        canalePromozioneEntity = canale;
    }

    public MuiSpStatoEntity addSpStatoEntity(MuiSpStatoEntity statoEntity) {
        getSpStatoEntities().add(statoEntity);
        statoEntity.setSpTestataEntity(this);
        return statoEntity;
    }

    public MuiSpStatoEntity removeSpStatoEntity(MuiSpStatoEntity statoEntity) {
        getSpStatoEntities().remove(statoEntity);
        statoEntity.setSpTestataEntity(null);
        return statoEntity;
    }

}