package com.axiante.mui.dbpromo.persistence.entities;


import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.TipoInclusioneEsclusione;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.SQLDataException;
import java.util.Date;

@Entity
@Table(name = "MUI_INCL_ESCL", schema = Metadata.SCHEMA)
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "MuiInclusioneEsclusioneEntity.findByPromozione",
                query = "SELECT mie FROM MuiInclusioneEsclusioneEntity mie WHERE mie.promozione.id = :idPromozione"),
        @NamedQuery(name = "MuiInclusioneEsclusioneEntity.findByPromozioneAndTipo",
                query = "SELECT mie FROM MuiInclusioneEsclusioneEntity mie WHERE mie.promozione.id = :idPromozione AND mie.tipo = :tipo"),
        @NamedQuery(name = "MuiInclusioneEsclusioneEntity.countByPromozioneAndMeccanicaAndTipo",
                query = "SELECT COUNT(mie) FROM MuiInclusioneEsclusioneEntity mie WHERE mie.promozione.id = :idPromozione AND mie.meccanicaEntity.id = :idMeccanica AND mie.tipo = :tipo"),
        @NamedQuery(name = "MuiInclusioneEsclusioneEntity.countByPromozioneAndMeccanicaAndTipoAndTipoElemento",
                query = "SELECT COUNT(mie) FROM MuiInclusioneEsclusioneEntity mie WHERE mie.promozione.id = :idPromozione AND mie.meccanicaEntity.id = :idMeccanica AND mie.tipo = :tipo AND mie.tipoElemento = :tipoElemento")
})
public class MuiInclusioneEsclusioneEntity implements Serializable, AuditLogInterface {
    private static final long serialVersionUID = -8286384424690680814L;
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_INCL_ESCL_ID_GENERATOR", sequenceName = "MUI_INCL_ESCL_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_INCL_ESCL_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private PromozioneTestataEntity promozione;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", length = 20, nullable = false)
    private TipoInclusioneEsclusione tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ELEMENTO", length = 20, nullable = false)
    private ElementType tipoElemento;

    @Column(name = "CODICE_ELEMENTO", length = 100, nullable = false)
    private String codiceElemento;

    @Column(name = "ELEMENTO", length = 100)
    private String elemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MECCANICA", nullable = false)
    private MeccanicheEntity meccanicaEntity;

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

    public void setTipoElemento(ElementType elemento) throws SQLDataException {
        if (elemento != null && ( elemento.equals(ElementType.ATTRIBUTO)  || elemento.equals(ElementType.TOTALE))) {
            throw new SQLDataException("Non e' possibile impostare il tipo ATTRIBUTO in MuiInclusioneEsclusioneEntity");
        }
        this.tipoElemento = elemento;
    }

}
