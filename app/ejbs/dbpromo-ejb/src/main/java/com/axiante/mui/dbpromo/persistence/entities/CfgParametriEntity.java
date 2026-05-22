package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * The persistent class for the MUI_CFG_PARAMETRI database table.
 *
 */
@Entity

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "MUI_CFG_PARAMETRI", schema = Metadata.SCHEMA)
@NamedQuery(name = "MuiCfgParametri.findAll", query = "SELECT m FROM CfgParametriEntity m")
@NoArgsConstructor
@Getter
@Setter

public class CfgParametriEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_PARAMETRI_ID_GENERATOR", sequenceName = "MUI_CFG_PARAMETRI_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_PARAMETRI_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	@Getter

	private Long id;

	@Column(name = "CODICE_PARAMETRO", nullable = false, length = 50)
	@Getter

	private String codiceParametro;

	@Column(length = 255)
	@Getter

	private String descrizione;

	@Column(length = 255)
	@Getter

	private String valore;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INSERIMENTO")
	@Getter

	Date dataInserimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGGIORNAMENTO")
	@Getter

	Date dataAggiornamento;

	@Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
	@Getter

	String codiceUtenteInserimento;

	@Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 50)
	@Getter

	String codiceUtenteAggiornamento;

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