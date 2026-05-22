package com.axiante.tm1.json.objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class Table {
	@Getter
	private int colSize;
	@Setter
	@Getter
	private int actualDataIndex;
	@Setter
	@Getter
	private int actualDataSize;
	@Setter
	@Getter
	private String name;
	@Getter(value = AccessLevel.PROTECTED)
	List<Row<Cell>> rows;
	@Getter
	@Setter
	private String cellSetId;

	private final boolean padding = true;

	@Getter
	@Setter
	private boolean error = false;
	@Getter
	@Setter
	private String errorMessage = null;
	@Getter(value = AccessLevel.PROTECTED)
	private static final ObjectMapper rowKeyMapper = new ObjectMapper();

	@Getter
	@Setter
	/*
	 * the size of the data of the axis2. the index of the data of the axis 1 is
	 * this, the size of the data is actualDataIndex - whereDataSize. If the axis2
	 * is not present then the index of the axis1 is 0 and the size is
	 * actualDataIndex
	 *
	 * This value is used to create rows on the fly
	 *
	 */
	private int whereDataSize = 0;
	@Getter
	@Setter
	/*
	 * this is the expected size. This value is used when creating data on the fly
	 */
	private int expectedSize = 0;

	public static final Cell PAD = new Cell();
	static {
		PAD.setName("padded");
	}

	public Table() {
		this.rows = Collections.synchronizedList(new ArrayList<Row<Cell>>());
	}

	/**
	 *
	 * @param _synchronized
	 * @deprecated use Table() instead
	 */
	@Deprecated
	public Table(final boolean _synchronized) {
		// if (_synchronized)
		// this.rows = Collections.synchronizedList(new ArrayList<Row<Cell>>());
		// else
		// this.rows = new ArrayList<Row<Cell>>();
		// this._synchronized = _synchronized;
		this();
	}

	public int getRowSize() {
		return this.getRows() == null ? 0 : this.getRows().size() - 1; // exclude headers
	}

	public int getColumSize() {
		return this.getHeaders() == null ? 0 : this.getHeaders().size();
	}

	public Point getSize() {
		return new Point(this.getRowSize(), this.getColSize());
	}

	public Cell get(final int row, final int col) {
		if ((row >= this.getRows().size()) || (row < 0)) {
			throw new ArrayIndexOutOfBoundsException(
					"row size is between 0 and " + this.getRows().size() + " but " + row + " was requested");
		}

		if ((col >= this.getColSize()) || (col < 0)) {
			throw new ArrayIndexOutOfBoundsException("col size is between 0 and " + this.getColSize());
		}

		return this.getRows().get(row).get(col);
	}

	public Row<Cell> getHeaders() {
		if (this.getRows().size() > 0) {
			return this.getRows().get(0);
		}
		return Row.getEmptyRow();
	}

	public void addRow(Row<Cell> row) throws IOException {
		if ((this.getRows().size() != 0) && this.padding && (this.colSize != row.size())) {
			if (log.isDebugEnabled())
				log.debug("added row size and table columns differs: trying to pad");
			if (this.getColSize() > row.size()) {
				// the current row has less values than the table... ok no worries
				row = this.padRow(row, this.getColSize());
			} else {
				// ok ...
				this.padTable(this.getColSize());
			}
		}
		this.getRows().add(row);
		this.colSize = this.getHeaders().size();
	}

	public boolean canQueryForCells() {
		return (this.getColSize() > 0) && (this.getRows().size() > 0);
	}

	public void appendHeaders(final Row<Cell> headers) {
		if (this.getRows().size() == 0) {
			this.getRows().add(headers);
		} else {
			if ((this.getHeaders() == null) || (this.getHeaders().size() == 0)) {
				this.getRows().set(0, headers);
			} else {
				this.getHeaders().addAll(headers);
			}
		}

		this.colSize = this.getHeaders().size();
	}

	public void prePendHeaders(final Row<Cell> headers) {
		if (headers != null) {
			if (this.getRows().size() == 0) {
				this.getRows().add(headers);
			} else {
				if ((this.getRows().get(0) == null) || (this.getRows().get(0).size() == 0)) {
					this.getRows().set(0, headers);
				} else {
					this.getHeaders().addAll(0, headers);
				}
			}

			this.colSize = this.getHeaders().size();
		} else {
			if (log.isDebugEnabled())
				log.debug("no headers in variable");
		}
	}

	protected Row<Cell> padRow(@NonNull final Row<Cell> row, final int elements) {
		final int skip = row.size();
		IntStream.range(0, elements - skip).parallel().forEach(i -> {
			row.add(PAD);
		});
		return row;
	}

	protected void padTable(final int elements) {
		log.warn("padding table  of " + elements + " elements");
		if (this.getRows() != null) {
			this.getRows().parallelStream().forEach(row -> {
				IntStream.range(0, elements - row.size()).parallel().forEach(i -> {
					row.add(PAD);
				});
			});
		}
	}

	public void addAll(final Table table) throws NullPointerException {
		this.getRows().addAll(table.getRows());
	}

	public Row<Cell> remove(final int index) {
		return this.getRows().remove(index);
	}

	public Stream<Row<Cell>> parallelStream() {
		return this.getRows().parallelStream();
	}

	public Stream<Row<Cell>> stream() {
		return this.getRows().stream();
	}

	public void clear() {
		this.getRows().clear();
	}

	/**
	 * this calculates the position in which this cell shall fit into and pushes the
	 * cell into the table
	 *
	 * @param cell
	 */
	public boolean pushCell(@NonNull final Cell cell) {
		final int columnIndex = (cell.getOrdinal() % this.getActualDataSize()) + this.getActualDataIndex();
		final int rowIndex = (cell.getOrdinal() / this.getActualDataSize()) + 1;
		Row<Cell> row = null;
		try {
			row = this.getRow(rowIndex);
		} catch (final Exception e) {
			log.warn("Error accessing row at index " + rowIndex, e);
		}
		if (row == null) {
			// wtf ?
			log.warn("row is empty but there should be data here ...");
			row = new Row<>(this.getHeaders().size());
			log.warn("padding row " + row.getRowIndex() + " of " + ((columnIndex - row.size()) + 1) + " elements");
			this.padRow(row, columnIndex + 1); // here we're preparing to add elements: we need to pad for at least one
		} else if (row.size() <= columnIndex) {
			// something's odd here.
			log.warn("table contains less data room than expected: trying to pad");
			log.warn("padding row " + row.getRowIndex() + " of " + (columnIndex - row.size()) + " elements");
			this.padRow(row, columnIndex + 1);
		}

		cell.setColumPosition(columnIndex);
		cell.setName(this.getHeaders().get(columnIndex).getName()); // <-- with this I can calculate the unique key of
																	// the cell starting from the row
		cell.setGridColumn(columnIndex - this.getActualDataIndex());
		row.set(columnIndex, cell);

		return true;
	}

	public List<List<String>> generateSimpleTable() {
		return this.getRows().stream().map(row -> {
			return this.map(row);
		}).collect(Collectors.toList());
	}

	/**
	 * helper method. Maps the Row<Cell> into a List<String> object
	 *
	 * @param row
	 * @return
	 */
	public List<String> map(final Row<Cell> row) {
		if ((row == null) || (row.size() == 0)) {
			return new ArrayList<>(this.getColSize());
		}
		return row.stream().map(cell -> {
			if (cell == null) {
				return "null";
			}
			if (cell.getIsHeader()) {
				return cell.getCaption();
			}
			return cell.getValue();
		}).collect(Collectors.toList());
	}

	/**
	 * This method appends a cell to the headers
	 *
	 * @param cell
	 */
	public void appendHeaderCell(final Cell cell) {
		if (this.getRows().size() == 0) {
			this.getRows().add(new Row<>());
		}
		this.getRows().get(0).add(cell);
	}

	/**
	 * this method inserts a cell in the headers at the specified index
	 *
	 * @param index
	 * @param cell
	 */
	public void appendHeaderCell(final int index, final Cell cell) {
		if (this.getRows().size() == 0) {
			this.getRows().add(new Row<>());
		}
		this.getHeaders().add(index, cell);
	}

	/**
	 * Retutrns the stream of the data rows contained in this table. The headers are
	 * skipped from the content
	 *
	 * @return
	 */
	public Stream<Row<Cell>> getData() {
		if (this.rows == null) {
			return Stream.empty();
		}
		return this.getRows().stream().skip(1);
	}

	/**
	 * this sorts the table by the row index contained in the row
	 */
	public void sort() {
		this.rows = this.getRows().parallelStream().sorted((r1, r2) -> r1.getRowIndex().compareTo(r2.getRowIndex()))
				.collect(Collectors.toList());
	}

	/**
	 * Returns the row at the specified index. The considered index is the position
	 * in the list of rows contained in the current table
	 *
	 * @param index
	 * @return
	 * @throws IllegalAccessException
	 */
	public Row<Cell> getRow(final int index) throws IllegalAccessException {
		if (this.getRows() == null) {
			return null;
		}
		if ((index < 0) || (index > this.getRows().size())) {
			throw new IllegalAccessException("index out of bounds");
		}
		if (index == 0) {
			log.warn("Trying to access headers by index, should use getHeaders() instead !");
			return this.getHeaders();
		}
		return this.getRows().get(index);
	}

	/**
	 * returns the row specified by row index
	 *
	 * @param rowIndex
	 * @return
	 */
	public Row<Cell> getRowByRowIndex(final int rowIndex) {
		if (rowIndex == -1) {
			return this.getHeaders();
		}
		final Row<Cell> ret = this.getRows().parallelStream().filter(r -> {
			return r.getRowIndex() == rowIndex;
		}).findAny().orElse(null);
		if (ret == null) {
			log.warn("could not find requested row with index " + rowIndex);
		}
		return ret;
	}

	/**
	 * This method calculates a cell key which is later needed to perfom updates on
	 * tm1
	 *
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public String calculateCellKey(final int rowIndex, final int colIndex) {
		// the row key is a json??
		// name/value pair of everything from 0 to actualDataIndex + name/value of the
		// cell
		final Row<Cell> row = this.getRowByRowIndex(rowIndex);
		final StringBuilder builder = new StringBuilder("{\"");
		row.stream().limit(this.getActualDataIndex()).forEach(c -> {
			builder.append(c.getName()).append("\"").append(":").append("\"").append(c.getValue()).append("\"")
					.append(",");
		});
		final Cell c = row.get(colIndex);
		builder.append(c.getName()).append("\"").append(":").append("\"").append(c.getValue()).append("\"").append("}");
		return builder.toString();
	}

	public void generateRowKeys(final int numberOfColumnsToUse) {
		this.getRows().parallelStream().forEach(r -> {
			// for each row
			r.generateRowKey(numberOfColumnsToUse, rowKeyMapper);
		});
	}

}
