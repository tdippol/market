package com.axiante.mui.webapp.business.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Function;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaControlliFactoryTest {

    @Mock
    Instance<PianoMediaPromoDbpromoService> pianoMediaPromoDbpromoServiceInstance;

    @Mock
    PianoMediaPromoDbpromoService pianoMediaPromoDbpromoService;

    @Mock
    PianoMediaEntity pianoMedia;

    @InjectMocks
    private PianoMediaControlliFactory factory;

    @Before
    public void setUp() throws Exception {
        when(pianoMediaPromoDbpromoServiceInstance.get()).thenReturn(pianoMediaPromoDbpromoService);
        final Date dataInizioCampagna = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        final Date dataFineCampagna = new GregorianCalendar(2023, Calendar.SEPTEMBER, 30).getTime();
        final PianoMediaPromoDbpromoEntity pianoMediaPromo = mockPianoMediaPromo(dataInizioCampagna, dataFineCampagna);
        when(pianoMediaPromoDbpromoService.findByCodicePromo("FOO")).thenReturn(pianoMediaPromo);
        when(pianoMedia.getPromoMaster()).thenReturn("FOO");
    }

    @Test
    public void chk1_dataInizioPianoMediaUgualeDataInizioCampagna() {
        // DataInizio PianificazionePianoMedia == DataInizio Campagna --> Check passa
        final Function<PianificazionePianoMediaEntity, Boolean> function = factory.getControlli("CHK1");
        Date dataInizio = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        PianificazionePianoMediaEntity ppm = mockPianificazionePianoMedia(dataInizio, null);
        assertTrue(function.apply(ppm));
        // DataInizio PianificazionePianoMedia != DataInizio Campagna --> Check fallisce
        dataInizio = new GregorianCalendar(2023, Calendar.SEPTEMBER, 20).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, null);
        assertFalse(function.apply(ppm));
        verify(pianoMediaPromoDbpromoService, times(2)).findByCodicePromo("FOO");
    }

    @Test
    public void chk2_durataMinore3gg() {
        // DataFine PianificazionePianoMedia - DataInizio PianificazionePianoMedia < 3gg --> Check passa
        final Function<PianificazionePianoMediaEntity, Boolean> function = factory.getControlli("CHK2");
        Date dataInizio = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        Date dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 12).getTime();
        PianificazionePianoMediaEntity ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertTrue(function.apply(ppm));
        // DataFine PianificazionePianoMedia - DataInizio PianificazionePianoMedia >= 3gg --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 20).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 13).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        // DataInizio null --> Check fallisce
        ppm = mockPianificazionePianoMedia(null, dataFine);
        assertFalse(function.apply(ppm));
        // DataFine null --> Check fallisce
        ppm = mockPianificazionePianoMedia(dataInizio, null);
        assertFalse(function.apply(ppm));
        // DataInizio > DataFine --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 1).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        verify(pianoMediaPromoDbpromoService, never()).findByCodicePromo(anyString());
    }

    @Test
    public void chk3_durataMaggiore5gg() {
        // DataFine PianificazionePianoMedia - DataInizio PianificazionePianoMedia > 5gg --> Check passa
        final Function<PianificazionePianoMediaEntity, Boolean> function = factory.getControlli("CHK3");
        Date dataInizio = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        Date dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 20).getTime();
        PianificazionePianoMediaEntity ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertTrue(function.apply(ppm));
        // DataFine PianificazionePianoMedia - DataInizio PianificazionePianoMedia <= 5gg --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 15).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 12).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        // DataInizio null --> Check fallisce
        ppm = mockPianificazionePianoMedia(null, dataFine);
        assertFalse(function.apply(ppm));
        // DataFine null --> Check fallisce
        ppm = mockPianificazionePianoMedia(dataInizio, null);
        assertFalse(function.apply(ppm));
        // DataInizio > DataFine --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 1).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        verify(pianoMediaPromoDbpromoService, never()).findByCodicePromo(anyString());
    }

    @Test
    public void chk4_dataFinePianoMedia1ggPrimaDataFineCampagna() {
        // DataFine Campagna - DataFine PianificazionePianoMedia == 1gg --> Check passa
        final Function<PianificazionePianoMediaEntity, Boolean> function = factory.getControlli("CHK4");
        Date dataInizio = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        Date dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 29).getTime();
        PianificazionePianoMediaEntity ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertTrue(function.apply(ppm));
        // DataFine Campagna - DataFine PianificazionePianoMedia > 1gg --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 12).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        // DataFine Campagna < DataFine PianificazionePianoMedia --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.OCTOBER, 5).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        // DataFine PianificazionePianoMedia null --> Check fallisce
        ppm = mockPianificazionePianoMedia(dataInizio, null);
        assertFalse(function.apply(ppm));
    }

    @Test
    public void chk5_durata1gg() {
        // DataFine PianificazionePianoMedia - DataInizio PianificazionePianoMedia == 1gg --> Check passa
        final Function<PianificazionePianoMediaEntity, Boolean> function = factory.getControlli("CHK5");
        Date dataInizio = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        Date dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10,23,59,59).getTime();
        PianificazionePianoMediaEntity ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertTrue(function.apply(ppm));
        // DataFine PianificazionePianoMedia - DataInizio PianificazionePianoMedia > 1gg --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 12).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 20).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        // DataInizio null --> Check fallisce
        ppm = mockPianificazionePianoMedia(null, dataFine);
        assertFalse(function.apply(ppm));
        // DataFine null --> Check fallisce
        ppm = mockPianificazionePianoMedia(dataInizio, null);
        assertFalse(function.apply(ppm));
        // DataInizio > DataFine --> Check fallisce
        dataFine = new GregorianCalendar(2023, Calendar.SEPTEMBER, 1).getTime();
        ppm = mockPianificazionePianoMedia(dataInizio, dataFine);
        assertFalse(function.apply(ppm));
        verify(pianoMediaPromoDbpromoService, never()).findByCodicePromo(anyString());
    }

    private PianificazionePianoMediaEntity mockPianificazionePianoMedia(Date dataInizio, Date dataFine) {
        final PianificazionePianoMediaEntity mock = mock(PianificazionePianoMediaEntity.class);
        when(mock.getDataInizio()).thenReturn(dataInizio);
        when(mock.getDataFine()).thenReturn(dataFine);
        when(mock.getPianoMedia()).thenReturn(pianoMedia);
        return mock;
    }

    private PianoMediaPromoDbpromoEntity mockPianoMediaPromo(Date dataInizio, Date dataFine) {
        final PianoMediaPromoDbpromoEntity mock = mock(PianoMediaPromoDbpromoEntity.class);
        when(mock.getDataInizio()).thenReturn(dataInizio);
        when(mock.getDataFine()).thenReturn(dataFine);
        return mock;
    }
}