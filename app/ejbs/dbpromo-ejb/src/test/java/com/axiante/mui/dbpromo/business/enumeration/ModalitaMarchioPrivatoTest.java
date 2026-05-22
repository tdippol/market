package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ModalitaMarchioPrivatoTest {

    @Test
    public void fromValue_caseNo() {
        assertSame(ModalitaMarchioPrivato.NO, ModalitaMarchioPrivato.fromValue(-1));
    }

    @Test
    public void fromValue_caseSi() {
        assertSame(ModalitaMarchioPrivato.SI, ModalitaMarchioPrivato.fromValue(0));
    }

    @Test
    public void fromValue_caseAutomatico() {
        assertSame(ModalitaMarchioPrivato.AUTOMATICO, ModalitaMarchioPrivato.fromValue(1));
    }

    @Test
    public void fromValue_caseEsteso() {
        assertSame(ModalitaMarchioPrivato.ESTESO, ModalitaMarchioPrivato.fromValue(2));
    }

    @Test
    public void fromValue_whenUnmanaged_shouldReturnNo() {
        assertSame(ModalitaMarchioPrivato.NO, ModalitaMarchioPrivato.fromValue(42));
    }
}