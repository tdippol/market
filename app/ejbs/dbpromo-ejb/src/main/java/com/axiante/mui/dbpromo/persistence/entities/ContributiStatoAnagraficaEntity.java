package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "V_MUI_CONTRIBUTI_STATO_ANAG", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "ContributiStatoAnagraficaEntity.countDescrizioneByCodice",
                query = "SELECT COUNT(e) FROM ContributiStatoAnagraficaEntity e WHERE e.codiceStatoContributo = :codice"),
        @NamedQuery(name = "ContributiStatoAnagraficaEntity.findDescrizioneByCodice",
                query = "SELECT e.descrizioneStatoContributo FROM ContributiStatoAnagraficaEntity e WHERE e.codiceStatoContributo = :codice")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class ContributiStatoAnagraficaEntity implements Serializable {
    private static final long serialVersionUID = -6242228263678532936L;

    @Id
    @Column(name="COD_STATO_CONTR", unique = true, nullable = false, length = 4)
    @EqualsAndHashCode.Include
    String codiceStatoContributo ; //COD_STATO_CONTR VARCHAR(4),

    @Column(name="DESCR_STATO_CONTR", length = 255)
    String descrizioneStatoContributo; //DESCR_STATO_CONTR VARCHAR(255)
}
