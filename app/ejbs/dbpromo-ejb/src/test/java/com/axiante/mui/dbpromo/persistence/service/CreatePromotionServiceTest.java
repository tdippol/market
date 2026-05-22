package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.CanaleLastProgEntityDAO;
import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaMeccCanaleDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleNegoziDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgParametriDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgSetPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgStatiCanaleConsentDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgStatiTransizioniDAO;
import com.axiante.mui.dbpromo.persistence.dao.CreaPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgParametriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.CreatePromotionServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreatePromotionServiceTest {

	@Mock
	private CanalePromozioneDAO canalePromozioneDAO;

	@Mock
	private CfgParametriDAO cfgParametriEntityDAO;

	@Mock
	private CreaPromozioneDAO creaPromozioneEntityDAO;

	@Mock
	private GruppoPromozioneDAO gruppoPromozioneDAO;

	@Mock
	private StatoPromozioneDAO statoPromozioneDAO;

	@Mock
	private CfgCanaleNegoziDAO cfgCanaleNegoziDAO;

	@Mock
	private CfgAbilitaMeccCanaleDAO cfgAbilitaMeccCanaleDAO;

	@Mock
	private CfgSetPianificazioneDAO cfgSetPianificazioneDAO;

	@Mock
	private CfgStatiCanaleConsentDAO cfgStatiCanaleConsentDAO;

	@Mock
	private CfgStatiTransizioniDAO cfgStatiTransizioniDAO;

	@Mock
	private CanaleLastProgEntityDAO canaleLastProgEntityDAO;
	@Spy
	@InjectMocks
	private final CreatePromotionService service = new CreatePromotionServiceImpl();

	private final String USER = "junit test";

	@Test
	public void shouldReturnChannelGivenGroup() throws Exception {
		final GruppoPromozioneEntity group = mock(GruppoPromozioneEntity.class);
		List<CanalePromozioneEntity> channels = service.findChannelListByGroup(null);
		assertNotNull(channels);
		verify(canalePromozioneDAO, never()).findAllByGroup(group);
		assertTrue(channels.isEmpty());

		channels = service.findChannelListByGroup(group);
		assertNotNull(channels);
		verify(canalePromozioneDAO, times(1)).findAllByGroup(group);
		assertTrue(channels.isEmpty());

		List<CanalePromozioneEntity> canaliPromozione = new ArrayList<>();
		CanalePromozioneEntity channel1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity channel2 = mock(CanalePromozioneEntity.class);
		Collections.addAll(canaliPromozione, channel1, channel2);
		when(canalePromozioneDAO.findAllByGroup(group)).thenReturn(canaliPromozione);
		channels = service.findChannelListByGroup(group);
		assertNotNull(channels);
		verify(canalePromozioneDAO, times(2)).findAllByGroup(group);
		assertFalse(channels.isEmpty());
		assertEquals(canaliPromozione.size(), channels.size());
		assertEquals(channel1, channels.get(0));
		assertEquals(channel2, channels.get(1));
	}

	@Test
	public void shouldFindSlotCreaPromoValue() throws Exception {
		String slotCreaPromoValue = service.findSlotCreaPromoValue();
		verify(cfgParametriEntityDAO, times(1)).findAll();
		assertNotNull(slotCreaPromoValue);
		assertTrue(slotCreaPromoValue.isEmpty());

		when(cfgParametriEntityDAO.findAll()).thenReturn(null);
		slotCreaPromoValue = service.findSlotCreaPromoValue();
		verify(cfgParametriEntityDAO, times(2)).findAll();
		assertNotNull(slotCreaPromoValue);
		assertTrue(slotCreaPromoValue.isEmpty());

		when(cfgParametriEntityDAO.findAll()).thenReturn(new ArrayList<>());
		slotCreaPromoValue = service.findSlotCreaPromoValue();
		verify(cfgParametriEntityDAO, times(3)).findAll();
		assertNotNull(slotCreaPromoValue);
		assertTrue(slotCreaPromoValue.isEmpty());

		CfgParametriEntity mock = mock(CfgParametriEntity.class);
		when(mock.getCodiceParametro()).thenReturn(Constants.CREA_PROMOZIONE_SLOT_MINIMUM);
		when(mock.getValore()).thenReturn("test");
		when(cfgParametriEntityDAO.findAll()).thenReturn(Arrays.asList(mock));
		slotCreaPromoValue = service.findSlotCreaPromoValue();
		verify(cfgParametriEntityDAO, times(4)).findAll();
		assertNotNull(slotCreaPromoValue);
		assertFalse(slotCreaPromoValue.isEmpty());
		assertEquals(mock.getValore(), slotCreaPromoValue);
	}

	@Test
	public void testGetAllGroups() throws Exception {
		service.getAllGroups();
		verify(gruppoPromozioneDAO).findAll();
	}

	@Test
	public void testFindAllPromoTransitionByChannel() {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		service.findAllPromoTransitionByChannel(mock);
		verify(cfgStatiTransizioniDAO).findAllPromoTransitionByChannel(mock);
	}

	@Test
	public void testFindAllPromoAllowedStateByChannel() {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		service.findAllPromoAllowedStateByChannel(mock);
		verify(cfgStatiCanaleConsentDAO).findAllPromoAllowedStateByChannel(mock);
	}

	@Test
	public void testPersistCanaleLastProgEntity() {
		CanaleLastProgEntity mock = mock(CanaleLastProgEntity.class);
		service.persistCanaleLastProgEntity(mock, USER);
		verify(canaleLastProgEntityDAO).persistWithAuditLog(mock, USER);
	}

	@Test
	public void testFindCanaleLastProgEntityByYearAndChannel() {
		String anno = "2000";
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		service.findCanaleLastProgEntityByYearAndChannel(anno, mock);
		verify(canaleLastProgEntityDAO).getByYearAndChannel(anno, mock);
	}

	@Test
	public void testFindChannelByDescription() throws Exception {
		String description = "description";
		service.findChannelByDescription(description);
		verify(canalePromozioneDAO).findByDescription(description);
	}

	@Test
	public void testFindStatoPromozioneEntityByCodiceStato() throws Exception {
		String codiceStato = "codiceStato";
		service.findStatoPromozioneEntityByCodiceStato(codiceStato);
		verify(statoPromozioneDAO).findByCode(codiceStato);
	}

	@Test
	public void testFindChannelShopsByChannel() throws Exception {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		service.findChannelShopsByChannel(mock);
		verify(cfgCanaleNegoziDAO).findAllByIdCanale(mock);
	}

	@Test
	public void testFindEnabledChannelMechanicByChannel() throws Exception {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		service.findEnabledChannelMechanicByChannel(mock);
		verify(cfgAbilitaMeccCanaleDAO).findAllByIdCanale(mock);
	}

	@Test
	public void testFindByLockedAndDataInizio() throws Exception {
		Date dataInizio = mock(Date.class);
		service.findByLockedAndDataInizio(dataInizio);
		verify(cfgSetPianificazioneDAO).findByLockedAndDataInizio(dataInizio);
	}

	@Test
	public void testPersist() throws Exception {
		CreaPromozioneEntity mock = mock(CreaPromozioneEntity.class);
		service.persist(mock, USER);
		verify(creaPromozioneEntityDAO).persistWithAuditLog(mock, USER);
	}

	@Test(expected = NullPointerException.class)
	public void testWhenPersistWithNoEntityThenThrowsNullpointerException() throws Exception {
		service.persist(null, USER);
	}

	@Test(expected = NullPointerException.class)
	public void testWhenPersistWithNoUserThenThrowsNullpointerException() throws Exception {
		CreaPromozioneEntity mock = mock(CreaPromozioneEntity.class);
		service.persist(mock, null);
	}

	@Test(expected = NullPointerException.class)
	public void testWhenPersistWithNoParamsThenThrowsNullpointerException() throws Exception {
		service.persist(null, null);
	}

	@Test
	public void testGetAllPromoByUserId() throws Exception {
		String userId = "userId";
		service.getAllPromoByUserId(userId);
		verify(creaPromozioneEntityDAO).findAllByUserId(userId);
	}

	@Test
	public void testFindByUserIdAndSlotId() throws Exception {
		String userId = "userId";
		String slotId = "slotId";
		service.findByUserIdAndSlotId(userId, slotId);
		verify(creaPromozioneEntityDAO).findByUserIdAndSlotId(userId, slotId);
	}

	@Test
	public void testFindChannelById() throws Exception {
		CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
		when(canalePromozioneDAO.findById(Long.MAX_VALUE)).thenReturn(mock);
		CanalePromozioneEntity entity = service.findChannelById(Long.MAX_VALUE);
		verify(canalePromozioneDAO, times(1)).findById(Long.MAX_VALUE);
		verify(service, times(1)).findChannelById(Long.MAX_VALUE);
		assertNotNull(entity);
	}

	@Test(expected = NullPointerException.class)
	public void testWhenPersistCanaleLastProgEntityWithNoUserThenThrowsNullpointerException() {
		CanaleLastProgEntity mock = mock(CanaleLastProgEntity.class);
		service.persistCanaleLastProgEntity(mock, null);
	}

}
