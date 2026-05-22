package com.axiante.mui.persistence.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.axiante.mui.common.PianificazioneSecurityEnum;
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

import lombok.NonNull;

public interface MuiService extends Serializable {
    void addMenuPermissions(@NonNull List<MenuEntity> menuEntityList, RolesEntity rolesEntity) throws Exception;

    AclEntity findACL(@NonNull Integer id) throws Exception;

    ApplicationPropertiesEntity findApplicationProperty(String key) throws Exception;

    boolean deleteApplicationProperty(String key) throws Exception;

    List<ConfigurationEntity> findConfigurationByIdMenu(final Integer idMenu);

    ConfigurationEntity findConfiguration(final Integer idMenu, final ConfigurationTypes type);

    ConfigurationEntity findConfigurations(@NonNull Integer id) throws Exception;

    ConfigurationEntity findConfigurations(@NonNull String path, @NonNull ConfigurationTypes type) throws Exception;

    MenuEntity findMenu(@NonNull Integer id) throws Exception;

    RolesEntity findRole(@NonNull Integer id) throws Exception;

    UsersEntity findUser(@NonNull Integer id) throws Exception;

    UsersEntity findUser(@NonNull String username) throws Exception;

    AclEntity persistACL(@NonNull AclEntity acl) throws Exception;

    ApplicationPropertiesEntity persistApplicationProperty(
            @NonNull ApplicationPropertiesEntity applicationPropertiesEntity) throws Exception;

    void refreshApplicationProperties() throws Exception;

    ConfigurationEntity persistConfigurations(@NonNull ConfigurationEntity configurationEntity) throws Exception;

    ConnectionSetupEntity persistConnectionSetup(@NonNull ConnectionSetupEntity connectionSetupEntity) throws Exception;

    MenuEntity persistMenu(@NonNull MenuEntity menu) throws Exception;

    RolesEntity persistRole(@NonNull RolesEntity role) throws Exception;

    GroupEntity persistGroup(@NonNull GroupEntity group) throws Exception;

    List<GroupEntity> persistGroups(final List<GroupEntity> group) throws Exception;

    CanaleEntity persistCanale(CanaleEntity canale) throws Exception;

    Collection<CanaleEntity> persistCanali(Collection<CanaleEntity> canali) throws Exception;

    UsersEntity persistUser(@NonNull UsersEntity user) throws Exception;

    // ACLs methods
    List<AclEntity> readACLs() throws Exception;

    // application properties
    List<ApplicationPropertiesEntity> readApplicationProperties() throws Exception;

    List<MenuEntity> readByParentId(@NonNull Integer id) throws Exception;

    List<String> readComponentGroups() throws Exception;

    // Configurations methods
    List<ConfigurationEntity> readConfigurations() throws Exception;

    List<ConfigurationEntity> readConfigurations(ConfigurationTypes type) throws Exception;

    // connection setup
    List<ConnectionSetupEntity> readConnectionSetups() throws Exception;

    // Menus methods
    List<MenuEntity> readMenus() throws Exception;

    List<MenuEntity> readMenus(@NonNull RolesEntity rolesEntity) throws Exception;

    List<GroupEntity> readGroups() throws Exception;

    // RolesEntity methods
    List<RolesEntity> readRoles() throws Exception;

    List<MenuEntity> readTopMenus() throws Exception;

    List<CanaleEntity> readCanali() throws Exception;

    // UsersEntity methods
    List<UsersEntity> readUsers() throws Exception;

    void removeACL(@NonNull AclEntity acl) throws Exception;

    void removeConfigurations(@NonNull ConfigurationEntity configurationEntity) throws Exception;

    void removeConnectionSetup(@NonNull ConnectionSetupEntity connectionSetupEntity) throws Exception;

    void removeMenu(@NonNull MenuEntity menuEntity) throws Exception;

    void removeMenuPermissions(@NonNull List<MenuEntity> menuEntityList, RolesEntity rolesEntity) throws Exception;

    boolean removeMenuPermissions(@NonNull RolesEntity rolesEntity) throws Exception;

    void removeRole(@NonNull RolesEntity role) throws Exception;

    void removeGroup(@NonNull GroupEntity group) throws Exception;

    void removeUser(@NonNull UsersEntity user) throws Exception;

    boolean updateMenuPermissions(@NonNull Set<MenuEntity> list, RolesEntity rolesEntity) throws Exception;

    GroupEntity findGroupById(Integer id) throws Exception;

    List<GroupEntity> findGroupsByUser(UsersEntity user) throws Exception;

    MenuRoleEntity findMenuRoleByMenuAndRole(MenuEntity menu, RolesEntity role);

    MenuRoleEntity findMenuRoleByMenuAndRoles(MenuEntity menu, Set<RolesEntity> roles);

