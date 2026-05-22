package com.axiante.mui.webapp.template.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.utils.RoleMenuUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.tree.TreeNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@RunWith(MockitoJUnitRunner.class)
public class MenuProducerTest {

    @Mock
    private MuiService muiService;

    @Mock
    private RoleMenuUtil roleMenuUtil;

    @Spy
    private TreeNode rootTreeNode;

    @Spy
    @InjectMocks
    private MenuProducer menuProducer;

    @Mock
    private UsersEntity userAdmin;

    @Mock
    private UsersEntity userOverride;

    @Mock
    private UsersEntity userNotOverride;

    @Mock
    private RolesEntity roleAdmin;

    @Mock
    private RolesEntity roleOverride;

    @Mock
    private RolesEntity roleNotOverride;

    @Mock
    private MenuEntity rootMenu1;

    @Mock
    private MenuEntity rootMenu2;

    @Mock
    private MenuEntity menu11;

    @Mock
    private MenuEntity menu12;

    @Mock
    private MenuEntity menu13;

    @Mock
    private MenuEntity menu21;

    @Mock
    private MenuEntity menu22;

    // Menu Tree
    // ROOT1
    // |_ CHILD-1.1
    // |_ CHILD-1.2
    // |_ CHILD-1.3
    // ROOT2
    // |_ CHILD-2.1
    // |_ CHILD-2.2

    @Before
    public void setUp() throws Exception {
        when(userAdmin.getRoles()).thenReturn(new HashSet<>(Collections.singleton(roleAdmin)));
        when(userOverride.getRoles()).thenReturn(new HashSet<>(Collections.singleton(roleOverride)));
        when(userNotOverride.getRoles()).thenReturn(new HashSet<>(Collections.singleton(roleNotOverride)));
        mockMenu(rootMenu1, 1, null, "ROOT-1");
        mockMenu(rootMenu2, 2, null, "ROOT-2");
        mockMenu(menu11, 11, rootMenu1, "CHILD-1.1");
        mockMenu(menu12, 12, rootMenu1, "CHILD-1.2");
        mockMenu(menu13, 13, rootMenu1, "CHILD-1.3", true);
        mockMenu(menu21, 21, rootMenu2, "CHILD-2.1");
        mockMenu(menu22, 22, rootMenu2, "CHILD-2.2");
        mockMenuTree();
        when(muiService.readTopMenus()).thenReturn(Arrays.asList(rootMenu1, rootMenu2));
    }

    @Test(expected = Exception.class)
    public void readMenu_shouldThrowException_whenSomethingWhenWrong() throws Exception {
        when(muiService.readTopMenus()).thenThrow(Exception.class);
        menuProducer.readMenu(mock(UsersEntity.class));
    }

    @Test
    public void readMenu_givenNullUser_shouldReturnEmptyMenu() throws Exception {
        final MenuModel menuModel = menuProducer.readMenu(null);
        assertTrue(menuModel.getElements().isEmpty());
    }

