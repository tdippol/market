package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepartoEntityTest {
    @Mock
    CategoriaEntity categoria;

    private RepartoEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new RepartoEntity();
        entity.setCodiceReparto("R01");
        entity.setDescrizione("REPARTO FOO");
    }

    @Test
    public void getKey_shouldReturnCodiceReparto() {
        assertEquals("R01", entity.getKey());
    }

    @Test
    public void getLabel_shouldReturnCodiceRepartoAndDescrizione() {
        assertEquals("R01 - REPARTO FOO", entity.getLabel());
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself"})
    @Test
    public void testEquals_whenSameObject_shouldReturnTrue() {
        assertTrue(entity.equals(entity));
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals_whenDifferenteObject_shouldReturnFalse() {
        ItemEntity item = mock(ItemEntity.class);
        assertFalse(entity.equals(item));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentId_shouldReturnFalse() {
        RepartoEntity other = mock(RepartoEntity.class);
        when(other.getId()).thenReturn(2L);
        entity.setId(1L);
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenSameId_shouldReturnTrue() {
        RepartoEntity other = mock(RepartoEntity.class);
        when(other.getId()).thenReturn(1L);
        entity.setId(1L);
        assertTrue(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenNullId_shouldReturnFalse() {
        RepartoEntity other = mock(RepartoEntity.class);
        entity.setId(null);
        assertFalse(entity.equals(other));
    }

    @Test
    public void addCategoria_whenCategorieNull() {
        entity.setCategorie(null);
        assertEquals(categoria, entity.addCategoria(categoria));
    }

    @Test
    public void addCategoria() {
        entity.setCategorie(new HashSet<>());
        assertEquals(categoria, entity.addCategoria(categoria));
    }

    @Test
    public void removeCategoria_whenCategorieNotNull() {
        entity.setCategorie(new HashSet<>());
        doNothing().when(categoria).setRepartoEntity(null);
        assertEquals(categoria, entity.removeCategoria(categoria));
        verify(categoria, times(1)).setRepartoEntity(null);
    }

    @Test
    public void removeCategoria_whenCategorieNull() {
        entity.setCategorie(null);
        doNothing().when(categoria).setRepartoEntity(null);
        assertEquals(categoria, entity.removeCategoria(categoria));
        verify(categoria, times(1)).setRepartoEntity(null);
    }

    @SuppressWarnings("ObviousNullCheck")
    @Test
    public void testEquals() {
        assertNotNull(entity.hashCode());
    }
}