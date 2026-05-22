package com.axiante.mui.dbpromo.persistence.dao.impl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SottoclasseDAOImplStoredProceduresTest {
    @Mock
    private EntityManager em;

    @Mock
    private StoredProcedureQuery spq;

    @Spy
    private SottoclasseDAOImpl dao = new SottoclasseDAOImpl();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        doReturn(em).when(dao).getEm();
        when(em.createStoredProcedureQuery(anyString())).thenReturn(spq);
        when(spq.registerStoredProcedureParameter(anyInt(), any(), any(ParameterMode.class))).thenReturn(spq);
        when(spq.setParameter(anyInt(), any())).thenReturn(spq);
        when(spq.executeUpdate()).thenReturn(1);
    }

    @Test
    public void runFunctionPubblicaSottoclassi_givenNullUsername_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        dao.runFunctionPubblicaSottoclassi(null);
        verifyZeroInteractions(em, spq);
    }

    @Test
    public void runFunctionPubblicaSottoclassi_whenExecuteFails_shouldPropagateException() throws Exception {
        ex.expect(RuntimeException.class);
        when(spq.executeUpdate()).thenThrow(new RuntimeException("DB failure"));
        dao.runFunctionPubblicaSottoclassi("USER");
        verify(em, times(1)).createStoredProcedureQuery(anyString());
        verify(spq, times(1)).registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        verify(spq, times(1)).setParameter(1, "USER");
        verify(spq, times(1)).executeUpdate();
    }

    @Test
    public void runFunctionPubblicaSottoclassi() throws Exception {
        assertTrue(dao.runFunctionPubblicaSottoclassi("USER"));
        verify(em, times(1)).createStoredProcedureQuery(anyString());
        verify(spq, times(1)).registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        verify(spq, times(1)).setParameter(1, "USER");
        verify(spq, times(1)).executeUpdate();
    }
}