package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "AttributoArticoloEntity.findAllActive",
        query = "SELECT a FROM AttributoArticoloEntity a WHERE a.attivo = true")
@Entity
@Table(name = "MUI_ATTRIBUTO_ARTICOLO", schema = Metadata.SCHEMA)
public class AttributoArticoloEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_ATTRIBUTO_ARTICOLO_ID_GENERATOR",
            sequenceName = "MUI_ATTRIBUTO_ARTICOLO_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_ATTRIBUTO_ARTICOLO_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "CODICE_ATTRIBUTO", nullable = false, length = 5)
    private String codiceAttributo;

    @Column(name = "DESCRIZIONE", nullable = false)
    private String descrizione;

    @Column(name = "ATTIVO", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private Boolean attivo;
}
