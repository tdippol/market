package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromozionePianificazioneEntityTest {
    @Mock
    CfgPianificazTipoRigaEntity riga;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PromozionePianificazioneEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new PromozionePianificazioneEntity();
    }

    @Test
    public void whenAddPromozionePianificazioneEntityThenEntityInList() {
        entity.setChildren(new HashSet<>());
        int size = entity.getChildren().size();
        PromozionePianificazioneEntity promozionePianificazione = new PromozionePianificazioneEntity();

        promozionePianificazione = entity.addDetail(promozionePianificazione);
        assertThat(promozionePianificazione.getParent(), CoreMatchers.equalTo(entity));
        assertThat(entity.getChildren().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemovePromozionePianificazioneEntityThenEntityNotInList() {
        entity.setChildren(new HashSet<>());
        int size = entity.getChildren().size();
        PromozionePianificazioneEntity promozionePianificazione = new PromozionePianificazioneEntity();

        promozionePianificazione = entity.addDetail(promozionePianificazione);
        assertThat(promozionePianificazione.getParent(), CoreMatchers.equalTo(entity));
        assertThat(entity.getChildren().size(), CoreMatchers.equalTo(size + 1));

        promozionePianificazione = entity.removeDetail(promozionePianificazione);
        assertNull(promozionePianificazione.getParent());
        assertThat(entity.getChildren().size(), CoreMatchers.equalTo(size));
    }

    @Test
    public void whenAddUploadFidatyEntityThenEntityInList() {
        entity.setUploadFidaty(new HashSet<>());
        int size = entity.getUploadFidaty().size();
        UploadFidayEntity uploadFiday = new UploadFidayEntity();

        uploadFiday = entity.addUploadFidaty(uploadFiday);
        assertThat(uploadFiday.getPromozionePianificazioneEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getUploadFidaty().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemoveUploadFidatyEntityThenEntityNotInList() {
        entity.setUploadFidaty(new HashSet<>());
        int size = entity.getUploadFidaty().size();
        UploadFidayEntity uploadFiday = new UploadFidayEntity();

        uploadFiday = entity.addUploadFidaty(uploadFiday);
        assertThat(uploadFiday.getPromozionePianificazioneEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getUploadFidaty().size(), CoreMatchers.equalTo(size + 1));

        uploadFiday = entity.removeUploadFidaty(uploadFiday);
        assertNull(uploadFiday.getPromozionePianificazioneEntity());
        assertThat(entity.getUploadFidaty().size(), CoreMatchers.equalTo(size));
    }

    @Test
    public void addCastellettoMessaggi_whenNullCastelletto_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCastellettoMessaggi(null);
    }

    @Test
    public void addCastellettoMessaggi_whenCastellettiNull() {
        CastellettoMessaggiEntity castelletto = mock(CastellettoMessaggiEntity.class);
        entity.setCastellettoMessaggi(null);
        entity.addCastellettoMessaggi(castelletto);
        verify(castelletto, times(1)).setPianificazione(entity);
    }

    @Test
    public void addCastellettoMessaggi() {
        CastellettoMessaggiEntity castelletto = mock(CastellettoMessaggiEntity.class);
        entity.setCastellettoMessaggi(new HashSet<>());
        entity.addCastellettoMessaggi(castelletto);
        verify(castelletto, times(1)).setPianificazione(entity);
    }

    @Test
    public void removeCastellettoMessaggi_whenNullCastelletto_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCastellettoMessaggi(null);
    }

    @Test
    public void removeCastellettoMessaggi_whenCastellettiNull() {
        CastellettoMessaggiEntity castelletto = mock(CastellettoMessaggiEntity.class);
        entity.setCastellettoMessaggi(null);
        entity.removeCastellettoMessaggi(castelletto);
        verify(castelletto, never()).setPianificazione(null);
    }

    @Test
    public void removeCastellettoMessaggi_whenCastellettiEmpty() {
        CastellettoMessaggiEntity castelletto = mock(CastellettoMessaggiEntity.class);
        entity.setCastellettoMessaggi(new HashSet<>());
        entity.removeCastellettoMessaggi(castelletto);
        verify(castelletto, never()).setPianificazione(null);
    }

    @Test
    public void removeCastellettoMessaggi() {
        CastellettoMessaggiEntity castelletto = mock(CastellettoMessaggiEntity.class);
        entity.setCastellettoMessaggi(Stream.of(castelletto).collect(Collectors.toSet()));
        entity.removeCastellettoMessaggi(castelletto);
        verify(castelletto, times(1)).setPianificazione(null);
    }

    @Test
    public void addTotalizzatore_whenNullTotalizzatore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addTotalizzatore(null);
    }

    @Test
    public void addTotalizzatore_whenTotalizzatoriNull() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        entity.setTotalizzatori(null);
        entity.addTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setPianificazione(entity);
    }

    @Test
    public void addTotalizzatore() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        entity.setTotalizzatori(new HashSet<>());
        entity.addTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setPianificazione(entity);
    }

    @Test
    public void removeTotalizzatore_whenNullTotalizzatore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeTotalizzatore(null);
    }

    @Test
    public void removeTotalizzatore_whenTotalizzatoriNull() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        entity.setTotalizzatori(null);
        entity.removeTotalizzatore(totalizzatore);
        verify(totalizzatore, never()).setPianificazione(null);
    }

    @Test
    public void removeTotalizzatore_whenTotalizzatoriEmpty() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        entity.setTotalizzatori(new HashSet<>());
        entity.removeTotalizzatore(totalizzatore);
        verify(totalizzatore, never()).setPianificazione(null);
    }

    @Test
    public void removeTotalizzatore() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        entity.setTotalizzatori(Stream.of(totalizzatore).collect(Collectors.toSet()));
        entity.removeTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setPianificazione(null);
    }

    @Test
    public void isRafSeed_whenRigaIsNotRaggruppamento_shouldReturnFalse() {
        when(riga.getCodiceTipo()).thenReturn("S");
        entity.setTipoRiga(riga);
        assertFalse(entity.isRafSeed());
    }

    @Test
    public void isRafSeed_whenRigaIsRaggruppamentoAndInvalidNumRaggruppamento_shouldReturnFalse() {
        when(riga.getCodiceTipo()).thenReturn("R");
        entity.setNumRaggruppamento("3");
        entity.setTipoRiga(riga);
        assertFalse(entity.isRafSeed());
    }

    @Test
    public void isRafSeed_whenRigaIsRaggruppamentoAndValidNumRaggruppamentoAndNullParent_shouldReturnFalse() {
        when(riga.getCodiceTipo()).thenReturn("R");
        entity.setNumRaggruppamento("1");
        entity.setTipoRiga(riga);
        entity.setParent(null);
        assertFalse(entity.isRafSeed());
    }

    @Test
    public void isRafSeed_whenRigaIsRaggruppamentoAndValidNumRaggruppamentoAndParentIsNotSet_shouldReturnFalse() {
        PromozionePianificazioneEntity parent = mock(PromozionePianificazioneEntity.class);
        when(parent.isSet()).thenReturn(false);
        when(riga.getCodiceTipo()).thenReturn("R");
        entity.setNumRaggruppamento("2");
        entity.setTipoRiga(riga);
        entity.setParent(parent);
        assertFalse(entity.isRafSeed());
    }

    @Test
    public void isRafSeed_whenRigaIsRaggruppamentoAndValidNumRaggruppamentoAndParentIsSetWithoutChildrenMultitransazione_shouldReturnFalse() {
        PromozionePianificazioneEntity parent = mock(PromozionePianificazioneEntity.class);
        when(parent.isSet()).thenReturn(true);
        when(parent.getChildren()).thenReturn(Stream.of(entity).collect(Collectors.toSet()));
        when(riga.getCodiceTipo()).thenReturn("R");
        entity.setNumRaggruppamento("2");
        entity.setMultiTransazione("FOO");
        entity.setTipoRiga(riga);
        entity.setParent(parent);
        assertFalse(entity.isRafSeed());
    }

    @Test
    public void isRafSeed_whenRigaIsRaggruppamentoAndValidNumRaggruppamentoAndParentIsSetWithChildrenMultitransazione_shouldReturnFalse() {
        PromozionePianificazioneEntity parent = mock(PromozionePianificazioneEntity.class);
        when(parent.isSet()).thenReturn(true);
        when(parent.getChildren()).thenReturn(Stream.of(entity).collect(Collectors.toSet()));
        when(riga.getCodiceTipo()).thenReturn("R");
        entity.setNumRaggruppamento("1");
        entity.setMultiTransazione("MULTITRANSAZIONE");
        entity.setTipoRiga(riga);
        entity.setParent(parent);
        assertTrue(entity.isRafSeed());
    }

    @Test
    public void isSet_whenRigaIsSet_shouldReturnTrue() {
        when(riga.getCodiceTipo()).thenReturn("S");
        entity.setTipoRiga(riga);
        assertTrue(entity.isSet());
    }


    @Test
    public void isSet_whenRigaIsNotSet_shouldReturnFalse() {
        when(riga.getCodiceTipo()).thenReturn("R");
        entity.setTipoRiga(riga);
        assertFalse(entity.isSet());
    }

    @Test
    public void isRaggruppamento_whenRigaIsRaggruppamento_shouldReturnTrue() {
        when(riga.getCodiceTipo()).thenReturn("R");
        entity.setTipoRiga(riga);
        assertTrue(entity.isRaggruppamento());
    }

    @Test
    public void isRaggruppamento_whenRigaIsNotRaggruppamento_shouldReturnFalse() {
        when(riga.getCodiceTipo()).thenReturn("S");
        entity.setTipoRiga(riga);
        assertFalse(entity.isRaggruppamento());
    }
}
