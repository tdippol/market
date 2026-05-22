package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PrestazioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.PrestazioniEntity;
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
public class PrestazioniServiceImplTest {
    @Mock
    private PrestazioniDAO dao;

    @Mock
    private PrestazioniEntity entity1;

    @Mock
    private PrestazioniEntity entity2;

    @InjectMocks
    private PrestazioniServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<PrestazioniEntity> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByCodiceGruppo("FOO")).thenReturn(entities);
        when(dao.findDescrizioneByCodice("FOO")).thenReturn("FOOBAR");
    }

    @Test
    public void findDescrizioneByCodice_givenNullCodice_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findDescrizioneByCodice(null);
        verify(dao, never()).findDescrizioneByCodice(any());
    }

    @Test
    public void findDescrizioneByCodice() {
        assertEquals("FOOBAR", service.findDescrizioneByCodice("FOO"));
        verify(dao, times(1)).findDescrizioneByCodice("FOO");
    }

    @Test
    public void findByCodiceGruppo() {
        assertEquals(entities, service.findByCodiceGruppo("FOO"));
        verify(dao, times(1)).findByCodiceGruppo("FOO");
    }
}