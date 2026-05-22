package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
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
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CONTRIBUTI_PROMO", schema = Metadata.SCHEMA)
@NamedQueries(value = {
        @NamedQuery(name = "ContributiPromoEntity.findAllByPromozioni",
                query = "SELECT e FROM ContributiPromoEntity e WHERE e.promozione IN :promozioni"),
        @NamedQuery(name = "ContributiPromoEntity.findAllByPromozione",
                query = "SELECT e FROM ContributiPromoEntity e WHERE e.promozione = :promozione"),
        @NamedQuery(name = "ContributiPromoEntity.countByPromozioneAndCompratoreAndFornitore",
                query = "SELECT COUNT(e) FROM ContributiPromoEntity e WHERE e.promozione = :promozione AND e.compratore = :compratore AND e.fornitore = :fornitore"),
        @NamedQuery(name = "ContributiPromoEntity.findAllItemsIdByPromozione",
                query = "SELECT a.item.id FROM ContributiPromoEntity c JOIN FETCH c.articoli a WHERE c.promozione = :promozione"),
        @NamedQuery(name = "ContributiPromoEntity.nextProgressivo",
                query = "SELECT COUNT(e) + 1 FROM ContributiPromoEntity e WHERE e.promozione = :promozione AND e.compratore = :compratore AND e.fornitore = :fornitore")
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContributiPromoEntity implements Serializable {
    private static final long serialVersionUID = 351226946758242907L;

    @Id
    @SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CONTRIBUTI_PROMO_ID_GENERATOR", sequenceName = "MUI_CONTRIBUTI_PROMO_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CONTRIBUTI_PROMO_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    PromozioneTestataEntity promozione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPRATORE", nullable = false)
    CompratoreEntity compratore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FORNITORE", nullable = false)
    FornitoreEntity fornitore;

    @Column(name = "PROGRESSIVO", precision = 16)
    Integer progressivo;

    @Column(name="COD_PRESTAZIONE", length = 6)
    String codicePrestazione;

    @Column(name="VAL_APPLICATO", precision = 10, scale = 2)
    Double valoreApplicato;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_LIQUIDAZIONE")
    Date dataLiquidazione;

    @Column(name = "NOTA_COMPRATORE", length = 255)
    String notaCompratore;

    @Column(name = "NOTA_FATTURA", length = 255)
    String notaFattura;

    @Column(name="COD_STATO_CONTR", length = 4)
    String codiceStato;

    @Column(name="SALDO_MOVIMENTI", precision = 10, scale = 2)
    Double saldoMovimenti;

    @OneToMany(mappedBy = "contributiPromo", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, orphanRemoval = true)
    Set<ContributiPromoArticoloEntity> articoli;

    public void addArticolo(@NonNull ContributiPromoArticoloEntity articolo ){
        if ( getArticoli() == null ){
            setArticoli(new HashSet<>());
        }
        getArticoli().add(articolo);
        articolo.setContributiPromo(this);
    }

    public void removeArticolo(@NonNull ContributiPromoArticoloEntity articolo){
        if ( getArticoli() != null && getArticoli().remove(articolo)){
            articolo.setContributiPromo(null);
        }
    }
}
