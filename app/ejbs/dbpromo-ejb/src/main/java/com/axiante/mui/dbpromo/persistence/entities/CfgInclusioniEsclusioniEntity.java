package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_INCL_ESCL", schema = Metadata.SCHEMA)
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "CfgInclusioniEsclusioniEntity.findByMeccanicaAndTipoElemento",
                query = "SELECT c FROM CfgInclusioniEsclusioniEntity c WHERE c.meccanicaEntity.id = :idMeccanica AND c.tipoElemento = :tipoElemento"),
        @NamedQuery(name = "CfgInclusioniEsclusioniEntity.findByMeccanica",
                query = "SELECT c FROM CfgInclusioniEsclusioniEntity c WHERE c.meccanicaEntity.id = :idMeccanica"),
        @NamedQuery(name = "CfgInclusioniEsclusioniEntity.findAllIdMeccanica",
                query = "SELECT c.meccanicaEntity.id FROM CfgInclusioniEsclusioniEntity c")
})
public class CfgInclusioniEsclusioniEntity implements Serializable, DbPromoEntityInterface, AuditLogInterface {
    private static final long serialVersionUID = 8262131891485840004L;
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_INCL_ESCL_ID_GENERATOR",
            sequenceName = "MUI_CFG_INCL_ESCL_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_INCL_ESCL_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MECCANICA", nullable = false)
    private MeccanicheEntity meccanicaEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ELEMENTO", length = 20, nullable = false)
    private ElementType tipoElemento;

    @Column(name = "INCLUSIONI")
    private Long inclusioni;

    @Column(name = "ESCLUSIONI")
    Long esclusioni;

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
}
