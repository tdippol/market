package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.UploadFidatyDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class JpaUploadFidatyDAOTest extends AbstractDaoTest {

    @Inject
    private UploadFidatyDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
        .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaUploadFidatyDAOImpl.class)
        .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private UploadFidayEntity entityMock;
    private UploadFidayEntity entityMock2;

    @Before
    public void setUp() throws Exception {
        PromozionePianificazioneEntity promozionePianificazioneEntity = createPromozionePianificazione();
        entityMock = new UploadFidayEntity();
        entityMock.setId(Long.parseLong("55"));
        entityMock.setPromozionePianificazioneEntity(promozionePianificazioneEntity);
        entityMock.setOriginalFileName("og");
        entityMock.setUserUpload("test");
        entityMock.setUploadedFileName("testName");
        entityMock.setDataPubblicazione(Date.from(LocalDate.of(2020, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        entityMock2 = new UploadFidayEntity();
        entityMock2.setId(Long.parseLong("56"));
        entityMock2.setPromozionePianificazioneEntity(promozionePianificazioneEntity);
        entityMock2.setOriginalFileName("og");
        entityMock2.setUserUpload("test");
        entityMock2.setUploadedFileName("testName");
        entityMock2.setDataPubblicazione(Date.from(LocalDate.of(2020, 12, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        openTransaction();
        dao.persist(entityMock);
        dao.persist(entityMock2);
        commitTransaction();
    }

    @Test
    public void shouldFindByPromozione() {
        final List<UploadFidayEntity> list = dao.findByPromozione(entityMock.getPromozionePianificazioneEntity().getPromozioneTestataEntity().getId());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(this.entityMock.getId(), list.get(0).getId());
        assertEquals(this.entityMock.getOriginalFileName(), list.get(0).getOriginalFileName());
        assertEquals(this.entityMock.getUserUpload(), list.get(0).getUserUpload());
        assertEquals(this.entityMock.getUploadedFileName(), list.get(0).getUploadedFileName());
        assertEquals(this.entityMock.getPromozionePianificazioneEntity().getId(), list.get(0).getPromozionePianificazioneEntity().getId());
    }

    @Test
    public void shouldNotFindByPromozione() {
        List<UploadFidayEntity> list = dao.findByPromozione(Long.MIN_VALUE);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldFindByPianificazione() {
        final List<UploadFidayEntity> list = dao.findByPianificazione(entityMock.getPromozionePianificazioneEntity().getId());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(this.entityMock.getId(), list.get(0).getId());
        assertEquals(this.entityMock.getOriginalFileName(), list.get(0).getOriginalFileName());
        assertEquals(this.entityMock.getUserUpload(), list.get(0).getUserUpload());
        assertEquals(this.entityMock.getUploadedFileName(), list.get(0).getUploadedFileName());
        assertEquals(this.entityMock.getPromozionePianificazioneEntity().getId(), list.get(0).getPromozionePianificazioneEntity().getId());
    }

    @Test
    public void shouldNotFindByPianificazione() {
        List<UploadFidayEntity> list = dao.findByPianificazione(Long.MIN_VALUE);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldFindValidByPromozione() {
        final List<UploadFidayEntity> list = dao.findValidByPromozione(entityMock.getPromozionePianificazioneEntity().getPromozioneTestataEntity().getId());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(this.entityMock.getId(), list.get(0).getId());
        assertEquals(this.entityMock.getOriginalFileName(), list.get(0).getOriginalFileName());
        assertEquals(this.entityMock.getUserUpload(), list.get(0).getUserUpload());
        assertEquals(this.entityMock.getUploadedFileName(), list.get(0).getUploadedFileName());
        assertEquals(this.entityMock.getPromozionePianificazioneEntity().getId(), list.get(0).getPromozionePianificazioneEntity().getId());
    }

    @Test
    public void shouldNotFindValidByPromozione() {
        List<UploadFidayEntity> list = dao.findValidByPromozione(Long.MIN_VALUE);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldFindByNomeDestinazioneAndPianificazione() {
        final UploadFidayEntity uploadFidaty = dao.findByNomeDestinazioneAndPianificazione(entityMock.getUploadedFileName(), entityMock.getPromozionePianificazioneEntity().getId());
        assertNotNull(uploadFidaty);
        assertEquals(this.entityMock2.getId(), uploadFidaty.getId());
        assertEquals(this.entityMock2.getOriginalFileName(), uploadFidaty.getOriginalFileName());
        assertEquals(this.entityMock2.getUserUpload(), uploadFidaty.getUserUpload());
        assertEquals(this.entityMock2.getUploadedFileName(), uploadFidaty.getUploadedFileName());
        assertEquals(this.entityMock2.getPromozionePianificazioneEntity().getId(), uploadFidaty.getPromozionePianificazioneEntity().getId());
    }

    @Test
    public void shouldNotFindByNomeDestinazioneAndPianificazione() {
        final UploadFidayEntity uploadFidaty = dao.findByNomeDestinazioneAndPianificazione("nomeFile", entityMock.getPromozionePianificazioneEntity().getId());
        assertNull(uploadFidaty);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFindByNomeDestinazioneAndPianificazioneThrowsNomeFileException() {
        dao.findByNomeDestinazioneAndPianificazione(null, entityMock.getPromozionePianificazioneEntity().getId());
    }

    @Test(expected = NullPointerException.class)
    public void shouldFindByNomeDestinazioneAndPianificazioneThrowsIdPianificazioneException() {
        dao.findByNomeDestinazioneAndPianificazione(entityMock.getUploadedFileName(), null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFindByNomeDestinazioneAndPianificazioneThrowsNUllException() {
        dao.findByNomeDestinazioneAndPianificazione(null, null);
    }

    private PromozionePianificazioneEntity createPromozionePianificazione() {
        PromozionePianificazioneEntity pianificazione = new PromozionePianificazioneEntity();
        pianificazione.setId(Long.parseLong("51"));
        pianificazione.setMeccanicaEntity(createMeccanica("meccanica", "M01"));
        pianificazione.setPromozioneTestataEntity(createPromozioneTestata(1L, "Semestre I", "2020",
            new GregorianCalendar(2020, 1, 1).getTime(),
            new GregorianCalendar(2020, 6, 30).getTime(), "COD000"));
//        pianificazione.setTipoRiga("MASTER");
        pianificazione.setTipoRiga(createTipoRiga("MASTER"));
        return  pianificazione;
    }

    private MeccanicheEntity createMeccanica(String descrizione, String codiceMeccanica) {
        MeccanicheEntity meccanicheEntity = new MeccanicheEntity();
        meccanicheEntity.setId(100L);
        meccanicheEntity.setDescrizione(descrizione);
        meccanicheEntity.setCodiceMeccanica(codiceMeccanica);
        return meccanicheEntity;
    }

    private GruppoPromozioneEntity createGruppoPromozione(String codiceGruppo, String descrizione) {
        GruppoPromozioneEntity gruppoPromozioneEntity = new GruppoPromozioneEntity();
        gruppoPromozioneEntity.setId(2L);
        gruppoPromozioneEntity.setCodiceGruppo(codiceGruppo);
        gruppoPromozioneEntity.setDescrizione(descrizione);
        return gruppoPromozioneEntity;
    }

    private PromozioneTestataEntity createPromozioneTestata(Long id, String semestre, String year, Date start, Date end, String promoCode) {
        PromozioneTestataEntity promozioneTestataEntity = new PromozioneTestataEntity();
        promozioneTestataEntity.setId(id);
        promozioneTestataEntity.setSemestre(semestre);
        promozioneTestataEntity.setAnno(year);
        promozioneTestataEntity.setDataInizio(start);
        promozioneTestataEntity.setDataFine(end);
        promozioneTestataEntity.setCodicePromozione(promoCode);
        promozioneTestataEntity.setMuiCanalePromozione(createCanalePromozione(1L, 10L, 100L, "descrizione test",
            createGruppoPromozione("GR1", "gruppo 1")));
        return promozioneTestataEntity;
    }

    private CanalePromozioneEntity createCanalePromozione(Long codiceCanale, Long codeRangeMin, Long codeRangeMax, String descrizione, GruppoPromozioneEntity gruppoPromozioneEntity) {
        CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
        canalePromozioneEntity.setId(2L);
        canalePromozioneEntity.setCodiceCanale(codiceCanale);
        canalePromozioneEntity.setCodeRangeMin(codeRangeMin);
        canalePromozioneEntity.setCodeRangeMax(codeRangeMax);
        canalePromozioneEntity.setDescrizione(descrizione);
        canalePromozioneEntity.setGruppoPromozioneEntity(gruppoPromozioneEntity);
        return canalePromozioneEntity;
    }
    
    private CfgPianificazTipoRigaEntity createTipoRiga(String descrizione) {
    	CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
    	entity.setId(1l);
    	entity.setDescrizione(descrizione);
    	entity.setCodiceTipo("M");
    	return entity;
    }
}
