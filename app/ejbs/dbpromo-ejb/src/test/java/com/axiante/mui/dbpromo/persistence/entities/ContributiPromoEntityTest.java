package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContributiPromoEntityTest {
    @Mock
    private ContributiPromoArticoloEntity articolo;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    private ContributiPromoEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new ContributiPromoEntity();
    }

    @Test
    public void addArticolo_whenNullArticolo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addArticolo(null);
    }

    @Test
    public void addArticolo_whenArticoliNull() {
        doNothing().when(articolo).setContributiPromo(entity);
        entity.setArticoli(null);
        entity.addArticolo(articolo);
        verify(articolo, times(1)).setContributiPromo(entity);
    }

    @Test
    public void addArticolo() {
        doNothing().when(articolo).setContributiPromo(entity);
        entity.setArticoli(new HashSet<>());
        entity.addArticolo(articolo);
        verify(articolo, times(1)).setContributiPromo(entity);
    }

    @Test
    public void removeArticolo_whenNullArticolo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeArticolo(null);
    }

    @Test
    public void removeArticolo_whenArticoliNull() {
        entity.setArticoli(null);
        entity.removeArticolo(articolo);
        verify(articolo, never()).setContributiPromo(null);
    }

    @Test
    public void removeArticolo() {
        Set<ContributiPromoArticoloEntity> articoli = Stream.of(articolo).collect(Collectors.toSet());
        entity.setArticoli(articoli);
        entity.removeArticolo(articolo);
        verify(articolo, times(1)).setContributiPromo(null);
    }
}