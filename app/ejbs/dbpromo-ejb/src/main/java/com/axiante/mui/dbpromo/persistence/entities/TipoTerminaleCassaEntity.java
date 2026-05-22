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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_TIPO_TERMINALE_CASSA", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class TipoTerminaleCassaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_TIPO_TERMINALE_CASSA_ID_GENERATOR", sequenceName = "MUI_TIPO_TERM_CASSA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_TIPO_TERMINALE_CASSA_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(name = "TIPO", length = 1)
	String tipo;

	@Column(name = "DESCRIZIONE", length = 30)
	String descrizione;

	@Column(name = "TIPO_TERMINALE", precision = 3, nullable = false)
	Integer tipoTerminale;

	@OneToMany(mappedBy = "tipoTerminaleCassaEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	Set<PromozioneTipoTerminaleEntity> promozioneTipoTerminale = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TipoTerminaleCassaEntity)) {
			return false;
		}
		return getId() != null && getId().equals(((TipoTerminaleCassaEntity) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
