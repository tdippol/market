package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_TEMPLATE_TOTALIZZATORI", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class TemplateTotalizzatoriEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_TEMPLATE_TOTALIZZATORI_ID_GENERATOR", sequenceName = "MUI_TEMPLATE_TOTAL_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_TEMPLATE_TOTALIZZATORI_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "DESCRIZIONE", length = 200)
    String descrizione;

    @Column(name = "ACTION_TYPE", precision = 16)
    Integer actionType;

    @Column(name = "SEGNO", precision = 1, scale = 0)
    private Integer segno;

    @Column(name = "EXPORT_VS", precision = 1, scale = 0)
    private Integer exportVs;

}
