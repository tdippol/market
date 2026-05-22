package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.backing.ConfigurationCatalog;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.filter.utils.FilterUtils;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.admin.menu.AddMenuBean;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@MuiViewModel("gestioneMenuRole")
@Named("gestioneMenuRole")
@SessionScoped
@Slf4j
public class GestioneMenuRoleView extends AbstractAdminView {
    private static final long serialVersionUID = 4243646987259535673L;

    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private TreeNode menu = null;

    @Getter
    private TreeNode previewMenu = null;

    @Inject
    @Getter(value = AccessLevel.PRIVATE)
    private MuiService service;
    @Inject
    transient ConfigurationCatalog configurationCatalog;
    @Getter
    private TreeNode selectedNode;
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private MenuEntity selectedMenuNode;
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private List<RolesEntity> roles;
    @Getter
    @Setter
    private List<RolesEntity> selectedRoles;

    @Inject
    FilterUtils filterUtils;

    @Getter
    private final LinkedHashMap<String, String> availableTemplates = new LinkedHashMap<String, String>() {
        // TODO: these should go into db
        private static final long serialVersionUID = -2687057057901241340L;

        {
            this.put("1-0-0", "layouts/1-0-0.xhtml");
            this.put("1-1-0", "layouts/1-1-0.xhtml");
            this.put("1-1-1", "layouts/1-1-1.xhtml");
            this.put("1-*", "layouts/1-infinite.xhtml");
            this.put("2-0-0", "layouts/2-0-0.xhtml");
            this.put("2-1-0", "layouts/2-1-0.xhtml");
            this.put("2-1-1", "layouts/2-1-1.xhtml");
        }
    };
    @Getter
    private boolean editingGrid = true;
    @Getter
    private boolean canEditConfig = false;
    @Setter
    @Getter
    private String editingGridJson = null;
    @Setter
    @Getter
    private String editingFilterJson = null;
    @Setter
    @Getter
    private RolesEntity selectedPreviewRole;
    @Getter
    private AddMenuBean newMenuBean;

    @Getter
    private ConfigurationEntity globalFilters;

    @Getter
    private ConfigurationEntity welcomeFilters;

    @PostConstruct
    public void init() {
        this.loadMenu();
        if (getSelectedMenuNode() == null) {
            setSelectedNode(getMenu());
        }
        try {
            setRoles(getService().readRoles());
            getRoles().sort(Comparator.comparing(RolesEntity::getName));
        } catch (final Exception e) {
            log.error("Error reading roles", e);
        }
        if (getSelectedNode() != null) {
            getSelectedNode().setSelected(true);
        }
        this.loadGlobalFilters();
        this.loadWelcomeFilters();
    }

    private void loadMenu() {
        MenuEntity selectedMenuNode = null;
        try {
            final List<MenuEntity> menuList = this.service.readTopMenus();

            if (menuList.size() > 0) {
                menuList.sort(Comparator.comparing(MenuEntity::getOrderId));
                if (selectedMenuNode == null) {
                    selectedMenuNode = menuList.get(0);
                }
            }
            this.loadMenu(selectedMenuNode);
        } catch (final Exception e) {
            log.error("Error reading menu", e);
        }
    }

    public void loadMenu(final MenuEntity selectedMenuNode) {
        setMenu(new DefaultTreeNode("Menu", null));
        try {
            final List<MenuEntity> menuList = this.service.readTopMenus();
            menuList.stream().sorted(Comparator.comparing(MenuEntity::getOrderId))
                    .forEach(m -> this.createNodes(m, getMenu(), selectedMenuNode));
        } catch (final Exception e) {
            log.error("error reading menu", e);
        }
        getMenu().setExpanded(true);
    }

    private TreeNode createNodes(final MenuEntity entity, final TreeNode parent, final MenuEntity selectedNode) {
        final TreeNode node = new DefaultTreeNode(this.getNodeType(entity), entity, parent);
        node.setExpanded(true);
        if ((selectedNode != null) && entity.getIdMenu().equals(selectedNode.getIdMenu())) {
            setSelectedMenuNode(selectedNode);
            setSelectedNode(node);
        }

        if (entity.hasChildren()) {
            final List<MenuEntity> list = entity.getChildren();
            list.sort(Comparator.comparing(MenuEntity::getOrderId)
                    .thenComparing(Comparator.comparing(MenuEntity::getLabel)));
            for (final MenuEntity child : list) {
                this.createNodes(child, node, selectedNode);
            }
        }
        return node;
    }

    private ConfigurationEntity readConfigurationEntity(@NonNull final Integer idMenu,
                                                        @NonNull final ConfigurationTypes type) {
        return service.findConfiguration(idMenu, type);
    }

