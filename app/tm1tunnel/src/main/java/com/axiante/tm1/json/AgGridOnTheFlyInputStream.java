package com.axiante.tm1.json;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.ConnectionData;
import com.axiante.mui.common.interfaces.MuiConnectionListener;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.ForceSuppressColumnsHelper;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.Command;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;

@Slf4j
public class AgGridOnTheFlyInputStream extends InputStream implements MuiConnectionListener {
    transient Tm1Tunnel tunnel;
    transient CloseableHttpResponse axisOneConnection;
    transient CloseableHttpResponse cellsConnection;
    @Getter(value = AccessLevel.PROTECTED)
    transient Configuration configuration;
    transient String cellsetId;
    transient String token;

    transient Row<Cell> axisZeroHeaders;
    transient Row<Cell> axisOneHeaders;
    transient Table axisTwoData;

    transient JsonParser axisOneParser;
    transient JsonParser cellsParser;

    transient AgGridDataProducer producer;
    transient int actualDataIndex;
    // these are needed for the actual stream
    transient private char[] buffer;
    transient private AtomicInteger bufferIndex;
    transient private AtomicInteger tableIndex;
    transient private int tableSize;
    transient private boolean streamConsumed = false;
    transient private Cookie cookie = null;

    @Getter
    /**
     * usato per il listener 
     */
    transient private boolean streamClosing = false;
    @Getter
    /**
     * usato per interrompere la lettura
     */
    transient private boolean readAbort = false;
    transient private boolean readAbortCompleted = false;
    transient private static final ObjectMapper rowKeyMapper = new ObjectMapper();
    transient private final HttpUtils httpUtils = new HttpUtils();
    transient private Double timeout;
    transient private long elapsedTime;

    transient private ConnectionData cellsData = null;
    transient private ConnectionData axisOneData = null;

    //	private static final List<String> headersOnlyList = Arrays.asList("Axes","Hierarchies","Name");
    transient private final ForceSuppressColumnsHelper suppressColumnsHelper = new ForceSuppressColumnsHelper();
    /**
     * 
     * @param configuration the configuration to create the connection
     * @param cellsetId     the id of the cellset to retrieve
     * @param token         the token to use to authenticate
     */
    public AgGridOnTheFlyInputStream(@NonNull final Configuration configuration, @NonNull final String cellsetId, final String token,
            @NonNull final Table tableHeaders, final Cookie cookie, final Double timeout) {
        // TODO: if token is null and auth is passport then try to login
        log.debug("creating input stream for TM1 request");
        try {
            this.configuration = configuration;
            this.cellsetId = cellsetId;
            this.token = token;
            tunnel = new Tm1Tunnel(this.configuration.getProfile());
            this.cookie = cookie;
            this.timeout = timeout;
            if (!verifyCellSet()) {
                throw new RuntimeException("could not access cellset with id " + cellsetId);
            }

            tableSize = calculateTableSize();

            if (tableSize > 0) {
                if (!initializeHeaders(tableHeaders)) {
                    throw new RuntimeException("Unable to initialize headers; please check log for errors");
                }

                log.debug("querying for axis 1 stream ");

                axisOneData = getHttData(createCommandFor(this.configuration.getCommand().getAxes(true), 1, false));
                if ( axisOneData != null ) {
                    axisOneConnection = axisOneData.getResponse();
                }
                log.debug("connection opened");				

                log.debug("querying for cells stream ");
                cellsData = getHttData(createCommandFor(this.configuration.getCommand().getCells(true), null, false));
                if ( cellsData != null ) {
                    cellsConnection = cellsData.getResponse();
                }
                if (!verifyStreamInitialized()) {
                    // print the errors
                    httpUtils.checkResponse(axisOneConnection, true);
                    httpUtils.checkResponse(cellsConnection, true);
                    if ((axisOneConnection == null) || (cellsConnection == null)) {
                        throw new SocketTimeoutException(
                                "Unable to initialize input stream from tm1; please check log for errors");
                    } else {
                        throw new RuntimeException("Internal server error");
                    }
                }

                axisOneParser = openParser(axisOneConnection, JsonConstants.Tuples);
                cellsParser = openParser(cellsConnection, JsonConstants.Cells);
                prepareAxisOneParser();

                // check parsers
                if (!verifyParsers()) {
                    throw new RuntimeException("Unable to initialize parsers; please check log for errors");
                }

                actualDataIndex = calculateActualDataIndex();
                producer = new AgGridDataProducer(null);
                // initialize buffer
            } else {
                log.debug("table has no rows");
            }
            tableIndex = new AtomicInteger(0);
            bufferIndex = new AtomicInteger(-1);
            buffer = "{\"maxrows\":false,\"data\": [".toCharArray();
        } catch (final Throwable t) {
            log.error("Unexpected error thrown while setting up input stream", t);
            throw new RuntimeException("Unable to initialize Tm1 input stream", t);
        } finally {
            log.debug("Inputstream initialized");
            elapsedTime = System.currentTimeMillis();
        }

    }

