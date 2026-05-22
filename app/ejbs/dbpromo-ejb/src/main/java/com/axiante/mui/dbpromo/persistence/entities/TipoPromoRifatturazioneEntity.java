package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "V_MUI_TIPO_PROMO_RIFATT", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "TipoPromoRifatturazioneEntity.findAll",
                query = "select e from TipoPromoRifatturazioneEntity e order by e.descrizione")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
public class TipoPromoRifatturazioneEntity implements Serializable {
    private static final long serialVersionUID = 948877928724346587L;

    @Id
    @Column(name = "CODICE_TIPO_PROMO", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private Integer codice;

    @Column(name = "DESCRIZIONE", nullable = false)
    private String descrizione;
}
