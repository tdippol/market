package com.axiante.mui.persistence.entity;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.persistence.Metadata;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ROLES", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = { @NamedQuery(name = "RolesEntity.findAll", query = "select t from RolesEntity t") })
public class SlowRolesEntity implements Serializable, UUIDEnabledEntity {
    private static final long serialVersionUID = -4866346115981891967L;
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "ROLES_SEQ", allocationSize = 1, name = "rolesSeq")
    @GeneratedValue(generator = "rolesSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Integer id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "help_filename")
    private String helpFilename;

    @Lob
    @Column(name = "help_data")
    private byte[] helpData;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

    @Column(name = "override_admin")
    @Convert(converter = BooleanConverter.class)
    private Boolean overrideAdmin = false;

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }

    public SlowRolesEntity(final Integer id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getManualCaption() {
        if (helpFilename != null) {
            return "\"" + helpFilename + "\": cliccare per modificare";
        }
        return "Carica Manuale";
    }


}
