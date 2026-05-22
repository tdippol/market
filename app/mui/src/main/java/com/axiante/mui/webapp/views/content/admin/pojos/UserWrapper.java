package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;

public class UserWrapper {
    Integer id;

    @Getter
    private UsersEntity entity;

    @Getter
    private HashSet<GroupEntity> groupsToUpdate;

    @Getter
    List<GroupEntity> groups;

    @Getter
    List<RolesEntity> roles;

    @Getter
    private HashSet<RolesEntity> rolesToUpdate;

    @Getter
    String groupList;

    @Getter
    String roleList;

    public UserWrapper() {
    }

    public UserWrapper(UsersEntity user) {
        this.entity = user;
        this.groups = new ArrayList<>();
        if (user.getGruppi() != null) {
            this.groups.addAll(user.getGruppi());
        }
        this.groupsToUpdate = new HashSet<>();
        updateGroupList();
        this.roles = new ArrayList<>();
        if (user.getRoles() != null) {
            this.roles.addAll(user.getRoles());
        }
        this.rolesToUpdate = new HashSet<>();
        updateRoleList();
        if (user.getId() != null) {
            this.id = user.getId();
        } else {
            id = new Random().nextInt();
            if (id > 0) {
                // avoid any chances of a random match
                id = id * -1;
            }
        }
    }

    public void setRoles(List<RolesEntity> roles){
        if ( this.entity != null ) {
            if (this.entity.getRoles() != null) {
                // prima tolgo quelli che non ci sono piu'
                List<RolesEntity> remove = this.entity.getRoles().stream().filter(g -> !roles.contains(g))
                        .collect(Collectors.toList());
                // poi aggiungo quelli che non ci sono ancora
                List<RolesEntity> add = roles.stream().filter(g -> !this.entity.getRoles().contains(g))
                        .collect(Collectors.toList());
                remove.forEach(g -> {
//                    g.removeUser(this.entity);
                    this.entity.removeRole(g);
                });
                add.forEach(g -> this.entity.addRole(g));
                rolesToUpdate.addAll(remove);
                rolesToUpdate.addAll(add);
            } else {
                roles.forEach(r -> entity.addRole(r));
                rolesToUpdate.addAll(roles);
            }
        }
        this.roles = roles;
        updateRoleList();
    }

    public void setGroups(List<GroupEntity> groups) {
        if (this.entity != null) {
            if (this.entity.getGruppi() != null) {
                // prima tolgo quelli che non ci sono piu'
                List<GroupEntity> remove = this.entity.getGruppi().stream().filter(g -> !groups.contains(g))
                        .collect(Collectors.toList());
                // poi aggiungo quelli che non ci sono ancora
                List<GroupEntity> add = groups.stream().filter(g -> !this.entity.getGruppi().contains(g))
                        .collect(Collectors.toList());
                remove.forEach(g -> {
                    g.removeUser(this.entity);
                    this.entity.removeGruppo(g);
                });
                add.forEach(g -> this.entity.addGruppo(g));
                groupsToUpdate.addAll(remove);
                groupsToUpdate.addAll(add);
            } else {
                // tutti i gruppi sono da aggiungere
                groups.forEach(g -> this.entity.addGruppo(g));
                groupsToUpdate.addAll(groups);
            }
        }
        this.groups = groups;
        updateGroupList();
    }

    public Integer getId() {
        return entity != null ? this.entity.getId() : null;
    }

    public void setId(Integer id) {
        if (entity != null) {
            this.entity.setId(id);
        }
    }

    public void setRoles(Set<RolesEntity> roles) {
        if (entity != null) {
            this.entity.setRoles(roles);
        }
    }

    public String getName() {
        return entity != null ? this.entity.getName() : null;
    }

    public void setName(String name) {
        if (entity != null) {
            this.entity.setName(name);
        }
    }

    public void setDescription(String description) {
        if (entity != null) {
            this.entity.setDescription(description);
        }
    }

    public String getDescription() {
        return entity != null ? this.entity.getDescription() : null;
    }

    public Boolean getCamLogin() {
        return entity != null ? entity.getCamLogin() : null;
    }

    public void setCamLogin(Boolean camLogin) {
        if (entity != null) {
            entity.setCamLogin(camLogin);
        }
    }

    public Boolean getNamespaceLogin() {
        return entity != null ? entity.getNamespaceLogin() : null;
    }

    public void setNamespaceLogin(Boolean namespaceLogin) {
        if (entity != null) {
            this.entity.setNamespaceLogin(namespaceLogin);
        }
    }

    public String getFilters() {
        return entity != null ? entity.getFilters() : null;
    }

    public void setFilters(String filters) {
        if (entity != null) {
            this.entity.setFilters(filters);
        }
    }

    private String hiddenCols;

    public String getHiddenCols() {
        return hiddenCols;
    }

    public void setHiddenCols(String hiddenCols) {
        this.hiddenCols = hiddenCols;
        if (entity != null) {
            entity.setHiddenCols(hiddenCols);
        }
    }

    public String getAdminFilters() {
        return entity != null ? this.entity.getAdminFilters() : null;
    }

    public void setAdminFilters(String adminFilters) {
        if (entity != null) {
            this.entity.setAdminFilters(adminFilters);
        }
    }

    public String getIngridFilters() {
        return entity != null ? this.entity.getIngridFilters() : null;
    }

    public void setIngridFilters(String ingridFilters) {
        if (entity != null) {
            this.entity.setIngridFilters(ingridFilters);
        }
    }

    public Date getLastAccess() {
        return entity != null ? this.entity.getLastAccess() : null;
    }

    public void setLastAccess(Date lastAccess) {
        if (entity != null) {
            this.entity.setLastAccess(lastAccess);
        }
    }

    public String getDbFilters() {
        return entity != null ? this.entity.getDbFilters() : null;
    }

    public void setDbFilters(String dbFilters) {
        if (entity != null) {
            this.entity.setDbFilters(dbFilters);
        }
    }

    public void updateUser(@NonNull UsersEntity user) {
        if (this.entity == null) {
            throw new IllegalStateException("Corrupted memory object");
        }
        this.entity = user;
        updateGroupList();
    }

    public void clearGroupUpdateList() {
        this.groupsToUpdate.clear();
    }

    public void clearRolesUpdateList() {
        this.rolesToUpdate.clear();
    }

    public boolean removeCurrentRoles() {
        if (getRoles() != null) {
            getRoles().forEach(r -> entity.removeRole(r));
            return true;
        }
        return false;
    }

    public String getEscapedAdminFilter() {
        return entity != null ? entity.getEscapedAdminFilter() : null;
    }

    private void updateGroupList() {
        if ((entity != null) && (entity.getGruppi() != null)) {
            groupList = entity.getGruppi().stream().filter(Objects::nonNull).map(GroupEntity::getDescrizione)
                    .collect(Collectors.joining(","));
        } else {
            groupList = "empty";
        }
    }
    private void updateRoleList() {
        if ((entity != null) && (entity.getRoles() != null)) {
            roleList = entity.getRoles().stream().filter(Objects::nonNull).map(RolesEntity::getDescription).collect(Collectors.joining(","));
        } else {
            roleList = "empty";
        }
    }

}
