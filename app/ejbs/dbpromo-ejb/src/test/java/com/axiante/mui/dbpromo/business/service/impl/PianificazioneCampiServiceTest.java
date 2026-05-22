package com.axiante.mui.dbpromo.business.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneCampiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneCampiService;
import com.axiante.mui.dbpromo.persistence.service.impl.PianificazioneCampiServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneCampiServiceTest {

	@Mock
	CfgPianificazioneCampiDAO dao;

	@Spy
	@InjectMocks
	private final PianificazioneCampiService service = new PianificazioneCampiServiceImpl();

	@Test
	public void shouldFindAll() {
		CfgPianificazioneCampiEntity mock1 = mock(CfgPianificazioneCampiEntity.class);
		CfgPianificazioneCampiEntity mock2 = mock(CfgPianificazioneCampiEntity.class);
		List<CfgPianificazioneCampiEntity> pianificazioneCampi = Arrays.asList(mock1, mock2);
		when(dao.findAll()).thenReturn(pianificazioneCampi);
		List<CfgPianificazioneCampiEntity> entities = service.findAll();
		verify(dao, times(1)).findAll();
		verify(service, times(1)).findAll();
		assertNotNull(entities);
		assertTrue(entities.size() == 2);
	}

	@Test
	public void shouldNotFindAll() {
		List<CfgPianificazioneCampiEntity> entities = service.findAll();
		verify(dao, times(1)).findAll();
		verify(service, times(1)).findAll();
		assertNotNull(entities);
		assertTrue(entities.isEmpty());
	}

}
