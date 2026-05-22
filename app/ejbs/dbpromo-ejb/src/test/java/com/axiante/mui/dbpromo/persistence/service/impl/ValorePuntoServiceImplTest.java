package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.ValorePuntoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ValorePuntoEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValorePuntoServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private ValorePuntoDAO dao;

    @Mock
    private ValorePuntoEntity entity;

    @InjectMocks
    private ValorePuntoServiceImpl service;

    private Date date = new GregorianCalendar(2023, Calendar.APRIL, 28).getTime();
    private List<ValorePuntoEntity> entities = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        final ValorePuntoEntity entity1 = mock(ValorePuntoEntity.class);
        final ValorePuntoEntity entity2 = mock(ValorePuntoEntity.class);
        entities.add(entity1);
        entities.add(entity2);
    }

    @Test
    public void findValorePuntoWhereDate_whenDateIsNull_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findValorePuntoWhereDate(null);
    }

    @Test
    public void findValorePuntoWhereDate_whenDaoReturnTwoOrMoreEntities_shouldReturnNull() {
        when(dao.findWhereDate(date)).thenReturn(entities);
        assertNull(service.findValorePuntoWhereDate(date));
        verify(dao, times(1)).findWhereDate(date);
    }

    @Test
    public void findValorePuntoWhereDate_whenDaoReturnEmptyList_shouldReturnNull() {
        when(dao.findWhereDate(date)).thenReturn(Collections.emptyList());
        assertNull(service.findValorePuntoWhereDate(date));
        verify(dao, times(1)).findWhereDate(date);
    }

    @Test
    public void findValorePuntoWhereDate_whenDaoReturnOneEntity_shouldReturnValorePunto() {
        final BigDecimal value = new BigDecimal("0.23");
        when(dao.findWhereDate(date)).thenReturn(Collections.singletonList(entity));
        when(entity.getValorePunto()).thenReturn(value);
        final BigDecimal valorePunto = service.findValorePuntoWhereDate(date);
        assertEquals(value, valorePunto);
        verify(dao, times(1)).findWhereDate(date);
    }
}