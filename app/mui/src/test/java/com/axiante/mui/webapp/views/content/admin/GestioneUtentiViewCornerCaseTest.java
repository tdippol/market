package com.axiante.mui.webapp.views.content.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import com.axiante.mui.webapp.views.content.admin.pojos.UserWrapper;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GestioneUtentiViewCornerCaseTest {
    @InjectMocks
    GestioneUtentiView gestioneUtentiView = new GestioneUtentiView();
    @Mock
    UserWrapper userMock = Mockito.mock(UserWrapper.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(userMock.getId()).thenReturn(null);
        when(userMock.getName()).thenReturn("NAME" + System.currentTimeMillis());
        when(userMock.getRoles()).thenReturn(new ArrayList<>());
    }

    @Test
    public void validateName() {
        when(userMock.getName()).thenReturn(null);
        try {
            assertFalse(gestioneUtentiView.validate());
        } catch (Exception e) {
            assertEquals("Campo 'Nome' obbligatorio.", e.getMessage());
        }
    }

    @Test
    public void validateNameSize() {
        when(userMock.getName()).thenReturn("12345678901234567890123456789012345678901234567890A");
        try {
            assertFalse(gestioneUtentiView.validate());
        } catch (Exception e) {
            assertEquals("La lunghezza massima del campo 'Nome' è 50 caratteri.", e.getMessage());
        }
    }

    @Test
    public void validateRole() {
        when(userMock.getRoles()).thenReturn(null);
        try {
            assertFalse(gestioneUtentiView.validate());
        } catch (Exception e) {
            assertEquals("Campo 'Ruolo' obbligatorio.", e.getMessage());
        }
    }

    @Test
    public void validateUserNull() {
        gestioneUtentiView.setSelectedUser(null);
        try {
            assertFalse(gestioneUtentiView.validate());
        } catch (Exception e) {
            assertEquals("Errore di inizializzazione oggetto 'Utente'.", e.getMessage());
        }
    }

}