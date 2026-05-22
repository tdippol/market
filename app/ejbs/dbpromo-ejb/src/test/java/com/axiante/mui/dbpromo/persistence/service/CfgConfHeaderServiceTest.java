package com.axiante.mui.dbpromo.persistence.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgConfHeaderDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.CfgConfHeaderServiceImpl;
import java.util.Arrays;
import java.util.Collections;
import javax.persistence.NonUniqueResultException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgConfHeaderServiceTest {

    @Spy
    private CfgPianificazioneDAO cfgPianificazioneDAO;

    @Spy
    private CfgConfHeaderDAO dao;

    @Spy
    @InjectMocks
    private CfgConfHeaderService service = new CfgConfHeaderServiceImpl();

    @Test
    public void findByMeccanicaAndSetPromozione_shouldReturnAssociatedHeader() {
        final CfgConfHeaderEntity confHeaderEntity = createConfHeaderEntity();
        when(dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
                .thenReturn(confHeaderEntity);
        final CfgConfHeaderEntity entity = service.findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
        verify(dao, times(1)).findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
        assertNotNull(entity);
        assertEquals(1, (int) entity.getMinSet());
        assertEquals(1, (int) entity.getMinRaggruppamento());
        assertNull(entity.getMaxSet());
        assertNull(entity.getMaxRaggruppamento());
    }

    @Test(expected = NonUniqueResultException.class)
    public void findTipoElemento_givenMeccanicaAndSetPromozione_shouldThrowException_whenMultipleEntities() {
        when(dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
                .thenThrow(NonUniqueResultException.class);
        service.findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
        verify(dao, times(1))
                .findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
    }

    @Test(expected = NullPointerException.class)
    public void isFieldMeccanicaFullyConfigured_givenNullId_shouldThrowException() {
        service.isFieldMeccanicaFullyConfigured(null);
    }

    @Test
    public void isFieldMeccanicaFullyConfigured_shouldReturnFalse_whenMeccanicaNotConfigured() {
        // Meccanica configurata a livello SET, ma solo riga ELEMENTO
        when(dao.findLivelloByIdHeader(1L)).thenReturn("SET");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Collections.singletonList("ELEMENTO"));
        assertFalse(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello SET, ma solo riga RAGGRUPPAMENTO
        when(dao.findLivelloByIdHeader(1L)).thenReturn("SET");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Collections.singletonList("RAGGRUPPAMENTO"));
        assertFalse(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello SET, ma solo righe RAGGRUPPAMENTO e ELEMENTO
        when(dao.findLivelloByIdHeader(1L)).thenReturn("SET");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Arrays.asList("RAGGRUPPAMENTO", "ELEMENTO"));
        assertFalse(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello SET, ma senza righe
        when(dao.findLivelloByIdHeader(1L)).thenReturn("SET");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Collections.emptyList());
        assertFalse(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello RAGGRUPPAMENTO, ma solo riga ELEMENTO
        when(dao.findLivelloByIdHeader(1L)).thenReturn("RAGGRUPPAMENTO");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Collections.singletonList("ELEMENTO"));
        assertFalse(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello RAGGRUPPAMENTO, ma senza righe
        when(dao.findLivelloByIdHeader(1L)).thenReturn("RAGGRUPPAMENTO");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Collections.emptyList());
        assertFalse(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello ELEMENTO, ma senza righe
        when(dao.findLivelloByIdHeader(1L)).thenReturn("ELEMENTO");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Collections.emptyList());
        assertFalse(service.isFieldMeccanicaFullyConfigured(1L));
    }

    @Test
    public void isFieldMeccanicaFullyConfigured_shouldReturnTrue_whenMeccanicaConfigured() {
        // Meccanica configurata a livello ELEMENTO
        when(dao.findLivelloByIdHeader(1L)).thenReturn("ELEMENTO");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Collections.singletonList("ELEMENTO"));
        assertTrue(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello RAGGRUPPAMENTO
        when(dao.findLivelloByIdHeader(1L)).thenReturn("RAGGRUPPAMENTO");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Arrays.asList("ELEMENTO", "RAGGRUPPAMENTO"));
        assertTrue(service.isFieldMeccanicaFullyConfigured(1L));
        // Meccanica configurata a livello SET
        when(dao.findLivelloByIdHeader(1L)).thenReturn("SET");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, "ID_MECCANICA"))
                .thenReturn(Arrays.asList("ELEMENTO", "RAGGRUPPAMENTO", "SET"));
        assertTrue(service.isFieldMeccanicaFullyConfigured(1L));
    }

    private CfgConfHeaderEntity createConfHeaderEntity() {
        final CfgConfHeaderEntity entity = new CfgConfHeaderEntity();
        entity.setMinSet(1);
        entity.setMinRaggruppamento(1);
        return entity;
    }
}
