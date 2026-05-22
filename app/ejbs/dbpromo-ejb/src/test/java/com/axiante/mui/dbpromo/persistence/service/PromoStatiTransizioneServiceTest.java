package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PromoStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.PromoStatiTransizioneServiceImpl;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoStatiTransizioneServiceTest {
    @Mock
    private PromoStatiTransizioneDAO promoStatiTransizioneDAO;
    @Spy
    @InjectMocks
    PromoStatiTransizioneServiceImpl service;

    @Test
    public void testFindByIdAndStatus() {
        Long promoID = 123l;
        Long statusID = 123l;
        service.findByIdAndStatus(promoID, statusID);
        verify(promoStatiTransizioneDAO).findByIdAndStatus(promoID, statusID);
    }

    @Test
    public void testPersist() {
        PromoStatiTransizioneEntity entity = mock(PromoStatiTransizioneEntity.class);
        service.persist(entity);

        verify(promoStatiTransizioneDAO).persist(entity);
    }

    @Test(expected = NullPointerException.class)
    public void whenFindByPromozioneWithNullThenThrowNullpointerException() {
        service.findByPromozione(null);
    }

    @Test
    public void whenFindByPromozioneHasNoStatusThenReturnEmptyList() {
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        when(promozione.getPromozioneStatoEntities()).thenReturn(new HashSet<PromozioneStatoEntity>());
        List<PromoStatiTransizioneEntity> result = service.findByPromozione(promozione);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void whenFindByPromozioneHasStatusThenDelegatesToFindByIdAndStatus() {
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        PromozioneStatoEntity stato = mock(PromozioneStatoEntity.class);
        StatoPromozioneEntity statoPromozione = mock(StatoPromozioneEntity.class);
        when(promozione.getPromozioneStatoEntities())
                .thenReturn(new HashSet<PromozioneStatoEntity>(Arrays.asList(stato)));
        long id = 123l;
        when(promozione.getId()).thenReturn(id);
        when(stato.getStatoPromozioneEntity()).thenReturn(statoPromozione);
        when(statoPromozione.getId()).thenReturn(id);
        service.findByPromozione(promozione);
        verify(service).findByIdAndStatus(id, id);
    }

    @Test
    public void whenFindByPromozioneHasNoStatusThenReturnEmptyListWithDataFineStatoNotNull() {
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        PromozioneStatoEntity stato = mock(PromozioneStatoEntity.class);
        when(stato.getDataFineStato()).thenReturn(Calendar.getInstance().getTime());
        when(promozione.getPromozioneStatoEntities())
            .thenReturn(new HashSet<PromozioneStatoEntity>(Arrays.asList(stato)));
        List<PromoStatiTransizioneEntity> result = service.findByPromozione(promozione);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

}