    public void loadWelcomeFilters() {
        boolean showMessage = false;
        FacesMessage message = null;
        try {
            // Welcome filters are the filters with id_menu = -1
            this.welcomeFilters = this.readConfigurationEntity(-1, ConfigurationTypes.FILTER);
            if (this.welcomeFilters == null) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Configurazione filtri in Welcome non trovata!",
                        "Il bottone di attivazione dei filtri nella welcome page è disattivato");
                showMessage = true;
            }
        } catch (Exception e) {
            showMessage = true;
            log.error("Error reading welcome page configuration", e);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore lettura filtri in Welcome non trovata!",
                    "Il bottone di attivazione dei filtri nella welcome page è disattivato");
        }
        if (showMessage) {
            this.addMessage(null, message);
            this.welcomeFilters = null;
        }
    }

    private void loadGlobalFilters() {
        boolean showMessage = false;
        try {
            final List<ConfigurationEntity> list = this.service.readConfigurations(ConfigurationTypes.GLOBAL);
            if (list != null) {
                if (list.size() > 1) {
                    log.error("there are " + list.size() + " entries for Global filters while I was expecting 1");
                    showMessage = true;
                } else {
                    this.globalFilters = list.get(0);
                }
            } else {
                showMessage = true;
            }
        } catch (final Exception e) {
            showMessage = true;
            log.error("error loading global filters", e);
        }
        if (showMessage) {
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento",
                    "Impossibile caricare i filtri globali"));
            this.globalFilters = null;
        }
    }

    private String getNodeType(@NonNull final MenuEntity entity) {
        if (!entity.isLeaf()) {
            return "folder";
        }
        if (entity.getExternalLink()) {
            return "external";
        }
        return "internal";
    }

    public void setSelectedNode(final TreeNode selectedNode) {
        if (selectedNode != null) {
            this.selectedNode = selectedNode;
            if (this.selectedRoles == null) {
                this.selectedRoles = new ArrayList<>();
            }
            this.selectedRoles.clear();
            setSelectedMenuNode((MenuEntity) selectedNode.getData());
            if ((this.selectedMenuNode != null) && (getSelectedMenuNode().getRoles() != null)) {
                this.selectedRoles.addAll(((MenuEntity) selectedNode.getData()).getRoles());
            } else {
                log.debug("trying to set a null roles list");
            }
            this.selectedRoles.sort(Comparator.comparing(RolesEntity::getName));
            // prepareConfigrationEditing();
            this.canEditConfig = getSelectedMenuNode().isLeaf();
        } else {
            log.debug("trying to set a null tree-node");
            setSelectedMenuNode(null);
        }
    }

    public void updateRolesAssociation() {
        final MenuEntity menu = (MenuEntity) this.selectedNode.getData();
        if ((this.selectedRoles != null) && (menu != null)) {
            final Set<RolesEntity> roles = menu.getRoles();
            try {
                // ruoli da rimuovere: quelli non presenti in selectedRoles
                final List<RolesEntity> toBeRemoved = roles.stream().filter(r -> !this.selectedRoles.contains(r))
                        .collect(Collectors.toList());
                for (final RolesEntity role : toBeRemoved) {
                    this.removeRole(role, menu);
                    this.service.persistRole(role);
                }
                // ruoli da aggiungere : tutti quelli presenti in selectedRoles non in roles
                final List<RolesEntity> toBeAdded = this.selectedRoles.stream().filter(r -> !roles.contains(r))
                        .collect(Collectors.toList());
                for (final RolesEntity role : toBeAdded) {
                    this.addRole(role, menu);
                    this.service.persistRole(role);
                }
                this.service.persistMenu(menu);
            } catch (final Exception ex) {
                log.error("error saving menu entry " + menu.getLabel(), ex);
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR",
                        "error saving menu entry " + menu.getLabel()));
            }
        } else {
            log.error("trying to set roles on null menu entity");
            this.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "trying to set roles on null menu entity"));
        }
    }

    /**
     * rimuove il menu dal ruolo. se il menu e' un parent allora lo rimuove da tutti
     * i figli. Una volta finito coi figli mette a posto il percorso fino alla root:
     * se non ci sono fratelli che hanno il ruolo allora lo toglie al padre
     *
     * @param role
     * @param menu
     */
    private void removeRole(@NonNull final RolesEntity role, @NonNull final MenuEntity menu) {
        this.removeRole(role, menu, false);
        this.adjustParentRoleRemoval(role, menu);
    }

    /**
     * rimuove il menu dal ruolo. se il menu e' un parent allora lo rimuove da tutti
     * i figli. Una volta finito coi figli, se il parametro
     *
     * <pre>
     * adjustParent
     * </pre>
     * <p>
     * e' true, mette a posto il percorso fino alla root: se non ci sono fratelli
     * che hanno il ruolo allora lo toglie al padre
     *
     * @param role
     * @param menu
     * @param adjustParent
     */
    private void removeRole(@NonNull final RolesEntity role, @NonNull final MenuEntity menu,
                            final boolean adjustParent) {
        role.removeMenu(menu);
        /*
         * se non sono una foglia: controlla se i miei figli hanno quel ruolo : se lo
         * hanno lo tolgo
         */
        if (!menu.isLeaf()) {
            menu.getChildren().forEach(child -> this.removeRole(role, child, adjustParent));
        }
        if (adjustParent) {
            /*
             * adesso sali : se mio padre non ha altri figli con quel ruolo e' inutile che
             * abbia il ruolo
             */
            this.adjustParentRoleRemoval(role, menu);
        }
        try {
            this.service.persistMenu(menu);
        } catch (final Exception e) {
            log.error("error saving menu state ", e);
        }
    }

    /**
     * se il padre del nodo corrente non ha figli (incluso in nodo corrente) che
     * hanno il ruolo allora lo toglie anche dal padre ripercorrendo l'albero del
     * menu fino alla root e aggiustando il ruolo
     *
     * @param role
     * @param menu
     */
    private void adjustParentRoleRemoval(@NonNull final RolesEntity role, @NonNull final MenuEntity menu) {
        MenuEntity parent = menu.getParent();
        while (parent != null) {
            final boolean roleNeeded = parent.getChildren().stream().map(MenuEntity::getRoles) // get all the roles :
                    // Stream<List<RolesEntity>>
                    .flatMap(Set::stream) // get the stream of roles : Stream<RolesEntity>
                    .distinct() // remove duplicates
                    .anyMatch(r -> r.getName().equals(role.getName()));
            if (roleNeeded) {
                // i need the role, stop here
                parent = null;
            } else {
                role.removeMenu(parent);
                try {
                    this.service.persistMenu(parent);
                } catch (final Exception e) {
                    log.error("error saving menu state", e);
                }
                parent = parent.getParent();
            }
        }
    }

    /**
     * Aggiunge un ruolo ad una voce di menu ed ai suoi figli. Una volta finito si
     * assicura che esista il percorso per il ruolo dalla radice alla voce di menu
     * corrente
     *
     * @param role
     * @param menu
     */
    private void addRole(@NonNull final RolesEntity role, @NonNull final MenuEntity menu) {
        this.addRole(role, menu, false); // aggiungi il ruolo, lascia stare i parent
        this.adjustParentRoleAdd(role, menu); // metti a posto i padri
    }

    /**
     * Aggiunge un ruolo ad una voce di menu ed ai suoi figli. Se il parametro
     *
     * <pre>
     * adjustParent
     * </pre>
     * <p>
     * e' true, una volta finito, si assicura che esista il percorso per il ruolo
     * dalla radice alla voce di menu corrente
     *
     * @param role
     * @param menu
     * @param adjustParent
     */
    private void addRole(@NonNull final RolesEntity role, @NonNull final MenuEntity menu, final boolean adjustParent) {
        role.addMenu(menu);
        if (!menu.isLeaf()) {
            /*
             * non sono una foglia, aggiungi il ruolo a tutti i miei figli
             */
            menu.getChildren().forEach(child -> this.addRole(role, child, false));
        }
        if (adjustParent) {
            // aggiungi a mio padre e basta ( non ai miei fratelli )
            this.adjustParentRoleAdd(role, menu);
        }
        try {
            this.service.persistMenu(menu);
        } catch (final Exception e) {
            log.error("error saving menu state", e);
        }
    }

    /**
     * setta il ruolo per l'elemento corrente. Una volta settato risale il nodo
     * padre per fare lo stesso fino ad arrivare alla radice
     *
     * @param role
     * @param menu
     */
    private void adjustParentRoleAdd(@NonNull final RolesEntity role, @NonNull final MenuEntity menu) {
        MenuEntity parent = menu.getParent();
        while (parent != null) {
            role.addMenu(parent);
            parent = parent.getParent();
        }

    }

    public void expandAll() {
        this.expandOrCollapse(this.menu, false);
    }

    public void collapseAll() {
        this.expandOrCollapse(this.menu, true);
    }

    private void expandOrCollapse(final TreeNode node, final boolean collapse) {
        if (node.getChildCount() > 0) {
            node.getChildren().forEach(c -> this.expandOrCollapse(c, collapse));
        }
        node.setExpanded(!collapse);
    }

    public void onDragDrop(final TreeDragDropEvent event) {
        final TreeNode dragNode = event.getDragNode(); // lui e'quello che si sposta
        final TreeNode dropNode = event.getDropNode(); // lui e'la destinazione
        FacesMessage message = null;
        MenuEntity drop = (MenuEntity) dropNode.getData();

        if (drop.isLeaf()) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR",
                    "Drop point is a leaf: you should change the nature of the node first");
            // ricaricare il menu
            this.loadMenu(this.selectedMenuNode);
        } else {
            MenuEntity drag = (MenuEntity) dragNode.getData();
            drag.setParent(drop);
            try {
                // metti a posto l'indice dei menu entity all'interno del drop node
                MenuEntity indexed = null;
                for (int i = 0; i < dropNode.getChildCount(); ++i) {
                    indexed = (MenuEntity) dropNode.getChildren().get(i).getData();
                    if (indexed.getIdMenu() != drag.getIdMenu()) {
                        indexed.setOrderId(i);
                        this.service.persistMenu(indexed);
                    } else {
                        drag.setOrderId(i);
                    }
                }
                drag = this.service.persistMenu(drag);
                drop = this.service.persistMenu(drop);

            } catch (final Exception e) {
                log.error("error saving layout ", e);
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR",
                        "Error saving menu, please reload the page");
            }
        }
        if (message != null) {
            this.addMessage(null, message);
        }
    }

    public boolean getSelectedNodeEditable() {
        if (this.selectedNode != null) {
            return ((MenuEntity) this.selectedNode.getData()).isLeaf();
        }
        return false;
    }

    public void updateSelectedNode() {
        if (this.selectedNode != null) {
            if (getNewMenuBean() != null) {
                setSelectedMenuNode(getNewMenuBean().getUpdatedMenu());
            } else {
                // edit label
            }
            try {
                final MenuEntity oldNode = this.service.findMenu(getSelectedMenuNode().getIdMenu());

                if ((oldNode.getExternalLink() != getSelectedMenuNode().getExternalLink())
                        || (oldNode.getTemplate() != getSelectedMenuNode().getTemplate())) {
                    // cambiato il tipo
                    if (getSelectedMenuNode().getExternalLink()) {
                        // devo annullare le configurazioni
                        getSelectedMenuNode().getConfigurations().stream().forEach(config -> {
                            config.setRevisionDate(new Date(System.currentTimeMillis()));
                            config.setLastEditor(this.getCurrentUser().getName());
                            try {
                                this.service.persistConfigurations(config);
                            } catch (final Exception e) {
                                log.error("error deleting configuration", e);
                            }
                        });
                    } else {
                        // devo creare le configurazioni
                        this.createConfiguration(this.selectedMenuNode);
                    }
                }
                this.service.persistMenu(this.selectedMenuNode);
                ((DefaultTreeNode) this.selectedNode).setType(this.getNodeType(this.selectedMenuNode));
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "Node updated"));
            } catch (final Exception e) {
                log.error("Error updating node " + this.selectedNode.getData(), e);
                this.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "Node update failed, please check logs"));
            }
        }
    }

    public void resetSelectedNode() {
        if (this.selectedNode != null) {
            try {
                final MenuEntity selected = (MenuEntity) this.selectedNode.getData();
                final MenuEntity reset = this.service.findMenu(selected.getIdMenu());
                if (reset != null) {
                    ((DefaultTreeNode) this.selectedNode).setData(reset);
                    ((DefaultTreeNode) this.selectedNode).setType(this.getNodeType(reset));
                } else {
                    log.error("Error resetting node id " + selected.getIdMenu() + ": the menu node has been deleted!");
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR",
                            "Il nodo selezionato risulta rimosso. Ricaricare la pagina"));
                }
            } catch (final Exception e) {
                log.error("error resetting node ", e);
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR",
                        "Reset del nodo fallito. Controllare i log"));
            }
        }
    }

    public void deleteSelectedNode() {
        if (this.selectedNode != null) {
            try {
                this.selectedNode.getParent().getChildren().remove(this.selectedNode);
                this.service.removeMenu(this.selectedMenuNode);
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "Nodo rimosso"));
            } catch (final Exception e) {
                log.error("error deleting menu node", e);
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR",
                        "Cancellazione fallita. Controllare i log"));
            }
        }
    }

    public void prepareConfigrationEditing() {
        setEditingGrid(true);
    }

    public void setEditingGrid(final boolean value) {
        this.editingGrid = value;
        this.readEditingJson();
    }

    private String getCurrentConfiguration(@NonNull final ConfigurationTypes type, boolean dynamic) {
        String result = null;
        try {
            final ConfigurationEntity e = getCurrentConfiguration(type);
            if (e != null) {
                result = e.getJson();
                if (StringUtils.isBlank(result)) {
                    result = "";
                }
                result = JsonUtils.prettyPrint(result);
            } else if (dynamic) {
                this.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione !", "Configurazione di tipo " + type
                                + " non presente per la voce di menu " + getSelectedMenuNode().getLabel()));
                result = "";
            } else {
                log.warn("la configurazione dovrebbe essere stata creata in getCurrentConfiguration ....");
                log.warn("configurazione non presente: ne creo una");
                setSelectedMenuNode(fixMissingConfiguration(this.selectedMenuNode));
                final ConfigurationEntity fixed = getCurrentConfiguration(type);
                if (fixed != null) {
                    // devo aggiornare il nodo
                    ((DefaultTreeNode) getSelectedNode()).setData(getSelectedMenuNode());
                    // adesso aggiorno il testo da editare
                    result = JsonUtils.prettyPrint(fixed.getJson());
                } else {
                    log.error("impossibile sanificare la configurazione per la voce di menu "
                            + getSelectedMenuNode().getIdMenu());
                    result = "";
                }
            }
        } catch (Exception ex) {
            log.error("Unexpected error reading configuration type " + type + " on menu "
                    + getSelectedMenuNode().getLabel(), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione!",
                    "Errore lettura configurazione per il nodo " + ex.getMessage()));
        }
        return result;
    }

    private ConfigurationEntity getCurrentConfiguration(@NonNull final ConfigurationTypes type) {
        if (type.equals(ConfigurationTypes.GRID) || type.equals(ConfigurationTypes.FILTER)) {
            ConfigurationEntity result = null;
            List<ConfigurationEntity> list = getSelectedMenuNode().getConfigurations().stream()
                    .filter(c -> c.getRevisionDate() == null).filter(c -> c.getType().equals(type))
                    .collect(Collectors.toList());
            if ((list != null) && (list.size() > 1)) {
                list.sort(Comparator.comparing(ConfigurationEntity::getIdConfiguration));
                result = list.get(list.size() - 1);
            }
            if (result == null) {
                // non ci sono configurazioni disponibili: ne creo una
                fixMissingConfiguration(getSelectedMenuNode());
                // e ritorno la configurazione del tipo che sto cercando
                result = getSelectedMenuNode().getConfigurations().stream().filter(c -> c.getRevisionDate() == null)
                        .filter(c -> c.getType().equals(type)).collect(Collectors.toList()).get(0);
            } else if (list.size() > 1) {
                // se ci sono + configurazioni allora prendi l'ultima in termini di id e metti
                // tutte le altre con revision date = oggi
                for (ConfigurationEntity configuration : list) {
                    if (!(configuration.getIdConfiguration() == result.getIdConfiguration())) {
                        // setta il last revision date
                        try {
                            configuration.setLastEditor("AUTOMATIC");
                            configuration.setRevisionDate(new Date(System.currentTimeMillis()));
                            service.persistConfigurations(configuration);
                        } catch (Exception e) {
                            log.error("The configuration with id " + configuration.getIdConfiguration()
                                    + " should have a revision date but it couldn't be updated", e);
                        }
                    }
                }
            }
            return result;
        }
        return null;
    }

    private void readEditingJson() {
        if ((getSelectedMenuNode() != null) && getSelectedMenuNode().isLeaf()) {
            AddMenuBean tester = new AddMenuBean(getSelectedMenuNode());
            if (tester.isExternal()) {
                this.editingGridJson = "";
                this.editingFilterJson = "";
            } else {
                editingGridJson = getCurrentConfiguration(ConfigurationTypes.GRID, tester.isDynamic());
                editingFilterJson = getCurrentConfiguration(ConfigurationTypes.FILTER, tester.isDynamic());
            }
        } else {
            log.error("readEditingJson has not selected a configuration as there are none");
            this.editingGridJson = null;
            this.editingFilterJson = null;
        }
    }

    private MenuEntity fixMissingConfiguration(@NonNull MenuEntity e) {
        Set<ConfigurationEntity> configurations = e.getConfigurations().stream()
                .filter(c -> c.getRevisionDate() == null).collect(Collectors.toSet());
        boolean createGrid = false;
        boolean createFilter = false;
        if ((configurations == null) || (configurations.size() == 0)) {
            // tutte da fare
            createGrid = true;
            createFilter = true;
        } else if (configurations.size() == 1) {
            // fai la mancante
            ConfigurationTypes type = configurations.iterator().next().getType();
            if (type == null) {
                // tutte da fare
            } else {
                createGrid = type.equals(ConfigurationTypes.FILTER);
                createFilter = type.equals(ConfigurationTypes.GRID);
            }
        }
        boolean error = false;
        if (createGrid) {
            ConfigurationEntity entity = new ConfigurationEntity();
            entity.setJson("{}");
            entity.setType(ConfigurationTypes.GRID);
            e.addConfiguration(entity);
            try {
                e = this.service.persistMenu(e);
            } catch (Exception ex) {
                log.error(
                        "Errore durante la creazione di emergenza del nodo di configurazione di tipo Griglia per la voce di menu "
                                + e.getIdMenu(),
                        ex);
                error = true;
            }
        }
        if (createFilter) {
            ConfigurationEntity entity = new ConfigurationEntity();
            entity.setJson("{}");
            entity.setType(ConfigurationTypes.FILTER);
            e.addConfiguration(entity);
            try {
                e = this.service.persistMenu(e);
            } catch (Exception ex) {
                log.error(
                        "Errore durante la creazione di emergenza del nodo di configurazione di tipo Filtro per la voce di menu "
                                + e.getIdMenu(),
                        ex);
                error = true;
            }
        }
        if (error) {
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione !",
                    "Configurazione non presente per la voce di menu " + getSelectedMenuNode().getLabel()
                            + ".\nIl risultato delle operazioni su questa configurazione non e' prevedibile !"));
        }
        return e;
    }

    public void updateConfiguration() {
        boolean filterChanged = false;
        boolean gridChanged = false;
        try {
            ConfigurationEntity old = getCurrentConfiguration(ConfigurationTypes.GRID);
            if (JsonUtils.validate(editingGridJson) == null) {
                gridChanged = hasChanged(old.getJson(), editingGridJson);
                if (gridChanged) {
                    updateConfiguration(ConfigurationTypes.GRID);
                }
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di validazione",
                        "json configurazione griglia non valido"));
            }
            old = getCurrentConfiguration(ConfigurationTypes.FILTER);
            if (JsonUtils.validate(editingFilterJson) == null) {
                filterChanged = hasChanged(old.getJson(), editingFilterJson);
                if (filterChanged) {
                    updateConfiguration(ConfigurationTypes.FILTER);
                }
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di validazione",
                        "json configurazione filtri non valido"));
            }
            if (!gridChanged && !filterChanged) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nothing to save", ""));
            }
        } catch (Exception ex) {
            log.error("Error update configuration for menu " + getSelectedMenuNode().getLabel(), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione",
                    "Errore update configurazione " + ex.getMessage()));
        }
    }

    private boolean hasChanged(String currentJson, String updatedJson) {
        try {
            if ((currentJson != null) && (updatedJson != null)) {
                ObjectMapper mapper = JsonUtils.getMapper();
                return !mapper.readTree(currentJson).equals(mapper.readTree(updatedJson));
            }
            if (updatedJson == null) {
                return false;
            }
        } catch (IOException e) {
            log.error("error checking json strings", e);
        }
        return true;
    }

    private void updateConfiguration(final ConfigurationTypes filter) {
        if (this.selectedMenuNode != null) {
            // prendi la configurazione che stai editando
            ConfigurationEntity e = getSelectedMenuNode().getConfigurations().stream()
                    .filter(c -> c.getType().equals(filter)).filter(c -> c.getRevisionDate() == null).findAny()
                    .orElse(null);
            if (filter.equals(ConfigurationTypes.GRID)) {

                String messaggio = null;
                if (!this.validate(editingGridJson)) {
                    messaggio = "Validazione errata";

                } else if (!this.checkMandatoryConfigurationSettins()) {
                    messaggio = "Campi obbligatori mancanti";
                } else {
                    // controlla che non ci siano duplicati all'interno della configurazione
                    final Map<String, Configuration> configurations = configurationCatalog
                            .readConfiguration(this.editingGridJson);
                    final List<String> actions = getActionsFromCurrentConfiguration(configurations);
                    if ((actions != null) && !actions.isEmpty()) {
                        List<String> duplicatedActions = findDuplicatedActionsInList(actions);
                        if ((duplicatedActions != null) && !duplicatedActions.isEmpty()) {
                            if (duplicatedActions.size() == 1) {
                                messaggio = String.format(
                                        "Codice action [%s] duplicata all'interno della configurazione corrente",
                                        duplicatedActions.get(0));
                            } else {
                                messaggio = String.format(
                                        "Codici action [%s] duplicati all'interno della configurazione corrente",
                                        duplicatedActions.stream().collect(Collectors.joining(",")));
                            }
                        } else {
                            // controlla i duplicati all'iterno di tutto il catalogo
                            // crea la lista delle griglie dichiarate
                            final List<String> grids = configurations.entrySet().stream().map(Entry::getValue)
                                    .map(Configuration::getName).filter(Objects::nonNull).distinct()
                                    .collect(Collectors.toList()); // lista delle griglie che sto controllando
                            // controlla se ci sono duplicati in registro configurazioni
                            duplicatedActions = findDuplicatedActionsInConfigurations(actions, grids);
                            if (duplicatedActions.size() > 0) {
                                if (duplicatedActions.size() == 1) {
                                    messaggio = String.format(
                                            "Codice action [%s] duplicato all'interno del catalogo delle configurazioni",
                                            duplicatedActions.get(0));
                                } else {
                                    messaggio = String.format(
                                            "Codici action [%s] duplicati all'interno del catalogo delle configurazioni",
                                            duplicatedActions.stream().collect(Collectors.joining(",")));
                                }
                            }
                        }
                    }
                }
                if (messaggio != null) {
                    addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Configurazione non valida", messaggio));
                    return;
                }
            } else if (filter.equals(ConfigurationTypes.FILTER)) {
                // validazione json pura
                String validationError = JsonUtils.validate(editingFilterJson);
                if (validationError != null) {
                    addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Json non valido ", validationError));
                    return;
                }
            }
            boolean updateSuccess = false;
            boolean rollback = false;
            if (e != null) {
                // update della vecchia configurazione
                e.setRevisionDate(new Date(System.currentTimeMillis()));
                e.setLastEditor(this.getCurrentUser().getName());
                try {
                    e = this.service.persistConfigurations(e);
                    updateSuccess = true;
                } catch (final Exception ex) {
                    log.error("error saving configuration", ex);
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            "Errore durante il salvataggio della configurazione : " + ex.getMessage()));
                }
            }

            if (updateSuccess) {
                // salva la nuova configurazione
                ConfigurationEntity config = new ConfigurationEntity();
                // try to format the json string
                config.setJson(filter.equals(ConfigurationTypes.GRID) ? JsonUtils.uglyPrint(this.editingGridJson)
                        : filter.equals(ConfigurationTypes.FILTER) ? JsonUtils.uglyPrint(this.editingFilterJson)
                        : null);
                config.setPath(e != null ? e.getPath() : "non usata");
                config.setRevisionDate(null);
                config.setType(filter);
                config.setMenu(this.selectedMenuNode);

                try {
                    config = this.service.persistConfigurations(config);
                } catch (final Exception ex) {
                    log.error("Error saving configuration", ex);
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            "Errore durante il salvataggio della configurazione : " + ex.getMessage()));
                    rollback = true;
                }
                // mettila nel menu
                if (rollback) {
                    e.setRevisionDate(null);
                    e.setLastEditor(null);
                    try {
                        e = this.service.persistConfigurations(e);
                    } catch (final Exception ex) {
                        log.error("error saving configuration", ex);
                        this.addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                                        "Errore durante il salvataggio della configurazione (rollback failed) : "
                                                + ex.getMessage()));
                    }
                } else {
                    final boolean configurationAdded = getSelectedMenuNode().addConfiguration(config);
                    if (!configurationAdded) {
                        this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
                                "Configurazione salvata, rieffettuare il login per ricaricare il database"));
                    } else {
                        this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                                "Configurazione salvata correttamente"));

                        this.configurationCatalog.update();
                    }
                }
            }
        }
    }

    List<String> getActionsFromCurrentConfiguration(Map<String, Configuration> configurations) {
        // Map<String, Configuration> configurations =
        // configurationCatalog.readConfiguration(this.editingGridJson);
        List<String> result = Collections.<String>emptyList();
        if ((configurations != null) && !configurations.isEmpty()) {
            result = configurations.entrySet().stream().map(Entry::getValue).flatMap(s -> getActionList(s).stream())
                    .filter(Objects::nonNull).collect(Collectors.toList());
        }
        return result;
    }

    List<String> readGridsFromCurrentConfiguration(Map<String, Configuration> configurations) {
        if ((configurations != null) && !configurations.isEmpty()) {
            return configurations.entrySet().stream().map(Entry::getValue).map(Configuration::getName)
                    .filter(Objects::nonNull).distinct().collect(Collectors.toList()); // lista delle griglie che sto
            // controllando
        }
        return Collections.<String>emptyList();
    }

    List<String> findDuplicatedActionsInConfigurations(final List<String> actions, List<String> grids) {
        Map<String, Configuration> configurations = configurationCatalog.getConfigurations(); // tutte le configurazioni
        // possibili
        if ((configurations != null) && !configurations.isEmpty()) {
            final List<String> availableActions = configurations.entrySet().stream().map(Entry::getValue)
                    .filter(e -> !grids.contains(e.getName())) // escluso i presenti
                    .flatMap(s -> getActionList(s).stream()).filter(Objects::nonNull).distinct()
                    .collect(Collectors.toList());
            if ((availableActions != null) && !availableActions.isEmpty()) {
                // devo controllare
                return availableActions.stream().filter(a -> actions.contains(a)).collect(Collectors.toList());
            }
        }
        return Collections.<String>emptyList();
    }

    List<String> findDuplicatedActionsInList(List<String> list) {
        Set<String> items = new HashSet<>();
        return list.stream().filter(n -> !items.add(n)) // Set.add() returns false if the element was already in the
                // set.
                .collect(Collectors.toList());

    }

    List<String> getActionList(@NonNull Configuration config) {
        List<String> actionList = new ArrayList<>();
        String actions = config.getActions();
        if ((actions != null) && !actions.equals("")) {

            final JSONArray jsonArr = new JSONArray(config.getActions());
            for (int i = 0; i < jsonArr.length(); i++) {
                final JSONObject jsonObj = jsonArr.getJSONObject(i);
                actionList.add(jsonObj.getString("componentId"));
            }
        }
        return actionList;
    }

    private boolean validate(@NonNull String json) {
        boolean result = true;
        if (this.editingGrid) {
            final ObjectMapper mapper = JsonUtils.getValidationMapper();
            try {
                final JsonNode contentNode = mapper.readTree(json);
                final JsonNode configurationsNode = contentNode.get("configurations");
                if (configurationsNode != null) {
                    final List<JsonNode> members = configurationsNode.findValues("Members");
                    for (final JsonNode member : members) {
                        if ((member != null) && (member.size() > 0)) {
                            JsonNode node = null;
                            boolean nodeResult = false;
                            for (int i = 0; i < member.size(); ++i) {
                                node = member.get(i);
                                if (!nodeResult && (node != null)) {
                                    nodeResult = "UniqueName".equals(node.asText());
                                }
                            }
                            if (!nodeResult) {
                                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Errore di configurazione",
                                        "Manca l'attributo \"UniqueName\" all'interno di \"Members\": la funzione \"CellPut\" non puo' funzionare"));
                                result = false;
                            }
                        }
                    }
                } else {
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione",
                            "Impossibile validare la configurazione: manca il campo \"configurations\""));
                }

            } catch (final IOException e) {
                log.error("error checking attributes", e);
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione",
                        "Impossibile validare la configurazione"));
                result = false;
            }

        }
        return result;
    }

    private boolean checkMandatoryConfigurationSettins() {
        final AtomicBoolean result = new AtomicBoolean(true);
        if (this.editingGrid) {
            final Map<String, Configuration> configurations = this.configurationCatalog
                    .readConfiguration(this.editingGridJson);
            if (configurations == null) {
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore di configurazione",
                        "Impossibile deserializzare la configurazione: controllare i log per il dettaglio "));
                result.set(false);
            } else {
                configurations.entrySet().stream().forEach(e -> {
                    final Configuration con = e.getValue();
                    if (StringUtils.isEmpty(StringUtils.stripToEmpty(con.getName()))) {
                        this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione",
                                "Manca il campo name in configurazione"));
                        result.set(false);
                    }
                    if (con.getHeight() == null) {
                        this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione",
                                "Manca il campo height in configurazione"));
                        result.set(false);
                    } else if ((con.getHeight() < 0) || (con.getHeight() > 100)) {
                        this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione",
                                "Il campo height deve essere intero tra 0 e 100"));
                        result.set(false);
                    }
                });
            }
        }
        return result.get();
    }

    public void updatePreview() {
        if (this.selectedPreviewRole != null) {
            this.previewMenu = new DefaultTreeNode("Menu", null);
            try {
                final List<MenuEntity> menuList = this.service.readTopMenus();
                menuList.stream().sorted(Comparator.comparing(MenuEntity::getOrderId))
                        .forEach(m -> this.createPreviewNode(m, this.previewMenu, this.selectedPreviewRole));
            } catch (final Exception e) {
                log.error("Error creating preview for role " + selectedPreviewRole.getDescription(), e);
            }
            this.previewMenu.setExpanded(true);
        }

    }

    private TreeNode createPreviewNode(final MenuEntity entity, final TreeNode parent, final RolesEntity role) {
        if ((role != null) && role.isAdmin()) {
            return this.createNodes(entity, parent, null);
        }
        final TreeNode node = new DefaultTreeNode(this.getNodeType(entity), entity, parent);
        if (entity.isLeaf()) {
            if (!this.isNodeAccessibleByRole(entity, role)) {
                // nodo non accessibie, devo toglierlo
                parent.getChildren().remove(node);
            }
        } else {
            // aggiungo il nodo se e solo se posso aggiungere almeno una foglia nella visita
            entity.getChildren().forEach(child -> this.createPreviewNode(child, node, role));
            if (node.getChildCount() > 0) {
                // ok
            } else {
                // nodo non accessibie, devo toglierlo
                parent.getChildren().remove(node);
            }
        }
        node.setExpanded(true);
        return node;
    }

    private boolean isNodeAccessibleByRole(final MenuEntity node, final RolesEntity role) {
        if ((role != null) && role.isAdmin()) {
            return true;
        }
        return service.menuBelongsToRole(node, role);
    }

    public void selectAllRoles() {
        this.selectRoles(true);
    }

    public void deselectAllRoles() {
        this.selectRoles(false);
    }

    private void selectRoles(final boolean all) {
        if (all) {
            try {
                this.selectedRoles = this.service.readRoles();
            } catch (final Exception e) {
                log.error("error reading roles list", e);
                this.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Errore di comunicazione col DB"));
            }
        } else {
            this.selectedRoles = new ArrayList<>();
        }
        this.updateRolesAssociation();
    }

    /**
     * controlla se un layout e' modificabile
     *
     * @return
     */
    public boolean isEditLayout() {
        if ((this.selectedNode != null) && (this.selectedMenuNode != null) && getSelectedMenuNode().isLeaf()) {
            MenuEntity parent = getSelectedMenuNode().getParent();
            MenuEntity entity = this.selectedMenuNode;
            while (parent != null) {
                entity = parent;
                parent = entity.getParent();
            }
            return !entity.getLabel().toLowerCase().trim().equals("admin");
        }
        return false;
    }

    /**
     * controlla se un nodo e' cancellabile: se e' una foglia o e' un container
     * vuoto
     *
     * @return
     */
    public boolean isDeletableNode() {
        final boolean isDeletable = this.isEditLayout();
        if (!isDeletable) {
            // se non e' editabile
            return (this.selectedMenuNode != null) && !getSelectedMenuNode().hasChildren();
        }
        return true;
    }

    /**
     * controlla se una configurazione e' modificabile
     *
     * @return
     */
    public boolean isEditConfiguration() {
        if ((this.selectedNode != null) && (this.selectedMenuNode != null) && getSelectedMenuNode().isLeaf()
                && getSelectedMenuNode().getExternalLink()) {
            MenuEntity parent = getSelectedMenuNode().getParent();
            MenuEntity entity = this.selectedMenuNode;
            while (parent != null) {
                entity = parent;
                parent = entity.getParent();
            }
            return !entity.getLabel().toLowerCase().trim().equals("admin");
        }
        return false;
    }

    /**
     * this is to prepare the add menu
     */
    public void prepareNewMenu() {
        log.debug("preparing add menu");
        setNewMenuBean(new AddMenuBean(this.menu));
        if (selectedMenuNode != null) {
            MenuEntity parent = selectedMenuNode;
            if (parent.isLeaf()) {
                parent = parent.getParent();
            }
            this.newMenuBean.setIdParent(parent.getIdMenu());
            this.newMenuBean.getMenu().getParent().setIdMenu(parent.getIdMenu());
        }
    }

    public void addMenu(ActionEvent e) {
        log.debug("action listener called");
        addMenu();
    }

    /**
     * this adds a menu entry
     */
    public void addMenu() {
        try {
            MenuEntity menu = check(getNewMenuBean().getUpdatedMenu(), getNewMenuBean().getIdParent());

            menu = this.service.persistMenu(menu);
            if (getNewMenuBean().isLeafNode()) {
                if (!this.createConfiguration(menu)) {
                    this.service.removeMenu(menu);
                    this.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", "Voce Menu già esistente."));
                    return;
                }
                menu = this.service.persistMenu(menu);
            }
            this.loadMenu(menu);
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Voce Menu Aggiunta!", ""));

        } catch (final Exception e) {
            log.error("Error adding menu", e);
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", e.getMessage()));
        }
        setNewMenuBean(null);
    }

    private MenuEntity check(@NonNull final MenuEntity entity, @NonNull final Integer idParent) throws Exception {
        if (entity.getOrderId() == null) {
            // set
            Optional<MenuEntity> lastMenu = this.service.readMenus().stream().filter(m -> m.getOrderId() != null)
                    .max(Comparator.comparing(MenuEntity::getOrderId));
            if (lastMenu.isPresent()) {
                entity.setOrderId(lastMenu.get().getOrderId() + 1);
            } else {
                entity.setOrderId(999);
            }
        }
        // attach to parent.
        if (idParent.equals(AddMenuBean.ROOT_NODE_VALUE)) {
            entity.setParent(null);
        } else {
            MenuEntity parent = service.findMenu(idParent);
            entity.setParent(parent);
        }
        return entity;
    }

    private boolean createConfiguration(final MenuEntity menu) {
        boolean canAdd = false;
        try {
            final Set<ConfigurationEntity> configurations = menu.getConfigurations();
            if ((configurations == null) || (!canAdd && (configurations.size() == 0))) {
                canAdd = true;
            }
            if (canAdd) {
                ConfigurationEntity configurationEntity = new ConfigurationEntity();
                configurationEntity.setJson("{}");
                configurationEntity.setPath("");
                configurationEntity.setType(ConfigurationTypes.GRID);
                menu.addConfiguration(configurationEntity);
                // this.service.persistConfigurations(configurationEntity);
                configurationEntity = new ConfigurationEntity();
                configurationEntity.setJson("{}");
                configurationEntity.setPath("");
                configurationEntity.setType(ConfigurationTypes.FILTER);
                menu.addConfiguration(configurationEntity);
                // this.service.persistConfigurations(configurationEntity);
            }
        } catch (final Exception e) {
            log.error("Error creating configuration", e);
            canAdd = false;
        }
        return canAdd;
    }

    public void saveGlobalFilters() {
        if (this.globalFilters != null) {
            final String s = JsonUtils.validate(this.globalFilters.getJson());
            if (s == null) {
                try {
                    this.service.persistConfigurations(this.globalFilters);
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                            "Salvataggio avvenuto con successo"));
                } catch (final Exception e) {
                    log.error("error saving global filters", e);
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "Errore durante il salvataggio avvenuto con successo"));
                    // rollback
                    this.loadGlobalFilters();
                }
            } else {
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "Errore durante la validazione del json :\n" + s));
            }
        }
    }

    public void saveWelcomeFilters() {
        final String s = JsonUtils.validate(this.welcomeFilters.getJson());
        FacesMessage message = null;
        if (s == null) {
            try {
                this.welcomeFilters = this.service.persistConfigurations(this.welcomeFilters);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                        "Configurazione salvata correttamente");
            } catch (Exception e) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore", "Errore durante il salvataggio");
                log.error("Error saving configuration for welcome page", e);
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore", s);
        }
        if (message != null) {
            this.addMessage(null, message);
        }
    }

    public void resetWelcomeFilters() {
        final ConfigurationEntity old = this.readConfigurationEntity(-1, ConfigurationTypes.FILTER);
        FacesMessage message = null;
        if (old == null) {
            // this is an error !!!
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
                    "Errore durante il recupero della versione precedente");
        } else {
            this.welcomeFilters = old;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    "Configurazione ripristinata correttamente");
        }
        if (message != null) {
            this.addMessage(null, message);
        }
    }

    public void resetGlobalFilters() {
        this.loadGlobalFilters();
    }
    /*
     * Ignored methods
     */

    @Override
    public void updateView() {
    }

    @Override
    public void updateView(final String grid) {
    }

    @Override
    public Query prepareFilteredQuery(final String grid) {
        return null;
    }

    @Override
    protected ApplicationFilterCatalogProducer getCatalogProducer() {
        return null;
    }

    @Override
    protected CatalogReducer getCatalogReducer() {
        return null;
    }

    public void prepareEditLayout(ActionEvent event) {
        log.debug("preparing to edit layout for node " + getSelectedMenuNode().getLabel());
        setNewMenuBean(new AddMenuBean(getSelectedMenuNode()));
    }

    private void setNewMenuBean(AddMenuBean bean) {
        log.debug("changed new menu bean to " + bean);
        this.newMenuBean = bean;
    }

    public void moveDown() {
        if (canMove() && canPushDown()) {
            List<TreeNode> list = getSelectedNode().getParent().getChildren();
            Integer position = find(list, false);
            if (position != null) {
                swap(list, position - 1, position);
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile scambiare la posizione dei nodi"));
                log.error("Elemento non trovato");
            }
        }
    }

    public void moveUp() {
        if (canMove() && canPushUp()) {
            final List<TreeNode> list = getSelectedNode().getParent().getChildren();
            Integer position = find(list, true);
            if (position != null) {
                swap(list, position + 1, position);
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Impossibile scambiare la posizione dei nodi"));
                log.error("Elemento non trovato");
            }
        }
    }

    Boolean canPushDown() {
        List<TreeNode> list = getSelectedNode().getParent().getChildren();
        Integer position = list.indexOf(getSelectedNode());
        if ((position == -1) || (position == (list.size() - 1))) {
            return false;
        }
        return true;
    }

    Boolean canPushUp() {
        List<TreeNode> list = getSelectedNode().getParent().getChildren();
        Integer position = list.indexOf(getSelectedNode());
        if ((position == -1) || (position == 0)) {
            return false;
        }
        return true;
    }

    Boolean canMove() {

        if (getSelectedNode() != null && getSelectedNode().getParent() != null) {
            return getSelectedNode().getParent().getChildren().size() > 1;
        } else {
            if (getSelectedNode() == null) {
                log.warn("node not selected");
            } else {
                log.warn("node has no parent");
            }
            return false;
        }
    }

    Integer find(List<TreeNode> list, Boolean north) {
        Integer position = list.indexOf(getSelectedNode());
        if (position > -1) {
            return north ? --position : ++position;
        }
        return null;
    }

    private void fixChildPositions(TreeNode node) {
        if (node.getChildCount() > 0) {
            AtomicInteger pos = new AtomicInteger(0);
            node.getChildren().forEach(n -> {
                MenuEntity m = ((MenuEntity) n.getData());
                m.setOrderId(10 * pos.incrementAndGet());
                try {
                    getService().persistMenu(m);
                } catch (Exception e) {
                    log.error("Error fixing menu position", e);
                }
            });
        }
    }

    private void swap(@NonNull final List<TreeNode> list, @NonNull Integer pos1, @NonNull Integer pos2) {
        TreeNode n1 = list.get(pos1);
        TreeNode n2 = list.get(pos2);

        MenuEntity m1 = ((MenuEntity) n1.getData());
        MenuEntity m2 = ((MenuEntity) n2.getData());

        if (m1.getOrderId() == m2.getOrderId()) {
            // devo rinumerare gli ordini
            fixChildPositions(n1.getParent());
        }

        Integer appoggio = m1.getOrderId();
        m1.setOrderId(m2.getOrderId());
        m2.setOrderId(appoggio);

        try {
            getService().persistMenu(m1);
            getService().persistMenu(m2);

            list.set(pos1, n2);
            list.set(pos2, n1);

        } catch (Exception e) {
            log.error(String.format("Errore durante lo scambio di posizione tra il menu %s e il menu %s", m1.getLabel(),
                    m2.getLabel()), e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile scambiare la posizione dei nodi"));
        }

    }

    public boolean isPreviewEmpty() {
        if (previewMenu == null) {
            return true;
        }
        return previewMenu.getChildren().size() == 0;
    }
}
