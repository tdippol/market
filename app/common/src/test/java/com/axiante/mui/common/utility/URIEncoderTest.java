package com.axiante.mui.common.utility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class URIEncoderTest {

    @Test
    public void testEncode() throws Exception{
        final String url = "http://www.axiante.com";
        assertEquals(url,URIEncoder.encode(url));
    }

    @Test(expected = Exception.class)
    public void testEncodeThrowsException() throws Exception{
        final String url = "http://www...?axiante!.com";
        URIEncoder.encode(url);
    }
}