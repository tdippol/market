package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.StatoContributiDAO;
import com.axiante.mui.dbpromo.persistence.entities.ContributiStatoAnagraficaEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaStatoContributiDAOImplTest extends AbstractDaoTest {
    @Inject
    private StatoContributiDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaStatoContributiDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ContributiStatoAnagraficaEntity contributo1 = createContributo("C001", "CONTRIBUTO 001");
        persist(contributo1);
    }

    @Test
    public void findDescrizioneByCodice() {
        assertEquals("CONTRIBUTO 001", dao.findDescrizioneByCodice("C001"));
    }

    @Test
    public void findDescrizioneByCodice_whenNoResult_shouldReturnNull() {
        assertNull(dao.findDescrizioneByCodice("C004"));
    }

    private ContributiStatoAnagraficaEntity createContributo(String codice, String descrizione) {
        final ContributiStatoAnagraficaEntity e = new ContributiStatoAnagraficaEntity();
        e.setCodiceStatoContributo(codice);
        e.setDescrizioneStatoContributo(descrizione);
        return e;
    }
}