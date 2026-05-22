package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the V_MUI_PROMO_DBPROMO database table.
 */
@Entity
@Getter
@Setter
@Table(name = "V_MUI_PROMO_DBPROMO", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries(value = {
		@NamedQuery(name = "MuiPromoDbPromoEntity.findAll", query = "SELECT e FROM MuiPromoDbPromoEntity e"),
		@NamedQuery(name = "MuiPromoDbPromoEntity.findByGiorniSelezione",
				query = "SELECT e FROM MuiPromoDbPromoEntity e WHERE e.dataFine >= :data"),
		@NamedQuery(name = "MuiPromoDbPromoEntity.countByCodicePromozione",
				query = "SELECT COUNT(e) FROM MuiPromoDbPromoEntity e WHERE e.codicePromozione = :codicePromozione"),
		@NamedQuery(name = "MuiPromoDbPromoEntity.findByCodicePromozione",
				query = "SELECT e FROM MuiPromoDbPromoEntity e WHERE e.codicePromozione = :codicePromozione")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MuiPromoDbPromoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODICE_PROMOZIONE", unique = true, nullable = false)
	@EqualsAndHashCode.Include
	private String codicePromozione;

	@Column(name="CODICE_GRUPPO", length = 4)
	String codiceGruppo;

	@Column(name = "DESCRIZIONE_GRUPPO", length = 30)
	String descrizioneGruppo;

	@Column(name="CODICE_CANALE", precision = 0, length = 3)
	String codiceCanale;

	@Column(name = "DESCRIZIONE_CANALE", length = 30)
	String descrizioneCanale;

	@Column(name="DESCRIZIONE_ESTESA", length = 139)
	String descrizioneEstesa;

	@Column(name="DATA_INIZIO")
	@Temporal(TemporalType.DATE)
	private Date dataInizio;

	@Column(name="DATA_FINE")
	@Temporal(TemporalType.DATE)
	private Date dataFine;

	@Column (name = "COD_STATO")
	String codiceStato;

	@Column (name="DESC_STATO")
	String descrizioneStato;

	@Column(name="SORGENTE_DATI", length = 7)
	String sorgenteDati;

	@Transient
	public String getStato(){
		StringBuffer stato = new StringBuffer();
		if ( getCodiceStato() != null ) {
			stato.append(getCodiceStato());
		}
		if ( getDescrizioneStato() != null ) {
			if ( stato.length() > 0 ) {
				stato.append("-");
			}
			stato.append(getDescrizioneStato());
		}
		return stato.toString();
	}
}