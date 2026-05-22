package com.axiante.mui.dbpromo.business.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgPianificazioneServiceTest {

	@Spy
	CfgPianificazioneDAO dao;

	@Spy
	@InjectMocks
	private CfgPianificazioneService service = new CfgPianificazioneServiceImpl();

	private Set<PromozioneTestataEntity> testate;

	private Set<PromozioneTestataEntity> testateNotActive;

	@Before
	public void setUp() {
		testate = new HashSet<>();
		PromozioneTestataEntity mock1 = mock(PromozioneTestataEntity.class);

		when(mock1.getDataInizio()).thenReturn(
				Date.from(LocalDate.now().plusDays(5).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		when(mock1.getDataFine()).thenReturn(
				Date.from(LocalDate.now().plusDays(10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		PromozioneTestataEntity mock2 = mock(PromozioneTestataEntity.class);
		when(mock2.getDataInizio()).thenReturn(
				Date.from(LocalDate.now().minusDays(5).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		when(mock2.getDataFine()).thenReturn(
				Date.from(LocalDate.now().plusDays(10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		testate.addAll(Arrays.asList(mock1, mock2));

		testateNotActive = new HashSet<>();
		PromozioneTestataEntity mock3 = mock(PromozioneTestataEntity.class);
		when(mock3.getDataInizio()).thenReturn(
				Date.from(LocalDate.now().plusDays(5).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		when(mock3.getDataFine()).thenReturn(
				Date.from(LocalDate.now().plusDays(5).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		PromozioneTestataEntity mock4 = mock(PromozioneTestataEntity.class);
		when(mock4.getDataInizio()).thenReturn(
				Date.from(LocalDate.now().plusDays(5).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		when(mock4.getDataFine()).thenReturn(
				Date.from(LocalDate.now().plusDays(7).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		testateNotActive.addAll(Arrays.asList(mock3, mock4));
	}

	@Test
	public void shouldCfgIsNotLockableWhenJustOnePromoIsActive() {
		CfgSetPianificazioneEntity setPianificazione = mock(CfgSetPianificazioneEntity.class);
		Mockito.when(setPianificazione.getPromozioneTestataEntities()).thenReturn(testate);
		setPianificazione.setPromozioneTestataEntities(testate);
		final boolean lockable = service.isLockable(setPianificazione);
		assertFalse(lockable);
		verify(service, times(1)).isLockable(setPianificazione);
	}

	@Test
	public void shouldCfgIsLockableWhenEveryPromoTestataIsNotActive() {
		CfgSetPianificazioneEntity setPianificazione = mock(CfgSetPianificazioneEntity.class);
		Mockito.when(setPianificazione.getPromozioneTestataEntities()).thenReturn(testateNotActive);
		setPianificazione.setPromozioneTestataEntities(testateNotActive);
		final boolean lockable = service.isLockable(setPianificazione);
		assertTrue(lockable);
		verify(service, times(1)).isLockable(setPianificazione);
	}

	@Test
	public void findTipoElement() {
		Long promoID = Long.MIN_VALUE;
		Long meccanicaID = Long.MAX_VALUE;
		service.findTipoElement(promoID, meccanicaID);
		verify(dao).findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(promoID, meccanicaID);
		verify(service, times(1)).findTipoElement(promoID, meccanicaID);
	}

	@Test
	public void shouldCfgIsNotLockableWhenPromoHasFuzzyDates() {
		CfgSetPianificazioneEntity setPianificazione = mock(CfgSetPianificazioneEntity.class);
		PromozioneTestataEntity promo = mock(PromozioneTestataEntity.class);
		Mockito.when(setPianificazione.getPromozioneTestataEntities()).thenReturn(new HashSet<>(Arrays.asList(promo)));

		when(promo.getDataInizio()).thenReturn(null);
		setPianificazione.setPromozioneTestataEntities(testate);
		assertTrue(service.isLockable(setPianificazione));
		verify(service, times(1)).isLockable(setPianificazione);

		when(promo.getDataInizio()).thenReturn(new Date(System.currentTimeMillis()));
		when(promo.getDataFine()).thenReturn(null);
		assertTrue(service.isLockable(setPianificazione));
		verify(service, times(2)).isLockable(setPianificazione);
	}

	@Test
	public void shouldNotLockableBeforeBothDates() {
		PromozioneTestataEntity mock = mock(PromozioneTestataEntity.class);
		when(mock.getDataInizio()).thenReturn(
				Date.from(LocalDate.now().minusDays(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		when(mock.getDataFine()).thenReturn(
				Date.from(LocalDate.now().minusDays(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

		CfgSetPianificazioneEntity setPianificazione = mock(CfgSetPianificazioneEntity.class);
		Mockito.when(setPianificazione.getPromozioneTestataEntities())
				.thenReturn(Stream.of(mock).collect(Collectors.toSet()));
		setPianificazione.setPromozioneTestataEntities(Stream.of(mock).collect(Collectors.toSet()));
		final boolean lockable = service.isLockable(setPianificazione);
		assertTrue(lockable);
		verify(service, times(1)).isLockable(setPianificazione);
	}
}
