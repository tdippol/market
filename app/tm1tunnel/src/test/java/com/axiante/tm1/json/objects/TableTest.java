package com.axiante.tm1.json.objects;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TableTest {

	@Spy Table table;
	
	@Test public void testConstructor() {
		assertNotNull(table);
	}
	
	@Test public void testGetRowSizeReturnsZeroWhenEmpty() {
		when(table.getRows()).thenReturn(null);
		assertThat(table.getRowSize(), is(0));
	}
	@SuppressWarnings("unchecked")
	@Test public void testGetRowSizeReturnsInnerListSizeWhenNotEmpty() {
		@SuppressWarnings("rawtypes")
		ArrayList mockedRows = mock(ArrayList.class);
		when(table.getRows()).thenReturn(mockedRows);
		table.getRowSize();
		verify(mockedRows,times(1)).size();
	}
	@SuppressWarnings("unchecked")
	@Test public void testGetColumSizeReturnsInnerListSizeWhenNotEmpty() {
		Row<Cell> mockedRows = mock(Row.class);
		doReturn(mockedRows).when(table).getHeaders();
		table.getColumSize();
		verify(mockedRows,times(1)).size();
	}
	@Test public void testGetSizeCreatesPointWithInnerSize() {
		table.getSize();
		verify(table,times(1)).getRowSize();
		verify(table,times(1)).getColSize();
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testGetThrowsExceptionWhenColParamIsNegative() {
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRows = mock(Row.class);
		doReturn(mockedRows).when(table).getRows();
		when(mockedRows.size()).thenReturn(10);
		table.get(0, -1);
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testGetThrowsExceptionWhenColParamIsGreaterThanOrEqualColSize() {
		int test = 10;
		@SuppressWarnings("unchecked")
		Row<Cell> mockedCols = mock(Row.class);
		doReturn(mockedCols).when(table).getRows();
		when(mockedCols.size()).thenReturn(test);
		table.get(0, test);
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testGetThrowsExceptionWhenRowParamIsNegative() {
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRows = mock(Row.class);
		doReturn(mockedRows).when(table).getRows();
		when(mockedRows.size()).thenReturn(10);
		table.get(-1, 0);
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testGetThrowsExceptionWhenRowParamIsGreaterThanOrEqualColSize() {
		int test = 10;
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRows = mock(Row.class);
		doReturn(mockedRows).when(table).getRows();
		when(mockedRows.size()).thenReturn(test);
		table.get(0, test);
	}

	@Test public void testGetReturnsCell() {
		int test = 10;
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRow = mock(Row.class);
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedRows = mock(ArrayList.class);
		when(mockedRows.get(anyInt())).thenReturn(mockedRow);
		
		doReturn(mockedRows).when(table).getRows();
		doReturn(test+1).when(table).getColSize();
		doReturn(test+1).when(mockedRows).size();
		when(mockedRow.get(test)).thenReturn(mock(Cell.class));
		
		assertNotNull(table.get(test, test));
	}
	
	@Test public void testGetHeaders() {
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRow = mock(Row.class);
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedRows = mock(ArrayList.class);
		when(mockedRows.size()).thenReturn(1); //force get(0)
		when(mockedRows.get(0)).thenReturn(mockedRow);
		doReturn(mockedRows).when(table).getRows();
		assertEquals(mockedRow, table.getHeaders());
	}
	
	@Test public void testGetHeadersReturnsEmptyRowWhenTableIsEmpty() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedRows = mock(ArrayList.class);
		doReturn(mockedRows).when(table).getRows();
		assertEquals(Row.getEmptyRow(), table.getHeaders());
	}
	
	@Test public void testAddRowSimplyAddsWhenRowsizeIsZero() throws IOException {
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		doReturn(mockedContent).when(table).getRows();
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRow = mock(Row.class);
		table.addRow(mockedRow);
		assertThat(table.getRows().size(), is(1));
	}
	@Test public void testAddRowPutsHeadersWhenRowsizeIsZero() throws IOException {
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRow = mock(Row.class);
		Table test = new Table();
		test.addRow(mockedRow);
		assertThat(test.getRowSize(), is(0));
	}
	@Test public void testAddRowPutsRowWhenRowsizeIsNotZero() throws IOException {
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRow = mock(Row.class);
		Table test = new Table();
		test.addRow(mockedRow);
		test.addRow(mockedRow);
		assertThat(test.getRowSize(), is(1));
	}
	@Test public void testAddRowDoesNotPadWhenNotRequested() throws IOException {
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRow = mock(Row.class);
		Table test = new Table();
		test.addRow(mockedRow);
		test.addRow(mockedRow);
		assertThat(test.getRowSize(), is(1));
	}
	
	@Test public void testAddRowPadsWhenRequested() throws IOException {
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		Row<Cell> headers = new Row<Cell>();
		final int expectedCols = 10;
		IntStream.range(0, expectedCols).forEach(i->{headers.add(mock(Cell.class));});
		mockedContent.add(headers);
		doReturn(mockedContent).when(table).getRows();
		doReturn(expectedCols).when(table).getColSize();
		final Row<Cell> row = new Row<Cell>();
		row.add(mock(Cell.class));
		table.addRow(row);
		
		assertThat(row.size(), is(expectedCols));
		assertThat(row.get(5), equalTo(Table.PAD));
	}

	@Test public void testCanQueryForCellsReturnsFalseWhenColSizeLessThanOrEqualsZero() {
		when(table.getColSize()).thenReturn(0);
		assertThat(table.canQueryForCells(),is(false));
		when(table.getColSize()).thenReturn(-1);
		assertThat(table.canQueryForCells(),is(false));
	}
	@Test public void testCanQueryForCellsReturnsFalseWhenRowSizeLessThanOrEqualsZero() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedRows = mock(List.class);
		when(table.getColSize()).thenReturn(1);
		doReturn(mockedRows).when(table).getRows();
		doReturn(0).when(mockedRows).size();
		assertThat(table.canQueryForCells(),is(false));
		doReturn(-1).when(mockedRows).size();
		assertThat(table.canQueryForCells(),is(false));
	}

	@Test public void testCanQueryForCellsReturnsTrueWhenRowSizeAndColSizeGreaterThanZero() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedRows = mock(List.class);
		when(table.getColSize()).thenReturn(1);
		doReturn(mockedRows).when(table).getRows();
		doReturn(1).when(mockedRows).size();
		assertThat(table.canQueryForCells(),is(true));
	}

	@Test public void testAppendHeadersHandlesEmptyTable() {
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		doReturn(mockedContent).when(table).getRows();
		@SuppressWarnings("unchecked")
		Row<Cell> mockedHeaders = mock(Row.class);
		table.appendHeaders(mockedHeaders);
		assertThat(mockedContent.size(), is (1));
		assertThat(mockedContent.get(0), equalTo(mockedHeaders));
	}
	@Test public void testAppendHeadersHandlesEmptyHeaders() {
		final int expectedColSize = 4;
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		doReturn(mockedContent).when(table).getRows();
		@SuppressWarnings("unchecked")
		Row<Cell> mockedHeaders = mock(Row.class);
		when(mockedHeaders.size()).thenReturn(expectedColSize);
		mockedContent.add(null);
		
		table.appendHeaders(mockedHeaders);
		assertThat(mockedContent.size(), is (1));
		assertThat(mockedContent.get(0), equalTo(mockedHeaders));
		assertThat(table.getColSize(), is(expectedColSize));
	}
	@Test public void testAppendHeadersHandlesHeadersAlreadySet() {
		final int expectedColSize = 4;
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		doReturn(mockedContent).when(table).getRows();
		final Row<Cell> initialHeaders = new Row<Cell>();
		IntStream.range(0, expectedColSize).forEach(i->{initialHeaders.add(Table.PAD);});
		Row<Cell> mockedHeaders = new Row<Cell>();
		IntStream.range(0, expectedColSize).forEach(i->{mockedHeaders.add(Table.PAD);});
		
		mockedContent.add(initialHeaders);
		
		table.appendHeaders(mockedHeaders);
		assertThat(mockedContent.size(), is (1));
		assertThat(mockedContent.get(0), equalTo(initialHeaders));
		assertThat(table.getColSize(), is(2*expectedColSize));
		assertThat(table.getHeaders(), everyItem(equalTo(Table.PAD)));
	}
	
	
	@Test public void testPrePendHeadersHandlesEmptyTable() {
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		doReturn(mockedContent).when(table).getRows();
		@SuppressWarnings("unchecked")
		Row<Cell> mockedHeaders = mock(Row.class);
		table.prePendHeaders(mockedHeaders);
		assertThat(mockedContent.size(), is (1));
		assertThat(mockedContent.get(0), equalTo(mockedHeaders));
	}
	
	@Test public void testPrePendHeadersHandlesEmptyHeaders() {
		final int expectedColSize = 4;
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		doReturn(mockedContent).when(table).getRows();
		@SuppressWarnings("unchecked")
		Row<Cell> mockedHeaders = mock(Row.class);
		when(mockedHeaders.size()).thenReturn(expectedColSize);
		mockedContent.add(null);
		
		table.prePendHeaders(mockedHeaders);
		assertThat(mockedContent.size(), is (1));
		assertThat(mockedContent.get(0), equalTo(mockedHeaders));
		assertThat(table.getColSize(), is(expectedColSize));
	}
	@Test public void testPrePendHeadersHandlesHeadersAlreadySet() {
		final int expectedColSize = 4;
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		doReturn(mockedContent).when(table).getRows();
		final Row<Cell> initialHeaders = new Row<Cell>();
		IntStream.range(0, expectedColSize).forEach(i->{initialHeaders.add(Table.PAD);});
		initialHeaders.set(0, Cell.createHeader("test"));
		Row<Cell> mockedHeaders = new Row<Cell>();
		IntStream.range(0, expectedColSize).forEach(i->{mockedHeaders.add(Table.PAD);});
		
		mockedContent.add(initialHeaders);
		
		table.prePendHeaders(mockedHeaders);
		assertThat(mockedContent.size(), is (1));
		assertThat(mockedContent.get(0), equalTo(initialHeaders));
		assertThat(table.getColSize(), is(2*expectedColSize));
		assertThat(table.getHeaders().get(0), equalTo(Table.PAD));
		assertThat(table.getHeaders().get(expectedColSize).getCaption(), equalTo("test"));
	}
	
	@Test(expected = NullPointerException.class) 
	public void testPadRowThrowsExceptionWhenRowIsNull() {
		table.padRow(null, 123);
	}
	@Test public void testPadRowPadsWhenEmptyRow() {
		int expectedCells = 10;
		Row<Cell> test = new Row<Cell>();
		Row<Cell> expect = table.padRow(test, expectedCells);
		assertNotNull(expect);
		assertThat(test, equalTo(expect));
		assertThat(expect, everyItem(equalTo(Table.PAD)));
		assertThat(expect.size(), is(expectedCells));
	}
	
	@Test public void testPadRowPadsWhenNonEmptyRow() {
		int expectedCells = 10;
		Row<Cell> test = new Row<Cell>();
		test.add(Cell.createHeader("test"));
		Row<Cell> expect = table.padRow(test, expectedCells);
		assertNotNull(expect);
		assertThat(test, equalTo(expect));
		assertThat(expect.size(), is(expectedCells));
		assertThat(expect.get(0).getCaption(), equalTo("test"));
		expect.remove(0);
		assertThat(expect, everyItem(equalTo(Table.PAD)));
	}
	
	@Test public void testPadTableHandlesEmptyContent() {
		List<Row<Cell>> mockedContent = null;
		doReturn(mockedContent).when(table).getRows();
		table.padTable(123);
		assertNull(mockedContent);
		mockedContent = new ArrayList<Row<Cell>>();
		table.padTable(123);
		assertThat(mockedContent.size(), is (0));
	}
	
	@Test public void testPadTablePadsExpectedCells() {
		final int expectedSize = 10;
		final List<Row<Cell>> mockedContent = new ArrayList<Row<Cell>>();
		IntStream.range(0, expectedSize).forEach(i->{mockedContent.add(new Row<Cell>());});
		doReturn(mockedContent).when(table).getRows();
		mockedContent.stream().forEach(row->{
			assertThat(row.size(), is (0)); // initial check
		});
		table.padTable(expectedSize);
		assertThat(mockedContent.size(), is (expectedSize)); //no rows added
		mockedContent.stream().forEach(row->{
			assertThat(row.size(), is (expectedSize)); // rows resized
			assertThat(row, everyItem(equalTo(Table.PAD)));
		});
	}
	@Test(expected = NullPointerException.class)
	public void testAddAllThrowsExceptionWhenAddingNull() {
		table.addAll(null);
	}
	
	@SuppressWarnings("unchecked")
	@Test public void testAddAllCallRowsAddAll() {
		Table insert = mock (Table.class);
		List<Row<Cell>> mockedInsertedContent = mock(List.class);
		when(insert.getRows()).thenReturn(mockedInsertedContent);
		List<Row<Cell>> mockedContent = mock(List.class);
		when(table.getRows()).thenReturn(mockedContent);

		table.addAll(insert);
		verify(mockedContent).addAll(ArgumentMatchers.any(List.class));
		verify(insert).getRows();
	}
	
	@Test public void testRemove() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedContent = mock(List.class);
		when(table.getRows()).thenReturn(mockedContent);
		table.remove(0);
		verify(mockedContent).remove(0);
	}
	
	@Test public void testParallelStream() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedContent = mock(List.class);
		when(table.getRows()).thenReturn(mockedContent);
		table.parallelStream();
		verify(mockedContent).parallelStream();
	}
	@Test public void testStream() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedContent = mock(List.class);
		when(table.getRows()).thenReturn(mockedContent);
		table.stream();
		verify(mockedContent).stream();
	}
	@Test public void testClear() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> mockedContent = mock(List.class);
		when(table.getRows()).thenReturn(mockedContent);
		table.clear();
		verify(mockedContent).clear();
	}
	@Test(expected = NullPointerException.class) 
	public void testPushCellThrowsExceptionWhenPushingNullElement() {
		table.pushCell(null);
	}
	
	@Test public void testPushCellHandlesWhenReturnedRowIsNull() {
		int initialOrdinal = 4;
		Cell mockedCell = mock(Cell.class);
		when(mockedCell.getOrdinal()).thenReturn(initialOrdinal);
		doReturn(0).when(table).getActualDataIndex();
		doReturn(initialOrdinal).when(table).getActualDataSize();
		@SuppressWarnings("unchecked")
		Row<Cell> mockedHeaders = mock(Row.class);
		Cell mockedHeader = Cell.createHeader("mock");
		doReturn(mockedHeaders).when(table).getHeaders();
		doReturn(mockedHeader).when(mockedHeaders).get(anyInt());
		assertTrue(table.pushCell(mockedCell));
	}
	
	@Test public void testPushCellHandlesWhenReturnedRowIsSmallerThanPushPosition() throws IllegalAccessException {
		int initialOrdinal = 4;
		Cell mockedCell = mock(Cell.class);
		when(mockedCell.getOrdinal()).thenReturn(initialOrdinal);
		doReturn(4).when(table).getActualDataIndex();
		doReturn(initialOrdinal).when(table).getActualDataSize();
		Row<Cell> mockedInsertionRow = new Row<Cell>();
		doReturn(mockedInsertionRow).when(table).getRow(anyInt());
		@SuppressWarnings("unchecked")
		Row<Cell> mockedHeaders = mock(Row.class);
		Cell mockedHeader = Cell.createHeader("mock");
		doReturn(mockedHeaders).when(table).getHeaders();
		doReturn(mockedHeader).when(mockedHeaders).get(anyInt());
		assertTrue(table.pushCell(mockedCell));
	}

	@Test public void testGenerateSimpleTable() {
		List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		final Row<Cell> row = new Row<Cell>();
		final Cell mockedCell = Cell.createHeader("mock");
		IntStream.range(0, 100).forEach(i->{row.add(mockedCell);});
		content.add(row);
		doReturn(content).when(table).getRows();
		table.generateSimpleTable();
		verify(table).getRows();
		verify(table).map(row);
	}
	
	@SuppressWarnings("unchecked")
	@Test public void testMapHandlesEmptyOrNullRow() {
		doReturn(1).when(table).getColSize();
		assertNotNull(table.map(null));
		assertNotNull(table.map(mock(Row.class)));
	}
	
	@Test public void testMapHandlesNullCell() {
		Row<Cell> row = new Row<Cell>();
		row.add(null);
		assertNotNull(table.map(row));
	}
	
	@Test public void testMapHandlesHeaderCell() {
		Row<Cell> row = new Row<Cell>();
		row.add(Cell.createHeader("mock_header"));
		List<String> test  = table.map(row);
		assertNotNull(test);
		assertThat(test.get(0), containsString("mock_header"));
	}
	
	@Test public void testMapHandlesCell() {
		Row<Cell> row = new Row<Cell>();
		Cell cell = new Cell();
		cell.setValue("mock_value");
		row.add(cell);
		List<String> test  = table.map(row);
		assertNotNull(test);
		assertThat(test.get(0), containsString("mock_value"));
	}

	@Test public void testAppendHeaderCell(){
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		table.appendHeaderCell(mock(Cell.class));
		assertThat(content.size(), is (1));
		assertThat(content.get(0).size(), is (1));
		table.appendHeaderCell(mock(Cell.class));
		assertThat(content.size(), is (1));
		assertThat(content.get(0).size(), is (2));
	}
	
	@Test public void testAppendHeaderCellAtIndex() {
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		content.add(headers);
		table.appendHeaderCell(4, Cell.createHeader("test"));
		assertThat(content.size(), is(1));
		assertThat(headers.size(), is (length+1));
		assertThat(headers.get(4).getCaption(), equalTo("test"));
	}
	@Test public void testGetDataReturnsEmptyStreamWhenTableDataIsNull(){
		Stream<Row<Cell>> test = table.getData();
		assertNull(test.findAny().orElse(null));
	}
	@Test public void testGetDataReturnsEmptyStreamWhenTableHasOnlyHeaders(){
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		content.add(headers);
		Stream<Row<Cell>> test = table.getData();
		assertNull(test.findAny().orElse(null));
	}
	@Test public void testGetDataReturnsStreamWhenTableHasData(){
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		content.add(headers);
		Row<Cell> data = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{data.add(mock(Cell.class));});
		content.add(data);

		Stream<Row<Cell>> test = table.getData();
		assertNotNull(test.findAny());
	}	

	@Test public void testSort() {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> content = mock(List.class);
		doReturn(content).when(table).getRows();
		table.sort();
		verify(table).getRows();
		verify(content).parallelStream();
	}

	
	@Test public void testGetRowReturnsNullWhenRowsIsNull() throws IllegalAccessException {
		when(table.getRows()).thenReturn(null);
		assertNull(table.getRow(2));
	}
	
	@Test(expected = IllegalAccessException.class)
	public void testGetRowThrowsIllegalAccessExceptionWhenIndexLessThanZero() throws IllegalAccessException {
		table.getRow(-1);
	}
	
	@Test(expected = IllegalAccessException.class)
	public void testGetRowThrowsIllegalAccessExceptionWhenIndexGreaterThanRowSize() throws IllegalAccessException {
		@SuppressWarnings("unchecked")
		List<Row<Cell>> content = mock(List.class);
		doReturn(content).when(table).getRows();
		doReturn(1).when(content).size();
		table.getRow(2);
	}

	@Test public void testGetRowReturnsHeadersWhenIndexIsZero() throws IllegalAccessException {
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		content.add(headers);
		when(table.getRows()).thenReturn(content);
		Row<Cell> test = table.getRow(0);
		assertNotNull(test);
		assertThat(test, equalTo(headers));
	}

	@Test public void testGetRowByRowIndexReturnsHeadersWhenIndexIsMinusOne() {
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		content.add(headers);
		when(table.getRows()).thenReturn(content);
		Row<Cell> test = table.getRowByRowIndex(-1);
		assertNotNull(test);
		assertThat(test, equalTo(headers));
	}

	@Test public void testGetRowByRowIndexReturnsNullWhenNotFound() {
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		headers.setRowIndex(5);
		content.add(headers);
		when(table.getRows()).thenReturn(content);
		assertNull(table.getRowByRowIndex(1));
	}
	@Test public void testGetRowByRowIndexReturnsRowWhenFound() {
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		headers.setRowIndex(5);
		content.add(headers);
		when(table.getRows()).thenReturn(content);
		Row<Cell> test = table.getRowByRowIndex(5);
		assertNotNull(test);
		assertThat(test, equalTo(headers));
	}

	@Test public void testCalculateCellKey() {
		int length = 10;
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		Row<Cell> headers = new Row<Cell>();
		IntStream.range(0, length).forEach(i->{headers.add(Cell.createHeader("mock_header_" + i));});
		headers.setRowIndex(5);
		content.add(headers);
		when(table.getRows()).thenReturn(content);
		when(table.getActualDataIndex()).thenReturn(2);
		String test = table.calculateCellKey(5, 3);
		String expected = "{'mock_header_0':'null',mock_header_1':'null',mock_header_3':'null'}".replace("\'", "\"");
		assertThat(test, equalTo(expected));
	}
	
	@Test public void testGenerateRowkey() {
		final List<Row<Cell>> content = new ArrayList<Row<Cell>>();
		doReturn(content).when(table).getRows();
		@SuppressWarnings("unchecked")
		Row<Cell> mockedRow = mock(Row.class);
		content.add(mockedRow);
		table.generateRowKeys(5);
		verify(mockedRow).generateRowKey(5, Table.getRowKeyMapper());
	}
}

