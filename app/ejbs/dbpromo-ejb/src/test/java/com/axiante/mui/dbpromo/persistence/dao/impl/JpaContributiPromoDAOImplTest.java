package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ContributiPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CategoriaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.FornitoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GrmEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ItemEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.RepartoEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.SubGrmEntityBuilder;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaContributiPromoDAOImplTest extends AbstractDaoTest {
    @Inject
    private ContributiPromoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaContributiPromoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private FornitoreEntity fornitore;
    private CompratoreEntity compratore;
    private PromozioneTestataEntity promozione;
    private ContributiPromoEntity contributo1;
    private ContributiPromoEntity contributo2;

    @Before
    public void setUp() throws Exception {
        fornitore = new FornitoreEntityBuilder(1L).withCodice("F001").build();
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("RES").build();
        compratore = new CompratoreEntityBuilder(1L).withCodice("FOO")
                .withResponsabile(responsabile).build();
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        CfgSetPianificazioneEntity cfgSetPianificazione = new CfgSetPianificazioneEntityBuilder(1L)
                .withDataInizio(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime())
                .withDescrizione("JUNIT TEST")
                .build();
        promozione = new PromozioneTestataEntityBuilder(1L).withCodice("PROMO 01")
                .withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 31).getTime())
                .withCanale(canale).withCfgSetPianificazione(cfgSetPianificazione).build();
        RepartoEntity reparto = new RepartoEntityBuilder(1L).withCodice("R1").build();
        CategoriaEntity categoria = new CategoriaEntityBuilder(1L).withCodice("C01").withReparto(reparto).build();
        GrmEntity grm = new GrmEntityBuilder(1L).withCodice("G01").withCategoria(categoria).build();
        SubGrmEntity subGrm = new SubGrmEntityBuilder(1L).withCodice("SG1").withGrm(grm).build();
        ItemEntity item = new ItemEntityBuilder(1L).withCodice("I001").withSubGrm(subGrm).withCompratore(compratore)
                .build();
        contributo1 = createContributo(1L, promozione, compratore, fornitore);
        contributo2 = createContributo(2L, promozione, compratore, fornitore);
        ContributiPromoArticoloEntity contributoArticolo = createContributoArticolo(1L, contributo1, item);
        persist(fornitore, responsabile, compratore, gruppo, canale, promozione, reparto, categoria, grm, subGrm,
                item, contributo1, contributo2, contributoArticolo);
    }

    @Test
    public void findAllByPromozioni() {
        final List<ContributiPromoEntity> result = dao.findAllByPromozioni(Collections.singletonList(promozione));
        assertEquals(2, result.size());
        assertTrue(result.contains(contributo1));
        assertTrue(result.contains(contributo2));
    }

    @Test
    public void findAllByPromozioni_whenNullPromozioni_shouldThrownException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByPromozioni(null);
    }

    @Test
    public void findAllByPromozioni_whenEmptyPromozioni_shouldThrownException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByPromozioni(Collections.emptyList());
    }

    @Test
    public void findAllByPromozione() {
        final List<ContributiPromoEntity> result = dao.findAllByPromozione(promozione);
        assertEquals(2, result.size());
        assertTrue(result.contains(contributo1));
        assertTrue(result.contains(contributo2));
    }

    @Test
    public void findAllByPromozione_givenNullPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByPromozione(null);
    }

    @Test
    public void countByPromozioneAndCompratoreAndFornitore() {
        assertEquals(2, (long) dao.countByPromozioneAndCompratoreAndFornitore(promozione, compratore, fornitore));
    }

    @Test
    public void countByPromozioneAndCompratoreAndFornitore_givenNullPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.countByPromozioneAndCompratoreAndFornitore(null, compratore, fornitore);
    }

    @Test
    public void countByPromozioneAndCompratoreAndFornitore_givenNullCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.countByPromozioneAndCompratoreAndFornitore(promozione, null, fornitore);
    }

    @Test
    public void countByPromozioneAndCompratoreAndFornitore_givenNullFornitore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.countByPromozioneAndCompratoreAndFornitore(promozione, compratore, null);
    }

    @Test
    public void findAllItemsIdByPromozione() {
        final List<Long> result = dao.findAllItemsIdByPromozione(promozione);
        assertEquals(1, result.size());
        assertEquals(1, (long) result.get(0));
    }

    @Test
    public void findAllItemsIdByPromozione_givenNullPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllItemsIdByPromozione(null);
    }

    @Test
    public void nextProgressivo() {
        final Integer value = dao.nextProgressivo(promozione, compratore, fornitore);
        assertEquals(3, value.intValue());
    }

    @Test
    public void nextProgressivo_givenNullPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.nextProgressivo(null, compratore, fornitore);
    }

    @Test
    public void nextProgressivo_givenNullCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.nextProgressivo(promozione, null, fornitore);
    }

    @Test
    public void nextProgressivo_givenNullFornitore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.nextProgressivo(promozione, compratore, null);
    }

    private ContributiPromoEntity createContributo(Long id, PromozioneTestataEntity promozione, CompratoreEntity compratore,
                                                   FornitoreEntity fornitore) {
        final ContributiPromoEntity e = new ContributiPromoEntity();
        e.setId(id);
        e.setPromozione(promozione);
        e.setCompratore(compratore);
        e.setFornitore(fornitore);
        return e;
    }

    private ContributiPromoArticoloEntity createContributoArticolo(Long id, ContributiPromoEntity contributo,
                                                                   ItemEntity item) {
        final ContributiPromoArticoloEntity e = new ContributiPromoArticoloEntity();
        e.setId(id);
        e.setContributiPromo(contributo);
        e.setItem(item);
        return e;
    }
}