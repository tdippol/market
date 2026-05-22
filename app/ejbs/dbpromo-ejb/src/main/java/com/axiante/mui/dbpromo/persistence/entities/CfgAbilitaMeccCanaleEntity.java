package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_CFG_ABILITA_MECC_CANALE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_ABILITA_MECC_CANALE", schema = Metadata.SCHEMA)
@NamedQueries({
	@NamedQuery(name = "MuiCfgAbilitaMeccCanale.findAll",
			query = "SELECT m FROM CfgAbilitaMeccCanaleEntity m"),
	@NamedQuery(name = "MuiCfgAbilitaMeccCanale.findAllByIdCanale",
			query = "SELECT m FROM CfgAbilitaMeccCanaleEntity m WHERE m.canalePromozioneEntity = :canalePromozioneEntity"),
	@NamedQuery(name = "MuiCfgAbilitaMeccCanale.findByMeccanicaAndCanale",
				query = "SELECT m FROM CfgAbilitaMeccCanaleEntity m WHERE m.canalePromozioneEntity.id = :idCanale and m.meccanicheEntity.id = :idMeccanica")
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CfgAbilitaMeccCanaleEntity implements Serializable, AuditLogInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_ABILITA_MECC_CANALE_ID_GENERATOR", sequenceName = "MUI_CFG_ABLT_MECC_CANL_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_ABILITA_MECC_CANALE_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(name = "FLAG_ABILITA", precision = 1)
	private Long flagAbilita;

	@Column(name = "FLAG_DEFAULT", precision = 1)
	private Long flagDefault;

	// bi-directional many-to-one association to MuiCanalePromozione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CANALE", nullable = false)
	private CanalePromozioneEntity canalePromozioneEntity;

	// bi-directional many-to-one association to MeccanicheEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MECCANICA", nullable = false)
	private MeccanicheEntity meccanicheEntity;

	@Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
	private String codiceUtenteAggiornamento;

	@Column(name = "CODICE_UTENTE_INSERIMENTO")
	private String codiceUtenteInserimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataAggiornamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INSERIMENTO")
	private Date dataInserimento;

	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "meccanicaCanaleAbilitata",
			orphanRemoval = true,
			cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	Set<CfgAbilitaCheckEntity> controlliConfigurati = new HashSet<>();
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meccanicaCanaleAbilitata", orphanRemoval = true)
	List<CfgSovrapposizioneMeccanicheEntity> sovrapposizioniConfigurate = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CfgAbilitaMeccCanaleEntity )) {
			return false;
		}
		CfgAbilitaMeccCanaleEntity other = (CfgAbilitaMeccCanaleEntity) o;
		if ( id != null ) {
			// se ha id allora e' uguale se ha id
			return id.equals(other.getId());
		} else {
			// se non ha id, allora controlla di non stare inserendo qualcosa che di duplicato:
			// la chiave univoca e' id_canale+id_meccanica
			if ( (getCanalePromozioneEntity() != null) && (getMeccanicheEntity() != null) ) {
				return getCanalePromozioneEntity().getId().equals(other.getCanalePromozioneEntity().getId())
						&&
						getMeccanicheEntity().getId().equals(other.getMeccanicheEntity().getId());
			}
			if ((getCanalePromozioneEntity() != other.getCanalePromozioneEntity())
					|| (getMeccanicheEntity() != other.getMeccanicheEntity())) {
				return false;
			}
			return true;
		}
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	
	public CfgAbilitaCheckEntity addControllo(CfgAbilitaCheckEntity controllo) {
		controlliConfigurati.add(controllo) ;
		controllo.setMeccanicaCanaleAbilitata(this);
		return controllo;
	}
	
	public CfgAbilitaCheckEntity removeControllo(CfgAbilitaCheckEntity controllo) {
		controlliConfigurati.remove(controllo);
		controllo.setMeccanicaCanaleAbilitata(null);
		return controllo;
	}

	public void addSovrapposizione(CfgSovrapposizioneMeccanicheEntity sovrapposizione) {
		sovrapposizioniConfigurate.add(sovrapposizione);
		sovrapposizione.setMeccanicaCanaleAbilitata(this);
	}

	public void removeSovrapposizione(CfgSovrapposizioneMeccanicheEntity sovrapposizione) {
		sovrapposizioniConfigurate.remove(sovrapposizione);
		sovrapposizione.setMeccanicaCanaleAbilitata(null);
	}
}