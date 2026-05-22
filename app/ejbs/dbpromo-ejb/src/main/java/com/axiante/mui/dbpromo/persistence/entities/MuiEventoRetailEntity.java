package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MUI_EVENTO_RETAIL", schema = Metadata.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(
                name = "MuiEventoRetailEntity.allEagerWithDescription",
                query =
                        "SELECT new com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO("
                                + "e.id, e.codiceFornitore, e.codiceCausale, e.dataInizio, e.dataFine, "
                                + "e.valoreContributo, e.note, "
                                + "c.descrizione, f.descrizione, "
                                + "m.id, m.codice, m.descrizione, m.dataInizio, m.dataFine, "
                                + "m.dataInserimento, m.codiceUtenteInserimento, m.dataAggiornamento, m.codiceUtenteAggiornamento"
                                + ") "
                                + "FROM MuiEventoRetailEntity e "
                                + "LEFT JOIN e.macrospazio m "
                                + "LEFT JOIN CausaliRetailEntity c ON e.codiceCausale = c.codice "
                                + "LEFT JOIN FornitoriRetailEntity f ON e.codiceFornitore = f.codice "
                                + "ORDER BY m.codice, e.codiceFornitore, e.dataInizio"),
        @NamedQuery(
                name = "MuiEventoRetailEntity.countByIdMacrospazio",
                query =
                        "SELECT COUNT(e) FROM MuiEventoRetailEntity e WHERE e.macrospazio.id = :idMacrospazio"),
        @NamedQuery(
                name = "MuiEventoRetailEntity.findByIdMacrospazio",
                query = "SELECT e FROM MuiEventoRetailEntity e WHERE e.macrospazio.id = :idMacrospazio"),
        @NamedQuery(
                name = "MuiEventoRetailEntity.findEagerByIdMacrospazio",
                query = "SELECT new com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO("
                        + "e.id, e.codiceFornitore, e.codiceCausale, e.dataInizio, e.dataFine, "
                        + "e.valoreContributo, e.note, "
                        + "c.descrizione, f.descrizione, "
                        + "m.id, m.codice, m.descrizione, m.dataInizio, m.dataFine, "
                        + "m.dataInserimento, m.codiceUtenteInserimento, m.dataAggiornamento, m.codiceUtenteAggiornamento"
                        + ") "
                        + "FROM MuiEventoRetailEntity e "
                        + "LEFT JOIN e.macrospazio m "
                        + "LEFT JOIN CausaliRetailEntity c ON e.codiceCausale = c.codice "
                        + "LEFT JOIN FornitoriRetailEntity f ON e.codiceFornitore = f.codice "
                        + "WHERE e.macrospazio.id = :idMacrospazio"),
        @NamedQuery(
                name = "MuiEventoRetailEntity.findEagerById",
                query = "SELECT new com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO("
                        + "e.id, e.codiceFornitore, e.codiceCausale, e.dataInizio, e.dataFine, "
                        + "e.valoreContributo, e.note, "
                        + "c.descrizione, f.descrizione, "
                        + "m.id, m.codice, m.descrizione, m.dataInizio, m.dataFine, "
                        + "m.dataInserimento, m.codiceUtenteInserimento, m.dataAggiornamento, m.codiceUtenteAggiornamento"
                        + ") "
                        + "FROM MuiEventoRetailEntity e "
                        + "LEFT JOIN e.macrospazio m "
                        + "LEFT JOIN CausaliRetailEntity c ON e.codiceCausale = c.codice "
                        + "LEFT JOIN FornitoriRetailEntity f ON e.codiceFornitore = f.codice "
                        + "WHERE e.id = :id"),




})
public class MuiEventoRetailEntity implements Serializable, AuditLogInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_EVENTO_RETAIL_ID_GENERATOR",
            sequenceName = "MUI_EVENTO_RETAIL_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_EVENTO_RETAIL_ID_GENERATOR")
    @Column(name = "ID_EVENTO_RETAIL", unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MACROSPAZIO", nullable = false)
    private MuiMacrospazioMediaEntity macrospazio;

    @Column(name = "CODICE_FORNITORE", length = 10, nullable = false)
    private String codiceFornitore;

    @Column(name = "CODICE_CAUSALE", length = 10, nullable = false)
    private String codiceCausale;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataFine;

    @Column(name = "VALORE_CONTRIBUTO", nullable = false, precision = 16, scale = 2)
    private BigDecimal valoreContributo;

    @Column(name = "NOTE", length = 4000)
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

    @Transient
    String causaleDescription;

    @Transient
    String fornitoreDescription;
}
