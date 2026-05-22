package com.axiante.mui.persistence.entity;

import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "V_GRM", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(value = {
		@NamedQuery(name="GrmEntity.findAll", query = "Select g from GrmEntity g"),
		@NamedQuery(name="GrmEntity.findAllByGroup", query = "Select g from GrmEntity g where :gruppo MEMBER OF g.gruppi")
})
public class GrmEntity implements Serializable{
	private static final long serialVersionUID = -818441908760970554L;

	@Id
	@Column(name = "ID")
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name="CODICE_GRM", length = 3)
	String codice;

	@Column(name="DESCRIZIONE", length = 30)
	String descrizione;

	@ManyToMany(mappedBy = "grm")
	Set<GroupEntity> gruppi;

}
