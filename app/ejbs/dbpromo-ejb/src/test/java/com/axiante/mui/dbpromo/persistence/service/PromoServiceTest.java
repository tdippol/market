package com.axiante.mui.dbpromo.persistence.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CategoriaDAO;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.FornitoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.NegozioDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.dao.ResponsabileDAO;
import com.axiante.mui.dbpromo.persistence.dao.TipoNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.PromoServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoServiceTest {

	@Mock
	private PromozioneTestataDAO promozioneTestataDAO;

	@Mock
	private ResponsabileDAO responsabileDAO;

	@Mock
	private CompratoreDAO compratoreDAO;

	@Mock
	private TipoNegozioDAO tipoNegozioDAO;

	@Mock
	private CategoriaDAO categoriaDAO;

	@Mock
	private FornitoreDAO fornitoreDAO;

	@Mock
	private NegozioDAO negozioDAO;

	@Spy
	@InjectMocks
	private PromoServiceImpl service;

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void testPersistPromozione() throws Exception {
		final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
		service.persist(entity, "junit_test");
		verify(promozioneTestataDAO).persistWithAuditLog(entity, "junit_test");
	}

	@Test
	public void persist_givenNullPromozione_shouldThrowException() throws Exception {
		ex.expect(NullPointerException.class);
		service.persist((PromozioneTestataEntity) null, "junit_test");
	}

	@Test
	public void testRead() throws Exception {
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		service.read(canali);
		verify(promozioneTestataDAO).findAllSecured(canali);
	}

	@Test
	public void testPersistList() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final List<PromozioneTestataEntity> entities = new ArrayList<>();
		entities.add(testata);
		service.persist(entities, "junit_test");
		verify(promozioneTestataDAO).persist(entities);
	}

	@Test
	public void persist_givenNullPromozioni_shouldThrowException() throws Exception {
		ex.expect(NullPointerException.class);
		service.persist((List<PromozioneTestataEntity>) null, "junit_test");
	}

	@Test
	public void testFindAllManager() {
		service.findAllManager();
		verify(responsabileDAO).findAll();
	}

	@Test
	public void testFindCompratoreEntityById() throws Exception {
		final Long compratoreId = 1L;
		service.findCompratoreEntityById(compratoreId);
		verify(compratoreDAO).findById(compratoreId);
	}

	@Test
	public void testFindAllBuyers() {
		service.findAllBuyers();
		verify(compratoreDAO).findAllOrderedBy();
	}

	@Test
	public void testFindAllBuyersByCodesThrowsNullPointerExceptionWhenListIsNull() {
		ex.expect(NullPointerException.class);
		service.findAllBuyersByCodes(null);
	}
	@Test
	public void testFindAllBuyersByCodesReturnsEmptyListWhenListIsEmpty() {
		assertThat(service.findAllBuyersByCodes(Collections.emptyList()).isEmpty(), is(true));
	}
	@Test
	public void testFindAllBuyersByCodes(){
		List<String> codes = Collections.singletonList("code");
		service.findAllBuyersByCodes(codes);
		verify(compratoreDAO).findAllByCodes(codes);
	}

	@Test
	public void testFindAllBuyersByIdItemsReturnsEmptyListWhenListIsEmpty() {
		assertThat(service.findAllBuyersByIdItems(Collections.emptyList()).isEmpty(), is(true));
	}
	@Test
	public void testFindAllBuyersByIdItemsThrowsNullPointerExceptionWhenListIsNull() {
		ex.expect(NullPointerException.class);
		service.findAllBuyersByIdItems(null);
	}

	@Test
	public void testFindAllBuyersByIdItems(){
		List<Long> ids = Collections.singletonList(1L);
		service.findAllBuyersByIdItems(ids);
		verify(compratoreDAO).findAllByIdItems(ids);
	}

	@Test
	public void testRemoveThrowsRuntimeException(){
		ex.expect(RuntimeException.class);
		service.remove(null);
		service.remove(new PromozioneTestataEntity());
	}

	@Test
	public void testFindAllCategorieByCodiciCompratoreThrowsNullPointerExceptionWhenListIsNull() {
		ex.expect(NullPointerException.class);
		service.findAllCategorieByCodiciCompratore(null);
	}
	@Test
	public void testFindAllCategorieByCodiciCompratoreReturnsEmptyListWhenListIsEmpty() {
		assertThat(service.findAllCategorieByCodiciCompratore(Collections.emptyList()).isEmpty(), is(true));
	}

	@Test
	public void testFindAllCategorieByCodiciCompratore(){
		List<String> codes = Collections.singletonList("code");
		service.findAllCategorieByCodiciCompratore(codes);
		verify(categoriaDAO).findAllByCodiciCompratore(codes);
	}

	@Test
	public void testFindAllFornitoriByCodiciCompratoreThrowsNullPointerExceptionWhenListIsNull() {
		ex.expect(NullPointerException.class);
		service.findAllFornitoriByCodiciCompratore(null);
	}
	@Test
	public void testFindAllFornitoriByCodiciCompratoreReturnsEmptyListWhenListIsEmpty() {
		assertThat(service.findAllFornitoriByCodiciCompratore(Collections.emptyList()).isEmpty(), is(true));
	}
	@Test
	public void testFindAllFornitoriByCodiciCompratore(){
		List<String> codes = Collections.singletonList("code");
		service.findAllFornitoriByCodiciCompratore(codes);
		verify(fornitoreDAO).findAllByCodiciCompratore(codes);
	}

	@Test
	public void testRunFunctionCheckStatusTransictionThrowsExceptionWhenFuncionThrowsIt(){
		try {
			when(service.getDao()).thenReturn(promozioneTestataDAO);
			doThrow(new RuntimeException()).when(promozioneTestataDAO).runFunctionCheckStatusTransiction(anyLong(), anyString(), anyLong());
			service.runFunctionCheckStatusTransiction(1L, "status", 1L);
			fail("Expected exception");
		} catch (Exception ignored) {
		}
	}

	@Test
	public void testRunFunctionCheckStatusTransiction(){
		when(service.getDao()).thenReturn(promozioneTestataDAO);
		try {
			service.runFunctionCheckStatusTransiction(1L, "status", 1L);
			verify(promozioneTestataDAO).runFunctionCheckStatusTransiction(1L, "status", 1L);
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}

	@Test
	public void testRunFunctionExportPianificazioniThrowsExceptionWhenFuncionThrowsIt(){
		try {
			when(service.getDao()).thenReturn(promozioneTestataDAO);
			doThrow(new RuntimeException()).when(promozioneTestataDAO).runFunctionExportPianificazioni(anyLong(), anyString(), anyString(),anyInt());
			service.runFunctionExportPianificazioni(1L, "compratore", "username", 1);
			fail("Expected exception");
		} catch (Exception ignored) {
		}
	}

	@Test
	public void testRunFunctionExportPianificazioni(){
		when(service.getDao()).thenReturn(promozioneTestataDAO);
		try {
			service.runFunctionExportPianificazioni(1L, "compratore", "username", 1);
			verify(promozioneTestataDAO).runFunctionExportPianificazioni(1L, "compratore", "username", 1);
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}

	@Test
	public void testRunFunctionCalcolaSovrapposizioniThrowsExceptionWhenFuncionThrowsIt(){
		try {
			when(service.getDao()).thenReturn(promozioneTestataDAO);
			doThrow(new RuntimeException()).when(promozioneTestataDAO).runFunctionCalcolaSovrapposizioni(anyLong(), anyString(), anyInt());
			service.runFunctionCalcolaSovrapposizioni(1L,  "username", 1);
			fail("Expected exception");
		} catch (Exception ignored) {
		}
	}

	@Test
	public void testRunFunctionCalcolaSovrapposizioni(){
		when(service.getDao()).thenReturn(promozioneTestataDAO);
		try {
			service.runFunctionCalcolaSovrapposizioni(1L,  "username", 1);
			verify(promozioneTestataDAO).runFunctionCalcolaSovrapposizioni(1L,  "username", 1);
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}

	@Test
	public void testFindAllTipoConsegna(){
		service.findAllTipoConsegna();
		verify(negozioDAO).findAllTipoConsegna();
	}

	@Test
	public void testFindAllDistinctZone(){
		service.findAllDistinctZone();
		verify(negozioDAO).findAllDistinctZone();
	}

	@Test
	public void testFindAllDistinctCedi(){
		service.findAllDistinctCedi();
		verify(negozioDAO).findAllDistinctCedi();
	}

	@Test
	public void whenFindTipoElementoHasNoTestataThenReturnEmptyString() {
		final String expected = "";
		final Long id = 123L;
		when(promozioneTestataDAO.findById(id)).thenReturn(null);
		assertThat(service.findTipoElemento(id), equalTo(expected));
		verify(promozioneTestataDAO, times(1)).findById(id);
		verify(service, times(1)).findTipoElemento(id);
	}

	@Test
	public void whenFindTipoElementoHasNoSetConfigurazioniThenReturnEmptyString() {
		final String expected = "";
		final Long id = 123L;
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(promozioneTestataDAO.findById(id)).thenReturn(testata);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(null);
		assertThat(service.findTipoElemento(id), equalTo(expected));
		verify(promozioneTestataDAO, times(1)).findById(id);
		verify(service, times(1)).findTipoElemento(id);
	}

	@Test
	public void whenFindTipoElementoHasNoPianificazioneThenReturnEmptyString() {
		final String expected = "";
		final Long id = 123L;
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CfgSetPianificazioneEntity pianificazione = mock(CfgSetPianificazioneEntity.class);

		when(promozioneTestataDAO.findById(id)).thenReturn(testata);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(pianificazione);

		assertThat(service.findTipoElemento(id), equalTo(expected));
		verify(promozioneTestataDAO, times(1)).findById(id);
		verify(service, times(1)).findTipoElemento(id);
	}

	@Test
	public void whenFindTipoElementoHasPianificazioneThenReturnSomething() {
		final String expected = "expected";
		final Long id = 123L;
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CfgSetPianificazioneEntity pianificazione = mock(CfgSetPianificazioneEntity.class);
		final CfgPianificazioneEntity piano = mock(CfgPianificazioneEntity.class);
		final CfgConfHeaderEntity header = mock(CfgConfHeaderEntity.class);
		when(piano.getLista()).thenReturn(expected);
		when(promozioneTestataDAO.findById(id)).thenReturn(testata);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(pianificazione);
		when(pianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(header));
		when(header.getMuiCfgPianificaziones()).thenReturn(Collections.singleton(piano));
		assertThat(service.findTipoElemento(id), equalTo(expected));
		verify(promozioneTestataDAO, times(1)).findById(id);
		verify(service, times(1)).findTipoElemento(id);
	}

	@Test
	public void whenFindTipoElementoHasPianificazioneThenReturnEmpty() {
		final Long id = 123L;
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CfgSetPianificazioneEntity pianificazione = mock(CfgSetPianificazioneEntity.class);
		when(promozioneTestataDAO.findById(id)).thenReturn(testata);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(pianificazione);
		assertTrue(service.findTipoElemento(id).isEmpty());
		verify(promozioneTestataDAO, times(1)).findById(id);
		verify(service, times(1)).findTipoElemento(id);
	}

	@Test
	public void findTipoElemento_whenWithoutCfgConfHeader_shouldReturnEmpty() {
		final Long id = 123L;
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
		when(promozioneTestataDAO.findById(id)).thenReturn(testata);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(null);
		assertTrue(service.findTipoElemento(id).isEmpty());
		verify(promozioneTestataDAO, times(1)).findById(id);
	}

	@Test(expected = RuntimeException.class)
	public void testRemovePromozioneTestata() throws Exception {
		final PromozioneTestataEntity e = mock(PromozioneTestataEntity.class);
		service.remove(e, "junit_test");
		verify(promozioneTestataDAO).remove(e);
	}

	@Test
	public void testFindPromozioneTestataByid() throws Exception {
		final Long id = 123L;
		service.findById(id);
		verify(promozioneTestataDAO).findById(id);
	}

	@Test
	public void testFindAllNotCancelled() throws Exception {
		final List<CanalePromozioneEntity> channels = new ArrayList<>();
		channels.add(mock(CanalePromozioneEntity.class));
		service.findAllNotCancelled(channels);
		verify(promozioneTestataDAO).findAllNotCancelled(channels);
	}

	@Test
	public void testFindAllTipoNegozioEntity() throws Exception {
		service.findAllTipoNegozioEntity();
		verify(tipoNegozioDAO).findAll();
	}

	@Test
	public void testFindShopTypeById() throws Exception {
		final Long shopTypeId = 123L;
		service.findShopTypeById(shopTypeId);
		verify(tipoNegozioDAO).findById(shopTypeId);
	}

	@Test
	public void testFindOverlappingEqualsDataInizioAndDataFine() throws Exception {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final List<PromozioneTestataEntity> promozioneTestataEntities = new ArrayList<>();
		promozioneTestataEntities.add(testata);
		final List<CanalePromozioneEntity> channels = new ArrayList<>();
		channels.add(mock(CanalePromozioneEntity.class));
		when(promozioneTestataDAO.findOverlappingPromo(testata, channels)).thenReturn(promozioneTestataEntities);
		final List<PromozioneTestataEntity> overlappingPromozioneTestataEntities = service.findOverlappingPromo(testata,
				channels);
		assertFalse(overlappingPromozioneTestataEntities.isEmpty());
		assertEquals(1, overlappingPromozioneTestataEntities.size());
		verify(promozioneTestataDAO, times(1)).findOverlappingPromo(testata, channels);
		verify(service, times(1)).findOverlappingPromo(testata, channels);
	}

	@Test
	public void findOverlappingByCodiciMeccanica_givenNullPromozione_shouldThrowException() {
		ex.expect(NullPointerException.class);
		service.findOverlappingByCodiciMeccanica(null, Arrays.asList("M141", "M142"));
		verify(promozioneTestataDAO, never()).findOverlappingByCodiciMeccanica(any(), anyList());
	}

	@Test
	public void findOverlappingByCodiciMeccanica_givenNullCodiciMeccanica_shouldThrowException() {
		ex.expect(NullPointerException.class);
		PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
		service.findOverlappingByCodiciMeccanica(promozione, null);
		verify(promozioneTestataDAO, never()).findOverlappingByCodiciMeccanica(any(), anyList());
	}

	@Test
	public void findOverlappingByCodiciMeccanica_givenEmptyCodiciMeccanica_shouldReturnEmptyList() {
		PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
		assertTrue(service.findOverlappingByCodiciMeccanica(promozione, Collections.emptyList()).isEmpty());
		verify(promozioneTestataDAO, never()).findOverlappingByCodiciMeccanica(any(), anyList());
	}

	@Test
	public void findOverlappingByCodiciMeccanica() {
		PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
		List<String> codiciMeccanica = Arrays.asList("M141", "M142");
		List<PromozioneTestataEntity> overlapping = Collections.singletonList(mock(PromozioneTestataEntity.class));
		when(promozioneTestataDAO.findOverlappingByCodiciMeccanica(promozione, codiciMeccanica))
				.thenReturn(overlapping);
		List<PromozioneTestataEntity> result = service.findOverlappingByCodiciMeccanica(promozione, codiciMeccanica);
		assertEquals(1, result.size());
		verify(promozioneTestataDAO, times(1))
				.findOverlappingByCodiciMeccanica(promozione, codiciMeccanica);
	}

	@Test
	public void findOverlappingByAnnoAndCodiceMeccanicaWithOffset_givenNullPromozione_shouldThrowException() {
		ex.expect(NullPointerException.class);
		service.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(null, null).isEmpty();
	}

	@Test
	public void findOverlappingByAnnoAndCodiceMeccanicaWithOffset_givenNullOrEmptyCodiciMeccanica_shouldReturnEmptyList() {
		PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
		assertTrue(service.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozione, null).isEmpty());
		assertTrue(service.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozione, Collections.emptyList()).isEmpty());
		verify(promozioneTestataDAO, never())
				.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(any(PromozioneTestataEntity.class), any(), anyInt(), anyInt());
	}

	@Test
	public void findOverlappingByAnnoAndCodiceMeccanicaWithOffset() {
		CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		when(canale.getOverlapOffsetStart()).thenReturn(5);
		when(canale.getOverlapOffsetEnd()).thenReturn(5);
		PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
		when(promozione.getCanalePromozioneEntity()).thenReturn(canale);
		List<String> codiciMeccanica = Arrays.asList("M141", "M142");
		List<PromozioneTestataEntity> overlapping = Collections.singletonList(mock(PromozioneTestataEntity.class));
		when(promozioneTestataDAO.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozione, codiciMeccanica, 5, 5))
				.thenReturn(overlapping);
		List<PromozioneTestataEntity> result = service.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozione,
				codiciMeccanica);
		assertEquals(1, result.size());
		verify(promozioneTestataDAO, times(1))
				.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozione, codiciMeccanica, 5, 5);
	}

	@Test
	public void testFindAllCategorie() {
		service.findAllCategorie();
		verify(categoriaDAO).findAll();
	}

	@Test
	public void testFindAllFornitori() {
		service.findAllFornitori();
		verify(fornitoreDAO).findAll();
	}

	@Test(expected = NullPointerException.class)
	public void testRemovePromozioneTestatayWithoutUserException() throws Exception {
		final PromozioneTestataEntity mock = mock(PromozioneTestataEntity.class);
		service.remove(mock, null);
		verify(service, times(1)).remove(mock, null);
	}

	@Test(expected = RuntimeException.class)
	public void testSavePromozionePianificazioneEntityWithUsernameWithoutEntityException() throws Exception {
		service.remove(null, "test");
		verify(service, times(1)).remove(null, "test");
	}

	@Test(expected = NullPointerException.class)
	public void testSavePromozionePianificazioneEntityWithoutParamsException() throws Exception {
		service.remove(null, null);
		verify(service, times(1)).remove(null, null);
	}

	@Test(expected = NullPointerException.class)
	public void testFindTipoElementWithoutParamsException() {
		service.findTipoElemento(null);
		verify(service, times(1)).findTipoElemento(null);
	}

	@Test
	public void testRunFunctionAccodamentoPubblicazioni() throws Exception {
		when(promozioneTestataDAO.runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata",
				Long.MIN_VALUE + 1)).thenReturn(true);
		final boolean result = service.runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata",
				Long.MIN_VALUE + 1);
		verify(promozioneTestataDAO, times(1)).runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata",
				Long.MIN_VALUE + 1);
		verify(service, times(1)).runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata",
				Long.MIN_VALUE + 1);
		assertTrue(result);
	}

	@Test(expected = Exception.class)
	public void testRunFunctionAccodamentoPubblicazioniException() throws Exception {
		when(promozioneTestataDAO.runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata",
				Long.MIN_VALUE + 1)).thenThrow(Exception.class);
		service.runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata", Long.MIN_VALUE + 1);
		verify(promozioneTestataDAO, times(1)).runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata",
				Long.MIN_VALUE + 1);
		verify(service, times(1)).runFunctionAccodamentoPubblicazioni(Long.MIN_VALUE, "codInsTestata",
				Long.MIN_VALUE + 1);
	}

	@Test
	public void testFindAllNotCancelledFilteredEmpty() throws Exception {
		final List<CanalePromozioneEntity> channels = new ArrayList<>();
		channels.add(mock(CanalePromozioneEntity.class));
		@SuppressWarnings("unchecked")
		final Map<String, String> userFilters = mock(HashMap.class);
		when(userFilters.isEmpty()).thenReturn(true);
		when(promozioneTestataDAO.findAllNotCancelled(channels)).thenReturn(new ArrayList<>());
		final List<PromozioneTestataEntity> testate = service.findAllNotCancelled(userFilters, channels);
		verify(promozioneTestataDAO, times(1)).findAllNotCancelled(channels);
		verify(service, times(1)).findAllNotCancelled(userFilters, channels);
		assertTrue(testate.isEmpty());
	}

	@Test
	public void testFindAllNotCancelledFiltered() throws Exception {
		@SuppressWarnings("unchecked")
		final Map<String, String> mock = mock(HashMap.class);
		final PromozioneTestataEntity mockPromozione = mock(PromozioneTestataEntity.class);
		final List<CanalePromozioneEntity> channels = new ArrayList<>();
		channels.add(mock(CanalePromozioneEntity.class));
		when(mockPromozione.getId()).thenReturn(Long.MIN_VALUE);
		when(mock.isEmpty()).thenReturn(false);
		when(promozioneTestataDAO.findAllNotCancelled(mock, channels)).thenReturn(Collections.singletonList(mockPromozione));
		final List<PromozioneTestataEntity> testate = service.findAllNotCancelled(mock, channels);
		verify(promozioneTestataDAO, times(1)).findAllNotCancelled(mock, channels);
		verify(service, times(1)).findAllNotCancelled(mock, channels);
		assertEquals(mockPromozione.getId(), testate.get(0).getId());
	}

	@Test(expected = Exception.class)
	public void testFindAllNotCancelledFilteredEmptyException() throws Exception {
		final List<CanalePromozioneEntity> channels = new ArrayList<>();
		channels.add(mock(CanalePromozioneEntity.class));
		when(promozioneTestataDAO.findAllNotCancelled(channels)).thenThrow(Exception.class);
		service.findAllNotCancelled(new HashMap<>(), channels);
		verify(promozioneTestataDAO, times(1)).findAllNotCancelled(channels);
		verify(service, times(1)).findAllNotCancelled(channels);
	}

	@Test(expected = Exception.class)
	public void testFindAllNotCancelledFilteredException() throws Exception {
		final List<CanalePromozioneEntity> channels = new ArrayList<>();
		channels.add(mock(CanalePromozioneEntity.class));
		when(promozioneTestataDAO.findAllNotCancelled(new HashMap<>(), channels)).thenThrow(Exception.class);
		service.findAllNotCancelled(new HashMap<>(), channels);
		verify(promozioneTestataDAO, times(1)).findAllNotCancelled(new HashMap<>(), channels);
		verify(service, times(1)).findAllNotCancelled(new HashMap<>(), channels);
	}

	@Test
	public void findAllByAnnoAndCanale() {
		CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		PromozioneTestataEntity testata1 = mock(PromozioneTestataEntity.class);
		List<PromozioneTestataEntity> promozioni = new ArrayList<>();
		promozioni.add(testata1);
		when(promozioneTestataDAO.findAllByAnnoAndCanale("2024", canale)).thenReturn(promozioni);
		List<PromozioneTestataEntity> result = service.findAllByAnnoAndCanale("2024", canale);
		assertEquals(1, result.size());
		assertTrue(result.contains(testata1));
		verify(promozioneTestataDAO, times(1)).findAllByAnnoAndCanale("2024", canale);
	}
}
