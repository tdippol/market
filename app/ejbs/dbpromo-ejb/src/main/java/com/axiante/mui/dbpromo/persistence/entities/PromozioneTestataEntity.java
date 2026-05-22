package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.business.ModalitaMarchioPrivatoConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.business.enumeration.ModalitaMarchioPrivato;
import com.axiante.mui.dbpromo.persistence.Metadata;
import com.axiante.mui.validator.model.Promotion;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the MUI_PROMOZIONE_TESTATA database table.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "PromozioneTestataEntity.findByPromoCode",
                query = "SELECT p FROM PromozioneTestataEntity p WHERE p.codicePromozione = :codicePromozione"),
        @NamedQuery(name = "PromozioneTestataEntity.findAllByAnnoAndCanale",
                query = "SELECT p FROM PromozioneTestataEntity p WHERE p.anno = :anno AND p.muiCanalePromozione = :canale"),
        @NamedQuery(name = "PromozioneTestataEntity.findOverlappingByCodiciMeccanica",
                query = "SELECT p FROM PromozioneTestataEntity p " +
                        "INNER JOIN FETCH p.promozioneStatoEntities st " +
                        "INNER JOIN FETCH st.statoPromozioneEntity tt " +
                        "INNER JOIN FETCH p.promozionePianificazioneEntities pp " +
                        "WHERE tt.codiceStato <> :code AND st.dataFineStato IS NULL " +
                        "AND pp.meccanicaEntity.codiceMeccanica IN :codiciMeccanica " +
                        "AND ((p.dataInizio BETWEEN :dataInizio AND :dataFine) " +
                        "OR (p.dataFine BETWEEN :dataInizio AND :dataFine) " +
                        "OR (p.dataInizio <= :dataInizio and p.dataFine >= :dataFine) " +
                        "OR (p.dataInizio >= :dataInizio and p.dataFine <= :dataFine))"),
        @NamedQuery(name = "PromozioneTestataEntity.findOverlappingByAnnoAndCodiciMeccanica",
                query = "SELECT p FROM PromozioneTestataEntity p " +
                        "INNER JOIN FETCH p.promozioneStatoEntities st " +
                        "INNER JOIN FETCH st.statoPromozioneEntity tt " +
                        "INNER JOIN FETCH p.promozionePianificazioneEntities pp " +
                        "WHERE tt.codiceStato <> :code AND p.anno = :anno AND st.dataFineStato IS NULL " +
                        "AND pp.meccanicaEntity.codiceMeccanica IN :codiciMeccanica " +
                        "AND ((p.dataInizio BETWEEN :dataInizio AND :dataFine) " +
                        "OR (p.dataFine BETWEEN :dataInizio AND :dataFine) " +
                        "OR (p.dataInizio <= :dataInizio and p.dataFine >= :dataFine) " +
                        "OR (p.dataInizio >= :dataInizio and p.dataFine <= :dataFine))"),
        @NamedQuery(name = "PromozioneTestataEntity.findByCanaleMeccanicheDate",
                query = "SELECT DISTINCT p FROM PromozioneTestataEntity p " +
                        "INNER JOIN FETCH p.promozioneStatoEntities st " +
                        "INNER JOIN FETCH st.statoPromozioneEntity tt " +
                        "INNER JOIN FETCH p.promozionePianificazioneEntities pp " +
                        "WHERE tt.codiceStato <> :codiceStato AND st.dataFineStato IS NULL " +
                        "AND p.muiCanalePromozione.id = :idCanale " +
                        "AND pp.meccanicaEntity.codiceMeccanica IN :codiciMeccaniche " +
                        "AND p.dataInizio >= :dataInizio AND p.dataFine <= :dataFine"),
        @NamedQuery(name = "PromozioneTestataEntity.findByAnnoAndCodiciMeccanica",
                query = "SELECT p FROM PromozioneTestataEntity p " +
                        "INNER JOIN FETCH p.promozioneStatoEntities st " +
                        "INNER JOIN FETCH st.statoPromozioneEntity tt " +
                        "INNER JOIN FETCH p.promozionePianificazioneEntities pp " +
                        "WHERE tt.codiceStato <> :code AND p.anno = :anno AND st.dataFineStato IS NULL " +
                        "AND pp.meccanicaEntity.codiceMeccanica IN :codiciMeccanica"),
        @NamedQuery(name = "PromozioneTestataEntity.findByIdFullEagerFetch",
                query = "SELECT DISTINCT p FROM PromozioneTestataEntity p " +
                        "LEFT JOIN FETCH p.muiCfgSetPianificazione cfgSet " +
                        "LEFT JOIN FETCH cfgSet.muiCfgConfHeaders confHeaders " +
                        "LEFT JOIN FETCH confHeaders.meccanicaEntity " +
                        "LEFT JOIN FETCH confHeaders.muiCfgPianificaziones " +
                        "LEFT JOIN FETCH confHeaders.livelloPianificazione " +
                        "LEFT JOIN FETCH p.promozionePianificazioneEntities pp " +
                        "LEFT JOIN FETCH pp.meccanicaEntity " +
                        "LEFT JOIN FETCH p.owners " +
                        "WHERE p.id = :id"),

        @NamedQuery(name = "PromozioneTestataEntity.findOverlappingPromoWithAttributo",
                query = "SELECT p FROM PromozioneTestataEntity p " +
                        "INNER JOIN FETCH p.promozioneStatoEntities st " +
                        "INNER JOIN FETCH st.statoPromozioneEntity tt " +
                        "INNER JOIN FETCH p.promozioneAttributiEntity pa " +
                        "WHERE tt.codiceStato <> :code AND st.dataFineStato IS NULL AND p.id <> :idTestata " +
                        "AND ((p.dataInizio BETWEEN :dataInizio AND :dataFine) " +
                        "OR (p.dataFine BETWEEN :dataInizio AND :dataFine) " +
                        "OR (p.dataInizio <= :dataInizio and p.dataFine >= :dataFine) " +
                        "OR (p.dataInizio >= :dataInizio and p.dataFine <= :dataFine)) " +
                        "AND pa.attributo.id = :idAttributo AND pa.valore = :valoreAttributo")
})
@Table(name = "MUI_PROMOZIONE_TESTATA", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class PromozioneTestataEntity
        implements Serializable, Promotion, AuditLogInterface, UUIDEnabledEntity {
    /**
     * non cancello: cascade delete no merge tutte persist tutto
     */
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_PROMOZIONE_TESTATA_ID_GENERATOR",
            sequenceName = "MUI_PROMOZIONE_TESTATA_ID_SEQ")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MUI_PROMOZIONE_TESTATA_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "ANNO", length = 4)
    private String anno;

    @Column(name = "CODICE_PROMOZIONE", nullable = false, length = 10)
    private String codicePromozione;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataFine;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInizio;

    @Column(name = "DESCRIZIONE", length = 100)
    private String descrizione;

    @Column(name = "DESCRIZIONE_ESTESA", length = 500)
    private String descrizioneEstesa;

    @Column(name = "NOTE_MARKETING", length = 2000)
    private String noteMarketing;

    @Column(length = 50)
    private String semestre;

    @Column(name = "ORA_INIZIO")
    @Temporal(TemporalType.TIME)
    private Date oraInizio;

    @Column(name = "ORA_FINE")
    @Temporal(TemporalType.TIME)
    private Date oraFine;

    // bi-directional many-to-one association to PromozioneMeccanicheEntity
    @OneToMany(
            mappedBy = "promozioneTestataEntity",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PromozioneMeccanicheEntity> promozioneMeccanicheEntities;

    // bi-directional many-to-one association to PromozioneNegozioEntity
    @OneToMany(
            mappedBy = "promozioneTestataEntity",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PromozioneNegozioEntity> promozioneNegozioEntities;

    // bi-directional many-to-one association to PromozionePianificazioneEntity
    @OneToMany(
            mappedBy = "promozioneTestataEntity",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PromozionePianificazioneEntity> promozionePianificazioneEntities;

    // bi-directional many-to-one association to PromozioneStatoEntity
    @OneToMany(
            mappedBy = "promozioneTestataEntity",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<PromozioneStatoEntity> promozioneStatoEntities;

    // bi-directional many-to-one association to MuiCanalePromozione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CANALE", nullable = false)
    private CanalePromozioneEntity muiCanalePromozione;

    // bi-directional many-to-one association to MuiCfgSetPianificazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CFG_SET_PIANIFIC")
    private CfgSetPianificazioneEntity muiCfgSetPianificazione;

    // bi-directional many-to-one association to CreaPromozioneEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CREA_PROMOZIONE")
    private CreaPromozioneEntity creaPromozioneEntity;

    // bi-directional many-to-one association to PromoStatiConsentitiEntity
    @OneToMany(mappedBy = "promozioneTestataEntity")
    private Set<PromoStatiConsentitiEntity> promoStatiConsentitiEntities;

    // bi-directional many-to-one association to PromoStatiTransizioneEntity
    @OneToMany(mappedBy = "promozioneTestataEntity")
    private Set<PromoStatiTransizioneEntity> promoStatiTransizioneEntities;

    // bi-directional many-to-one association to PromoPubblicazioneTestataEntity
    @OneToMany(mappedBy = "promozioneTestataEntity")
    private Set<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "MUI_PROMOZIONE_REPARTO",
            schema = Metadata.SCHEMA,
            joinColumns = {@JoinColumn(name = "ID_PROMOZIONE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_REPARTO")})
    private Set<RepartoEntity> reparti = new HashSet<>();

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

    @OneToMany(
            mappedBy = "promozioneTestataEntity",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PromozioneTipoTerminaleEntity> promozioneTipiTerminaleCassa;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

    @Column(name = "FLAG_ERRORE")
    @Convert(converter = BooleanConverter.class)
    private Boolean flagErrore;

    @Column(name = "VALORE_PUNTO")
    @Digits(integer = 3, fraction = 3)
    private BigDecimal valorePunto;

    @OneToMany(
            mappedBy = "promozioneTestataEntity",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<CheckTestataEntity> checks;

    @OneToMany(
            mappedBy = "promozioneTestataEntity",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<CheckCompratoriEntity> checkCompratori;

    @OneToMany(
            mappedBy = "testata",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<CheckPianificazioneEntity> checkPianificazioni;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "MUI_PROMOZIONE_OWNER",
            schema = Metadata.SCHEMA,
            joinColumns = {@JoinColumn(name = "ID_PROMOZIONE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_COMPRATORE")})
    private Set<CompratoreEntity> owners = new HashSet<>();

    @Column(name = "CODICE_PROMO_RIF", length = 10, nullable = true)
    String codicePromoRiferimento;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "MUI_PROMOZIONE_PLANO",
            schema = Metadata.SCHEMA,
            joinColumns = @JoinColumn(name = "ID_PROMOZIONE"),
            inverseJoinColumns = @JoinColumn(name = "PLANO_ID"))
    Set<MuiPlanoDbPromoEntity> planogrammi;

    // bi-directional many-to-one association to PromozioneMeccanicheEntity
    @OneToMany(
            mappedBy = "testata",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<ReportSovrapposizioniEntity> reportSovrapposizioni;

    @OneToMany(
            mappedBy = "promozione",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<ContributiPromoEntity> contributiPromozione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INIZIATIVA", nullable = true)
    private IniziativaEntity iniziativa;

    @OneToMany(
            mappedBy = "promozioneTestata",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<MuiPromozioneFlagEntity> promozioneFlags;

    // bi-directional many-to-one association to PromoStatiTransizioneEntity
    @OneToMany(mappedBy = "promozioneTestataEntity")
    private Set<PromozioneAttributiEntity> promozioneAttributiEntity;

    @OneToMany(
            mappedBy = "promozione",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PromozioneMarchioPrivatoEntity> marchiPrivati;

    @Column(name = "FLAG_MOD_MARCHIO_PRIV")
    @Convert(converter = ModalitaMarchioPrivatoConverter.class)
    private ModalitaMarchioPrivato flagMarchioPrivato;

    @Column(name = "FL_DIFFERENZIAZIONE_MECCANICA")
    @Convert(converter = BooleanConverter.class)
    private Boolean flDifferenziazioneMeccanica;

    // bi-directional many-to-one association to PromozioneMeccanicheEntity
    @OneToMany(
            mappedBy = "promozione",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<PromoRepartoMarchioPrivato> repartiMarchiPrivati;

    @OneToMany(
            mappedBy = "testata",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<PianificazioneTotalizzatoriEntity> totalizzatori = new HashSet<>();

  @Column(name = "FL_COPIA_AUTOMATICA")
  @Convert(converter = BooleanConverter.class)
  private Boolean flCopiaAutomatica;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "MUI_PROMOZIONE_SOTTOSCRIZIONE", schema = Metadata.SCHEMA,
            joinColumns = {@JoinColumn(name = "ID_PROMO_MISSIONE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_SOTTOSCRIZIONE")})
    private Set<SottoscrizioneEntity> sottoscrizioni = new HashSet<>();

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }

    public PromozioneMeccanicheEntity addMuiPromozioneMeccanich(
            @NonNull PromozioneMeccanicheEntity muiPromozioneMeccanich) {
        getPromozioneMeccanicheEntities().add(muiPromozioneMeccanich);
        muiPromozioneMeccanich.setPromozioneTestataEntity(this);

        return muiPromozioneMeccanich;
    }

    public PromozioneMeccanicheEntity removeMuiPromozioneMeccanich(
            @NonNull PromozioneMeccanicheEntity muiPromozioneMeccanich) {
        getPromozioneMeccanicheEntities().remove(muiPromozioneMeccanich);
        muiPromozioneMeccanich.setPromozioneTestataEntity(null);

        return muiPromozioneMeccanich;
    }

    public PromozioneNegozioEntity addPromozioneNegozioEntity(
            PromozioneNegozioEntity promozioneNegozioEntity) {
        getPromozioneNegozioEntities().add(promozioneNegozioEntity);
        promozioneNegozioEntity.setPromozioneTestataEntity(this);

        return promozioneNegozioEntity;
    }

    public PromozioneNegozioEntity removePromozioneNegozioEntity(
            PromozioneNegozioEntity promozioneNegozioEntity) {
        getPromozioneNegozioEntities().remove(promozioneNegozioEntity);
        promozioneNegozioEntity.setPromozioneTestataEntity(null);

        return promozioneNegozioEntity;
    }

    public PromozionePianificazioneEntity addPromozionePianificazioneEntity(
            PromozionePianificazioneEntity promozionePianificazioneEntity) {
        if (getPromozionePianificazioneEntities() == null) {
            setPromozionePianificazioneEntities(new HashSet<>());
        }
        getPromozionePianificazioneEntities().add(promozionePianificazioneEntity);
        promozionePianificazioneEntity.setPromozioneTestataEntity(this);

        // anche i dettagli
        if (promozionePianificazioneEntity.getChildren() != null) {
            for (PromozionePianificazioneEntity entity : promozionePianificazioneEntity.getChildren()) {
                addPromozionePianificazioneEntity(entity);
            }
        }

        return promozionePianificazioneEntity;
    }

    public PromozionePianificazioneEntity removePromozionePianificazioneEntity(
            PromozionePianificazioneEntity promozionePianificazioneEntity) {
        if (getPromozionePianificazioneEntities() != null) {
            getPromozionePianificazioneEntities().remove(promozionePianificazioneEntity);
            promozionePianificazioneEntity.setPromozioneTestataEntity(null);
            // anche i dettagli
            if (promozionePianificazioneEntity.getChildren() != null) {
                for (PromozionePianificazioneEntity entity : promozionePianificazioneEntity.getChildren()) {
                    removePromozionePianificazioneEntity(entity);
                }
            }
        }
        return promozionePianificazioneEntity;
    }

    public PromozioneStatoEntity addPromozioneStatoEntity(
            PromozioneStatoEntity promozioneStatoEntity) {
        getPromozioneStatoEntities().add(promozioneStatoEntity);
        promozioneStatoEntity.setPromozioneTestataEntity(this);

        return promozioneStatoEntity;
    }

    public PromozioneStatoEntity removePromozioneStatoEntity(
            PromozioneStatoEntity promozioneStatoEntity) {
        getPromozioneStatoEntities().remove(promozioneStatoEntity);
        promozioneStatoEntity.setPromozioneTestataEntity(null);

        return promozioneStatoEntity;
    }

    public PromoStatiConsentitiEntity addPromoStatiConsentitiEntity(
            PromoStatiConsentitiEntity promoStatiConsentitiEntity) {
        getPromoStatiConsentitiEntities().add(promoStatiConsentitiEntity);
        promoStatiConsentitiEntity.setPromozioneTestataEntity(this);

        return promoStatiConsentitiEntity;
    }

    public PromoStatiConsentitiEntity removePromoStatiConsentitiEntity(
            PromoStatiConsentitiEntity promoStatiConsentitiEntity) {
        getPromoStatiConsentitiEntities().remove(promoStatiConsentitiEntity);
        promoStatiConsentitiEntity.setPromozioneTestataEntity(null);

        return promoStatiConsentitiEntity;
    }

    public PromoStatiTransizioneEntity addPromoStatiTransizioneEntity(
            PromoStatiTransizioneEntity promoStatiTransizioneEntity) {
        if (getPromoStatiTransizioneEntities() == null) {
            setPromoStatiTransizioneEntities(new HashSet<>());
        }
        getPromoStatiTransizioneEntities().add(promoStatiTransizioneEntity);
        promoStatiTransizioneEntity.setPromozioneTestataEntity(this);

        return promoStatiTransizioneEntity;
    }

    public PromoStatiTransizioneEntity removePromoStatiTransizioneEntity(
            PromoStatiTransizioneEntity promoStatiTransizioneEntity) {
        getPromoStatiTransizioneEntities().remove(promoStatiTransizioneEntity);
        promoStatiTransizioneEntity.setPromozioneTestataEntity(null);

        return promoStatiTransizioneEntity;
    }

    public PromoPubblicazioneTestataEntity addPromoPubblicazioneTestataEntity(
            PromoPubblicazioneTestataEntity promoPubblicazioneTestata) {
        getPromoPubblicazioneTestataEntities().add(promoPubblicazioneTestata);
        promoPubblicazioneTestata.setPromozioneTestataEntity(this);

        return promoPubblicazioneTestata;
    }

    public PromoPubblicazioneTestataEntity removePromoPubblicazioneTestataEntity(
            PromoPubblicazioneTestataEntity promoPubblicazioneTestata) {
        getPromoPubblicazioneTestataEntities().remove(promoPubblicazioneTestata);
        promoPubblicazioneTestata.setPromozioneTestataEntity(null);

        return promoPubblicazioneTestata;
    }

    public PromozioneTipoTerminaleEntity addPromozioneTipoTerminale(
            PromozioneTipoTerminaleEntity promozioneTerminale) {
        if (getPromozioneTipiTerminaleCassa() == null) {
            setPromozioneTipiTerminaleCassa(new HashSet<>());
        }
        getPromozioneTipiTerminaleCassa().add(promozioneTerminale);
        promozioneTerminale.setPromozioneTestataEntity(this);
        return promozioneTerminale;
    }

    public PromozioneTipoTerminaleEntity removePromozioneTipoTerminale(
            PromozioneTipoTerminaleEntity promozioneTerminale) {
        if (getPromozioneTipiTerminaleCassa() != null) {
            getPromozioneTipiTerminaleCassa().remove(promozioneTerminale);
        }
        promozioneTerminale.setPromozioneTestataEntity(null);
        return promozioneTerminale;
    }

    @Override
    public String getNewDescrizione() {
        return null;
    }

    @Override
    public Date getNewDataFine() {
        return null;
    }

    @Override
    public Date getNewDataInizio() {
        return null;
    }

    @Override
    public CanalePromozioneEntity getCanalePromozioneEntity() {
        return muiCanalePromozione;
    }

    @Override
    public void setCanalePromozioneEntity(CanalePromozioneEntity canale) {
        muiCanalePromozione = canale;
    }

    @Override
    public String getNewNoteMarketing() {
        return null;
    }

    @Override
    public Date getNewOraInizio() {
        return null;
    }

    @Override
    public Date getNewOraFine() {
        return null;
    }

    public void addReparto(@NonNull RepartoEntity reparto) {
        if (getReparti() == null) {
            setReparti(new HashSet<>());
        }
        getReparti().add(reparto);
        reparto.getPromozioni().add(this);
    }

    public RepartoEntity removeReparto(@NonNull RepartoEntity reparto) {
        if (getReparti() != null) {
            getReparti().remove(reparto);
        }
        reparto.getPromozioni().remove(this);
        return reparto;
    }

    public void addCheckTestata(@NonNull CheckTestataEntity check) {
        if (getChecks() == null) {
            setChecks(new HashSet<>());
        }
        getChecks().add(check);
        check.setPromozioneTestataEntity(this);
    }

    public CheckTestataEntity removeCheckTestata(@NonNull CheckTestataEntity check) {
        if ((getChecks() != null) && getChecks().remove(check)) {
            check.setPromozioneTestataEntity(null);
        }
        return check;
    }

    public void addCheckCompratori(@NonNull CheckCompratoriEntity check) {
        if (getCheckCompratori() == null) {
            setCheckCompratori(new HashSet<>());
        }
        getCheckCompratori().add(check);
        check.setPromozioneTestataEntity(this);
    }

    public CheckCompratoriEntity removeCheckCompratori(@NonNull CheckCompratoriEntity check) {
        if ((getCheckCompratori() != null) && getCheckCompratori().remove(check)) {
            check.setPromozioneTestataEntity(null);
        }
        return check;
    }

    public void addCheckPianificazione(@NonNull CheckPianificazioneEntity check) {
        if (getCheckPianificazioni() == null) {
            setCheckPianificazioni(new HashSet<>());
        }
        getCheckPianificazioni().add(check);
        check.setTestata(this);
    }

    public CheckPianificazioneEntity removeCheckPianificazione(
            @NonNull CheckPianificazioneEntity check) {
        if (getCheckPianificazioni() != null && getCheckPianificazioni().remove(check)) {
            check.setTestata(null);
        }
        return check;
    }

    public CompratoreEntity addOwner(@NonNull CompratoreEntity owner) {
        if (owners.add(owner)) {
            owner.getPromozioni().add(this);
        }
        return owner;
    }

    public boolean removeOwner(@NonNull CompratoreEntity owner) {
        if (owners.remove(owner)) {
            owner.getPromozioni().remove(this);
            return true;
        }
        return false;
    }

    public ReportSovrapposizioniEntity addReportSovrapposizione(
            @NonNull ReportSovrapposizioniEntity report) {
        getReportSovrapposizioni().add(report);
        report.setTestata(this);
        return report;
    }

    public ReportSovrapposizioniEntity removeReportSovrapposizioni(
            ReportSovrapposizioniEntity report) {
        getReportSovrapposizioni().remove(report);
        report.setTestata(null);
        return report;
    }

    public void addPlano(@NonNull MuiPlanoDbPromoEntity plano) {
        getPlanogrammi().add(plano);
    }

    public void removePlano(@NonNull MuiPlanoDbPromoEntity plano) {
        getPlanogrammi().remove(plano);
    }

    public void addContributiPromozione(ContributiPromoEntity contributo) {
        if (getContributiPromozione() == null) {
            setContributiPromozione(new HashSet<>());
        }
        getContributiPromozione().add(contributo);
        contributo.setPromozione(this);
    }

    public void removeContributo(ContributiPromoEntity contributo) {
        if (getContributiPromozione() != null && getContributiPromozione().remove(contributo)) {
            contributo.setPromozione(null);
        }
    }

    public MuiPromozioneFlagEntity addMuiPromozioneFlagEntity(
            MuiPromozioneFlagEntity promozioneFlag) {
        if (getPromozioneFlags() == null) {
            setPromozioneFlags(new HashSet<>());
        }
        getPromozioneFlags().add(promozioneFlag);
        promozioneFlag.setPromozioneTestata(this);

        return promozioneFlag;
    }

    public MuiPromozioneFlagEntity removePromoCompratoreEntity(
            MuiPromozioneFlagEntity promozioneFlag) {
        if (getPromozioneFlags() != null) {
            getPromozioneFlags().remove(promozioneFlag);
        }
        if (getId() != null && getId().equals(promozioneFlag.getPromozioneTestata().getId())) {
            promozioneFlag.setPromozioneTestata(null);
        }

        return promozioneFlag;
    }

    public void addPromozioneAttributo(PromozioneAttributiEntity attributo) {
        if (getPromozioneAttributiEntity() == null) {
            setPromozioneAttributiEntity(new HashSet<>());
        }
        getPromozioneAttributiEntity().add(attributo);
        attributo.setPromozioneTestataEntity(this);
    }

    public void removePromozioneAttributo(PromozioneAttributiEntity attributo) {
        if (attributo != null) {
            if (getPromozioneAttributiEntity() != null) {
                getPromozioneAttributiEntity().remove(attributo);
            }
            attributo.setPromozioneTestataEntity(null);
        }
    }

    public void addMarchioPrivato(PromozioneMarchioPrivatoEntity cfg) {
        if (getMarchiPrivati() == null) {
            setMarchiPrivati(new HashSet<>());
        }
        getMarchiPrivati().add(cfg);
        cfg.setPromozione(this);
    }

    public void removeMarchioPrivato(PromozioneMarchioPrivatoEntity cfg) {
        if (getMarchiPrivati() != null) {
            getMarchiPrivati().remove(cfg);
            cfg.setPromozione(null);
        }
    }

    public void addRepartoMarchioPrivato(PromoRepartoMarchioPrivato m) {
        if (getRepartiMarchiPrivati() == null) {
            setRepartiMarchiPrivati(new HashSet<>());
        }
        getRepartiMarchiPrivati().add(m);
        m.setPromozione(this);
    }

    public void removeRepartoMarchioPrivato(PromoRepartoMarchioPrivato m) {
        if (getRepartiMarchiPrivati() != null) {
            getRepartiMarchiPrivati().remove(m);
        }
        m.setPromozione(null);
    }

    public void addTotalizzatore(PianificazioneTotalizzatoriEntity t) {
        if (getTotalizzatori() == null) {
            setTotalizzatori(new HashSet<>());
        }
        getTotalizzatori().add(t);
        t.setTestata(this);
    }

    public void removeTotalizzatori(PianificazioneTotalizzatoriEntity t) {
        if (getTotalizzatori() != null) {
            getTotalizzatori().remove(t);
        }
        t.setTestata(null);
    }

    public void addSottoscrizione(SottoscrizioneEntity sottoscrizione) {
        if (getSottoscrizioni() == null) {
            setSottoscrizioni(new HashSet<>());
        }
        getSottoscrizioni().add(sottoscrizione);
    }

    public void removeSottoscrizione(SottoscrizioneEntity sottoscrizione) {
        if (getSottoscrizioni() != null) {
            getSottoscrizioni().remove(sottoscrizione);
        }
    }
}
