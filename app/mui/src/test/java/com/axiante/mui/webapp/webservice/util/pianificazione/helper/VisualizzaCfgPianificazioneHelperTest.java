package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VisualizzaCfgPianificazioneHelperTest {
    @Mock
    private CfgPianificazioneEntity entity;

    @Spy
    private DateTimeUtils dateTimeUtils;

    @InjectMocks
    private VisualizzaCfgPianificazioneHelper helper;

    private List<String> allHeaders = Arrays.asList("DESCRIZIONE", "HIDE", "LISTA", "MANDATORY", "ORDINAMENTO",
            "SICUREZZA", "TIPO_LISTA", "UNIQUE_PERIODO_MECCANICA", "UNIQUE_PROMO", "WARN", "DEF_VALUE",
            "FLAG_MULTISELECT", "LENGTH", "CODICE_UTENTE_AGGIORNAMENTO", "CODICE_UTENTE_INSERIMENTO",
            "DATA_AGGIORNAMENTO", "DATA_INSERIMENTO", "FORMAT_STRING", "MIN_LENGTH", "ALLOW_ZERO",
            "VALIDO_SE_RAGGRUPPAMENTO");

    @Before
    public void setUp() {
        when(entity.getDescrizione()).thenReturn("FOO");
        when(entity.getHide()).thenReturn("FOO");
        when(entity.getLista()).thenReturn("[FOO;BAR]");
        when(entity.getMandatory()).thenReturn("FOO");
        when(entity.getOrdinamento()).thenReturn(1);
        when(entity.getSicurezza()).thenReturn("FOO");
        when(entity.getTipoLista()).thenReturn("FOO");
        when(entity.getUniquePeriodoMeccanica()).thenReturn("FOO");
        when(entity.getUniquePromo()).thenReturn("FOO");
        when(entity.getWarn()).thenReturn("FOO");
        when(entity.getDefValue()).thenReturn("FOO");
        when(entity.getMultiselect()).thenReturn((short) 1);
        when(entity.getLength()).thenReturn(42);
        when(entity.getCodiceUtenteAggiornamento()).thenReturn("FOO");
        when(entity.getCodiceUtenteInserimento()).thenReturn("FOO");
        when(entity.getDataAggiornamento()).thenReturn(null);
        when(entity.getDataInserimento()).thenReturn(null);
        when(entity.getFormatString()).thenReturn("FOO");
        when(entity.getMinLength()).thenReturn(42);
        when(entity.getAllowZero()).thenReturn(0);
        when(entity.getValidoSeRaggruppamento()).thenReturn(1);
    }

    @Test
    public void invokeGetterEntity_givenNullHeader_shouldReturnNull() {
        assertNull(helper.invokeGetterEntity(null, entity));
    }

    @Test
    public void invokeGetterEntity_givenValidHeader_shouldReturnValue() {
        for (String header : allHeaders) {
            assertNotNull(helper.invokeGetterEntity(header, entity));
        }
    }

    @Test
    public void invokeGetterEntity_givenValidHeader_shouldReturnEmptyString_whenPropertyIsnull() {
        when(entity.getFormatString()).thenReturn(null);
        when(entity.getMinLength()).thenReturn(null);
        when(entity.getAllowZero()).thenReturn(null);
        when(entity.getValidoSeRaggruppamento()).thenReturn(null);
        assertTrue(((String) helper.invokeGetterEntity("FORMAT_STRING", entity)).isEmpty());
        assertTrue(((String) helper.invokeGetterEntity("MIN_LENGTH", entity)).isEmpty());
        assertTrue(((String) helper.invokeGetterEntity("ALLOW_ZERO", entity)).isEmpty());
        assertTrue(((String) helper.invokeGetterEntity("VALIDO_SE_RAGGRUPPAMENTO", entity)).isEmpty());
    }

    @Test
    public void invokeGetterEntity_givenDataAggiornamentoHeader_shouldReturnValue() {
        final Date date = new Date();
        when(entity.getDataAggiornamento()).thenReturn(date);
        assertNotNull(helper.invokeGetterEntity("DATA_AGGIORNAMENTO", entity));
        verify(dateTimeUtils, times(1)).toExcelDate(date);
    }

    @Test
    public void invokeGetterEntity_givenDataInserimentoHeader_shouldReturnValue() {
        final Date date = new Date();
        when(entity.getDataInserimento()).thenReturn(date);
        assertNotNull(helper.invokeGetterEntity("DATA_INSERIMENTO", entity));
        verify(dateTimeUtils, times(1)).toExcelDate(date);
    }

    @Test
    public void invokeGetterEntity_givenNotExistentHeader_shouldReturnNull() {
        assertNull(helper.invokeGetterEntity("WRONG", entity));
    }
}
