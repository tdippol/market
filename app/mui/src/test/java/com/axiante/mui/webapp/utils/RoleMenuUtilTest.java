package com.axiante.mui.webapp.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.MenuRoleEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;
import com.axiante.mui.persistence.service.MuiService;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleMenuUtilTest {

    @Mock
    private ApplicationPropertiesService appPropsService;

    @Mock
    private MuiService muiService;

    @InjectMocks
    private RoleMenuUtil util;

    // Useful entities
    private RolesEntity roleAdmin;
    private RolesEntity roleFoo;
    private MenuEntity menuFoo;
    private MenuRoleEntity menuRoleFoo;
    private Set<RolesEntity> roles = new HashSet<>();

    @Before
    public void setUp() {
        roleAdmin = mockRole("ADMIN");
        roleFoo = mockRole("ROLE-FOO");
        menuFoo = mock(MenuEntity.class);
        when(roleFoo.isAdmin()).thenCallRealMethod();
        when(roleAdmin.isAdmin()).thenCallRealMethod();
    }

    @Test
    public void canViewMenu_givenAdminUser_shouldReturnTrue() {
        roles.clear();
        roles.add(roleAdmin);
        roles.add(roleFoo);
        assertTrue(util.canViewMenu(roles, menuFoo));
        verify(appPropsService, never()).calculateAdminMode();
    }

    @Test
    public void canViewMenu_givenNonAdminUser_whenAdminModeOffAndMenuVisible_shouldReturnTrue() {
        roles.clear();
        roles.add(roleAdmin);
        roles.add(roleFoo);
        assertTrue(util.canViewMenu(roles, menuFoo));
    }

    @Test
    public void canViewMenu_givenNonAdminUser_whenAdminModeOffAndMenuNotVisible_shouldReturnFalse() {
        when(appPropsService.calculateAdminMode()).thenReturn(false);
        roles.clear();
        roles.add(roleFoo);
        assertFalse(util.canViewMenu(roles, menuFoo));
    }

    @Test
    public void canViewMenu_givenNonAdminUser_whenAdminModeOnAndRoleOverrideAndMenuNotVisible_shouldReturnFalse() {
        when(appPropsService.calculateAdminMode()).thenReturn(true);
        roles.clear();
        roles.add(roleFoo);
        assertFalse(util.canViewMenu(roles, menuFoo));
    }

    @Test
    public void canViewMenu_givenNonAdminUser_whenAdminModeOnAndRoleOverrideAndMenuAccessibleWithFlagVisibleTrue_shouldReturnTrue() {
        menuRoleFoo = mockMenuRole(menuFoo, true);
        when(appPropsService.calculateAdminMode()).thenReturn(true);
        when(appPropsService.hasAccessAsAdmin(roles)).thenReturn(true);
        when(muiService.findMenuRoleByMenuAndRoles(menuFoo, roles)).thenReturn(menuRoleFoo);
        when(muiService.menuBelongsToRoles(menuFoo, roles)).thenReturn(true);
        roles.clear();
        roles.add(roleFoo);

        assertTrue(util.canViewMenu(roles, menuFoo));
        verify(muiService, times(1)).findMenuRoleByMenuAndRoles(menuFoo, roles);
    }

    @Test
    public void canViewMenu_givenNonAdminUser_whenAdminModeOnAndRoleOverrideAndMenuAccessibleWithFlagVisibleFalse_shouldReturnFalse() {
        menuRoleFoo = mockMenuRole(menuFoo, false);
        roles.clear();
        roles.add(roleFoo);

        when(appPropsService.calculateAdminMode()).thenReturn(true);
        when(appPropsService.hasAccessAsAdmin(roles)).thenReturn(true);
        when(muiService.findMenuRoleByMenuAndRoles(menuFoo, roles)).thenReturn(menuRoleFoo);
        when(muiService.menuBelongsToRoles(menuFoo, roles)).thenReturn(true);
        roles.clear();
        roles.add(roleFoo);
        assertFalse(util.canViewMenu(roles, menuFoo));
        verify(muiService, times(1)).menuBelongsToRoles(menuFoo, roles);
    }

    @Test
    public void canViewMenu_givenNonAdminUser_whenAdminModeOnAndRoleNotOverrideAndMenuVisible_shouldReturnFalse() {
        when(appPropsService.calculateAdminMode()).thenReturn(true);
        roles.clear();
        roles.add(roleFoo);
        assertFalse(util.canViewMenu(roles, menuFoo));
    }

    @Test
    public void canViewMenu_givenNonAdminUser_whenAdminModeOnAndRoleNotOverrideAndMenuNotVisible_shouldReturnFalse() {
        when(appPropsService.calculateAdminMode()).thenReturn(true);
        roles.clear();
        roles.add(roleFoo);

        assertFalse(util.canViewMenu(roles, menuFoo));
    }

    @Test(expected = NullPointerException.class)
    public void canViewMenu_givenNullRole_shouldThrowException() {
        util.canViewMenu(null, menuFoo);
    }

    @Test(expected = NullPointerException.class)
    public void canViewMenu_givenNullMenu_shouldThrowException() {
        roles.clear();
        roles.add(roleAdmin);
        roles.add(roleFoo);
        util.canViewMenu(roles, null);
    }

    private RolesEntity mockRole(String name) {
        final RolesEntity mock = mock(RolesEntity.class);
        when(mock.getName()).thenReturn(name);
        return mock;
    }

    private MenuRoleEntity mockMenuRole(MenuEntity menu, boolean visible) {
        final MenuRoleEntity mock = mock(MenuRoleEntity.class);
        when(mock.getAdminModeVisible()).thenReturn(visible);
        return mock;
    }
}
