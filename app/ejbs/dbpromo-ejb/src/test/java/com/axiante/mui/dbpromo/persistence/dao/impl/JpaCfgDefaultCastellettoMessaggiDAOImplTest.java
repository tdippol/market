package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiCfgCastellettoMessaggiComponentDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JpaCfgDefaultCastellettoMessaggiDAOImplTest extends AbstractDaoTest {
    @Inject
    private MuiCfgCastellettoMessaggiComponentDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgDefaultCastellettoMessaggiDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() throws Exception {
        MuiCfgCastellettoMessaggiComponentEntity entity1 = createEntity(1L, 1L, "M-01",
                "D-01", MessaggiComponentsEnum.ALLINEAMENTO);
        MuiCfgCastellettoMessaggiComponentEntity entity2 = createEntity(2L, 1L, "M-02",
                "D-01", MessaggiComponentsEnum.BOLD);
        persist(entity1, entity2);
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        assertEquals(1,
                dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M-01",
                        "D-01").size());
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent() {
        assertNotNull(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(1L, "M-02",
                "D-01", MessaggiComponentsEnum.BOLD));
    }

    @Test
    public void countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent() {
        assertEquals(1,
                dao.countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(1L, "M-02",
                        "D-01", MessaggiComponentsEnum.BOLD).longValue());
    }

    private MuiCfgCastellettoMessaggiComponentEntity createEntity(Long id, Long codiceCanale, String codiceMeccanica,
                                                                  String codiceDispositivo, MessaggiComponentsEnum component) {
        final MuiCfgCastellettoMessaggiComponentEntity entity = new MuiCfgCastellettoMessaggiComponentEntity();
        entity.setId(id);
        entity.setCodiceCanale(codiceCanale);
        entity.setCodiceMeccanica(codiceMeccanica);
        entity.setCodiceDispositivo(codiceDispositivo);
        entity.setComponent(component);
        return entity;
    }
}