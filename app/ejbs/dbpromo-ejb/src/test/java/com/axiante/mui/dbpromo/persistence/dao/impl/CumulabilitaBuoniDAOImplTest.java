package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaBuoniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CumulabilitaBuoniDAOImplTest extends AbstractDaoTest {
    @Inject
    private CumulabilitaBuoniDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    CumulabilitaBuoniDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Before
    public void setUp() throws Exception {
        CumulabilitaEsclusivitaEntity cumulabilita = createComulabilita(CumulabilitaType.CUMULABILE, new Date(), new Date(), "PROMO-01");
        CumulabilitaBuoniEntity entity1 = createEntity(1L, "BS-01", "C01", "P-01");
        CumulabilitaBuoniEntity entity2 = createEntity(2L, "BS-02", "C02", "P-02");
        CumulabilitaBuoniEntity entity3 = createEntity(3L, "BS-42", "C42", "P-42");
        CumulabilitaBuoniEntity entity4 = createEntity(4L, "BS-42", "C42", "P-42");
        cumulabilita.addCumulabilitaBuoniEntity(entity1);
        cumulabilita.addCumulabilitaBuoniEntity(entity2);
        cumulabilita.addCumulabilitaBuoniEntity(entity3);
        cumulabilita.addCumulabilitaBuoniEntity(entity4);
        persist(cumulabilita);
    }

    @Test
    public void findByCodicePromozioneAndCodiceCanaleAndPrefissoBs() {
        assertNotNull(dao.findByCodicePromozioneAndCodiceCanaleAndPrefissoBs("P-01", "C01", "BS-01"));
    }

    @Test
    public void findByCodicePromozioneAndCodiceCanaleAndPrefissoBs_notExisting() {
        assertNull(dao.findByCodicePromozioneAndCodiceCanaleAndPrefissoBs("P-42", "C01", "BS-42"));
    }

    @Test
    public void findByCodicePromozioneAndCodiceCanaleAndPrefissoBs_moreThanOne() {
        assertNull(dao.findByCodicePromozioneAndCodiceCanaleAndPrefissoBs("P-42", "C42", "BS-42"));
    }

    private CumulabilitaBuoniEntity createEntity(Long id, String prefissoBS, String codiceCanale,
                                                 String codicePromozione) {
        CumulabilitaBuoniEntity entity = new CumulabilitaBuoniEntity();
        entity.setId(id);
        entity.setPrefissoBS(prefissoBS);
        entity.setCodiceCanale(codiceCanale);
        entity.setCodicePromozione(codicePromozione);
        entity.setDescrizionePromozione("PROMO " + codicePromozione);
        entity.setCodiceUtenteInserimento("user");
        entity.setDataInserimento(new Date());
        entity.setDataInizio(new Date());
        entity.setDataFine(new Date());
        return entity;
    }

    private CumulabilitaEsclusivitaEntity createComulabilita(CumulabilitaType tipo, Date dataInizio, Date dataFine,
                                                             String codicePromozione) {
        CumulabilitaEsclusivitaEntity entity = new CumulabilitaEsclusivitaEntity();
        entity.setTipo(tipo);
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        entity.setCodicePromozione(codicePromozione);
        entity.setDescrizione(codicePromozione);
        entity.setPrefissoBS("BS");
        entity.setCodiceUtenteInserimento("user");
        entity.setDataInserimento(new Date(System.currentTimeMillis()));
        return entity;
    }
}