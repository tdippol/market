package com.axiante.mui.webapp.webservice;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaControlliService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.webservice.util.PianoMediaRowDataUtil;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
public class PianificazionePianoMediaResourceControlliTest {

    private static final String CONTESTO = "PianoMedia";

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    HttpSession session;

    @Mock
    private UsersEntity usersEntity;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Instance<PianoMediaService> pianoMediaServiceInstance;

    @Mock
    private PianoMediaService pianoMediaService;

    @Mock
    private Instance<PianoMediaControlliService> pianoMediaControlliServiceInstance;

    @Mock
    private PianoMediaControlliService pianoMediaControlliService;

    @Mock
    private Instance<PianoMediaRowDataUtil> pianoMediaRowDataUtilInstance;

    @Mock
    private PianoMediaRowDataUtil pianoMediaRowDataUtil;

    @Spy
    @InjectMocks
    private PianificazionePianoMediaResource ws;

    @Before
    public void setUp() throws Exception {
        when(ws.getCurrentUser()).thenReturn(usersEntity);
        when(pianoMediaServiceInstance.get()).thenReturn(pianoMediaService);
        when(pianoMediaControlliServiceInstance.get()).thenReturn(pianoMediaControlliService);
        when(pianoMediaRowDataUtilInstance.get()).thenReturn(pianoMediaRowDataUtil);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void controlliColumnDef() {
        final Response response = ws.controlliColumnDef(CONTESTO, request);
        assertEquals(200, response.getStatus());
        final String columnDef = response.getEntity().toString();
        assertThat(columnDef, isJson(allOf(
                withJsonPath("$.columnDef", hasSize(3)),
                withJsonPath("$.columnDef[0].headerName", equalTo("ID")),
                withJsonPath("$.columnDef[0].field", equalTo("id")),
                withJsonPath("$.columnDef[0].type", equalTo("string")),
                withJsonPath("$.columnDef[0].hide", equalTo(true)),
                withJsonPath("$.columnDef[0].editable", equalTo(false)),
                withJsonPath("$.columnDef[1].headerName", equalTo("Media")),
                withJsonPath("$.columnDef[1].field", equalTo("media")),
                withJsonPath("$.columnDef[1].type", equalTo("string")),
                withJsonPath("$.columnDef[1].hide", equalTo(false)),
                withJsonPath("$.columnDef[1].editable", equalTo(false)),
                withJsonPath("$.columnDef[2].headerName", equalTo("Controllo")),
                withJsonPath("$.columnDef[2].field", equalTo("controllo")),
                withJsonPath("$.columnDef[2].type", equalTo("string")),
                withJsonPath("$.columnDef[2].hide", equalTo(false)),
                withJsonPath("$.columnDef[2].editable", equalTo(false))
        )));
    }

    @Test
    public void controlliRowData_givenWrongPianoMediaId_shouldReturnPreconditionFailed() {
        final Response response = ws.controlliRowData("wrongId", CONTESTO, request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaService, never()).findById(any());
        verify(pianoMediaControlliService, never()).findByIdPianificazioniMedia(anyList());
        verify(pianoMediaControlliService, never()).findByIdPianificazioniMedia(anyList());
    }

    @Test
    public void controlliRowData_whenPianoMediaNotExists_shouldReturnPreconditionFailed() {
        when(pianoMediaService.findById(1L)).thenReturn(null);
        final Response response = ws.controlliRowData("1", CONTESTO, request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaService, times(1)).findById(1L);
        verify(pianoMediaControlliService, never()).findByIdPianificazioniMedia(anyList());
    }

    @Test
    public void controlliRowData() {
        final PianificazionePianoMediaEntity ppm1 = mockPianificazioneMedia(1L);
        final PianificazionePianoMediaEntity ppm2 = mockPianificazioneMedia(2L);
        final PianoMediaEntity pianoMedia = mockPianoMedia(Stream.of(ppm1, ppm2).collect(Collectors.toSet()));
        when(pianoMediaService.findById(1L)).thenReturn(pianoMedia);
        final Response response = ws.controlliRowData("1", CONTESTO, request);
        assertEquals(200, response.getStatus());
        verify(pianoMediaService, times(1)).findById(1L);
        verify(pianoMediaControlliService, times(1)).findByIdPianificazioniMedia(anyList());
        verify(pianoMediaRowDataUtil, times(1)).createControlliRowData(anyList());
    }

    private PianoMediaEntity mockPianoMedia(Set<PianificazionePianoMediaEntity> pianificazioniPianoMedia) {
        final PianoMediaEntity mock = mock(PianoMediaEntity.class);
        when(mock.getConfigurazioniPianoMedia()).thenReturn(pianificazioniPianoMedia);
        return mock;
    }

    private PianificazionePianoMediaEntity mockPianificazioneMedia(Long id) {
        final PianificazionePianoMediaEntity mock = mock(PianificazionePianoMediaEntity.class);
        when(mock.getId()).thenReturn(id);
        return mock;
    }
}