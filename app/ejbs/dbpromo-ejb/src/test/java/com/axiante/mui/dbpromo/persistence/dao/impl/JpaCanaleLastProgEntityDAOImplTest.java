package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CanaleLastProgEntityDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
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

public class JpaCanaleLastProgEntityDAOImplTest extends AbstractDaoTest {

    @Inject
    private CanaleLastProgEntityDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCanaleLastProgEntityDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private static final String ANNO_CANALEPROMOZIONE1 = "2020";
    private static final String ANNO_CANALEPROMOZIONE2 = "2021";

    private CanalePromozioneEntity canale1;
    private CanalePromozioneEntity canale2;
    private CanaleLastProgEntity canaleLastProg1;
    private CanaleLastProgEntity canaleLastProg2;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        canale1 = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        canale2 = new CanalePromozioneEntityBuilder(2L).withCodice(2L).withGruppo(gruppo).build();
        canaleLastProg1 = createCanaleLastProgEntity(51L, ANNO_CANALEPROMOZIONE1, canale1);
        canaleLastProg2 = createCanaleLastProgEntity(52L, ANNO_CANALEPROMOZIONE2, canale2);
        persist(canale1, canale2, canaleLastProg1, canaleLastProg2);
    }

    @Test
    public void shouldFindByYearAndChannel() {
        CanaleLastProgEntity canaleLastProg = dao.getByYearAndChannel(null, null);
        assertNull(canaleLastProg);
        canaleLastProg = dao.getByYearAndChannel(ANNO_CANALEPROMOZIONE1, null);
        assertNull(canaleLastProg);
        canaleLastProg = dao.getByYearAndChannel(null, canale2);
        assertNull(canaleLastProg);

        canaleLastProg = dao.getByYearAndChannel(ANNO_CANALEPROMOZIONE1, canale1);
        assertNotNull(canaleLastProg);
        assertEquals(canaleLastProg1.getId(), canaleLastProg.getId());
        assertEquals(canaleLastProg1.getAnno(), canaleLastProg.getAnno());
        assertEquals(canaleLastProg1.getLastProgressivo(), canaleLastProg.getLastProgressivo());
        assertNotNull(canaleLastProg.getMuiCanalePromozione());
        assertEquals(canaleLastProg1.getMuiCanalePromozione().getId(), canaleLastProg.getMuiCanalePromozione().getId());
        assertEquals(canaleLastProg1.getMuiCanalePromozione().getCodiceCanale(),
                canaleLastProg.getMuiCanalePromozione().getCodiceCanale());
        assertNotNull(canaleLastProg.getMuiCanalePromozione().getGruppoPromozioneEntity());
        assertEquals(canaleLastProg1.getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
                canaleLastProg.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
        assertEquals(canaleLastProg1.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
                canaleLastProg.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());

        canaleLastProg = dao.getByYearAndChannel(ANNO_CANALEPROMOZIONE2, canale2);
        assertNotNull(canaleLastProg);
        assertEquals(canaleLastProg2.getId(), canaleLastProg.getId());
        assertEquals(canaleLastProg2.getAnno(), canaleLastProg.getAnno());
        assertEquals(canaleLastProg2.getLastProgressivo(), canaleLastProg.getLastProgressivo());
        assertNotNull(canaleLastProg.getMuiCanalePromozione());
        assertEquals(canaleLastProg2.getMuiCanalePromozione().getId(), canaleLastProg.getMuiCanalePromozione().getId());
        assertEquals(canaleLastProg2.getMuiCanalePromozione().getCodiceCanale(),
                canaleLastProg.getMuiCanalePromozione().getCodiceCanale());
        assertNotNull(canaleLastProg.getMuiCanalePromozione().getGruppoPromozioneEntity());
        assertEquals(canaleLastProg2.getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
                canaleLastProg.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
        assertEquals(canaleLastProg2.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
                canaleLastProg.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());
    }

    @Test
    public void findByChannel() {
        final List<CanaleLastProgEntity> result = dao.findByChannel(canale1);
        assertEquals(1, result.size());
        assertEquals(canaleLastProg1, result.get(0));
    }

    private CanaleLastProgEntity createCanaleLastProgEntity(long id, String anno, CanalePromozioneEntity canale) {
        CanaleLastProgEntity entity = new CanaleLastProgEntity();
        entity.setId(id);
        entity.setAnno(Long.parseLong(anno));
        entity.setLastProgressivo(1L);
        entity.setMuiCanalePromozione(canale);
        entity.setUuid(AxUUID.randomUUID().toString());
        return entity;
    }
}
