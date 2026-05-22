package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * The persistent class for the MUI_CFG_SET_PIANIFICAZIONE database table.
 *
 */
@Entity
@Table(name = "MUI_CFG_SET_PIANIFICAZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "MuiCfgSetPianificazione.findAll", query = "SELECT m FROM CfgSetPianificazioneEntity m"),
		@NamedQuery(name = "MuiCfgSetPianificazione.findAllEnabled", query = "SELECT m FROM CfgSetPianificazioneEntity m WHERE m.id > 0 ORDER BY m.descrizione"),
		@NamedQuery(name = "MuiCfgSetPianificazione.findOpenSet", query = "SELECT p FROM CfgSetPianificazioneEntity p WHERE p.id > 0 AND p.dataFine IS null"),
		@NamedQuery(name = "MuiCfgSetPianificazione.findByLockedAndDataInizio", query = "SELECT m FROM CfgSetPianificazioneEntity m WHERE m.dataInizio <= :dataInizio AND (m.dataFine >= :dataInizio OR m.dataFine is NULL)") })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class CfgSetPianificazioneEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_SET_PIANIFICAZIONE_ID_GENERATOR", sequenceName = "MUI_CFG_SET_PIANIFICAZ_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_SET_PIANIFICAZIONE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)

	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_FINE")

	private Date dataFine;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INIZIO")

	private Date dataInizio;

	@Column(length = 255)

	private String descrizione;

	@Column(precision = 1)

	private Long locked;

	// bi-directional many-to-one association to PromozioneTestataEntity
	@OneToMany(mappedBy = "muiCfgSetPianificazione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
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

	// bi-directional many-to-one association to MuiCfgPianificazione
	@OneToMany(mappedBy = "muiCfgSetPianificazione",
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			orphanRemoval = true)
	private Set<CfgConfHeaderEntity> muiCfgConfHeaders;

	@Override
	@PreUpdate
	@PrePersist
	public String getUuid() {
		if (this.uuid == null) {
			this.uuid = AxUUID.randomUUID().toString();
		}
		return uuid;
	}

	public CfgConfHeaderEntity addMuiCfgConfHeaderEntity(CfgConfHeaderEntity muiCfgConfHeader) {
		getMuiCfgConfHeaders().add(muiCfgConfHeader);
		muiCfgConfHeader.setMuiCfgSetPianificazione(this);

		return muiCfgConfHeader;
	}

	public CfgConfHeaderEntity removeMuiCfgConfHeaderEntity(CfgConfHeaderEntity muiCfgConfHeader) {
		getMuiCfgConfHeaders().remove(muiCfgConfHeader);
		muiCfgConfHeader.setMuiCfgSetPianificazione(null);

		return muiCfgConfHeader;
	}

	public PromozioneTestataEntity addPromozioneTestataEntity(PromozioneTestataEntity promozioneTestataEntity) {
		getPromozioneTestataEntities().add(promozioneTestataEntity);
		promozioneTestataEntity.setMuiCfgSetPianificazione(this);

		return promozioneTestataEntity;
	}

	public PromozioneTestataEntity removePromozioneTestataEntity(PromozioneTestataEntity promozioneTestataEntity) {
		getPromozioneTestataEntities().remove(promozioneTestataEntity);
		promozioneTestataEntity.setMuiCfgSetPianificazione(null);

		return promozioneTestataEntity;
	}

}