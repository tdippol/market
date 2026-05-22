package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CategoriaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GrmEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ItemEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.RepartoEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.SubGrmEntityBuilder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaReportSovrapposizioniDAOImplTest extends AbstractDaoTest {
    @Inject
    private ReportSovrapposizioniDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaReportSovrapposizioniDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PromozioneTestataEntity testata;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        CfgSetPianificazioneEntity cfgSetPianificazione = new CfgSetPianificazioneEntityBuilder(1L)
                .withDataInizio(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime())
                .withDescrizione("JUNIT TEST")
                .build();
        testata = new PromozioneTestataEntityBuilder(1L).withCodice("PROMO 01")
                .withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 31).getTime())
                .withCanale(canale).withCfgSetPianificazione(cfgSetPianificazione).build();
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("RES").build();
        CompratoreEntity compratore1 = new CompratoreEntityBuilder(1L).withCodice("FOO")
                .withResponsabile(responsabile).build();
        CompratoreEntity compratore2 = new CompratoreEntityBuilder(1L).withCodice("BAR")
                .withResponsabile(responsabile).build();
        RepartoEntity reparto = new RepartoEntityBuilder(1L).withCodice("R1").build();
        CategoriaEntity categoria = new CategoriaEntityBuilder(1L).withCodice("C01").withReparto(reparto).build();
        GrmEntity grm = new GrmEntityBuilder(1L).withCodice("G01").withCategoria(categoria).build();
        SubGrmEntity subGrm = new SubGrmEntityBuilder(1L).withCodice("SG1").withGrm(grm).build();
        ItemEntity item1 = new ItemEntityBuilder(1L).withCodice("I001").withSubGrm(subGrm).withCompratore(compratore1)
                .build();
        ItemEntity item2 = new ItemEntityBuilder(2L).withCodice("I002").withSubGrm(subGrm).withCompratore(compratore2)
                .build();
        ReportSovrapposizioniEntity reportSovrapposizione1 = createReportSovrapposizione(1L, testata, item1,
                "PROMO 02");
        ReportSovrapposizioniEntity reportSovrapposizione2 = createReportSovrapposizione(2L, testata, item2,
                "PROMO 03");
        persist(gruppo, canale, cfgSetPianificazione, testata, item1, item2, responsabile, compratore1, compratore2,
                reparto, categoria, grm, subGrm, reportSovrapposizione1, reportSovrapposizione2);
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore() {
        final List<ReportSovrapposizioniEntity> result = dao.findAllByPromozioneAndCodiciCompratore(testata,
                Arrays.asList("FOO", "BAR"));
        assertEquals(2, result.size());
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByPromozioneAndCodiciCompratore(null, Collections.singletonList("FOO"));
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenNullCodiciCompratore_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByPromozioneAndCodiciCompratore(testata, null);
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenEmptyCodiciCompratore_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByPromozioneAndCodiciCompratore(testata, Collections.emptyList());
    }

    private ReportSovrapposizioniEntity createReportSovrapposizione(Long id, PromozioneTestataEntity testata,
                                                                    ItemEntity item, String codicePromoSovrapposta) {
        final ReportSovrapposizioniEntity e = new ReportSovrapposizioniEntity();
        e.setId(id);
        e.setTestata(testata);
        e.setItem(item);
        e.setCodicePromoSovrapposta(codicePromoSovrapposta);
        e.setDataInizioArticolo(new Date());
        e.setDataInizioArticoloSovrapposto(new Date());
        e.setDataFineArticolo(new Date());
        e.setDataFineArticoloSovrapposto(new Date());
        e.setSeverita("FOO");
        return e;
    }
}