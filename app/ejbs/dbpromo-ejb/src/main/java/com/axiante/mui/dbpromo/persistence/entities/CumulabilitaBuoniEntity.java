package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MUI_CUMUL_ESCLUS_BUONI", schema = Metadata.SCHEMA)
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "CumulabilitaBuoniEntity.findByCodicePromozioneAndCodiceCanaleAndPrefissoBs", query = "SELECT c FROM CumulabilitaBuoniEntity c where c.codicePromozione = :codicePromozione and c.codiceCanale = :codiceCanale and c.prefissoBS = :prefissoBS"),
})
public class CumulabilitaBuoniEntity implements DbPromoEntityInterface, AuditLogInterface {
    @Id
    @Column(name = "ID", nullable = false, precision = 16)
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CUMULABILITA_BUONI_ID_GENERATOR", sequenceName = "MUI_CUMUL_ESCLUS_BUONI_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CUMULABILITA_BUONI_ID_GENERATOR")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CUMUL_ESCLUS", referencedColumnName = "ID", nullable = false)
    private CumulabilitaEsclusivitaEntity cumulabilitaEsclusivitaEntity;

    @Column(name = "PREFISSO_BS", length = 5, nullable = false)
    private String prefissoBS;

    @Column(name = "CODICE_CANALE", length = 3, nullable = false)
    private String codiceCanale;

    @Column(name = "CODICE_PROMOZIONE", length = 10, nullable = false)
    private String codicePromozione;

    @Column(name = "DESCRIZIONE_PROMOZIONE", length = 100, nullable = false)
    private String descrizionePromozione;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INIZIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FINE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataFine;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 10, nullable = false)
    private String codiceUtenteInserimento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 10)
    private String codiceUtenteAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO", nullable = false)
    private Date dataInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Column(name = "BUONO_DEFAULT")
    @Convert(converter = BooleanConverter.class)
    Boolean buonoDefault = false;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CumulabilitaBuoniEntity)) return false;
//        CumulabilitaBuoniEntity that = (CumulabilitaBuoniEntity) o;
//        boolean canEqual = canEqual(that);
//        if (canEqual) {
//            if (getId() != null) {
//                return getId().equals(that.getId());
//            } else {
//                if (that.getId() == null) {
//                    if (getCumulabilitaEsclusivitaEntity() != null) {
//                        return getCumulabilitaEsclusivitaEntity().equals(that.getCumulabilitaEsclusivitaEntity());
//                    } else {
//                        return that.getCumulabilitaEsclusivitaEntity() == null;
//                    }
//                }
//            }
//        }
//        return false;
//
//    }
//
//    private boolean canEqual(CumulabilitaBuoniEntity that) {
//        return that != null && getCodicePromozione().equals(that.getCodicePromozione()) && getPrefissoBS().equals(that.prefissoBS);
//    }
//
//    @Override
//    public int hashCode() {
//        return Arrays.hashCode(new Object[]{codicePromozione, prefissoBS, cumulabilitaEsclusivitaEntity != null? cumulabilitaEsclusivitaEntity.getId():null});
//    }


    @Transient
    public String getChiave() {
        return String.format("%s|%s", codicePromozione, prefissoBS);
    }
}
