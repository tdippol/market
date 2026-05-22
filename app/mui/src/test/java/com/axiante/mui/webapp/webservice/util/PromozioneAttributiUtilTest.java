package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.AttributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttributiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaliAttributiService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneAttributiService;
import java.util.Collections;
import java.util.Set;
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
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneAttributiUtilTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<CfgCanaliAttributiService> cfgCanaliAttributiServiceInstance;

    @Mock
    private CfgCanaliAttributiService cfgCanaliAttributiService;

    @Mock
    private Instance<PromozioneAttributiService> promozioneAttributiServiceInstance;

    @Mock
    private PromozioneAttributiService promozioneAttributiService;

    @InjectMocks
    @Spy
    private PromozioneAttributiUtil util;

    @Before
    public void setUp() throws Exception {
        when(cfgCanaliAttributiServiceInstance.get()).thenReturn(cfgCanaliAttributiService);
        when(promozioneAttributiServiceInstance.get()).thenReturn(promozioneAttributiService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createAttributiRowData_whenEditableFalse() {
        PromozioneAttributiEntity attributoPromo1 = mockAttributoPromo(1L, "FOO", "42");
        PromozioneAttributiEntity attributoPromo2 = mockAttributoPromo(2L, "BAR", "23");
        PromozioneAttributiEntity attributoPromo3 = mockAttributoPromo(3L, "BAZ", "10");
        Set<PromozioneAttributiEntity> attributiPromo = Stream.of(attributoPromo1, attributoPromo2, attributoPromo3)
                .collect(Collectors.toSet());
        final String rowData = util.createAttributiRowData(attributiPromo, 1L, false);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(3)),
                withJsonPath("$.rowData[0].id.value", equalTo("2")),
                withJsonPath("$.rowData[0].attributo.value", equalTo("BAR")),
                withJsonPath("$.rowData[0].attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[0].valore.value", equalTo("23")),
                withJsonPath("$.rowData[0].valore.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[0].valore.type", equalTo(DBPromoCellTypeEnum.STRING.getType())),
                withJsonPath("$.rowData[1].id.value", equalTo("3")),
                withJsonPath("$.rowData[1].attributo.value", equalTo("BAZ")),
                withJsonPath("$.rowData[1].attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[1].valore.value", equalTo("10")),
                withJsonPath("$.rowData[1].valore.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[1].valore.type", equalTo(DBPromoCellTypeEnum.STRING.getType())),
                withJsonPath("$.rowData[2].id.value", equalTo("1")),
                withJsonPath("$.rowData[2].attributo.value", equalTo("FOO")),
                withJsonPath("$.rowData[2].attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[2].valore.value", equalTo("42")),
                withJsonPath("$.rowData[2].valore.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[2].valore.type", equalTo(DBPromoCellTypeEnum.STRING.getType()))
        )));
        verifyZeroInteractions(cfgCanaliAttributiServiceInstance, cfgCanaliAttributiService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createAttributiRowData_whenEditableTrue() {
        PromozioneAttributiEntity attributoPromo1 = mockAttributoPromo(1L, "FOO", "42");
        PromozioneAttributiEntity attributoPromo2 = mockAttributoPromo(2L, "BAR", "23");
        PromozioneAttributiEntity attributoPromo3 = mockAttributoPromo(3L, "BAZ", "10");
        Set<PromozioneAttributiEntity> attributiPromo = Stream.of(attributoPromo1, attributoPromo2, attributoPromo3)
                .collect(Collectors.toSet());
        when(cfgCanaliAttributiService.getListaByCanaleAndAttributo(1L, 1L)).thenReturn(null);
        when(cfgCanaliAttributiService.getListaByCanaleAndAttributo(1L, 2L)).thenReturn("[A;B]");
        when(cfgCanaliAttributiService.getListaByCanaleAndAttributo(1L, 3L)).thenReturn("[1..3]");
        final String rowData = util.createAttributiRowData(attributiPromo, 1L, true);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(3)),
                withJsonPath("$.rowData[0].id.value", equalTo("2")),
                withJsonPath("$.rowData[0].attributo.value", equalTo("BAR")),
                withJsonPath("$.rowData[0].attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[0].valore.value", equalTo("23")),
                withJsonPath("$.rowData[0].valore.editable", equalTo(Boolean.TRUE)),
                withJsonPath("$.rowData[0].valore.type", equalTo(DBPromoCellTypeEnum.PICKLIST.getType())),
                withJsonPath("$.rowData[0].valore.picklistValues", hasSize(2)),
                withJsonPath("$.rowData[0].valore.picklistValues[0]", equalTo("A")),
                withJsonPath("$.rowData[0].valore.picklistValues[1]", equalTo("B")),
                withJsonPath("$.rowData[1].id.value", equalTo("3")),
                withJsonPath("$.rowData[1].attributo.value", equalTo("BAZ")),
                withJsonPath("$.rowData[1].attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[1].valore.value", equalTo("10")),
                withJsonPath("$.rowData[1].valore.editable", equalTo(Boolean.TRUE)),
                withJsonPath("$.rowData[1].valore.type", equalTo(DBPromoCellTypeEnum.PICKLIST.getType())),
                withJsonPath("$.rowData[1].valore.picklistValues", hasSize(3)),
                withJsonPath("$.rowData[1].valore.picklistValues[0]", equalTo("1")),
                withJsonPath("$.rowData[1].valore.picklistValues[1]", equalTo("2")),
                withJsonPath("$.rowData[1].valore.picklistValues[2]", equalTo("3")),
                withJsonPath("$.rowData[2].id.value", equalTo("1")),
                withJsonPath("$.rowData[2].attributo.value", equalTo("FOO")),
                withJsonPath("$.rowData[2].attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.rowData[2].valore.value", equalTo("42")),
                withJsonPath("$.rowData[2].valore.editable", equalTo(Boolean.TRUE)),
                withJsonPath("$.rowData[2].valore.type", equalTo(DBPromoCellTypeEnum.STRING.getType()))
        )));
        verify(cfgCanaliAttributiServiceInstance, times(3)).get();
        verify(cfgCanaliAttributiService, times(3)).getListaByCanaleAndAttributo(eq(1L), anyLong());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createOriginalRow() {
        final PromozioneAttributiEntity promoAttributo = mockAttributoPromo(1L, "FOO", "BAR");
        when(cfgCanaliAttributiService.getListaByCanaleAndAttributo(1L, 1L)).thenReturn("[A;B]");
        final String rowData = util.createOriginalRow(promoAttributo, "Foo", 1L, true);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.originalRow.id.value", equalTo("1")),
                withJsonPath("$.originalRow.attributo.value", equalTo("FOO")),
                withJsonPath("$.originalRow.attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.originalRow.valore.value", equalTo("BAR")),
                withJsonPath("$.originalRow.valore.editable", equalTo(Boolean.TRUE)),
                withJsonPath("$.originalRow.valore.type", equalTo(DBPromoCellTypeEnum.PICKLIST.getType())),
                withJsonPath("$.originalRow.valore.picklistValues", hasSize(2)),
                withJsonPath("$.originalRow.valore.picklistValues[0]", equalTo("A")),
                withJsonPath("$.originalRow.valore.picklistValues[1]", equalTo("B")),
                withJsonPath("$.message", equalTo("Foo"))
        )));
        verify(cfgCanaliAttributiServiceInstance, times(1)).get();
        verify(cfgCanaliAttributiService, times(1)).getListaByCanaleAndAttributo(1L, 1L);
    }

    @Test
    public void update_whenSuccessfull() {
        final CanalePromozioneEntity canale = mockCanale(1L);
        final PromozioneTestataEntity promozione = mockPromozione(canale);
        final PromozioneAttributiEntity promoAttributo = mockAttributoPromo(1L, "FOO", "BAR");
        when(promozione.getPromozioneAttributiEntity()).thenReturn(Collections.singleton(promoAttributo));
        when(cfgCanaliAttributiService.getListaByCanaleAndAttributo(1L, 1L)).thenReturn("[BAR;BAZ]");
        when(promozioneAttributiService.persistWithAuditLog(promoAttributo, "junit")).thenReturn(promoAttributo);
        assertTrue(util.update(promozione, promoAttributo, "BAZ", "junit"));
        verify(cfgCanaliAttributiServiceInstance, times(1)).get();
        verify(cfgCanaliAttributiService, times(1)).getListaByCanaleAndAttributo(1L, 1L);
        verify(promozioneAttributiServiceInstance, times(1)).get();
        verify(promozioneAttributiService, times(1)).persistWithAuditLog(promoAttributo, "junit");
    }

    @Test
    public void update_whenPromoAttributoNotAssociatedWithPromozione_shouldReturnFalse() {
        final CanalePromozioneEntity canale = mockCanale(1L);
        final PromozioneTestataEntity promozione = mockPromozione(canale);
        final PromozioneAttributiEntity promoAttributo1 = mockAttributoPromo(1L, "FOO", "BAR");
        final PromozioneAttributiEntity promoAttributo2 = mockAttributoPromo(2L, "BAZ", "SAMPLE");
        when(promozione.getPromozioneAttributiEntity()).thenReturn(Collections.singleton(promoAttributo2));
        assertFalse(util.update(promozione, promoAttributo1, "FOO", "junit"));
        verifyZeroInteractions(cfgCanaliAttributiServiceInstance, cfgCanaliAttributiService,
                promozioneAttributiServiceInstance, promozioneAttributiService);
    }

    @Test
    public void update_whenValoreAttributoNotContainedInList_shouldReturnFalse() {
        final CanalePromozioneEntity canale = mockCanale(1L);
        final PromozioneTestataEntity promozione = mockPromozione(canale);
        final PromozioneAttributiEntity promoAttributo = mockAttributoPromo(1L, "FOO", "BAR");
        when(promozione.getPromozioneAttributiEntity()).thenReturn(Collections.singleton(promoAttributo));
        when(cfgCanaliAttributiService.getListaByCanaleAndAttributo(1L, 1L)).thenReturn("[A;B]");
        assertFalse(util.update(promozione, promoAttributo, "FOO", "junit"));
        verify(cfgCanaliAttributiServiceInstance, times(1)).get();
        verify(cfgCanaliAttributiService, times(1)).getListaByCanaleAndAttributo(1L, 1L);
        verifyZeroInteractions(promozioneAttributiServiceInstance, promozioneAttributiService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createUpdatedRow() {
        final PromozioneAttributiEntity promoAttributo = mockAttributoPromo(1L, "FOO", "BAR");
        when(cfgCanaliAttributiService.getListaByCanaleAndAttributo(1L, 1L)).thenReturn("[A;B]");
        final String rowData = util.createUpdatedRow(promoAttributo, 1L, true);
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.id.value", equalTo("1")),
                withJsonPath("$.attributo.value", equalTo("FOO")),
                withJsonPath("$.attributo.editable", equalTo(Boolean.FALSE)),
                withJsonPath("$.valore.value", equalTo("BAR")),
                withJsonPath("$.valore.editable", equalTo(Boolean.TRUE)),
                withJsonPath("$.valore.type", equalTo(DBPromoCellTypeEnum.PICKLIST.getType())),
                withJsonPath("$.valore.picklistValues", hasSize(2)),
                withJsonPath("$.valore.picklistValues[0]", equalTo("A")),
                withJsonPath("$.valore.picklistValues[1]", equalTo("B"))
        )));
        verify(cfgCanaliAttributiServiceInstance, times(1)).get();
        verify(cfgCanaliAttributiService, times(1)).getListaByCanaleAndAttributo(1L, 1L);
        verifyZeroInteractions(promozioneAttributiServiceInstance, promozioneAttributiService);
    }

    private PromozioneAttributiEntity mockAttributoPromo(Long id, String description, String value) {
        final AttributiPromoEntity attrMock = mock(AttributiPromoEntity.class);
        when(attrMock.getId()).thenReturn(id);
        when(attrMock.getDescrizioneParametro()).thenReturn(description);
        final PromozioneAttributiEntity mock = mock(PromozioneAttributiEntity.class);
        when(mock.getAttributo()).thenReturn(attrMock);
        when(mock.getValore()).thenReturn(value);
        return mock;
    }

    private PromozioneTestataEntity mockPromozione(CanalePromozioneEntity canale) {
        final PromozioneTestataEntity mock = mock(PromozioneTestataEntity.class);
        when(mock.getMuiCanalePromozione()).thenReturn(canale);
        return mock;
    }

    private CanalePromozioneEntity mockCanale(Long id) {
        final CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
        when(mock.getId()).thenReturn(id);
        return mock;
    }
}