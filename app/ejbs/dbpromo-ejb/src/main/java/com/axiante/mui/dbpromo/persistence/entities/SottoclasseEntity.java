package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NamedQueries({
        @NamedQuery(name = "SottoclasseEntity.findAll",
                query = "SELECT s FROM SottoclasseEntity s ORDER BY s.descrizione"),
        @NamedQuery(name = "SottoclasseEntity.countByCodeOrDescription",
                query = "SELECT count(s) FROM SottoclasseEntity s WHERE s.codice = :codice OR s.descrizione = :descrizione"),
        @NamedQuery(name = "SottoclasseEntity.countSottoclassiNonScaricate",
                query = "SELECT count(s) FROM SottoclasseEntity s WHERE s.dataScarico IS NULL")
})
@Table(name = "MUI_SOTTOCLASSE", schema = Metadata.SCHEMA)
public class SottoclasseEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = -4990816453096726413L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_SOTTOCLASSE_ID_GENERATOR",
            sequenceName = "MUI_SOTTOCLASSE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_SOTTOCLASSE_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "CODICE", nullable = false, unique = true, length = 10)
    private String codice;

    @Column(name = "DESCRIZIONE", nullable = false, unique = true, length = 100)
    private String descrizione;

    @Column(name = "PRIORITA", precision = 5)
    private Integer priorita;

    @Column(name = "DELAY", precision = 5)
    private Integer delay;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "FL_ATTIVA")
    private Boolean attiva = false;

    @Column(name = "DATA_SCARICO")
    private Date dataScarico;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

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
