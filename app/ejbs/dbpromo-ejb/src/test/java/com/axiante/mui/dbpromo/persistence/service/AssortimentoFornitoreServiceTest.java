package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.AssortimentoFornitoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.AssortimentoFornitoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssortimentoFornitoreServiceTest {

	@Mock
	private AssortimentoFornitoreDAO dao;

	@Spy
	@InjectMocks
	private final AssortimentoFornitoreService service = new AssortimentoFornitoreServiceImpl();

	@Test
	public void shouldFindById() {
		AssortimentoFornitoreEntity mock = mock(AssortimentoFornitoreEntity.class);
		when(mock.getId()).thenReturn(Long.MIN_VALUE);
		when(dao.findById(mock.getId())).thenReturn(mock);
		AssortimentoFornitoreEntity entity = service.findById(mock.getId());
		verify(service, times(1)).findById(mock.getId());
		assertNotNull(entity);
		assertEquals(mock, entity);
	}

	@Test
	public void shouldNotFindById() {
		AssortimentoFornitoreEntity mock = mock(AssortimentoFornitoreEntity.class);
		when(mock.getId()).thenReturn(Long.MIN_VALUE);
		when(dao.findById(mock.getId())).thenReturn(null);
		AssortimentoFornitoreEntity entity = service.findById(mock.getId());
		verify(service, times(1)).findById(mock.getId());
		assertNull(entity);
	}
}
