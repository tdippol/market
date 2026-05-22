package com.axiante.mui.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.persistence.dao.ApplicationPropertiesDAO;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.impl.ApplicationPropertiesServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationPropertiesServiceTest {

	@Mock
	ApplicationPropertiesDAO dao;

	@Mock
	ApplicationProperties properties;

	@Spy
	@InjectMocks
	ApplicationPropertiesService service = new ApplicationPropertiesServiceImpl();

	@Test
	public void testWhenManualAdminTrueThenCalculateAdminReturnsTrue() throws Exception {
		// ApplicationProperties.MANUAL_ADMIN_MODE = true
		ApplicationPropertiesEntity mock = mock(ApplicationPropertiesEntity.class);
		when(mock.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.MANUAL_ADMIN_MODE)).thenReturn(mock);
		assertThat(service.calculateAdminMode(), CoreMatchers.equalTo(true));
	}

	@Test
	public void testWhenManualAdminTrueThenScheduleIsNotCheckedCalculateAdminReturnsTrue() throws Exception {
		// ApplicationProperties.MANUAL_ADMIN_MODE = true
		// ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE = anything

		ApplicationPropertiesEntity admin = mock(ApplicationPropertiesEntity.class);
		when(admin.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.MANUAL_ADMIN_MODE)).thenReturn(admin);

		assertThat(service.calculateAdminMode(), CoreMatchers.equalTo(true));
	}

	@Test
	public void testWhenAdminFalseAndScheduleFalseThenReturnFalse() throws Exception {
		// ApplicationProperties.MANUAL_ADMIN_MODE = false
		// ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE = false
		ApplicationPropertiesEntity admin = mock(ApplicationPropertiesEntity.class);
		when(admin.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.MANUAL_ADMIN_MODE)).thenReturn(admin);

		ApplicationPropertiesEntity schedule = mock(ApplicationPropertiesEntity.class);
		when(schedule.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE)).thenReturn(schedule);

		assertThat(service.calculateAdminMode(), CoreMatchers.equalTo(false));
	}

	@Test
	public void testWhenAdminFalseAndScheduleAndAllDaysAndNotTimeThenReturnFalse() throws Exception {
		// ApplicationProperties.MANUAL_ADMIN_MODE = false
		// ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE = true
		// ApplicationProperties.ADMIN_MODE_ALL_DAYS = true
		// ApplicationProperties.ADMIN_MODE_FROM_TIME = now.minusHours(2)
		// ApplicationProperties.ADMIN_MODE_TO_TIME = now.minusHours(1);

		ApplicationPropertiesEntity admin = mock(ApplicationPropertiesEntity.class);
		when(admin.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.MANUAL_ADMIN_MODE)).thenReturn(admin);

		ApplicationPropertiesEntity schedule = mock(ApplicationPropertiesEntity.class);
		when(schedule.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE)).thenReturn(schedule);

		ApplicationPropertiesEntity allDays = mock(ApplicationPropertiesEntity.class);
		when(allDays.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_ALL_DAYS)).thenReturn(allDays);

		LocalDateTime now = LocalDateTime.now();
		// Custom format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String startTime = now.minusHours(2).format(formatter);
		String endTime = now.minusHours(1).format(formatter);
		ApplicationPropertiesEntity start = mock(ApplicationPropertiesEntity.class);
		when(start.getValue()).thenReturn(startTime);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_FROM_TIME)).thenReturn(start);
		ApplicationPropertiesEntity end = mock(ApplicationPropertiesEntity.class);
		when(end.getValue()).thenReturn(endTime);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_TO_TIME)).thenReturn(end);

		assertThat(service.calculateAdminMode(), CoreMatchers.equalTo(false));
	}

	@Test
	public void testWhenAdminFalseAndScheduleAndAllDaysAndNotTodayAndTimeThenReturnTrue() throws Exception {
		// ApplicationProperties.MANUAL_ADMIN_MODE = false
		// ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE = true
		// ApplicationProperties.ADMIN_MODE_ALL_DAYS = true
		// ApplicationProperties.ADMIN_MODE_FROM_TIME = now.minusHours(2)
		// ApplicationProperties.ADMIN_MODE_TO_TIME = now.plusHours(1);

		ApplicationPropertiesEntity admin = mock(ApplicationPropertiesEntity.class);
		when(admin.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.MANUAL_ADMIN_MODE)).thenReturn(admin);

		ApplicationPropertiesEntity schedule = mock(ApplicationPropertiesEntity.class);
		when(schedule.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE)).thenReturn(schedule);

		ApplicationPropertiesEntity allDays = mock(ApplicationPropertiesEntity.class);
		when(allDays.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_ALL_DAYS)).thenReturn(allDays);

		LocalDateTime now = LocalDateTime.now();
		// Custom format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String startTime = now.minusHours(2).format(formatter);
		String endTime = now.plusHours(1).format(formatter);
		ApplicationPropertiesEntity start = mock(ApplicationPropertiesEntity.class);
		when(start.getValue()).thenReturn(startTime);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_FROM_TIME)).thenReturn(start);
		ApplicationPropertiesEntity end = mock(ApplicationPropertiesEntity.class);
		when(end.getValue()).thenReturn(endTime);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_TO_TIME)).thenReturn(end);

		assertThat(service.calculateAdminMode(), CoreMatchers.equalTo(true));
	}

	@Test
	public void testWhenAdminFalseAndScheduleAndNotAllDaysAndNotTodayFalse() throws Exception {
		// ApplicationProperties.MANUAL_ADMIN_MODE = false
		// ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE = true
		// ApplicationProperties.ADMIN_MODE_ALL_DAYS = false
		// ApplicationProperties.ADMIN_MODE_SELECTED_DAYS = not today
		// ApplicationProperties.ADMIN_MODE_FROM_TIME = now.minusHours(2)
		// ApplicationProperties.ADMIN_MODE_TO_TIME = now.plusHours(1);

		ApplicationPropertiesEntity admin = mock(ApplicationPropertiesEntity.class);
		when(admin.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.MANUAL_ADMIN_MODE)).thenReturn(admin);

		ApplicationPropertiesEntity schedule = mock(ApplicationPropertiesEntity.class);
		when(schedule.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE)).thenReturn(schedule);

		ApplicationPropertiesEntity allDays = mock(ApplicationPropertiesEntity.class);
		when(allDays.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_ALL_DAYS)).thenReturn(allDays);

		LocalDateTime now = LocalDateTime.now();

		Integer[] i = { 1, 2, 3, 4, 5, 6, 7 };
		// remove today
		String string = Arrays.stream(i).filter(j -> j != now.getDayOfWeek().getValue()).map(Object::toString)
				.collect(Collectors.joining(","));
		ApplicationPropertiesEntity selectedDays = mock(ApplicationPropertiesEntity.class);
		when(selectedDays.getValue()).thenReturn(string);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_SELECTED_DAYS)).thenReturn(selectedDays);
		assertThat(service.calculateAdminMode(), CoreMatchers.equalTo(false));
	}

	public void testWhenAdminFalseAndScheduleAndNotAllDaysAndTodayAndTimeThenReturnTrue() throws Exception {
		// ApplicationProperties.MANUAL_ADMIN_MODE = false
		// ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE = true
		// ApplicationProperties.ADMIN_MODE_ALL_DAYS = false
		// ApplicationProperties.ADMIN_MODE_SELECTED_DAYS = not today
		// ApplicationProperties.ADMIN_MODE_FROM_TIME = now.minusHours(2)
		// ApplicationProperties.ADMIN_MODE_TO_TIME = now.plusHours(1);

		ApplicationPropertiesEntity admin = mock(ApplicationPropertiesEntity.class);
		when(admin.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.MANUAL_ADMIN_MODE)).thenReturn(admin);

		ApplicationPropertiesEntity schedule = mock(ApplicationPropertiesEntity.class);
		when(schedule.getValue()).thenReturn(Boolean.TRUE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE)).thenReturn(schedule);

		ApplicationPropertiesEntity allDays = mock(ApplicationPropertiesEntity.class);
		when(allDays.getValue()).thenReturn(Boolean.FALSE.toString());
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_ALL_DAYS)).thenReturn(allDays);

		LocalDateTime now = LocalDateTime.now();

		Integer[] i = { 1, 2, 3, 4, 5, 6, 7 };
		// remove today
		String string = Arrays.stream(i).filter(j -> j == now.getDayOfWeek().getValue()).map(Object::toString)
				.collect(Collectors.joining(","));
		ApplicationPropertiesEntity selectedDays = mock(ApplicationPropertiesEntity.class);
		when(selectedDays.getValue()).thenReturn(string);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_SELECTED_DAYS)).thenReturn(selectedDays);

		// Custom format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String startTime = now.minusHours(2).format(formatter);
		String endTime = now.plusHours(1).format(formatter);
		ApplicationPropertiesEntity start = mock(ApplicationPropertiesEntity.class);
		when(start.getValue()).thenReturn(startTime);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_FROM_TIME)).thenReturn(start);
		ApplicationPropertiesEntity end = mock(ApplicationPropertiesEntity.class);
		when(end.getValue()).thenReturn(endTime);
		when(dao.findByCode(ApplicationProperties.ADMIN_MODE_TO_TIME)).thenReturn(end);

		assertThat(service.calculateAdminMode(), CoreMatchers.equalTo(true));
	}

	@Test
	public void saveProperty_withValue_whenException_shouldReturnFalse() throws Exception {
		final ApplicationPropertiesEntity entity = mock(ApplicationPropertiesEntity.class);
		when(dao.persist(entity)).thenThrow(Exception.class);
		assertFalse(service.saveProperty(entity, "foo"));
		verify(dao, times(1)).persist(entity);
	}

	@Test
	public void saveProperty_withValue_shouldReturnTrue() throws Exception {
		final ApplicationPropertiesEntity entity = mock(ApplicationPropertiesEntity.class);
		when(dao.persist(entity)).thenReturn(entity);
		assertTrue(service.saveProperty(entity, "foo"));
		verify(dao, times(1)).persist(entity);
	}

	@Test
	public void saveProperty_whenException_shouldReturnEntity() throws Exception {
		final ApplicationPropertiesEntity entity = mock(ApplicationPropertiesEntity.class);
		when(dao.persist(entity)).thenThrow(Exception.class);
		assertNotNull(service.saveProperty(entity));
		verify(dao, times(1)).persist(entity);
	}

	@Test
	public void saveProperty_givenValidEntity_shouldPersistIt() throws Exception {
		final ApplicationPropertiesEntity entity = mock(ApplicationPropertiesEntity.class);
		when(dao.persist(entity)).thenReturn(entity);
		assertNotNull(service.saveProperty(entity));
		verify(dao, times(1)).persist(entity);
	}

	@Test
	public void getPropertiesEntity_givenValidKey_shouldReturnEntity() throws Exception {
		final ApplicationPropertiesEntity entity = mock(ApplicationPropertiesEntity.class);
		when(dao.findByCode("foo")).thenReturn(entity);
		assertNotNull(service.getPropertiesEntity("foo"));
		verify(dao, times(1)).findByCode("foo");
	}

	@Test
	public void getPropertiesEntity_whenException_shouldReturnNull() throws Exception {
		when(dao.findByCode("foo")).thenThrow(Exception.class);
		assertNull(service.getPropertiesEntity("foo"));
		verify(dao, times(1)).findByCode("foo");
	}

	@Test(expected = NullPointerException.class)
	public void getOrCreateProperty_givenNullKey_shouldThrowException() {
		service.getOrCreateProperty(null, "bar");
	}

	@Test
	public void getOrCreateProperty_whenCannotFindEntity_shouldCreateIt() throws Exception {
		final ApplicationPropertiesEntity entity = new ApplicationPropertiesEntity();
		entity.setKey("foo");
		entity.setValue("bar");
		when(dao.findByCode("foo")).thenReturn(null);
		when(dao.persist(any(ApplicationPropertiesEntity.class))).thenReturn(entity);
		assertNotNull(service.getOrCreateProperty("foo", "bar"));
		verify(dao, times(1)).findByCode("foo");
		verify(dao, times(1)).persist(any(ApplicationPropertiesEntity.class));
	}

	@Test
	public void getOrCreateProperty_whenException_shouldReturnNull() throws Exception {
		//		final ApplicationPropertiesEntity entity = mock(ApplicationPropertiesEntity.class);
		when(dao.findByCode("foo")).thenThrow(Exception.class);
		assertNull(service.getOrCreateProperty("foo", "bar"));
		verify(dao, times(1)).findByCode("foo");
		// se c'e' una eccezione generica allora non la crea
		//		verify(dao, times(1)).persist(any(ApplicationPropertiesEntity.class));
	}

	@Test
	public void findAll_shouldCallDao() {
		final List<ApplicationPropertiesEntity> entities = Collections.singletonList(mock(ApplicationPropertiesEntity.class));
		when(dao.readAll()).thenReturn(entities);
		assertEquals(1, service.findAll().size());
		verify(dao, times(1)).readAll();
	}

	@Test
	public void findAnyById_shouldCallDao() throws Exception {
		final List<ApplicationPropertiesEntity> entities = Collections.singletonList(mock(ApplicationPropertiesEntity.class));
		when(dao.findAnyByCode("foo")).thenReturn(entities);
		assertEquals(1, service.findAnyById("foo").size());
		verify(dao, times(1)).findAnyByCode("foo");
	}

	@Test
	public void hasAccessAsAdmin_givenAdminRole_shouldReturnTrue() {
		final RolesEntity entity = mockRoleEntity(RolesEntity.ADMIN_ROLE);
		when(entity.isAdmin()).thenCallRealMethod();
		assertTrue(service.hasAccessAsAdmin(entity));
	}

	@Test
	public void hasAccessAsAdmin_givenNonAdminRole_shouldReturnTrue_whenRoleOvverrideAdmin() {
		final RolesEntity entity = mockRoleEntity("foo");
		when(entity.getOverrideAdmin()).thenReturn(true);
		assertTrue(service.hasAccessAsAdmin(entity));
		when(entity.getOverrideAdmin()).thenReturn(false);
		assertFalse(service.hasAccessAsAdmin(entity));
	}

	private RolesEntity mockRoleEntity(String name) {
		final RolesEntity mock = mock(RolesEntity.class);
		when(mock.getName()).thenReturn(name);
		return mock;
	}
}
