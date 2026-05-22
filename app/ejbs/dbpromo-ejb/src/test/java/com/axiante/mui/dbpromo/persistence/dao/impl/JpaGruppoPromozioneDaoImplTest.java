package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
public class JpaGruppoPromozioneDaoImplTest extends AbstractDaoTest {

    @Inject
    private GruppoPromozioneDAO groupDao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaGruppoPromozioneDaoImpl.class, JpaCanalePromozioneDaoImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        final GruppoPromozioneEntity groupFoo = createGruppoPromo(101L, "FOO");
        final GruppoPromozioneEntity groupBar = createGruppoPromo(102L, "BAR");
        final GruppoPromozioneEntity groupBaz = createGruppoPromo(103L, "BAZ");
        final CanalePromozioneEntity channel1 = createCanalePromo(101L);
        final CanalePromozioneEntity channel2 = createCanalePromo(102L);
        final CanalePromozioneEntity channel3 = createCanalePromo(103L);
        final CanalePromozioneEntity channel4 = createCanalePromo(104L);
        groupFoo.addMuiCanalePromozione(channel1);
        groupFoo.addMuiCanalePromozione(channel2);
        groupBar.addMuiCanalePromozione(channel2);
        groupBar.addMuiCanalePromozione(channel3);
        groupBaz.addMuiCanalePromozione(channel4);
        openTransaction();
        groupDao.persist(groupFoo);
        groupDao.persist(groupBar);
        groupDao.persist(groupBaz);
        commitTransaction();
    }

    @Test
    public void testFindAllGroups() {
        List<GruppoPromozioneEntity> groups = groupDao.findAll();
        assertNotNull(groups);
        assertEquals(3, groups.size());
    }

    @Test
    public void findByCodiciCanale() {
        final List<Long> channelIds = Arrays.asList(101L, 102L, 103L);
        final List<GruppoPromozioneEntity> groups = groupDao.findAllByCodiciCanale(channelIds);
        assertEquals(2, groups.size());
        final List<String> codes = groups.stream().map(GruppoPromozioneEntity::getCodiceGruppo).collect(Collectors.toList());
        assertTrue(codes.contains("FOO"));
        assertTrue(codes.contains("BAR"));
        assertFalse(codes.contains("BAZ"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByCodiciCanale_givenEmptyList_shouldThrowException() {
        groupDao.findAllByCodiciCanale(Collections.emptyList());
    }

    private GruppoPromozioneEntity createGruppoPromo(Long id, String code) {
        final GruppoPromozioneEntity e = new GruppoPromozioneEntity();
        e.setId(id);
        e.setCodiceGruppo(code);
        e.setDescrizione(String.format("GROUP %s", code));
        return e;
    }

    private CanalePromozioneEntity createCanalePromo(Long id) {
        final CanalePromozioneEntity e = new CanalePromozioneEntity();
        e.setId(id);
        e.setCodiceCanale(id);
        e.setDescrizione(String.format("CHANNEL %02d", id));
        return e;
    }
}
