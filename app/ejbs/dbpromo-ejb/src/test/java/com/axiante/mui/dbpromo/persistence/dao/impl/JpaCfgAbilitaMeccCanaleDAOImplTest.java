package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaMeccCanaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgAbilitaMeccCanaleEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.MeccanicaEntityBuilder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCfgAbilitaMeccCanaleDAOImplTest extends AbstractDaoTest {

    @Inject
    private CfgAbilitaMeccCanaleDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgAbilitaMeccCanaleDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanale;
    CanalePromozioneEntity canale;
    CanalePromozioneEntity canaleNotFound;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("GR1")
                .withDescrizione("Gruppo 1").build();
        canaleNotFound = new CanalePromozioneEntityBuilder(10L).withCodice(10L).withDescrizione("Canale 1")
                .withCodeRangeMin(1L).withCodeRangeMax(10L).withGruppo(gruppo).build();
        canale = new CanalePromozioneEntityBuilder(2L).withCodice(1L).withDescrizione("Canale 1")
                .withCodeRangeMin(1L).withCodeRangeMax(10L).withGruppo(gruppo).build();
        MeccanicheEntity meccanica = new MeccanicaEntityBuilder(1L).withCodice("M00").withDescrizione("Meccanica 1")
                .build();
        cfgAbilitaMeccCanale = new CfgAbilitaMeccCanaleEntityBuilder(2L).withCanale(canale)
                .withMeccanica(meccanica).build();
        persist(cfgAbilitaMeccCanale);
    }

    @Test
    public void shouldFindById() {
        final CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanaleEntity = dao.findById(2L);
        assertNotNull(cfgAbilitaMeccCanaleEntity);
        assertEquals(cfgAbilitaMeccCanaleEntity.getMeccanicheEntity().getCodiceMeccanica(), "M00");
    }

    @Test
    public void shouldReadAll() {
        List<CfgAbilitaMeccCanaleEntity> cfgAbilitaMeccCanaleList = dao.findAll();
        assertNotNull(cfgAbilitaMeccCanaleList);
        assertFalse(cfgAbilitaMeccCanaleList.isEmpty());
    }

    @Test
    public void shouldFindAllByCanaleId() throws Exception {
        final List<CfgAbilitaMeccCanaleEntity> allByIdCanale = dao.findAllByIdCanale(canale);
        assertNotNull(allByIdCanale);
        assertFalse(allByIdCanale.isEmpty());
        final CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanaleEntity = allByIdCanale.get(0);
        assertEquals(1L, (long) cfgAbilitaMeccCanaleEntity.getCanalePromozioneEntity().getCodeRangeMin());
        assertEquals(1L, (long) cfgAbilitaMeccCanaleEntity.getCanalePromozioneEntity().getCodiceCanale());
    }

    @Test
    public void shouldNotFindAllByCanaleId() throws Exception {
        final List<CfgAbilitaMeccCanaleEntity> allByIdCanale = dao.findAllByIdCanale(canaleNotFound);
        assertNotNull(allByIdCanale);
        assertTrue(allByIdCanale.isEmpty());
    }

    @Test
    public void shouldThrowExceptionFindAllByCanaleId() throws Exception {
        ex.expect(Exception.class);
        dao.findAllByIdCanale(null);
    }

    @Test
    public void findByMeccanicaAndCanale() {
        assertEquals(cfgAbilitaMeccCanale, dao.findByMeccanicaAndCanale(1L, 2L));
    }

    @Test
    public void findByMeccanicaAndCanale_givenNullMeccanica_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByMeccanicaAndCanale(null, 2L);
    }

    @Test
    public void findByMeccanicaAndCanale_givenNullCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByMeccanicaAndCanale(1L, null);
    }
}
