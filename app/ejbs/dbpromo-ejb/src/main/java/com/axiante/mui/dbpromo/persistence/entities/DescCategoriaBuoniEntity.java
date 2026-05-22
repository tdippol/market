package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "MUI_DESC_CATEGORIA_BUONI", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "DescCategoriaBuoniEntity.countByIdPromozione",
                query = "select count(e) from DescCategoriaBuoniEntity e where e.idPromozione = :idPromozione"),
        @NamedQuery(name = "DescCategoriaBuoniEntity.findByIdPromozione",
                query = "select e from DescCategoriaBuoniEntity e where e.idPromozione = :idPromozione")
})
public class DescCategoriaBuoniEntity implements Serializable {
    private static final long serialVersionUID = -9079118823208457449L;

    @Id
    @Column(name = "ID_PROMOZIONE", unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long idPromozione;

    @Column(name = "CAT_MINUSC", length = 400)
    private String catMinusc;

    @Column(name = "CAT_MAIUSC", length = 400)
    private String catMaiusc;

    @Column(name = "CAT_DESC_ESTESA", length = 400)
    private String catDescEstesa;
}
