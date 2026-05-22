package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_AZIONI", schema = Metadata.SCHEMA)
@NamedQueries({
	@NamedQuery(name = "CfgAzioniEntity.findAll", query = "SELECT a FROM CfgAzioniEntity a"),
	@NamedQuery(name = "CfgAzioniEntity.findByCodice", query = "SELECT a FROM CfgAzioniEntity a where a.codice = :codice"),
	@NamedQuery(name = "CfgAzioniEntity.countByCodice", query = "SELECT count(a) FROM CfgAzioniEntity a where a.codice = :codice")
})
public class CfgAzioniEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 6313058897714813416L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_AZIONI_ID_GENERATOR", sequenceName = "MUI_CFG_AZIONI_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_AZIONI_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(name="CODICE", length = 50)
	String codice;

	@Column(name="DESCRIZIONE", length = 255)
	String descrizione;

	@ManyToMany(mappedBy = "azioni")
	private Set<CfgStatiCanaleConsentEntity> canali;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CfgAzioniEntity)) {
			return false;
		}
		return (id != null) && id.equals(((CfgAzioniEntity) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
