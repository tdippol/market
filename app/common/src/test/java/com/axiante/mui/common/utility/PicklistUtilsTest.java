package com.axiante.mui.common.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.axiante.mui.common.PianificazioneConstants;
import java.util.List;
import org.junit.Test;

public class PicklistUtilsTest {
    private PicklistUtils picklistUtils = new PicklistUtils();

    @Test
    public void defineListaCellEditor_givenValidString_shouldReturnList() {
        List<String> list = picklistUtils.defineListaCellEditor("[Luke;Leia;Han Solo]");
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("Luke", list.get(0));
        assertEquals("Leia", list.get(1));
        assertEquals("Han Solo", list.get(2));
    }

    @Test
    public void defineListaCellEditor_givenNullOrEmptyString_shouldReturnListWithOneBlankElement() {
        List<String> list = picklistUtils.defineListaCellEditor(null);
        assertListWithOneBlankElement(list);
        list = picklistUtils.defineListaCellEditor("");
        assertListWithOneBlankElement(list);
    }

    @Test
    public void defineListaCellEditor_givenInvalidString_shouldReturnNull() {
        List<String> list = picklistUtils.defineListaCellEditor("[;]");
        assertNull(list);
    }

    @Test
    public void defineListaCellEditor_givenInvalidString_shouldReturnListWithOneBlankElement() {
        List<String> list = picklistUtils.defineListaCellEditor("[Luke;Leia");
        assertListWithOneBlankElement(list);
        list = picklistUtils.defineListaCellEditor("Luke;Leia]");
        assertListWithOneBlankElement(list);
    }

    @Test
    public void defineListaCellEditor_givenOneItemString_shouldReturnListWithOneGivenElement() {
        List<String> list = picklistUtils.defineListaCellEditor("[Luke]");
        assertEquals(1, list.size());
        assertEquals("Luke", list.get(0));
    }

    @Test
    public void defineListaCellEditor_givenRangeOfString_shouldReturnListWithOneBlankElement() {
        List<String> list = picklistUtils.defineListaCellEditor("[Luke..Leia]");
        assertListWithOneBlankElement(list);
    }

    @Test
    public void defineListaCellEditor_givenWrongRangeOfString_shouldReturnListWithOneBlankElement() {
        List<String> list = picklistUtils.defineListaCellEditor("[Luke..Leia..Han Solo]");
        assertListWithOneBlankElement(list);
        list = picklistUtils.defineListaCellEditor("[1..Luke]");
        assertListWithOneBlankElement(list);
    }

    @Test
    public void defineListaCellEditor_givenListOfInteger_shouldReturnList() {
        List<String> list = picklistUtils.defineListaCellEditor("[1..10]");
        assertEquals(10, list.size());
    }

    @Test
    public void defineListaCellEditorAsInts_givenValidString_shouldReturnList() {
        List<Integer> list = picklistUtils.defineListaCellEditorAsInts("[1..10]");
        assertEquals(10, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(10, (int) list.get(list.size() - 1));
    }

    @Test
    public void defineListaCellEditorAsInts_givenNullString_shouldReturnNull() {
        assertNull(picklistUtils.defineListaCellEditorAsInts("[;]"));
    }

    @Test
    public void defineListaCellEditorAsInts_givenStringWithInvalidItems_shouldDiscardThem() {
        List<Integer> list = picklistUtils.defineListaCellEditorAsInts("[Luke;42]");
        assertEquals(1, list.size());
        assertEquals(42, (int) list.get(0));
    }

    @Test
    public void convertToList_givenValidString_shouldReturnList() {
        List<String> list = picklistUtils.convertToList("[Luke;Leia]");
        assertEquals(2, list.size());
        assertEquals("Luke", list.get(0));
        assertEquals("Leia", list.get(1));
    }

    @Test
    public void convertToList_givenSingleElementString_shouldReturnListWithOneElement() {
        List<String> list = picklistUtils.convertToList("[Luke]");
        assertEquals(1, list.size());
        assertEquals("Luke", list.get(0));
    }

    @Test
    public void convertToList_givenNullString_shouldReturnNull() {
        assertNull(picklistUtils.convertToList(null));
    }

    private void assertListWithOneBlankElement(List<String> list) {
        assertEquals(1, list.size());
        assertEquals(PianificazioneConstants.CONFIGURATION_FIELD_LIST_BLANK_VALUE, list.get(0));
    }
}
