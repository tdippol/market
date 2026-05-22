package com.axiante.mui.dbpromo.persistence.service;

import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.StatoPromoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatoPromoServiceTest {
    @Mock
    private StatoPromozioneDAO statoPromozioneDAO;

    @Spy
    @InjectMocks
    StatoPromoServiceImpl service;

    @Test
    public void testFindAll() throws Exception {
        service.findAll();
        verify(statoPromozioneDAO).findAll();
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 123l;
        service.findById(id);
        verify(statoPromozioneDAO).findById(id);
    }

    @Test
    public void testFindByCode() throws Exception {
        String code = "code";
        service.findByCode(code);
        verify(statoPromozioneDAO).findByCode(code);
    }

}
