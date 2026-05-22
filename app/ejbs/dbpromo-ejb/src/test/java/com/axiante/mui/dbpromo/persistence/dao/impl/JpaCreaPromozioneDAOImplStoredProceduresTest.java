package com.axiante.mui.dbpromo.persistence.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaCreaPromozioneDAOImplStoredProceduresTest {
    @Mock
    private EntityManager em;

    @Mock
    private StoredProcedureQuery spq;

    // Spy the concrete DAO implementation so we can override getEm()
    @Spy
    private JpaCreaPromozioneDAOImpl dao = new JpaCreaPromozioneDAOImpl();

    private final Long idUser = 10L;
    private final Long idCanale = 20L;
    private final Date dataInizio = new Date(System.currentTimeMillis() - 86400000L);
    private final Date dataFine = new Date();
    private final Long maxTestate = 100L;

    @Before
    public void setUp() {
        // Force the DAO to use our mocked EntityManager
        doReturn(em).when(dao).getEm();

        // Default stubbing for SP creation and fluent API
        when(em.createStoredProcedureQuery(anyString())).thenReturn(spq);
        when(spq.registerStoredProcedureParameter(anyInt(), any(), any(ParameterMode.class))).thenReturn(spq);
        when(spq.setParameter(anyInt(), any())).thenReturn(spq);
        when(spq.executeUpdate()).thenReturn(1);
    }

    @Test
    public void runFunctionCountTestate_userCanaleDate_shouldExecuteAndReturnTrue() throws Exception {
        boolean result = dao.runFunctionCountTestate(idUser, idCanale, dataInizio, dataFine);

        assertTrue(result);

        // The specific SP name may vary; ensure a stored procedure is created
        verify(em, times(1)).createStoredProcedureQuery(anyString());

        // Verify parameters have been registered (indexes/types may vary by implementation; we assert count)
        verify(spq, atLeast(4)).registerStoredProcedureParameter(anyInt(), any(), eq(ParameterMode.IN));

        // Verify parameters are set (we expect 4 input params for this overload)
        verify(spq, times(1)).setParameter(eq(1), eq(idUser));
        verify(spq, times(1)).setParameter(eq(2), eq(idCanale));
        verify(spq, times(1)).setParameter(eq(3), eq(dataInizio));
        verify(spq, times(1)).setParameter(eq(4), eq(dataFine));

        verify(spq, times(1)).executeUpdate();
    }

    @Test(expected = Exception.class)
    public void runFunctionCountTestate_userCanaleDate_whenExecuteFails_shouldPropagateException() throws Exception {
        when(spq.executeUpdate()).thenThrow(new RuntimeException("DB failure"));
        dao.runFunctionCountTestate(idUser, idCanale, dataInizio, dataFine);
    }

    @Test
    public void runFunctionCountTestate_canaleDateMax_shouldExecuteAndReturnTrue() throws Exception {
        boolean result = dao.runFunctionCountTestate(idCanale, dataInizio, dataFine, maxTestate);

        assertTrue(result);

        // The specific SP name may vary; ensure a stored procedure is created
        verify(em, times(1)).createStoredProcedureQuery(anyString());

        // Verify parameters have been registered (at least 4 IN params here as well)
        verify(spq, atLeast(4)).registerStoredProcedureParameter(anyInt(), any(), eq(ParameterMode.IN));

        // Verify parameters are set (we expect 4 input params for this overload)
        verify(spq, times(1)).setParameter(eq(1), eq(idCanale));
        verify(spq, times(1)).setParameter(eq(2), eq(dataInizio));
        verify(spq, times(1)).setParameter(eq(3), eq(dataFine));
        verify(spq, times(1)).setParameter(eq(4), eq(maxTestate));

        verify(spq, times(1)).executeUpdate();
    }

    @Test(expected = Exception.class)
    public void runFunctionCountTestate_canaleDateMax_whenExecuteFails_shouldPropagateException() throws Exception {
        when(spq.executeUpdate()).thenThrow(new RuntimeException("DB failure"));
        dao.runFunctionCountTestate(idCanale, dataInizio, dataFine, maxTestate);
    }
}