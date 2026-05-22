package com.axiante.mui.webapp.views.content.dbpromo.data.pojo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleValueFilterSelectionPojoTest {
    private SingleValueFilterSelectionPojo pojo;

    @Before
    public void setUp() throws Exception {
        pojo = new SingleValueFilterSelectionPojo("foo");
    }

    @Test
    public void getKey() {
        assertEquals("foo", pojo.getKey());
    }

    @Test
    public void getLabel() {
        assertEquals("foo", pojo.getLabel());
    }
}