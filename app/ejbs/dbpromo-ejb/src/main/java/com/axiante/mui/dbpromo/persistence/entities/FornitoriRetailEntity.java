package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "V_MUI_FORNITORI_RETAIL", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "FornitoriRetailEntity.all",
                query = "select m from FornitoriRetailEntity m"),
        @NamedQuery(name = "FornitoriRetailEntity.allNotDeleted",
                query = "select m from FornitoriRetailEntity m where m.dataEliminazione is null or m.dataEliminazione > :date order by m.codice"),
        @NamedQuery(name = "FornitoriRetailEntity.byId",
                query = "select m from FornitoriRetailEntity m where m.codice = :codice"),
        @NamedQuery(name = "FornitoriRetailEntity.byDescription",
                query = "select m from FornitoriRetailEntity m where m.descrizione = :descrizione"),
        @NamedQuery(name = "FornitoriRetailEntity.likeCode",
                query = "select m from FornitoriRetailEntity m where m.codice LIKE :codice"),
        @NamedQuery(name = "FornitoriRetailEntity.likeDescription",
                query = "select m from FornitoriRetailEntity m where m.descrizione LIKE :descrizione")
})
public class FornitoriRetailEntity implements Serializable {
    private static final long serialVersionUID = 3029252102107805132L;

    @Id
    @Column(name = "codice", length = 10)
    String codice;

    @Column(name = "descrizione", length = 255)
    String descrizione;

    @Column(name = "data_eliminazione_fornitore")
    Date dataEliminazione;
}
