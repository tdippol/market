package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.TotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.TotalizzatoriServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TotalizzatoriServiceTest {
    @Mock
    TotalizzatoriDAO dao;

    @Spy
    @InjectMocks
    private TotalizzatoriServiceImpl service;

    @Test
    public void testFindAllWithParentByIdIniziativa(){
        Long idIniziativa = 1L;
        service.findAllWithParentByIdIniziativa(idIniziativa);
        verify(dao).findAllWithParentByIdIniziativa(idIniziativa);
    }
}
