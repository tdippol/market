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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CompratoreEntityTest {
    @Mock
    private ItemEntity item;

    @Mock
    private CheckCompratoriEntity check;

    @Mock
    private CheckPianificazioneEntity checkPianificazione;

    @Mock
    private ContributiPromoEntity contributo;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CompratoreEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new CompratoreEntity();
        entity.setItemEntities(new HashSet<>());
    }

    @Test
    public void addMuiItem() {
        doNothing().when(item).setCompratoreEntity(entity);
        assertEquals(item, entity.addMuiItem(item));
        verify(item, times(1)).setCompratoreEntity(entity);
    }

    @Test
    public void removeMuiItem() {
        Set<ItemEntity> items = Stream.of(item).collect(Collectors.toSet());
        entity.setItemEntities(items);
        doNothing().when(item).setCompratoreEntity(null);
        assertEquals(item, entity.removeMuiItem(item));
        verify(item, times(1)).setCompratoreEntity(null);
    }

    @Test
    public void addCheckCompratori_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCheckCompratori(null);
    }

    @Test
    public void addCheckCompratori_withCheckNull() {
        entity.setCheckCompratori(null);
        doNothing().when(check).setCompratoreEntity(entity);
        entity.addCheckCompratori(check);
        verify(check, times(1)).setCompratoreEntity(entity);
    }

    @Test
    public void addCheckCompratori() {
        entity.setCheckCompratori(new HashSet<>());
        doNothing().when(check).setCompratoreEntity(entity);
        entity.addCheckCompratori(check);
        verify(check, times(1)).setCompratoreEntity(entity);
    }

    @Test
    public void removeCheckCompratori_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCheckCompratori(null);
    }

    @Test
    public void removeCheckCompratori_whenChecksNull() {
        entity.setCheckCompratori(null);
        assertEquals(check, entity.removeCheckCompratori(check));
        verify(check, never()).setCompratoreEntity(null);
    }

    @Test
    public void removeCheckCompratori() {
        Set<CheckCompratoriEntity> checks = Stream.of(check).collect(Collectors.toSet());
        entity.setCheckCompratori(checks);
        assertEquals(check, entity.removeCheckCompratori(check));
        verify(check, times(1)).setCompratoreEntity(null);
    }

    @Test
    public void addCheckPianificazione_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCheckPianificazione(null);
    }

    @Test
    public void addCheckPianificazione_withCheckNull() {
        entity.setCheckPianificazioni(null);
        doNothing().when(checkPianificazione).setCompratore(entity);
        entity.addCheckPianificazione(checkPianificazione);
        verify(checkPianificazione, times(1)).setCompratore(entity);
    }

    @Test
    public void addCheckPianificazione() {
        entity.setCheckPianificazioni(new HashSet<>());
        doNothing().when(checkPianificazione).setCompratore(entity);
        entity.addCheckPianificazione(checkPianificazione);
        verify(checkPianificazione, times(1)).setCompratore(entity);
    }

    @Test
    public void removeCheckPianificazione_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCheckPianificazione(null);
    }

    @Test
    public void removeCheckPianificazione_whenChecksNull() {
        entity.setCheckPianificazioni(null);
        assertEquals(checkPianificazione, entity.removeCheckPianificazione(checkPianificazione));
        verify(checkPianificazione, never()).setCompratore(null);
    }

    @Test
    public void removeCheckPianificazione() {
        Set<CheckPianificazioneEntity> checks = Stream.of(checkPianificazione).collect(Collectors.toSet());
        entity.setCheckPianificazioni(checks);
        assertEquals(checkPianificazione, entity.removeCheckPianificazione(checkPianificazione));
        verify(checkPianificazione, times(1)).setCompratore(null);
    }

    @Test
    public void addContributo_whenNullContributo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addContributo(null);
    }

    @Test
    public void addContributo_withContributiNull() {
        entity.setContributiPromo(null);
        doNothing().when(contributo).setCompratore(entity);
        entity.addContributo(contributo);
        verify(contributo, times(1)).setCompratore(entity);
    }

    @Test
    public void addContributo() {
        entity.setContributiPromo(new HashSet<>());
        doNothing().when(contributo).setCompratore(entity);
        entity.addContributo(contributo);
        verify(contributo, times(1)).setCompratore(entity);
    }

    @Test
    public void removeContributo_whenNullContributo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeContributo(null);
    }

    @Test
    public void removeContributo_whenContributiNull() {
        entity.setContributiPromo(null);
        entity.removeContributo(contributo);
        verify(contributo, never()).setCompratore(null);
    }

    @Test
    public void removeContributo() {
        Set<ContributiPromoEntity> contributi = Stream.of(contributo).collect(Collectors.toSet());
        entity.setContributiPromo(contributi);
        entity.removeContributo(contributo);
        verify(contributo, times(1)).setCompratore(null);
    }

    @Test
    public void getFullDescription() {
        entity.setCodiceCompratore("S01");
        entity.setDescrizione("Foo");
        assertEquals("[S01] Foo", entity.getFullDescription());
    }
}