package com.axiante.mui.webapp.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.PianoMediaSecurityService;
import java.util.List;
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
public class PianoMediaSecurityUtilsTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<PianoMediaSecurityService> pianoMediaSecurityServiceInstance;

    @Mock
    private PianoMediaSecurityService pianoMediaSecurityService;

    @Mock
    private UsersEntity user;

    @Mock
    private PianoMediaSecurityEntity security1;

    @Mock
    private PianoMediaSecurityEntity security2;

    @InjectMocks
    private PianoMediaSecurityUtils utils;

    @Before
    public void setUp() throws Exception {
        final List<PianoMediaSecurityEntity> securities = Stream.of(security1, security2).collect(Collectors.toList());
        when(pianoMediaSecurityServiceInstance.get()).thenReturn(pianoMediaSecurityService);
        when(pianoMediaSecurityService.findByUser(user)).thenReturn(securities);
    }

    @Test
    public void canWriteScheda_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.canWriteScheda(null);
        verifyZeroInteractions(pianoMediaSecurityServiceInstance, pianoMediaSecurityService, security1, security2);
    }

    @Test
    public void canWriteScheda_whenAtLeastOneGroupFromUserHasWriteFlag_shouldReturnTrue() throws Exception {
        lenient().when(security1.getAccessoScheda()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security2.getAccessoScheda()).thenReturn(PianificazioneSecurityEnum.READ);
        assertTrue(utils.canWriteScheda(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoScheda();
        verify(security2, atMost(1)).getAccessoScheda();
    }

    @Test
    public void canWriteScheda_whenNoGroupFromUserHasWriteFlag_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoScheda()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getAccessoScheda()).thenReturn(PianificazioneSecurityEnum.READ);
        assertFalse(utils.canWriteScheda(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoScheda();
        verify(security2, atMost(1)).getAccessoScheda();
    }

    @Test
    public void canViewAllItems_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.canViewAllItems(null);
        verifyZeroInteractions(pianoMediaSecurityServiceInstance, pianoMediaSecurityService, security1, security2);
    }

    @Test
    public void canViewAllItems_whenAtLeastOneGroupFromUserHasFiltroArticoliFalse_shouldReturnTrue() throws Exception {
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.FALSE);
        assertTrue(utils.canViewAllItems(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canViewAllItems_whenAllGroupFromUserHasFiltroArticoliTrue_shouldReturnFalse() throws Exception {
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        assertFalse(utils.canViewAllItems(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canViewOwnItemsOnly_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.canViewOwnItemsOnly(null);
        verifyZeroInteractions(pianoMediaSecurityServiceInstance, pianoMediaSecurityService, security1, security2);
    }

    @Test
    public void canViewOwnItemsOnly_whenAllGroupFromUserHasFiltroArticoliTrue_shouldReturnTrue() throws Exception {
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        assertTrue(utils.canViewOwnItemsOnly(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canViewOwnItemsOnly_whenAtLeastOneGroupFromUserHasFiltroArticoliFalse_shouldReturnFalse() throws Exception {
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canViewOwnItemsOnly(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canAddAllItems_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.canAddAllItems(null);
        verifyZeroInteractions(pianoMediaSecurityServiceInstance, pianoMediaSecurityService, security1, security2);
    }

    @Test
    public void canAddAllItems_whenAtLeastOneGroupFromUserHasAccessoPianificazioneWriteAndFiltroArticoliFalse_shouldReturnTrue() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.FALSE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        assertTrue(utils.canAddAllItems(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canAddAllItems_whenNoGroupFromUserHasAccessoPianificazioneWriteAndFiltroArticoliFalse_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canAddAllItems(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canAddOwnItemsOnly_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.canAddOwnItemsOnly(null);
        verifyZeroInteractions(pianoMediaSecurityServiceInstance, pianoMediaSecurityService, security1, security2);
    }

    @Test
    public void canAddOwnItemsOnly_whenAllGroupFromUserWithAccessoPianificazioneWriteHaveFiltroArticoliTrue_shouldReturnTrue() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        assertTrue(utils.canAddOwnItemsOnly(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canAddOwnItemsOnly_whenAtLeastOneGroupFromUserWithAccessoPianificazioneWriteHasFiltroArticoliFalse_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canAddOwnItemsOnly(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canAddOwnItemsOnly_whenNoGroupFromUserWithAccessoPianificazioneWrite_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getFiltroArticoli()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getFiltroArticoli()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canAddOwnItemsOnly(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getFiltroArticoli();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getFiltroArticoli();
    }

    @Test
    public void canWritePianificazione_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.canWritePianificazione(null);
        verifyZeroInteractions(pianoMediaSecurityServiceInstance, pianoMediaSecurityService, security1, security2);
    }

    @Test
    public void canWritePianificazione_whenAtLeastOneGroupFromUserHasAccessoPianificazioneWrite_shouldReturnTrue() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        assertTrue(utils.canWritePianificazione(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getAccessoPianificazione();
    }

    @Test
    public void canWritePianificazione_whenNoGroupFromUserHasAccessoPianificazioneWrite_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        assertFalse(utils.canWritePianificazione(user));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getAccessoPianificazione();
    }

    @Test
    public void canWritePianificazioneNoteOnly_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.canWritePianificazioneNoteOnly(null, true);
        verifyZeroInteractions(pianoMediaSecurityServiceInstance, pianoMediaSecurityService, security1, security2);
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAllGroupFromUserHasAccessoPianificazioneReadAndAtLeastOnePianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOn_shouldReturnTrue() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canWritePianificazioneNoteOnly(user, true));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getPianificazioneCompratore();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getPianificazioneCompratore();
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAllGroupFromUserHasAccessoPianificazioneReadAndAtLeastOnePianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOff_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        // se la modalita' compratore e' off il compratore non scrive mai
        assertFalse(utils.canWritePianificazioneNoteOnly(user, false));
        verify(pianoMediaSecurityServiceInstance, times(0)).get();
        verify(pianoMediaSecurityService, times(0)).findByUser(user);
        verify(security1, atMost(0)).getAccessoPianificazione();
        verify(security1, atMost(0)).getPianificazioneCompratore();
        verify(security2, atMost(0)).getAccessoPianificazione();
        verify(security2, atMost(0)).getPianificazioneCompratore();
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAllGroupFromUserHasAccessoPianificazioneReadAndNoPianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOn_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canWritePianificazioneNoteOnly(user, true));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getPianificazioneCompratore();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getPianificazioneCompratore();
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAllGroupFromUserHasAccessoPianificazioneReadAndNoPianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOff_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canWritePianificazioneNoteOnly(user, false));
        verify(pianoMediaSecurityServiceInstance, times(0)).get();
        verify(pianoMediaSecurityService, times(0)).findByUser(user);
        verify(security1, atMost(0)).getAccessoPianificazione();
        verify(security1, atMost(0)).getPianificazioneCompratore();
        verify(security2, atMost(0)).getAccessoPianificazione();
        verify(security2, atMost(0)).getPianificazioneCompratore();
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAtLeastOneGroupFromUserHasAccessoPianificazioneWriteAndAtLeastOnePianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOn_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canWritePianificazioneNoteOnly(user, true));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getPianificazioneCompratore();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getPianificazioneCompratore();
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAtLeastOneGroupFromUserHasAccessoPianificazioneWriteAndAtLeastOnePianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOff_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        // se la modalita' compratore e' off il compratore non scrive mai
        assertFalse(utils.canWritePianificazioneNoteOnly(user, false));
        verify(pianoMediaSecurityServiceInstance, times(0)).get();
        verify(pianoMediaSecurityService, times(0)).findByUser(user);
        verify(security1, atMost(0)).getAccessoPianificazione();
        verify(security1, atMost(0)).getPianificazioneCompratore();
        verify(security2, atMost(0)).getAccessoPianificazione();
        verify(security2, atMost(0)).getPianificazioneCompratore();
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAtLeastOneGroupFromUserHasAccessoPianificazioneWriteAndNoPianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOn_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        assertFalse(utils.canWritePianificazioneNoteOnly(user, true));
        verify(pianoMediaSecurityServiceInstance, times(1)).get();
        verify(pianoMediaSecurityService, times(1)).findByUser(user);
        verify(security1, atMost(1)).getAccessoPianificazione();
        verify(security1, atMost(1)).getPianificazioneCompratore();
        verify(security2, atMost(1)).getAccessoPianificazione();
        verify(security2, atMost(1)).getPianificazioneCompratore();
    }

    @Test
    public void canWritePianificazioneNoteOnly_whenAtLeastOneGroupFromUserHasAccessoPianificazioneWriteAndNoPianificazioneCompratoreHasFlagTrueAndModalitaCompratoreOff_shouldReturnFalse() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        // se la modalita' compratore e' off il compratore non scrive mai
        assertFalse(utils.canWritePianificazioneNoteOnly(user, false));
        verify(pianoMediaSecurityServiceInstance, times(0)).get();
        verify(pianoMediaSecurityService, times(0)).findByUser(user);
        verify(security1, atMost(0)).getAccessoPianificazione();
        verify(security1, atMost(0)).getPianificazioneCompratore();
        verify(security2, atMost(0)).getAccessoPianificazione();
        verify(security2, atMost(0)).getPianificazioneCompratore();
    }

    @Test
    public void getPianificazioneMediaWriteLevel_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.getPianificazioneMediaWriteLevel(null, true);
    }

    @Test
    public void getPianificazioneMediaWriteLevel_canWritePianificazioneNoteOnly_shouldReturnNOTES() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        // per poter scrivere un compratore deve avere accesso in scrittura
        assertEquals(PianificazioneMediaWriteLevelEnum.NONE, utils.getPianificazioneMediaWriteLevel(user, true));
        // il metodo getWriteLevel testa prima se ha accesso solo alle note, poi se ha accesso a tutto quindi il metodo viene chiamato 2 volte
        verify(pianoMediaSecurityServiceInstance, times(2)).get();
        verify(pianoMediaSecurityService, times(2)).findByUser(user);
        verify(security1, atMost(3)).getAccessoPianificazione();
        verify(security1, atMost(2)).getPianificazioneCompratore();
        verify(security2, atMost(3)).getAccessoPianificazione();
        verify(security2, atMost(2)).getPianificazioneCompratore();
    }

    @Test
    public void getPianificazioneMediaWriteLevel_whenCannotWritePianificazioneNoteOnlyButCanWritePianificazione_shouldReturnALL() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.TRUE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.WRITE);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        assertEquals(PianificazioneMediaWriteLevelEnum.ALL, utils.getPianificazioneMediaWriteLevel(user, true));
        verify(pianoMediaSecurityServiceInstance, atLeastOnce()).get();
        verify(pianoMediaSecurityService, atLeastOnce()).findByUser(user);
        verify(security1, atMost(2)).getAccessoPianificazione();
        verify(security1, atMost(1)).getPianificazioneCompratore();
        verify(security2, atMost(2)).getAccessoPianificazione();
        verify(security2, atMost(1)).getPianificazioneCompratore();
    }

    @Test
    public void getPianificazioneMediaWriteLevel_whenCannotWritePianificazioneNoteOnlyAndCannotWritePianificazione_shouldReturnNONE() throws Exception {
        lenient().when(security1.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security1.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        lenient().when(security2.getAccessoPianificazione()).thenReturn(PianificazioneSecurityEnum.READ);
        lenient().when(security2.getPianificazioneCompratore()).thenReturn(Boolean.FALSE);
        assertEquals(PianificazioneMediaWriteLevelEnum.NONE, utils.getPianificazioneMediaWriteLevel(user, true));
        verify(pianoMediaSecurityServiceInstance, atLeastOnce()).get();
        verify(pianoMediaSecurityService, atLeastOnce()).findByUser(user);
        verify(security1, atMost(3)).getAccessoPianificazione();
        verify(security1, atMost(1)).getPianificazioneCompratore();
        verify(security2, atMost(3)).getAccessoPianificazione();
        verify(security2, atMost(1)).getPianificazioneCompratore();
    }
}