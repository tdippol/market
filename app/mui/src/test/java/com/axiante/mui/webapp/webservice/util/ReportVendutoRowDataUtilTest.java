package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportVendutoRowDataUtilTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @InjectMocks
    private ReportVendutoRowDataUtil util;

    @Test
    public void createRowData_givenNullEntities_shouldThrowException() {
        ex.expect(NullPointerException.class);
        util.createRowData(null);
    }

    @Test
    public void createRowData_givenEmptyList_shouldReturnEmptyJsonArray() {
        final String rowData = util.createRowData(Collections.emptyList());
        assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowData() {
        List<ReportVendutoEntity> entities = new ArrayList<>();
        final ReportVendutoEntity row1 = mockEntity("I001", "Item 001", "S01", "Compratore 01",
                "F01", "Fornitore 01", "R01", "Reparto 01", "M001", "Meccanica 001");
        final ReportVendutoEntity row2 = mockEntity("I002", "Item 002", "S02", "Compratore 02",
                "F02", "Fornitore 02", "R02", "Reparto 02", "M002", "Meccanica 002");
        entities.add(row2);
        entities.add(row1);
        final String rowData = util.createRowData(entities);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].codiceArticolo.value", equalTo("I001")),
                withJsonPath("$.rowData[0].descrizioneArticolo.value", equalTo("Item 001")),
                withJsonPath("$.rowData[0].compratore.value", equalTo("[S01] Compratore 01")),
                withJsonPath("$.rowData[0].fornitore.value", equalTo("[F01] Fornitore 01")),
                withJsonPath("$.rowData[0].reparto.value", equalTo("[R01] Reparto 01")),
                withJsonPath("$.rowData[0].meccanica.value", equalTo("[M001] Meccanica 001")),
                withJsonPath("$.rowData[1].codiceArticolo.value", equalTo("I002")),
                withJsonPath("$.rowData[1].descrizioneArticolo.value", equalTo("Item 002")),
                withJsonPath("$.rowData[1].compratore.value", equalTo("[S02] Compratore 02")),
                withJsonPath("$.rowData[1].fornitore.value", equalTo("[F02] Fornitore 02")),
                withJsonPath("$.rowData[1].reparto.value", equalTo("[R02] Reparto 02")),
                withJsonPath("$.rowData[1].meccanica.value", equalTo("[M002] Meccanica 002"))
        )));
    }

    private ReportVendutoEntity mockEntity(String itemCode, String itemDesc, String buyerCode, String buyerDesc,
                                           String supplierCode, String supplierDesc, String depCode, String depDesc,
                                           String mechCode, String mechDesc) {
        final ReportVendutoId mockId = mock(ReportVendutoId.class);
        when(mockId.getCodiceItem()).thenReturn(itemCode);
        when(mockId.getDescrizioneItem()).thenReturn(itemDesc);
        when(mockId.getCodiceCompratore()).thenReturn(buyerCode);
        when(mockId.getDescrizioneCompratore()).thenReturn(buyerDesc);
        when(mockId.getCodiceFornitore()).thenReturn(supplierCode);
        when(mockId.getDescrizioneFornitore()).thenReturn(supplierDesc);
        when(mockId.getCodiceReparto()).thenReturn(depCode);
        when(mockId.getDescrizioneReparto()).thenReturn(depDesc);
        when(mockId.getCodiceMeccanica()).thenReturn(mechCode);
        when(mockId.getDescrizioneMeccanica()).thenReturn(mechDesc);
        final ReportVendutoEntity mock = mock(ReportVendutoEntity.class);
        when(mock.getId()).thenReturn(mockId);
        return mock;
    }
}