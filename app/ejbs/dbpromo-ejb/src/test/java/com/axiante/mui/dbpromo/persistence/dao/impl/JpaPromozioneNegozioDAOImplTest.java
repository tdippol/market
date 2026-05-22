package com.axiante.mui.dbpromo.persistence.dao.impl;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaPromozioneNegozioDAOImplTest {

    @Mock
    private EntityManager em;

    @Mock
    private StoredProcedureQuery spq;

    @Spy
    private JpaPromozioneNegozioDAOImpl dao = new JpaPromozioneNegozioDAOImpl();

    // Common test data
    private final Long idPromo = 100L;
    private final String codicePromozioneSorgente = "PROMO-001";
    private final String codiceCategoriaPlano = "CAT-01";
    private final String tipologiaPlano = "TIPO-01";
    private final String dimensionePlano = "DIM-01";
    private final String username = "tester";

    @Before
    public void setUp() {
        // Make dao.getEm() return our mocked EntityManager
        doReturn(em).when(dao).getEm();

        // Default stubbing for the SP fluent chain
        when(em.createStoredProcedureQuery(anyString())).thenReturn(spq);
        when(spq.registerStoredProcedureParameter(anyInt(), any(), any())).thenReturn(spq);
        when(spq.setParameter(anyInt(), any())).thenReturn(spq);
        when(spq.executeUpdate()).thenReturn(1);
    }

    @Test
    public void impostaNegozi_shouldCallStoredProcedureWithCorrectParams_andReturnTrue() throws Exception {
        boolean result = dao.impostaNegozi(
                idPromo, codicePromozioneSorgente, codiceCategoriaPlano, tipologiaPlano, dimensionePlano, username
        );

        assertTrue(result);

        // Verify stored procedure name
        verify(em, times(1)).createStoredProcedureQuery(Constants.SP_IMPOSTA_NEGOZI);

        // Verify parameter registrations
        verify(spq).registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(6, String.class, ParameterMode.IN);

        // Verify parameter values
        verify(spq).setParameter(1, idPromo);
        verify(spq).setParameter(2, codicePromozioneSorgente);
        verify(spq).setParameter(3, codiceCategoriaPlano);
        verify(spq).setParameter(4, tipologiaPlano);
        verify(spq).setParameter(5, dimensionePlano);
        verify(spq).setParameter(6, username);

        // Verify execution
        verify(spq, times(1)).executeUpdate();
    }

    @Test(expected = Exception.class)
    public void impostaNegozi_whenExecuteFails_shouldPropagateException() throws Exception {
        when(spq.executeUpdate()).thenThrow(new RuntimeException("DB failure"));

        dao.impostaNegozi(
                idPromo, codicePromozioneSorgente, codiceCategoriaPlano, tipologiaPlano, dimensionePlano, username
        );
    }

    @Test
    public void eliminaNegozi_shouldCallStoredProcedureWithCorrectParams_andReturnTrue() throws Exception {
        boolean result = dao.eliminaNegozi(
                idPromo, codiceCategoriaPlano, tipologiaPlano, dimensionePlano
        );

        assertTrue(result);

        // Verify stored procedure name
        verify(em, times(1)).createStoredProcedureQuery(Constants.SP_ELIMINA_NEGOZI);

        // Verify parameter registrations
        verify(spq).registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        verify(spq).registerStoredProcedureParameter(4, String.class, ParameterMode.IN);

        // Verify parameter values
        verify(spq).setParameter(1, idPromo);
        verify(spq).setParameter(2, codiceCategoriaPlano);
        verify(spq).setParameter(3, tipologiaPlano);
        verify(spq).setParameter(4, dimensionePlano);

        // Verify execution
        verify(spq, times(1)).executeUpdate();
    }

    @Test(expected = Exception.class)
    public void eliminaNegozi_whenExecuteFails_shouldPropagateException() throws Exception {
        when(spq.executeUpdate()).thenThrow(new RuntimeException("DB failure"));

        dao.eliminaNegozi(idPromo, codiceCategoriaPlano, tipologiaPlano, dimensionePlano);
    }
}