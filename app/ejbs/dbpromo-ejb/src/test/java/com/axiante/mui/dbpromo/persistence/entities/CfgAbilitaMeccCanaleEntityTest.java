package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgAbilitaMeccCanaleEntityTest {
    @Mock
    private CfgAbilitaCheckEntity controllo;

    @Mock
    private CfgSovrapposizioneMeccanicheEntity sovrapposizione;

    @Mock
    private CanalePromozioneEntity canale;

    @Mock
    private MeccanicheEntity meccanica;

    private CfgAbilitaMeccCanaleEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new CfgAbilitaMeccCanaleEntity();
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself"})
    @Test
    public void testEquals_whenSameObject_shouldReturnTrue() {
        assertTrue(entity.equals(entity));
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals_whenDifferentObjectType_shouldReturnFalse() {
        ItemEntity item = mock(ItemEntity.class);
        assertFalse(entity.equals(item));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenSameCanaleAndMeccanica_shouldReturnTrue() {
        CfgAbilitaMeccCanaleEntity other = mock(CfgAbilitaMeccCanaleEntity.class);
        when(other.getCanalePromozioneEntity()).thenReturn(canale);
        when(other.getMeccanicheEntity()).thenReturn(meccanica);
        when(canale.getId()).thenReturn(1L);
        when(meccanica.getId()).thenReturn(1L);
        entity.setCanalePromozioneEntity(canale);
        entity.setMeccanicheEntity(meccanica);
        assertTrue(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenSameCanaleAndDifferentMeccanica_shouldReturnFalse() {
        CfgAbilitaMeccCanaleEntity other = mock(CfgAbilitaMeccCanaleEntity.class);
        MeccanicheEntity meccanicaOther = mock(MeccanicheEntity.class);
        when(other.getCanalePromozioneEntity()).thenReturn(canale);
        when(other.getMeccanicheEntity()).thenReturn(meccanicaOther);
        when(canale.getId()).thenReturn(1L);
        when(meccanica.getId()).thenReturn(1L);
        when(meccanicaOther.getId()).thenReturn(2L);
        entity.setCanalePromozioneEntity(canale);
        entity.setMeccanicheEntity(meccanica);
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentCanaleAndSameMeccanica_shouldReturnFalse() {
        CfgAbilitaMeccCanaleEntity other = mock(CfgAbilitaMeccCanaleEntity.class);
        CanalePromozioneEntity canaleOther = mock(CanalePromozioneEntity.class);
        when(other.getCanalePromozioneEntity()).thenReturn(canaleOther);
        lenient().when(other.getMeccanicheEntity()).thenReturn(meccanica);
        when(canale.getId()).thenReturn(1L);
        when(canaleOther.getId()).thenReturn(2L);
        lenient().when(meccanica.getId()).thenReturn(1L);
        entity.setCanalePromozioneEntity(canale);
        entity.setMeccanicheEntity(meccanica);
        assertFalse(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_withoutCanaleAndMeccanica_shouldReturnTrue() {
        CfgAbilitaMeccCanaleEntity other = mock(CfgAbilitaMeccCanaleEntity.class);
        assertTrue(entity.equals(other));
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testEquals_whenDifferentCanaleAndMeccanica_shouldReturnFalse() {
        CfgAbilitaMeccCanaleEntity other = mock(CfgAbilitaMeccCanaleEntity.class);
        CanalePromozioneEntity canaleOther = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanicaOther = mock(MeccanicheEntity.class);
        when(other.getCanalePromozioneEntity()).thenReturn(canaleOther);
        lenient().when(other.getMeccanicheEntity()).thenReturn(meccanicaOther);
        when(canale.getId()).thenReturn(1L);
        when(canaleOther.getId()).thenReturn(2L);
        lenient().when(meccanica.getId()).thenReturn(1L);
        lenient().when(meccanicaOther.getId()).thenReturn(2L);
        entity.setCanalePromozioneEntity(canale);
        entity.setMeccanicheEntity(meccanica);
        assertFalse(entity.equals(other));
    }

    @Test
    public void addControllo() {
        doNothing().when(controllo).setMeccanicaCanaleAbilitata(entity);
        assertEquals(controllo, entity.addControllo(controllo));
        verify(controllo, times(1)).setMeccanicaCanaleAbilitata(entity);
    }

    @Test
    public void removeControllo() {
        doNothing().when(controllo).setMeccanicaCanaleAbilitata(null);
        assertEquals(controllo, entity.removeControllo(controllo));
        verify(controllo, times(1)).setMeccanicaCanaleAbilitata(null);
    }

    @Test
    public void addSovrapposizione() {
        doNothing().when(sovrapposizione).setMeccanicaCanaleAbilitata(entity);
        entity.addSovrapposizione(sovrapposizione);
        verify(sovrapposizione, times(1)).setMeccanicaCanaleAbilitata(entity);
    }

    @Test
    public void removeSovrapposizione() {
        doNothing().when(sovrapposizione).setMeccanicaCanaleAbilitata(null);
        entity.removeSovrapposizione(sovrapposizione);
        verify(sovrapposizione, times(1)).setMeccanicaCanaleAbilitata(null);
    }
}