package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "V_MUI_PROMO_ARTICOLI_DBPROMO", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "MuiPromoArticoliDbPromoEntity.findAll",
                query = "SELECT e FROM MuiPromoArticoliDbPromoEntity e ORDER BY e.id.codicePromozione, e.id.tipoItem, e.id.codiceItem"),
        @NamedQuery(name = "MuiPromoArticoliDbPromoEntity.findAllByCodicePromozione",
                query = "SELECT e FROM MuiPromoArticoliDbPromoEntity e WHERE e.id.codicePromozione = :codicePromozione ORDER BY e.id.tipoItem, e.id.codiceItem")
})
public class MuiPromoArticoliDbPromoEntity implements Serializable {
    private static final long serialVersionUID = -4901792322006368816L;

    @EmbeddedId
    private MuiPromoArticoliDbPromoId id;

    @Column(name = "DESCRIZIONE_ITEM")
    private String descrizioneItem;

    @Column(name = "TIPO_OFFERTA")
    private String tipoOfferta;

    @Column(name = "COD_MECCANICA")
    private String codiceMeccanica;

    @Column(name = "DESCRIZIONE_MECCANICA")
    private String descrizioneMeccanica;

    @Column(name = "COD_CONDIZIONE")
    private String codiceCondizione;

    @Column(name = "VALORE")
    private String valore;

    @Column(name = "COD_COMPRATORE", nullable = false)
    private String codiceCompratore;

    @Column(name = "SORGENTE_DATI")
    private String sorgenteDati;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODICE_ITEM", referencedColumnName = "ID")
    private ItemEntity item;
}
