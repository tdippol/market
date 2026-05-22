package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.NamedStoredProcedureQuery;
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
 * The persistent class for the MUI_PROMO_PUBBLICAZ_TESTATA database table.
 *
 */
@Entity
@Table(name = "MUI_PROMO_PUBBLICAZ_TESTATA", schema = Metadata.SCHEMA)
@NamedQueries({
	@NamedQuery(name = "PromoPubblicazioneTestataEntity.findAll",
			query = "SELECT m FROM PromoPubblicazioneTestataEntity m"),
	@NamedQuery(name = "PromoPubblicazioneTestataEntity.findByPromoID",
	query = "SELECT m FROM PromoPubblicazioneTestataEntity m WHERE m.promozioneTestataEntity.id = :promoID ORDER BY m.dataPubblicazione ASC")
})
@NamedStoredProcedureQuery(name = "P_MUI_UPDATE_ESITO_PUBBLICAZ", procedureName = Constants.SP_UPDATE_ESITO_PUBBLICAZIONE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class PromoPubblicazioneTestataEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMO_PUBBLICAZ_TESTATA_ID_GENERATOR", sequenceName = "MUI_PROMO_PUBB_TESTATA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMO_PUBBLICAZ_TESTATA_ID_GENERATOR")

	private Long id;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_PUBBLICAZIONE")

	private Date dataPubblicazione;

	@Column(name = "DESCRIZIONE")

	private String descrizione;

	@Column(name = "FLAG_ESITO")

	private BigDecimal flagEsito;
	@Column(name = "EXPORT_ID")

	private String exportId;

	// bi-directional many-to-one association to MuiPromozioneTestata
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROMOZIONE")

	private PromozioneTestataEntity promozioneTestataEntity;

	// bi-directional many-to-one association to MuiStatoPromozione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATO")

	private StatoPromozioneEntity statoPromozioneEntity;

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