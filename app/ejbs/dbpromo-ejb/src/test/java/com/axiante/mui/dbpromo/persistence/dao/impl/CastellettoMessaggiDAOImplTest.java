package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.MeccanicaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozionePianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
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

import static org.junit.Assert.assertEquals;

public class CastellettoMessaggiDAOImplTest extends AbstractDaoTest {
    @Inject
    private CastellettoMessaggiDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    CastellettoMessaggiDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CastellettoMessaggiEntity castelletto1;
    private CastellettoMessaggiEntity castelletto2;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        CfgSetPianificazioneEntity cfgSetPianificazione = new CfgSetPianificazioneEntityBuilder(1L)
                .withDataInizio(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime())
                .withDescrizione("JUNIT TEST").build();
        PromozioneTestataEntity testata = new PromozioneTestataEntityBuilder(1L).withCodice("PROMO 01")
                .withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 31).getTime())
                .withCanale(canale).withCfgSetPianificazione(cfgSetPianificazione).build();
        MeccanicheEntity meccanica = new MeccanicaEntityBuilder(1L).withCodice("M001").withDescrizione("MECCANICA 001")
                .build();
        PromozionePianificazioneEntity pianificazione1 = new PromozionePianificazioneEntityBuilder(1L)
                .withMeccanica(meccanica).withPromozioneTestata(testata).withTipoRiga(PianificazioneRowTypeEnum.ELEMENTO)
                .build();
        PromozionePianificazioneEntity pianificazione2 = new PromozionePianificazioneEntityBuilder(2L)
                .withMeccanica(meccanica).withPromozioneTestata(testata).withTipoRiga(PianificazioneRowTypeEnum.ELEMENTO)
                .build();
        castelletto1 = createCastellettoMessaggio(1L, pianificazione1, MessaggiSezioneEnum.RIGA, "D-01");
        castelletto2 = createCastellettoMessaggio(2L, pianificazione2, MessaggiSezioneEnum.MESSAGGI, "D-02");
        persist(gruppo, canale, testata, meccanica, pianificazione1, pianificazione2, castelletto1, castelletto2);
    }

    @Test
    public void findByIdPianificazione() {
        final List<CastellettoMessaggiEntity> result = dao.findByIdPianificazione(1L);
        assertEquals(1, result.size());
        assertEquals(castelletto1, result.get(0));
    }

    @Test
    public void findMessaggiByIdPianificazione() {
        final List<CastellettoMessaggiEntity> result = dao.findMessaggiByIdPianificazione(2L);
        assertEquals(1, result.size());
        assertEquals(castelletto2, result.get(0));
    }

    @Test
    public void findCastellettiByIdPianificazione() {
        final List<CastellettoMessaggiEntity> result = dao.findCastellettiByIdPianificazione(1L);
        assertEquals(1, result.size());
        assertEquals(castelletto1, result.get(0));
    }

    @Test
    public void remove() {
        openTransaction();
        dao.remove(Collections.singletonList(1L));
        commitTransaction();
        final List<CastellettoMessaggiEntity> result = dao.findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void findMessaggiByIdPianificazioneAndCodiceDispositivo() {
        final List<CastellettoMessaggiEntity> result = dao.findMessaggiByIdPianificazioneAndCodiceDispositivo(2L, "D-02");
        assertEquals(1, result.size());
        assertEquals(castelletto2, result.get(0));
    }

    @Test
    public void remove_whenListNullOrEmpty() {
        dao.remove((List<Long>) null);
        dao.remove(Collections.emptyList());
    }

    @Test
    public void findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan() {
        castelletto2.setSeqStampa(5);
        persist(castelletto2);
        final List<CastellettoMessaggiEntity> result = dao.findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan(2L, "D-02", 1);
        assertEquals(1, result.size());
        assertEquals(castelletto2, result.get(0));
    }

    private CastellettoMessaggiEntity createCastellettoMessaggio(Long id, PromozionePianificazioneEntity pianificazione,
                                                                 MessaggiSezioneEnum sezione, String codiceDispositivo) {
        final CastellettoMessaggiEntity e = new CastellettoMessaggiEntity();
        e.setId(id);
        e.setPianificazione(pianificazione);
        e.setSezione(sezione);
        e.setCodiceCanaleDispositivo(codiceDispositivo);
        return e;
    }
}