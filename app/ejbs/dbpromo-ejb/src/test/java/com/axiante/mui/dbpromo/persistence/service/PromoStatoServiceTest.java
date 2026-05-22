package com.axiante.mui.dbpromo.persistence.service;

import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneStatoDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.PromoStatoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoStatoServiceTest {

    @Mock
    private PromozioneStatoDAO promozioneStatoDAO;

    @Spy
    @InjectMocks
    PromoStatoServiceImpl service;

    @Test
    public void testFindAll() throws Exception {
        service.findAll();
        verify(promozioneStatoDAO).findAll();
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 123l;
        service.findById(id);
        verify(promozioneStatoDAO).findById(id);
    }

    @Test
    public void testFindByPromozioneID() throws Exception {
        Long promoId = 123l;
        service.findByPromozioneID(promoId);
        verify(promozioneStatoDAO).findByPromozioneID(promoId);
    }

}
