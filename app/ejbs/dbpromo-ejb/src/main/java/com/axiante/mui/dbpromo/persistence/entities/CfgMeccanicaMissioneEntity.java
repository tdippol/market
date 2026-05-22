package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MUI_CFG_MECCANICHE_MISSIONI", schema = Metadata.SCHEMA)
@NamedQuery(name = "CfgMeccanicaMissioneEntity.findAll", query = "SELECT m FROM CfgMeccanicaMissioneEntity m")
@EntityListeners(DbPromoReadOnlyEntity.class)
public class CfgMeccanicaMissioneEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 4647984086843035780L;

    @Id
    @Column(name = "ID_MECCANICA", unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "CODICE_MECCANICA", unique = true, nullable = false, length = 20)
    private String codiceMeccanica;
}
