package com.axiante.mui.webapp.template.menu;

import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.utils.RoleMenuUtil;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.*;

@Dependent
@Slf4j
public class MenuProducer implements Serializable {
    private static final long serialVersionUID = 6984142821278613835L;
    public final static String PATH_SEPARATOR = "/";

    @Inject
    transient private MuiService muiService;

    @Inject
    transient private RoleMenuUtil roleMenuUtil;

    public MenuModel readMenu(final UsersEntity user) throws Exception {
        final MenuModel menu = new DefaultMenuModel();
        if (user != null) {
            final List<MenuEntity> menuList = new ArrayList<>();
            menuList.addAll(this.muiService.readTopMenus()); // leggo tutti i root menu
            menuList.sort(Comparator.comparing(MenuEntity::getOrderId).thenComparing(MenuEntity::getLabel));
            for (final MenuEntity entity : menuList) {
                final MenuElement ele = this.readEntity(entity, "", user.getRoles());
                if (ele != null) {
                    menu.addElement(ele);
                }
            }
        }
        return menu;
    }

    private MenuElement readEntity(@NonNull final MenuEntity entity, final String path, final Set<RolesEntity> roles) {
        final List<MenuEntity> children = entity.getChildren();
        if ((children != null) && (children.size() > 0)) {
            return this.createSubMenu(entity, path, roles);
        }
        return this.createMenuItem(entity, path, roles);
    }

    private DefaultSubMenu createSubMenu(@NonNull final MenuEntity entity, final String path,
                                         final Set<RolesEntity> roles) {
        final DefaultSubMenu menu = new DefaultSubMenu(entity.getLabel());
        final List<MenuEntity> children = entity.getChildren();
        children.sort(Comparator.comparing(MenuEntity::getOrderId).thenComparing(MenuEntity::getLabel));
        children.forEach(child -> {
            final MenuElement ele = this.readEntity(child, path.concat(PATH_SEPARATOR).concat(entity.getLabel()), roles);
            if (ele != null) {
                menu.getElements().add(ele);
            }
        });
        if (menu.getElements().size() == 0) {
            return null;
        }
        return menu;
    }

    private DefaultMenuItem createMenuItem(final MenuEntity menuEntity, final String path,
                                           final Set<RolesEntity> roles) {
        // TODO: check usage roleMenuUtil.canViewMenu
        return roleMenuUtil.canViewMenu(roles, menuEntity) ? this.createItem(menuEntity, path) : null;
    }

    public DefaultMenuItem createItem(@NonNull final MenuEntity menuEntity, @NonNull final String path) {
        final DefaultMenuItem menuItem = new DefaultMenuItem(menuEntity.getLabel());
        if (menuEntity.getExternalLink()) {
            menuItem.setUrl(menuEntity.getUrl());
            menuItem.setTarget("_blank");
        } else {
            final Map<String, List<String>> params = new HashMap<>();
            params.put("url", Collections.singletonList(menuEntity.getUrl()));
            params.put("bean", Collections.singletonList(menuEntity.getBean()));
            params.put("path", Collections.singletonList(path.concat(PATH_SEPARATOR).concat(menuEntity.getLabel())));
            params.put("idMenu", Collections.singletonList(menuEntity.getIdMenu().toString()));
            menuItem.setParams(params);
            menuItem.setCommand("#{templateView.menuItemClicked}");
            menuItem.setOnerror("redirectToExpiredPage()");
            menuItem.setUpdate("@(.updateContent)");
            menuItem.setOnclick("PF('globalSpinner').show()");
            menuItem.setOncomplete("PF('globalSpinner').hide();PF('mui-menu').hide();");
        }
        return menuItem;
    }

    /*
     * @Deprecated public void readPathFromMenuItem(final MenuEntity menuEntity,
     * final StringBuilder path) { if ( menuEntity != null ) { path.insert(0,
     * menuEntity.getLabel()).insert(0, PATH_SEPARATOR); MenuEntity parent =
     * menuEntity.getParent(); while(parent != null ) { path.insert(0,
     * parent.getLabel()).insert(0, PATH_SEPARATOR); parent = parent.getParent(); }
     * } }
     *
     * @Deprecated public TreeNode createTree() throws Exception { // leggi i menu e
     * crea la struttura ad albero final MenuEntity menuEntity = new
     * MenuEntity(null, null, "label", "url", "bean", null, false); final
     * CheckboxTreeNode root = new CheckboxTreeNode(menuEntity, null);
     *
     * final List<MenuEntity> menuList = this.muiService.readTopMenus();
     * menuList.sort(Comparator.comparing(MenuEntity::getOrderId));
     * root.setChildren(new ArrayList<TreeNode>()); menuList.forEach(menu->{
     * root.getChildren().add(this.createTreeNode(menu)); }); return root; }
     *
     * @Deprecated private CheckboxTreeNode createTreeNode(final MenuEntity entity)
     * { final CheckboxTreeNode treenode = new CheckboxTreeNode(entity); if (
     * !entity.isLeaf()){ treenode.setExpanded(true);
     * entity.getChildren().sort(Comparator.comparing(MenuEntity::getOrderId));
     * entity.getChildren().forEach(menu->{treenode.getChildren().add(this.
     * createTreeNode(menu));}); } return treenode; }
     *
     * @Deprecated public List<TreeNode> updateTreeWithSelections(final TreeNode
     * root, final List<MenuEntity> list, final List<TreeNode> selectedNodes) { if (
     * root.getChildCount() > 0 ) { final List<Integer> ids =
     * list.stream().map(MenuEntity::getIdMenu).distinct().collect(Collectors.toList
     * ()); this.selectNodes(root, ids, selectedNodes); } return selectedNodes; }
     *
     * @Deprecated private List<TreeNode> selectNodes(final TreeNode node, final
     * List<Integer> ids, @NonNull final List<TreeNode> selectedNodes) { if (
     * (node.getChildren() != null) && (node.getChildren().size() > 0) ) {
     * node.getChildren().forEach(n->this.selectNodes(n, ids, selectedNodes)); }
     * else { final MenuEntity data = (MenuEntity) node.getData(); final int id =
     * data.getIdMenu().intValue(); final boolean select =
     * ids.stream().map(Integer::intValue).filter(i->i==id).findFirst().orElse(-1)
     * == id; ((CheckboxTreeNode)node).setSelected(select, true); if ( select) {
     * selectedNodes.add(node); log.debug("selecting menu entry " +
     * data.getLabel()); } } return selectedNodes; }
     */

    /**
     * finds the path of the menu entity
     *
     * @param e
     * @return
     */
    public String findPath(final MenuEntity e) {
        String path = null;
        MenuEntity parent = e.getParent();
        while (parent != null) {
            path = (path == null ? parent.getLabel() : parent.getLabel() + PATH_SEPARATOR + path);
            parent = parent.getParent();
        }
        return path;
    }
}
