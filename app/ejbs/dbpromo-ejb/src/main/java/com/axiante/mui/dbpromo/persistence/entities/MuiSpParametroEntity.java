package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MUI_SP_PARAMETRO", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(
                name = "MuiSpParametroEntity.findByPromozioneId",
                query = "SELECT e FROM MuiSpParametroEntity e " +
                        "WHERE e.idPromozione = :idPromozione " +
                        "ORDER BY e.idCompratore ASC, e.idFornitore ASC"
        ),
        @NamedQuery(
                name = "MuiSpParametroEntity.findByPromozioneIdAndCompratoreAndFornitore",
                query = "SELECT e FROM MuiSpParametroEntity e " +
                        "WHERE e.idPromozione = :idPromozione " +
                        "AND e.idCompratore = :idCompratore " +
                        "AND e.idFornitore = :idFornitore"
        )
})
public class MuiSpParametroEntity implements Serializable, DbPromoEntityInterface, AuditLogInterface {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_SP_PARAMETRO_ID_GENERATOR",
            sequenceName = "MUI_SP_PARAMETRO_ID_SEQ"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_SP_PARAMETRO_ID_GENERATOR")
    @Column(name = "ID", unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "ID_PROMOZIONE", nullable = false, precision = 16)
    private Long idPromozione;

    @Column(name = "ID_COMPRATORE", nullable = false, precision = 16)
    private Long idCompratore;

    @Transient
    private String descrizioneCompratore;

    @Transient
    private String codiceCompratore;

    @Transient
    private String descrizioneFornitore;

    @Transient
    private String codiceFornitore;

    @Column(name = "ID_FORNITORE", nullable = false, precision = 16)
    private Long idFornitore;

    @Column(name = "TIPO_PREMIO", length = 100)
    private String tipoPremio;

    @Column(name = "PREMIO_CF", precision = 10, scale = 2)
    private BigDecimal premioCf;

    @Column(name = "PREMIO_PERC", precision = 5, scale = 2)
    private BigDecimal premioPerc;

    @Column(name = "TIPO_BASE_IMP", length = 100)
    private String tipoBaseImp;

    @Column(name = "SOGLIA_MAX", precision = 10, scale = 2)
    private BigDecimal sogliaMax;

    @Column(name = "MOD_LIQ", length = 10)
    private String modLiq;

    @Column(name = "\"check\"", length = 4000)
    private String checkValue;

    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
    private String codiceUtenteInserimento;

    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 50)
    private String codiceUtenteAggiornamento;

    @Column(name = "UUID", length = 32)
    private String uuid;

    @Column(name = "premio_finale_vend")
    private BigDecimal premioFinaleVend;

    @Column(name = "premio_finale_cons")
    private BigDecimal premioFinaleCons;
}