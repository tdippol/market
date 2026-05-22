package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.UploadFidatyDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.UploadFidatyServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UploadFidatyServiceTest {

    @Mock
    UploadFidatyDAO dao;

    @Spy
    @InjectMocks
    private final UploadFidatyService service = new UploadFidatyServiceImpl();

    @Test
    public void shouldFindById() {
        UploadFidayEntity mock = mock(UploadFidayEntity.class);
        when(mock.getId()).thenReturn(Long.MIN_VALUE);
        when(dao.findById(mock.getId())).thenReturn(mock);
        UploadFidayEntity entity = service.findById(mock.getId());
        verify(dao, times(1)).findById(mock.getId());
        verify(service, times(1)).findById(mock.getId());
        assertNotNull(entity);
        assertEquals(mock, entity);
    }

    @Test
    public void shouldNotFindById() {
        UploadFidayEntity mock = mock(UploadFidayEntity.class);
        when(mock.getId()).thenReturn(Long.MIN_VALUE);
        when(dao.findById(mock.getId())).thenReturn(null);
        UploadFidayEntity entity = service.findById(mock.getId());
        verify(dao, times(1)).findById(mock.getId());
        verify(service, times(1)).findById(mock.getId());
        assertNull(entity);
    }

    @Test
    public void shouldFindAll() {
        UploadFidayEntity mock1 = mock(UploadFidayEntity.class);
        UploadFidayEntity mock2 = mock(UploadFidayEntity.class);
        List<UploadFidayEntity> uploads = Arrays.asList(mock1, mock2);
        when(dao.findAll()).thenReturn(uploads);
        List<UploadFidayEntity> entities = service.findAll();
        verify(dao, times(1)).findAll();
        verify(service, times(1)).findAll();
        assertNotNull(entities);
        assertTrue(entities.size() == 2);
    }

    @Test
    public void shouldNotFindAll() {
        List<UploadFidayEntity> entities = service.findAll();
        verify(dao, times(1)).findAll();
        verify(service, times(1)).findAll();
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }

    @Test
    public void shouldFindByPromozione() {
        UploadFidayEntity mock1 = mock(UploadFidayEntity.class);
        UploadFidayEntity mock2 = mock(UploadFidayEntity.class);
        PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
        when(mockTestata.getId()).thenReturn(Long.MAX_VALUE);
        List<UploadFidayEntity> uploads = Arrays.asList(mock1, mock2);
        when(dao.findByPromozione(mockTestata.getId())).thenReturn(uploads);
        List<UploadFidayEntity> entities = service.findByPromozione(mockTestata);
        verify(dao, times(1)).findByPromozione(mockTestata.getId());
        verify(service, times(1)).findByPromozione(mockTestata);
        verify(service, times(1)).findByPromozione(mockTestata.getId());
        assertNotNull(entities);
        assertTrue(entities.size() == 2);
    }

    @Test
    public void shouldNotFindByPromozione() {
        PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
        when(mockTestata.getId()).thenReturn(Long.MAX_VALUE);
        List<UploadFidayEntity> entities = service.findByPromozione(mockTestata);
        verify(dao, times(1)).findByPromozione(mockTestata.getId());
        verify(service, times(1)).findByPromozione(mockTestata);
        verify(service, times(1)).findByPromozione(mockTestata.getId());
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }

    @Test
    public void shouldFindByPianificazione() {
        UploadFidayEntity mock1 = mock(UploadFidayEntity.class);
        UploadFidayEntity mock2 = mock(UploadFidayEntity.class);
        PromozionePianificazioneEntity mockPianificazione = mock(PromozionePianificazioneEntity.class);
        when(mockPianificazione.getId()).thenReturn(Long.MAX_VALUE);
        List<UploadFidayEntity> uploads = Arrays.asList(mock1, mock2);
        when(dao.findByPianificazione(mockPianificazione.getId())).thenReturn(uploads);
        List<UploadFidayEntity> entities = service.findByPianificazione(mockPianificazione);
        verify(dao, times(1)).findByPianificazione(mockPianificazione.getId());
        verify(service, times(1)).findByPianificazione(mockPianificazione);
        assertNotNull(entities);
        assertTrue(entities.size() == 2);
    }

    @Test
    public void shouldNotFindByPianificazione() {
        PromozionePianificazioneEntity mockPianificazione = mock(PromozionePianificazioneEntity.class);
        when(mockPianificazione.getId()).thenReturn(Long.MAX_VALUE);
        List<UploadFidayEntity> entities = service.findByPianificazione(mockPianificazione);
        verify(dao, times(1)).findByPianificazione(mockPianificazione.getId());
        verify(service, times(1)).findByPianificazione(mockPianificazione);
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void whenFindByPianificazioneWithNoEntityThenThrowsNullpointerException() {
        service.findByPianificazione(null);
    }

    @Test
    public void shouldFindByPianificazioneId() {
        UploadFidayEntity mock1 = mock(UploadFidayEntity.class);
        UploadFidayEntity mock2 = mock(UploadFidayEntity.class);
        PromozionePianificazioneEntity mockPianificazione = mock(PromozionePianificazioneEntity.class);
        when(mockPianificazione.getId()).thenReturn(Long.MAX_VALUE);
        List<UploadFidayEntity> uploads = Arrays.asList(mock1, mock2);
        when(dao.findByPianificazione(mockPianificazione.getId())).thenReturn(uploads);
        List<UploadFidayEntity> entities = service.findByPianificazione(mockPianificazione.getId());
        verify(dao, times(1)).findByPianificazione(mockPianificazione.getId());
        verify(service, times(1)).findByPianificazione(mockPianificazione.getId());
        assertNotNull(entities);
        assertTrue(entities.size() == 2);
    }

    @Test
    public void shouldNotFindByPianificazioneId() {
        PromozionePianificazioneEntity mockPianificazione = mock(PromozionePianificazioneEntity.class);
        when(mockPianificazione.getId()).thenReturn(Long.MAX_VALUE);
        List<UploadFidayEntity> entities = service.findByPianificazione(mockPianificazione.getId());
        verify(dao, times(1)).findByPianificazione(mockPianificazione.getId());
        verify(service, times(1)).findByPianificazione(mockPianificazione.getId());
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }

    @Test
    public void testPersistUploadFidayEntity() {
        final UploadFidayEntity mock = mock(UploadFidayEntity.class);
        UploadFidayEntity entity = service.persist(mock);
        verify(dao, times(1)).persist(mock);
        verify(service, times(1)).persist(mock);
        assertNull(entity);
    }

    @Test
    public void testDeleteUploadFidayEntity() {
        UploadFidayEntity mock = mock(UploadFidayEntity.class);
        UploadFidayEntity entity = service.delete(mock);
        verify(dao, times(1)).remove(mock);
        verify(service, times(1)).delete(mock);
        assertEquals(mock, entity);
    }

    @Test
    public void testFindValidByPromozione() {
        UploadFidayEntity mock = mock(UploadFidayEntity.class);
        when(mock.getId()).thenReturn(Long.MIN_VALUE);
        when(dao.findValidByPromozione(mock.getId())).thenReturn(Arrays.asList(mock));
        List<UploadFidayEntity> uploadFidatys = service.findValidByPromozione(mock.getId());
        verify(dao, times(1)).findValidByPromozione(mock.getId());
        verify(service, times(1)).findValidByPromozione(mock.getId());
        assertEquals(mock.getId(), uploadFidatys.get(0).getId());
    }

    @Test
    public void testFindValidByPromozioneEmpty() {
        when(dao.findValidByPromozione(Long.MIN_VALUE)).thenReturn(new ArrayList<>());
        List<UploadFidayEntity> uploadFidatys = service.findValidByPromozione(Long.MIN_VALUE);
        verify(dao, times(1)).findValidByPromozione(Long.MIN_VALUE);
        verify(service, times(1)).findValidByPromozione(Long.MIN_VALUE);
        assertTrue(uploadFidatys.isEmpty());
    }

    @Test
    public void testFindByNomeDestinazioneAndPianificazione() {
        UploadFidayEntity mock = mock(UploadFidayEntity.class);
        when(mock.getId()).thenReturn(Long.MIN_VALUE);
        when(dao.findByNomeDestinazioneAndPianificazione("nomeFile", mock.getId())).thenReturn(mock);
        UploadFidayEntity uploadFidaty = service.findByNomeDestinazioneAndPianificazione("nomeFile", mock.getId());
        verify(dao, times(1)).findByNomeDestinazioneAndPianificazione("nomeFile", mock.getId());
        verify(service, times(1)).findByNomeDestinazioneAndPianificazione("nomeFile", mock.getId());
        assertEquals(mock.getId(), uploadFidaty.getId());
    }

    @Test
    public void testNotFindByNomeDestinazioneAndPianificazione() {
        when(dao.findByNomeDestinazioneAndPianificazione("nomeFile", Long.MAX_VALUE)).thenReturn(null);
        UploadFidayEntity uploadFidaty = service.findByNomeDestinazioneAndPianificazione("nomeFile", Long.MAX_VALUE);
        verify(dao, times(1)).findByNomeDestinazioneAndPianificazione("nomeFile", Long.MAX_VALUE);
        verify(service, times(1)).findByNomeDestinazioneAndPianificazione("nomeFile", Long.MAX_VALUE);
        assertNull(uploadFidaty);
    }

    @Test(expected = NullPointerException.class)
    public void testNotFindByNomeDestinazioneAndPianificazioneNoFileNameException() {
        service.findByNomeDestinazioneAndPianificazione(null, Long.MAX_VALUE);
        verify(dao, times(0)).findByNomeDestinazioneAndPianificazione(null, Long.MAX_VALUE);
    }

    @Test(expected = NullPointerException.class)
    public void testNotFindByNomeDestinazioneAndPianificazioneNoIdPianificazioneException() {
        service.findByNomeDestinazioneAndPianificazione("nomeFile", null);
        verify(dao, times(0)).findByNomeDestinazioneAndPianificazione("nomeFile", null);
    }

    @Test(expected = NullPointerException.class)
    public void testNotFindByNomeDestinazioneAndPianificazioneNullParamsException() {
        service.findByNomeDestinazioneAndPianificazione(null, null);
        verify(dao, times(0)).findByNomeDestinazioneAndPianificazione(null, null);
    }

}
