package com.axiante.mui.persistence.entity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class AclEntityTest {
	@Test
	public void testConstructor() {
		// public AclEntity(Integer id, String componentClass, String component, Boolean
		// visible, Boolean editable, Boolean enabled, RolesEntity rolesByRoleId,
		// UiEntity uiByUiId) {
		Integer id = 1;
		String componentClass = "test_class";
		String component = "test_component";
		Boolean visible = true;
		Boolean editable = true;
		Boolean enabled = true;
		RolesEntity rolesByRoleId = mock(RolesEntity.class);
		AclEntity e = new AclEntity(id, componentClass, component, visible, editable, enabled, rolesByRoleId);
		assertNotNull(e);
		assertThat(e.getId(), equalTo(id));
		assertThat(e.getComponentClass(), equalTo(componentClass));
		assertThat(e.getComponent(), equalTo(component));
		assertThat(e.getVisible(), equalTo(visible));
		assertThat(e.getEditable(), equalTo(editable));
		assertThat(e.getEnabled(), equalTo(enabled));
		assertThat(e.getRolesByRoleId(), equalTo(rolesByRoleId));

		visible = false;
		editable = false;
		enabled = false;
		e = new AclEntity(id, componentClass, component, visible, editable, enabled, rolesByRoleId);
		assertNotNull(e);
		assertThat(e.getId(), equalTo(id));
		assertThat(e.getComponentClass(), equalTo(componentClass));
		assertThat(e.getComponent(), equalTo(component));
		assertThat(e.getVisible(), equalTo(visible));
		assertThat(e.getEditable(), equalTo(editable));
		assertThat(e.getEnabled(), equalTo(enabled));
		assertThat(e.getRolesByRoleId(), equalTo(rolesByRoleId));

		e = new AclEntity();
		assertNotNull(e);

	}

	@Test
	public void testGetVisible() {
		AclEntity e = new AclEntity();
		assertFalse(e.getVisible());
		e.setVisible(true);
		assertTrue(e.getVisible());
		e.setVisible(false);
		assertFalse(e.getVisible());
	}

	@Test
	public void testGetEditable() {
		AclEntity e = new AclEntity();
		assertFalse(e.getEditable());
		e.setEditable(true);
		assertTrue(e.getEditable());
		e.setEditable(false);
		assertFalse(e.getEditable());
	}

	@Test
	public void testGetEnabled() {
		AclEntity e = new AclEntity();
		assertFalse(e.getEnabled());
		e.setEnabled(true);
		assertTrue(e.getEnabled());
		e.setEnabled(false);
		assertFalse(e.getEnabled());
	}

}
