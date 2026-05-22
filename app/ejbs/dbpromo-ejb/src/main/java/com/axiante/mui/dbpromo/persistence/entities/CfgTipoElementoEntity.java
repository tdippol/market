package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_TIPO_ELEMENTO", schema = Metadata.SCHEMA)
@NamedQuery(name = "CfgTipoElementoEntity.findByMeccanicaIdAndSetPianificazioneId",
		query = "SELECT e FROM CfgTipoElementoEntity e INNER JOIN FETCH CfgConfHeaderEntity h ON e.confHeader=h WHERE h.meccanicaEntity.id = :meccanicaId AND h.muiCfgSetPianificazione.id = :setPianificazioneId")
@NoArgsConstructor
public class CfgTipoElementoEntity {
	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_TIPO_ELEMENTO_ID_GENERATOR", sequenceName = "MUI_CFG_TIPO_ELEMENTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_TIPO_ELEMENTO_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	// bi-directional many-to-one association to NegozioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CFG_CONG_HEADER", nullable = false)
	private CfgConfHeaderEntity confHeader;

	@Column(name = "RAGGRUPPAMENTO")
	Integer raggruppamento;

	@Column(name = "TOTALE")
	Integer totale;

	@Column(name = "REPARTO")
	Integer reparto;

	@Column(name = "GRM")
	Integer grm;

	@Column(name = "ARTICOLO")
	Integer articolo;

	@Column(name = "ATTRIBUTO")
	Integer attributo;

	@Column(name = "OMOGENEO")
	Integer omogeneo;
}
