package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class PromoStatusEnumTest {
    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Test
    public void fromCode_givenNullCode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        PromoStatusEnum.fromCode(null);
    }

    @Test
    public void fromCode_whenManaged() {
        assertSame(PromoStatusEnum.CANCELLATA_00, PromoStatusEnum.fromCode("00"));
    }

    @Test
    public void fromCode_whenUnmanaged_shouldReturnNull() {
        assertNull(PromoStatusEnum.fromCode("666"));
    }
}