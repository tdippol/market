package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners(DbPromoReadOnlyEntity.class)
@Table(name = "V_PROMOZIONI_ATTIVE", schema = Metadata.SCHEMA)
@NamedQuery(name = "PromozioneAttiva.findAll", query = "SELECT e FROM PromozioneAttiva e order by e.codice")
public class PromozioneAttiva implements Serializable {
    private static final long serialVersionUID = 6232598582637378179L;

    @Id
    @Column(name = "COD_PROMO", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String codice;

    @Column(name = "DES_PROMOZIONE", nullable = false)
    private String descrizione;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INIZIO", nullable = false)
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FINE", nullable = false)
    private Date dataFine;

    @Transient
    public String getDescrizioneEstesa() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%s %s [%s - %s]", codice, descrizione, sdf.format(dataInizio), sdf.format(dataFine));
    }
}
