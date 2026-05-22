package com.axiante.mui.webapp.webservice.util.pianificazione.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlanningCommonsTest {

    @InjectMocks
    private PlanningCommons planningCommons;

    @Test(expected = NullPointerException.class)
    public void isPlanningEditableCellAndRow_givenNullTestata_shouldThrowException() {
        planningCommons.isPlanningEditableCellAndRow(null);
    }

    @Test
    public void isPlanningEditableCellAndRow_givenTestataWithStatoCorrenteNotInStatiBlocco_shouldReturnTrue() {
        final StatoPromozioneEntity statoCorrente = mock(StatoPromozioneEntity.class);
        final StatoPromozioneEntity statoBlocco = mock(StatoPromozioneEntity.class);
        final PromozioneStatoEntity promoStato = mockPromoStato(statoCorrente);
        final CanalePromozioneEntity canale = mockCanale(statoBlocco);
        final PromozioneTestataEntity testata = mockTestata(promoStato, canale);
        assertTrue(planningCommons.isPlanningEditableCellAndRow(testata));
        verify(testata, times(1)).getMuiCanalePromozione();
    }

    @Test
    public void isPlanningEditableCellAndRow_givenTestataWithStatoCorrenteInStatiBlocco_shouldReturnFalse() {
        final StatoPromozioneEntity statoCorrenteAndBlocco = mock(StatoPromozioneEntity.class);
        final PromozioneStatoEntity promoStato = mockPromoStato(statoCorrenteAndBlocco);
        final CanalePromozioneEntity canale = mockCanale(statoCorrenteAndBlocco);
        final PromozioneTestataEntity testata = mockTestata(promoStato, canale);
        assertFalse(planningCommons.isPlanningEditableCellAndRow(testata));
        verify(testata, times(1)).getMuiCanalePromozione();
    }

    @Test
    public void defineCellType_givenListWithTwoOrMoreElements_shouldReturnListType() {
        assertEquals("picklist", planningCommons.defineCellType(null, Arrays.asList("FOO", "BAR"), null, null));
    }

    @Test
    public void defineCellType_givenDateField_shouldReturnDateType() {
        assertEquals("date", planningCommons.defineCellType(null, null, "DATA_INIZIO", null));
        assertEquals("date", planningCommons.defineCellType(null, null, "DATA_FINE", null));
    }

    @Test
    public void defineCellType_givenNumericType_shouldReturnNumericType() {
        assertEquals("numeric", planningCommons.defineCellType(null, null, null, "Integer"));
        assertEquals("numeric", planningCommons.defineCellType(null, null, null, "Number"));
    }

    @Test
    public void defineCellType_givenTimeType_shouldReturnTimeType() {
        assertEquals("time", planningCommons.defineCellType(null, null, null, "Time"));
    }

    @Test
    public void defineCellType_givenNoListNoDataNoNumeric_shouldReturnStringType() {
        assertEquals("string", planningCommons.defineCellType(null, null, null, "FOO"));
    }

    @Test
    public void defineDBPromoColumnType_givenListWithTwoOrMoreElements_shouldReturnStringType() {
        assertEquals("string", planningCommons.defineDBPromoColumnType(Arrays.asList("FOO", "BAR"), null, null));
    }

    @Test
    public void defineDBPromoColumnType_givenDateField_shouldReturnDateType() {
        assertEquals("date", planningCommons.defineDBPromoColumnType(null, "DATA_INIZIO", null));
        assertEquals("date", planningCommons.defineDBPromoColumnType(null, "DATA_FINE", null));
    }

    @Test
    public void defineDBPromoColumnType_givenNumericType_shouldReturnNumericType() {
        assertEquals("numeric", planningCommons.defineDBPromoColumnType(null, null, "42"));
        assertEquals("numeric", planningCommons.defineDBPromoColumnType(null, null, "42.42"));
    }

    @Test
    public void defineDBPromoColumnType_givenTimeType_shouldReturnTimeType() {
        assertEquals("time", planningCommons.defineDBPromoColumnType(null, null, "Time"));
    }

    @Test
    public void defineDBPromoColumnType_givenNoListNoDataNoNumeric_shouldReturnStringType() {
        assertEquals("string", planningCommons.defineDBPromoColumnType(null, null, "FOO"));
    }

    @Test
    public void checkSpecialHeader_givenHeaderAsElementoMeccanica_shouldReturnTrue() {
        final List<String> headers = Arrays.asList("TIPO_ELEMENTO", "ELEMENTO", "ID_MECCANICA");
        for (String header : headers) {
            assertTrue(planningCommons.checkSpecialHeader(header));
        }
    }

    @Test
    public void checkSpecialHeader_givenHeaderOtherThenElementoMeccanica_shouldReturnFalse() {
        final List<String> headers = Arrays.asList("FOO", "BAR");
        for (String header : headers) {
            assertFalse(planningCommons.checkSpecialHeader(header));
        }
    }

    @Test(expected = NullPointerException.class)
    public void checkColumnEditable_givenNullTestataAndValidSicurezza_shouldThrowException() {
        planningCommons.checkColumnEditable((PromozioneTestataEntity) null, "W");
    }

    @Test
    public void checkColumnEditable_givenWrongSicurezza_shouldReturnFalse() {
        final StatoPromozioneEntity statoCorrente = mock(StatoPromozioneEntity.class);
        final PromozioneStatoEntity promoStato = mockPromoStato(statoCorrente);
        final CanalePromozioneEntity canale = mockCanale(null);
        final PromozioneTestataEntity testata = mockTestata(promoStato, canale);
        assertFalse(planningCommons.checkColumnEditable(testata, "FOO"));
    }

    @Test(expected = NullPointerException.class)
    public void checkColumnEditable_givenNullTestata_shouldThrowException() {
        planningCommons.checkColumnEditable((PromozioneTestataEntity) null, "W", "NOTE");
    }

    @Test
    public void checkColumnEditable_givenWrongStato_shouldReturnTrue_whenFieldIsNoteAndSicurezzaIsEditable_elseFalse() {
        final StatoPromozioneEntity statoCorrenteAndBlocco = mockStatoPromo("31");
        final PromozioneStatoEntity promoStato = mockPromoStato(statoCorrenteAndBlocco);
        final CanalePromozioneEntity canale = mockCanale(statoCorrenteAndBlocco);
        final PromozioneTestataEntity testata = mockTestata(promoStato, canale);
        assertTrue(planningCommons.checkColumnEditable(testata, "W", "NOTE"));
        assertFalse(planningCommons.checkColumnEditable(testata, "W", "FOO"));
        assertFalse(planningCommons.checkColumnEditable(testata, "R", "NOTE"));
    }

    @Test
    public void checkColumnEditable_givenReadSecurity_shouldReturnFalse() {
        final StatoPromozioneEntity statoCorrente = mock(StatoPromozioneEntity.class);
        final PromozioneStatoEntity promoStato = mockPromoStato(statoCorrente);
        final CanalePromozioneEntity canale = mockCanale(null);
        final PromozioneTestataEntity testata = mockTestata(promoStato, canale);
        assertFalse(planningCommons.checkColumnEditable(testata, "R", null));
    }

    @Test
    public void checkColumnEditable_givenUnknownSecurity_shouldReturnFalse() {
        final StatoPromozioneEntity statoCorrente = mock(StatoPromozioneEntity.class);
        final PromozioneStatoEntity promoStato = mockPromoStato(statoCorrente);
        final CanalePromozioneEntity canale = mockCanale(null);
        final PromozioneTestataEntity testata = mockTestata(promoStato, canale);
        assertFalse(planningCommons.checkColumnEditable(testata, "UNK", null));
        assertFalse(planningCommons.checkColumnEditable(testata, null, null));
    }

    @Test
    public void checkColumnEditable_givenWriteSecurity_shouldReturnTrue() {
        final StatoPromozioneEntity statoCorrente = mock(StatoPromozioneEntity.class);
        final PromozioneStatoEntity promoStato = mockPromoStato(statoCorrente);
        final CanalePromozioneEntity canale = mockCanale(null);
        final PromozioneTestataEntity testata = mockTestata(promoStato, canale);
        assertTrue(planningCommons.checkColumnEditable(testata, "W", null));
    }

    @Test
    public void getBooleanHideValue_givenHideValue_shouldReturnTrue_elseFalse() {
        assertTrue(planningCommons.getBooleanHideValue("1"));
        assertFalse(planningCommons.getBooleanHideValue("0"));
        assertFalse(planningCommons.getBooleanHideValue(null));
        assertFalse(planningCommons.getBooleanHideValue("FOO"));
    }

    @Test
    public void isCellEditable_givenMasterTypeAndArticoloElemento_shouldReturnTrue_elseFalse() {
        final DBPromoAgCell cell = spy(DBPromoAgCell.class);
        // Good path
        HashMap<String, DBPromoAgCell> map = new HashMap<>();
        map.put("TIPO_ELEMENTO", cell);
        cell.setValue("ARTICOLO");
        assertTrue(planningCommons.isCellEditable("BUDGET_PEZZI", "FOO", "FOO", map));
        // Bad paths
        assertFalse(planningCommons.isCellEditable("BUDGET_PEZZI", "FOO", "BAR", map));
        map.put("TIPO_ELEMENTO", null);
        assertFalse(planningCommons.isCellEditable("BUDGET_PEZZI", "FOO", "FOO", map));
        cell.setValue("FOO");
        map.put("TIPO_ELEMENTO", cell);
        assertFalse(planningCommons.isCellEditable("BUDGET_PEZZI", "FOO", "FOO", map));
        // Also good paths
        map.put("FOO", cell);
        assertTrue(planningCommons.isCellEditable("FOO", "BAR", "BAZ", map));
        map.clear();
        assertTrue(planningCommons.isCellEditable("TIPO_ELEMENTO", "BAR", "BAZ", map));
        assertTrue(planningCommons.isCellEditable("FOO", "BAZ", "BAZ", map));
        // Also bad path
        assertFalse(planningCommons.isCellEditable("FOO", "BAR", "BAZ", map));
    }

    @Test
    public void isNoteEditable_givenValidStateAndFieldNote_shouldReturnTrue_elseFalse() {
        // Good paths
        assertTrue(planningCommons.isNoteEditable("NOTE", "31"));
        assertTrue(planningCommons.isNoteEditable("NOTE", "400"));
        // Bad paths
        assertFalse(planningCommons.isNoteEditable("FOO", "31"));
        assertFalse(planningCommons.isNoteEditable("FOO", "BAR"));
    }

    @Test
    public void getRowType_givenMasterCode_shouldReturnMaster() {
        assertEquals("MASTER", planningCommons.getRowType("m"));
    }

    @Test
    public void getRowType_givenDetailCode_shouldReturnDetail() {
        assertEquals("DETAIL", planningCommons.getRowType("d"));
    }

    @Test
    public void getRowType_givenSetCode_shouldReturnSet() {
        assertEquals("SET", planningCommons.getRowType("s"));
    }

    @Test
    public void getRowType_givenRaggruppamentoCode_shouldReturnRaggruppamento() {
        assertEquals("RAGGRUPPAMENTO", planningCommons.getRowType("r"));
    }

    @Test
    public void getRowType_givenElementoCode_shouldReturnElemento() {
        assertEquals("ELEMENTO", planningCommons.getRowType("e"));
    }

    @Test
    public void getRowType_givenNotMasterEitherDetail_shouldReturnNull() {
        assertNull(planningCommons.getRowType("FOO"));
    }

    @Test
    public void toValue_givenNullObject_shouldReturnNull() {
        assertNull(planningCommons.toValue(null, "FOO"));
    }

    @Test
    public void toValue_givenNullCellType_shouldReturnObjectAsString() {
        assertEquals("FOO", planningCommons.toValue("FOO", null));
    }

    @Test
    public void toValue_givenCellType_shouldReturnObjectValue() {
        final Date date = new GregorianCalendar(2021, Calendar.SEPTEMBER, 8).getTime();
        final String value = planningCommons.toValue(date, "TIME");
        assertEquals("00:00", value);
        assertEquals("10", planningCommons.toValue(10, "Integer"));
    }

    @Test
    public void overrideConfiguration_givenCfgWithoutRaggruppamentoFlag_shouldReturnFalse() {
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(cfg.getValidoSeRaggruppamento()).thenReturn(null);
        assertFalse(planningCommons.overrideConfiguration(cfg, mock(PromozionePianificazioneEntity.class)));
    }

    @Test
    public void overrideConfiguration_givenCfgWithRaggruppamentoNotEqualPianificazioneRaggr_shouldReturnTrue_whenLivelloPianificazioneIsSet() {
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        final CfgConfHeaderEntity cfgHeader = mock(CfgConfHeaderEntity.class);
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfg.getValidoSeRaggruppamento()).thenReturn(2);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(pianificazione.getNumRaggruppamento()).thenReturn("1");
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeader));
        when(cfgHeader.getMeccanicaEntity()).thenReturn(meccanica);
        when(cfgHeader.getLivelloPianificazione()).thenReturn(cfgLivello);
        when(cfgLivello.getCodice()).thenReturn("SET");
        assertTrue(planningCommons.overrideConfiguration(cfg, pianificazione));
    }

    @Test
    public void overrideConfiguration_givenCfgWithRaggruppamentoEqualPianificazioneRaggr_shouldReturnFalse_whenLivelloPianificazioneIsSet() {
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        final CfgConfHeaderEntity cfgHeader = mock(CfgConfHeaderEntity.class);
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfg.getValidoSeRaggruppamento()).thenReturn(2);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(pianificazione.getNumRaggruppamento()).thenReturn("2");
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeader));
        when(cfgHeader.getMeccanicaEntity()).thenReturn(meccanica);
        when(cfgHeader.getLivelloPianificazione()).thenReturn(cfgLivello);
        when(cfgLivello.getCodice()).thenReturn("SET");
        assertFalse(planningCommons.overrideConfiguration(cfg, pianificazione));
    }

    @Test
    public void overrideConfiguration_givenCfgWithRaggruppamentoFlag_shouldReturnFalse_whenLivelloPianificazioneIsNotSet() {
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        final CfgConfHeaderEntity cfgHeader = mock(CfgConfHeaderEntity.class);
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfg.getValidoSeRaggruppamento()).thenReturn(1);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeader));
        when(cfgHeader.getMeccanicaEntity()).thenReturn(meccanica);
        when(cfgHeader.getLivelloPianificazione()).thenReturn(cfgLivello);
        when(cfgLivello.getCodice()).thenReturn("FOO");
        assertFalse(planningCommons.overrideConfiguration(cfg, pianificazione));
    }

    private CanalePromozioneEntity mockCanale(StatoPromozioneEntity statoBlocco) {
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        if (statoBlocco != null) {
            when(canale.getStatiBlocco()).thenReturn(Collections.singleton(statoBlocco));
        } else {
            when(canale.getStatiBlocco()).thenReturn(Collections.emptySet());
        }
        return canale;
    }

    private PromozioneTestataEntity mockTestata(PromozioneStatoEntity promoStato, CanalePromozioneEntity canale) {
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        return testata;
    }

    private PromozioneStatoEntity mockPromoStato(StatoPromozioneEntity stato) {
        final PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
        when(promoStato.getDataFineStato()).thenReturn(null);
        when(promoStato.getStatoPromozioneEntity()).thenReturn(stato);
        return promoStato;
    }

    private StatoPromozioneEntity mockStatoPromo(String codiceStato) {
        final StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        when(stato.getCodiceStato()).thenReturn(codiceStato);
        return stato;
    }
}
