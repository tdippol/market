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
 * The persistent class for the MUI_TIPO_NEGOZIO database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_TIPO_NEGOZIO", schema = Metadata.SCHEMA)
@NamedQuery(name = "TipoNegozioEntity.findAll", query = "SELECT m FROM TipoNegozioEntity m")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class TipoNegozioEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_TIPO_NEGOZIO_ID_GENERATOR", sequenceName = "MUI_TIPO_NEGOZIO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_TIPO_NEGOZIO_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_TIPO_NEGOZIO", nullable = false, length = 50)
	private String codiceTipoNegozio;

	@Column(length = 255)
	private String descrizione;

	// bi-directional many-to-one association to NegozioEntity
	@OneToMany(mappedBy = "tipoNegozioEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<NegozioEntity> negozioEntities;

	public NegozioEntity addMuiNegozio(NegozioEntity negozioEntity) {
		getNegozioEntities().add(negozioEntity);
		negozioEntity.setTipoNegozioEntity(this);

		return negozioEntity;
	}

	public NegozioEntity removeMuiNegozio(NegozioEntity negozioEntity) {
		getNegozioEntities().remove(negozioEntity);
		negozioEntity.setTipoNegozioEntity(null);

		return negozioEntity;
	}

}