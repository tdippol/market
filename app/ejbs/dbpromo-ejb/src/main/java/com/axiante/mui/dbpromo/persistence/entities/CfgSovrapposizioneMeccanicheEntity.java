package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
		@NamedQuery(name="CfgSovrapposizioneMeccanicheEntity.findByCanaleMeccanica",
				query = "SELECT e FROM CfgSovrapposizioneMeccanicheEntity e WHERE e.meccanicaCanaleAbilitata = :meccanicaCanaleAbilitata")
})
@Entity
@Table(name = "MUI_CFG_SOVR_MECCANICHE", schema = Metadata.SCHEMA)
public class CfgSovrapposizioneMeccanicheEntity {

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_SOVR_MECCANICHE_ID_GENERATOR", sequenceName = "MUI_CFG_SOVR_MECCANICHE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_SOVR_MECCANICHE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ABILITA_MECC_CANALE", nullable = false)
	private CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MECCANICA_SOVR", nullable = false)
	private MeccanicheEntity meccanicaSovrapposta;
	
	@Column(name="COMPATIBILITA")
	@Convert(converter = BooleanConverter.class)
	Boolean compatibilita;
}
