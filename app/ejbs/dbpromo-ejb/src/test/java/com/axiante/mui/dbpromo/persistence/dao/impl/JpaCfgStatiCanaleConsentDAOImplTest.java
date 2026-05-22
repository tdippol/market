package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgStatiCanaleConsentDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgAzioniEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.StatoPromozioneEntityBuilder;
import java.util.HashSet;
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
public class JpaCfgStatiCanaleConsentDAOImplTest extends AbstractDaoTest {

    @Inject
    private CfgStatiCanaleConsentDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaCfgStatiCanaleConsentDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CanalePromozioneEntity canale;
    private StatoPromozioneEntity statoPromozione;
    private CfgStatiCanaleConsentEntity cfgStatiCanaleConsent;
    private CfgAzioniEntity cfgAzione;

    @Before
    public void setUp() throws Exception {
        statoPromozione = new StatoPromozioneEntityBuilder(1L).withCodice("500").withDescrizione("COMPLETATA").build();
        cfgAzione = new CfgAzioniEntityBuilder(1L).withCodice("FOO").build();
        cfgStatiCanaleConsent = createCfgStatiCanaleConsent(canale, statoPromozione, "COD_11");
        cfgStatiCanaleConsent.addAzione(cfgAzione);
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        canale = new CanalePromozioneEntityBuilder(1L).withCodice(100L).withGruppo(gruppo).build();
        canale.setMuiCfgStatiCanaleConsents(new HashSet<>());
        canale.addMuiCfgStatiCanaleConsent(cfgStatiCanaleConsent);
        persist(cfgStatiCanaleConsent);
    }

    @Test
    public void shouldFindAllAllowedByChannel() {
        final List<CfgStatiCanaleConsentEntity> allPromoAllowedStateByChannel = dao
                .findAllPromoAllowedStateByChannel(canale);
        assertNotNull(allPromoAllowedStateByChannel);
        assertFalse(allPromoAllowedStateByChannel.isEmpty());
    }

    @Test
    public void shouldNotFindAllAllowedByChannel() {
        final List<CfgStatiCanaleConsentEntity> allPromoAllowedStateByChannel = dao
                .findAllPromoAllowedStateByChannel(null);
        assertNotNull(allPromoAllowedStateByChannel);
        assertTrue(allPromoAllowedStateByChannel.isEmpty());
    }

    @Test
    public void findByCanaleAndStato() {
        assertEquals(cfgStatiCanaleConsent, dao.findByCanaleAndStato(canale, statoPromozione));
    }

    @Test
    public void findAzioniConsentByCanale() {
        final List<CfgAzioniEntity> result = dao.findAzioniConsentByCanale(canale);
        assertEquals(1, result.size());
        assertTrue(result.contains(cfgAzione));
    }

    @Test
    public void findAzioniConsentByCanaleAndStato() {
        final List<CfgAzioniEntity> result = dao.findAzioniConsentByCanaleAndStato(canale, statoPromozione);
        assertEquals(1, result.size());
        assertTrue(result.contains(cfgAzione));
    }

    @Test
    public void findCodiciAzioniConsentiteByCanaleAndStato() {
        final List<String> result = dao.findCodiciAzioniConsentiteByCanaleAndStato(canale, statoPromozione);
        assertEquals(1, result.size());
        assertEquals("FOO", result.get(0));
    }

    private CfgStatiCanaleConsentEntity createCfgStatiCanaleConsent(CanalePromozioneEntity canale,
                                                                    StatoPromozioneEntity statoPromozione,
                                                                    String codiceUtenteAggiornamento) {
        CfgStatiCanaleConsentEntity cfgStatiCanaleConsentEntity = new CfgStatiCanaleConsentEntity();
        cfgStatiCanaleConsentEntity.setId(1L);
        cfgStatiCanaleConsentEntity.setMuiCanalePromozione(canale);
        cfgStatiCanaleConsentEntity.setStatoPromozioneEntity(statoPromozione);
        cfgStatiCanaleConsentEntity.setCodiceUtenteAggiornamento(codiceUtenteAggiornamento);
        return cfgStatiCanaleConsentEntity;
    }
}
