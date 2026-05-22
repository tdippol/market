package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.AttributoArticoloDAO;
import com.axiante.mui.dbpromo.persistence.entities.AttributoArticoloEntity;
import java.util.Collections;
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
public class AttributoArticoloServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private AttributoArticoloDAO dao;

    @Mock
    private AttributoArticoloEntity attributo;

    @InjectMocks
    private AttributoArticoloServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAllActive()).thenReturn(Collections.singletonList(attributo));
    }

    @Test
    public void findAllActive() {
        List<AttributoArticoloEntity> result = service.findAllActive();
        assertEquals(1, result.size());
        verify(dao, times(1)).findAllActive();
    }
}