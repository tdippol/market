package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ClusterEntityDAO;
import com.axiante.mui.dbpromo.persistence.entities.ClusterClienteEntity;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClusterEntityDAOImplTest extends AbstractDaoTest {

    @Inject
    private ClusterEntityDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    ClusterEntityDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private ClusterClienteEntity clusterCliente1;
    private ClusterClienteEntity clusterCliente2;

    @Before
    public void setUp() throws Exception {
        Date dataInizio1 = new GregorianCalendar(2024, GregorianCalendar.OCTOBER, 1).getTime();
        Date dataFine1 = new GregorianCalendar(2024, GregorianCalendar.OCTOBER, 15).getTime();
        Date dataInizio2 = new GregorianCalendar(2024, GregorianCalendar.OCTOBER, 16).getTime();
        Date dataFine2 = new GregorianCalendar(2024, GregorianCalendar.OCTOBER, 30).getTime();
        clusterCliente1 = createClusterCliente("001", dataInizio1, dataFine1);
        clusterCliente2 = createClusterCliente("002", dataInizio2, dataFine2);
        persist(clusterCliente1, clusterCliente2);
    }

    @Test
    public void findAll() {
        assertEquals(2, dao.findAll().size());
    }

    @Test
    public void findAllByIdCluster() {
        final List<ClusterClienteEntity> result = dao.findAllByIdCluster("001");
        assertEquals(1, result.size());
        assertEquals(clusterCliente1, result.get(0));
    }

    @Test
    public void findByDataInizioAndDataFine() {
        Date dataInizio = new GregorianCalendar(2024, GregorianCalendar.OCTOBER, 20).getTime();
        Date dataFine = new GregorianCalendar(2024, GregorianCalendar.OCTOBER, 25).getTime();
        final List<ClusterClienteEntity> result = dao.findByDataInizioAndDataFine(dataInizio, dataFine);
        assertEquals(1, result.size());
        assertEquals(clusterCliente2, result.get(0));
    }

    @Test
    public void findByDataInizioAndDataFine_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByDataInizioAndDataFine(null, null);
    }

    private ClusterClienteEntity createClusterCliente(String idCluster, Date dataInizio, Date dataFine) {
        final ClusterClienteEntity e = new ClusterClienteEntity();
        e.setIdCluster(idCluster);
        e.setDataInizio(dataInizio);
        e.setDataFine(dataFine);
        return e;
    }
}