package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.CfgSovrapposizioneMeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.CfgSovrapposizioneMeccanicheServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgSovrapposizioneMeccanicheServiceTest {
    @Mock
    private CfgSovrapposizioneMeccanicheDAO dao;

    @Spy
    @InjectMocks
    private CfgSovrapposizioneMeccanicheServiceImpl service;

    @Test
    public void testFindByCanaleMeccanica(){
        CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata = mock(CfgAbilitaMeccCanaleEntity.class);
        service.findByCanaleMeccanica(meccanicaCanaleAbilitata);
        verify(dao).findByCanaleMeccanica(meccanicaCanaleAbilitata);
    }
}
