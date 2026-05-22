package com.axiante.mui.validator.service;

import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.factory.PromotionValidatorFactory;
import com.axiante.mui.validator.model.ChannelValidator;
import com.axiante.mui.validator.model.DescriptionValidator;
import com.axiante.mui.validator.model.EndDateValidator;
import com.axiante.mui.validator.model.NoteMarketingValidator;
import com.axiante.mui.validator.model.Promotion;
import com.axiante.mui.validator.model.PromotionValidatorEnum;
import com.axiante.mui.validator.model.StartDateValidator;
import com.axiante.mui.validator.model.YearValidator;
import com.axiante.mui.validator.service.impl.PromotionValidatorServiceImpl;
import com.axiante.mui.validator.util.ReflectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

@RunWith(MockitoJUnitRunner.class)
public class PromotionValidatorServiceImplTest {

	@Mock
	private PromotionValidatorFactory factory;

	@Mock
	private CanalePromozioneDAO canalePromozioneDAO;

	@Spy
	@InjectMocks
	private final PromotionValidatorService service = new PromotionValidatorServiceImpl();

	@Spy
	@InjectMocks
	private final ChannelValidator channelValidator = new ChannelValidator();

	@Spy
	@InjectMocks
	private NoteMarketingValidator noteMarketingValidator = new NoteMarketingValidator();

	@Spy
	@InjectMocks
	private DescriptionValidator descriptionValidator = new DescriptionValidator();

	@Spy
	@InjectMocks
	private StartDateValidator startDateValidator = new StartDateValidator();

	private CreaPromozioneEntity creaPromozione;
	private GruppoPromozioneEntity gruppoPromozione;
	private int counter;
	private LocalDate start = LocalDate.now().plusDays(1);
	private CanalePromozioneEntity canalePromozioneEntity;

