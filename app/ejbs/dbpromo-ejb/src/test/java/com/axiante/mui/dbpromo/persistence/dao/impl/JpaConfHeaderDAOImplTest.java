package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgConfHeaderDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaConfHeaderDAOImplTest extends AbstractDaoTest {

    @Inject
    CfgConfHeaderDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaConfHeaderDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CfgConfHeaderEntity confHeaderEntity1;
    private CfgConfHeaderEntity confHeaderEntity2;

    @Before
    public void setUp() {
        final CfgSetPianificazioneEntity setPianificazioneEntity = createSetPianificazioneEntity();
        final MeccanicheEntity meccanicaEntity = createMeccanicaEntity();
        final CfgLivelloPianificazioneEntity lvlPianificazione1 = createLivelloPianificazione(1, "SET");
        final CfgLivelloPianificazioneEntity lvlPianificazione2 = createLivelloPianificazione(2, "ELEMENTO");
        confHeaderEntity1 = createConfHeaderEntity(1, setPianificazioneEntity, meccanicaEntity, lvlPianificazione1);
        confHeaderEntity2 = createConfHeaderEntity(2, setPianificazioneEntity, meccanicaEntity, lvlPianificazione2);
    }

    @Test
    public void findByMeccanicaIdAndSetPianificazioneId_givenValidId_shouldReturnOneEntity() {
        openTransaction();
        dao.persist(confHeaderEntity1);
        commitTransaction();
        final CfgConfHeaderEntity entity = dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
        assertNotNull(entity);
    }

    @Test(expected = NonUniqueResultException.class)
    public void findByMeccanicaIdAndSetPianificazioneId_shouldThrowException_whenMoreThanOneResult() {
        openTransaction();
        dao.persist(confHeaderEntity1);
        dao.persist(confHeaderEntity2);
        commitTransaction();
        dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
    }

    @Test
    public void findLivelloByIdHeader() {
        openTransaction();
        dao.persist(confHeaderEntity1);
        dao.persist(confHeaderEntity2);
        commitTransaction();
        assertEquals("SET", dao.findLivelloByIdHeader(1L));
    }

    private CfgConfHeaderEntity createConfHeaderEntity(int id,
                                                       CfgSetPianificazioneEntity cfgSetPianificazione,
                                                       MeccanicheEntity meccanica,
                                                       CfgLivelloPianificazioneEntity livelloEntity) {
        final CfgConfHeaderEntity entity = new CfgConfHeaderEntity();
        entity.setId((long) id);
        entity.setMuiCfgSetPianificazione(cfgSetPianificazione);
        entity.setMeccanicaEntity(meccanica);
        entity.setLivelloPianificazione(livelloEntity);
        return entity;
    }
    private CfgSetPianificazioneEntity createSetPianificazioneEntity() {
        final CfgSetPianificazioneEntity entity = new CfgSetPianificazioneEntity();
        entity.setId(1L);
        return entity;
    }

    private MeccanicheEntity createMeccanicaEntity() {
        final MeccanicheEntity entity = new MeccanicheEntity();
        entity.setId(1L);
        entity.setCodiceMeccanica("MC1");
        entity.setDescrizione("MECCANICA 1");
        return entity;
    }

    private CfgLivelloPianificazioneEntity createLivelloPianificazione(int id, String code) {
        final CfgLivelloPianificazioneEntity entity = new CfgLivelloPianificazioneEntity();
        entity.setId((long) id);
        entity.setCodice(code);
        return entity;
    }
}
