package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_CONF_HEADER", schema = Metadata.SCHEMA)
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "CfgConfHeaderEntity.findByMeccanicaIdAndSetPianificazioneId",
                query = "SELECT h FROM CfgConfHeaderEntity h WHERE h.meccanicaEntity.id = :meccanicaId AND h.muiCfgSetPianificazione.id = :setPianificazioneId"),
        @NamedQuery(name = "CfgConfHeaderEntity.findLivelloById",
                query = "SELECT DISTINCT h.livelloPianificazione.codice FROM CfgConfHeaderEntity h WHERE h.id=:idHeader")
})
public class CfgConfHeaderEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 8262131891485840004L;
	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_CONF_HEADER_ID_GENERATOR", sequenceName = "MUI_CFG_CONF_HEADER_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_CONF_HEADER_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	// bi-directional many-to-one association to NegozioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CFG_SET_PIANIFICAZIONE", nullable = false)
	private CfgSetPianificazioneEntity muiCfgSetPianificazione;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MECCANICA", nullable = false)
	private MeccanicheEntity meccanicaEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CFG_LIV_PIANIFICAZIONE", nullable = false)
	CfgLivelloPianificazioneEntity livelloPianificazione;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "confHeader",
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			orphanRemoval = true)
	Set<CfgTipoElementoEntity> tipiElemento;

	@Column(name = "MIN_SET")
	Integer minSet;

	@Column(name = "MAX_SET")
	Integer maxSet;

	@Column(name = "MIN_RAGGRUPPAMENTO")
	Integer minRaggruppamento;

	@Column(name = "MAX_RAGGRUPPAMENTO")
	Integer maxRaggruppamento;

	@Column(name = "UNICA_IN_PROMO")
	Integer unicaInPromo;

	@Column(name = "DUPLICA_ARTICOLO")
	@Convert(converter = BooleanConverter.class)
	Boolean duplicaArticolo;

	@Column(name = "DUPLICA_REPARTO")
	@Convert(converter = BooleanConverter.class)
	Boolean duplicaReparto;

	@Column(name = "DUPLICA_GRM")
	@Convert(converter = BooleanConverter.class)
	Boolean duplicaGrm;

	@Column(name="DUPLICA_TOTALE")
	@Convert(converter = BooleanConverter.class)
	Boolean duplicaTotale;

	@Column(name="LOGO_MESSAGGI")
	@Convert(converter =  BooleanConverter.class)
	Boolean logoMessaggi;

	@Column(name="CASTELLETTI")
	@Convert(converter = BooleanConverter.class)
	Boolean castelletti;

	// bi-directional many-to-one association to MuiCfgPianificazione
	@OneToMany(mappedBy = "muiCfgConfHeader",
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			orphanRemoval = true)
	private Set<CfgPianificazioneEntity> muiCfgPianificaziones;

	public CfgTipoElementoEntity addTipoElemento(final @NonNull CfgTipoElementoEntity tipoElemento) {
		if (getTipiElemento() == null) {
			tipiElemento = new HashSet<>();
		}
		this.getTipiElemento().add(tipoElemento);
		tipoElemento.setConfHeader(this);
		return tipoElemento;
	}

	public CfgTipoElementoEntity removeTipoElemento(final @NonNull CfgTipoElementoEntity tipoElemento) {
		if (getTipiElemento() != null && getTipiElemento().remove(tipoElemento)) {
			tipoElemento.setConfHeader(null);
		}
		return tipoElemento;
	}

	public CfgPianificazioneEntity addPianificazione(final @NonNull CfgPianificazioneEntity pianificazione) {
		if (getMuiCfgPianificaziones() == null) {
			muiCfgPianificaziones = new HashSet<>();
		}
		this.getMuiCfgPianificaziones().add(pianificazione);
		pianificazione.setMuiCfgConfHeader(this);
		return pianificazione;
	}

	public CfgPianificazioneEntity removePianificazione(final @NonNull CfgPianificazioneEntity pianificazione) {
		if (getMuiCfgPianificaziones() != null && getMuiCfgPianificaziones().remove(pianificazione)) {
			pianificazione.setMuiCfgConfHeader(null);
		}
		return pianificazione;
	}
}
