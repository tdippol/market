package com.axiante.mui.dbpromo.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.service.UploadExcelService;
import com.axiante.mui.dbpromo.business.service.impl.data.ItemUpload;
import com.axiante.mui.dbpromo.business.service.impl.data.ShopItemUpload;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.GrmService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UploadExcelServiceTest {

	@Spy
	@InjectMocks
	private UploadExcelService service = new UploadExcelServiceImpl();

	@Mock
	private ItemService itemService;

	@Mock
	private GrmService grmService;

	@Mock
	private RepartoService repartoService;

	private static String filename;

	@BeforeClass
	public static void setUp() {
		Path resourceDirectory = Paths.get("src", "test", "resources", "formato_upload.xlsx");
		filename = resourceDirectory.toFile().getAbsolutePath();
	}

	@Test
	public void shouldReadXLSXArticoloFileWithAllResult() throws IOException {
		final ItemEntity entity = mock(ItemEntity.class);
		when(itemService.findByCode(Mockito.anyString())).thenReturn(entity);

		final File file = new File(filename);
		final Set<String> codes = service.readFile(ElementType.ARTICOLO, file);

		assertFalse(codes.isEmpty());
	}

	@Test
	public void shouldReadXLSArticoloFileWithAllResult() throws IOException {
		final ItemEntity entity = mock(ItemEntity.class);
		when(itemService.findByCode(Mockito.anyString())).thenReturn(entity);

		Path resourceDirectory = Paths.get("src", "test", "resources", "formato_upload.xls");
		final File file = resourceDirectory.toFile();

		final Set<String> codes = service.readFile(ElementType.ARTICOLO, file);

		assertFalse(codes.isEmpty());
	}

	@Test
	public void shouldReadXLSXGRMFileWithAllResult() throws IOException {
		final GrmEntity entity = mock(GrmEntity.class);
		when(grmService.findByCode(Mockito.anyString())).thenReturn(entity);

		Path resourceDirectory = Paths.get("src", "test", "resources", "grm_upload.xlsx");
		final File file = resourceDirectory.toFile();
		final Set<String> codes = service.readFile(ElementType.GRM, file);

		assertFalse(codes.isEmpty());
	}

	@Test
	public void shouldReadXLSGRMFileWithAllResult() throws IOException {
		final GrmEntity entity = mock(GrmEntity.class);
		when(grmService.findByCode(Mockito.anyString())).thenReturn(entity);

		Path resourceDirectory = Paths.get("src", "test", "resources", "grm_upload.xls");
		final File file = resourceDirectory.toFile();

		final Set<String> codes = service.readFile(ElementType.GRM, file);

		assertFalse(codes.isEmpty());
	}

	@Test
	public void shouldReadXLSXRepartoFileWithAllResult() throws IOException {
		final RepartoEntity entity = mock(RepartoEntity.class);
		when(repartoService.findByCode(Mockito.anyString())).thenReturn(entity);

		Path resourceDirectory = Paths.get("src", "test", "resources", "reparto_upload.xlsx");
		final File file = resourceDirectory.toFile();
		final Set<String> codes = service.readFile(ElementType.REPARTO, file);

		assertFalse(codes.isEmpty());
	}

	@Test
	public void shouldReadXLSRepartoFileWithAllResult() throws IOException {
		final RepartoEntity entity = mock(RepartoEntity.class);
		when(repartoService.findByCode(Mockito.anyString())).thenReturn(entity);

		Path resourceDirectory = Paths.get("src", "test", "resources", "reparto_upload.xls");
		final File file = resourceDirectory.toFile();

		final Set<String> codes = service.readFile(ElementType.REPARTO, file);

		assertFalse(codes.isEmpty());
	}

	@Test
	public void shouldReadFileUntilEmptyRowElementTypeXls() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "grm_upload.xls");
		final File file = resourceDirectory.toFile();
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("88"));
		List<ItemUpload> itemSet = service.readFileUntilEmptyRow(ElementType.GRM, file, mockCompratore.getId(), 100);
		verify(service, times(1)).readFileUntilEmptyRow(ElementType.GRM, file, mockCompratore.getId(), 100);
		assertNotNull(itemSet);
		assertFalse(itemSet.isEmpty());
	}

	@Test
	public void shouldReadFileUntilEmptyRowElementTypeXlsx() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "grm_upload.xlsx");
		final File file = resourceDirectory.toFile();
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("89"));
		List<ItemUpload> itemSet = service.readFileUntilEmptyRow(ElementType.GRM, file, mockCompratore.getId(), 100);
		verify(service, times(1)).readFileUntilEmptyRow(ElementType.GRM, file, mockCompratore.getId(), 100);
		assertNotNull(itemSet);
		assertFalse(itemSet.isEmpty());
	}

	@Test
	public void shouldReadFileUntilEmptyRowFileXls() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "grm_upload.xls");
		final File file = resourceDirectory.toFile();
		Set<String> promoNegoziSigles = new HashSet<>();
		promoNegoziSigles.add("ABC");
		promoNegoziSigles.add("DEF");
		Set<ShopItemUpload> itemUploadSet = service.readFileUntilEmptyRow(file, promoNegoziSigles, 100);
		verify(service, times(1)).readFileUntilEmptyRow(file, promoNegoziSigles, 100);
		assertNotNull(itemUploadSet);
		assertFalse(itemUploadSet.isEmpty());
	}

	@Test
	public void shouldReadFileUntilEmptyRowFileXlsx() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "grm_upload.xlsx");
		final File file = resourceDirectory.toFile();
		Set<String> promoNegoziSigles = new HashSet<>();
		promoNegoziSigles.add("ABC");
		promoNegoziSigles.add("DEF");
		Set<ShopItemUpload> itemUploadSet = service.readFileUntilEmptyRow(file, promoNegoziSigles, 100);
		verify(service, times(1)).readFileUntilEmptyRow(file, promoNegoziSigles, 100);
		assertNotNull(itemUploadSet);
		assertFalse(itemUploadSet.isEmpty());
	}

	@Test
	public void shoudValidatUploadGrm() {
		GrmEntity mockGrm = mock(GrmEntity.class);
		when(mockGrm.getCodiceGrm()).thenReturn("888");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("87"));
		ItemUpload itemUpload = service.validateUpload(ElementType.GRM, mockGrm.getCodiceGrm(), mockCompratore.getId());
		verify(grmService, times(1)).findByCode(mockGrm.getCodiceGrm());
		verify(service, times(1)).validateUpload(ElementType.GRM, mockGrm.getCodiceGrm(), mockCompratore.getId());
		assertNotNull(itemUpload);
	}

	@Test
	public void shoudValidatUploadReparto() {
		RepartoEntity mockReparto = mock(RepartoEntity.class);
		when(mockReparto.getCodiceReparto()).thenReturn("887");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("85"));
		ItemUpload itemUpload = service.validateUpload(ElementType.REPARTO, mockReparto.getCodiceReparto(),
				mockCompratore.getId());
		verify(repartoService, times(1)).findByCode(mockReparto.getCodiceReparto());
		verify(service, times(1)).validateUpload(ElementType.REPARTO, mockReparto.getCodiceReparto(),
				mockCompratore.getId());
		assertNotNull(itemUpload);
	}

	@Test
	public void shoudValidatUploadArticolo() {
		ItemEntity mockItem = mock(ItemEntity.class);
		when(mockItem.getCodiceItem()).thenReturn("886");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("85"));
		ItemUpload itemUpload = service.validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		verify(itemService, times(1)).findByCode(mockItem.getCodiceItem());
		verify(service, times(1)).validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		assertNotNull(itemUpload);
	}

	@Test(expected = IOException.class)
	public void shouldReadFileWrongFormat() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "upload.txt");
		final File file = resourceDirectory.toFile();
		service.readFile(ElementType.ARTICOLO, file);
		verify(service, times(1)).readFile(ElementType.ARTICOLO, file);
	}

	@Test(expected = IOException.class)
	public void shouldReadFileUntilEmptyRowIdWrongFormat() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "upload.txt");
		final File file = resourceDirectory.toFile();
		service.readFileUntilEmptyRow(ElementType.ARTICOLO, file, Long.MAX_VALUE, 100);
		verify(service, times(1)).readFileUntilEmptyRow(ElementType.ARTICOLO, file, Long.MAX_VALUE,any(Integer.class));
	}

	@Test(expected = IOException.class)
	public void shouldReadFileUntilEmptyRowSetWrongFormat() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "upload.txt");
		final File file = resourceDirectory.toFile();
		Set<String> promoNegoziSigles = new HashSet<>();
		promoNegoziSigles.add("ABC");
		promoNegoziSigles.add("DEF");
		service.readFileUntilEmptyRow(file, promoNegoziSigles,100);
		verify(service, times(1)).readFileUntilEmptyRow(file, promoNegoziSigles,100);
	}

	@Test
	public void shoudValidatUploadEmptyCode() {
		ItemUpload itemUpload = service.validateUpload(ElementType.GRM, "", Long.MAX_VALUE);
		verify(service, times(1)).validateUpload(ElementType.GRM, "", Long.MAX_VALUE);
		assertNotNull(itemUpload);
		assertEquals("BLANK", itemUpload.getType());
	}

	@Test
	public void shoudValidatUploadNullCode() {
		ItemUpload itemUpload = service.validateUpload(ElementType.GRM, null, Long.MAX_VALUE);
		verify(service, times(1)).validateUpload(ElementType.GRM, null, Long.MAX_VALUE);
		assertNotNull(itemUpload);
		assertEquals("BLANK", itemUpload.getType());
	}

	@Test
	public void shoudValidatUploadGrmEntityNull() {
		GrmEntity mockGrm = mock(GrmEntity.class);
		when(mockGrm.getCodiceGrm()).thenReturn("888");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("87"));
		when(grmService.findByCode(mockGrm.getCodiceGrm())).thenReturn(null);
		ItemUpload itemUpload = service.validateUpload(ElementType.GRM, mockGrm.getCodiceGrm(), mockCompratore.getId());
		verify(grmService, times(1)).findByCode(mockGrm.getCodiceGrm());
		verify(service, times(1)).validateUpload(ElementType.GRM, mockGrm.getCodiceGrm(), mockCompratore.getId());
		assertNotNull(itemUpload);
		assertEquals("STRING", itemUpload.getType());
	}

	@Test
	public void shoudValidatUploadRepartoEntityNull() {
		RepartoEntity mockReparto = mock(RepartoEntity.class);
		when(mockReparto.getCodiceReparto()).thenReturn("887");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("85"));
		when(repartoService.findByCode(mockReparto.getCodiceReparto())).thenReturn(null);
		ItemUpload itemUpload = service.validateUpload(ElementType.REPARTO, mockReparto.getCodiceReparto(),
				mockCompratore.getId());
		verify(repartoService, times(1)).findByCode(mockReparto.getCodiceReparto());
		verify(service, times(1)).validateUpload(ElementType.REPARTO, mockReparto.getCodiceReparto(),
				mockCompratore.getId());
		assertNotNull(itemUpload);
		assertEquals("STRING", itemUpload.getType());
	}

	@Test
	public void shoudValidatUploadItemEntityNull() {
		ItemEntity mockItem = mock(ItemEntity.class);
		when(mockItem.getCodiceItem()).thenReturn("886");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("85"));
		when(itemService.findByCode(mockItem.getCodiceItem())).thenReturn(null);
		ItemUpload itemUpload = service.validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		verify(itemService, times(1)).findByCode(mockItem.getCodiceItem());
		verify(service, times(1)).validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		assertNotNull(itemUpload);
		assertEquals("STRING", itemUpload.getType());
	}

	@Test
	public void shoudValidatUploadGrmReturnItemUpload() {
		GrmEntity mockGrm = mock(GrmEntity.class);
		when(mockGrm.getCodiceGrm()).thenReturn("888");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("87"));
		when(grmService.findByCode(mockGrm.getCodiceGrm())).thenReturn(mockGrm);
		ItemUpload itemUpload = service.validateUpload(ElementType.GRM, mockGrm.getCodiceGrm(), mockCompratore.getId());
		verify(grmService, times(1)).findByCode(mockGrm.getCodiceGrm());
		verify(service, times(1)).validateUpload(ElementType.GRM, mockGrm.getCodiceGrm(), mockCompratore.getId());
		assertNotNull(itemUpload);
	}

	@Test
	public void shoudValidatUploadRepartoReturnItemUpload() {
		RepartoEntity mockReparto = mock(RepartoEntity.class);
		when(mockReparto.getCodiceReparto()).thenReturn("887");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("85"));
		when(repartoService.findByCode(mockReparto.getCodiceReparto())).thenReturn(mockReparto);
		ItemUpload itemUpload = service.validateUpload(ElementType.REPARTO, mockReparto.getCodiceReparto(),
				mockCompratore.getId());
		verify(repartoService, times(1)).findByCode(mockReparto.getCodiceReparto());
		verify(service, times(1)).validateUpload(ElementType.REPARTO, mockReparto.getCodiceReparto(),
				mockCompratore.getId());
		assertNotNull(itemUpload);
	}

	@Test
	public void shoudValidatUploadArticoloReturnItemUpload() {
		ItemEntity mockItem = mock(ItemEntity.class);
		when(mockItem.getCodiceItem()).thenReturn("886");
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("85"));
		when(itemService.findByCode(mockItem.getCodiceItem())).thenReturn(mockItem);
		ItemUpload itemUpload = service.validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		verify(itemService, times(1)).findByCode(mockItem.getCodiceItem());
		verify(service, times(1)).validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		assertNotNull(itemUpload);
	}

	@Test
	public void shoudValidatUploadArticoloNoFornitorePrincipale() {
		ItemEntity mockItem = mock(ItemEntity.class);
		when(mockItem.getCodiceItem()).thenReturn("886");
		AssortimentoFornitoreEntity assortimentoFornitoreMock = mock(AssortimentoFornitoreEntity.class);
		when(assortimentoFornitoreMock.getFornitorePrincipale()).thenReturn(Long.parseLong("0"));
		Set<AssortimentoFornitoreEntity> assortimentoFornitoreSet = new HashSet<>();
		assortimentoFornitoreSet.add(assortimentoFornitoreMock);
		when(mockItem.getMuiAssortimentoFornitores()).thenReturn(assortimentoFornitoreSet);
		CompratoreEntity mockCompratore = mock(CompratoreEntity.class);
		when(mockCompratore.getId()).thenReturn(new Long("85"));
		when(itemService.findByCode(mockItem.getCodiceItem())).thenReturn(mockItem);
		ItemUpload itemUpload = service.validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		verify(itemService, times(1)).findByCode(mockItem.getCodiceItem());
		verify(service, times(1)).validateUpload(ElementType.ARTICOLO, mockItem.getCodiceItem(),
				mockCompratore.getId());
		assertNotNull(itemUpload);
		assertEquals("STRING", itemUpload.getType());
	}

	@Test
	public void shouldValidateGrmCodeNotFound() {
		;
		assertFalse(service.validate(ElementType.GRM, ""));
		verify(grmService, times(1)).findByCode("");
		verify(service, times(1)).validate(ElementType.GRM, "");
	}

	@Test
	public void shouldValidateRepartoCodeNotFound() {
		assertFalse(service.validate(ElementType.REPARTO, ""));
		verify(repartoService, times(1)).findByCode("");
		verify(service, times(1)).validate(ElementType.REPARTO, "");
	}

	@Test
	public void shouldValidateArticoloCodeNotFound() {
		assertFalse(service.validate(ElementType.ARTICOLO, ""));
		verify(itemService, times(1)).findByCode("");
		verify(service, times(1)).validate(ElementType.ARTICOLO, "");
	}

	@Test(expected = IOException.class)
	public void shouldReadFileException() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "test.txt");
		service.readFile(ElementType.ARTICOLO, new File(resourceDirectory.toFile().getAbsolutePath()));
		verify(service, times(1)).readFile(ElementType.ARTICOLO, new File("test.csv"));
	}

	@Test
	public void shouldValidateNullCodeNotFound() {
		assertFalse(service.validate(null, ""));
		verify(service, times(1)).validate(null, "");
	}

	@Test(expected = IOException.class)
	public void shouldReadFileUntilEmptyShopsRowException() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "test.txt");
		service.readFileUntilEmptyRow(new File(resourceDirectory.toFile().getAbsolutePath()), null, 100);
		verify(service, times(1)).readFileUntilEmptyRow(new File("test.csv"), null,100 );
	}

	@Test(expected = IOException.class)
	public void shouldReadFileUntilEmptyRowException() throws IOException {
		Path resourceDirectory = Paths.get("src", "test", "resources", "test.txt");
		service.readFileUntilEmptyRow(ElementType.ARTICOLO, new File(resourceDirectory.toFile().getAbsolutePath()),
				Long.MIN_VALUE, 100);
		verify(service, times(1)).readFileUntilEmptyRow(ElementType.ARTICOLO,
				new File(resourceDirectory.toFile().getAbsolutePath()), Long.MIN_VALUE, any(Integer.class));
	}

	@Test
	public void shouldValidateUploadNullElementType() {
		ItemUpload itemUpload = service.validateUpload(null, "123", Long.MIN_VALUE);
		verify(service, times(1)).validateUpload(null, "123", Long.MIN_VALUE);
		assertNotNull(itemUpload);
		assertEquals("STRING", itemUpload.getType());
	}

	@Test
	public void shouldValidateUploadFornitorePrincipaleNoCodiceItem() {
		ItemEntity itemMock = mock(ItemEntity.class);
		Set<AssortimentoFornitoreEntity> assortimentoFornitori = new HashSet<>();
		AssortimentoFornitoreEntity assortimentoFornitoreMock = mock(AssortimentoFornitoreEntity.class);
		when(assortimentoFornitoreMock.getFornitorePrincipale()).thenReturn(Long.parseLong("1"));
		assortimentoFornitori.add(assortimentoFornitoreMock);
		when(itemMock.getMuiAssortimentoFornitores()).thenReturn(assortimentoFornitori);
		when(itemService.findByCode("123")).thenReturn(itemMock);
		ItemUpload itemUpload = service.validateUpload(ElementType.ARTICOLO, "123", Long.MIN_VALUE);
		verify(itemService, times(1)).findByCode("123");
		verify(service, times(1)).validateUpload(ElementType.ARTICOLO, "123", Long.MIN_VALUE);
		assertNotNull(itemUpload);
		assertTrue(itemUpload.getElementDescription().isEmpty());
	}

	@Test
	public void shouldValidateUploadFornitorePrincipaleItemNoDescrizione() {
		ItemEntity itemMock = mock(ItemEntity.class);
		Set<AssortimentoFornitoreEntity> assortimentoFornitori = new HashSet<>();
		AssortimentoFornitoreEntity assortimentoFornitoreMock = mock(AssortimentoFornitoreEntity.class);
		when(assortimentoFornitoreMock.getFornitorePrincipale()).thenReturn(Long.parseLong("1"));
		assortimentoFornitori.add(assortimentoFornitoreMock);
		when(itemMock.getMuiAssortimentoFornitores()).thenReturn(assortimentoFornitori);
		when(itemMock.getCodiceItem()).thenReturn("ABC");
		when(itemService.findByCode("123")).thenReturn(itemMock);
		ItemUpload itemUpload = service.validateUpload(ElementType.ARTICOLO, "123", Long.MIN_VALUE);
		verify(itemService, times(1)).findByCode("123");
		verify(service, times(1)).validateUpload(ElementType.ARTICOLO, "123", Long.MIN_VALUE);
		assertNotNull(itemUpload);
		assertEquals(String.format("%s - %s", itemMock.getCodiceItem(), ""), itemUpload.getElementDescription());
	}

	@Test
	public void shouldValidateUploadFornitorePrincipaleItem() {
		ItemEntity itemMock = mock(ItemEntity.class);
		Set<AssortimentoFornitoreEntity> assortimentoFornitori = new HashSet<>();
		AssortimentoFornitoreEntity assortimentoFornitoreMock = mock(AssortimentoFornitoreEntity.class);
		when(assortimentoFornitoreMock.getFornitorePrincipale()).thenReturn(Long.parseLong("1"));
		assortimentoFornitori.add(assortimentoFornitoreMock);
		when(itemMock.getMuiAssortimentoFornitores()).thenReturn(assortimentoFornitori);
		when(itemMock.getCodiceItem()).thenReturn("ABC");
		when(itemMock.getDescrizione()).thenReturn("descrizione");
		when(itemService.findByCode("123")).thenReturn(itemMock);
		ItemUpload itemUpload = service.validateUpload(ElementType.ARTICOLO, "123", Long.MIN_VALUE);
		verify(itemService, times(1)).findByCode("123");
		verify(service, times(1)).validateUpload(ElementType.ARTICOLO, "123", Long.MIN_VALUE);
		assertNotNull(itemUpload);
		assertEquals(String.format("%s - %s", itemMock.getCodiceItem(), itemMock.getDescrizione().toUpperCase()),
				itemUpload.getElementDescription());
	}

}
