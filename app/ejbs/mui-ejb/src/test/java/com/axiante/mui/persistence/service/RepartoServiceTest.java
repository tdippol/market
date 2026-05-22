package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoRepartoDAO;
import com.axiante.mui.persistence.service.impl.RepartoServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepartoServiceTest {

    @Mock
    private Instance<GruppoRepartoDAO> gruppoRepartoDAOInstance;

    @Mock
    private GruppoRepartoDAO gruppoRepartoDAO;

    @InjectMocks
    private RepartoServiceImpl service;

    private List<String> codiciGruppo;

    @Before
    public void setUp() {
        when(gruppoRepartoDAOInstance.get()).thenReturn(gruppoRepartoDAO);
        codiciGruppo = Arrays.asList("A", "B");
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdRepartoAndCodiciGruppo_givenNullIdReparto_shouldThrowException() {
        service.hasAssociationWithIdRepartoAndCodiciGruppo(null, codiciGruppo, PianificazioneSecurityEnum.READ);
        verify(gruppoRepartoDAO, never()).countByRepartoIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdRepartoAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        service.hasAssociationWithIdRepartoAndCodiciGruppo(1, null, PianificazioneSecurityEnum.READ);
        verify(gruppoRepartoDAO, never()).countByRepartoIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdRepartoAndCodiciGruppo_givenNullSecurity_shouldThrowException() {
        service.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, null);
        verify(gruppoRepartoDAO, never()).countByRepartoIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test
    public void hasAssociationWithIdRepartoAndCodiciGruppo_givenEmptyCodiciGruppo_shouldReturnFalse() {
        assertFalse(service.hasAssociationWithIdRepartoAndCodiciGruppo(1, Collections.emptyList(),
                PianificazioneSecurityEnum.READ));
        verify(gruppoRepartoDAO, never()).countByRepartoIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test
    public void hasAssociationWithIdRepartoAndCodiciGruppo_whenReadSecurityAndNoAssociation_shouldReturnFalse() {
        when(gruppoRepartoDAO.countByRepartoIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.READ));
        verify(gruppoRepartoDAO, times(1)).countByRepartoIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoRepartoDAO, never()).countWriteableByRepartoIdAndCodiciGruppo(1, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdRepartoAndCodiciGruppo_whenReadSecurityAndAssociation_shouldReturnTrue() {
        when(gruppoRepartoDAO.countByRepartoIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.READ));
        verify(gruppoRepartoDAO, times(1)).countByRepartoIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoRepartoDAO, never()).countWriteableByRepartoIdAndCodiciGruppo(1, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdRepartoAndCodiciGruppo_whenWriteSecurityAndNoAssociation_shouldReturnFalse() {
        when(gruppoRepartoDAO.countWriteableByRepartoIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.WRITE));
        verify(gruppoRepartoDAO, times(1)).countWriteableByRepartoIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoRepartoDAO, never()).countByRepartoIdAndCodiciGruppo(1, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdRepartoAndCodiciGruppo_whenWriteSecurityAndAssociation_shouldReturnTrue() {
        when(gruppoRepartoDAO.countWriteableByRepartoIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.WRITE));
        verify(gruppoRepartoDAO, times(1)).countWriteableByRepartoIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoRepartoDAO, never()).countByRepartoIdAndCodiciGruppo(1, codiciGruppo);
    }
}
