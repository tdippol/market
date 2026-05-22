package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgStatiTransizioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgStatiTransizioniEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.StatoPromozioneEntityBuilder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaCfgStatiTransizioniDAOImplTest extends AbstractDaoTest {

    @Inject
    private CfgStatiTransizioniDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgStatiTransizioniDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    CanalePromozioneEntity canale;
    StatoPromozioneEntity stato1;
    StatoPromozioneEntity stato2;
    StatoPromozioneEntity stato3;
    CfgStatiTransizioniEntity transizione1;
    CfgStatiTransizioniEntity transizione2;
    CfgStatiTransizioniEntity transizione3;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        stato1 = StatoPromozioneEntityBuilder.buildCompletata();
        stato1.setId(1L);
        stato2 = StatoPromozioneEntityBuilder.buildCreata();
        stato2.setId(2L);
        stato3 = StatoPromozioneEntityBuilder.buildCompletata();
        stato3.setId(3L);
        transizione1 = new CfgStatiTransizioniEntityBuilder(1L)
                .withCanale(canale).withStatoPromozione(stato1).withStatoTransizione(stato1)
                .withStatoErrore(stato1).build();
        transizione2 = new CfgStatiTransizioniEntityBuilder(2L)
                .withCanale(canale).withStatoPromozione(stato2).withStatoTransizione(stato3)
                .withStatoErrore(stato2).withFlagAutomatico(true).build();
        transizione3 = new CfgStatiTransizioniEntityBuilder(3L)
                .withCanale(canale).withStatoPromozione(stato3).withStatoTransizione(stato2)
                .withStatoErrore(stato2).withFlagAutomatico(false).build();
        persist(gruppo, canale, stato1, stato2, stato3, transizione1, transizione2, transizione3);
    }

    @Test
    public void shouldFindById() {
        final CfgStatiTransizioniEntity result = dao.findById(1L);
        assertNotNull(result);
        assertEquals(1L, (long) result.getId());
        assertEquals(result.getStatoTransizione().getCodiceStato(), "500");
    }

    @Test
    public void shouldReadAll() {
        List<CfgStatiTransizioniEntity> result = dao.findAll();
        assertEquals(3, result.size());
    }

    @Test
    public void shouldFindAllByCanalePromozione() {
        final List<CfgStatiTransizioniEntity> allPromoTransitionByChannel = dao.findAllPromoTransitionByChannel(canale);
        assertNotNull(allPromoTransitionByChannel);
        assertFalse(allPromoTransitionByChannel.isEmpty());
    }

    @Test
    public void shouldNotFindAllByCanalePromozione() {
        final List<CfgStatiTransizioniEntity> allPromoTransitionByChannel = dao.findAllPromoTransitionByChannel(null);
        assertNotNull(allPromoTransitionByChannel);
        assertTrue(allPromoTransitionByChannel.isEmpty());
    }

    @Test
    public void existAutomaticByCanaleAndFromStatus_shouldReturnTrue_whenExistAtLeastOneEntityWithAutomaticTransition() {
        assertTrue(dao.existAutomaticByCanaleAndFromStatus(canale, stato2));
        assertFalse(dao.existAutomaticByCanaleAndFromStatus(canale, stato3));
    }

    @Test
    public void existAutomaticByCanaleAndFromStatus_givenNullCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.existAutomaticByCanaleAndFromStatus(null, stato1);
    }

    @Test
    public void existAutomaticByCanaleAndFromStatus_givenNullFromStatus_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.existAutomaticByCanaleAndFromStatus(canale, null);
    }

    @Test
    public void findAllByCanaleAndStatusFromAndStatusTo() {
        final List<CfgStatiTransizioniEntity> result = dao.findAllByCanaleAndStatusFromAndStatusTo(canale, stato2, stato3);
        assertEquals(1, result.size());
    }
}
