package com.axiante.mui.business;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.axiante.mui.common.PianificazioneSecurityEnum;

public class SecurityEnumConverterTest {
	SecurityEnumConverter converter = new SecurityEnumConverter();

	@Test
	public void testWhenDatabaseValueIsNullThenReturnsNull() {
		assertNull(converter.convertToEntityAttribute(null));
	}

	@Test
	public void testWhenDatabaseValueIsRThenReturnsRead() {
		assertTrue(converter.convertToEntityAttribute("r").equals(PianificazioneSecurityEnum.READ));
		assertTrue(converter.convertToEntityAttribute("R").equals(PianificazioneSecurityEnum.READ));
	}
	@Test
	public void testWhenDatabaseValueIsWThenReturnsWrite() {
		assertTrue(converter.convertToEntityAttribute("w").equals(PianificazioneSecurityEnum.WRITE));
		assertTrue(converter.convertToEntityAttribute("W").equals(PianificazioneSecurityEnum.WRITE));
	}
	@Test
	public void testWhenDatabaseValueIsUnknownThenReturnsRead() {
		assertTrue(converter.convertToEntityAttribute("").equals(PianificazioneSecurityEnum.READ));
		assertTrue(converter.convertToEntityAttribute("p").equals(PianificazioneSecurityEnum.READ));
	}

	@Test
	public void testWhenEntityValueIsNullThenReturnsNull() {
		assertNull(converter.convertToDatabaseColumn(null));
	}

	@Test
	public void testWhenEntityValueIsRThenReturnsRead() {
		assertTrue(converter.convertToDatabaseColumn(PianificazioneSecurityEnum.READ).equals(PianificazioneSecurityEnum.READ.getSecurity()));
	}
	@Test
	public void testWhenEntityValueIsWThenReturnsWrite() {
		assertTrue(converter.convertToDatabaseColumn(PianificazioneSecurityEnum.WRITE).equals(PianificazioneSecurityEnum.WRITE.getSecurity()));
	}

}