	@Before
	public void setUp() throws Exception {
		counter = new ReflectionUtil().findGetters(CreaPromozioneEntity.class).size() + 1;
		creaPromozione = mock(CreaPromozioneEntity.class);
		when(creaPromozione.getAnno()).thenReturn(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		when(creaPromozione.getDescrizione()).thenReturn("description");
		canalePromozioneEntity = mock(CanalePromozioneEntity.class);
		when(canalePromozioneEntity.getDescrizione()).thenReturn("canale");
		when(canalePromozioneEntity.getCodiceCanale()).thenReturn((long) 123456);
		gruppoPromozione = mock(GruppoPromozioneEntity.class);
		when(gruppoPromozione.getCodiceGruppo()).thenReturn("1");
		when(canalePromozioneEntity.getGruppoPromozioneEntity()).thenReturn(gruppoPromozione);
		when(creaPromozione.getCanalePromozioneEntity()).thenReturn(canalePromozioneEntity);
		when(creaPromozione.getDataInizio()).thenReturn(Date.from(start.atStartOfDay().toInstant(ZoneOffset.UTC)));
		when(creaPromozione.getDataFine())
				.thenReturn(Date.from(start.plusDays(2).atStartOfDay().toInstant(ZoneOffset.UTC)));
	}

	@Test
	public void testPromotionValidator() {
		CreaPromozioneEntity promotionEntity = mock(CreaPromozioneEntity.class);
		List<CanalePromozioneEntity> entities = new ArrayList<>();
		CanalePromozioneEntity channel1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity channel2 = mock(CanalePromozioneEntity.class);
		Collections.addAll(entities, channel1, channel2);
		List<String> messages = service.validateNewPromotion(null,  null, gruppoPromozione);
		assertNull(messages);
		verify(factory, never()).getPromotionValidator(any(String.class));

		messages = service.validateNewPromotion(promotionEntity,  null, gruppoPromozione);
		assertNull(messages);
		verify(factory, times(counter)).getPromotionValidator(any(String.class));
		
		when(gruppoPromozione.getCodiceGruppo()).thenReturn("1");
		when(promotionEntity.getCanalePromozioneEntity()).thenReturn(channel1);
		messages = service.validateNewPromotion(promotionEntity, entities, gruppoPromozione);
		assertNull(messages);
		verify(factory, times(counter*2)).getPromotionValidator(any(String.class));
	}
	
	@Test
	public void testPromotionValidatorPromoChannelNotAccessible() {
		CreaPromozioneEntity promotionEntity = mock(CreaPromozioneEntity.class);
		List<CanalePromozioneEntity> entities = new ArrayList<>();
		CanalePromozioneEntity channel1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity channel2 = mock(CanalePromozioneEntity.class);
		Collections.addAll(entities, channel1, channel2);
		when(channel1.getCodiceCanale()).thenReturn((long) 1);
		when(channel2.getCodiceCanale()).thenReturn((long) 2);
		when(channel1.getGruppoPromozioneEntity()).thenReturn(null);
		when(channel2.getGruppoPromozioneEntity()).thenReturn(gruppoPromozione);
		when(gruppoPromozione.getCodiceGruppo()).thenReturn("1");
		when(promotionEntity.getCanalePromozioneEntity()).thenReturn(canalePromozioneEntity);
		List<String> messages = service.validateNewPromotion(promotionEntity, entities, gruppoPromozione);
		assertNotNull(messages);
		verify(factory, times(counter)).getPromotionValidator(any(String.class));
	}

	@Test
	public void testPromotionYear() {
		int validatorCounter = 0;
		when(factory.getPromotionValidator(PromotionValidatorEnum.YEAR.getColumn())).thenReturn(new YearValidator());
		List<String> messages = service.validateNewPromotion(creaPromozione,  null, gruppoPromozione);
		assertNull(messages);
		validatorCounter = counter;
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		Calendar cal = Calendar.getInstance();
		cal.setTime(creaPromozione.getDataInizio());
		cal.add(Calendar.YEAR, -2);
		Date startDateMinus2Years = cal.getTime();
		when(creaPromozione.getDataInizio()).thenReturn(startDateMinus2Years);
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		cal.setTime(creaPromozione.getDataInizio());
		cal.add(Calendar.YEAR, 2);
		Date startDatePlus2Years = cal.getTime();
		cal.setTime(creaPromozione.getDataFine());
		cal.add(Calendar.YEAR, -2);
		Date endDateMinus2Years = cal.getTime();
		when(creaPromozione.getDataInizio()).thenReturn(startDatePlus2Years);
		when(creaPromozione.getDataFine()).thenReturn(endDateMinus2Years);
		messages = service.validateNewPromotion(creaPromozione,  null, gruppoPromozione);
		assertNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		when(creaPromozione.getDataInizio()).thenReturn(startDateMinus2Years);
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNotNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		assertFalse(messages.isEmpty());
		assertEquals(1, messages.size());
		assertEquals(MessageConsts.START_END_DATES_YEAR_NOT_VALID, messages.get(0));
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		when(creaPromozione.getDataInizio())
				.thenReturn(Date.from(LocalDate.of(currYear - 1, Calendar.JUNE, 11).atStartOfDay().toInstant(ZoneOffset.UTC)));
		when(creaPromozione.getDataFine())
				.thenReturn(Date.from(LocalDate.of(currYear - 1, Calendar.JUNE, 11).plusDays(2).atStartOfDay().toInstant(ZoneOffset.UTC)));
		when(creaPromozione.getAnno()).thenReturn(String.valueOf(currYear));
		messages = service.validateNewPromotion(creaPromozione,null, gruppoPromozione);
		assertNotNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		assertFalse(messages.isEmpty());
		assertEquals(1, messages.size());
		assertEquals(MessageConsts.START_END_DATES_YEAR_NOT_VALID, messages.get(0));
		when(creaPromozione.getAnno()).thenReturn("2022");
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNotNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		assertFalse(messages.isEmpty());
		assertEquals(1, messages.size());
	}

	@Test
	public void testPromotionEndDate() {
		int validatorCounter = 0;
		when(factory.getPromotionValidator(PromotionValidatorEnum.END_DATE.getColumn()))
				.thenReturn(new EndDateValidator());
		List<String> messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNull(messages);
		validatorCounter = counter;
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		when(creaPromozione.getDataFine()).thenReturn(Date.from(start.atStartOfDay().toInstant(ZoneOffset.UTC)));
		messages = service.validateNewPromotion(creaPromozione, null , gruppoPromozione);
		assertNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		Date inThreeMonths = Date.from(start.plusMonths(3).atStartOfDay().toInstant(ZoneOffset.UTC));
		when(creaPromozione.getDataFine()).thenReturn(inThreeMonths);
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNull(messages);
	}

	@Test
	public void testPromotionStartDate() {
		int validatorCounter = 0;
		when(factory.getPromotionValidator(PromotionValidatorEnum.START_DATE.getColumn()))
				.thenReturn(new StartDateValidator());
		List<String> messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNull(messages);
		validatorCounter = counter;
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		Calendar nextMonth = Calendar.getInstance();
		nextMonth.add(Calendar.MONTH, 1);
		when(creaPromozione.getDataInizio())
				.thenReturn(Date.from(start.plusMonths(1).atStartOfDay().toInstant(ZoneOffset.UTC)));
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
	}

	@Test
	public void testPromotionDescription() {
		int validatorCounter = 0;
		when(factory.getPromotionValidator(PromotionValidatorEnum.DESCRIPTION.getColumn()))
				.thenReturn(new DescriptionValidator());
		List<String> messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNotNull(messages);
		validatorCounter = counter;
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		assertFalse(messages.isEmpty());
		assertEquals(1, messages.size());
		assertEquals(MessageConsts.DESCRIPTION_NOT_UPPERCASE, messages.get(0));
		String descrizioneUppercase = creaPromozione.getDescrizione().toUpperCase();
		when(creaPromozione.getDescrizione()).thenReturn(descrizioneUppercase);
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		when(creaPromozione.getDescrizione()).thenReturn(createTooLongDescription(65, 90));// letter 'A' - 'Z'
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNotNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		assertFalse(messages.isEmpty());
		assertEquals(1, messages.size());
		assertEquals(String.format(MessageConsts.DESCRIPTION_TOO_LONG, 100), messages.get(0));
		when(creaPromozione.getDescrizione()).thenReturn(createTooLongDescription(97, 122));// letter 'a' - 'z'
		messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNotNull(messages);
		validatorCounter = increaseCounter(validatorCounter, counter);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		assertFalse(messages.isEmpty());
		assertEquals(1, messages.size());
	}

	@Test
	public void testPromotionChannel() {
		int validatorCounter = 0;
		when(factory.getPromotionValidator(PromotionValidatorEnum.CHANNEL.getColumn())).thenReturn(channelValidator);
		List<CanalePromozioneEntity> entities = new ArrayList<>();
		CanalePromozioneEntity channel1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity channel2 = mock(CanalePromozioneEntity.class);
		Collections.addAll(entities, channel1, channel2);
		when(canalePromozioneDAO.findAllByGroup(creaPromozione.getCanalePromozioneEntity().getGruppoPromozioneEntity()))
				.thenReturn(entities);
		validatorCounter = counter;
		List<String> messages = service.validateNewPromotion(creaPromozione, entities, null);
		assertNotNull(messages);
		assertFalse(messages.isEmpty());
		assertEquals(2, messages.size());
		assertEquals(MessageConsts.NOT_EMPTY_CHANNEL_NO_GROUP, messages.get(0));
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		entities.remove(1);
		when(canalePromozioneDAO.findAllByGroup(creaPromozione.getCanalePromozioneEntity().getGruppoPromozioneEntity()))
				.thenReturn(entities);
		validatorCounter = increaseCounter(validatorCounter, counter);
		messages = service.validateNewPromotion(creaPromozione, entities, null);
		verify(factory, times(validatorCounter)).getPromotionValidator(any(String.class));
		assertFalse(messages.isEmpty());
		assertEquals(2, messages.size());
		assertEquals(String.format("%s;%s", MessageConsts.DEFAULT_CHANNEL_NOT_SELECTED,
				MessageConsts.NOT_EMPTY_CHANNEL_NO_GROUP), messages.get(0));
	}

	@Test
	public void testPromotionChannelNull() {
		when(factory.getPromotionValidator(PromotionValidatorEnum.CHANNEL.getColumn())).thenReturn(channelValidator);
		when(creaPromozione.getCanalePromozioneEntity()).thenReturn(null);
		List<String> messages = service.validateNewPromotion(creaPromozione, null, null);
		assertNull(messages);
	}

	@Test
	public void testPromotionChannelDescriptionNotEqual() {
		CanalePromozioneEntity channel1 = mock(CanalePromozioneEntity.class);
		when(creaPromozione.getDescrizione()).thenReturn("descrizione1");
		when(channel1.getDescrizione()).thenReturn("descrizione2");
		when(creaPromozione.getCanalePromozioneEntity()).thenReturn(channel1);
		when(factory.getPromotionValidator(PromotionValidatorEnum.CHANNEL.getColumn())).thenReturn(channelValidator);
		List<String> messages = service.validateNewPromotion(creaPromozione, null, null);
		assertNotNull(messages);
		assertTrue(messages.contains(MessageConsts.NOT_EMPTY_CHANNEL_NO_GROUP));
	}

	@Test
	public void testPromotionChannelFindAllNull() {
		when(canalePromozioneDAO.findAllByGroup(creaPromozione.getCanalePromozioneEntity().getGruppoPromozioneEntity()))
				.thenReturn(null);
		when(factory.getPromotionValidator(PromotionValidatorEnum.CHANNEL.getColumn())).thenReturn(channelValidator);
		List<String> messages = service.validateNewPromotion(creaPromozione, null, null);
		assertNotNull(messages);
		assertTrue(messages.contains(MessageConsts.NOT_EMPTY_CHANNEL_NO_GROUP));
	}

	@Test
	public void testPromotionDescriptionNull() {
		when(factory.getPromotionValidator(PromotionValidatorEnum.DESCRIPTION.getColumn()))
				.thenReturn(descriptionValidator);
		when(creaPromozione.getDescrizione()).thenReturn(null);
		List<String> messages = service.validateNewPromotion(creaPromozione, null, null);
		assertNull(messages);
	}

	@Test
	public void testPromotionStartDateNull() {
		when(factory.getPromotionValidator(PromotionValidatorEnum.START_DATE.getColumn()))
				.thenReturn(startDateValidator);
		when(creaPromozione.getDataInizio()).thenReturn(null);
		List<String> messages = service.validateNewPromotion(creaPromozione, null, null);
		assertNull(messages);
	}

	private int increaseCounter(int counter, int increment) {
		return counter + increment;
	}

	private String createTooLongDescription(int leftLimit, int rightLimit) {
		return new Random().ints(leftLimit, rightLimit + 1).limit(101)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	@Test
	public void testValidateEditPromotionDataInizioValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getDataInizio()).thenReturn(Calendar.getInstance().getTime());
		when(mockTestata.getDataInizio()).thenReturn(Calendar.getInstance().getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertTrue(validazioni.isEmpty());
	}

	@Test
	public void testValidateEditPromotionDataInizioNotValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		Calendar calendar = Calendar.getInstance();
		when(mockTestata.getDataInizio()).thenReturn(calendar.getTime());
		calendar.add(Calendar.YEAR, -1);
		when(mockPromotion.getDataInizio()).thenReturn(calendar.getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertFalse(validazioni.isEmpty());
		assertEquals(1, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionDataInizioNotValidYear() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getAnno()).thenReturn("2010");
		Calendar calendar = Calendar.getInstance();
		when(mockTestata.getDataInizio()).thenReturn(calendar.getTime());
		calendar.add(Calendar.YEAR, -1);
		when(mockPromotion.getDataInizio()).thenReturn(calendar.getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertFalse(validazioni.isEmpty());
		assertEquals(1, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionDataFineValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertTrue(validazioni.isEmpty());
	}

	@Test
	public void testValidateEditPromotionDataFineNotValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		Calendar calendar = Calendar.getInstance();
		when(mockPromotion.getDataInizio()).thenReturn(calendar.getTime());
		calendar.add(Calendar.HOUR, -1);
		when(mockPromotion.getDataFine()).thenReturn(calendar.getTime());
		when(mockTestata.getDataFine()).thenReturn(Calendar.getInstance().getTime());

		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertFalse(validazioni.isEmpty());
		assertEquals(1, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionDataFineNotValidYear() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getAnno()).thenReturn("2010");
		Calendar calendar = Calendar.getInstance();
		when(mockPromotion.getDataInizio()).thenReturn(calendar.getTime());
		calendar.add(Calendar.HOUR, -1);
		when(mockPromotion.getDataFine()).thenReturn(calendar.getTime());
		when(mockTestata.getDataFine()).thenReturn(Calendar.getInstance().getTime());

		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertFalse(validazioni.isEmpty());
		assertEquals(2, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionDescrizioneValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getDescrizione()).thenReturn("");
		when(mockTestata.getDescrizione()).thenReturn("note");
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertTrue(validazioni.isEmpty());
	}

	@Test
	public void testValidateEditPromotionDescrizioneNotValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getDescrizione()).thenReturn(
				"testare100testare100testare100testare100testare100testare100testare100testare100testare100testare100Z");
		when(mockTestata.getDescrizione()).thenReturn("note");
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertFalse(validazioni.isEmpty());
		assertEquals(1, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionNoteMarketingValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getNoteMarketing()).thenReturn("");
		when(mockTestata.getNoteMarketing()).thenReturn("note");
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertTrue(validazioni.isEmpty());
	}

