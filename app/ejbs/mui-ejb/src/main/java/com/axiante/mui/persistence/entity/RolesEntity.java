package com.axiante.mui.persistence.entity;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
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
public class RolesEntity implements Serializable, UUIDEnabledEntity {
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

    // bi-directional many-to-one association to Acl
    @ToString.Exclude
    @OneToMany(mappedBy = "rolesByRoleId", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY)
    private List<AclEntity> acls;

    // bi-directional many-to-many association to User
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH },fetch = FetchType.LAZY)
    private Set<UsersEntity> users;

    // bi-directional many-to-many association to Menu... comanda il Menu !!!
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<MenuEntity> menus;

    @Basic
    @Column(name = "help_filename")
    private String helpFilename;

    @Column(name = "UUID", length = 32)
    @EqualsAndHashCode.Include
    private String uuid;

    @Column(name = "override_admin")
    @Convert(converter = BooleanConverter.class)
    private Boolean overrideAdmin = false;

    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "DOCUMENT_ROLE", schema = Metadata.SCHEMA, joinColumns = {
            @JoinColumn(name = "ID_ROLE") }, inverseJoinColumns = { @JoinColumn(name = "ID_DOCUMENT") })
    private Set<UploadDocumentEntity> documents;


    @Override
    @PrePersist
    @PreUpdate
    public String getUuid() {
        if (this.uuid == null) {
            this.uuid = AxUUID.randomUUID().toString();
        }
        return uuid;
    }

    public RolesEntity(final Integer id, final String name, final String description) {
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

    public AclEntity addAcl(final AclEntity acl) {
        if (acls == null) {
            acls = new ArrayList<>();
        }
        getAcls().add(acl);
        acl.setRolesByRoleId(this);

        return acl;
    }

    public AclEntity removeAcl(final AclEntity acl) {
        if (getAcls() != null) {
            getAcls().remove(acl);
        }
        acl.setRolesByRoleId(null);

        return acl;
    }

    public UsersEntity addUser(final UsersEntity user) {
        if (users == null) {
            users = new HashSet<>();
        }
        getUsers().add(user);

        return user;
    }

    public UsersEntity removeUser(final UsersEntity user) {
        if (getUsers() != null) {
            getUsers().remove(user);
        }

        return user;
    }

    public MenuEntity addMenu(final MenuEntity menu) {
        if (getMenus() == null) {
            setMenus(new HashSet<>());
        }
        getMenus().add(menu);
        menu.addRole(this);
        return menu;
    }

    public MenuEntity removeMenu(final MenuEntity menu) {
        if (getMenus() != null) {
            getMenus().remove(menu);
        }
        menu.removeRole(this);
        return menu;
    }

    public UploadDocumentEntity addDocument(final UploadDocumentEntity doc) {
        if (getDocuments() == null) {
            setDocuments(new HashSet<>());
        }
        getDocuments().add(doc);
        return doc;
    }

    public UploadDocumentEntity removeDocument(final UploadDocumentEntity doc) {
        if (documents != null) {
            documents.remove(doc);
        }
        return doc;
    }

    public boolean isAdmin() {
        return (getName() != null) && ADMIN_ROLE.equals(getName());
    }

    @Transient
    public byte[] getHelpData(){
        return null;
    }

    @Transient
    public void setHelpData(byte[] helpData) {
    }
}
