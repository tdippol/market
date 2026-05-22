package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MuiPlanoArticoliDbPromoServiceImplTest {
    @Mock
    private MuiPlanoArticoliDbPromoDAO dao;

    @Mock
    private MuiPlanoArticoliDbPromoEntity entity1;

    @Mock
    private MuiPlanoArticoliDbPromoEntity entity2;

    @InjectMocks
    private MuiPlanoArticoliDbPromoServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<MuiPlanoArticoliDbPromoEntity> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(entities);
        when(dao.findAllByIdPlano("FOO")).thenReturn(entities);
        when(dao.findAllByIdPlano(Arrays.asList("FOO", "BAR"))).thenReturn(entities);
    }

    @Test
    public void findAll() {
        assertEquals(entities, service.findAll());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findAllByIdPlano_givenNullIdPlano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllByIdPlano((String) null);
    }

    @Test
    public void findAllByIdPlano() {
        assertEquals(entities, service.findAllByIdPlano("FOO"));
        verify(dao, times(1)).findAllByIdPlano("FOO");
    }

    @Test
    public void testFindAllByIdPlano() {
        assertEquals(entities, service.findAllByIdPlano(Arrays.asList("FOO", "BAR")));
        verify(dao, times(1)).findAllByIdPlano(Arrays.asList("FOO", "BAR"));
    }
}