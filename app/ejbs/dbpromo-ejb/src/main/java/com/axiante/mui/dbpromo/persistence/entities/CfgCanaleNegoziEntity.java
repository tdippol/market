package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

/**
 * The persistent class for the MUI_CFG_CANALE_NEGOZI database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_CANALE_NEGOZI", schema = Metadata.SCHEMA)
@NamedQueries({ @NamedQuery(name = "MuiCfgCanaleNegozi.findAll", query = "SELECT m FROM CfgCanaleNegoziEntity m"),
		@NamedQuery(name = "MuiCfgCanaleNegozi.findAllByIdCanale", query = "SELECT m FROM CfgCanaleNegoziEntity m WHERE m.muiCanalePromozione = :muiCanalePromozione") })
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class CfgCanaleNegoziEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_CANALE_NEGOZI_ID_GENERATOR", sequenceName = "MUI_CFG_CANALE_NEGOZI_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_CANALE_NEGOZI_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	@Column(name = "DEFAULT_FLAG", length = 1)
	private String defaultFlag;

	// bi-directional many-to-one association to MuiCanalePromozione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CANALE", nullable = false)
	private CanalePromozioneEntity muiCanalePromozione;

	// bi-directional many-to-one association to NegozioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NEGOZIO", nullable = false)
	private NegozioEntity negozioEntity;
}