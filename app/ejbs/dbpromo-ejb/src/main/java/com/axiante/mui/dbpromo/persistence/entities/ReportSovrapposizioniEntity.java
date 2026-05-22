package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_RPT_SOVRAPPOSIZIONI", schema = Metadata.SCHEMA)
@NamedQuery(name = "ReportSovrapposizioniEntity.findAllByPromozioneAndCodiciCompratore",
		query = "SELECT e FROM ReportSovrapposizioniEntity e WHERE e.testata = :testata AND e.item.compratoreEntity.codiceCompratore IN :codiciCompratore")
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class ReportSovrapposizioniEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROMOZIONE")
	private PromozioneTestataEntity testata;

	@Column(name = "COD_PROMO_SOVR", length = 10, nullable = false)
	private String codicePromoSovrapposta;

	@Column(name = "DES_PROMO_SOVR", length = 1000)
	private String descrizionePromoSovrapposta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ITEM")
	private ItemEntity item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MECCANICA")
	private MeccanicheEntity meccanica;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MECCANICA_SOVR")
	private MeccanicheEntity meccanicaSovrapposta;

	@Column(name = "CONDIZIONE")
	@Digits(integer = 8, fraction = 2)
	private BigDecimal valore;

	@Column(name = "CONDIZIONE_SOVR ")
	@Digits(integer = 8, fraction = 2)
	private BigDecimal valoreSovrapposizione;

	@Column(name = "NUM_NEGOZI", precision = 16)
	private Integer numNegozi;

	@Column(name = "DATA_INIZIO_ART", nullable = false)
	private Date dataInizioArticolo;

	@Column(name = "DATA_FINE_ART", nullable = false)
	private Date dataFineArticolo;

	@Column(name = "DATA_INIZIO_ART_SOVR", nullable = false)
	private Date dataInizioArticoloSovrapposto;

	@Column(name = "DATA_FINE_ART_SOVR", nullable = false)
	private Date dataFineArticoloSovrapposto;

	@Column(name = "SEVERITA", length = 20, nullable = false)
	private String severita;

	@Column(name = "NOTE ", length = 4000)
	private String note;

	@Column(name = "ID_TIPO_SOVR", precision = 16)
	private Integer idTipoSovrapposizione;

	@Column(name = "COD_TIPO_SOVR", length = 20)
	private String codiceTipoSovrapposizione;

	@Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
	private String codiceUtenteInserimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataCalcoloSovrapposizione;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "report")
	private Set<ReportSovrapposizioniNegoziEntity> negozi= new HashSet<>();
	
}