package com.axiante.mui.persistence.entity;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class MenuEntityTest {

	@Test
	public void testSetExternalLink() {
		final MenuEntity e = new MenuEntity();
		assertFalse(e.getExternalLink());
		e.setExternalLink(true);
		assertTrue(e.getExternalLink());
		e.setExternalLink(false);
		assertFalse(e.getExternalLink());
	}

	@Test
	public void testConstructor() {
		final Integer idMenu = 1;
		final String label = "test_label";
		final String url = "test_url";
		final String bean = "test_bean";
		final Integer orderId = 3;
		boolean externalLink = false;
		final MenuEntity e = new MenuEntity(idMenu, null, label, url, bean, orderId, externalLink);
		assertEquals(e.getIdMenu(), idMenu);
		assertEquals(e.getLabel(), label);
		assertEquals(e.getUrl(), url);
		assertEquals(e.getBean(), bean);
		assertEquals(e.getOrderId(), orderId);
		assertEquals(e.getExternalLink(), externalLink);

		assertEquals(e, e);
		externalLink = true;
		final MenuEntity e1 = new MenuEntity(idMenu, null, label, url, bean, orderId, externalLink);
		assertEquals(e1.getExternalLink(), externalLink);
	}

	@Test
	public void testGetTemplateReturnsFalseWhenNullOrFalse() {
		MenuEntity entity = new MenuEntity();
		// template is null as it has not been set
		assertFalse(entity.getTemplate());
		entity.setTemplate(Boolean.FALSE);
		assertFalse(entity.getTemplate());
	}

	@Test
	public void testGetTemplateReturnsTrueWhenNotNullAndEqualsToOne() {
		MenuEntity entity = new MenuEntity();
		entity.setTemplate(Boolean.TRUE);
		assertTrue(entity.getTemplate());
	}

	@Test
	public void testIsLeafReturnsFalseWhenBeanIsNull() {
		MenuEntity entity = new MenuEntity();
		// bean is null as it has not been set
		assertFalse(entity.isLeaf());
	}

	@Test
	public void testIsLeafReturnsTrueWhenBeanIsSet() {
		MenuEntity entity = new MenuEntity();
		// bean is null as it has not been set
		entity.setBean("something");
		assertTrue(entity.isLeaf());
	}

	@Test
	public void testHasChildrenReturnsFalseWhenChildrenIsNull() {
		MenuEntity entity = new MenuEntity();
		// null as it has not been set
		assertFalse(entity.hasChildren());
	}

	@Test
	public void testHasChildrenReturnsFalseWhenChildrenSizeIsZero() {
		MenuEntity entity = spy(MenuEntity.class);
		@SuppressWarnings("unchecked")
		List<MenuEntity> children = mock(List.class);
		when(children.size()).thenReturn(0);
		when(entity.getChildren()).thenReturn(children);
		assertFalse(entity.hasChildren());
	}

	@Test
	public void testHasChildrenReturnsTrueWhenChildrenSizeGreaterThanZero() {
		MenuEntity entity = spy(MenuEntity.class);
		@SuppressWarnings("unchecked")
		List<MenuEntity> children = mock(List.class);
		when(children.size()).thenReturn(2);
		when(entity.getChildren()).thenReturn(children);
		assertTrue(entity.hasChildren());
	}

	@Test
	public void testAddRoleReturnsInsertedRole() {
		MenuEntity entity = spy(MenuEntity.class);
		RolesEntity role = mock(RolesEntity.class);
		assertThat(role, CoreMatchers.equalTo(entity.addRole(role)));
	}

	@Test
	public void testAddRoleSavesInternally() {
		MenuEntity entity = spy(MenuEntity.class);
		RolesEntity role = mock(RolesEntity.class);
		entity.addRole(role);
		assertThat(entity.getRoles(), hasItem(role));
	}

	@Test
	public void testRemoveRoleReturnsInsertedRole() {
		MenuEntity entity = spy(MenuEntity.class);
		RolesEntity role = mock(RolesEntity.class);
		assertThat(role, CoreMatchers.equalTo(entity.removeRole(role)));
	}

	@Test
	public void testRemoveRoleSavesInternally() {
		MenuEntity entity = spy(MenuEntity.class);
		RolesEntity role = mock(RolesEntity.class);
		entity.setRoles(new HashSet<>());
		entity.getRoles().add(role);
		assertThat(entity.getRoles(), hasItem(role));
		entity.removeRole(role);
		assertThat(entity.getRoles(), not(hasItem(role)));
	}

	@Test
	public void getUuid_shouldReturnNotNull() {
		final MenuEntity entity1 = new MenuEntity();
		assertNotNull(entity1.getUuid());
		final MenuEntity entity2 = new MenuEntity();
		entity2.setUuid("foo-uuid");
		assertEquals("foo-uuid", entity2.getUuid());
	}

	@Test
	public void addConfiguration_shouldReturnTrue() {
		final MenuEntity menu = spy(MenuEntity.class);
		menu.setLabel("label");
		final ConfigurationEntity cfg = spy(ConfigurationEntity.class);
		assertTrue(menu.addConfiguration(cfg));
		assertEquals(menu, cfg.getMenu());
		assertEquals("label", cfg.getPath());
	}

	@Test
	public void addConfiguration_shouldReturnTrueAndSetPathWithParentLabel() {
		final MenuEntity menu = spy(MenuEntity.class);
		menu.setLabel("child");
		final MenuEntity parent = spy(MenuEntity.class);
		parent.setLabel("parent");
		menu.setParent(parent);
		final ConfigurationEntity cfg = spy(ConfigurationEntity.class);
		assertTrue(menu.addConfiguration(cfg));
		assertEquals(menu, cfg.getMenu());
		assertEquals("parent/child", cfg.getPath());
	}

	@Test
	public void removeConfiguration_shouldReturnTrue_whenMenuHasConfiguration() {
		final MenuEntity menu = spy(MenuEntity.class);
		final ConfigurationEntity cfg = spy(ConfigurationEntity.class);
		Set<ConfigurationEntity> cfgs = new HashSet<>();
		cfgs.add(cfg);
		menu.setConfigurations(cfgs);
		assertTrue(menu.removeConfiguration(cfg));
		assertNull(cfg.getMenu());
	}

	@Test
	public void removeConfiguration_shouldReturnFalse_whenMenuDoesNotHaveConfiguration() {
		final MenuEntity menu = spy(MenuEntity.class);
		final ConfigurationEntity cfg = spy(ConfigurationEntity.class);
		assertFalse(menu.removeConfiguration(cfg));
	}

	@Test
	public void hasConfigurations_shouldReturnFalse_whenConfigurationsIsNull() {
		final MenuEntity menu = spy(MenuEntity.class);
		assertFalse(menu.hasConfigurations());
	}

	@Test
	public void hasConfigurations_shouldReturnTrue_whenHasAtLeastOneConfigurationsWithRevisionDateNull() {
		final MenuEntity menu = spy(MenuEntity.class);
		final ConfigurationEntity cfg1 = spy(ConfigurationEntity.class);
		cfg1.setRevisionDate(new GregorianCalendar(2021, Calendar.JULY, 30).getTime());
		final ConfigurationEntity cfg2 = spy(ConfigurationEntity.class);
		menu.setConfigurations(Stream.of(cfg1, cfg2).collect(Collectors.toSet()));
		assertTrue(menu.hasConfigurations());
	}

	@Test
	public void hasConfigurations_shouldReturnFalse_whenAllConfigurationsHaveRevisionDate() {
		final MenuEntity menu = spy(MenuEntity.class);
		final ConfigurationEntity cfg1 = spy(ConfigurationEntity.class);
		cfg1.setRevisionDate(new GregorianCalendar(2021, Calendar.JULY, 30).getTime());
		final ConfigurationEntity cfg2 = spy(ConfigurationEntity.class);
		cfg2.setRevisionDate(new GregorianCalendar(2021, Calendar.JULY, 1).getTime());
		menu.setConfigurations(Stream.of(cfg1, cfg2).collect(Collectors.toSet()));
		assertFalse(menu.hasConfigurations());
	}

	@Test
	public void toString_shouldReturnStringThatContainsSettedValues() {
		Integer idMenu = 42;
		Integer idParent = 24;
		String label = "label";
		String url = "url";
		String bean = "bean";
		Integer orderId = 23;
		boolean externalLink = false;
		final MenuEntity entity = new MenuEntity(idMenu, null, label, url, bean, orderId, externalLink);
		String s = entity.toString();
		assertTrue(s.contains("id: " + idMenu));
		assertTrue(s.contains("label: " + label));
		assertTrue(s.contains("url: " + url));
		assertTrue(s.contains("bean: " + bean));
		assertTrue(s.contains("order: " + orderId));
		assertTrue(s.contains("external: " + externalLink));
		assertTrue(s.contains("parent: null"));
		assertTrue(s.contains("roles: null"));
		assertTrue(s.contains("children: null"));
		assertTrue(s.contains("configurations: null"));
		final MenuEntity parent = spy(MenuEntity.class);
		parent.setIdMenu(idParent);
		entity.setParent(parent);
		s = entity.toString();
		assertTrue(s.contains("parent: " + idParent));
		final RolesEntity role = spy(RolesEntity.class);
		role.setName("foo");
		entity.setRoles(Collections.singleton(role));
		s = entity.toString();
		assertTrue(s.contains("roles: [foo]"));
		final ConfigurationEntity cfg = spy(ConfigurationEntity.class);
		cfg.setType(ConfigurationTypes.GRID);
		cfg.setJson("{bar:baz}");
		entity.setConfigurations(Collections.singleton(cfg));
		s = entity.toString();
		assertTrue(s.contains("configurations: [GRID:{{bar:baz}}]"));
	}
}
