package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VisualizzaPianificazioneHelperTest {
    @Mock
    private PromozionePianificazioneEntity entity;

    @Spy
    private DateTimeUtils dateTimeUtils;

    @InjectMocks
    private VisualizzaPianificazioneHelper helper;

    private List<String> allHeaders = Arrays.asList("BUDGET_PEZZI", "BUONO_SCONTO_PROGRESSIVO", "BUONO_SCONTO_RADICE",
            "CARTA_ORO", "CARTA_ORO_PRIVILEGIATE", "CARTA_VERDE", "CARTA_VERDE_PRIVILEGIATE", "CLASSE_DEFAULT",
            "CODICE_ELEMENTO", "DATA_FINE", "DATA_FINE_BARCODE", "DATA_INIZIO", "DESCRIZIONE_SCONTO", "ELEMENTO",
            "ELENCO_CLUSTER", "ESCLUSIONE", "FASCIA_ORARIA", "GENERA_BUONO_WEB", "GIORNO_SETTIMANA",
            "LETTERA_CODICE_ECOMMERCE", "MAX_GIORNO", "MAX_PERIODO", "MAX_SCONTRINO", "MOLTEPLICITA", "NOTE_TIMING",
            "NUM_RAGGRUPPAMENTO", "NUM_SET", "SCALA", "STAMPA_OFFERTA", "TARGET", "TIPO_ELEMENTO", "TIPO_RIGA",
            "TIPO_SOGLIA", "TIPO_TAGLIO", "TIPO_TETTO", "VALORE", "VALORE_SCALA", "VALORE_SOGLIA", "VALORE_TAGLIO",
            "VALORE_TETTO", "NOTE", "NUM_UTILIZZI", "CODICE_UTENTE_AGGIORNAMENTO", "CODICE_UTENTE_INSERIMENTO",
            "DATA_AGGIORNAMENTO", "DATA_INSERIMENTO", "CANALE_DISPOSITIVO", "CODICE_ON_LINE", "VALIDITA_PERIODO",
            "NUMERO_STAMPE", "MAGGIOR_VANTAGGIO", "INCLUDI_ESSELUNGA", "INCLUDI_ESSERBELLA", "INCLUDI_ATLANTIC",
            "INCLUDI_LAESSE", "SCONTO_RIFATTURABILE", "LINK", "SCONTO_CASSA", "PORTI_VIA", "PAGHI",
            "TIPO_SCONTO", "ORA_INIZIO", "ORA_FINE", "STAMPA_ETICHETTA");

    @Mock
    CfgPianificazTipoRigaEntity tipoRiga;
    
    @Before
    public void setUp() {
        final Date date = new GregorianCalendar(2021, Calendar.SEPTEMBER, 1).getTime();
        when(entity.getBudgetPezzi()).thenReturn(42);
        when(entity.getBuonoScontoProgressivo()).thenReturn(42);
        when(entity.getBuonoScontoRadice()).thenReturn(42);
        when(entity.getCartaOro()).thenReturn(BigDecimal.valueOf(1d));
        when(entity.getCartaOroPrivilegiate()).thenReturn(BigDecimal.valueOf(2d));
        when(entity.getCartaVerde()).thenReturn(BigDecimal.valueOf(0.5d));
        when(entity.getCartaVerdePrivilegiate()).thenReturn(BigDecimal.valueOf(0.7d));
        when(entity.getClasseDefault()).thenReturn(BigDecimal.valueOf(0d));
        when(entity.getCodiceElemento()).thenReturn("FOO");
        when(entity.getDataFine()).thenReturn(null);
        when(entity.getDataFineBarcode()).thenReturn(new Date());
        when(entity.getDataInizio()).thenReturn(null);
        when(entity.getDescrizioneSconto()).thenReturn("FOO");
        when(entity.getElemento()).thenReturn("FOO");
        when(entity.getElencoCluster()).thenReturn("FOO");
        when(entity.getEsclusione()).thenReturn("FOO");
        when(entity.getFasciaOraria()).thenReturn("FOO");
        when(entity.getGeneraBuonoWeb()).thenReturn("FOO");
        when(entity.getGiornoSettimana()).thenReturn("FOO");
        when(entity.getLetteraCodiceEcommerce()).thenReturn("FOO");
        when(entity.getMaxGiorno()).thenReturn(42);
        when(entity.getMaxPeriodo()).thenReturn(42);
        when(entity.getMaxScontrino()).thenReturn(42);
        when(entity.getMolteplicita()).thenReturn(42);
        when(entity.getNoteTiming()).thenReturn("FOO");
        when(entity.getNumRaggruppamento()).thenReturn("FOO");
        when(entity.getNumSet()).thenReturn("FOO");
        when(entity.getScala()).thenReturn("FOO");
        when(entity.getStampaOfferta()).thenReturn("FOO");
        when(entity.getTarget()).thenReturn("FOO");
        when(entity.getTipoElemento()).thenReturn("FOO");
        when(tipoRiga.getDescrizione()).thenReturn("FOO");
        when(entity.getTipoRiga()).thenReturn(tipoRiga);
        when(entity.getTipoSoglia()).thenReturn("FOO");
        when(entity.getTipoTaglio()).thenReturn("FOO");
        when(entity.getTipoTetto()).thenReturn("FOO");
        when(entity.getValore()).thenReturn(BigDecimal.valueOf(4200));
        when(entity.getValoreScala()).thenReturn(42);
        when(entity.getValoreSoglia()).thenReturn(BigDecimal.valueOf(4200));
        when(entity.getValoreTaglio()).thenReturn(BigDecimal.valueOf(4200));
        when(entity.getValoreTetto()).thenReturn(BigDecimal.valueOf(4200));
        when(entity.getNote()).thenReturn("FOO");
        when(entity.getNumUtilizzi()).thenReturn("FOO");
        when(entity.getCodiceUtenteAggiornamento()).thenReturn("FOO");
        when(entity.getCodiceUtenteInserimento()).thenReturn("FOO");
        when(entity.getDataAggiornamento()).thenReturn(new Date());
        when(entity.getDataInserimento()).thenReturn(new Date());
        when(entity.getCanaleDispositivo()).thenReturn("FOO");
        when(entity.getCodiceOnline()).thenReturn("FOO");
        when(entity.getValiditaPeriodo()).thenReturn(42);
        when(entity.getMaggiorVantaggio()).thenReturn("FOO");
        when(entity.getNumeroStampe()).thenReturn(42);
        when(entity.getIncludiEsselunga()).thenReturn("FOO");
        when(entity.getIncludiEsserbella()).thenReturn("FOO");
        when(entity.getIncludiAtlantic()).thenReturn("FOO");
        when(entity.getIncludiLaesse()).thenReturn("FOO");
        when(entity.getScontoRifatturabile()).thenReturn(42);
        when(entity.getLink()).thenReturn("LINK");
        when(entity.getScontoCassa()).thenReturn("42");
        when(entity.getPortiVia()).thenReturn(3);
        when(entity.getPaghi()).thenReturn(2);
        when(entity.getTipoSconto()).thenReturn("FOO");
        when(entity.getOraInizio()).thenReturn(date);
        when(entity.getOraFine()).thenReturn(date);
        when(entity.getStampaEtichetta()).thenReturn("BAR");
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
    public void invokeGetterEntity_givenDataFineHeader_shouldReturnValue() {
        final Date date = new Date();
        when(entity.getDataFine()).thenReturn(date);
        assertNotNull(helper.invokeGetterEntity("DATA_FINE", entity));
        verify(dateTimeUtils, times(1)).toExcelDate(date);
    }

    @Test
    public void invokeGetterEntity_givenDataInizioHeader_shouldReturnValue() {
        final Date date = new Date();
        when(entity.getDataInizio()).thenReturn(date);
        assertNotNull(helper.invokeGetterEntity("DATA_INIZIO", entity));
        verify(dateTimeUtils, times(1)).toExcelDate(date);
    }

    @Test
    public void invokeGetterEntity_givenNotExistentHeader_shouldReturnNull() {
        assertNull(helper.invokeGetterEntity("WRONG", entity));
    }
}
