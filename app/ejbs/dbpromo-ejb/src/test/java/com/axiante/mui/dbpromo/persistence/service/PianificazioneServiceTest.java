package com.axiante.mui.dbpromo.persistence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.GrmDAO;
import com.axiante.mui.dbpromo.persistence.dao.ItemDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.RepartoDAO;
import com.axiante.mui.dbpromo.persistence.dao.UploadFidatyDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.PianificazioneServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneServiceTest {

	@Mock
	private CfgPianificazioneDAO pianificazioneDAO;

	@Mock
	private CompratoreDAO compratoreDAO;

	@Mock
	private GrmDAO grmDAO;

	@Mock
	private ItemDAO itemDAO;

	@Mock
	private RepartoDAO repartoDAO;

	@Mock
	private PromozionePianificazioneDAO promozionePianificazioneDAO;

	@Mock
	private UploadFidatyDAO uploadFidatyDAO;

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Spy
	@InjectMocks
	PianificazioneServiceImpl service;

	@Test
	public void testFindAllCfgPianificazioneEntitiesByPromozione() {
		PromozioneTestataEntity promoTestataEntity = mock(PromozioneTestataEntity.class);
		CfgSetPianificazioneEntity setPianificazioneEntity = mock(CfgSetPianificazioneEntity.class);
		when(promoTestataEntity.getMuiCfgSetPianificazione()).thenReturn(setPianificazioneEntity);
		service.findAllCfgPianificazioneEntitiesByPromozione(promoTestataEntity);
		verify(pianificazioneDAO, times(0)).findAllByMuiCfgSetPianificazione(promoTestataEntity);
	}

	@Test
	public void testFindCompratoreById() {
		Long compratoreId = 123L;
		service.findCompratoreById(compratoreId);
		verify(compratoreDAO).findById(compratoreId);
	}

	@Test
	public void testGetAllGrmEntity() {
		service.getAllGrmEntity();
		verify(grmDAO).findAllOrderedBy();
	}

	@Test
	public void testGetAllItemsByCompratore() {
		CompratoreEntity mock = mock(CompratoreEntity.class);
		service.getAllItemsByCompratore(mock);
		verify(itemDAO).findAllByCompratore(mock);
	}

	@Test
	public void testGetAllReparti() {
		service.getAllReparti();
		verify(repartoDAO).findAllOrderedBy();
	}

	@Test
	public void testFindAllCfgPianificazioneEntitiesByPromozioneMeccanica() {
		PromozioneTestataEntity mock = mock(PromozioneTestataEntity.class);
		Long meccanicheEntityId = 123L;
		service.findAllCfgPianificazioneEntitiesByPromozioneMeccanica(mock, meccanicheEntityId);
		verify(pianificazioneDAO).findAllByPromoAndMeccanicaId(mock, meccanicheEntityId);
	}

	@Test
	public void testSavePromozionePianificazioneEntity() {
		PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
		service.savePromozionePianificazioneEntity(mock);
		verify(promozionePianificazioneDAO).persist(mock);
	}

	@Test
	public void whenSavePromozionePianificazioneEntityWithNullThenThrowNullPointerException() {
		ex.expect(NullPointerException.class);
		service.savePromozionePianificazioneEntity(null);
	}

	@Test
	public void testFindAllMasterPromozionePianificazioneEntityByPromozioneTestata() {
		PromozioneTestataEntity mock = mock(PromozioneTestataEntity.class);
		service.findAllMasterPromozionePianificazioneEntityByPromozioneTestata(mock);
		verify(promozionePianificazioneDAO).findAllMasterRowsByPromozioneTestata(mock);
	}

	@Test
	public void testGetPromoPianificazoneById() {
		Long promoPianificazioneId = 123L;
		service.getPromoPianificazoneById(promoPianificazioneId);
		verify(promozionePianificazioneDAO).findById(promoPianificazioneId);
	}

	@Test
	public void whenRemovePromozionePianificazioneEntityWithNullValueThenThrowException() {
		ex.expect(NullPointerException.class);
		service.removePromozionePianificazioneEntity(null);
	}

	@Test
	public void removePromozionePianificazioneEntity() {
		PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
		service.removePromozionePianificazioneEntity(mock);
		verify(promozionePianificazioneDAO).remove(mock);
	}

	@Test
	public void shouldFindAllItemsByDynamicFilters() {
		ItemEntity itemMock = mock(ItemEntity.class);
		when(itemDAO.findAllByFilter(Long.parseLong("111"), Long.parseLong("111"), Long.parseLong("111"),
				Long.parseLong("111"), Long.parseLong("111"), "01")).thenReturn(Collections.singletonList(itemMock));
		List<ItemEntity> items = service.findAllItemsByDynamicFilters(Long.parseLong("111"), Long.parseLong("111"),
				Long.parseLong("111"), Long.parseLong("111"), Long.parseLong("111"), "01");
		verify(itemDAO, times(1)).findAllByFilter(Long.parseLong("111"), Long.parseLong("111"), Long.parseLong("111"),
				Long.parseLong("111"), Long.parseLong("111"), "01");
		verify(service, times(1)).findAllItemsByDynamicFilters(Long.parseLong("111"), Long.parseLong("111"),
				Long.parseLong("111"), Long.parseLong("111"), Long.parseLong("111"), "01");
		assertNotNull(items);
		assertFalse(items.isEmpty());
	}

	@Test
	public void shouldGetUploadFidayEntityByPromozionePianificazioneEntity() {
		PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
		when(mock.getId()).thenReturn(Long.MIN_VALUE);
		UploadFidayEntity uploadFidayMock = mock(UploadFidayEntity.class);
		when(uploadFidatyDAO.findByPianificazione(mock.getId())).thenReturn(Collections.singletonList(uploadFidayMock));
		List<UploadFidayEntity> uploadFidays = service.getUploadFidayEntityByPromozionePianificazioneEntity(mock);
		verify(uploadFidatyDAO, times(1)).findByPianificazione(mock.getId());
		verify(service, times(1)).getUploadFidayEntityByPromozionePianificazioneEntity(mock);
		assertNotNull(uploadFidays);
		assertFalse(uploadFidays.isEmpty());
	}

	@Test
	public void shouldGetUploadFidayEntityByPromozionePianificazioneEntityException() {
		ex.expect(NullPointerException.class);
		service.getUploadFidayEntityByPromozionePianificazioneEntity(null);
		verify(service, times(0)).getUploadFidayEntityByPromozionePianificazioneEntity(null);
	}

	@Test
	public void shouldRemoveUploadFidayEntity() {
		UploadFidayEntity uploadFidayMock = mock(UploadFidayEntity.class);
		service.removeUploadFidayEntity(uploadFidayMock);
		verify(uploadFidatyDAO, times(1)).remove(uploadFidayMock);
		verify(service, times(1)).removeUploadFidayEntity(uploadFidayMock);
	}

	@Test
	public void shouldRemoveUploadFidayEntityException() {
		ex.expect(NullPointerException.class);
		service.removeUploadFidayEntity(null);
		verify(service, times(0)).removeUploadFidayEntity(null);
	}

	@Test
	public void testSavePromozionePianificazioneEntityWithUsername() {
		PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
		service.savePromozionePianificazioneEntity(mock, "test");
		verify(promozionePianificazioneDAO, times(1)).persistWithAuditLog(mock, "test");
		verify(service, times(1)).savePromozionePianificazioneEntity(mock, "test");
	}

	@Test
	public void whenSavePromozionePianificazioneEntityWithoutUsernameException() {
		ex.expect(NullPointerException.class);
		PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
		service.savePromozionePianificazioneEntity(mock, null);
	}

	@Test
	public void whenSavePromozionePianificazioneEntityWithUsernameWithoutEntityException() {
		ex.expect(NullPointerException.class);
		service.savePromozionePianificazioneEntity(null, "test");
	}

	@Test
	public void whenSavePromozionePianificazioneEntityWithoutParamsException() {
		ex.expect(NullPointerException.class);
		service.savePromozionePianificazioneEntity(null, null);
	}

	@Test
	public void whenFindDuplicatedItems() {
		PromozionePianificazioneEntity mock1 = mock(PromozionePianificazioneEntity.class);
		PromozionePianificazioneEntity mock2 = mock(PromozionePianificazioneEntity.class);
		when(mock1.getId()).thenReturn(Long.parseLong("947"));
		when(mock1.getCodiceElemento()).thenReturn("1001");
		when(mock2.getCodiceElemento()).thenReturn("1001");
		when(promozionePianificazioneDAO.findAllDetailsByIdPromozione(mock1.getId()))
				.thenReturn(Arrays.asList(mock1, mock2));
		List<PromozionePianificazioneEntity> promozionePianificazioni = service.findDuplicatedItems(mock1.getId(),
				Long.parseLong(mock1.getCodiceElemento()));
		verify(promozionePianificazioneDAO, times(1)).findAllDetailsByIdPromozione(mock1.getId());
		verify(service, times(1)).findDuplicatedItems(mock1.getId(),
				Long.parseLong(mock1.getCodiceElemento()));
		assertEquals(Arrays.asList(mock1, mock2).size(), promozionePianificazioni.size());
	}

	@Test
	public void whenFindNoDuplicatedItemsDifferentCodiceElemento() {
		PromozionePianificazioneEntity mock1 = mock(PromozionePianificazioneEntity.class);
		PromozionePianificazioneEntity mock2 = mock(PromozionePianificazioneEntity.class);
		when(mock2.getId()).thenReturn(Long.parseLong("947"));
		when(mock1.getCodiceElemento()).thenReturn("1001");
		when(mock2.getCodiceElemento()).thenReturn("1002");
		when(promozionePianificazioneDAO.findAllDetailsByIdPromozione(mock2.getId()))
				.thenReturn(Arrays.asList(mock1, mock2));
		List<PromozionePianificazioneEntity> promozionePianificazioni = service.findDuplicatedItems(mock2.getId(),
				Long.parseLong(mock2.getCodiceElemento()));
		verify(promozionePianificazioneDAO, times(1)).findAllDetailsByIdPromozione(mock2.getId());
		verify(service, times(1)).findDuplicatedItems(mock2.getId(),
				Long.parseLong(mock2.getCodiceElemento()));
		assertEquals(mock2.getId(), promozionePianificazioni.get(0).getId());
	}

	@Test
	public void whenFindNoDuplicatedItemsDifferentMeccanica() {
		PromozionePianificazioneEntity mock1 = mock(PromozionePianificazioneEntity.class);
		PromozionePianificazioneEntity mock2 = mock(PromozionePianificazioneEntity.class);
		when(mock2.getId()).thenReturn(Long.parseLong("947"));
		when(mock2.getCodiceElemento()).thenReturn("1002");
		when(promozionePianificazioneDAO.findAllDetailsByIdPromozione(mock2.getId()))
				.thenReturn(Arrays.asList(mock1, mock2));
		List<PromozionePianificazioneEntity> promozionePianificazioni = service.findDuplicatedItems(mock2.getId(),
				Long.parseLong(mock2.getCodiceElemento()));
		verify(promozionePianificazioneDAO, times(1)).findAllDetailsByIdPromozione(mock2.getId());
		verify(service, times(1)).findDuplicatedItems(mock2.getId(),
				Long.parseLong(mock2.getCodiceElemento()));
		assertEquals(mock2.getId(), promozionePianificazioni.get(0).getId());
	}

	@Test
	public void findAllBuoniNotUsed_givenEmptyList() {
		final List<PromozionePianificazioneEntity> pianificazioni = new ArrayList<>();
		pianificazioni.add(mock(PromozionePianificazioneEntity.class));
		pianificazioni.add(mock(PromozionePianificazioneEntity.class));
		when(promozionePianificazioneDAO.findAllBuoni()).thenReturn(pianificazioni);
		final List<PromozionePianificazioneEntity> entities = service.findAllBuoniNotUsed(Collections.emptyList());
		assertEquals(2, entities.size());
		verify(promozionePianificazioneDAO, times(1)).findAllBuoni();
		verify(promozionePianificazioneDAO, never()).findAllBuoniNotUsed(anyList());
	}

	@Test
	public void findAllBuoniNotUsed_givenNullList() {
		final List<PromozionePianificazioneEntity> pianificazioni = new ArrayList<>();
		pianificazioni.add(mock(PromozionePianificazioneEntity.class));
		pianificazioni.add(mock(PromozionePianificazioneEntity.class));
		when(promozionePianificazioneDAO.findAllBuoni()).thenReturn(pianificazioni);
		final List<PromozionePianificazioneEntity> entities = service.findAllBuoniNotUsed(null);
		assertEquals(2, entities.size());
		verify(promozionePianificazioneDAO, times(1)).findAllBuoni();
		verify(promozionePianificazioneDAO, never()).findAllBuoniNotUsed(anyList());
	}

	@Test
	public void findAllBuoniNotUsed_givenPopulatedList() {
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		final List<Long> usedIds = Collections.singletonList(1L);
		when(promozionePianificazioneDAO.findAllBuoniNotUsed(usedIds)).thenReturn(Collections.singletonList(pianificazione));
		final List<PromozionePianificazioneEntity> entities = service.findAllBuoniNotUsed(usedIds);
		assertEquals(1, entities.size());
		verify(promozionePianificazioneDAO, never()).findAllBuoni();
		verify(promozionePianificazioneDAO, times(1)).findAllBuoniNotUsed(usedIds);
	}

	@Test
	public void findOverlappingByAnnoCanaleMeccanica_givenNullPianificazione_shouldThrowException() {
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		ex.expect(NullPointerException.class);
		service.findOverlappingByAnnoCanaleMeccanica(null, "2024", canale, meccanica);
		verify(promozionePianificazioneDAO, never()).findOverlappingByAnnoCanaleMeccanica(
				any(PromozionePianificazioneEntity.class), any(String.class),
				any(CanalePromozioneEntity.class), any(MeccanicheEntity.class));
	}

	@Test
	public void findOverlappingByAnnoCanaleMeccanica_givenNullAnno_shouldThrowException() {
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		ex.expect(NullPointerException.class);
		service.findOverlappingByAnnoCanaleMeccanica(pianificazione, null, canale, meccanica);
		verify(promozionePianificazioneDAO, never()).findOverlappingByAnnoCanaleMeccanica(
				any(PromozionePianificazioneEntity.class), any(String.class),
				any(CanalePromozioneEntity.class), any(MeccanicheEntity.class));
	}

	@Test
	public void findOverlappingByAnnoCanaleMeccanica_givenNullCanale_shouldThrowException() {
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		ex.expect(NullPointerException.class);
		service.findOverlappingByAnnoCanaleMeccanica(pianificazione, "2024", null, meccanica);
		verify(promozionePianificazioneDAO, never()).findOverlappingByAnnoCanaleMeccanica(
				any(PromozionePianificazioneEntity.class), any(String.class),
				any(CanalePromozioneEntity.class), any(MeccanicheEntity.class));
	}

	@Test
	public void findOverlappingByAnnoCanaleMeccanica_givenNullMeccanica_shouldThrowException() {
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		ex.expect(NullPointerException.class);
		service.findOverlappingByAnnoCanaleMeccanica(pianificazione, "2024", canale, null);
		verify(promozionePianificazioneDAO, never()).findOverlappingByAnnoCanaleMeccanica(
				any(PromozionePianificazioneEntity.class), any(String.class),
				any(CanalePromozioneEntity.class), any(MeccanicheEntity.class));
	}

	@Test
	public void findOverlappingByAnnoCanaleMeccanica() {
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
		final List<PromozionePianificazioneEntity> pianificazioni = Collections.singletonList(mock(PromozionePianificazioneEntity.class));
		when(promozionePianificazioneDAO.findOverlappingByAnnoCanaleMeccanica(pianificazione, "2024", canale, meccanica))
				.thenReturn(pianificazioni);
		final List<PromozionePianificazioneEntity> overlapping = service.findOverlappingByAnnoCanaleMeccanica(
				pianificazione, "2024", canale, meccanica);
		assertEquals(1, overlapping.size());
		verify(promozionePianificazioneDAO, times(1)).findOverlappingByAnnoCanaleMeccanica(
				pianificazione, "2024", canale, meccanica);
	}
}