    void prepareAxisOneParser() {
        try {
            moveToBeginOfData(axisOneParser, JsonConstants.Members);
        } catch (final IOException e) {
            log.error("could not find Members within Tuples", e);
            try {
                axisOneParser.close();
            } catch (final IOException e1) {
                log.warn("unable to close axis stream resource, this may cause a memory leak", e1);
            } finally {
                axisOneParser = null;
            }
        }
    }

    private boolean verifyParsers() {
        return (cellsParser != null) && (axisOneParser != null);
    }

    private boolean initializeHeaders(final Table skelton) {
        log.debug("initializing headers");
        axisTwoData = readAxis(2, false);
        axisOneHeaders = readAxisHeaders(1);
        final int startZero = skelton.getActualDataIndex();
        axisZeroHeaders = new Row<>();
        axisZeroHeaders.addAll(skelton.getHeaders().subList(startZero, skelton.getHeaders().size()));

        // posso avere dati sull'asse 1 ma non sull'asse 2, sull'asse 2 ma non sull'asse
        // 1, sull'asse 2 e sull'asse 1
        suppressColumnsHelper.setApplyForceSuppression(
                getConfiguration().getForceSuppression() && 
                (configuration.getMdx() != null) && 
                (configuration.getMdx().getCols() != null) && 
                configuration.getMdx().getCols().isNonEmpty()
                );
        if (suppressColumnsHelper.isApplyForceSuppression()) {
            log.debug("applying forced suppression");
            if ((axisTwoData != null) && (axisTwoData.getRowSize() > 1)) {
                try {
                    axisTwoData.getRow(0).stream().forEach(h -> {
                        suppressColumnsHelper.put(h.getName(), Boolean.FALSE);
                    });
                } catch (final IllegalAccessException e) {
                    log.error("error reading axis 2 headers", e);
                }
            }
            if (axisOneHeaders != null) {
                axisOneHeaders.stream().forEach(h -> {
                    suppressColumnsHelper.put(h.getName(), Boolean.FALSE);
                });
            }
            if (axisZeroHeaders != null) {
                axisZeroHeaders.stream().forEach(h -> {
                    suppressColumnsHelper.put(h.getName(), Boolean.FALSE);
                });
            }

        }
        log.debug("headers initialized");

        return (((axisZeroHeaders != null) && (axisZeroHeaders.size() > 0))
                || ((axisTwoData != null) && (axisTwoData.getRowSize() > 0))) && (axisZeroHeaders != null)
                && (axisZeroHeaders.size() > 0);
    }

    private boolean verifyCellSet() {
        try (CloseableHttpResponse response = getHttResponse(
                createCommandFor(configuration.getCommand().getCube(), null, false));) {
            final boolean result = httpUtils.checkResponse(response, true);
            EntityUtils.consume(response.getEntity());
            return result;
        } catch (final IOException e) {
            log.error("error closing connection to tm1, this could lead to memory leak!", e);
        }
        return false;
    }

