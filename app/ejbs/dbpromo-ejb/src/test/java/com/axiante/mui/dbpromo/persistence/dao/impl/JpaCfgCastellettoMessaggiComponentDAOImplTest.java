package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiCfgDefaultCastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JpaCfgCastellettoMessaggiComponentDAOImplTest extends AbstractDaoTest {
    @Inject
    private MuiCfgDefaultCastellettoMessaggiDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgCastellettoMessaggiComponentDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MuiCfgDefaultCastellettoMessaggiEntity cfgCastellettoMessaggio1 = createCfgCastellettoMessaggio(
                1L, 1L, "M001", "D001", (short) 1);
        MuiCfgDefaultCastellettoMessaggiEntity cfgCastellettoMessaggio2 = createCfgCastellettoMessaggio(
                2L, 2L, "M0002", "D002", (short) 1);
        MuiCfgDefaultCastellettoMessaggiEntity cfgCastellettoMessaggio3 = createCfgCastellettoMessaggio(
                3L, 3L, "M0003", "D003", (short) 1);
        persist(cfgCastellettoMessaggio1, cfgCastellettoMessaggio2, cfgCastellettoMessaggio3);
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_whenSingleCodiceDispositivo() {
        List<MuiCfgDefaultCastellettoMessaggiEntity> result = dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M001", "D001");
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_whenMultipleCodiceDispositivo() {
        List<MuiCfgDefaultCastellettoMessaggiEntity> result = dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M001", "D001||D002");
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanica() {
        List<MuiCfgDefaultCastellettoMessaggiEntity> result = dao.findByCodiceCanaleAndCodiceMeccanica(1L, "M001");
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
    }

    @Test
    public void findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_withoutSequenza() {
        Short result = dao.findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(11L, "M001", "D001");
        assertEquals(0, result.shortValue());
    }

    @Test
    public void findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_withSequenza() {
        MuiCfgDefaultCastellettoMessaggiEntity cfgCastellettoMessaggio4 = createCfgCastellettoMessaggio(
                4L, 1L, "M001", "D001", (short) 2);
        persist(cfgCastellettoMessaggio4);
        Short result = dao.findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M001", "D001");
        assertEquals(2, result.shortValue());
    }

    @Test
    public void countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        Long count = dao.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M001", "D001");
        assertEquals(1, count.longValue());
    }

    @Test
    public void findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan() {
        final List<MuiCfgDefaultCastellettoMessaggiEntity> result = dao.findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan(1L, "M001", "D001", (short) 1);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
    }

    private MuiCfgDefaultCastellettoMessaggiEntity createCfgCastellettoMessaggio(Long id, Long codiceCanale,
                                                                                 String codiceMeccanica,
                                                                                 String codiceDispositivo,
                                                                                 short seqStampa) {
        MuiCfgDefaultCastellettoMessaggiEntity entity = new MuiCfgDefaultCastellettoMessaggiEntity();
        entity.setId(id);
        entity.setCodiceCanale(codiceCanale);
        entity.setCodiceMeccanica(codiceMeccanica);
        entity.setCodiceDispositivo(codiceDispositivo);
        entity.setSeqStampa(seqStampa);
        entity.setSezione(MessaggiSezioneEnum.MESSAGGI);
        return entity;
    }
}