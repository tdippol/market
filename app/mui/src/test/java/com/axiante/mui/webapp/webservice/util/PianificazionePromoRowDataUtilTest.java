package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianificazionePromoRowDataUtilTest {

    @InjectMocks
    private PianificazionePromoRowDataUtil util;

    @Test(expected = NullPointerException.class)
    public void createCheckCompratoriRowData_givenNullList_shouldThrowException() {
        util.createCheckCompratoriRowData(null);
    }

    @Test
    public void createCheckCompratoriRowData_givenEmptyList_shouldReturnEmptyRowData() {
        final String rowData = util.createCheckCompratoriRowData(new ArrayList<>());
        assertNotNull(rowData);
        assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createCheckCompratoriRowData_givenListWithCompratori_shouldReturnPopulatedRowData() {
        final DateTimeUtils dateTimeUtils = new DateTimeUtils();
        final List<CheckCompratoriEntity> list = new ArrayList<>();
        final Date date1 = new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime();
        final Date date2 = new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime();
        final String excelDate1 = dateTimeUtils.toExcelDate(date1);
        final String excelDate2 = dateTimeUtils.toExcelDate(date2);
        final CheckCompratoriEntity buyer1 = mockCheckCompratore(1L, "Anakin Skywalker", "KO", date1, date2);
        final CheckCompratoriEntity buyer2 = mockCheckCompratore(2L, "Ahsoka Tano", "OK", date1, null);
        list.add(buyer1);
        list.add(buyer2);
        final String rowData = util.createCheckCompratoriRowData(list);
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].compratore.value", equalTo("[S01] Anakin Skywalker")),
                withJsonPath("$.rowData[0].compratore.mandatory", equalTo(true)),
                withJsonPath("$.rowData[0].data.value", equalTo(excelDate2)),
                withJsonPath("$.rowData[0].data.mandatory", equalTo(true)),
                withJsonPath("$.rowData[0].esito.value", equalTo("KO")),
                withJsonPath("$.rowData[0].esito.mandatory", equalTo(true)),
                withJsonPath("$.rowData[1].compratore.value", equalTo("[S02] Ahsoka Tano")),
                withJsonPath("$.rowData[1].data.value", equalTo(excelDate1)),
                withJsonPath("$.rowData[1].esito.value", equalTo("OK"))
        )));
    }

    @Test(expected = NullPointerException.class)
    public void createCompratoriRowData_givenNullList_shouldThrowException() {
        util.createCompratoriRowData(null);
    }

    @Test
    public void createCompratoriRowData_givenEmptyList_shouldReturnEmptyRowData() {
        final String rowData = util.createCompratoriRowData(new ArrayList<>());
        assertNotNull(rowData);
        assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
    }

    @Test
    public void createCompratoriRowData_givenListWithCompratori_shouldReturnPopulatedRowData() {
        final List<CompratoreEntity> list = new ArrayList<>();
        final CompratoreEntity buyer1 = mockCompratore(1L, "Anakin Skywalker");
        final CompratoreEntity buyer2 = mockCompratore(2L, "Ahsoka Tano");
        list.add(buyer1);
        list.add(buyer2);
        final String rowData = util.createCompratoriRowData(list);
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].compratore.value", equalTo("[S01] Anakin Skywalker")),
                withJsonPath("$.rowData[1].id.value", equalTo("2")),
                withJsonPath("$.rowData[1].compratore.value", equalTo("[S02] Ahsoka Tano"))
        )));
    }

    @Test(expected = NullPointerException.class)
    public void createPromoRiferimentoArticoliRowData_givenNullItems_shouldThrowException() {
        util.createPromoRiferimentoArticoliRowData(null);
    }

    @Test
    public void createPromoRiferimentoArticoliRowData_givenEmptyList_shouldReturnEmptyRowData() {
        final String rowData = util.createPromoRiferimentoArticoliRowData(new ArrayList<>());
        assertNotNull(rowData);
        assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
    }

    @Test
    public void createPromoRiferimentoArticoliRowData_givenListWithItems_shouldReturnPopulatedRowData() {
        final List<MuiPromoArticoliDbPromoEntity> list = new ArrayList<>();
        final MuiPromoArticoliDbPromoEntity item1 = mockItem("RIF_01", 23L, "M001",
                "10", "Omogenea", "S23");
        final MuiPromoArticoliDbPromoEntity item2 = mockItem("RIF_01", 10L, "M002",
                "20", "Omogenea", "S10");
        final MuiPromoArticoliDbPromoEntity item3 = mockItem("RIF_01", 42L, "M001",
                "30", "Differenziata Meccanica", "S42");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        final String rowData = util.createPromoRiferimentoArticoliRowData(list);
        assertNotNull(rowData);
        //noinspection unchecked
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(3)),
                withJsonPath("$.rowData[0].codicePromozione.value", equalTo("RIF_01")),
                withJsonPath("$.rowData[0].codiceItem.value", equalTo("23")),
                withJsonPath("$.rowData[0].tipoItem.value", equalTo("A")),
                withJsonPath("$.rowData[0].meccanica.value", equalTo("M001")),
                withJsonPath("$.rowData[0].offerta.value", equalTo("10")),
                withJsonPath("$.rowData[0].differenziazione.value", equalTo("Omogenea")),
                withJsonPath("$.rowData[0].codiceArticolo.value", equalTo("23")),
                withJsonPath("$.rowData[0].descrizioneArticolo.value", equalTo("ITEM 00023")),
                withJsonPath("$.rowData[0].codiceCompratore.value", equalTo("S23")),
                withJsonPath("$.rowData[0].descrizioneCompratore.value", equalTo("Compratore S23")),
                withJsonPath("$.rowData[1].codicePromozione.value", equalTo("RIF_01")),
                withJsonPath("$.rowData[1].codiceItem.value", equalTo("10")),
                withJsonPath("$.rowData[1].tipoItem.value", equalTo("A")),
                withJsonPath("$.rowData[1].meccanica.value", equalTo("M002")),
                withJsonPath("$.rowData[1].offerta.value", equalTo("20")),
                withJsonPath("$.rowData[1].differenziazione.value", equalTo("Omogenea")),
                withJsonPath("$.rowData[1].codiceArticolo.value", equalTo("10")),
                withJsonPath("$.rowData[1].descrizioneArticolo.value", equalTo("ITEM 00010")),
                withJsonPath("$.rowData[1].codiceCompratore.value", equalTo("S10")),
                withJsonPath("$.rowData[1].descrizioneCompratore.value", equalTo("Compratore S10")),
                withJsonPath("$.rowData[2].codicePromozione.value", equalTo("RIF_01")),
                withJsonPath("$.rowData[2].codiceItem.value", equalTo("42")),
                withJsonPath("$.rowData[2].tipoItem.value", equalTo("A")),
                withJsonPath("$.rowData[2].meccanica.value", equalTo("M001")),
                withJsonPath("$.rowData[2].offerta.value", equalTo("30")),
                withJsonPath("$.rowData[2].differenziazione.value", equalTo("Differenziata Meccanica")),
                withJsonPath("$.rowData[2].codiceArticolo.value", equalTo("42")),
                withJsonPath("$.rowData[2].descrizioneArticolo.value", equalTo("ITEM 00042")),
                withJsonPath("$.rowData[2].codiceCompratore.value", equalTo("S42")),
                withJsonPath("$.rowData[2].descrizioneCompratore.value", equalTo("Compratore S42"))
        )));
    }

    private MuiPromoArticoliDbPromoEntity mockItem(String codicePromozione, Long codiceItem, String codiceMeccanica,
                                                   String valoreOfferta, String tipoOfferta, String codiceCompratore) {
        final MuiPromoArticoliDbPromoId id = mock(MuiPromoArticoliDbPromoId.class);
        when(id.getCodicePromozione()).thenReturn(codicePromozione);
        when(id.getCodiceItem()).thenReturn(codiceItem);
        when(id.getTipoItem()).thenReturn("A");
        final MuiPromoArticoliDbPromoEntity mock = mock(MuiPromoArticoliDbPromoEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getCodiceMeccanica()).thenReturn(codiceMeccanica);
        when(mock.getValore()).thenReturn(valoreOfferta);
        when(mock.getTipoOfferta()).thenReturn(tipoOfferta);
        final CompratoreEntity compratore = mock(CompratoreEntity.class);
        when(compratore.getCodiceCompratore()).thenReturn(codiceCompratore);
        when(compratore.getDescrizione()).thenReturn(String.format("Compratore %s", codiceCompratore));
        final ItemEntity itemEntity = mock(ItemEntity.class);
        when(itemEntity.getCodiceItem()).thenReturn(String.valueOf(codiceItem));
        when(itemEntity.getDescrizione()).thenReturn(String.format("ITEM %05d", codiceItem));
        when(itemEntity.getCompratoreEntity()).thenReturn(compratore);
        when(mock.getItem()).thenReturn(itemEntity);
        return mock;
    }

    private CheckCompratoriEntity mockCheckCompratore(Long id, String description, String result,
                                                      Date insertDate, Date updateDate) {
        final CompratoreEntity c = mockCompratore(id, description);
        final CheckCompratoriEntity cc = mock(CheckCompratoriEntity.class);
        when(cc.getCompratoreEntity()).thenReturn(c);
        when(cc.getEsito()).thenReturn(result);
        when(cc.getDataAggiornamento()).thenReturn(updateDate);
        when(cc.getDataInserimento()).thenReturn(insertDate);
        return cc;
    }

    private CompratoreEntity mockCompratore(Long id, String description) {
        final CompratoreEntity c = mock(CompratoreEntity.class);
        when(c.getId()).thenReturn(id);
        when(c.getCodiceCompratore()).thenReturn(String.format("S%02d", id));
        when(c.getDescrizione()).thenReturn(description);
        return c;
    }
}