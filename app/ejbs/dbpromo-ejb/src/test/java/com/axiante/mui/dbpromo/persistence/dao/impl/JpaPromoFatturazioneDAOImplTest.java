package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.FornitoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JpaPromoFatturazioneDAOImplTest extends AbstractDaoTest {
    @Inject
    private PromoFatturazioneDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaPromoFatturazioneDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        CfgSetPianificazioneEntity cfgSetPianificazione = new CfgSetPianificazioneEntityBuilder(1L)
                .withDataInizio(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime())
                .withDescrizione("JUNIT TEST").build();
        PromozioneTestataEntity promozione = new PromozioneTestataEntityBuilder(1L).withCodice("PROMO 01")
                .withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 31).getTime())
                .withCanale(canale).withCfgSetPianificazione(cfgSetPianificazione).build();
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("R01").build();
        CompratoreEntity compratore1 = new CompratoreEntityBuilder(1L).withCodice("S01")
                .withResponsabile(responsabile).build();
        CompratoreEntity compratore2 = new CompratoreEntityBuilder(2L).withCodice("S02")
                .withResponsabile(responsabile).build();
        FornitoreEntity fornitore1 = new FornitoreEntityBuilder(1L).withCodice("F01").build();
        FornitoreEntity fornitore2 = new FornitoreEntityBuilder(2L).withCodice("F02").build();
        PromoFatturazioneEntity promoFatturazione1 = buildPromoFatturazione(promozione, compratore1, fornitore1, "SI");
        PromoFatturazioneEntity promoFatturazione2 = buildPromoFatturazione(promozione, compratore2, fornitore2, "NO");
        persist(gruppo, canale, cfgSetPianificazione, promozione, compratore1, compratore2, fornitore1, fornitore2,
                promoFatturazione1, promoFatturazione2);
    }

    @Test
    public void findAllByIdsCompratori_givenNullIds_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByIdsCompratori(null);
    }

    @Test
    public void findAllByIdsCompratori_givenEmptyIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByIdsCompratori(Collections.emptyList());
    }

    @Test
    public void findAllByIdsCompratori() {
        List<PromoFatturazioneEntity> result = dao.findAllByIdsCompratori(Collections.singletonList(1L));
        List<String> codiciCompratori = result.stream()
                .map(PromoFatturazioneEntity::getCompratore)
                .map(CompratoreEntity::getCodiceCompratore)
                .collect(Collectors.toList());
        assertEquals(1, result.size());
        assertTrue(codiciCompratori.contains("S01"));
        assertFalse(codiciCompratori.contains("S02"));
    }

    @Test
    public void findAllByCodiciCompratori_givenNullIds_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByCodiciCompratori(null);
    }

    @Test
    public void findAllByCodiciCompratori_givenEmptyIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByCodiciCompratori(Collections.emptyList());
    }

    @Test
    public void findAllByCodiciCompratori() {
        List<PromoFatturazioneEntity> result = dao.findAllByCodiciCompratori(Collections.singletonList("S01"));
        List<String> codiciCompratori = result.stream()
                .map(PromoFatturazioneEntity::getCompratore)
                .map(CompratoreEntity::getCodiceCompratore)
                .collect(Collectors.toList());
        assertEquals(1, result.size());
        assertTrue(codiciCompratori.contains("S01"));
        assertFalse(codiciCompratori.contains("S02"));
    }

    @Test
    public void findAllByIdCompratoreAndIdPromozione_givenNullIdCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByIdCompratoreAndIdPromozione(null, 1L);
    }

    @Test
    public void findAllByIdCompratoreAndIdPromozione_givenNullIdPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByIdCompratoreAndIdPromozione(1L, null);
    }

    @Test
    public void findAllByIdCompratoreAndIdPromozione() {
        List<PromoFatturazioneEntity> result = dao.findAllByIdCompratoreAndIdPromozione(1L, 1L);
        assertEquals(1, result.size());
        assertEquals("SI", result.get(0).getRifattura());
        assertEquals("S01", result.get(0).getCompratore().getCodiceCompratore());
        assertEquals("PROMO 01", result.get(0).getPromozione().getCodicePromozione());
        assertEquals("F01", result.get(0).getFornitore().getCodiceFornitore());
    }

    private PromoFatturazioneEntity buildPromoFatturazione(PromozioneTestataEntity promozione,
                                                           CompratoreEntity compratore,
                                                           FornitoreEntity fornitore,
                                                           String rifattura) {
        PromoFatturazioneEntity entity = new PromoFatturazioneEntity();
        entity.setPromozione(promozione);
        entity.setCompratore(compratore);
        entity.setFornitore(fornitore);
        entity.setRifattura(rifattura);
        return entity;
    }
}