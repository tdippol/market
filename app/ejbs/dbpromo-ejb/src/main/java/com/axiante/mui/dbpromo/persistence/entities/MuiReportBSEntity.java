package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "V_MUI_REPORT_PREFISSO_BS", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NamedQueries(value = {
        @NamedQuery(name = "MuiReportBSEntity.findAll",
                query = "select e from MuiReportBSEntity e"),
        @NamedQuery(name = "MuiReportBSEntity.findAllInProgressByIdItem",
                query = "select e from MuiReportBSEntity e where e.id.prefissoBS = :prefissoBS and e.dataInizio <= :date and e.dataFine > :date"),
        @NamedQuery(name = "MuiReportBSEntity.findAllFuturesByIdItem",
                query = "select e from MuiReportBSEntity e where e.id.prefissoBS = :prefissoBS and e.dataInizio > :date"),
        @NamedQuery(name = "MuiReportBSEntity.findAllCompletedByIdItem",
                query = "select e from MuiReportBSEntity e where e.id.prefissoBS = :prefissoBS and e.dataFine <= :date"),
        @NamedQuery(name = "MuiReportBSEntity.findAllNotUsedInProgress",
                query = "select e from MuiReportBSEntity e where e.chiave not in :chiavi and  e.dataFine > :date"),
        @NamedQuery(name = "MuiReportBSEntity.findInProgress",
                query = "select e from MuiReportBSEntity e where e.dataFine >= :date"),
        @NamedQuery(name = "MuiReportBSEntity.findWithChiaveIn",
                query = "select e from MuiReportBSEntity e where e.chiave in :chiavi"),
        @NamedQuery(name = "MuiReportBSEntity.findNotUsedInBetween",
                query = "select e from MuiReportBSEntity e where e.chiave not in :chiavi and  e.dataInizio < :fine and e.dataFine > :inizio and e.dataFine > :today"),
        @NamedQuery(name = "MuiReportBSEntity.findInBetween",
                query = "select e from MuiReportBSEntity e where e.dataInizio < :fine and e.dataFine > :inizio and e.dataFine > :today"),
})
public class MuiReportBSEntity implements Serializable {
    private static final long serialVersionUID = 7964679662113092572L;

    @EmbeddedId
    private MuiReportBSId id;

    @Column(name = "CHIAVE", nullable = false)
    private String chiave;

    @Column(name = "ANNO", nullable = false)
    private String anno;

    @Column(name = "CODICE_CANALE", nullable = false, precision = 3)
    private Long codiceCanale;

    @Column(name = "DESCRIZIONE_CANALE", nullable = false)
    private String descrizioneCanale;

    @Column(name = "CODICE_GRUPPO", nullable = false)
    private String codiceGruppo;

    @Column(name = "DESCRIZIONE_GRUPPO", nullable = false)
    private String descrizioneGruppo;

    @Column(name = "DESCRIZIONE_PROMOZIONE", nullable = false)
    private String descrizionePromozione;

    @Column(name = "CODICE_STATO", nullable = false)
    private String codiceStato;

    @Column(name = "DESCRIZIONE_STATO", nullable = false)
    private String descrizioneStato;

    @Column(name = "DATA_INIZIO", nullable = false)
    private Date dataInizio;

    @Column(name = "DATA_FINE", nullable = false)
    private Date dataFine;
}
