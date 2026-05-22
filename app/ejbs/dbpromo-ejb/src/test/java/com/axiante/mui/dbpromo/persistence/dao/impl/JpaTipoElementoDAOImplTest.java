package com.axiante.mui.dbpromo.persistence.dao.impl;

import static junit.framework.TestCase.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.TipoElementoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaTipoElementoDAOImplTest extends AbstractDaoTest {

    @Inject
    TipoElementoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(
                DbPromoTestsEntityManagerProducer.class,
                EntityManagerFactoryProducer.class,
                JpaTipoElementoDAOImpl.class,
                DbPromo.class)
            .activate(RequestScoped.class)
            .inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CfgTipoElementoEntity tipoElementoEntity1;
    private CfgTipoElementoEntity tipoElementoEntity2;

    @Before
    public void setUp() {
        CfgLivelloPianificazioneEntity livelloPianificazione1 = createLivelloPianificazione(1, "SET");
        CfgLivelloPianificazioneEntity livelloPianificazione2 = createLivelloPianificazione(2, "ELEMENTO");
        MeccanicheEntity meccanica = createMeccanica();
        CfgSetPianificazioneEntity setPianificazioneEntity = createSetPianificazioneEntity();
        CfgConfHeaderEntity confHeaderEntity1 = createConfHeaderEntity(1, setPianificazioneEntity, meccanica, livelloPianificazione1);
        CfgConfHeaderEntity confHeaderEntity2 = createConfHeaderEntity(2, setPianificazioneEntity, meccanica, livelloPianificazione2);
        tipoElementoEntity1 = createTipoElementoEntity(1, 1, 0, 0, confHeaderEntity1);
        tipoElementoEntity2 = createTipoElementoEntity(2, 0, 1, 1, confHeaderEntity2);
    }

    @Test
    public void findByMeccanicaIdAndSetPianificazioneId_givenValidId_shouldReturnOneEntity() {
        openTransaction();
        dao.persist(tipoElementoEntity1);
        commitTransaction();
        final CfgTipoElementoEntity entity = dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
        assertNotNull(entity);
    }

    @Test(expected = NonUniqueResultException.class)
    public void findByMeccanicaIdAndSetPianificazioneId_shouldThrowException_whenMoreThanOneResult() {
        openTransaction();
        dao.persist(tipoElementoEntity1);
        dao.persist(tipoElementoEntity2);
        commitTransaction();
        dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
    }

    private CfgLivelloPianificazioneEntity createLivelloPianificazione(int id, String code) {
        final CfgLivelloPianificazioneEntity entity = new CfgLivelloPianificazioneEntity();
        entity.setId((long) id);
        entity.setCodice(code);
        return entity;
    }

    private MeccanicheEntity createMeccanica() {
        final MeccanicheEntity entity = new MeccanicheEntity();
        entity.setId(1L);
        entity.setCodiceMeccanica("MC1");
        entity.setDescrizione("MECCANICA 1");
        return entity;
    }

    private CfgTipoElementoEntity createTipoElementoEntity(int id, int articolo, int grm, int reparto,
                                                           CfgConfHeaderEntity confHeader) {
        final CfgTipoElementoEntity entity = new CfgTipoElementoEntity();
        entity.setId((long) id);
        entity.setArticolo(articolo);
        entity.setGrm(grm);
        entity.setReparto(reparto);
        entity.setOmogeneo(1);
        entity.setTotale(0);
        entity.setConfHeader(confHeader);
        return entity;
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
}
