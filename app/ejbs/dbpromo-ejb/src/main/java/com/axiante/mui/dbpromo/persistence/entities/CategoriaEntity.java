package com.axiante.mui.dbpromo.persistence.entities;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_CATEGORIA database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_CATEGORIA", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "MuiCategoria.findAll", query = "SELECT m FROM CategoriaEntity m"),
		@NamedQuery(name = "CategoriaEntity.findAllByCodiciCompratore",
				query = "SELECT DISTINCT c FROM CategoriaEntity c JOIN FETCH c.grmEntities g JOIN FETCH g.subGrmEntities s JOIN FETCH s.itemEntities i WHERE i.compratoreEntity.codiceCompratore IN :codiciCompratore")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class CategoriaEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CATEGORIA_ID_GENERATOR", sequenceName = "MUI_CATEGORIA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CATEGORIA_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_CATEGORIA", nullable = false, length = 3)
	private String codiceCategoria;

	@Column(length = 30)
	private String descrizione;

	// bi-directional many-to-one association to RepartoEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REPARTO", nullable = false)
	private RepartoEntity repartoEntity;

	// bi-directional many-to-one association to GrmEntity
	@OneToMany(mappedBy = "muiCategoria", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			fetch = FetchType.LAZY)
	private Set<GrmEntity> grmEntities;

	public GrmEntity addMuiGrm(GrmEntity grmEntity) {
		getGrmEntities().add(grmEntity);
		grmEntity.setMuiCategoria(this);

		return grmEntity;
	}

	public GrmEntity removeMuiGrm(GrmEntity grmEntity) {
		getGrmEntities().remove(grmEntity);
		grmEntity.setMuiCategoria(null);

		return grmEntity;
	}

}