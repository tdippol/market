package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AuditLogFillerTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void fillAuditLogFields_whenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        AuditLogFiller.fillAuditLogFields(null, "user");
    }

    @Test
    public void fillAuditLogFields_whenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        AuditLogFiller.fillAuditLogFields(mock(SupportoMediaEntity.class), null);
    }
}