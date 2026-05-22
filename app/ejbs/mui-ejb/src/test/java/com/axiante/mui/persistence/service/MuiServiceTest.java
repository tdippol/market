package com.axiante.mui.persistence.service;

import com.axiante.mui.persistence.dao.*;
import com.axiante.mui.persistence.entity.*;
import com.axiante.mui.persistence.service.impl.MuiServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MuiServiceTest {

	@Mock
	private Instance<AclDAO> aclDAO;

	@Mock
	private AclDAO aclDAOMock;

	@Mock
	private Instance<RolesDAO> rolesDAO;

	@Mock
	private RolesDAO rolesDAOMock;

	@Mock
	private Instance<UsersDAO> usersDAO;

	@Mock
	private UsersDAO usersDAOMock;

	@Mock
	private Instance<MenuDAO> menuDAO;

	@Mock
	private MenuDAO menuDAOMock;

	@Mock
	private Instance<ApplicationPropertiesDAO> applicationPropertiesDAO;

	@Mock
	private ApplicationPropertiesDAO applicationPropertiesDAOMock;

	@Mock
	private Instance<ConnectionSetupDAO> connectionSetupDAO;

	@Mock
	private ConnectionSetupDAO connectionSetupDAOMock;

	@Mock
	private Instance<ConfigurationsDAO> configurationsDAO;

	@Mock
	private ConfigurationsDAO configurationsDAOMock;

	@Mock
	private Instance<GroupDAO> groupDAO;

	@Mock
	private GroupDAO groupDAOMock;

	@Mock
	private Instance<CanaleDAO> canaleDAO;

	@Mock
	private CanaleDAO canaleDAOMock;

	@Mock
	private Instance<MenuRoleDAO> menuRoleDAO;

	@Mock
	private MenuRoleDAO menuRoleDAOMock;

	@Mock
	private Instance<UploadDocumentDAO> uploadDocumentDAO;

	@Mock
	private UploadDocumentDAO uploadDocumentDAOMock;

	@Mock
	private Instance <GruppoCanaleDAO> gruppoCanaleDAO;

	@Mock
	private GruppoCanaleDAO gruppoCanaleMock;

	@Mock
	private Instance<GruppoRepartoDAO> gruppoRepartoDaoInstance;

	@Mock
	private GruppoRepartoDAO gruppoRepartoMock;

	@Mock
	private Instance<RepartoDAO> repartoDaoInstance;

	@Mock
	private RepartoDAO repartoMock;

	@Mock
	private Instance<CompratoreDAO> compratoreDAOInstance;
	@Mock
	private CompratoreDAO compratoreDAO;

	@Mock
	Instance<GruppoCompratoreDAO> gruppoCompratoreDAOInstance;
	@Mock
	private GruppoCompratoreDAO gruppoCompratoreDAO;

	@Mock
	Instance<GruppoGrmDAO> gruppoGrmDAOInstance;
	@Mock
	private GruppoGrmDAO gruppoGrmDAO;
	@Mock
	Instance<GrmDAO> grmDAOInstance;
	@Mock
	private GrmDAO grmDAO;




	@Spy
	@InjectMocks
	private final MuiServiceImpl service = new MuiServiceImpl();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		when(aclDAO.get()).thenReturn(aclDAOMock);
		when(rolesDAO.get()).thenReturn(rolesDAOMock);
		when(usersDAO.get()).thenReturn(usersDAOMock);
		when(menuDAO.get()).thenReturn(menuDAOMock);
		when(applicationPropertiesDAO.get()).thenReturn(applicationPropertiesDAOMock);
		when(connectionSetupDAO.get()).thenReturn(connectionSetupDAOMock);
		when(configurationsDAO.get()).thenReturn(configurationsDAOMock);
		when(groupDAO.get()).thenReturn(groupDAOMock);
		when(canaleDAO.get()).thenReturn(canaleDAOMock);
		when(menuRoleDAO.get()).thenReturn(menuRoleDAOMock);
		when(uploadDocumentDAO.get()).thenReturn(uploadDocumentDAOMock);
		when(gruppoCanaleDAO.get()).thenReturn(gruppoCanaleMock);
		when(gruppoRepartoDaoInstance.get()).thenReturn(gruppoRepartoMock);
		when(repartoDaoInstance.get()).thenReturn(repartoMock);
		when(compratoreDAOInstance.get()).thenReturn(compratoreDAO);
		when(gruppoCompratoreDAOInstance.get()).thenReturn(gruppoCompratoreDAO);
		when(gruppoGrmDAOInstance.get()).thenReturn(gruppoGrmDAO);
		when(grmDAOInstance.get()).thenReturn(grmDAO);
	}

	@Test
	public void testReadUsers() throws Exception {
		service.readUsers();
		verify(usersDAOMock).readAll();
	}

	@Test
	public void testPersistUser() throws Exception {
		final UsersEntity entity = mock(UsersEntity.class);
		service.persistUser(entity);
		verify(usersDAOMock).persist(entity);
	}

	@Test
	public void testRemoveUser() throws Exception {
		final UsersEntity entity = mock(UsersEntity.class);
		service.removeUser(entity);
		verify(usersDAOMock).remove(entity);
	}

	@Test
	public void testFindUserById() throws Exception {
		service.findUser(1);
		verify(usersDAOMock).findById(1);
	}

	@Test
	public void testFindUserByName() throws Exception {
		service.findUser("tst");
		verify(usersDAOMock).findByUsername("tst");
	}


	@Test
	public void testReadACLs() throws Exception {
		service.readACLs();
		verify(aclDAOMock).readAll();
	}

	@Test
	public void testPersistACL() throws Exception {
		final AclEntity acl = mock(AclEntity.class);
		service.persistACL(acl);
		verify(aclDAOMock).persist(acl);
	}

	@Test
	public void testRemoveACL() throws Exception {
		final AclEntity acl = mock(AclEntity.class);
		service.removeACL(acl);
		verify(aclDAOMock).remove(acl);
	}

	@Test
	public void testFindACL() throws Exception {
		service.findACL(1);
		verify(aclDAOMock).findById(1);
	}

	@Test
	public void testReadComponentGroups() throws Exception {
		service.readComponentGroups();
		verify(aclDAOMock).readComponentGroups();
	}

	@Test
	public void testReadMenus() throws Exception {
		service.readMenus();
		verify(menuDAOMock).readAll();
	}

	@Test
	public void testReadMenusByRole() throws Exception {
		final RolesEntity rolesEntity = mock(RolesEntity.class);
		service.readMenus(rolesEntity);
		verify(menuDAOMock).readAll(rolesEntity);
	}

	@Test
	public void testFindMenu() throws Exception {
		service.findMenu(1);
		verify(menuDAOMock).findById(1);
	}
	@Test
	public void testRemoveMenuPermissions() throws Exception {
		// final List<MenuEntity> menuEntityList,final RolesEntity rolesEntity
		final MenuEntity menu = mock(MenuEntity.class);
		final RolesEntity role = mock (RolesEntity.class);
		final Set<MenuEntity> menus = new HashSet<>();
		menus.add(menu);
		final Set<RolesEntity> roles = new HashSet<>();
		roles.add(role);
		when(role.getMenus()).thenReturn(menus);
		when(menu.getRoles()).thenReturn(roles);
		when(role.removeMenu(menu)).thenCallRealMethod();
		when(menu.removeRole(role)).thenCallRealMethod();
		assertThat(menus,hasItem(menu));
		assertThat(roles,hasItem(role));

		service.removeMenuPermissions(Collections.singletonList(menu),role);

		verify(role).removeMenu(menu);
		verify(menu,times(2)).removeRole(role);
		assertThat(menus, not(hasItem(menu)));
		assertThat(roles,not(hasItem(role)));
	}

	@Test
	public void testRemoveMenuPermissionsCalledWithRolesOnly() throws Exception {
		final MenuEntity menu = mock(MenuEntity.class);
		final RolesEntity role = mock (RolesEntity.class);
		final Set<MenuEntity> menus = new HashSet<>();
		menus.add(menu);
		final Set<RolesEntity> roles = new HashSet<>();
		roles.add(role);
		when(role.getMenus()).thenReturn(menus);
		when(menu.getRoles()).thenReturn(roles);
		when(role.removeMenu(menu)).thenCallRealMethod();
		when(menu.removeRole(role)).thenCallRealMethod();
		assertThat(menus,hasItem(menu));
		assertThat(roles,hasItem(role));

		service.removeMenuPermissions(role);

		verify(service).removeMenuPermissions(ArgumentMatchers.any(), ArgumentMatchers.eq(role));
		verify(role).removeMenu(menu);
		verify(menu,times(2)).removeRole(role);
		assertThat(menus, not(hasItem(menu)));
		assertThat(roles,not(hasItem(role)));

	}
	@Test
	public void testRemoveMenuPermissionsCalledWithNullRolesOnly() throws Exception {
		expectedException.expect(NullPointerException.class);
		service.removeMenuPermissions(null);
	}

	@Test
	public void testPersistMenu() throws Exception {
		final MenuEntity menu = mock(MenuEntity.class);
		service.persistMenu(menu);
		verify(menuDAOMock).persist(menu);
	}

	@Test
	public void testReadConnectionSetups() throws Exception {
		service.readConnectionSetups();
		verify(connectionSetupDAOMock).readAll();
	}

	@Test
	public void testPersistConnectionSetup() throws Exception {
		final ConnectionSetupEntity connectionSetupEntity = mock(ConnectionSetupEntity.class);
		service.persistConnectionSetup(connectionSetupEntity);
		verify(connectionSetupDAOMock).persist(connectionSetupEntity);
	}

	@Test
	public void testReadApplicationProperties() throws Exception {
		service.readApplicationProperties();
		verify(applicationPropertiesDAOMock).readAll();
	}

	@Test
	public void testPersistApplicationProperty() throws Exception {
		final ApplicationPropertiesEntity applicationPropertiesEntity = mock(ApplicationPropertiesEntity.class);
		service.persistApplicationProperty(applicationPropertiesEntity);
		verify(applicationPropertiesDAOMock).persist(applicationPropertiesEntity);
	}

	@Test
	public void testReadConfigurations() throws Exception {
		service.readConfigurations();
		verify(configurationsDAOMock).readAll();
	}

	@Test
	public void testReadConfigurationsByType() throws Exception {
		service.readConfigurations(ConfigurationTypes.FILTER);
		verify(configurationsDAOMock).findByType(ConfigurationTypes.FILTER);
	}

	@Test
	public void testPersistConfigurations() throws Exception {
		final ConfigurationEntity entity = mock(ConfigurationEntity.class);
		service.persistConfigurations(entity);
		verify(configurationsDAOMock).persist(entity);
	}

	@Test
	public void testRemoveConfigurations() throws Exception {
		final ConfigurationEntity entity = mock(ConfigurationEntity.class);
		service.removeConfigurations(entity);
		verify(configurationsDAOMock).remove(entity);

	}

	@Test
	public void testFindConfigurationsById() throws Exception {
		service.findConfigurations(1);
		verify(configurationsDAOMock).findById(1);
	}

	@Test
	public void testFindConfigurationsByPathAndType() throws Exception {
		service.findConfigurations("aPath", ConfigurationTypes.FILTER);
		verify(configurationsDAOMock).findByPath("aPath", ConfigurationTypes.FILTER);
	}

	@Test
	public void testReadRoles() throws Exception {
		service.readRoles();
		verify(rolesDAOMock).readAll();
	}

	@Test
	public void testPersistRole() throws Exception {
		final RolesEntity role = mock(RolesEntity.class);
		service.persistRole(role);
		verify(rolesDAOMock).persist(role);
	}

	@Test
	public void testRemoveRole() throws Exception {
		final RolesEntity role = mock(RolesEntity.class);
		service.removeRole(role);
		verify(rolesDAOMock).remove(role);
	}

	@Test
	public void testRemoveRoleThrowsException() throws Exception {
		final RolesEntity role = mock(RolesEntity.class);
		final UsersEntity user = mock(UsersEntity.class);
		when(role.getUsers()).thenReturn(new HashSet<>(Collections.singletonList(user)));
		when(role.getName()).thenReturn("mocked");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Existing users with role mocked" );
		service.removeRole(role);
		verify(rolesDAOMock).remove(role);
	}

	@Test
	public void testFindRole() throws Exception {
		service.findRole(1);
		verify(rolesDAOMock).findById(1);
	}

	@Test
	public void testReadTopMenus() throws Exception{
		service.readTopMenus();
		verify(menuDAOMock).readNullParent();
	}

	@Test
	public void testRemoveConnectionSetup() throws Exception {
		final ConnectionSetupEntity mockedEntity = mock(ConnectionSetupEntity.class);
		service.removeConnectionSetup(mockedEntity);
		verify(connectionSetupDAOMock).remove(mockedEntity);
	}

	@Test
	public void testReadByParentId() throws Exception{
		final int id = new Random().nextInt();
		service.readByParentId(id);
		verify(menuDAOMock).readByParentId(id);
	}

	@Test
	public void testRemoveMenu() throws Exception{
		//(@NotNull final MenuEntity menuEntity) throws Exception{
		//   menuDAO.remove(menuEntity);
		final MenuEntity menuEntity = mock(MenuEntity.class);
		service.removeMenu(menuEntity);
		verify(menuDAOMock).remove(menuEntity);
	}
	@Test
	public void testRemoveMenuThrowsExceptionWhenMenuIsNull() throws Exception{
		expectedException.expect(NullPointerException.class);
		service.removeMenu(null);
		verify(menuDAOMock).remove(null);
	}

	@Test
	public void testAdddMenuPermissions() throws Exception{
		final MenuEntity menu = mock(MenuEntity.class);
		final RolesEntity role = mock(RolesEntity.class);
		final List<MenuEntity> menuEntityList = Collections.singletonList(menu);
		final Set<RolesEntity> menuRoles = new HashSet<>();
		when(menu.getRoles()).thenReturn(menuRoles);
		when(menu.addRole(role)).thenCallRealMethod();
		service.addMenuPermissions(menuEntityList, role);
		verify(menu).addRole(role);
		assertThat(menuRoles.size(), is(1));
		assertThat(menuRoles, hasItem(role));
	}

	@Test
	public void testUpdateMenuPermissions() throws Exception {
		final MenuEntity menu = mock(MenuEntity.class);
		final MenuEntity menu2 = mock(MenuEntity.class);
		final RolesEntity role = mock(RolesEntity.class);
		final Set<MenuEntity> list = new HashSet<>();
		list.add(menu2); // keep menu 2
		final Set<MenuEntity> menuRoles = new HashSet<>();
		final Set<RolesEntity> roles2 = new HashSet<>();
		roles2.add(role);
		final Set<RolesEntity> roles = new HashSet<>();
		roles.add(role);

		menuRoles.add(menu2);
		menuRoles.add(menu);

		when(role.getMenus()).thenReturn(menuRoles);
		when(menu.getRoles()).thenReturn(roles);

		assertThat(menuRoles, hasItems(menu, menu2));
		assertThat(roles, hasItem(role));
		assertThat(roles2, hasItem(role));

		when(menu.removeRole(role)).thenCallRealMethod();
		service.updateMenuPermissions(list, role);
		assertThat(roles, not(hasItem(role)));
		assertThat(roles2, hasItem(role));
	}

	@Test
	public void testUpdateMenuPermissionsReturnsFalseWhenExceptionDuringPersist() throws Exception {
		doThrow(Exception.class).when(menuDAOMock).persist(ArgumentMatchers.any(MenuEntity.class));
		final MenuEntity menu = mock(MenuEntity.class);
		final MenuEntity menu2 = mock(MenuEntity.class);
		final RolesEntity role = mock(RolesEntity.class);
		final Set<MenuEntity> list = new HashSet<>();
		list.add(menu2); // keep menu 2
		final Set<MenuEntity> menuRoles = new HashSet<>();
		final Set<RolesEntity> roles2 = new HashSet<>();
		roles2.add(role);
		final Set<RolesEntity> roles = new HashSet<>();
		roles.add(role);

		menuRoles.add(menu2);
		menuRoles.add(menu);

		when(role.getMenus()).thenReturn(menuRoles);
		when(menu.getRoles()).thenReturn(roles);

		assertThat(menuRoles, hasItems(menu, menu2));
		assertThat(roles, hasItem(role));
		assertThat(roles2, hasItem(role));

		when(menu.removeRole(role)).thenCallRealMethod();
		assertFalse(service.updateMenuPermissions(list, role));

	}

	@Test
	public void findMenuRoleByMenuAndRole() {
		final MenuEntity menu1 = mock(MenuEntity.class);
		final MenuEntity menu2 = mock(MenuEntity.class);
		final RolesEntity role1 = mock(RolesEntity.class);
		final RolesEntity role2 = mock(RolesEntity.class);
		final MenuRoleEntity menuRole = mock(MenuRoleEntity.class);
		when(menuRoleDAOMock.findByMenuAndRole(menu1, role1)).thenReturn(menuRole);
		final MenuRoleEntity entity1 = service.findMenuRoleByMenuAndRole(menu1, role1);
		assertNotNull(entity1);
		verify(menuRoleDAOMock, times(1)).findByMenuAndRole(menu1, role1);
		verify(menuRoleDAOMock, never()).findByMenuAndRole(menu1, role2);
		verify(menuRoleDAOMock, never()).findByMenuAndRole(menu2, role1);
		verify(menuRoleDAOMock, never()).findByMenuAndRole(menu2, role2);
	}

	@Test
	public void findAllVisibleMenuByRole() {
		final MenuEntity menu1 = mock(MenuEntity.class);
		final MenuEntity menu2 = mock(MenuEntity.class);
		final RolesEntity role1 = mock(RolesEntity.class);
		final RolesEntity role2 = mock(RolesEntity.class);
		when(menuRoleDAOMock.findAllVisibleByRole(role1)).thenReturn(Arrays.asList(menu1, menu2));
		final List<MenuEntity> entities = service.findAllVisibleMenuByRole(role1);
		assertFalse(entities.isEmpty());
		assertEquals(2, entities.size());
		verify(menuRoleDAOMock, times(1)).findAllVisibleByRole(role1);
		verify(menuRoleDAOMock, never()).findAllVisibleByRole(role2);
	}

	@Test
	public void toggleAdminModeVisible_givenVisibleTrue_shouldSetToFalse_andReturnIt() {
		final MenuRoleEntity menuRole = spy(MenuRoleEntity.class);
		menuRole.setAdminModeVisible(true);
		when(menuRoleDAOMock.toggleAdminModeVisible(menuRole)).thenReturn(false);
		assertFalse(service.toggleAdminModeVisible(menuRole));
		verify(menuRoleDAOMock, times(1)).toggleAdminModeVisible(menuRole);
	}

	@Test
	public void toggleAdminModeVisible_givenVisibleFalse_shouldSetToTrue_andReturnIt() {
		final MenuRoleEntity menuRole = spy(MenuRoleEntity.class);
		menuRole.setAdminModeVisible(false);
		when(menuRoleDAOMock.toggleAdminModeVisible(menuRole)).thenReturn(true);
		assertTrue(service.toggleAdminModeVisible(menuRole));
		verify(menuRoleDAOMock, times(1)).toggleAdminModeVisible(menuRole);
	}

	@Test
	public void readDocuments_shouldReturnsAllDocuments() {
		// Arrange
		List<UploadDocumentEntity> list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			list.add(mockUploadDocumentEntity(i, String.format("document-%03d.txt", i)));
		}
		when(uploadDocumentDAOMock.readAll()).thenReturn(list);
		// Act & Assert
		final List<UploadDocumentEntity> documents = service.readDocuments();
		assertEquals(5, documents.size());
	}

	@Test(expected = NullPointerException.class)
	public void removeDocument_givenNullEntity_shouldThrowException() throws Exception {
		service.removeDocument(null);
	}

	@Test
	public void removeDocument_givenValidEntity_shouldRemoveIt() throws Exception {
		final UploadDocumentEntity entity = mock(UploadDocumentEntity.class);
		service.removeDocument(entity);
		verify(uploadDocumentDAOMock, times(1)).delete(entity);
	}

	@Test(expected = NullPointerException.class)
	public void persistDocument_givenNullEntity_shouldThrowException() {
		service.persistDocument(null, "junit");
	}

	@Test(expected = NullPointerException.class)
	public void persistDocument_givenNullUser_shouldThrowException() {
		final UploadDocumentEntity entity = mockUploadDocumentEntity(1, "foo.txt");
		service.persistDocument(entity, null);
	}

	@Test
	public void persistDocument_givenValidEntity_shouldSaveIt() {
		final UploadDocumentEntity entity = new UploadDocumentEntity();
		entity.setName("foo.txt");
		service.persistDocument(entity, "junit");
		verify(uploadDocumentDAOMock, times(1)).persistWithAuditLog(entity, "junit");
	}

	@Test(expected = NullPointerException.class)
	public void findDocumentByName_givenNullName_shouldThrowException() throws Exception {
		service.findDocumentByName(null);
	}

	@Test
	public void findDocumentByName_givenExistingName_shouldReturnIt() throws Exception {
		final UploadDocumentEntity entity = mockUploadDocumentEntity(1, "foo.txt");
		when(uploadDocumentDAOMock.findByName("foo.txt")).thenReturn(entity);
		final UploadDocumentEntity document = service.findDocumentByName("foo.txt");
		assertNotNull(document);
		assertEquals(1, (int) document.getId());
		assertEquals("foo.txt", document.getName());
		verify(uploadDocumentDAOMock, times(1)).findByName("foo.txt");
	}

	@Test
	public void findDocumentByName_givenNotExistingName_shouldReturnNull() throws Exception {
		when(uploadDocumentDAOMock.findByName("bar.txt")).thenReturn(null);
		assertNull(service.findDocumentByName("bar.txt"));
		verify(uploadDocumentDAOMock, times(1)).findByName("bar.txt");
	}

	@Test(expected = NullPointerException.class)
	public void findApplicationProperty_givenNullKey_shouldThrowException() throws Exception {
		service.findApplicationProperty(null);
	}

	@Test
	public void findApplicationProperty_givenKey_shouldReturnProperty() throws Exception {
		final ApplicationPropertiesEntity entity = mock(ApplicationPropertiesEntity.class);
		when(applicationPropertiesDAOMock.findByCode("foo")).thenReturn(entity);
		final ApplicationPropertiesEntity property = service.findApplicationProperty("foo");
		assertNotNull(property);
		verify(applicationPropertiesDAOMock, times(1)).findByCode("foo");
	}

	@Test
	public void findConfigurationByIdMenu_givenValidId_shouldReturnConfiguration() {
		final ConfigurationEntity entity = mock(ConfigurationEntity.class);
		when(configurationsDAOMock.findByIdMenu(1)).thenReturn(Collections.singletonList(entity));
		final List<ConfigurationEntity> configurations = service.findConfigurationByIdMenu(1);
		assertNotNull(configurations);
		verify(configurationsDAOMock, times(1)).findByIdMenu(1);
	}

	@Test(expected = NullPointerException.class)
	public void findConfigurations_givenNullId_shouldThrowException() throws Exception {
		service.findConfigurations(null);
	}

	@Test(expected = NullPointerException.class)
	public void findConfigurations_givenNullPath_shouldThrowException() throws Exception {
		service.findConfigurations(null, ConfigurationTypes.FILTER);
	}

	@Test(expected = NullPointerException.class)
	public void findConfigurations_givenNullType_shouldThrowException() throws Exception {
		service.findConfigurations("foo", null);
	}

	@Test(expected = NullPointerException.class)
	public void persistConfigurations_givenNullEntity_shouldThrowException() throws Exception {
		service.persistConfigurations(null);
	}

	@Test(expected = NullPointerException.class)
	public void persistMenu_givenNullEntity_shouldThrowException() throws Exception {
		service.persistMenu(null);
	}

	@Test
	public void findGroupById_givenValidId_shouldReturnGroup() throws Exception {
		final GroupEntity entity = mock(GroupEntity.class);
		when(groupDAOMock.findById(1)).thenReturn(entity);
		final GroupEntity group = service.findGroupById(1);
		assertNotNull(group);
		verify(groupDAOMock, times(1)).findById(1);
	}

	@Test
	public void persistGroup_givenValidGroup_shouldPersistIt() throws Exception {
		final GroupEntity entity = mock(GroupEntity.class);
		service.persistGroup(entity);
		verify(groupDAOMock, times(1)).persist(entity);
	}

	@Test
	public void persistCanale_givenValidCanale_shouldPersistIt() throws Exception {
		final CanaleEntity entity = mock(CanaleEntity.class);
		service.persistCanale(entity);
		verify(canaleDAOMock, times(1)).persist(entity);
	}

	@Test
	public void persistCanali_givenListOfCanale_shouldPersistThem() throws Exception {
		final CanaleEntity entity1 = mock(CanaleEntity.class);
		final CanaleEntity entity2 = mock(CanaleEntity.class);
		final List<CanaleEntity> canali = Arrays.asList(entity1, entity2);
		service.persistCanali(canali);
		verify(canaleDAOMock, times(1)).persist(canali);
	}

	@Test
	public void readGroups_shouldReturnListOfGroups() throws Exception {
		final List<GroupEntity> entities = Collections.singletonList(mock(GroupEntity.class));
		when(groupDAOMock.findAll()).thenReturn(entities);
		final List<GroupEntity> groups = service.readGroups();
		assertNotNull(groups);
		assertFalse(groups.isEmpty());
		verify(groupDAOMock, times(1)).findAll();
	}

	@Test
	public void readCanali_shouldReturnListOfCanali() throws Exception {
		final List<CanaleEntity> entities = Collections.singletonList(mock(CanaleEntity.class));
		when(canaleDAOMock.findAll()).thenReturn(entities);
		final List<CanaleEntity> canali = service.readCanali();
		assertNotNull(canali);
		assertFalse(canali.isEmpty());
		verify(canaleDAOMock, times(1)).findAll();
	}

	@Test(expected = NullPointerException.class)
	public void removeConfigurations_givenNullEntity_shouldThrowException() throws Exception {
		service.removeConfigurations(null);
	}

	@Test(expected = NullPointerException.class)
	public void removeGroup_givenNullEntity_shouldThrowException() throws Exception {
		service.removeGroup(null);
	}

	@Test
	public void removeGroup_givenValidEntity_shouldRemoveIt() throws Exception {
		final GroupEntity entity = mock(GroupEntity.class);
		when(groupDAOMock.remove(entity)).thenReturn(entity);
		service.removeGroup(entity);
		verify(groupDAOMock, times(1)).remove(entity);
	}

	@Test(expected = NullPointerException.class)
	public void updateMenuPermissions_givenNullMenuList_shouldThrowException() throws Exception {
		final RolesEntity entity = mock(RolesEntity.class);
		service.updateMenuPermissions(null, entity);
	}

	@Test
	public void refreshApplicationProperties_shouldCallDao() {
		service.refreshApplicationProperties();
		verify(applicationPropertiesDAOMock, times(1)).refreshProperties();
	}

	@Test
	public void changeAdminModeVisible_shouldCallDao() {
		final List<MenuRoleEntity> entities = Collections.singletonList(mock(MenuRoleEntity.class));
		service.changeAdminModeVisible(entities, true);
		verify(menuRoleDAOMock, times(1)).changeAdminModeVisible(entities, true);
	}

	@Test
	public void resetAllExceptions_shouldCallDao() {
		service.resetAllExceptions();
		verify(menuRoleDAOMock, times(1)).resetAllExceptions();
		verify(rolesDAOMock, times(1)).resetAllExceptions();
	}

	private UploadDocumentEntity mockUploadDocumentEntity(int id, String name) {
		final UploadDocumentEntity mock = mock(UploadDocumentEntity.class);
		when(mock.getId()).thenReturn(id);
		when(mock.getName()).thenReturn(name);
		return mock;
	}

	@Test
	public void testWhenfindByGruppoAndCanaleHasDataThenReturnsResult() {
		GruppoCanaleId id = mock(GruppoCanaleId.class);
		GruppoCanaleEntity g = mock(GruppoCanaleEntity.class);
		when(g.getId()).thenReturn(id);
		when(id.getCanaleId()).thenReturn(10010110);
		when(id.getGroupId()).thenReturn(10010111);
		when(gruppoCanaleMock.findByGruppoAndCanale(any(GroupEntity.class),any(CanaleEntity.class))).thenReturn(g);
		GruppoCanaleEntity g1 = service.findByGruppoAndCanale(mock(GroupEntity.class), mock(CanaleEntity.class));
		assertEquals(10010110, (int) g1.getId().getCanaleId());
		assertEquals(10010111, (int) g1.getId().getGroupId());
	}

	@Test(expected = NoResultException.class)
	public void testWhenfindByGruppoAndCanaleHasNoResultsThenThrowsException() {
		when(gruppoCanaleMock.findByGruppoAndCanale(any(GroupEntity.class),any(CanaleEntity.class)))
		.thenThrow(NoResultException.class);
		service.findByGruppoAndCanale(mock(GroupEntity.class), mock(CanaleEntity.class));
	}

	@Test(expected = NonUniqueResultException.class)
	public void testWhenfindByGruppoAndCanaleHasManyResultsThenThrowsException() {
		when(gruppoCanaleMock.findByGruppoAndCanale(any(GroupEntity.class),any(CanaleEntity.class)))
		.thenThrow(NonUniqueResultException.class);
		service.findByGruppoAndCanale(mock(GroupEntity.class), mock(CanaleEntity.class));
	}

	@Test
	public void testWhenfindCreatableCanaliByGruppoHasDataThenReturns() {
		CanaleEntity g = mock(CanaleEntity.class);
		List<CanaleEntity> expected = new ArrayList<>();
		expected.add(g);
		List<GroupEntity> mockList = new ArrayList<>();
		mockList.add(mock(GroupEntity.class));
		when(gruppoCanaleMock.findCreatableCanaliByGruppo(mockList)).thenReturn(expected);
		List<CanaleEntity> result = service.findCreatableCanaliByGruppo(mockList);
		assertEquals(1, result.size());
	}

	@Test(expected = NullPointerException.class)
	public void testfindGruppoRepartoByGruppoAndRepartoThrowsExceptionWhenGruppoIsNull() {
		service.findGruppoRepartoByGruppoAndReparto(null, mock(RepartoEntity.class));
	}
	@Test(expected = NullPointerException.class)
	public void testfindGruppoRepartoByGruppoAndRepartoThrowsExceptionWhenRepartoIsNull() {
		service.findGruppoRepartoByGruppoAndReparto(mock(GroupEntity.class), null );
	}
	@Test
	public void testfindGruppoRepartoByGruppoAndRepartoCallsDao() {
		GroupEntity gruppo = mock(GroupEntity.class);
		RepartoEntity reparto = mock(RepartoEntity.class);
		service.findGruppoRepartoByGruppoAndReparto(gruppo, reparto);
		verify(gruppoRepartoMock,times(1)).findByGruppoAndReparto(gruppo, reparto);

	}
	@Test(expected = NullPointerException.class)
	public void testsaveGruppoRepartoThrowsExceptionWhenEntityIsNull() {
		service.saveGruppoReparto(null);
	}

	@Test
	public void testsaveGruppoRepartoCallsDao() {
		GruppoRepartoEntity entity = mock(GruppoRepartoEntity.class);
		service.saveGruppoReparto(entity);
		verify(gruppoRepartoMock,times(1)).save(entity);
	}

	@Test (expected = NullPointerException.class)
	public void testfindAllGruppoRepartoByGruppoThrowsExceptionWhenGruppoIsNull() {
		service.findAllGruppoRepartoByGruppo(null);
	}

	@Test
	public void testfindAllGruppoRepartoCallsDao() {
		GroupEntity gruppo = mock (GroupEntity.class);
		service.findAllGruppoRepartoByGruppo(gruppo);
		verify(gruppoRepartoMock, times(1)).findAllByGruppo(gruppo);
	}

	@Test
	public void testfindAllRepartiCallsDAO() {
		service.findAllReparti();
		verify(repartoMock, times(1)).findAll();
	}

	@Test
	public void testFindAllCompratoriCallsDAO() {
		service.findAllCompratori();
		verify(compratoreDAO, times(1)).findAll();
	}
	@Test(expected = NullPointerException.class)
	public void testFindAllCompratoriByGroupThrowsExceptionWhenGroupIsNull() {
		service.findAllCompratoriByGroup(null);
	}
	@Test
	public void testFindAllCompratoriByGroupCallsDAO() {
		GroupEntity group = mock(GroupEntity.class);
		service.findAllCompratoriByGroup(group);
		verify(compratoreDAO, times(1)).findAllByGroup(group);
	}

	@Test
	public void testFindAllGruppoCompratoreCallsDAO() {
		service.findAllGruppoCompratore();
		verify(gruppoCompratoreDAO, times(1)).findAll();
	}

	@Test(expected = NullPointerException.class)
	public void testFindAllGruppoCompratoreByGroupThrowsExceptionWhenGroupIsNull() {
		service.findAllGruppoCompratoreByGroup(null);
	}
	@Test
	public void testFindAllGruppoCompratoreByGroupCallsDao() {
		GroupEntity group = mock(GroupEntity.class);
		service.findAllGruppoCompratoreByGroup(group);
		verify(gruppoCompratoreDAO, times(1)).findAllByGroup(group);
	}
	@Test(expected = NullPointerException.class)
	public void testFindGruppoCompratoreByGroupAndCompratoreThrowsExceptionWhenGroupIsNull() {
		service.findGruppoCompratoreByGroupAndCompratore(null, mock(CompratoreEntity.class));
	}
	@Test(expected = NullPointerException.class)
	public void testFindGruppoCompratoreByGroupAndCompratoreThrowsExceptionWhenCompratoreIsNull() {
		service.findGruppoCompratoreByGroupAndCompratore(mock(GroupEntity.class), null);
	}
	@Test()
	public void testFindGruppoCompratoreByGroupAndCompratoreCallsDAO() {
		CompratoreEntity compratore = mock(CompratoreEntity.class);
		GroupEntity gruppo = mock(GroupEntity.class);
		service.findGruppoCompratoreByGroupAndCompratore(gruppo, compratore);
		verify(gruppoCompratoreDAO, times(1)).findByGroupAndCompratore(gruppo, compratore);
	}
	@Test(expected = NullPointerException.class)
	public void testSaveGruppoCompratoreThrowsExceptionWhenEntityIsNull() {
		service.saveGruppoCompratore((GruppoCompratoreEntity) null);
	}
	@Test
	public void testSaveGruppoCompratoreCallsDAO() {
		GruppoCompratoreEntity entity = mock(GruppoCompratoreEntity.class);
		service.saveGruppoCompratore(entity);
		verify(gruppoCompratoreDAO, times(1)).save(entity);
	}

	@Test(expected = NullPointerException.class)
	public void testFindGruppoGrmByGroupThrowsExceptionWhenGruppoIsNull() {
		service.findGruppoGrmByGroup(null);
	}
	@Test
	public void testFindGruppoGrmByGroupCallsDao() {
		GroupEntity gruppo = mock(GroupEntity.class);
		service.findGruppoGrmByGroup(gruppo);
		verify(gruppoGrmDAO, times(1)).findByGroup(gruppo);
	}
	@Test(expected = NullPointerException.class)
	public void testFindGruppoGrmByGroupAndGrmThrowsExceptionWhenGruppoIsNull() {
		service.findGruppoGrmByGroupAndGrm(null, mock(GrmEntity.class));
	}
	@Test(expected = NullPointerException.class)
	public void testFindGruppoGrmByGroupAndGrmThrowsExceptionWhenGrmIsNull() {
		service.findGruppoGrmByGroupAndGrm(mock(GroupEntity.class), null);
	}
	@Test
	public void testFindGruppoGrmByGroupAndGrmCallsDAO() {
		GroupEntity gruppo = mock(GroupEntity.class);
		GrmEntity grm = mock(GrmEntity.class);
		service.findGruppoGrmByGroupAndGrm(gruppo,grm);
		verify(gruppoGrmDAO, times(1)).findByGroupAndGrm(gruppo, grm);
	}
	@Test(expected = NullPointerException.class)
	public void testSaveGruppoGrmThrowsExceptionWhenEntityIsNull() {
		service.saveGruppoGrm((GruppoGrmEntity) null);
	}
	@Test
	public void testSaveGruppoGrmCallsDAO() {
		GruppoGrmEntity e = mock(GruppoGrmEntity.class);
		service.saveGruppoGrm(e);
		verify(gruppoGrmDAO, times(1)).save(e);
	}

	@Test
	public void testFindAllGrmCallsDAO() {
		service.findAllGrm();
		verify(grmDAO, times(1)).findAll();
	}
	@Test(expected = NullPointerException.class)
	public void testFindAllGrmByGroupThrowsExceptionWhenGrouoIsNull() {
		service.findAllGrmByGroup(null);
	}
	@Test
	public void testFindAllGrmByGroupCallsDao() {
		GroupEntity g = mock(GroupEntity.class);
		service.findAllGrmByGroup(g);
		verify(grmDAO, times(1)).findAllByGroup(g);
	}
}
