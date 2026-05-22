package com.axiante.mui.persistence.entity;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CONFIGURATION", schema = Metadata.SCHEMA)
@Cacheable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "ConfigurationEntity.findAllOrderedByPath", query = "select t from ConfigurationEntity t order by t.path"),
        @NamedQuery(name = "ConfigurationEntity.findAllByPathAndType", query = "select t from ConfigurationEntity t where t.path = :path and t.type = :type"),
        @NamedQuery(name = "ConfigurationEntity.findAllByTypeOrderByPath", query = "select t from ConfigurationEntity t where t.type = :type order by t.path"),
        @NamedQuery(name = "ConfigurationEntity.findAllByIdMenu", query = "select t from ConfigurationEntity t where t.menu.idMenu = :idMenu"),
        @NamedQuery(name = "ConfigurationEntity.findValidByTypeAndIdMenu", query = "select t from ConfigurationEntity t where t.menu.idMenu = :idMenu and t.type = :type and t.revisionDate is null"),})

public class ConfigurationEntity implements Serializable, UUIDEnabledEntity {
    private static final long serialVersionUID = 2059745221164536347L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "CONFIGURATION_SEQ", allocationSize = 1, name = "configurationSeq")
    @GeneratedValue(generator = "configurationSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_CONFIGURATION")
    private int idConfiguration;
    // TODO: remove me
    @Basic
    @Column(name = "PATH")
    private String path;
    @Basic
    @Column(name = "JSON")
    private String json;
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ConfigurationTypes type;
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "ID_MENU")
    MenuEntity menu;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_REVISION_DATE")
    private java.util.Date revisionDate;
    @Basic
    @Column(name = "LAST_EDITOR", length = 255)
    String lastEditor;
    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

    @Override
    @PreUpdate
    @PrePersist
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }

}
