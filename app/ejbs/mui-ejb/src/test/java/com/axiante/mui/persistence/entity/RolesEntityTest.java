package com.axiante.mui.persistence.entity;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RolesEntityTest {
	@Test
	public void testConstructors() {
		assertNotNull(new RolesEntity());
		assertNotNull(new RolesEntity(1, "Test", "Test"));
	}

	@Test
	public void testGetManualCaption() {
		RolesEntity e = new RolesEntity();
		assertThat(e.getManualCaption(), equalTo("Carica Manuale"));

		e.setHelpFilename("test file name");
		assertThat(e.getManualCaption(), containsString("cliccare per modificare"));
	}

	@Test
	public void addAcl_givenValidAcl_shouldAddIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final AclEntity acl = createAclEntity(1, "foo-acl");
		assertNull(role.getAcls());
		role.addAcl(acl);
		assertEquals(1, role.getAcls().size());
	}

	@Test
	public void removeAcl_givenValidAcl_shouldRemoveIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final AclEntity acl = createAclEntity(1, "foo-acl");
		role.addAcl(acl);
		assertEquals(1, role.getAcls().size());
		role.removeAcl(acl);
		assertTrue(role.getAcls().isEmpty());
	}

	@Test
	public void removeAcl_givenInvalidAcl_shouldNotRemoveIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final AclEntity acl1 = createAclEntity(1, "foo-acl");
		final AclEntity acl2 = createAclEntity(2, "bar-acl");
		role.addAcl(acl1);
		assertEquals(1, role.getAcls().size());
		role.removeAcl(acl2);
		assertEquals(1, role.getAcls().size());
	}

	@Test
	public void addUser_givenValidUser_shouldAddIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final UsersEntity user = createUserEntity(1, "Luke");
		assertNull(role.getUsers());
		role.addUser(user);
		assertEquals(1, role.getUsers().size());
	}

	@Test
	public void removeUser_givenValidUser_shouldRemoveIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final UsersEntity user = createUserEntity(2, "Han Solo");
		role.addUser(user);
		assertEquals(1, role.getUsers().size());
		role.removeUser(user);
		assertTrue(role.getUsers().isEmpty());
	}

	@Test
	public void removeUser_givenInvalidUser_shouldNotRemoveIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final UsersEntity user1 = createUserEntity(1, "Luke");
		final UsersEntity user2 = createUserEntity(2, "Han Solo");
		role.addUser(user1);
		assertEquals(1, role.getUsers().size());
		role.removeUser(user2);
		assertEquals(1, role.getUsers().size());
	}

	@Test
	public void addMenu_givenValidMenu_shouldAddIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final MenuEntity menu = createMenuEntity(1, "bar-menu");
		assertNull(role.getMenus());
		role.addMenu(menu);
		assertEquals(1, role.getMenus().size());
	}

	@Test
	public void removeMenu_givenValidMenu_shouldRemoveIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final MenuEntity menu = createMenuEntity(2, "baz-menu");
		role.addMenu(menu);
		assertEquals(1, role.getMenus().size());
		role.removeMenu(menu);
		assertTrue(role.getMenus().isEmpty());
	}

	@Test
	public void removeMenu_givenInvalidMenu_shouldNotRemoveIt() {
		final RolesEntity role = createRoleEntity(1, "foo");
		final MenuEntity menu1 = createMenuEntity(1, "bar-menu");
		final MenuEntity menu2 = createMenuEntity(2, "baz-menu");
		role.addMenu(menu1);
		assertEquals(1, role.getMenus().size());
		role.removeMenu(menu2);
		assertEquals(1, role.getMenus().size());
	}

	private RolesEntity createRoleEntity(Integer id, String name) {
		final RolesEntity entity = new RolesEntity();
		entity.setId(id);
		entity.setName(name);
		return entity;
	}

	private AclEntity createAclEntity(Integer id, String component) {
		final AclEntity entity = new AclEntity();
		entity.setId(id);
		entity.setComponent(component);
		return entity;
	}

	private UsersEntity createUserEntity(Integer id, String name) {
		final UsersEntity entity = new UsersEntity();
		entity.setId(id);
		entity.setName(name);
		return entity;
	}

	private MenuEntity createMenuEntity(Integer id, String label) {
		final MenuEntity entity = new MenuEntity();
		entity.setIdMenu(id);
		entity.setLabel(label);
		return entity;
	}
}
