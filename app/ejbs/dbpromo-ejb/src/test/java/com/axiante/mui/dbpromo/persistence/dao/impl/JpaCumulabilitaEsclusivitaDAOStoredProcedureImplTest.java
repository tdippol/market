package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.OperazioneCumulabilita;
import com.axiante.mui.dbpromo.business.utils.Constants;
import org.junit.Before;
import org.junit.Test;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaCumulabilitaEsclusivitaDAOStoredProcedureImplTest {
    @Mock
    private EntityManager em;

    @Mock
    private StoredProcedureQuery spq;

    @Spy
    private JpaCumulabilitaEsclusivitaDAOImpl dao = new JpaCumulabilitaEsclusivitaDAOImpl();

    @Before
    public void setUp() {
        doReturn(em).when(dao).getEm();
        when(em.createStoredProcedureQuery(anyString())).thenReturn(spq);
        when(spq.registerStoredProcedureParameter(anyInt(), any(), any(ParameterMode.class))).thenReturn(spq);
        when(spq.setParameter(anyInt(), any())).thenReturn(spq);
        when(spq.executeUpdate()).thenReturn(1);
    }

    @Test
    public void exportCumulabilita_invokesStoredProcedureWithExpectedParameters_andReturnsTrue() {
        boolean result = dao.exportCumulabilita(123L, "user1");
        assertTrue(result);
        verify(em).createStoredProcedureQuery(Constants.P_MUI_EXPORT_CUMUL_ESCLUS);
        verify(spq).registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        verify(spq).setParameter(1, 123L);
        verify(spq).setParameter(2, "user1");
        verify(spq).executeUpdate();
    }

    @Test
    public void updateCumulabilita_invokesStoredProcedureWithExpectedParameters_andReturnsTrue() {
        boolean result = dao.updateCumulabilita(123L, OperazioneCumulabilita.INS_TES, "user1");
        assertTrue(result);
        verify(em).createStoredProcedureQuery(Constants.P_AGGIORNA_COMULABILITA);
        verify(spq).registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        verify(spq).setParameter(1, 123L);
        verify(spq).setParameter(2, OperazioneCumulabilita.INS_TES.getCode());
        verify(spq).setParameter(3, "user1");
        verify(spq).executeUpdate();
    }
}