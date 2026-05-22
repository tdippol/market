package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgSovrapposizioneMeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSovrapposizioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgAbilitaMeccCanaleEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSovrapposizioneMeccanicheEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.MeccanicaEntityBuilder;
import java.util.List;
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
public class JpaCfgSovrapposizioneMeccanicheDAOTest extends AbstractDaoTest {

    @Inject
    private CfgSovrapposizioneMeccanicheDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgSovrapposizioneMeccanicheDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CfgAbilitaMeccCanaleEntity meccanicaCanale1;

    @Before
    public void setUp() throws Exception {
        final GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withCodice("GR1")
                .withDescrizione("Gruppo 1").build();
        final CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withCodice(1L)
                .withDescrizione("Canale 1").withGruppo(gruppo).build();
        final MeccanicheEntity meccanica1 = new MeccanicaEntityBuilder().withCodice("M001")
                .withDescrizione("Meccanica 001").build();
        final MeccanicheEntity meccanica2 = new MeccanicaEntityBuilder().withCodice("M002")
                .withDescrizione("Meccanica 002").build();
        meccanicaCanale1 = new CfgAbilitaMeccCanaleEntityBuilder()
                .withCanale(canale).withMeccanica(meccanica1).build();
        CfgAbilitaMeccCanaleEntity meccanicaCanale2 = new CfgAbilitaMeccCanaleEntityBuilder()
                .withCanale(canale).withMeccanica(meccanica2).build();
        CfgSovrapposizioneMeccanicheEntity sovr1 = new CfgSovrapposizioneMeccanicheEntityBuilder().withMeccanicaCanaleAbilitata(meccanicaCanale1)
                .withMeccanicaSovrapposta(meccanica2).build();
        CfgSovrapposizioneMeccanicheEntity sovr2 = new CfgSovrapposizioneMeccanicheEntityBuilder().withMeccanicaCanaleAbilitata(meccanicaCanale2)
                .withMeccanicaSovrapposta(meccanica1).build();
        openTransaction();
        em.persist(gruppo);
        em.persist(canale);
        em.persist(meccanica1);
        em.persist(meccanica2);
        em.persist(meccanicaCanale1);
        em.persist(meccanicaCanale2);
        em.persist(sovr1);
        em.persist(sovr2);
        commitTransaction();
    }

    @Test
    public void findByCanaleMeccanica_givenNullMeccanicaCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByCanaleMeccanica(null);
    }

    @Test
    public void findByCanaleMeccanica_givenExistingMeccanicaCanale_shouldReturnEntities() {
        final List<CfgSovrapposizioneMeccanicheEntity> entities = dao.findByCanaleMeccanica(meccanicaCanale1);
        assertEquals(1, entities.size());
        final CfgSovrapposizioneMeccanicheEntity sovr = entities.get(0);
        assertEquals("M001", sovr.getMeccanicaCanaleAbilitata().getMeccanicheEntity().getCodiceMeccanica());
        assertEquals(1L, (long) sovr.getMeccanicaCanaleAbilitata().getCanalePromozioneEntity().getCodiceCanale());
    }

    @Test
    public void findByCanaleMeccanica_givenNotExistingMeccanicaCanale_shouldReturnEmptyList() {
        final GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withCodice("GR9")
                .withDescrizione("Gruppo 9").build();
        final CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withCodice(99L)
                .withDescrizione("Canale 99").withGruppo(gruppo).build();
        final MeccanicheEntity meccanica = new MeccanicaEntityBuilder().withCodice("M099")
                .withDescrizione("Meccanica 099").build();
        final CfgAbilitaMeccCanaleEntity meccanicaCanale = new CfgAbilitaMeccCanaleEntityBuilder()
                .withCanale(canale).withMeccanica(meccanica).build();
        openTransaction();
        em.persist(gruppo);
        em.persist(canale);
        em.persist(meccanica);
        em.persist(meccanicaCanale);
        commitTransaction();
        assertTrue(dao.findByCanaleMeccanica(meccanicaCanale).isEmpty());
    }
}
