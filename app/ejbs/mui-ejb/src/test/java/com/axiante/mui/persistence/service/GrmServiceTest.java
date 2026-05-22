package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoGrmDAO;
import com.axiante.mui.persistence.service.impl.GrmServiceImpl;
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
public class GrmServiceTest {

    @Mock
    private Instance<GruppoGrmDAO> gruppoGrmDAOInstance;

    @Mock
    private GruppoGrmDAO gruppoGrmDAO;

    @InjectMocks
    private GrmServiceImpl service;

    private List<String> codiciGruppo;

    @Before
    public void setUp() {
        when(gruppoGrmDAOInstance.get()).thenReturn(gruppoGrmDAO);
        codiciGruppo = Arrays.asList("A", "B");
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdGrmAndCodiciGruppo_givenNullIdGrm_shouldThrowException() {
        service.hasAssociationWithIdGrmAndCodiciGruppo(null, codiciGruppo, PianificazioneSecurityEnum.READ);
        verify(gruppoGrmDAO, never()).countByGrmIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdGrmAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        service.hasAssociationWithIdGrmAndCodiciGruppo(1, null, PianificazioneSecurityEnum.READ);
        verify(gruppoGrmDAO, never()).countByGrmIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdGrmAndCodiciGruppo_givenNullSecurity_shouldThrowException() {
        service.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, null);
        verify(gruppoGrmDAO, never()).countByGrmIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test
    public void hasAssociationWithIdGrmAndCodiciGruppo_givenEmptyCodiciGruppo_shouldReturnFalse() {
        assertFalse(service.hasAssociationWithIdGrmAndCodiciGruppo(1, Collections.emptyList(),
                PianificazioneSecurityEnum.READ));
        verify(gruppoGrmDAO, never()).countByGrmIdAndCodiciGruppo(anyInt(), anyList());
    }

    @Test
    public void hasAssociationWithIdGrmAndCodiciGruppo_whenReadSecurityNoAssociation_shouldReturnFalse() {
        when(gruppoGrmDAO.countByGrmIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.READ));
        verify(gruppoGrmDAO, times(1)).countByGrmIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoGrmDAO, never()).countWritableByGrmIdAndCodiciGruppo(1, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdGrmAndCodiciGruppo_whenReadSecurityAssociation_shouldReturnTrue() {
        when(gruppoGrmDAO.countByGrmIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.READ));
        verify(gruppoGrmDAO, times(1)).countByGrmIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoGrmDAO, never()).countWritableByGrmIdAndCodiciGruppo(1, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdGrmAndCodiciGruppo_whenWriteSecurityNoAssociation_shouldReturnFalse() {
        when(gruppoGrmDAO.countWritableByGrmIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.WRITE));
        verify(gruppoGrmDAO, times(1)).countWritableByGrmIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoGrmDAO, never()).countByGrmIdAndCodiciGruppo(1, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdGrmAndCodiciGruppo_whenWriteSecurityAssociation_shouldReturnTrue() {
        when(gruppoGrmDAO.countWritableByGrmIdAndCodiciGruppo(1, codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo,
                PianificazioneSecurityEnum.WRITE));
        verify(gruppoGrmDAO, times(1)).countWritableByGrmIdAndCodiciGruppo(1, codiciGruppo);
        verify(gruppoGrmDAO, never()).countByGrmIdAndCodiciGruppo(1, codiciGruppo);
    }
}
