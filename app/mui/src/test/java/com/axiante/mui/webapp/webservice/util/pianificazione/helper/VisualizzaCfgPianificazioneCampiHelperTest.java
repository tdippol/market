package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VisualizzaCfgPianificazioneCampiHelperTest {

    @Spy
    private VisualizzaCfgPianificazioneCampiHelper helper;

    @Mock
    private CfgPianificazioneCampiEntity entity;

    @Before
    public void setUp() {
        // As excel: 44440.0
        final Date date1 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 1).getTime();
        // As excel: 44441.0
        final Date date2 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 2).getTime();
        when(entity.getDescrizione()).thenReturn("DESCRIZIONE");
        when(entity.getCodiceCampo()).thenReturn("CODICE");
        when(entity.getDescrizioneEstesa()).thenReturn("DESCRIZIONE ESTESA");
        when(entity.getRaggruppamento()).thenReturn("1");
        when(entity.getTipo()).thenReturn("STRING");
        when(entity.getCampo()).thenReturn("CAMPO");
        when(entity.getEntityLookup()).thenReturn("FooEntity");
        when(entity.getEntityAttribute()).thenReturn("FooAttribute");
        when(entity.getColumnWidth()).thenReturn(100);
        when(entity.getCodiceUtenteInserimento()).thenReturn("USER1");
        when(entity.getCodiceUtenteAggiornamento()).thenReturn("USER2");
        when(entity.getDataInserimento()).thenReturn(date1);
        when(entity.getDataAggiornamento()).thenReturn(date2);
    }

    @Test
    public void invokeGetterEntity_givenExistingField_shouldReturnItsValue() {
        assertEquals("DESCRIZIONE", helper.invokeGetterEntity("DESCRIZIONE", entity));
        assertEquals("CODICE", helper.invokeGetterEntity("CODICE_CAMPO", entity));
        assertEquals("DESCRIZIONE ESTESA", helper.invokeGetterEntity("DESCRIZIONE_ESTESA", entity));
        assertEquals("1", helper.invokeGetterEntity("RAGGRUPPAMENTO", entity));
        assertEquals("STRING", helper.invokeGetterEntity("TIPO", entity));
        assertEquals("CAMPO", helper.invokeGetterEntity("CAMPO", entity));
        assertEquals("FooEntity", helper.invokeGetterEntity("ENTITY_LOOKUP", entity));
        assertEquals("FooAttribute", helper.invokeGetterEntity("ENTITY_ATTRIBUTE", entity));
        assertEquals(100, helper.invokeGetterEntity("COLUMN_WIDTH", entity));
        assertEquals("USER1", helper.invokeGetterEntity("CODICE_UTENTE_INSERIMENTO", entity));
        assertEquals("USER2", helper.invokeGetterEntity("CODICE_UTENTE_AGGIORNAMENTO", entity));
        assertEquals("44440.0", helper.invokeGetterEntity("DATA_INSERIMENTO", entity));
        assertEquals("44441.0", helper.invokeGetterEntity("DATA_AGGIORNAMENTO", entity));
    }

    @Test
    public void invokeGetterEntity_givenExistingField_withNullDate_shouldReturnEmptyString() {
        when(entity.getDataInserimento()).thenReturn(null);
        when(entity.getDataAggiornamento()).thenReturn(null);
        assertEquals("", helper.invokeGetterEntity("DATA_INSERIMENTO", entity));
        assertEquals("", helper.invokeGetterEntity("DATA_AGGIORNAMENTO", entity));
    }

    @Test
    public void invokeGetterEntity_givenNonExistingFieldOrNull_shouldReturnNull() {
        assertNull(helper.invokeGetterEntity("WRONG_FIELD", entity));
        assertNull(helper.invokeGetterEntity(null, entity));
    }
}