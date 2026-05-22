package com.axiante.mui.webapp.webservice;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.webservice.util.PianoMediaRowDataUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.inject.Instance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaResourceTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private String contesto = "contesto";

    @Mock
    HttpSession session;

    @Mock
    UserService userService;

    @Mock
    private UsersEntity usersEntity;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Instance<SupportoMediaService> supportoMediaServiceInstance;

    @Mock
    private SupportoMediaService supportoMediaService;

    @Mock
    private Instance<PianoMediaRowDataUtil> pianoMediaRowDataUtilInstance;

    @Mock
    private PianoMediaRowDataUtil pianoMediaRowDataUtil;

    private final List<SupportoMediaEntity> supportiMedia = new ArrayList<>();

    @Spy
    @InjectMocks
    private PianoMediaResource ws;

    @Before
    public void setUp() throws Exception {
        supportiMedia.add(mock(SupportoMediaEntity.class));
        supportiMedia.add(mock(SupportoMediaEntity.class));
        supportiMedia.add(mock(SupportoMediaEntity.class));
        when(ws.getCurrentUser()).thenReturn(usersEntity);
        when(supportoMediaServiceInstance.get()).thenReturn(supportoMediaService);
        when(pianoMediaRowDataUtilInstance.get()).thenReturn(pianoMediaRowDataUtil);
        when(usersEntity.getName()).thenReturn("junit");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getSupportiColumnDef() throws Exception {
        final Response response = ws.getSupportiColumnDef(contesto);
        assertEquals(200, response.getStatus());
        final String columnDef = response.getEntity().toString();
        assertThat(columnDef, isJson(allOf(
                withJsonPath("$.columnDef", hasSize(5)),
                withJsonPath("$.columnDef[0].headerName", equalTo("ID")),
                withJsonPath("$.columnDef[0].field", equalTo("id")),
                withJsonPath("$.columnDef[0].type", equalTo("string")),
                withJsonPath("$.columnDef[0].hide", equalTo(true)),
                withJsonPath("$.columnDef[0].editable", equalTo(false)),
                withJsonPath("$.columnDef[1].headerName", equalTo("Codice")),
                withJsonPath("$.columnDef[1].field", equalTo("codice")),
                withJsonPath("$.columnDef[1].type", equalTo("string")),
                withJsonPath("$.columnDef[1].hide", equalTo(false)),
                withJsonPath("$.columnDef[1].editable", equalTo(false)),
                withJsonPath("$.columnDef[2].headerName", equalTo("Descrizione")),
                withJsonPath("$.columnDef[2].field", equalTo("descrizione")),
                withJsonPath("$.columnDef[2].type", equalTo("string")),
                withJsonPath("$.columnDef[2].hide", equalTo(false)),
                withJsonPath("$.columnDef[2].editable", equalTo(true)),
                withJsonPath("$.columnDef[3].headerName", equalTo("Attivo")),
                withJsonPath("$.columnDef[3].field", equalTo("attivo")),
                withJsonPath("$.columnDef[3].type", equalTo("string")),
                withJsonPath("$.columnDef[3].hide", equalTo(false)),
                withJsonPath("$.columnDef[3].editable", equalTo(true)),
                withJsonPath("$.columnDef[4].editable", equalTo(true)),
                withJsonPath("$.columnDef[4].headerName", equalTo("Colore")),
                withJsonPath("$.columnDef[4].field", equalTo("colore")),
                withJsonPath("$.columnDef[4].type", equalTo("pickcolor")),
                withJsonPath("$.columnDef[4].hide", equalTo(false)),
                withJsonPath("$.columnDef[4].editable", equalTo(true))
        )));
        verify(ws, times(1))
                .loadColumnDefFromFile("columnDefPianoMediaSupporti.json", "db_pianoMediaSupporti", contesto);
    }

    @Test
    public void getSupportiRowData_whenSomethingWentWrong_shouldReturnServerError() throws Exception {
        when(supportoMediaService.findAll()).thenReturn(supportiMedia);
        when(pianoMediaRowDataUtil.createSupportiRowData(supportiMedia, null)).thenThrow(NullPointerException.class);
        final Response response = ws.getSupportiRowData(contesto, request);
        assertEquals(500, response.getStatus());
        assertEquals("{\"message\":\"Errore recupero rowData\"}", response.getEntity().toString());
        verify(supportoMediaServiceInstance, times(1)).get();
        verify(supportoMediaService, times(1)).findAll();
        verify(pianoMediaRowDataUtilInstance, times(1)).get();
        verify(pianoMediaRowDataUtil, times(1)).createSupportiRowData(supportiMedia, null);
    }

    @Test
    public void getSupportiRowData() throws Exception {
        when(supportoMediaService.findAll()).thenReturn(supportiMedia);
        when(pianoMediaRowDataUtil.createSupportiRowData(supportiMedia, null)).thenReturn("ok");
        final Response response = ws.getSupportiRowData(contesto, request);
        assertEquals(200, response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(supportoMediaServiceInstance, times(1)).get();
        verify(supportoMediaService, times(1)).findAll();
        verify(pianoMediaRowDataUtilInstance, times(1)).get();
        verify(pianoMediaRowDataUtil, times(1)).createSupportiRowData(supportiMedia, null);
    }

    @Test
    public void updateSupporto_whenSomethingWentWrong_shouldReturnServerError() throws Exception {
        final String payload = "{\"descrizione\":\"FOOBAR\"}";
        final Response response = ws.updateSupporto("fuffa", contesto, payload, request);
        assertEquals(500, response.getStatus());
        verify(supportoMediaService, never()).findById("fuffa");
    }

    @Test
    public void updateSupporto_whenSupportoNotExist_shouldReturnPreconditionFail() throws Exception {
        final String payload = "{\"descrizione\":\"BAZAR\"}";
        when(supportoMediaService.findById(1L)).thenReturn(null);
        final Response response = ws.updateSupporto("1", contesto, payload, request);
        assertEquals(412, response.getStatus());
        verify(supportoMediaService, times(1)).findById(1L);
        verify(supportoMediaService, never()).persist(any(SupportoMediaEntity.class));
    }

    @Test
    public void updateSupporto_whenUpdateFail_shouldReturnServerError() throws Exception {
        final String payload = "{\"descrizione\":\"BAZAR\"}";
        final SupportoMediaEntity supporto = mock(SupportoMediaEntity.class);
        final SupportoMediaEntity supportoUpdated = mock(SupportoMediaEntity.class);
        when(supportoMediaService.findById(1L)).thenReturn(supporto);
        when(supportoMediaService.persist(any(SupportoMediaEntity.class))).thenThrow(NullPointerException.class);
        when(pianoMediaRowDataUtil.update(supporto, payload,"junit")).thenReturn(supportoUpdated);
        final Response response = ws.updateSupporto("1", contesto, payload, request);
        assertEquals(500, response.getStatus());
        verify(supportoMediaService, times(1)).findById(1L);
        verify(supportoMediaService, times(1)).persist(supportoUpdated);
    }

    @Test
    public void updateSupporto_whenEverythingOk_shouldReturnUpdatedEntity() throws Exception {
        final String payload = "{\"descrizione\":\"BAZAR\"}";
        final SupportoMediaEntity supporto = mock(SupportoMediaEntity.class);
        final SupportoMediaEntity supportoUpdated = mock(SupportoMediaEntity.class);
        when(supportoMediaService.findById(1L)).thenReturn(supporto);
        when(supportoMediaService.persist(supportoUpdated)).thenReturn(supportoUpdated);
        when(pianoMediaRowDataUtil.update(supporto, payload, "junit")).thenReturn(supportoUpdated);
        when(pianoMediaRowDataUtil.createSupportiRowData(Collections.singletonList(supportoUpdated), "Supporto Media aggiornato"))
                .thenReturn("ok");
        final Response response = ws.updateSupporto("1", contesto, payload, request);
        assertEquals(200, response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(supportoMediaService, times(1)).findById(1L);
        verify(supportoMediaService, times(1)).persist(supportoUpdated);
        verify(pianoMediaRowDataUtil, times(1))
                .createSupportiRowData(Collections.singletonList(supportoUpdated), "Supporto Media aggiornato");
    }
}