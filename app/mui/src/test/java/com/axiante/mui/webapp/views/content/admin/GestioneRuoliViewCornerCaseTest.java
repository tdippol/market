package com.axiante.mui.webapp.views.content.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import com.axiante.mui.persistence.entity.RolesEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GestioneRuoliViewCornerCaseTest {
    @InjectMocks
    GestioneRuoliView gestioneRuoliView = new GestioneRuoliView();
    @Mock
    RolesEntity roleMock = Mockito.mock(RolesEntity.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(roleMock.getId()).thenReturn(null);
        when(roleMock.getName()).thenReturn("NAME" + System.currentTimeMillis());
        when(roleMock.getDescription()).thenReturn("DESCRIPTION" + System.currentTimeMillis());
        gestioneRuoliView.setSelectedRole(roleMock);
    }

    @Test
    public void validateName() {
        when(roleMock.getName()).thenReturn(null);
        try {
            assertFalse(gestioneRuoliView.validate());
        } catch (final Exception e) {
            assertEquals("Campo 'Nome' obbligatorio.", e.getMessage());
        }
    }
    @Test
    public void validateNameSize() {
        when(roleMock.getName()).thenReturn("12345678901234567890123456789012345678901234567890A");
        try {
            assertFalse(gestioneRuoliView.validate());
        } catch (final Exception e) {
            assertEquals("La lunghezza massima del campo 'Nome' è 50 caratteri.", e.getMessage());
        }
    }
    @Test
    public void validateDescriptionSize() {
        when(roleMock.getDescription()).thenReturn("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890A");
        try {
            assertFalse(gestioneRuoliView.validate());
        } catch (final Exception e) {
            assertEquals("La lunghezza massima del campo 'Descrizione' è 250 caratteri.", e.getMessage());
        }
    }
    @Test
    public void validateRoleNull() {
        gestioneRuoliView.setSelectedRole(null);
        try {
            assertFalse(gestioneRuoliView.validate());
        } catch (final Exception e) {
            assertEquals("Errore di inizializzazione oggetto 'Ruolo'.", e.getMessage());
        }
    }
}