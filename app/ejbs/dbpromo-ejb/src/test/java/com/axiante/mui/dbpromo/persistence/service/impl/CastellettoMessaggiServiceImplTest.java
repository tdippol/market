package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.business.utils.DefaultMessaggiHelper;
import com.axiante.mui.dbpromo.persistence.dao.CastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgIdMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.dao.MuiCfgDefaultCastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.dto.CastellettoMessaggiDTO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CastellettoMessaggiServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private CastellettoMessaggiDAO dao;

    @Mock
    private CastellettoMessaggiEntity entity1;

    @Mock
    private CastellettoMessaggiEntity entity2;

    @Mock
    private Instance<MuiCfgDefaultCastellettoMessaggiDAO> cfgDaoInstance;

    @Mock
    private MuiCfgDefaultCastellettoMessaggiDAO cfgDao;

    @Mock
    private PromozionePianificazioneDAO pianificazioneDAO;

    @Mock
    private CfgIdMessaggiDAO idMessaggiDAO;

    @Mock
    private Instance<DefaultMessaggiHelper> defaultMessaggiHelperInstance;

    @Mock
    private DefaultMessaggiHelper defaultMessaggiHelper;

    @Mock
    private Instance<PromozioneTestataDAO> promozioneTestataDAOInstance;

    @Mock
    private PromozioneTestataDAO promozioneTestataDao;

    @Mock
    private Instance<MuiFontEntitiesService> fontEntitiesServiceInstance;

    @Mock
    private MuiFontEntitiesService fontEntitiesService;

    @InjectMocks
    @Spy
    private CastellettoMessaggiServiceImpl service;

    private List<CastellettoMessaggiEntity> entities = Arrays.asList(entity1, entity2);

    private final String TEST_DISPOSITIVO = "DISP123";
    private final Long TEST_PIANIFICAZIONE_ID = 42L;
    private final String TEST_USER = "testUser";
    private final Long TEST_MESSAGGIO_ID = 100L;
    private final Long TEST_CANALE = 1L;
    private final String TEST_MECCANICA = "MEC1";

    @Before
    public void setUp() throws Exception {
        when(dao.findByIdPianificazione(42L)).thenReturn(entities);
        when(dao.findMessaggiByIdPianificazione(42L)).thenReturn(entities);
        when(dao.findCastellettiByIdPianificazione(42L)).thenReturn(entities);
        when(dao.findByIdPianificazione(42L)).thenReturn(entities);
        when(dao.findMessaggiByIdPianificazione(42L)).thenReturn(entities);
        when(dao.findCastellettiByIdPianificazione(42L)).thenReturn(entities);
        doNothing().when(dao).remove(Arrays.asList(23L, 24L));
        when(service.getCfgDao()).thenReturn(cfgDaoInstance);
        when(cfgDaoInstance.get()).thenReturn(cfgDao);
        when(service.getDefaultMessaggiHelper()).thenReturn(defaultMessaggiHelperInstance);
        when(service.getPromozioneTestataDao()).thenReturn(promozioneTestataDAOInstance);
        when(defaultMessaggiHelperInstance.get()).thenReturn(defaultMessaggiHelper);
        when(promozioneTestataDAOInstance.get()).thenReturn(promozioneTestataDao);
        when(fontEntitiesServiceInstance.get()).thenReturn(fontEntitiesService);
        when(promozioneTestataDao.countByLogoFilenameAndIdTestata("FOO", 1L)).thenReturn(1L);
        when(promozioneTestataDao.countByLogoFilenameAndIdTestata("BAR", 1L)).thenReturn(0L);
        when(fontEntitiesService.findAll()).thenReturn(Collections.emptyList());
    }

    @Test
    public void findByIdPianificazione() {
        assertEquals(entities, service.findByIdPianificazione(42L));
        verify(dao, times(1)).findByIdPianificazione(42L);
    }

    @Test
    public void findMessaggiByIdPianificazione() {
        assertEquals(entities, service.findMessaggiByIdPianificazione(42L));
        verify(dao, times(1)).findMessaggiByIdPianificazione(42L);
    }

    @Test
    public void findCastellettiByIdPianificazione() {
        assertEquals(entities, service.findCastellettiByIdPianificazione(42L));
        verify(dao, times(1)).findCastellettiByIdPianificazione(42L);
    }

    @Test
    public void remove() {
        service.remove(Arrays.asList(23L, 24L));
        verify(dao, times(1)).remove(Arrays.asList(23L, 24L));
    }

    @Test
    public void findMessaggiByIdPianificazioneAndCodiceDispositivo() {
        when(dao.findMessaggiByIdPianificazioneAndCodiceDispositivo(
                TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO))
                .thenReturn(entities);

        List<CastellettoMessaggiEntity> result =
                service.findMessaggiByIdPianificazioneAndCodiceDispositivo(
                        TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO);

        assertEquals(entities, result);
        verify(dao)
                .findMessaggiByIdPianificazioneAndCodiceDispositivo(
                        TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO);
    }

    @Test
    public void findEnhancedMessaggiByIdPianificazioneAndCodiceDispositivo() {
        // Setup
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        CanalePromozioneEntity canaleEntity = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanicaEntity = mock(MeccanicheEntity.class);

        when(pianificazioneDAO.findById(TEST_PIANIFICAZIONE_ID)).thenReturn(pianificazione);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getCanalePromozioneEntity()).thenReturn(canaleEntity);
        when(canaleEntity.getCodiceCanale()).thenReturn(TEST_CANALE);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicaEntity);
        when(meccanicaEntity.getCodiceMeccanica()).thenReturn(TEST_MECCANICA);

        // Setup test data
        CastellettoMessaggiEntity msg1 = mock(CastellettoMessaggiEntity.class);
        CastellettoMessaggiEntity msg2 = mock(CastellettoMessaggiEntity.class);
        when(msg1.getIdMessaggio()).thenReturn(1);
        when(msg2.getIdMessaggio()).thenReturn(2);
        List<CastellettoMessaggiEntity> messaggi = Arrays.asList(msg1, msg2);

        when(dao.findMessaggiByIdPianificazioneAndCodiceDispositivo(
                TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO))
                .thenReturn(messaggi);

        // Setup IdMessaggi
        CfgIdMessaggiEntity idMsg1 = mock(CfgIdMessaggiEntity.class);
        CfgIdMessaggiEntity idMsg2 = mock(CfgIdMessaggiEntity.class);
        when(idMsg1.getIdMessaggio()).thenReturn(1);
        when(idMsg1.getDescrizione()).thenReturn("Messaggio 1");
        when(idMsg2.getIdMessaggio()).thenReturn(2);
        when(idMsg2.getDescrizione()).thenReturn("Messaggio 2");

        when(idMessaggiDAO.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO))
                .thenReturn(Arrays.asList(idMsg1, idMsg2));

        // Execute
        List<CastellettoMessaggiDTO> result =
                service.findEnhancedMessaggiByIdPianificazioneAndCodiceDispositivo(
                        TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO);

        // Verify
        assertEquals(2, result.size());
        assertEquals("Messaggio 1", result.get(0).getDescrizione());
        assertEquals("Messaggio 2", result.get(1).getDescrizione());
    }

    @Test
    public void addMessageAbove() {
        // Setup
        CastellettoMessaggiEntity existingMsg = new CastellettoMessaggiEntity();
        existingMsg.setId(TEST_MESSAGGIO_ID);
        existingMsg.setIdMessaggio(5);
        existingMsg.setSeqStampa(3);

        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        when(pianificazione.getId()).thenReturn(TEST_PIANIFICAZIONE_ID);
        existingMsg.setPianificazione(pianificazione);
        existingMsg.setCodiceCanaleDispositivo(TEST_DISPOSITIVO);
        existingMsg.setSezione(MessaggiSezioneEnum.RIGA);

        when(dao.findById(TEST_MESSAGGIO_ID)).thenReturn(existingMsg);

        List<CastellettoMessaggiEntity> higherSeqMessages = new ArrayList<>();
        CastellettoMessaggiEntity higherMsg = new CastellettoMessaggiEntity();
        higherMsg.setSeqStampa(4);
        higherSeqMessages.add(higherMsg);

        when(dao.findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan(
                TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO, 3))
                .thenReturn(higherSeqMessages);

        // Execute
        service.addMessageAbove(TEST_MESSAGGIO_ID, TEST_USER);

        // Verify
        verify(dao).findById(TEST_MESSAGGIO_ID);
        verify(dao)
                .findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan(
                        TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO, 3);

        verify(dao).persistInTransaction(argThat(list -> list.size() == 3));
    }

    @Test
    public void resetMessaggi_whenDefaultsExist() {
        // Setup
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        CfgCanaleDispositivoEntity dispositivo = mock(CfgCanaleDispositivoEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);

        when(pianificazione.getId()).thenReturn(TEST_PIANIFICAZIONE_ID);
        when(dispositivo.getCodice()).thenReturn(TEST_DISPOSITIVO);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getCanalePromozioneEntity()).thenReturn(canalePromo);
        when(canalePromo.getCodiceCanale()).thenReturn(TEST_CANALE);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(meccanica.getCodiceMeccanica()).thenReturn(TEST_MECCANICA);

        // Mock existing messages
        List<CastellettoMessaggiEntity> existingMessages = new ArrayList<>();
        CastellettoMessaggiEntity msg1 = new CastellettoMessaggiEntity();
        msg1.setId(1L);
        existingMessages.add(msg1);

        when(dao.findMessaggiByIdPianificazioneAndCodiceDispositivo(
                TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO))
                .thenReturn(existingMessages);

        // Mock default message configurations
        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultConfigs = new ArrayList<>();
        MuiCfgDefaultCastellettoMessaggiEntity config1 =
                mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        defaultConfigs.add(config1);

        when(cfgDao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO))
                .thenReturn(defaultConfigs);

        when(cfgDao.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO))
                .thenReturn(1L);

        // Mock helper to create new messages
        CastellettoMessaggiEntity newMsg = new CastellettoMessaggiEntity();
        when(defaultMessaggiHelper.mapToMessageRow(config1, pianificazione, TEST_USER))
                .thenReturn(newMsg);

        // Execute
        service.resetMessaggi(pianificazione, dispositivo, TEST_USER);

        // Verify
        verify(dao)
                .findMessaggiByIdPianificazioneAndCodiceDispositivo(
                        TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO);
        verify(dao).remove(Collections.singletonList(1L));
        verify(cfgDao)
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO);
        verify(defaultMessaggiHelper).mapToMessageRow(config1, pianificazione, TEST_USER);
        verify(dao).persistInTransaction(Collections.singletonList(newMsg));
    }

    @Test
    public void resetMessaggi_whenThrowException_thenRethrownException() {
        ex.expect(RuntimeException.class);
        ex.expectMessage("FOO EXCEPTION");
        // Setup
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        CfgCanaleDispositivoEntity dispositivo = mock(CfgCanaleDispositivoEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);

        when(pianificazione.getId()).thenReturn(TEST_PIANIFICAZIONE_ID);
        when(dispositivo.getCodice()).thenReturn(TEST_DISPOSITIVO);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getCanalePromozioneEntity()).thenReturn(canalePromo);
        when(canalePromo.getCodiceCanale()).thenReturn(TEST_CANALE);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(meccanica.getCodiceMeccanica()).thenReturn(TEST_MECCANICA);

        // Mock existing messages
        List<CastellettoMessaggiEntity> existingMessages = new ArrayList<>();
        CastellettoMessaggiEntity msg1 = new CastellettoMessaggiEntity();
        msg1.setId(1L);
        existingMessages.add(msg1);

        when(dao.findMessaggiByIdPianificazioneAndCodiceDispositivo(
                TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO))
                .thenReturn(existingMessages);

        // Mock default message configurations
        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultConfigs = new ArrayList<>();
        MuiCfgDefaultCastellettoMessaggiEntity config1 =
                mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        defaultConfigs.add(config1);

        when(cfgDao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO))
                .thenReturn(defaultConfigs);

        when(cfgDao.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO))
                .thenReturn(1L);

        // Mock helper to create new messages
        CastellettoMessaggiEntity newMsg = new CastellettoMessaggiEntity();
        when(defaultMessaggiHelper.mapToMessageRow(config1, pianificazione, TEST_USER))
                .thenReturn(newMsg);
        when(dao.persistInTransaction(Collections.singletonList(newMsg))).thenThrow(new RuntimeException("FOO EXCEPTION"));

        // Execute
        service.resetMessaggi(pianificazione, dispositivo, TEST_USER);

        // Verify
        verify(dao)
                .findMessaggiByIdPianificazioneAndCodiceDispositivo(
                        TEST_PIANIFICAZIONE_ID, TEST_DISPOSITIVO);
        verify(dao).remove(Collections.singletonList(1L));
        verify(cfgDao)
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO);
        verify(defaultMessaggiHelper).mapToMessageRow(config1, pianificazione, TEST_USER);
        verify(dao).persistInTransaction(Collections.singletonList(newMsg));
    }

    @Test
    public void canResetMessaggi_whenConfigExists() {
        // Setup
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        CfgCanaleDispositivoEntity dispositivo = mock(CfgCanaleDispositivoEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);

        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getCanalePromozioneEntity()).thenReturn(canalePromo);
        when(canalePromo.getCodiceCanale()).thenReturn(TEST_CANALE);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(meccanica.getCodiceMeccanica()).thenReturn(TEST_MECCANICA);
        when(dispositivo.getCodice()).thenReturn(TEST_DISPOSITIVO);

        when(cfgDao.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO))
                .thenReturn(2L);

        // Execute
        boolean result = service.canResetMessaggi(pianificazione, dispositivo);

        // Verify
        assertTrue(result);
        verify(cfgDao)
                .countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO);
    }

    @Test
    public void canResetMessaggi_whenNoConfigExists() {
        // Setup
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        CfgCanaleDispositivoEntity dispositivo = mock(CfgCanaleDispositivoEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);

        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getCanalePromozioneEntity()).thenReturn(canalePromo);
        when(canalePromo.getCodiceCanale()).thenReturn(TEST_CANALE);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(meccanica.getCodiceMeccanica()).thenReturn(TEST_MECCANICA);
        when(dispositivo.getCodice()).thenReturn(TEST_DISPOSITIVO);

        when(cfgDao.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO))
                .thenReturn(0L);

        // Execute
        boolean result = service.canResetMessaggi(pianificazione, dispositivo);

        // Verify
        assertFalse(result);
        verify(cfgDao)
                .countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        TEST_CANALE, TEST_MECCANICA, TEST_DISPOSITIVO);
    }

    @Test
    public void fillAuditLogFields_existing() {
        CastellettoMessaggiEntity entity = new CastellettoMessaggiEntity();
        entity.setId(1L);

        CastellettoMessaggiEntity result = service.fillAuditLogFields(entity, TEST_USER);

        assertEquals(TEST_USER, result.getCodiceUtenteAggiornamento());
        assertNotNull(result.getDataAggiornamento());
        assertNull(result.getCodiceUtenteInserimento());
        assertNull(result.getDataInserimento());
    }

    @Test
    public void fillAuditLogFields_new() {
        CastellettoMessaggiEntity entity = new CastellettoMessaggiEntity();

        CastellettoMessaggiEntity result = service.fillAuditLogFields(entity, TEST_USER);

        assertEquals(TEST_USER, result.getCodiceUtenteInserimento());
        assertNotNull(result.getDataInserimento());
        assertNull(result.getCodiceUtenteAggiornamento());
        assertNull(result.getDataAggiornamento());
    }

    @Test
    public void testAddMessageAbove_whenEntityNotFound() {
        when(dao.findById(TEST_MESSAGGIO_ID)).thenReturn(null);
        service.addMessageAbove(TEST_MESSAGGIO_ID, TEST_USER);
        verify(dao, times(1)).findById(TEST_MESSAGGIO_ID);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void fillAuditLogFields_whenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.fillAuditLogFields(null, TEST_USER);
    }

    @Test
    public void fillAuditLogFields_whenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        CastellettoMessaggiEntity entity = mock(CastellettoMessaggiEntity.class);
        service.fillAuditLogFields(entity, null);
        verifyZeroInteractions(entity);
    }

    @Test
    public void resetMessaggi_whenCannotReset() {
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getCodiceCanale()).thenReturn(1L);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(testata.getCanalePromozioneEntity()).thenReturn(canale);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        when(meccanica.getCodiceMeccanica()).thenReturn("M1");
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        CfgCanaleDispositivoEntity dispositivo = mock(CfgCanaleDispositivoEntity.class);
        when(dispositivo.getCodice()).thenReturn("D1");
        when(cfgDao.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M1", "D1"))
                .thenReturn(0L);
        service.resetMessaggi(pianificazione, dispositivo, "USER");
        verify(cfgDao, times(1)).countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M1", "D1");
        verifyNoMoreInteractions(cfgDao);
        verifyZeroInteractions(dao, defaultMessaggiHelper);
    }

    @Test
    public void isLogoUsedInPromo_whenLogoUsed_shouldReturnTrue() {
        assertTrue(service.isLogoUsedInPromo("FOO", 1L));
        verify(promozioneTestataDao, times(1)).countByLogoFilenameAndIdTestata("FOO", 1L);
    }

    @Test
    public void isLogoUsedInPromo_whenLogoNotUsed_shouldReturnFalse() {
        assertFalse(service.isLogoUsedInPromo("BAR", 1L));
        verify(promozioneTestataDao, times(1)).countByLogoFilenameAndIdTestata("BAR", 1L);
    }
}
