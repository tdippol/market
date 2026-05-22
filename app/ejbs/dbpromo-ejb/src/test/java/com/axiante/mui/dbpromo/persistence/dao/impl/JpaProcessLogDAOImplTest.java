package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ProcessLogDAO;
import com.axiante.mui.dbpromo.persistence.entities.ProcessLogEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JpaProcessLogDAOImplTest extends AbstractDaoTest {
    @Inject
    private ProcessLogDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaProcessLogDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() throws Exception {
        final Timestamp timestamp = new Timestamp(new Date().getTime());
        ProcessLogEntity entity1 = buildEntity(timestamp, timestamp, "ERRORE 1", "FOO", "PROCEDURA 1", "CRITICAL");
        ProcessLogEntity entity2 = buildEntity(timestamp, timestamp, "ERRORE 2", "BAR", "PROCEDURA 2", "WARNING");
        persist(entity1, entity2);
    }

    @Test
    public void findAll() {
        final List<ProcessLogEntity> result = dao.findAll();
        assertEquals(2, result.size());
    }

    private ProcessLogEntity buildEntity(Timestamp dataInizio, Timestamp dataFine, String descrizioneErrore, String esito,
                                         String procedura, String severity) {
        final ProcessLogEntity entity = new ProcessLogEntity();
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        entity.setDescrizioneErrore(descrizioneErrore);
        entity.setEsito(esito);
        entity.setProcedura(procedura);
        entity.setSeverity(severity);
        return entity;
    }
}