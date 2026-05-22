package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MUI_MACROSPAZIO_MEDIA", schema = Metadata.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "MuiMacrospazioMediaEntity.countByCodiceMacrospazio",
                query = "SELECT COUNT(m) FROM MuiMacrospazioMediaEntity m WHERE m.codice = :codiceMacrospazio"),
        @NamedQuery(name = "MuiMacrospazioMediaEntity.countByCodiceMacrospazioExcludedIds",
                query = "SELECT COUNT(m) FROM MuiMacrospazioMediaEntity m WHERE m.codice = :codiceMacrospazio AND m.id NOT IN :excludedIds"),
        @NamedQuery(name = "MuiMacrospazioMediaEntity.countByDescrizioneMacrospazio",
                query = "SELECT COUNT(m) FROM MuiMacrospazioMediaEntity m WHERE m.descrizione = :descrizioneMacrospazio"),
        @NamedQuery(name = "MuiMacrospazioMediaEntity.countByDescrizioneMacrospazioExcludedIds",
                query = "SELECT COUNT(m) FROM MuiMacrospazioMediaEntity m WHERE m.descrizione = :descrizioneMacrospazio AND m.id NOT IN :excludedIds")
})
public class MuiMacrospazioMediaEntity implements Serializable, AuditLogInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_MACROSPAZIO_MEDIA_ID_GENERATOR",
            sequenceName = "MUI_MACROSPAZIO_MEDIA_ID_SEQ")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MUI_MACROSPAZIO_MEDIA_ID_GENERATOR")
    @Column(name = "ID_MACROSPAZIO_MEDIA", unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "CODICE", length = 10, nullable = false)
    private String codice;

    @Column(name = "DESCRIZIONE", length = 255)
    private String descrizione;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataFine;

    @Column(name = "LISTINO", precision = 16, scale = 2)
    private BigDecimal listino;

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
}
