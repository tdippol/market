package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
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
 * The persistent class for the MUI_CFG_PIANIFICAZ_TIPO_RIGA database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_PIANIFICAZ_TIPO_RIGA", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "MuiCfgPianificazTipoRiga.findAll", query = "SELECT e FROM CfgPianificazioneCampiEntity e"),
		@NamedQuery(name = "MuiCfgPianificazTipoRiga.findByDescription",
				query = "SELECT e FROM CfgPianificazTipoRigaEntity e WHERE e.descrizione = :description"),
		@NamedQuery(name = "MuiCfgPianificazTipoRiga.findByCodiceTipo",
				query = "SELECT e FROM CfgPianificazTipoRigaEntity e where e.codiceTipo = :codiceTipo")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class CfgPianificazTipoRigaEntity implements Serializable, AuditLogInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_PIANIFICAZ_TIPO_RIGA_ID_GENERATOR", sequenceName = "MUI_CFG_PNFCZ_TIPO_RIGA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_PIANIFICAZ_TIPO_RIGA_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_TIPO", nullable = false, length = 50)
	private String codiceTipo;

	@Column(length = 255)
	private String descrizione;

	// bi-directional many-to-one association to MuiCfgPianificazione
	@OneToMany(mappedBy = "muiCfgPianificazTipoRiga", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CfgPianificazioneEntity> muiCfgPianificaziones;


	// bi-directional many-to-one association to MuiCfgPianificazione
	@OneToMany(mappedBy = "tipoRiga", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PromozionePianificazioneEntity> pianificazioni;

	
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
	
	public CfgPianificazioneEntity addMuiCfgPianificazione(CfgPianificazioneEntity muiCfgPianificazione) {
		getMuiCfgPianificaziones().add(muiCfgPianificazione);
		muiCfgPianificazione.setMuiCfgPianificazTipoRiga(this);

		return muiCfgPianificazione;
	}

	public CfgPianificazioneEntity removeMuiCfgPianificazione(CfgPianificazioneEntity muiCfgPianificazione) {
		getMuiCfgPianificaziones().remove(muiCfgPianificazione);
		muiCfgPianificazione.setMuiCfgPianificazTipoRiga(null);

		return muiCfgPianificazione;
	}

	public PromozionePianificazioneEntity addPianificazione(PromozionePianificazioneEntity muiCfgPianificazione) {
		getPianificazioni().add(muiCfgPianificazione);
		muiCfgPianificazione.setTipoRiga(this);

		return muiCfgPianificazione;
	}

	public PromozionePianificazioneEntity removePianificazione(PromozionePianificazioneEntity muiCfgPianificazione) {
		getPianificazioni().remove(muiCfgPianificazione);
		muiCfgPianificazione.setTipoRiga(null);

		return muiCfgPianificazione;
	}
}