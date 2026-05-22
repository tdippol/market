package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import com.axiante.mui.validator.model.Promotion;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_CREA_PROMOZIONE database table.
 */
@Entity
@Table(name = "MUI_CREA_PROMOZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "CreaPromozioneEntity.findAll", query = "SELECT e FROM CreaPromozioneEntity e"),
		@NamedQuery(name = "CreaPromozioneEntity.findAllByUserId",
				query = "SELECT c FROM CreaPromozioneEntity c WHERE c.userId = :userId"),
		@NamedQuery(name = "CreaPromozioneEntity.findByUserIdAndSlotId",
				query = "SELECT c FROM CreaPromozioneEntity c WHERE c.userId = :userId AND c.slotId = :slotId") })

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class CreaPromozioneEntity implements Serializable, Promotion, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 4730332300139100892L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CREA_PROMOZIONE_ID_GENERATOR", sequenceName = "MUI_CREA_PROMOZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CREA_PROMOZIONE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(length = 4)
	private String anno;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(length = 100)
	private String descrizione;

	@Column(length = 2000)
	private String messaggio;

	@Column(name = "SLOT_ID", nullable = false, length = 50)
	private String slotId;

	@Column(name = "USER_ID", nullable = false, length = 50)
	private String userId;

	// bi-directional many-to-one association to MuiCanalePromozione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CANALE")
	private CanalePromozioneEntity canalePromozioneEntity;

	// bi-directional many-to-one association to MuiCanalePromozione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_GRUPPO")
	private GruppoPromozioneEntity gruppoPromozioneEntity;

	// bi-directional many-to-one association to PromozioneTestataEntity
	@OneToMany(mappedBy = "creaPromozioneEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<PromozioneTestataEntity> promozioneTestataEntities;

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

	@Column(name = "ORA_INIZIO")
	@Temporal(TemporalType.TIME)
	private Date oraInizio;

	@Column(name = "ORA_FINE")
	@Temporal(TemporalType.TIME)
	private Date oraFine;

	@Override
	@PreUpdate
	@PrePersist
	public String getUuid() {
		if (this.uuid == null) {
			this.uuid = AxUUID.randomUUID().toString();
		}
		return uuid;
	}

	public PromozioneTestataEntity addPromozioneTestataEntity(PromozioneTestataEntity promozioneTestataEntity) {
		getPromozioneTestataEntities().add(promozioneTestataEntity);
		promozioneTestataEntity.setCreaPromozioneEntity(this);

		return promozioneTestataEntity;
	}

	public PromozioneTestataEntity removePromozioneTestataEntity(PromozioneTestataEntity promozioneTestataEntity) {
		getPromozioneTestataEntities().remove(promozioneTestataEntity);
		promozioneTestataEntity.setCreaPromozioneEntity(null);

		return promozioneTestataEntity;
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
	public String getNoteMarketing() {
		return null;
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
}