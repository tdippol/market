package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.MuiFlagDominioDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
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
public class MuiFlagDominioServiceImplTest {
    @Mock
    private MuiFlagDominioDAO dao;

    @Mock
    private MuiFlagDominioEntity entity1;

    @Mock
    private MuiFlagDominioEntity entity2;

    @InjectMocks
    private MuiFlagDominioServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<MuiFlagDominioEntity> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findAllAttiviByFlag(42L)).thenReturn(entities);
        when(dao.findAllAttiviAndDefaultByFlag(42L)).thenReturn(entities);
    }

    @Test
    public void findAllAttiviByFlag_givenNullIdFlag_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllAttiviByFlag(null);
        verify(dao, never()).findAllAttiviByFlag(any());
    }

    @Test
    public void findAllAttiviByFlag() {
        assertEquals(entities, service.findAllAttiviByFlag(42L));
        verify(dao, times(1)).findAllAttiviByFlag(42L);
    }

    @Test
    public void findAllAttiviAndDefaultByFlag_givenNullIdFlag_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllAttiviAndDefaultByFlag(null);
        verify(dao, never()).findAllAttiviAndDefaultByFlag(any());
    }

    @Test
    public void findAllAttiviAndDefaultByFlag() {
        assertEquals(entities, service.findAllAttiviAndDefaultByFlag(42L));
        verify(dao, times(1)).findAllAttiviAndDefaultByFlag(42L);
    }
}