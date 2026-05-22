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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_SUB_GRM database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_SUB_GRM", schema = Metadata.SCHEMA)
@NamedQuery(name = "SubGrmEntity.findAll", query = "SELECT m FROM SubGrmEntity m")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class SubGrmEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_SUB_GRM_ID_GENERATOR", sequenceName = "MUI_SUB_GRM_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_SUB_GRM_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_SUBGRM", nullable = false, length = 3)
	private String codiceSubgrm;

	@Column(length = 30)
	private String descrizione;

	// bi-directional many-to-one association to ItemEntity
	@OneToMany(mappedBy = "subGrmEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			fetch = FetchType.LAZY)
	private Set<ItemEntity> itemEntities;

	// bi-directional many-to-one association to GrmEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_GRM", nullable = false)
	private GrmEntity grmEntity;

	public ItemEntity addMuiItem(ItemEntity itemEntity) {
		getItemEntities().add(itemEntity);
		itemEntity.setSubGrmEntity(this);

		return itemEntity;
	}

	public ItemEntity removeMuiItem(ItemEntity itemEntity) {
		getItemEntities().remove(itemEntity);
		itemEntity.setSubGrmEntity(null);

		return itemEntity;
	}

}