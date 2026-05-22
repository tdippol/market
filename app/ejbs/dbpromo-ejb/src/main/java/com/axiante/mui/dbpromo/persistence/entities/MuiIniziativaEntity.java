package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "V_MUI_INIZIATIVE", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NamedQuery(name = "MuiIniziativaEntity.findAllByDataInizioAndDataFine",
        query = "select e from MuiIniziativaEntity e where e.dataInizio <= :dataInizio and e.dataFine >= :dataFine")
public class MuiIniziativaEntity implements Serializable, ComboBoxCapable {
    private static final long serialVersionUID = 3770525399696563515L;

    @Id
    @Column(name = "COD_INIZIATIVA", nullable = false, length = 5)
    @EqualsAndHashCode.Include
    private String codiceIniziativa;

    @Column(name = "DESCRIZIONE", nullable = false, length = 50)
    private String descrizione;

    @Column(name = "DATA_INIZIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInizio;

    @Column(name = "DATA_FINE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFine;

    @Column(name = "FL_DECIMALI", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private Boolean flagDecimali = Boolean.FALSE;

    @Column(name = "NOME_OGGETTO", length = 50)
    private String nomeOggetto;

    @Column(name = "TIPO_INIZIATIVA")
    private Integer tipoIniziativa;

    @Column(name = "STATO")
    private Integer stato;

    @Override
    public Long getId() {
        try {
            return Long.parseLong(codiceIniziativa);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    @Override
    public String getKey() {
        return codiceIniziativa;
    }

    @Override
    public String getLabel() {
        return String.format("%s - %s", codiceIniziativa, descrizione);
    }
}
