package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ContributiArticoliDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CategoriaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.FornitoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GrmEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ItemEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.RepartoEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.SubGrmEntityBuilder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaContributiArticoliDAOImplTest extends AbstractDaoTest {
    @Inject
    private ContributiArticoliDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaContributiArticoliDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("R01").build();
        CompratoreEntity compratore = new CompratoreEntityBuilder(1L).withCodice("S01")
                .withResponsabile(responsabile).build();
        FornitoreEntity fornitore = new FornitoreEntityBuilder(1L).withCodice("F01").build();
        CfgSetPianificazioneEntity cfgSetPianificazione = new CfgSetPianificazioneEntityBuilder(1L)
                .withDataInizio(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime())
                .withDescrizione("JUNIT TEST")
                .build();
        PromozioneTestataEntity testata = new PromozioneTestataEntityBuilder(1L).withCodice("PROMO 01")
                .withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime())
                .withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 31).getTime())
                .withCanale(canale).withCfgSetPianificazione(cfgSetPianificazione).build();
        RepartoEntity reparto = new RepartoEntityBuilder(1L).withCodice("RE").build();
        CategoriaEntity categoria = new CategoriaEntityBuilder(1L).withCodice("FOO").withReparto(reparto).build();
        GrmEntity grm = new GrmEntityBuilder(1L).withCodice("001").withCategoria(categoria).build();
        SubGrmEntity subGrm = new SubGrmEntityBuilder(1L).withCodice("001").withGrm(grm).build();
        ItemEntity item = new ItemEntityBuilder(1L).withCodice("I0001").withCompratore(compratore)
                .withSubGrm(subGrm).build();
        ContributiPromoEntity contributoPromo1 = createContributoPromo(1L, compratore, fornitore, testata);
        ContributiPromoEntity contributoPromo2 = createContributoPromo(2L, compratore, fornitore, testata);
        ContributiPromoEntity contributoPromo3 = createContributoPromo(3L, compratore, fornitore, testata);
        ContributiPromoArticoloEntity contributoPromoArticolo1 = createContributoPromoArticolo(1L, contributoPromo1, item);
        ContributiPromoArticoloEntity contributoPromoArticolo2 = createContributoPromoArticolo(2L, contributoPromo2, item);
        ContributiPromoArticoloEntity contributoPromoArticolo3 = createContributoPromoArticolo(3L, contributoPromo3, item);
        persist(contributoPromo1, contributoPromo2, contributoPromo3, contributoPromoArticolo1, contributoPromoArticolo2,
                contributoPromoArticolo3, responsabile, compratore, fornitore, cfgSetPianificazione, testata,
                gruppo, canale, item, subGrm, grm, categoria, reparto);
    }

    @Test
    public void findAllByIds() {
        final List<ContributiPromoArticoloEntity> result = dao.findAllByIds(Arrays.asList(1L, 2L));
        assertEquals(2, result.size());
    }

    @Test
    public void findAllByIds_givenNullIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByIds(null);
    }

    @Test
    public void findAllByIds_givenEmptyIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByIds(Collections.emptyList());
    }

    @Test
    public void findAllByIdRata() {
        final List<ContributiPromoArticoloEntity> result = dao.findAllByIdRata(1L);
        assertEquals(1, result.size());
    }

    private ContributiPromoArticoloEntity createContributoPromoArticolo(Long id, ContributiPromoEntity contributoPromo,
                                                                        ItemEntity item) {
        final ContributiPromoArticoloEntity e = new ContributiPromoArticoloEntity();
        e.setId(id);
        e.setContributiPromo(contributoPromo);
        e.setItem(item);
        return e;
    }

    private ContributiPromoEntity createContributoPromo(Long id, CompratoreEntity compratore, FornitoreEntity fornitore,
                                                        PromozioneTestataEntity promozione) {
        final ContributiPromoEntity e = new ContributiPromoEntity();
        e.setId(id);
        e.setCompratore(compratore);
        e.setFornitore(fornitore);
        e.setPromozione(promozione);
        return e;
    }
}