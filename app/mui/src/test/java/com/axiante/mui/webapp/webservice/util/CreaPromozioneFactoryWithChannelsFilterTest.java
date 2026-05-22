package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreaPromozioneFactoryWithChannelsFilterTest {

    @Spy
    private ComboBoxFactory comboFactory;

    @Mock
    private CreatePromotionService promotionService;

    @Spy
    @InjectMocks
    private CreaPromozioneFactory factory;

    // Useful channels
    private CanalePromozioneEntity canale1;     // Canale1 su gruppo1
    private CanalePromozioneEntity canale2;     // Canale2 su gruppo2
    private CanalePromozioneEntity canale3;     // Canale3 su gruppo3
    private CanalePromozioneEntity canale4;     // Canale4 su gruppo2
    // Useful promo
    private CreaPromozioneEntity promo1;        // Promo1 su gruppo1
    private CreaPromozioneEntity promo2;        // Promo2 su gruppo2

    @Before
    public void setUp() {
        canale1 = mockCanalePromozione(1);
        canale2 = mockCanalePromozione(2);
        canale3 = mockCanalePromozione(3);
        canale4 = mockCanalePromozione(4);
        final GruppoPromozioneEntity gruppo1 = mockGruppoPromozione(1, canale1);
        final GruppoPromozioneEntity gruppo2 = mockGruppoPromozione(2, canale2, canale4);
        final GruppoPromozioneEntity gruppo3 = mockGruppoPromozione(3, canale3);
        final GruppoPromozioneEntity gruppo4 = mockGruppoPromozione(4, canale4);
        when(canale1.getGruppoPromozioneEntity()).thenReturn(gruppo1);
        when(canale2.getGruppoPromozioneEntity()).thenReturn(gruppo2);
        when(canale3.getGruppoPromozioneEntity()).thenReturn(gruppo3);
        when(canale4.getGruppoPromozioneEntity()).thenReturn(gruppo2);
        final List<GruppoPromozioneEntity> allGroups = Arrays.asList(gruppo1, gruppo2, gruppo3, gruppo4);
        when(promotionService.getAllGroups()).thenReturn(allGroups);
        promo1 = mockCreaPromozione(1, "001", gruppo1);
        promo2 = mockCreaPromozione(2, "002", gruppo2);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowData_givenNullChannelsFilter_shouldGetAllGroups() throws Exception {
        final String rowData = factory.createRowData("1", Collections.emptyList(), "junit",
                null, null);
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues", hasSize(5)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 1")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[1].label", equalTo("GRUPPO 2")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[2].label", equalTo("GRUPPO 3")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[3].label", equalTo("GRUPPO 4")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[4].label", equalTo(""))
        )));
    }

    @Test
    public void createRowData_givenEmptyChannelsFilter_shouldGetEmptyGroups() throws Exception {
        final String rowData = factory.createRowData("1", Collections.emptyList(), "junit",
                Collections.emptyList(), Collections.emptyList());
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues", hasSize(1)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[0].label", equalTo(""))
        )));
    }

    @Test
    public void createRowData_givenPopulatedChannelsFilter_shouldGetAvailableGroupsFromCreatableChannelsCodes() throws Exception {
        final String rowData = factory.createRowData("1", Collections.emptyList(), "junit",
                Arrays.asList(canale1, canale4), Collections.singletonList(1L));
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 1")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[1].label", equalTo(""))
        )));
    }

    @Test
    public void createRowData_givenSingleChannelsFilter_shouldGetSingleGroup_whenChannelIsCreable() throws Exception {
        final String rowData = factory.createRowData("1", Collections.emptyList(), "junit",
                Collections.singletonList(canale3), Collections.singletonList(3L));
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 3")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[1].label", equalTo(""))
        )));
    }

    @Test
    public void createRowData_givenSingleChannelsFilter_shouldGetEmptyList_whenChannelIsNotCreable() throws Exception {
        final String rowData = factory.createRowData("1", Collections.emptyList(), "junit",
                Collections.singletonList(canale3), Collections.emptyList());
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues", hasSize(1)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[0].label", equalTo(""))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowData_givenPopulatedChannelsFilterAndManySlots_shouldGetAvailableGroups() throws Exception {
        final String rowData = factory.createRowData("5", Collections.emptyList(), "junit",
                Arrays.asList(canale2, canale4), Arrays.asList(2L, 4L));
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(5)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 2")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[1].label", equalTo("")),
                withJsonPath("$.rowData[1].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[1].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 2")),
                withJsonPath("$.rowData[1].gruppo.comboBoxValues[1].label", equalTo("")),
                withJsonPath("$.rowData[2].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[2].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 2")),
                withJsonPath("$.rowData[2].gruppo.comboBoxValues[1].label", equalTo("")),
                withJsonPath("$.rowData[3].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[3].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 2")),
                withJsonPath("$.rowData[3].gruppo.comboBoxValues[1].label", equalTo("")),
                withJsonPath("$.rowData[4].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[4].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 2")),
                withJsonPath("$.rowData[4].gruppo.comboBoxValues[1].label", equalTo(""))
        )));
    }

    // --- Con una promozione gia' in fase di creazione ---
    @Test
    public void createRowData_givenNullChannelsFilterAndCreaPromozione_shouldGetAllChannelsForSelectedGroup() throws Exception {
        when(promo1.getCanalePromozioneEntity()).thenReturn(canale1);
        final String rowData = factory.createRowData("1", Collections.singletonList(promo1),
                "junit", null, null);
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].canale.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[0].canale.comboBoxValues[0].label", equalTo("CANALE 1")),
                withJsonPath("$.rowData[0].canale.comboBoxValues[1].label", equalTo(""))
        )));
    }

    @Test
    public void createRowData_givenEmptyChannelsFilterAndCreaPromozione_shouldReturnNull() throws Exception {
        final String rowData = factory.createRowData("1", Collections.singletonList(promo1),
                "junit", Collections.emptyList(), Collections.emptyList());
        assertNull(rowData);
    }

    @Test
    public void createRowData_givenPopulatedChannelsFilterAndCreaPromozione_shouldGetOnlyCreatableChannelsForSelectedGroup() throws Exception {
        final String rowData = factory.createRowData("1", Collections.singletonList(promo1),
                "junit", Arrays.asList(canale1, canale4), Collections.singletonList(1L));
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].canale.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[0].canale.comboBoxValues[0].label", equalTo("CANALE 1")),
                withJsonPath("$.rowData[0].canale.comboBoxValues[1].label", equalTo(""))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowData_givenPopulatedChannelsFilterAndManySlotsAndManyCreaPromozione_shouldGetCreatableChannelsForSelectedGroup() throws Exception {
        final String rowData = factory.createRowData("5", Arrays.asList(promo1, promo2),
                "junit", Arrays.asList(canale1, canale2), Arrays.asList(1L, 2L));
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(5)),
                withJsonPath("$.rowData[0].canale.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[0].canale.comboBoxValues[0].label", equalTo("CANALE 1")),
                withJsonPath("$.rowData[0].canale.comboBoxValues[1].label", equalTo("")),
                withJsonPath("$.rowData[1].canale.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[1].canale.comboBoxValues[0].label", equalTo("CANALE 2")),
                withJsonPath("$.rowData[1].canale.comboBoxValues[1].label", equalTo(""))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createRowData_givenPopulatedChannelsFilterAndManySlotsAndCreaPromozione_shouldGetCreatableChannelsForSelectedGroup() throws Exception {
        final String rowData = factory.createRowData("5", Collections.singletonList(promo2),
                "junit", Arrays.asList(canale2, canale3, canale4), Collections.singletonList(2L));
        assertNotNull(rowData);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(5)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[0].label", equalTo("GRUPPO 2")),
                withJsonPath("$.rowData[0].gruppo.comboBoxValues[1].label", equalTo("")),
                withJsonPath("$.rowData[1].canale.comboBoxValues", hasSize(2)),
                withJsonPath("$.rowData[1].canale.comboBoxValues[0].label", equalTo("CANALE 2")),
                withJsonPath("$.rowData[1].canale.comboBoxValues[1].label", equalTo(""))
        )));
    }

    private CanalePromozioneEntity mockCanalePromozione(int i) {
        final CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
        when(mock.getId()).thenReturn((long) i);
        when(mock.getCodiceCanale()).thenReturn((long) i);
        when(mock.getLabel()).thenReturn(String.format("CANALE %d", i));
        when(mock.getKey()).thenReturn(String.valueOf(i));
        return mock;
    }

    private GruppoPromozioneEntity mockGruppoPromozione(int i, CanalePromozioneEntity... canali) {
        final GruppoPromozioneEntity mock = mock(GruppoPromozioneEntity.class);
        when(mock.getId()).thenReturn((long) i);
        when(mock.getCodiceGruppo()).thenReturn(String.format("GR%d", i));
        when(mock.getLabel()).thenReturn(String.format("GRUPPO %d", i));
        when(mock.getKey()).thenReturn(String.valueOf(i));
        when(mock.getMuiCanalePromoziones()).thenReturn(new HashSet<>(Arrays.asList(canali)));
        return mock;
    }

    private CreaPromozioneEntity mockCreaPromozione(int i, String slotId, GruppoPromozioneEntity group) {
        final CreaPromozioneEntity mock = mock(CreaPromozioneEntity.class);
        when(mock.getMessaggio()).thenReturn("Tutti i campi sono obbligatori");
        when(mock.getSlotId()).thenReturn(slotId);
        when(mock.getUserId()).thenReturn("junit");
        when(mock.getGruppoPromozioneEntity()).thenReturn(group);
        return mock;
    }
}
