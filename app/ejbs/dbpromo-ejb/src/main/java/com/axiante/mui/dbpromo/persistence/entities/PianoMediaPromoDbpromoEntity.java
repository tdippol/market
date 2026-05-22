package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "V_MUI_PN_MED_PROMO_DBPROMO", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NamedQueries({
        @NamedQuery(name = "PianoMediaPromoDbpromoEntity.findByDataFineGreaterThan",
                query = "SELECT e FROM PianoMediaPromoDbpromoEntity e WHERE e.dataFine > :date"),
        @NamedQuery(name = "PianoMediaPromoDbpromoEntity.findByDataFineGreaterThanAndCanali",
                query = "SELECT e FROM PianoMediaPromoDbpromoEntity e WHERE e.dataFine > :date AND e.codiceCanale IN :codiciCanale"),
        @NamedQuery(name = "PianoMediaPromoDbpromoEntity.countByCodicePromo",
                query = "SELECT COUNT(e) FROM PianoMediaPromoDbpromoEntity e WHERE e.codicePromozione = :codicePromo"),
        @NamedQuery(name = "PianoMediaPromoDbpromoEntity.findByCodicePromo",
                query = "SELECT e FROM PianoMediaPromoDbpromoEntity e WHERE e.codicePromozione = :codicePromo"),
        @NamedQuery(name = "PianoMediaPromoDbpromoEntity.findAllByCodiciPromo",
                query = "SELECT e FROM PianoMediaPromoDbpromoEntity e WHERE e.codicePromozione IN :codiciPromo")
})
@NoArgsConstructor
@Getter
@Setter
public class PianoMediaPromoDbpromoEntity implements Serializable, ComboBoxCapable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODICE_PROMOZIONE", unique = true, nullable = false)
    private String codicePromozione;

    @Column(name = "CODICE_GRUPPO", length = 4)
    private String codiceGruppo;

    @Column(name = "DESCRIZIONE_GRUPPO", length = 30)
    private String descrizioneGruppo;

    @Column(name = "CODICE_CANALE", precision = 0, length = 3)
    private Long codiceCanale;

    @Column(name = "DESCRIZIONE_CANALE", length = 30)
    private String descrizioneCanale;

    @Column(name = "DESCRIZIONE_ESTESA", length = 139)
    private String descrizioneEstesa;

    @Column(name = "DATA_INIZIO")
    @Temporal(TemporalType.DATE)
    private Date dataInizio;

    @Column(name = "DATA_FINE")
    @Temporal(TemporalType.DATE)
    private Date dataFine;

    @Column(name = "COD_STATO")
    private String codiceStato;

    @Column(name = "DESC_STATO")
    private String descrizioneStato;

    @Column(name = "ANNO")
    private Integer anno;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getKey() {
        return codicePromozione;
    }

    @Override
    public String getLabel() {
        return descrizioneEstesa;
    }
}
