package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "V_MUI_PN_MED_PROMO_DBP_ART_L2", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@Getter
@Setter
@IdClass(PianoMediaPromoArticoliDettaglioDbPromoIdClass.class)
@NamedQueries({
        @NamedQuery(name = "PianoMediaPromoArticoliDettaglioDbPromoEntity.findByCodicePromoAndItemAndTipoItem",
                query = "SELECT e FROM PianoMediaPromoArticoliDettaglioDbPromoEntity e WHERE e.codicePromozione = :codicePromo and e.codiceItem = :codiceItem and e.tipoItem = :tipoItem"),
})
public class PianoMediaPromoArticoliDettaglioDbPromoEntity {

    @Id
    @Column(name = "CODICE_PROMOZIONE", nullable = false)
    String codicePromozione;

    @Id
    @Column(name = "COD_ITEM", nullable = false)
    String codiceItem;

    @Id
    @Column(name = "TIPO_ITEM", nullable = false)
    String tipoItem;

    @Id
    @Column(name = "COD_MECCANICA")
    String codiceMeccanica;

    @Id
    @Column(name = "COD_CONDIZIONE")
    String codiceCondizione;

    @Id
    @Column(name = "SOCIETA")
    String codiceSocieta;

    @Id
    @Column(name = "COD_ZONA")
    String codiceZona;

    @Column(name = "FL_VOLANTINO")
    @Convert(converter = BooleanConverter.class)
    Boolean flVolantino;

    @Column(name = "DATA_INIZIO")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dataInizio;

    @Column(name = "DATA_FINE")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dataFine;

    @Column(name="VALORE")
    String valore;

    @Column(name="DES_ZONA")
    String descrizioneZona;
}
