package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Calendar;
import java.util.Date;
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
public class JpaPromozioneStatoDAOTest extends AbstractDaoTest {

    @Inject
    private PromozioneStatoDAO promozioneStatoDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
        .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaPromozioneStatoDAOImpl.class, DbPromo.class)
        .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private final static Long PROMOZIONETESTATAENTITY1_ID = 9L;
    private final static Long PROMOZIONETESTATAENTITY2_ID = 10L;

    private PromozioneStatoEntity promozioneStatoEntity1;
    private PromozioneStatoEntity promozioneStatoEntity2;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppoPromozioneEntity = createGruppoPromozioneEntity();
        CanalePromozioneEntity canalePromozioneEntity = createCanalePromozioneEntity(gruppoPromozioneEntity);
        StatoPromozioneEntity statoPromozioneEntity1 = createStatoPromozioneEntity(1l,"1234", PromoStatusEnum.CANCELLATA_00.getKey());
        StatoPromozioneEntity statoPromozioneEntity2 = createStatoPromozioneEntity(2l,"5678", PromoStatusEnum.CANCELLATA_00.getKey());
        PromozioneTestataEntity promozioneTestataEntity1 = createPromozioneTestataEntity(PROMOZIONETESTATAENTITY1_ID, "cod-120", canalePromozioneEntity);
        PromozioneTestataEntity promozioneTestataEntity2 = createPromozioneTestataEntity(PROMOZIONETESTATAENTITY2_ID, "cod-457", canalePromozioneEntity);
        promozioneStatoEntity1 = createPromozioneStatoEntity(61l, Calendar.getInstance().getTime(), statoPromozioneEntity1, promozioneTestataEntity1);
        promozioneStatoEntity2 = createPromozioneStatoEntity(62l, null, statoPromozioneEntity2, promozioneTestataEntity2);

        openTransaction();
        promozioneStatoDAO.persist(promozioneStatoEntity1);
        promozioneStatoDAO.persist(promozioneStatoEntity2);
        commitTransaction();
    }

    private GruppoPromozioneEntity createGruppoPromozioneEntity() {
        GruppoPromozioneEntity gruppoPromozioneEntity = new GruppoPromozioneEntity();
        gruppoPromozioneEntity.setId(1l);
        gruppoPromozioneEntity.setCodiceGruppo("g987");
        return gruppoPromozioneEntity;
    }

    private CanalePromozioneEntity createCanalePromozioneEntity(GruppoPromozioneEntity gruppoPromozioneEntity) {
        CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
        canalePromozioneEntity.setId(1l);
        canalePromozioneEntity.setCodiceCanale(1l);
        canalePromozioneEntity.setGruppoPromozioneEntity(gruppoPromozioneEntity);
        return canalePromozioneEntity;
    }

    private PromozioneTestataEntity createPromozioneTestataEntity(Long id, String codice, CanalePromozioneEntity canalePromozioneEntity) {
        PromozioneTestataEntity promozioneTestataEntity = new PromozioneTestataEntity();
        promozioneTestataEntity.setId(id);
        promozioneTestataEntity.setCodicePromozione(codice);
        promozioneTestataEntity.setDataInizio(Calendar.getInstance().getTime());
        promozioneTestataEntity.setDataFine(Calendar.getInstance().getTime());
        promozioneTestataEntity.setMuiCanalePromozione(canalePromozioneEntity);
        return promozioneTestataEntity;
    }

    private StatoPromozioneEntity createStatoPromozioneEntity(Long id, String descrizione, String codice) {
        StatoPromozioneEntity statoPromozioneEntity = new StatoPromozioneEntity();
        statoPromozioneEntity.setId(id);
        statoPromozioneEntity.setDescrizione(descrizione);
        statoPromozioneEntity.setCodiceStato(codice);
        return statoPromozioneEntity;
    }

    private PromozioneStatoEntity createPromozioneStatoEntity(Long id, Date dataFineStato, StatoPromozioneEntity statoPromozioneEntity, PromozioneTestataEntity promozioneTestataEntity) {
        PromozioneStatoEntity promozioneStatoEntity = new PromozioneStatoEntity();
        promozioneStatoEntity.setId(id);
        promozioneStatoEntity.setDataFineStato(dataFineStato);
        promozioneStatoEntity.setStatoPromozioneEntity(statoPromozioneEntity);
        promozioneStatoEntity.setPromozioneTestataEntity(promozioneTestataEntity);
        return promozioneStatoEntity;
    }

    @Test
    public void shouldFindAllByPromozioneID() {
        List<PromozioneStatoEntity> promozioneStatoEntities = promozioneStatoDAO.findByPromozioneID(null);
        assertNotNull(promozioneStatoEntities);
        assertTrue(promozioneStatoEntities.isEmpty());
        promozioneStatoEntities = promozioneStatoDAO.findByPromozioneID(9999l);
        assertNotNull(promozioneStatoEntities);
        assertTrue(promozioneStatoEntities.isEmpty());

        promozioneStatoEntities = promozioneStatoDAO.findByPromozioneID(PROMOZIONETESTATAENTITY1_ID);
        assertNotNull(promozioneStatoEntities);
        assertFalse(promozioneStatoEntities.isEmpty());
        assertEquals(promozioneStatoEntities.size(), 1);
        assertEquals(promozioneStatoEntity1.getId(), promozioneStatoEntities.get(0).getId());
        assertEquals(promozioneStatoEntity1.getDataFineStato(), promozioneStatoEntities.get(0).getDataFineStato());
        assertNotNull(promozioneStatoEntities.get(0).getStatoPromozioneEntity());
        assertEquals(promozioneStatoEntity1.getStatoPromozioneEntity().getId(), promozioneStatoEntities.get(0).getStatoPromozioneEntity().getId());
        assertEquals(promozioneStatoEntity1.getStatoPromozioneEntity().getCodiceStato(), promozioneStatoEntities.get(0).getStatoPromozioneEntity().getCodiceStato());
        assertEquals(promozioneStatoEntity1.getStatoPromozioneEntity().getDescrizione(), promozioneStatoEntities.get(0).getStatoPromozioneEntity().getDescrizione());
        assertNotNull(promozioneStatoEntities.get(0).getPromozioneTestataEntity());
        PromozioneTestataEntity promozioneTestataEntity = promozioneStatoEntities.get(0).getPromozioneTestataEntity();
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getId(), promozioneTestataEntity.getId());
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getCodicePromozione(), promozioneTestataEntity.getCodicePromozione());
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getDataInizio(), promozioneTestataEntity.getDataInizio());
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getDataFine(), promozioneTestataEntity.getDataFine());
        assertNotNull(promozioneTestataEntity.getMuiCanalePromozione());
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getId(), promozioneTestataEntity.getMuiCanalePromozione().getId());
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getCodiceCanale(), promozioneTestataEntity.getMuiCanalePromozione().getCodiceCanale());
        assertNotNull(promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity());
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
            promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
        assertEquals(promozioneStatoEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
            promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());

        promozioneStatoEntities = promozioneStatoDAO.findByPromozioneID(PROMOZIONETESTATAENTITY2_ID);
        assertNotNull(promozioneStatoEntities);
        assertFalse(promozioneStatoEntities.isEmpty());
        assertEquals(promozioneStatoEntities.size(), 1);
        assertEquals(promozioneStatoEntity2.getId(), promozioneStatoEntities.get(0).getId());
        assertEquals(promozioneStatoEntity2.getDataFineStato(), promozioneStatoEntities.get(0).getDataFineStato());
        assertNotNull(promozioneStatoEntities.get(0).getStatoPromozioneEntity());
        assertEquals(promozioneStatoEntity2.getStatoPromozioneEntity().getId(), promozioneStatoEntities.get(0).getStatoPromozioneEntity().getId());
        assertEquals(promozioneStatoEntity2.getStatoPromozioneEntity().getCodiceStato(), promozioneStatoEntities.get(0).getStatoPromozioneEntity().getCodiceStato());
        assertEquals(promozioneStatoEntity2.getStatoPromozioneEntity().getDescrizione(), promozioneStatoEntities.get(0).getStatoPromozioneEntity().getDescrizione());
        promozioneTestataEntity = promozioneStatoEntities.get(0).getPromozioneTestataEntity();
        assertNotNull(promozioneTestataEntity);
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getId(), promozioneTestataEntity.getId());
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getCodicePromozione(), promozioneTestataEntity.getCodicePromozione());
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getDataInizio(), promozioneTestataEntity.getDataInizio());
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getDataFine(), promozioneTestataEntity.getDataFine());
        assertNotNull(promozioneTestataEntity.getMuiCanalePromozione());
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getId(), promozioneTestataEntity.getMuiCanalePromozione().getId());
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getCodiceCanale(), promozioneTestataEntity.getMuiCanalePromozione().getCodiceCanale());
        assertNotNull(promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity());
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
            promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
        assertEquals(promozioneStatoEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
            promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());
    }
}
