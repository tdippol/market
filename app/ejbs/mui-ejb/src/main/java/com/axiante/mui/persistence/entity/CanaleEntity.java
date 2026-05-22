package com.axiante.mui.persistence.entity;

import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "V_CANALE", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NamedQueries({
		@NamedQuery(name = "CanaleEntity.findAll", query = "SELECT c FROM CanaleEntity c ORDER BY c.codiceCanale")
})
public class CanaleEntity {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CODICE_CANALE", precision = 3, nullable = false)
	Long codiceCanale;

	@Column(name = "DESCRIZIONE", length = 30)
	private String descrizione;

	@Column(name = "SIGLA_CANALE", length = 10)
	private String sigla;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "GRUPPO_CANALE", schema = Metadata.SCHEMA, joinColumns = {
			@JoinColumn(name = "ID_CANALE", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ID_GRUPPO", referencedColumnName = "ID") })
	private Set<GroupEntity> groups;

	@ManyToMany(mappedBy = "canali")
	private Set<MuiContext> contexts ;

	public GroupEntity addGroup(final GroupEntity group) {
		if (getGroups() == null) {
			setGroups(new HashSet<>());
		}
		getGroups().add(group);
		return group;
	}

	public GroupEntity removeGroup(final GroupEntity group) {
		if (getGroups() != null) {
			getGroups().remove(group);
		}
		return group;
	}

	public void addContext(@NonNull MuiContext context) {
		if( getContexts() == null ) {
			setContexts(new HashSet<MuiContext>());
		}
		getContexts().add(context);
	}

	public MuiContext removeContext(@NonNull MuiContext context) {
		if ( getContexts() != null ) {
			if ( getContexts().remove(context) ) {
				return context;
			}
			return null;
		}
		return null;
	}

}
