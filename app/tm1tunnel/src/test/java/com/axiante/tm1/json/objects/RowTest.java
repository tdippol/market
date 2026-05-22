package com.axiante.tm1.json.objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RowTest {

	@Spy Row<Cell> row;
	
	@Test public void testIsHeaderReturnsTrueWhenListIsNull() {
		doReturn(null).when(row).get_list();
		assertTrue(row.isHeader());
	}
	@Test public void testIsHeaderReturnsFalseWhenListIsNotNullAndEmpty() {
		doReturn(new ArrayList<Cell>()).when(row).get_list();
		assertFalse(row.isHeader());
	}
	@Test public void testIsHeaderReturnsFalseWhenListIsNotNullAndNotContainsHeader() {
		ArrayList<Cell> mock = new ArrayList<Cell>();
		Cell cell = mock(Cell.class);
		when(cell.getIsHeader()).thenReturn(false);
		mock.add(cell);
		doReturn(mock).when(row).get_list();
		assertFalse(row.isHeader());
	}
	@Test public void testIsHeaderReturnsTrueWhenListIsNotNullAndContainsHeader() {
		ArrayList<Cell> mock = new ArrayList<Cell>();
		Cell cell = mock(Cell.class);
		when(cell.getIsHeader()).thenReturn(true);
		mock.add(cell);
		doReturn(mock).when(row).get_list();
		assertTrue(row.isHeader());
	}
	@Test public void testConstructorReturnsObject() {
		assertNotNull(new Row<Cell>());
	}
	@Test public void testGetSizeCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.size();
		verify(list, times(1)).size();
	}

	@Test public void testIsEmptyCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.isEmpty();
		verify(list, times(1)).isEmpty();
	}
	@Test public void testContainsCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		Cell c = mock(Cell.class);
		row.contains(c);
		verify(list, times(1)).contains(c);
	}
	@Test public void testIteratorCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.iterator();
		verify(list, times(1)).iterator();
	}
	@Test public void testToArrayCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.toArray();
		verify(list, times(1)).toArray();
	}	
	@Test public void testToTypedArrayCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		Cell[] a = new Cell[] {};
		row.toArray(a);
		verify(list, times(1)).toArray(a);
	}	
	@Test public void testAddCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		Cell a = mock(Cell.class);
		row.add(a);
		verify(list, times(1)).add(a);
	}		
	@Test public void testContainsAllInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		ArrayList<Cell> c = new ArrayList<Cell>();
		row.containsAll(c);
		verify(list, times(1)).containsAll(c);
	}		
	@Test public void testAddAllCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		ArrayList<Cell> a = new ArrayList<Cell>();
		row.addAll(a);
		verify(list, times(1)).addAll(a);
	}		
	@Test public void testAddAllAtPositionCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		ArrayList<Cell> a = new ArrayList<Cell>();
		row.addAll(0,a);
		verify(list, times(1)).addAll(0,a);
	}		
	@Test public void testAddAllAtPositionFixesCellColumnPosition() {
		ArrayList<Cell> list = new ArrayList<Cell>();
		Cell mock = mock(Cell.class);
		list.add(mock);
		doReturn(list).when(row).get_list();
		ArrayList<Cell> a = new ArrayList<Cell>();
		Cell insertedMock = mock(Cell.class);
		a.add(insertedMock);
		row.addAll(0,a);
		verify(mock, times(1)).setColumPosition(anyInt());
	}
	@Test public void testAddAllAtPositionReturnsFalseWhenAddingNull() {
		assertFalse(row.addAll(0,null));
	}		
	
	@Test public void testRemoveAllCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		ArrayList<Cell> test = new ArrayList<Cell>();
		row.removeAll(test);
		verify(list, times(1)).removeAll(test);
	}
	
	@Test public void testRetailAllCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		ArrayList<Cell> test = new ArrayList<Cell>();
		row.retainAll(test);
		verify(list, times(1)).retainAll(test);
	}

	@Test public void testClearCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.clear();
		verify(list, times(1)).clear();
	}

	@Test public void testGetCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.get(0);
		verify(list, times(1)).get(0);
	}
	@Test public void testSetCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		Cell test = mock(Cell.class);
		row.set(0, test);
		verify(list, times(1)).set(0, test);
	}
	@Test public void testAddAtIndexCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		Cell test = mock(Cell.class);
		row.add(0, test);
		verify(list, times(1)).add(0, test);
	}
	@Test public void testRemoveCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.remove(0);
		verify(list, times(1)).remove(0);
	}
	@Test public void testIndexOfCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		Cell test = mock(Cell.class);
		row.indexOf(test);
		verify(list, times(1)).indexOf(test);
	}
	@Test public void testLastIndexOfCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		Cell test = mock(Cell.class);
		row.lastIndexOf(test);
		verify(list, times(1)).lastIndexOf(test);
	}
	@Test public void testListIteratorCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.listIterator();
		verify(list, times(1)).listIterator();
	}
	@Test public void testSubListCallsInternalList() {
		@SuppressWarnings("unchecked")
		ArrayList<Cell> list = mock(ArrayList.class);
		doReturn(list).when(row).get_list();
		row.subList(0, 1);
		verify(list, times(1)).subList(0, 1);
	}
	
	@Test public void testEnsureCapacityDoesNothingWhenCapacityGreaterThanParameter() {
		doReturn(1000).when(row).size();
		row.ensureCapacity(99);
		verify(row, never()).add(any(Cell.class));
	}
	
	@Test public void testEnsureCapacityCreatesCorrectNumberOfPads() {
		ArrayList<Cell> list = new ArrayList<Cell>();
		doReturn(list).when(row).get_list();
		row.ensureCapacity(10);
		verify(row, times(10)).add(any(Cell.class));
		assertThat(list, org.hamcrest.CoreMatchers.everyItem(CoreMatchers.equalTo(Table.PAD)));
	}
	@Test public void testGenerateRowKeyReturnsErrorWhenQueryMoreRowsThanSize() {
		doReturn(2).when(row).size();
		row.generateRowKey(10, mock(ObjectMapper.class));
		assertThat(row.getRowKey(), CoreMatchers.equalTo("error generating key"));
	}
	@Test public void testGenerateRowKeyReturnsDataWhenQueryCorrectSize() {
		ArrayList<Cell> list = new ArrayList<Cell>();
		Cell cell = mock(Cell.class);
		when(cell.getName()).thenReturn("mock");
		when(cell.getValue()).thenReturn("value");
		list.add(cell);
		doReturn(list).when(row).get_list();
		ObjectMapper mapper = new ObjectMapper(); //easier to create one that to mock it
		row.generateRowKey(1, mapper);
		assertThat(row.getRowKey(), CoreMatchers.equalTo("{\"mock\":\"value\"}"));
		list.add(cell);
		row.generateRowKey(2, mapper);
		assertThat(row.getRowKey(), CoreMatchers.equalTo("{\"mock\":\"value\",\"mock\":\"value\"}"));
	}
}
