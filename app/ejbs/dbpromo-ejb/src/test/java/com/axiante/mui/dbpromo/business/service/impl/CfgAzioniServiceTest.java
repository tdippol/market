package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.CfgAzioniDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.CfgAzioniServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgAzioniServiceTest {
    @Mock
    private CfgAzioniDAO dao;
    @Spy
    @InjectMocks
    private CfgAzioniServiceImpl service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFindByCodeThrowsExceptionWhenNullCodeIsPassed(){
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("code is marked non-null but is null");
        service.findByCode(null);
    }
    @Test
    public void testFindByCode(){
        String code = "some";
        service.findByCode(code);
        verify(dao).findByCodice(code);
    }
}
