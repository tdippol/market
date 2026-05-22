package com.axiante.mui.webapp.webservice.util.pianificazione;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComplementaryColumnDefTest extends ColumnDefTest {
    @Mock
    private UploadFidatyService service;

    @Mock
    private ApplicationProperties applicationProperties;

    @InjectMocks
    private ComplementaryColumnDef columnDef = new ComplementaryColumnDef();

    @Test
    public void generateColumnDefByPromoConfiguration_givenPromozioneTestata_shouldReturnJsonString() throws IOException {
        // Good path
        final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
        final String hiddenCols = readJsonAsString("hiddenColsForComplementari.json");
        String json = columnDef.generateColumnDefByPromoConfiguration(entity, hiddenCols, "grid1", null);
        assertNotNull(json);
        DocumentContext cxt = JsonPath.parse(json);
        assertFalse(cxt.read("$.columnDef[0].hide", Boolean.class));
        assertTrue(cxt.read("$.columnDef[1].hide", Boolean.class));
        // Unable to apply filter
        json = columnDef.generateColumnDefByPromoConfiguration(entity, "wrong", "grid2", null);
        assertNotNull(json);
        cxt = JsonPath.parse(json);
        assertFalse(cxt.read("$.columnDef[0].hide", Boolean.class));
        assertFalse(cxt.read("$.columnDef[1].hide", Boolean.class));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnRowData() {
        // Arrange
        List<UploadFidayEntity> fidayEntities = mockFiday();
        when(service.findValidByPromozione(1L)).thenReturn(fidayEntities);
        when(applicationProperties.getProperty(anyString())).thenReturn("foo");
        // Act
        final String json = columnDef.generateRowDataByPromoConfiguration("1", "bar");
        // Assert
        assertNotNull(json);
        assertEquals(5, (int) JsonPath.read(json, "$.rowData.length()"));
    }

    @Test
    public void generateRowDataByPromoPianificazioneMaster_shouldReturnNull() {
        assertNull(columnDef.generateRowDataByPromoPianificazioneMaster(null));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataByPromoConfiguration(null, null, null, true, null));
    }

    @Test
    public void generateRowDataFilteredByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataFilteredByPromoConfiguration(null, null));
    }

    @Test
    public void generateRowDataByPromoElementMechanic_shouldReturnNull() {
        assertNull(columnDef.generateRowDataByPromoElementMechanic(null, null, null, null));
    }

    @Test(expected = NullPointerException.class)
    public void checkScarto_givenNullFilename_shouldThrowException() {
        columnDef.checkScarto(null);
    }

    @Test
    public void checkScarto_shouldReturnFalse_whenPropertyNotExist() {
        when(applicationProperties.getProperty(anyString())).thenReturn(null);
        assertFalse(columnDef.checkScarto("foo"));
    }

    @Test
    public void checkScarto_shouldReturnTrue_whenCanCreatePathForWastedFile() throws IOException {
        // Arrange - create a fake directory to ensure the call to file on tested method can pass
        final String wastedFullPath = "wastedPath/SCARTI_filename";
        final Path path = Paths.get(wastedFullPath);
        Files.createDirectories(path);
        // Act
        when(applicationProperties.getProperty(anyString())).thenReturn("wastedPath");
        // Assert
        assertTrue(columnDef.checkScarto("filename"));
        // Clean fake directory
        Files.deleteIfExists(path);
    }

    @Test(expected = NullPointerException.class)
    public void getScarto_givenNullIdUpload_shouldThrowException() {
        columnDef.getScarto(null);
    }

    @Test
    public void getScarto_givenNotExistentUpload_shouldReturnNull() {
        // Arrange
        when(service.findById(1L)).thenReturn(null);
        // Act & Assert
        assertNull(columnDef.getScarto(1L));
    }

    @Test
    public void getScarto_givenExistingUpload_shouldReturnFile_whenUploadHasFilename() throws IOException {
        // Arrange - create a fake directory to ensure the call to file on tested method can pass
        final String wastedFullPath = "wastedPath/SCARTI_filename";
        final Path path = Paths.get(wastedFullPath);
        Files.createDirectories(path);
        final UploadFidayEntity entity = mock(UploadFidayEntity.class);
        when(entity.getUploadedFileName()).thenReturn("filename");
        when(service.findById(1L)).thenReturn(entity);
        when(applicationProperties.getProperty(anyString())).thenReturn("wastedPath");
        // Act & Assert
        assertNotNull(columnDef.getScarto(1L));
        // Clean fake directory
        Files.deleteIfExists(path);
    }

    @Test
    public void getScarto_givenExistingUpload_shouldReturnNull_whenUploadHasNotFilename() {
        // Arrange
        final UploadFidayEntity entity = mock(UploadFidayEntity.class);
        when(entity.getUploadedFileName()).thenReturn("foo");
        when(service.findById(1L)).thenReturn(entity);
        // Act & Assert
        assertNull(columnDef.getScarto(1L));
    }


    private List<UploadFidayEntity> mockFiday() {
        List<UploadFidayEntity> entities = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final UploadFidayEntity entity = new UploadFidayEntity();
            entity.setId((long) i);
            entity.setDataPubblicazione(new Date());
            entity.setUserUpload("foo");
            entity.setOriginalFileName("filename");
            entity.setUploadedFileName("filename.orig");
            entity.setPromozionePianificazioneEntity(withPromoPianificazione());
            entities.add(entity);
        }
        return entities;
    }

    private PromozionePianificazioneEntity withPromoPianificazione() {
        final PromozionePianificazioneEntity entity = new PromozionePianificazioneEntity();
        entity.setTipoElemento("Elemento");
        entity.setBuonoScontoRadice(42);
        entity.setNumSet("1");
        entity.setMeccanicaEntity(withMeccanica());
        return entity;
    }

    private MeccanicheEntity withMeccanica() {
        final MeccanicheEntity entity = new MeccanicheEntity();
        entity.setCodiceMeccanica("MC");
        return entity;
    }
}
