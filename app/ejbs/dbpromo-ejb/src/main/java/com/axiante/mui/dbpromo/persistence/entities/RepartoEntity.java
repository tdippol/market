package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
 * The persistent class for the MUI_REPARTO database table.
 */
@Entity
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name = "RepartoEntity.findAll", query = "SELECT r FROM RepartoEntity r ORDER BY r.codiceReparto ASC"),
	@NamedQuery(name = "RepartoEntity.findByCode", query = "SELECT r FROM RepartoEntity r WHERE r.codiceReparto = :codiceReparto"),
	@NamedQuery(name = "RepartoEntity.findAllByCodiciCompratore",
			query = "SELECT DISTINCT r FROM RepartoEntity r JOIN FETCH r.categorie c JOIN FETCH c.grmEntities g JOIN FETCH g.subGrmEntities s JOIN FETCH s.itemEntities i WHERE i.compratoreEntity.codiceCompratore IN :codiciCompratore")
})
@Table(name = "MUI_REPARTO", schema = Metadata.SCHEMA)
@NoArgsConstructor
public class RepartoEntity implements Serializable, DbPromoEntityInterface, ComboBoxCapable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_REPARTO_ID_GENERATOR", sequenceName = "MUI_REPARTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_REPARTO_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_REPARTO", nullable = false, length = 2)
	private String codiceReparto;

	@Column(length = 30)
	private String descrizione;

	@Column(name = "CODICE_REPARTO_PADRE", length = 2)
	private String codiceRepartoPadre;

	@Transient
	@Override
	public String getKey() {
		return getCodiceReparto();
	}

	@ManyToMany(mappedBy = "reparti")
	private Set<PromozioneTestataEntity> promozioni = new HashSet<>();

	@OneToMany(mappedBy = "repartoEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			fetch = FetchType.LAZY)
	private Set<CategoriaEntity> categorie;

	@Transient
	@Override
	public String getLabel() {
		return String.format("%s - %s", getCodiceReparto(), getDescrizione());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RepartoEntity)) {
			return false;
		}
		return (id != null) && id.equals(((RepartoEntity) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public CategoriaEntity addCategoria(CategoriaEntity categoria) {
		if (categorie == null) {
			categorie = new HashSet<>();
		}
		categorie.add(categoria);
		return categoria;
	}

	public CategoriaEntity removeCategoria(CategoriaEntity categoria) {
		if (categorie != null) {
			categorie.remove(categoria);
		}
		categoria.setRepartoEntity(null);
		return categoria;
	}
}