    private int calculateTableSize() {
        log.debug("calculating table size");
        Integer ret = -1;
        try (CloseableHttpResponse response = getHttResponse(
                createCommandFor(configuration.getCommand().getAxes(false), null, true));) {
            final Tm1JsonReader reader = new Tm1JsonReader();
            final Integer[] values = reader.readAxesCardinality(response.getEntity().getContent());
            try {
                ret = values[1];
            } catch (final ArrayIndexOutOfBoundsException e) {
                log.error("error calculating table size", e);
            }
        } catch (final IOException e) {
            log.warn("unable to close resource for table size calculation, this might lead to a memory leak", e);
        }
        log.debug("done calculating table size");
        return ret;
    }

    private int calculateActualDataIndex() {
        return axisOneHeaders.size() + (axisTwoData != null ? axisTwoData.getHeaders().size() : 0);
    }

    private boolean verifyStreamInitialized() {
        boolean result = false;
        result = (configuration != null) && (cellsetId != null) && (tunnel != null)
                && (axisOneConnection != null) && httpUtils.checkResponse(axisOneConnection)
                && (cellsConnection != null) && httpUtils.checkResponse(cellsConnection) && (tableSize >= 0);

        return result;
    }

    private JsonParser openParser(@NonNull final CloseableHttpResponse response, @NonNull final String beginOfDataTag) {
        try {
            JsonParser parser = JsonUtils.getFactory().createParser(response.getEntity().getContent());
            if (parser != null) {
                try {
                    moveToBeginOfData(parser, beginOfDataTag);
                    return parser;
                } catch (final IOException e) {
                    try {
                        parser.close();
                        parser = null;
                    } catch (final IOException e1) {
                        log.error("unable to close cells parser, this could lead to a memory leak!", e1);
                    } finally {
                        log.error("unable to move to cells begin of data", e);
                    }
                }
            }

        } catch (IllegalStateException | IOException e) {
            log.error("unable to open json parser", e);
        }
        return null;
    }

    private CloseableHttpResponse getHttResponse(final String command) {
        log.debug("opening connection for command string " + command);
        CloseableHttpResponse response;
        try {
            response = tunnel.executeRequestAsHttpResponse("GET", command, null, timeout, cookie);
        } catch (URISyntaxException | IOException e) {
            log.error("error connecting to TM1 for cell data", e);
            response = null;
        }
        return response;
    }

    private ConnectionData getHttData(final String command) {
        log.debug("opening connection for command string " + command);
        ConnectionData response;
        try {
            response = tunnel.executeRequestAsConnectionData("GET", command, null,null, timeout, cookie);
        } catch (URISyntaxException | IOException e) {
            log.error("error connecting to TM1 for cell data", e);
            response = null;
        }
        return response;
    }

    private String createCommandFor(@NonNull final Command command, final Integer ordinal, final boolean headersOnly) {
        final StringBuffer uriRequest = new StringBuffer();
        uriRequest.setLength(0);
        command.generateRequestForCellset(
                uriRequest, 
                cellsetId, 
                headersOnly?Command.HEADERONLYLIST:null
                ).toString();
        if (ordinal != null) {
            uriRequest.append(",Axes($filter=Ordinal%20eq%20").append(ordinal).append(")");
        }
        log.debug("created command: " + uriRequest);
        return uriRequest.toString();
    }

    private Table readAxis(final Integer ordinal, final boolean headersOnly) {
        try (CloseableHttpResponse axisRespone = getHttResponse(
                createCommandFor(configuration.getCommand().getAxes(true), ordinal, headersOnly));) {
            return readAxis(headersOnly, axisRespone);
        } catch (final IOException e) {
            log.warn("unable to close stream resource for axis "+ordinal+", this may cause a memory leak", e);
        }
        return null;
    }

    private Table readAxis(final boolean headersOnly, final CloseableHttpResponse axisRespone ) throws JsonParseException, IllegalStateException, IOException {
        final Tm1JsonReader reader = new Tm1JsonReader();
        final Table table = reader.readSingleAxis(axisRespone.getEntity().getContent(), headersOnly);
        return table;
    }

