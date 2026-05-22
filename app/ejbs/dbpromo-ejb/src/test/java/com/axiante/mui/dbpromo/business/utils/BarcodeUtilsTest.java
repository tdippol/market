package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.google.zxing.WriterException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BarcodeUtilsTest {

	@Test(expected = NullPointerException.class)
	public void testCalculateEan13ChecksumTrowsExceptionWhenNullString() {
		final BarcodeUtils utils = new BarcodeUtils();
		utils.calculateEan13Checksum(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateEan13ChecksumTrowsExceptionWhenCharacterString() {
		final BarcodeUtils utils = new BarcodeUtils();
		utils.calculateEan13Checksum("12345678901a");
	}

	@Test
	public void testCalculateEan13WithChecksumReturnsNullWhenStringLenghtNotCorrect() {
		final BarcodeUtils utils = new BarcodeUtils();
		assertNull(utils.calculateEan13Checksum(""));// no data
		assertNull(utils.calculateEan13Checksum("123456789")); // fewer
		assertNull(utils.calculateEan13Checksum("0123456789123")); // extra
	}

	@Test
	public void testCalculateEan13ChecksumReturnsCorrectString() {
		final BarcodeUtils utils = new BarcodeUtils();
		String test = "210987654321";
		Integer expected = 0;
		assertEquals(expected, utils.calculateEan13Checksum(test));

		test = "320987654321";
		expected = 6;
		assertEquals(expected, utils.calculateEan13Checksum(test));
	}

	@Test(expected = NullPointerException.class)
	public void testGenerateEan13ThrowsExceptionWhenArgumentIsNull() {
		final BarcodeUtils utils = new BarcodeUtils();
		utils.generateEan13(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateEan13ThrowsExceptionWhenArgumentIsNotANumber() {
		final BarcodeUtils utils = new BarcodeUtils();
		utils.generateEan13("1234as");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateEan13ThrowsExceptionWhenArgumentIsTooLong() {
		final BarcodeUtils utils = new BarcodeUtils();
		utils.generateEan13("01234567890123");
	}

	@Test
	public void testGenerateEan13ReturnsCorrectValues() {
		final BarcodeUtils utils = new BarcodeUtils();
		String test = "123"; // less digits
		String expected = "0000000001236";
		assertEquals(expected, utils.generateEan13(test));

		expected = "0000000000031";
		test = "3"; // single digit
		assertEquals(expected, utils.generateEan13(test));

		test = "221422975313"; // exact match
		expected = "2214229753137";
		assertEquals(expected, utils.generateEan13(test));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGenerateEan13FromEntityThrowsUnsupportedOperationExceptionWhenWrongMec() {
		final BarcodeUtils utils = new BarcodeUtils();
		final PromozionePianificazioneEntity entity = createEntity("qualcos'altro", "", "0");
		assertNull(utils.generateEan13FromEntity(entity));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateEan13FromEntityThrowsIllegalArgumentExceptionWhenValueNull() {
		final BarcodeUtils utils = new BarcodeUtils();
		final PromozionePianificazioneEntity entity = createEntityWithNullValue("M018", "1234");
		final String expected = "9851234123123";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateEan13FromEntityThrowsIllegalArgumentExceptionWhenLinkNull() {
		final BarcodeUtils utils = new BarcodeUtils();
		final PromozionePianificazioneEntity entity = createEntityWithNullValue("M018", null);
		final String expected = "9851234123123";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
	}

	@Test
	public void testGenerateEan13FromEntityReturnsStringWhenMecAndLinkAndValue() {
		final BarcodeUtils utils = new BarcodeUtils();
		PromozionePianificazioneEntity entity = createEntity("M018", "1234", "123.12");
		String expected = "9851234123123";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
		// value > 5 chars
		entity = createEntity("M018", "1234", "1123.12");
		expected = "9851234112318";
		// link < 4
		assertEquals(expected, utils.generateEan13FromEntity(entity));
		entity = createEntity("M018", "123", "1123.12");
		expected = "9850123112316";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
		// value < 5 chars
		entity = createEntity("M018", "1234", "123");
		expected = "9851234001230";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
	}

	@Test
	public void testGenerateEan13FromEntityReturnsStringWhenMecAndNumSet() {
		final BarcodeUtils utils = new BarcodeUtils();
		PromozionePianificazioneEntity entity = createNumSetEntity("M933", "1");
		final String expected = "9810000000013";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
		entity = createNumSetEntity("M932", "1");
		assertEquals(expected, utils.generateEan13FromEntity(entity));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateEan13FromEntityThrowsIllegalArgumentWhenM933AndNumSetNull() {
		final BarcodeUtils utils = new BarcodeUtils();
		final PromozionePianificazioneEntity entity = createNumSetEntity("M933", null);
		final String expected = "9810000000013";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateEan13FromEntityThrowsIllegalArgumentWhenM932AndNumSetNull() {
		final BarcodeUtils utils = new BarcodeUtils();
		final PromozionePianificazioneEntity entity = createNumSetEntity("M932", null);
		final String expected = "9810000000013";
		assertEquals(expected, utils.generateEan13FromEntity(entity));
	}

	@Test(expected = NullPointerException.class)
	public void generateEan13FromEntity_whenEntityNull_shouldThrowException() {
		final BarcodeUtils utils = new BarcodeUtils();
		utils.generateEan13FromEntity(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void generateEan13FromEntity_whenEntityHasNullLink_shouldThrowException() {
		final BarcodeUtils utils = new BarcodeUtils();
		MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		PromozionePianificazioneEntity entity = mock(PromozionePianificazioneEntity.class);
		when(meccanica.getCodiceMeccanica()).thenReturn("M018");
		when(entity.getMeccanicaEntity()).thenReturn(meccanica);
		when(entity.getLink()).thenReturn(null);
		when(entity.getValore()).thenReturn(new BigDecimal("123.12"));
		utils.generateEan13FromEntity(entity);
	}

	@Test(expected = NullPointerException.class)
	public void generateEan13ImageFromEntity_whenNullEntity_shouldThrowException() throws WriterException {
		final BarcodeUtils utils = new BarcodeUtils();
		utils.generateEan13ImageFromEntity(null);
	}

	@Test
	public void generateEan13ImageFromEntity() throws WriterException {
		final BarcodeUtils utils = new BarcodeUtils();
		PromozionePianificazioneEntity entity = createEntity("M018", "1234", "123.12");
		assertNotNull(utils.generateEan13ImageFromEntity(entity));
	}

	private PromozionePianificazioneEntity createEntity(String codiceMeccanica, String link, String value) {
		final PromozionePianificazioneEntity entity = mock(PromozionePianificazioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		when(entity.getMeccanicaEntity()).thenReturn(meccanica);
		when(meccanica.getCodiceMeccanica()).thenReturn(codiceMeccanica);
		when(entity.getLink()).thenReturn(link);
		when(entity.getValore()).thenReturn(new BigDecimal(value));
		return entity;
	}

	private PromozionePianificazioneEntity createEntityWithNullValue(String codiceMeccanica, String link) {
		final PromozionePianificazioneEntity entity = mock(PromozionePianificazioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		when(entity.getMeccanicaEntity()).thenReturn(meccanica);
		when(meccanica.getCodiceMeccanica()).thenReturn(codiceMeccanica);
		when(entity.getLink()).thenReturn(link);
		when(entity.getValore()).thenReturn(null);
		return entity;
	}

	private PromozionePianificazioneEntity createNumSetEntity(String codiceMeccanica, String numSet) {
		final PromozionePianificazioneEntity entity = mock(PromozionePianificazioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		when(entity.getMeccanicaEntity()).thenReturn(meccanica);
		when(meccanica.getCodiceMeccanica()).thenReturn(codiceMeccanica);
		when(entity.getNumSet()).thenReturn(numSet);
		return entity;
	}
}
