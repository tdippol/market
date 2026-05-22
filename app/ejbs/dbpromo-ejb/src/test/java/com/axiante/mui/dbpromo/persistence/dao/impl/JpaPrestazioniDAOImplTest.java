package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PrestazioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.PrestazioniEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JpaPrestazioniDAOImplTest extends AbstractDaoTest {
    @Inject
    private PrestazioniDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPrestazioniDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        PrestazioniEntity prestazione1 = createPrestazione("FOO", "PRESTAZIONE 001");
        PrestazioniEntity prestazione2 = createPrestazione("BAR", "PRESTAZIONE 002", "FOO");
        PrestazioniEntity prestazione3 = createPrestazione("BAZ", "PRESTAZIONE 003", "FOO");
        persist(prestazione1, prestazione2, prestazione3);
    }

    @Test
    public void findDescrizioneByCodice() {
        assertEquals("PRESTAZIONE 001", dao.findDescrizioneByCodice("FOO"));
    }

    @Test
    public void findDescrizioneByCodice_givenNullCodice_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findDescrizioneByCodice(null);
    }

    @Test
    public void findDescrizioneByCodice_whenNoResult_shouldReturnNull() {
        assertNull(dao.findDescrizioneByCodice("FOOBAR"));
    }

    @Test
    public void findByCodiceGruppo() {
        final List<PrestazioniEntity> result = dao.findByCodiceGruppo("FOO");
        assertEquals(2, result.size());
    }

    private PrestazioniEntity createPrestazione(String codice, String descrizione) {
        return createPrestazione(codice, descrizione, null);
    }

    private PrestazioniEntity createPrestazione(String codice, String descrizione, String codiceGruppo) {
        final PrestazioniEntity e = new PrestazioniEntity();
        e.setCodicePrestazione(codice);
        e.setDescrizionePrestazione(descrizione);
        e.setCodiceGruppo(codiceGruppo);
        return e;
    }
}