    private Row<Cell> readRow(final Integer index) throws IOException, IllegalAccessException {

        int columnIndex = 0;
        final Row<Cell> row = new Row<>();
        final Row<Cell> axisOne = readAxisOneRow(axisOneParser);
        if (axisTwoData != null) {
            row.addAll(axisTwoData.getRow(1));
        }
        if (axisOne != null) {
            row.addAll(axisOne);
        }
        columnIndex = row.size();
        row.addAll(readCellsData(cellsParser, index));

        row.generateRowKey(columnIndex, rowKeyMapper);
        return row;
    }

    private Row<Cell> readAxisOneRow(@NonNull final JsonParser parser) throws IOException {
        final Tm1JsonReader reader = new Tm1JsonReader();
        final int ordinal = 0;
        if (parser.getCurrentToken() != null) {
            if (!(JsonToken.FIELD_NAME.equals(parser.getCurrentToken())
                    && JsonConstants.Members.equals(parser.getCurrentName()))) {
                moveToBeginOfData(parser, JsonConstants.Members);
            }
        }
        final Row<Cell> row = reader.readExtraDataInMembers(parser, ordinal, axisOneHeaders, false);
        if (JsonToken.END_OBJECT.equals(parser.getCurrentToken())) {
            // sono posizionato alla fine dell'oggetto
            parser.nextToken();
        }
        return row;
    }

    private Row<Cell> readCellsData(final JsonParser parser, int columnIndex) throws IOException {
        JsonToken currentToken = parser.getCurrentToken();
        if (currentToken == null) {
            currentToken = parser.nextToken();
        }
        final Row<Cell> row = new Row<>();
        row.ensureCapacity(axisZeroHeaders.size());
        final Tm1JsonReader reader = new Tm1JsonReader();
        final int offset = axisOneHeaders.size() + (axisTwoData != null ? axisTwoData.getHeaders().size() : 0);
        while ((currentToken != null) && (currentToken != JsonToken.END_ARRAY)) {
            if (currentToken == JsonToken.START_OBJECT) {
                final Cell cell = reader.readDataCell(parser);
                columnIndex = cell.getOrdinal() % (axisZeroHeaders.size());
                cell.setColumPosition(columnIndex + offset);
                cell.setName(axisZeroHeaders.get(columnIndex).getName()); // <-- with this I can calculate the unique
                cell.setGridColumn(columnIndex);
                // key of the cell starting from the row
                row.set(columnIndex, cell);
                if (columnIndex == (row.size() - 1)) {
                    break;
                }
            }
            currentToken = parser.nextToken();
        }
        return row;
    }

    private JsonParser moveToBeginOfData(@NonNull final JsonParser parser, @NonNull final String jsonToken)
            throws JsonParseException, IOException {
        JsonToken current = parser.getCurrentToken();
        if (current == null) {
            current = parser.nextToken();
        }
        while (current != null) {
            if (JsonToken.FIELD_NAME.equals(current) && jsonToken.equals(parser.getCurrentName())) {
                break;
            }
            current = parser.nextToken();
        }
        return parser;
    }

    private Row<Cell> readAxisHeaders(final Integer ordinal) {
        final Table t = readAxis(ordinal, true);
        if (t != null) {
            return t.getHeaders();
        }
        return null;
    }
    @Override
    public int read(final byte b[], final int off, final int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if ((off < 0) || (len < 0) || (len > (b.length - off))) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte) c;

