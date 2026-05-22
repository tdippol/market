package com.axiante.mui.webapp.business.data;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaDettaglioDTOTest {

    @Spy
    PianoMediaDettaglioDTO underTest;

    @Test
    public void testInizializzaDTOSemplice() {
        PianoMediaDettaglioDTO t = new PianoMediaDettaglioDTO();
        assertNull(t.getDettaglioPianificazione());
        assertNull(t.articoloLookup);
        assertNull(t.dettaglioArticoloLookup);
    }

    @Test
    public void testInizializzaDTOConDettaglio() {
        PianoMediaPianificazioneDettaglioEntity dettaglio = mock(PianoMediaPianificazioneDettaglioEntity.class);
        PianoMediaDettaglioDTO t = new PianoMediaDettaglioDTO(dettaglio, null, null);
        assertEquals(dettaglio, t.getDettaglioPianificazione());
        assertNull(t.articoloLookup);
        assertNotNull(t.dettaglioArticoloLookup);
        assertThat(t.dettaglioArticoloLookup.size(), is(0));
        assertThat(t.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testInizializzaDTOConArticolo() {
        PianoMediaPromoArticoliDbPromoEntity articoliDbPromoEntity = mock(PianoMediaPromoArticoliDbPromoEntity.class);
        PianoMediaDettaglioDTO t = new PianoMediaDettaglioDTO(null, articoliDbPromoEntity, null);
        assertNull(t.getDettaglioPianificazione());
        assertNotNull(t.articoloLookup);
        assertThat(t.articoloLookup, equalTo(articoliDbPromoEntity));
        assertNotNull(t.dettaglioArticoloLookup);
        assertThat(t.dettaglioArticoloLookup.size(), is(0));
        assertThat(t.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testInizializzaDTOConDettaglioArticolo() {
        List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglioArticolo = new ArrayList<>();
        dettaglioArticolo.add(mock(PianoMediaPromoArticoliDettaglioDbPromoEntity.class));
        PianoMediaDettaglioDTO t = new PianoMediaDettaglioDTO(null, null, dettaglioArticolo);
        assertNull(t.getDettaglioPianificazione());
        assertNull(t.articoloLookup);
        assertNotNull(t.dettaglioArticoloLookup);
        assertThat(t.dettaglioArticoloLookup.size(), equalTo(1));
        assertThat(t.isNuovaPianificazione(), is(false));
    }


    @Test
    public void testSetDettaglioArticoloLookup() {
        List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglioArticolo = new ArrayList<>();
        dettaglioArticolo.add(mock(PianoMediaPromoArticoliDettaglioDbPromoEntity.class));
        underTest.setDettaglioArticoloLookup(dettaglioArticolo);
        assertNotNull(underTest.dettaglioArticoloLookup);
        assertThat(underTest.dettaglioArticoloLookup.size(), equalTo(1));
    }


    @Test
    public void testSetCodicePromozioneOnANewDTO() {
        doReturn(null).when(underTest).getArticoloLookup();
        underTest.setCodicePromozione("codicePromozione");
        assertNotNull(underTest.getDettaglioPianificazione());
        assertThat(underTest.getCodicePromozione(), equalTo("codicePromozione"));
        assertThat(underTest.getCodicePromozione(), equalTo(underTest.getDettaglioPianificazione().getCodicePromozione()));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testSetCodicePromozioneOnExistingDTO() {
        String s = "codicePromozione";
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        underTest.setCodicePromozione(s);
        assertNotNull(underTest.getDettaglioPianificazione());
        assertThat(underTest.getCodicePromozione(), equalTo("codicePromozione"));
        assertThat(underTest.getCodicePromozione(), equalTo(underTest.getDettaglioPianificazione().getCodicePromozione()));
        assertThat(underTest.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testSetCodiceItemOnANewDTO() {
        PianoMediaDettaglioDTO underTest = new PianoMediaDettaglioDTO();
        underTest.setCodiceItem("codicePromozione");
        assertNotNull(underTest.getCodiceItem());
        assertThat(underTest.getCodiceItem(), equalTo("codicePromozione"));
        assertThat(underTest.getCodiceItem(), equalTo(underTest.getDettaglioPianificazione().getCodiceItem()));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testSetCodiceItemOnAExistingDTO() {
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        underTest.setCodiceItem("codicePromozione");
        assertNotNull(underTest.getCodiceItem());
        assertThat(underTest.getCodiceItem(), equalTo("codicePromozione"));
        assertThat(underTest.getCodiceItem(), equalTo(underTest.getDettaglioPianificazione().getCodiceItem()));
        assertThat(underTest.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testSetTipoItemOnANewDTO() {
        PianoMediaDettaglioDTO underTest = new PianoMediaDettaglioDTO();
        underTest.setTipoItem("A");
        assertNotNull(underTest.getTipoItem());
        assertThat(underTest.getTipoItem(), equalTo("A"));
        assertThat(underTest.getTipoItem(), equalTo(underTest.getDettaglioPianificazione().getTipoItem()));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testSetTipoItemOnAExistingDTO() {
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        underTest.setTipoItem("A");
        assertNotNull(underTest.getTipoItem());
        assertThat(underTest.getTipoItem(), equalTo("A"));
        assertThat(underTest.getTipoItem(), equalTo(underTest.getDettaglioPianificazione().getTipoItem()));
        assertThat(underTest.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testSetPianoMediaCreatesANewEntry() {
        underTest.setPianoMedia(mock(PianoMediaEntity.class));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testArticoloRemovedIsTrueWhenLookupIsNull() {
        underTest.setArticoloLookup(null);
        assertThat(underTest.articoloRemoved(), is(true));
    }

    @Test
    public void testValidateReturnsTrueOnNewDTOWhenDettaglioIsComplete() {
        underTest.setTipoItem("A");
        assertThat(underTest.validateDettaglio(), is(false));
        underTest.setCodiceItem("codiceItem");
        assertThat(underTest.validateDettaglio(), is(false));
        underTest.setCodicePromozione("codicePromozione");
        assertThat(underTest.validateDettaglio(), is(false));
        underTest.setPianoMedia(mock(PianoMediaEntity.class));
        assertThat(underTest.validateDettaglio(), is(true));
    }

    @Test
    public void testValidateReturnsTrueOnExistingDTOWhenDettaglioIsComplete() {
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        assertThat(underTest.validateDettaglio(), is(false));
        underTest.setTipoItem("A");
        assertThat(underTest.validateDettaglio(), is(false));
        underTest.setCodiceItem("codiceItem");
        assertThat(underTest.validateDettaglio(), is(false));
        underTest.setCodicePromozione("codicePromozione");
        assertThat(underTest.validateDettaglio(), is(false));
        underTest.setPianoMedia(mock(PianoMediaEntity.class));
        assertThat(underTest.validateDettaglio(), is(true));
    }

//    @Test
//    public void testCanAddReturnsTrueOnNewDTOWhenDettaglioIsComplete() {
//        underTest.setTipoItem("A");
//        underTest.setCodiceItem("codiceItem");
//        underTest.setCodicePromozione("codicePromozione");
//        underTest.setPianoMedia(mock(PianoMediaEntity.class));
//        assertThat(underTest.canAddDettaglio(), is(true));
//    }

//    @Test
//    public void testCanAddReturnsFalseOnExistingDTO() {
//        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
//        underTest.setDettaglioPianificazione(piano);
//        assertThat(underTest.canAddDettaglio(), is(false));
//        underTest.setTipoItem("A");
//        assertThat(underTest.canAddDettaglio(), is(false));
//        underTest.setCodiceItem("codiceItem");
//        assertThat(underTest.canAddDettaglio(), is(false));
//        underTest.setCodicePromozione("codicePromozione");
//        assertThat(underTest.canAddDettaglio(), is(false));
//        underTest.setPianoMedia(mock(PianoMediaEntity.class));
//        assertThat(underTest.canAddDettaglio(), is(false));
//    }

    @Test
    public void testIsCivettaReturnsFalseOnNewDTO() {
        assertThat(underTest.isCivetta(), is(false));
    }

    @Test
    public void testSetCivettaOnNewDTO() {
        assertThat(underTest.isCivetta(), is(false));
        underTest.setCivetta(true);
        assertThat(underTest.isCivetta(), is(true));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testSetCivettaOnExistingDTO() {
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        underTest.setCivetta(true);
        assertThat(underTest.isCivetta(), is(true));
        assertThat(underTest.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testSetPezzoRankOnNewDTO() {
        assertNull(underTest.getPezzoRank());
        underTest.setPezzoRank(1);
        assertThat(underTest.getPezzoRank(), is(1));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testSetPezzoRankOnExistingDTO() {
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        assertNull(underTest.getPezzoRank());
        underTest.setPezzoRank(1);
        assertThat(underTest.getPezzoRank(), is(1));
        assertThat(underTest.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testSetFatturatoPezzoRankOnNewDTO() {
        assertNull(underTest.getFatturatoRank());
        underTest.setFatturatoRank(1);
        assertThat(underTest.getFatturatoRank(), is(1));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testSetFatturatoRankOnExistingDTO() {
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        assertNull(underTest.getFatturatoRank());
        underTest.setFatturatoRank(1);
        assertThat(underTest.getFatturatoRank(), is(1));
        assertThat(underTest.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testSetNoteOnNewDTO() {
        assertNull(underTest.getFatturatoRank());
        underTest.setNoteCompratore("note");
        assertThat(underTest.getNoteCompratore(), is("note"));
        assertThat(underTest.isNuovaPianificazione(), is(true));
    }

    @Test
    public void testSetNoteExistingDTO() {
        PianoMediaPianificazioneDettaglioEntity piano = new PianoMediaPianificazioneDettaglioEntity();
        underTest.setDettaglioPianificazione(piano);
        assertNull(underTest.getNoteCompratore());
        underTest.setNoteCompratore("note");
        assertThat(underTest.getNoteCompratore(), is("note"));
        assertThat(underTest.isNuovaPianificazione(), is(false));
    }

    @Test
    public void testSettersOnPianificazioneAttributesCallUnderlyingMethods() {
        PianoMediaPianificazioneDettaglioEntity piano = spy(new PianoMediaPianificazioneDettaglioEntity());
        underTest.setDettaglioPianificazione(piano);
        underTest.setNoteCompratore("note");
        verify(piano, times(1)).setNoteCompratore("note");
        underTest.setCivetta(true);
        verify(piano, times(1)).setCivetta(true);
        underTest.setPezzoRank(1);
        verify(piano, times(1)).setPezzoRank(1);
        underTest.setFatturatoRank(1);
        verify(piano, times(1)).setFatturatoRank(1);
        underTest.setCodiceItem("codiceItem");
        verify(piano, times(1)).setCodiceItem("codiceItem");
        underTest.setCodicePromozione("codicePromozione");
        verify(piano, times(1)).setCodicePromozione("codicePromozione");
        underTest.setTipoItem("A");
        verify(piano, times(1)).setTipoItem("A");
    }

    @Test
    public void testWhenListaDettaglioArticoliIsSetThenListIsConverted() {
        List<PianoMediaPromoArticoliDettaglioDbPromoEntity> list = new ArrayList<>();
        underTest.setDettaglioArticoloLookup(list);
        verify(underTest, times(1)).from(list);
    }

    @Test
    public void testWhenDifferenziatoPerVolantinoThenFlVolantinoIsTrue(){
        underTest.setDettaglioArticoloLookup(generateDifferenziatoPerVolantino("codiceItem", "codicePromozione", "A"));
        assertThat(underTest.isDifferenziato(), is(true));
        assertThat(underTest.isFlVolantino(), is(true));
    }

    @Test
    public void testWhenDifferenziatoPerDataInizioThenDataInizioIsMin(){
        underTest.setDettaglioArticoloLookup(generateDifferenziatoPerDataInizio("codiceItem", "codicePromozione", "A"));
        assertThat(underTest.isDifferenziato(), is(true));
        assertThat(underTest.getDataInizio(), is(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()));
    }
    @Test
    public void testWhenDifferenziatoPerDataFineThenDataInizioIsMin() {
        underTest.setDettaglioArticoloLookup(generateDifferenziatoPerDataInizio("codiceItem", "codicePromozione", "A"));
        assertThat(underTest.isDifferenziato(), is(true));
        assertThat(underTest.getDataFine(), is(new GregorianCalendar(2019, Calendar.FEBRUARY, 28).getTime()));
    }
    private List<PianoMediaPromoArticoliDettaglioDbPromoEntity> generateDifferenziatoPerVolantino(String codiceItem, String codicePromozione, String tipoItem) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.JANUARY, 1);
        Date dataInizio = calendar.getTime();
        calendar.set(2019, Calendar.JANUARY, 31);
        Date dataFine = calendar.getTime();
        String codiceCondizione = "codiceCondizione";
        String codiceMeccanica = "codiceMeccanica";
        String valore = "valore";
        String codiceSocieta="codiceSocieta";

        List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglio = new ArrayList<>();
        dettaglio.add(getOne(codiceItem, codicePromozione, tipoItem, codiceCondizione, codiceMeccanica, codiceSocieta, "codiceZona", valore, true, dataInizio, dataFine));
        dettaglio.add(getOne(codiceItem, codicePromozione, tipoItem, codiceCondizione, codiceMeccanica, codiceSocieta, "codiceZona1", valore, false, dataInizio, dataFine));
        return dettaglio;
    }
    private List<PianoMediaPromoArticoliDettaglioDbPromoEntity> generateDifferenziatoPerDataInizio(String codiceItem, String codicePromozione, String tipoItem) {
        Date dataInizio = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        Date dataFine = new GregorianCalendar(2019, Calendar.FEBRUARY, 28).getTime();
        String codiceCondizione = "codiceCondizione";
        String codiceMeccanica = "codiceMeccanica";
        String codiceSocieta = "codiceSocieta";
        String valore = "valore";
        boolean volantino = true;

        List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglio = new ArrayList<>();
        dettaglio.add(getOne(codiceItem, codicePromozione, tipoItem, codiceCondizione, codiceMeccanica, codiceSocieta, "codiceZona", valore, volantino, dataInizio, dataFine));
        Date dataInizio2 = new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime();
        dettaglio.add(getOne(codiceItem, codicePromozione, tipoItem, codiceCondizione, codiceMeccanica, codiceSocieta, "codiceZona1", valore, volantino, dataInizio2, dataFine));
        return dettaglio;
    }

    private List<PianoMediaPromoArticoliDettaglioDbPromoEntity> generateDifferenziatoPerDataFine(String codiceItem, String codicePromozione, String tipoItem) {
        Date dataInizio = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        Date dataFine = new GregorianCalendar(2019, Calendar.FEBRUARY, 28).getTime();
        String codiceCondizione = "codiceCondizione";
        String codiceMeccanica = "codiceMeccanica";
        String codiceSocieta = "codiceSocieta";
        String valore = "valore";
        boolean volantino = true;

        List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglio = new ArrayList<>();
        dettaglio.add(getOne(codiceItem, codicePromozione, tipoItem, codiceCondizione, codiceMeccanica, codiceSocieta, "codiceZona", valore, volantino, dataInizio, dataFine));
        Date dataFine2 = new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime();
        dettaglio.add(getOne(codiceItem, codicePromozione, tipoItem, codiceCondizione, codiceMeccanica, codiceSocieta, "codiceZona1", valore, volantino, dataInizio, dataFine2));
        return dettaglio;
    }

    private PianoMediaPromoArticoliDettaglioDbPromoEntity getOne(String codiceItem, String codicePromozione,
                                                                 String tipoItem, String codiceCondizione,
                                                                 String codiceMeccanica, String codiceSocieta,
                                                                 String codiceZona, String valore, boolean volantino,
                                                                 Date dataInizio, Date dataFine) {
        PianoMediaPromoArticoliDettaglioDbPromoEntity dettaglio = new PianoMediaPromoArticoliDettaglioDbPromoEntity();

        dettaglio.setCodiceItem(codiceItem);
        dettaglio.setCodicePromozione(codicePromozione);
        dettaglio.setTipoItem(tipoItem);

        dettaglio.setCodiceCondizione(codiceCondizione);
        dettaglio.setCodiceMeccanica(codiceMeccanica);
        dettaglio.setCodiceSocieta(codiceSocieta);
        dettaglio.setCodiceZona(codiceZona);

        dettaglio.setFlVolantino(volantino);
        dettaglio.setValore(valore);
        dettaglio.setDataInizio(dataInizio);
        dettaglio.setDataFine(dataFine);

        return dettaglio;

    }

}
