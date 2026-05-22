package com.axiante.mui.common.utility;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Generates a UUID. Copied from {@link UUID} but changes the
 * {@link UUID#toString()}. The change is in the toString method: it suppresses
 * the insertion of dashes speeding the method by 4% on average (tested by
 * comparing 10^6 generations for 100 times with the implementation on JVM8)
 *
 * @author marco
 *
 * @see AxUUID#toString()
 */
public final class AxUUID implements Serializable{
	private static final long serialVersionUID = -1108257961219778210L;
	private final long mostSigBits;

	private final long leastSigBits;

	public static AxUUID randomUUID() {
		SecureRandom ng = Holder.numberGenerator;

		byte[] randomBytes = new byte[16];
		ng.nextBytes(randomBytes);
		randomBytes[6] &= 0x0f; /* clear version */
		randomBytes[6] |= 0x40; /* set to version 4 */
		randomBytes[8] &= 0x3f; /* clear variant */
		randomBytes[8] |= 0x80; /* set to IETF variant */
		return new AxUUID(randomBytes);
	}

	private static class Holder {
		static final SecureRandom numberGenerator = new SecureRandom();
	}

	private AxUUID(byte[] data) {
		long msb = 0;
		long lsb = 0;
		assert data.length == 16 : "data must be 16 bytes in length";
		for (int i = 0; i < 8; i++) {
			msb = (msb << 8) | (data[i] & 0xff);
		}
		for (int i = 8; i < 16; i++) {
			lsb = (lsb << 8) | (data[i] & 0xff);
		}
		this.mostSigBits = msb;
		this.leastSigBits = lsb;
	}

	/**
	 * Same as original {@link UUID#toString()} without the dashes: saves 5 chars (5
	 * bytes) and results in faster generation by 4%
	 */
	@Override
	public String toString() {
		return (digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4)
				+ digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12));
	}

	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toHexString(hi | (val & (hi - 1))).substring(1);
	}

}
