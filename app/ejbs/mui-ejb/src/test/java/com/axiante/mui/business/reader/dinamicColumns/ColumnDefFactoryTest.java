package com.axiante.mui.business.reader.dinamicColumns;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.CellAttribute;
import com.axiante.utility.configuration.DynamicColumnsSettings;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class ColumnDefFactoryTest {

    static final String config = "{\n" +
            "				'headerconf': [\n" +
            "					'Caption_Default',\n" +
            "					'Monate_DE'\n" +
            "				],\n" +
            "				'headerdefaults': {\n" +
            "					'marryChildren': true\n" +
            "				},\n" +
            "				'childrendefaults': {\n" +
            "					'width': 100,\n" +
            "					'editable': true,\n" +
            "					'columnGroupShow': 'always',\n" +
            "					'type': [\n" +
            "						'TM1DataColumnNumber',\n" +
            "						'numericColumn'\n" +
            "					]\n" +
            "				},\n" +
            "				'childrenCustomTypes': {\n" +
            "					'Mrz': {\n" +
            "						'type': [\n" +
            "							'TM1DataColumnText',\n" +
            "							'numericColumn'\n" +
            "						]\n" +
            "					},\n" +
            "					'Dez': {\n" +
            "						'type': [\n" +
            "							'TM1DataColumnText',\n" +
            "							'numericColumn'\n" +
            "						]\n" +
            "					}\n" +
            "				}\n" +
            "			}\n";

    static DynamicColumnsSettings settings = null;
    @BeforeClass
    public static void init() throws JsonParseException, JsonMappingException, IOException {
        settings = JsonUtils.getMapper().readValue(new ByteArrayInputStream(config.replaceAll("'", "\"").getBytes()), DynamicColumnsSettings.class);
    }

    @Test
    public void testInstantiate() {
        assertNotNull(new ColumnDefFactory(settings));
    }

    @Test (expected = NullPointerException.class)
    public void testInstantiateWithNullThrowsException() {
        assertNotNull(new ColumnDefFactory(null));
    }

    @Test(expected = NullPointerException.class)
    public void testFromNullCellThrowsException() {
        assertNotNull(new ColumnDefFactory(settings).fromCell(null));
    }


    @Test
    public void testFromCellWithCustomTypeReturnsColumnDef() {
        Cell cell = mock(Cell.class);
        when(cell.getName()).thenReturn("Mrz");

        Map<String, LinkedList<CellAttribute>> attributes = new HashMap<>();
        LinkedList<CellAttribute> list = new LinkedList<>();
        list.add(new CellAttribute("value one"));
        list.add(new CellAttribute("value two"));
        list.add(null);
        list.add(new CellAttribute(""));
        attributes.put("attribute_one", list);

        when(cell.getAttributes()).thenReturn(attributes);

        assertNotNull(new ColumnDefFactory(settings).fromCell(cell));
        attributes.put("attribute_one", new LinkedList<CellAttribute>());
        assertNotNull(new ColumnDefFactory(settings).fromCell(cell));

    }

    @Test
    public void testFromCellReturnsColumnDef() {
        Cell cell = mock(Cell.class);
        when(cell.getName()).thenReturn("test");

        Map<String, LinkedList<CellAttribute>> attributes = new HashMap<>();
        LinkedList<CellAttribute> list = new LinkedList<>();
        list.add(new CellAttribute("value one"));
        list.add(new CellAttribute("value two"));
        list.add(null);
        list.add(new CellAttribute(""));
        attributes.put("attribute_one", list);

        when(cell.getAttributes()).thenReturn(attributes);

        assertNotNull(new ColumnDefFactory(settings).fromCell(cell));
        attributes.put("attribute_one", new LinkedList<CellAttribute>());
        assertNotNull(new ColumnDefFactory(settings).fromCell(cell));

    }

    @Test
    public void testFromComplexCellReturnsColumnDef() {
        Cell cell = mock(Cell.class);
        when(cell.getName()).thenReturn("test$$test");

        Map<String, LinkedList<CellAttribute>> attributes = new HashMap<>();
        LinkedList<CellAttribute> list = new LinkedList<>();
        list.add(new CellAttribute("value one"));
        list.add(new CellAttribute("value two"));
        attributes.put("attribute_one", list);

        when(cell.getAttributes()).thenReturn(attributes);

        assertNotNull(new ColumnDefFactory(settings).fromCell(cell));
    }

    @Test
    public void testFromCellWithNullNameReturnsColumnDef() {
        Cell cell = mock(Cell.class);
        when(cell.getName()).thenReturn(null);

        Map<String, LinkedList<CellAttribute>> attributes = new HashMap<>();
        LinkedList<CellAttribute> list = new LinkedList<>();
        list.add(new CellAttribute("value one"));
        list.add(new CellAttribute("value two"));
        attributes.put("attribute_one", list);

        when(cell.getAttributes()).thenReturn(attributes);

        assertNotNull(new ColumnDefFactory(settings).fromCell(cell));
    }

    @Test public void testGenerateHeaderNames() {
        Cell cell = mock(Cell.class);
        Map<String, LinkedList<CellAttribute>> attributes = new HashMap<>();
        LinkedList<CellAttribute> cellAttributes1 = new LinkedList<>();
        LinkedList<CellAttribute> cellAttributes2 = new LinkedList<>();
        LinkedList<CellAttribute> cellAttributes3 = null;
        LinkedList<CellAttribute> cellAttributes4 = new LinkedList<>();

        cellAttributes1.add(new CellAttribute("one"));
        cellAttributes1.add(new CellAttribute("two"));
        cellAttributes1.add(new CellAttribute(null));

        cellAttributes2.add(new CellAttribute("three"));
        cellAttributes2.add(new CellAttribute("four"));
        cellAttributes2.add(new CellAttribute(""));

        attributes.put("test 1", cellAttributes1);
        attributes.put("test 2", cellAttributes2);
        attributes.put(null, cellAttributes3);
        attributes.put("", cellAttributes4);
        attributes.put("test 4", cellAttributes2);

        when(cell.getAttributes()).thenReturn(attributes);
        when(cell.getAttribute("test 1")).thenReturn(cellAttributes1);
        when(cell.getAttribute("test 2")).thenReturn(cellAttributes2);
        when(cell.getAttribute("test 4")).thenReturn(cellAttributes4);

        new ColumnDefFactory(settings).generateHeaderNames(cell);
    }
}
