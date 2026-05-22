package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDettaglioDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaPianoMediaPromoArticoliDettaglioDbPromoServiceImplTest {
    @Mock
    private PianoMediaPromoArticoliDettaglioDbPromoDAO dao;

    @Mock
    private PianoMediaPromoArticoliDettaglioDbPromoEntity entity1;

    @Mock
    private PianoMediaPromoArticoliDettaglioDbPromoEntity entity2;

    @InjectMocks
    private JpaPianoMediaPromoArticoliDettaglioDbPromoServiceImpl service;

    private List<PianoMediaPromoArticoliDettaglioDbPromoEntity> daoResult = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByCodicePromoAndItemAndTipoItem("FOO", "BAR", "BAZ"))
                .thenReturn(daoResult);
    }

    @Test
    public void findByCodicePromoAndItemAndTipoItem() {
        assertEquals(daoResult, service.findByCodicePromoAndItemAndTipoItem("FOO", "BAR", "BAZ"));
        verify(dao, times(1)).findByCodicePromoAndItemAndTipoItem("FOO", "BAR", "BAZ");
    }
}