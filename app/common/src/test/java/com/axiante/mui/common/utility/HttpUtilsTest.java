package com.axiante.mui.common.utility;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class HttpUtilsTest {

	@Test
	public void testIsHostReachableReturnsTrue() {
		HttpUtils u = new HttpUtils();
		String host = "https://uglypipponet";
		assertFalse(u.isHostReachable(host));
	}
}
