package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LinkHelperTest {
    private static final String LISTA = "[1..999]";

    @Mock
    PromozionePianificazioneEntity pianificazione;

    @Mock
    CanalePromozioneEntity canale;

    @Mock
    MeccanicheEntity meccanica;

    @Mock
    Instance<PianificazioneService> pianificazioneServiceInstance;

    @Mock
    PianificazioneService pianificazioneService;

    @InjectMocks
    LinkHelper linkHelper;

    @Before
    public void setUp() throws Exception {
        final PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        final PromozionePianificazioneEntity p1 = mockPianificazione("0001");
        final PromozionePianificazioneEntity p2 = mockPianificazione("0004");
        final PromozionePianificazioneEntity p3 = mockPianificazione("0005");
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(promozione);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(promozione.getAnno()).thenReturn("2024");
        when(promozione.getMuiCanalePromozione()).thenReturn(canale);
        when(pianificazioneServiceInstance.get()).thenReturn(pianificazioneService);
        when(pianificazioneService.findOverlappingByAnnoCanaleMeccanica(pianificazione, "2024", canale, meccanica))
                .thenReturn(Stream.of(p1, p2, p3).collect(Collectors.toList()));
    }

    @Test
    public void getAvailableValues() {
        final List<String> availableValues = linkHelper.getAvailableValues(LISTA, pianificazione);
        assertFalse(availableValues.isEmpty());
        assertEquals(996, availableValues.size());
        assertFalse(availableValues.contains("0001"));
        assertFalse(availableValues.contains("0004"));
        assertFalse(availableValues.contains("0005"));
        assertTrue(availableValues.contains("0002"));
        assertTrue(availableValues.contains("0003"));
        for (int i = 6; i <= 999; i++) {
            assertTrue(availableValues.contains(String.format("%04d", i)));
        }
        verify(pianificazioneServiceInstance, times(1)).get();
        verify(pianificazioneService, times(1))
                .findOverlappingByAnnoCanaleMeccanica(pianificazione, "2024", canale, meccanica);
    }

    private PromozionePianificazioneEntity mockPianificazione(String link) {
        final PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
        when(mock.getLink()).thenReturn(link);
        return mock;
    }
}