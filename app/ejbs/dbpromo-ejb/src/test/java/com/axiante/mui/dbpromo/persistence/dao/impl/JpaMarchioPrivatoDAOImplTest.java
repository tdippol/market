package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaMarchioPrivatoDAOImplTest extends AbstractDaoTest {
    @Inject
    private MarchioPrivatoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMarchioPrivatoDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private MarchioPrivatoEntity marchioPrivato1;
    private MarchioPrivatoEntity marchioPrivato2;
    private MarchioPrivatoEntity marchioPrivato3;

    @Before
    public void setUp() throws Exception {
        marchioPrivato1 = createMarchioPrivate(1L, "M1");
        marchioPrivato2 = createMarchioPrivate(2L, "M2");
        marchioPrivato3 = createMarchioPrivate(3L, "M3");
        persist(marchioPrivato1, marchioPrivato2, marchioPrivato3);
    }

    @Test
    public void findAll() {
        assertEquals(3, dao.findAll().size());
    }

    @Test
    public void findByCodice() {
        final MarchioPrivatoEntity result = dao.findByCodice("M1");
        assertEquals(marchioPrivato1, result);
    }

    @Test
    public void findByCodici() {
        final List<MarchioPrivatoEntity> result = dao.findByCodici(Arrays.asList("M2", "M3"));
        assertEquals(2, result.size());
        assertFalse(result.contains(marchioPrivato1));
        assertTrue(result.contains(marchioPrivato2));
        assertTrue(result.contains(marchioPrivato3));
    }

    @Test
    public void findByCodici_whenNullCodici_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByCodici(null);
    }

    @Test
    public void findByCodici_whenEmptyCodici_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByCodici(Collections.emptyList());
    }

    private MarchioPrivatoEntity createMarchioPrivate(Long id, String codice) {
        final MarchioPrivatoEntity e = new MarchioPrivatoEntity();
        e.setId(id);
        e.setCodice(codice);
        return e;
    }
}