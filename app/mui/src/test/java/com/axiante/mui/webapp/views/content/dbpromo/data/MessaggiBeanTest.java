package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgCastellettoMessaggiComponentService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.listener.MessaggiUpdatedListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessaggiBeanTest {
    private MessaggiBean messaggiBean;

    @Mock
    private CastellettoMessaggiService messaggiService;

    @Mock
    private ApplicationProperties properties;

    @Mock
    private MuiCfgCastellettoMessaggiComponentService defaultService;

    @Mock
    private MuiCfgDefaultCastellettoMessaggiService cfgMessaggiService;

    @Mock
    private MuiFontEntitiesService fontEntitiesService;

    @Mock
    private CfgCanaleDispositivoEntity dispositivo;

    @Mock
    private PromozionePianificazioneEntity pianificazione;

    @Mock
    private PromozioneTestataEntity promozioneTestata;

    @Mock
    private CanalePromozioneEntity canalePromozione;

    @Mock
    private MeccanicheEntity meccanicaEntity;

    @Mock
    private MessaggiUpdatedListener listener;

    @Mock
    private FileUploadEvent fileUploadEvent;

    @Mock
    private UploadedFile uploadedFile;

    @Mock
    private UsersEntity user;

    @Before
    public void setUp() {
        // Setup basic mocks
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(promozioneTestata);
        when(promozioneTestata.getCanalePromozioneEntity()).thenReturn(canalePromozione);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanicaEntity);
        when(canalePromozione.getCodiceCanale()).thenReturn(1L);
        when(meccanicaEntity.getCodiceMeccanica()).thenReturn("CODMECCANICA");
        when(dispositivo.getCodice()).thenReturn("CODDISPOSITIVO");
        when(dispositivo.getDescrizione()).thenReturn("Test Dispositivo");

        // Create the bean under test
        messaggiBean = Mockito.spy(new MessaggiBean(pianificazione, true, dispositivo, messaggiService, cfgMessaggiService,
                properties, defaultService, fontEntitiesService));

        when(user.getName()).thenReturn("testUser");
        // Mock FacesContextAware methods to avoid null pointer exceptions
        when(messaggiBean.getCurrentUser()).thenReturn(user);
        when(messaggiBean.getRequestParameterMap()).thenReturn(new HashMap<>());
    }

    @Test
    public void testInit() {
        // Setup
        List<MuiFontEntities> fontEntitiesList = new ArrayList<>();
        fontEntitiesList.add(new MuiFontEntities());
        when(fontEntitiesService.findAll()).thenReturn(fontEntitiesList);

        List<MuiCfgCastellettoMessaggiComponentEntity> componentList = new ArrayList<>();
        MuiCfgCastellettoMessaggiComponentEntity component =
                new MuiCfgCastellettoMessaggiComponentEntity();
        component.setComponent(MessaggiComponentsEnum.FONT);
        component.setEnabled(true);
        componentList.add(component);

        when(defaultService.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                anyLong(), anyString(), anyString()))
                .thenReturn(componentList);

        when(cfgMessaggiService.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                anyLong(), anyString(), anyString()))
                .thenReturn(1L);

        // Execute
        messaggiBean.init();

        // Verify
        // this is twice because init is called in the constructor and then again in the method
        verify(fontEntitiesService, Mockito.times(2)).findAll();
        assertEquals(fontEntitiesList, messaggiBean.getFontEntities());
        Assert.assertTrue("Font should be visible", messaggiBean.getVisibilityState().isFontVisible());
        Assert.assertFalse("Reset should not be disabled", messaggiBean.isResetDisabled());
    }

    @Test
    public void testLoadFonEntities() {
        // Setup
        List<MuiFontEntities> fontEntitiesList = new ArrayList<>();
        fontEntitiesList.add(new MuiFontEntities());
        when(fontEntitiesService.findAll()).thenReturn(fontEntitiesList);

        // Execute
        messaggiBean.init();

        // Verify
        // this is called twice: once in the constructor and once in the method
        verify(fontEntitiesService, Mockito.times(2)).findAll();
        assertEquals(fontEntitiesList, messaggiBean.getFontEntities());
    }

    @Test
    public void testClearForm() {
        // Setup
        messaggiBean.getFormBean().setTesto("Test Text");
        messaggiBean.getFormBean().setBold(true);

        // Execute
        messaggiBean.clearForm();

        // Verify
        Assert.assertNull(
                "Testo should be null after clearForm", messaggiBean.getFormBean().getTesto());
        assertEquals(
                "Font should be reset to NORMALE",
                MessaggiFontEnum.NORMALE,
                messaggiBean.getFormBean().getFont());
        assertEquals(
                "Allineamento should be reset to SINISTRA",
                MessaggiAllineamentoEnum.SINISTRA,
                messaggiBean.getFormBean().getAllineamento());
        Assert.assertFalse("Bold should be reset to false", messaggiBean.getFormBean().getBold());
    }

    @Test
    public void testUpdateMessaggioValidationErrorWhenPianificazioneNull() {
        // Setup
        messaggiBean = Mockito.spy(new MessaggiBean(pianificazione, true, dispositivo, messaggiService, cfgMessaggiService,
                properties, defaultService, fontEntitiesService));

        // Execute
        messaggiBean.updateMessaggio();

        // Verify
        verify(messaggiBean).addMessage(ArgumentMatchers.eq(null), any(FacesMessage.class));
        verify(messaggiService, Mockito.never()).persistWithAuditLog(any(), anyString());
    }

    @Test
    public void testUpdateMessaggioValidationErrorWhenIdMessaggioNull() {
        // Setup
        messaggiBean.getFormBean().setIdMessaggio(null);

        // Execute
        messaggiBean.updateMessaggio();

        // Verify
        verify(messaggiBean).addMessage(ArgumentMatchers.eq(null), any(FacesMessage.class));
        verify(messaggiService, Mockito.never()).persistWithAuditLog(any(), anyString());
    }

    @Test
    public void testUpdateMessaggioSuccess() {
        // Setup
        messaggiBean.getFormBean().setIdMessaggio(1);
        messaggiBean.getFormBean().setTesto("Test testo");
        messaggiBean.getFormBean().setSeqStampa(1);
        messaggiBean.getFormBean().setBold(true);
        messaggiBean.getFormBean().setFont(MessaggiFontEnum.NORMALE);
        messaggiBean.getFormBean().setAllineamento(MessaggiAllineamentoEnum.SINISTRA);

        when(messaggiService.persistWithAuditLog(any(CastellettoMessaggiEntity.class), anyString()))
                .thenReturn(new CastellettoMessaggiEntity());

        // Add a listener to verify it's called
        messaggiBean.addMessaggiUpdateListener(listener);
        when(dispositivo.getNumeroCaratteri()).thenReturn(100);
        when(messaggiBean.validateTestoMessaggio(anyString())).thenReturn(null);
        // Execute
        messaggiBean.updateMessaggio();

        // Verify
        ArgumentCaptor<CastellettoMessaggiEntity> captor =
                ArgumentCaptor.forClass(CastellettoMessaggiEntity.class);
        verify(messaggiService).persistWithAuditLog(captor.capture(), ArgumentMatchers.eq("testUser"));
        verify(listener).onMessaggiUpdated();

        CastellettoMessaggiEntity savedEntity = captor.getValue();
        assertEquals(Integer.valueOf(1), savedEntity.getIdMessaggio());
        assertEquals(MessaggiSezioneEnum.MESSAGGI, savedEntity.getSezione());
        assertEquals("Test testo", savedEntity.getTesto());
        Assert.assertTrue(savedEntity.getBold());
    }

    @Test
    public void testCalcolaSeqStampa_EmptyList() {
        // Setup
        when(messaggiService.findMessaggiByIdPianificazioneAndCodiceDispositivo(anyLong(), anyString()))
                .thenReturn(Collections.emptyList());

        // Execute - now we can call calcolaSeqStampa directly since it's protected
        Integer result = messaggiBean.calcolaSeqStampa();

        // Verify
        verify(messaggiService)
                .findMessaggiByIdPianificazioneAndCodiceDispositivo(any(), eq("CODDISPOSITIVO"));
        assertEquals(Integer.valueOf(1), result);
    }

    @Test
    public void testCalcolaSeqStampa_WithExistingMessages() {
        // Setup
        CastellettoMessaggiEntity msg1 = new CastellettoMessaggiEntity();
        msg1.setSeqStampa(1);
        CastellettoMessaggiEntity msg2 = new CastellettoMessaggiEntity();
        msg2.setSeqStampa(2);
        List<CastellettoMessaggiEntity> messages = Arrays.asList(msg1, msg2);

        when(messaggiService.findMessaggiByIdPianificazioneAndCodiceDispositivo(anyLong(), anyString()))
                .thenReturn(messages);

        // Execute - now we can call calcolaSeqStampa directly
        Integer result = messaggiBean.calcolaSeqStampa();

        // Verify
        verify(messaggiService)
                .findMessaggiByIdPianificazioneAndCodiceDispositivo(any(), eq("CODDISPOSITIVO"));
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    public void testValidateTestoMessaggio_ExceedsMaxLength() {
        // Setup
        when(dispositivo.getNumeroCaratteri()).thenReturn(5);

        // Execute
        String result = messaggiBean.validateTestoMessaggio("123456");

        // Verify
        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("Superata la dimensione massima"));
    }

    @Test
    public void testValidateTestoMessaggio_InvalidCharacters() {
        // Setup
        when(dispositivo.getLimitaCaratteri()).thenReturn("ASCII-128");

        // Execute
        String result = messaggiBean.validateTestoMessaggio("Test with non-ASCII: ñ");

        // Verify
        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("caratteri non consentiti"));
    }

    @Test
    public void testHandleLogoUpload() {
        // Setup
        messaggiBean.getFormBean().setIdMessaggio(1);
        messaggiBean.getFormBean().setSeqStampa(1);

        when(fileUploadEvent.getFile()).thenReturn(uploadedFile);
        when(uploadedFile.getFileName()).thenReturn("test.bmp");
        when(uploadedFile.getContents()).thenReturn("test content".getBytes());

        // Execute
        messaggiBean.handleLogoUpload(fileUploadEvent);

        // Verify
        Assert.assertNotNull(messaggiBean.getLogoUploadBean().getUploadedLogo());
        Assert.assertFalse(messaggiBean.getFormBean().getBarcode());
        Assert.assertFalse(messaggiBean.getFormBean().getTaglioCarta());
    }

    @Test
    public void testSelezionaMessaggio() {
        // Setup
        Map<String, String> params = new HashMap<>();
        params.put("messaggioSelected", "123");
        when(messaggiBean.getRequestParameterMap()).thenReturn(params);

        CastellettoMessaggiEntity message = new CastellettoMessaggiEntity();
        message.setSeqStampa(1);
        message.setTaglioCarta(true);
        message.setBarcode(false);
        message.setFont(MessaggiFontEnum.NORMALE);
        message.setAllineamento(MessaggiAllineamentoEnum.SINISTRA);
        message.setBold(true);
        message.setTesto("Test");
        message.setIdMessaggio(1);

        when(messaggiService.findById(anyLong())).thenReturn(message);

        // Execute
        messaggiBean.selezionaMessaggio();

        // Verify
        verify(messaggiService).findById(anyLong());
        Assert.assertTrue(messaggiBean.getFormBean().getTaglioCarta());
        assertEquals("Test", messaggiBean.getFormBean().getTesto());
    }

    @Test
    public void testDetermineVisibleComponents() {
        // Setup
        List<MuiCfgCastellettoMessaggiComponentEntity> components = new ArrayList<>();

        MuiCfgCastellettoMessaggiComponentEntity comp1 = new MuiCfgCastellettoMessaggiComponentEntity();
        comp1.setComponent(MessaggiComponentsEnum.TAGLIO_CARTA);
        comp1.setEnabled(true);
        components.add(comp1);

        MuiCfgCastellettoMessaggiComponentEntity comp2 = new MuiCfgCastellettoMessaggiComponentEntity();
        comp2.setComponent(MessaggiComponentsEnum.BARCODE);
        comp2.setEnabled(true);
        components.add(comp2);

        when(defaultService.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                anyLong(), anyString(), anyString()))
                .thenReturn(components);

        // Execute
        messaggiBean.init();

        // Verify
        Assert.assertTrue(messaggiBean.getVisibilityState().isTaglioCartaVisible());
        Assert.assertTrue(messaggiBean.getVisibilityState().isBarcodeVisible());
        Assert.assertFalse(messaggiBean.getVisibilityState().isLogoVisible());
    }

    @Test
    public void testAddMessaggiUpdateListener() {
        // Execute
        messaggiBean.addMessaggiUpdateListener(listener);
        messaggiBean.getFormBean().setIdMessaggio(1);
        messaggiBean.updateMessaggio();

        // Verify
        verify(listener).onMessaggiUpdated();
    }

    @Test
    public void testResetDefaultMessaggi() {
        // Execute
        messaggiBean.resetDefaultMessaggi();

        // Verify
        verify(messaggiService).resetMessaggi(pianificazione, dispositivo, "testUser");
    }

    @Test
    public void testGetNomeDispositivo() {
        // Execute
        String result = messaggiBean.getNomeDispositivo();

        // Verify
        assertEquals("Test Dispositivo", result);
    }

    @Test
    public void testDetermineResetEnabled_NoDefaultsAvailable() {
        // Setup
        when(cfgMessaggiService.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                anyLong(), anyString(), anyString()))
                .thenReturn(0L);

        // Execute
        messaggiBean.init();

        // Verify
        Assert.assertTrue(messaggiBean.isResetDisabled());
    }

    @Test
    public void testDetermineResetEnabled_DefaultsAvailable() {
        // Setup
        when(cfgMessaggiService.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                anyLong(), anyString(), anyString()))
                .thenReturn(5L);

        // Execute
        messaggiBean.init();

        // Verify
        Assert.assertFalse(messaggiBean.isResetDisabled());
    }
}
