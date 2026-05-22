package com.axiante.mui.validator.service.impl;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.service.CreaPianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaValidatorServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<CreaPianoMediaService> creaPianoMediaServiceInstance;

    @Mock
    private CreaPianoMediaService creaPianoMediaService;

    @Mock
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Mock
    private PianoMediaPromoDbpromoService pianoMediaPromoService;

    @InjectMocks
    private PianoMediaValidatorServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(creaPianoMediaServiceInstance.get()).thenReturn(creaPianoMediaService);
        when(pianoMediaPromoServiceInstance.get()).thenReturn(pianoMediaPromoService);
    }

    @Test
    public void validateCreaPianoMedia_whenPayloadIsNull_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.validateCreaPianoMedia((String) null);
        verify(creaPianoMediaService, never()).findByUserIdAndSlotId(any(), any());
    }

    @Test
    public void validateCreaPianoMedia_whenJsonNodeIsNull_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.validateCreaPianoMedia((JsonNode) null);
        verify(creaPianoMediaService, never()).findByUserIdAndSlotId(any(), any());
    }

    @Test
    public void validateCreaPianoMedia_whenUserIdAndSlotIdInPayload_shouldReturnNull() {
        final String payload = mockPayload(null, null, null, null, null,
                null, null, null, null, null);
        assertNull(service.validateCreaPianoMedia(payload));
        verify(creaPianoMediaService, never()).findByUserIdAndSlotId(any(), any());
    }

    @Test
    public void validateCreaPianoMedia_whenNoEntityWithUserIdAndSlotId_shouldReturnCreatedEntityWithUserIdAndSlotId() {
        when(creaPianoMediaService.findByUserIdAndSlotId("junit", "001")).thenReturn(null);
        final String payload = mockPayload("junit", "001", null, null, null,
                null, null, null, null, null);
        final CreaPianoMediaEntity entity = service.validateCreaPianoMedia(payload);
        assertEquals("junit", entity.getUserId());
        assertEquals("001", entity.getSlotId());
        assertNull(entity.getPromoMaster());
        assertNull(entity.getDescrizione());
        assertNull(entity.getDataInizio());
        assertNull(entity.getDataFine());
        assertNull(entity.getPromoSecondaryA());
        assertNull(entity.getPromoSecondaryB());
        assertNull(entity.getPromoSecondaryC());
        verify(creaPianoMediaService, times(1)).findByUserIdAndSlotId("junit", "001");
    }

    @Test
    public void validateCreaPianoMedia_fromString_whenException_shouldReturnNull() {
        assertNull(service.validateCreaPianoMedia(""));
    }

    @Test
    public void validateCreaPianoMedia_fromJsonNode_whenException_shouldReturnNull() throws IOException {
        when(creaPianoMediaService.findByUserIdAndSlotId("junit", "001"))
                .thenThrow(new RuntimeException("FOO Exception"));
        final String payload = mockPayload("junit", "001", null, null, null,
                null, null, null, null, null);
        JsonNode jsonNode = JsonUtils.getMapper().readTree(payload);
        assertNull(service.validateCreaPianoMedia(jsonNode));
    }

    @Test
    public void validateCreaPianoMedia_whenAnnoInvalid_shouldSetAsNull() throws IOException {
        CreaPianoMediaEntity entity = new CreaPianoMediaEntity();
        entity.setAnno(2025);
        CreaPianoMediaEntity entitySpy = spy(entity);
        when(creaPianoMediaService.findByUserIdAndSlotId("junit", "001")).thenReturn(entitySpy);
        final String payload = mockPayload("junit", "001", "FOO", null, null,
                null, null, null, null, null);
        JsonNode jsonNode = JsonUtils.getMapper().readTree(payload);
        entity = service.validateCreaPianoMedia(jsonNode);
        assertEquals(2025, entity.getAnno().intValue());
        verify(creaPianoMediaService, times(1)).findByUserIdAndSlotId("junit", "001");
        verify(entitySpy, never()).setAnno(any());
    }

    @Test
    public void validateCreaPianoMedia_whenPromoMasterChangedAndHasValue_shouldChangeDescrizioneAnnoDataInizioDataFine() throws IOException {
        Date dataInizio = new GregorianCalendar(2025, Calendar.JULY, 1).getTime();
        Date dataFine = new GregorianCalendar(2025, Calendar.JULY, 10).getTime();
        final PianoMediaPromoDbpromoEntity promoMaster = mock(PianoMediaPromoDbpromoEntity.class);
        CreaPianoMediaEntity entity = new CreaPianoMediaEntity();
        entity.setPromoMaster("FOO");
        CreaPianoMediaEntity entitySpy = spy(entity);
        when(creaPianoMediaService.findByUserIdAndSlotId("junit", "001")).thenReturn(entitySpy);
        when(pianoMediaPromoService.findByCodicePromo("BAR")).thenReturn(promoMaster);
        when(promoMaster.getDescrizioneEstesa()).thenReturn("HELLO+WORLD");
        when(promoMaster.getDataInizio()).thenReturn(dataInizio);
        when(promoMaster.getDataFine()).thenReturn(dataFine);
        when(promoMaster.getAnno()).thenReturn(2025);
        final String payload = mockPayload("junit", "001", null, "BAR", null,
                null, null, null, null, null);
        JsonNode jsonNode = JsonUtils.getMapper().readTree(payload);
        entity = service.validateCreaPianoMedia(jsonNode);
        assertEquals("HELLO", entity.getDescrizione());
        assertEquals(dataInizio, entity.getDataInizio());
        assertEquals(dataFine, entity.getDataFine());
        assertEquals(2025, entity.getAnno().intValue());
        assertEquals("BAR", entity.getPromoMaster());
        verify(creaPianoMediaService, times(1)).findByUserIdAndSlotId("junit", "001");
        verify(pianoMediaPromoService, times(1)).findByCodicePromo("BAR");
        verify(entitySpy, times(1)).setDescrizione("HELLO");
        verify(entitySpy, times(1)).setDataInizio(dataInizio);
        verify(entitySpy, times(1)).setDataFine(dataFine);
        verify(entitySpy, times(1)).setAnno(2025);
        verify(entitySpy, times(1)).setPromoSecondaryA(null);
        verify(entitySpy, times(1)).setPromoSecondaryB(null);
        verify(entitySpy, times(1)).setPromoSecondaryC(null);
        verify(entitySpy, times(1)).setPromoMaster("BAR");
    }

    @Test
    public void validateCreaPianoMedia_whenPromoMasterChangedWithNone_shouldResetEntityFields() throws IOException {
        CreaPianoMediaEntity entity = new CreaPianoMediaEntity();
        entity.setPromoMaster("FOO");
        CreaPianoMediaEntity entitySpy = spy(entity);
        when(creaPianoMediaService.findByUserIdAndSlotId("junit", "001")).thenReturn(entitySpy);
        final String payload = mockPayload("junit", "001", null, null, null,
                null, null, null, null, null);
        JsonNode jsonNode = JsonUtils.getMapper().readTree(payload);
        entity = service.validateCreaPianoMedia(jsonNode);
        assertNull(entity.getDescrizione());
        assertNull(entity.getDataInizio());
        assertNull(entity.getDataFine());
        assertNull(entity.getAnno());
        assertNull(entity.getPromoSecondaryA());
        assertNull(entity.getPromoSecondaryB());
        assertNull(entity.getPromoSecondaryC());
        assertNull(entity.getPromoMaster());
        verify(creaPianoMediaService, times(1)).findByUserIdAndSlotId("junit", "001");
        verifyZeroInteractions(pianoMediaPromoService);
        verify(entitySpy, times(1)).setDescrizione(null);
        verify(entitySpy, times(1)).setDataInizio(null);
        verify(entitySpy, times(1)).setDataFine(null);
        verify(entitySpy, times(2)).setAnno(null);
        verify(entitySpy, times(1)).setPromoSecondaryA(null);
        verify(entitySpy, times(1)).setPromoSecondaryB(null);
        verify(entitySpy, times(1)).setPromoSecondaryC(null);
        verify(entitySpy, times(1)).setPromoMaster(null);
    }

    @Test
    public void validateCreaPianoMedia_whenPromoMasterNotChangedAndAllFields_shouldChangeAll() throws IOException {
        CreaPianoMediaEntity entity = new CreaPianoMediaEntity();
        entity.setPromoMaster("FOO");
        CreaPianoMediaEntity entitySpy = spy(entity);
        when(creaPianoMediaService.findByUserIdAndSlotId("junit", "001")).thenReturn(entitySpy);
        final String payload = mockPayload("junit", "001", "2025", "FOO", null,
                null, null, "SEC-A", "SEC-B", "SEC-C");
        JsonNode jsonNode = JsonUtils.getMapper().readTree(payload);
        entity = service.validateCreaPianoMedia(jsonNode);
        assertNull(entity.getDescrizione());
        assertNull(entity.getDataInizio());
        assertNull(entity.getDataFine());
        assertEquals(2025, entity.getAnno().intValue());
        assertEquals("SEC-A", entity.getPromoSecondaryA());
        assertEquals("SEC-B", entity.getPromoSecondaryB());
        assertEquals("SEC-C", entity.getPromoSecondaryC());
        assertEquals("FOO", entity.getPromoMaster());
        verify(creaPianoMediaService, times(1)).findByUserIdAndSlotId("junit", "001");
        verifyZeroInteractions(pianoMediaPromoService);
        verify(entitySpy, times(1)).setDescrizione(null);
        verify(entitySpy, times(1)).setDataInizio(null);
        verify(entitySpy, times(1)).setDataFine(null);
        verify(entitySpy, times(1)).setAnno(2025);
        verify(entitySpy, times(1)).setPromoSecondaryA("SEC-A");
        verify(entitySpy, times(1)).setPromoSecondaryB("SEC-B");
        verify(entitySpy, times(1)).setPromoSecondaryC("SEC-C");
        verify(entitySpy, times(1)).setPromoMaster("FOO");
    }

    private String mockPayload(String userId, String slotId, String anno, String promoRif, String descrizione,
                               String dataInizio, String dataFine, String promoSecA, String promoSecB, String promoSecC) {
        final String s = "{"
                + "'userId': {'value': '%s'},"
                + "'slotId': {'value': '%s'},"
                + "'anno': {'value': '%s'},"
                + "'promoRif': {'value': '%s'},"
                + "'descrizione': {'value': '%s'},"
                + "'dataInizio': {'value': '%s'},"
                + "'dataFine': {'value': '%s'},"
                + "'promoSecA': {'value': '%s'},"
                + "'promoSecB': {'value': '%s'},"
                + "'promoSecC': {'value': '%s'}"
                + "}";
        return String.format(s,
                userId != null ? userId : "",
                slotId != null ? slotId : "",
                anno != null ? anno : "",
                promoRif != null ? promoRif : "",
                descrizione != null ? descrizione : "",
                dataInizio != null ? dataInizio : "",
                dataFine != null ? dataFine : "",
                promoSecA != null ? promoSecA : "",
                promoSecB != null ? promoSecB : "",
                promoSecC != null ? promoSecC : "").replaceAll("'", "\"");
    }
}