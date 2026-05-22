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
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(
        name = "MUI_PROMOZIONE_FLAG", schema = Metadata.SCHEMA,
        uniqueConstraints = {
                @UniqueConstraint(name = "MUI_PROMOZIONE_FLAG_UK1", columnNames = {"ID_PROMOZIONE","ID_FLAG"})
        }
)
@NoArgsConstructor
public class MuiPromozioneFlagEntity  implements Serializable, AuditLogInterface, UUIDEnabledEntity {
    private static final long serialVersionUID = -6626823694760866193L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMOZIONE_FLAG_ID_GENERATOR",
            sequenceName = "MUI_PROMOZIONE_FLAG_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMOZIONE_FLAG_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_PROMOZIONE", referencedColumnName = "ID")
    private PromozioneTestataEntity promozioneTestata;

    @ManyToOne
    @JoinColumn(name="ID_FLAG", referencedColumnName = "ID")
    private MuiFlagEntity flag;

    @Column(name="VALORE", length = 50)
    private String valore;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO", nullable = false)
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

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
