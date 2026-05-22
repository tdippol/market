package com.axiante.mui.webapp.business.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaCfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaControlliService;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaHelperServiceTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<PianoMediaControlliFactory> controlliFactoryInstance;

    @Mock
    private PianoMediaControlliFactory controlliFactory;

    @Mock
    private Instance<PianoMediaControlliService> controlliServiceInstance;

    @Mock
    private PianoMediaControlliService controlliService;

    @Mock
    private SupportoMediaEntity supportoMedia;

    @Mock
    private PianificazionePianoMediaEntity pianificazioneMedia;

    @Mock
    private PianoMediaEntity pianoMedia;

    @InjectMocks
    private PianoMediaHelperService helper;

    @Before
    public void setUp() throws Exception {
        when(controlliFactoryInstance.get()).thenReturn(controlliFactory);
        when(controlliServiceInstance.get()).thenReturn(controlliService);
        when(pianificazioneMedia.getSupportoMedia()).thenReturn(supportoMedia);
        when(controlliService.findByPianificazioneMedia(pianificazioneMedia)).thenReturn(Collections.emptyList());
    }

    @Test
    public void executeChecks_givenNullPianificazioneMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.executeChecks(null, "junit");
    }

    @Test
    public void executeChecks_givenNullUsername_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.executeChecks(pianificazioneMedia, null);
    }

    @Test
    public void executeChecks_whenNoChecks_shouldNotCallFactory() {
        when(supportoMedia.getSupportoMediaChecks()).thenReturn(Collections.emptySet());
        helper.executeChecks(pianificazioneMedia, "junit");
        verify(controlliFactory, never()).getControlli(anyString());
        verify(controlliFactory, never()).getControlli(any(SupportoMediaCheckEnum.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void executeChecks() {
        final SupportoMediaCfgCheckEntity cfgChk1 = mockCfgCheck("CHK1");
        final SupportoMediaCfgCheckEntity cfgChk2 = mockCfgCheck("CHK2");
        final Function<PianificazionePianoMediaEntity, Boolean> chk1 = mock(Function.class);
        final Function<PianificazionePianoMediaEntity, Boolean> chk2 = mock(Function.class);
        when(supportoMedia.getSupportoMediaChecks()).thenReturn(Stream.of(cfgChk1, cfgChk2).collect(Collectors.toSet()));
        when(controlliFactory.getControlli("CHK1")).thenReturn(chk1);
        when(controlliFactory.getControlli("CHK2")).thenReturn(chk2);
        when(chk1.apply(pianificazioneMedia)).thenReturn(Boolean.TRUE);
        when(chk2.apply(pianificazioneMedia)).thenReturn(Boolean.FALSE);
        helper.executeChecks(pianificazioneMedia, "junit");
        verify(controlliFactory, times(1)).getControlli("CHK1");
        verify(controlliFactory, times(1)).getControlli("CHK2");
        verify(chk1, times(1)).apply(pianificazioneMedia);
        verify(chk2, times(1)).apply(pianificazioneMedia);
        verify(controlliFactory, never()).getControlli(any(SupportoMediaCheckEnum.class));
        verify(controlliService, times(1)).persist(any(PianoMediaControlliEntity.class));
    }

    @Test
    public void removeExistingChecks_givenNullPianificazioneMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.removeExistingChecks((PianificazionePianoMediaEntity) null);
    }

    @Test
    public void removeExistingChecks_whenNoChecks_shouldNotCallRemove() {
        helper.removeExistingChecks(pianificazioneMedia);
        verify(controlliService, times(1)).findByPianificazioneMedia(pianificazioneMedia);
        verify(controlliService, never()).remove(any(PianoMediaControlliEntity.class));
    }

    @Test
    public void removeExistingChecks() {
        final PianoMediaControlliEntity check1 = mock(PianoMediaControlliEntity.class);
        final PianoMediaControlliEntity check2 = mock(PianoMediaControlliEntity.class);
        when(controlliService.findByPianificazioneMedia(pianificazioneMedia))
                .thenReturn(Stream.of(check1, check2).collect(Collectors.toList()));
        helper.removeExistingChecks(pianificazioneMedia);
        verify(controlliService, times(1)).findByPianificazioneMedia(pianificazioneMedia);
        verify(controlliService, times(1)).remove(check1);
        verify(controlliService, times(1)).remove(check2);
    }

    @Test
    public void removeExistingChecks_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.removeExistingChecks((PianoMediaEntity) null);
    }

    @Test
    public void removeExistingChecksFromPianoMedia_whenNoChecks_shouldNotCallRemove() {
        helper.removeExistingChecks(pianoMedia);
        verify(controlliService, times(1)).findByPianoMedia(pianoMedia);
        verify(controlliService, never()).remove(any(PianoMediaControlliEntity.class));
    }

    @Test
    public void removeExistingChecksFromPianoMedia() {
        final PianoMediaControlliEntity check1 = mock(PianoMediaControlliEntity.class);
        final PianoMediaControlliEntity check2 = mock(PianoMediaControlliEntity.class);
        when(controlliService.findByPianoMedia(pianoMedia))
                .thenReturn(Stream.of(check1, check2).collect(Collectors.toList()));
        helper.removeExistingChecks(pianoMedia);
        verify(controlliService, times(1)).findByPianoMedia(pianoMedia);
        verify(controlliService, times(1)).remove(check1);
        verify(controlliService, times(1)).remove(check2);
    }

    private SupportoMediaCfgCheckEntity mockCfgCheck(String codice) {
        final SupportoMediaCfgCheckEntity mock = mock(SupportoMediaCfgCheckEntity.class);
        when(mock.getCodiceControllo()).thenReturn(codice);
        return mock;
    }
}