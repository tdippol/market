package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.FormaPagamentoDAO;
import com.axiante.mui.dbpromo.persistence.entities.FormaPagamentoEntity;
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
public class FormaPagamentoServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private FormaPagamentoDAO dao;

    @Mock
    private FormaPagamentoEntity formaPagamento;

    @InjectMocks
    private FormaPagamentoServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAllActive()).thenReturn(Collections.singletonList(formaPagamento));
        when(dao.findByCode("23")).thenReturn(formaPagamento);
        when(dao.findByCode("42")).thenReturn(null);
    }

    @Test
    public void findAllActive() {
        List<FormaPagamentoEntity> result = service.findAllActive();
        assertEquals(1, result.size());
        verify(dao, times(1)).findAllActive();
    }

    @Test
    public void findByCode_whenEntityExists_shouldReturnEntity() {
        FormaPagamentoEntity byCode = service.findByCode("23");
        assertNotNull(byCode);
        verify(dao, times(1)).findByCode("23");
    }

    @Test
    public void findByCode_whenEntityNotExists_shouldReturnNull() {
        FormaPagamentoEntity byCode = service.findByCode("42");
        assertNull(byCode);
        verify(dao, times(1)).findByCode("42");
    }
}