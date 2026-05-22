package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ReportVendutoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoId;
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
public class JpaReportVendutoDAOImplTest extends AbstractDaoTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaReportVendutoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Inject
    private ReportVendutoDAO dao;

    @Before
    public void setUp() throws Exception {
        final ReportVendutoEntity report1 = create(1L);
        final ReportVendutoEntity report2 = create(2L);
        openTransaction();
        getEm().merge(report1);
        getEm().merge(report2);
        getEm().flush();
        commitTransaction();
    }

    @Test
    public void findAllByIdPromozione_givenNullIdPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByIdPromozione(null);
    }

    @Test
    public void findAllByIdPromozione_givenExistingIdPromozione_shouldReturnListOfEntities() {
        final List<ReportVendutoEntity> entities = dao.findAllByIdPromozione(1L);
        assertEquals(1, entities.size());
    }

    @Test
    public void findAllByIdPromozione_givenNotExistingIdPromozione_shouldReturnEmptyListOfEntities() {
        final List<ReportVendutoEntity> entities = dao.findAllByIdPromozione(3L);
        assertTrue(entities.isEmpty());
    }

    private ReportVendutoEntity create(Long idPromozione) {
        final ReportVendutoId id = new ReportVendutoId(idPromozione, "IT-001", "ITEM 001",
                "S01", "COMPRATORE 01", "F01", "FORNITORE 01",
                "R01", "REPARTO 01", "M001", "MECCANICA 001");
        final ReportVendutoEntity entity = new ReportVendutoEntity();
        entity.setId(id);
        return entity;
    }
}