package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.IniziativaStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.IniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.IniziativaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.IniziativaStatoEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.StatoPromozioneEntityBuilder;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JpaIniziativaDAOImplTest extends AbstractDaoTest {

    @Inject
    private IniziativaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaIniziativaDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        final Date dataInizio = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final Date dataFine = new GregorianCalendar(2022, Calendar.AUGUST, 31).getTime();
        final Date dataInizioStatoCancellata = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final Date dataInizioStatoCreata = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final StatoPromozioneEntity statoCancellata = StatoPromozioneEntityBuilder.buildCancellata();
        final StatoPromozioneEntity statoCreata = StatoPromozioneEntityBuilder.buildCreata();
        final IniziativaStatoEntity iniziativaStato1 = new IniziativaStatoEntityBuilder()
                .withStato(statoCancellata).withDataInizioStato(dataInizioStatoCancellata).build();
        // CANCELLATA
        IniziativaEntity iniziativa1 = new IniziativaEntityBuilder().withDescrizione("INIZIATIVA 01")
                .withDataInizio(dataInizio).withDataFine(dataFine).build();
        iniziativa1.addStato(iniziativaStato1);
        final IniziativaStatoEntity iniziativaStato2 = new IniziativaStatoEntityBuilder()
                .withStato(statoCreata).withDataInizioStato(dataInizioStatoCreata).build();
        // CREATA
        IniziativaEntity iniziativa2 = new IniziativaEntityBuilder().withDescrizione("INIZIATIVA 02")
                .withDataInizio(dataInizio).withDataFine(dataFine).build();
        iniziativa2.addStato(iniziativaStato2);
        openTransaction();
        em.persist(statoCancellata);
        em.persist(statoCreata);
        em.persist(iniziativaStato1);
        em.persist(iniziativaStato2);
        em.persist(iniziativa1);
        em.persist(iniziativa2);
        commitTransaction();
    }

    @Test
    public void findAllNotCancelled() {
        final List<IniziativaEntity> entities = dao.findAllNotCancelled();
        assertEquals(1, entities.size());
        final List<String> descrizioni = entities.stream().map(IniziativaEntity::getDescrizione)
                .collect(Collectors.toList());
        assertTrue(descrizioni.contains("INIZIATIVA 02"));
        assertFalse(descrizioni.contains("INIZIATIVA 01"));
    }

    @Test
    public void findAllPublishedAndInProgressAndValidDates() {
        final Date dataInizioStato = new GregorianCalendar(2022, Calendar.OCTOBER, 1).getTime();
        final StatoPromozioneEntity statoPubblicata = StatoPromozioneEntityBuilder.buildPubblicata();
        final StatoPromozioneEntity statoInEsecuzione = StatoPromozioneEntityBuilder.buildInEsecuzione();
        final IniziativaStatoEntity iniziativaStato3 = new IniziativaStatoEntityBuilder()
                .withStato(statoPubblicata).withDataInizioStato(dataInizioStato).build();
        IniziativaEntity iniziativa3 = new IniziativaEntityBuilder().withDescrizione("INIZIATIVA 03")
                .withDataInizio(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2022, Calendar.OCTOBER, 31).getTime())
                .build();
        iniziativa3.addStato(iniziativaStato3);
        final IniziativaStatoEntity iniziativaStato4 = new IniziativaStatoEntityBuilder()
                .withStato(statoPubblicata).withDataInizioStato(dataInizioStato).build();
        IniziativaEntity iniziativa4 = new IniziativaEntityBuilder().withDescrizione("INIZIATIVA 04")
                .withDataInizio(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2022, Calendar.SEPTEMBER, 15).getTime()).build();
        iniziativa4.addStato(iniziativaStato4);
        final IniziativaStatoEntity iniziativaStato5 = new IniziativaStatoEntityBuilder()
                .withStato(statoInEsecuzione).withDataInizioStato(dataInizioStato).build();
        IniziativaEntity iniziativa5 = new IniziativaEntityBuilder().withDescrizione("INIZIATIVA 05")
                .withDataInizio(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2022, Calendar.NOVEMBER, 15).getTime()).build();
        iniziativa5.addStato(iniziativaStato5);
        openTransaction();
        em.persist(statoPubblicata);
        em.persist(statoInEsecuzione);
        em.persist(iniziativaStato3);
        em.persist(iniziativaStato4);
        em.persist(iniziativaStato5);
        em.persist(iniziativa3);
        em.persist(iniziativa4);
        em.persist(iniziativa5);
        commitTransaction();
        final Date dataInizioTestata = new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime();
        final Date dataFineTestata = new GregorianCalendar(2022, Calendar.OCTOBER, 15).getTime();
        final List<IniziativaEntity> entities = dao.findAllPublishedAndInProgressAndValidDates(dataInizioTestata, dataFineTestata);
        assertEquals(2, entities.size());
        final List<String> descrizioni = entities.stream().map(IniziativaEntity::getDescrizione)
                .collect(Collectors.toList());
        assertTrue(descrizioni.contains("INIZIATIVA 03"));
        assertFalse(descrizioni.contains("INIZIATIVA 04"));
        assertTrue(descrizioni.contains("INIZIATIVA 05"));
    }

    @Test
    public void findAllStatiTransizione() {
        final List<StatoPromozioneEntity> stati = Arrays.stream(IniziativaStatusEnum.values())
                .map(iniziativaStato -> new StatoPromozioneEntityBuilder()
                        .withCodice(iniziativaStato.getCodice())
                        .withDescrizione(iniziativaStato.getDescrizione()).build())
                .collect(Collectors.toList());
        openTransaction();
        stati.forEach(statiPromozioneEntity -> em.persist(statiPromozioneEntity));
        commitTransaction();
        final List<StatoPromozioneEntity> entities = dao.findStatiTransizioneConsentiti();
        final List<String> codiciStato = entities.stream().map(StatoPromozioneEntity::getCodiceStato).collect(Collectors.toList());
        assertEquals(2, entities.size());
        assertTrue(codiciStato.contains(IniziativaStatusEnum.PUBBLICATA.getCodice()));
        assertTrue(codiciStato.contains(IniziativaStatusEnum.SBLOCCATA.getCodice()));
        assertFalse(codiciStato.contains(IniziativaStatusEnum.CANCELLATA_00.getCodice()));
        assertFalse(codiciStato.contains(IniziativaStatusEnum.NON_PUBBLICATA.getCodice()));
    }
}