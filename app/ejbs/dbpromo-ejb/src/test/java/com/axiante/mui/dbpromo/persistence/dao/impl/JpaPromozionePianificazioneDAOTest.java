package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class JpaPromozionePianificazioneDAOTest extends AbstractDaoTest {

    @Inject
    private PromozionePianificazioneDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaPromozionePianificazioneDAOImpl.class).activate(RequestScoped.class)
            .inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PromozionePianificazioneEntity promozionePianificazionePadreSenzaFigli;
    private PromozionePianificazioneEntity promozionePianificazioneMaster;
    private PromozionePianificazioneEntity promozionePianificazioneDetail;

    @Before
    public void setUp() throws Exception {
        CanalePromozioneEntity canalePromozione = createCanalePromozione(130L, 10L, 100L, "descrizione test",
                createGruppoPromozioneEntity("GR1", "gruppo 1"));

        promozionePianificazionePadreSenzaFigli = createPromozionePianificazione(336L, canalePromozione, null, "ELENCO");
        promozionePianificazioneMaster = createPromozionePianificazione(333L, canalePromozione, null, "ELENCO");
        promozionePianificazioneDetail = createPromozionePianificazione(335L, canalePromozione,
                promozionePianificazioneMaster, ElementType.GRM.getDescription());

        openTransaction();
        dao.persist(promozionePianificazioneMaster);
        dao.persist(promozionePianificazioneDetail);
        commitTransaction();
    }

    @Test
    public void shouldFindAllMasterRowsByPromozioneTestata() {
        List<PromozionePianificazioneEntity> promozionePianificazioni = dao
                .findAllMasterRowsByPromozioneTestata(promozionePianificazioneMaster.getPromozioneTestataEntity());
        assertNotNull(promozionePianificazioni);
        assertFalse(promozionePianificazioni.isEmpty());
        assertEquals(promozionePianificazioneMaster.getId(), promozionePianificazioni.get(0).getId());
        assertEquals(promozionePianificazioneMaster.getMeccanicaEntity().getId(),
                promozionePianificazioni.get(0).getMeccanicaEntity().getId());
        assertEquals(promozionePianificazioneMaster.getPromozioneTestataEntity().getId(),
                promozionePianificazioni.get(0).getPromozioneTestataEntity().getId());
    }

    @Test
    public void shouldNotFindAllMasterRowsByPromozioneTestata() {
        List<PromozionePianificazioneEntity> promozionePianificazioni = dao.findAllMasterRowsByPromozioneTestata(null);
        assertNotNull(promozionePianificazioni);
        assertTrue(promozionePianificazioni.isEmpty());
    }

    @Test
    public void shouldFindAllDetailsByIdPromozione() {
        List<PromozionePianificazioneEntity> promozionePianificazioni = dao
                .findAllDetailsByIdPromozione(promozionePianificazioneMaster.getPromozioneTestataEntity().getId());
        assertNotNull(promozionePianificazioni);
        assertFalse(promozionePianificazioni.isEmpty());
        assertEquals(promozionePianificazioneMaster.getId(), promozionePianificazioni.get(0).getId());
        assertEquals(promozionePianificazioneDetail.getMeccanicaEntity().getId(),
                promozionePianificazioni.get(0).getMeccanicaEntity().getId());
        assertEquals(promozionePianificazioneDetail.getPromozioneTestataEntity().getId(),
                promozionePianificazioni.get(0).getPromozioneTestataEntity().getId());
    }

    @Test
    public void shouldNotFindAllDetailsByIdPromozione() {
        List<PromozionePianificazioneEntity> promozionePianificazioni = dao.findAllDetailsByIdPromozione(null);
        assertNotNull(promozionePianificazioni);
        assertTrue(promozionePianificazioni.isEmpty());
    }


    @Test
    public void shouldFindAllParentRowsByPromozioneTestata() {
        List<PromozionePianificazioneEntity> promozionePianificazioni = dao
                .findAllParentRowsByPromozioneTestata(promozionePianificazioneMaster.getPromozioneTestataEntity());
        assertNotNull(promozionePianificazioni);
        assertFalse(promozionePianificazioni.isEmpty());
        assertEquals(promozionePianificazioneMaster.getId(), promozionePianificazioni.get(0).getId());
        assertEquals(promozionePianificazioneDetail.getMeccanicaEntity().getId(),
                promozionePianificazioni.get(0).getMeccanicaEntity().getId());
        assertEquals(promozionePianificazioneDetail.getPromozioneTestataEntity().getId(),
                promozionePianificazioni.get(0).getPromozioneTestataEntity().getId());
    }


    @Test
    public void shouldFindAllSetByPromozione() {
        List<PromozionePianificazioneEntity> promozionePianificazioni = dao
                .findAllSetByPromozione(promozionePianificazioneMaster.getPromozioneTestataEntity());
        assertNotNull(promozionePianificazioni);
        assertFalse(promozionePianificazioni.isEmpty());
        assertEquals(promozionePianificazioneMaster.getId(), promozionePianificazioni.get(0).getId());
        assertEquals(promozionePianificazioneDetail.getMeccanicaEntity().getId(),
                promozionePianificazioni.get(0).getMeccanicaEntity().getId());
        assertEquals(promozionePianificazioneDetail.getPromozioneTestataEntity().getId(),
                promozionePianificazioni.get(0).getPromozioneTestataEntity().getId());
    }

    @Test
    public void getMappedFields_shouldReturnMapColumnNameAndField() {
        final Map<String, Field> map = dao.getMappedFields();
        assertNotNull(map);
        assertFalse(map.isEmpty());
        final List<String> keys = map.keySet().stream().sorted().collect(Collectors.toList());

        final List<String> expectedFields = Arrays.asList("BRUCIABILITA",
                "BUDGET_PEZZI", "BUONO_SCONTO_PROGRESSIVO", "BUONO_SCONTO_RADICE", "CANALE_DISPOSITIVO", "CARTA_ORO",
                "CARTA_ORO_PRIVILEGIATE", "CARTA_VERDE", "CARTA_VERDE_PRIVILEGIATE", "CLASSE", "CLASSE_DEFAULT", "CLUSTER_CLIENTE",
                "CODICE_ELEMENTO", "CODICE_GRUPPO", "CODICE_ON_LINE", "CODICE_UTENTE_AGGIORNAMENTO", "CODICE_UTENTE_INSERIMENTO",
                "COD_INIZIATIVA",
                "CONV_BOLLINI", "CUMULABILITA", "DATA_AGGIORNAMENTO", "DATA_FINE", "DATA_FINE_BARCODE", "DATA_INIZIO", "DATA_INSERIMENTO",
                "DESCRIZIONE_SCONTO", "ELEMENTO", "ELENCO_CLUSTER", "ESCLUDI_CHECK_SOVR", "ESCLUSIONE", "FASCIA_ORARIA", "FORMA_PAGAMENTO", "GENERA_BUONO_WEB",
                "GIORNO_SETTIMANA", "ID_PROMO_EXT", "INCLUDI_ATLANTIC", "INCLUDI_ESSELUNGA", "INCLUDI_ESSERBELLA", "INCLUDI_LAESSE",
                "LETTERA_CODICE_ECOMMERCE", "LINK", "MAGGIOR_VANTAGGIO", "MAX_GIORNO", "MAX_PERIODO", "MAX_SCONTRINO",
                "MECCANICA_REDENZIONE", "MOLTEPLICITA", "MULTITRANSAZIONE", "NOTE", "NOTE_TIMING", "NUMERO_STAMPE", "NUM_RAGGRUPPAMENTO",
                "NUM_SET", "NUM_UTILIZZI", "ORA_FINE", "ORA_INIZIO", "PAGHI", "PORTI_VIA", "PREZZO", "PREZZO_BUDGET", "SCALA",
                "SCONTO_CASSA", "SCONTO_IN_APP", "SCONTO_RIFATTURABILE", "SOTTOCLASSE", "STAMPA_ETICHETTA", "STAMPA_OFFERTA", "TAGLIO_PUNTI",
                "TARGET", "TIPO_ELEMENTO", "TIPO_SCONTO", "TIPO_SMALTIMENTO", "TIPO_SOGLIA", "TIPO_TAGLIO", "TIPO_TETTO",
                "UUID", "VALIDITA_PERIODO", "VALORE", "VALORE_2", "VALORE_SCALA", "VALORE_SOGLIA", "VALORE_TAGLIO", "VALORE_TETTO", "VISUALIZZA_MECCANICA");
        assertEquals(expectedFields.size(), keys.size());
        assertEquals(expectedFields, keys);
    }


    @Test
    public void testFindChildByParentReturnsChildWhenItExists() {
        PromozionePianificazioneEntity pianificazioneChild = dao.findFirstChildByParent(promozionePianificazioneMaster);
        assertNotNull(pianificazioneChild);
    }

    @Test
    public void testFindChildByParentReturnsNullWhenItDoesntExists() {
        PromozionePianificazioneEntity pianificazioneChild = dao.findFirstChildByParent(promozionePianificazionePadreSenzaFigli);
        assertNull(pianificazioneChild);
    }

    @Test
    public void findAllBuoni_shouldReturnOnlyEntitiesWithBuonoScontoRadice() {
        CanalePromozioneEntity canalePromozione = createCanalePromozione(130L, 10L, 100L,
                "descrizione test", createGruppoPromozioneEntity("GR1", "gruppo 1"));
        PromozioneTestataEntity testata = createPromozioneTestata(42L, DateTimeUtils.I_SEMESTRE, "2022",
                new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime(),
                new GregorianCalendar(2022, Calendar.AUGUST, 30).getTime(),
                "COD130", canalePromozione);
        testata.setPromozioneStatoEntities(new HashSet<>());
        StatoPromozioneEntity statoPromozione = createStatoPromozioneEntity(10L, "10", "Stato 10");
        PromozioneStatoEntity promozioneStato = createPromozioneStatoEntity(10L,
                new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime(), null,
                statoPromozione);
        testata.addPromozioneStatoEntity(promozioneStato);
        final PromozionePianificazioneEntity pianificazione = new PromozionePianificazioneEntity();
        MeccanicheEntity meccanica = createMeccanica("Meccanica 001", "M001");
        CfgPianificazTipoRigaEntity tipoRiga = createTipoRiga(42L, "ELEMENTO", "E");
        pianificazione.setMeccanicaEntity(meccanica);
        pianificazione.setBuonoScontoRadice(23);
        pianificazione.setTipoRiga(tipoRiga);
        pianificazione.setDataFine(getTomorrowDate());
        testata.addPromozionePianificazioneEntity(pianificazione);
        openTransaction();
        em.persist(statoPromozione);
        em.persist(promozioneStato);
        em.persist(tipoRiga);
        em.persist(testata);
        commitTransaction();
        final List<PromozionePianificazioneEntity> entities = dao.findAllBuoni();
        assertEquals(1, entities.size());
        assertEquals(23, (int) entities.get(0).getBuonoScontoRadice());
    }

    @Test
    public void findAllBuoniNotUsed_shouldReturnOnlyEntitiesWithBuonoScontoRadiceNotInList() {
        CanalePromozioneEntity canalePromozione = createCanalePromozione(130L, 10L, 100L,
                "descrizione test", createGruppoPromozioneEntity("GR1", "gruppo 1"));
        PromozioneTestataEntity testata = createPromozioneTestata(42L, DateTimeUtils.I_SEMESTRE, "2022",
                new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime(),
                new GregorianCalendar(2022, Calendar.AUGUST, 30).getTime(),
                "COD130", canalePromozione);
        testata.setPromozioneStatoEntities(new HashSet<>());
        StatoPromozioneEntity statoPromozione = createStatoPromozioneEntity(10L, "10", "Stato 10");
        PromozioneStatoEntity promozioneStato = createPromozioneStatoEntity(10L,
                new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime(), null,
                statoPromozione);
        testata.addPromozioneStatoEntity(promozioneStato);
        final PromozionePianificazioneEntity pianificazioneUsed = new PromozionePianificazioneEntity();
        MeccanicheEntity meccanica = createMeccanica("Meccanica 001", "M001");
        CfgPianificazTipoRigaEntity tipoRiga = createTipoRiga(42L, "ELEMENTO", "E");
        pianificazioneUsed.setId(666L);
        pianificazioneUsed.setMeccanicaEntity(meccanica);
        pianificazioneUsed.setBuonoScontoRadice(23);
        pianificazioneUsed.setTipoRiga(tipoRiga);
        pianificazioneUsed.setDataFine(getTomorrowDate());
        testata.addPromozionePianificazioneEntity(pianificazioneUsed);
        final PromozionePianificazioneEntity pianificazione = new PromozionePianificazioneEntity();
        pianificazione.setMeccanicaEntity(meccanica);
        pianificazione.setBuonoScontoRadice(24);
        pianificazione.setTipoRiga(tipoRiga);
        pianificazione.setDataFine(getTomorrowDate());
        testata.addPromozionePianificazioneEntity(pianificazione);
        openTransaction();
        em.persist(statoPromozione);
        em.persist(promozioneStato);
        em.persist(tipoRiga);
        em.persist(testata);
        commitTransaction();
        final List<PromozionePianificazioneEntity> entities = dao.findAllBuoniNotUsed(Collections.singletonList(666L));
        assertEquals(1, entities.size());
        assertEquals(24, (int) entities.get(0).getBuonoScontoRadice());
    }

    @Test
    public void findAllBuoniNotUsed_givenEmptyList_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllBuoniNotUsed(Collections.emptyList());
    }

    @Test
    public void findAllBuoniNotUsed_givenNullList_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllBuoniNotUsed(null);
    }

    @Test
    public void findOverlappingByAnnoCanaleMeccanica_givenNullPianificazione_shouldThrowException() {
        MeccanicheEntity meccanica = createMeccanica(1L, "FOO", "M018");
        CanalePromozioneEntity canale = createCanalePromozione(1L, 10L, 100L,
                "CANALE 01", createGruppoPromozioneEntity("GR1", "GRUPPO 1"));
        ex.expect(NullPointerException.class);
        dao.findOverlappingByAnnoCanaleMeccanica(null, "2024", canale, meccanica);
    }

    @Test
    public void findOverlappingByAnnoCanaleMeccanica_givenNullAnno_shouldThrowException() {
        MeccanicheEntity meccanica = createMeccanica(1L, "FOO", "M018");
        CanalePromozioneEntity canale = createCanalePromozione(1L, 10L, 100L,
                "CANALE 01", createGruppoPromozioneEntity("GR1", "GRUPPO 1"));
        PromozioneTestataEntity promozione = createPromozioneTestata(42L, DateTimeUtils.II_SEMESTRE, "2022",
                new GregorianCalendar(2024, Calendar.AUGUST, 1).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 30).getTime(),
                "COD130", canale);
        PromozionePianificazioneEntity pianificazione = createPromozionePianificazione(1L, promozione, meccanica,
                new GregorianCalendar(2024, Calendar.JULY, 19).getTime(),
                new GregorianCalendar(2024, Calendar.JULY, 23).getTime());
        ex.expect(NullPointerException.class);
        dao.findOverlappingByAnnoCanaleMeccanica(pianificazione, null, canale, meccanica);
    }

    @Test
    public void findOverlappingByAnnoCanaleMeccanica_givenNullCanale_shouldThrowException() {
        MeccanicheEntity meccanica = createMeccanica(1L, "FOO", "M018");
        CanalePromozioneEntity canale = createCanalePromozione(1L, 10L, 100L,
                "CANALE 01", createGruppoPromozioneEntity("GR1", "GRUPPO 1"));
        PromozioneTestataEntity promozione = createPromozioneTestata(42L, DateTimeUtils.II_SEMESTRE, "2022",
                new GregorianCalendar(2024, Calendar.AUGUST, 1).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 30).getTime(),
                "COD130", canale);
        PromozionePianificazioneEntity pianificazione = createPromozionePianificazione(1L, promozione, meccanica,
                new GregorianCalendar(2024, Calendar.JULY, 19).getTime(),
                new GregorianCalendar(2024, Calendar.JULY, 23).getTime());
        ex.expect(NullPointerException.class);
        dao.findOverlappingByAnnoCanaleMeccanica(pianificazione, "2024", null, meccanica);
    }

    @Test
    public void findOverlappingByAnnoCanaleMeccanica_givenNullMeccanica_shouldThrowException() {
        MeccanicheEntity meccanica = createMeccanica(1L, "FOO", "M018");
        CanalePromozioneEntity canale = createCanalePromozione(1L, 10L, 100L,
                "CANALE 01", createGruppoPromozioneEntity("GR1", "GRUPPO 1"));
        PromozioneTestataEntity promozione = createPromozioneTestata(42L, DateTimeUtils.II_SEMESTRE, "2022",
                new GregorianCalendar(2024, Calendar.AUGUST, 1).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 30).getTime(),
                "COD130", canale);
        PromozionePianificazioneEntity pianificazione = createPromozionePianificazione(1L, promozione, meccanica,
                new GregorianCalendar(2024, Calendar.JULY, 19).getTime(),
                new GregorianCalendar(2024, Calendar.JULY, 23).getTime());
        ex.expect(NullPointerException.class);
        dao.findOverlappingByAnnoCanaleMeccanica(pianificazione, "2024", canale, null);
    }

    @Test
    public void findOverlappingByAnnoCanaleMeccanica() {
        CanalePromozioneEntity canale = createCanalePromozione(1L, 10L, 100L,
                "CANALE 01", createGruppoPromozioneEntity("GR1", "GRUPPO 1"));
        PromozioneTestataEntity promozione1 = createPromozioneTestata(1L, DateTimeUtils.II_SEMESTRE, "2024",
                new GregorianCalendar(2024, Calendar.JULY, 1).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 1).getTime(),
                "PROMO_001", canale);
        PromozioneTestataEntity promozione2 = createPromozioneTestata(2L, DateTimeUtils.II_SEMESTRE, "2024",
                new GregorianCalendar(2024, Calendar.AUGUST, 2).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 31).getTime(),
                "PROMO_002", canale);
        MeccanicheEntity meccanica1 = createMeccanica(1L, "FOO", "M018");
        MeccanicheEntity meccanica2 = createMeccanica(2L, "BAZ", "M666");
        PromozionePianificazioneEntity pianificazione1 = createPromozionePianificazione(1L, promozione1, meccanica1,
                new GregorianCalendar(2024, Calendar.JULY, 19).getTime(),
                new GregorianCalendar(2024, Calendar.JULY, 23).getTime());
        PromozionePianificazioneEntity pianificazione2 = createPromozionePianificazione(2L, promozione1, meccanica1,
                new GregorianCalendar(2024, Calendar.JULY, 22).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 1).getTime());
        PromozionePianificazioneEntity pianificazione3 = createPromozionePianificazione(3L, promozione1, meccanica1,
                new GregorianCalendar(2024, Calendar.JULY, 24).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 1).getTime());
        PromozionePianificazioneEntity pianificazione4 = createPromozionePianificazione(4L, promozione2, meccanica1,
                new GregorianCalendar(2024, Calendar.AUGUST, 5).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 15).getTime());
        PromozionePianificazioneEntity pianificazione5 = createPromozionePianificazione(5L, promozione2, meccanica2,
                new GregorianCalendar(2024, Calendar.AUGUST, 3).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 20).getTime());
        PromozionePianificazioneEntity pianificazione6 = createPromozionePianificazione(6L, promozione2, meccanica2,
                new GregorianCalendar(2024, Calendar.AUGUST, 25).getTime(),
                new GregorianCalendar(2024, Calendar.AUGUST, 30).getTime());
        openTransaction();
        dao.persist(pianificazione1);
        dao.persist(pianificazione2);
        dao.persist(pianificazione3);
        dao.persist(pianificazione4);
        dao.persist(pianificazione5);
        dao.persist(pianificazione6);
        commitTransaction();
        List<PromozionePianificazioneEntity> overlapping = dao
                .findOverlappingByAnnoCanaleMeccanica(pianificazione5, "2024", canale, meccanica1);
        assertEquals(1, overlapping.size());
        overlapping = dao.findOverlappingByAnnoCanaleMeccanica(pianificazione6, "2024", canale, meccanica1);
        assertTrue(overlapping.isEmpty());
    }

    private PromozionePianificazioneEntity createPromozionePianificazione(Long id, PromozioneTestataEntity promozione,
                                                                          MeccanicheEntity meccanica,
                                                                          Date dataInizio, Date dataFine) {
        PromozionePianificazioneEntity pianificazione = new PromozionePianificazioneEntity();
        pianificazione.setId(id);
        pianificazione.setPromozioneTestataEntity(promozione);
        pianificazione.setMeccanicaEntity(meccanica);
        pianificazione.setDataInizio(dataInizio);
        pianificazione.setDataFine(dataFine);
        pianificazione.setTipoRiga(createTipoRiga(1L, "ELEMENTO", "E"));
        return pianificazione;
    }

    private PromozionePianificazioneEntity createPromozionePianificazione(Long id,
                                                                          CanalePromozioneEntity canalePromozione, PromozionePianificazioneEntity master, String codiceElemento) {
        PromozionePianificazioneEntity promozionePianificazione = new PromozionePianificazioneEntity();
        promozionePianificazione.setId(id);
        promozionePianificazione.setMeccanicaEntity(createMeccanica("meccanica", "M33"));
        promozionePianificazione.setPromozioneTestataEntity(
                createPromozioneTestata(130L, DateTimeUtils.I_SEMESTRE, "2020",
                        new GregorianCalendar(2020, Calendar.FEBRUARY, 1).getTime(),
                        new GregorianCalendar(2020, Calendar.JULY, 30).getTime(),
                        "COD130", canalePromozione));
        promozionePianificazione.setParent(master);
        promozionePianificazione.setCodiceElemento(codiceElemento);
        promozionePianificazione.setTipoElemento("ARTICOLO");
        promozionePianificazione.setTipoRiga(createTipoRiga(1L, "SET", "S"));

        return promozionePianificazione;
    }

    private CfgPianificazTipoRigaEntity createTipoRiga(long id, String descrizione, String codice) {
        CfgPianificazTipoRigaEntity e = new CfgPianificazTipoRigaEntity();
        e.setId(id);
        e.setDescrizione(descrizione);
        e.setCodiceTipo(codice);
        return e;
    }

    private MeccanicheEntity createMeccanica(String descrizione, String codiceMeccanica) {
        return createMeccanica(130L, descrizione, codiceMeccanica);
    }

    private MeccanicheEntity createMeccanica(Long id, String descrizione, String codiceMeccanica) {
        MeccanicheEntity meccaniche = new MeccanicheEntity();
        meccaniche.setId(id);
        meccaniche.setDescrizione(descrizione);
        meccaniche.setCodiceMeccanica(codiceMeccanica);
        return meccaniche;
    }

    private PromozioneTestataEntity createPromozioneTestata(Long id, String semestre, String year, Date start, Date end,
                                                            String promoCode, CanalePromozioneEntity canalePromozione) {
        final StatoPromozioneEntity statoPromozione = createStatoPromozioneEntity(1L,
                PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey(),
                PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getDescription());
        PromozioneStatoEntity promozioneStato = createPromozioneStatoEntity(id, start, null, statoPromozione);
        PromozioneTestataEntity promozioneTestata = new PromozioneTestataEntity();
        promozioneTestata.setId(id);
        promozioneTestata.setSemestre(semestre);
        promozioneTestata.setAnno(year);
        promozioneTestata.setDataInizio(start);
        promozioneTestata.setDataFine(end);
        promozioneTestata.setCodicePromozione(promoCode);
        promozioneTestata.setMuiCanalePromozione(canalePromozione);
        promozioneTestata.setPromozioneStatoEntities(new HashSet<>());
        promozioneTestata.addPromozioneStatoEntity(promozioneStato);
        return promozioneTestata;
    }

    private CanalePromozioneEntity createCanalePromozione(Long codiceCanale, Long codeRangeMin, Long codeRangeMax,
                                                          String descrizione, GruppoPromozioneEntity gruppoPromozione) {
        CanalePromozioneEntity canalePromozione = new CanalePromozioneEntity();
        canalePromozione.setId(130L);
        canalePromozione.setCodiceCanale(codiceCanale);
        canalePromozione.setCodeRangeMin(codeRangeMin);
        canalePromozione.setCodeRangeMax(codeRangeMax);
        canalePromozione.setDescrizione(descrizione);
        canalePromozione.setGruppoPromozioneEntity(gruppoPromozione);
        return canalePromozione;
    }

    private GruppoPromozioneEntity createGruppoPromozioneEntity(String codiceGruppo, String descrizione) {
        GruppoPromozioneEntity gruppoPromozione = new GruppoPromozioneEntity();
        gruppoPromozione.setId(130L);
        gruppoPromozione.setCodiceGruppo(codiceGruppo);
        gruppoPromozione.setDescrizione(descrizione);
        return gruppoPromozione;
    }

    private PromozioneStatoEntity createPromozioneStatoEntity(Long id, Date dataInizio, Date dataFine,
                                                              StatoPromozioneEntity statoPromozione) {
        final PromozioneStatoEntity entity = new PromozioneStatoEntity();
        entity.setId(id);
        entity.setDataInizioStato(dataInizio);
        entity.setDataFineStato(dataFine);
        entity.setStatoPromozioneEntity(statoPromozione);
        return entity;
    }

    private StatoPromozioneEntity createStatoPromozioneEntity(Long id, String codice, String descrizione) {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setId(id);
        entity.setCodiceStato(codice);
        entity.setDescrizione(descrizione);
        return entity;
    }

    private Date getTomorrowDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
}
