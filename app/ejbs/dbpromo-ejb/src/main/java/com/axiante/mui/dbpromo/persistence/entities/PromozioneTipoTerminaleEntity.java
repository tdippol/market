package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "MUI_PROMOZIONE_TIPO_TERMINALE", schema = Metadata.SCHEMA)
@NamedQuery(name = "PromozioneTipoTerminaleEntity.findByIdTestataIdTerminale",
		query = "SELECT t from PromozioneTipoTerminaleEntity t WHERE t.promozioneTestataEntity = :testata AND t.tipoTerminaleCassaEntity = :cassa")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class PromozioneTipoTerminaleEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMOZIONE_TIPO_TERMINALE_ID_GENERATOR", sequenceName = "MUI_PROMOZIONE_TIPO_TERM_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMOZIONE_TIPO_TERMINALE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PROMOZIONE")
	private PromozioneTestataEntity promozioneTestataEntity;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_TERMINALE_CASSA")
	private TipoTerminaleCassaEntity tipoTerminaleCassaEntity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INSERIMENTO", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataInserimento;

	@Column(name = "CODICE_UTENTE_INSERIMENTO")
	private String codiceUtenteInserimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGGIORNAMENTO", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataAggiornamento;

	@Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
	private String codiceUtenteAggiornamento;
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
