package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleFlagDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaCfgCanaleFlagDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgCanaleFlagDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgCanaleFlagDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo)
                .build();
        MuiFlagEntity flag1 = createFlagEntity(1L);
        MuiFlagEntity flag2 = createFlagEntity(2L, Boolean.TRUE);
        CfgCanaleFlagEntity cfgCanale1 = createCfgCanaleFlagEntity(1L, canale, flag1);
        CfgCanaleFlagEntity cfgCanale2 = createCfgCanaleFlagEntity(2L, canale, flag2);
        persist(gruppo, canale, flag1, flag2, cfgCanale1, cfgCanale2);
    }

    @Test
    public void findActiveByChannel() {
        final List<CfgCanaleFlagEntity> result = dao.findActiveByChannel(1L);
        assertEquals(1, result.size());
        final CfgCanaleFlagEntity entity = result.get(0);
        assertEquals(2L, (long) entity.getId());
    }

    @Test
    public void findActiveByChannel_givenNullCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findActiveByChannel(null);
    }

    private CfgCanaleFlagEntity createCfgCanaleFlagEntity(Long id, CanalePromozioneEntity canale, MuiFlagEntity flag) {
        CfgCanaleFlagEntity e = new CfgCanaleFlagEntity();
        e.setId(id);
        e.setCanale(canale);
        e.setFlag(flag);
        return e;
    }

    private MuiFlagEntity createFlagEntity(Long id) {
        return createFlagEntity(id, Boolean.FALSE);
    }

    private MuiFlagEntity createFlagEntity(Long id, Boolean attivo) {
        MuiFlagEntity e = new MuiFlagEntity();
        e.setId(id);
        e.setAttivo(attivo);
        return e;
    }
}