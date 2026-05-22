package com.axiante.mui.dbpromo.persistence.dto;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CastellettoMessaggiDTOTest {
    @Mock
    private CastellettoMessaggiEntity entity;

    @Mock
    private PromozionePianificazioneEntity pianificazione;

    @Mock
    private MuiFontStileEntity fontStile;

    @Mock
    private MuiBottoneEntity bottone;

    CastellettoMessaggiDTO dto;
    final Date date = new Date();
    private MessaggiSezioneEnum sezione = MessaggiSezioneEnum.MESSAGGI;
    private MessaggiFontEnum font = MessaggiFontEnum.NORMALE;
    private MessaggiAllineamentoEnum allineamento = MessaggiAllineamentoEnum.SINISTRA;

    @Before
    public void setUp() throws Exception {
        when(bottone.getDescrizione()).thenReturn("Bottone");
        when(entity.getId()).thenReturn(1L);
        when(entity.getPianificazione()).thenReturn(pianificazione);
        when(entity.getSeqStampa()).thenReturn(1);
        when(entity.getSezione()).thenReturn(sezione);
        when(entity.getTesto()).thenReturn("Testo");
        when(entity.getTaglioCarta()).thenReturn(true);
        when(entity.getBarcode()).thenReturn(true);
        when(entity.getFont()).thenReturn(font);
        when(entity.getAllineamento()).thenReturn(allineamento);
        when(entity.getBold()).thenReturn(true);
        when(entity.getLogo()).thenReturn("Logo");
        when(entity.getCodiceUtenteAggiornamento()).thenReturn("CodiceUtenteAggiornamento");
        when(entity.getCodiceUtenteInserimento()).thenReturn("CodiceUtenteInserimento");
        when(entity.getDataAggiornamento()).thenReturn(date);
        when(entity.getDataInserimento()).thenReturn(date);
        when(entity.getCodiceCanaleDispositivo()).thenReturn("CodiceCanaleDispositivo");
        when(entity.getFontStile()).thenReturn(fontStile);
        when(entity.getBottone()).thenReturn(bottone);
        when(entity.getCodice()).thenReturn(true);
        when(entity.getRegolamento()).thenReturn("Regolamento");
        when(entity.getBarra()).thenReturn(true);
        when(entity.getIdMessaggio()).thenReturn(1);
        when(entity.getVariabile()).thenReturn("Variabile");
        when(entity.getFontEntity()).thenReturn("FontEntity");
        dto = new CastellettoMessaggiDTO(entity);
    }

    @Test
    public void toEntity() {
        CastellettoMessaggiEntity entity = dto.toEntity();
        assertNotNull(entity);
        assertEquals(1L, (long) entity.getId());
        assertEquals(pianificazione, entity.getPianificazione());
        assertEquals(1, (int) entity.getSeqStampa());
        assertEquals(sezione, entity.getSezione());
        assertEquals("Testo", entity.getTesto());
        assertTrue(entity.getTaglioCarta());
        assertTrue(entity.getBarcode());
        assertEquals(font, entity.getFont());
        assertEquals(allineamento, entity.getAllineamento());
        assertTrue(entity.getBold());
        assertEquals("Logo", entity.getLogo());
        assertEquals("CodiceUtenteAggiornamento", entity.getCodiceUtenteAggiornamento());
        assertEquals("CodiceUtenteInserimento", entity.getCodiceUtenteInserimento());
        assertEquals(date, entity.getDataAggiornamento());
        assertEquals(date, entity.getDataInserimento());
        assertEquals("CodiceCanaleDispositivo", entity.getCodiceCanaleDispositivo());
        assertEquals(fontStile, entity.getFontStile());
        assertEquals("Bottone", entity.getBottone().getDescrizione());
        assertTrue(entity.getCodice());
        assertEquals("Regolamento", entity.getRegolamento());
        assertTrue(entity.getBarcode());
        assertEquals(1, (int) entity.getIdMessaggio());
        assertEquals("Variabile", entity.getVariabile());
        assertEquals("FontEntity", entity.getFontEntity());
    }
}