package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.MenuRoleEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

@Slf4j
public class AdminModeOverrideBackingBean implements FacesContextAware {
    @Getter
    private List<RolesEntity> roles;

    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private TreeNode menu = null;

    @Getter
    @Setter
    private RolesEntity selectedRole;

    @Getter
    @Setter
    private TreeNode[] selectedNodes;

    @Getter
    @Setter
    private List<MenuEntity> menusForRole;

    @Getter
    @Setter
    private boolean btnSelectAllMenusDisabled = true;

    @Getter
    @Setter
    private boolean btnDeselectAllMenusDisabled = true;

    private MuiService muiService;

    public AdminModeOverrideBackingBean(MuiService muiService) {
        this.muiService = muiService;
        loadRoles();
    }

    public void resetToDefault() {
        try {
            muiService.resetAllExceptions();
            loadRoles();
            executeScript("PF('resetDialog').hide()");
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                    "Tutte le eccezioni sono state resettate al valore di default"));
        } catch (Exception ex) {
            final String msg = "Errore reset eccezioni ai valori di default";
            log.error(msg, ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", msg));
        }
        loadTree();
        updateTreeMenuButtons();
    }

    public void toggleOverrideAdminForRole(RolesEntity role) {
        try {
            muiService.persistRole(role);
            final String msg = String.format("'Override Admin' settato a %s per il ruolo %s",
                    role.getOverrideAdmin(), role.getName());
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
        } catch (Exception ex) {
            final String msg = "Errore settando 'Override Admin' per il ruolo " + role.getName();
            log.error(msg, ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", msg));
        }
    }

    public void onRowSelected(SelectEvent event) {
        final RolesEntity role = (RolesEntity) event.getObject();
        // Load menu for given role and create tree
        loadMenuForRole(role);
        loadTree();
        updateTreeMenuButtons();
    }

    public void onTreeNodeSelect(NodeSelectEvent event) {
        changeAdminModeVisible((MenuEntity) event.getTreeNode().getData(), true);
    }

    public void onTreeNodeUnselect(NodeUnselectEvent event) {
        changeAdminModeVisible((MenuEntity) event.getTreeNode().getData(), false);
    }

    public void onTabChange(TabChangeEvent event) {
        if ("tab-sched_eccezioni".equals(event.getTab().getId())) {
            loadRoles();
        }
    }

    public void selectAllMenus() {
        changeAllAdminModeVisible(true);
        adjustTreeMenu(true);
        loadTree();
        updateTreeMenuButtons();
    }

    public void deselectAllMenus() {
        changeAllAdminModeVisible(false);
        adjustTreeMenu(false);
        loadTree();
        updateTreeMenuButtons();
    }

    public void selectAllRoles() {
        changeAllOverrideAdmin(true);
    }

    public void deselectAllRoles() {
        changeAllOverrideAdmin(false);
    }

    private void changeAllOverrideAdmin(final boolean visible) {
        try {
            for (RolesEntity entity : roles) {
                entity.setOverrideAdmin(visible);
                muiService.persistRole(entity);
            }
            final String msg = String.format("'Override Admin' settato a %s su tutti i ruoli", visible);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
        } catch (Exception ex) {
            final String msg = String.format("Errore settando 'Override Admin' a %s su tutti i ruoli", visible);
            log.error(msg, ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", msg));
        }
    }

    private void changeAllAdminModeVisible(final boolean visible) {
        menu.getChildren().stream()
                .map(node -> (MenuEntity) node.getData())
                .forEach(entity -> changeAdminModeVisible(entity, visible));

    }

    private void adjustTreeMenu(final boolean visible) {
        for (TreeNode node : menu.getChildren()) {
            node.setSelected(visible);
        }
    }

    private void loadRoles() {
        try {
            // Skip ADMIN role
            roles = muiService.readRoles().stream()
                    .filter(r -> !"ADMIN".equals(r.getName()))
                    .sorted(Comparator.comparing(RolesEntity::getName, String::compareToIgnoreCase))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.warn("Unable to load roles", ex);
            roles = new ArrayList<>();
        }
    }

    private void loadMenuForRole(RolesEntity role) {
        try {
            menusForRole = muiService.readMenus(role);
        } catch (Exception ex) {
            log.warn(String.format("Unable to load menus for role '%s'", role.getName()), ex);
            menusForRole = new ArrayList<>();
        }
    }

    private void changeAdminModeVisible(final MenuEntity menuEntity, final boolean visible) {
        try {
            // Recupero il menu corrente, più tutti i figli (e nipoti, ecc.)
            List<MenuRoleEntity> list = new ArrayList<>();
            final MenuRoleEntity menuRole = muiService.findMenuRoleByMenuAndRole(menuEntity, selectedRole);
            if (menuRole != null) {
                list.add(menuRole);
            }
            if (menuEntity.hasChildren()) {
                descendMenuTree(list, menuEntity);
            }
            // Salgo il menu con tutti i padri
            if (menuEntity.getParent() != null) {
                ascendMenuTree(list, menuEntity.getParent(), visible);
            }

            // Ho tutti i menu ai quali settare il flag
            muiService.changeAdminModeVisible(list, visible);
            final String msg = String.format("Flag adminModeVisible settato a '%s' per menu '%s' con ruolo '%s'",
                    visible, menuEntity.getLabel(), selectedRole.getName());
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
        } catch (Exception ex) {
            final String msg = String.format("Errore settando flag adminModeVisible per menu '%s' con ruolo '%s'",
                    menuEntity.getLabel(), selectedRole.getName());
            log.error(msg, ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", msg));
            loadTree();
        }
    }

    private void ascendMenuTree(List<MenuRoleEntity> list, MenuEntity menuEntity, boolean visible) {
        if (visible && menusForRole.contains(menuEntity)) {
            final MenuRoleEntity menuRole = muiService.findMenuRoleByMenuAndRole(menuEntity, selectedRole);
            if (menuRole != null) {
                list.add(menuRole);
            }
        }
        if (menuEntity.getParent() != null) {
            ascendMenuTree(list, menuEntity.getParent(), visible);
        }
    }

    private void descendMenuTree(List<MenuRoleEntity> list, MenuEntity menuEntity) {
        menuEntity.getChildren().forEach(m -> {
            if (m.isLeaf() && menusForRole.contains(m)) {
                final MenuRoleEntity menuRole = muiService.findMenuRoleByMenuAndRole(m, selectedRole);
                if (menuRole != null) {
                    list.add(menuRole);
                }
            }
            if (m.hasChildren()) {
                descendMenuTree(list, m);
            }
        });
    }

    private void loadTree() {
        setMenu(new CheckboxTreeNode("Menu", null));
        try {
            final List<MenuEntity> menuList = muiService.readTopMenus();
            menuList.stream().sorted(Comparator.comparing(MenuEntity::getOrderId))
                    .forEach(menu -> createTreeNode(menu, getMenu()));
        } catch (Exception ex) {
            log.error("Error loading tree menu", ex);
        }
        getMenu().setExpanded(true);
    }

    private void createTreeNode(final MenuEntity menu, final TreeNode parent) {
        final CheckboxTreeNode node = new CheckboxTreeNode(getNodeType(menu), menu, parent);
        if (menu.isLeaf()) {
            if (isMenuAccessibleBySelectedRole(menu)) {
                final MenuRoleEntity menuRole = muiService.findMenuRoleByMenuAndRole(menu, selectedRole);
                if (menuRole != null) {
                    node.setSelected(menuRole.getAdminModeVisible());
                }
            } else {
                parent.getChildren().remove(node);
            }
        } else {
            menu.getChildren().forEach(child -> createTreeNode(child, node));
            if (node.getChildCount() == 0) {
                parent.getChildren().remove(node);
            }
        }
        node.setExpanded(true);
    }

    private boolean isMenuAccessibleBySelectedRole(final MenuEntity menu) {
        if (selectedRole == null) {
            return false;
        }
        if (RolesEntity.ADMIN_ROLE.equals(selectedRole.getName())) {
            return true;
        }
        final Set<RolesEntity> roles = menu.getRoles();
        if (roles == null || roles.isEmpty()) {
            return false;
        }
        return roles.stream().anyMatch(r -> r.getName().equals(selectedRole.getName()));
    }

    private String getNodeType(@NonNull final MenuEntity entity) {
        if (entity.isLeaf()) {
            if (entity.getExternalLink()) {
                return "external";
            } else {
                return "internal";
            }
        } else {
            return "folder";
        }
    }

    private void updateTreeMenuButtons() {
        btnSelectAllMenusDisabled = menusForRole == null || menusForRole.isEmpty();
        btnDeselectAllMenusDisabled = menusForRole == null || menusForRole.isEmpty();
    }
}
