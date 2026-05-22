package com.axiante.mui.webapp.views.content.enumeration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TipologiaArticoliEnumTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void fromValue_shouldReturnOwnForOwnValue() {
        assertThat(TipologiaArticoliEnum.fromValue("OWN"), equalTo(TipologiaArticoliEnum.OWN));
    }

    @Test
    public void fromValue_shouldReturnOwnForOwnValueWithSpacesAndLowerCase() {
        assertThat(TipologiaArticoliEnum.fromValue("  own  "), equalTo(TipologiaArticoliEnum.OWN));
    }

    @Test
    public void fromValue_shouldReturnAllForAllValue() {
        assertThat(TipologiaArticoliEnum.fromValue("ALL"), equalTo(TipologiaArticoliEnum.ALL));
    }

    @Test
    public void fromValue_shouldReturnAllForAllValueWithSpacesAndLowerCase() {
        assertThat(TipologiaArticoliEnum.fromValue("  all  "), equalTo(TipologiaArticoliEnum.ALL));
    }

    @Test
    public void fromValue_shouldReturnNullForUnsupportedValue() {
        assertNull(TipologiaArticoliEnum.fromValue("UNKNOWN"));
    }

    @Test
    public void fromValue_shouldReturnNullForEmptyValue() {
        assertNull(TipologiaArticoliEnum.fromValue("   "));
    }

    @Test
    public void getLabel_shouldReturnConfiguredLabel() {
        assertThat(TipologiaArticoliEnum.OWN.getLabel(), equalTo("Articoli esclusivamente di propria competenza"));
        assertThat(TipologiaArticoliEnum.ALL.getLabel(), equalTo("Tutti gli articoli dell'attività in oggetto"));
    }

    @Test
    public void getValue_shouldReturnConfiguredValue() {
        assertThat(TipologiaArticoliEnum.OWN.getValue(), equalTo("OWN"));
        assertThat(TipologiaArticoliEnum.ALL.getValue(), equalTo("ALL"));
    }

    @Test(expected = NullPointerException.class)
    public void fromValue_shouldThrowWhenValueIsNull() {
        TipologiaArticoliEnum.fromValue(null);
    }
}