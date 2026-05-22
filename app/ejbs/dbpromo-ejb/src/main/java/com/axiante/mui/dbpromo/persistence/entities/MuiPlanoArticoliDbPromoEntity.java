package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "V_MUI_PLANO_ARTICOLI_DBPROMO", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "MuiPlanoArticoliDbPromoEntity.findAll",
                query = "SELECT e FROM MuiPlanoArticoliDbPromoEntity e ORDER BY e.id.idPlano, e.id.tipoItem, e.id.codiceItem"),
        @NamedQuery(name = "MuiPlanoArticoliDbPromoEntity.findAllByIdPlano",
                query = "SELECT e FROM MuiPlanoArticoliDbPromoEntity e WHERE e.id.idPlano = :idPlano ORDER BY e.id.tipoItem, e.id.codiceItem"), 
        @NamedQuery(name = "MuiPlanoArticoliDbPromoEntity.findAllByIdPlanos",
        query = "SELECT e FROM MuiPlanoArticoliDbPromoEntity e WHERE e.id.idPlano IN :idPlanos ORDER BY e.id.tipoItem, e.id.codiceItem"), 
      
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MuiPlanoArticoliDbPromoEntity implements Serializable {
    private static final long serialVersionUID = 4962270096453611995L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    private MuiPlanoArticoliDbPromoId id;

    @Column(name = "CATEGORIA_ESPOSITIVA")
    private String categoriaEspositiva;

    @Column(name = "DIMENSIONE")
    private String dimensione;

    @Column(name = "TIPOLOGIA")
    private String tipologia;

    @Column(name = "DESCRIZIONE_ITEM")
    private String descrizioneItem;

    @Column(name = "COD_COMPRATORE")
    private String codiceCompratore;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CODICE_ITEM", referencedColumnName = "ID")
    private ItemEntity item;
    
    @Transient
    private String descrizionePlanogramma;
}
