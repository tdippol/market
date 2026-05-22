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
import javax.persistence.NamedQueries;
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
@Table(name = "MUI_PROMOZIONE_ATTRIBUTI", schema = Metadata.SCHEMA)
@NamedQueries({
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class PromozioneAttributiEntity implements Serializable, AuditLogInterface, UUIDEnabledEntity{
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMOZIONE_ATTRIBUTI_ID_GENERATOR", sequenceName = "MUI_PROMOZIONE_ATTR_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMOZIONE_ATTRIBUTI_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private PromozioneTestataEntity promozioneTestataEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ATTRIBUTO", nullable = false)
    private AttributiPromoEntity attributo;

    @Column(name="VALORE", length = 50)
    String valore;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
    private String codiceUtenteAggiornamento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

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
