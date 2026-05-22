package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CheckPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class JpaCheckPianificazioneDAOImplTest extends AbstractDaoTest {

    @Inject
    private CheckPianificazioneDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCheckPianificazioneDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    private PromozioneTestataEntity testata;
    private List<String> codiciCompratore = Arrays.asList("S01", "S02");

    @Before
    public void setUp() throws Exception {
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder().withCodice("R01").build();
        CompratoreEntity compratore1 = new CompratoreEntityBuilder().withCodice("S01")
                .withResponsabile(responsabile).build();
        CompratoreEntity compratore2 = new CompratoreEntityBuilder().withCodice("S02")
                .withResponsabile(responsabile).build();
        CompratoreEntity compratore3 = new CompratoreEntityBuilder().withCodice("S03")
                .withResponsabile(responsabile).build();
        CheckPianificazioneEntity check1 = createCheckPianificazione(1L, compratore1, "ITEM 01");
        CheckPianificazioneEntity check2 = createCheckPianificazione(2L, compratore2, "ITEM 02");
        CheckPianificazioneEntity check3 = createCheckPianificazione(3L, compratore3, "ITEM 03");
        GruppoPromozioneEntity gruppo = createGruppo("GR1");
        CanalePromozioneEntity canale = createCanale(42L, gruppo);
        testata = createTestata("PROMO-01", canale);
        testata.addCheckPianificazione(check1);
        testata.addCheckPianificazione(check2);
        testata.addCheckPianificazione(check3);
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            testata = em.merge(testata);
            em.persist(testata);
            em.getTransaction().commit();
        }
    }

    private CheckPianificazioneEntity createCheckPianificazione(Long id, CompratoreEntity compratore, String elemento) {
        final CheckPianificazioneEntity check = new CheckPianificazioneEntity();
        check.setId(id);
        check.setCompratore(compratore);
        check.setElemento(elemento);
        return check;
    }

    @Test(expected = NullPointerException.class)
    public void findByPromozioneAndCodiciCompratore_givenNullTestata_shouldThrowException() {
        dao.findAllByPromozioneAndCodiciCompratore(null, codiciCompratore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findAllByPromozioneAndCodiciCompratore_givenNullCodiciCompratore_shouldThrowException() {
        dao.findAllByPromozioneAndCodiciCompratore(testata, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findAllByPromozioneAndCodiciCompratore_givenEmptyCodiciCompratore_shouldThrowException() {
        dao.findAllByPromozioneAndCodiciCompratore(testata, Collections.emptyList());
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenTestataAndCodiciCompratore_shouldReturnChecksForCompratoriInList() {
        final List<CheckPianificazioneEntity> checks = dao.findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore);
        assertEquals(2, checks.size());
        final List<String> elementi = checks.stream().map(CheckPianificazioneEntity::getElemento).collect(Collectors.toList());
        assertEquals(2, elementi.size());
        assertTrue(elementi.contains("ITEM 01"));
        assertTrue(elementi.contains("ITEM 02"));
        assertFalse(elementi.contains("ITEM 03"));
    }

    private PromozioneTestataEntity createTestata(String codice, CanalePromozioneEntity canale) {
        final PromozioneTestataEntity testata = new PromozioneTestataEntity();
        testata.setCodicePromozione(codice);
        testata.setMuiCanalePromozione(canale);
        testata.setDataInizio(new Date());
        testata.setDataFine(new Date());
        return testata;
    }

    private CanalePromozioneEntity createCanale(Long codice, GruppoPromozioneEntity gruppo) {
        final CanalePromozioneEntity canale = new CanalePromozioneEntity();
        canale.setCodiceCanale(codice);
        canale.setGruppoPromozioneEntity(gruppo);
        return canale;
    }

    private GruppoPromozioneEntity createGruppo(String codice) {
        final GruppoPromozioneEntity gruppo = new GruppoPromozioneEntity();
        gruppo.setCodiceGruppo(codice);
        return gruppo;
    }
}