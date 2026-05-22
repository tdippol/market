package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.FornitoriRetailDAO;
import com.axiante.mui.dbpromo.persistence.entities.FornitoriRetailEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JpaFornitoriRetailDAOImplTest extends AbstractDaoTest {

    @Inject
    private FornitoriRetailDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaFornitoriRetailDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    private LocalDate today = LocalDate.now();

    @Before
    public void setUp() throws Exception {
        Date inThePast = Date.from(today.minusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date inTheFuture = Date.from(today.plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant());
        FornitoriRetailEntity fornitore1 = createFornitoriRetailEntity("F001", "FORNITORE 001");
        FornitoriRetailEntity fornitore2 = createFornitoriRetailEntity("F002", "FORNITORE 002", inThePast);
        FornitoriRetailEntity fornitore3 = createFornitoriRetailEntity("F003", "FORNITORE 003", inTheFuture);
        FornitoriRetailEntity fornitore4 = createFornitoriRetailEntity("F004", "FORNITORE 004");
        persist(fornitore1, fornitore2, fornitore3, fornitore4);
    }

    @Test
    public void getAllFornitoriRetailNotDeleted() {
        Date today = Date.from(this.today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<FornitoriRetailEntity> result = dao.getAllFornitoriRetailNotDeleted(today);
        assertEquals(3, result.size());
        List<String> codes = result.stream().map(FornitoriRetailEntity::getCodice).collect(Collectors.toList());
        assertTrue(codes.contains("F001"));
        assertTrue(codes.contains("F003"));
        assertTrue(codes.contains("F004"));
        assertFalse(codes.contains("F002"));
    }

    private FornitoriRetailEntity createFornitoriRetailEntity(String codice, String descrizione) {
        return createFornitoriRetailEntity(codice, descrizione, null);
    }

    private FornitoriRetailEntity createFornitoriRetailEntity(String codice, String descrizione, Date dataEliminazione) {
        FornitoriRetailEntity entity = new FornitoriRetailEntity();
        entity.setCodice(codice);
        entity.setDescrizione(descrizione);
        if (dataEliminazione != null) {
            entity.setDataEliminazione(dataEliminazione);
        }
        return entity;
    }
}