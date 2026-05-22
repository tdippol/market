package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_GRM database table.
 */
@Entity
@Getter
@Setter
@NamedQueries({
		@NamedQuery(name = "GrmEntity.findAll", query = "SELECT g FROM GrmEntity g ORDER BY g.muiCategoria.repartoEntity.codiceReparto, g.muiCategoria.codiceCategoria, g.codiceGrm"),
		@NamedQuery(name = "GrmEntity.findById", query = "SELECT g FROM GrmEntity g WHERE g.id = :id"),
		@NamedQuery(name = "GrmEntity.findByCode", query = "SELECT g FROM GrmEntity g WHERE g.codiceGrm = :codiceGrm"),
		@NamedQuery(name = "GrmEntity.findAllByCodiciCompratore",
				query = "SELECT DISTINCT g FROM GrmEntity g JOIN FETCH g.subGrmEntities s JOIN FETCH s.itemEntities i WHERE i.compratoreEntity.codiceCompratore IN :codiciCompratore")
})
@Table(name = "MUI_GRM", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class GrmEntity implements Serializable, DbPromoEntityInterface, ComboBoxCapable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_GRM_ID_GENERATOR", sequenceName = "MUI_GRM_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_GRM_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_GRM", nullable = false, length = 3)
	private String codiceGrm;

	@Column(length = 30)
	private String descrizione;

	// bi-directional many-to-one association to MuiCategoria
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CATEGORIA", nullable = false)
	private CategoriaEntity muiCategoria;

	// bi-directional many-to-one association to SubGrmEntity
	@OneToMany(mappedBy = "grmEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			fetch = FetchType.LAZY)
	private Set<SubGrmEntity> subGrmEntities;

	public SubGrmEntity addSubGrmEntity(SubGrmEntity subGrmEntity) {
		getSubGrmEntities().add(subGrmEntity);
		subGrmEntity.setGrmEntity(this);

		return subGrmEntity;
	}

	public SubGrmEntity removeSubGrmEntity(SubGrmEntity subGrmEntity) {
		getSubGrmEntities().remove(subGrmEntity);
		subGrmEntity.setGrmEntity(null);

		return subGrmEntity;
	}

	@Transient
	@Override
	public String getKey() {
		return getCodiceGrm();
	}

	@Transient
	@Override
	public String getLabel() {
		return String.format("%s - %s", getCodiceGrm(), getDescrizione());
	}

}