package com.axiante.mui.webapp.webservice;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import java.util.Collections;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SessionEnabledResourceTest {

    @Mock
    HttpSession session;

    @Mock
    UserService userService;

    @Mock
    PromoService promoService;

    @Mock
    private UsersEntity regularUser;

    @Mock
    private UsersEntity adminUser;

    @Mock
    private UsersEntity noRoleUser;

    @Mock
    private UserDTO appUser;

    @Spy
    @InjectMocks
    SessionEnabledResource resource;

    @Before
    public void setUp() {
        when(appUser.getUser()).thenReturn(adminUser);
        when(appUser.getCanali()).thenReturn(Collections.emptyList());
        when(adminUser.isAdmin()).thenReturn(true);
    }

    @Test
    public void isUserAdmin_shuldReturnFalse_whenCurrentUserIsNull() {
        when(session.getAttribute(UsersEntity.USER_ATTRIBUTE)).thenReturn(null);
        assertFalse(resource.isUserAdmin());
    }

    @Test
    public void isUserAdmin_shuldReturnFalse_whenCurrentUserHasNotAdminRole() {
        when(session.getAttribute(UsersEntity.USER_ATTRIBUTE)).thenReturn(regularUser);
        assertFalse(resource.isUserAdmin());
    }

    @Test
    public void isUserAdmin_shuldReturnFalse_whenCurrentUserHasNoRole() {
        when(session.getAttribute(UsersEntity.USER_ATTRIBUTE)).thenReturn(noRoleUser);
        assertFalse(resource.isUserAdmin());
    }

    @Test
    public void isUserAdmin_shuldReturnTrue_whenCurrentUserHasAdminRole() {
        when(session.getAttribute(UsersEntity.USER_ATTRIBUTE)).thenReturn(adminUser);
        assertTrue(resource.isUserAdmin());
    }

    @Test
    public void checkUser_givenNullRequest_shouldNotCallGetCurrentUser() {
        verify(resource, never()).getCurrentUser();
    }

    @Test
    public void getApplicationUser_shouldReturnNull_whenNoCurrentUser() {
        when(resource.getCurrentUser()).thenReturn(null);
        assertNull(resource.getApplicationUser(null));
    }

    @Test
    public void getApplicationUser_shouldReturnUserDto_whenCurrentUser() {
        when(resource.getCurrentUser()).thenReturn(adminUser);
        when(userService.asDto(adminUser, null)).thenReturn(appUser);
        final UserDTO result = resource.getApplicationUser(null);
        assertNotNull(result);
        assertEquals(adminUser, result.getUser());
        assertTrue(result.getCanali().isEmpty());
    }

    @Test
    public void getPromozioneTestataEntity_givenNullPromoId_shouldReturnNull() {
        assertNull(resource.getPromozioneTestataEntity(null));
        verify(promoService, never()).findById(any());
    }

    @Test
    public void getPromozioneTestataEntity_shouldReturnNull_whenSomethingWentWrong() {
        when(promoService.findById(1L)).thenThrow(IllegalArgumentException.class);
        assertNull(resource.getPromozioneTestataEntity(1L));
        verify(promoService, times(1)).findById(1L);
    }

    @Test
    public void getPromozioneTestataEntity_givenExistingPromoId_shouldReturnTestata() {
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(promoService.findById(1L)).thenReturn(testata);
        assertEquals(testata, resource.getPromozioneTestataEntity(1L));
        verify(promoService, times(1)).findById(1L);
    }

    @Test
    public void loadColumnDefFromFile_shouldReturnResponse500_whenSomethingWentWrong() {
        final Response response = resource.loadColumnDefFromFile("foo.json", "foo-grid");
        assertEquals(500, response.getStatus());
    }

    @Test
    public void loadColumnDefFromFile_shouldReturnResponse200_whenEverythingOk() {
        when(resource.getCurrentUser()).thenReturn(adminUser);
        when(adminUser.getHiddenCols()).thenReturn("[]");
        final Response response = resource.loadColumnDefFromFile("configurazionePlanningConfigurazione.json",
                "db_planning_configurazione");
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    public void loadColumnDefFromFile_shouldReturnResponse200_whenCannotApplyHiddenColumns() {
        when(resource.getCurrentUser()).thenReturn(adminUser);
        when(adminUser.getHiddenCols()).thenReturn("");
        final Response response = resource.loadColumnDefFromFile("configurazionePlanningConfigurazione.json",
                "db_planning_configurazione");
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    public void loadColumnDefFromFileWithContesto_shouldLoadDefaultColumnDef_whenContestoIsNull() {
        when(resource.getCurrentUser()).thenReturn(adminUser);
        when(adminUser.getHiddenCols()).thenReturn("[]");
        final Response response = resource.loadColumnDefFromFile("sampleColumnDef.json", "grid", null);
        assertEquals(200, response.getStatus());
        assertDefaultColumnDef(response.getEntity().toString());
    }

    @Test
    public void loadColumnDefFromFileWithContesto_shouldLoadDefaultColumnDef_whenContestoIsEmpty() {
        when(resource.getCurrentUser()).thenReturn(adminUser);
        when(adminUser.getHiddenCols()).thenReturn("[]");
        final Response response = resource.loadColumnDefFromFile("sampleColumnDef.json", "grid", "   ");
        assertEquals(200, response.getStatus());
        assertDefaultColumnDef(response.getEntity().toString());
    }

    @Test
    public void loadColumnDefFromFileWithContesto_shouldLoadDefaultColumnDef_whenColumnDefContestoNotExist() {
        when(resource.getCurrentUser()).thenReturn(adminUser);
        when(adminUser.getHiddenCols()).thenReturn("[]");
        final Response response = resource.loadColumnDefFromFile("sampleColumnDef.json", "grid", "foo");
        assertEquals(200, response.getStatus());
        assertDefaultColumnDef(response.getEntity().toString());
    }

    @Test
    public void loadColumnDefFromFileWithContesto_shouldLoadContestoColumnDef_whenColumnDefContestoExist() {
        when(resource.getCurrentUser()).thenReturn(adminUser);
        when(adminUser.getHiddenCols()).thenReturn("[]");
        final Response response = resource.loadColumnDefFromFile("sampleColumnDef.json", "grid", "contesto");
        assertEquals(200, response.getStatus());
        final String columnDef = response.getEntity().toString();
        assertThat(columnDef,
                isJson(allOf(withJsonPath("$.columnDef", hasSize(1)),
                        withJsonPath("$.columnDef[0].headerName", equalTo("Fornitore")),
                        withJsonPath("$.columnDef[0].field", equalTo("fornitore")),
                        withJsonPath("$.columnDef[0].width", equalTo(500)))));
    }

    @SuppressWarnings("unchecked")
    private void assertDefaultColumnDef(final String columnDef) {
        assertThat(columnDef,
                isJson(allOf(withJsonPath("$.columnDef", hasSize(2)),
                        withJsonPath("$.columnDef[0].headerName", equalTo("Articolo")),
                        withJsonPath("$.columnDef[0].field", equalTo("articolo")),
                        withJsonPath("$.columnDef[0].width", equalTo(300)),
                        withJsonPath("$.columnDef[1].headerName", equalTo("Compratore")),
                        withJsonPath("$.columnDef[1].field", equalTo("compratore")),
                        withJsonPath("$.columnDef[1].width", equalTo(300)))));
    }
}
