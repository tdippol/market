package com.axiante.mui.webapp.utils.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CumulabilitaSecurityTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @InjectMocks
    private CumulabilitaSecurity securityUtil;

    @Test
    public void canPublishCumulabilita_givenNullCumulabilita_shouldThrowException() {
        ex.expect(NullPointerException.class);
        securityUtil.canPublishCumulabilita(null);
    }

    @Test
    public void canPublishCumulabilita_givenCumulabilitaNotPublishedAndNotCancelled_shouldReturnTrue() {
        final CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(null, null);
        assertTrue(securityUtil.canPublishCumulabilita(cumulabilita));
    }

    @Test
    public void canPublishCumulabilita_givenCumulabilitaPublishedOrCancelledOrBoth_shouldReturnFalse() {
        final Date data = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(data, null);
        assertFalse(securityUtil.canPublishCumulabilita(cumulabilita));
        cumulabilita = mockCumulabilita(null, data);
        assertFalse(securityUtil.canPublishCumulabilita(cumulabilita));
        cumulabilita = mockCumulabilita(data, data);
        assertFalse(securityUtil.canPublishCumulabilita(cumulabilita));
    }

    @Test
    public void canCancelCumulabilita_givenNullCumulabilita_shouldThrowException() {
        ex.expect(NullPointerException.class);
        securityUtil.canCancelCumulabilita(null);
    }

    @Test
    public void canCancelCumulabilita_givenCumulabilitaNotCancelled_shouldReturnTrue() {
        final CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(null, null);
        assertTrue(securityUtil.canCancelCumulabilita(cumulabilita));
    }

    @Test
    public void canCancelCumulabilita_givenCumulabilitaCancelled_shouldReturnFalse() {
        final Date data = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(null, data);
        assertFalse(securityUtil.canCancelCumulabilita(cumulabilita));
    }

    @Test
    public void canDeleteBuono_givenNullCumulabilita_shouldThrowException() {
        ex.expect(NullPointerException.class);
        securityUtil.canDeleteBuono(null);
    }

    @Test
    public void canDeleteBuono_givenCumulabilitaNotPublishedAndNotCancelled_shouldReturnTrue() {
        final CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(null, null);
        assertTrue(securityUtil.canDeleteBuono(cumulabilita));
    }

    @Test
    public void canDeleteBuono_givenCumulabilitaPublishedOrCancelledOrBoth_shouldReturnFalse() {
        final Date data = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(data, null);
        assertFalse(securityUtil.canDeleteBuono(cumulabilita));
        cumulabilita = mockCumulabilita(null, data);
        assertFalse(securityUtil.canDeleteBuono(cumulabilita));
        cumulabilita = mockCumulabilita(data, data);
        assertFalse(securityUtil.canDeleteBuono(cumulabilita));
    }

    @Test
    public void canAddBuono_givenNullCumulabilita_shouldThrowException() {
        ex.expect(NullPointerException.class);
        securityUtil.canAddBuono(null);
    }

    @Test
    public void canAddBuono_givenCumulabilitaNotPublishedAndNotCancelled_shouldReturnTrue() {
        final CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(null, null);
        assertTrue(securityUtil.canAddBuono(cumulabilita));
    }

    @Test
    public void canAddBuono_givenCumulabilitaPublishedOrCancelledOrBoth_shouldReturnFalse() {
        final Date data = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(data, null);
        assertFalse(securityUtil.canAddBuono(cumulabilita));
        cumulabilita = mockCumulabilita(null, data);
        assertFalse(securityUtil.canAddBuono(cumulabilita));
        cumulabilita = mockCumulabilita(data, data);
        assertFalse(securityUtil.canAddBuono(cumulabilita));
    }

    private CumulabilitaEsclusivitaEntity mockCumulabilita(Date dataPubblicazione, Date dataAnnullamento) {
        final CumulabilitaEsclusivitaEntity mock = mock(CumulabilitaEsclusivitaEntity.class);
        when(mock.getDataPubblicazione()).thenReturn(dataPubblicazione);
        when(mock.getDataAnnullamento()).thenReturn(dataAnnullamento);
        return mock;
    }
}