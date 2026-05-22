package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_SUPPORTO_MEDIA_CFG_CHECK", schema = Metadata.SCHEMA)
@NamedQueries({
    @NamedQuery(name = "SupportoMediaCfgCheckEntity.findAllBySupportoMedia",
            query = "SELECT e FROM SupportoMediaCfgCheckEntity e WHERE e.supportoMedia = :supporto"),
    @NamedQuery(name = "SupportoMediaCfgCheckEntity.findAllAttivoBySupportoMedia",
            query = "SELECT e FROM SupportoMediaCfgCheckEntity e WHERE e.supportoMedia = :supporto and e.supportoMedia.attivo = true")
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SupportoMediaCfgCheckEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_SUPPORTO_MEDIA_CFG_CHECK_ID_GENERATOR",
            sequenceName = "MUI_SUPP_MED_CFG_CHECK_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_SUPPORTO_MEDIA_CFG_CHECK_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUPPORTO_MEDIA", nullable = false)
    @EqualsAndHashCode.Include
    private SupportoMediaEntity supportoMedia;

    @Column(name = "BLOCCO")
    @Convert(converter = BooleanConverter.class)
    private Boolean blocco = false;

    @Column(name = "CODICE_CONTROLLO")
    @EqualsAndHashCode.Include
    private String codiceControllo;
}
