package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaCheckDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.CfgAbilitaCheckServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgAbilitaCheckServiceTest {
    @Mock
    private CfgAbilitaCheckDAO dao;
    @Spy
    @InjectMocks
    private CfgAbilitaCheckServiceImpl service;

    @Test
    public void testFindByCanaleMeccanica(){
        CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata = mock(CfgAbilitaMeccCanaleEntity.class);
        service.findByCanaleMeccanica(meccanicaCanaleAbilitata);
        verify(dao).findByCanaleMeccanica(meccanicaCanaleAbilitata);
    }
}
