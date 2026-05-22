package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PromoShopsRadioEnumTest {

    @Test
    public void getValue_caseTutti() {
        assertEquals("Tutti", PromoShopsRadioEnum.VISUALIZZA_TUTTO.getValue());
    }

    @Test
    public void getValue_caseModifiche() {
        assertEquals("Modifiche", PromoShopsRadioEnum.VISUALIZZA_MODIFICHE.getValue());
    }
}