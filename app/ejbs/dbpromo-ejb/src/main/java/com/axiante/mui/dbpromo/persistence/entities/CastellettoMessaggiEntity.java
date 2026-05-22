package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.business.MessaggiAllineamentoEnumConverter;
import com.axiante.mui.business.MessaggiFontEnumConverter;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MUI_CASTELLETTO_MESSAGGI", schema = Metadata.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "CastellettoMessaggiEntity.findByIdPianificazione",
                query = "SELECT c FROM CastellettoMessaggiEntity c where c.pianificazione.id = :idPianificazione order by c.idMessaggio asc, c.seqStampa asc"),
        @NamedQuery(name = "CastellettoMessaggiEntity.findMessaggiByIdPianificazione",
                query = "SELECT c FROM CastellettoMessaggiEntity c where c.pianificazione.id = :idPianificazione and c.sezione = com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum.MESSAGGI order by c.idMessaggio asc, c.seqStampa asc"),
        @NamedQuery(name = "CastellettoMessaggiEntity.findMessaggiByIdPianificazioneAndCodiceDispositivo",
                query = "SELECT c FROM CastellettoMessaggiEntity c where c.pianificazione.id = :idPianificazione and c.sezione = com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum.MESSAGGI and c.codiceCanaleDispositivo = :codiceCanaleDispositivo order by c.idMessaggio asc, c.seqStampa asc"),
        @NamedQuery(name = "CastellettoMessaggiEntity.findCastellettiByIdPianificazione",
                query = "SELECT c FROM CastellettoMessaggiEntity c where c.pianificazione.id = :idPianificazione and c.sezione <> com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum.MESSAGGI order by c.idMessaggio asc, c.seqStampa asc"),
        @NamedQuery(name = "CastellettoMessaggiEntity.removeByIds",
                query = "DELETE FROM CastellettoMessaggiEntity c where c.id in :idCastelletti"),
        @NamedQuery(name = "CastellettoMessaggiEntity.findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan",
                query = "SELECT c FROM CastellettoMessaggiEntity c where c.pianificazione.id = :idPianificazione and c.sezione = com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum.MESSAGGI and c.codiceCanaleDispositivo = :codiceCanaleDispositivo and c.seqStampa > :seqStampa order by c.idMessaggio asc, c.seqStampa asc"),
})
public class CastellettoMessaggiEntity
        implements Serializable, DbPromoEntityInterface, AuditLogInterface {
    private static final long serialVersionUID = -6357168998481070208L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_CASTELLETTO_MESSAGGI_ID_GENERATOR",
            sequenceName = "MUI_CAST_MSG_ID_SEQ")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MUI_CASTELLETTO_MESSAGGI_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PIANIFICAZIONE", nullable = false)
    private PromozionePianificazioneEntity pianificazione;

    @Column(name = "SEQ_STAMPA")
    private Integer seqStampa;

    @Column(name = "SEZIONE")
    @Enumerated(javax.persistence.EnumType.STRING)
    private MessaggiSezioneEnum sezione;

    @Column(name = "TESTO", length = 4000)
    private String testo;

    @Column(name = "TAGLIO_CARTA")
    @Convert(converter = BooleanConverter.class)
    private Boolean taglioCarta = Boolean.FALSE;

    @Column(name = "BARCODE")
    @Convert(converter = BooleanConverter.class)
    private Boolean barcode = Boolean.FALSE;

    @Column(name = "FONT")
    @Convert(converter = MessaggiFontEnumConverter.class)
    private MessaggiFontEnum font;

    @Column(name = "ALLINEAMENTO")
    @Convert(converter = MessaggiAllineamentoEnumConverter.class)
    private MessaggiAllineamentoEnum allineamento;

    @Column(name = "BOLD")
    @Convert(converter = BooleanConverter.class)
    private Boolean bold = Boolean.FALSE;

    @Column(name = "LOGO")
    private String logo;

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

    @Column(name = "CODICE_CANALE_DISPOSITIVO", length = 50, nullable = true)
    private String codiceCanaleDispositivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FONT_STILE")
    private MuiFontStileEntity fontStile;

    @ManyToOne
    @JoinColumn(name = "BOTTONE", referencedColumnName = "DESCRIZIONE_BOTTONE")
    private MuiBottoneEntity bottone;

    @Column(name = "CODICE")
    @Convert(converter = BooleanConverter.class)
    private Boolean codice = Boolean.FALSE;

    @Column(name = "REGOLAMENTO", length = 40)
    private String regolamento;

    @Column(name = "BARRA")
    @Convert(converter = BooleanConverter.class)
    private Boolean barra = Boolean.FALSE;

    @Column(name = "ID_MESSAGGIO")
    private Integer idMessaggio;

    @Column(name = "VARIABILE", length = 20)
    private String variabile;

    @Column(name = "FONT_ENTITIES")
    private String fontEntity;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CastellettoMessaggiEntity)) {
            return false;
        }
        CastellettoMessaggiEntity castOther = (CastellettoMessaggiEntity) other;
        if (this.id == null && castOther.id == null) {
            return this.hashCode() == castOther.hashCode();
        } else {
            return Objects.equals(this.id, castOther.id);
        }
    }

    public int hashCode() {
        return Objects.hash(
                id,
                pianificazione,
                seqStampa,
                sezione,
                testo,
                taglioCarta,
                barcode,
                font,
                allineamento,
                bold,
                logo);
    }

    @Override
    public String toString() {
        return "{\n"
                + "  \"id\": "
                + id
                + ",\n"
                + "  \"pianificazione\": "
                + (pianificazione != null ? pianificazione.getId() : null)
                + ",\n"
                + "  \"seqStampa\": "
                + seqStampa
                + ",\n"
                + "  \"sezione\": \""
                + sezione
                + "\",\n"
                + "  \"testo\": \""
                + testo
                + "\",\n"
                + "  \"taglioCarta\": "
                + taglioCarta
                + ",\n"
                + "  \"barcode\": "
                + barcode
                + ",\n"
                + "  \"font\": \""
                + font
                + "\",\n"
                + "  \"allineamento\": \""
                + allineamento
                + "\",\n"
                + "  \"bold\": "
                + bold
                + ",\n"
                + "  \"logo\": \""
                + logo
                + "\",\n"
                + "  \"codiceUtenteAggiornamento\": \""
                + codiceUtenteAggiornamento
                + "\",\n"
                + "  \"codiceUtenteInserimento\": \""
                + codiceUtenteInserimento
                + "\",\n"
                + "  \"dataAggiornamento\": \""
                + dataAggiornamento
                + "\",\n"
                + "  \"dataInserimento\": \""
                + dataInserimento
                + "\"\n"
                + "}";
    }
}
