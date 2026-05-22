package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.ArticoloDAO;
import com.axiante.mui.persistence.service.impl.ArticoloServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticoloServiceTest {

    @Mock
    private Instance<ArticoloDAO> articoloDAOInstance;

    @Mock
    private ArticoloDAO articoloDAO;

    @InjectMocks
    private ArticoloServiceImpl service;

    private List<String> codiciGruppo;

    @Before
    public void setUp() {
        when(articoloDAOInstance.get()).thenReturn(articoloDAO);
        codiciGruppo = Arrays.asList("A", "B");
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdArticoloAndCodiciGruppo_givenNullIdArticolo_shouldThrowException() {
        service.hasAssociationWithIdArticoloAndCodiciGruppo(null, codiciGruppo, PianificazioneSecurityEnum.READ);
        verify(articoloDAO, never()).countByArticoloIdAndCodiciGruppo(anyLong(), anyList());
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdArticoloAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        service.hasAssociationWithIdArticoloAndCodiciGruppo(1L, null, PianificazioneSecurityEnum.READ);
        verify(articoloDAO, never()).countByArticoloIdAndCodiciGruppo(anyLong(), anyList());
    }

    @Test(expected = NullPointerException.class)
    public void hasAssociationWithIdArticoloAndCodiciGruppo_givenNullSecurity_shouldThrowException() {
        service.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, null);
        verify(articoloDAO, never()).countByArticoloIdAndCodiciGruppo(anyLong(), anyList());
    }

    @Test
    public void hasAssociationWithIdArticoloAndCodiciGruppo_givenEmptyCodiciGruppo_shouldReturnFalse() {
        assertFalse(service.hasAssociationWithIdArticoloAndCodiciGruppo(1L, Collections.emptyList(),
                PianificazioneSecurityEnum.READ));
        verify(articoloDAO, never()).countByArticoloIdAndCodiciGruppo(anyLong(), anyList());
    }

    @Test
    public void hasAssociationWithIdArticoloAndCodiciGruppo_whenReadSecurityNoAssociation_shouldReturnFalse() {
        when(articoloDAO.countByArticoloIdAndCodiciGruppo(1L, codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo,
                PianificazioneSecurityEnum.READ));
        verify(articoloDAO, times(1)).countByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
        verify(articoloDAO, never()).countWritableByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdArticoloAndCodiciGruppo_whenReadSecurityAssociation_shouldReturnTrue() {
        when(articoloDAO.countByArticoloIdAndCodiciGruppo(1L, codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo,
                PianificazioneSecurityEnum.READ));
        verify(articoloDAO, times(1)).countByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
        verify(articoloDAO, never()).countWritableByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdArticoloAndCodiciGruppo_whenWriteSecurityNoAssociation_shouldReturnFalse() {
        when(articoloDAO.countWritableByArticoloIdAndCodiciGruppo(1L, codiciGruppo)).thenReturn(0L);
        assertFalse(service.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo,
                PianificazioneSecurityEnum.WRITE));
        verify(articoloDAO, times(1)).countWritableByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
        verify(articoloDAO, never()).countByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
    }

    @Test
    public void hasAssociationWithIdArticoloAndCodiciGruppo_whenWriteSecurityAssociation_shouldReturnTrue() {
        when(articoloDAO.countWritableByArticoloIdAndCodiciGruppo(1L, codiciGruppo)).thenReturn(2L);
        assertTrue(service.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo,
                PianificazioneSecurityEnum.WRITE));
        verify(articoloDAO, times(1)).countWritableByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
        verify(articoloDAO, never()).countByArticoloIdAndCodiciGruppo(1L, codiciGruppo);
    }
}
