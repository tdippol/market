package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MUI_CFG_ABILITA_CHECK", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "CfgAbilitaCheckEntity.findByCanaleMeccanica", query = "Select c from CfgAbilitaCheckEntity c WHERE c.meccanicaCanaleAbilitata = :meccanicaCanaleAbilitata")
})
public class CfgAbilitaCheckEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_ABILITA_CHECK_ID_GENERATOR", sequenceName = "MUI_CFG_ABILITA_CHECK_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_ABILITA_CHECK_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CHECK", nullable = false)
    CfgCheckEntity check;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ABILITA_MECC_CANALE", nullable = false)
    CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata;

    @Column(name = "SEVERITA", length = 20, nullable = false)
    String severita;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CfgAbilitaCheckEntity))
            return false;

        CfgAbilitaCheckEntity other = (CfgAbilitaCheckEntity) o;
        return
                check != null && check.equals(other.getCheck()) &&
                        meccanicaCanaleAbilitata != null && meccanicaCanaleAbilitata.equals(other.getMeccanicaCanaleAbilitata());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CfgAbilitaCheckEntity{" +
                "id=" + id +
                ", check=" + (check != null ? String.valueOf(check.getId()) : "null") +
                ", meccanicaCanaleAbilitata=" + (meccanicaCanaleAbilitata != null ? String.valueOf(meccanicaCanaleAbilitata.getId()) : "null") +
                ", severita='" + severita + '\'' +
                '}';
    }
}
