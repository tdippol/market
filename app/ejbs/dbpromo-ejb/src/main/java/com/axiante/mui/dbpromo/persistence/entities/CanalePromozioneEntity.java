package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The persistent class for the MUI_CANALE_PROMOZIONE database table.
 *
 */
@Entity
@Getter
@Setter
@Slf4j
@Table(name = "MUI_CANALE_PROMOZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
	@NamedQuery(name = "MuiCanalePromozione.findAll", query = "SELECT m FROM CanalePromozioneEntity m"),
	@NamedQuery(name = "MuiCanalePromozione.findAllByGroup",
			query = "SELECT c FROM CanalePromozioneEntity c WHERE c.gruppoPromozioneEntity = :gruppoPromozioneEntity"),
	@NamedQuery(name = "MuiCanalePromozione.findByDescription",
			query = "SELECT c FROM CanalePromozioneEntity c WHERE c.descrizione = :descrizione"),
	@NamedQuery(name = "MuiCanalePromozione.findByCodiceCanale",
			query = "SELECT c FROM CanalePromozioneEntity c WHERE c.codiceCanale = :codiceCanale"),
	@NamedQuery(name = "MuiCanalePromozione.findByCodiciCanale",
			query = "SELECT c FROM CanalePromozioneEntity c WHERE c.codiceCanale IN :codiciCanale"),
		@NamedQuery(name = "MuiCanalePromozione.countByIdWithTipologiaInitialLoad",
				query = "SELECT count(c) FROM CanalePromozioneEntity c JOIN c.tipologiaRifatturazione t WHERE c.id = :id AND c.flRateSingolaAttivita = true")
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public class CanalePromozioneEntity implements Serializable, ComboBoxCapable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CANALE_PROMOZIONE_ID_GENERATOR", sequenceName = "MUI_CANALE_PROMOZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CANALE_PROMOZIONE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(name = "CODE_RANGE_MAX", precision = 8)
	private Long codeRangeMax;

	@Column(name = "CODE_RANGE_MIN", precision = 8)
	private Long codeRangeMin;

	@EqualsAndHashCode.Include
	@Column(name = "CODICE_CANALE", nullable = false, precision = 3)
	private Long codiceCanale;

	@Column(length = 30)
	private String descrizione;

	@Column(name = "SIGLA_CANALE", length = 10)
	private String siglaCanale;

	// bi-directional many-to-one association to MuiCanaleLastProg
	@OneToMany(mappedBy = "muiCanalePromozione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CanaleLastProgEntity> muiCanaleLastProgs;

	// bi-directional many-to-one association to GruppoPromozioneEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_GRUPPO", nullable = false)
	private GruppoPromozioneEntity gruppoPromozioneEntity;

	// bi-directional many-to-one association to MuiCfgAbilitaMeccCanale
	@OneToMany(mappedBy = "canalePromozioneEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, orphanRemoval = true)
	private Set<CfgAbilitaMeccCanaleEntity> muiCfgAbilitaMeccCanales = new HashSet<>();

	// bi-directional many-to-one association to MuiCfgCanaleNegozi
	@OneToMany(mappedBy = "muiCanalePromozione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CfgCanaleNegoziEntity> muiCfgCanaleNegozis;

	// bi-directional many-to-one association to MuiCfgStatiCanaleConsent
	@OneToMany(mappedBy = "muiCanalePromozione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CfgStatiCanaleConsentEntity> muiCfgStatiCanaleConsents;

	// bi-directional many-to-one association to CfgStatiTransizioniEntity
	@OneToMany(mappedBy = "muiCanalePromozione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CfgStatiTransizioniEntity> cfgStatiTransizioniEntities;

	// bi-directional many-to-one association to CreaPromozioneEntity
	@OneToMany(mappedBy = "canalePromozioneEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CreaPromozioneEntity> creaPromozioneEntities;

	// bi-directional many-to-one association to PromozioneTestataEntity
	@OneToMany(mappedBy = "muiCanalePromozione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<PromozioneTestataEntity> promozioneTestataEntities;

	@Column(name = "ABILITA_UPLOAD", precision = 1)
	private Integer abilitaUpload;

	@Column(name = "FILE_NAMING_CONVENTION", length = 255)
	private String namingConvention;

	@Column(name = "RIPETIZIONE_MECCANICHE")
	private Integer ripetizioneMeccaniche;

	@Column(name="UPLOAD_EXT_NAMING_CONVENTION")
	@Convert(converter = BooleanConverter.class)
	boolean legacyUploadNamingConvention = true;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "MUI_STATO_CANALE_BLOCCO", schema = Metadata.SCHEMA,
			joinColumns = {@JoinColumn(name = "ID_CANALE", referencedColumnName = "ID")},
			inverseJoinColumns = {@JoinColumn(name = "ID_STATO", referencedColumnName = "ID")})
	private Set<StatoPromozioneEntity> statiBlocco;

	@Column(name = "MAX_PROGRESSIVO", precision = 16 )
	private Long maxProgressivo;

	@Column(name = "DUPLICA_ARTICOLO")
	@Convert(converter = BooleanConverter.class)
	private Boolean duplicaArticolo;

	@Column(name = "DUPLICA_REPARTO")
	@Convert(converter = BooleanConverter.class)
	private Boolean duplicaReparto;

	@Column(name="DUPLICA_GRM")
	@Convert(converter = BooleanConverter.class)
	private Boolean duplicaGrm;

	@Column(name="DUPLICA_TOTALE")
	@Convert(converter = BooleanConverter.class)
	private Boolean duplicaTotale;

	@OneToMany(mappedBy = "canale", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<CfgCanaleFlagEntity> muiCfgCanaleFlagEntities;


	@OneToMany(mappedBy = "canale", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<CfgCanaliAttributiEntity> cfgCanaliAttributi;

	@Column(name = "FL_CHECK_DATE", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flCheckDate = false;

	@Column(name = "TOLLERANZA_DATA_FINE", nullable = false)
	private Integer tolleranzaDataFine = 0;

	@Column(name = "TOLLERANZA_DATA_INIZIO", nullable = false)
	private Integer tolleranzaDataInizio = 0;

	@Column(name = "FL_OVERRIDE_PIANIFICAZ_INIZIO", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flOverridePianificazioneInizio = false;

	@Column(name = "FL_VAL_PUNTO_FRAGOLA", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flValorePuntoFragola = false;

	@Column(name = "FL_MECCANICA_SINGOLA", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flMeccanicaSingola = false;

	@Column(name = "FL_BIDONE_APERTO", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flBidoneAperto = false;

	@Column(name = "FL_CHK_DATE_OVERRIDE_DELETE", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flCheckDateOverrideDelete = false;

	@Column(name = "DELETE_AFTER_DATA_FINE")
	private Integer deleteActiveDaysAfterDataFine = 0;

	@Column(name = "MAX_NUM_PROMO")
	private Long maxPromo = null;

	@Column(name = "FL_MARCHIO_PRIVATO", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flMarchioPrivato = false;

	@Column(name = "FL_LOGO_MESSAGGIO", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flLogoMessaggio = false;

	@Column(name = "FL_CALCOLA_REPARTO", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flCalcolaReparto = false;

	@Column(name = "FL_SEC_SET", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flSicurezzaSet = false;

	@Column(name = "FL_TOTALIZZATORI", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flTotalizzatori = false;

	@Column(name = "FL_OVERLAP_OFFSET", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flOverlapOffset = false;

	@Column(name = "OVERLAP_OFFSET_START", nullable = false)
	private Integer overlapOffsetStart = 0;

	@Column(name = "OVERLAP_OFFSET_END", nullable = false)
	private Integer overlapOffsetEnd = 0;

	@Column(name = "FL_DIFFERENZIAZIONE_NEGOZI", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flDifferenziazioneNegozi = false;

	@Column(name = "FL_LISTA_CONDIZIONALE", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flListaCondizionale = false;

	@Column(name = "FL_RATE_CIFRA_FISSA", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flRateCifraFissa = false;

	@Column(name = "FL_RATE_COSTO_CONTATTO", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flRateCostoContatto = false;

	@Column(name = "FL_RATE_SINGOLA_ATTIVITA", nullable = false)
	@Convert(converter = BooleanConverter.class)
	private Boolean flRateSingolaAttivita = false;

	@Column(name = "VALORE_COSTO_CONTATTO", precision = 6, scale = 2)
	private BigDecimal valoreCostoContatto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "MUI_CFG_CANALE_TIPO_RIFATT", schema = Metadata.SCHEMA,
			joinColumns = {@JoinColumn(name = "ID_CANALE", referencedColumnName = "ID")},
			inverseJoinColumns = {@JoinColumn(name = "ID_TIPO_PROMO_RIFATT", referencedColumnName = "CODICE_TIPO_PROMO")})
	private TipoPromoRifatturazioneEntity tipologiaRifatturazione;

	public void addStatoBlocco(@NonNull StatoPromozioneEntity stato) {
		if (getStatiBlocco() == null) {
			setStatiBlocco(new HashSet<>());
		}
		getStatiBlocco().add(stato);
		stato.addCanale(this);
	}

	public void removeStatoBlocco(@NonNull StatoPromozioneEntity stato) {
		if ( getStatiBlocco() != null ) {
			if ( !getStatiBlocco().remove(stato) ){
				log.warn(String.format("Lo stato %s non e' presente tra gli stati di blocco per il canale %s",
						stato.getCodiceStato(), getCodiceCanale()));
			}
			stato.removeCanale(this);
		}
	}
	
	public CanaleLastProgEntity addMuiCanaleLastProg(CanaleLastProgEntity muiCanaleLastProg) {
		getMuiCanaleLastProgs().add(muiCanaleLastProg);
		muiCanaleLastProg.setMuiCanalePromozione(this);
		return muiCanaleLastProg;
	}

	public CanaleLastProgEntity removeMuiCanaleLastProg(CanaleLastProgEntity muiCanaleLastProg) {
		getMuiCanaleLastProgs().remove(muiCanaleLastProg);
		muiCanaleLastProg.setMuiCanalePromozione(null);
		return muiCanaleLastProg;
	}

	public void addMuiCfgAbilitaMeccCanale(final CfgAbilitaMeccCanaleEntity muiCfgAbilitaMeccCanale) {
		getMuiCfgAbilitaMeccCanales().add(muiCfgAbilitaMeccCanale);
		muiCfgAbilitaMeccCanale.setCanalePromozioneEntity(this);
	}

	public void removeMuiCfgAbilitaMeccCanale(final CfgAbilitaMeccCanaleEntity muiCfgAbilitaMeccCanale) {
		getMuiCfgAbilitaMeccCanales().remove(muiCfgAbilitaMeccCanale) ;
		muiCfgAbilitaMeccCanale.setCanalePromozioneEntity(null);

	}

	public CfgCanaleNegoziEntity addMuiCfgCanaleNegozi(CfgCanaleNegoziEntity muiCfgCanaleNegozi) {
		getMuiCfgCanaleNegozis().add(muiCfgCanaleNegozi);
		muiCfgCanaleNegozi.setMuiCanalePromozione(this);
		return muiCfgCanaleNegozi;
	}

	public CfgCanaleNegoziEntity removeMuiCfgCanaleNegozi(CfgCanaleNegoziEntity muiCfgCanaleNegozi) {
		getMuiCfgCanaleNegozis().remove(muiCfgCanaleNegozi);
		muiCfgCanaleNegozi.setMuiCanalePromozione(null);
		return muiCfgCanaleNegozi;
	}

	public CfgStatiCanaleConsentEntity addMuiCfgStatiCanaleConsent(CfgStatiCanaleConsentEntity muiCfgStatiCanaleConsent) {
		getMuiCfgStatiCanaleConsents().add(muiCfgStatiCanaleConsent);
		muiCfgStatiCanaleConsent.setMuiCanalePromozione(this);
		return muiCfgStatiCanaleConsent;
	}

	public CfgStatiCanaleConsentEntity removeMuiCfgStatiCanaleConsent(CfgStatiCanaleConsentEntity muiCfgStatiCanaleConsent) {
		getMuiCfgStatiCanaleConsents().remove(muiCfgStatiCanaleConsent);
		muiCfgStatiCanaleConsent.setMuiCanalePromozione(null);
		return muiCfgStatiCanaleConsent;
	}

	public CfgStatiTransizioniEntity addMuiCfgStatiTransizioni(CfgStatiTransizioniEntity cfgStatiTransizioniEntity) {
		if ( getCfgStatiTransizioniEntities() == null ) {
			setCfgStatiTransizioniEntities(new HashSet<>());
		}
		getCfgStatiTransizioniEntities().add(cfgStatiTransizioniEntity);
		cfgStatiTransizioniEntity.setMuiCanalePromozione(this);
		return cfgStatiTransizioniEntity;
	}

	public CfgStatiTransizioniEntity removeMuiCfgStatiTransizioni(CfgStatiTransizioniEntity cfgStatiTransizioniEntity) {
		if ( getCfgStatiTransizioniEntities() != null ) {
			getCfgStatiTransizioniEntities().remove(cfgStatiTransizioniEntity);
		}
		cfgStatiTransizioniEntity.setMuiCanalePromozione(null);
		return cfgStatiTransizioniEntity;
	}

	public CreaPromozioneEntity addMuiCreaPromozione(CreaPromozioneEntity creaPromozioneEntity) {
		getCreaPromozioneEntities().add(creaPromozioneEntity);
		creaPromozioneEntity.setCanalePromozioneEntity(this);
		return creaPromozioneEntity;
	}

	public CreaPromozioneEntity removeMuiCreaPromozione(CreaPromozioneEntity creaPromozioneEntity) {
		getCreaPromozioneEntities().remove(creaPromozioneEntity);
		creaPromozioneEntity.setCanalePromozioneEntity(null);
		return creaPromozioneEntity;
	}

	public PromozioneTestataEntity addPromozioneTestataEntity(PromozioneTestataEntity promozioneTestataEntity) {
		getPromozioneTestataEntities().add(promozioneTestataEntity);
		promozioneTestataEntity.setMuiCanalePromozione(this);
		return promozioneTestataEntity;
	}

	public PromozioneTestataEntity removePromozioneTestataEntity(PromozioneTestataEntity promozioneTestataEntity) {
		getPromozioneTestataEntities().remove(promozioneTestataEntity);
		promozioneTestataEntity.setMuiCanalePromozione(null);
		return promozioneTestataEntity;
	}

	@Override
	@Transient
	public String getKey() {
		return "" + getId();
	}

	@Override
	@Transient
	public String getLabel() {
		return getDescrizione();
	}

	public void addCfgCanaleFlag(@NonNull CfgCanaleFlagEntity flag){
		if ( getMuiCfgCanaleFlagEntities() == null ){
			setMuiCfgCanaleFlagEntities(new HashSet<>());
		}
		getMuiCfgCanaleFlagEntities().add(flag);
		flag.setCanale(this);
	}

	public void removeCfgCanaleFlag(@NonNull CfgCanaleFlagEntity flag){
		if ( getMuiCfgCanaleFlagEntities() != null ){
			getMuiCfgCanaleFlagEntities().remove(flag);
		}
		if ( getId() != null && getId().equals(flag.getCanale().getId())){
			flag.setCanale(null);
		}
	}
}