package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.ReportArticoloDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloId;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

public class JpaReportArticoloDAOImplTest extends AbstractDaoTest {

    @Inject
    private ReportArticoloDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaReportArticoloDAOImpl.class, DbPromo.class)
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
        MuiReportArticoloEntity entity1 = createEntity("PROMO-01", 1L, "1_01",
                "M001", "C001", String.valueOf(year), "PROMOZIONE 01",
                "I-001", "ITEM 001", dataInizio1, dataFine1);
        MuiReportArticoloEntity entity2 = createEntity("PROMO-02", 1L, "1_02",
                "M002", "C002", String.valueOf(year), "PROMOZIONE 02",
                "I-002", "ITEM 002", dataInizio2, dataFine2);
        MuiReportArticoloEntity entity3 = createEntity("PROMO-03", 1L, "1_03",
                "M003", "C003", String.valueOf(year), "PROMOZIONE 03",
                "I-003", "ITEM 003", dataInizio3, dataFine3);
        persist(entity1, entity2, entity3);
    }

    @Test
    public void findAllInProgressFuturesByIdItem_givenNullIdItem_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllInProgressFuturesByIdItem(null);
    }

    @Test
    public void findAllInProgressFuturesByIdItem() {
        final List<MuiReportArticoloEntity> result = dao.findAllInProgressFuturesByIdItem(1L);
        assertEquals(2, result.size());
        final MuiReportArticoloEntity entity1 = result.stream()
                .filter(e -> e.getId().getCodicePromozione().equals("PROMO-01"))
                .findFirst().orElse(null);
        assertNotNull(entity1);
        assertEquals(1L, (long) entity1.getId().getIdItem());
        assertEquals("PROMO-01", entity1.getId().getCodicePromozione());
        assertEquals("PROMOZIONE 01", entity1.getDescrizionePromozione());
        assertEquals("I-001", entity1.getCodiceArticolo());
        assertEquals("ITEM 001", entity1.getDescrizioneArticolo());
        final MuiReportArticoloEntity entity2 = result.stream()
                .filter(e -> e.getId().getCodicePromozione().equals("PROMO-02"))
                .findFirst().orElse(null);
        assertNotNull(entity2);
        assertEquals(1L, (long) entity2.getId().getIdItem());
        assertEquals("PROMO-02", entity2.getId().getCodicePromozione());
        assertEquals("PROMOZIONE 02", entity2.getDescrizionePromozione());
        assertEquals("I-002", entity2.getCodiceArticolo());
        assertEquals("ITEM 002", entity2.getDescrizioneArticolo());
    }

    @Test
    public void findAllCompletedByIdItem_givenNullIdItem_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllCompletedByIdItem(null);
    }

    @Test
    public void findAllCompletedByIdItem() {
        final List<MuiReportArticoloEntity> result = dao.findAllCompletedByIdItem(1L);
        assertEquals(1, result.size());
        final MuiReportArticoloEntity entity = result.get(0);
        assertEquals(1L, (long) entity.getId().getIdItem());
        assertEquals("PROMO-03", entity.getId().getCodicePromozione());
        assertEquals("PROMOZIONE 03", entity.getDescrizionePromozione());
        assertEquals("I-003", entity.getCodiceArticolo());
        assertEquals("ITEM 003", entity.getDescrizioneArticolo());
    }

    private MuiReportArticoloEntity createEntity(String codicePromozione, Long idItem, String codiceZona,
                                                 String codiceMeccanica, String codiceCondizione,
                                                 String anno, String descrizionePromozione,
                                                 String codiceArticolo, String descrizioneArticolo,
                                                 Date dataInizio, Date dataFine) {
        MuiReportArticoloId id = new MuiReportArticoloId(codicePromozione, idItem, codiceZona, codiceMeccanica,
                codiceCondizione);
        MuiReportArticoloEntity entity = new MuiReportArticoloEntity();
        entity.setId(id);
        entity.setDescrizionePromozione(descrizionePromozione);
        entity.setCodiceArticolo(codiceArticolo);
        entity.setDescrizioneArticolo(descrizioneArticolo);
        entity.setAnno(anno);
        entity.setCodiceGruppo("GR-01");
        entity.setDescrizioneGruppo("GRUPPO 01");
        entity.setCodiceCanale(1L);
        entity.setDescrizioneCanale("CANALE 01");
        entity.setCodiceStato("10");
        entity.setDescrizioneStato("STATO 10");
        entity.setCodiceCompratore("S01");
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        return entity;
    }
}