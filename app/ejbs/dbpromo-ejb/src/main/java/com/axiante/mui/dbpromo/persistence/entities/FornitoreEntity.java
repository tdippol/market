package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * The persistent class for the MUI_FORNITORE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_FORNITORE", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "FornitoreEntity.findAll", query = "SELECT m FROM FornitoreEntity m"),
		@NamedQuery(name = "FornitoreEntity.findAllByCodiciCompratore",
				query = "SELECT DISTINCT e FROM FornitoreEntity e JOIN e.muiAssortimentoFornitores af WHERE af.itemEntity.compratoreEntity.codiceCompratore in :codiciCompratore"),
		@NamedQuery(name = "FornitoreEntity.findAllFornitoriAttiviByCodiceCompratore",
				query = "SELECT DISTINCT e FROM FornitoreEntity e JOIN e.muiAssortimentoFornitores af WHERE af.itemEntity.compratoreEntity.codiceCompratore = :codiceCompratore AND af.dataEliminazione IS NULL")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class FornitoreEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_FORNITORE_ID_GENERATOR", sequenceName = "MUI_FORNITORE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_FORNITORE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_FORNITORE", nullable = false, length = 6)
	private String codiceFornitore;

	@Column(length = 30)
	private String descrizione;

	// bi-directional many-to-one association to MuiAssortimentoFornitore
	@OneToMany(mappedBy = "fornitoreEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<AssortimentoFornitoreEntity> muiAssortimentoFornitores;

	@OneToMany(mappedBy = "fornitore", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<ContributiPromoEntity> contributi;

	public AssortimentoFornitoreEntity addMuiAssortimentoFornitore(
			AssortimentoFornitoreEntity muiAssortimentoFornitore) {
		getMuiAssortimentoFornitores().add(muiAssortimentoFornitore);
		muiAssortimentoFornitore.setFornitoreEntity(this);

		return muiAssortimentoFornitore;
	}

	public AssortimentoFornitoreEntity removeMuiAssortimentoFornitore(
			AssortimentoFornitoreEntity muiAssortimentoFornitore) {
		getMuiAssortimentoFornitores().remove(muiAssortimentoFornitore);
		muiAssortimentoFornitore.setFornitoreEntity(null);

		return muiAssortimentoFornitore;
	}

	public void addContributo(@NonNull ContributiPromoEntity contributo){
		if( getContributi() == null ){
			setContributi(new HashSet<>());
		}
		getContributi().add(contributo);
		contributo.setFornitore(this);
	}

	public void removeCompratore(@NonNull ContributiPromoEntity contributo){
		if ( getContributi() != null && getContributi().remove(contributo)){
			contributo.setFornitore(null);
		}
	}

	@Transient
	public String getFullDescription() {
		return String.format("[%s] %s", codiceFornitore, descrizione);
	}
}