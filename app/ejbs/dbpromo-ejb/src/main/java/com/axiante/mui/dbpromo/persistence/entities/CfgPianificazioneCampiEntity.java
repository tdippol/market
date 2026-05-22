package com.axiante.mui.dbpromo.persistence.entities;

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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_CFG_PIANIFICAZIONE_CAMPI database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_PIANIFICAZIONE_CAMPI", schema = Metadata.SCHEMA)
@NamedQuery(name = "MuiCfgPianificazioneCampi.findAll", query = "SELECT m FROM CfgPianificazioneCampiEntity m")
@NoArgsConstructor
public class CfgPianificazioneCampiEntity implements Serializable, AuditLogInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_PIANIFICAZIONE_CAMPI_ID_GENERATOR", sequenceName = "MUI_CFG_PNFCZ_CAMPI_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_PIANIFICAZIONE_CAMPI_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_CAMPO", nullable = false, length = 50)
	private String codiceCampo;

	@Column(name = "CAMPO", nullable = false, length = 50)
	private String campo;

	@Column(length = 255)
	private String descrizione;

	@Column(name = "DESCRIZIONE_ESTESA", length = 500)
	private String descrizioneEstesa;

	@Column(length = 50)
	private String raggruppamento;

	@Column(length = 50)
	private String tipo;

	// bi-directional many-to-one association to MuiCfgPianificazione
	@OneToMany(mappedBy = "muiCfgPianificazioneCampi", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CfgPianificazioneEntity> muiCfgPianificaziones;

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

	//ALTER TABLE MUI_CFG_PIANIFICAZIONE_CAMPI  ADD ENTITY_LOOKUP VARCHAR(255) DEFAULT NULL;
	@Column(name="ENTITY_LOOKUP")
	private String entityLookup;

	//ALTER TABLE MUI_CFG_PIANIFICAZIONE_CAMPI  ADD ENTITY_ATTRIBUTE VARCHAR(255) DEFAULT NULL;
	@Column(name="ENTITY_ATTRIBUTE")
	private String entityAttribute;
	//ALTER TABLE MUI_CFG_PIANIFICAZIONE_CAMPI ADD COLUMN_WIDTH INTEGER DEFAULT NULL;
	@Column(name="COLUMN_WIDTH")
	private Integer columnWidth;

	public CfgPianificazioneEntity addMuiCfgPianificazione(CfgPianificazioneEntity muiCfgPianificazione) {
		getMuiCfgPianificaziones().add(muiCfgPianificazione);
		muiCfgPianificazione.setMuiCfgPianificazioneCampi(this);

		return muiCfgPianificazione;
	}

	public CfgPianificazioneEntity removeMuiCfgPianificazione(CfgPianificazioneEntity muiCfgPianificazione) {
		getMuiCfgPianificaziones().remove(muiCfgPianificazione);
		muiCfgPianificazione.setMuiCfgPianificazioneCampi(null);

		return muiCfgPianificazione;
	}

}