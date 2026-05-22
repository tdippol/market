package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleDispositivoService;
import com.axiante.mui.dbpromo.persistence.service.MuiBottoneService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgCastellettoMessaggiComponentService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontStileService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessaggiCastellettiBeanTest {

    @Mock
    private Instance<CastellettoMessaggiService> messaggiServiceInstance;

    @Mock
    private CastellettoMessaggiService messaggiService;

    @Mock
    private Instance<MuiCfgDefaultCastellettoMessaggiService> defaultMessaggiServiceInstance;

    @Mock
    private MuiCfgDefaultCastellettoMessaggiService defaultMessaggiService;

    @Mock
    private Instance<ApplicationProperties> applicationPropertiesInstance;

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Mock
    private PianificazioneService pianificazioneService;

    @Mock
    private Instance<MuiCfgCastellettoMessaggiComponentService> defaultComponentServices;

    @Mock
    private MuiCfgCastellettoMessaggiComponentService componentService;

    @Mock
    private Instance<CfgCanaleDispositivoService> canaleDispositivoServices;

    @Mock
    private CfgCanaleDispositivoService canaleDispositivoService;

    @Mock
    private Instance<MuiFontStileService> muiFontStileServices;

    @Mock
    private MuiFontStileService muiFontStileService;

    @Mock
    private Instance<MuiBottoneService> muiBottoneServices;

    @Mock
    private MuiBottoneService muiBottoneService;

    @Mock
    private CastellettiBean castellettiBean;

    @Mock
    private CategoriaBuoniBean categoriaBuoniBean;

    @Mock
    private Instance<MuiFontEntitiesService> muiFontEntitiesServicesInstance;

    @Mock
    private MuiFontEntitiesService muiFontEntitiesService;

    @Mock
    private PlanningCommons planningCommons;

    @Spy
    @InjectMocks
    private MessaggiCastellettiBean bean;

    private PromozionePianificazioneEntity pianificazione;
    private List<CfgCanaleDispositivoEntity> dispositivi;
    private PromozioneTestataEntity testata;
    private List<MuiFontStileEntity> fontStileList;
    private List<MuiBottoneEntity> bottoniList;

    @Before
    public void setUp() {
        // Configure instances to return the mocked services
        when(messaggiServiceInstance.get()).thenReturn(messaggiService);
        when(defaultMessaggiServiceInstance.get()).thenReturn(defaultMessaggiService);
        when(applicationPropertiesInstance.get()).thenReturn(applicationProperties);
        when(pianificazioneServiceInstance.get()).thenReturn(pianificazioneService);
        when(defaultComponentServices.get()).thenReturn(componentService);
        when(canaleDispositivoServices.get()).thenReturn(canaleDispositivoService);
        when(muiFontStileServices.get()).thenReturn(muiFontStileService);
        when(muiFontEntitiesServicesInstance.get()).thenReturn(muiFontEntitiesService);
        when(muiBottoneServices.get()).thenReturn(muiBottoneService);
        doNothing().when(categoriaBuoniBean).setPromozione(any(PromozioneTestataEntity.class));

        // Set up test entities
        pianificazione = new PromozionePianificazioneEntity();
        pianificazione.setId(1L);
        pianificazione.setBuonoScontoRadice(100);
        pianificazione.setBuonoScontoProgressivo(10);
        pianificazione.setCanaleDispositivo("DISP1||DISP2");

        // Set up testata
        testata = new PromozioneTestataEntity();
        testata.setCodicePromozione("PROMO123");

        // Set up MuiCfgSetPianificazione
        CfgSetPianificazioneEntity setPianificazione = new CfgSetPianificazioneEntity();
        setPianificazione.setId(1L);
        testata.setMuiCfgSetPianificazione(setPianificazione);

        // Set up meccanica
        MeccanicheEntity meccanica = new MeccanicheEntity();
        meccanica.setId(1L);
        meccanica.setCodiceMeccanica("MECH1");
        meccanica.setDescrizione("Test Meccanica");

        // Set up CanalePromozione
        CanalePromozioneEntity canalePromozione = new CanalePromozioneEntity();
        canalePromozione.setCodiceCanale(1L);
        canalePromozione.setFlLogoMessaggio(true);
        testata.setCanalePromozioneEntity(canalePromozione);

        // Connect all entities
        pianificazione.setPromozioneTestataEntity(testata);
        pianificazione.setMeccanicaEntity(meccanica);

        // Set up configuration header
        CfgConfHeaderEntity configHeader = new CfgConfHeaderEntity();
        configHeader.setLogoMessaggi(true);
        configHeader.setCastelletti(true);
        configHeader.setMeccanicaEntity(meccanica);

        Set<CfgConfHeaderEntity> headers = new HashSet<>();
        headers.add(configHeader);
        setPianificazione.setMuiCfgConfHeaders(headers);

        // Set up dispositivi
        dispositivi = new ArrayList<>();
        CfgCanaleDispositivoEntity dispositivo1 = new CfgCanaleDispositivoEntity();
        dispositivo1.setCodice("DISP1");
        dispositivo1.setDescrizione("Dispositivo 1");

        CfgCanaleDispositivoEntity dispositivo2 = new CfgCanaleDispositivoEntity();
        dispositivo2.setCodice("DISP2");
        dispositivo2.setDescrizione("Dispositivo 2");

        dispositivi.add(dispositivo1);
        dispositivi.add(dispositivo2);

        // Set up font stile list
        fontStileList = new ArrayList<>();
        MuiFontStileEntity fontStile = new MuiFontStileEntity();
        fontStile.setId("1L");
        fontStileList.add(fontStile);

        // Set up mui bottone list
        bottoniList = new ArrayList<>();
        MuiBottoneEntity bottone = new MuiBottoneEntity();
        bottone.setId(1L);
        bottone.setDescrizione("BTN-01");
        bottoniList.add(bottone);

        // Set up request parameters
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("idPianificazione", "1");

        // Set up mocks for the bean's request parameter map
        doReturn(requestParams).when(bean).getRequestParameterMap();

        // Allow executeScript to do nothing
        doNothing().when(bean).executeScript(anyString());

        // Allow addMessage to do nothing
        doNothing().when(bean).addMessage(any(), any(FacesMessage.class));
    }

    @Test
    public void testRefreshHeaderMessaggiHeader_Success() {
        // Arrange
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(planningCommons.isPlanningEditableCellAndRow(testata)).thenReturn(true);
        when(canaleDispositivoService.findAll()).thenReturn(dispositivi);
        when(muiFontStileService.findAll()).thenReturn(fontStileList);
        when(muiBottoneService.findAll()).thenReturn(bottoniList);

        // Act
        bean.refreshHeaderMessaggiHeader();

        // Assert
        assertEquals(1L, bean.getIdPianificazione().longValue());
        assertEquals("PROMO123", bean.getCodicePromo());
        assertEquals("MECH1 - Test Meccanica", bean.getMeccanica());
        assertEquals("100", bean.getRadice());
        assertEquals("10", bean.getProgressivo());
        verify(castellettiBean).setPianificazione(pianificazione);
        verify(bean).executeScript("PF('logoMessaggioDialog').show();");
        assertTrue(bean.isLogoMessaggiRendered());
        assertTrue(bean.isCastellettiRendered());
        assertTrue(bean.isRenderTabs());
    }

    @Test
    public void testRefreshHeaderMessaggiHeader_PianificazioneNull() {
        // Arrange
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(null);

        // Act
        bean.refreshHeaderMessaggiHeader();

        // Assert
        verify(bean).addMessage(any(), any(FacesMessage.class));
        verify(bean, never()).executeScript(anyString());
    }

    @Test
    public void testRefreshHeaderMessaggiHeader_Exception() {
        // Arrange
        when(pianificazioneService.getPromoPianificazoneById(1L))
                .thenThrow(new RuntimeException("Test exception"));

        // Act
        bean.refreshHeaderMessaggiHeader();

        // Assert
        verify(bean).addMessage(any(), any(FacesMessage.class));
        verify(bean, never()).executeScript(anyString());
    }


    @Test
    public void testGetDispositiviList() {
        // Initialize private field
        bean.setDispositivi(dispositivi);

        // Act
        String result = bean.getDispositiviList();

        // Assert
        assertEquals("DISP1,DISP2", result);
    }

    @Test
    public void testGetDispositiviList_Null() {
        // Initialize private field
        bean.setDispositivi(null);
        bean.setPianificazione(pianificazione);

        // Act
        String result = bean.getDispositiviList();

        // Assert
        assertEquals("", result);
    }

    @Test
    public void testGetDispositivi() {
        // Initialize private field
        bean.setDispositivi(dispositivi);

        // Act
        List<String> result = bean.getDispositivi();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains("Dispositivo 1"));
        assertTrue(result.contains("Dispositivo 2"));
    }


    @Test
    public void testSetSelectedTab() {
        // Arrange
        doNothing().when(bean).fetchMessaggi();

        // Act
        bean.setSelectedTab(1);

        // Assert
        assertEquals(1, bean.getSelectedTab());
        verify(bean).fetchMessaggi();
    }

    @Test
    public void testGetMessaggiBean() {
        // Arrange
        MessaggiBean messaggiBean = mock(MessaggiBean.class);
        List<MessaggiBean> messaggiBeans = new ArrayList<>();
        messaggiBeans.add(messaggiBean);

        // Initialize private field
        bean.setMessaggiBeans(messaggiBeans);
        bean.setSelectedTab(0);

        // Act
        MessaggiBean result = bean.getMessaggiBean();

        // Assert
        assertEquals(messaggiBean, result);
    }

    @Test
    public void testGetMessaggiBean_NoBean() {
        // Initialize private field
        bean.setMessaggiBeans(Collections.emptyList());

        // Act
        MessaggiBean result = bean.getMessaggiBean();

        // Assert
        assertNull(result);
    }
}
