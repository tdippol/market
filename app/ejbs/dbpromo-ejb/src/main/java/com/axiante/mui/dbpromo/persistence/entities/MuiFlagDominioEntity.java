package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.beans.Transient;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_FLAG_DOMINIO", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NamedQueries({
        @NamedQuery(name = "MuiFlagDominioEntity.findAllAttiviByFlag",
                query = "SELECT f FROM MuiFlagDominioEntity f WHERE f.flag.id = :flagId AND f.attivo = true ORDER BY f.ordine"),
        @NamedQuery(name = "MuiFlagDominioEntity.findAllAttiviAndDefaultByFlag",
                query = "SELECT f FROM MuiFlagDominioEntity f WHERE f.flag.id = :flagId AND f.attivo = true and f.flDefault = true ORDER BY f.ordine")
})
@NoArgsConstructor
public class MuiFlagDominioEntity implements Serializable, DbPromoEntityInterface, ComboBoxCapable {
    private static final long serialVersionUID = -5733162986686423370L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_FLAG_DOMINIO_ID_GENERATOR",
            sequenceName = "MUI_FLAG_DOMINIO_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_FLAG_DOMINIO_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "VALORE", length = 50)
    private String valore;

    @Column(name = "DESCRIZIONE", length = 50)
    private String descrizione;

    @Column(name = "ORDINE")
    private Integer ordine;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "ATTIVO")
    private Boolean attivo;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "FL_DEFAULT")
    private Boolean flDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FLAG", referencedColumnName = "ID")
    private MuiFlagEntity flag;

    @Override
    @Transient
    public String getKey() {
        return String.valueOf(getId());
    }

    @Override
    @Transient
    public String getLabel() {
        return getValore();
    }
}
