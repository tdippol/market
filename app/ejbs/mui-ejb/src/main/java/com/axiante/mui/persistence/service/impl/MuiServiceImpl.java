package com.axiante.mui.persistence.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.AclDAO;
import com.axiante.mui.persistence.dao.ApplicationPropertiesDAO;
import com.axiante.mui.persistence.dao.CanaleDAO;
import com.axiante.mui.persistence.dao.CompratoreDAO;
import com.axiante.mui.persistence.dao.ConfigurationsDAO;
import com.axiante.mui.persistence.dao.ConnectionSetupDAO;
import com.axiante.mui.persistence.dao.GrmDAO;
import com.axiante.mui.persistence.dao.GroupDAO;
import com.axiante.mui.persistence.dao.GruppoCanaleDAO;
import com.axiante.mui.persistence.dao.GruppoCompratoreDAO;
import com.axiante.mui.persistence.dao.GruppoGrmDAO;
import com.axiante.mui.persistence.dao.GruppoRepartoDAO;
import com.axiante.mui.persistence.dao.MenuDAO;
import com.axiante.mui.persistence.dao.MenuRoleDAO;
import com.axiante.mui.persistence.dao.MuiContextDAO;
import com.axiante.mui.persistence.dao.RepartoDAO;
import com.axiante.mui.persistence.dao.RolesDAO;
import com.axiante.mui.persistence.dao.UploadDocumentDAO;
import com.axiante.mui.persistence.dao.UsersDAO;
import com.axiante.mui.persistence.dto.GruppoUtenteDto;
import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.entity.ConnectionSetupEntity;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCanaleEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import com.axiante.mui.persistence.entity.GruppoGrmEntity;
import com.axiante.mui.persistence.entity.GruppoRepartoEntity;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.MenuRoleEntity;
import com.axiante.mui.persistence.entity.MuiContext;
import com.axiante.mui.persistence.entity.RepartoEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class MuiServiceImpl implements MuiService {
    private static final long serialVersionUID = -7268500368861854480L;

    @Inject
    private Instance<AclDAO> aclDAO;

    @Inject
    private Instance<RolesDAO> rolesDAO;

    @Inject
    private Instance<UsersDAO> usersDAO;

    @Inject
    private Instance<MenuDAO> menuDAO;

    @Inject
    private Instance<ApplicationPropertiesDAO> applicationPropertiesDAO;

    @Inject
    private Instance<ConnectionSetupDAO> connectionSetupDAO;

    @Inject
    private Instance<ConfigurationsDAO> configurationsDAO;

    @Inject
    private Instance<GroupDAO> groupDAO;

    @Inject
    private Instance<CanaleDAO> canaleDAO;

    @Inject
    private Instance<MenuRoleDAO> menuRoleDAO;

    @Inject
    private Instance<UploadDocumentDAO> uploadDocumentDAO;

    @Inject
    private Instance<MuiContextDAO> muiContextDAO;

    @Inject
    private Instance<GruppoCanaleDAO> gruppoCanaleDAO;

    @Inject
    Instance<GruppoRepartoDAO> gruppoRepartoDaoInstance;

    @Inject
    Instance<RepartoDAO> repartoDaoInstance;

    @Inject
    Instance<CompratoreDAO> compratoreDAOInstance;

    @Inject
    Instance<GruppoCompratoreDAO> gruppoCompratoreDAOInstance;

    @Inject
    Instance<GruppoGrmDAO> gruppoGrmDAOInstance;
    @Inject
    Instance<GrmDAO> grmDAOInstance;

    @Override
    public void addMenuPermissions(final List<MenuEntity> menuEntityList, final RolesEntity rolesEntity)
            throws Exception {
        // add newly authorised menu nodes
        menuEntityList.forEach(m -> {
            m.addRole(rolesEntity);
        });
    }

    boolean addMenuRoleGoingDown(final RolesEntity role, final MenuEntity menu) {
        if (!menu.isLeaf()) {
            try {
                menu.addRole(role);
                role.addMenu(menu);
                for (final MenuEntity m : menu.getChildren()) {
                    addMenuRoleGoingDown(role, m);
                }
            } catch (final Exception e) {
                log.error("error fixing menu-roles model", e);
                return false;
            }
        }
        return true;
    }

    boolean addMenuRoleGoingUp(final RolesEntity role, final MenuEntity menu) {
        try {
            MenuEntity parent = menu.getParent();
            while (parent != null) {
                parent.addRole(role);
                role.addMenu(parent);
                parent = parent.getParent();
            }
        } catch (final Exception e) {
            log.error("error fixing menu-roles model", e);
        }
        return true;
    }

    @Override
    public AclEntity findACL(final Integer id) throws Exception {
        return getAclDAO().findById(id);
    }

    @Override
    public ApplicationPropertiesEntity findApplicationProperty(@NonNull String key) throws Exception {
        return getApplicationPropertiesDAO().findByCode(key);
    }
    @Override
    public boolean deleteApplicationProperty(String key) throws Exception{
        return getApplicationPropertiesDAO().delete(key);
    }
    @Override
    public List<ConfigurationEntity> findConfigurationByIdMenu(final Integer idMenu) {
        return getConfigurationsDAO().findByIdMenu(idMenu);
    }

    @Override
    public ConfigurationEntity findConfigurations(@NonNull final Integer id) throws Exception {
        return getConfigurationsDAO().findById(id);
    }

    @Override
    public ConfigurationEntity findConfigurations(@NonNull final String path, @NonNull final ConfigurationTypes type)
            throws Exception {
        return getConfigurationsDAO().findByPath(path, type);
    }

    @Override
    public MenuEntity findMenu(final Integer id) throws Exception {
        return getMenuDAO().findById(id);
    }

    @Override
    public RolesEntity findRole(final Integer id) throws Exception {
        return getRolesDAO().findById(id);
    }

    @Override
    public UsersEntity findUser(final Integer id) throws Exception {
        return getUsersDAO().findById(id);
    }

    @Override
    public UsersEntity findUser(final String username) throws Exception {
        return getUsersDAO().findByUsername(username);
    }

    @Override
    public AclEntity persistACL(final AclEntity acl) throws Exception {
        return getAclDAO().persist(acl);
    }

    @Override
    public ApplicationPropertiesEntity persistApplicationProperty(
            final ApplicationPropertiesEntity applicationPropertiesEntity) throws Exception {
        return getApplicationPropertiesDAO().persist(applicationPropertiesEntity);
    }

    @Override
    public ConfigurationEntity persistConfigurations(@NonNull final ConfigurationEntity configurationEntity)
            throws Exception {
        return getConfigurationsDAO().persist(configurationEntity);
    }

    @Override
    public ConnectionSetupEntity persistConnectionSetup(final ConnectionSetupEntity connectionSetupEntity)
            throws Exception {
        return getConnectionSetupDAO().persist(connectionSetupEntity);
    }

    @Override
    public MenuEntity persistMenu(@NonNull final MenuEntity menu) throws Exception {
        return getMenuDAO().persist(menu);

    }

    @Override
    public RolesEntity persistRole(final RolesEntity role) throws Exception {
        return getRolesDAO().persist(role);

    }

    @Override
    public GroupEntity persistGroup(final GroupEntity group) throws Exception {
        return getGroupDAO().persist(group);
    }

    @Override
    public List<GroupEntity> persistGroups(final List<GroupEntity> group) throws Exception {
        return getGroupDAO().persist(group);
    }

    @Override
    public GroupEntity findGroupById(Integer id) throws Exception {
        return getGroupDAO().findById(id);
    }

    @Override
    public List<GroupEntity> findGroupsByUser(UsersEntity user) throws Exception {
        return getGroupDAO().findAllByUser(user);
    }

    @Override
    public CanaleEntity persistCanale(CanaleEntity canale) throws Exception {
        return getCanaleDAO().persist(canale);
    }

    @Override
    public Collection<CanaleEntity> persistCanali(Collection<CanaleEntity> canali) throws Exception {
        return getCanaleDAO().persist(canali);
    }

    @Override
    public UsersEntity persistUser(final UsersEntity user) throws Exception {
        return getUsersDAO().persist(user);

    }

    @Override
    public List<GroupEntity> readGroups() throws Exception {
        return getGroupDAO().findAll();
    }

    @Override
    public List<AclEntity> readACLs() throws Exception {
        return getAclDAO().readAll();
    }

    @Override
    public List<ApplicationPropertiesEntity> readApplicationProperties() throws Exception {
        return getApplicationPropertiesDAO().readAll();
    }

    @Override
    public List<MenuEntity> readByParentId(final Integer id) throws Exception {
        return getMenuDAO().readByParentId(id);
    }

    @Override
    public List<String> readComponentGroups() throws Exception {
        return getAclDAO().readComponentGroups();
    }

    @Override
    public List<ConfigurationEntity> readConfigurations() throws Exception {
        return getConfigurationsDAO().readAll();
    }

    @Override
    public List<ConfigurationEntity> readConfigurations(final ConfigurationTypes type) throws Exception {
        return getConfigurationsDAO().findByType(type);
    }

    @Override
    public List<ConnectionSetupEntity> readConnectionSetups() throws Exception {
        return getConnectionSetupDAO().readAll();
    }

    @Override
    public List<MenuEntity> readMenus() throws Exception {
        return getMenuDAO().readAll();
    }

    @Override
    public List<MenuEntity> readMenus(final RolesEntity rolesEntity) throws Exception {
        return getMenuDAO().readAll(rolesEntity);
    }

    @Override
    public List<RolesEntity> readRoles() throws Exception {
        return getRolesDAO().readAll();
    }

    @Override
    public List<MenuEntity> readTopMenus() throws Exception {
        return getMenuDAO().readNullParent();
    }

    @Override
    public List<UsersEntity> readUsers() throws Exception {
        return getUsersDAO().readAll();
    }

    @Override
    public List<CanaleEntity> readCanali() throws Exception {
        return getCanaleDAO().findAll();
    }

    @Override
    public void removeACL(final AclEntity acl) throws Exception {
        getAclDAO().remove(acl);
    }

    @Override
    public void removeConfigurations(@NonNull final ConfigurationEntity configurationEntity) throws Exception {
        getConfigurationsDAO().remove(configurationEntity);
    }

    @Override
    public void removeConnectionSetup(final ConnectionSetupEntity connectionSetupEntity) throws Exception {
        getConnectionSetupDAO().remove(connectionSetupEntity);
    }

    @Override
    public void removeMenu(@NonNull final MenuEntity menuEntity) throws Exception {
        getMenuDAO().remove(menuEntity);
    }

    @Override
    public void removeGroup(@NonNull GroupEntity group) throws Exception {
        getGroupDAO().remove(group);
    }

    @Override
    public void removeMenuPermissions(final List<MenuEntity> menuEntityList, final RolesEntity rolesEntity)
            throws Exception {
        // remove unauthorised menu nodes
        menuEntityList.forEach(menu -> {
            rolesEntity.removeMenu(menu);
            menu.removeRole(rolesEntity);
        });
        getMenuDAO().persist(menuEntityList);
        getRolesDAO().persist(rolesEntity);
    }

    @Override
    public boolean removeMenuPermissions(@NonNull final RolesEntity rolesEntity) throws Exception {
        removeMenuPermissions(new ArrayList<>(rolesEntity.getMenus()), rolesEntity);
        return true;
    }

    @Override
    public void removeRole(final RolesEntity role) throws Exception {
        if ((role.getUsers() != null) && (role.getUsers().size() > 0)) {
            throw new Exception("Existing users with role " + role.getName());
        }
        getRolesDAO().remove(role);

    }

    boolean removeRole(final RolesEntity role, final MenuEntity menu, final Boolean down) {
        try {
            menu.removeRole(role);
            role.removeMenu(menu);
            if (down != null) {
                // propaga
                if (down) {
                    if (!menu.isLeaf()) {
                        // se il menu non e' una foglia, scendi
                        for (final MenuEntity m : menu.getChildren()) {
                            removeRole(role, m, down);
                        }
                    }
                } else if (menu.getParent() != null) {
                    // controlla se i miei fratelli hanno il ruolo
                    boolean removeFromParent = true;
                    for (final MenuEntity m : menu.getParent().getChildren()) {
                        if ((m != menu) && removeFromParent) {
                            removeFromParent = !m.getRoles().contains(role);
                        }
                    }
                    if (!removeFromParent) {
                        // qualcuno dei miei fratelli ha il ruolo
                        // quindi lascio stare mio pardre
                    } else {
                        // tolgo da mio padre ed eventualmente salgo
                        removeRole(role, menu.getParent(), down);
                    }
                }
            }
        } catch (final Exception e) {
            log.error("error while fixing the menu-roles model", e);
            return false;
        }
        return true;
    }

    @Override
    public void removeUser(final UsersEntity user) throws Exception {
        getUsersDAO().remove(user);
    }

    @Override
    public boolean updateMenuPermissions(@NonNull final Set<MenuEntity> list, final RolesEntity rolesEntity)
            throws Exception {
        /*
         * in list ci sono tutte le voci di menu che hanno quel ruolo quindi : - rimuovi
         * il ruolo da tutte le voci di menu che non sono in list - aggiungi il ruolo a
         * tutte le voci di menu che sono in list
         *
         */
        final List<MenuEntity> menusFromWhichRoleShouldBeRemoved = rolesEntity.getMenus() // all the menus that actually
                // have the role set
                .parallelStream().filter(menu -> !list.contains(menu)) // all the menus that should have the role
                // removed
                .collect(Collectors.toList());

        boolean updateSuccess = true;
        for (final MenuEntity menu : menusFromWhichRoleShouldBeRemoved) {
            // rimuovi il ruolo
            if (!menu.isLeaf()) {
                // propaga verso il basso e ...
                updateSuccess = removeRole(rolesEntity, menu, true);
            }
            // se e' una foglia, controlla il parent
            updateSuccess = updateSuccess && removeRole(rolesEntity, menu, false);
        }
        // adesso che hai rimosso tutto, aggiungi
        for (final MenuEntity menu : list) {
            updateSuccess = updateSuccess && addMenuRoleGoingUp(rolesEntity, menu);
            updateSuccess = updateSuccess && addMenuRoleGoingDown(rolesEntity, menu);
        }
        if (updateSuccess) {
            try {
                // adesso salva il tutto
                for (final MenuEntity menu : list) {
                    updateSuccess = updateSuccess && (getMenuDAO().persist(menu) != null);
                }

                updateSuccess = updateSuccess && (getRolesDAO().persist(rolesEntity) != null);
            } catch (final Exception e) {
                log.error("error saving menu-roles model ", e);
                updateSuccess = false;
            }
        }

        return updateSuccess;
    }

    @Override
    public void refreshApplicationProperties() {
        getApplicationPropertiesDAO().refreshProperties();
    }

    @Override
    public MenuRoleEntity findMenuRoleByMenuAndRole(MenuEntity menu, RolesEntity role) {
        return getMenuRoleDAO().findByMenuAndRole(menu, role);
    }

    @Override
    public MenuRoleEntity findMenuRoleByMenuAndRoles(MenuEntity menu, Set<RolesEntity> roles) {
        return getMenuRoleDAO().findByMenuAndRoles(menu, roles);
    }

    @Override
    public List<MenuEntity> findAllVisibleMenuByRole(RolesEntity role) {
        return getMenuRoleDAO().findAllVisibleByRole(role);
    }

    @Override
    public boolean toggleAdminModeVisible(MenuRoleEntity menuRole) {
        return getMenuRoleDAO().toggleAdminModeVisible(menuRole);
    }

    @Override
    public void changeAdminModeVisible(List<MenuRoleEntity> menuRoles, boolean visible) {
        getMenuRoleDAO().changeAdminModeVisible(menuRoles, visible);
    }

    @Override
    @Transactional
    public void resetAllExceptions() {
        // Reset all exceptions on default on roles (overrideAdmin) and menuRoles
        // (adminModeVisible)
        getMenuRoleDAO().resetAllExceptions();
        getRolesDAO().resetAllExceptions();
    }

    @Override
    public List<UploadDocumentEntity> readDocuments() {
        return getUploadDocumentDAO().readAll();
    }

    @Override
    public void removeDocument(@NonNull UploadDocumentEntity document) throws Exception {
        getUploadDocumentDAO().delete(document);
    }

    @Override
    public UploadDocumentEntity persistDocument(@NonNull UploadDocumentEntity document, @NonNull String user) {
        return getUploadDocumentDAO().persistWithAuditLog(document, user);
    }

    @Override
    public UploadDocumentEntity findDocumentByName(@NonNull String name) throws Exception {
        return getUploadDocumentDAO().findByName(name);
    }

    private AclDAO getAclDAO() {
        return aclDAO.get();
    }

    private RolesDAO getRolesDAO() {
        return rolesDAO.get();
    }

    private UsersDAO getUsersDAO() {
        return usersDAO.get();
    }

    private MenuDAO getMenuDAO() {
        return menuDAO.get();
    }

    private ApplicationPropertiesDAO getApplicationPropertiesDAO() {
        return applicationPropertiesDAO.get();
    }

    private ConnectionSetupDAO getConnectionSetupDAO() {
        return connectionSetupDAO.get();
    }

    private ConfigurationsDAO getConfigurationsDAO() {
        return configurationsDAO.get();
    }

    private GroupDAO getGroupDAO() {
        return groupDAO.get();
    }

    private CanaleDAO getCanaleDAO() {
        return canaleDAO.get();
    }

    private MenuRoleDAO getMenuRoleDAO() {
        return menuRoleDAO.get();
    }

    private UploadDocumentDAO getUploadDocumentDAO() {
        return uploadDocumentDAO.get();
    }

    @Override
    public boolean menuBelongsToRole(MenuEntity menu, RolesEntity r) {
        if (RolesEntity.ADMIN_ROLE.equals(r.getName())) {
            return true;
        }
        return menuDAO.get().menuBelongsToRole(menu, r);
    }

    @Override
    public boolean menuBelongsToRoles(MenuEntity menu, Set<RolesEntity> roles) {
        if (roles.stream().filter(Objects::nonNull).filter(RolesEntity::isAdmin).findFirst().isPresent()) {
            return true;
        }
        return menuDAO.get().menuBelongsToRoles(menu, roles);
    }

    @Override
    public ConfigurationEntity findConfiguration(final Integer idMenu, @NonNull final ConfigurationTypes type) {
        return configurationsDAO.get().findByIdMenuAndType(idMenu, type);
    }

    @Override
    public List<MuiContext> findAllContexts() {
        return muiContextDAO.get().findAll();
    }

    @Override
    public MuiContext findContextById(@NonNull Long id) {
        return muiContextDAO.get().findById(id);
    }

    @Override
    public MuiContext findContextByCode(@NonNull String code) {
        return muiContextDAO.get().findByCode(code);
    }

    @Override
    public MuiContext saveContext(@NonNull MuiContext context) throws Exception {
        return muiContextDAO.get().persist(context);
    }

    @Override
    public List<GroupEntity> findGroupsByContextCode(String contextCode) {
        // Non ho capito perche' ma la query, se senza risultati, ritorna
        // una lista di valori NULL
//        return muiContextDAO.get().findGroupsByContextCode(contextCode);
        return muiContextDAO.get().findGroupsByContextCode(contextCode).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public GruppoCanaleEntity findByGruppoAndCanale(@NonNull GroupEntity gruppo, @NonNull CanaleEntity canale) {
        return gruppoCanaleDAO.get().findByGruppoAndCanale(gruppo, canale);
    }

    @Override
    public List<GruppoCanaleEntity> findAllGruppoCanale() {
        return gruppoCanaleDAO.get().findAll();
    }

    @Override
    public List<GruppoCanaleEntity> findAllByGruppo(@NonNull GroupEntity gruppo) {
        return gruppoCanaleDAO.get().findAllByGruppo(gruppo);
    }

    @Override
    public List<CanaleEntity> findCreatableCanaliByGruppo(@NonNull List<GroupEntity> gruppi) {
        return gruppoCanaleDAO.get().findCreatableCanaliByGruppo(gruppi);
    }

    @Override
    public List<CanaleEntity> findOwnedCanaliByGruppi(List<GroupEntity> gruppi) {
        if (gruppi.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoCanaleDAO.get().findOwnedCanaliByGruppi(gruppi);
    }

    @Override
    public GruppoCanaleEntity persistGruppoCanale(@NonNull GruppoCanaleEntity entity) {
        return gruppoCanaleDAO.get().save(entity);
    }

    @Override
    public List<Long> findOwnershipCodiciCanaleByGruppi(@NonNull Collection<GroupEntity> gruppi) {
        if ((gruppi == null) || gruppi.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoCanaleDAO.get().findOwnershipCodiciCanaleByGruppi(gruppi);
    }

    @Override
    public List<Long> findOwnershipCodiciCanaleByGruppiAndCompratori(Collection<String> gruppi,
            Collection<String> compratori) {
        if ((gruppi == null) || gruppi.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoCanaleDAO.get().findOwnershipCodiciCanaleByGruppiAndCompratori(gruppi, compratori);
    }

    @Override
    public GruppoRepartoEntity findGruppoRepartoByGruppoAndReparto(@NonNull GroupEntity gruppo,
            @NonNull RepartoEntity reparto) {
        return gruppoRepartoDaoInstance.get().findByGruppoAndReparto(gruppo, reparto);
    }

    @Override
    public GruppoRepartoEntity saveGruppoReparto(@NonNull GruppoRepartoEntity entity) {
        return gruppoRepartoDaoInstance.get().save(entity);
    }

    @Override
    public Collection<GruppoRepartoEntity> saveGruppoReparti(Collection<GruppoRepartoEntity> entities) {
        return gruppoRepartoDaoInstance.get().save(entities);
    }

    @Override
    public List<GruppoRepartoEntity> findAllGruppoRepartoByGruppo(@NonNull GroupEntity gruppo) {
        return gruppoRepartoDaoInstance.get().findAllByGruppo(gruppo);
    }

    @Override
    public List<RepartoEntity> findAllReparti() {
        return repartoDaoInstance.get().findAll();
    }

    @Override
    public RepartoEntity findRepartoById(Integer id) {
        return repartoDaoInstance.get().findById(id);
    }

    @Override
    public void removeGruppoReparto(GruppoRepartoEntity entity) {
        gruppoRepartoDaoInstance.get().remove(entity);
    }

    @Override
    public void removeAllGruppoRepartoByGruppo(GroupEntity group) {
        gruppoRepartoDaoInstance.get().removeAllByGruppo(group);
    }

    @Override
    public List<CompratoreEntity> findAllCompratori() {
        return compratoreDAOInstance.get().findAll();
    };

    @Override
    public List<CompratoreEntity> findAllCompratoriByGroup(@NonNull GroupEntity group) {
        return compratoreDAOInstance.get().findAllByGroup(group);
    };

    @Override
    public List<GruppoCompratoreEntity> findAllGruppoCompratore() {
        return gruppoCompratoreDAOInstance.get().findAll();
    };

    @Override
    public List<GruppoCompratoreEntity> findAllGruppoCompratoreByGroup(@NonNull GroupEntity group) {
        return gruppoCompratoreDAOInstance.get().findAllByGroup(group);
    }

    @Override
    public GruppoCompratoreEntity findGruppoCompratoreByGroupAndCompratore(@NonNull GroupEntity group,
            @NonNull CompratoreEntity compratore) {
        return gruppoCompratoreDAOInstance.get().findByGroupAndCompratore(group, compratore);
    }

    @Override
    public GruppoCompratoreEntity saveGruppoCompratore(@NonNull GruppoCompratoreEntity gruppoCompratore) {
        return gruppoCompratoreDAOInstance.get().save(gruppoCompratore);
    }

    @Override
    public Collection<GruppoCompratoreEntity> saveGruppoCompratore(Collection<GruppoCompratoreEntity> entities) {
        return gruppoCompratoreDAOInstance.get().save(entities);
    }

    @Override
    public CompratoreEntity findCompratoreById(Integer id) {
        return compratoreDAOInstance.get().findById(id);
    }

    @Override
    public void removeGruppoCompratore(GruppoCompratoreEntity entity) {
        gruppoCompratoreDAOInstance.get().remove(entity);
    }

    @Override
    public void removeAllGruppoCompratoreByGruppo(GroupEntity group) {
        gruppoCompratoreDAOInstance.get().removeAllByGruppo(group);
    }

    @Override
    public List<String> findAllCodiciCompratoreByGroupAndTipoAccesso(List<GroupEntity> groups,
            PianificazioneSecurityEnum security) {
        if ((groups == null) || groups.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoCompratoreDAOInstance.get().findAllCodiciCompratoreByGroupAndTipoAccesso(groups, security);
    }

    @Override
    public List<String> findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(List<String> groups,
            PianificazioneSecurityEnum security) {
        if ((groups == null) || groups.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoCompratoreDAOInstance.get().findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(groups, security);
    }

    @Override
    public List<String> findAllCodiciCompratoreByCodiciGruppo(List<String> codiciGruppo) {
        if ((codiciGruppo == null) || codiciGruppo.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return gruppoCompratoreDAOInstance.get().findAllCodiciCompratoreByCodiciGruppo(codiciGruppo);
    }

    @Override
    public List<GruppoGrmEntity> findAllGruppoGrm() {
        return gruppoGrmDAOInstance.get().findAll();
    }

    @Override
    public List<GruppoGrmEntity> findGruppoGrmByGroup(@NonNull GroupEntity group) {
        return gruppoGrmDAOInstance.get().findByGroup(group);
    }

    @Override
    public GruppoGrmEntity findGruppoGrmByGroupAndGrm(@NonNull GroupEntity group, @NonNull GrmEntity grm) {
        return gruppoGrmDAOInstance.get().findByGroupAndGrm(group, grm);
    }

    @Override
    public List<GrmEntity> findAllGrm() {
        return grmDAOInstance.get().findAll();
    }

    @Override
    public List<GrmEntity> findAllGrmByGroup(@NonNull GroupEntity group) {
        return grmDAOInstance.get().findAllByGroup(group);
    }

    @Override
    public GrmEntity findGrmById(Integer id) {
        return grmDAOInstance.get().findById(id);
    }

    @Override
    public void removeGruppoGrm(GruppoGrmEntity entity) {
        gruppoGrmDAOInstance.get().remove(entity);
    }

    @Override
    public void removeAllGruppoGrmByGruppo(GroupEntity group) {
        gruppoGrmDAOInstance.get().removeAllByGruppo(group);
    }

    @Override
    public GruppoGrmEntity saveGruppoGrm(@NonNull GruppoGrmEntity gruppoGrm) {
        return gruppoGrmDAOInstance.get().save(gruppoGrm);
    }

    @Override
    public Collection<GruppoGrmEntity> saveGruppoGrm(Collection<GruppoGrmEntity> entities) {
        return gruppoGrmDAOInstance.get().save(entities);
    }

    @Override
    public List<GruppoUtenteDto> findAllGroupsWithUsers() {
        return groupDAO.get().findAllWithUsers();
    }

    @Override
    public GruppoCanaleEntity findByCodiceGruppoAndCodiceCanale(@NonNull String gruppo, @NonNull Long canale) {
        return gruppoCanaleDAO.get().findByCodiceGruppoAndCodiceCanale(gruppo, canale);
    }

}
