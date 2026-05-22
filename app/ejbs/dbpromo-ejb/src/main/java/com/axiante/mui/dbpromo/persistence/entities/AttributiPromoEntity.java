package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "MUI_ATTRIBUTI_PROMO", schema = Metadata.SCHEMA,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"PARAMETRO"}, name = "MUI_ATTRIBUTI_PROMO_UK1")
        }
)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class AttributiPromoEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 4669972463453824566L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_ATTRIBUTI_PROMO_ID_GENERATOR", sequenceName = "MUI_ATTRIBUTI_PROMO_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_ATTRIBUTI_PROMO_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "PARAMETRO", length = 20, nullable = false)
    private String parametro;

    @Column(name = "DES_PARAMETRO", length = 50, nullable = false)
    private String descrizioneParametro;

    @Column(name = "CLASSE_PARAMETRO", length = 30)
    private String classeParametro;

    @Column(name = "DES_ESTESA", length = 120)
    private String descrizioneEstesa;

    @OneToMany(mappedBy = "attributo", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<CfgCanaliAttributiEntity> canaliAttributi;

    @OneToMany(mappedBy = "attributo")
    private Set<PromozioneAttributiEntity> promozioneAttributi;
}
