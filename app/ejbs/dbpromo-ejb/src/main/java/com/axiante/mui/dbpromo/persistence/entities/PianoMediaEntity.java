package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MUI_PIANO_MEDIA", schema = Metadata.SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "PianoMediaEntity.findByDataInizioAndDataFine", query = "SELECT p FROM PianoMediaEntity p where p.dataInizio >= :dataInizio and p.dataFine <= :dataFine"),
        @NamedQuery(name = "PianoMediaEntity.findNonPubblicatiByDataInizioAndDataFine", query = "SELECT p FROM PianoMediaEntity p " +
                "INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s where p.dataInizio >= :dataInizio and p.dataFine <= :dataFine and ps.dataFineStato IS NULL AND s.codiceStato = '10'"),
        @NamedQuery(name = "PianoMediaEntity.findOpenPianiMedia",
                query = "SELECT p FROM PianoMediaEntity p INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s WHERE ps.dataFineStato IS NULL AND s.codiceStato NOT IN ('00','500')"),
        @NamedQuery(name = "PianoMediaEntity.findNonPubblicatiPianiMedia",
                query = "SELECT p FROM PianoMediaEntity p INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s WHERE ps.dataFineStato IS NULL AND s.codiceStato = '10'"),
        @NamedQuery(name = "PianoMediaEntity.findNotCancelled",
                query = "SELECT p FROM PianoMediaEntity p INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s WHERE ps.dataFineStato IS NULL AND s.codiceStato <>'00'"),
        @NamedQuery(name = "PianoMediaEntity.findOpenAvailableYears",
                query = "SELECT DISTINCT (p.anno) FROM PianoMediaEntity p INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s WHERE ps.dataFineStato IS NULL AND s.codiceStato  NOT IN ('00','500')"),
        @NamedQuery(name = "PianoMediaEntity.findPubblicatiByDataInizio",
                query = "SELECT p FROM PianoMediaEntity p INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s WHERE ps.dataFineStato IS NULL AND s.codiceStato  IN ('300','310') AND p.dataInizio <= :dataInizio"),
        @NamedQuery(name = "PianoMediaEntity.findOpenByDataFine",
                query = "SELECT p FROM PianoMediaEntity p INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s WHERE ps.dataFineStato IS NULL AND s.codiceStato NOT IN ('00','500') AND p.dataFine <= :dataFine"),
        @NamedQuery(name = "PianoMediaEntity.findPianiMediaAccessibiliInPianificazione",
                query = "SELECT p FROM PianoMediaEntity p INNER JOIN p.pianoMediaStati ps INNER JOIN ps.stato s WHERE ps.dataFineStato IS NULL AND s.codiceStato NOT IN ('00','500', '10')"),
})
public class PianoMediaEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 3965006351592444287L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PIANO_MEDIA_ID_GENERATOR",
            sequenceName = "MUI_PIANO_MEDIA_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PIANO_MEDIA_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CANALE", nullable = false)
    private CanalePromozioneEntity canale;

    @Column(name = "ANNO", nullable = false)
    private Integer anno;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO")
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE")
    private Date dataFine;

    @Column(name = "DESCRIZIONE", length = 100)
    private String descrizione;

    @Column(name = "CODICE_PROMO_MASTER", length = 10)
    private String promoMaster;

    @Column(name = "CODICE_PROMO_SEC_A", length = 10)
    private String promoSecondaryA;

    @Column(name = "CODICE_PROMO_SEC_B", length = 10)
    private String promoSecondaryB;

    @Column(name = "CODICE_PROMO_SEC_C", length = 10)
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

    @OneToMany(mappedBy = "pianoMedia", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private Set<PianoMediaStatoEntity> pianoMediaStati;

    @OneToMany(mappedBy = "pianoMedia", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, orphanRemoval = true)
    private Set<PianificazionePianoMediaEntity> configurazioniPianoMedia;

    // bi-directional many-to-one association to PromoStatiConsentitiEntity
    @OneToMany(mappedBy = "pianoMediaEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PianoMediaStatiConsentitiEntity> statiConsentiti;

    @OneToMany(mappedBy = "pianoMediaEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PianoMediaStatiTransizioneEntity> statiTransizione;

    @OneToMany(mappedBy = "pianoMedia",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true,
            fetch = FetchType.LAZY)
    Set<PianoMediaPianificazioneDettaglioEntity> dettagliPianificazione;

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }

    public PianoMediaStatoEntity addPianoMediaStato(PianoMediaStatoEntity pianoMediaStato) {
        if (getPianoMediaStati() == null) {
            setPianoMediaStati(new HashSet<>());
        }
        getPianoMediaStati().add(pianoMediaStato);
        pianoMediaStato.setPianoMedia(this);
        return pianoMediaStato;
    }

    public PianoMediaStatoEntity removePianoMediaStato(PianoMediaStatoEntity pianoMediaStato) {
        if (getPianoMediaStati() != null) {
            getPianoMediaStati().remove(pianoMediaStato);
        }
        pianoMediaStato.setPianoMedia(null);
        return pianoMediaStato;
    }

    public PianoMediaStatiConsentitiEntity addStatiConsentitiEntity(
            PianoMediaStatiConsentitiEntity statiConsentitiEntity) {
        if ( getStatiConsentiti() == null){
            setStatiConsentiti(new HashSet<>());
        }
        getStatiConsentiti().add(statiConsentitiEntity);
        statiConsentitiEntity.setPianoMediaEntity(this);

        return statiConsentitiEntity;
    }

    public PianoMediaStatiConsentitiEntity removeStatiConsentitiEntity(
            PianoMediaStatiConsentitiEntity statiConsentitiEntity) {
        getStatiConsentiti().remove(statiConsentitiEntity);
        statiConsentitiEntity.setPianoMediaEntity(null);

        return statiConsentitiEntity;
    }

    public PianoMediaStatiTransizioneEntity addStatiTransizione(
            PianoMediaStatiTransizioneEntity statoTransizione) {
        if ( getStatiTransizione() == null ) {
            setStatiTransizione(new HashSet<>());
        }
        getStatiTransizione().add(statoTransizione);
        statoTransizione.setPianoMediaEntity(this);

        return statoTransizione;
    }

    public PianoMediaStatiTransizioneEntity removeStatiTransizione(
            PianoMediaStatiTransizioneEntity statoTransizione) {
        getStatiTransizione().remove(statoTransizione);
        statoTransizione.setPianoMediaEntity(null);

        return statoTransizione;
    }

    public PianoMediaPianificazioneDettaglioEntity addDettagliPianificazione(@NonNull PianoMediaPianificazioneDettaglioEntity e){
        if ( getDettagliPianificazione() == null ){
            setDettagliPianificazione(new HashSet<>());
        }
        getDettagliPianificazione().add(e);
        e.setPianoMedia(this);
        return e;
    }

    public PianoMediaPianificazioneDettaglioEntity removeDettagliPianificazione(@NonNull PianoMediaPianificazioneDettaglioEntity e){
        if ( getDettagliPianificazione() != null && getDettagliPianificazione().remove(e)){
            e.setPianoMedia(null);
        }
        return e;
    }

    @Transient
    public PianoMediaStatoEntity getCurrentStatus() {
        if (getPianoMediaStati() != null && !getPianoMediaStati().isEmpty()) {
            return getPianoMediaStati().stream().filter(p -> p.getDataFineStato() == null).findFirst().orElse(null);
        }
        return null;
    }

    @Transient
    public String getDescrizioneEstesa() {
        return String.format("%s %s",
                dataInizio != null ? DateTimeUtils.getFormatoEsselunga().format(dataInizio) : "",
                descrizione);
    }
}
