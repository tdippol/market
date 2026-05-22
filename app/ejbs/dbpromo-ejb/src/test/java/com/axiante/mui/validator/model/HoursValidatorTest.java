package com.axiante.mui.validator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import com.axiante.mui.validator.constants.MessageConsts;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HoursValidatorTest {

    @Mock
    private Promotion promotion;

    @Spy
    @InjectMocks
    private HoursValidator validator;

    @Test
    public void bothOraInizioAndOraFine_shouldBeSettedOrNone() {
        Date oraInizio = new GregorianCalendar(2021, Calendar.JANUARY, 1, 9, 0)
                .getTime();
        Date oraFine = new GregorianCalendar(2021, Calendar.JANUARY, 1, 18, 0)
                .getTime();
        // Case 1 - solo oraInizio
        when(promotion.getOraInizio()).thenReturn(oraInizio);
        when(promotion.getOraFine()).thenReturn(null);
        String message = validator.validate(promotion);
        assertEquals(MessageConsts.START_HOUR_WITHOUT_END, message);
        // Case 2 - solo oraFine
        when(promotion.getOraInizio()).thenReturn(null);
        when(promotion.getOraFine()).thenReturn(oraFine);
        message = validator.validate(promotion);
        assertEquals(MessageConsts.END_HOUR_WITHOUT_START, message);
        // Case 3 - entrambe settate
        when(promotion.getOraInizio()).thenReturn(oraInizio);
        when(promotion.getOraFine()).thenReturn(oraFine);
        message = validator.validate(promotion);
        assertNull(message);
        // Case 3 - entrambe NULL
        when(promotion.getOraInizio()).thenReturn(oraInizio);
        when(promotion.getOraFine()).thenReturn(oraFine);
        message = validator.validate(promotion);
        assertNull(message);
    }

    @Test
    public void oraInizioAfterOraFine_shouldReturnErrorMessage() {
        Date oraInizio = new GregorianCalendar(2021, Calendar.JANUARY, 1, 9, 0)
                .getTime();
        Date oraFineAfter = new GregorianCalendar(2021, Calendar.JANUARY, 1, 18, 0)
                .getTime();
        Date oraFineBefore = new GregorianCalendar(2021, Calendar.JANUARY, 1, 8, 0)
                .getTime();
        Date oraFineEqual = new GregorianCalendar(2021, Calendar.JANUARY, 1, 9, 0)
                .getTime();
        // Case 1 - oraInizio > oraFine
        when(promotion.getOraInizio()).thenReturn(oraInizio);
        when(promotion.getOraFine()).thenReturn(oraFineBefore);
        String message = validator.validate(promotion);
        assertEquals(MessageConsts.END_HOUR_BEFORE_START, message);
        // Case 2 - oraInizio == oraFine
        when(promotion.getOraInizio()).thenReturn(oraInizio);
        when(promotion.getOraFine()).thenReturn(oraFineEqual);
        message = validator.validate(promotion);
        assertEquals(MessageConsts.END_HOUR_BEFORE_START, message);
        // Case 3 - promotion oraInizio < oraFine
        when(promotion.getOraInizio()).thenReturn(oraInizio);
        when(promotion.getOraFine()).thenReturn(oraFineAfter);
        message = validator.validate(promotion);
        assertNull(message);
    }
}