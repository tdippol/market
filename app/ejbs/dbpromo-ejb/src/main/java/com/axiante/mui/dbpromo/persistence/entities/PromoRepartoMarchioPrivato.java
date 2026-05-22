package com.axiante.mui.dbpromo.persistence.entities;


import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MUI_PROMO_REPARTO_MARCHIO_P", schema = Metadata.SCHEMA)
@NamedQueries({
    @NamedQuery(name = "PromoRepartoMarchioPrivato.findByIdPromozione", query = "SELECT p FROM PromoRepartoMarchioPrivato p WHERE p.promozione.id = :promozione"),
})
public class PromoRepartoMarchioPrivato implements Serializable, AuditLogInterface, UUIDEnabledEntity, DbPromoEntityInterface {
    private static final long serialVersionUID = 9193112440792753283L;

    @Id
    @Column(unique = true, nullable = false, precision = 16, name = "ID")
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMO_REPARTO_MARCHIO_P_ID_GENERATOR",
            sequenceName = "MUI_PROMO_REP_MARCHIO_P_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMO_REPARTO_MARCHIO_P_ID_GENERATOR")
    Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    PromozioneTestataEntity promozione;

    @ManyToOne
    @JoinColumn(name = "ID_REPARTO", nullable = false)
    RepartoEntity reparto;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "ATTIVO")
    private boolean attivo = false;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PromoRepartoMarchioPrivato that = (PromoRepartoMarchioPrivato) o;
        return getUuid().equals(that.getUuid());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getUuid());
    }
}
