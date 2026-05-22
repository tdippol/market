package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PianificazioneTotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneTotalizzatoriServiceImplTest {
    @Mock
    private PianificazioneTotalizzatoriDAO dao;

    @InjectMocks
    private PianificazioneTotalizzatoriServiceImpl service;

    @Mock
    private PianificazioneTotalizzatoriEntity totalizzatore1;

    @Mock
    private PianificazioneTotalizzatoriEntity totalizzatore2;

    private List<PianificazioneTotalizzatoriEntity> daoResult = Arrays.asList(totalizzatore1, totalizzatore2);

    @Before
    public void setUp() throws Exception {
        when(dao.findAllWithParentByIdPianificazione(42L)).thenReturn(daoResult);
    }

    @Test
    public void findAllWithParentByIdIniziativa() {
        assertEquals(daoResult, service.findAllWithParentByIdIniziativa(42L));
        verify(dao, times(1)).findAllWithParentByIdPianificazione(42L);
    }
}