    List<MenuEntity> findAllVisibleMenuByRole(RolesEntity role);

    boolean toggleAdminModeVisible(MenuRoleEntity menuRole);

    void changeAdminModeVisible(List<MenuRoleEntity> menuRoles, boolean visible);

    void resetAllExceptions();

    List<UploadDocumentEntity> readDocuments();

    void removeDocument(@NonNull UploadDocumentEntity document) throws Exception;

    UploadDocumentEntity persistDocument(@NonNull UploadDocumentEntity document, @NonNull String user);

    UploadDocumentEntity findDocumentByName(@NonNull String name) throws Exception;

    boolean menuBelongsToRoles(MenuEntity menu, Set<RolesEntity> r);

    boolean menuBelongsToRole(MenuEntity menu, RolesEntity r);

    List<MuiContext> findAllContexts();

    MuiContext findContextById(Long id);

    MuiContext findContextByCode(String code);

    MuiContext saveContext(MuiContext context) throws Exception;

    List<GroupEntity> findGroupsByContextCode(String contextCode);

    GruppoCanaleEntity findByGruppoAndCanale(GroupEntity gruppo, CanaleEntity canale);

    List<GruppoCanaleEntity> findAllGruppoCanale();

    List<GruppoCanaleEntity> findAllByGruppo(GroupEntity gruppo);

    List<CanaleEntity> findCreatableCanaliByGruppo(List<GroupEntity> gruppi);

    List<CanaleEntity> findOwnedCanaliByGruppi(List<GroupEntity> gruppi);

    GruppoCanaleEntity persistGruppoCanale(GruppoCanaleEntity entity);

    List<Long> findOwnershipCodiciCanaleByGruppi(Collection<GroupEntity> gruppi);

    List<Long> findOwnershipCodiciCanaleByGruppiAndCompratori(Collection<String> gruppi, Collection<String> compratori);

    GruppoRepartoEntity findGruppoRepartoByGruppoAndReparto(GroupEntity gruppo, RepartoEntity reparto);

    GruppoRepartoEntity saveGruppoReparto(GruppoRepartoEntity entity);

    Collection<GruppoRepartoEntity> saveGruppoReparti(Collection<GruppoRepartoEntity> entities);

    List<GruppoRepartoEntity> findAllGruppoRepartoByGruppo(GroupEntity gruppo);

    List<RepartoEntity> findAllReparti();

    RepartoEntity findRepartoById(Integer id);

    void removeGruppoReparto(GruppoRepartoEntity entity);

    void removeAllGruppoRepartoByGruppo(GroupEntity group);

    List<CompratoreEntity> findAllCompratori();

    List<CompratoreEntity> findAllCompratoriByGroup(GroupEntity group);

    List<GruppoCompratoreEntity> findAllGruppoCompratore();

    List<GruppoCompratoreEntity> findAllGruppoCompratoreByGroup(GroupEntity group);

    GruppoCompratoreEntity findGruppoCompratoreByGroupAndCompratore(GroupEntity group, CompratoreEntity compratore);

    GruppoCompratoreEntity saveGruppoCompratore(GruppoCompratoreEntity gruppoCompratore);

    Collection<GruppoCompratoreEntity> saveGruppoCompratore(Collection<GruppoCompratoreEntity> entities);

    CompratoreEntity findCompratoreById(Integer id);

    void removeGruppoCompratore(GruppoCompratoreEntity entity);

    void removeAllGruppoCompratoreByGruppo(GroupEntity group);

    List<String> findAllCodiciCompratoreByGroupAndTipoAccesso(List<GroupEntity> groups,
            PianificazioneSecurityEnum security);

    List<String> findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(List<String> groups,
            PianificazioneSecurityEnum security);

    List<String> findAllCodiciCompratoreByCodiciGruppo(List<String> codiciGruppo);

    List<GruppoGrmEntity> findAllGruppoGrm();

    List<GruppoGrmEntity> findGruppoGrmByGroup(GroupEntity group);

    GruppoGrmEntity findGruppoGrmByGroupAndGrm(GroupEntity group, GrmEntity grm);

    List<GrmEntity> findAllGrm();

    List<GrmEntity> findAllGrmByGroup(GroupEntity group);

    GrmEntity findGrmById(Integer id);

    void removeGruppoGrm(GruppoGrmEntity entity);

    void removeAllGruppoGrmByGruppo(GroupEntity group);

    GruppoGrmEntity saveGruppoGrm(GruppoGrmEntity gruppoGrm);

    Collection<GruppoGrmEntity> saveGruppoGrm(Collection<GruppoGrmEntity> entities);

    List<GruppoUtenteDto> findAllGroupsWithUsers();

    GruppoCanaleEntity findByCodiceGruppoAndCodiceCanale(@NonNull String gruppo, @NonNull Long canale) ;

}