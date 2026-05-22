package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_CANALE_REPARTO", schema = Metadata.SCHEMA)
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "CfgCanaleReparto.findByCanale",
                query = "SELECT m FROM CfgCanaleReparto m WHERE m.canale = :canale"),
        @NamedQuery(name = "CfgCanaleReparto.findByCanaleAndReparto",
                query = "SELECT m FROM CfgCanaleReparto m WHERE m.canale = :canale AND m.reparto = :reparto")
})
public class CfgCanaleReparto implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = -6664185360614304366L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_CANALE_REPARTO_ID_GENERATOR",
            sequenceName = "MUI_CFG_CANALE_REPARTO_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_CANALE_REPARTO_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CANALE", nullable = false)
    private CanalePromozioneEntity canale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REPARTO", nullable = false)
    private RepartoEntity reparto;

    @Column(name = "MAX_TESTATE")
    Integer maxTestate = 0;
}