    @Test
    public void readMenu_whenAdmin_givenAdminModeOn_shouldViewAllMenus() throws Exception {
        Set<RolesEntity> roleAdmin = new HashSet<>();
        roleAdmin.add(this.roleAdmin);
        when(roleMenuUtil.canViewMenu(roleAdmin, menu11)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleAdmin, menu12)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleAdmin, menu13)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleAdmin, menu21)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleAdmin, menu22)).thenReturn(true);
        final MenuModel menuModel = menuProducer.readMenu(userAdmin);
        assertNotNull(menuModel);
        assertEquals(2, menuModel.getElements().size());
        // Childs of rootMenu1
        final DefaultSubMenu rootMenu1 = (DefaultSubMenu) menuModel.getElements().get(0);
        assertEquals(3, rootMenu1.getElementsCount());
        final List<String> urls1 = rootMenu1.getElements().stream().map(m -> (String) ((DefaultMenuItem) m).getValue())
                .collect(Collectors.toList());
        assertTrue(urls1.contains("CHILD-1.1"));
        assertTrue(urls1.contains("CHILD-1.2"));
        assertTrue(urls1.contains("CHILD-1.3"));
        assertFalse(urls1.contains("CHILD-2.1"));
        assertFalse(urls1.contains("CHILD-2.2"));
        // Childs of rootMenu2
        final DefaultSubMenu rootMenu2 = (DefaultSubMenu) menuModel.getElements().get(1);
        assertEquals(2, rootMenu2.getElementsCount());
        final List<String> urls2 = rootMenu2.getElements().stream().map(m -> (String) ((DefaultMenuItem) m).getValue())
                .collect(Collectors.toList());
        assertFalse(urls2.contains("CHILD-1.1"));
        assertFalse(urls2.contains("CHILD-1.2"));
        assertFalse(urls2.contains("CHILD-1.3"));
        assertTrue(urls2.contains("CHILD-2.1"));
        assertTrue(urls2.contains("CHILD-2.2"));
    }

    @Test
    public void readMenu_whenNonAdminWithOverrideAndFlagAdminModeTrue_givenAdminModeOn_shouldGetMenus()
            throws Exception {
        Set<RolesEntity> roleOverride = new HashSet<>();
        roleOverride.add(this.roleOverride);

        when(roleMenuUtil.canViewMenu(roleOverride, menu11)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleOverride, menu12)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleOverride, menu13)).thenReturn(true);
        final MenuModel menuModel = menuProducer.readMenu(userOverride);
        assertNotNull(menuModel);
        assertEquals(1, menuModel.getElements().size());
        // Childs of rootMenu1
        final DefaultSubMenu rootMenu = (DefaultSubMenu) menuModel.getElements().get(0);
        assertEquals(3, rootMenu.getElementsCount());
        final List<String> urls = rootMenu.getElements().stream().map(m -> (String) ((DefaultMenuItem) m).getValue())
                .collect(Collectors.toList());
        assertTrue(urls.contains("CHILD-1.1"));
        assertTrue(urls.contains("CHILD-1.2"));
        assertTrue(urls.contains("CHILD-1.3"));
        assertFalse(urls.contains("CHILD-2.1"));
        assertFalse(urls.contains("CHILD-2.2"));
    }

    @Test
    public void readMenu_whenNonAdminWithOverrideAndFlagAdminModeFalse_givenAdminModeOn_shouldGetMenus()
            throws Exception {
        Set<RolesEntity> roleOverride = new HashSet<>();
        roleOverride.add(this.roleOverride);

        when(roleMenuUtil.canViewMenu(roleOverride, menu11)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleOverride, menu12)).thenReturn(false);
        when(roleMenuUtil.canViewMenu(roleOverride, menu13)).thenReturn(false);
        final MenuModel menuModel = menuProducer.readMenu(userOverride);
        assertNotNull(menuModel);
        assertEquals(1, menuModel.getElements().size());
        // Childs of rootMenu1
        final DefaultSubMenu rootMenu = (DefaultSubMenu) menuModel.getElements().get(0);
        assertEquals(1, rootMenu.getElementsCount());
        final List<String> urls = rootMenu.getElements().stream().map(m -> (String) ((DefaultMenuItem) m).getValue())
                .collect(Collectors.toList());
        assertTrue(urls.contains("CHILD-1.1"));
        assertFalse(urls.contains("CHILD-1.2"));
        assertFalse(urls.contains("CHILD-1.3"));
        assertFalse(urls.contains("CHILD-2.1"));
        assertFalse(urls.contains("CHILD-2.2"));
    }

    @Test
    public void readMenu_whenNonAdminWithoutOverrideAndFlagAdminModeTrue_givenAdminModeOff_shouldGetMenus()
            throws Exception {
        Set<RolesEntity> roleNotOverride = new HashSet<>();
        roleNotOverride.add(this.roleNotOverride);

        when(roleMenuUtil.canViewMenu(roleNotOverride, menu21)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleNotOverride, menu22)).thenReturn(true);
        final MenuModel menuModel = menuProducer.readMenu(userNotOverride);
        assertNotNull(menuModel);
        assertEquals(1, menuModel.getElements().size());
        // Childs of rootMenu1
        final DefaultSubMenu rootMenu = (DefaultSubMenu) menuModel.getElements().get(0);
        assertEquals(2, rootMenu.getElementsCount());
        final List<String> urls = rootMenu.getElements().stream().map(m -> (String) ((DefaultMenuItem) m).getValue())
                .collect(Collectors.toList());
        assertFalse(urls.contains("CHILD-1.1"));
        assertFalse(urls.contains("CHILD-1.2"));
        assertFalse(urls.contains("CHILD-1.3"));
        assertTrue(urls.contains("CHILD-2.1"));
        assertTrue(urls.contains("CHILD-2.2"));
    }

    @Test
    public void readMenu_whenNonAdminWithoutOverrideAndFlagAdminModeFalse_givenAdminModeOn_shouldNotGetMenus()
            throws Exception {
        final MenuModel menuModel = menuProducer.readMenu(userNotOverride);
        assertNotNull(menuModel);
        assertTrue(menuModel.getElements().isEmpty());
    }

    @Test
    public void readMenu_whenNonAdminWithoutOverrideAndFlagAdminModeFalse_givenAdminModeOff_shouldGetMenus()
            throws Exception {
        Set<RolesEntity> roleNotOverride = new HashSet<>();
        roleNotOverride.add(this.roleNotOverride);

        when(roleMenuUtil.canViewMenu(roleNotOverride, menu21)).thenReturn(true);
        when(roleMenuUtil.canViewMenu(roleNotOverride, menu22)).thenReturn(true);
        final MenuModel menuModel = menuProducer.readMenu(userNotOverride);
        assertNotNull(menuModel);
        assertEquals(1, menuModel.getElements().size());
        // Childs of rootMenu1
        final DefaultSubMenu rootMenu = (DefaultSubMenu) menuModel.getElements().get(0);
        assertEquals(2, rootMenu.getElementsCount());
        final List<String> urls = rootMenu.getElements().stream().map(m -> (String) ((DefaultMenuItem) m).getValue())
                .collect(Collectors.toList());
        assertFalse(urls.contains("CHILD-1.1"));
        assertFalse(urls.contains("CHILD-1.2"));
        assertFalse(urls.contains("CHILD-1.3"));
        assertTrue(urls.contains("CHILD-2.1"));
        assertTrue(urls.contains("CHILD-2.2"));
    }

    @Test(expected = NullPointerException.class)
    public void createItem_givenNullMenu_shouldThrowException() {
        menuProducer.createItem(null, "foo");
    }

    @Test(expected = NullPointerException.class)
    public void createItem_givenNullPath_shouldThrowException() {
        menuProducer.createItem(mock(MenuEntity.class), null);
    }

    @Test
    public void findPath_givenMenuWithoutParent_shouldReturnNull() {
        assertNull(menuProducer.findPath(rootMenu1));
    }

    @Test
    public void findPath_givenMenuWithParent_shouldBuildPathTree() {
        final MenuEntity menu = mock(MenuEntity.class);
        mockMenu(menu, 111, menu11, "CHILD-1.1.1");
        final String path = menuProducer.findPath(menu);
        assertNotNull(path);
        assertEquals("ROOT-1/CHILD-1.1", path);
    }

    private void mockMenu(MenuEntity menu, int id, MenuEntity parent, String label) {
        mockMenu(menu, id, parent, label, false);
    }

    private void mockMenu(MenuEntity menu, int id, MenuEntity parent, String label, boolean externalLink) {
        when(menu.getIdMenu()).thenReturn(id);
        when(menu.getParent()).thenReturn(parent);
        when(menu.getLabel()).thenReturn(label);
        when(menu.getUrl()).thenReturn("url/" + id);
        when(menu.getBean()).thenReturn("bean-" + id);
        when(menu.getOrderId()).thenReturn(id);
        when(menu.getExternalLink()).thenReturn(externalLink);
        if (externalLink) {
            when(menu.getUrl()).thenReturn("http://www.foo.com");
        }
    }

    private void mockMenuTree() {
        when(rootMenu1.getChildren()).thenReturn(Arrays.asList(menu11, menu12, menu13));
        when(rootMenu2.getChildren()).thenReturn(Arrays.asList(menu21, menu22));
    }
}