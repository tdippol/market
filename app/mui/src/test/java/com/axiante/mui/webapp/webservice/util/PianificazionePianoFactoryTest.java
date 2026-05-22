package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaService;
import com.axiante.mui.webapp.webservice.dto.CreatePianificazioneDto;
import com.axiante.mui.webapp.webservice.dto.PianificazioniPianoDto;
import com.axiante.mui.webapp.webservice.pojo.PianificazionePianoMedia;
import com.axiante.mui.webapp.webservice.pojo.SupportoMedia;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class PianificazionePianoFactoryTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<SupportoMediaService> supportoMediaServiceInstance;

    @Mock
    private SupportoMediaService supportoMediaService;

    @Mock
    private Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Mock
    private PianificazionePianoMediaService pianificazionePianoMediaService;

    @InjectMocks
    private PianificazionePianoFactory factory;

    @Mock
    private PianoMediaEntity pianoMedia;

    @Before
    public void setUp() throws Exception {
        when(supportoMediaServiceInstance.get()).thenReturn(supportoMediaService);
        when(pianificazionePianoMediaServiceInstance.get()).thenReturn(pianificazionePianoMediaService);
        // Supporti media
        final SupportoMediaEntity supportoMedia1 = mockSupportoMedia(1L, "Foo");
        final SupportoMediaEntity supportoMedia2 = mockSupportoMedia(2L, "Bar");
        final SupportoMediaEntity supportoMedia3 = mockSupportoMedia(3L, "Baz");
        List<SupportoMediaEntity> supportiMedia = Stream.of(supportoMedia1, supportoMedia2, supportoMedia3)
                .collect(Collectors.toList());
        when(supportoMediaService.findAllAttivi()).thenReturn(supportiMedia);
        // Pianificazioni media
        final Date dataInizio1 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 5).getTime();
        final Date dataFine1 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 15).getTime();
        final Date dataInizio2 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 20).getTime();
        final Date dataFine2 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 25).getTime();
        final Date dataInizio3 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        final Date dataFine3 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 22).getTime();
        final PianificazionePianoMediaEntity ppm1 = mockPianificazionePiano(1L, supportoMedia1, dataInizio1, dataFine1);
        final PianificazionePianoMediaEntity ppm2 = mockPianificazionePiano(2L, supportoMedia1, dataInizio2, dataFine2);
        final PianificazionePianoMediaEntity ppm3 = mockPianificazionePiano(3L, supportoMedia2, dataInizio3, dataFine3);
        List<PianificazionePianoMediaEntity> pianificazionePiani = Stream.of(ppm1, ppm2, ppm3)
                        .collect(Collectors.toList());
        when(pianificazionePianoMediaService.findAttiviByPianoMedia(pianoMedia)).thenReturn(pianificazionePiani);
        // BAR       10-------------22
        // BAZ
        // FOO  5---------15     20----25
    }

    @Test
    public void build_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        factory.build(null, true);
    }

    @Test
    public void build_editable() {
        final PianificazioniPianoDto dto = factory.build(pianoMedia, true);
        assertEquals("2023-09-02", dto.getStartDate());
        assertEquals(26, (long) dto.getDays());
        final List<SupportoMedia> supportiMedia = dto.getSupportiMedia();
        assertEquals(3, supportiMedia.size());
        assertEquals(2L, (long) supportiMedia.get(0).getId());
        assertEquals("Bar", supportiMedia.get(0).getName());
        assertEquals(3L, (long) supportiMedia.get(1).getId());
        assertEquals("Baz", supportiMedia.get(1).getName());
        assertEquals(1L, (long) supportiMedia.get(2).getId());
        assertEquals("Foo", supportiMedia.get(2).getName());
        final List<PianificazionePianoMedia> pianificazionePiani = dto.getPianificazioniPiano();
        assertEquals(3, pianificazionePiani.size());
        assertEquals(1L, (long) pianificazionePiani.get(0).getId());
        assertEquals(1L, (long) pianificazionePiani.get(0).getResource());
        assertEquals("2023-09-05", pianificazionePiani.get(0).getStart());
        assertEquals("2023-09-15", pianificazionePiani.get(0).getEnd());
        assertEquals("Foo #1", pianificazionePiani.get(0).getText());
        assertFalse(pianificazionePiani.get(0).getDeleteDisabled());
        assertFalse(pianificazionePiani.get(0).getMoveDisabled());
        assertFalse(pianificazionePiani.get(0).getResizeDisabled());
        assertEquals(2L, (long) pianificazionePiani.get(1).getId());
        assertEquals(1L, (long) pianificazionePiani.get(1).getResource());
        assertEquals("2023-09-20", pianificazionePiani.get(1).getStart());
        assertEquals("2023-09-25", pianificazionePiani.get(1).getEnd());
        assertEquals("Foo #2", pianificazionePiani.get(1).getText());
        assertFalse(pianificazionePiani.get(1).getDeleteDisabled());
        assertFalse(pianificazionePiani.get(1).getMoveDisabled());
        assertFalse(pianificazionePiani.get(1).getResizeDisabled());
        assertEquals(3L, (long) pianificazionePiani.get(2).getId());
        assertEquals(2L, (long) pianificazionePiani.get(2).getResource());
        assertEquals("2023-09-10", pianificazionePiani.get(2).getStart());
        assertEquals("2023-09-22", pianificazionePiani.get(2).getEnd());
        assertEquals("Bar #1", pianificazionePiani.get(2).getText());
        assertFalse(pianificazionePiani.get(2).getDeleteDisabled());
        assertFalse(pianificazionePiani.get(2).getMoveDisabled());
        assertFalse(pianificazionePiani.get(2).getResizeDisabled());
        verify(supportoMediaServiceInstance, times(1)).get();
        verify(supportoMediaService, times(1)).findAllAttivi();
        verify(pianificazionePianoMediaServiceInstance, times(1)).get();
        verify(pianificazionePianoMediaService, times(1)).findAttiviByPianoMedia(pianoMedia);
    }

    @Test
    public void build_notEditable() {
        final PianificazioniPianoDto dto = factory.build(pianoMedia, false);
        assertEquals("2023-09-02", dto.getStartDate());
        assertEquals(26, (long) dto.getDays());
        final List<SupportoMedia> supportiMedia = dto.getSupportiMedia();
        assertEquals(3, supportiMedia.size());
        assertEquals(2L, (long) supportiMedia.get(0).getId());
        assertEquals("Bar", supportiMedia.get(0).getName());
        assertEquals(3L, (long) supportiMedia.get(1).getId());
        assertEquals("Baz", supportiMedia.get(1).getName());
        assertEquals(1L, (long) supportiMedia.get(2).getId());
        assertEquals("Foo", supportiMedia.get(2).getName());
        final List<PianificazionePianoMedia> pianificazionePiani = dto.getPianificazioniPiano();
        assertEquals(3, pianificazionePiani.size());
        assertEquals(1L, (long) pianificazionePiani.get(0).getId());
        assertEquals(1L, (long) pianificazionePiani.get(0).getResource());
        assertEquals("2023-09-05", pianificazionePiani.get(0).getStart());
        assertEquals("2023-09-15", pianificazionePiani.get(0).getEnd());
        assertEquals("Foo #1", pianificazionePiani.get(0).getText());
        assertTrue(pianificazionePiani.get(0).getDeleteDisabled());
        assertTrue(pianificazionePiani.get(0).getMoveDisabled());
        assertTrue(pianificazionePiani.get(0).getResizeDisabled());
        assertEquals(2L, (long) pianificazionePiani.get(1).getId());
        assertEquals(1L, (long) pianificazionePiani.get(1).getResource());
        assertEquals("2023-09-20", pianificazionePiani.get(1).getStart());
        assertEquals("2023-09-25", pianificazionePiani.get(1).getEnd());
        assertEquals("Foo #2", pianificazionePiani.get(1).getText());
        assertTrue(pianificazionePiani.get(1).getDeleteDisabled());
        assertTrue(pianificazionePiani.get(1).getMoveDisabled());
        assertTrue(pianificazionePiani.get(1).getResizeDisabled());
        assertEquals(3L, (long) pianificazionePiani.get(2).getId());
        assertEquals(2L, (long) pianificazionePiani.get(2).getResource());
        assertEquals("2023-09-10", pianificazionePiani.get(2).getStart());
        assertEquals("2023-09-22", pianificazionePiani.get(2).getEnd());
        assertEquals("Bar #1", pianificazionePiani.get(2).getText());
        assertTrue(pianificazionePiani.get(2).getDeleteDisabled());
        assertTrue(pianificazionePiani.get(2).getMoveDisabled());
        assertTrue(pianificazionePiani.get(2).getResizeDisabled());
        verify(supportoMediaServiceInstance, times(1)).get();
        verify(supportoMediaService, times(1)).findAllAttivi();
        verify(pianificazionePianoMediaServiceInstance, times(1)).get();
        verify(pianificazionePianoMediaService, times(1)).findAttiviByPianoMedia(pianoMedia);
    }

    @Test
    public void buildPianificazionePianoMedia_givenNullPianoMedia_shoulThrowException() {
        ex.expect(NullPointerException.class);
        final CreatePianificazioneDto payload = mockCreatePianificazioneDto(1L, "2023-09-10", "2023-09-15");
        factory.buildPianificazionePianoMedia(null, payload, "junit");
        verify(supportoMediaServiceInstance, never()).get();
        verify(supportoMediaService, never()).findById(any());
    }

    @Test
    public void buildPianificazionePianoMedia_givenNullPayload_shoulThrowException() {
        ex.expect(NullPointerException.class);
        factory.buildPianificazionePianoMedia(pianoMedia, null, "junit");
        verify(supportoMediaServiceInstance, never()).get();
        verify(supportoMediaService, never()).findById(any());
    }

    @Test
    public void buildPianificazionePianoMedia_givenNullUsername_shoulThrowException() {
        ex.expect(NullPointerException.class);
        final CreatePianificazioneDto payload = mockCreatePianificazioneDto(1L, "2023-09-10", "2023-09-15");
        factory.buildPianificazionePianoMedia(pianoMedia, payload, null);
        verify(supportoMediaServiceInstance, never()).get();
        verify(supportoMediaService, never()).findById(any());
    }

    @Test
    public void buildPianificazionePianoMedia_whenSupportoMediaNotExists_shouldReturnNull() {
        when(supportoMediaService.findById(1L)).thenReturn(null);
        final CreatePianificazioneDto payload = mockCreatePianificazioneDto(1L, "2023-09-10", "2023-09-15");
        assertNull(factory.buildPianificazionePianoMedia(pianoMedia, payload, "junit"));
        verify(supportoMediaServiceInstance, times(1)).get();
        verify(supportoMediaService, times(1)).findById(1L);
    }

    @Test
    public void buildPianificazionePianoMedia_whenDatesNotValid_shouldReturnNull() {
        final SupportoMediaEntity supportoMedia = mockSupportoMedia(1L, "Foo");
        when(supportoMediaService.findById(1L)).thenReturn(supportoMedia);
        // Start date null
        CreatePianificazioneDto payload = mockCreatePianificazioneDto(1L, null, "2023-09-15");
        assertNull(factory.buildPianificazionePianoMedia(pianoMedia, payload, "junit"));
        // End date null
        payload = mockCreatePianificazioneDto(1L, "2023-09-15", null);
        assertNull(factory.buildPianificazionePianoMedia(pianoMedia, payload, "junit"));
        // Both dates null
        payload = mockCreatePianificazioneDto(1L, null, null);
        assertNull(factory.buildPianificazionePianoMedia(pianoMedia, payload, "junit"));
        // Start date greater than end date
        payload = mockCreatePianificazioneDto(1L, "2023-09-20", "2023-09-10");
        assertNull(factory.buildPianificazionePianoMedia(pianoMedia, payload, "junit"));
    }

    @Test
    public void buildPianificazionePianoMedia() {
        final SupportoMediaEntity supportoMedia = mockSupportoMedia(1L, "Foo");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        Date endDate = new GregorianCalendar(2023, Calendar.SEPTEMBER, 15).getTime();
        String start = sdf.format(startDate);
        String end = sdf.format(endDate);
        when(supportoMediaService.findById(1L)).thenReturn(supportoMedia);
        final CreatePianificazioneDto payload = mockCreatePianificazioneDto(1L, start, end);
        final PianificazionePianoMediaEntity entity = factory.buildPianificazionePianoMedia(pianoMedia, payload, "junit");
        assertEquals(pianoMedia, entity.getPianoMedia());
        assertEquals(supportoMedia, entity.getSupportoMedia());
        assertEquals("junit", entity.getCodiceUtenteInserimento());
        assertEquals(startDate, entity.getDataInizio());
        assertEquals(endDate, entity.getDataFine());
        assertNotNull(entity.getDataInserimento());
        verify(supportoMediaServiceInstance, times(1)).get();
        verify(supportoMediaService, times(1)).findById(1L);
    }

    @Test
    public void update_givenNullPianificazionePiano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        final CreatePianificazioneDto dto = mockCreatePianificazioneDto(1L, "2023-09-10", "2023-09-20");
        factory.update(null, dto);
    }

    @Test
    public void update_givenNullCreatePianificazioneDto_shouldThrowException() {
        ex.expect(NullPointerException.class);
        final SupportoMediaEntity supportoMedia = mockSupportoMedia(1L, "FOO");
        final PianificazionePianoMediaEntity pianificazionePiano = mockPianificazionePiano(1L, supportoMedia,
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 5).getTime(),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime());
        factory.update(pianificazionePiano, null);
    }

    @Test
    public void update_givenInvalidPayload_shouldReturnNull() {
        final SupportoMediaEntity supportoMedia = mockSupportoMedia(1L, "FOO");
        final PianificazionePianoMediaEntity pianificazionePiano = mockPianificazionePiano(1L, supportoMedia,
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 5).getTime(),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime());
        // Resource null
        CreatePianificazioneDto dto = mockCreatePianificazioneDto(null, "2023-09-10", "2023-09-20");
        assertFalse(factory.update(pianificazionePiano, dto));
        // Start null
        dto = mockCreatePianificazioneDto(1L, null, "2023-09-20");
        assertFalse(factory.update(pianificazionePiano, dto));
        // End null
        dto = mockCreatePianificazioneDto(1L, "2023-09-10", null);
        assertFalse(factory.update(pianificazionePiano, dto));
        // Invalid dates
        dto = mockCreatePianificazioneDto(1L, "2023-09-30", "2023-09-20");
        assertFalse(factory.update(pianificazionePiano, dto));
    }

    @Test
    public void update_whenDtoResourceNotMatchSupportoMedia_shouldReturnNull() {
        final SupportoMediaEntity supportoMedia = mockSupportoMedia(2L, "FOO");
        final PianificazionePianoMediaEntity pianificazionePiano = mockPianificazionePiano(1L, supportoMedia,
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 5).getTime(),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime());
        CreatePianificazioneDto dto = mockCreatePianificazioneDto(1L, "2023-09-10", "2023-09-20");
        assertFalse(factory.update(pianificazionePiano, dto));
    }

    @Test
    public void update() {
        final SupportoMediaEntity supportoMedia = mockSupportoMedia(1L, "FOO");
        final PianificazionePianoMediaEntity pianificazionePiano = mockPianificazionePiano(1L, supportoMedia,
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 5).getTime(),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime());
        // Resource null
        Date startDate = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        Date endDate = new GregorianCalendar(2023, Calendar.SEPTEMBER, 20).getTime();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CreatePianificazioneDto dto = mockCreatePianificazioneDto(1L, sdf.format(startDate), sdf.format(endDate));
        assertTrue(factory.update(pianificazionePiano, dto));
        verify(pianificazionePiano, times(1)).setDataInizio(startDate);
        verify(pianificazionePiano, times(1)).setDataFine(endDate);
    }

    private CreatePianificazioneDto mockCreatePianificazioneDto(Long resource, String start, String end) {
        final CreatePianificazioneDto mock = mock(CreatePianificazioneDto.class);
        when(mock.getResource()).thenReturn(resource);
        when(mock.getStart()).thenReturn(start);
        when(mock.getEnd()).thenReturn(end);
        return mock;
    }

    private SupportoMediaEntity mockSupportoMedia(Long id, String descrizione) {
        final SupportoMediaEntity mock = mock(SupportoMediaEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getDescrizione()).thenReturn(descrizione);
        when(mock.getAttivo()).thenReturn(Boolean.TRUE);
        return mock;
    }

    private PianificazionePianoMediaEntity mockPianificazionePiano(Long id, SupportoMediaEntity supportoMedia,
                                                                   Date dataInizio, Date dataFine) {
        final PianificazionePianoMediaEntity mock = mock(PianificazionePianoMediaEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getSupportoMedia()).thenReturn(supportoMedia);
        when(mock.getDataInizio()).thenReturn(dataInizio);
        when(mock.getDataFine()).thenReturn(dataFine);
        return mock;
    }
}