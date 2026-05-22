package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaliAttributiDAO;
import com.axiante.mui.dbpromo.persistence.entities.AttributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaliAttributiEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCfgCanaliAttributiDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgCanaliAttributiDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgCanaliAttributiDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        AttributiPromoEntity attributo1 = createAttributo(1L);
        AttributiPromoEntity attributo2 = createAttributo(2L);
        CfgCanaliAttributiEntity canaleAttributo1 = createCanaleAttributo(1L, canale, attributo1, "FOO");
        CfgCanaliAttributiEntity canaleAttributo2 = createCanaleAttributo(2L, canale, attributo2, "BAR");
        persist(gruppo, canale, attributo1, attributo2, canaleAttributo1, canaleAttributo2);
    }

    @Test
    public void getListaByCanaleAndAttributo() {
        assertEquals("FOO", dao.getListaByCanaleAndAttributo(1L, 1L));
    }

    @Test
    public void getListaByCanaleAndAttributo_givenNullIdCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.getListaByCanaleAndAttributo(null, 1L);
    }

    @Test
    public void getListaByCanaleAndAttributo_givenNullIdAttributo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.getListaByCanaleAndAttributo(1L, null);
    }

    @Test
    public void getAllByCanale() {
        final List<CfgCanaliAttributiEntity> result = dao.getAllByCanale(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void getAllByCanale_givenNullIdCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.getAllByCanale(null);
    }

    private CfgCanaliAttributiEntity createCanaleAttributo(Long id, CanalePromozioneEntity canale,
                                                           AttributiPromoEntity attributo, String lista) {
        final CfgCanaliAttributiEntity e = new CfgCanaliAttributiEntity();
        e.setId(id);
        e.setCanale(canale);
        e.setAttributo(attributo);
        e.setLista(lista);
        return e;
    }

    private AttributiPromoEntity createAttributo(Long id) {
        final AttributiPromoEntity e = new AttributiPromoEntity();
        e.setId(id);
        e.setParametro("FOO" + id);
        e.setDescrizioneParametro("FOO");
        return e;
    }
}