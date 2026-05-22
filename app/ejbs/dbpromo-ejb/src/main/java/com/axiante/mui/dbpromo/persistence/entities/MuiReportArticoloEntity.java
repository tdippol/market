package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "V_MUI_REPORT_ARTICOLI", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NamedQueries(value = {
        @NamedQuery(name = "MuiReportArticoloEntity.findAllInProgressFuturesByIdItem",
                query = "select e from MuiReportArticoloEntity e where e.id.idItem = :idItem and ((e.dataInizio <= :date and e.dataFine > :date) or (e.dataInizio > :date))"),
        @NamedQuery(name = "MuiReportArticoloEntity.findAllCompletedByIdItem",
                query = "select e from MuiReportArticoloEntity e where e.id.idItem = :idItem and e.dataFine <= :date")
})
public class MuiReportArticoloEntity implements Serializable {
    private static final long serialVersionUID = -6218504764824162602L;

    @EmbeddedId
    private MuiReportArticoloId id;

    @Column(name = "ANNO")
    private String anno;

    @Column(name = "CODICE_CANALE", nullable = false, precision = 3)
    private Long codiceCanale;

    @Column(name = "DESCRIZIONE_CANALE")
    private String descrizioneCanale;

    @Column(name = "CODICE_GRUPPO", nullable = false)
    private String codiceGruppo;

    @Column(name = "DESCRIZIONE_GRUPPO")
    private String descrizioneGruppo;

    @Column(name = "DESCRIZIONE_PROMOZIONE")
    private String descrizionePromozione;

    @Column(name = "CODICE_STATO", nullable = false)
    private String codiceStato;

    @Column(name = "DESCRIZIONE_STATO")
    private String descrizioneStato;

    @Column(name = "DATA_INIZIO_TESTATA")
    private Date dataInizioTestata;

    @Column(name = "DATA_FINE_TESTATA")
    private Date dataFineTestata;

    @Column(name = "CODICE_ARTICOLO", nullable = false)
    private String codiceArticolo;

    @Column(name = "DESCRIZIONE_ARTICOLO", nullable = false)
    private String descrizioneArticolo;

    @Column(name = "CODICE_COMPRATORE", nullable = false)
    private String codiceCompratore;

    @Column(name = "DATA_INIZIO_ARTICOLO")
    private Date dataInizioArticolo;

    @Column(name = "DATA_FINE_ARTICOLO")
    private Date dataFineArticolo;

    @Column(name = "VALORE")
    private String valore;

    @Column(name = "DATA_INIZIO")
    private Date dataInizio;

    @Column(name = "DATA_FINE")
    private Date dataFine;

    @Column(name = "FL_ESTENDI_LAESSE")
    private String flEstendiLaesse;

    @Column(name = "FL_ESTENSIONE_CLUSTER")
    private String flEstensioneCluster;
}
