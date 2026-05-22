package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianificazioneTotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.MeccanicaEntityBuilder;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PianificazioneTotalizzatoriDAOImplTest extends AbstractDaoTest {
    @Inject
    private PianificazioneTotalizzatoriDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    PianificazioneTotalizzatoriDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder().withId(1L).withCodice("G1").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder().withId(1L).withCodice(1L).withGruppo(gruppo).build();
        MeccanicheEntity meccanica = new MeccanicaEntityBuilder().withId(1L).withCodice("M001").withDescrizione("MECCANICA 001")
                .build();
        PromozioneTestataEntity testata = createPromozioneTestataEntity(1L, "PROMO 001", new Date(), new Date(), canale);
        CfgPianificazTipoRigaEntity tipoRiga = createTipoRiga(1L, "R1");
        PromozionePianificazioneEntity p = createPromozionePianificazioneEntity(1L, testata, meccanica, tipoRiga);
        PianificazioneTotalizzatoriEntity e1 = createPianificazioneTotalizzatoriEntity(1L, "FOO", null, p);
        PianificazioneTotalizzatoriEntity e2 = createPianificazioneTotalizzatoriEntity(2L, "BAR", 2L, p);
        persist(gruppo, canale, meccanica, testata, p, e1, e2);
    }

    @Test
    public void findAllWithParentByIdPianificazione() {
        List<PianificazioneTotalizzatoriEntity> result = dao.findAllWithParentByIdPianificazione(1L);
        assertEquals(1, result.size());
        PianificazioneTotalizzatoriEntity entity = result.get(0);
        assertEquals(2L, (long) entity.getId());
        assertEquals("BAR", entity.getDescrizione());
        assertEquals(2L, (long) entity.getIdParent());
    }

    private PianificazioneTotalizzatoriEntity createPianificazioneTotalizzatoriEntity(Long id, String descrizione, Long idParent,
                                                                                      PromozionePianificazioneEntity pianificazione) {
        PianificazioneTotalizzatoriEntity e = new PianificazioneTotalizzatoriEntity();
        e.setId(id);
        e.setDescrizione(descrizione);
        e.setIdParent(idParent);
        e.setPianificazione(pianificazione);
        return e;
    }

    private PromozionePianificazioneEntity createPromozionePianificazioneEntity(Long id, PromozioneTestataEntity testata,
                                                                                MeccanicheEntity meccanica,
                                                                                CfgPianificazTipoRigaEntity tipoRiga) {
        PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();
        e.setId(id);
        e.setPromozioneTestataEntity(testata);
        e.setMeccanicaEntity(meccanica);
        e.setTipoRiga(tipoRiga);
        return e;
    }

    private PromozioneTestataEntity createPromozioneTestataEntity(Long id, String codice, Date dataInizio, Date dataFine,
                                                                  CanalePromozioneEntity canale) {
        PromozioneTestataEntity e = new PromozioneTestataEntity();
        e.setId(id);
        e.setCodicePromozione(codice);
        e.setDataInizio(dataInizio);
        e.setDataFine(dataFine);
        e.setCanalePromozioneEntity(canale);
        return e;
    }

    private CfgPianificazTipoRigaEntity createTipoRiga(Long id, String codice) {
        CfgPianificazTipoRigaEntity e = new CfgPianificazTipoRigaEntity();
        e.setId(id);
        e.setCodiceTipo(codice);
        return e;
    }
}