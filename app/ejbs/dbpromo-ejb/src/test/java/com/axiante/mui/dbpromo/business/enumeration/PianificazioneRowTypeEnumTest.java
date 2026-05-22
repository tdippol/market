package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class PianificazioneRowTypeEnumTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void fromCode_givenNullCode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        PianificazioneRowTypeEnum.fromCode(null);
    }

    @Test
    public void fromCode_caseSet() {
        assertSame(PianificazioneRowTypeEnum.SET, PianificazioneRowTypeEnum.fromCode("S"));
    }

    @Test
    public void fromCode_caseRaggruppamento() {
        assertSame(PianificazioneRowTypeEnum.RAGGRUPPAMENTO, PianificazioneRowTypeEnum.fromCode("R"));
    }

    @Test
    public void fromCode_caseElemento() {
        assertSame(PianificazioneRowTypeEnum.ELEMENTO, PianificazioneRowTypeEnum.fromCode("E"));
    }

    @Test
    public void fromCode_caseMaster() {
        assertSame(PianificazioneRowTypeEnum.MASTER, PianificazioneRowTypeEnum.fromCode("M"));
    }

    @Test
    public void fromCode_caseDetail() {
        assertSame(PianificazioneRowTypeEnum.DETAIL, PianificazioneRowTypeEnum.fromCode("D"));
    }

    @Test
    public void fromCode_caseUnknown_shouldReturnNull() {
        assertNull(PianificazioneRowTypeEnum.fromCode("X"));
    }
}