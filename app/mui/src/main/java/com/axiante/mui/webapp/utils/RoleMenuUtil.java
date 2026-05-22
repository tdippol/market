package com.axiante.mui.webapp.utils;

import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.MenuRoleEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.MuiService;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.NonNull;

@Dependent
public class RoleMenuUtil implements Serializable {

    private static final long serialVersionUID = 1271785418174527973L;
    public static final Integer FORBIDDEN_MENU = -403;

    @Inject
    private ApplicationPropertiesService appPropsService;

    @Inject
    private MuiService muiService;

    public boolean canViewMenu(@NonNull final Set<RolesEntity> roles, @NonNull final MenuEntity menu) {
        // User is ADMIN, can view menu
        final Optional<RolesEntity> isAdmin = roles.stream().filter(Objects::nonNull).filter(RolesEntity::isAdmin)
                .findAny();
        if (isAdmin.isPresent()) {
            return true;
        }
        if (appPropsService.calculateAdminMode()) {
            return appPropsService.hasAccessAsAdmin(roles) && menuAccesibleForRole(menu, roles)
                    && menuRoleVisibleInAdminMode(menu, roles);
        }

        return menuAccesibleForRole(menu, roles);
    }

    private boolean menuRoleVisibleInAdminMode(@NonNull final MenuEntity menu, @NonNull final Set<RolesEntity> role) {
        final MenuRoleEntity menuRole = muiService.findMenuRoleByMenuAndRoles(menu, role);
        return (menuRole != null) && menuRole.getAdminModeVisible();
    }

    private boolean menuAccesibleForRole(@NonNull final MenuEntity menu, @NonNull final Set<RolesEntity> role) {
        return muiService.menuBelongsToRoles(menu, role);
    }

}
