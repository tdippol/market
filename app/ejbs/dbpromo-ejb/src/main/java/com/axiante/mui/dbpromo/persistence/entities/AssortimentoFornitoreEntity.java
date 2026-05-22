package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "MUI_ASSORTIMENTO_FORNITORE", schema = Metadata.SCHEMA)
@NamedQuery(name = "MuiAssortimentoFornitore.findAll", query = "SELECT m FROM AssortimentoFornitoreEntity m")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class AssortimentoFornitoreEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_ASSORTIMENTO_FORNITORE_ID_GENERATOR", sequenceName = "MUI_ASSORTIM_FORNITORE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_ASSORTIMENTO_FORNITORE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ELIMINAZIONE")
	private Date dataEliminazione;

	@Column(name = "FORNITORE_PRINCIPALE", nullable = false, precision = 1)
	private Long fornitorePrincipale;

	// bi-directional many-to-one association to FornitoreEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FORNITORE", nullable = false)
	private FornitoreEntity fornitoreEntity;

	// bi-directional many-to-one association to ItemEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ITEM", nullable = false)
	private ItemEntity itemEntity;

}