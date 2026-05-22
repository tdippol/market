package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
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
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the MUI_PROMOZIONE_PIANIFICAZIONE database table.
 */
@Entity
@Table(name = "MUI_PROMOZIONE_PIANIFICAZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "PromozionePianificazioneEntity.findAll",
                query = "SELECT m FROM PromozionePianificazioneEntity m"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findAllByPromozioneTestataEntity",
                query = "SELECT p FROM PromozionePianificazioneEntity p WHERE p.promozioneTestataEntity = :promozioneTestataEntity AND p.parent IS NULL"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findAllDetailsByIdPromozione",
                query = "SELECT p FROM PromozionePianificazioneEntity p WHERE p.promozioneTestataEntity.id = :idPromozione AND p.tipoElemento = 'ARTICOLO'"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findAllParentRowsByPromozioneTestata",
                query = "SELECT p from PromozionePianificazioneEntity p WHERE p.promozioneTestataEntity.id = :idPromozione AND p.parent IS NULL ORDER BY p.id"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findAllSetByPromozione",
                query = "SELECT p FROM PromozionePianificazioneEntity p WHERE p.promozioneTestataEntity = :testata AND p.tipoRiga.codiceTipo = 'S'"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findChildByParent",
                query = "SELECT p FROM PromozionePianificazioneEntity p WHERE p.parent = :pianificazione"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findAllByIds",
                query = "SELECT e FROM PromozionePianificazioneEntity e WHERE e.id IN :ids"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findAllBuoni",
                query = "SELECT e FROM PromozionePianificazioneEntity e INNER JOIN e.promozioneTestataEntity t INNER JOIN t.promozioneStatoEntities s WHERE s.dataFineStato IS NULL AND s.statoPromozioneEntity.codiceStato <> '00' AND e.buonoScontoRadice IS NOT NULL AND e.dataFine > :dataFine ORDER BY e.dataInizio"),
        @NamedQuery(name = "PromozionePianificazioneEntity.findAllBuoniNotUsed",
                query = "SELECT e FROM PromozionePianificazioneEntity e INNER JOIN e.promozioneTestataEntity t INNER JOIN t.promozioneStatoEntities s WHERE e.id NOT IN :ids AND s.dataFineStato IS NULL AND s.statoPromozioneEntity.codiceStato <> '00' AND e.buonoScontoRadice IS NOT NULL AND e.dataFine > :dataFine ORDER BY e.dataInizio"),
        @NamedQuery(name = "PromozionePianificazioneEntity.countArticoliByPromozione",
                query = "SELECT count(p) FROM PromozionePianificazioneEntity p WHERE p.promozioneTestataEntity.id = :idPromozione AND p.tipoRiga.codiceTipo = 'E' AND p.tipoElemento = 'ARTICOLO'" ),
        @NamedQuery(name = "PromozionePianificazioneEntity.getUsedBuonoScontoProgressivo",
                query = "SELECT DISTINCT pp.buonoScontoProgressivo FROM PromozionePianificazioneEntity pp JOIN pp.promozioneTestataEntity p JOIN p.promozioneStatoEntities ps JOIN ps.statoPromozioneEntity s WHERE ps.dataFineStato IS NULL AND s.codiceStato <> '00' AND ((p.dataInizio BETWEEN :dataInizio AND :dataFine) OR (p.dataFine BETWEEN :dataInizio AND :dataFine) OR (p.dataInizio <= :dataInizio AND p.dataFine >= :dataFine) OR (p.dataInizio >= :dataInizio AND p.dataFine <= :dataFine)) AND pp.buonoScontoRadice = :buonoScontoRadice ORDER BY pp.buonoScontoProgressivo"),
        @NamedQuery(name = "PromozionePianificazioneEntity.countSottoclassiUsedInPromoByStato",
                query = "SELECT new com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto(pp.sottoclasse, count(pp.sottoclasse)) FROM PromozionePianificazioneEntity pp INNER JOIN pp.promozioneTestataEntity t INNER JOIN t.promozioneStatoEntities ps INNER JOIN ps.statoPromozioneEntity s WHERE ps.dataFineStato IS NULL AND pp.sottoclasse IS NOT NULL AND s.codiceStato = :codiceStato GROUP BY pp.sottoclasse")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class PromozionePianificazioneEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 1L;
    /**
     * delete fisica
     */
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMOZIONE_PIANIFICAZIONE_ID_GENERATOR", sequenceName = "MUI_PROMO_PIANIFICAZ_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMOZIONE_PIANIFICAZIONE_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "BUDGET_PEZZI")
    private Integer budgetPezzi;

    @Column(name = "BUONO_SCONTO_PROGRESSIVO")
    private Integer buonoScontoProgressivo;

    @Column(name = "BUONO_SCONTO_RADICE")
    private Integer buonoScontoRadice;

    @Column(name = "CARTA_ORO")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal cartaOro;

    @Column(name = "CARTA_ORO_PRIVILEGIATE")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal cartaOroPrivilegiate;

    @Column(name = "CARTA_VERDE")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal cartaVerde;

    @Column(name = "CARTA_VERDE_PRIVILEGIATE")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal cartaVerdePrivilegiate;

    @Column(name = "CLASSE_DEFAULT")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal classeDefault;

    @Column(name = "CODICE_ELEMENTO", length = 100)
    private String codiceElemento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE")
    private Date dataFine;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE_BARCODE")
    private Date dataFineBarcode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO")
    private Date dataInizio;

    @Column(name = "DESCRIZIONE_SCONTO", length = 50)
    private String descrizioneSconto;

    @Column(length = 30)
    private String elemento;

    @Column(name = "ELENCO_CLUSTER", length = 16)
    private String elencoCluster;

    @Column(length = 16)
    private String esclusione;

    @Column(name = "FASCIA_ORARIA", length = 16)
    private String fasciaOraria;

    @Column(name = "GENERA_BUONO_WEB", length = 16)
    private String generaBuonoWeb;

    @Column(name = "GIORNO_SETTIMANA", length = 16)
    private String giornoSettimana;

    @Column(name = "LETTERA_CODICE_ECOMMERCE", length = 50)
    private String letteraCodiceEcommerce;

    @Column(name = "MAX_GIORNO")
    @Digits(integer = 6, fraction = 0)
    private Integer maxGiorno;

    @Column(name = "MAX_PERIODO")
    @Digits(integer = 6, fraction = 0)
    private Integer maxPeriodo;

    @Column(name = "MAX_SCONTRINO")
    @Digits(integer = 6, fraction = 0)
    private Integer maxScontrino;

    @Column(precision = 0)
    private Integer molteplicita;

    @Column(name = "NOTE_TIMING", length = 255)
    private String noteTiming;

    @Column(name = "NUM_RAGGRUPPAMENTO", length = 16)
    private String numRaggruppamento;

    @Column(name = "NUM_SET", length = 16)
    private String numSet;

    @Column(length = 16)
    private String scala;

    @Column(name = "STAMPA_OFFERTA", length = 16)
    private String stampaOfferta;

    @Column(length = 16)
    private String target;

    @Column(name = "TIPO_ELEMENTO", length = 20)
    private String tipoElemento;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_RIGA", nullable = false)
    private CfgPianificazTipoRigaEntity tipoRiga;

    @Column(name = "TIPO_SOGLIA", length = 20)
    private String tipoSoglia;

    @Column(name = "TIPO_TAGLIO", length = 20)
    private String tipoTaglio;

    @Column(name = "TIPO_TETTO", length = 20)
    private String tipoTetto;

    @Column
    @Digits(integer = 8, fraction = 2)
    private BigDecimal valore;

    @Column(name="VALORE_2")
    @Digits(integer = 8, fraction = 2)

    private BigDecimal valore2;

    @Column(name = "VALORE_SCALA")
    @Digits(integer = 6, fraction = 0)
    private Integer valoreScala;

    @Column(name = "VALORE_SOGLIA")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal valoreSoglia;

    @Column(name = "VALORE_TAGLIO")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal valoreTaglio;

    @Column(name = "VALORE_TETTO")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal valoreTetto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MECCANICA", nullable = false)
    private MeccanicheEntity meccanicaEntity;

    // bi-directional many-to-one association to PromozioneTestataEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private PromozioneTestataEntity promozioneTestataEntity;

    @ManyToOne
    @JoinColumn(name = "ID_PROMOZIONE_PIANIFICAZIONE", nullable = true)
    private PromozionePianificazioneEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, orphanRemoval = true)
    private Set<PromozionePianificazioneEntity> children;

    @OneToMany(mappedBy = "promozionePianificazioneEntity", cascade = {CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.PERSIST}, orphanRemoval = true)
    private Set<UploadFidayEntity> uploadFidaty;

    @Column(name = "NOTE", length = 255)
    private String note;

    @Column(name = "NUM_UTILIZZI", length = 16)
    private String numUtilizzi;

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

    @Column(name = "CANALE_DISPOSITIVO")
    private String canaleDispositivo;

    @Column(name = "CODICE_ON_LINE")
    private String codiceOnline;

    @Column(name = "VALIDITA_PERIODO")
    private Integer validitaPeriodo;

    @Column(name = "NUMERO_STAMPE")
    private Integer numeroStampe;

    @Column(name = "MAGGIOR_VANTAGGIO", length = 255)
    private String maggiorVantaggio;

    @Column(name = "INCLUDI_ESSELUNGA", length = 16)
    private String includiEsselunga;

    @Column(name = "INCLUDI_ESSERBELLA", length = 16)
    private String includiEsserbella;

    @Column(name = "INCLUDI_ATLANTIC", length = 16)
    private String includiAtlantic;

    @Column(name = "INCLUDI_LAESSE", length = 16)
    private String includiLaesse;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

    @Column(name = "SCONTO_RIFATTURABILE")
    private Integer scontoRifatturabile;

    @Column(name = "LINK", length = 4)
    private String link;

    @Column(name = "SCONTO_CASSA", length = 2)
    private String scontoCassa;

    @Column(name = "PORTI_VIA")
    private Integer portiVia;

    @Column(name = "PAGHI")
    private Integer paghi;

    @Column(name = "TIPO_SCONTO", length = 50)
    private String tipoSconto;

    @Column(name = "ORA_INIZIO")
    @Temporal(TemporalType.TIME)
    private Date oraInizio;

    @Column(name = "ORA_FINE")
    @Temporal(TemporalType.TIME)
    private Date oraFine;

    @Column(name = "STAMPA_ETICHETTA", length = 16)
    private String stampaEtichetta;

    @Column(name = "MECCANICA_REDENZIONE", length = 100)
    private String meccanicaRedenzione;

    @Column(name = "BRUCIABILITA", length = 100)
    private String bruciabilita;

    @Column(name = "VISUALIZZA_MECCANICA", length = 100)
    private String visualizzaMeccanica;

    @Column(name = "CLASSE", length = 100)
    private String classe;

    @Column(name = "SOTTOCLASSE")
    private String sottoclasse;

    @Column(name = "PREZZO_BUDGET")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal prezzoBudget;

    @Column(name = "TAGLIO_PUNTI")
    private Integer taglioPunti;

    @OneToMany(mappedBy = "pianificazione", cascade = {CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<CheckPianificazioneEntity> checks;

    @Column(name = "CUMULABILITA", length = 100)
    private String cumulabilita;

    @Column(name = "MULTITRANSAZIONE", length = 50)
    private String multiTransazione;

    @Column(name = "CLUSTER_CLIENTE", length = 50)
    private String clusterCliente;

    @Column(name="TIPO_SMALTIMENTO", length = 50)
    private String tipoSmaltimento;

    @Column(name="ID_PROMO_EXT", length = 255)
    private String idPromoExt;

    @Column(name="CODICE_GRUPPO", length = 10)
    private String codiceGruppo;

    @OneToMany(mappedBy = "pianificazione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, orphanRemoval = true)
    private Set<CastellettoMessaggiEntity> castellettoMessaggi;

    @Column(name = "CONV_BOLLINI" , length = 2, nullable = false)
    private String convBollini = "NO";

    @Column(name = "ESCLUDI_CHECK_SOVR", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private Boolean escludiCheckSovr = Boolean.FALSE;

    @Column(name = "FORMA_PAGAMENTO", length = 100)
    private String formaPagamento;

    @Column(name = "SCONTO_IN_APP", length = 2)
    private String scontoInApp;

    @OneToMany(mappedBy = "pianificazione", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<PianificazioneTotalizzatoriEntity> totalizzatori;

    @Column(name = "COD_INIZIATIVA", length = 5)
    private String codiceIniziativa;

    @Column(name = "PREZZO")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal prezzo;

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }

    public PromozionePianificazioneEntity addDetail(PromozionePianificazioneEntity detail) {
        if (getChildren() == null) {
            setChildren(new HashSet<>());
        }
        getChildren().add(detail);
        detail.setParent(this);
        return detail;
    }

    public PromozionePianificazioneEntity removeDetail(PromozionePianificazioneEntity detail) {
        getChildren().remove(detail);
        detail.setParent(null);
        return detail;
    }

    public UploadFidayEntity addUploadFidaty(UploadFidayEntity upload) {
        getUploadFidaty().add(upload);
        upload.setPromozionePianificazioneEntity(this);
        return upload;
    }

    public UploadFidayEntity removeUploadFidaty(UploadFidayEntity upload) {
        getUploadFidaty().remove(upload);
        upload.setPromozionePianificazioneEntity(null);
        return upload;
    }

    public void addCastellettoMessaggi(@NonNull CastellettoMessaggiEntity castellettoMessaggi){
        if ( getCastellettoMessaggi() == null ){
            setCastellettoMessaggi(new HashSet<>());
        }
        getCastellettoMessaggi().add(castellettoMessaggi);
        castellettoMessaggi.setPianificazione(this);
    }

    public void removeCastellettoMessaggi(@NonNull CastellettoMessaggiEntity castellettoMessaggi){
        if (getCastellettoMessaggi() != null && !getCastellettoMessaggi().isEmpty() ){
            getCastellettoMessaggi().remove(castellettoMessaggi);
            castellettoMessaggi.setPianificazione(null);
        }
    }

    public void addTotalizzatore(@NonNull PianificazioneTotalizzatoriEntity totalizzatore){
        if ( getTotalizzatori() == null ){
            setTotalizzatori(new HashSet<>());
        }
        getTotalizzatori().add(totalizzatore);
        totalizzatore.setPianificazione(this);
    }

    public void removeTotalizzatore(@NonNull PianificazioneTotalizzatoriEntity totalizzatore){
        if (getTotalizzatori() != null && !getTotalizzatori().isEmpty() ){
            getTotalizzatori().remove(totalizzatore);
            totalizzatore.setPianificazione(null);
        }
    }
    @Transient
    public boolean isRafSeed() {
        // se ho num raggruppamento 1 o 2
        // se faccio parte di un set che ha almeno un raggruppamento
        // con campo multiTransazione == "multitransazione"
        if ( getTipoRiga().getCodiceTipo().equals("R") &&
                ("1".equals(getNumRaggruppamento()) || "2".equals(getNumRaggruppamento()))
        )  {
            if (parent != null && parent.isSet() ){
                return parent.getChildren().stream().filter(p->p.isRaggruppamento())
                        .filter(p->"multitransazione".equalsIgnoreCase(p.getMultiTransazione())).findAny().isPresent();
            }
        }
        return false;
    }

    @Transient
    public boolean isSet(){
        return getTipoRiga().getCodiceTipo().equals("S");
    }
    @Transient
    public boolean isRaggruppamento(){
        return getTipoRiga().getCodiceTipo().equals("R");
    }

}