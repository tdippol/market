package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * The persistent class for the MUI_PROMO_STATI_CONSENTITI database table.
 *
 */
@Entity
@Table(name = "MUI_PROMO_STATI_CONSENTITI", schema = Metadata.SCHEMA)
@NamedQuery(name = "PromoStatiConsentitiEntity.findAll", query = "SELECT m FROM PromoStatiConsentitiEntity m")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class PromoStatiConsentitiEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMO_STATI_CONSENTITI_ID_GENERATOR", sequenceName = "MUI_PROMO_STATI_CONSENT_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMO_STATI_CONSENTITI_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)

	private Long id;
	// bi-directional many-to-one association to PromozioneTestataEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROMOZIONE", nullable = false)

	private PromozioneTestataEntity promozioneTestataEntity;

	// bi-directional many-to-one association to StatoPromozioneEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATO", nullable = false)

	private StatoPromozioneEntity statoPromozioneEntity;

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