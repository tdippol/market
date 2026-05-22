package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatoPromozioneEntityTest {
    @Mock
    private CanalePromozioneEntity canale;

    @Mock
    private PianoMediaPianificazioneDettaglioEntity dettaglio;

    private static StatoPromozioneEntity entity = new StatoPromozioneEntity();

    @BeforeClass
    public static void init() {
        entity.setMuiCfgStatiCanaleConsents(new HashSet<>());
        entity.setMuiCfgStatiTransizionis1(new HashSet<>());
        entity.setMuiCfgStatiTransizionis2(new HashSet<>());
        entity.setPromozioneStatoEntities(new HashSet<>());
        entity.setPromoStatiConsentitiEntities(new HashSet<>());
        entity.setPromoStatiTransizioneEntities1(new HashSet<>());
        entity.setPromoStatiTransizioneEntities2(new HashSet<>());
        entity.setPromoPubblicazioneTestataEntities(new HashSet<>());
    }

    @Test
    public void whenAddMuiCfgStatiCanaleConsentThenEntityInList() {
        CfgStatiCanaleConsentEntity e = new CfgStatiCanaleConsentEntity();
        int size = entity.getMuiCfgStatiCanaleConsents().size();
        entity.addMuiCfgStatiCanaleConsent(e);
        size = entity.getMuiCfgStatiCanaleConsents().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozioneEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveMuiCfgStatiCanaleConsentThenEntityNotInList() {
        CfgStatiCanaleConsentEntity e = new CfgStatiCanaleConsentEntity();
        e.setId(42L);
        int size = entity.getMuiCfgStatiCanaleConsents().size();
        entity.addMuiCfgStatiCanaleConsent(e);
        size = entity.getMuiCfgStatiCanaleConsents().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozioneEntity(), CoreMatchers.equalTo(entity));
        entity.removeMuiCfgStatiCanaleConsent(e);
        assertNull(e.getStatoPromozioneEntity());
    }

    @Test
    public void whenAddMuiCfgStatiTransizioniThenEntityInList() {
        CfgStatiTransizioniEntity e = new CfgStatiTransizioniEntity();
        int size = entity.getMuiCfgStatiTransizionis1().size();
        entity.addMuiCfgStatiTransizionis1(e);
        size = entity.getMuiCfgStatiTransizionis1().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoTransizione(), CoreMatchers.equalTo(entity));

        e = new CfgStatiTransizioniEntity();
        size = entity.getMuiCfgStatiTransizionis2().size();
        entity.addMuiCfgStatiTransizionis2(e);
        size = entity.getMuiCfgStatiTransizionis2().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozione(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveMuiCfgStatiTransizionisThenEntityNotInList() {
        CfgStatiTransizioniEntity e = new CfgStatiTransizioniEntity();
        int size = entity.getMuiCfgStatiTransizionis1().size();
        entity.addMuiCfgStatiTransizionis1(e);
        size = entity.getMuiCfgStatiTransizionis1().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoTransizione(), CoreMatchers.equalTo(entity));
        entity.removeMuiCfgStatiTransizionis1(e);
        assertNull(e.getStatoTransizione());

        e = new CfgStatiTransizioniEntity();
        size = entity.getMuiCfgStatiTransizionis2().size();
        entity.addMuiCfgStatiTransizionis2(e);
        size = entity.getMuiCfgStatiTransizionis2().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozione(), CoreMatchers.equalTo(entity));
        entity.removeMuiCfgStatiTransizionis2(e);
        assertNull(e.getStatoPromozione());
    }

    @Test
    public void whenAddPromozioneStatoEntityThenEntityInList() {
        PromozioneStatoEntity e = new PromozioneStatoEntity();
        int size = entity.getPromozioneStatoEntities().size();
        entity.addPromozioneStatoEntity(e);
        size = entity.getPromozioneStatoEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozioneEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromozioneStatoEntityThenEntityNotInList() {
        PromozioneStatoEntity e = new PromozioneStatoEntity();
        int size = entity.getPromozioneStatoEntities().size();
        entity.addPromozioneStatoEntity(e);
        size = entity.getPromozioneStatoEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozioneEntity(), CoreMatchers.equalTo(entity));
        entity.removePromozioneStatoEntity(e);
        assertNull(e.getStatoPromozioneEntity());
    }



    @Test
    public void whenRemovePromoStatiConsentitiEntityThenEntityNotInList() {
        PromoStatiConsentitiEntity e = new PromoStatiConsentitiEntity();
        int size = entity.getPromoStatiConsentitiEntities().size();
        entity.addPromoStatiConsentitiEntity(e);
        size = entity.getPromoStatiConsentitiEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozioneEntity(), CoreMatchers.equalTo(entity));
        entity.removePromoStatiConsentitiEntity(e);
        assertNull(e.getStatoPromozioneEntity());
    }

    @Test
    public void whenAddPromoStatiTransizioneEntitiesThenEntityInList() {
        PromoStatiTransizioneEntity e = new PromoStatiTransizioneEntity();
        int size = entity.getPromoStatiTransizioneEntities1().size();
        entity.addPromoStatiTransizioneEntities1(e);
        size = entity.getPromoStatiTransizioneEntities1().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoTransizione(), CoreMatchers.equalTo(entity));

        e = new PromoStatiTransizioneEntity();
        size = entity.getPromoStatiTransizioneEntities2().size();
        entity.addPromoStatiTransizioneEntities2(e);
        size = entity.getPromoStatiTransizioneEntities2().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozione(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromoStatiTransizioneEntitiesThenEntityNotInList() {
        PromoStatiTransizioneEntity e = new PromoStatiTransizioneEntity();
        int size = entity.getPromoStatiTransizioneEntities1().size();
        entity.addPromoStatiTransizioneEntities1(e);
        size = entity.getPromoStatiTransizioneEntities1().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoTransizione(), CoreMatchers.equalTo(entity));
        entity.removePromoStatiTransizioneEntities1(e);
        assertNull(e.getStatoTransizione());

        e = new PromoStatiTransizioneEntity();
        size = entity.getPromoStatiTransizioneEntities2().size();
        entity.addPromoStatiTransizioneEntities2(e);
        size = entity.getPromoStatiTransizioneEntities2().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozione(), CoreMatchers.equalTo(entity));
        entity.removePromoStatiTransizioneEntities2(e);
        assertNull(e.getStatoPromozione());
    }

    @Test
    public void whenAddPromoPubblicazioneTestataEntityThenEntityInList() {
        PromoPubblicazioneTestataEntity e = new PromoPubblicazioneTestataEntity();
        int size = entity.getPromoPubblicazioneTestataEntities().size();
        entity.addPromoPubblicazioneTestataEntity(e);
        size = entity.getPromoPubblicazioneTestataEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozioneEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromoPubblicazioneTestataEntityThenEntityNotInList() {
        PromoPubblicazioneTestataEntity e = new PromoPubblicazioneTestataEntity();
        int size = entity.getPromoPubblicazioneTestataEntities().size();
        entity.addPromoPubblicazioneTestataEntity(e);
        size = entity.getPromoPubblicazioneTestataEntities().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getStatoPromozioneEntity(), CoreMatchers.equalTo(entity));
        entity.removePromoPubblicazioneTestataEntity(e);
        assertNull(e.getStatoPromozioneEntity());
    }

    @Test
    public void addPianoMediaDettaglio_whenDettagliNull() {
        doNothing().when(dettaglio).setStato(entity);
        entity.setPianoMediaPianificazioneDettaglioEntities(null);
        assertEquals(dettaglio, entity.addPianoMediaDettaglio(dettaglio));
        verify(dettaglio, times(1)).setStato(entity);
    }

    @Test
    public void addPianoMediaDettaglio() {
        doNothing().when(dettaglio).setStato(entity);
        entity.setPianoMediaPianificazioneDettaglioEntities(new HashSet<>());
        assertEquals(dettaglio, entity.addPianoMediaDettaglio(dettaglio));
        verify(dettaglio, times(1)).setStato(entity);
    }

    @Test
    public void removePianoMediaDettaglio_whenDettagliNull() {
        entity.setPianoMediaPianificazioneDettaglioEntities(null);
        assertEquals(dettaglio, entity.removePianoMediaDettaglio(dettaglio));
        verify(dettaglio, never()).setStato(null);
    }

    @Test
    public void removePianoMediaDettaglio_whenStatoDettaglioNull() {
        entity.setPianoMediaPianificazioneDettaglioEntities(new HashSet<>());
        when(dettaglio.getStato()).thenReturn(null);
        assertEquals(dettaglio, entity.removePianoMediaDettaglio(dettaglio));
        verify(dettaglio, never()).setStato(null);
    }

    @Test
    public void removePianoMediaDettaglio_whenStatoDettaglioIsDifferent() {
        StatoPromozioneEntity stato1 = mock(StatoPromozioneEntity.class);
        entity.setPianoMediaPianificazioneDettaglioEntities(new HashSet<>());
        when(dettaglio.getStato()).thenReturn(stato1);
        assertEquals(dettaglio, entity.removePianoMediaDettaglio(dettaglio));
        verify(dettaglio, never()).setStato(null);
    }

    @Test
    public void removePianoMediaDettaglio_whenStatoDettaglioNotPresent() {
        entity.setPianoMediaPianificazioneDettaglioEntities(new HashSet<>());
        when(dettaglio.getStato()).thenReturn(entity);
        assertEquals(dettaglio, entity.removePianoMediaDettaglio(dettaglio));
        verify(dettaglio, never()).setStato(null);
    }

    @Test
    public void removePianoMediaDettaglio() {
        entity.setPianoMediaPianificazioneDettaglioEntities(Stream.of(dettaglio).collect(Collectors.toSet()));
        when(dettaglio.getStato()).thenReturn(entity);
        assertEquals(dettaglio, entity.removePianoMediaDettaglio(dettaglio));
        verify(dettaglio, times(1)).setStato(null);
    }

    @Test
    public void addCanale_whenCanaliNull() {
        entity.setCanali(null);
        assertEquals(canale, entity.addCanale(canale));
    }

    @Test
    public void addCanale() {
        entity.setCanali(new HashSet<>());
        assertEquals(canale, entity.addCanale(canale));
    }

    @Test
    public void removeCanale_whenCanaliNull() {
        entity.setCanali(null);
        assertEquals(canale, entity.removeCanale(canale));
    }

    @Test
    public void removeCanale() {
        entity.setCanali(Stream.of(canale).collect(Collectors.toSet()));
        assertEquals(canale, entity.removeCanale(canale));
    }

    @Test
    public void fullDescription() {
        entity.setCodiceStato("10");
        entity.setDescrizione("STATO FOO");
        assertEquals("10 - STATO FOO", entity.fullDescription());
    }
}