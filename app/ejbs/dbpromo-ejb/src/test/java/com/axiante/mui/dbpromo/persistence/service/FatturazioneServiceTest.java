package com.axiante.mui.dbpromo.persistence.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.ContributiArticoliDAO;
import com.axiante.mui.dbpromo.persistence.dao.ContributiPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.FatturazioneServiceImpl;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FatturazioneServiceTest {
    @Mock
    ContributiPromoDAO dao;

    @Mock
    ContributiArticoliDAO articoliDAO;
    @Spy
    @InjectMocks
    FatturazioneServiceImpl service;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFindAllByPromozioniReturnsEmptyWhenNullInput() {
        assertThat(service.findAllByPromozioni(null).isEmpty(), is(true));
    }

    @Test
    public void testFindAllByPromozioniReturnsEmptyWhenEmptyInput() {
        assertThat(service.findAllByPromozioni(Collections.emptyList()).isEmpty(), is(true));
    }

    @Test
    public void testFindAllByPromozioni() {
        List<PromozioneTestataEntity> list = Collections.singletonList(mock(PromozioneTestataEntity.class));
        service.findAllByPromozioni(list);
        verify(dao).findAllByPromozioni(list);
    }

    @Test
    public void testFindAllByPromozioneThrowsExceptionWhenNullInput() {
        expectedException.expect(NullPointerException.class);
        service.findAllByPromozione(null);
    }

    @Test
    public void testFindAllByPromozione(){
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        service.findAllByPromozione(promozione);
        verify(dao).findAllByPromozione(promozione);
    }

    @Test
    public void testCountByPromozioneAndCompratoreAndFornitoreThrowsExceptionWhenAnyInput(){
        expectedException.expect(NullPointerException.class);
        CompratoreEntity compratore = mock(CompratoreEntity.class);
        FornitoreEntity fornitore = mock(FornitoreEntity.class);
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);

        service.countByPromozioneAndCompratoreAndFornitore(null, null, null);
        service.countByPromozioneAndCompratoreAndFornitore(promozione, null, null);
        service.countByPromozioneAndCompratoreAndFornitore(promozione, compratore, null);
        service.countByPromozioneAndCompratoreAndFornitore(promozione, null, fornitore);
        service.countByPromozioneAndCompratoreAndFornitore(null, compratore, fornitore);
        service.countByPromozioneAndCompratoreAndFornitore(null, null, fornitore);
    }

    @Test
    public void testCountByPromozioneAndCompratoreAndFornitore(){
        CompratoreEntity compratore = mock(CompratoreEntity.class);
        FornitoreEntity fornitore = mock(FornitoreEntity.class);
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);

        service.countByPromozioneAndCompratoreAndFornitore(promozione, compratore, fornitore);
        verify(dao).countByPromozioneAndCompratoreAndFornitore(promozione, compratore, fornitore);
    }

    @Test
    public void testFindAllItemsIdByPromozioneThrowsExceptionWhenNullInput(){
        expectedException.expect(NullPointerException.class);
        service.findAllItemsIdByPromozione(null);
    }
    @Test
    public void testFindAllItemsIdByPromozione(){
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        service.findAllItemsIdByPromozione(promozione);
        verify(dao).findAllItemsIdByPromozione(promozione);
    }
    @Test
    public void testNextProgressivoByPromozioneAndCompratoreAndFornitoreThrowsExceptionWhenPromoInputIsNull(){
        expectedException.expect(NullPointerException.class);
        CompratoreEntity compratore = mock(CompratoreEntity.class);
        FornitoreEntity fornitore = mock(FornitoreEntity.class);

        service.nextProgressivoByPromozioneAndCompratoreAndFornitore(null, compratore, fornitore);
    }
    @Test
    public void testNextProgressivoByPromozioneAndCompratoreAndFornitoreThrowsExceptionWhenCompratoreInputIsNull(){
        expectedException.expect(NullPointerException.class);
        FornitoreEntity fornitore = mock(FornitoreEntity.class);
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);

        service.nextProgressivoByPromozioneAndCompratoreAndFornitore(promozione, null, fornitore);
    }
    @Test
    public void testNextProgressivoByPromozioneAndCompratoreAndFornitoreThrowsExceptionWhenFornitoreInputIsNull(){
        expectedException.expect(NullPointerException.class);
        CompratoreEntity compratore = mock(CompratoreEntity.class);
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);

        service.nextProgressivoByPromozioneAndCompratoreAndFornitore(promozione, compratore, null);
    }
    @Test
    public void testNextProgressivoByPromozioneAndCompratoreAndFornitore(){
        CompratoreEntity compratore = mock(CompratoreEntity.class);
        FornitoreEntity fornitore = mock(FornitoreEntity.class);
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);

        service.nextProgressivoByPromozioneAndCompratoreAndFornitore(promozione, compratore, fornitore);
        verify(dao).nextProgressivo(promozione, compratore, fornitore);
    }

    @Test
    public void testFindAllContributiArticoliByIdsReturnsEmptyWhenNullInput() {
        assertThat(service.findAllContributiArticoliByIds(null).isEmpty(), is(true));
    }

    @Test
    public void testFindAllContributiArticoliByIdsReturnsEmptyWhenEmptyInput() {
        assertThat(service.findAllContributiArticoliByIds(Collections.emptyList()).isEmpty(), is(true));
    }
    @Test
    public void testFindAllContributiArticoliByIds(){
        List<Long> ids = Collections.singletonList(1L);
        service.findAllContributiArticoliByIds(ids);
        verify(articoliDAO).findAllByIds(ids);
    }

    @Test
    public void testFindAllContributiArticoliByIdRataThrowsExceptionWhenNullInput(){
        expectedException.expect(NullPointerException.class);
        service.findAllContributiArticoliByIdRata(null);
    }

    @Test
    public void testFindAllContributiArticoliByIdRata(){
        Long idRata = 1L;
        service.findAllContributiArticoliByIdRata(idRata);
        verify(articoliDAO).findAllByIdRata(idRata);
    }
}
