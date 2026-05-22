package com.axiante.tm1.json.objects;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CellTest {
	@Spy Cell cell;

	@Test public void testCreateHeader() {
		String check = "test";
		Cell  test = Cell.createHeader(check);
		assertTrue(test.getIsHeader());
		assertThat(test.getName(), equalTo(check));
		assertThat(test.getCaption(), equalTo(check));
	}
	@Test public void testCreateHeaderFromConfiguration() {
		String check = "test";
		Cell  test = Cell.createHeaderFromConfiguration(check);
		assertTrue(test.getIsHeader());
		assertTrue(test.isFromConfiguration());
		assertThat(test.getName(), equalTo(check));
		assertThat(test.getCaption(), equalTo(check));
	}
	
	@Test public void testCalculateUpdate() {
		String test = "11110001001000000";
		Long number = Long.parseLong(test, 2);
		cell.calculateUpdate(number);
		assertFalse(cell.getEditable());
		
		test = "11110001001000010";
		number = Long.parseLong(test, 2);
		cell.calculateUpdate(number);
		assertTrue(cell.getEditable());
	}
	
	@Test public void testAddPickElementCreatesListOnFirstAdd() {
		Cell c = new Cell();
		assertNull(c.getPickList());
		c.addPickElement("test");
		assertThat(c.getPickList(), everyItem(equalTo("test")));
	}

	@Test public void testAddPickElementHandlesNullValue() {
		Cell c = new Cell();
		c.addPickElement(null);
		assertThat(c.getPickList(), everyItem(equalTo("null")));
	}
	@Test public void testAddAttributeAddsAttributeWhenNotPresent() {
		Cell c = new Cell();
		c.addAttribute("mock", "value");
		assertNotNull(c.getAttribute("mock"));
		assertThat(c.getAttribute("mock"), everyItem(equalTo(new CellAttribute("value"))));
	}
	
	@Test public void testAddAttributeAddsAttributeAndIncrementsIndexWhenPresent() {
		Cell c = new Cell();
		c.addAttribute("mock", "value");
		assertNotNull(c.getAttribute("mock"));
		CellAttribute test = c.getAttribute("mock").get(0);
		c.addAttribute("mock", "value");
		assertThat(c.getAttribute("mock").size(), is(2));
		CellAttribute _test = c.getAttribute("mock").get(1);
		assertThat(test.getValue(),equalTo("value"));
		assertThat(test.getValue(),equalTo(_test.getValue()));
		assertThat(test.getIndex(),not(equalTo(_test.getIndex())));
		assertThat(_test.getIndex(), equalTo(test.getIndex() + 1));
	}
	
	@SuppressWarnings("deprecation")
	@Test public void testDeprecatedGetAgGridCellCallsNoParamMethod() {
		cell.getAgGridCell("something", new Random().nextInt());
		verify(cell, times(1)).getAgGridCell();
	}
	@Test public void testGetAgGridCellReturnsOddStringWhenCellIsEmpty() {
		Cell c = new Cell();
		String expected = "'cella null / null':{'Updateable':false,'HasPicklist':false,'Value':null,'Name':null,'Consolidated':false}".replace("\'", "\"");
		String test = c.getAgGridCell();
		assertThat(test, equalTo(expected));
	}
	@Test public void testGetAgGridCellReturnsString() {
		when(cell.getName()).thenReturn("moked_name");
		String expected = "'moked_name':{'Updateable':false,'HasPicklist':false,'Value':null,'Name':null,'Consolidated':false}".replace("\'", "\"");
		assertThat(cell.getAgGridCell(), equalTo(expected));
		when(cell.getValue()).thenReturn("mocked_value");
		expected = "'moked_name':{'Updateable':false,'HasPicklist':false,'Value':'mocked_value','Name':'mocked_value','Consolidated':false}".replace("\'", "\"");
		assertThat(cell.getAgGridCell(), equalTo(expected));
		cell.addAttribute("mocked_attribute", "attribute_value");
		expected = "'moked_name':{'mocked_attribute':'attribute_value','Updateable':false,'HasPicklist':false,'Value':'mocked_value','Name':'mocked_value','Consolidated':false}".replace("\'", "\"");
		assertThat(cell.getAgGridCell(), equalTo(expected));
		cell.addPickElement("mocked_picklist");
		expected = "'moked_name':{'mocked_attribute':'attribute_value','Updateable':false,'HasPicklist':false,'Value':'mocked_value','Name':'mocked_value','Consolidated':false}".replace("\'", "\"");
		assertThat(cell.getAgGridCell(), equalTo(expected));
		when(cell.getHasPickList()).thenReturn(true);
		expected = "'moked_name':{'mocked_attribute':'attribute_value','Updateable':false,'HasPicklist':true,'Value':'mocked_value','Name':'mocked_value','Consolidated':false,'GridColumn':null,'PicklistValues':['mocked_picklist']}".replace("\'", "\"");
		assertThat(cell.getAgGridCell(), equalTo(expected));
	}
	
	@Test (expected = NullPointerException.class) 
	public void testSetNameThrowsExceptionWhenSettingNull() {
		cell.setName(null);
	}
}
