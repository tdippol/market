package com.axiante.tm1.json;

import com.axiante.mui.common.interfaces.MuiConnectionListener;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tm1JsonReader implements MuiConnectionListener {

    @Getter
    private int expectedRows = -1;
    private String name;
    @SuppressWarnings("unused")
    private boolean tableNotInitialized;
    private JsonFactory jasonFactory = new JsonFactory();
    private static final String NULL_VALUE = "null";

    private boolean connectionClosing = false;

    /**
     * reads tm1 json formatted data from a stream that contains the definitions of
     * the axes
     * 
     * @param in
     * @return
     * @throws IOException
     */

    public Table readAxes(@NonNull final InputStream in) throws IOException {
        return readAxes(in, false);
    }

    /**
     * reads tm1 json formatted data from a stream that contains the definitions of
     * the axes
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public Table readAxes(@NonNull final InputStream in, boolean attributesData) throws IOException {
        return readAxes(in, attributesData, false);
    }

    public Table readAxes(@NonNull final InputStream in, boolean attributesData, boolean headersOnly)
            throws IOException {
        log.debug("opening stream for parsing json data");

        Table table = new Table();
        try (JsonParser parser = jasonFactory.createParser(in);) {
            JsonToken token = parser.nextToken();
            if (token != JsonToken.START_OBJECT) {
                log.error("expected start object");
            } else {
                boolean endLoop = false;
                if (!connectionClosing) {
                    while (token != JsonToken.END_OBJECT && !endLoop) {
                        switch (token) {
                        case FIELD_NAME:
                            // read what we have
                            String field = parser.getCurrentName();
                            switch (field) {
                            case "Axes":
                                table = readAxes(parser, attributesData, headersOnly);
                                endLoop = true;
                                break;

                            default:
                                break;
                            }
                            break;
                        default:
                            break;
                        }
                        token = parser.nextToken();
                    }
                }
            }
        }

        this.tableNotInitialized = false;
        return table;
    }

    public Integer[] readAxesCardinality(@NonNull final InputStream in) throws IOException {
        log.debug("opening stream for parsing json data");
        Integer[] table = null;
        if (!connectionClosing) {
            try (JsonParser parser = jasonFactory.createParser(in);) {
                JsonToken token = parser.nextToken();
                if (token != JsonToken.START_OBJECT) {
                    log.error("expected start object");
                } else {
                    while (token != JsonToken.END_OBJECT) {
                        switch (token) {
                        case FIELD_NAME:
                            // read what we have
                            String field = parser.getCurrentName();
                            switch (field) {
                            case "Axes":
                                table = readAxesCardinality(parser);
                                break;

                            default:
                                break;
                            }
                            break;
                        default:
                            break;
                        }
                        token = parser.nextToken();
                    }
                }
            }
        }
        this.tableNotInitialized = false;
        return table;
    }

    private Integer[] readAxesCardinality(@NonNull final JsonParser parser) throws IOException {
        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected begin array");
            return null;
        } else {
            boolean objectStarted = false;
            boolean objectEnded = false;
            currentToken = parser.nextToken();
            String field = null;
            CardinalityReader reader = null;
            List<CardinalityReader> list = new ArrayList<Tm1JsonReader.CardinalityReader>();
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    if (currentToken == JsonToken.START_OBJECT) {
                        objectStarted = true;
                        objectEnded = false;
                        reader = new CardinalityReader();
                    } else if (currentToken == JsonToken.END_OBJECT) {
                        objectEnded = true;
                        objectStarted = false;
                        list.add(reader);
                    }
                    if (objectStarted && !objectEnded) {
                        currentToken = parser.nextToken();
                        field = parser.getCurrentName();
                        if ("Ordinal".equals(field)) {
                            reader.setOrdinal(JsonUtils.readInt(parser));
                        } else if ("Cardinality".equals(field)) {
                            reader.setCardinality(JsonUtils.readInt(parser));
                        } else if (currentToken != JsonToken.END_OBJECT) {
                            log.debug("unexpected value " + field + " in cardinality check");
                        }
                    } else {
                        currentToken = parser.nextToken();
                    }
                }
            }
            return list.stream().map(CardinalityReader::getCardinality).collect(Collectors.toList())
                    .toArray(new Integer[list.size()]);
        }
    }

    /**
     * this reads the cell set data from an input stream into the table data
     * 
     * @param table
     * @param in
     * @return
     * @throws IOException
     */
    public void readCellSetData(@NonNull final Table table, @NonNull final InputStream in) throws IOException {
        log.debug("creating json iterator object");
        if (!connectionClosing) {
            try (JsonParser parser = jasonFactory.createParser(in);) {
                JsonToken token = parser.nextToken();
                if (token != JsonToken.START_OBJECT) {
                    log.error("expected start object");
                } else {
                    boolean limitReached = false;
                    while (token != JsonToken.END_OBJECT && !limitReached) {
                        if (JsonToken.FIELD_NAME.equals(token) && JsonConstants.Cells.equals(parser.getCurrentName()))
                            limitReached = !readCellSetData(table, parser);
                        token = parser.nextToken();
                    }
                }
            }
        }
    }

    /**
     * This method reads a list of cells
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public List<Cell> readCells(@NonNull final InputStream in) throws IOException {
        log.debug("creating json iterator object");
        List<Cell> list = new ArrayList<Cell>();
        if (!connectionClosing) {
            try (JsonParser parser = jasonFactory.createParser(in);) {
                JsonToken token = parser.nextToken();
                if (token != JsonToken.START_OBJECT) {
                    log.error("expected start object");
                } else {
                    // ################
                    while (token != null) {
                        if (JsonToken.FIELD_NAME.equals(token) && JsonConstants.Cells.equals(parser.getCurrentName())) {
                            break;
                        }
                        token = parser.nextToken();
                    }
                    // ###############
                    if ( token != null ) {
                        // ok, there are cells ... and the token is on the field "Cells"
                        if ( parser.nextToken() != JsonToken.START_ARRAY) {
                            // malformed cells
                            log.error("expected start array, found " + token != null ? token.asString() : "null");
                        } else {
                            token = parser.nextToken();
                            while (token != JsonToken.END_ARRAY) {
                                if (token  == JsonToken.START_OBJECT) {
                                    list.add(readDataCell(parser));
                                }
                                token = parser.nextToken();
                            }

                        }
                        
                    }
                }
            }
        }
        return list;
    }

    private Table readAxes(@NonNull final JsonParser parser, boolean attributesData, boolean headersOnly)
            throws IOException {
        Table table = new Table();
        Table extraData = new Table();
        Row<Cell> whereClauseHeaders = null;
        List<String> colNames = null;
        final Row<Cell> whereClauseContent = new Row<Cell>();

        JsonToken currentToken = parser.nextToken();
        boolean objectStarted = false;
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected begin array");
            return null;
        } else {
            int ordinal = -1;
            int cardinality = -1;
            boolean prematureEndRead = false;
            while (currentToken != JsonToken.END_ARRAY && !prematureEndRead) {
                if (connectionClosing)
                    break;
                currentToken = parser.nextToken();
                if (objectStarted) {
                    if (currentToken == JsonToken.END_OBJECT) {
                        // end of axes
                        log.debug("###### axes " + ordinal + " ends");
                        objectStarted = false;
                    } else {
                        // read data
                        String field = parser.getCurrentName();
                        switch (field) {
                        case JsonConstants.Ordinal:
                            ordinal = JsonUtils.readInt(parser);
                            break;
                        case JsonConstants.Cardinality:
                            cardinality = JsonUtils.readInt(parser);
                            break;
                        case JsonConstants.Hierarchies:
                            switch (ordinal) {
                            case 0:
                                colNames = readNamesInHierarchy(parser); // this is the list of names to pass when
                                                                         // reading header names as they might be
                                                                         // combinedl
                                table.setActualDataSize(cardinality);
                                table.setName(this.name);
                                break;
                            case 1:
                                // here we have extra columns to be pre-pended to the list of headers
                                extraData.appendHeaders(readExtraHeadersInHierarchies(parser));
                                expectedRows = cardinality;
                                extraData.setExpectedSize(expectedRows);
                                break;
                            case 2:
                                // here we're reading the headers of the "fixed" data, the one that is in the
                                // where clause
                                whereClauseHeaders = readWhereClauseHeaders(parser);
                                break;
                            default:
                                log.error("Unimplemented handling of ordinal " + ordinal);
                                break;
                            }
                            break;
                        case JsonConstants.Tuples:
                            switch (ordinal) {
                            case 0:
                                // on the first row we put the headers
                                table.addRow(readTuplesForHeaders(parser, cardinality, colNames, attributesData));
                                break;
                            case 1:
                                // check the the tuples for ordinal 1 : here we have the actual values of the
                                // cells that
                                // have been pre-pended to the table so they go in extra data
                                if (!headersOnly)
                                    extraData.addAll(readExtraDataInTuples(parser, extraData.getHeaders()));
                                else
                                    // finished reading as we are not going to read tuples other than for
                                    // axis 0
                                    prematureEndRead = true;
                                break;
                            case 2:
                                // check the the tuples for ordinal 2 : here we have the actual values of the
                                // cells
                                if (!headersOnly)
                                    whereClauseContent.addAll(readWhereClauseContentInTuples(parser));

                                break;
                            default:
                                log.error("Unimplemented handling of Tuples with ordinal " + ordinal);
                                break;
                            }
                            break;
                        default:
                            break;
                        }
                    }
                } else {
                    // check that the object starts
                    if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.END_ARRAY) {
                        log.error("expected start object or end array after start array");
                        return null;
                    } else {
                        log.debug("###### axes " + (++ordinal) + " starts");
                        objectStarted = true;
                    }
                }
            }
        }
        if (!connectionClosing) {
            table = construct(table, extraData, whereClauseHeaders, whereClauseContent);
            // when inserting the actual cell content, remember that they must fill cells
            // after the
            // data that we have already put in the table
            table.setActualDataIndex(extraData.getHeaders().size() + whereClauseContent.size());//
            table.setWhereDataSize(whereClauseContent.size());
            table.setExpectedSize(extraData.getExpectedSize());
            if (extraData != null)
                extraData.clear();
            if (whereClauseHeaders != null)
                whereClauseHeaders.clear();
            if (whereClauseContent != null)
                whereClauseContent.clear();

            extraData = null;
            whereClauseHeaders = null;
        }
        return table;
    }

    /**
     * Assembles the table by putting together the pieces read in
     * {@link #readAxes(InputStream)}
     * 
     * @param columnsTable
     * @param extraDataTable
     * @param whereClauseHeaders
     * @param whereClauseData
     * @return
     */
    private Table construct(final Table columnsTable, final Table extraDataTable, Row<Cell> whereClauseHeaders,
            Row<Cell> whereClauseData) {
        int columnIndex = extraDataTable.getColSize();
        if (whereClauseHeaders != null) {
            columnIndex += whereClauseHeaders.size(); // number of columns needed for rowkey
        }

        // need to set the column name in whereClauseData
        for (int i = 0; i < whereClauseData.size(); ++i) {
            whereClauseData.get(i).setName(whereClauseHeaders.get(i).getName());
        }
        // create a row for the collated headers: this means prepending stuff to the
        // columns
        // we already have
        columnsTable.prePendHeaders(extraDataTable.getHeaders());
        columnsTable.prePendHeaders(whereClauseHeaders);

        // now, for each row in extra data create a row that contains the correct value:
        // the leftmost cells are coming from whereclause, then the data in extra data

        // 1. calculate the number of columns
        final AtomicBoolean interrupt = new AtomicBoolean(false);
        extraDataTable.getData().parallel().forEach(row -> {
            if (!interrupt.get()) {
                // 2. create a row with the correct size
                // 3. fill the leftmost data
                row.addAll(0, whereClauseData);
                // 4. fill the next data
                try {
                    columnsTable.addRow(row);
                } catch (IOException e) {
                    interrupt.set(true);
                }
            }
        });

        // now, sort the table rows by row index
        columnsTable.sort();

        columnsTable.generateRowKeys(columnIndex);
        columnsTable.setExpectedSize(columnsTable.getRowSize() * columnsTable.getHeaders().size());
        return columnsTable;
    }

    /**
     * this reads the content of the columns that are "fixed" by the where clause.
     * The json here is a json array
     * 
     * @param iter
     * @return
     * @throws IOException
     */
    private Row<Cell> readWhereClauseContentInTuples(@NonNull JsonParser parser) throws IOException {

        Row<Cell> ret = new Row<Cell>();

        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of tuples");
        } else {
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    currentToken = parser.nextToken();
                    if (currentToken == JsonToken.START_OBJECT) {
                        // new tuple
                        ret.addAll(readWhereClauseContent(parser));
                    } else if (currentToken == JsonToken.END_OBJECT) {
                    } else if (currentToken == JsonToken.FIELD_NAME) {
                    } else {
                        // something else we don't care about
                    }
                }
            }
        }
        return ret;
    }

    /**
     * we are reading the value of the attribute JsonConstants.Name within the json
     * array of hierarchies
     * 
     * @param iter
     * @return
     * @throws IOException
     */

    public List<String> readNamesInHierarchy(@NonNull final JsonParser parser) throws IOException {
        List<String> names = new ArrayList<String>();
        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of hierarchies");
        } else {
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    currentToken = parser.nextToken();
                    switch (currentToken) {
                    case START_OBJECT:
                        break;
                    case END_OBJECT:
                        break;
                    case FIELD_NAME:
                        if (JsonConstants.Name.contentEquals(parser.getCurrentName()))
                            names.add(JsonUtils.readString(parser));
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        return names;
    }

    /**
     * Reads the tuples and returns a list of cells containing the headers of the
     * table
     * 
     * @param iter
     * @param cardinality
     * @return
     * @throws IOException
     */

    private Row<Cell> readTuplesForHeaders(@NonNull final JsonParser parser, final int cardinality,
            final List<String> names, boolean attributesData) throws IOException {
        Row<Cell> headers = new Row<Cell>(cardinality);
        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of tuples");
        } else {
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    currentToken = parser.nextToken();
                    if (currentToken == JsonToken.FIELD_NAME) {
                        String field = parser.getCurrentName();
                        switch (field) {
                        case JsonConstants.Members:
                            if (attributesData)
                                headers.addAll(readMembersForAttributesHeaders(parser));
                            else
                                headers.addAll(readMembersForHeaders(parser, names));
                            break;
                        default:
                            // something we don't care
                            break;
                        }
                    }
                }
            }
        }
        return headers;
    }

    /**
     * here we're reading the json array of the Members and returning a list of
     * cells
     * 
     * @param iter
     * @return
     * @throws IOException
     */

    private List<Cell> readMembersForAttributesHeaders(@NonNull final JsonParser parser) throws IOException {
        List<Cell> members = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of members");
        } else {
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    currentToken = parser.nextToken();
                    if (currentToken == JsonToken.FIELD_NAME) {
                        String field = parser.getCurrentName();
                        switch (field) {
                        case JsonConstants.Name:
                            buffer.append(JsonUtils.readString(parser));
                            break;
                        default:
                            break;
                        }
                    }
                }
            }
        }
        // done reading the member, push the object in the return variable

        members.add(Cell.createHeader(buffer.toString()));
        return members;
    }

    /**
     * here we're reading the extra column headers from the Hierarchies in ordinal 2
     * 
     * @param iter
     * @return
     * @throws IOException
     */
    private Row<Cell> readExtraHeadersInHierarchies(@NonNull final JsonParser parser) throws IOException {
        Row<Cell> extraHeaders = new Row<Cell>();
        Cell cell = null;

        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of hierarchies");
        }
        String fieldName;
        if (!connectionClosing) {
            while (currentToken != JsonToken.END_ARRAY) {
                currentToken = parser.nextToken();
                if (currentToken == JsonToken.FIELD_NAME) {
                    fieldName = parser.getCurrentName();
                    switch (fieldName) {
                    case JsonConstants.Name:
                        cell = Cell.createHeader(JsonUtils.readString(parser));
                        break;
                    }
                } else if (currentToken == JsonToken.END_OBJECT) {
                    // end of hierarchy
                    if (cell != null)
                        extraHeaders.add(cell);
                } else {
                    // something else we don't care about
                }
            }
        }
        return extraHeaders;
    }

    /**
     * here we're reading the json array of the Members and returning a list of
     * cells
     * 
     * @param iter
     * @return
     * @throws IOException
     */

    private List<Cell> readMembersForHeaders(@NonNull final JsonParser parser, @NonNull final List<String> names)
            throws IOException {
        /**
         * -- passa l'array dei valori letti in hierarchies per ogni nuovo Ordinal: -
         * crea la cella, - metti il contatore a 0 - il valore dell'header e'
         * hierarchies [contatore]$name - salta gli attributi - se appendi il valore a
         * header, in header ci prepende $$
         * 
         * Es.: Year$2016$$Month$M01
         * 
         */
        List<Cell> members = new ArrayList<>();
        int index = 0;
        StringBuffer buffer = new StringBuffer();
        Cell dummyCell = new Cell();
        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of members");
        } else {
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    currentToken = parser.nextToken();
                    if (currentToken == JsonToken.FIELD_NAME) {
                        String field = parser.getCurrentName();
                        switch (field) {
                        case JsonConstants.Name:
                            if (buffer.length() > 0) {
                                buffer.append("$$");
                            }
                            buffer.append(names.get(index)).append("$").append(JsonUtils.readString(parser));
                            ++index;
                            break;
                        case JsonConstants.Attributes:
                            addAttributesToCell(parser, dummyCell, false);
                            break;

                        default:
                            break;
                        }
                    }
                }
            }
        }
        // done reading the member, push the object in the return variable
        Cell cell = Cell.createHeader(buffer.toString());
        cell.setAttributes(dummyCell.getAttributes());

        members.add(cell);
        return members;
    }

    /**
     * we're reading a table, minus the headers, of the extra data, meaning the data
     * that is returned in the the json array Tuples of the Axes 1
     * 
     * @param iter
     * @return
     * @throws IOException
     */
    private Table readExtraDataInTuples(@NonNull final JsonParser parser, @NonNull final Row<Cell> headers)
            throws IOException {
        Table extraData = new Table();
        int ordinal = -1;

        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of tuples");
        } else {
            boolean full = false;
            Row<Cell> data = null;
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    currentToken = parser.nextToken();
                    if (currentToken == JsonToken.FIELD_NAME) {
                        String field = parser.getCurrentName();
                        switch (field) {
                        case JsonConstants.Ordinal:
                            ordinal = JsonUtils.readInt(parser);
                            break;
                        case JsonConstants.Members:
                            data = readExtraDataInMembers(parser, ordinal, headers, full);
                            if (!full) {
                                try {
                                    extraData.addRow(data);
                                } catch (IOException e) {
                                    full = true;
                                }
                            }
                            break;
                        default:
                            // something we don't care
                            break;
                        }
                    } else {
                        // something else we don't care about
                    }
                }
            }
        }
        return extraData;
    }

    /**
     * we're reading a tuple content, of extra data, meaning that we're reading the
     * json array of Member elements contained in Members, of the Axes 1
     * 
     * @param iter
     * @return
     * @throws IOException
     */
    public Row<Cell> readExtraDataInMembers(@NonNull final JsonParser parser, final int ordinal,
            @NonNull final Row<Cell> headers, boolean full) throws IOException {
        Row<Cell> extraData = null;

        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of members");
        } else {
            Cell cell = null;
            if (!full) {
                // initialize
                extraData = new Row<Cell>();
                extraData.setRowIndex(ordinal + 1); // ordinal 0 equals row 1 as row 0 means headers
            }
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY) {
                    currentToken = parser.nextToken();
                    if (!full) {
                        if (currentToken == JsonToken.START_OBJECT) {
                            cell = new Cell();
                            cell.setName(headers.get(extraData.size()).getName());
                        } else if (currentToken == JsonToken.END_OBJECT) {
                            extraData.add(cell);
                        } else if (currentToken == JsonToken.FIELD_NAME) {
                            String field = parser.getCurrentName();
                            switch (field) {
                            case JsonConstants.Name:
                                cell.setValue(JsonUtils.readString(parser));
                                break;
                            case JsonConstants.Attributes:
                                // here we have the attributes of the cell, try to add stuff to it
                                addAttributesToCell(parser, cell);
                                if (cell.getCaption() == null) {
                                    // the cell has no caption, set the name as default caption
                                    cell.setCaption(cell.getName());
                                }
                                break;
                            default:
                                break;
                            }
                        } else {
                            // something else we don't care about
                        }
                    }
                }
            }
        }
        return extraData;
    }

    /**
     * This adds the attributes to the cell.
     * 
     * @param iter : the iterator over the json content
     * @param cell : the cell where the content goes into
     * @throws IOException
     */
    private void addAttributesToCell(@NonNull final JsonParser parser, @NonNull final Cell cell) throws IOException {
        addAttributesToCell(parser, cell, true);
    }

    private void addAttributesToCell(@NonNull final JsonParser parser, @NonNull final Cell cell,
            final boolean readCaption) throws IOException {

        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_OBJECT) {
            log.error("expected attributes object");
            return;
        }
        Matcher matcher = JsonUtils.jsonStringPatternFix.matcher("");
        while (currentToken != JsonToken.END_OBJECT) {
            currentToken = parser.nextToken();

            if (currentToken == JsonToken.FIELD_NAME) {
                switch (parser.getCurrentName()) {
                case JsonConstants.Caption:
                    // at the moment we're interested in the caption, if exists
                    if (readCaption)
                        cell.setCaption(JsonUtils.readString(parser));
                    else
                        cell.addAttribute(parser.getCurrentName(),
                                matcher.reset(JsonUtils.readRawObject(parser)).replaceAll(""));
                    break;
                default:
                    // was : cell.addAttribute(field, iter.readAny().toString());
                    cell.addAttribute(parser.getCurrentName(),
                            matcher.reset(JsonUtils.readRawObject(parser)).replaceAll(""));
                    break;
                }
            } else {
                // we don't really care
                if (currentToken != JsonToken.END_OBJECT)
                    parser.nextToken();
            }
        }
    }

    /**
     * Reads the header of the where clause: this data is "fixed" in the returned
     * json
     * 
     * @param iter
     * @return
     * @throws IOException
     */
    private Row<Cell> readWhereClauseHeaders(@NonNull JsonParser parser) throws IOException {
        Row<Cell> headers = new Row<Cell>();
        Cell cell = null;
        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected array of hierarchies");
        } else {
            while (currentToken != JsonToken.END_ARRAY) {
                currentToken = parser.nextToken();
                if (currentToken == JsonToken.START_OBJECT) {
                    // new hierarchy
                    if (!connectionClosing) {
                        while (currentToken != JsonToken.END_OBJECT) {
                            currentToken = parser.nextToken();
                            if (currentToken == JsonToken.FIELD_NAME) {
                                switch (parser.getCurrentName()) {
                                case JsonConstants.Name:
                                    cell = Cell.createHeader(JsonUtils.readString(parser));
                                    break;
                                case JsonConstants.Caption:
                                    cell.setCaption(JsonUtils.readString(parser));
                                default:
                                    break;
                                }
                            }
                        }
                    }
                    if (cell.getCaption() == null)
                        cell.setCaption(cell.getName());

                    headers.add(cell);
                }
            }
        }
        return headers;
    }

    /**
     * reads the content of the "where clause" that is fixed in the returned json.
     * The json, here, is the content of a json array of the form
     * 
     * <pre>
     * {
     *     Ordinal: <number>,
     *     Members:[
     *     	{
     *        Name: <string>
     *        [some skipped content]: [skipped],
     *        Attributes:{
     *        	Caption: <string if exists>,
     *          [skipped content]: [skipped]
     *        }
     *     ]
     * }
     * </pre>
     * 
     * @param iter
     * @return
     * @throws IOException
     */
    private List<Cell> readWhereClauseContent(@NonNull JsonParser parser) throws IOException {
        // no need to call other stuff: the piece of json is of the form :
        // Ordinal,
        // Members[{name, stuff,attributes}]
        // so we might as well read it in one shot
        List<Cell> result = new ArrayList<>();
        JsonToken currentToken = parser.getCurrentToken();
        while (currentToken != JsonToken.END_OBJECT) {
            currentToken = parser.nextToken();
            if (currentToken == JsonToken.FIELD_NAME) {
                String field = parser.getCurrentName();
                switch (field) {
                case JsonConstants.Members:
                    Cell cell = null;
                    while (currentToken != JsonToken.END_ARRAY) {
                        currentToken = parser.nextToken();

                        if (currentToken == JsonToken.END_OBJECT) {
                            // end of this member
                            if (cell != null) {
                                if (cell.getName() == null) {
                                    cell.setName("oddly empty from readWhereClauseContent");
                                }
                                result.add(cell);
                            }
                            cell = null;
                        } else if (currentToken == JsonToken.START_OBJECT) {
                            // this member starts
                            cell = new Cell();
                        } else if (currentToken == JsonToken.FIELD_NAME) {
                            switch (parser.getCurrentName()) {
                            case JsonConstants.Name:
                                cell.setValue(JsonUtils.readString(parser));
                                break;
                            case JsonConstants.UniqueName:
                                cell.setName(JsonUtils.readString(parser).split("\\]\\.\\[")[0].replaceAll("\\[", ""));
                                break;
                            case JsonConstants.Attributes:
                                while (currentToken != JsonToken.END_OBJECT) {
                                    currentToken = parser.nextToken();
                                    if (currentToken == JsonToken.FIELD_NAME
                                            && JsonConstants.Caption.contentEquals(parser.getCurrentName())) {
                                        cell.setCaption(JsonUtils.readString(parser));
                                    }
                                }
                                break;
                            default:
                                break;
                            }
                        }
                    }
                    break;

                default:
                    break;
                }
            }
        }
        return result;
    }

    /**
     * reads the cellset data into the table passed as parameter. The table
     * parameter should be generated by the readAxes method; if this is not the
     * case, the control flags should be set accordingly . The data in the "Cells"
     * json tag is a json array
     * 
     * @param table
     * @param iter
     * @throws IOException
     */
    public boolean readCellSetData(@NonNull final Table table, @NonNull final JsonParser parser) throws IOException {
        boolean limitReached = false;
        JsonToken currentToken = parser.nextToken();
        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected begin array");
        } else {
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY && !limitReached) {
                    if (currentToken == JsonToken.START_OBJECT) {
                        limitReached = !table.pushCell(readDataCell(parser));
                    }
                    currentToken = parser.nextToken();
                }
            }
        }
        return limitReached;
    }

    /**
     * reads a single data cell.
     * 
     * @param iter
     * @return
     * @throws IOException
     */
    public Cell readDataCell(@NonNull final JsonParser parser) throws IOException {
        // {"Ordinal":0,"Value":null,"FormattedValue":"0.00","Updateable":268435716,"RuleDerived":true,"Consolidated":false,"HasPicklist":false,"PicklistValues":null}
        JsonToken currentToken = parser.getCurrentToken();
        Cell c = new Cell();
        try {
            while (currentToken != JsonToken.END_OBJECT) {
                if (JsonToken.FIELD_NAME == currentToken) {
                    switch (parser.getCurrentName()) {
                    case JsonConstants.Ordinal:
                        c.setOrdinal(JsonUtils.readInt(parser));
                        break;
                    case JsonConstants.Value:
                        c.setValue(JsonUtils.readString(parser));
                        break;
                    case JsonConstants.Updateable:
                        c.calculateUpdate(JsonUtils.readLong(parser));
                        break;
                    case JsonConstants.Consolidated:
                        c.setConsolidated(JsonUtils.readBoolean(parser));
                        break;
                    case JsonConstants.HasPicklist:
                        c.setHasPickList(JsonUtils.readBoolean(parser));
                        break;
                    case JsonConstants.PicklistValues:
                        if (c.getHasPickList()) {
                            currentToken = parser.nextToken();
                            if (currentToken != JsonToken.START_ARRAY) {
                                log.error("expected begin array for picklist");
                            } else {
                                currentToken = parser.nextToken();
                                while (currentToken != JsonToken.END_ARRAY) {
                                    c.addPickElement(NULL_VALUE.equals(parser.getText()) ? "" : parser.getText());
                                    currentToken = parser.nextToken();
                                }
                            }
                        }
                        break;
                    default:
                        break;
                    }
                }
                currentToken = parser.nextToken();
            }
        } catch (Exception e) {
            if (c.getOrdinal() != null) {
                log.error("Error reading cell with ordinal " + c.getOrdinal(), e);
            }
        }
        if (c.getName() == null) {
            c.setName("oddly empty from readDataCell");
        }
        return c;
    }

    public Table readSingleAxis(@NonNull final InputStream in, boolean headersOnly)
            throws JsonParseException, IOException {
        Table table = null;
        try (JsonParser parser = jasonFactory.createParser(in);) {
            JsonToken token = parser.nextToken();
            if (token != JsonToken.START_OBJECT) {
                log.error("expected start object");
            } else {
                boolean endLoop = false;
                if (!connectionClosing) {
                    while (token != JsonToken.END_OBJECT && !endLoop) {
                        switch (token) {
                        case FIELD_NAME:
                            // read what we have
                            String field = parser.getCurrentName();
                            switch (field) {
                            case "Axes":
                                table = readSingleAxis(parser, headersOnly);
                                endLoop = true;
                                break;
                            default:
                                break;
                            }
                            break;
                        default:
                            break;
                        }
                        token = parser.nextToken();
                    }
                }
            }
        }
        return table;
    }

    private Table readSingleAxis(@NonNull final JsonParser parser, boolean headersOnly)
            throws JsonParseException, IOException {
        JsonToken currentToken = parser.nextToken();
        Table table = new Table();
        Table extraData = new Table();
        Row<Cell> whereClauseHeaders = null;
        List<String> colNames = null;
        final Row<Cell> whereClauseContent = new Row<Cell>();
        boolean objectStarted = false;
        int ordinal = -1;
        int cardinality = -1;

        if (currentToken != JsonToken.START_ARRAY) {
            log.error("expected begin array");
            return null;
        } else {
            boolean prematureEndRead = false;
            if (!connectionClosing) {
                while (currentToken != JsonToken.END_ARRAY && !prematureEndRead) {
                    currentToken = parser.nextToken();
                    if (objectStarted) {
                        if (currentToken == JsonToken.END_OBJECT) {
                            // end of axes
                            log.debug("###### axes " + ordinal + " ends");
                            objectStarted = false;
                        } else {
                            // read data
                            String field = parser.getCurrentName();
                            switch (field) {
                            case JsonConstants.Ordinal:
                                ordinal = JsonUtils.readInt(parser);
                                break;
                            case JsonConstants.Cardinality:
                                cardinality = JsonUtils.readInt(parser);
                                break;
                            case JsonConstants.Hierarchies:
                                switch (ordinal) {
                                case 0:
                                    colNames = readNamesInHierarchy(parser); // this is the list of names to pass when
                                                                             // reading header names as they might be
                                                                             // combinedl
                                    table.setActualDataSize(cardinality);
                                    table.setName(this.name);
                                    break;
                                case 1:
                                    // here we have extra columns to be pre-pended to the list of headers
                                    extraData.appendHeaders(readExtraHeadersInHierarchies(parser));
                                    expectedRows = cardinality;
                                    extraData.setExpectedSize(expectedRows);
                                    break;
                                case 2:
                                    // here we're reading the headers of the "fixed" data, the one that is in the
                                    // where clause
                                    whereClauseHeaders = readWhereClauseHeaders(parser);
                                    break;
                                default:
                                    log.error("Unimplemented handling of Hierarchies with ordinal " + ordinal);
                                    break;
                                }
                                break;
                            case JsonConstants.Tuples:
                                switch (ordinal) {
                                case 0:
                                    // on the first row we put the headers
                                    table.addRow(readTuplesForHeaders(parser, cardinality, colNames, !headersOnly));
                                    break;
                                case 1:
                                    // check the the tuples for ordinal 1 : here we have the actual values of the
                                    // cells that
                                    // have been pre-pended to the table so they go in extra data
                                    if (!headersOnly)
                                        extraData.addAll(readExtraDataInTuples(parser, extraData.getHeaders()));
                                    else
                                        // finished reading as we are not going to read tuples other than for
                                        // axis 0
                                        prematureEndRead = true;
                                    break;
                                case 2:
                                    // check the the tuples for ordinal 2 : here we have the actual values of the
                                    // cells
                                    if (!headersOnly)
                                        whereClauseContent.addAll(readWhereClauseContentInTuples(parser));
                                    break;
                                default:
                                    log.error("Unimplemented handling of Tuples with ordinal " + ordinal);
                                    break;
                                }
                                break;
                            default:
                                break;
                            }
                        }
                    } else {
                        // check that the object starts
                        if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.END_ARRAY) {
                            log.error("expected start object or end array after start array");
                            return null;
                        } else {
                            log.debug("###### axes " + (ordinal + 1) + " starts");
                            objectStarted = true;
                        }
                    }
                }
            }
        }

        switch (ordinal) {
        case 0:
            return table;
        case 1:
            return extraData;
        case 2:
            table.clear();
            table.addRow(whereClauseHeaders);
            table.addRow(whereClauseContent);
            return table;
        default:
            return null;
        }
    }

    @Data
    @NoArgsConstructor
    private static class CardinalityReader {
        int ordinal;
        int cardinality;
    }

    @Override
    public void connectionClosing() {
        this.connectionClosing = true;
    }
}
