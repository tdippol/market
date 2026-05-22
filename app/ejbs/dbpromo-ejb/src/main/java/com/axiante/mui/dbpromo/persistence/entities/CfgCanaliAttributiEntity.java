package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
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
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "MUI_CFG_CANALE_ATTRIBUTI", schema = Metadata.SCHEMA,
        uniqueConstraints = {
                @UniqueConstraint(columnNames={"ID_CANALE","ID_ATTRIBUTO"}, name="MUI_CFG_CANALE_ATTRIBUTI_UK1")
        }
)
@NamedQueries({
        @NamedQuery(name="CfgCanaliAttributiEntity.getListaByCanaleAndAttributo", query = "select c.lista from CfgCanaliAttributiEntity c where c.canale.id = :idCanale and c.attributo.id = :idAttributo"),
        @NamedQuery(name="CfgCanaliAttributiEntity.getAllByCanale", query = "select c from CfgCanaliAttributiEntity c where c.canale.id = :idCanale")
})
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class CfgCanaliAttributiEntity implements Serializable, DbPromoEntityInterface {
    @Id
    @SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_CANALE_ATTRIBUTI_ID_GENERATOR", sequenceName = "MUI_CFG_CANALE_ATTR_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_CANALE_ATTRIBUTI_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    // bi-directional many-to-one association to MuiCanalePromozione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CANALE", nullable = false)
    CanalePromozioneEntity canale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ATTRIBUTO", nullable = false)
    AttributiPromoEntity attributo;
    
    @Column(name="VALORE_DEFAULT", length = 50)
    String valoreDefault;

    @Column(name="LISTA", length = 100)
    String lista;
}
