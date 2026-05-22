package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import java.awt.image.BufferedImage;
import java.io.Serializable;

@Slf4j
@Dependent
public class BarcodeUtils implements Serializable {
	private static final long serialVersionUID = -5086085045077013312L;

	/**
	 * Returns a barcode string in EAN13 format.
	 *
	 * @param barcode the barcode string before the checksum
	 * @return the correct EAN13 string or null in case the string is not 12 digits.
	 * @see https://barcode-coder.com/en/ean-13-specification-102.html#:~:text=EAN%2013%20checksum%20formula%20%3A&text=(10%20%2D%20%5B%20(78%20%2B,(10%20%2D%200)%20modulo%2010
	 */
	protected Integer calculateEan13Checksum(@NonNull String barcode) throws IllegalArgumentException {

		// ( 10 - [ (3 * Odd + Even) modulo 10 ] ) modulo 10
		if (barcode.length() != 12) {
			log.error(String.format("barcode before checksum must be 12 digits! provided string %s is %d digits",
					barcode, barcode.length()));
			return null;
		}
		int odds = 0;
		int evens = 0;
		int calc = 1;
		char c = 99;
		for (int pos = barcode.length() - 1; pos >= 0; --pos) {
			c = barcode.charAt(pos);
			if (Character.isDigit(c)) {
				if (calc % 2 == 0) {
					evens += Character.getNumericValue(c);
				} else {
					odds += Character.getNumericValue(c);
				}
				++calc;
			} else {
				throw new IllegalArgumentException(
						"Unexpected character " + barcode.charAt(pos) + " at position " + pos);
			}
		}
		calc = (10 - (3 * odds + evens) % 10) % 10;
		return calc;
	}

	public String generateEan13(@NonNull String barcode) throws NumberFormatException, IllegalArgumentException {
		final StringUtils utils = new StringUtils();
		if (barcode.length() > 12) {
			throw new IllegalArgumentException("Illegal string length for barcode " + barcode);
		}
		final String formatted = utils.pad(barcode, 12, '0');
		return formatted + calculateEan13Checksum(formatted);
	}

	public String generateEan13FromEntity(@NonNull PromozionePianificazioneEntity entity)
			throws UnsupportedOperationException, IllegalArgumentException {
		final StringUtils stringUtils = new StringUtils();
		switch (entity.getMeccanicaEntity().getCodiceMeccanica()) {
		case "M933":
		case "M932":
			/*
			 * Valore fisso = 981 + Valore del campo "NumSet" di 9 cifre paddato a 0.
			 */
			if (entity.getNumSet() == null) {
				throw new IllegalArgumentException("NumSet non presente");
			}
			return generateEan13("981" + stringUtils.pad(entity.getNumSet(), 9, '0'));
		case "M018":
			/*
			 * Valore fisso = 985 + Valore del campo "Link" [Il campo Link è valorizzato
			 * dall'utente con **4 caratteri fissi] + 5 cifre paddate a 0 con il valore
			 * (senza virgole) del campo : VALORE OFFERTA
			 */
			if (entity.getValore() == null) {
				throw new IllegalArgumentException("Valore non presente");
			}
			if (entity.getLink() == null) {
				throw new IllegalArgumentException("Link non presente");
			}
			return generateEan13(
					"985" + stringUtils.pad(stringUtils.truncate(entity.getLink(), 4), 4, '0') + stringUtils
							.pad(stringUtils.truncate(entity.getValore().toString().replaceAll("\\.", ""), 5), 5, '0'));

		default:
			throw new UnsupportedOperationException("Generazione del barcode per la meccanica "
					+ entity.getMeccanicaEntity().getCodiceMeccanica() + " non supportata");
		}
	}

	public BufferedImage generateEan13ImageFromEntity(@NonNull PromozionePianificazioneEntity entity)
			throws UnsupportedOperationException, IllegalArgumentException, WriterException {

		final String code = generateEan13FromEntity(entity);
		final EAN13Writer barcodeWriter = new EAN13Writer();
		final BitMatrix bitMatrix = barcodeWriter.encode(code, BarcodeFormat.EAN_13, 300, 150);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
}
