package com.axiante.mui.filter;

import com.axiante.connection.ConnectionProfile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PromozioneElementTest {

    @Spy
    @InjectMocks
    private PromozioneElement element;

    @Before
    public void setUp() {
        element = PromozioneElement.builder().build();
    }

    @Test(expected = NullPointerException.class)
    public void setServer_givenNullConnectionProfile_shouldThrowException() {
        element.setServer(null);
    }

    @Test
    public void setServer_givenValidConnectionProfile_shouldSetIt() {
        element.setServer(mock(ConnectionProfile.class));
        assertNotNull(element.getServer());
    }

    @Test
    public void containsAttributeCode_shouldReturnTrue_whenAttributesContainsCode_elseFalse() {
        final FilterAttribute attribute = mock(FilterAttribute.class);
        when(attribute.getCode()).thenReturn("foo");
        element.setAttributes(Collections.singletonList(attribute));
        assertTrue(element.containsAttributeCode("foo"));
        assertFalse(element.containsAttributeCode("bar"));
    }
}