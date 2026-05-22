package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.business.service.impl.CfgPianificazioneServiceImpl;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgPianificazioneServiceTest {

    @Mock
    private CfgPianificazioneDAO dao;

    @Spy
    @InjectMocks
    private final CfgPianificazioneService service = new CfgPianificazioneServiceImpl();

    @Test
    public void shouldIsLockable() {
        CfgSetPianificazioneEntity mock = mock(CfgSetPianificazioneEntity.class);
        Set<PromozioneTestataEntity> testate = new HashSet<>();
        when(mock.getPromozioneTestataEntities()).thenReturn(testate);
        boolean isLockable = service.isLockable(mock);
        verify(service, times(1)).isLockable(mock);
        assertTrue(isLockable);
    }

    @Test
    public void shouldIsNotLockable() {
        CfgSetPianificazioneEntity mock = mock(CfgSetPianificazioneEntity.class);
        PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        when(mockTestata.getDataInizio()).thenReturn(calendar.getTime());
        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        when(mockTestata.getDataFine()).thenReturn(calendar.getTime());
        Set<PromozioneTestataEntity> testate = new HashSet<>();
        testate.add(mockTestata);
        when(mock.getPromozioneTestataEntities()).thenReturn(testate);
        boolean isLockable = service.isLockable(mock);
        verify(service, times(1)).isLockable(mock);
        assertFalse(isLockable);
    }

    @Test
    public void shouldFindTipoElement() {
        when(dao.findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(Long.MAX_VALUE, Long.MAX_VALUE)).thenReturn(Arrays.asList("test"));
        List<String> tipoElementi = service.findTipoElement(Long.MAX_VALUE, Long.MAX_VALUE);
        verify(dao, times(1)).findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(Long.MAX_VALUE, Long.MAX_VALUE);
        verify(service, times(1)).findTipoElement(Long.MAX_VALUE, Long.MAX_VALUE);
        assertNotNull(tipoElementi);
        assertFalse(tipoElementi.isEmpty());
    }

    @Test
    public void shouldNotFindTipoElement() {
        List<String> tipoElementi = service.findTipoElement(Long.MIN_VALUE, Long.MIN_VALUE);
        verify(dao, times(1)).findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(Long.MIN_VALUE, Long.MIN_VALUE);
        verify(service, times(1)).findTipoElement(Long.MIN_VALUE, Long.MIN_VALUE);
        assertNotNull(tipoElementi);
        assertTrue(tipoElementi.isEmpty());
    }

    @Test
    public void shouldFindPianificazioneByCanaleMeccanica() {
        CanalePromozioneEntity mockCanale = mock(CanalePromozioneEntity.class);
        MeccanicheEntity mockMeccanica = mock(MeccanicheEntity.class);
        CfgPianificazioneEntity pianificazioneMock = mock(CfgPianificazioneEntity.class);
        when(dao.findAllDistinctByCanaleAndMeccanica(mockCanale, mockMeccanica)).thenReturn(Arrays.asList(pianificazioneMock));
        List<CfgPianificazioneEntity> pianificazioni = service.findPianificazioneByCanaleMeccanica(mockCanale, mockMeccanica);
        verify(dao, times(1)).findAllDistinctByCanaleAndMeccanica(mockCanale, mockMeccanica);
        verify(service, times(1)).findPianificazioneByCanaleMeccanica(mockCanale, mockMeccanica);
        assertNotNull(pianificazioni);
        assertFalse(pianificazioni.isEmpty());
    }

    @Test
    public void shouldNotFindPianificazioneByCanaleMeccanica() {
        CanalePromozioneEntity mockCanale = mock(CanalePromozioneEntity.class);
        List<CfgPianificazioneEntity> pianificazioni = service.findPianificazioneByCanaleMeccanica(mockCanale, null);
        verify(dao, times(1)).findAllDistinctByCanaleAndMeccanica(mockCanale, null);
        verify(service, times(1)).findPianificazioneByCanaleMeccanica(mockCanale, null);
        assertNotNull(pianificazioni);
        assertTrue(pianificazioni.isEmpty());
    }

    @Test
    public void shouldFindPianificazioneByCanaleMeccanicaField() {
        CanalePromozioneEntity mockCanale = mock(CanalePromozioneEntity.class);
        MeccanicheEntity mockMeccanica = mock(MeccanicheEntity.class);
        CfgPianificazioneEntity pianificazioneMock = mock(CfgPianificazioneEntity.class);
        when(dao.findAllByCanaleAndMeccanicaAndField(mockCanale, mockMeccanica, "field")).thenReturn(Arrays.asList(pianificazioneMock));
        List<CfgPianificazioneEntity> pianificazioni = service.findPianificazioneByCanaleMeccanicaField(mockCanale, mockMeccanica, "field");
        verify(dao, times(1)).findAllByCanaleAndMeccanicaAndField(mockCanale, mockMeccanica, "field");
        verify(service, times(1)).findPianificazioneByCanaleMeccanicaField(mockCanale, mockMeccanica, "field");
        assertNotNull(pianificazioni);
        assertFalse(pianificazioni.isEmpty());
    }

    @Test
    public void shouldNotFindPianificazioneByCanaleMeccanicaField() {
        CanalePromozioneEntity mockCanale = mock(CanalePromozioneEntity.class);
        List<CfgPianificazioneEntity> pianificazioni = service.findPianificazioneByCanaleMeccanicaField(mockCanale, null, "field");
        verify(dao, times(1)).findAllByCanaleAndMeccanicaAndField(mockCanale, null, "field");
        verify(service, times(1)).findPianificazioneByCanaleMeccanicaField(mockCanale, null, "field");
        assertNotNull(pianificazioni);
        assertTrue(pianificazioni.isEmpty());
    }


    @Test
    public void findById_givenId_shouldReturnEntity() {
        CfgPianificazioneEntity entityMock = mock(CfgPianificazioneEntity.class);
        when(dao.findById(1L)).thenReturn(entityMock);
        CfgPianificazioneEntity entity = service.findById(1L);
        verify(dao, times(1)).findById(1L);
        verify(service, times(1)).findById(1L);
        assertNotNull(entity);
    }

    @Test
    public void findByid_givenNotExistentId_shouldReturnNull() {
        when(dao.findById(99L)).thenReturn(null);
        CfgPianificazioneEntity entity = service.findById(99L);
        verify(dao, times(1)).findById(99L);
        verify(service, times(1)).findById(99L);
        assertNull(entity);
    }

    @Test
    public void saveCfgPianificazioneEntity_givenValidEntity_shouldSave() {
        CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
        when(dao.persistWithAuditLog(any(CfgPianificazioneEntity.class), eq("mockito"))).thenReturn(mock);
        final CfgPianificazioneEntity entity = service.saveCfgPianificazioneEntity(mock, "mockito");
        verify(dao, times(1)).persistWithAuditLog(mock, "mockito");
        verify(service, times(1)).saveCfgPianificazioneEntity(mock, "mockito");
        assertNotNull(entity);
    }

    @Test(expected = NullPointerException.class)
    public void saveCfgPianificazioneEntity_givenNullEntity_shouldThrowException() {
        try {
            service.saveCfgPianificazioneEntity(null, "mockito");
        } finally {
            verify(dao, never()).persistWithAuditLog((AuditLogInterface) null, "mockito");
            verify(service, times(1)).saveCfgPianificazioneEntity(null, "mockito");
        }
    }

    @Test(expected = NullPointerException.class)
    public void saveCfgPianificazioneEntity_givenNullUsername_shouldThrowException() {
        CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
        try {
            service.saveCfgPianificazioneEntity(mock, null);
        } finally {
            verify(dao, never()).persistWithAuditLog(mock, null);
            verify(service, times(1)).saveCfgPianificazioneEntity(mock, null);
        }
    }

    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void saveCfgPianificazioneEntities_givenListOfEntities_shouldSaveAll() {
		List entities = mock(List.class);
        doNothing().when(dao).persist(anyList());
        service.saveCfgPianificazioneEntities(entities);
        verify(dao, times(1)).persist(entities);
        verify(service, times(1)).saveCfgPianificazioneEntities(entities);
    }

    @Test(expected = NullPointerException.class)
    public void saveCfgPianificazioneEntities_givenNullList_shouldThrowException() {
        try {
            service.saveCfgPianificazioneEntities(null);
        } finally {
            verify(dao, never()).persist((List<CfgPianificazioneEntity>) null);
            verify(service, times(1)).saveCfgPianificazioneEntities(null);
        }
    }

    @Test
    public void removeCfgPianificazioneEntity_givenEntity_shouldDelete() {
        CfgPianificazioneEntity mockEntity = mock(CfgPianificazioneEntity.class);
        doNothing().when(dao).remove(any(CfgPianificazioneEntity.class));
        service.removeCfgPianificazioneEntity(mockEntity);
        verify(dao, times(1)).remove(mockEntity);
        verify(service, times(1)).removeCfgPianificazioneEntity(mockEntity);
    }
}
