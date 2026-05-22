package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasNoJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaCfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaRowDataUtilTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Mock
    private PianoMediaPromoDbpromoService pianoMediaPromoDbpromoService;

    @InjectMocks
    private PianoMediaRowDataUtil util;

    @Before
    public void setUp() throws Exception {
        when(pianoMediaPromoServiceInstance.get()).thenReturn(pianoMediaPromoDbpromoService);
    }

    @Test
    public void createSupportiRowData_givenNullEntities_shouldThrowException() {
        ex.expect(NullPointerException.class);
        util.createSupportiRowData(null, null);
    }

    @Test
    public void createSupportiRowData_givenEmptyList_shouldReturnEmptyJsonArray() {
        final String rowData = util.createSupportiRowData(Collections.emptyList(), null);
        assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createSupportiRowData() {
        List<SupportoMediaEntity> entities = new ArrayList<>();
        entities.add(mockSupportoMediaEntity(1L, "SP", "Spotify"));
        entities.add(mockSupportoMediaEntity(2L, "RD", "Radio"));
        entities.add(mockSupportoMediaEntity(3L, "TV", "Televisione"));
        final String rowData = util.createSupportiRowData(entities, null);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(3)),
                withJsonPath("$.rowData[0].id.value", equalTo("2")),
                withJsonPath("$.rowData[0].codice.value", equalTo("RD")),
                withJsonPath("$.rowData[0].descrizione.value", equalTo("Radio")),
                withJsonPath("$.rowData[1].id.value", equalTo("1")),
                withJsonPath("$.rowData[1].codice.value", equalTo("SP")),
                withJsonPath("$.rowData[1].descrizione.value", equalTo("Spotify")),
                withJsonPath("$.rowData[2].id.value", equalTo("3")),
                withJsonPath("$.rowData[2].codice.value", equalTo("TV")),
                withJsonPath("$.rowData[2].descrizione.value", equalTo("Televisione"))
        )));
    }

    @Test
    public void createControlliRowData_givenNullEntities_shouldThrowException() {
        ex.expect(NullPointerException.class);
        util.createControlliRowData(null);
    }

    @Test
    public void createControlliRowData_givenEmptyList_shouldReturnEmptyJsonArray() {
        final String rowData = util.createControlliRowData(Collections.emptyList());
        assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createControlliRowData() {
        final PianoMediaEntity pianoMedia = mock(PianoMediaEntity.class);
        final Date data1 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 5).getTime();
        final Date data2 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 10).getTime();
        final Date data3 = new GregorianCalendar(2023, Calendar.SEPTEMBER, 15).getTime();
        final SupportoMediaEntity supportoMedia1 = mockSupportoMediaEntity(1L, "QT", "QUOTIDIANI");
        final SupportoMediaEntity supportoMedia2 = mockSupportoMediaEntity(2L, "RD", "RADIO");
        final PianificazionePianoMediaEntity p1 = mockPianificazioneMediaEntity(data1, supportoMedia1, pianoMedia);
        final PianificazionePianoMediaEntity p2 = mockPianificazioneMediaEntity(data2, supportoMedia1, pianoMedia);
        final PianificazionePianoMediaEntity p3 = mockPianificazioneMediaEntity(data3, supportoMedia2, pianoMedia);
        final SupportoMediaCfgCheckEntity cfgChk1 = mockSupportoMediaCfgCheckEntity("CHK1");
        final SupportoMediaCfgCheckEntity cfgChk2 = mockSupportoMediaCfgCheckEntity("CHK2");
        final SupportoMediaCfgCheckEntity cfgChk3 = mockSupportoMediaCfgCheckEntity("CHK2");
        final PianoMediaControlliEntity check1 = mockPianoMediaControlloEntity(1L, cfgChk1, p1);
        final PianoMediaControlliEntity check2 = mockPianoMediaControlloEntity(2L, cfgChk2, p2);
        final PianoMediaControlliEntity check3 = mockPianoMediaControlloEntity(3L, cfgChk3, p3);
        when(pianoMedia.getConfigurazioniPianoMedia()).thenReturn(Stream.of(p1, p2, p3).collect(Collectors.toSet()));
        final String rowData = util.createControlliRowData(Stream.of(check1, check2, check3).collect(Collectors.toList()));
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(3)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].id.editable", equalTo(false)),
                withJsonPath("$.rowData[0].id.warning", equalTo(true)),
                withJsonPath("$.rowData[0].media.value", equalTo("QUOTIDIANI #1")),
                withJsonPath("$.rowData[0].media.editable", equalTo(false)),
                withJsonPath("$.rowData[0].media.warning", equalTo(true)),
                withJsonPath("$.rowData[0].controllo.value", equalTo("Data inizio coincidente con data inizio campagna")),
                withJsonPath("$.rowData[0].controllo.editable", equalTo(false)),
                withJsonPath("$.rowData[0].controllo.warning", equalTo(true)),
                withJsonPath("$.rowData[1].id.value", equalTo("2")),
                withJsonPath("$.rowData[1].id.editable", equalTo(false)),
                withJsonPath("$.rowData[1].id.warning", equalTo(true)),
                withJsonPath("$.rowData[1].media.value", equalTo("QUOTIDIANI #2")),
                withJsonPath("$.rowData[1].media.editable", equalTo(false)),
                withJsonPath("$.rowData[1].media.warning", equalTo(true)),
                withJsonPath("$.rowData[1].controllo.value", equalTo("Durata minore di 3 giorni")),
                withJsonPath("$.rowData[1].controllo.editable", equalTo(false)),
                withJsonPath("$.rowData[1].controllo.warning", equalTo(true)),
                withJsonPath("$.rowData[2].id.value", equalTo("3")),
                withJsonPath("$.rowData[2].id.editable", equalTo(false)),
                withJsonPath("$.rowData[2].id.warning", equalTo(true)),
                withJsonPath("$.rowData[2].media.value", equalTo("RADIO #1")),
                withJsonPath("$.rowData[2].media.editable", equalTo(false)),
                withJsonPath("$.rowData[2].media.warning", equalTo(true)),
                withJsonPath("$.rowData[2].controllo.value", equalTo("Durata minore di 3 giorni")),
                withJsonPath("$.rowData[2].controllo.editable", equalTo(false)),
                withJsonPath("$.rowData[2].controllo.warning", equalTo(true))
        )));
    }

    private PianificazionePianoMediaEntity mockPianificazioneMediaEntity(Date date, SupportoMediaEntity supportoMedia,
                                                                         PianoMediaEntity pianoMedia) {
        final PianificazionePianoMediaEntity mock = mock(PianificazionePianoMediaEntity.class);
        when(mock.getDataInizio()).thenReturn(date);
        when(mock.getSupportoMedia()).thenReturn(supportoMedia);
        when(mock.getPianoMedia()).thenReturn(pianoMedia);
        return mock;
    }

    @Test
    public void update() {
        SupportoMediaEntity supporto = mockSupportoMediaEntity(1L, "FOO", "FOOBAR");
        doCallRealMethod().when(supporto).setDescrizione(anyString());
        final String payload = "{\"descrizione\":\"BAZAR\"}";
        SupportoMediaEntity supportoUpdated = util.update(supporto, payload, "junit");
        when(supportoUpdated.getDescrizione()).thenCallRealMethod();
        assertEquals(1L, (long) supportoUpdated.getId());
        assertEquals("FOO", supportoUpdated.getCodiceMedia());
        assertEquals("BAZAR", supportoUpdated.getDescrizione());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createCreaPianoMediaRowData_emptyRow() {
        final String rowData = util.createCreaPianoMediaRowData("1", new ArrayList<>(), "junit");
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].createEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[0].createEnabled.editable", equalTo(false)),
                withJsonPath("$.rowData[0].slotId.value", equalTo("001")),
                withJsonPath("$.rowData[0].slotId.editable", equalTo(false)),
                hasNoJsonPath("$.rowData[0].anno.value"),
                withJsonPath("$.rowData[0].anno.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoRif.value"),
                withJsonPath("$.rowData[0].promoRif.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoRif.comboBoxValues"),
                hasNoJsonPath("$.rowData[0].descrizione.value"),
                withJsonPath("$.rowData[0].descrizione.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].dataInizio.value"),
                withJsonPath("$.rowData[0].dataInizio.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].dataFine.value"),
                withJsonPath("$.rowData[0].dataFine.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecA.value"),
                withJsonPath("$.rowData[0].promoSecA.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecB.value"),
                withJsonPath("$.rowData[0].promoSecB.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecC.value"),
                withJsonPath("$.rowData[0].promoSecC.editable", equalTo(true))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createCreaPianoMediaRowData_fullRow() {
        final DateTimeUtils dtUtils = new DateTimeUtils();
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 30).getTime();
        final String dataInizioExcel = dtUtils.toExcelDate(dataInizio);
        final String dataFineExcel = dtUtils.toExcelDate(dataFine);
        final CreaPianoMediaEntity piano = mockCreaPianoMediaEntity("junit", "001", 2023,
                "MASTER", "PIANO-01", dataInizio, dataFine,
                "SEC_A", "SEC_B", "SEC_C");
        final String rowData = util.createCreaPianoMediaRowData("1", new ArrayList<>(Collections.singletonList(piano)),
                "junit");
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].createEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].createEnabled.editable", equalTo(false)),
                withJsonPath("$.rowData[0].slotId.value", equalTo("001")),
                withJsonPath("$.rowData[0].slotId.editable", equalTo(false)),
                withJsonPath("$.rowData[0].anno.value", equalTo("2023")),
                withJsonPath("$.rowData[0].anno.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoRif.value", equalTo("MASTER")),
                withJsonPath("$.rowData[0].promoRif.editable", equalTo(true)),
                withJsonPath("$.rowData[0].descrizione.value", equalTo("PIANO-01")),
                withJsonPath("$.rowData[0].descrizione.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataInizio.value", equalTo(dataInizioExcel)),
                withJsonPath("$.rowData[0].dataInizio.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataFine.value", equalTo(dataFineExcel)),
                withJsonPath("$.rowData[0].dataFine.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoSecA.value", equalTo("SEC_A")),
                withJsonPath("$.rowData[0].promoSecA.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoSecB.value", equalTo("SEC_B")),
                withJsonPath("$.rowData[0].promoSecB.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoSecC.value", equalTo("SEC_C")),
                withJsonPath("$.rowData[0].promoSecC.editable", equalTo(true))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createCreaPianoMediaRowData_onlyMasterSetted() {
        final DateTimeUtils dtUtils = new DateTimeUtils();
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 30).getTime();
        final String dataInizioExcel = dtUtils.toExcelDate(dataInizio);
        final String dataFineExcel = dtUtils.toExcelDate(dataFine);
        final CreaPianoMediaEntity piano = mockCreaPianoMediaEntity("junit", "001", 2023,
                "MASTER", "PIANO-01", dataInizio, dataFine,
                null, null, null);
        final String rowData = util.createCreaPianoMediaRowData("1", new ArrayList<>(Collections.singletonList(piano)),
                "junit");
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].createEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].createEnabled.editable", equalTo(false)),
                withJsonPath("$.rowData[0].slotId.value", equalTo("001")),
                withJsonPath("$.rowData[0].slotId.editable", equalTo(false)),
                withJsonPath("$.rowData[0].anno.value", equalTo("2023")),
                withJsonPath("$.rowData[0].anno.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoRif.value", equalTo("MASTER")),
                withJsonPath("$.rowData[0].promoRif.editable", equalTo(true)),
                withJsonPath("$.rowData[0].descrizione.value", equalTo("PIANO-01")),
                withJsonPath("$.rowData[0].descrizione.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataInizio.value", equalTo(dataInizioExcel)),
                withJsonPath("$.rowData[0].dataInizio.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataFine.value", equalTo(dataFineExcel)),
                withJsonPath("$.rowData[0].dataFine.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecA.value"),
                withJsonPath("$.rowData[0].promoSecA.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecB.value"),
                withJsonPath("$.rowData[0].promoSecB.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecC.value"),
                withJsonPath("$.rowData[0].promoSecC.editable", equalTo(true))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createCreaPianoMediaRowData_masterAndSecASetted() {
        final DateTimeUtils dtUtils = new DateTimeUtils();
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 30).getTime();
        final String dataInizioExcel = dtUtils.toExcelDate(dataInizio);
        final String dataFineExcel = dtUtils.toExcelDate(dataFine);
        final CreaPianoMediaEntity piano = mockCreaPianoMediaEntity("junit", "001", 2023,
                "MASTER", "PIANO-01", dataInizio, dataFine,
                "SEC_A", null, null);
        final String rowData = util.createCreaPianoMediaRowData("1", new ArrayList<>(Collections.singletonList(piano)),
                "junit");
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].createEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].createEnabled.editable", equalTo(false)),
                withJsonPath("$.rowData[0].slotId.value", equalTo("001")),
                withJsonPath("$.rowData[0].slotId.editable", equalTo(false)),
                withJsonPath("$.rowData[0].anno.value", equalTo("2023")),
                withJsonPath("$.rowData[0].anno.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoRif.value", equalTo("MASTER")),
                withJsonPath("$.rowData[0].promoRif.editable", equalTo(true)),
                withJsonPath("$.rowData[0].descrizione.value", equalTo("PIANO-01")),
                withJsonPath("$.rowData[0].descrizione.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataInizio.value", equalTo(dataInizioExcel)),
                withJsonPath("$.rowData[0].dataInizio.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataFine.value", equalTo(dataFineExcel)),
                withJsonPath("$.rowData[0].dataFine.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoSecA.value", equalTo("SEC_A")),
                withJsonPath("$.rowData[0].promoSecA.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecB.value"),
                withJsonPath("$.rowData[0].promoSecB.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecC.value"),
                withJsonPath("$.rowData[0].promoSecC.editable", equalTo(true))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createCreaPianoMediaRowData_masterAndSecAAndSecBSetted() {
        final DateTimeUtils dtUtils = new DateTimeUtils();
        final Date dataInizio = new GregorianCalendar(2023, Calendar.JULY, 1).getTime();
        final Date dataFine = new GregorianCalendar(2023, Calendar.JULY, 30).getTime();
        final String dataInizioExcel = dtUtils.toExcelDate(dataInizio);
        final String dataFineExcel = dtUtils.toExcelDate(dataFine);
        final CreaPianoMediaEntity piano = mockCreaPianoMediaEntity("junit", "001", 2023,
                "MASTER", "PIANO-01", dataInizio, dataFine,
                "SEC_A", "SEC_B", null);
        final String rowData = util.createCreaPianoMediaRowData("1", new ArrayList<>(Collections.singletonList(piano)),
                "junit");
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].createEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].createEnabled.editable", equalTo(false)),
                withJsonPath("$.rowData[0].slotId.value", equalTo("001")),
                withJsonPath("$.rowData[0].slotId.editable", equalTo(false)),
                withJsonPath("$.rowData[0].anno.value", equalTo("2023")),
                withJsonPath("$.rowData[0].anno.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoRif.value", equalTo("MASTER")),
                withJsonPath("$.rowData[0].promoRif.editable", equalTo(true)),
                withJsonPath("$.rowData[0].descrizione.value", equalTo("PIANO-01")),
                withJsonPath("$.rowData[0].descrizione.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataInizio.value", equalTo(dataInizioExcel)),
                withJsonPath("$.rowData[0].dataInizio.editable", equalTo(true)),
                withJsonPath("$.rowData[0].dataFine.value", equalTo(dataFineExcel)),
                withJsonPath("$.rowData[0].dataFine.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoSecA.value", equalTo("SEC_A")),
                withJsonPath("$.rowData[0].promoSecA.editable", equalTo(true)),
                withJsonPath("$.rowData[0].promoSecB.value", equalTo("SEC_B")),
                withJsonPath("$.rowData[0].promoSecB.editable", equalTo(true)),
                hasNoJsonPath("$.rowData[0].promoSecC.value"),
                withJsonPath("$.rowData[0].promoSecC.editable", equalTo(true))
        )));
    }

    private CreaPianoMediaEntity mockCreaPianoMediaEntity(String userId, String slotId, Integer anno, String promoMaster,
                                                          String descrizione, Date dataInizio, Date dataFine,
                                                          String promoSecA, String promoSecB, String promoSecC) {
        final CreaPianoMediaEntity mock = mock(CreaPianoMediaEntity.class);
        when(mock.getUserId()).thenReturn(userId);
        when(mock.getSlotId()).thenReturn(slotId);
        when(mock.getAnno()).thenReturn(anno);
        when(mock.getPromoMaster()).thenReturn(promoMaster);
        when(mock.getDescrizione()).thenReturn(descrizione);
        when(mock.getDataInizio()).thenReturn(dataInizio);
        when(mock.getDataFine()).thenReturn(dataFine);
        when(mock.getPromoSecondaryA()).thenReturn(promoSecA);
        when(mock.getPromoSecondaryB()).thenReturn(promoSecB);
        when(mock.getPromoSecondaryC()).thenReturn(promoSecC);
        return mock;
    }

    private SupportoMediaEntity mockSupportoMediaEntity(Long id, String codice, String descrizione) {
        final SupportoMediaEntity mock = mock(SupportoMediaEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getCodiceMedia()).thenReturn(codice);
        when(mock.getDescrizione()).thenReturn(descrizione);
        return mock;
    }

    private PianoMediaControlliEntity mockPianoMediaControlloEntity(Long id, SupportoMediaCfgCheckEntity cfgCheck,
                                                                    PianificazionePianoMediaEntity pianificazioneMedia) {
        final PianoMediaControlliEntity mock = mock(PianoMediaControlliEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getSupportoMediaCfgCheck()).thenReturn(cfgCheck);
        when(mock.getPianificazioneMedia()).thenReturn(pianificazioneMedia);
        return mock;
    }

    private SupportoMediaCfgCheckEntity mockSupportoMediaCfgCheckEntity(String codice) {
        final SupportoMediaCfgCheckEntity mock = mock(SupportoMediaCfgCheckEntity.class);
        when(mock.getCodiceControllo()).thenReturn(codice);
        return mock;
    }
}