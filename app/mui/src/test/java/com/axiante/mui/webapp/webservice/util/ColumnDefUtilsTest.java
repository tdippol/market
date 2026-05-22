package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

public class ColumnDefUtilsTest {
    private JsonNode columnDef;

    private ColumnDefUtils utils = new ColumnDefUtils();

    @Before
    public void setUp() throws IOException {
        columnDef = readJson("columnDef.json");
    }

    @Test
    public void applyFilters_shouldReturnColumnDef_whenHiddenColsNotPresents() throws IOException {
        final JsonNode hiddenCols = readJson("hiddenCols.json");
        final JsonNode node = utils.applyFilters(columnDef, hiddenCols, "nogrid");
        assertEquals(columnDef, node);
    }

    @Test
    public void applyFilters_shouldReplaceFilters_whenHiddenColsPresents() throws IOException {
        final JsonNode hiddenCols = readJson("hiddenCols.json");
        final JsonNode node = utils.applyFilters(columnDef, hiddenCols, "grid1");
        final Boolean value = JsonPath.parse(node.toString()).read("$.columnDef[1].hide", Boolean.class);
        assertTrue(value);
    }

    @Test
    public void applyFilters_givenSingleHiddenCol_shouldReplaceFilters_whenHiddenColsPresents() throws IOException {
        final JsonNode hiddenCols = readJson("hiddenCols2.json");
        final JsonNode node = utils.applyFilters(columnDef, hiddenCols, "grid1");
        final Boolean value = JsonPath.parse(node.toString()).read("$.columnDef[1].hide", Boolean.class);
        assertTrue(value);
    }

    @Test
    public void applyFilters_givenNoHiddenCol_shouldReturnColumnDef() throws IOException {
        final JsonNode hiddenCols = readJson("hiddenCols5.json");
        final JsonNode node = utils.applyFilters(columnDef, hiddenCols, "grid1");
        assertEquals(columnDef, node);
    }

    @Test
    public void applyFilters_givenWrongHiddenCol_shouldReturnColumnDef() throws IOException {
        final JsonNode hiddenCols = readJson("hiddenCols4.json");
        final JsonNode node = utils.applyFilters(columnDef, hiddenCols, "grid1");
        assertEquals(columnDef, node);
        final JsonNode hiddenCols2 = readJson("hiddenCols3.json");
        final JsonNode node2 = utils.applyFilters(columnDef, hiddenCols2, "grid1");
        assertEquals(columnDef, node2);
    }

    @Test
    public void applyFilters_givenWrongColumnDef_shouldReturnColumnDef() throws IOException {
        final JsonNode hiddenCols = readJson("hiddenCols.json");
        final JsonNode columnDef = readJson("wrongColumnDef.json");
        final JsonNode node = utils.applyFilters(columnDef, hiddenCols, "grid1");
        assertEquals(columnDef, node);
        final JsonNode columnDef2 = readJson("wrongColumnDef2.json");
        final JsonNode node2 = utils.applyFilters(columnDef2, hiddenCols, "grid1");
        assertEquals(columnDef2, node2);
    }

    @Test(expected = NullPointerException.class)
    public void applyHiddenColumns_givenNullColumnDefStream_shouldThrowException() {
        utils.applyHiddenColumns((InputStream) null, "foo", "bar");
    }

    @Test
    public void applyHiddenColumns_shouldReturnColumnDef_whenHiddenColsNotPresents() throws IOException {
        final String hiddenCols = readJsonAsString("hiddenCols.json");
        final String columnDefJson = readJsonAsString("columnDef.json");
        final InputStream columnDef = jsonAsStream(columnDefJson);
        final JsonNode node = utils.applyHiddenColumns(columnDef, hiddenCols, "nogrid");
        assertEquals(columnDefJson, node.toString());
    }

    @Test
    public void applyHiddenColumns_shouldReturnColumnDef_whenThereIsErrorApplyingFilters() throws IOException {
        final String columnDefJson = readJsonAsString("columnDef.json");
        final InputStream columnDef = jsonAsStream(columnDefJson);
        final JsonNode node = utils.applyHiddenColumns(columnDef, "foo", "grid");
        assertEquals(columnDefJson, node.toString());
    }

    @Test
    public void applyHiddenColumns_shouldReturnNull_whenThereIsExceptionReadingJson() {
        assertNull(utils.applyHiddenColumns("foo", "bar", "grid"));
    }

    @Test(expected = NullPointerException.class)
    public void applyHiddenColumns_givenNullColumnDefString_shouldThrowException() {
        utils.applyHiddenColumns((String) null, "foo", "grid");
    }

    @Test
    public void applyHiddenColumns_givenColumnDefAsString_shouldReturnColumnDef_whenHiddenColsNotPresents() throws IOException {
        final String hiddenCols = readJsonAsString("hiddenCols.json");
        final String columnDefJson = readJsonAsString("columnDef.json");
        final JsonNode node = utils.applyHiddenColumns(columnDefJson, hiddenCols, "nogrid");
        assertEquals(columnDefJson, node.toString());
    }

    private String readJsonAsString(String filename) throws IOException {
        return readJson(filename).toString();
    }

    private JsonNode readJson(String filename) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource(filename).getFile());
        return mapper.readTree(file);
    }

    private InputStream jsonAsStream(String json) {
        return new ByteArrayInputStream(json.getBytes());
    }
}
