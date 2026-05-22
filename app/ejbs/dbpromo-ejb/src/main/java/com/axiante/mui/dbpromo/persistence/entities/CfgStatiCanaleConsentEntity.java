package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_CFG_STATI_CANALE_CONSENT database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_STATI_CANALE_CONSENT", schema = Metadata.SCHEMA)
@NamedQueries({
	@NamedQuery(name = "MuiCfgStatiCanaleConsent.findAll", query = "SELECT m FROM CfgStatiCanaleConsentEntity m"),
	@NamedQuery(name = "MuiCfgStatiCanaleConsent.findByCanaleAndStato",
			query = "SELECT e FROM CfgStatiCanaleConsentEntity e WHERE e.muiCanalePromozione = :canale AND e.statoPromozioneEntity = :stato"),
	@NamedQuery(name = "MuiCfgStatiCanaleConsent.findAllByIdCanale",
			query = "SELECT m FROM CfgStatiCanaleConsentEntity m WHERE m.muiCanalePromozione = :muiCanalePromozione"),
	@NamedQuery(name = "MuiCfgStatiCanaleConsent.findAzioniConsentByCanale",
			query = "SELECT a FROM CfgStatiCanaleConsentEntity m INNER JOIN m.azioni a WHERE m.muiCanalePromozione = :canale"),
	@NamedQuery(name = "MuiCfgStatiCanaleConsent.findAzioniConsentByCanaleAndStato",
			query = "SELECT a FROM CfgStatiCanaleConsentEntity m INNER JOIN m.azioni a WHERE m.muiCanalePromozione = :canale and m.statoPromozioneEntity = :stato"),
	@NamedQuery(name = "MuiCfgStatiCanaleConsent.findCodiciAzioniConsentiteByCanaleAndStato",
			query = "SELECT a.codice FROM CfgStatiCanaleConsentEntity m INNER JOIN m.azioni a WHERE m.muiCanalePromozione = :canale and m.statoPromozioneEntity = :stato")
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CfgStatiCanaleConsentEntity implements Serializable, AuditLogInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_STATI_CANALE_CONSENT_ID_GENERATOR", sequenceName = "MUI_CFG_STATI_CANL_CONS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_STATI_CANALE_CONSENT_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	@EqualsAndHashCode.Include
	private Long id;

	// bi-directional many-to-one association to MuiCanalePromozione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CANALE", nullable = false)
	private CanalePromozioneEntity muiCanalePromozione;

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

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "MUI_CFG_AZIONI_CONSENT", schema = Metadata.SCHEMA,
	joinColumns = { @JoinColumn(name = "ID_STATO_CANALE") },
	inverseJoinColumns = { @JoinColumn(name = "ID_AZIONE") })
	Set<CfgAzioniEntity> azioni;


	public void addAzione(CfgAzioniEntity azione) {
		if ( getAzioni() == null) {
			setAzioni(new HashSet<>());
		}
		azione.getCanali().add(this);
		azioni.add(azione);

	}

	public CfgAzioniEntity removeAzione(CfgAzioniEntity azione) {
		if ( getAzioni() != null ) {
			if ( getAzioni().remove(azione) ) {
				azione.getCanali().remove(this);
				return azione;
			}
		}
		return null;
	}
}