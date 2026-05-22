package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * The persistent class for the MUI_COMPRATORE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_COMPRATORE", schema = Metadata.SCHEMA)
@NamedQueries(value = {
		@NamedQuery(name = "CompratoreEntity.findAll",
				query = "SELECT m FROM CompratoreEntity m ORDER BY m.codiceCompratore ASC"),
		@NamedQuery(name = "CompratoreEntity.findAllByCodes",
				query = "SELECT e FROM CompratoreEntity e WHERE e.codiceCompratore IN :codes ORDER BY e.codiceCompratore ASC"),
		@NamedQuery(name = "CompratoreEntity.findAllByIdItems",
				query = "SELECT DISTINCT e FROM CompratoreEntity e JOIN FETCH e.itemEntities i WHERE i.id IN :idItems ORDER BY e.codiceCompratore ASC")
})
@NoArgsConstructor
public class CompratoreEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_COMPRATORE_ID_GENERATOR", sequenceName = "MUI_COMPRATORE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_COMPRATORE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	@Column(name = "CODICE_COMPRATORE", nullable = false, length = 3)
	private String codiceCompratore;

	@Column(length = 20)
	private String descrizione;

	@Column(name = "FLAG_ATTIVO", nullable = false, precision = 1)
	private Long flagAttivo;

	// bi-directional many-to-one association to ResponsabileEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RESPONSABILE", nullable = false)
	private ResponsabileEntity responsabileEntity;

	// bi-directional many-to-one association to ItemEntity
	@OneToMany(mappedBy = "compratoreEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Set<ItemEntity> itemEntities;

	@OneToMany(mappedBy = "compratoreEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<CheckCompratoriEntity> checkCompratori;

	@OneToMany(mappedBy = "compratore", fetch = FetchType.LAZY,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH },
			orphanRemoval = true)
	private Set<CheckPianificazioneEntity> checkPianificazioni;

	@ManyToMany(mappedBy = "owners")
	private Set<PromozioneTestataEntity> promozioni = new HashSet<>();

	@OneToMany(mappedBy = "compratore", fetch = FetchType.LAZY,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH },
			orphanRemoval = true)
	private Set<ContributiPromoEntity> contributiPromo;

	public ItemEntity addMuiItem(ItemEntity itemEntity) {
		getItemEntities().add(itemEntity);
		itemEntity.setCompratoreEntity(this);

		return itemEntity;
	}

	public ItemEntity removeMuiItem(ItemEntity itemEntity) {
		getItemEntities().remove(itemEntity);
		itemEntity.setCompratoreEntity(null);

		return itemEntity;
	}

	public void addCheckCompratori(@NonNull CheckCompratoriEntity check) {
		if ( getCheckCompratori() == null ) {
			setCheckCompratori(new HashSet<CheckCompratoriEntity>());
		}
		getCheckCompratori().add(check);
		check.setCompratoreEntity(this);
	}

	public CheckCompratoriEntity removeCheckCompratori(@NonNull CheckCompratoriEntity check) {
		if ( (getCheckCompratori()!= null) && getCheckCompratori().remove(check)) {
			check.setCompratoreEntity(null);
		}
		return check;
	}

	public void addCheckPianificazione(@NonNull CheckPianificazioneEntity check) {
		if (getCheckPianificazioni() == null) {
			setCheckPianificazioni(new HashSet<>());
		}
		getCheckPianificazioni().add(check);
		check.setCompratore(this);
	}

	public CheckPianificazioneEntity removeCheckPianificazione(@NonNull CheckPianificazioneEntity check) {
		if (getCheckPianificazioni() != null && getCheckPianificazioni().remove(check)) {
			check.setCompratore(null);
		}
		return check;
	}

	public void addContributo(@NonNull ContributiPromoEntity contributo){
		if ( getContributiPromo() == null ){
			setContributiPromo(new HashSet<>());
		}
		getContributiPromo().add(contributo);
		contributo.setCompratore(this);
	}

	public void removeContributo(@NonNull ContributiPromoEntity contributo){
		if ( getContributiPromo() != null && getContributiPromo().remove(contributo)) {
			contributo.setCompratore(null);
		}
	}

	@Transient
	public String getFullDescription() {
		return String.format("[%s] %s", codiceCompratore, descrizione);
	}
}