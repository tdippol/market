package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultMessaggiHelperTest {

    @Mock
    private CastellettoMessaggiService castellettoMessaggiService;

    @Mock
    private Instance<CfgConfHeaderService> cfgConfHeaderServiceInstance;

    @Mock
    private CfgConfHeaderService cfgConfHeaderService;

    @Mock
    private Instance<MuiCfgDefaultCastellettoMessaggiService>
            muiCfgDefaultCastellettoMessaggiServiceInstance;

    @Mock
    private MuiCfgDefaultCastellettoMessaggiService muiCfgDefaultCastellettoMessaggiService;

    @InjectMocks
    @Spy
    private DefaultMessaggiHelper defaultMessaggiHelper;

    @Before
    public void setUp() {
        when(defaultMessaggiHelper.getMuiCfgDefaultCastellettoMessaggiService())
                .thenReturn(muiCfgDefaultCastellettoMessaggiServiceInstance);

        when(muiCfgDefaultCastellettoMessaggiServiceInstance.get())
                .thenReturn(muiCfgDefaultCastellettoMessaggiService);

        when(defaultMessaggiHelper.getCfgConfHeaderService()).thenReturn(cfgConfHeaderServiceInstance);
        when(cfgConfHeaderServiceInstance.get()).thenReturn(cfgConfHeaderService);
    }

    @Test
    public void testAddDefaultMessageRows_noPianificazioni() {
        // Setup
        List<PromozionePianificazioneEntity> pianificazioni = Collections.emptyList();
        String utente = "testUser";

        // Execute
        defaultMessaggiHelper.addDefaultMessageRows(pianificazioni, utente);

        // Verify no interactions with services
        verify(castellettoMessaggiService, never()).persist(anyList(), anyInt());
    }

    @Test
    public void testAddDefaultMessageRows_withPianificazioniButNoEligibleOnes() {
        // Setup
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(false, false, false);
        List<PromozionePianificazioneEntity> pianificazioni = Collections.singletonList(pianificazione);
        String utente = "testUser";
        // Execute
        defaultMessaggiHelper.addDefaultMessageRows(pianificazioni, utente);

        // Verify no messages were persisted
        verify(castellettoMessaggiService, never()).persist(anyList(), anyInt());
    }

    @Test
    public void testAddDefaultMessageRows_withEligiblePianificazioni() {
        // Setup
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(true, true, true);
        List<PromozionePianificazioneEntity> pianificazioni = Collections.singletonList(pianificazione);
        String utente = "testUser";

        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultMessages = createDefaultMessages();

        doReturn(defaultMessages)
                .when(defaultMessaggiHelper)
                .getDefaultMessages(any(PromozionePianificazioneEntity.class));

        // Execute
        defaultMessaggiHelper.addDefaultMessageRows(pianificazioni, utente);

        // Verify messages were persisted
        //noinspection unchecked
        ArgumentCaptor<List<CastellettoMessaggiEntity>> captor = ArgumentCaptor.forClass(List.class);
        verify(castellettoMessaggiService).persist(captor.capture(), eq(defaultMessages.size()));

        List<CastellettoMessaggiEntity> persistedMessages = captor.getValue();
        assertEquals(defaultMessages.size(), persistedMessages.size());
    }

    @Test
    public void testAddDefaultMessageRows_forSinglePianificazione() {
        // Setup
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(true, true, true);
        String utente = "testUser";

        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultMessages = createDefaultMessages();
        doReturn(defaultMessages)
                .when(defaultMessaggiHelper)
                .getDefaultMessages(any(PromozionePianificazioneEntity.class));

        // Execute
        defaultMessaggiHelper.addDefaultMessageRows(pianificazione, utente);

        // Verify messages were persisted
        //noinspection unchecked
        ArgumentCaptor<List<CastellettoMessaggiEntity>> captor = ArgumentCaptor.forClass(List.class);
        verify(castellettoMessaggiService).persist(captor.capture(), eq(defaultMessages.size()));

        List<CastellettoMessaggiEntity> persistedMessages = captor.getValue();
        assertEquals(defaultMessages.size(), persistedMessages.size());
        assertEquals(pianificazione, persistedMessages.get(0).getPianificazione());
    }

    @Test
    public void testAddDefaultMessageRows_withParentHierarchy() {
        // Setup
        PromozionePianificazioneEntity rootPianificazione = createMockPianificazione(true, true, true);
        PromozionePianificazioneEntity childPianificazione = createMockPianificazione(true, true, true);
        when(childPianificazione.getParent()).thenReturn(rootPianificazione);

        String utente = "testUser";

        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultMessages = createDefaultMessages();
        doReturn(defaultMessages)
                .when(defaultMessaggiHelper)
                .getDefaultMessages(any(PromozionePianificazioneEntity.class));

        // Execute
        defaultMessaggiHelper.addDefaultMessageRows(childPianificazione, utente);

        // Verify messages were persisted for the root pianificazione
        //noinspection unchecked
        ArgumentCaptor<List<CastellettoMessaggiEntity>> captor = ArgumentCaptor.forClass(List.class);
        verify(castellettoMessaggiService).persist(captor.capture(), eq(defaultMessages.size()));

        List<CastellettoMessaggiEntity> persistedMessages = captor.getValue();
        assertEquals(rootPianificazione, persistedMessages.get(0).getPianificazione());
    }

    @Test
    public void testAddDefaultMessageRows_withExistingMessages() {
        // Setup
        PromozionePianificazioneEntity pianificazione =
                createMockPianificazione(true, true, false); // false = has previous messages
        String utente = "testUser";

        // Execute
        defaultMessaggiHelper.addDefaultMessageRows(pianificazione, utente);

        // Verify no messages were persisted since there are already messages
        verify(castellettoMessaggiService, never()).persist(anyList(), anyInt());
    }

    @Test
    public void addDefaultMessageRows_whenCanaleAndMeccanicaNotEnabled() {
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(false, false, true);
        defaultMessaggiHelper.addDefaultMessageRows(pianificazione, "testUser");
        verify(castellettoMessaggiService, never()).persist(anyList(), anyInt());
    }

    @Test
    public void addDefaultMessageRows_whenCanaleEnabledAndMeccanicaNotEnabled() {
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(true, false, true);
        defaultMessaggiHelper.addDefaultMessageRows(pianificazione, "testUser");
        verify(castellettoMessaggiService, never()).persist(anyList(), anyInt());
    }

    @Test
    public void testGetDefaultMessages_withCanaleDispositivo() {
        // Setup
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(true, true, true);
        when(pianificazione.getCanaleDispositivo()).thenReturn("DISP-001");

        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultMessages = createDefaultMessages();
        // Need to make sure the Instance.get() returns our mock service
        when(defaultMessaggiHelper.getMuiCfgDefaultCastellettoMessaggiService())
                .thenReturn(muiCfgDefaultCastellettoMessaggiServiceInstance);
        when(muiCfgDefaultCastellettoMessaggiServiceInstance.get())
                .thenReturn(muiCfgDefaultCastellettoMessaggiService);
        when(muiCfgDefaultCastellettoMessaggiService
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        anyLong(), anyString(), anyString()))
                .thenReturn(defaultMessages);

        // Execute
        List<MuiCfgDefaultCastellettoMessaggiEntity> result =
                defaultMessaggiHelper.getDefaultMessages(pianificazione);

        // Verify
        assertEquals(defaultMessages, result);
        verify(muiCfgDefaultCastellettoMessaggiService)
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        anyLong(), anyString(), eq("DISP-001"));
        verify(muiCfgDefaultCastellettoMessaggiService, never())
                .findByCodiceCanaleAndCodiceMeccanica(anyLong(), anyString());
    }

    @Test
    public void testGetDefaultMessages_withoutCanaleDispositivo() {
        // Setup
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(true, true, true);
        when(pianificazione.getCanaleDispositivo()).thenReturn(null);

        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultMessages = createDefaultMessages();
        // Need to mock the service instance returned by .get() directly
        when(defaultMessaggiHelper.getMuiCfgDefaultCastellettoMessaggiService())
                .thenReturn(muiCfgDefaultCastellettoMessaggiServiceInstance);

        when(muiCfgDefaultCastellettoMessaggiServiceInstance.get())
                .thenReturn(muiCfgDefaultCastellettoMessaggiService);
        when(muiCfgDefaultCastellettoMessaggiService.findByCodiceCanaleAndCodiceMeccanica(
                anyLong(), anyString()))
                .thenReturn(defaultMessages);

        // Execute
        List<MuiCfgDefaultCastellettoMessaggiEntity> result =
                defaultMessaggiHelper.getDefaultMessages(pianificazione);

        // Verify
        assertEquals(defaultMessages, result);
        verify(muiCfgDefaultCastellettoMessaggiService, never())
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        anyLong(), anyString(), anyString());
        verify(muiCfgDefaultCastellettoMessaggiService)
                .findByCodiceCanaleAndCodiceMeccanica(anyLong(), anyString());
    }

    @Test
    public void testMapToMessageRow() {
        // Setup
        MuiCfgDefaultCastellettoMessaggiEntity defaultMessage =
                new MuiCfgDefaultCastellettoMessaggiEntity();
        defaultMessage.setSeqStampa((short) 1);
        defaultMessage.setSezione(MessaggiSezioneEnum.TITOLO);
        defaultMessage.setTesto("Test message");
        defaultMessage.setTaglioCarta(true);
        defaultMessage.setBarcode(false);
        defaultMessage.setFont(MessaggiFontEnum.NORMALE);
        defaultMessage.setAllineamento(MessaggiAllineamentoEnum.SINISTRA);
        defaultMessage.setBold(true);
        defaultMessage.setLogo("logo.png");
        defaultMessage.setCodiceDispositivo("DISP-001");
        defaultMessage.setIdMessaggio(123);

        MuiFontStileEntity fontStile = mock(MuiFontStileEntity.class);
        MuiBottoneEntity bottone = mock(MuiBottoneEntity.class);
        when(bottone.getDescrizione()).thenReturn("BTN-001");
        defaultMessage.setFontStile(fontStile);
        defaultMessage.setBottone(bottone);
        defaultMessage.setCodice(false);
        defaultMessage.setRegolamento("REG-001");
        defaultMessage.setBarra(true);
        defaultMessage.setFontEntity(mock(MuiFontEntities.class));
        when(defaultMessage.getFontEntity().getId()).thenReturn("FONT-001");

        PromozionePianificazioneEntity pianificazione = createMockPianificazione(true, true, true);
        String utente = "testUser";

        // Execute
        CastellettoMessaggiEntity result =
                defaultMessaggiHelper.mapToMessageRow(defaultMessage, pianificazione, utente);

        // Verify
        assertNotNull(result);
        assertEquals(pianificazione, result.getPianificazione());
        assertEquals(Integer.valueOf(1), result.getSeqStampa());
        assertEquals(MessaggiSezioneEnum.TITOLO, result.getSezione());
        assertEquals("Test message", result.getTesto());
        assertEquals(true, result.getTaglioCarta());
        assertEquals(false, result.getBarcode());
        assertEquals(MessaggiFontEnum.NORMALE, result.getFont());
        assertEquals(MessaggiAllineamentoEnum.SINISTRA, result.getAllineamento());
        assertEquals(true, result.getBold());
        assertEquals("logo.png", result.getLogo());
        assertEquals("DISP-001", result.getCodiceCanaleDispositivo());
        assertEquals(Integer.valueOf(123), result.getIdMessaggio());
        assertEquals(fontStile, result.getFontStile());
        assertEquals("BTN-001", result.getBottone().getDescrizione());
        assertEquals(false, result.getCodice());
        assertEquals("REG-001", result.getRegolamento());
        assertEquals(true, result.getBarra());
        assertEquals("FONT-001", result.getFontEntity());
        assertEquals(utente, result.getCodiceUtenteInserimento());
        assertNotNull(result.getDataInserimento());
    }

    @Test
    public void testAddMessageRows_exception() {
        // Setup
        PromozionePianificazioneEntity pianificazione = createMockPianificazione(true, true, true);
        String utente = "testUser";

        List<MuiCfgDefaultCastellettoMessaggiEntity> defaultMessages = createDefaultMessages();
        doReturn(defaultMessages)
                .when(defaultMessaggiHelper)
                .getDefaultMessages(any(PromozionePianificazioneEntity.class));

        doThrow(new RuntimeException("Test exception"))
                .when(castellettoMessaggiService)
                .persist(anyList(), anyInt());

        // Execute - should not throw exception
        defaultMessaggiHelper.addDefaultMessageRows(pianificazione, utente);

        // Verify attempt to persist was made
        verify(castellettoMessaggiService).persist(anyList(), anyInt());
    }

    // Helper methods to create mock objects
    private PromozionePianificazioneEntity createMockPianificazione(
            boolean canaleEnabled, boolean meccanicaEnabled, boolean noPreviousMessages) {
        PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        CfgSetPianificazioneEntity setPianificazione = mock(CfgSetPianificazioneEntity.class);
        CfgConfHeaderEntity confHeader = mock(CfgConfHeaderEntity.class);

        // Setup the chain
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(testata.getCanalePromozioneEntity()).thenReturn(canale);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(setPianificazione);
        when(pianificazione.getId()).thenReturn(1L);
        when(meccanica.getId()).thenReturn(1L);
        when(setPianificazione.getId()).thenReturn(1L);

        // Enable/disable canale
        when(canale.getFlLogoMessaggio()).thenReturn(canaleEnabled);
        when(canale.getCodiceCanale()).thenReturn(1L);

        // Enable/disable meccanica
        when(confHeader.getLogoMessaggi()).thenReturn(meccanicaEnabled);
        when(cfgConfHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong()))
                .thenReturn(confHeader);
        when(meccanica.getCodiceMeccanica()).thenReturn("MECC-001");

        // Set previous messages
        if (noPreviousMessages) {
            when(castellettoMessaggiService.findMessaggiByIdPianificazione(anyLong()))
                    .thenReturn(Collections.emptyList());
        } else {
            when(castellettoMessaggiService.findMessaggiByIdPianificazione(anyLong()))
                    .thenReturn(Collections.singletonList(new CastellettoMessaggiEntity()));
        }

        return pianificazione;
    }

    private List<MuiCfgDefaultCastellettoMessaggiEntity> createDefaultMessages() {
        MuiCfgDefaultCastellettoMessaggiEntity message1 = new MuiCfgDefaultCastellettoMessaggiEntity();
        message1.setSeqStampa((short) 1);
        message1.setSezione(MessaggiSezioneEnum.TITOLO);

        MuiCfgDefaultCastellettoMessaggiEntity message2 = new MuiCfgDefaultCastellettoMessaggiEntity();
        message2.setSeqStampa((short) 2);
        message2.setSezione(MessaggiSezioneEnum.RIGA);

        return Arrays.asList(message1, message2);
    }
}
