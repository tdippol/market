package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.NegoziPromoServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NegoziPromoServiceTest {
    @Mock
    PromozioneNegozioDAO promozioneNegozioDAO;
    @Spy
    @InjectMocks
    NegoziPromoServiceImpl service;

    @Test
    public void testFindAll() throws Exception {
        service.findAll();
        Mockito.verify(promozioneNegozioDAO).findAll();
    }

    @Test
    public void testFindById() {
        long promoShopId = 123l;
        service.findById(promoShopId);
        Mockito.verify(promozioneNegozioDAO).findById(promoShopId);
    }

    @Test
    public void testSavePromozioneNegozioEntity() {
        PromozioneNegozioEntity promozioneNegozioEntity = Mockito.mock(PromozioneNegozioEntity.class);
        service.savePromozioneNegozioEntity(promozioneNegozioEntity);
        Mockito.verify(promozioneNegozioDAO).persist(promozioneNegozioEntity);
    }

    @Test
    public void testFindByIdWithList() {
        PromozioneNegozioEntity mock1 = Mockito.mock(PromozioneNegozioEntity.class);
        PromozioneNegozioEntity mock2 = Mockito.mock(PromozioneNegozioEntity.class);
        when(mock1.getId()).thenReturn(Long.parseLong("123"));
        when(mock2.getId()).thenReturn(Long.parseLong("456"));
        List<PromozioneNegozioEntity> promozioniNegoziMock = Arrays.asList(mock1, mock2);
        List<Long> idList = Arrays.asList(mock1.getId(), mock2.getId());
        when(promozioneNegozioDAO.findById(idList)).thenReturn(promozioniNegoziMock);
        List<PromozioneNegozioEntity> promozioniNegozi = service.findById(idList);
        Mockito.verify(promozioneNegozioDAO, Mockito.times(1)).findById(idList);
        Mockito.verify(service, Mockito.times(1)).findById(idList);
        assertNotNull(promozioniNegozi);
        assertFalse(promozioniNegozi.isEmpty());
    }

}
