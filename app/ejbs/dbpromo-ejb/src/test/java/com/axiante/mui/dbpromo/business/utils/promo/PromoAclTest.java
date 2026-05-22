package com.axiante.mui.dbpromo.business.utils.promo;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromoAclTest {

	@Mock
	private PromozioneTestataEntity testata;

	@Mock
	private PromozioneStatoEntity promoStato;

	@Before
	public void setUp() {
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
	}

	@Test(expected = NullPointerException.class)
	public void isDeletable_givenNullTestata_shouldThrowException() {
		PromoAcl.isDeletable((PromozioneTestataEntity) null);
	}

	@Test
	public void isDeletable_givenTestataWithoutStato_shouldReturnFalse() {
		mockTestataWithoutStato();
		assertFalse(PromoAcl.isDeletable(testata));
	}

	@Test
	public void isDeletable_givenTestataWithStato31or311or400or410or500_shouldReturnFalse_otherwiseTrue() {
		// Not deletable: 31 | 311 | 400 | 410 | 500
		mockStatoPromo("31");
		assertFalse(PromoAcl.isDeletable(testata));
		mockStatoPromo("311");
		assertFalse(PromoAcl.isDeletable(testata));
		mockStatoPromo("400");
		assertFalse(PromoAcl.isDeletable(testata));
		mockStatoPromo("410");
		assertFalse(PromoAcl.isDeletable(testata));
		mockStatoPromo("500");
		assertFalse(PromoAcl.isDeletable(testata));
		// Deletable: 10 | 30
		mockStatoPromo("10");
		assertTrue(PromoAcl.isDeletable(testata));
		mockStatoPromo("30");
		assertTrue(PromoAcl.isDeletable(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isClosed_givenNullTestata_shouldThrowException() {
		PromoAcl.isClosed((PromozioneTestataEntity) null);
	}

	@Test
	public void isClosed_givenTestataWithoutStato_shouldReturnFalse() {
		mockTestataWithoutStato();
		assertFalse(PromoAcl.isClosed(testata));
	}

	@Test
	public void isClosed_givenTestataWithStato500_shouldReturnTrue_otherwiseFalse() {
		mockStatoPromo("500");
		assertTrue(PromoAcl.isClosed(testata));
		mockStatoPromo("10");
		assertFalse(PromoAcl.isClosed(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isEditable_givenNullTestata_shouldThrowException() {
		PromoAcl.isEditable((PromozioneTestataEntity) null);
	}

	@Test
	public void isEditable_givenTestataWithoutStato_shouldReturnFalse() {
		mockTestataWithoutStato();
		assertFalse(PromoAcl.isEditable(testata));
	}

	@Test
	public void isEditable_givenTestataWithStato10or30or31or311_shouldReturnTrue_otherwiseFalse() {
		mockStatoPromo("10");
		assertTrue(PromoAcl.isEditable(testata));
		mockStatoPromo("30");
		assertTrue(PromoAcl.isEditable(testata));
		mockStatoPromo("31");
		assertTrue(PromoAcl.isEditable(testata));
		mockStatoPromo("311");
		assertTrue(PromoAcl.isEditable(testata));
		mockStatoPromo("35");
		assertTrue(PromoAcl.isEditable(testata));
		mockStatoPromo("415");
		assertTrue(PromoAcl.isEditable(testata));
		mockStatoPromo("500");
		assertFalse(PromoAcl.isEditable(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isEditableDescription_givenNullStato_shouldThrowException() {
		PromoAcl.isEditableDescription(null);
	}

	@Test
	public void isEditable_givenStato10or30or311_shouldReturnTrue_otherwiseFalse() {
		// Editable: 10 | 30 | 311
		StatoPromozioneEntity statoPromo = mockStatoPromo("10");
		assertTrue(PromoAcl.isEditableDescription(statoPromo));
		statoPromo = mockStatoPromo("30");
		assertTrue(PromoAcl.isEditableDescription(statoPromo));
		statoPromo = mockStatoPromo("311");
		assertTrue(PromoAcl.isEditableDescription(statoPromo));
		// Not editable: 31 | 400 | 410 | 500
		statoPromo = mockStatoPromo("31");
		assertFalse(PromoAcl.isEditableDescription(statoPromo));
		statoPromo = mockStatoPromo("400");
		assertFalse(PromoAcl.isEditableDescription(statoPromo));
		statoPromo = mockStatoPromo("410");
		assertFalse(PromoAcl.isEditableDescription(statoPromo));
		statoPromo = mockStatoPromo("500");
		assertFalse(PromoAcl.isEditableDescription(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isEditableStartDate_givenNullStato_shouldThrowException() {
		PromoAcl.isEditableStartDate(null);
	}

	@Test
	public void isEditableStartDate_givenStato10or30or311_shouldReturnTrue_otherwiseFalse() {
		// Editable: 10 | 30 | 311
		StatoPromozioneEntity statoPromo = mockStatoPromo("10");
		assertTrue(PromoAcl.isEditableStartDate(statoPromo));
		statoPromo = mockStatoPromo("30");
		assertTrue(PromoAcl.isEditableStartDate(statoPromo));
		statoPromo = mockStatoPromo("311");
		assertTrue(PromoAcl.isEditableStartDate(statoPromo));
		// Not editable: 31 | 400 | 410 | 500
		statoPromo = mockStatoPromo("31");
		assertFalse(PromoAcl.isEditableStartDate(statoPromo));
		statoPromo = mockStatoPromo("400");
		assertFalse(PromoAcl.isEditableStartDate(statoPromo));
		statoPromo = mockStatoPromo("410");
		assertFalse(PromoAcl.isEditableStartDate(statoPromo));
		statoPromo = mockStatoPromo("500");
		assertFalse(PromoAcl.isEditableStartDate(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isEditableEndDate_givenNullStato_shouldThrowException() {
		PromoAcl.isEditableEndDate(null);
	}

	@Test
	public void isEditableEndDate_givenStato10or30or311or410_shouldReturnTrue_otherwiseFalse() {
		// Editable: 10 | 30 | 311 | 410 | 415 | 35
		StatoPromozioneEntity statoPromo = mockStatoPromo("10");
		assertTrue(PromoAcl.isEditableEndDate(statoPromo));
		statoPromo = mockStatoPromo("30");
		assertTrue(PromoAcl.isEditableEndDate(statoPromo));
		statoPromo = mockStatoPromo("311");
		assertTrue(PromoAcl.isEditableEndDate(statoPromo));
		statoPromo = mockStatoPromo("410");
		assertTrue(PromoAcl.isEditableEndDate(statoPromo));
		statoPromo = mockStatoPromo("415");
		assertTrue(PromoAcl.isEditableEndDate(statoPromo));
		statoPromo = mockStatoPromo("35");
		assertTrue(PromoAcl.isEditableEndDate(statoPromo));
		// Not editable: 31 | 400 | 500
		statoPromo = mockStatoPromo("31");
		assertFalse(PromoAcl.isEditableEndDate(statoPromo));
		statoPromo = mockStatoPromo("400");
		assertFalse(PromoAcl.isEditableEndDate(statoPromo));
		statoPromo = mockStatoPromo("500");
		assertFalse(PromoAcl.isEditableEndDate(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isEditableNotes_givenNullStato_shouldThrowException() {
		PromoAcl.isEditableNotes(null);
	}

	@Test
	public void isEditableNotes_givenStatoOtherThan500_shouldReturnTrue_otherwiseFalse() {
		// Editable: 10 | 30 | 31| 311 | 400 | 410
		StatoPromozioneEntity statoPromo = mockStatoPromo("10");
		assertTrue(PromoAcl.isEditableNotes(statoPromo));
		statoPromo = mockStatoPromo("30");
		assertTrue(PromoAcl.isEditableNotes(statoPromo));
		statoPromo = mockStatoPromo("31");
		assertTrue(PromoAcl.isEditableNotes(statoPromo));
		statoPromo = mockStatoPromo("311");
		assertTrue(PromoAcl.isEditableNotes(statoPromo));
		statoPromo = mockStatoPromo("400");
		assertTrue(PromoAcl.isEditableNotes(statoPromo));
		statoPromo = mockStatoPromo("410");
		assertTrue(PromoAcl.isEditableNotes(statoPromo));
		// Not editable: 500
		statoPromo = mockStatoPromo("500");
		assertFalse(PromoAcl.isEditableNotes(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isSbloccoEsecuzione_givenNullTestata_shouldThrowException() {
		PromoAcl.isSbloccoEsecuzione((PromozioneTestataEntity) null);
	}

	@Test
	public void isSbloccoEsecuzione_givenTestataWithoutStato_shouldReturnFalse() {
		mockTestataWithoutStato();
		assertFalse(PromoAcl.isSbloccoEsecuzione(testata));
	}

	@Test
	public void isSbloccoEsecuzione_givenTestataWithStato410_shouldReturnTrue_otherwiseFalse() {
		mockStatoPromo("410");
		assertTrue(PromoAcl.isSbloccoEsecuzione(testata));
		mockStatoPromo("10");
		assertFalse(PromoAcl.isSbloccoEsecuzione(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isCreated_givenNullTestata_shouldThrowException() {
		PromoAcl.isCreated((PromozioneTestataEntity) null);
	}

	@Test
	public void isCreated_givenTestataWithoutStato_shouldReturnFalse() {
		mockTestataWithoutStato();
		assertFalse(PromoAcl.isCreated(testata));
	}

	@Test
	public void isCreated_givenTestataWithStato10_shouldReturnTrue_otherwiseFalse() {
		mockStatoPromo("10");
		assertTrue(PromoAcl.isCreated(testata));
		mockStatoPromo("500");
		assertFalse(PromoAcl.isCreated(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isPromoInEsecuzione_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isPromoInEsecuzione(null);
	}

	@Test
	public void isPromoInEsecuzione_givenTestataWithStato400or410_shouldReturnTrue_otherwiseFalse() {
		StatoPromozioneEntity statoPromo = mockStatoPromo("400");
		assertTrue(PromoAcl.isPromoInEsecuzione(statoPromo));
		statoPromo = mockStatoPromo("410");
		assertTrue(PromoAcl.isPromoInEsecuzione(statoPromo));
		statoPromo = mockStatoPromo("10");
		assertFalse(PromoAcl.isPromoInEsecuzione(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isClosed_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isClosed((StatoPromozioneEntity) null);
	}

	@Test(expected = NullPointerException.class)
	public void isEditable_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isEditable((StatoPromozioneEntity) null);
	}

	@Test(expected = NullPointerException.class)
	public void isDeletable_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isDeletable((StatoPromozioneEntity) null);
	}

	@Test(expected = NullPointerException.class)
	public void isShopEditable_givenNullTestata_shouldThrowException() {
		PromoAcl.isShopEditable(null);
	}

	@Test
	public void isShopEditable_givenTestataWithStato10or30or311or410_shouldReturnTrue_otherwiseFalse() {
		mockStatoPromo("10");
		assertTrue(PromoAcl.isShopEditable(testata));
		mockStatoPromo("30");
		assertTrue(PromoAcl.isShopEditable(testata));
		mockStatoPromo("35");
		assertTrue(PromoAcl.isShopEditable(testata));
		mockStatoPromo("415");
		assertTrue(PromoAcl.isShopEditable(testata));
		mockStatoPromo("311");
		assertTrue(PromoAcl.isShopEditable(testata));
		mockStatoPromo("410");
		assertTrue(PromoAcl.isShopEditable(testata));
		mockStatoPromo("40");
		assertFalse(PromoAcl.isShopEditable(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isSbloccoEsecuzione_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isSbloccoEsecuzione((StatoPromozioneEntity) null);
	}

	@Test(expected = NullPointerException.class)
	public void isCreated_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isCreated((StatoPromozioneEntity) null);
	}

	@Test(expected = NullPointerException.class)
	public void getStatoCorrente_givenNullTestata_shouldThrowException() {
		PromoAcl.getStatoCorrente(null);
	}

	@Test(expected = NullPointerException.class)
	public void getCodiceStatoPromozione_givenNullTestata_shouldThrowException() {
		PromoAcl.getCodiceStatoPromozione(null);
	}

	@Test
	public void getCodiceStatoPromozione_givenTestataWithoutStato_shouldReturnNull() {
		mockTestataWithoutStato();
		assertNull(PromoAcl.getCodiceStatoPromozione(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isBuyerSelectEnabled_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isBuyerSelectEnabled(null);
	}

	@Test
	public void isBuyerSelectEnabled_givenStatoPromo10or30or311_shouldReturnTrue_otherwiseFalse() {
		StatoPromozioneEntity statoPromo = mockStatoPromo("10");
		assertTrue(PromoAcl.isBuyerSelectEnabled(statoPromo));
		statoPromo = mockStatoPromo("30");
		assertTrue(PromoAcl.isBuyerSelectEnabled(statoPromo));
		statoPromo = mockStatoPromo("311");
		assertTrue(PromoAcl.isBuyerSelectEnabled(statoPromo));
		statoPromo = mockStatoPromo("400");
		assertFalse(PromoAcl.isBuyerSelectEnabled(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isCfgPianificazioneEditable_givenNullCfgSet_shouldThrowException() {
		PromoAcl.isCfgPianificazioneEditable(null);
	}

	@Test
	public void isCfgPianificazioneEditable_givenCfgSetWithLockedFlag_shouldReturnTrue() {
		final CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione((long) 1);
		assertTrue(PromoAcl.isCfgPianificazioneEditable(cfgSetPianificazione));
	}

	@Test
	public void isCfgPianificazioneEditable_givenCfgSetWithNullLockedFlagAndWithoutTestate_shouldReturnTrue() {
		final CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione(null);
		when(cfgSetPianificazione.getPromozioneTestataEntities()).thenReturn(null);
		assertTrue(PromoAcl.isCfgPianificazioneEditable(cfgSetPianificazione));
	}

	@Test
	public void isCfgPianificazioneEditable_givenCfgSetWithNullLockedFlagAndWithTestateAndTestateWithoutPianificazioni_shouldReturnTrue() {
		final CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione(null);
		final Set<PromozioneTestataEntity> testate = Collections.singleton(testata);
		when(cfgSetPianificazione.getPromozioneTestataEntities()).thenReturn(testate);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(Collections.emptySet());
		assertTrue(PromoAcl.isCfgPianificazioneEditable(cfgSetPianificazione));
	}

	@Test
	public void isCfgPianificazioneEditable_givenCfgSetWithNullLockedFlagAndWithTestateAndTestateWithPianificazioni_shouldReturnFalse() {
		final CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione(null);
		final Set<PromozioneTestataEntity> testate = Collections.singleton(testata);
		final PromozionePianificazioneEntity promoPianificazione = mock(PromozionePianificazioneEntity.class);
		when(cfgSetPianificazione.getPromozioneTestataEntities()).thenReturn(testate);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(Collections.singleton(promoPianificazione));
		assertFalse(PromoAcl.isCfgPianificazioneEditable(cfgSetPianificazione));
	}

	@Test(expected = NullPointerException.class)
	public void isMeccanicaCancellabile_givenNullMeccanica_shouldThrowException() {
		PromoAcl.isMeccanicaCancellabile(null, testata);
	}

	@Test(expected = NullPointerException.class)
	public void isMeccanicaCancellabile_givenNullTestata_shouldThrowException() {
		PromoAcl.isMeccanicaCancellabile(mock(MeccanicheEntity.class), null);
	}

	@Test
	public void isMeccanicaCancellabile_givenTestataWithMeccanicaInPromoPianificazione_shouldReturnFalse() {
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		final PromozionePianificazioneEntity promoPianificazione = mock(PromozionePianificazioneEntity.class);
		when(meccanica.getId()).thenReturn(42L);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(Collections.singleton(promoPianificazione));
		when(promoPianificazione.getMeccanicaEntity()).thenReturn(meccanica);
		assertFalse(PromoAcl.isMeccanicaCancellabile(meccanica, testata));
	}

	@Test
	public void isMeccanicaCancellabile_givenTestataWithoutMeccanicaInPromoPianificazione_shouldReturnTrue() {
		final MeccanicheEntity meccanica1 = mock(MeccanicheEntity.class);
		final MeccanicheEntity meccanica2 = mock(MeccanicheEntity.class);
		final PromozionePianificazioneEntity promoPianificazione = mock(PromozionePianificazioneEntity.class);
		when(meccanica1.getId()).thenReturn(1L);
		when(meccanica2.getId()).thenReturn(2L);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(Collections.singleton(promoPianificazione));
		when(promoPianificazione.getMeccanicaEntity()).thenReturn(meccanica1);
		assertTrue(PromoAcl.isMeccanicaCancellabile(meccanica2, testata));
	}


	@Test
	public void getStatoCorrente_givenTestataWithPromozioniWithEndedStatus_shouldReturnNull() {
		mockTestataWithoutStato();
		assertNull(PromoAcl.getStatoCorrente(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isCancelled_givenNullStatoPromo_shouldThrowException() {
		PromoAcl.isCancelled(null);
	}

	@Test
	public void isCancelled_givenStatoPromo00_shouldReturnTrue_otherwiseFalse() {
		StatoPromozioneEntity statoPromo = mockStatoPromo("00");
		assertTrue(PromoAcl.isCancelled(statoPromo));
		statoPromo = mockStatoPromo("10");
		assertFalse(PromoAcl.isCancelled(statoPromo));
		statoPromo = mockStatoPromo("30");
		assertFalse(PromoAcl.isCancelled(statoPromo));
		statoPromo = mockStatoPromo("31");
		assertFalse(PromoAcl.isCancelled(statoPromo));
		statoPromo = mockStatoPromo("311");
		assertFalse(PromoAcl.isCancelled(statoPromo));
		statoPromo = mockStatoPromo("400");
		assertFalse(PromoAcl.isCancelled(statoPromo));
		statoPromo = mockStatoPromo("410");
		assertFalse(PromoAcl.isCancelled(statoPromo));
		statoPromo = mockStatoPromo("500");
		assertFalse(PromoAcl.isCancelled(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isShopCodiceMeccanicaEditable_givenNullTestata_shouldThrowException() {
		PromoAcl.isShopCodiceMeccanicaEditable(null);
	}

	@Test
	public void isShopCodiceMeccanicaEditable_whenFlDifferenziazioneNegoziFalse_shouldReturnFalse() {
		final PromozioneTestataEntity testata = mockTestata(false, false);
		assertFalse(PromoAcl.isShopCodiceMeccanicaEditable(testata));
	}

	@Test
	public void isShopCodiceMeccanicaEditable_whenFlDifferenziazioneNegoziTrueAndFlDifferenziazioneMeccanicaFalse_shouldReturnFalse() {
		final PromozioneTestataEntity testata = mockTestata(true, false);
		assertFalse(PromoAcl.isShopCodiceMeccanicaEditable(testata));
	}

	@Test
	public void isShopCodiceMeccanicaEditable_whenFlDifferenziazioneNegoziTrueAndFlDifferenziazioneMeccanicaTrue_shouldReturnTrue() {
		final PromozioneTestataEntity testata = mockTestata(true, true);
		assertTrue(PromoAcl.isShopCodiceMeccanicaEditable(testata));
	}

	@Test(expected = NullPointerException.class)
	public void isEditableValorePunto_givenNullTestata_shouldThrowException() {
		PromoAcl.isEditableValorePunto((PromozioneTestataEntity) null);
	}

	@Test
	public void isEditableValorePunto_givenClosedTestata_shouldReturnFalse_otherwiseTrue() {
		StatoPromozioneEntity statoPromo10 = mockStatoPromo("10");
		PromozioneStatoEntity promoStato1 = mock(PromozioneStatoEntity.class);
		PromozioneTestataEntity testata1 = mock(PromozioneTestataEntity.class);
		when(promoStato1.getStatoPromozioneEntity()).thenReturn(statoPromo10);
		when(testata1.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato1));
		assertTrue(PromoAcl.isEditableValorePunto(testata1));
		StatoPromozioneEntity statoPromo500 = mockStatoPromo("500");
		PromozioneStatoEntity promoStato2 = mock(PromozioneStatoEntity.class);
		PromozioneTestataEntity testata2 = mock(PromozioneTestataEntity.class);
		when(promoStato2.getStatoPromozioneEntity()).thenReturn(statoPromo500);
		when(testata2.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato2));
		assertFalse(PromoAcl.isEditableValorePunto(testata2));
	}

	@Test(expected = NullPointerException.class)
	public void isEditableValorePunto_givenNullStato_shouldThrowException() {
		PromoAcl.isEditableValorePunto((StatoPromozioneEntity) null);
	}

	@Test
	public void isEditableValorePunto_givenClosedStato_shouldReturnFalse_otherwiseTrue() {
		StatoPromozioneEntity statoPromo = mockStatoPromo("10");
		assertTrue(PromoAcl.isEditableValorePunto(statoPromo));
		statoPromo = mockStatoPromo("500");
		assertFalse(PromoAcl.isEditableValorePunto(statoPromo));
	}

	@Test(expected = NullPointerException.class)
	public void isRepartoCancellabile_givenNullReparto_shouldThrowException() {
		PromoAcl.isRepartoCancellabile(null, testata);
	}

	@Test(expected = NullPointerException.class)
	public void isRepartoCancellabile_givenNullTestata_shouldReturnException() {
		final RepartoEntity reparto = mock(RepartoEntity.class);
		PromoAcl.isRepartoCancellabile(reparto, null);
	}

	@Test
	public void isRepartoCancellabile_alwaysReturnTrue() {
		final RepartoEntity reparto = mock(RepartoEntity.class);
		assertTrue(PromoAcl.isRepartoCancellabile(reparto, testata));
	}

	@Test(expected = NullPointerException.class)
	public void isActive_givenNullTestata_shouldThrowException() {
		PromoAcl.isActive((PromozioneTestataEntity) null);
	}

	@Test(expected = NullPointerException.class)
	public void isActive_givenNullStato_shouldThrowException() {
		PromoAcl.isActive((StatoPromozioneEntity) null);
	}

	@Test
	public void isActive_givenCancelledTestata_shouldReturnFalse() {
		StatoPromozioneEntity statoPromo = mockStatoPromo("00");
		PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
		PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(promoStato.getStatoPromozioneEntity()).thenReturn(statoPromo);
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
		assertFalse(PromoAcl.isActive(testata));
	}

	@Test
	public void isActive_givenClosedTestata_shouldReturnFalse() {
		StatoPromozioneEntity statoPromo = mockStatoPromo("500");
		PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
		PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(promoStato.getStatoPromozioneEntity()).thenReturn(statoPromo);
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
		assertFalse(PromoAcl.isActive(testata));
	}

	@Test
	public void isActive_givenNotCancelledOrClosedTestata_shouldReturnFalse() {
		Stream.of("10", "30", "31", "35", "311", "400", "415").forEach(code -> {
			StatoPromozioneEntity statoPromo = mockStatoPromo(code);
			PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
			PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
			when(promoStato.getStatoPromozioneEntity()).thenReturn(statoPromo);
			when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
			assertTrue(PromoAcl.isActive(testata));
		});
	}

	private PromozioneTestataEntity mockTestata(boolean flDifferenziazioneNegozi, boolean flDifferenziazioneMeccanica) {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		when(canale.getFlDifferenziazioneNegozi()).thenReturn(flDifferenziazioneNegozi);
		when(testata.getMuiCanalePromozione()).thenReturn(canale);
		when(testata.getFlDifferenziazioneMeccanica()).thenReturn(flDifferenziazioneMeccanica);
		return testata;
	}

	private void mockTestataWithoutStato() {
		final Date date = new GregorianCalendar(2021, Calendar.SEPTEMBER, 1).getTime();
		final PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
		when(promoStato.getDataFineStato()).thenReturn(date);
	}

	private CfgSetPianificazioneEntity mockCfgSetPianificazione(Long locked) {
		final CfgSetPianificazioneEntity mock = mock(CfgSetPianificazioneEntity.class);
		when(mock.getLocked()).thenReturn(locked);
		return mock;
	}

	private StatoPromozioneEntity mockStatoPromo(String code) {
		final StatoPromozioneEntity mock = mock(StatoPromozioneEntity.class);
		when(mock.getCodiceStato()).thenReturn(code);
		when(promoStato.getStatoPromozioneEntity()).thenReturn(mock);
		return mock;
	}
}