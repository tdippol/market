package com.axiante.mui.webapp.views.content.dbpromo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.junit.Test;

public class UploadComplementariTest {

    @Test
    public void testWhenCanaleIsNullThenCreateFileNameReturnsNull() {
        UploadComplementari uploadComplementari = new UploadComplementari();
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(canale.getCodiceCanale()).thenReturn(null);
        uploadComplementari.setPianificazione(pianificazione);

        assertNull(uploadComplementari.createFileName(new Date()));
    }

    @Test
    public void testWhenCanaleIsNotBpOrBcOrPcOrCommOrLocThenCreateFileNameReturnsNull() throws ParseException {
        UploadComplementari uploadComplementari = spy(new UploadComplementari());
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        uploadComplementari.setPianificazione(pianificazione);
        // any value different from 12, 13, 14, 19 or 20
        List<Long> codiciCanale = generateListWithExclusions(1L, 99L, 12L, 13L, 14L, 19L, 20L);
        for (Long codiceCanale : codiciCanale) {
            when(canale.getCodiceCanale()).thenReturn(codiceCanale);
            assertNull(uploadComplementari.createFileName(new Date()));
        }
        // any value from 12, 13, 14, 19 or 20
        final String date = "01/01/1999";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        when(uploadComplementari.getDialogDataInizioPubblicazione()).thenReturn(sdf.parse(date));
        when(uploadComplementari.getDialogDataFinePubblicazione()).thenReturn(sdf.parse(date));
        when(testata.getCodicePromozione()).thenReturn("123456");
        MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicheEntity);
        when(meccanicheEntity.getCodiceMeccanica()).thenReturn("123456");
        when(pianificazione.getBuonoScontoRadice()).thenReturn(913);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        when(uploadComplementari.getDescrizione()).thenReturn("descrizione");
        codiciCanale = Arrays.asList(12L, 13L, 14L, 19L, 20L);
        for (Long codiceCanale : codiciCanale) {
            when(canale.getCodiceCanale()).thenReturn(codiceCanale);
            assertNotNull(uploadComplementari.createFileName(new Date()));
        }
    }

    @Test
    public void testWhenCanaleIsBuoniPotenziamentoThenCreateFileNameReturnsCorrectValue() throws ParseException {
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(canale.getCodiceCanale()).thenReturn(12L);
        UploadComplementari uploadComplementari = spy(new UploadComplementari());
        final String date = "01/01/1999";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        when(uploadComplementari.getDialogDataInizioPubblicazione()).thenReturn(sdf.parse(date));
        when(uploadComplementari.getDialogDataFinePubblicazione()).thenReturn(sdf.parse(date));
        when(testata.getCodicePromozione()).thenReturn("123456");

        MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicheEntity);
        when(meccanicheEntity.getCodiceMeccanica()).thenReturn("123456");
        when(pianificazione.getBuonoScontoRadice()).thenReturn(913);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        when(uploadComplementari.getDescrizione()).thenReturn("descrizione");
        uploadComplementari.setPianificazione(pianificazione);

        String formatter = "%s-%s-%s-%s-%s%s-%s.txt";
        //Se BUONO_SCONTO_RADICE = 3 cifre E BUONO_SCONTO_PROGRESSIVO = 1 cifra la MUI NON deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        String expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "913",
                "1",
                "descrizione");
        String actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Radice 3 e progressivo 1 non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

        //Se BUONO_SCONTO_RADICE = 2 cifre E BUONO_SCONTO_PROGRESSIVO = 1 cifra la MUI deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        when(pianificazione.getBuonoScontoRadice()).thenReturn(91);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "91",
                "01",
                "descrizione");
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Radice 2 e progressivo 1 non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

        //Se BUONO_SCONTO_RADICE = 2 cifre E BUONO_SCONTO_PROGRESSIVO = 2 cifra la MUI NON deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        when(pianificazione.getBuonoScontoRadice()).thenReturn(91);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(12);
        expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "91",
                "12",
                "descrizione");
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Radice 2 e progressivo 2 non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

    }

    @Test
    public void testWhenCanaleIsBuoniCategoriaThenCreateFileNameReturnsCorrectValue() throws ParseException {
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(canale.getCodiceCanale()).thenReturn(13L);
        UploadComplementari uploadComplementari = spy(new UploadComplementari());

        final String date = "01/01/1999";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        when(uploadComplementari.getDialogDataInizioPubblicazione()).thenReturn(sdf.parse(date));
        when(uploadComplementari.getDialogDataFinePubblicazione()).thenReturn(sdf.parse(date));
        when(testata.getCodicePromozione()).thenReturn("123456");

        MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicheEntity);
        when(meccanicheEntity.getCodiceMeccanica()).thenReturn("123456");
        when(pianificazione.getBuonoScontoRadice()).thenReturn(974);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        when(uploadComplementari.getDescrizione()).thenReturn("descrizione");

        // legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(true);
        uploadComplementari.setPianificazione(pianificazione);

        String formatter = "%s-%s-%s-%s%s.txt";
        //Se BUONO_SCONTO_RADICE = 3 cifre E BUONO_SCONTO_PROGRESSIVO = 1 cifra la MUI deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        String expected = String.format(formatter,
                "01011999",
                "123456",
                "123456",
                "974",
                "01");
        String actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Legacy naming convention con radice 3 e progressivo 1 non combacia", expected, org.hamcrest.CoreMatchers.is(actual));
        //Se BUONO_SCONTO_RADICE = 3 cifre E BUONO_SCONTO_PROGRESSIVO = 2 cifra la MUI NON deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(12);
        expected = String.format(formatter,
                "01011999",
                "123456",
                "123456",
                "974",
                "12");
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Legacy naming convention con radice 3 e progressivo 2 non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

        // non legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(false);
        uploadComplementari.setPianificazione(pianificazione);
        formatter = "%s-%s-%s-%s-%s%s-%s.txt";
        //Se BUONO_SCONTO_RADICE = 3 cifre E BUONO_SCONTO_PROGRESSIVO = 1 cifra la MUI deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "974",
                "01",
                "descrizione");

        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Non legacy naming convention con radice 3 e progressivo 1 non combacia", expected, org.hamcrest.CoreMatchers.is(actual));
        //Se BUONO_SCONTO_RADICE = 3 cifre E BUONO_SCONTO_PROGRESSIVO = 2 cifra la MUI NON deve paddare uno 0 nel BUONO_SCONTO_PROGRESSIVO
        expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "974",
                "12",
                "descrizione");

        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(12);
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Non legacy naming convention con radice 3 e progressivo 2 non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

    }

    @Test
    public void testWhenCanaleIsPrecaricatiSuCartaThenCreateFileNameReturnsCorrectValue() throws ParseException {
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(canale.getCodiceCanale()).thenReturn(14L);
        UploadComplementari uploadComplementari = spy(new UploadComplementari());

        final String date = "01/01/1999";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        when(uploadComplementari.getDialogDataInizioPubblicazione()).thenReturn(sdf.parse(date));
        when(uploadComplementari.getDialogDataFinePubblicazione()).thenReturn(sdf.parse(date));
        when(testata.getCodicePromozione()).thenReturn("123456");

        MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicheEntity);
        when(meccanicheEntity.getCodiceMeccanica()).thenReturn("123456");
        when(pianificazione.getBuonoScontoRadice()).thenReturn(974);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        when(uploadComplementari.getDescrizione()).thenReturn("descrizione");
        when(pianificazione.getNumSet()).thenReturn("12");
        // legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(true);
        uploadComplementari.setPianificazione(pianificazione);

        String formatter = "%s-%s-%s-%s.txt";

        String expected = String.format(formatter,
                "01011999",
                "123456",
                "123456",
                "12");
        String actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Legacy naming convention non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

        // non legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(false);
        uploadComplementari.setPianificazione(pianificazione);
        formatter = "%s-%s-%s-%s-%s-%s.txt";
        expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "12",
                "descrizione");
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Non legacy naming convention non combacia", expected, org.hamcrest.CoreMatchers.is(actual));
    }

    @Test
    public void testWhenCanaleIsCommercialeThenCreateFileNameReturnsCorrectValue() throws ParseException {
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(canale.getCodiceCanale()).thenReturn(19L);
        UploadComplementari uploadComplementari = spy(new UploadComplementari());

        final String date = "01/01/1999";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        when(uploadComplementari.getDialogDataInizioPubblicazione()).thenReturn(sdf.parse(date));
        when(uploadComplementari.getDialogDataFinePubblicazione()).thenReturn(sdf.parse(date));
        when(testata.getCodicePromozione()).thenReturn("123456");

        MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicheEntity);
        when(meccanicheEntity.getCodiceMeccanica()).thenReturn("123456");
        when(pianificazione.getBuonoScontoRadice()).thenReturn(974);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        when(uploadComplementari.getDescrizione()).thenReturn("descrizione");
        when(pianificazione.getNumSet()).thenReturn("12");
        // legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(true);
        uploadComplementari.setPianificazione(pianificazione);

        String formatter = "%s-%s-%s-%s.txt";

        String expected = String.format(formatter,
                "01011999",
                "123456",
                "123456",
                "12");
        String actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Legacy naming convention non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

        // non legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(false);
        uploadComplementari.setPianificazione(pianificazione);
        formatter = "%s-%s-%s-%s-%s-%s.txt";
        expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "12",
                "descrizione");
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Non legacy naming convention non combacia", expected, org.hamcrest.CoreMatchers.is(actual));
    }

    @Test
    public void testWhenCanaleIsLocaleThenCreateFileNameReturnsCorrectValue() throws ParseException {
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(canale.getCodiceCanale()).thenReturn(20L);
        UploadComplementari uploadComplementari = spy(new UploadComplementari());

        final String date = "01/01/1999";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        when(uploadComplementari.getDialogDataInizioPubblicazione()).thenReturn(sdf.parse(date));
        when(uploadComplementari.getDialogDataFinePubblicazione()).thenReturn(sdf.parse(date));
        when(testata.getCodicePromozione()).thenReturn("123456");

        MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicheEntity);
        when(meccanicheEntity.getCodiceMeccanica()).thenReturn("123456");
        when(pianificazione.getBuonoScontoRadice()).thenReturn(974);
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(1);
        when(uploadComplementari.getDescrizione()).thenReturn("descrizione");
        when(pianificazione.getNumSet()).thenReturn("12");
        // legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(true);
        uploadComplementari.setPianificazione(pianificazione);

        String formatter = "%s-%s-%s-%s.txt";

        String expected = String.format(formatter,
                "01011999",
                "123456",
                "123456",
                "12");
        String actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Legacy naming convention non combacia", expected, org.hamcrest.CoreMatchers.is(actual));

        // non legacy naming convention
        when(canale.isLegacyUploadNamingConvention()).thenReturn(false);
        uploadComplementari.setPianificazione(pianificazione);
        formatter = "%s-%s-%s-%s-%s-%s.txt";
        expected = String.format(formatter,
                "01011999",
                "01011999",
                "123456",
                "123456",
                "12",
                "descrizione");
        actual = uploadComplementari.createFileName(sdf.parse(date));
        assertThat("Non legacy naming convention non combacia", expected, org.hamcrest.CoreMatchers.is(actual));
    }

    protected List<Long> generateListWithExclusions(Long start, Long end, Long... exclusions) {
        return LongStream.rangeClosed(1, 99).boxed()
                .filter(r -> Arrays.stream(exclusions).noneMatch(r::equals))
                .collect(Collectors.toList());
    }
}
