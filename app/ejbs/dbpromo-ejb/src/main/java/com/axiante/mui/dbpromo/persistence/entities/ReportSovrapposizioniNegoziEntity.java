package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_ITEM database table.
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_RPT_SOVRAPP_NEGOZI", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = "ReportSovrapposizioniNegoziEntity.findByIdSovrapposizione", query = "Select n from ReportSovrapposizioniNegoziEntity n where n.report.id = :id")
})
public class ReportSovrapposizioniNegoziEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SOVRAPPOSIZIONE", nullable = false)
	private ReportSovrapposizioniEntity report;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NEGOZIO")
	private NegozioEntity negozio;
	@Column(name = "ID_TIPO_SOVR", precision = 16)
	private Integer tipoSovrapposizione;
	@Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
	private String codiceUtenteInserimento;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataCalcoloSovrapposizione;
}