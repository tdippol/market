package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MUI_SP_REPARTO", schema = Metadata.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@IdClass(MuiSpRepartoEntity.Pk.class)
@NamedQueries({
        @NamedQuery(
                name = "MuiSpRepartoEntity.findByPromozioneId",
                query = "SELECT DISTINCT r FROM MuiSpRepartoEntity r " +
                        "LEFT JOIN FETCH r.repartoEntity rep " +
                        "WHERE r.idPromozione = :idPromozione " +
                        "ORDER BY rep.codiceReparto"
        ),
        @NamedQuery(
                name = "MuiSpRepartoEntity.findByPromozioneIdAndRepartoId",
                query = "SELECT r FROM MuiSpRepartoEntity r " +
                        "WHERE r.idPromozione = :idPromozione " +
                        "AND r.idReparto = :idReparto"
        )
})
public class MuiSpRepartoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID_PROMOZIONE", nullable = false, precision = 16)
    private Long idPromozione;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID_REPARTO", nullable = false, precision = 16)
    private Long idReparto;

    @Column(name = "ATTIVO", nullable = false, precision = 1)
    private Integer attivo;

    @Transient
    private boolean espanso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", referencedColumnName = "ID", insertable = false, updatable = false)
    private MuiSpTestataEntity spTestataEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REPARTO", referencedColumnName = "ID", insertable = false, updatable = false)
    private RepartoEntity repartoEntity;

    @Getter
    @Setter
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long idPromozione;
        private Long idReparto;
    }
}