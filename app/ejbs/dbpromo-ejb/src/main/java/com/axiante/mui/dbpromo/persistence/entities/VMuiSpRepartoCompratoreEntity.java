package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "V_MUI_SP_REPARTO_COMPRATORE", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@IdClass(VMuiSpRepartoCompratoreEntity.Pk.class)
@NamedQuery(
        name = "VMuiSpRepartoCompratoreEntity.findAllOrdered",
        query = "SELECT v FROM VMuiSpRepartoCompratoreEntity v " +
                "JOIN FETCH v.repartoEntity r " +
                "JOIN FETCH v.compratoreEntity c " +
                "ORDER BY r.codiceReparto ASC, c.codiceCompratore ASC"
)
public class VMuiSpRepartoCompratoreEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "\"ID_REPARTO\"", nullable = false, precision = 16)
    private Long idReparto;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "\"ID_COMPRATORE\"", nullable = false, precision = 16)
    private Long idCompratore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"ID_REPARTO\"", referencedColumnName = "ID", insertable = false, updatable = false)
    private RepartoEntity repartoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"ID_COMPRATORE\"", referencedColumnName = "ID", insertable = false, updatable = false)
    private CompratoreEntity compratoreEntity;

    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long idReparto;
        private Long idCompratore;
    }
}