package com.axiante.mui.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axiante.mui.persistence.Metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "CONTEXTS", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
	@NamedQuery(name = "MuiContext.findAll", query = "SELECT c FROM MuiContext c ORDER BY c.code"),
	@NamedQuery(name = "MuiContext.findById", query = "Select c from MuiContext c where c.id = :id"),
	@NamedQuery(name = "MuiContext.findByCode", query = "Select c from MuiContext c where c.code = :code"),
	@NamedQuery(name = "MuiContext.countByCode", query = "Select count(c) from MuiContext c where c.code = :code"),
	@NamedQuery(name = "MuiContext.findGroupsByContextCode",
			query = "SELECT DISTINCT c.groups FROM MuiContext cxt JOIN FETCH cxt.canali c where cxt.code = :code")
})
public class MuiContext {


	@Id
	@SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "CONTEXT_SEQ", allocationSize = 1, name = "contextSeq")
	@GeneratedValue(generator = "contextSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "DESCRIPTION ", nullable = false)
	private String description;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "CONTEXT_CANALE", schema = Metadata.SCHEMA,
	joinColumns = { @JoinColumn(name = "ID_CONTEXT") },
	inverseJoinColumns = { @JoinColumn(name = "ID_CANALE") })
	Set<CanaleEntity> canali;

	public void addCanale(@NonNull CanaleEntity canale) {
		if ( getCanali() == null ) {
			setCanali(new HashSet<>());
		}
		canale.addContext(this);
		getCanali().add(canale);
	}

	public CanaleEntity removeCanale(@NonNull CanaleEntity canale) {
		if ( getCanali() != null ) {
			canale.removeContext(this);
			if ( getCanali().remove(canale) ) {
				return canale;
			}
			return null;
		}
		return null;
	}
}
