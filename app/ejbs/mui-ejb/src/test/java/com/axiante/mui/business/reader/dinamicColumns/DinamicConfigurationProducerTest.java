package com.axiante.mui.business.reader.dinamicColumns;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.backing.MuiToken;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.utility.configuration.ColumnDef;
import com.axiante.utility.configuration.DynamicColumnsSettings;

@RunWith(MockitoJUnitRunner.class)
public class DinamicConfigurationProducerTest {

	@Mock
	Table table;
	@Mock
	DynamicColumnsSettings settings;
	@Mock
	MuiToken muiToken;
	@Mock
	ColumnDef columnDef;
	@Spy
	DinamicConfigurationProducer producer = new DinamicConfigurationProducer();

	@Before
	public void initTest() {
		when(table.getHeaders()).thenReturn(new Row<Cell>());
		when(muiToken.getHeaders()).thenReturn(new Row<Cell>());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetDinamicConfigurationTreeDelegatesCorrectly() {
		producer.getDinamicConfigurationTree(table, settings);
		producer.getDinamicConfigurationTree(muiToken, settings);
		verify(producer, times(2)).getDinamicConfigurationTree(ArgumentMatchers.any(Row.class),
				ArgumentMatchers.any(DynamicColumnsSettings.class), ArgumentMatchers.any(Integer.class));
	}

	@Test
	public void testGetDinamicConfigurationTreeReturnsEmptyRootNode() {
		ColumnDef def = producer.getDinamicConfigurationTree(table.getHeaders(), settings, table.getActualDataIndex());
		assertNotNull(def);
		assertTrue(def.isRoot());
		assertThat(def.getField(), equalTo(null));
	}

	@Test
	public void testGenerateConfigurationWithEmptyDataReturnsEmptyString() {
		StringBuilder builder = producer.generateConfiguration(columnDef, 1, settings);
		assertNotNull(builder);
		assertThat(builder.toString().length(), equalTo(0));
	}

	@Test
	public void testGenerateConfigurationWithNonEmptyDataReturnsString() {
		String headerName = "header";
		String field = "field";
		Integer width = 10;
		Boolean editable = true;
		String columnGroupShow = "yes";
		String aggFunc = "nothing";
		String aggFuncParam = "nothing";
		String[] type = new String[] { "type1", "type2" };
		Boolean hide = false;
		Boolean rowGroup = true;
		Boolean marryChildren = true;
		String headerClass = "headerClass";
		Boolean openByDefault = true;
		String cellClass = "cellClass";

		ColumnDef childColumnDef = new ColumnDef();

		childColumnDef.setHeaderName(headerName);
		childColumnDef.setField(field);
		childColumnDef.setWidth(width);
		childColumnDef.setEditable(editable);
		childColumnDef.setColumnGroupShow(columnGroupShow);
		childColumnDef.setAggFunc(aggFunc);
		childColumnDef.setAggFuncParam(aggFuncParam);
		childColumnDef.setType(type);
		childColumnDef.setHide(hide);
		childColumnDef.setRowGroup(rowGroup);
		childColumnDef.setMarryChildren(marryChildren);
		childColumnDef.setHeaderClass(headerClass);
		childColumnDef.setOpenByDefault(openByDefault);
		childColumnDef.setCellClass(cellClass);

		LinkedHashMap<String, ColumnDef> children = new LinkedHashMap<String, ColumnDef>();
		children.put("child", childColumnDef);

		when(columnDef.getChildren()).thenReturn(children);

		StringBuilder builder = producer.generateConfiguration(columnDef, 1, settings);
		assertNotNull(builder);
		assertThat(builder.toString().length()>0, equalTo(true));
		assertThat(builder.indexOf("\""+headerName+"\"") >0, equalTo(true));

		childColumnDef.setCustomHeaders(new String[] { "header1", "header2" });
		builder = producer.generateConfiguration(columnDef, 1, settings);
		assertNotNull(builder);
		assertThat(builder.toString().length()>0, equalTo(true));
		assertThat(builder.indexOf("\""+headerName+"\"") , equalTo(-1));
		assertThat(builder.indexOf("\"header2\"")>0, equalTo(true));

	}

	
	@Test
	public void testGenerateConfigurationWithChildrenDataReturnsString() {
		String headerName = "header";
		String field = "field";
		Integer width = 10;
		Boolean editable = true;
		String columnGroupShow = "yes";
		String aggFunc = "nothing";
		String aggFuncParam = "nothing";
		String[] type = new String[] { "type1", "type2" };
		Boolean hide = false;
		Boolean rowGroup = true;
		Boolean marryChildren = true;
		String headerClass = "headerClass";
		Boolean openByDefault = true;
		String cellClass = "cellClass";

		ColumnDef childColumnDef = new ColumnDef();

		childColumnDef.setHeaderName(headerName);
		childColumnDef.setField(field);
		childColumnDef.setWidth(width);
		childColumnDef.setEditable(editable);
		childColumnDef.setColumnGroupShow(columnGroupShow);
		childColumnDef.setAggFunc(aggFunc);
		childColumnDef.setAggFuncParam(aggFuncParam);
		childColumnDef.setType(type);
		childColumnDef.setHide(hide);
		childColumnDef.setRowGroup(rowGroup);
		childColumnDef.setMarryChildren(marryChildren);
		childColumnDef.setHeaderClass(headerClass);
		childColumnDef.setOpenByDefault(openByDefault);
		childColumnDef.setCellClass(cellClass);
		childColumnDef.setCustomHeaders(new String[] { "header1", "header2" });

		LinkedHashMap<String, ColumnDef> children = new LinkedHashMap<String, ColumnDef>();
		children.put("child", childColumnDef);

		when(columnDef.getChildren()).thenReturn(children);

		
		// child of child
		String headerName2 = "2header";
		String field2 = "2field";
		Integer width2 = 20;
		Boolean editable2 = true;
		String columnGroupShow2 = "2yes";
		String aggFunc2 = "2nothing";
		String aggFuncParam2 = "2nothing";
		String[] type2 = new String[] { "2type1", "2type2" };
		Boolean hide2 = false;
		Boolean rowGroup2 = true;
		Boolean marryChildren2 = true;
		String headerClass2 = "2headerClass";
		Boolean openByDefault2 = true;
		String cellClass2 = "2cellClass";

		ColumnDef childColumnDef2 = new ColumnDef();

 		childColumnDef2.setHeaderName(headerName2);
		childColumnDef2.setField(field2);
		childColumnDef2.setWidth(width2);
		childColumnDef2.setEditable(editable2);
		childColumnDef2.setColumnGroupShow(columnGroupShow2);
		childColumnDef2.setAggFunc(aggFunc2);
		childColumnDef2.setAggFuncParam(aggFuncParam2);
		childColumnDef2.setType(type2);
		childColumnDef2.setHide(hide2);
		childColumnDef2.setRowGroup(rowGroup2);
		childColumnDef2.setMarryChildren(marryChildren2);
		childColumnDef2.setHeaderClass(headerClass2);
		childColumnDef2.setOpenByDefault(openByDefault2);
		childColumnDef2.setCellClass(cellClass2);
		
		LinkedHashMap<String, ColumnDef> children2 = new LinkedHashMap<String, ColumnDef>();
		children2.put("child2", childColumnDef2);
		childColumnDef.setChildren(children2);
		
		StringBuilder builder = producer.generateConfiguration(columnDef, 1, settings);
		assertNotNull(builder);
		assertThat(builder.toString().length()>0, equalTo(true));
		assertThat(builder.indexOf("\"headerName\"") >0, equalTo(true));

		builder = producer.generateConfiguration(columnDef, 1, settings);
		assertNotNull(builder);
		assertThat(builder.toString().length()>0, equalTo(true));
		assertThat(builder.indexOf("\""+headerName+"\"") , equalTo(-1));
		assertThat(builder.indexOf("\"header2\""), equalTo(14));
		assertThat(builder.indexOf("\""+headerName2+"\"")>0, equalTo(true));
	}

}
