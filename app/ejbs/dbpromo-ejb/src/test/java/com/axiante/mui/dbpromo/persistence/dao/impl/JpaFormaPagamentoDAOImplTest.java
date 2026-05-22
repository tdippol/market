package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.FormaPagamentoDAO;
import com.axiante.mui.dbpromo.persistence.entities.FormaPagamentoEntity;
import com.axiante.mui.dbpromo.persistence.listener.ReadOnlyListenerImpl;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaFormaPagamentoDAOImplTest extends AbstractDaoTest {

    @Inject
    private FormaPagamentoDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaFormaPagamentoDAOImpl.class, ReadOnlyListenerImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        FormaPagamentoEntity formaPagamento1 = createFormaPagamento("23", "FOOBAR", true);
        FormaPagamentoEntity formaPagamento2 = createFormaPagamento("24", "BAZAAR", false);
        openTransaction();
        dao.persist(Arrays.asList(formaPagamento1, formaPagamento2));
        commitTransaction();
    }

    @Test
    public void findAllActive() {
        List<FormaPagamentoEntity> list = dao.findAllActive();
        assertEquals(1, list.size());
        final FormaPagamentoEntity entity = list.get(0);
        assertEquals("23", entity.getCodice());
        assertEquals("23", entity.getKey());
        assertEquals("FOOBAR", entity.getDescrizione());
        assertEquals("FOOBAR", entity.getLabel());
        assertTrue(entity.getAttivo());
    }

    @Test
    public void findByCode_whenEntityExists_shouldReturnEntity() {
        final FormaPagamentoEntity byCode = dao.findByCode("23");
        assertNotNull(byCode);
        assertEquals("23", byCode.getCodice());
        assertEquals("FOOBAR", byCode.getDescrizione());
        assertTrue(byCode.getAttivo());
    }

    @Test
    public void findByCode_whenEntityNotExists_shouldThrowException() {
        ex.expect(NoResultException.class);
        dao.findByCode("42");
    }

    private FormaPagamentoEntity createFormaPagamento(String codice, String descrizione, Boolean attivo) {
        final FormaPagamentoEntity e = new FormaPagamentoEntity();
        e.setCodice(codice);
        e.setDescrizione(descrizione);
        e.setAttivo(attivo);
        return e;
    }
}