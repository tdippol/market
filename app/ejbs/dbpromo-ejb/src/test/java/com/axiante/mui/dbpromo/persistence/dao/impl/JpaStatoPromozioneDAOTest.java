package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
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
public class JpaStatoPromozioneDAOTest extends AbstractDaoTest {

    @Inject
    private StatoPromozioneDAO statoPromozioneDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
        .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaStatoPromozioneDAOImpl.class)
        .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private static final String CODICESTATO1 = "abc";
    private static final String CODICESTATO2 = "xyz";

    private StatoPromozioneEntity statoPromozioneEntity1;
    private StatoPromozioneEntity statoPromozioneEntity2;

    @Before
    public void setUp() throws Exception {
        statoPromozioneEntity1 = createStatoPromozioneEntity(2L, CODICESTATO1);
        statoPromozioneEntity2 = createStatoPromozioneEntity(3L, CODICESTATO2);

        openTransaction();
        statoPromozioneDAO.persist(statoPromozioneEntity1);
        statoPromozioneDAO.persist(statoPromozioneEntity2);
        commitTransaction();
    }

    private StatoPromozioneEntity createStatoPromozioneEntity(long id, String codiceStato) {
        StatoPromozioneEntity statoPromozioneEntity = new StatoPromozioneEntity();
        statoPromozioneEntity.setId(id);
        statoPromozioneEntity.setCodiceStato(codiceStato);
        statoPromozioneEntity.setDescrizione("descrizione");
        return statoPromozioneEntity;
    }

    @Test
    public void shouldFindByCode() {
        StatoPromozioneEntity statoPromozioneEntity = statoPromozioneDAO.findByCode(null);
        assertNull(statoPromozioneEntity);
        statoPromozioneEntity = statoPromozioneDAO.findByCode("test");
        assertNull(statoPromozioneEntity);
        statoPromozioneEntity = statoPromozioneDAO.findByCode(CODICESTATO1);
        assertNotNull(statoPromozioneEntity);
        assertEquals(statoPromozioneEntity1.getId(), statoPromozioneEntity.getId());
        assertEquals(statoPromozioneEntity1.getCodiceStato(), statoPromozioneEntity.getCodiceStato());
        assertEquals(statoPromozioneEntity1.getDescrizione(), statoPromozioneEntity.getDescrizione());
        statoPromozioneEntity = statoPromozioneDAO.findByCode(CODICESTATO2);
        assertNotNull(statoPromozioneEntity);
        assertEquals(statoPromozioneEntity2.getId(), statoPromozioneEntity.getId());
        assertEquals(statoPromozioneEntity2.getCodiceStato(), statoPromozioneEntity.getCodiceStato());
        assertEquals(statoPromozioneEntity2.getDescrizione(), statoPromozioneEntity.getDescrizione());
    }

}
