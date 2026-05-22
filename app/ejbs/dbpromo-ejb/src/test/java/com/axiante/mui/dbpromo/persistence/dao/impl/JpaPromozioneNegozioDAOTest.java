package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
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
public class JpaPromozioneNegozioDAOTest extends AbstractDaoTest {

    @Inject
    private PromozioneNegozioDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
        .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaPromozioneNegozioDAOImpl.class)
        .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PromozioneNegozioEntity entityMock;

    @Before
    public void setUp() throws Exception {
        entityMock = new PromozioneNegozioEntity();
        entityMock.setId(Long.parseLong("55"));
        entityMock.setPromozioneTestataEntity(createTestata(1L, "Semestre I", "2020",
            new GregorianCalendar(2020, Calendar.FEBRUARY, 1).getTime(),
            new GregorianCalendar(2020, Calendar.JULY, 30).getTime(), "COD000"));
        entityMock.setNegozioEntity(createNegozio("TYPE_1", 2L, "111", "FMT", "00", "descrizione 1", "S"));

        openTransaction();
        dao.persist(entityMock);
        commitTransaction();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_givenNullIds_shouldThrowException() {
        dao.findById((List<Long>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_givenEmptyIds_shouldThrowException() {
        dao.findById(Collections.emptyList());
    }

    @Test
    public void shouldFindById() {
        final List<PromozioneNegozioEntity> list = dao.findById(Collections.singletonList(entityMock.getId()));
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(this.entityMock.getId(), list.get(0).getId());
        assertEquals(this.entityMock.getPromozioneTestataEntity().getId(), list.get(0).getPromozioneTestataEntity().getId());
        assertEquals(this.entityMock.getNegozioEntity().getId(), list.get(0).getNegozioEntity().getId());
    }

    @Test
    public void shouldNotFindById() {
        List<PromozioneNegozioEntity> list = dao.findById(Collections.singletonList(Long.MIN_VALUE));
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldFindByIdWithIdListGreaterThanMaxChunk() {
        List<PromozioneNegozioEntity> list = dao.findById(LongStream.rangeClosed(0, 1000).boxed().collect(Collectors.toList()));
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(this.entityMock.getId(), list.get(0).getId());
        assertEquals(this.entityMock.getPromozioneTestataEntity().getId(), list.get(0).getPromozioneTestataEntity().getId());
        assertEquals(this.entityMock.getNegozioEntity().getId(), list.get(0).getNegozioEntity().getId());
    }

    private GruppoPromozioneEntity createGruppoPromozione(String codiceGruppo, String descrizione) {
        GruppoPromozioneEntity gruppoPromozione = new GruppoPromozioneEntity();
        gruppoPromozione.setId(2L);
        gruppoPromozione.setCodiceGruppo(codiceGruppo);
        gruppoPromozione.setDescrizione(descrizione);
        return gruppoPromozione;
    }

    private CanalePromozioneEntity createCanalePromozione(Long codiceCanale, Long codeRangeMin, Long codeRangeMax, String descrizione, GruppoPromozioneEntity gruppoPromozioneEntity) {
        CanalePromozioneEntity canalePromozione = new CanalePromozioneEntity();
        canalePromozione.setId(2L);
        canalePromozione.setCodiceCanale(codiceCanale);
        canalePromozione.setCodeRangeMin(codeRangeMin);
        canalePromozione.setCodeRangeMax(codeRangeMax);
        canalePromozione.setDescrizione(descrizione);
        canalePromozione.setGruppoPromozioneEntity(gruppoPromozioneEntity);
        return canalePromozione;
    }

    private PromozioneTestataEntity createTestata(Long id, String semestre, String year, Date start, Date end, String promoCode) {
        PromozioneTestataEntity promozioneTestata = new PromozioneTestataEntity();
        promozioneTestata.setId(id);
        promozioneTestata.setSemestre(semestre);
        promozioneTestata.setAnno(year);
        promozioneTestata.setDataInizio(start);
        promozioneTestata.setDataFine(end);
        promozioneTestata.setCodicePromozione(promoCode);
        promozioneTestata.setMuiCanalePromozione(createCanalePromozione(1L, 10L, 100L, "descrizione test",
            createGruppoPromozione("GR1", "gruppo 1")));
        return promozioneTestata;
    }

    private NegozioEntity createNegozio(String tipoNegozio, Long codiceCluster, String codiceNegozio,
                                        String codiceFormato, String codiceZona, String descrizione, String societa) {
        NegozioEntity negozio = new NegozioEntity();
        negozio.setId(5L);
        negozio.setTipoNegozio(tipoNegozio);
        negozio.setCodiceCluster(codiceCluster);
        negozio.setCodiceNegozio(codiceNegozio);
        negozio.setCodiceFormato(codiceFormato);
        negozio.setCodiceZona(codiceZona);
        negozio.setDescrizione(descrizione);
        negozio.setSocieta(societa);
        negozio.setTipoNegozioEntity(createTipoNegozio(negozio, "descrizione 1", "S"));
        return negozio;
    }

    private TipoNegozioEntity createTipoNegozio(NegozioEntity negozioEntity, String descrizione, String codiceTipoNegozio) {
        TipoNegozioEntity tipoNegozio = new TipoNegozioEntity();
        tipoNegozio.setNegozioEntities(new HashSet<>());
        tipoNegozio.addMuiNegozio(negozioEntity);
        tipoNegozio.setDescrizione(descrizione);
        tipoNegozio.setId(99L);
        tipoNegozio.setCodiceTipoNegozio(codiceTipoNegozio);
        return tipoNegozio;
    }
}
