package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaMeccCanaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.ConfigurazioneMeccanicheCanaleServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurazioneMeccanicheCanaleServiceTest {
    @Mock
    CfgAbilitaMeccCanaleDAO dao;
    @Spy
    @InjectMocks
    ConfigurazioneMeccanicheCanaleServiceImpl service;

    @Test(expected = NullPointerException.class)
    public void whenFindMeccanicaCanaleByPromozioneWithNullValueThenNullPointerException() {
        service.findMeccanicaCanaleByPromozione(null);
    }

    @Test
    public void testFindMeccanicaCanaleByPromozione() throws Exception {
        CanalePromozioneEntity canalePromozioneEntity = Mockito.mock(CanalePromozioneEntity.class);
        service.findMeccanicaCanaleByPromozione(canalePromozioneEntity);
        Mockito.verify(dao).findAllByIdCanale(canalePromozioneEntity);
    }

    @Test
    public void whenFindMeccanicaCanaleGetsExceptionThenItManagesIt() throws Exception {
        CanalePromozioneEntity canalePromozioneEntity = Mockito.mock(CanalePromozioneEntity.class);
        Mockito.when(dao.findAllByIdCanale(canalePromozioneEntity)).thenThrow(new Exception());
        List<CfgAbilitaMeccCanaleEntity> result = service.findMeccanicaCanaleByPromozione(canalePromozioneEntity);
        assertNotNull(result);
        assertThat(result.size(), CoreMatchers.equalTo(0));
    }
    
    @Test
	public void shouldFindAll() {
    	CfgAbilitaMeccCanaleEntity mock1 = mock(CfgAbilitaMeccCanaleEntity.class);
    	CfgAbilitaMeccCanaleEntity mock2 = mock(CfgAbilitaMeccCanaleEntity.class);
		List<CfgAbilitaMeccCanaleEntity> canale = Arrays.asList(mock1, mock2);
		when(dao.findAll()).thenReturn(canale);
		List<CfgAbilitaMeccCanaleEntity> entities = service.findAll();
		verify(dao, times(1)).findAll();
		verify(service, times(1)).findAll();
		assertNotNull(entities);
		assertTrue(entities.size() == 2);
	}

	@Test
	public void shouldNotFindAll() {
		List<CfgAbilitaMeccCanaleEntity> entities = service.findAll();
		verify(dao, times(1)).findAll();
		verify(service, times(1)).findAll();
		assertNotNull(entities);
		assertTrue(entities.isEmpty());
	}

    
  
}
