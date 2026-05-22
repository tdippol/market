package com.axiante.mui.webapp.utils.silentActions;

import static org.junit.Assert.*;

import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedParam;
import java.util.List;
import org.junit.Test;

public class SilentParamTest {

    @Test
    public void toSelectedParam_givenSilentParam_shouldReturnSelectedParam() {
        final SilentParam silentParam = new SilentParam();
        silentParam.setDimColumnName("dimColumnName");
        silentParam.setAttributeColumnName("attributeColumnName");
        silentParam.setAvailableValues(new String[] { "foo", "bar", "baz" });
        final SelectedParam selectedParam = silentParam.toSelectedParam();
        assertEquals("dimColumnName", selectedParam.getDimension());
        assertEquals("attributeColumnName", selectedParam.getAttribute());
        final List<String> values = selectedParam.getValues();
        assertEquals(3, values.size());
        assertEquals("foo", values.get(0));
        assertEquals("bar", values.get(1));
        assertEquals("baz", values.get(2));
    }
}