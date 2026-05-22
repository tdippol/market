package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_MARCHIO_PRIVATO", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "MarchioPrivatoEntity.findAll", query = "SELECT m FROM MarchioPrivatoEntity m"),
        @NamedQuery(name = "MarchioPrivatoEntity.findByCodice", query = "SELECT m FROM MarchioPrivatoEntity m WHERE m.codice = :codice"),
        @NamedQuery(name = "MarchioPrivatoEntity.findByCodici", query = "SELECT m FROM MarchioPrivatoEntity m WHERE m.codice IN :codici")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MarchioPrivatoEntity implements Serializable, ComboBoxCapable {
    private static final long serialVersionUID = 7708107775052378518L;
    
    @Id
    @Column(unique = true, nullable = false, precision = 16, name = "ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "COD_MARCHIO_PRIVATO ", nullable = false, length = 2, unique = true)
    @EqualsAndHashCode.Include
    private String codice;

    @Column(name = "DES_MARCHIO_PRIVATO", length = 36)
    private String descrizione;

    @Column(name = "TIPO_MARCHIO", length = 1)
    private String tipoMarchio;

    @Column(name = "ORDINE_ESPOSIZIONE")
    private Integer ordineEsposizione;

    @Column(name = "VISUALIZZAZIONE", length = 1)
    private String visualizzazione;

    @OneToMany(mappedBy = "marchioPrivato" , cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, orphanRemoval = true)
    private Set<PromozioneMarchioPrivatoEntity> marchioPrivatoList;

    @Override
    public String getKey() {
        return getCodice();
    }

    @Override
    public String getLabel() {
        return getDescrizione();
    }
}
