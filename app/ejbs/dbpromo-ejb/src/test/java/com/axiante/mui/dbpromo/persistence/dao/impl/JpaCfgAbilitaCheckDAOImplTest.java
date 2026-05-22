package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaCheckDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
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

public class JpaCfgAbilitaCheckDAOImplTest extends AbstractDaoTest {
    @Inject
    private CfgAbilitaCheckDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgAbilitaCheckDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CfgAbilitaMeccCanaleEntity meccanicaCanale;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("GR1")
                .withDescrizione("GRUPPO 1").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L)
                .withDescrizione("CANALE 1").withGruppo(gruppo).build();
        MeccanicheEntity meccanica = new MeccanicaEntityBuilder().withId(1L).withCodice("M001")
                .withDescrizione("MECCANICA 001").build();
        meccanicaCanale = createMeccanicaCanaleEntity(1L, meccanica, canale);
        CfgCheckEntity check = createCheckEntity(1L, "CHK-01", "CHECK 01", "FOO",
                "FOO", "FOO");
        CfgAbilitaCheckEntity abilitaCheck = createAbilitaCheckEntity(1L, check, meccanicaCanale, "FOO");
        persist(gruppo, canale, meccanica, meccanicaCanale, canale, abilitaCheck);
    }

    @Test
    public void findByCanaleMeccanica() {
        final List<CfgAbilitaCheckEntity> result = dao.findByCanaleMeccanica(meccanicaCanale);
        assertEquals(1, result.size());
    }

    private CfgAbilitaCheckEntity createAbilitaCheckEntity(Long id, CfgCheckEntity check,
                                                           CfgAbilitaMeccCanaleEntity meccanicaCanale, String severita) {
        CfgAbilitaCheckEntity e = new CfgAbilitaCheckEntity();
        e.setId(id);
        e.setCheck(check);
        e.setMeccanicaCanaleAbilitata(meccanicaCanale);
        e.setSeverita(severita);
        return e;
    }

    private CfgCheckEntity createCheckEntity(Long id, String codice, String descrizione, String messaggioErrore,
                                             String ambito, String tipoControllo) {
        CfgCheckEntity e = new CfgCheckEntity();
        e.setId(id);
        e.setCodice(codice);
        e.setDescrizione(descrizione);
        e.setMessaggioErrore(messaggioErrore);
        e.setAmbito(ambito);
        e.setTipoControllo(tipoControllo);
        return e;
    }

    private CfgAbilitaMeccCanaleEntity createMeccanicaCanaleEntity(Long id, MeccanicheEntity meccanica,
                                                                   CanalePromozioneEntity canale) {
        CfgAbilitaMeccCanaleEntity e = new CfgAbilitaMeccCanaleEntity();
        e.setId(id);
        e.setMeccanicheEntity(meccanica);
        e.setCanalePromozioneEntity(canale);
        return e;
    }
}