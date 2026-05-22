package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "V_MUI_FONT_STILE", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "MuiFontStileEntity.findAll", query = "SELECT e FROM MuiFontStileEntity e"),
        @NamedQuery(name = "MuiFontStileEntity.countById", query = "SELECT COUNT(e) FROM MuiFontStileEntity e WHERE e.id = :id"),
        @NamedQuery(name = "MuiFontStileEntity.findById", query = "SELECT e FROM MuiFontStileEntity e WHERE e.id = :id"),
})
public class MuiFontStileEntity  {
    @Id
    @Column(name = "ID_FONT_STILE", length = 5, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String id;

    @Column (name = "STILE", length = 10)
    private String stile;

    @Column(name = "COLORE", length = 40)
    private String colore;

    @Transient
    public String getLabel(){
        return String.format("%s (%s)", colore, stile);
    }
}
