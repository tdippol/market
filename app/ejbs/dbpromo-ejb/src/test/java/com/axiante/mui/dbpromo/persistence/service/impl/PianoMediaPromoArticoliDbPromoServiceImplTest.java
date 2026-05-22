package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaPromoArticoliDbPromoServiceImplTest {
    @Mock
    private PianoMediaPromoArticoliDbPromoDAO dao;

    @Mock
    private PianoMediaPromoArticoliDbPromoEntity entity1;

    @Mock
    private PianoMediaPromoArticoliDbPromoEntity entity2;

    @InjectMocks
    private PianoMediaPromoArticoliDbPromoServiceImpl service;

    private List<PianoMediaPromoArticoliDbPromoEntity> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByCodicePromo("FOO")).thenReturn(entities);
        when(dao.findByCodiceItemAndCodicePromoAndTipoItem("BAR", "FOO", "BAZ"))
                .thenReturn(entity1);
    }

    @Test
    public void findByCodicePromo() {
        assertEquals(entities, service.findByCodicePromo("FOO"));
        verify(dao, times(1)).findByCodicePromo("FOO");
    }

    @Test
    public void findByCodiceItemAndCodicePromoAndTipoItem() {
        assertEquals(entity1, service.findByCodiceItemAndCodicePromoAndTipoItem("BAR", "FOO", "BAZ"));
        verify(dao, times(1))
                .findByCodiceItemAndCodicePromoAndTipoItem("BAR", "FOO", "BAZ");
    }
}