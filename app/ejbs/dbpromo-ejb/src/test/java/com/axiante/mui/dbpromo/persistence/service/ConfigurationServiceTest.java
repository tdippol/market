package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.ConfigurationServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationServiceTest {
	@Mock
	private CfgPianificazioneDAO pianificazioneDAO;

	@Spy
	@InjectMocks
	private final ConfigurationService service = new ConfigurationServiceImpl();

	@Test
	public void testAsJsonObjectReturnsDataWhenObject() {
		// make sure something's in the bean
		CfgPianificazioneEntity entity = new CfgPianificazioneEntity();
		entity.setId(Long.MAX_VALUE);
		assertNotNull(service.asJsonObject(entity));
	}

	@Test
	public void testAsJsonObjectReturnsNullJsonWhenNull() {
		assertThat(service.asJsonObject(null), CoreMatchers.equalTo("null"));
	}

	@Test
	public void testGenerateCompletePromoPlanningGridCallsAsJsonWhenDataFoung() {
		CfgPianificazioneEntity entity = mock(CfgPianificazioneEntity.class);
		when(entity.getId()).thenReturn(Long.MAX_VALUE);
		when(pianificazioneDAO.findById(entity.getId())).thenReturn(entity);
		service.generateCompletePromoPlanningGrid(entity);
		verify(pianificazioneDAO, times(1)).findById(entity.getId());
		verify(service, times(1)).asJsonObject(entity);
	}

	@Test
	public void testGenerateCompletePromoPlanningGridCallsNothigWhenNoDataFound() {
		CfgPianificazioneEntity entity = mock(CfgPianificazioneEntity.class);
		when(entity.getId()).thenReturn(Long.MAX_VALUE);
		when(pianificazioneDAO.findById(entity.getId())).thenReturn(null);
		service.generateCompletePromoPlanningGrid(entity);
		verify(pianificazioneDAO, times(1)).findById(entity.getId());
		verify(service, times(0)).asJsonObject(entity);
	}

	@Test
	public void testGenerateCompletePromoPlanningGridCallsNothigWhenNoData() {
		service.generateCompletePromoPlanningGrid(null);
		CfgPianificazioneEntity entity = mock(CfgPianificazioneEntity.class);
		service.generateCompletePromoPlanningGrid(entity);
		verify(pianificazioneDAO, times(0)).findById(Any.class);
		verify(service, times(0)).asJsonObject(entity);
	}

	@Test
	public void testPersist() {
		CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
		service.persist(mock);
		verify(pianificazioneDAO, times(1)).persist(mock);
		verify(service, times(1)).persist(mock);
	}

	@Test
	public void testRemove() {
		CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
		service.remove(mock);
		verify(pianificazioneDAO, times(1)).remove(mock);
		verify(service, times(1)).remove(mock);
	}

	@Test
	public void shouldFindByCfgPianificazioneId() {
		CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
		when(mock.getId()).thenReturn(Long.MAX_VALUE);
		when(pianificazioneDAO.findById(mock.getId())).thenReturn(mock);
		CfgPianificazioneEntity entity = service.findByCfgPianificazioneId(mock.getId());
		verify(pianificazioneDAO, times(1)).findById(mock.getId());
		verify(service, times(1)).findByCfgPianificazioneId(mock.getId());
		assertNotNull(entity);
		assertEquals(mock, entity);
	}

	@Test
	public void shouldNotFindByCfgPianificazioneId() {
		CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
		when(mock.getId()).thenReturn(Long.MAX_VALUE);
		CfgPianificazioneEntity entity = service.findByCfgPianificazioneId(mock.getId());
		verify(pianificazioneDAO, times(1)).findById(mock.getId());
		verify(service, times(1)).findByCfgPianificazioneId(mock.getId());
		assertNull(entity);
	}

}
