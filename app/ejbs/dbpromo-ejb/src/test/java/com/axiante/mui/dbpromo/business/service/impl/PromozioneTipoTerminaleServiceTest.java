package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneTipoTerminaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.PromozioneTipoTerminaleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneTipoTerminaleServiceTest {
    @Mock
    private PromozioneTipoTerminaleDAO dao;
    @Spy
    @InjectMocks
    private PromozioneTipoTerminaleServiceImpl service;

    @Test
    public void testFindByIdTestataIdTerminale(){
        PromozioneTestataEntity idTestata = mock(PromozioneTestataEntity.class);
        TipoTerminaleCassaEntity idTerminale = mock(TipoTerminaleCassaEntity.class);
        service.findByIdTestataIdTerminale(idTestata, idTerminale);
        verify(dao).findByTestataAndCassa(idTestata, idTerminale);
    }
}
