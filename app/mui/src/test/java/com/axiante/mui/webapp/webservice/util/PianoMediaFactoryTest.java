package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.dbpromo.business.enumeration.PianoMediaStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
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
public class PianoMediaFactoryTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<ApplicationProperties> applicationPropertiesInstance;

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private Instance<CanalePromozioneService> canaleServiceInstance;

    @Mock
    private CanalePromozioneService canaleService;

    @Mock
    private Instance<StatoPromoService> statoPromoServiceInstance;

    @Mock
    private StatoPromoService statoPromoService;

    @InjectMocks
    private PianoMediaFactory factory;

    @Before
    public void setUp() throws Exception {
        when(applicationPropertiesInstance.get()).thenReturn(applicationProperties);
        when(canaleServiceInstance.get()).thenReturn(canaleService);
        when(statoPromoServiceInstance.get()).thenReturn(statoPromoService);
    }

    @Test
    public void build_whenNullCreaPianoMedia_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        factory.build(null, "junit");
        verify(applicationProperties, never()).getProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
        verify(canaleService, never()).findByCodiceCanale(anyLong());
        verify(statoPromoService, never()).findByCode(anyString());
    }

    @Test
    public void build_whenNullUsername_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 31).getTime();
        final CreaPianoMediaEntity creaPianoMedia = mockCreaPianoMedia(2023, "FOOBAR",
                dataInizio, dataFine, "MASTER", "SEC_A", "SEC_B", "SEC_C");
        factory.build(creaPianoMedia, null);
        verify(applicationProperties, never()).getProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
        verify(canaleService, never()).findByCodiceCanale(anyLong());
        verify(statoPromoService, never()).findByCode(anyString());
    }

    @Test
    public void build_whenCannotFindCanaleProps_shouldReturnNull() throws Exception {
        when(applicationProperties.getProperty(ApplicationProperties.CANALE_PIANO_MEDIA)).thenReturn(null);
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 31).getTime();
        final CreaPianoMediaEntity creaPianoMedia = mockCreaPianoMedia(2023, "FOOBAR",
                dataInizio, dataFine, "MASTER", "SEC_A", "SEC_B", "SEC_C");
        assertNull(factory.build(creaPianoMedia, "junit"));
        verify(applicationProperties, times(1)).getProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
        verify(canaleService, never()).findByCodiceCanale(anyLong());
        verify(statoPromoService, never()).findByCode(anyString());
    }

    @Test
    public void build_whenErrorFindingCanaleProps_shouldReturnNull() throws Exception {
        when(applicationProperties.getProperty(ApplicationProperties.CANALE_PIANO_MEDIA)).thenReturn("FOO");
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 31).getTime();
        final CreaPianoMediaEntity creaPianoMedia = mockCreaPianoMedia(2023, "FOOBAR",
                dataInizio, dataFine, "MASTER", "SEC_A", "SEC_B", "SEC_C");
        assertNull(factory.build(creaPianoMedia, "junit"));
        verify(applicationProperties, times(1)).getProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
        verify(canaleService, never()).findByCodiceCanale(anyLong());
        verify(statoPromoService, never()).findByCode(anyString());
    }

    @Test
    public void build_whenCannotFindCanalePromozione_shouldReturnNull() throws Exception {
        when(applicationProperties.getProperty(ApplicationProperties.CANALE_PIANO_MEDIA)).thenReturn("42");
        when(canaleService.findByCodiceCanale(42L)).thenReturn(null);
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 31).getTime();
        final CreaPianoMediaEntity creaPianoMedia = mockCreaPianoMedia(2023, "FOOBAR",
                dataInizio, dataFine, "MASTER", "SEC_A", "SEC_B", "SEC_C");
        assertNull(factory.build(creaPianoMedia, "junit"));
        verify(applicationProperties, times(1)).getProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
        verify(canaleService, times(1)).findByCodiceCanale(42L);
        verify(statoPromoService, never()).findByCode(anyString());
    }

    @Test
    public void build() throws Exception {
        final CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        final StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        when(applicationProperties.getProperty(ApplicationProperties.CANALE_PIANO_MEDIA)).thenReturn("42");
        when(canaleService.findByCodiceCanale(42L)).thenReturn(canalePromo);
        when(statoPromoService.findByCode(PianoMediaStatusEnum.PIANO_MEDIA_CREATO.getKey())).thenReturn(stato);
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 31).getTime();
        final CreaPianoMediaEntity creaPianoMedia = mockCreaPianoMedia(2023, "FOOBAR",
                dataInizio, dataFine, "MASTER", "SEC_A", "SEC_B", "SEC_C");
        final PianoMediaEntity pianoMedia = factory.build(creaPianoMedia, "junit");
        assertNotNull(pianoMedia);
        assertEquals(2023, (int) pianoMedia.getAnno());
        assertEquals("FOOBAR", pianoMedia.getDescrizione());
        assertEquals(dataInizio, pianoMedia.getDataInizio());
        assertEquals(dataFine, pianoMedia.getDataFine());
        assertEquals("MASTER", pianoMedia.getPromoMaster());
        assertEquals("SEC_A", pianoMedia.getPromoSecondaryA());
        assertEquals("SEC_B", pianoMedia.getPromoSecondaryB());
        assertEquals("SEC_C", pianoMedia.getPromoSecondaryC());
        assertEquals(canalePromo, pianoMedia.getCanale());
        final Set<PianoMediaStatoEntity> pianoMediaStati = pianoMedia.getPianoMediaStati();
        assertEquals(1, pianoMediaStati.size());
        final PianoMediaStatoEntity pianoMediaStato = pianoMediaStati.iterator().next();
        assertEquals(stato, pianoMediaStato.getStato());
        verify(applicationProperties, times(1)).getProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
        verify(canaleService, times(1)).findByCodiceCanale(42L);
        verify(statoPromoService, times(1))
                .findByCode(PianoMediaStatusEnum.PIANO_MEDIA_CREATO.getKey());
    }

    private CreaPianoMediaEntity mockCreaPianoMedia(Integer anno, String descrizione, Date dataInizio, Date dataFine,
                                                    String promoMaster, String promoSecA, String promoSecB, String promoSecC) {
        final CreaPianoMediaEntity mock = mock(CreaPianoMediaEntity.class);
        when(mock.getAnno()).thenReturn(anno);
        when(mock.getDescrizione()).thenReturn(descrizione);
        when(mock.getDataInizio()).thenReturn(dataInizio);
        when(mock.getDataFine()).thenReturn(dataFine);
        when(mock.getPromoMaster()).thenReturn(promoMaster);
        when(mock.getPromoSecondaryA()).thenReturn(promoSecA);
        when(mock.getPromoSecondaryB()).thenReturn(promoSecB);
        when(mock.getPromoSecondaryC()).thenReturn(promoSecC);
        return mock;
    }
}