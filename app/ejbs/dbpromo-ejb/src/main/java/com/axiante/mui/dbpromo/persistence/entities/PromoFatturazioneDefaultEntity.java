package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "MUI_PROMO_FATTURAZIONE_DEFAULT", schema = Metadata.SCHEMA)
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "PromoFatturazioneDefaultEntity.findAllByIdsCompratori",
                query = "SELECT e FROM PromoFatturazioneDefaultEntity e WHERE e.compratore.id IN :idsCompratori ORDER BY e.compratore.codiceCompratore, e.fornitore.codiceFornitore, e.tipoPromozione.descrizione")
})
public class PromoFatturazioneDefaultEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = -1693144239653780591L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMO_FATTURAZ_DEF_ID_GENERATOR",
            sequenceName = "MUI_PROMO_FATTURAZ_DEF_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMO_FATTURAZ_DEF_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPRATORE", nullable = false)
    private CompratoreEntity compratore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FORNITORE", nullable = false)
    private FornitoreEntity fornitore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_PROMO_RIFATT", nullable = false, updatable = false)
    private TipoPromoRifatturazioneEntity tipoPromozione;

    @Column(name = "RIFATTURA", nullable = false, length = 10)
    private String rifattura;

    @Column(name = "VAR_FISSO", precision = 10, scale = 2)
    private BigDecimal varFiss;

    @Column(name = "VAR_PERC", length = 3)
    private Integer varPerc;

    @Column(name = "ABBATTIMENTO", length = 10)
    private String abbattimento;

    @Column(name = "NC_ND", length = 10)
    private String ncNd;

    @Column(name = "CAP_MIN", precision = 10, scale = 2)
    private BigDecimal capMin;

    @Column(name = "CAP_MAX", precision = 10, scale = 2)
    private BigDecimal capMax;

    @Column(name = "SOVRAPPOSIZIONI", length = 10)
    private String sovrapposizioni;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
    private String codiceUtenteAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }
}
