package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ReportBSDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSId;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JpaReportBSDAOImplTest extends AbstractDaoTest {
    @Inject
    private ReportBSDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaReportBSDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class)
            .inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        final Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        final int year = cal.get(Calendar.YEAR);
        Date dataInizio1 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .minusDays(20).atZone(ZoneId.systemDefault()).toInstant());
        Date dataFine1 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
        Date dataInizio2 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
        Date dataFine2 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .plusDays(10).atZone(ZoneId.systemDefault()).toInstant());
        Date dataInizio3 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .minusDays(20).atZone(ZoneId.systemDefault()).toInstant());
        Date dataFine3 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .minusDays(5).atZone(ZoneId.systemDefault()).toInstant());
        MuiReportBSEntity entity1 = createEntity("00001", "PROMO-01", "PROMOZIONE 01", String.valueOf(year),
                dataInizio1, dataFine1);
        MuiReportBSEntity entity2 = createEntity("00002", "PROMO-02", "PROMOZIONE 02", String.valueOf(year),
                dataInizio2, dataFine2);
        MuiReportBSEntity entity3 = createEntity("00003", "PROMO-03", "PROMOZIONE 03", String.valueOf(year),
                dataInizio3, dataFine3);
        persist(entity1, entity2, entity3);
    }

    @Test
    public void findAllInProgressByPrefissoBS_givenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllInProgressByPrefissoBS(null);
    }

    @Test
    public void findAllInProgressByPrefissoBS() {
        final List<MuiReportBSEntity> result = dao.findAllInProgressByPrefissoBS("00001");
        assertEquals(1, result.size());
        final MuiReportBSEntity entity = result.get(0);
        assertEquals("00001", entity.getId().getPrefissoBS());
        assertEquals("PROMO-01", entity.getId().getCodicePromozione());
        assertEquals("PROMOZIONE 01", entity.getDescrizionePromozione());
    }

    @Test
    public void findAllFuturesByPrefissoBS_givenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllFuturesByPrefissoBS(null);
    }

    @Test
    public void findAllFuturesByPrefissoBS() {
        final List<MuiReportBSEntity> result = dao.findAllFuturesByPrefissoBS("00002");
        assertEquals(1, result.size());
        final MuiReportBSEntity entity = result.get(0);
        assertEquals("00002", entity.getId().getPrefissoBS());
        assertEquals("PROMO-02", entity.getId().getCodicePromozione());
        assertEquals("PROMOZIONE 02", entity.getDescrizionePromozione());
    }

    @Test
    public void findAllCompletedByPrefissoBS_givenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllCompletedByPrefissoBS(null);
    }

    @Test
    public void findAllCompletedByPrefissoBS() {
        final List<MuiReportBSEntity> result = dao.findAllCompletedByPrefissoBS("00003");
        assertEquals(1, result.size());
        final MuiReportBSEntity entity = result.get(0);
        assertEquals("00003", entity.getId().getPrefissoBS());
        assertEquals("PROMO-03", entity.getId().getCodicePromozione());
        assertEquals("PROMOZIONE 03", entity.getDescrizionePromozione());
    }

    @Test
    public void findAll() {
        assertEquals(3, dao.findAll().size());
    }

    @Test
    public void findById_givenNullCodicePromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findById(null, "BS");
    }

    @Test
    public void findById_givenNullPrefissoBS_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findById("P", null);
    }

    @Test
    public void findById() {
        MuiReportBSEntity entity = dao.findById("PROMO-01", "00001");
        assertNotNull(entity);
        assertEquals("PROMO-01", entity.getId().getCodicePromozione());
        assertEquals("00001", entity.getId().getPrefissoBS());
        assertEquals("PROMOZIONE 01", entity.getDescrizionePromozione());
    }

    @Test
    public void findAllNotUsedInProgress() {
        final List<String> chiavi = Stream.of("PROMO-01|00001", "PROMO-02|00002").collect(Collectors.toList());
        final List<MuiReportBSEntity> result = dao.findAllNotUsedInProgress(chiavi, new Date());
        assertTrue(result.isEmpty());
    }

    @Test
    public void findAllNotUsedInBetween() {
        final Date today = new Date();
        Date data1 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        Date data2 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        final List<String> chiavi = Stream.of("PROMO-01|00001", "PROMO-02|00002").collect(Collectors.toList());
        final List<MuiReportBSEntity> result = dao.findAllNotUsedInBetween(chiavi, data1, data2, today);
        assertTrue(result.isEmpty());
    }

    @Test
    public void findAllNotUsedInBetween_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllNotUsedInBetween(Collections.emptyList(), null, new Date(), new Date());
    }

    @Test
    public void findAllNotUsedInBetween_givenNullDataFinale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllNotUsedInBetween(Collections.emptyList(), new Date(), null, new Date());
    }

    @Test
    public void findAllNotUsedInBetween_givenNullToday_shouldThrowException() {
        final Date today = new Date();
        Date data1 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        Date data2 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        ex.expect(NullPointerException.class);
        dao.findAllNotUsedInBetween(Collections.emptyList(), data1, data2, null);
    }

    @Test
    public void findInBetween() {
        final Date today = new Date();
        Date data1 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        Date data2 = Date.from(LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault())
                .plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        final List<MuiReportBSEntity> result = dao.findInBetween(data1, data2, today);
        assertEquals(1, result.size());
    }

    @Test
    public void findInProgress() {
        final List<MuiReportBSEntity> result = dao.findInProgress(new Date());
        assertEquals(2, result.size());
    }

    @Test
    public void findWithChiaveIn() {
        final List<String> chiavi = Stream.of("PROMO-01|00001", "PROMO-02|00002").collect(Collectors.toList());
        final List<MuiReportBSEntity> result = dao.findWithChiaveIn(chiavi);
        assertEquals(2, result.size());
    }

    @Test
    public void findWithChiaveIn_givenNullOrEmptyChiavi_shouldReturnAll() {
        List<MuiReportBSEntity> result = dao.findWithChiaveIn(null);
        assertEquals(3, result.size());
        result = dao.findWithChiaveIn(Collections.emptyList());
        assertEquals(3, result.size());
    }

    private MuiReportBSEntity createEntity(String prefissoBS, String codicePromo, String descrizionePromo, String anno,
                                           Date dataInizio, Date dataFine) {
        MuiReportBSId id = new MuiReportBSId(codicePromo, prefissoBS);
        MuiReportBSEntity e = new MuiReportBSEntity();
        e.setId(id);
        e.setDescrizionePromozione(descrizionePromo);
        e.setAnno(anno);
        e.setDataInizio(dataInizio);
        e.setDataFine(dataFine);
        e.setCodiceGruppo("GR-01");
        e.setDescrizioneGruppo("GRUPPO 01");
        e.setCodiceCanale(1L);
        e.setDescrizioneCanale("CANALE 01");
        e.setCodiceStato("10");
        e.setDescrizioneStato("STATO 10");
        e.setChiave(id.getCodicePromozione() + "|" + id.getPrefissoBS());
        return e;
    }
}