package com.axiante.mui.webapp.views.content.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GestioneACLViewCornerCaseTest {
	@InjectMocks
	GestioneACLView gestioneACLView = new GestioneACLView();

	@Mock
	AclEntity aclMock = Mockito.mock(AclEntity.class);

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(aclMock.getId()).thenReturn(null);
		when(aclMock.getComponentClass()).thenReturn("COMPONENT_CLASS" + System.currentTimeMillis());
		when(aclMock.getComponent()).thenReturn("COMPONENT" + System.currentTimeMillis());
		when(aclMock.getVisible()).thenReturn(true);
		when(aclMock.getEnabled()).thenReturn(true);
		when(aclMock.getEditable()).thenReturn(true);
		when(aclMock.getRolesByRoleId()).thenReturn(new RolesEntity());
	}

	@Test
	public void validateRole() {
		when(aclMock.getRolesByRoleId()).thenReturn(null);
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("Campo 'Ruolo' obbligatorio.", e.getMessage());
		}
	}

	@Test
	public void validateComponentClass() {
		when(aclMock.getComponentClass()).thenReturn(null);
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("Campo 'Classe Componente' obbligatorio.", e.getMessage());
		}
	}

	@Test
	public void validateComponent() {
		when(aclMock.getComponent()).thenReturn(null);
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("Campo 'Componente' obbligatorio.", e.getMessage());
		}
	}

	@Test
	public void validateComponentSize() {
		when(aclMock.getComponent()).thenReturn("1234567890123456789012345678901234567890ABCDEFGHILM");
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("La lunghezza massima del campo 'Componente' è 50 caratteri.", e.getMessage());
		}
	}

	@Test
	public void validateVisible() {
		when(aclMock.getVisible()).thenReturn(null);
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("Campo 'Visibile' obbligatorio.", e.getMessage());
		}
	}

	@Test
	public void validateEnabled() {
		when(aclMock.getEnabled()).thenReturn(null);
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("Campo 'Enabled' obbligatorio.", e.getMessage());
		}
	}

	@Test
	public void validateEditable() {
		when(aclMock.getEditable()).thenReturn(null);
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("Campo 'Editable' obbligatorio.", e.getMessage());
		}
	}

	@Test
	public void validateAclNull() {
		gestioneACLView.setSelectedACL(null);
		try {
			assertFalse(gestioneACLView.validate());
		} catch (Exception e) {
			assertEquals("Errore di inizializzazione oggetto 'Regola ACL'.", e.getMessage());
		}
	}
}