        int i = 1;
        try {
            for (; i < len; i++) {
                c = read();
                if (c == -1) {
                    break;
                } else if ((c < 32) || (c > 255)) {
                    log.debug("skipping control char " + c);
                    continue;
                } else {
                    b[off + i] = (byte) c;
                }
            }
        } catch (final IOException ee) {
        }
        return i;
    }

    @Override
    public int read() throws IOException {
        if (streamClosing) {
            return -1;
        } else {
            if ((buffer != null) && (bufferIndex.get() < (buffer.length - 1))) {
                return buffer[bufferIndex.incrementAndGet()];
            } else {
                // fill the buffer with a new chunk
                bufferIndex.set(-1);
                nextBuffer();
                if (buffer != null) {
                    return buffer[bufferIndex.incrementAndGet()];
                } else {
                    return -1;
                }
            }
        }
    }

    private void nextBuffer() {
        if (streamClosing) {
            streamConsumed = true;
            if ( !readAbort ) {
                buffer = "]}".toCharArray();
                bufferIndex.set(-1); // reads are made by increment and get
            } else { 
                buffer = null;
            }
        } else if (readAbort) {
            if ( readAbortCompleted ) {
                buffer = null;
            }
            else { 
                streamConsumed = true;
                buffer = "],\"error\":\"Read Aborted\"}".toCharArray();
                bufferIndex.set(-1); // reads are made by increment and get
                readAbortCompleted = true;
            }
        } else {
            if (tableIndex.get() < tableSize) {
                /*
                 * read row
                 */

                // there's data that needs to be read
                StringBuilder builder;
                try {
                    final Row<Cell> data = readRow(tableIndex.incrementAndGet());
                    if (data == null) {
                        streamConsumed = true;
                        buffer = closeBuffer().toCharArray();
                        bufferIndex.set(-1); // reads are made by increment and get
                    } else {
                        builder = producer.transform(data, null, actualDataIndex, suppressColumnsHelper);
                        if (tableIndex.get() != 1) {
                            builder.insert(0, ","); // comma at the beginning
                        } else {
                            // it's the first row, nothing to do
                        }
                        // now check if this was the last row
                        if (tableIndex.get() == tableSize) {
                            builder.append(closeBuffer());
                            log.debug("last row read");
                            streamConsumed = true;
                        }
                        buffer = new char[builder.length()];
                        builder.getChars(0, builder.length(), buffer, 0);
                        bufferIndex.set(-1); // reads are made by increment and get
                    }
                } catch (IllegalAccessException | IOException e) {
                    buffer = null;
                    log.error("error reading next data", e);
                }
            } else if ((tableSize == 0) && !streamConsumed) {
                buffer = closeBuffer().toCharArray();
                log.debug("empty table");
                streamConsumed = true;
            } else {
                if (streamConsumed) {
                    buffer = null;
                    markTime();
                } else {
                    log.error("stream should be consumed as the table has been read !");
                }
            }
        }
    }


    private String closeBuffer() {
        log.debug("closing buffer ...");
        if (suppressColumnsHelper.isApplyForceSuppression()) {
            return ("]" + suppressColumnsHelper.produceSuppressJsonArray() + "}");
        } else {
            return "]}";
        }
    }

    @Override
    public void close() {
        if (tunnel != null) {
            try {
                tunnel.close();
            } finally {

            }
        }
        if ( axisOneData != null ) {
            axisOneData.close();
        }
        if ( cellsData != null ) {
            cellsData.close();
        }
        if (axisOneParser != null) {
            log.debug("closing axis one parser ");
            close(axisOneParser);
            axisOneParser = null;
        }
        if (cellsParser != null) {
            log.debug("closing cells parser ");
            close(cellsParser);
            cellsParser = null;
        }

        if (axisZeroHeaders != null) {
            axisZeroHeaders.clear();
            axisZeroHeaders = null;
        }
        if (axisOneHeaders != null) {
            axisOneHeaders.clear();
            axisOneHeaders = null;
        }
        if (axisTwoData != null) {
            axisTwoData.clear();
            axisTwoData = null;
        }

        axisOneConnection = null;
        cellsConnection = null;

        try {
            super.close();
        } catch (final IOException e) {
            log.warn("unable to close stream resource, this may cause a memory leak", e);
        }
        log.debug("stream closed");
    }

    private void close(@NonNull final JsonParser parser) {
        try {
            parser.close();
        } catch (final java.net.SocketException e) {
            // Socket is closed
            log.debug("stream already consumed", e);
        } catch (final IOException e) {
            log.warn("unable to close parser, this may cause a memory leak", e);
        }

    }

    @Override
    public void connectionClosing() {
        log.info("stream closing listener activated");
        streamClosing = true;
    }

    private void markTime() {
        log.debug("stream served in " + ((System.currentTimeMillis() - elapsedTime) / 1000) + " seconds");
    }

    public void abort() {
        log.info("aborting read: closing streams");
        readAbort = true;
    }

}
