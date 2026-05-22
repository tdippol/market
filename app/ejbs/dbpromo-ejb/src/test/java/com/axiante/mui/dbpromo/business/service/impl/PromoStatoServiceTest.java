package com.axiante.mui.dbpromo.business.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.StatoPromoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoStatoServiceTest {
    @Mock
    private StatoPromozioneDAO dao;
    @Spy
    @InjectMocks
    StatoPromoServiceImpl service;

    @Test
    public void testFindAll() throws Exception {
        service.findAll();
        Mockito.verify(dao).findAll();
    }

    @Test
    public void findByCode() throws Exception {
        String code = "some";
        service.findByCode(code);
        Mockito.verify(dao).findByCode(code);
    }

    @Test
    public void findById() throws Exception {
        Long id = 123l;
        service.findById(id);
        Mockito.verify(dao).findById(id);
    }
}
