package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MUI_INIZIATIVE_STATO", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class IniziativaStatoEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_INIZIATIVE_STATO_ID_GENERATOR",
            sequenceName = "MUI_INIZIATIVE_STATO_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_INIZIATIVE_STATO_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INIZIATIVA")
    private IniziativaEntity iniziativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO")
    private StatoPromozioneEntity statoPromo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO_STATO", nullable = false)
    private Date dataInizioStato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE_STATO")
    private Date dataFineStato;

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
