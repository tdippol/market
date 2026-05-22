package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
 * The persistent class for the MUI_CFG_PIANIFICAZIONE database table.
 */
@Entity
@Table(name = "MUI_CFG_PIANIFICAZIONE", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@Getter
@Setter
@NamedQueries(value = {
		@NamedQuery(name = "CfgPianificazioneEntity.findBySetAndMeccanicaAndCampo", query = "SELECT p FROM CfgPianificazioneEntity p INNER JOIN CfgPianificazioneCampiEntity c ON p.muiCfgPianificazioneCampi=c INNER JOIN CfgConfHeaderEntity h ON p.muiCfgConfHeader=h AND h.muiCfgSetPianificazione=:setPianificazione AND h.meccanicaEntity=:meccanica WHERE c.campo=:campo"),
		@NamedQuery(name = "CfgPianificazioneEntity.findBySetAndMeccanicaAndCampoAndTipoRiga", query = "SELECT p FROM CfgPianificazioneEntity p INNER JOIN CfgPianificazioneCampiEntity c ON p.muiCfgPianificazioneCampi=c INNER JOIN CfgConfHeaderEntity h ON p.muiCfgConfHeader=h AND h.muiCfgSetPianificazione=:setPianificazione AND h.meccanicaEntity=:meccanica WHERE c.campo=:campo AND p.muiCfgPianificazTipoRiga.codiceTipo=:tipoRiga"),
		@NamedQuery(name = "CfgPianificazioneEntity.findTipiRigaByHeaderAndCampo", query = "SELECT DISTINCT r.descrizione FROM CfgPianificazioneEntity p INNER JOIN CfgPianificazTipoRigaEntity r ON p.muiCfgPianificazTipoRiga=r INNER JOIN CfgPianificazioneCampiEntity c ON p.muiCfgPianificazioneCampi=c AND c.campo=:campo WHERE p.muiCfgConfHeader.id=:idHeader"),
		@NamedQuery(name = "CfgPianificazioneEntity.findAllByMuiCfgSetPianificazione", query = "SELECT DISTINCT p FROM CfgPianificazioneEntity p JOIN FETCH p.muiCfgConfHeader h JOIN FETCH h.muiCfgSetPianificazione s where s.id = :idSet"),
		@NamedQuery(name = "CfgPianificazioneEntity.findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento", query = "SELECT DISTINCT p.lista FROM CfgPianificazioneEntity p JOIN FETCH p.muiCfgPianificazioneCampi c JOIN FETCH p.muiCfgConfHeader h JOIN FETCH h.meccanicaEntity m JOIN FETCH h.muiCfgSetPianificazione s JOIN FETCH s.promozioneTestataEntities t  WHERE t.id = :promoId AND m.id = :meccanicaId AND c.codiceCampo = 'Tipo Elemento'"),
		@NamedQuery(name = "CfgPianificazioneEntity.findAllByPromoAndMeccanicaId", query = "SELECT p FROM CfgPianificazioneEntity p JOIN FETCH p.muiCfgConfHeader h JOIN FETCH h.meccanicaEntity m JOIN FETCH h.muiCfgSetPianificazione s JOIN FETCH s.promozioneTestataEntities t WHERE t = :promo AND m.id = :meccanicaId"),
		@NamedQuery(name = "CfgPianificazioneEntity.findAllDistinctByCanaleAndMeccanica", query = "SELECT DISTINCT p FROM CfgPianificazioneEntity p JOIN FETCH p.muiCfgConfHeader h JOIN FETCH h.meccanicaEntity m JOIN FETCH h.muiCfgSetPianificazione s JOIN FETCH s.promozioneTestataEntities t WHERE h.meccanicaEntity = :meccanica AND t.muiCanalePromozione = :canale"),
		@NamedQuery(name = "CfgPianificazioneEntity.findAllByCanaleAndMeccanicaAndField", query = "SELECT cfg FROM CfgPianificazioneEntity cfg JOIN FETCH cfg.muiCfgPianificazioneCampi cmp JOIN FETCH cfg.muiCfgConfHeader h JOIN FETCH h.muiCfgSetPianificazione s JOIN s.promozioneTestataEntities t WHERE  h.meccanicaEntity = :meccanica AND t.muiCanalePromozione = :canale AND cmp.campo = :campo"),
		@NamedQuery(name = "CfgPianificazioneEntity.findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo", query = "SELECT cfg FROM CfgPianificazioneEntity cfg JOIN FETCH cfg.muiCfgPianificazioneCampi cmp JOIN FETCH cfg.muiCfgConfHeader h JOIN FETCH h.muiCfgSetPianificazione s JOIN s.promozioneTestataEntities t WHERE  h.meccanicaEntity = :meccanica AND t.muiCanalePromozione = :canale AND cmp.campo = :campo AND cfg.muiCfgPianificazTipoRiga = :tipoRiga AND t.id = :idPromozione"),
		@NamedQuery(name = "CfgPianificazioneEntity.getDefaultValues", query = "SELECT DISTINCT p FROM PromozioneTestataEntity t INNER JOIN CfgSetPianificazioneEntity s ON t.muiCfgSetPianificazione=s INNER JOIN CfgConfHeaderEntity h ON h.muiCfgSetPianificazione=s AND h.meccanicaEntity.id=:meccanicaId INNER JOIN CfgPianificazioneEntity p ON p.muiCfgConfHeader=h INNER JOIN CfgPianificazioneCampiEntity c ON p.muiCfgPianificazioneCampi=c INNER JOIN CfgPianificazTipoRigaEntity r ON p.muiCfgPianificazTipoRiga=r WHERE t.id=:testataId AND r.codiceTipo=:codeTipoRiga AND p.defValue IS NOT NULL") })
public class CfgPianificazioneEntity
		implements Serializable, DbPromoEntityInterface, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * cancellazione fisica
	 */

	@Id
	@SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_PIANIFICAZIONE_ID_GENERATOR", sequenceName = "MUI_CFG_PIANIFICAZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_PIANIFICAZIONE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(name = "ABILITA_PER_TIPO_ELEMENTO", length = 50)
	private String abilitaPerTipoElemento;

	@Column(name = "DESCRIZIONE", length = 255)
	private String descrizione;

	@Column(name = "HIDE", length = 1)
	private String hide;

	@Column(name = "LISTA", length = 255)
	private String lista;

	@Column(name = "MANDATORY", length = 1)
	private String mandatory;

	@Column(name = "ORDINAMENTO", precision = 8)
	private Integer ordinamento = 999;

	@Column(name = "SICUREZZA", length = 1)
	private String sicurezza;

	@Column(name = "TIPO_LISTA", length = 255)
	private String tipoLista;

	@Column(name = "LISTA_CONDIZIONALE", length = 4000)
	@Basic
	private String listaCondizionale;

	@Column(name = "CHIAVE", length = 255)
	private String chiave;

	@Column(name = "UNIQUE_PERIODO_MECCANICA", length = 1)
	private String uniquePeriodoMeccanica;

	@Column(name = "UNIQUE_PROMO", length = 1)
	private String uniquePromo;

	@Column(name = "WARN", length = 1)
	private String warn;

	@Column(name = "DEF_VALUE", length = 255)
	private String defValue;

	@Column(name = "FLAG_MULTISELECT")
	private Short multiselect;

	@Column(name = "LENGTH")
	private Integer length;

	// bi-directional many-to-one association to MuiCfgPianificazioneCampi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CAMPO")
	private CfgPianificazioneCampiEntity muiCfgPianificazioneCampi;

	// bi-directional many-to-one association to MuiCfgPianificazTipoRiga
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_RIGA")
	private CfgPianificazTipoRigaEntity muiCfgPianificazTipoRiga;

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

	// bi-directional many-to-one association to MuiCfgPianificazioneCampi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CFG_CONF_HEADER")
	private CfgConfHeaderEntity muiCfgConfHeader;

	// ALTER TABLE DBPROMO.MUI_CFG_PIANIFICAZIONE ADD FORMAT_STRING VARCHAR(50 );
	@Column(name = "FORMAT_STRING", length = 50)
	private String formatString;

	// ALTER TABLE DBPROMO.MUI_CFG_PIANIFICAZIONE ADD MIN_LENGTH BIGINT;
	@Column(name = "MIN_LENGTH")
	private Integer minLength;

	// ALTER TABLE DBPROMO.MUI_CFG_PIANIFICAZIONE ADD ALLOW_ZERO INTEGER DEFAULT 0;
	@Column(name = "ALLOW_ZERO")
	private Integer allowZero = 0;

	// ALTER TABLE DBPROMO.MUI_CFG_PIANIFICAZIONE ADD VALIDO_SE_RAGGRUPPAMENTO
	// BIGINT ;
	@Column(name = "VALIDO_SE_RAGGRUPPAMENTO")
	private Integer validoSeRaggruppamento;

	// ALTER TABLE MUI_CFG_PIANIFICAZIONE ADD LOOKUP_ATTRIBUTE VARCHAR(255) DEFAULT
	// NULL;
	@Column(name = "LOOKUP_ATTRIBUTE")
	private String lookupAttribute;

	// ALTER TABLE MUI_CFG_PIANIFICAZIONE ADD VALUE VARCHAR(255) DEFAULT NULL;
	@Column(name = "VALUE")
	private String value;

	// ALTER TABLE MUI_CFG_PIANIFICAZIONE ADD VALUE_TYPE VARCHAR(255) DEFAULT NULL;
	@Column(name = "VALUE_TYPE")
	private String valueType;

	@Column(name = "FORMULA", nullable = true)
	private String formula;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "FORMULA_ENABLED")
	private Boolean formulaEnabled;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "COMBOBOX")
	private Boolean renderAsCombo;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "INVISIBILE")
	private Boolean invisible = Boolean.FALSE;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "PROPAGA")
	private Boolean propaga = Boolean.FALSE;

	@Override
	@PreUpdate
	@PrePersist
	public String getUuid() {
		if (this.uuid == null) {
			this.uuid = AxUUID.randomUUID().toString();
		}
		return uuid;
	}

	@Transient
	@Deprecated
	public Integer getMaxLength() {
		return allowZero;
	}

	@Transient
	@Deprecated
	public void setMaxLength(Integer i) {
		allowZero = i;
	}

}