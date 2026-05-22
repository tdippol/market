package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.CumulabilitaTypeConverter;
import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.persistence.Metadata;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "MUI_CUMULABILITA_ESCLUSIVITA", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "CumulabilitaEsclusivitaEntity.findByType",
                query = "SELECT c FROM CumulabilitaEsclusivitaEntity c where c.tipo = :type"),
        @NamedQuery(name = "CumulabilitaEsclusivitaEntity.findOpenByType",
                query = "SELECT c FROM CumulabilitaEsclusivitaEntity c where c.tipo = :type and c.dataFine > :dataFine"),
        @NamedQuery(name = "CumulabilitsEsclusivitaEntity.findOverlapping",
                query = "SELECT c FROM  CumulabilitaEsclusivitaEntity c where c.dataInizio < :dataFine and c.dataFine > :dataInizio and c.id <> :id")
})
public class CumulabilitaEsclusivitaEntity implements DbPromoEntityInterface, AuditLogInterface {
    @Id
    @Column(name = "ID", nullable = false, precision = 16)
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CUMULABILITA_ESCLUSIVITA_ID_GENERATOR", sequenceName = "MUI_CUMUL_ESCLUS_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CUMULABILITA_ESCLUSIVITA_ID_GENERATOR")
    private Long id;

    @Column(name = "TIPO", nullable = false)
    @Convert(converter = CumulabilitaTypeConverter.class)
    private CumulabilitaType tipo;

    @Column(name = "CODICE_PROMOZIONE", length = 10, nullable = false)
    private String codicePromozione;

    @Column(name = "PREFISSO_BS", length = 5, nullable = false)
    private String prefissoBS;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INIZIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FINE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataFine;

    @Column(name = "DESCRIZIONE", length = 100, nullable = false)
    private String descrizione;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_PUBBLICAZIONE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataPubblicazione;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_ANNULLAMENTO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataAnnullamento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 10, nullable = false)
    private String codiceUtenteInserimento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 10)
    private String codiceUtenteAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO", nullable = false)
    private Date dataInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @OneToMany(
            mappedBy = "cumulabilitaEsclusivitaEntity",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
    )
    private Set<CumulabilitaBuoniEntity> cumulabilitaBuoniEntities;

    public boolean addCumulabilitaBuoniEntity(CumulabilitaBuoniEntity cumulabilitaBuoniEntity) {
        if ( cumulabilitaBuoniEntities == null ) {
            cumulabilitaBuoniEntities = new HashSet<>();
        }
        cumulabilitaBuoniEntity.setCumulabilitaEsclusivitaEntity(this);
        return cumulabilitaBuoniEntities.add(cumulabilitaBuoniEntity);
    }

    public boolean removeCumulabilitaBuoniEntity(CumulabilitaBuoniEntity cumulabilitaBuoniEntity) {
        if ( cumulabilitaBuoniEntities == null ) {
            return false;
        }
        cumulabilitaBuoniEntity.setCumulabilitaEsclusivitaEntity(null);
        return cumulabilitaBuoniEntities.remove(cumulabilitaBuoniEntity);
    }
}
