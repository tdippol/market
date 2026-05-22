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
@Table(name = "V_MUI_PRESTAZIONI", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "PrestazioniEntity.countDescrizioneByCodice",
                query = "SELECT COUNT(e) FROM PrestazioniEntity e WHERE e.codicePrestazione = :codice"),
        @NamedQuery(name = "PrestazioniEntity.findDescrizioneByCodice",
                query = "SELECT e.descrizionePrestazione FROM PrestazioniEntity e WHERE e.codicePrestazione = :codice"),
        @NamedQuery(name = "PrestazioniEntity.findByCodiceGruppo",
                query = "SELECT e FROM PrestazioniEntity e WHERE e.codiceGruppo = :codiceGruppo"),
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class PrestazioniEntity implements Serializable {
    private static final long serialVersionUID = 949423177290261350L;

    @Id
    @Column(name="COD_PRESTAZIONE", unique = true, nullable = false, length = 6)
    @EqualsAndHashCode.Include
    String codicePrestazione;

    @Column(name= "COD_GRUPPO", length = 4)
    String codiceGruppo;

    @Column(name= "DES_PRESTAZIONE", length = 100)
    String descrizionePrestazione;

    @Column(name= "DES_FATTURA", length = 255)
    String descrizioneFattura;
}
