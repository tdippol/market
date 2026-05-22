package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.beans.Transient;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_LIV_PIANIFICAZIONE", schema = Metadata.SCHEMA)
@NamedQuery(name = "CfgLivelloPianificazioneEntity.findByDescription",
		query = "SELECT p FROM CfgLivelloPianificazioneEntity p WHERE UPPER(p.descrizione) = :descrizione ORDER BY p.id")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CfgLivelloPianificazioneEntity implements Serializable, DbPromoEntityInterface, ComboBoxCapable {
	private static final long serialVersionUID = 3671941852133819717L;
	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_LIV_PIANIFICAZIONE_ID_GENERATOR", sequenceName = "MUI_CFG_LIV_PIANIFICAZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_LIV_PIANIFICAZIONE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(name = "CODICE", length = 20, nullable = false)
	@EqualsAndHashCode.Include
	String codice;

	@Column(name = "DESCRIZIONE", length = 80)
	@EqualsAndHashCode.Include
	String descrizione;

	// bi-directional many-to-one association to MuiCfgPianificazione
	@OneToMany(mappedBy = "livelloPianificazione", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Set<CfgConfHeaderEntity> muiCfgConfHeaders;

	public CfgConfHeaderEntity addMuiCfgConfHeaderEntity(CfgConfHeaderEntity muiCfgConfHeader) {
		getMuiCfgConfHeaders().add(muiCfgConfHeader);
		muiCfgConfHeader.setLivelloPianificazione(this);

		return muiCfgConfHeader;
	}

	public CfgConfHeaderEntity removeMuiCfgConfHeaderEntity(CfgConfHeaderEntity muiCfgConfHeader) {
		getMuiCfgConfHeaders().remove(muiCfgConfHeader);
		muiCfgConfHeader.setLivelloPianificazione(null);

		return muiCfgConfHeader;
	}

	@Override
	@Transient
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	@Transient
	public String getLabel() {
		return descrizione;
	}
}
