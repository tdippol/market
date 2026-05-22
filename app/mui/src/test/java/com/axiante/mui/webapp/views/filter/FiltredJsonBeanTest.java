package com.axiante.mui.webapp.views.filter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FiltredJsonBeanTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void getDimensionKey_shouldConcatenateAllDimensionParts() {
        FiltredJsonBean bean = new FiltredJsonBean();
        bean.setDimension("dim");
        bean.setDimension_code("code");
        bean.setDimension_desc("desc");
        assertThat(bean.getDimensionKey(), equalTo("dim#code#desc"));
    }

    @Test
    public void getDimensionKey_shouldAllowNullParts() {
        FiltredJsonBean bean = new FiltredJsonBean();
        bean.setDimension(null);
        bean.setDimension_code("code");
        bean.setDimension_desc(null);
        assertThat(bean.getDimensionKey(), equalTo("null#code#null"));
    }

    @Test
    public void getAttributeKey_shouldConcatenateAllAttributeParts() {
        FiltredJsonBean bean = new FiltredJsonBean();
        bean.setAttribute("attr");
        bean.setAttribute_code("code");
        bean.setAttribute_desc("desc");
        assertThat(bean.getAttributeKey(), equalTo("attr#code#desc"));
    }

    @Test
    public void getAttributeKey_shouldAllowNullParts() {
        FiltredJsonBean bean = new FiltredJsonBean();
        bean.setAttribute(null);
        bean.setAttribute_code(null);
        bean.setAttribute_desc("desc");
        assertThat(bean.getAttributeKey(), equalTo("null#null#desc"));
    }

    @Test
    public void getPrintableValues_shouldReturnSelectedValuesWhenAttributeTypeIsNotDatePicker() {
        FiltredJsonBean bean = new FiltredJsonBean();
        bean.setAttribute_type("text");
        bean.setSelectedValues(new String[]{"a", "b"});
        assertThat(bean.getPrintableValues(), equalTo(new String[]{"a", "b"}));
    }

    @Test
    public void getPrintableValues_shouldReturnSelectedValuesWhenAttributeTypeIsNull() {
        FiltredJsonBean bean = new FiltredJsonBean();
        bean.setAttribute_type(null);
        bean.setSelectedValues(new String[]{"x"});
        assertThat(bean.getPrintableValues(), equalTo(new String[]{"x"}));
    }

    @Test
    public void getPrintableValues_shouldConvertExcelDateWhenAttributeTypeIsDatePicker() {
        FiltredJsonBean bean = new FiltredJsonBean();
        bean.setAttribute_type("datePicker");
        bean.setSelectedValues(new String[]{"44927"});
        String[] printableValues = bean.getPrintableValues();
        org.junit.Assert.assertNotNull(printableValues);
        org.junit.Assert.assertThat(printableValues.length, equalTo(1));
        org.junit.Assert.assertNotNull(printableValues[0]);
    }
}