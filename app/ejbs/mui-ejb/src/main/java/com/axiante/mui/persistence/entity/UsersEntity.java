package com.axiante.mui.persistence.entity;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.persistence.Metadata;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.beans.Transient;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERS", schema = Metadata.SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Slf4j
@NamedQueries(value = { @NamedQuery(name = "UsersEntity.findAll", query = "Select u from UsersEntity u"),
        @NamedQuery(name = "UsersEntity.findByUsername", query = "Select u from UsersEntity u where u.name = :username"),
        @NamedQuery(name = "UsersEntity.countByUsername", query = "Select COUNT(u) from UsersEntity u where u.name = :username") })
public class UsersEntity implements Serializable, UUIDEnabledEntity {
    private static final long serialVersionUID = 5637577560940414014L;
    transient public static String USER_ATTRIBUTE = "ax_user";
    transient public static String USER_ADMIN = "ADMIN";

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "USERS_SEQ", allocationSize = 1, name = "usersSeq")
    @GeneratedValue(generator = "usersSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "FILTERS")
    private String filters;
    @Basic
    @Column(name = "HIDDEN_COLS")
    private String hiddenCols;
    @Basic
    @Column(name = "CAM_LOGIN")
    private Integer camLogin;
    @Basic
    @Column(name = "NAMESPACE_LOGIN")
    private Integer namespaceLogin;
    @Basic
    @Column(name = "ADMIN_FILTERS")
    private String adminFilters;
    @Basic
    @Column(name = "GRID_SORTING")
    private String gridSorting;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "USERS_ROLES", schema = Metadata.SCHEMA, joinColumns = {
            @JoinColumn(name = "ID_USER", referencedColumnName = "ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID") }
    )
    private Set<RolesEntity> roles;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "USER_GROUP", schema = Metadata.SCHEMA,
	joinColumns = { @JoinColumn(name = "ID_USER", referencedColumnName = "ID") },
	inverseJoinColumns = { @JoinColumn(name = "ID_GRUPPO", referencedColumnName = "ID") })
	private Set<GroupEntity> gruppi;


    @Basic
    @Column(name = "INGRID_FILTERS")
    private String ingridFilters = "{}";
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "LAST_ACCESS")
    private Date lastAccess;
    @Basic
    @Column(name = "DB_FILTERS")
    private String dbFilters;

    public void addGruppo(GroupEntity gruppo) {
        if (getGruppi() == null) {
            setGruppi(new HashSet<GroupEntity>());
        }
        getGruppi().add(gruppo);
    }

    public boolean removeGruppo(GroupEntity gruppo) {
        return (getGruppi() != null) && getGruppi().remove(gruppo);
    }

    transient private Function<Entry<String, List<IngridFilter>>, String> mapToJson = (
            final Entry<String, List<IngridFilter>> entry) -> {
        /**
         * "test": [{ "label": null, "attribute": "test_attribute", "dimension":
         * "test_dimension", "selected_values": ["valueone", "valuetwo", "valuethree"],
         * "grid_name": "test_grid" }]
         */
        final StringBuilder string = new StringBuilder();
        string.append("\"").append(entry.getKey()).append("\":[");
        string.append(entry.getValue().stream().map(IngridFilter::asJsonObject).filter(Objects::nonNull)
                .collect(Collectors.joining(",")));
        string.append("]");
        return string.toString();
    };

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

    public UsersEntity(final Integer id, final String name, final RolesEntity role) {
        this.id = id;
        this.name = name;
        this.addRole(role);
    }

    public Boolean getCamLogin() {
        return (this.camLogin != null) && (this.camLogin == 1);
    }

    public void setCamLogin(final Boolean camLogin) {
        this.camLogin = camLogin ? 1 : 0;
    }

    public Boolean getNamespaceLogin() {
        return (this.namespaceLogin != null) && (this.namespaceLogin == 1);
    }

    public void setNamespaceLogin(final Boolean namespaceLogin) {
        this.namespaceLogin = namespaceLogin ? 1 : 0;
    }

    @Transient
    public void updateIngridFilters(Map<String, List<IngridFilter>> filters) {
        /**
         * { "test": [{ "label": null, "attribute": "test_attribute", "dimension":
         * "test_dimension", "selected_values": ["valueone", "valuetwo", "valuethree"],
         * "grid_name": "test_grid" }], "test1": [{ "label": null, "attribute":
         * "test_attribute", "dimension": "test_dimension", "selected_values":
         * ["valueone", "valuetwo", "valuethree"], "grid_name": "test_grid" }] }
         */
        if ((filters != null) && (filters.size() > 0)) {
            this.ingridFilters = "{" + filters.entrySet().stream().map(this.mapToJson).collect(Collectors.joining(","))
                    + "}";
        } else {
            this.ingridFilters = "{}";
        }
    }

    public Map<String, List<IngridFilter>> getIngridFilterAsMap() {
        if ((this.ingridFilters != null) && !this.ingridFilters.equals("{}")) {
            TypeReference<HashMap<String, List<IngridFilter>>> typeRef = new TypeReference<HashMap<String, List<IngridFilter>>>() {
            };
            try {
                return JsonUtils.getMapper().readValue(this.ingridFilters, typeRef);
            } catch (IOException e) {
                log.error("error deserializing in grid filters", e);
                return null;
            }
        }
        return new HashMap<>();
    }

    @Transient
    public String getEscapedAdminFilter() {
        if (getAdminFilters() != null) {
            String result = getAdminFilters().replace("'", "\\'");
            return result;
        }
        return getAdminFilters();
    }

    public void addRole(@NonNull RolesEntity role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
        role.addUser(this);
    }

    public void removeRole(@NonNull RolesEntity role) {
        if (roles != null) {
            roles.remove(role);
        }
        role.removeUser(this);
    }

    @Transient
    public boolean isAdmin() {
        if (getRoles() != null) {
            return getRoles().stream().filter(Objects::nonNull).filter(RolesEntity::isAdmin).findFirst().isPresent();
        }
        return false;
    }

    /**
     * Serve per evitare che un valore json errato
     * finisca per sbaglio nel database:
     * <ol>
     *     <li><pre>null</pre></li>
     *     <li><pre>[]</pre></li>
     *     <li><i>stringa vuola</i></li>
     * </ol>
     * vengono convertiti in <pre>{}</pre>
     * @param adminFilters
     */
    public void setAdminFilters(String adminFilters) {
        if (adminFilters == null || adminFilters.trim().equals("[]") || adminFilters.trim().length() == 0) {
            this.adminFilters = "{}";
        } else {
            this.adminFilters = adminFilters;
        }
    }

    public String getAdminFilters() {
        return (adminFilters == null || adminFilters.trim().equals("[]") || adminFilters.trim().length() == 0)
                ? "{}"
                : adminFilters;
    }

    public void setGridSorting(String gridSorting) {
        if (gridSorting == null || gridSorting.trim().equals("[]") || gridSorting.trim().length() == 0) {
            this.gridSorting = "{}";
        } else {
            this.gridSorting = gridSorting;
        }
    }

    public String getGridSorting() {
        return (gridSorting == null || gridSorting.trim().equals("[]") || gridSorting.trim().length() == 0)
                ? "{}"
                : gridSorting;
    }
}
