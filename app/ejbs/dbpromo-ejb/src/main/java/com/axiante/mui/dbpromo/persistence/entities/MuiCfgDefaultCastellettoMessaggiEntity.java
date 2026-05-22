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

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_DEFAULT_CAST_MSG", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(
                name = "MuiCfgDefaultCastellettoMessaggiEntity.findByCodiceCanaleAndCodiceMeccanica",
                query = "Select m from MuiCfgDefaultCastellettoMessaggiEntity m where m.codiceCanale = :codiceCanale and m.codiceMeccanica = :codiceMeccanica "
        ),
        @NamedQuery(
                name = "MuiCfgDefaultCastellettoMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo",
                query = "Select m from MuiCfgDefaultCastellettoMessaggiEntity m where m.codiceCanale = :codiceCanale and m.codiceMeccanica = :codiceMeccanica and m.codiceDispositivo = :codiceDispositivo"
        ),
        @NamedQuery(
                name = "MuiCfgDefaultCastellettoMessaggiEntity.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo",
                query = "Select count(m) from MuiCfgDefaultCastellettoMessaggiEntity m where m.codiceCanale = :codiceCanale and m.codiceMeccanica = :codiceMeccanica and m.codiceDispositivo = :codiceDispositivo"
        ),
        @NamedQuery(
                name = "MuiCfgDefaultCastellettoMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoIn",
                query = "Select m from MuiCfgDefaultCastellettoMessaggiEntity m where m.codiceCanale = :codiceCanale and m.codiceMeccanica = :codiceMeccanica and m.codiceDispositivo in :codiceDispositivo"
        ),
        @NamedQuery(
                name = "MuiCfgDefaultCastellettoMessaggiEntity.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo",
                query = "Select count(m) from MuiCfgDefaultCastellettoMessaggiEntity m where m.codiceCanale = :codiceCanale and m.codiceMeccanica = :codiceMeccanica and m.codiceDispositivo = :codiceDispositivo"
        ),
        @NamedQuery(
                name = "MuiCfgDefaultCastellettoMessaggiEntity.findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo",
                query = "Select max(m.seqStampa) from MuiCfgDefaultCastellettoMessaggiEntity m where m.codiceCanale = :codiceCanale and m.codiceMeccanica = :codiceMeccanica and m.codiceDispositivo = :codiceDispositivo"
        ),
        @NamedQuery(
                name = "MuiCfgDefaultCastellettoMessaggiEntity.findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan",
                query = "SELECT c FROM MuiCfgDefaultCastellettoMessaggiEntity c where c.sezione = com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum.MESSAGGI and c.codiceCanale = :codiceCanale and c.codiceMeccanica = :codiceMeccanica and c.codiceDispositivo = :codiceDispositivo and c.seqStampa >= :seqStampa order by c.seqStampa desc"
        )
})
@NoArgsConstructor
public class MuiCfgDefaultCastellettoMessaggiEntity implements Serializable, DbPromoEntityInterface, AuditLogInterface {
    private static final long serialVersionUID = -3290206985507096985L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_DEFAULT_CASTMSG_ID_GENERATOR", sequenceName = "MUI_CFG_DEFAULT_CASTMSG_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_DEFAULT_CASTMSG_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "CODICE_CANALE", nullable = false, precision = 3)
    private Long codiceCanale;

    @Column(name = "CODICE_MECCANICA", nullable = false, length = 20)
    private String codiceMeccanica;

    @Column(name = "CODICE_DISPOSITIVO", length = 50, nullable = false)
    String codiceDispositivo;

    @Column(name = "SEQ_STAMPA", precision = 3, nullable = false)
    private Short seqStampa;

    @Column(name = "SEZIONE", length = 10)
    @Enumerated(javax.persistence.EnumType.STRING)
    private MessaggiSezioneEnum sezione;

    @Column(name = "TESTO", length = 4000)
    private String testo;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "TAGLIO_CARTA")
    private Boolean taglioCarta;

    @Column(name = "BARCODE")
    @Convert(converter = BooleanConverter.class)
    private Boolean barcode;

    @Column(name = "FONT", length = 10)
    @Convert(converter = MessaggiFontEnumConverter.class)
    private MessaggiFontEnum font;

    @Column(name = "ALLINEAMENTO", length = 10)
    @Convert(converter = MessaggiAllineamentoEnumConverter.class)
    private MessaggiAllineamentoEnum allineamento;

    @Column(name = "BOLD")
    @Convert(converter = BooleanConverter.class)
    private Boolean bold;

    @Column(name = "LOGO", length = 100)
    private String logo;

    @ManyToOne
    @JoinColumn(name = "BOTTONE", referencedColumnName = "DESCRIZIONE_BOTTONE")
    private MuiBottoneEntity bottone;

    @Column(name = "CODICE")
    @Convert(converter = BooleanConverter.class)
    private Boolean codice;

    @Column(name = "REGOLAMENTO", length = 40)
    private String regolamento;

    @Column(name = "BARRA")
    @Convert(converter = BooleanConverter.class)
    private Boolean barra;

    @ManyToOne
    @JoinColumn(name = "FONT_STILE", referencedColumnName = "ID_FONT_STILE")
    private MuiFontStileEntity fontStile;

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

    @Column(name="ID_MESSAGGIO")
    private Integer idMessaggio;

    @Column(name="VARIABILE")
    private String variabile;

    @ManyToOne
    @JoinColumn(name = "FONT_ENTITIES", referencedColumnName = "ID_FONT_ENTITIES")
    private MuiFontEntities fontEntity;
}
