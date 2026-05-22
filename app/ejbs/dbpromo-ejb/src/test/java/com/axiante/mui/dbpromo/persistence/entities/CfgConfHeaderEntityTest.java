package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CfgConfHeaderEntityTest {

	static final CfgConfHeaderEntity entity= new CfgConfHeaderEntity();
	
	@Before
	public void init() {
		entity.setId((long) 1);
		entity.setMuiCfgPianificaziones(new HashSet<CfgPianificazioneEntity>());
		entity.setMeccanicaEntity(new MeccanicheEntity());
		entity.setLivelloPianificazione(new CfgLivelloPianificazioneEntity());
		entity.setTipiElemento(new HashSet<CfgTipoElementoEntity>());
		entity.setMinSet(1);
		entity.setMaxSet(5);
		entity.setMinRaggruppamento(1);
		entity.setMaxRaggruppamento(5);
		entity.setMuiCfgSetPianificazione(new CfgSetPianificazioneEntity());
		entity.setUnicaInPromo(1);
	}
	
	@Test
    public void addTipoElemento() {
		CfgTipoElementoEntity e = new CfgTipoElementoEntity();
        int size = entity.getTipiElemento().size();
        entity.addTipoElemento(e);
        size = entity.getTipiElemento().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getConfHeader(), CoreMatchers.equalTo(entity));
    }
	
	 @Test(expected = NullPointerException.class)
	    public void addTipoElemento_givenNullValue_shouldThrowException() {
	        entity.addTipoElemento(null);
	    }
		
	@Test
    public void removeTipoElemento() {
		CfgTipoElementoEntity e = new CfgTipoElementoEntity();
		int size = entity.getTipiElemento().size();
        e = entity.addTipoElemento(e);
        size = entity.getTipiElemento().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getConfHeader(), CoreMatchers.equalTo(entity));

        entity.removeTipoElemento(e);
        assertThat(entity.getTipiElemento().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getConfHeader());
    }
	
	@Test(expected = NullPointerException.class)
    public void removeTipoElemento_givenNullValue_shouldThrowException() {
        entity.removeTipoElemento(null);
    }
	
	@Test
    public void addPianificazione() {
		CfgPianificazioneEntity e = new CfgPianificazioneEntity();
        int size = entity.getMuiCfgPianificaziones().size();
        entity.addPianificazione(e);
        size = entity.getMuiCfgPianificaziones().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCfgConfHeader(), CoreMatchers.equalTo(entity));
    }
	
	@Test(expected = NullPointerException.class)
    public void addPianificazione_givenNullValue_shouldThrowException() {
        entity.addPianificazione(null);
    }
	
	@Test
    public void removePianificazione() {
		CfgPianificazioneEntity e = new CfgPianificazioneEntity();
        int size = entity.getMuiCfgPianificaziones().size();
        e = entity.addPianificazione(e);
        size = entity.getMuiCfgPianificaziones().size() - size;
        assertThat(size, CoreMatchers.equalTo(1));
        assertThat(e.getMuiCfgConfHeader(), CoreMatchers.equalTo(entity));

        entity.removePianificazione(e);
        assertThat(entity.getMuiCfgPianificaziones().size(), CoreMatchers.equalTo(size - 1));
        assertNull(e.getMuiCfgConfHeader());
    }
	
	@Test(expected = NullPointerException.class)
    public void removePianificazione_givenNullValue_shouldThrowException() {
        entity.removePianificazione(null);
    }

    @Test
    public void testAddTipoElemento_whenTipiElementoNull() {
        final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
        entity.setTipiElemento(null);
        assertThat(entity.addTipoElemento(tipoElemento), CoreMatchers.equalTo(tipoElemento));
        verify(tipoElemento, times(1)).setConfHeader(entity);
    }

    @Test
    public void testRemoveTipoElemento_whenTipiElementoNull() {
        final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
        entity.setTipiElemento(null);
        assertThat(entity.removeTipoElemento(tipoElemento), CoreMatchers.equalTo(tipoElemento));
        verify(tipoElemento, never()).setConfHeader(null);
    }

    @Test
    public void testRemoveTipoElemento_whenTipiElementoInList() {
        final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
        entity.setTipiElemento(Stream.of(tipoElemento).collect(Collectors.toSet()));
        assertThat(entity.removeTipoElemento(tipoElemento), CoreMatchers.equalTo(tipoElemento));
        verify(tipoElemento, times(1)).setConfHeader(null);
    }

    @Test
    public void testRemoveTipoElemento_whenTipiElementoNotInList() {
        final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
        entity.setTipiElemento(new HashSet<>());
        assertThat(entity.removeTipoElemento(tipoElemento), CoreMatchers.equalTo(tipoElemento));
        verify(tipoElemento, never()).setConfHeader(null);
    }

    @Test
    public void testAddPianificazione_whenCfgPianificazioniNull() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        entity.setMuiCfgPianificaziones(null);
        assertThat(entity.addPianificazione(cfgPianificazione), CoreMatchers.equalTo(cfgPianificazione));
        verify(cfgPianificazione, times(1)).setMuiCfgConfHeader(entity);
    }

    @Test
    public void testRemovePianificazione_whenCfgPianificazioniNull() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        entity.setMuiCfgPianificaziones(null);
        assertThat(entity.removePianificazione(cfgPianificazione), CoreMatchers.equalTo(cfgPianificazione));
        verify(cfgPianificazione, never()).setMuiCfgConfHeader(null);
    }

    @Test
    public void testRemovePianificazione_whenCfgPianificazioneInList() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        entity.setMuiCfgPianificaziones(Stream.of(cfgPianificazione).collect(Collectors.toSet()));
        assertThat(entity.removePianificazione(cfgPianificazione), CoreMatchers.equalTo(cfgPianificazione));
        verify(cfgPianificazione, times(1)).setMuiCfgConfHeader(null);
    }

    @Test
    public void testRemovePianificazione_whenCfgPianificazioneNotInList() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        entity.setMuiCfgPianificaziones(new HashSet<>());
        assertThat(entity.removePianificazione(cfgPianificazione), CoreMatchers.equalTo(cfgPianificazione));
        verify(cfgPianificazione, never()).setMuiCfgConfHeader(null);
    }
}
