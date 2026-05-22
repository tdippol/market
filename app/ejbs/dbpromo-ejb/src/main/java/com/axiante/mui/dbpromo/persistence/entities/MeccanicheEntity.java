package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * The persistent class for the MUI_MECCANICHE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_MECCANICA", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "MeccanicheEntity.findAll", query = "SELECT m FROM MeccanicheEntity m"),
		@NamedQuery(name = "MeccanicheEntity.countByCodice",
				query = "SELECT COUNT(m) FROM MeccanicheEntity m WHERE m.codiceMeccanica = :codiceMeccanica"),
		@NamedQuery(name = "MeccanicheEntity.findByCodice",
				query = "SELECT m FROM MeccanicheEntity m WHERE m.codiceMeccanica = :codiceMeccanica")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class MeccanicheEntity implements Serializable, ComboBoxCapable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_MECCANICHE_ID_GENERATOR", sequenceName = "MUI_MECCANICHE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_MECCANICHE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "CODICE_MECCANICA", nullable = false, length = 20)
	private String codiceMeccanica;

	@Column(nullable = false, length = 80)
	private String descrizione;

	// bi-directional many-to-one association to MuiCfgAbilitaMeccCanale
	@OneToMany(mappedBy = "meccanicheEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<CfgAbilitaMeccCanaleEntity> muiCfgAbilitaMeccCanales;

	// bi-directional many-to-one association to PromozioneMeccanicheEntity
	@OneToMany(mappedBy = "meccanicheEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<PromozioneMeccanicheEntity> promozioneMeccanicheEntities;

	@OneToMany(mappedBy = "meccanicaEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<PromozionePianificazioneEntity> promozionePianificazioneEntities;

	// bi-directional many-to-one association to MuiCfgPianificazione
	@OneToMany(mappedBy = "meccanicaEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<CfgConfHeaderEntity> muiCfgConfHeaders;

	public CfgConfHeaderEntity addMuiCfgConfHeaderEntity(CfgConfHeaderEntity muiCfgConfHeader) {
		getMuiCfgConfHeaders().add(muiCfgConfHeader);
		muiCfgConfHeader.setMeccanicaEntity(this);

		return muiCfgConfHeader;
	}

	public CfgConfHeaderEntity removeMuiCfgConfHeaderEntity(CfgConfHeaderEntity muiCfgConfHeader) {
		getMuiCfgConfHeaders().remove(muiCfgConfHeader);
		muiCfgConfHeader.setMeccanicaEntity(null);

		return muiCfgConfHeader;
	}

	public CfgAbilitaMeccCanaleEntity addMuiCfgAbilitaMeccCanale(CfgAbilitaMeccCanaleEntity muiCfgAbilitaMeccCanale) {
		getMuiCfgAbilitaMeccCanales().add(muiCfgAbilitaMeccCanale);
		muiCfgAbilitaMeccCanale.setMeccanicheEntity(this);

		return muiCfgAbilitaMeccCanale;
	}

	public CfgAbilitaMeccCanaleEntity removeMuiCfgAbilitaMeccCanale(
			CfgAbilitaMeccCanaleEntity muiCfgAbilitaMeccCanale) {
		getMuiCfgAbilitaMeccCanales().remove(muiCfgAbilitaMeccCanale);
		muiCfgAbilitaMeccCanale.setMeccanicheEntity(null);

		return muiCfgAbilitaMeccCanale;
	}

	public PromozioneMeccanicheEntity addMuiPromozioneMeccanich(PromozioneMeccanicheEntity muiPromozioneMeccanich) {
		getPromozioneMeccanicheEntities().add(muiPromozioneMeccanich);
		muiPromozioneMeccanich.setMeccanicheEntity(this);

		return muiPromozioneMeccanich;
	}

	public PromozioneMeccanicheEntity removeMuiPromozioneMeccanich(PromozioneMeccanicheEntity muiPromozioneMeccanich) {
		getPromozioneMeccanicheEntities().remove(muiPromozioneMeccanich);
		muiPromozioneMeccanich.setMeccanicheEntity(null);

		return muiPromozioneMeccanich;
	}

	public PromozionePianificazioneEntity addMuiPromozionePianificazione(
			PromozionePianificazioneEntity promozionePianificazioneEntity) {
		getPromozionePianificazioneEntities().add(promozionePianificazioneEntity);
		promozionePianificazioneEntity.setMeccanicaEntity(this);

		return promozionePianificazioneEntity;
	}

	public PromozionePianificazioneEntity removeMuiPromozionePianificazione(
			PromozionePianificazioneEntity promozionePianificazioneEntity) {
		getPromozionePianificazioneEntities().remove(promozionePianificazioneEntity);
		promozionePianificazioneEntity.setMeccanicaEntity(null);

		return promozionePianificazioneEntity;
	}

	@Override
	public boolean equals(Object o) {
		MeccanicheEntity other = null;
		if (o instanceof MeccanicheEntity) {
			other = (MeccanicheEntity) o;
		} else {
			return false;
		}
		// if (o == null)
		// return false;
		boolean result = false;
		if (this.getCodiceMeccanica() != null) {
			result = other.getCodiceMeccanica() != null
					&& getCodiceMeccanica().contentEquals(other.getCodiceMeccanica());
		} else {
			result = other.getCodiceMeccanica() == null;
		}

		result &= Objects.equals(getId(), other.getId());
		return result;
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	@Transient
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	@Transient
	public String getLabel() {
		return String.format("%s - %s", codiceMeccanica, descrizione);
	}
}