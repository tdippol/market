package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.ReportArticoloDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import java.util.Collections;
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
public class ReportArticoloServiceImplTest {

    @Mock
    ReportArticoloDAO dao;

    @InjectMocks
    private ReportArticoloServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private MuiReportArticoloEntity entity1;

    @Mock
    private MuiReportArticoloEntity entity2;

    @Mock
    private MuiReportArticoloEntity entity3;

    @Before
    public void setUp() throws Exception {
        when(dao.findAllInProgressFuturesByIdItem(1L)).thenReturn(Collections.singletonList(entity1));
//        when(dao.findAllFuturesByIdItem(2L)).thenReturn(Collections.singletonList(entity2));
        when(dao.findAllCompletedByIdItem(3L)).thenReturn(Collections.singletonList(entity3));
    }

    @Test
    public void findAllInProgressFutureByIdItem_givenNullIdItem_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllInProgressFutureByIdItem(null);
    }

    @Test
    public void findAllInProgressFutureByIdItem() {
        final List<MuiReportArticoloEntity> result = service.findAllInProgressFutureByIdItem(1L);
        assertEquals(1, result.size());
        assertEquals(entity1, result.get(0));
        verify(dao, times(1)).findAllInProgressFuturesByIdItem(1L);
        verify(dao, never()).findAllCompletedByIdItem(anyLong());
    }

    @Test
    public void findAllCompletedByIdItem_givenNullIdItem_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllCompletedByIdItem(null);
    }

    @Test
    public void findAllCompletedByIdItem() {
        final List<MuiReportArticoloEntity> result = service.findAllCompletedByIdItem(3L);
        assertEquals(1, result.size());
        assertEquals(entity3, result.get(0));
        verify(dao, never()).findAllInProgressFuturesByIdItem(anyLong());
        verify(dao, times(1)).findAllCompletedByIdItem(3L);
    }
}