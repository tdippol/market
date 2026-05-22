package com.axiante.mui.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.persistence.Metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "MENU", schema = Metadata.SCHEMA)
@Slf4j
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "MenuEntity.readAll", query = "select t from MenuEntity t order by t.parent, t.orderId"),
        @NamedQuery(name = "MenuEntity.readAllByRole", query = "select t from MenuEntity t join t.roles role where role = :role"),
        @NamedQuery(name = "MenuEntity.readAllByParent", query = "select t from MenuEntity t where t.parent.idMenu = :parentId"),
        @NamedQuery(name = "MenuEntity.readNullParent", query = "select menu from MenuEntity menu where menu.parent is null"),
        @NamedQuery(name = "MenuEntity.getChildrenByRole", query = "select distinct menu from MenuEntity menu join menu.roles role where menu.parent.idMenu = :idParent and role.id = :idRole"),
        @NamedQuery(name = "MenuEntity.belongsToRole", query = "select count(menu) from MenuEntity menu join menu.roles role where menu = :menu and role = :role"),
        @NamedQuery(name = "MenuEntity.belongsToRoles", query = "select count(menu) from MenuEntity menu join menu.roles role where menu = :menu and role IN :roles")

})
public class MenuEntity implements Serializable, UUIDEnabledEntity {

    private static final long serialVersionUID = 1362844567414069635L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "MENU_SEQ", allocationSize = 1, name = "menuSeq")
    @GeneratedValue(generator = "menuSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_MENU")
    private Integer idMenu;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    MenuEntity parent;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    List<MenuEntity> children;

    @Basic
    @Column(name = "LABEL")
    private String label;

    @Basic
    @Column(name = "URL")
    private String url;

    @Basic
    @Column(name = "BEAN")
    private String bean;

    @Basic
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Basic
    @Column(name = "EXTERNAL_LINK")
    @Convert(converter = BooleanConverter.class)
    private Boolean externalLink;

    @Basic
    @Column(name = "TEMPLATE")
    private Integer template;

    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "MENU_ROLES", schema = Metadata.SCHEMA, joinColumns = {
            @JoinColumn(name = "ID_MENU") }, inverseJoinColumns = { @JoinColumn(name = "ID_ROLES") })
    private Set<RolesEntity> roles;

    @ToString.Exclude
    @OneToMany(mappedBy = "menu", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ConfigurationEntity> configurations;

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

    public MenuEntity(final Integer idMenu, final MenuEntity parent, final String label, final String url,
            final String bean, final Integer orderId, final boolean externalLink) {
        this.idMenu = idMenu;
        this.parent = parent;
        this.label = label;
        this.url = url;
        this.bean = bean;
        this.orderId = orderId;
        this.externalLink = externalLink;
    }

    public Boolean getExternalLink() {
        if (externalLink == null) {
            return Boolean.FALSE;
        }
        return externalLink;
    }

    public Boolean getTemplate() {
        return (template != null) && (template == 1);
    }

    public void setTemplate(final Boolean template) {
        this.template = template ? 1 : 0;
    }

    public Boolean isLeaf() {
        if (bean != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean hasChildren() {
        return (getChildren() != null) && (getChildren().size() > 0);
    }

    public RolesEntity addRole(final RolesEntity role) {
        if (getRoles() == null) {
            setRoles(new HashSet<>());
        }
        getRoles().add(role);
        return role;
    }

    public RolesEntity removeRole(final RolesEntity role) {
        if (getRoles() != null) {
            getRoles().remove(role);
        }
        return role;
    }

    public boolean addConfiguration(final ConfigurationEntity configuration) {
        boolean ret = false;
        if (configurations == null) {
            configurations = new HashSet<>();
        }
        ret = configurations.add(configuration);
        if (!ret) {
            log.warn("The configuration id " + configuration.getIdConfiguration() + " is already present");
        }
        configuration.setMenu(this);
        configuration.setPath(generatePath());
        return ret;
    }

    public boolean removeConfiguration(final ConfigurationEntity configuration) {
        boolean ret = false;
        if (configurations != null) {
            ret = configurations.remove(configuration);
        }
        configuration.setMenu(null);
        return ret;
    }

    public Boolean hasConfigurations() {
        if (getConfigurations() != null) {
            return getConfigurations().stream().map(ConfigurationEntity::getRevisionDate).filter(Objects::isNull)
                    .count() > 0;
        }
        return Boolean.FALSE;
    }

    protected String generatePath() {
        final StringBuilder buffer = new StringBuilder(getLabel());
        MenuEntity parent = getParent();
        while (parent != null) {
            buffer.insert(0, "/");
            buffer.insert(0, parent.label);
            parent = parent.getParent();
        }
        return buffer.toString().replaceAll(" ", "_");
    }

    @Override
    public String toString() {
        return "{" + "id: " + getIdMenu() + ", label: " + label + ", bean: " + bean + ", order: " + orderId
                + ", external: " + getExternalLink() + ", template: " + getTemplate() + ", url: " + getUrl()
                + ", parent: " + (parent != null ? parent.getIdMenu() : "null") + ", roles: "
                + (roles == null ? "null"
                        : "[" + roles.stream().map(RolesEntity::getName).collect(Collectors.joining(", ")) + "]")
                + ",children: "
                + (children == null ? "null"
                        : "[" + children.stream().map(MenuEntity::toString).collect(Collectors.joining(", ")) + "]")
                + ",configurations: "
                + (configurations == null ? "null"
                        : "[" + configurations.stream().map(c -> ("" + c.getType() + ":{" + c.getJson() + "}"))
                                .collect(Collectors.joining(", ")) + "]")
                + "}";
    }
}
