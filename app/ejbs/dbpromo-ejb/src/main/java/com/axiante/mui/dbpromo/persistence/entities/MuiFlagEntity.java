package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "MUI_FLAG", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
public class MuiFlagEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 7285511523348549647L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_FLAG_ID_GENERATOR",
            sequenceName = "MUI_FLAG_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_FLAG_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "CODICE_FLAG", length = 20)
    private String codice;

    @Column(name = "DESCRIZIONE", length = 50)
    private String descrizione;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "ATTIVO")
    private Boolean attivo;
}
