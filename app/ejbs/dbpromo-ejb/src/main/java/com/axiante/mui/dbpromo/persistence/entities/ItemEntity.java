package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the MUI_ITEM database table.
 */
@Entity
@Getter
@Setter
@NamedQueries({
		@NamedQuery(name = "ItemEntity.findAll",
				query = "SELECT i FROM ItemEntity i"),
		@NamedQuery(name = "ItemEntity.findByCode",
				query = "SELECT i FROM ItemEntity i WHERE i.codiceItem = :codiceItem"),
		@NamedQuery(name = "ItemEntity.findActiveByCode",
				query = "SELECT i FROM ItemEntity i WHERE i.codiceItem = :codiceItem AND i.dataEliminazioneItem is null "),
		@NamedQuery(name = "ItemEntity.findAllByCompratore",
				query = "SELECT i FROM ItemEntity i WHERE i.compratoreEntity = :compratoreEntity ORDER BY i.subGrmEntity.grmEntity.muiCategoria.repartoEntity.codiceReparto, i.subGrmEntity.grmEntity.muiCategoria.codiceCategoria, i.subGrmEntity.grmEntity.codiceGrm, i.subGrmEntity.codiceSubgrm, i.codiceItem"),
		@NamedQuery(name = "ItemEntity.findCodiceById",
				query = "SELECT e.codiceItem FROM ItemEntity e WHERE e.id = :id"),
		@NamedQuery(name = "ItemEntity.findByIds",
				query = "SELECT i FROM ItemEntity i WHERE i.id IN :ids"),
		@NamedQuery(name = "ItemEntity.findByIdsAndCompratoreAndFornitore",
				query = "SELECT e FROM ItemEntity e JOIN FETCH e.muiAssortimentoFornitores af WHERE e.id IN :ids AND e.compratoreEntity.codiceCompratore = :codiceCompratore AND af.fornitoreEntity.codiceFornitore = :codiceFornitore"),
		@NamedQuery(name = "ItemEntity.findByIdsAndCompratoriAndFornitore",
				query = "SELECT e FROM ItemEntity e JOIN FETCH e.muiAssortimentoFornitores af WHERE e.id IN :ids AND e.compratoreEntity.codiceCompratore IN :codiciCompratori AND af.fornitoreEntity.codiceFornitore = :codiceFornitore"),
		@NamedQuery(name = "ItemEntity.findCodiceMarchioPrivatoByCompratori",
				query = "SELECT DISTINCT i.codiceMarchioPrivato FROM ItemEntity i WHERE i.compratoreEntity.codiceCompratore in :codiciCompratori")
})
@Table(name = "MUI_ITEM", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemEntity implements Serializable, DbPromoEntityInterface, ComboBoxCapable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 16)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(precision = 1)
	private Long attivo;

	@Column(name = "ATTIVO_FI", precision = 1)
	private Long attivoFi;

	@Column(name = "ATTIVO_MI", precision = 1)
	private Long attivoMi;

	@Column(name = "CODICE_FAMIGLIA_AGGR", length = 7)
	private String codiceFamigliaAggr;

	@Column(name = "CODICE_ITEM", nullable = false, length = 6)
	private String codiceItem;

	@Column(name = "CODICE_IVA", precision = 10, scale = 2)
	private BigDecimal codiceIva;

	@Column(name = "CODICE_MARCHIO_PRIVATO", length = 2)
	private String codiceMarchioPrivato;

	@Column(name = "COSTO_NETTO_GIORN", precision = 11, scale = 4)
	private BigDecimal costoNettoGiorn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ELIMINAZIONE_FI")
	private Date dataEliminazioneFi;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ELIMINAZIONE_ITEM")
	private Date dataEliminazioneItem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ELIMINAZIONE_MI")
	private Date dataEliminazioneMi;

	@Column(name = "DEALS_PROMOZIONALE", precision = 1)
	private Long dealsPromozionale;

	@Column(length = 48)
	private String descrizione;

	@Column(name = "DESCRIZIONE_FAMIGLIA_AGGR", length = 40)
	private String descrizioneFamigliaAggr;

	@Column(name = "FL_PR_ATT_PROMO", precision = 1)
	private Long flPrAttPromo;

	@Column(name = "FLAG_PESO", precision = 1)
	private Long flagPeso;

	@Column(name = "FUORI_STAGIONE", precision = 1)
	private Long fuoriStagione;

	@Column(name = "IN_OUT", precision = 1)
	private Long inOut;

	@Column(name = "MARCHIO_SL", precision = 1)
	private Long marchioSl;

	@Column(name = "NUMERO_NEGOZI", precision = 5)
	private Long numeroNegozi;

	@Column(precision = 1)
	private Long nuovo;

	@Column(precision = 7)
	private Long pezzatura;

	@Column(name = "PREZZO_ATTUALE", precision = 9, scale = 2)
	private BigDecimal prezzoAttuale;

	@Column(name = "PRODOTTO_PER_SL", precision = 1)
	private Long prodottoPerSl;

	@Column(name = "SENZA_GLUTINE", precision = 1)
	private Long senzaGlutine;

	@Column(precision = 1)
	private Long stagionale;

	@Column(precision = 3)
	private Long status;

	@Column(name = "UNITA_MISURA", length = 15)
	private String unitaMisura;

	// bi-directional many-to-one association to MuiAssortimentoFornitore
	@OneToMany(mappedBy = "itemEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<AssortimentoFornitoreEntity> muiAssortimentoFornitores;

	// bi-directional many-to-one association to MuiAssortimentoFornitore
	@OneToMany(mappedBy = "itemEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<AssortimentoNegozioEntity> muiAssortimentoNegozi;

	// bi-directional many-to-one association to CompratoreEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COMPRATORE", nullable = false)
	private CompratoreEntity compratoreEntity;

	// bi-directional many-to-one association to SubGrmEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SUBGRM", nullable = false)
	private SubGrmEntity subGrmEntity;

	@Column(name = "FORMATO1", length = 5, nullable = true)
	Integer formato1;

	@Column(name = "FORMATO2", length = 5, nullable = true)
	Integer formato2;

	@Column(name = "FORMATO3", length = 5, nullable = true)
	Integer formato3;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FORNITORE_PRINCIPALE")
	private FornitoreEntity fornitorePrincipale;

	public AssortimentoFornitoreEntity addMuiAssortimentoFornitore(
			AssortimentoFornitoreEntity muiAssortimentoFornitore) {
		getMuiAssortimentoFornitores().add(muiAssortimentoFornitore);
		muiAssortimentoFornitore.setItemEntity(this);

		return muiAssortimentoFornitore;
	}

	public AssortimentoFornitoreEntity removeMuiAssortimentoFornitore(
			AssortimentoFornitoreEntity muiAssortimentoFornitore) {
		getMuiAssortimentoFornitores().remove(muiAssortimentoFornitore);
		muiAssortimentoFornitore.setItemEntity(null);

		return muiAssortimentoFornitore;
	}

	public AssortimentoNegozioEntity addMuiAssortimentoNegozio(AssortimentoNegozioEntity muiAssortimentoNegozi) {
		getMuiAssortimentoNegozi().add(muiAssortimentoNegozi);

		muiAssortimentoNegozi.setItemEntity(this);

		return muiAssortimentoNegozi;
	}

	public AssortimentoNegozioEntity removeMuiAssortimentoNegozio(AssortimentoNegozioEntity muiAssortimentoNegozi) {
		getMuiAssortimentoNegozi().remove(muiAssortimentoNegozi);
		muiAssortimentoNegozi.setItemEntity(null);

		return muiAssortimentoNegozi;
	}

	@Transient
	@Override
	public String getKey() {
		return getCodiceItem();
	}

	@Transient
	@Override
	public String getLabel() {
		return String.format("%s - %s", getCodiceItem(), getDescrizione());
	}
}