package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_PROCESS_LOG database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_PROCESS_LOG", schema = Metadata.SCHEMA)
@NamedQuery(name = "ProcessLogEntity.findAll", query = "SELECT m FROM ProcessLogEntity m")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class ProcessLogEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROCESS_LOG_ID_GENERATOR", sequenceName = "MUI_PROCESS_LOG_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROCESS_LOG_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "DATA_FINE")
	private Timestamp dataFine;

	@Column(name = "DATA_INIZIO", nullable = false)
	private Timestamp dataInizio;

	@Column(name = "DESCRIZIONE_ERRORE", length = 4000)
	private String descrizioneErrore;

	@Column(length = 3)
	private String esito;

	@Column(nullable = false, length = 255)
	private String procedura;

	@Column(length = 20)
	private String severity;

}