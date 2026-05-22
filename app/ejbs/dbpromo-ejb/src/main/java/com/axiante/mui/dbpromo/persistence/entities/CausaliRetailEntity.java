package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "V_MUI_CAUSALI_RETAIL", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "CausaliRetail.all", query = "Select m from CausaliRetailEntity m "),
        @NamedQuery(name = "CausaliRetail.byCode", query = "Select m from CausaliRetailEntity m where m.codice = :codice"),
        @NamedQuery(name = "CausaliRetail.byDescription", query = "select m from CausaliRetailEntity m where m.descrizione = :descrizione"),
        @NamedQuery(name = "CausaliRetail.likeCode", query = "Select m from CausaliRetailEntity m where m.codice LIKE :codice"),
        @NamedQuery(name = "CausaliRetail.likeDescription", query = "Select m from CausaliRetailEntity m where m.descrizione LIKE :descrizione")
})
public class CausaliRetailEntity implements Serializable {
  @Id
  @Column(name = "codice", length = 10)
  String codice;

  @Column(name = "descrizione", length = 255)
  String descrizione;
}
