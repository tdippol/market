package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_ITEM database table.
 */
@Entity
@Getter
@Setter
@NamedQueries({
		@NamedQuery(name = "ClusterClienteEntity.findByDataInizioAndDataFine",
				query = "SELECT e FROM ClusterClienteEntity e WHERE (e.dataInizio IS NULL OR e.dataInizio <= :dataInizio) " +
						"AND (e.dataFine IS NULL OR :dataFine <= e.dataFine ) "),
		@NamedQuery(name = "ClusterClienteEntity.findAll", query = "SELECT e FROM ClusterClienteEntity e"),
		@NamedQuery(name = "ClusterClienteEntity.findAllByIdCluster", query = "SELECT e FROM ClusterClienteEntity e where e.idCluster = :idCluster"),
})
@Table(name = "V_MUI_CLUSTER_CLIENTE", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClusterClienteEntity implements Serializable, ComboBoxCapable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CLUSTER", unique = true, nullable = false, length = 50)
	@EqualsAndHashCode.Include
	private String idCluster;

	@Column(name = "DESCRIZIONE", length = 255)
	private String descrizione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataAggiornamento;

	@Override
	public Long getId() {
		return null;
	}

	@Override
	public String getKey() {
		return getIdCluster();
	}

	@Override
	public String getLabel() {
		return getDescrizione();
	}


}