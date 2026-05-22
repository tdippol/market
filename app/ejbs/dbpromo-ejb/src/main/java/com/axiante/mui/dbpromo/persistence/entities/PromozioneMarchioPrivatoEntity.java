package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
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
@Table(name = "MUI_PROMOZIONE_MARCHIO_PRIV", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "PromozioneMarchioPrivatoEntity.findByIdPromozione",
                query = "SELECT c FROM PromozioneMarchioPrivatoEntity c WHERE c.promozione.id = :promozione"),
})
public class PromozioneMarchioPrivatoEntity implements Serializable, AuditLogInterface {
    private static final long serialVersionUID = 8576841662000141832L;

    @Id
    @Column(unique = true, nullable = false, precision = 16, name = "ID")
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PROMO_MARCHIO_PRIV_ID_GENERATOR",
            sequenceName = "MUI_PROMO_MARCHIO_PRIV_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PROMO_MARCHIO_PRIV_ID_GENERATOR")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private PromozioneTestataEntity promozione;

    @ManyToOne
    @JoinColumn(name = "ID_MARCHIO_PRIVATO", nullable = false)
    private MarchioPrivatoEntity marchioPrivato;

    @Column(name = "ATTIVO")
    @Convert(converter = BooleanConverter.class)
    boolean attivo;

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

    public boolean equals(Object o){
        if (o == null) return false;
        if (!(o instanceof PromozioneMarchioPrivatoEntity)) return false;
        PromozioneMarchioPrivatoEntity other = (PromozioneMarchioPrivatoEntity) o;
        return this.id.equals(other.id);
    }

    public String getKey(){
        return marchioPrivato.getCodice();
    }
    public String getLabel(){
        return marchioPrivato.getDescrizione();
    }

    public int hashCode(){
        return Objects.hash(id, promozione, marchioPrivato, attivo);
    }
}
