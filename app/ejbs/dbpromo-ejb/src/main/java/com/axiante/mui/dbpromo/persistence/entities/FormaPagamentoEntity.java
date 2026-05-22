package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "V_MUI_FORMA_PAGAMENTO", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "FormaPagamentoEntity.findAllAttivo", query = "SELECT e FROM FormaPagamentoEntity e WHERE e.attivo = true"),
        @NamedQuery(name = "FormaPagamentoEntity.countByCodice", query = "SELECT COUNT(e) FROM FormaPagamentoEntity e WHERE e.codice = :codice"),
        @NamedQuery(name = "FormaPagamentoEntity.findByCodice", query = "SELECT e FROM FormaPagamentoEntity e WHERE e.codice = :codice")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormaPagamentoEntity implements Serializable, ComboBoxCapable {
    private static final long serialVersionUID = -7802494222245113682L;

    @Id
    @Column(name = "COD_FORMA_PAGAMENTO", unique = true, nullable = false, length = 5)
    @EqualsAndHashCode.Include
    private String codice;

    @Column(name = "DES_FORMA_PAGAMENTO", nullable = false, length = 100)
    private String descrizione;

    @Column(name = "ATTIVO", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private Boolean attivo;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getKey() {
        return getCodice();
    }

    @Override
    public String getLabel() {
        return getDescrizione();
    }
}
