package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.beans.Transient;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_GRUPPO_PROMOZIONE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_GRUPPO_PROMOZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "GruppoPromozioneEntity.findAll", query = "SELECT m FROM GruppoPromozioneEntity m"),
		@NamedQuery(name = "GruppoPromozioneEntity.findById", query = "SELECT m FROM GruppoPromozioneEntity m WHERE m.id = :id"),
		@NamedQuery(name = "GruppoPromozioneEntity.findAllByCodiciCanale",
				query = "SELECT DISTINCT g FROM GruppoPromozioneEntity g LEFT JOIN g.muiCanalePromoziones c WHERE c.codiceCanale IN :codiciCanale")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class GruppoPromozioneEntity implements Serializable, ComboBoxCapable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_GRUPPO_PROMOZIONE_ID_GENERATOR", sequenceName = "MUI_GRUPPO_PROMOZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_GRUPPO_PROMOZIONE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_GRUPPO", nullable = false, length = 4)
	private String codiceGruppo;

	@Column(length = 30)
	private String descrizione;

	// bi-directional many-to-one association to MuiCanalePromozione
	@OneToMany(mappedBy = "gruppoPromozioneEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CanalePromozioneEntity> muiCanalePromoziones;

	// bi-directional many-to-one association to CreaPromozioneEntity
	@OneToMany(mappedBy = "gruppoPromozioneEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CreaPromozioneEntity> creaPromozioneEntities;

	public CanalePromozioneEntity addMuiCanalePromozione(CanalePromozioneEntity muiCanalePromozione) {
		if ( getMuiCanalePromoziones() == null )
			setMuiCanalePromoziones(new HashSet<CanalePromozioneEntity>());
		getMuiCanalePromoziones().add(muiCanalePromozione);
		muiCanalePromozione.setGruppoPromozioneEntity(this);

		return muiCanalePromozione;
	}

	public CanalePromozioneEntity removeMuiCanalePromozione(CanalePromozioneEntity muiCanalePromozione) {
		if ( getMuiCanalePromoziones() != null ) {
			getMuiCanalePromoziones().remove(muiCanalePromozione);
		}
		muiCanalePromozione.setGruppoPromozioneEntity(null);
		

		return muiCanalePromozione;
	}

	@Override
	@Transient
	public String getKey() {
		return "" + getId();
	}

	@Override
	@Transient
	public String getLabel() {
		return getDescrizione();
	}

}