package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.TipoRigaService;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromozionePianificazioneEntityHelperTest {
    private List<String> allColumns = Arrays.asList("BUDGET_PEZZI", "BUONO_SCONTO_PROGRESSIVO", "BUONO_SCONTO_RADICE",
            "CARTA_ORO", "CARTA_ORO_PRIVILEGIATE", "CARTA_VERDE", "CARTA_VERDE_PRIVILEGIATE", "CLASSE_DEFAULT",
            "CODICE_ELEMENTO", "DATA_FINE", "DATA_FINE_BARCODE", "DATA_INIZIO", "DESCRIZIONE_SCONTO", "ELEMENTO",
            "ELENCO_CLUSTER", "ESCLUSIONE", "FASCIA_ORARIA", "GENERA_BUONO_WEB", "GIORNO_SETTIMANA",
            "LETTERA_CODICE_ECOMMERCE", "MAX_GIORNO", "MAX_PERIODO", "MAX_SCONTRINO", "MOLTEPLICITA", "NOTE_TIMING",
            "NUM_RAGGRUPPAMENTO", "NUM_SET", "SCALA", "STAMPA_OFFERTA", "TARGET", "TIPO_ELEMENTO", "TIPO_RIGA",
            "TIPO_SOGLIA", "TIPO_TAGLIO", "TIPO_TETTO", "VALORE", "VALORE_SCALA", "VALORE_SOGLIA", "VALORE_TAGLIO",
            "VALORE_TETTO", "NOTE", "NUM_UTILIZZI", "CODICE_UTENTE_AGGIORNAMENTO", "CODICE_UTENTE_INSERIMENTO",
            "DATA_AGGIORNAMENTO", "DATA_INSERIMENTO", "CANALE_DISPOSITIVO", "CODICE_ON_LINE", "VALIDITA_PERIODO",
            "NUMERO_STAMPE", "MAGGIOR_VANTAGGIO", "INCLUDI_ESSELUNGA", "INCLUDI_ESSERBELLA", "INCLUDI_ATLANTIC",
            "INCLUDI_LAESSE", "SCONTO_RIFATTURABILE", "LINK", "SCONTO_CASSA", "PORTI_VIA", "PAGHI", "TIPO_SCONTO",
            "ORA_INIZIO", "ORA_FINE", "STAMPA_ETICHETTA");
    private List<Object> allValues = Arrays.asList("42", "42", "42", "5", "6", "7.2", "0.2", "0.21", "FOO",
            new Date(), new Date(), new Date(), "FOO", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO",
            "42", "42", "42", "42", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO", "FOO",
            "4200", "42", "4200", "4200", "4200", "FOO", "FOO", "FOO", "FOO", new Date(), new Date(), "FOO", "FOO",
            "42", "42", "FOO", "FOO", "FOO", "FOO", "FOO", "42", "FOO", "FOO", "3", "2", "FOO", "8:00", "10:00", "FOO");

    @Mock
    private TipoRigaService tipoRigaService;

    @InjectMocks
    private PromozionePianificazioneEntityHelper helper;

    @Spy
    private PromozionePianificazioneEntity entity;

    // Useful stuffs
    private Map<String, Field> fields;
    private Map<String, String> values;

    @Before
    public void setUp() throws NoSuchFieldException {
        fields = createFieldsMap();
        values = createValuesMap();
    }

    @Test(expected = NullPointerException.class)
    public void invokeSetter_givenNullEntity_shouldThrowException() {
        helper.invokeSetter(null, fields, values);
    }

    @Test(expected = NullPointerException.class)
    public void invokeSetter_givenNullFields_shouldThrowException() {
        helper.invokeSetter(entity, null, values);
    }

    @Test(expected = NullPointerException.class)
    public void invokeSetter_givenNullValues_shouldThrowException() {
        helper.invokeSetter(entity, fields, null);
    }

    @Test
    public void invokeSetter_givenFieldsAndValues_shouldPopulateFieldsOnEntity() {
        final Date dataInizio = new GregorianCalendar(2021, Calendar.APRIL, 27).getTime();
        final Date oraInizio = new GregorianCalendar(1970, Calendar.JANUARY, 1, 10, 30).getTime();
        helper.invokeSetter(entity, fields, values);
        assertEquals(10, (int) entity.getBudgetPezzi());
        assertEquals(new BigDecimal(42), entity.getValore());
        assertEquals("SCONTO FIDATY", entity.getDescrizioneSconto());
        assertEquals("NO", entity.getIncludiLaesse());
        assertEquals(dataInizio, entity.getDataInizio());
        assertEquals(oraInizio, entity.getOraInizio());
    }

    @Test
    public void invokeSetter_givenInvalidFieldsOrValues_shouldSkipGivenField() {
        values.put("BUDGET_PEZZI", "FOO");
        values.put("DATA_INIZIO", "42/27/2021");
        values.put("ORA_INIZIO", "42:42");
        helper.invokeSetter(entity, fields, values);
        assertNull(entity.getBudgetPezzi());
        assertEquals(new BigDecimal(42), entity.getValore());
        assertEquals("SCONTO FIDATY", entity.getDescrizioneSconto());
        assertEquals("NO", entity.getIncludiLaesse());
        assertNull(entity.getDataInizio());
        assertNull(entity.getOraInizio());
        values.put("DATA_INIZIO", "foo-bar");
        values.put("ORA_INIZIO", "baz:foo");
        helper.invokeSetter(entity, fields, values);
        assertNull(entity.getDataInizio());
        assertNull(entity.getOraInizio());
    }

    @Test
    public void invokeSetter_givenNullField_shouldNotSetValue() {
        fields.clear();
        fields.put("BUDGET_PEZZI", null);
        helper.invokeSetter(entity, fields, values);
        assertNull(entity.getBudgetPezzi());
    }

    @Test
    public void invokeSetter_givenNullValue_shouldNotSetValue() {
        values.clear();
        values.put("BUDGET_PEZZI", null);
        helper.invokeSetter(entity, fields, values);
        assertNull(entity.getBudgetPezzi());
    }

    @Test
    public void invokeSetter_givenUnmanagedField_shouldNotSetValue() throws NoSuchFieldException {
        fields.clear();
        fields.put("PARENT", PromozionePianificazioneEntity.class.getDeclaredField("parent"));
        values.clear();
        values.put("PARENT", "FOO");
        helper.invokeSetter(entity, fields, values);
        assertNull(entity.getParent());
    }

    @Test
    public void invokeSetterEntity_givenValidColumn_shouldReturnTrue() {
        IntStream.range(0, Math.min(allColumns.size(), allValues.size()))
                .forEach(i -> {
                    assertTrue(helper.invokeSetterEntity(entity, allColumns.get(i), null));
                    assertTrue(helper.invokeSetterEntity(entity, allColumns.get(i), allValues.get(i)));
                });
    }

    @Test
    public void invokeSetterEntity_givenNotExistentColumn_shouldReturnFalse() {
        assertFalse(helper.invokeSetterEntity(entity, "WRONG", "foo"));
    }

    @Test
    public void invokeSetterEntity_givenInvalidColumnValue_shouldReturnFalse() {
        assertFalse(helper.invokeSetterEntity(entity, "VALIDITA_PERIODO", "foo"));
        assertFalse(helper.invokeSetterEntity(entity, "VALIDITA_PERIODO", new Date()));
        assertFalse(helper.invokeSetterEntity(entity, "DATA_AGGIORNAMENTO", "foo"));
    }

    @Test
    public void invokeSetterEntity_givenNullColumn_shouldReturnTrue() {
        assertTrue(helper.invokeSetterEntity(entity, null, "foo"));
    }

    @Test
    public void adjustDates() {
        final Date dataInizioRoot = new GregorianCalendar(2021, Calendar.SEPTEMBER, 1).getTime();
        final Date dataFineRoot = new GregorianCalendar(2021, Calendar.SEPTEMBER, 30).getTime();
        final Date dataInizioChild1 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 5).getTime();
        final Date dataFineChild1 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 25).getTime();
        final Date dataInizioChild11 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 10).getTime();
        final Date dataFineChild11 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 15).getTime();
        final Date dataInizioChild12 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 15).getTime();
        final Date dataFineChild12 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 20).getTime();
        final Date dataInizioChild2 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 10).getTime();
        final Date dataFineChild2 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 20).getTime();
        final Date dataInizioChild21 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 15).getTime();
        final Date dataFineChild21 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 18).getTime();
        final Date dataInizioChild3 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 1).getTime();
        final Date dataFineChild3 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 20).getTime();
        final Date dataInizioChild31 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 15).getTime();
        final Date dataFineChild31 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 18).getTime();
        final Date dataInizioChild4 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 1).getTime();
        final Date dataFineChild4 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 30).getTime();
        final Date dataInizioChild41 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 1).getTime();
        final Date dataFineChild41 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 30).getTime();

        // Tree pianificazioni
        // |__ root             01-09-2021  30-09-2021
        //    |__ child1        05-09-2021  25-09-2021
        //    |  |__ child11    10-09-2021  15-09-2021
        //    |  |__ child12    15-09-2021  20-09-2021
        //    |__ child2        10-09-2021  20-09-2021
        //       |__ child21    15-09-2021  18-09-2021
        //    |__ child3        01-09-2021  20-09-2021
        //       |__ child31    15-09-2021  18-09-2021
        //    |__ child4        01-09-2021  30-09-2021
        //       |__ child41    01-09-2021  30-09-2021
        // Arrange
        final PromozionePianificazioneEntity pianificazioneRoot = spy(PromozionePianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazioneChild1 = spy(PromozionePianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazioneChild2 = spy(PromozionePianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazioneChild3 = spy(PromozionePianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazioneChild4 = spy(PromozionePianificazioneEntity.class);
        
        final PromozionePianificazioneEntity pianificazioneChild11 = spy(PromozionePianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazioneChild12 = spy(PromozionePianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazioneChild21 = spy(PromozionePianificazioneEntity.class);
        
        final PromozionePianificazioneEntity pianificazioneChild31 = spy(PromozionePianificazioneEntity.class);
        final PromozionePianificazioneEntity pianificazioneChild41 = spy(PromozionePianificazioneEntity.class);
        
        pianificazioneRoot.setDataInizio(dataInizioRoot);
        pianificazioneRoot.setDataFine(dataFineRoot);
        pianificazioneChild1.setDataInizio(dataInizioChild1);
        pianificazioneChild1.setDataFine(dataFineChild1);
        pianificazioneChild11.setDataInizio(dataInizioChild11);
        pianificazioneChild11.setDataFine(dataFineChild11);
        pianificazioneChild12.setDataInizio(dataInizioChild12);
        pianificazioneChild12.setDataFine(dataFineChild12);
        pianificazioneChild2.setDataInizio(dataInizioChild2);
        pianificazioneChild2.setDataFine(dataFineChild2);
        pianificazioneChild21.setDataInizio(dataInizioChild21);
        pianificazioneChild21.setDataFine(dataFineChild21);
        pianificazioneChild3.setDataInizio(dataInizioChild3);
        pianificazioneChild3.setDataFine(dataFineChild3);
        pianificazioneChild31.setDataInizio(dataInizioChild31);
        pianificazioneChild31.setDataFine(dataFineChild31);
        pianificazioneChild4.setDataInizio(dataInizioChild4);
        pianificazioneChild4.setDataFine(dataFineChild4);
        pianificazioneChild41.setDataInizio(dataInizioChild41);
        pianificazioneChild41.setDataFine(dataFineChild41);
        
        when(pianificazioneRoot.getChildren())
                .thenReturn(new HashSet<>(Arrays.asList(pianificazioneChild1, pianificazioneChild2, pianificazioneChild3, pianificazioneChild4)));
        when(pianificazioneChild1.getChildren())
                .thenReturn(new HashSet<>(Arrays.asList(pianificazioneChild11, pianificazioneChild12)));
        when(pianificazioneChild2.getChildren())
                .thenReturn(new HashSet<>(Collections.singletonList(pianificazioneChild21)));
        when(pianificazioneChild3.getChildren())
        	.thenReturn(new HashSet<>(Collections.singletonList(pianificazioneChild31)));
        when(pianificazioneChild4.getChildren())
        	.thenReturn(new HashSet<>(Collections.singletonList(pianificazioneChild41)));

        // Act - Modifica alle date sulla testata
        Date parentDataInizio = new GregorianCalendar(2021, Calendar.SEPTEMBER, 12).getTime();
        Date parentDataFine = new GregorianCalendar(2021, Calendar.SEPTEMBER, 23).getTime();
    	//public boolean adjustDates(@NonNull PromozionePianificazioneEntity pianificazione, Date vecchioInizio, Date vecchiaFine, boolean aggiornaInizio, boolean aggiornaFine, Date dataInizio, Date dataFine, final List<PromozionePianificazioneEntity> updatedPromo) {
        helper.adjustDates(pianificazioneRoot, pianificazioneRoot.getDataInizio(), pianificazioneRoot.getDataFine(), true, true, parentDataInizio, parentDataFine, null);
        
        // Assert
        // root --> cambiano entrambe le date
        assertEquals(parentDataInizio, pianificazioneRoot.getDataInizio());
        assertEquals(parentDataFine, pianificazioneRoot.getDataFine());
        // child1 --> non cambia
        assertNotEquals(parentDataInizio, pianificazioneChild1.getDataInizio());
        assertNotEquals(parentDataFine, pianificazioneChild1.getDataFine());
        // child11 --> non cambia
        assertEquals(dataInizioChild11, pianificazioneChild11.getDataInizio());
        assertEquals(dataFineChild11, pianificazioneChild11.getDataFine());
        // child12 --> non cambiano
        assertEquals(dataInizioChild12, pianificazioneChild12.getDataInizio());
        assertEquals(dataFineChild12, pianificazioneChild12.getDataFine());
        // child2 --> non cambia 
        assertEquals(dataInizioChild2, pianificazioneChild2.getDataInizio());
        assertEquals(dataFineChild2, pianificazioneChild2.getDataFine());
        // child21 --> non cambiano
        assertEquals(dataInizioChild21, pianificazioneChild21.getDataInizio());
        assertEquals(dataFineChild21, pianificazioneChild21.getDataFine());
        // child3 --> cambia data inizio
        assertEquals(parentDataInizio, pianificazioneChild3.getDataInizio());
        assertEquals(dataFineChild3, pianificazioneChild3.getDataFine());
        // child31 --> non cambiano
        assertEquals(dataInizioChild31, pianificazioneChild31.getDataInizio());
        assertEquals(dataFineChild31, pianificazioneChild31.getDataFine());
        // child4 --> cambiano entrambi
        assertEquals(parentDataInizio, pianificazioneChild4.getDataInizio());
        assertEquals(parentDataFine, pianificazioneChild4.getDataFine());
        // child41 --> cambiano entrambi
        assertEquals(parentDataInizio, pianificazioneChild41.getDataInizio());
        assertEquals(parentDataFine, pianificazioneChild41.getDataFine());
    }

    private Map<String, Field> createFieldsMap() throws NoSuchFieldException {
        final Map<String, Field> map = new HashMap<>();
        map.put("BUDGET_PEZZI", PromozionePianificazioneEntity.class.getDeclaredField("budgetPezzi"));
        map.put("VALORE", PromozionePianificazioneEntity.class.getDeclaredField("valore"));
        map.put("DESCRIZIONE_SCONTO", PromozionePianificazioneEntity.class.getDeclaredField("descrizioneSconto"));
        map.put("INCLUDI_LAESSE", PromozionePianificazioneEntity.class.getDeclaredField("includiLaesse"));
        map.put("DATA_INIZIO", PromozionePianificazioneEntity.class.getDeclaredField("dataInizio"));
        map.put("ORA_INIZIO", PromozionePianificazioneEntity.class.getDeclaredField("oraInizio"));
        return map;
    }

    private Map<String, String> createValuesMap() {
        final Map<String, String> map = new HashMap<>();
        map.put("BUDGET_PEZZI", "10");
        map.put("VALORE", "42");
        map.put("DESCRIZIONE_SCONTO", "SCONTO FIDATY");
        map.put("INCLUDI_LAESSE", "NO");
        map.put("DATA_INIZIO", "27/04/2021");
        map.put("ORA_INIZIO", "10:30");
        return map;
    }
}
