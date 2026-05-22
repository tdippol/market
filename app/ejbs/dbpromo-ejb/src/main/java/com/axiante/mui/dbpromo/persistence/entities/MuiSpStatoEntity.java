package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MUI_SP_STATO", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(
                name = "MuiSpStatoEntity.findAllByPromozioneIdOrderByDataInizio",
                query = "SELECT s FROM MuiSpStatoEntity s " +
                        "JOIN FETCH s.statoPromozioneEntity sp " +
                        "WHERE s.spTestataEntity.id = :idPromozione " +
                        "ORDER BY s.dataInizioStato DESC"
        )
})
public class MuiSpStatoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "MUI_SP_STATO_ID_GENERATOR",
            sequenceName = "MUI_SP_STATO_ID_SEQ",
            schema = Metadata.SCHEMA,
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MUI_SP_STATO_ID_GENERATOR")
    @Column(name = "ID", nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private MuiSpTestataEntity spTestataEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO", nullable = false)
    private StatoPromozioneEntity statoPromozioneEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO_STATO", nullable = false)
    private Date dataInizioStato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE_STATO")
    private Date dataFineStato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO", nullable = false)
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 50)
    private String codiceUtenteAggiornamento;

    @Column(name = "UUID", nullable = false, length = 32)
    private String uuid;
}