package com.axiante.mui.persistence.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SlowRolesEntityTest {
    private SlowRolesEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new SlowRolesEntity(1, "NAME", "DESCRIPTION");
    }

    @Test
    public void getUuid_whenNullUuid_shouldGenerateOne() {
        assertNotNull(entity.getUuid());
    }

    @Test
    public void getUuid() {
        entity.setUuid("UUID");
        assertEquals("UUID", entity.getUuid());
    }

    @Test
    public void getManualCaption_whenHelpFilenameNull_shouldReturnHardcodedCaption() {
        entity.setHelpFilename(null);
        assertEquals("Carica Manuale", entity.getManualCaption());
    }

    @Test
    public void getManualCaption_whenHelpFilenamePresent_shouldReturnHelpFileCaptionWithFixedText() {
        entity.setHelpFilename("foo-help.html");
        assertEquals("\"foo-help.html\": cliccare per modificare", entity.getManualCaption());
    }
}