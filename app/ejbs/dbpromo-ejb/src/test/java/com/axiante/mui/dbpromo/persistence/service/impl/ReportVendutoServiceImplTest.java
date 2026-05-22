package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.ReportVendutoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportVendutoServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private ReportVendutoDAO dao;

    @InjectMocks
    private ReportVendutoServiceImpl service;

    @Test
    public void findAllByIdPromozione_givenNullIdPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllByIdPromozione(null);
        verify(dao, never()).findAllByIdPromozione(nullable(Long.class));
    }

    @Test
    public void findAllByIdPromozione_shouldCallDao() {
        List<ReportVendutoEntity> entities = Collections.singletonList(mock(ReportVendutoEntity.class));
        when(dao.findAllByIdPromozione(1L)).thenReturn(entities);
        assertEquals(1, service.findAllByIdPromozione(1L).size());
        verify(dao, times(1)).findAllByIdPromozione(1L);
    }
}