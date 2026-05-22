package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniNegoziDAO;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniNegoziEntity;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaReportSovrapposizioniNegoziDAOImplTest extends AbstractDaoTest {
    @Inject
    private ReportSovrapposizioniNegoziDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaReportSovrapposizioniNegoziDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ReportSovrapposizioniEntity sovrapposizione1 = createSovrapposizioneEntity(1L, "AA");
        ReportSovrapposizioniEntity sovrapposizione2 = createSovrapposizioneEntity(2L, "BB");
        ReportSovrapposizioniNegoziEntity sovrappNegozio1 = createSovrappNegozioEntity(1L, sovrapposizione1);
        ReportSovrapposizioniNegoziEntity sovrappNegozio2 = createSovrappNegozioEntity(2L, sovrapposizione2);
        persist(sovrapposizione1, sovrapposizione2, sovrappNegozio1, sovrappNegozio2);
    }

    @Test
    public void findByIdSovrapposizione() {
        final List<ReportSovrapposizioniNegoziEntity> result = dao.findByIdSovrapposizione(1L);
        assertEquals(1, result.size());
    }

    @Test
    public void findByIdSovrapposizione_givenNullIdReport_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByIdSovrapposizione(null);
    }

    private ReportSovrapposizioniNegoziEntity createSovrappNegozioEntity(Long id, ReportSovrapposizioniEntity sovrapposizione) {
        ReportSovrapposizioniNegoziEntity e = new ReportSovrapposizioniNegoziEntity();
        e.setId(id);
        e.setReport(sovrapposizione);
        return e;
    }

    private ReportSovrapposizioniEntity createSovrapposizioneEntity(Long id, String codiceSovrapposta) {
        ReportSovrapposizioniEntity e = new ReportSovrapposizioniEntity();
        e.setId(id);
        e.setCodicePromoSovrapposta(codiceSovrapposta);
        e.setDataInizioArticolo(new Date());
        e.setDataFineArticolo(new Date());
        e.setDataInizioArticoloSovrapposto(new Date());
        e.setDataFineArticoloSovrapposto(new Date());
        e.setSeverita("FOO");
        return e;
    }
}