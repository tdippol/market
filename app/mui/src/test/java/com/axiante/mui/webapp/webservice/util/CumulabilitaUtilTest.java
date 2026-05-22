package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonassert.impl.matcher.IsEmptyCollection.empty;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import com.axiante.mui.webapp.utils.security.CumulabilitaSecurity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.inject.Instance;
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
public class CumulabilitaUtilTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<CumulabilitaSecurity> cumulabilitaSecurityInstance;

    @Mock
    private CumulabilitaSecurity cumulabilitaSecurity;

    @InjectMocks
    @Spy
    private CumulabilitaUtil util;

    private DateTimeUtils dtUtils = new DateTimeUtils();

    @Before
    public void setUp() throws Exception {
        when(cumulabilitaSecurityInstance.get()).thenReturn(cumulabilitaSecurity);
    }

    @Test
    public void createRowData_shouldThrowException_whenListIsNull() {
        ex.expect(NullPointerException.class);
        util.createRowData(null);
    }

    @Test
    public void createRowData_shouldReturnEmptyRowData_whenThereIsNoEntities() {
        final String data = util.createRowData(Collections.emptyList());
        assertThat(data, isJson(
                withJsonPath("$.rowData", empty())
        ));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowData_shouldReturnPopulatedRowData_whenThereIsSomeEntities() {
        final List<CumulabilitaEsclusivitaEntity> entities = new ArrayList<>();
        final Date publishDate = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final String publishDateExcel = dtUtils.toExcelDate(publishDate);
        final Date cancelDate = new GregorianCalendar(2022, Calendar.AUGUST, 10).getTime();
        final String cancelDateExcel = dtUtils.toExcelDate(cancelDate);
        final CumulabilitaEsclusivitaEntity cumulabilita1 = mockCumulabilita((long) 1, "Sample 1", null, null);
        final CumulabilitaEsclusivitaEntity cumulabilita2 = mockCumulabilita((long) 2, "Sample 2", publishDate, null);
        final CumulabilitaEsclusivitaEntity cumulabilita3 = mockCumulabilita((long) 3, "Sample 3", null, cancelDate);
        entities.add(cumulabilita1);
        entities.add(cumulabilita2);
        entities.add(cumulabilita3);
        when(cumulabilitaSecurity.canPublishCumulabilita(cumulabilita1)).thenReturn(true);
        when(cumulabilitaSecurity.canPublishCumulabilita(cumulabilita2)).thenReturn(false);
        when(cumulabilitaSecurity.canPublishCumulabilita(cumulabilita3)).thenReturn(false);
        when(cumulabilitaSecurity.canCancelCumulabilita(cumulabilita1)).thenReturn(true);
        when(cumulabilitaSecurity.canCancelCumulabilita(cumulabilita2)).thenReturn(true);
        when(cumulabilitaSecurity.canCancelCumulabilita(cumulabilita3)).thenReturn(false);
        final String data = util.createRowData(entities);
        assertThat(data, isJson(allOf(
                withJsonPath("$.rowData", hasSize(3)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].descrizione.value", equalTo("Sample 1")),
                withJsonPath("$.rowData[0].publishEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].cancelEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[1].id.value", equalTo("3")),
                withJsonPath("$.rowData[1].descrizione.value", equalTo("Sample 3")),
                withJsonPath("$.rowData[1].dataAnnullamento.value", equalTo(cancelDateExcel)),
                withJsonPath("$.rowData[1].publishEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[1].cancelEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[2].id.value", equalTo("2")),
                withJsonPath("$.rowData[2].descrizione.value", equalTo("Sample 2")),
                withJsonPath("$.rowData[2].dataPubblicazione.value", equalTo(publishDateExcel)),
                withJsonPath("$.rowData[2].publishEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[2].cancelEnabled.value", equalTo(String.valueOf(true)))
        )));
        verify(cumulabilitaSecurity, times(3)).canPublishCumulabilita(any(CumulabilitaEsclusivitaEntity.class));
        verify(cumulabilitaSecurity, times(3)).canCancelCumulabilita(any(CumulabilitaEsclusivitaEntity.class));
    }

    @Test
    public void createRowDataBuoni_shouldThrowException_whenCumulabilitIsNull() {
        ex.expect(NullPointerException.class);
        util.createRowDataBuoni(null);
    }

    @Test
    public void createRowDataBuoni_shouldReturnEmptyRowData_whenThereIsNoEntities() {
        final CumulabilitaEsclusivitaEntity cumulabilita = mock(CumulabilitaEsclusivitaEntity.class);
        when(cumulabilita.getCumulabilitaBuoniEntities()).thenReturn(Collections.emptySet());
        final String data = util.createRowDataBuoni(cumulabilita);
        assertThat(data, isJson(
                withJsonPath("$.rowData", empty())
        ));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowDataBuoni_shouldReturnPopulatedRowDataDeletable_whenThereIsSomeEntitiesAndCumulabilitaNotPublishedAndNotCancelled() {
        final Date dataInizio1 = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final String dataInizio1Excel = dtUtils.toExcelDate(dataInizio1);
        final Date dataFine1 = new GregorianCalendar(2022, Calendar.AUGUST, 10).getTime();
        final String dataFine1Excel = dtUtils.toExcelDate(dataFine1);
        final Date dataInizio2 = new GregorianCalendar(2022, Calendar.AUGUST, 5).getTime();
        final String dataInizio2Excel = dtUtils.toExcelDate(dataInizio2);
        final Date dataFine2 = new GregorianCalendar(2022, Calendar.AUGUST, 20).getTime();
        final String dataFine2Excel = dtUtils.toExcelDate(dataFine2);
        final Set<CumulabilitaBuoniEntity> pianificazioni = new HashSet<>();
        CumulabilitaBuoniEntity pianificazione1 = mockCumulabilitaBuono("PR_001", 1L, 10, 42,
                dataInizio1, dataFine1);
        pianificazioni.add(pianificazione1);
        CumulabilitaBuoniEntity pianificazione2 = mockCumulabilitaBuono("PR_002", 2L, 23, 24,
                dataInizio2, dataFine2);
        pianificazioni.add(pianificazione2);
        final CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(1L, "CUMULABILITA 01", null, null);
        when(cumulabilita.getCumulabilitaBuoniEntities()).thenReturn(pianificazioni);
        when(cumulabilitaSecurity.canDeleteBuono(cumulabilita)).thenReturn(true);
        final String data = util.createRowDataBuoni(cumulabilita);
        assertThat(data, isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].codicePromo.value", equalTo("PR_001")),
                withJsonPath("$.rowData[0].prefissoCumulabile.value", equalTo(pianificazione1.getPrefissoBS())),
                withJsonPath("$.rowData[0].dataInizio.value", equalTo(dataInizio1Excel)),
                withJsonPath("$.rowData[0].dataFine.value", equalTo(dataFine1Excel)),
                withJsonPath("$.rowData[0].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[1].id.value", equalTo("2")),
                withJsonPath("$.rowData[1].codicePromo.value", equalTo("PR_002")),
                withJsonPath("$.rowData[1].prefissoCumulabile.value", equalTo(pianificazione2.getPrefissoBS())),
                withJsonPath("$.rowData[1].dataInizio.value", equalTo(dataInizio2Excel)),
                withJsonPath("$.rowData[1].dataFine.value", equalTo(dataFine2Excel)),
                withJsonPath("$.rowData[1].deleteEnabled.value", equalTo(String.valueOf(true)))
        )));
        verify(cumulabilitaSecurity, times(2)).canDeleteBuono(cumulabilita);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowDataBuoni_shouldReturnPopulatedRowDataNotDeletable_whenThereIsSomeEntitiesAndCumulabilitaPublished() {
        final Date dataInizio1 = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final String dataInizio1Excel = dtUtils.toExcelDate(dataInizio1);
        final Date dataFine1 = new GregorianCalendar(2022, Calendar.AUGUST, 10).getTime();
        final String dataFine1Excel = dtUtils.toExcelDate(dataFine1);
        final Date dataInizio2 = new GregorianCalendar(2022, Calendar.AUGUST, 5).getTime();
        final String dataInizio2Excel = dtUtils.toExcelDate(dataInizio2);
        final Date dataFine2 = new GregorianCalendar(2022, Calendar.AUGUST, 20).getTime();
        final String dataFine2Excel = dtUtils.toExcelDate(dataFine2);
        final Set<CumulabilitaBuoniEntity> cumulabilitaBuoni = new HashSet<>();
        CumulabilitaBuoniEntity pianificazione1 = mockCumulabilitaBuono("PR_001", 1L, 10, 42,
                dataInizio1, dataFine1);
        CumulabilitaBuoniEntity pianificazione2 = mockCumulabilitaBuono("PR_002", 2L, 23, 24,
                dataInizio2, dataFine2);
        cumulabilitaBuoni.add(pianificazione1);
        cumulabilitaBuoni.add(pianificazione2);
        final Date publishDate = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final Date cancelDate = new GregorianCalendar(2022, Calendar.AUGUST, 2).getTime();
        final CumulabilitaEsclusivitaEntity cumulabilita = mockCumulabilita(1L, "CUMULABILITA 01", publishDate, cancelDate);
        when(cumulabilita.getCumulabilitaBuoniEntities()).thenReturn(cumulabilitaBuoni);
        when(cumulabilitaSecurity.canDeleteBuono(cumulabilita)).thenReturn(false);
        final String data = util.createRowDataBuoni(cumulabilita);
        assertThat(data, isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].idCumulabilita.value", equalTo(String.valueOf(cumulabilita.getId()))),
                withJsonPath("$.rowData[0].codicePromo.value", equalTo("PR_001")),
                withJsonPath("$.rowData[0].prefissoCumulabile.value", equalTo(pianificazione1.getPrefissoBS())),
                withJsonPath("$.rowData[0].dataInizio.value", equalTo(dataInizio1Excel)),
                withJsonPath("$.rowData[0].dataFine.value", equalTo(dataFine1Excel)),
                withJsonPath("$.rowData[0].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[1].id.value", equalTo("2")),
                withJsonPath("$.rowData[1].idCumulabilita.value", equalTo(String.valueOf(cumulabilita.getId()))),
                withJsonPath("$.rowData[1].codicePromo.value", equalTo("PR_002")),
                withJsonPath("$.rowData[1].prefissoCumulabile.value", equalTo(pianificazione2.getPrefissoBS())),
                withJsonPath("$.rowData[1].dataInizio.value", equalTo(dataInizio2Excel)),
                withJsonPath("$.rowData[1].dataFine.value", equalTo(dataFine2Excel)),
                withJsonPath("$.rowData[1].deleteEnabled.value", equalTo(String.valueOf(false)))
        )));
        verify(cumulabilitaSecurity, times(2)).canDeleteBuono(cumulabilita);
    }

    private CumulabilitaEsclusivitaEntity mockCumulabilita(Long id, String description, Date publishDate, Date cancelDate) {
        final CumulabilitaEsclusivitaEntity entity = mock(CumulabilitaEsclusivitaEntity.class);
        when(entity.getId()).thenReturn(id);
        when(entity.getDescrizione()).thenReturn(description);
        when(entity.getDataPubblicazione()).thenReturn(publishDate);
        when(entity.getDataAnnullamento()).thenReturn(cancelDate);
        return entity;
    }

    private CumulabilitaBuoniEntity mockCumulabilitaBuono(String codicePromozione, Long id,
                                                          Integer radice, Integer progressivo,
                                                          Date dataInizio, Date dataFine) {
        final CumulabilitaBuoniEntity entity = mock(CumulabilitaBuoniEntity.class);
        when(entity.getId()).thenReturn(id);
        when(entity.getCodicePromozione()).thenReturn(codicePromozione);
        when(entity.getPrefissoBS()).thenReturn("" + radice + progressivo);
        when(entity.getDataInizio()).thenReturn(dataInizio);
        when(entity.getDataFine()).thenReturn(dataFine);
        return entity;
    }
}