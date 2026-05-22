package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_NEGOZIO database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_NEGOZIO", schema = Metadata.SCHEMA)
@NamedQueries({
		@NamedQuery(name = "NegozioEntity.findAll", query = "SELECT m FROM NegozioEntity m"),
		@NamedQuery(name = "NegozioEntity.findAllTipoConsegna",
				query = "SELECT DISTINCT(n.tipoConsegna) from NegozioEntity n"),
		@NamedQuery(name = "NegozioEntity.findDistinctZone",
				query = "SELECT DISTINCT new com.axiante.mui.dbpromo.persistence.dto.ZonaDto(n.societa, n.codiceZona, n.descrizioneZona) FROM NegozioEntity n WHERE n.puntoVenditaChiuso = 0 ORDER BY n.societa, n.codiceZona, n.descrizioneZona"),
		@NamedQuery(name = "NegozioEntity.findDistinctCedi",
				query = "SELECT DISTINCT(n.cedi) from NegozioEntity n")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class NegozioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_NEGOZIO_ID_GENERATOR", sequenceName = "MUI_NEGOZIO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_NEGOZIO_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(name = "ACCETTA_ESPOSITORI", precision = 1)
	private Long accettaEspositori = 0L;

	@Column(length = 2)
	private String cedi;

	@Temporal(TemporalType.TIMESTAMP)
	private Date chiusura;

	@Column(name = "CHIUSURA_NON_DEFINITIVA", precision = 1)
	private Long chiusuraNonDefinitiva = 0L;

	@Column(name = "CODICE_APPOG_NEG_WEB")
	private String codiceAppogNegWeb;

	@Column(name = "CODICE_CLUSTER", nullable = false, precision = 1)
	private Long codiceCluster;

	@Column(name = "CODICE_FORMATO", nullable = false, length = 3)
	private String codiceFormato;

	@Column(name = "CODICE_NEGOZIO", nullable = false, length = 3)
	private String codiceNegozio;

	@Column(name = "CODICE_REP_INFOSTORE", length = 3)
	private String codiceRepInfostore;

	@Column(name = "CODICE_ZONA", nullable = false, length = 2)
	private String codiceZona;

	@Column(length = 19)
	private String descrizione;

	@Column(name = "DESCRIZIONE_FORMATO", length = 30)
	private String descrizioneFormato;

	@Column(name = "DESCRIZIONE_SOCIETA", length = 30)
	private String descrizioneSocieta;

	@Column(name = "DESCRIZIONE_ZONA", length = 30)
	private String descrizioneZona;

	@Column(name = "PUNTO_VENDITA_CHIUSO", precision = 1)
	private Long puntoVenditaChiuso = 0L;

	@Column(name = "REPARTO_AVF", precision = 1)
	private Long repartoAvf = 0L;

	@Column(name = "REPARTO_PROFUMERIA", precision = 1)
	private Long repartoProfumeria = 0L;

	@Column(nullable = false, length = 1)
	private String societa;

	@Column(name = "TIPO_NEGOZIO", length = 50)
	private String tipoNegozio;

	@Column(name = "SIGLA", length = 7)
	private String sigla;

	@Column(name = "DESCRIZIONE_CLUSTER", length = 30)
	private String descrizioneCluster;

	@Column(name = "TIPO_CONSEGNA", length = 50)
	private String tipoConsegna;

	// bi-directional many-to-one association to MuiCfgCanaleNegozi
	@OneToMany(mappedBy = "negozioEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<CfgCanaleNegoziEntity> muiCfgCanaleNegozis;

	// bi-directional many-to-one association to TipoNegozioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_NEGOZIO", nullable = false)
	private TipoNegozioEntity tipoNegozioEntity;

	// bi-directional many-to-one association to PromozioneNegozioEntity
	@OneToMany(mappedBy = "negozioEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<PromozioneNegozioEntity> promozioneNegozioEntities;

	// bi-directional many-to-one association to MuiAssortimentoFornitore
	@OneToMany(mappedBy = "negozioEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<AssortimentoNegozioEntity> muiAssortimentoNegozi;

	public CfgCanaleNegoziEntity addMuiCfgCanaleNegozi(CfgCanaleNegoziEntity muiCfgCanaleNegozi) {
		getMuiCfgCanaleNegozis().add(muiCfgCanaleNegozi);
		muiCfgCanaleNegozi.setNegozioEntity(this);

		return muiCfgCanaleNegozi;
	}

	public CfgCanaleNegoziEntity removeMuiCfgCanaleNegozi(CfgCanaleNegoziEntity muiCfgCanaleNegozi) {
		getMuiCfgCanaleNegozis().remove(muiCfgCanaleNegozi);
		muiCfgCanaleNegozi.setNegozioEntity(null);

		return muiCfgCanaleNegozi;
	}

	public PromozioneNegozioEntity addPromozioneNegozioEntity(PromozioneNegozioEntity promozioneNegozioEntity) {
		getPromozioneNegozioEntities().add(promozioneNegozioEntity);
		promozioneNegozioEntity.setNegozioEntity(this);

		return promozioneNegozioEntity;
	}

	public PromozioneNegozioEntity removePromozioneNegozioEntity(PromozioneNegozioEntity promozioneNegozioEntity) {
		getPromozioneNegozioEntities().remove(promozioneNegozioEntity);
		promozioneNegozioEntity.setNegozioEntity(null);

		return promozioneNegozioEntity;
	}

	public AssortimentoNegozioEntity addMuiAssortimentoNegozio(AssortimentoNegozioEntity muiAssortimentoNegozi) {
		getMuiAssortimentoNegozi().add(muiAssortimentoNegozi);

		muiAssortimentoNegozi.setNegozioEntity(this);

		return muiAssortimentoNegozi;
	}

	public AssortimentoNegozioEntity removeMuiAssortimentoNegozio(AssortimentoNegozioEntity muiAssortimentoNegozi) {
		getMuiAssortimentoNegozi().remove(muiAssortimentoNegozi);
		muiAssortimentoNegozi.setNegozioEntity(null);

		return muiAssortimentoNegozi;
	}
}