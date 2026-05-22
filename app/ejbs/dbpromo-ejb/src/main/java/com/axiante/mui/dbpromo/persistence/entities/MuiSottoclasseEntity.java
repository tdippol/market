package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(DbPromoReadOnlyEntity.class)
@Table(name = "V_MUI_SOTTOCLASSE", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "MuiSottoclasseEntity.findAll",
                query = "SELECT e FROM MuiSottoclasseEntity e"),
        @NamedQuery(name = "MuiSottoclasseEntity.findAllAttive",
                query = "SELECT e FROM MuiSottoclasseEntity e WHERE e.attiva = true"),
        @NamedQuery(name = "MuiSottoclasseEntity.countActiveByCode",
                query = "SELECT COUNT(e) FROM MuiSottoclasseEntity e WHERE e.sottoClasse = :codice AND e.attiva = true"),
        @NamedQuery(name = "MuiSottoclasseEntity.findActiveByCode",
                query = "SELECT e FROM MuiSottoclasseEntity e WHERE e.sottoClasse = :codice AND e.attiva = true")
})
public class MuiSottoclasseEntity implements ComboBoxCapable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "SOTTOCLASSE", length = 10, unique = true, nullable = false)
    private String sottoClasse;

    @Column(name = "DESCRIZIONE", length = 100, unique = true, nullable = false)
    private String descrizione;

    @Column(name = "PRIORITA", precision = 5)
    private Integer priorita;

    @Column(name = "DELAY", precision = 5)
    private Integer delay;

    @Column(name = "FL_ATTIVA", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private Boolean attiva;

    @Transient
    @Override
    public String getKey() {
        return getSottoClasse();
    }

    @Transient
    @Override
    public String getLabel() {
        return getDescrizione();
    }

    @Transient
    @Override
    public Long getId() {
        return null;
    }
}
