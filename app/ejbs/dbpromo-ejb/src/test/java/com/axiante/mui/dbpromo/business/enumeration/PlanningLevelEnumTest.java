package com.axiante.mui.dbpromo.business.enumeration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;

public class PlanningLevelEnumTest {
    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Test
    public void fromCode_givenNullCode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        PlanningLevelEnum.fromCode(null);
    }

    @Test
    public void fromCode_whenUnmanaged_shouldReturnNull() {
        assertNull(PlanningLevelEnum.fromCode("FOO"));
    }

    @Test
    public void fromDescription_givenNullDescription_shouldThrowException() {
        ex.expect(NullPointerException.class);
        PlanningLevelEnum.fromDescription(null);
    }

    @Test
    public void fromDescription_whenUnmanaged_shouldReturnNull() {
        assertNull(PlanningLevelEnum.fromDescription("FOO"));
    }
}