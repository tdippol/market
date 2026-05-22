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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="V_COMPRATORE", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(value = {
		@NamedQuery(name = "CompratoreEntity.findAll", query = "SELECT c FROM CompratoreEntity c"),
		@NamedQuery(name = "CompratoreEntity.findAllByGroup", query = "SELECT c FROM CompratoreEntity c WHERE :gruppo MEMBER OF c.gruppi")
})
public class CompratoreEntity {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "ID")
	Integer id;

	@Column(length = 20)
	private String descrizione;

	@Column(length = 3, name = "CODICE_COMPRATORE")
	String codiceCompratore;

	@Column(name = "FLAG_ATTIVO", nullable = false, precision = 1)
	private Long flagAttivo;

	@ManyToMany(mappedBy = "compratori")
	Set<GroupEntity> gruppi;

	@OneToMany(mappedBy = "compratore")
	Set<ArticoloEntity> articoli;
}
