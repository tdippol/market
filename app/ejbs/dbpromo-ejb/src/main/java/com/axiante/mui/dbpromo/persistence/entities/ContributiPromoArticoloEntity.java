package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CONTRIBUTI_PROMO_ART", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "ContributiPromoArticoloEntity.findAllByIds",
                query = "SELECT e FROM ContributiPromoArticoloEntity e WHERE e.id IN :ids"),
        @NamedQuery(name = "ContributiPromoArticoloEntity.findAllByIdRata",
                query = "SELECT e FROM ContributiPromoArticoloEntity e WHERE e.contributiPromo.id = :idRata")
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContributiPromoArticoloEntity implements Serializable {
    private static final long serialVersionUID = 7327169615434130292L;

    @Id
    @SequenceGenerator(schema= Metadata.SCHEMA, allocationSize = 1, name = "MUI_CONTRIBUTI_PROMO_ART_ID_GENERATOR", sequenceName = "MUI_CONTRIBUTI_PROMO_ART_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CONTRIBUTI_PROMO_ART_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONTRIBUTI_PROMO", nullable = false)
    ContributiPromoEntity contributiPromo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ITEM", nullable = false)
    @EqualsAndHashCode.Include
    ItemEntity item;

    @Column(name = "WARN_FLAG")
    @Convert(converter = BooleanConverter.class)
    Boolean warn;

}