	@Test
	public void testValidateEditPromotionNoteMarketingNotValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getNoteMarketing()).thenReturn(
				"testare100testare100testare100testare100testare100testare100testare100testare100testare100testare100Z");
		when(mockTestata.getNoteMarketing()).thenReturn("note");
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertFalse(validazioni.isEmpty());
		assertEquals(1, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionNoteMarketingNull() {
		when(factory.getPromotionValidator(PromotionValidatorEnum.NOTE_MARKETING.getColumn()))
				.thenReturn(noteMarketingValidator);
		when(creaPromozione.getNoteMarketing()).thenReturn(null);
		List<String> messages = service.validateNewPromotion(creaPromozione, null, gruppoPromozione);
		assertNull(messages);
	}

	@Test
	public void testValidateEditPromotionPromotionNull() {
		PromozioneTestataEntity mock = mock(PromozioneTestataEntity.class);
		service.validateEditPromotion(null, mock);
		verify(service, times(1)).validateEditPromotion(null, mock);
	}

	@Test
	public void testValidateEditPromotionTestataNull() {
		Promotion mock = mock(Promotion.class);
		assertNull(service.validateEditPromotion(mock, null));
		verify(service, times(1)).validateEditPromotion(mock, null);
	}

	@Test
	public void testValidateEditPromotionBothNull() {
		assertNull(service.validateEditPromotion(null, null));
		verify(service, times(1)).validateEditPromotion(null, null);
	}

	@Test
	public void testFailValidateEndDate() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getDataFine()).thenReturn(Calendar.getInstance().getTime());
		when(mockTestata.getDataFine()).thenReturn(Calendar.getInstance().getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertTrue(validazioni.isEmpty());
	}

	@Test
	public void testValidateEditPromotionOraInizioNotValid() {
		Calendar calendar = Calendar.getInstance();
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getOraInizio()).thenReturn(calendar.getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertEquals(1, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionOraFineNotValid() {
		Calendar calendar = Calendar.getInstance();
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getOraFine()).thenReturn(calendar.getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertEquals(1, validazioni.size());
	}

	@Test
	public void testValidateEditPromotionOraInizioAndOraFineValid() {
		Calendar calendar = Calendar.getInstance();
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		when(mockPromotion.getOraInizio()).thenReturn(calendar.getTime());
		calendar.add(Calendar.HOUR, 2);
		when(mockPromotion.getOraFine()).thenReturn(calendar.getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertTrue(validazioni.isEmpty());
	}

	@Test
	public void testValidateEditPromotionDataFineBeforeTodayNotValid() {
		Promotion mockPromotion = mock(Promotion.class);
		PromozioneTestataEntity mockTestata = mock(PromozioneTestataEntity.class);
		Calendar calendar = Calendar.getInstance();
		when(mockTestata.getDataFine()).thenReturn(calendar.getTime());
		calendar.add(Calendar.MONTH, -1);
		when(mockPromotion.getDataFine()).thenReturn(calendar.getTime());
		Set<String> validazioni = service.validateEditPromotion(mockPromotion, mockTestata);
		verify(service, times(1)).validateEditPromotion(mockPromotion, mockTestata);
		assertEquals(1, validazioni.size());
	}
}
