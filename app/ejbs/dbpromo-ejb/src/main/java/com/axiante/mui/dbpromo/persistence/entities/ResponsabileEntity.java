package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_RESPONSABILE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_RESPONSABILE", schema = Metadata.SCHEMA)
@NamedQuery(name = "ResponsabileEntity.findAll", query = "SELECT m FROM ResponsabileEntity m")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class ResponsabileEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_RESPONSABILE_ID_GENERATOR", sequenceName = "MUI_RESPONSABILE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_RESPONSABILE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_RESPONSABILE", nullable = false, length = 4)
	private String codiceResponsabile;

	@Column(length = 100)
	private String descrizione;

	@Column(name = "FLAG_ATTIVO", nullable = false, precision = 1)
	private Long flagAttivo;

	// bi-directional many-to-one association to CompratoreEntity
	@OneToMany(mappedBy = "responsabileEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CompratoreEntity> compratoreEntities;

	public CompratoreEntity addMuiCompratore(CompratoreEntity compratoreEntity) {
		getCompratoreEntities().add(compratoreEntity);
		compratoreEntity.setResponsabileEntity(this);

		return compratoreEntity;
	}

	public CompratoreEntity removeMuiCompratore(CompratoreEntity compratoreEntity) {
		getCompratoreEntities().remove(compratoreEntity);
		compratoreEntity.setResponsabileEntity(null);

		return compratoreEntity;
	}

}