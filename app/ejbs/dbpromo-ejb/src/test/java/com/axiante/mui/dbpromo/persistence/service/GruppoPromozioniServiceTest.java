package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.GruppoPromozioniServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GruppoPromozioniServiceTest {
    @Mock
    GruppoPromozioneDAO gruppoPromozioneDAO;
    @Spy
    @InjectMocks
    GruppoPromozioniServiceImpl service;

    @Test
    public void testFindById() {
        Long id = 123l;
        service.findById(id);
        Mockito.verify(gruppoPromozioneDAO).findById(id);
    }
}
