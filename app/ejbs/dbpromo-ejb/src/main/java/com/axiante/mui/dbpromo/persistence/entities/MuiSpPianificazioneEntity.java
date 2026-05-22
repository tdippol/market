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
@Table(name = "MUI_SP_PIANIFICAZIONE", schema = Metadata.SCHEMA,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id_promozione", "id_item", "id_fornitore", "tipo"})
        })
@NamedQueries({
        @NamedQuery(
                name = "MuiSpPianificazioneEntity.findByPromozioneAndTipo",
                query = "SELECT e FROM MuiSpPianificazioneEntity e " +
                        "WHERE e.idPromozione = :idPromozione " +
                        "AND e.tipo = :tipo"
        ),
        @NamedQuery(
                name = "MuiSpPianificazioneEntity.findByUniqueKey",
                query = "SELECT e FROM MuiSpPianificazioneEntity e " +
                        "WHERE e.idPromozione = :idPromozione " +
                        "AND e.idItem = :idItem " +
                        "AND e.idFornitore = :idFornitore " +
                        "AND e.tipo = :tipo"
        )
})
public class MuiSpPianificazioneEntity implements Serializable, DbPromoEntityInterface, AuditLogInterface {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            schema = Metadata.SCHEMA,
            allocationSize = 1,
            name = "MUI_SP_PIANIFICAZIONE_ID_GENERATOR",
            sequenceName = "MUI_SP_PIANIFICAZIONE_ID_SEQ"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_SP_PIANIFICAZIONE_ID_GENERATOR")
    @Column(name = "ID", unique = true, nullable = false, precision = 16)
    private Long id;


    @Column(name = "ID_PROMOZIONE", nullable = false)
    private Long idPromozione;

    @Column(name = "ID_ITEM", nullable = false)
    private Long idItem;

    @Column(name = "ID_FORNITORE", nullable = false)
    private Long idFornitore;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "MARCHIO_PRIVATO")
    private String marchioPrivato;

    @Column(name = "PRIMO_PREZZO")
    private BigDecimal primoPrezzo;

    @Column(name = "PEZZI_TOT")
    private BigDecimal pezziTot;

    @Column(name = "PEZZI_NOPROMO")
    private BigDecimal pezziNopromo;

    @Column(name = "VEND_TOT")
    private BigDecimal vendTot;

    @Column(name = "VEND_NOPROMO")
    private BigDecimal vendNopromo;

    @Column(name = "VEND_COST_TOT")
    private BigDecimal vendCostTot;

    @Column(name = "VEND_COST_NOPROMO")
    private BigDecimal vendCostNopromo;

    @Column(name = "INCIDENZA")
    private BigDecimal incidenza;

    @Column(name = "ESC")
    private Integer esc;

    @Column(name = "ESC_PEZZI_KG")
    private Integer escPezziKg;

    @Column(name = "PREMIO_PERC_U")
    private BigDecimal premioPercU;

    @Column(name = "PREMIO_FINALE")
    private BigDecimal premioFinale;

    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
    private String codiceUtenteAggiornamento;

    @Column(name = "UUID")
    private String uuid;

    @Transient
    private String descrizioneFornitore;

    @Transient
    private String descrizioneCompratore;

    @Transient
    private String codiceCompratore;

    @Transient
    private String descrizioneItem;

    @Transient
    private Long idCompratore;

    @Transient
    private BigDecimal premioCf;

    @Transient
    private BigDecimal premioPerc;

    @Transient
    private String tipoBaseImp;

    @Transient
    private BigDecimal sogliaMax;

    @Transient
    private String modLiq;
}