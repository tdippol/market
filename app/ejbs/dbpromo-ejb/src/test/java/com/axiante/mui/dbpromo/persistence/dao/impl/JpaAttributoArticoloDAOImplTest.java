package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.AttributoArticoloDAO;
import com.axiante.mui.dbpromo.persistence.entities.AttributoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.listener.ReadOnlyListenerImpl;
import java.util.Arrays;
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
public class JpaAttributoArticoloDAOImplTest extends AbstractDaoTest {

    @Inject
    private AttributoArticoloDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaAttributoArticoloDAOImpl.class, ReadOnlyListenerImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        AttributoArticoloEntity attributo1 = createAttributoArticolo("23", "Attributo numero 23", true);
        AttributoArticoloEntity attributo2 = createAttributoArticolo("24", "Attributo numero 24", false);
        openTransaction();
        dao.persist(Arrays.asList(attributo1, attributo2));
        commitTransaction();
    }

    @Test
    public void findAllActive() {
        List<AttributoArticoloEntity> result = dao.findAllActive();
        assertEquals(1, result.size());
        AttributoArticoloEntity attributo = result.get(0);
        assertEquals("23", attributo.getCodiceAttributo());
        assertEquals("Attributo numero 23", attributo.getDescrizione());
        assertTrue(attributo.getAttivo());
    }

    private AttributoArticoloEntity createAttributoArticolo(String codice, String descrizione, Boolean attivo) {
        AttributoArticoloEntity entity = new AttributoArticoloEntity();
        entity.setCodiceAttributo(codice);
        entity.setDescrizione(descrizione);
        entity.setAttivo(attivo);
        return entity;
    }
}