package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.CanalePromozioneServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CanalePromozioneServiceTest {

	@Mock
	private CanalePromozioneDAO dao;

	@Spy
	@InjectMocks
	private final CanalePromozioneService service = new CanalePromozioneServiceImpl();

	@Test
	public void shouldFindById() {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		when(mock.getId()).thenReturn(Long.MIN_VALUE);
		when(dao.findById(mock.getId())).thenReturn(mock);
		CanalePromozioneEntity entity = service.findById(mock.getId());
		verify(dao, times(1)).findById(mock.getId());
		verify(service, times(1)).findById(mock.getId());
		assertNotNull(entity);
		assertEquals(mock, entity);
	}

	@Test
	public void shouldNotFindById() {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		when(mock.getId()).thenReturn(Long.MIN_VALUE);
		CanalePromozioneEntity entity = service.findById(mock.getId());
		verify(dao, times(1)).findById(mock.getId());
		verify(service, times(1)).findById(mock.getId());
		assertNull(entity);
	}

	@Test
	public void shouldFindAll() {
		CanalePromozioneEntity mock1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity mock2 = mock(CanalePromozioneEntity.class);
		List<CanalePromozioneEntity> canali = Arrays.asList(mock1, mock2);
		when(dao.findAll()).thenReturn(canali);
		List<CanalePromozioneEntity> entities = service.findAll();
		verify(dao, times(1)).findAll();
		verify(service, times(1)).findAll();
		assertNotNull(entities);
		assertTrue(entities.size() == 2);
	}

	@Test
	public void shouldNotFindAll() {
		List<CanalePromozioneEntity> entities = service.findAll();
		verify(dao, times(1)).findAll();
		verify(service, times(1)).findAll();
		assertNotNull(entities);
		assertTrue(entities.isEmpty());
	}

	@Test
	public void shouldFindByDescription() {
		List<CanalePromozioneEntity> list = new ArrayList<>();
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		when(mock.getDescrizione()).thenReturn("descrizione");
		list.add(mock);
		when(dao.findByDescription(mock.getDescrizione())).thenReturn(list);
		List<CanalePromozioneEntity> entity = service.findByDescription(mock.getDescrizione());
		verify(dao, times(1)).findByDescription(mock.getDescrizione());
		verify(service, times(1)).findByDescription(mock.getDescrizione());
		assertNotNull(entity);
		assertEquals(list, entity);
	}

	@Test
	public void shouldNotFindByDescription() {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		when(mock.getDescrizione()).thenReturn("descrizione");
		List<CanalePromozioneEntity> entity = service.findByDescription(mock.getDescrizione());
		verify(dao, times(1)).findByDescription(mock.getDescrizione());
		verify(service, times(1)).findByDescription(mock.getDescrizione());
		assertTrue(entity.isEmpty());
	}

	@Test
	public void shouldFindAllByGroup() {
		CanalePromozioneEntity mock1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity mock2 = mock(CanalePromozioneEntity.class);
		GruppoPromozioneEntity gruppoMock = mock(GruppoPromozioneEntity.class);
		List<CanalePromozioneEntity> canali = Arrays.asList(mock1, mock2);
		when(dao.findAllByGroup(gruppoMock)).thenReturn(canali);
		List<CanalePromozioneEntity> entities = service.findAllByGroup(gruppoMock);
		verify(dao, times(1)).findAllByGroup(gruppoMock);
		verify(service, times(1)).findAllByGroup(gruppoMock);
		assertNotNull(entities);
	}

	@Test
	public void shouldFindAllGroupId() {
		CanalePromozioneEntity mock1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity mock2 = mock(CanalePromozioneEntity.class);
		GruppoPromozioneEntity gruppoMock = mock(GruppoPromozioneEntity.class);
		when(gruppoMock.getId()).thenReturn(Long.MAX_VALUE);
		List<CanalePromozioneEntity> canali = Arrays.asList(mock1, mock2);
		when(dao.findByGroupId(gruppoMock.getId())).thenReturn(canali);
		List<CanalePromozioneEntity> entities = service.findByGroupId(gruppoMock.getId());
		verify(dao, times(1)).findByGroupId(gruppoMock.getId());
		verify(service, times(1)).findByGroupId(gruppoMock.getId());
		assertNotNull(entities);
	}

	@Test
	public void shouldFindByCodiceCanale() {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		when(mock.getCodiceCanale()).thenReturn(Long.MAX_VALUE);
		when(dao.findByCodiceCanale(mock.getCodiceCanale())).thenReturn(mock);
		CanalePromozioneEntity entity = service.findByCodiceCanale(mock.getCodiceCanale());
		verify(dao, times(1)).findByCodiceCanale(mock.getCodiceCanale());
		verify(service, times(1)).findByCodiceCanale(mock.getCodiceCanale());
		assertNotNull(entity);
		assertEquals(mock, entity);
	}

	@Test
	public void shouldNotFindByCodiceCanale() {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		when(mock.getCodiceCanale()).thenReturn(Long.MAX_VALUE);
		CanalePromozioneEntity entity = service.findByCodiceCanale(mock.getCodiceCanale());
		verify(dao, times(1)).findByCodiceCanale(mock.getCodiceCanale());
		verify(service, times(1)).findByCodiceCanale(mock.getCodiceCanale());
		assertNull(entity);
	}

}
