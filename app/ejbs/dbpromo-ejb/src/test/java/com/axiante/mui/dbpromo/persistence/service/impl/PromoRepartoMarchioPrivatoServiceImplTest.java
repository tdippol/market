package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PromoRepartoMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoRepartoMarchioPrivatoServiceImplTest {
    @Mock
    private PromoRepartoMarchioPrivatoDAO dao;

    @Mock
    private PromoRepartoMarchioPrivato repartoMarchioPrivato1;

    @Mock
    private PromoRepartoMarchioPrivato repartoMarchioPrivato2;

    @InjectMocks
    private PromoRepartoMarchioPrivatoServiceImpl service;

    private List<PromoRepartoMarchioPrivato> daoResult = Arrays.asList(repartoMarchioPrivato1, repartoMarchioPrivato2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByIdPromozione(42L)).thenReturn(daoResult);
    }

    @Test
    public void findByIdPromozione() {
        assertEquals(daoResult, service.findByIdPromozione(42L));
        verify(dao, times(1)).findByIdPromozione(42L);
    }
}