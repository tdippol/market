package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MUI_INIZIATIVE", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "IniziativaEntity.findAllNotCancelled",
                query = "SELECT e FROM IniziativaEntity e INNER JOIN FETCH e.stati s INNER JOIN FETCH s.statoPromo sp WHERE sp.codiceStato <> :codiceStato AND s.dataFineStato IS NULL ORDER BY e.id"),
        @NamedQuery(name = "IniziativaEntity.findAllPublishedAndInProgressAndValidDates",
                query = "SELECT e FROM IniziativaEntity e INNER JOIN FETCH e.stati s INNER JOIN FETCH s.statoPromo sp WHERE sp.codiceStato IN :codiciStato AND s.dataFineStato IS NULL AND e.dataInizio <= :dataInizio AND e.dataFine >= :dataFine ORDER BY e.id"),
        @NamedQuery(name = "IniziativaEntity.findStatiTransizioneConsentiti",
                query = "SELECT e FROM StatoPromozioneEntity e WHERE e.codiceStato in ('20', '311')")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class IniziativaEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_INIZIATIVE_ID_GENERATOR",
            sequenceName = "MUI_INIZIATIVE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_INIZIATIVE_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "DESCRIZIONE", nullable = false, length = 30)
    private String descrizione;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO", nullable = false)
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE", nullable = false)
    private Date dataFine;

    @Column(name = "NOTE")
    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 50)
    private String codiceUtenteAggiornamento;

    @Column(name = "UUID", length = 32, nullable = false)
    @EqualsAndHashCode.Include
    private String uuid;

    @OneToMany(mappedBy = "iniziativa", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<IniziativaStatoEntity> stati;

    @Basic
    @Column(name="OGGETTO", length = 24, nullable = true)
    private String oggetto;

    @Column(name="FL_DECIMALE")
    @Convert(converter = BooleanConverter.class)
    private Boolean flagDecimale;

    @OneToMany(mappedBy = "iniziativa", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<PromozioneTestataEntity> promozioni;

    @OneToMany(mappedBy = "iniziativa", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<TotalizzatoriEntity> totalizzatori;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "TOTALIZZATORE_ALLINEATO", nullable = false, columnDefinition = "numeric(1,0) default 0")
    Boolean totalizzatoreAllineato = false;

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }

    public void addStato(@NonNull IniziativaStatoEntity stato) {
        if (getStati() == null) {
            setStati(new HashSet<>());
        }
        getStati().add(stato);
        stato.setIniziativa(this);
    }

    public void removeStato(@NonNull IniziativaStatoEntity stato) {
        if (getStati() != null) {
            getStati().remove(stato);
        }
        stato.setIniziativa(null);
    }

    public void addTestata(@NonNull PromozioneTestataEntity testata){
        if ( getPromozioni() == null ){
            this.promozioni = new HashSet<>();
        }
        this.promozioni.add(testata);
        testata.setIniziativa(this);
    }

    public void removeTestata(@NonNull PromozioneTestataEntity testata){
        if(getPromozioni() != null ){
            getPromozioni().remove(testata);
        }
        testata.setIniziativa(null);
    }

    public void addTotalizzatore(TotalizzatoriEntity totalizzatore){
        if ( getTotalizzatori() == null ){
            setTotalizzatori(new HashSet<>());
        }
        getTotalizzatori().add(totalizzatore);
        totalizzatore.setIniziativa(this);
    }

    public void removeTotalizzatore(TotalizzatoriEntity totalizzatore){
        if ( getTotalizzatori() != null ){
            getTotalizzatori().remove(totalizzatore);
        }
        totalizzatore.setIniziativa(null);
    }
}
