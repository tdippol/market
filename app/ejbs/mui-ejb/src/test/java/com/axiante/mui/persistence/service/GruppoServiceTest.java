package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GroupDAO;
import com.axiante.mui.persistence.service.impl.GruppoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GruppoServiceTest {

    @Mock
    private Instance<GroupDAO> groupDAOInstance;

    @Mock
    private GroupDAO groupDAO;

    @InjectMocks
    private GruppoServiceImpl service;

    private List<String> codiciGruppo;

    @Before
    public void setUp() {
        when(groupDAOInstance.get()).thenReturn(groupDAO);
        codiciGruppo = Arrays.asList("A", "B");
    }

    @Test(expected = NullPointerException.class)
    public void hasAccessTotaleByCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        service.hasAccessTotaleByCodiciGruppo(null, PianificazioneSecurityEnum.READ);
        verify(groupDAO, never()).countAccessTotaleByCodiciGruppoAndTipoNotNull(anyList());
    }

    @Test(expected = NullPointerException.class)
    public void hasAccessTotaleByCodiciGruppo_givenNullSecurity_shouldThrowException() {
        service.hasAccessTotaleByCodiciGruppo(codiciGruppo, null);
        verify(groupDAO, never()).countAccessTotaleByCodiciGruppoAndTipoNotNull(anyList());
    }

    @Test
    public void hasAccessTotaleByCodiciGruppo_givenEmptyCodiciGruppo_shouldReturnFalse() {
        assertFalse(service.hasAccessTotaleByCodiciGruppo(Collections.emptyList(), PianificazioneSecurityEnum.READ));
        verify(groupDAO, never()).countAccessTotaleByCodiciGruppoAndTipoNotNull(anyList());
    }

    @Test
    public void hasAccessTotaleByCodiciGruppo_whenReadSecurityAndNoAssociation_shouldReturnFalse() {
        when(groupDAO.countAccessTotaleByCodiciGruppoAndTipoNotNull(codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ));
        verify(groupDAO, times(1)).countAccessTotaleByCodiciGruppoAndTipoNotNull(codiciGruppo);
        verify(groupDAO, never()).countWriteableAccessTotaleByCodiciGruppo(codiciGruppo);
    }

    @Test
    public void hasAccessTotaleByCodiciGruppo_whenReadSecurityAndAssociation_shouldReturnTrue() {
        when(groupDAO.countAccessTotaleByCodiciGruppoAndTipoNotNull(codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ));
        verify(groupDAO, times(1)).countAccessTotaleByCodiciGruppoAndTipoNotNull(codiciGruppo);
        verify(groupDAO, never()).countWriteableAccessTotaleByCodiciGruppo(codiciGruppo);
    }

    @Test
    public void hasAccessTotaleByCodiciGruppo_whenWriteSecurityAndNoAssociation_shouldReturnFalse() {
        when(groupDAO.countWriteableAccessTotaleByCodiciGruppo(codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.WRITE));
        verify(groupDAO, times(1)).countWriteableAccessTotaleByCodiciGruppo(codiciGruppo);
        verify(groupDAO, never()).countAccessTotaleByCodiciGruppoAndTipoNotNull(codiciGruppo);
    }

    @Test
    public void hasAccessTotaleByCodiciGruppo_whenWriteSecurityAndAssociation_shouldReturnTrue() {
        when(groupDAO.countWriteableAccessTotaleByCodiciGruppo(codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.WRITE));
        verify(groupDAO, times(1)).countWriteableAccessTotaleByCodiciGruppo(codiciGruppo);
        verify(groupDAO, never()).countAccessTotaleByCodiciGruppoAndTipoNotNull(codiciGruppo);
    }
}
