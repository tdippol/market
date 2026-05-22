package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDefaultDAO;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.FornitoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JpaPromoFatturazioneDefaultDAOImplTest extends AbstractDaoTest {
    @Inject
    private PromoFatturazioneDefaultDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPromoFatturazioneDefaultDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        TipoPromoRifatturazioneEntity tipoPromozione = new TipoPromoRifatturazioneEntity();
        tipoPromozione.setCodice(1);
        tipoPromozione.setDescrizione("FOO");
        ResponsabileEntity responsabile = new ResponsabileEntityBuilder(1L).withCodice("R01").build();
        CompratoreEntity compratore1 = new CompratoreEntityBuilder(1L).withCodice("S01")
                .withResponsabile(responsabile).build();
        CompratoreEntity compratore2 = new CompratoreEntityBuilder(2L).withCodice("S02")
                .withResponsabile(responsabile).build();
        FornitoreEntity fornitore1 = new FornitoreEntityBuilder(1L).withCodice("F01").build();
        FornitoreEntity fornitore2 = new FornitoreEntityBuilder(2L).withCodice("F02").build();
        PromoFatturazioneDefaultEntity promoFatturazioneDefault1 = buildPromoFatturazioneDefault(compratore1,
                fornitore1, tipoPromozione, "SI");
        PromoFatturazioneDefaultEntity promoFatturazioneDefault2 = buildPromoFatturazioneDefault(compratore2,
                fornitore2, tipoPromozione, "NO");
        persist(tipoPromozione, responsabile, compratore1, compratore2, fornitore1, fornitore2,
                promoFatturazioneDefault1, promoFatturazioneDefault2);
    }

    @Test
    public void findAllByIdsCompratori_givenNullIds_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllByIdsCompratori(null);
    }

    @Test
    public void findAllByIdsCompratori_givenEmptyIds_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllByIdsCompratori(Collections.emptyList());
    }

    @Test
    public void findAllByIdsCompratori() {
        List<PromoFatturazioneDefaultEntity> result = dao.findAllByIdsCompratori(Collections.singletonList(1L));
        List<String> codiciCompratori = result.stream()
                .map(PromoFatturazioneDefaultEntity::getCompratore)
                .map(CompratoreEntity::getCodiceCompratore)
                .collect(Collectors.toList());
        assertEquals(1, result.size());
        assertTrue(codiciCompratori.contains("S01"));
        assertFalse(codiciCompratori.contains("S02"));
    }

    private PromoFatturazioneDefaultEntity buildPromoFatturazioneDefault(CompratoreEntity compratore,
                                                                         FornitoreEntity fornitore,
                                                                         TipoPromoRifatturazioneEntity tipoPromozione,
                                                                         String rifattura) {
        PromoFatturazioneDefaultEntity entity = new PromoFatturazioneDefaultEntity();
        entity.setCompratore(compratore);
        entity.setFornitore(fornitore);
        entity.setTipoPromozione(tipoPromozione);
        entity.setRifattura(rifattura);
        return entity;
    }
}