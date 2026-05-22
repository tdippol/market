package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationServiceImplTest {
    @Mock
    private CfgPianificazioneDAO dao;

    @Mock
    private CfgSetPianificazioneEntity cfgSetPianificzione;

    @Mock
    private MeccanicheEntity meccanica;

    @Mock
    private CfgPianificazioneEntity entity1;

    @Mock
    private CfgPianificazioneEntity entity2;

    @InjectMocks
    private ConfigurationServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findBySetAndMeccanicaAndCampo(cfgSetPianificzione, meccanica, "FIELD"))
                .thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findBySetAndMeccanicaAndCampoAndTipoRiga(cfgSetPianificzione, meccanica, "FIELD", PianificazioneRowTypeEnum.ELEMENTO))
                .thenReturn(Collections.singletonList(entity1));
    }

    @Test
    public void findBySetAndMeccanicaAndCampo() {
        final List<CfgPianificazioneEntity> result = service.findBySetAndMeccanicaAndCampo(cfgSetPianificzione, meccanica, "FIELD");
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findBySetAndMeccanicaAndCampo(cfgSetPianificzione, meccanica, "FIELD");
    }

    @Test
    public void findBySetAndMeccanicaAndCampoAndTipoRiga() {
        final List<CfgPianificazioneEntity> result = service.findBySetAndMeccanicaAndCampoAndTipoRiga(cfgSetPianificzione, meccanica, "FIELD", PianificazioneRowTypeEnum.ELEMENTO);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity1));
        verify(dao, times(1)).findBySetAndMeccanicaAndCampoAndTipoRiga(cfgSetPianificzione, meccanica, "FIELD", PianificazioneRowTypeEnum.ELEMENTO);
    }
}