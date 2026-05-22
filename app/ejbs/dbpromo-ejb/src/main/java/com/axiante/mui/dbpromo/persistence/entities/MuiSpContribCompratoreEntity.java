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
@NoArgsConstructor
@Table(name = "MUI_SP_CONTRIB_COMPRATORE", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(
                name = "MuiSpContribCompratoreEntity.findByPromozioneId",
                query = "SELECT c FROM MuiSpContribCompratoreEntity c " +
                        "JOIN FETCH c.compratoreEntity compratore " +
                        "WHERE c.spTestataEntity.id = :idPromozione " +
                        "ORDER BY compratore.codiceCompratore ASC"
        ),
        @NamedQuery(
                name = "MuiSpContribCompratoreEntity.findByPromozioneIdAndCompratoreId",
                query = "SELECT c FROM MuiSpContribCompratoreEntity c " +
                        "WHERE c.spTestataEntity.id = :idPromozione " +
                        "AND c.compratoreEntity.id = :idCompratore"
        )
})
public class MuiSpContribCompratoreEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "MUI_SP_CONTRIB_COMPR_ID_GENERATOR",
            sequenceName = "MUI_SP_CONTR_COMPR_ID_SEQ",
            schema = Metadata.SCHEMA,
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MUI_SP_CONTRIB_COMPR_ID_GENERATOR")
    @Column(name = "ID", nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE", nullable = false)
    private MuiSpTestataEntity spTestataEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPRATORE", nullable = false)
    private CompratoreEntity compratoreEntity;

    @Column(name = "FL_CONTRIBUZIONE", nullable = false, precision = 1)
    private Integer flContribuzione;
}