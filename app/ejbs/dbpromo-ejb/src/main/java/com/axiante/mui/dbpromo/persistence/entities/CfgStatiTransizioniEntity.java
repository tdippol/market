package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_CFG_STATI_TRANSIZIONI database table.
 *
 */
@Entity
@Table(name = "MUI_CFG_STATI_TRANSIZIONI", schema = Metadata.SCHEMA)
@NamedQueries({
	@NamedQuery(name = "CfgStatiTransizioniEntity.findAll", query = "SELECT m FROM CfgStatiTransizioniEntity m"),
	@NamedQuery(name = "CfgStatiTransizioniEntity.findAllByIdCanale", query = "SELECT m FROM CfgStatiTransizioniEntity m WHERE m.muiCanalePromozione = :muiCanalePromozione"),
	@NamedQuery(name = "CfgStatiTransizioneEntity.findByCanaleAndStatusFromAndStatusTo",
			query = "SELECT e FROM CfgStatiTransizioniEntity e WHERE e.muiCanalePromozione = :channel AND e.statoPromozione= :fromStatus AND e.statoTransizione = :toStatus"),
	@NamedQuery(name = "CfgStatiTransizioneEntity.findByCanaleAndFromStatusAndFlagAutomatico",
			query = "SELECT e FROM CfgStatiTransizioniEntity e WHERE e.muiCanalePromozione = :channel AND e.statoPromozione= :fromStatus AND e.flagAutomatico = :flagAutomatico")
})
@NoArgsConstructor
@Getter
@Setter
public class CfgStatiTransizioniEntity implements Serializable, AuditLogInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_STATI_TRANSIZIONI_ID_GENERATOR", sequenceName = "MUI_CFG_STATI_TRANSIZ_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_STATI_TRANSIZIONI_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(length = 255)
	private String descrizione;

	// bi-directional many-to-one association to MuiCanalePromozione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CANALE", nullable = false)
	private CanalePromozioneEntity muiCanalePromozione;

	// bi-directional many-to-one association to StatoPromozioneEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATO_TRANSIZIONE", nullable = false)
	private StatoPromozioneEntity statoTransizione;

	// bi-directional many-to-one association to StatoPromozioneEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATO", nullable = false)
	private StatoPromozioneEntity statoPromozione;

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

	@Column(name = "FLAG_COMPRATORE")
	private Integer flagCompratore;


	@Convert(converter = BooleanConverter.class)
	@Column(name="FLAG_PUBBLICA")
	private Boolean flagPubblica;

	@Convert(converter = BooleanConverter.class)
	@Column(name="FLAG_CONTROLLA")
	private Boolean flagControlli;

	// bi-directional many-to-one association to StatoPromozioneEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATO_ERRORE", nullable = false)
	private StatoPromozioneEntity statoErrore;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "FL_AUTOMATICO")
	private Boolean flagAutomatico;

	@Transient
	public String getTipo() {
		return Boolean.TRUE.equals(flagAutomatico) ? "Automatico" : "Manuale";
	}
}