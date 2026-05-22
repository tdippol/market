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
 * The persistent class for the MUI_PROMOZIONE_STATO database table.
 */
@Entity
@Table(name = "MUI_PROMOZIONE_STATO", schema = Metadata.SCHEMA)
@NamedQueries({ @NamedQuery(name = "PromozioneStatoEntity.findAll", query = "SELECT m FROM PromozioneStatoEntity m"),
		@NamedQuery(name = "PromozioneStatoEntity.findByPromozioneID", query = "SELECT m FROM PromozioneStatoEntity m WHERE m.promozioneTestataEntity.id = :promoId") })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class PromozioneStatoEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMOZIONE_STATO_ID_GENERATOR", sequenceName = "MUI_PROMOZIONE_STATO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMOZIONE_STATO_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)

	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_FINE_STATO")

	private Date dataFineStato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INIZIO_STATO")

	private Date dataInizioStato;

	// bi-directional many-to-one association to PromozioneTestataEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROMOZIONE")

	private PromozioneTestataEntity promozioneTestataEntity;

	// bi-directional many-to-one association to StatoPromozioneEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATO")

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