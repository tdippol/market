package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CanalePromozioneEntityTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    static final CanalePromozioneEntity entity = new CanalePromozioneEntity();

    @Before
    public void init() {
        entity.setMuiCanaleLastProgs(new HashSet<CanaleLastProgEntity>());
        entity.setMuiCfgAbilitaMeccCanales(new HashSet<CfgAbilitaMeccCanaleEntity>());
        entity.setMuiCfgCanaleNegozis(new HashSet<CfgCanaleNegoziEntity>());
        entity.setMuiCfgStatiCanaleConsents(new HashSet<CfgStatiCanaleConsentEntity>());
        entity.setCfgStatiTransizioniEntities(new HashSet<CfgStatiTransizioniEntity>());
        entity.setCreaPromozioneEntities(new HashSet<CreaPromozioneEntity>());
        entity.setPromozioneTestataEntities(new HashSet<PromozioneTestataEntity>());
    }

    @Test
    public void addMuiCanaleLastProg() {
        CanaleLastProgEntity e = new CanaleLastProgEntity();
        int size = entity.getMuiCanaleLastProgs().size();
        entity.addMuiCanaleLastProg(e);
        size = entity.getMuiCanaleLastProgs().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void removeMuiCanaleLastProg() {
        CanaleLastProgEntity e = new CanaleLastProgEntity();
        int size = entity.getMuiCanaleLastProgs().size();
        e = entity.addMuiCanaleLastProg(e);
        size = entity.getMuiCanaleLastProgs().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));

        entity.removeMuiCanaleLastProg(e);
        assertThat(entity.getMuiCanaleLastProgs().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getMuiCanalePromozione());
    }

    @Test
    public void addMuiCfgAbilitaMeccCanale() {
        CfgAbilitaMeccCanaleEntity e = new CfgAbilitaMeccCanaleEntity();
        int size = entity.getMuiCfgAbilitaMeccCanales().size();
        entity.addMuiCfgAbilitaMeccCanale(e);
        size = entity.getMuiCfgAbilitaMeccCanales().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getCanalePromozioneEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void removeMuiCfgAbilitaMeccCanale() {
        CfgAbilitaMeccCanaleEntity e = new CfgAbilitaMeccCanaleEntity();
        int size = entity.getMuiCfgAbilitaMeccCanales().size();
        entity.addMuiCfgAbilitaMeccCanale(e);
        size = entity.getMuiCfgAbilitaMeccCanales().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getCanalePromozioneEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiCfgAbilitaMeccCanale(e);
        assertThat(entity.getMuiCanaleLastProgs().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getCanalePromozioneEntity());
    }

    @Test
    public void addMuiCfgCanaleNegozi() {
        CfgCanaleNegoziEntity e = new CfgCanaleNegoziEntity();
        int size = entity.getMuiCfgCanaleNegozis().size();
        entity.addMuiCfgCanaleNegozi(e);
        size = entity.getMuiCfgCanaleNegozis().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void removeMuiCfgCanaleNegozi() {
        CfgCanaleNegoziEntity e = new CfgCanaleNegoziEntity();
        int size = entity.getMuiCfgCanaleNegozis().size();
        entity.addMuiCfgCanaleNegozi(e);
        size = entity.getMuiCfgCanaleNegozis().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));

        entity.removeMuiCfgCanaleNegozi(e);
        assertThat(entity.getMuiCfgCanaleNegozis().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getMuiCanalePromozione());
    }

    @Test
    public void addMuiCfgStatiCanaleConsent() {
        CfgStatiCanaleConsentEntity e = new CfgStatiCanaleConsentEntity();
        int size = entity.getMuiCfgStatiCanaleConsents().size();
        entity.addMuiCfgStatiCanaleConsent(e);
        size = entity.getMuiCfgStatiCanaleConsents().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void removeMuiCfgStatiCanaleConsent() {
        CfgStatiCanaleConsentEntity e = new CfgStatiCanaleConsentEntity();
        int size = entity.getMuiCfgStatiCanaleConsents().size();
        entity.addMuiCfgStatiCanaleConsent(e);
        size = entity.getMuiCfgStatiCanaleConsents().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));

        entity.removeMuiCfgStatiCanaleConsent(e);
        assertThat(entity.getMuiCfgStatiCanaleConsents().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getMuiCanalePromozione());
    }

    @Test
    public void addMuiCfgStatiTransizioni() {
        CfgStatiTransizioniEntity e = new CfgStatiTransizioniEntity();
        int size = entity.getCfgStatiTransizioniEntities().size();
        entity.addMuiCfgStatiTransizioni(e);
        size = entity.getCfgStatiTransizioniEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void removeMuiCfgStatiTransizioni() {
        CfgStatiTransizioniEntity e = new CfgStatiTransizioniEntity();
        int size = entity.getCfgStatiTransizioniEntities().size();
        entity.addMuiCfgStatiTransizioni(e);
        size = entity.getCfgStatiTransizioniEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCanalePromozione(), CoreMatchers.equalTo(entity));

        entity.removeMuiCfgStatiTransizioni(e);
        assertThat(entity.getCfgStatiTransizioniEntities().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getMuiCanalePromozione());
    }

    @Test
    public void addMuiCreaPromozione() {
        CreaPromozioneEntity e = new CreaPromozioneEntity();
        int size = entity.getCreaPromozioneEntities().size();
        entity.addMuiCreaPromozione(e);
        size = entity.getCreaPromozioneEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getCanalePromozioneEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiCreaPromozione(e);
        assertThat(entity.getCreaPromozioneEntities().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getCanalePromozioneEntity());
    }

    @Test
    public void removeMuiCreaPromozione() {
        CreaPromozioneEntity e = new CreaPromozioneEntity();
        int size = entity.getCreaPromozioneEntities().size();
        entity.addMuiCreaPromozione(e);
        size = entity.getCreaPromozioneEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getCanalePromozioneEntity(), CoreMatchers.equalTo(entity));

        entity.removeMuiCreaPromozione(e);
        assertThat(entity.getCreaPromozioneEntities().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getCanalePromozioneEntity());
    }

    @Test
    public void addPromozioneTestataEntity() {
        PromozioneTestataEntity e = new PromozioneTestataEntity();
        int size = entity.getPromozioneTestataEntities().size();
        entity.addPromozioneTestataEntity(e);
        size = entity.getPromozioneTestataEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getCanalePromozioneEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void removePromozioneTestataEntity() {
        PromozioneTestataEntity e = new PromozioneTestataEntity();
        int size = entity.getPromozioneTestataEntities().size();
        entity.addPromozioneTestataEntity(e);
        size = entity.getPromozioneTestataEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getCanalePromozioneEntity(), CoreMatchers.equalTo(entity));

        entity.removePromozioneTestataEntity(e);
        assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getCanalePromozioneEntity());
    }

    @Test
    public void testGetKey() {
        entity.setId(Long.MIN_VALUE);
        String key = entity.getKey();
        assertNotNull(key);
        assertEquals(entity.getId().toString(), key);
    }

    @Test
    public void testGetLabel() {
        entity.setDescrizione("descrizione");
        String label = entity.getLabel();
        assertNotNull(label);
        assertEquals(entity.getDescrizione(), label);
    }

    @Test
    public void addStatoBlocco_whenNullStato_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addStatoBlocco(null);
    }

    @Test
    public void addStatoBlocco_whenStatiBloccoNull() {
        StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        entity.setStatiBlocco(null);
        entity.addStatoBlocco(stato);
        verify(stato, times(1)).addCanale(entity);
    }

    @Test
    public void addStatoBlocco() {
        StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        entity.setStatiBlocco(new HashSet<>());
        entity.addStatoBlocco(stato);
        verify(stato, times(1)).addCanale(entity);
    }

    @Test
    public void removeStatoBlocco_whenNullStato_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeStatoBlocco(null);
    }

    @Test
    public void removeStatoBlocco_whenStatiBloccoNull() {
        StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        entity.setStatiBlocco(null);
        entity.removeStatoBlocco(stato);
        verify(stato, never()).removeCanale(entity);
    }

    @Test
    public void removeStatoBlocco_whenStatoBloccoNotPresent() {
        StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        entity.setStatiBlocco(new HashSet<>());
        entity.removeStatoBlocco(stato);
        verify(stato, times(1)).removeCanale(entity);
    }

    @Test
    public void removeStatoBlocco() {
        StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        entity.setStatiBlocco(Stream.of(stato).collect(Collectors.toSet()));
        entity.removeStatoBlocco(stato);
        verify(stato, times(1)).removeCanale(entity);
    }

    @Test
    public void testAddMuiCfgStatiTransizioni_whenStatiNull() {
        CfgStatiTransizioniEntity stato = mock(CfgStatiTransizioniEntity.class);
        doNothing().when(stato).setMuiCanalePromozione(entity);
        entity.setCfgStatiTransizioniEntities(null);
        assertEquals(stato, entity.addMuiCfgStatiTransizioni(stato));
        verify(stato, times(1)).setMuiCanalePromozione(entity);
    }

    @Test
    public void testRemoveMuiCfgStatiTransizioni_whenStatiNull() {
        CfgStatiTransizioniEntity stato = mock(CfgStatiTransizioniEntity.class);
        doNothing().when(stato).setMuiCanalePromozione(null);
        entity.setCfgStatiTransizioniEntities(null);
        assertEquals(stato, entity.removeMuiCfgStatiTransizioni(stato));
        verify(stato, times(1)).setMuiCanalePromozione(null);
    }

    @Test
    public void addCfgCanaleFlag_whenNullFlag_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCfgCanaleFlag(null);
    }

    @Test
    public void addCfgCanaleFlag_whenFlagsNull() {
        CfgCanaleFlagEntity flag = mock(CfgCanaleFlagEntity.class);
        doNothing().when(flag).setCanale(entity);
        entity.setMuiCfgCanaleFlagEntities(null);
        entity.addCfgCanaleFlag(flag);
        verify(flag, times(1)).setCanale(entity);
    }

    @Test
    public void addCfgCanaleFlag() {
        CfgCanaleFlagEntity flag = mock(CfgCanaleFlagEntity.class);
        doNothing().when(flag).setCanale(entity);
        entity.setMuiCfgCanaleFlagEntities(new HashSet<>());
        entity.addCfgCanaleFlag(flag);
        verify(flag, times(1)).setCanale(entity);
    }

    @Test
    public void removeCfgCanaleFlag_whenNullFlag_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCfgCanaleFlag(null);
    }

    @Test
    public void removeCfgCanaleFlag_whenFlagsNull() {
        CfgCanaleFlagEntity flag = mock(CfgCanaleFlagEntity.class);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getId()).thenReturn(1L);
        when(flag.getCanale()).thenReturn(canale);
        doNothing().when(flag).setCanale(null);
        entity.setId(1L);
        entity.setMuiCfgCanaleFlagEntities(null);
        entity.removeCfgCanaleFlag(flag);
        verify(flag, times(1)).setCanale(null);
    }

    @Test
    public void removeCfgCanaleFlag_whenFlagsNotNull() {
        CfgCanaleFlagEntity flag = mock(CfgCanaleFlagEntity.class);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getId()).thenReturn(1L);
        when(flag.getCanale()).thenReturn(canale);
        doNothing().when(flag).setCanale(null);
        entity.setId(1L);
        entity.setMuiCfgCanaleFlagEntities(new HashSet<>());
        entity.removeCfgCanaleFlag(flag);
        verify(flag, times(1)).setCanale(null);
    }

    @Test
    public void removeCfgCanaleFlag_whenDifferentIds() {
        CfgCanaleFlagEntity flag = mock(CfgCanaleFlagEntity.class);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getId()).thenReturn(2L);
        when(flag.getCanale()).thenReturn(canale);
        entity.setId(1L);
        entity.setMuiCfgCanaleFlagEntities(new HashSet<>());
        entity.removeCfgCanaleFlag(flag);
        verify(flag, never()).setCanale(null);
    }

    @Test
    public void removeCfgCanaleFlag_whenIdNull() {
        CfgCanaleFlagEntity flag = mock(CfgCanaleFlagEntity.class);
        entity.setId(null);
        entity.setMuiCfgCanaleFlagEntities(new HashSet<>());
        entity.removeCfgCanaleFlag(flag);
        verify(flag, never()).setCanale(null);
    }
}
