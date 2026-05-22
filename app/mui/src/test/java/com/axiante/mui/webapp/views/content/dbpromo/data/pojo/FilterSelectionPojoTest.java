package com.axiante.mui.webapp.views.content.dbpromo.data.pojo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilterSelectionPojoTest {
    @Test
    public void getKey_shouldReturnKey() {
        FilterSelectionPojo pojo = new FilterSelectionPojo("A001", "Label");
        assertEquals("A001", pojo.getKey());
    }

    @Test
    public void getLabel_whenLabelIsPresent_shouldReturnFormattedLabel() {
        FilterSelectionPojo pojo = new FilterSelectionPojo("A001", "Label");
        assertEquals("A001 - Label", pojo.getLabel());
    }

    @Test
    public void getLabel_whenLabelIsNull_shouldReturnKey() {
        FilterSelectionPojo pojo = new FilterSelectionPojo("A001", null);
        assertEquals("A001", pojo.getLabel());
    }

    @Test
    public void compareTo_whenOtherIsNull_shouldReturnOne() {
        FilterSelectionPojo pojo = new FilterSelectionPojo("A001", "Label");
        assertEquals(1, pojo.compareTo(null));
    }

    @Test
    public void compareTo_whenThisKeyIsLessThanOtherKey_shouldReturnNegativeValue() {
        FilterSelectionPojo first = new FilterSelectionPojo("A001", "First");
        FilterSelectionPojo second = new FilterSelectionPojo("B001", "Second");
        assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void compareTo_whenKeysAreEqual_shouldReturnZero() {
        FilterSelectionPojo first = new FilterSelectionPojo("A001", "First");
        FilterSelectionPojo second = new FilterSelectionPojo("A001", "Second");
        assertEquals(0, first.compareTo(second));
    }

    @Test
    public void compareTo_whenThisKeyIsGreaterThanOtherKey_shouldReturnPositiveValue() {
        FilterSelectionPojo first = new FilterSelectionPojo("B001", "First");
        FilterSelectionPojo second = new FilterSelectionPojo("A001", "Second");
        assertTrue(first.compareTo(second) > 0);
    }
}