package com.axiante.mui.webapp.views.content.enumeration;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TabEnumTest {

    @Test
    public void values_shouldContainAllExpectedTabsInOrder() {
        TabEnum[] values = TabEnum.values();
        assertThat(values[0], equalTo(TabEnum.TAB_STATI));
        assertThat(values[1], equalTo(TabEnum.TAB_MECCANICHE));
        assertThat(values[2], equalTo(TabEnum.TAB_PROMO_RIFERIMENTO));
        assertThat(values[3], equalTo(TabEnum.TAB_PLANO_RIFERIMENTO));
        assertThat(values[4], equalTo(TabEnum.TAB_INIZIATIVA));
        assertThat(values[5], equalTo(TabEnum.TAB_NEGOZI));
        assertThat(values[6], equalTo(TabEnum.TAB_FLAG));
        assertThat(values[7], equalTo(TabEnum.TAB_ATTRIBUTI));
        assertThat(values[8], equalTo(TabEnum.TAB_TIPO_CASSA));
        assertThat(values[9], equalTo(TabEnum.TAB_REPARTI));
        assertThat(values[10], equalTo(TabEnum.TAB_PUBBLICAZIONI));
        assertThat(values[11], equalTo(TabEnum.TAB_OWNER));
        assertThat(values[12], equalTo(TabEnum.TAB_MARCHIO_PRIVATO));
        assertThat(values[13], equalTo(TabEnum.TAB_MODIFICA));
        assertThat(values[14], equalTo(TabEnum.TAB_CONTROLLI));
        assertThat(values[15], equalTo(TabEnum.TAB_COMPRATORI));
        assertThat(values[16], equalTo(TabEnum.TAB_PIANIFICAZIONE));
        assertThat(values[17], equalTo(TabEnum.TAB_COMPLEMENTARI));
        assertThat(values[18], equalTo(TabEnum.TAB_CONTROLLI_PIANIFICAZIONE));
        assertThat(values[19], equalTo(TabEnum.TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE));
        assertThat(values[20], equalTo(TabEnum.TAB_INCLUSIONI_ESCLUSIONI));
        assertThat(values[21], equalTo(TabEnum.TAB_SOTTOSCRIZIONI));
    }

    @Test
    public void valueOf_shouldReturnExpectedEnumConstant() {
        assertThat(TabEnum.valueOf("TAB_OWNER"), equalTo(TabEnum.TAB_OWNER));
    }
}