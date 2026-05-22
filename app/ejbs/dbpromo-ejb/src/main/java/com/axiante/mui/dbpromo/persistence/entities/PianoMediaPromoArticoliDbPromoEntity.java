package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "V_MUI_PN_MED_PROMO_DBP_ART_L1", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@Getter
@Setter
@IdClass(PianoMediaPromoArticoliDbPromoIdClass.class)
@NamedQueries({
        @NamedQuery(name = "PianoMediaPromoArticoliDbPromoEntity.findByCodicePromo",
                query = "SELECT e FROM PianoMediaPromoArticoliDbPromoEntity e WHERE e.codicePromozione = :codicePromo"),
        @NamedQuery(name = "PianoMediaPromoArticoliDbPromoEntity.findByCodiceItemAndCodicePromoAndTipoItem",
                query = "SELECT e FROM PianoMediaPromoArticoliDbPromoEntity e WHERE e.codiceItem = :codiceItem AND e.codicePromozione = :codicePromo AND e.tipoItem = :tipoItem"),
        @NamedQuery(name = "PianoMediaPromoArticoliDbPromoEntity.countByCodiceItemAndCodicePromoAndTipoItem",
                query = "SELECT COUNT(e) FROM PianoMediaPromoArticoliDbPromoEntity e WHERE e.codiceItem = :codiceItem AND e.codicePromozione = :codicePromo AND e.tipoItem = :tipoItem")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PianoMediaPromoArticoliDbPromoEntity {

    @Id
    @Column(name = "CODICE_PROMOZIONE", nullable = false)
    @EqualsAndHashCode.Include
    String codicePromozione;
    @Id
    @Column(name = "COD_ITEM", nullable = false)
    @EqualsAndHashCode.Include
    String codiceItem;
    @Id
    @Column(name = "TIPO_ITEM", nullable = false)
    @EqualsAndHashCode.Include
    String tipoItem;

    @Column(name="CODICE_ARTICOLO")
    String codiceArticolo;
    @Column(name="DESCRIZIONE")
    String descrizione;
    @Column(name="GRM")
    String codiceGrm;
    @Column(name="DES_GRM")
    String descrizioneGrm;
    @Column(name="SGRM")
    String codiceSubGrm;
    @Column(name="DES_SGRM")
    String descrizioneSubGrm;
    @Column(name="CATEGORIA")
    String codiceCategoria;
    @Column(name="DES_CATE")
    String descrizioneCategoria;
    @Column(name="REPARTO")
    String codiceReparto;
    @Column(name="DES_REPARTO")
    String descrizioneReparto;
    @Column(name="COMPRATORE")
    String codiceCompratore;
    @Column(name="DES_COMPRATORE")
    String descrizioneCompratore;
    @Column(name="PEZZI")
    Integer pezzi;
    @Column(name="FATTURATO")
    Double fatturato;

}
