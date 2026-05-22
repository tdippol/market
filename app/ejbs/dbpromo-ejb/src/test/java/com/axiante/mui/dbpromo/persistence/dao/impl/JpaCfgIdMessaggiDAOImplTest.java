package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgIdMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JpaCfgIdMessaggiDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgIdMessaggiDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgIdMessaggiDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() throws Exception {
        CfgIdMessaggiEntity entity1 = createEntity(1L, 1L, "M-01", "D-01", 1, "Descrizione messaggio 1");
        CfgIdMessaggiEntity entity2 = createEntity(2L, 1L, "M-02", "D-01", 1, "Descrizione messaggio 1");
        persist(entity1, entity2);
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio_whenNotExistShouldReturnNull() {
        assertNull(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(2L, "M-03", "D-01", 1));
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio() {
        assertNotNull(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(1L, "M-01", "D-01", 1));
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        final List<CfgIdMessaggiEntity> result = dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M-01", "D-01");
        assertEquals(1, result.size());
    }

    private CfgIdMessaggiEntity createEntity(Long id, Long codiceCanale, String codiceMeccanica, String codiceDispositivo,
                                             Integer idMessaggio, String descrizione) {
        final CfgIdMessaggiEntity entity = new CfgIdMessaggiEntity();
        entity.setId(id);
        entity.setCodiceCanale(codiceCanale);
        entity.setCodiceMeccanica(codiceMeccanica);
        entity.setCodiceDispositivo(codiceDispositivo);
        entity.setIdMessaggio(idMessaggio);
        entity.setDescrizione(descrizione);
        return entity;
    }
}