package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.business.MessaggiComponentsEnumConverter;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "MUI_CFG_COMPONENT_CASTMSG")
@NamedQueries({
        @NamedQuery(
                name = "MuiCfgCastellettoMessaggiComponentEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo",
                query = "Select m from MuiCfgCastellettoMessaggiComponentEntity m where " +
                        "m.codiceCanale = :codiceCanale and " +
                        "m.codiceMeccanica = :codiceMeccanica and " +
                        "m.codiceDispositivo = :codiceDispositivo"
        ),
        @NamedQuery(
                name = "MuiCfgCastellettoMessaggiComponentEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent",
                query = "Select m from MuiCfgCastellettoMessaggiComponentEntity m where " +
                        "m.codiceCanale = :codiceCanale and " +
                        "m.codiceMeccanica = :codiceMeccanica and " +
                        "m.codiceDispositivo = :codiceDispositivo and " +
                        "m.component = :component"
        ),
        @NamedQuery(name = "MuiCfgCastellettoMessaggiComponentEntity.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent",
                query = "SELECT COUNT(m) from MuiCfgCastellettoMessaggiComponentEntity m where " +
                        "m.codiceCanale = :codiceCanale and " +
                        "m.codiceMeccanica = :codiceMeccanica and " +
                        "m.codiceDispositivo = :codiceDispositivo and " +
                        "m.component = :component"),


})
public class MuiCfgCastellettoMessaggiComponentEntity implements Serializable, DbPromoEntityInterface, AuditLogInterface {
    private static final long serialVersionUID = 8214500460315315548L;
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_CASTMSG_COMPONENT_ID_GENERATOR", sequenceName = "MUI_CFG_COMPON_CASTMSG_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_CASTMSG_COMPONENT_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;
    @Column(name = "CODICE_CANALE", nullable = false, precision = 3)
    private Long codiceCanale;
    @Column(name = "CODICE_MECCANICA", nullable = false, length = 20)
    private String codiceMeccanica;
    @Column(name = "CODICE_DISPOSITIVO", length = 50, nullable = false)
    String codiceDispositivo;
    @Column(name = "COMPONENT")
    @Convert(converter = MessaggiComponentsEnumConverter.class)
    private MessaggiComponentsEnum component;
    @Column(name = "ENABLED")
    @Convert(converter = BooleanConverter.class)
    Boolean enabled;
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

    @Column(name = "unica_in_riga", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private Boolean unicaInRiga = false;
    @Column(name = "testo")
    private String testo ;
}
