package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazTipoRigaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
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
public class JpaCfgPianificazTipoRigaDAOTest extends AbstractDaoTest {

    @Inject
    private CfgPianificazTipoRigaDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
        .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCfgPianificazTipoRigaDAO.class, DbPromo.class)
        .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CfgPianificazTipoRigaEntity entityMock;

    @Before
    public void setUp() throws Exception {
        entityMock = new CfgPianificazTipoRigaEntity();
        entityMock.setDescrizione("descrizione");
        entityMock.setCodiceTipo("codiceTipo");
        entityMock.setId(Long.parseLong("55"));

        openTransaction();
        dao.persist(entityMock);
        commitTransaction();
    }

    @Test
    public void shouldFindByDescription() {
        final CfgPianificazTipoRigaEntity entity = dao.findByDescription(this.entityMock.getDescrizione());
        assertNotNull(entity);
        assertEquals(this.entityMock.getId(), entity.getId());
        assertEquals(this.entityMock.getDescrizione(), entity.getDescrizione());
        assertEquals(this.entityMock.getCodiceTipo(), entity.getCodiceTipo());
    }

    @Test
    public void shouldNotFindByDescription() {
        CfgPianificazTipoRigaEntity entity = dao.findByDescription("test");
        assertNull(entity);
    }

    @Test
    public void shouldFindByCodiceTipo() {
        final CfgPianificazTipoRigaEntity entity = dao.findByCodiceTipo(this.entityMock.getCodiceTipo());
        assertNotNull(entity);
        assertEquals(this.entityMock.getId(), entity.getId());
        assertEquals(this.entityMock.getDescrizione(), entity.getDescrizione());
        assertEquals(this.entityMock.getCodiceTipo(), entity.getCodiceTipo());
    }

    @Test
    public void shouldNotFindByCodiceTipo() {
        CfgPianificazTipoRigaEntity entity = dao.findByCodiceTipo("test");
        assertNull(entity);
    }

}
