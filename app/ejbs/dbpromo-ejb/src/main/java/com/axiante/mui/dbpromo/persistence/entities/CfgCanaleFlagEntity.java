package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_CANALE_FLAG", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "CfgCanaleFlagEntity.findActiveByChannel",
                query = "SELECT c FROM CfgCanaleFlagEntity c WHERE c.canale.id = :idCanale and c.flag.attivo = true")
})
public class CfgCanaleFlagEntity implements Serializable, DbPromoEntityInterface{
    private static final long serialVersionUID = -7110931583435884458L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_CANALE_FLAG_ID_GENERATOR",
            sequenceName = "MUI_CFG_CANALE_FLAG_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_CANALE_FLAG_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_CANALE", referencedColumnName = "ID")
    private CanalePromozioneEntity canale;

    @ManyToOne
    @JoinColumn(name="ID_FLAG", referencedColumnName = "ID")
    private MuiFlagEntity flag;
}
