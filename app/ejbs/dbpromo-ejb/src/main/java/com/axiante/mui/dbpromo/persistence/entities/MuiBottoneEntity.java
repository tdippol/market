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

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "V_MUI_BOTTONE", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "MuiBottoneEntity.findAll", query = "SELECT e FROM MuiBottoneEntity e ORDER BY e.descrizione"),
        @NamedQuery(name = "MuiBottoneEntity.countById", query = "SELECT COUNT(e) FROM MuiBottoneEntity e WHERE e.id = :id"),
        @NamedQuery(name = "MuiBottoneEntity.findById", query = "SELECT e FROM MuiBottoneEntity e WHERE e.id = :id")
})
public class MuiBottoneEntity {
    @Id
    @Column(name = "ID_BOTTONE", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DESCRIZIONE_BOTTONE", length = 40, nullable = false)
    private String descrizione;
}
