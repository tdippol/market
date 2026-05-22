package com.axiante.mui.backing;

import com.axiante.mui.business.PianoMediaStatoUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaSchedulerTest {
    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Mock
    PianoMediaService pianoMediaService;

    @Mock
    PianoMediaStatoUtils pianoMediaStatoUtils;

    @Mock
    private StatoPromoService statoPromoService;

    @Spy
    @InjectMocks
    private final PianoMediaScheduler pianoMediaScheduler = new PianoMediaScheduler();

    private static StatoPromozioneEntity statoInCorso = mock(StatoPromozioneEntity.class);
    private static StatoPromozioneEntity statoChiuso = mock(StatoPromozioneEntity.class);

    @BeforeClass
    public static void beforeClass() throws Exception {
        when(statoInCorso.getCodiceStato()).thenReturn("400");
        when(statoChiuso.getCodiceStato()).thenReturn("500");
    }

    @Test
    public void whenCanRunForceOpenFindsStatusThenReturnsTrue() {
        Assert.assertTrue(pianoMediaScheduler.canRunForceOpen());
    }

    @Test
    public void whenCanRunForceOpenCannotFindStatusThenReturnsFalse() {
        when(pianoMediaScheduler.getStatoInCorso()).thenReturn(null);
        Assert.assertFalse(pianoMediaScheduler.canRunForceOpen());
    }

    @Test
    public void whenCanRunForceCloseFindsStatusThenReturnsTrue() {
        Assert.assertTrue(pianoMediaScheduler.canRunForceClose());
    }

    @Test
    public void whenCanRunForceCloseCannotFindStatusThenReturnsFalse() {
        when(pianoMediaScheduler.getStatoChiuso()).thenReturn(null);
        Assert.assertFalse(pianoMediaScheduler.canRunForceClose());
    }

    @Test
    public void testForceOpenQueriesPianoMediaService() {
        pianoMediaScheduler.forceOpen();
        verify(pianoMediaService).findPubblicatiByDataInizio();
    }

    @Test
    public void testForceCloseQueriesPianoMediaService() {
        pianoMediaScheduler.forceClose();
        verify(pianoMediaService).findOpenByDataFine();
    }

    @Test
    public void testForceOpenCallsCambiaStatoForEachPianoMedia() {
        List<PianoMediaEntity> mockedList = new ArrayList<>();
        when(pianoMediaService.findPubblicatiByDataInizio()).thenReturn(mockedList);
        pianoMediaScheduler.forceOpen();
        verify(pianoMediaStatoUtils, times(0)).cambiaStato(any(), any(), anyString(), anyBoolean());
        PianoMediaEntity mockPiano1 = mock(PianoMediaEntity.class);
        when(mockPiano1.getId()).thenReturn(1L);
        mockedList.add(mockPiano1);
        when(pianoMediaService.persistWithAuditLog(mockPiano1, PianoMediaScheduler.USER)).thenReturn(mockPiano1);
        when(pianoMediaStatoUtils.cambiaStato(mockPiano1, statoInCorso, PianoMediaScheduler.USER, false)).thenReturn(mockPiano1);
        pianoMediaScheduler.forceOpen();
        verify(pianoMediaStatoUtils, times(mockedList.size())).cambiaStato(mockPiano1, statoInCorso, PianoMediaScheduler.USER, false);
        PianoMediaEntity mockPiano2 = mock(PianoMediaEntity.class);
        when(mockPiano2.getId()).thenReturn(1L);
        mockedList.add(mockPiano2);
        when(pianoMediaService.persistWithAuditLog(mockPiano2, PianoMediaScheduler.USER)).thenReturn(mockPiano2);
        when(pianoMediaStatoUtils.cambiaStato(mockPiano2, statoInCorso, PianoMediaScheduler.USER, false)).thenReturn(mockPiano2);
        pianoMediaScheduler.forceOpen();
        verify(pianoMediaStatoUtils, times(mockedList.size())).cambiaStato(mockPiano1, statoInCorso, PianoMediaScheduler.USER, false);
    }

    @Test
    public void testForceCloseCallsCambiaStatoForEachPianoMedia() {
        List<PianoMediaEntity> mockedList = new ArrayList<>();
        when(pianoMediaService.findOpenByDataFine()).thenReturn(mockedList);
        pianoMediaScheduler.forceClose();
        verify(pianoMediaStatoUtils, times(0)).cambiaStato(any(), any(), anyString(), anyBoolean());
        PianoMediaEntity mockPiano1 = mock(PianoMediaEntity.class);
        when(mockPiano1.getId()).thenReturn(1L);
        mockedList.add(mockPiano1);
        when(pianoMediaService.persistWithAuditLog(mockPiano1, PianoMediaScheduler.USER)).thenReturn(mockPiano1);
        when(pianoMediaStatoUtils.cambiaStato(mockPiano1, statoChiuso, PianoMediaScheduler.USER, false)).thenReturn(mockPiano1);
        pianoMediaScheduler.forceClose();
        verify(pianoMediaStatoUtils, times(mockedList.size())).cambiaStato(mockPiano1, statoChiuso, PianoMediaScheduler.USER, false);
        PianoMediaEntity mockPiano2 = mock(PianoMediaEntity.class);
        when(mockPiano2.getId()).thenReturn(1L);
        mockedList.add(mockPiano2);
        when(pianoMediaService.persistWithAuditLog(mockPiano2, PianoMediaScheduler.USER)).thenReturn(mockPiano2);
        when(pianoMediaStatoUtils.cambiaStato(mockPiano2, statoChiuso, PianoMediaScheduler.USER, false)).thenReturn(mockPiano2);
        pianoMediaScheduler.forceClose();
        verify(pianoMediaStatoUtils, times(mockedList.size())).cambiaStato(mockPiano1, statoChiuso, PianoMediaScheduler.USER, false);
    }

    @Test
    public void testForceOpenSavesPianoMedia() {
        List<PianoMediaEntity> mockedList = new ArrayList<>();
        when(pianoMediaService.findPubblicatiByDataInizio()).thenReturn(mockedList);
        pianoMediaScheduler.forceOpen();
        verify(pianoMediaService, times(0)).persistWithAuditLog(any(), anyString());
        PianoMediaEntity mockPiano1 = mock(PianoMediaEntity.class);
        when(mockPiano1.getId()).thenReturn(1L);
        mockedList.add(mockPiano1);
        when(pianoMediaService.persistWithAuditLog(mockPiano1, PianoMediaScheduler.USER)).thenReturn(mockPiano1);
        when(pianoMediaStatoUtils.cambiaStato(mockPiano1, statoInCorso, PianoMediaScheduler.USER, false)).thenReturn(mockPiano1);
        pianoMediaScheduler.forceOpen();
        verify(pianoMediaService, times(mockedList.size())).persistWithAuditLog(any(), anyString());
    }

    @Test
    public void testForceCloseSavesPianoMedia() {
        List<PianoMediaEntity> mockedList = new ArrayList<>();
        when(pianoMediaService.findOpenByDataFine()).thenReturn(mockedList);
        pianoMediaScheduler.forceClose();
        verify(pianoMediaService, times(0)).persistWithAuditLog(any(), anyString());
        PianoMediaEntity mockPiano1 = mock(PianoMediaEntity.class);
        when(mockPiano1.getId()).thenReturn(1L);
        mockedList.add(mockPiano1);
        when(pianoMediaService.persistWithAuditLog(mockPiano1, PianoMediaScheduler.USER)).thenReturn(mockPiano1);
        when(pianoMediaStatoUtils.cambiaStato(mockPiano1, statoChiuso, PianoMediaScheduler.USER, false)).thenReturn(mockPiano1);
        pianoMediaScheduler.forceClose();
        verify(pianoMediaService, times(mockedList.size())).persistWithAuditLog(any(), anyString());
    }

    @Test
    public void init_withExceptionOnStato400() throws Exception {
        when(statoPromoService.findByCode("400")).thenThrow(new RuntimeException());
        pianoMediaScheduler.init();
        verify(statoPromoService, times(2)).findByCode(anyString());
    }

    @Test
    public void init_withExceptionOnStato500() throws Exception {
        when(statoPromoService.findByCode("500")).thenThrow(new RuntimeException());
        pianoMediaScheduler.init();
        verify(statoPromoService, times(2)).findByCode(anyString());
    }

    @Test
    public void forceOpen() {
        when(pianoMediaScheduler.canRunForceOpen()).thenReturn(false);
        pianoMediaScheduler.forceOpen();
        verify(pianoMediaScheduler, times(2)).canRunForceOpen();
        verifyZeroInteractions(pianoMediaService, pianoMediaStatoUtils);
    }

    @Test
    public void forceClose() {
        when(pianoMediaScheduler.canRunForceClose()).thenReturn(false);
        pianoMediaScheduler.forceClose();
        verify(pianoMediaScheduler, times(2)).canRunForceClose();
        verifyZeroInteractions(pianoMediaService, pianoMediaStatoUtils);
    }

    @Test
    public void cambiaStato_givenNullStatoPromo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        pianoMediaScheduler.cambiaStato(Collections.emptyList(), null);
        verifyZeroInteractions(pianoMediaStatoUtils);
    }
}
