package com.axiante.mui.validator.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PromotionValidatorEnumTest {
    @Test
    public void testPromotionValidatorEnumMethods() {
        assertEquals("anno", PromotionValidatorEnum.YEAR.getColumn());
        assertThat(PromotionValidatorEnum.YEAR.getValidator(), instanceOf(YearValidator.class));
        assertEquals("descrizione", PromotionValidatorEnum.DESCRIPTION.getColumn());
        assertThat(PromotionValidatorEnum.DESCRIPTION.getValidator(), instanceOf(DescriptionValidator.class));
        assertEquals("canalePromozioneEntity", PromotionValidatorEnum.CHANNEL.getColumn());
        assertThat(PromotionValidatorEnum.CHANNEL.getValidator(), instanceOf(ChannelValidator.class));
        assertEquals("dataInizio", PromotionValidatorEnum.START_DATE.getColumn());
        assertThat(PromotionValidatorEnum.START_DATE.getValidator(), instanceOf(StartDateValidator.class));
        assertEquals("dataFine", PromotionValidatorEnum.END_DATE.getColumn());
        assertThat(PromotionValidatorEnum.END_DATE.getValidator(), instanceOf(EndDateValidator.class));
        assertEquals("noteMarketing", PromotionValidatorEnum.NOTE_MARKETING.getColumn());
        assertThat(PromotionValidatorEnum.NOTE_MARKETING.getValidator(), instanceOf(NoteMarketingValidator.class));
    }
}
