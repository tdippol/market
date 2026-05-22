package com.axiante.mui.persistence.entity;

import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "V_REPARTO", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(value = {
		@NamedQuery(name = "RepartoEntity.findAll" , query = "SELECT r FROM RepartoEntity r")
})
public class RepartoEntity {


	@Id
	@Column(unique = true, nullable = false, precision = 16)
	@Include
	private Integer id;

	@Column(name = "CODICE_REPARTO", nullable = false, length = 2)
	private String codiceReparto;

	@Column(length = 30)
	private String descrizione;

	@Column(name = "CODICE_REPARTO_PADRE", length = 2)
	private String codiceRepartoPadre;

	//	 bi-directional many-to-many association to Canali... comanda il Canale !!!
	//	@ManyToMany(mappedBy = "reparti")
	//	private Set<GroupEntity> gruppi;

	@Transient
	public String getLabel() {
		return String.format("%s - %s", getCodiceReparto(), getDescrizione());
	}


}
