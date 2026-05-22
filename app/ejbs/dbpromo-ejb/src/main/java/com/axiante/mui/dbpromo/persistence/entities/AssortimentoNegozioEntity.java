package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_ASSORTIMENTO_FORNITORE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_ASSORTIMENTO_NEGOZIO", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class AssortimentoNegozioEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_ASSORTIMENTO_NEGOZIO_ID_GENERATOR", sequenceName = "MUI_ASSORTIM_NEGOZIO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_ASSORTIMENTO_NEGOZIO_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	// bi-directional many-to-one association to FornitoreEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NEGOZIO", nullable = false)
	private NegozioEntity negozioEntity;

	// bi-directional many-to-one association to ItemEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ITEM", nullable = false)
	private ItemEntity itemEntity;

}