package com.axiante.mui.validator.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.validator.model.ChannelValidator;
import com.axiante.mui.validator.model.DescriptionValidator;
import com.axiante.mui.validator.model.EndDateValidator;
import com.axiante.mui.validator.model.NoteMarketingValidator;
import com.axiante.mui.validator.model.PromotionValidator;
import com.axiante.mui.validator.model.PromotionValidatorEnum;
import com.axiante.mui.validator.model.StartDateValidator;
import com.axiante.mui.validator.model.YearValidator;
import com.axiante.mui.validator.util.PromotionValidatorLiteral;
import javax.enterprise.inject.Instance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromotionValidatorFactoryTest {

    @Mock
    Instance<PromotionValidator> promotionValidators;

    @Mock
    Instance<PromotionValidator> yearValidator;

    @Mock
    Instance<PromotionValidator> startDateValidator;

    @Mock
    Instance<PromotionValidator> endDateValidator;

    @Mock
    Instance<PromotionValidator> descriptionValidator;

    @Mock
    Instance<PromotionValidator> channelValidator;

    @Mock
    Instance<PromotionValidator> noteMarketingValidator;

    @Spy
    @InjectMocks
    private final PromotionValidatorFactory factory =  new PromotionValidatorFactory();

    @Test
    public void testPromotionNullValidatorFactory() {
        PromotionValidator validator = factory.getPromotionValidator(null);
        verify(factory, times(1)).getPromotionValidator(null);
        assertNull(validator);
    }

    @Test
    public void testPromotionWrongValidatorFactory() {
        PromotionValidator validator = factory.getPromotionValidator("test");
        verify(factory, times(1)).getPromotionValidator("test");
        assertNull(validator);
    }

    @Test
    public void testYearValidator() {
        YearValidator mock = mock(YearValidator.class);
        when(promotionValidators.select(new PromotionValidatorLiteral(PromotionValidatorEnum.YEAR.getValidatorModel()))).thenReturn(yearValidator);
        when(yearValidator.get()).thenReturn(mock);
        PromotionValidator validator = factory.getPromotionValidator(PromotionValidatorEnum.YEAR.getColumn());
        verify(factory, times(1)).getPromotionValidator(PromotionValidatorEnum.YEAR.getColumn());
        assertNotNull(validator);
        assertEquals(yearValidator.get(), validator);
    }

    @Test
    public void testStartDateValidator() {
        StartDateValidator mock = mock(StartDateValidator.class);
        when(promotionValidators.select(new PromotionValidatorLiteral(PromotionValidatorEnum.START_DATE.getValidatorModel()))).thenReturn(startDateValidator);
        when(startDateValidator.get()).thenReturn(mock);
        PromotionValidator validator = factory.getPromotionValidator(PromotionValidatorEnum.START_DATE.getColumn());
        assertNotNull(validator);
        assertEquals(startDateValidator.get(), validator);
    }

    @Test
    public void testEndDateValidator() {
        EndDateValidator mock = mock(EndDateValidator.class);
        when(promotionValidators.select(new PromotionValidatorLiteral(PromotionValidatorEnum.END_DATE.getValidatorModel()))).thenReturn(endDateValidator);
        when(endDateValidator.get()).thenReturn(mock);
        PromotionValidator validator = factory.getPromotionValidator(PromotionValidatorEnum.END_DATE.getColumn());
        assertNotNull(validator);
        assertEquals(endDateValidator.get(), validator);
    }

    @Test
    public void testDescriptionValidator() {
        DescriptionValidator mock = mock(DescriptionValidator.class);
        when(promotionValidators.select(new PromotionValidatorLiteral(PromotionValidatorEnum.DESCRIPTION.getValidatorModel()))).thenReturn(descriptionValidator);
        when(descriptionValidator.get()).thenReturn(mock);
        PromotionValidator validator = factory.getPromotionValidator(PromotionValidatorEnum.DESCRIPTION.getColumn());
        assertNotNull(validator);
        assertEquals(descriptionValidator.get(), validator);
    }

    @Test
    public void testNoteMarketingValidator() {
        NoteMarketingValidator mock = mock(NoteMarketingValidator.class);
        when(promotionValidators.select(new PromotionValidatorLiteral(PromotionValidatorEnum.NOTE_MARKETING.getValidatorModel()))).thenReturn(noteMarketingValidator);
        when(noteMarketingValidator.get()).thenReturn(mock);
        PromotionValidator validator = factory.getPromotionValidator(PromotionValidatorEnum.NOTE_MARKETING.getColumn());
        assertNotNull(validator);
        assertEquals(noteMarketingValidator.get(), validator);
    }

    @Test
    public void testChannelValidator() {
        ChannelValidator mock = mock(ChannelValidator.class);
        when(promotionValidators.select(new PromotionValidatorLiteral(PromotionValidatorEnum.CHANNEL.getValidatorModel()))).thenReturn(channelValidator);
        when(channelValidator.get()).thenReturn(mock);
        PromotionValidator validator = factory.getPromotionValidator(PromotionValidatorEnum.CHANNEL.getColumn());
        assertNotNull(validator);
        assertEquals(channelValidator.get(), validator);
    }

}
