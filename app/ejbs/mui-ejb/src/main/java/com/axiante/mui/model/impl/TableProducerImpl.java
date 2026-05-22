package com.axiante.mui.model.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.ConnectionData;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.CellSetCache;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.tm1.json.Tm1JsonDynaConfReader;
import com.axiante.tm1.json.Tm1JsonReader;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.Command;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.axiante.utility.configuration.StaticColumnDef;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class TableProducerImpl implements TableProducer {
    @Inject
    protected CellSetCache cellSetCache;

    @Inject
    protected ApplicationConfiguration applicationConfiguration;

    @Inject
    CookieRepository cookieRepository;

    private static final long serialVersionUID = -7127293823431739824L;
    private boolean connectionClosing = false;
    private transient final HttpUtils httpUtils = new HttpUtils();

    public TableProducerImpl() {
        log.debug("creating instance of " + this.getClass().getName());
    }

    @Override
    public List<String> producePicklist(@NonNull final Configuration configuration, final Query filteredQuery,
            @NonNull final Integer cellOrdinal, final Double connectionTimeout, final Integer socketTimeout,
            final Integer connectionRequestTimeout, final Cookie cookie) {
        CloseableHttpResponse response = null;
        Query query = filteredQuery;
        if (query == null) {
            query = configuration.getMdx();
        }
        final List<String> picklist = new ArrayList<>();
        try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile())) {
            iface.setCookie(cookie);
            response = iface.executeMDXasHttpResponse(
                    "Cells($select=HasPicklist,PicklistValues),Cells($filter=Ordinal%20eq%20" + cellOrdinal + ")",
                    query.generateMDX(configuration.getDynamicColumns() != null && configuration.getDynamicColumns()),
                    false, connectionTimeout, socketTimeout, connectionRequestTimeout, cookie);
            if (this.httpUtils.checkResponse(response, true)) {
                final Tm1JsonReader reader = new Tm1JsonReader();
                /*
                 * { "@odata.context": "$metadata#Cellsets(Cells)/$entity", "ID":
                 * "mrXqy5LZAYCqAQAg", "Cells": [ { "Ordinal": 12, "Value": null,
                 * "FormattedValue": "" } ] }
                 */
                final List<Cell> list = reader.readCells(response.getEntity().getContent());
                if ((list.size() == 1) && (list.get(0) != null) && list.get(0).getHasPickList()) {
                    if (list.get(0).getPickList() == null) {
                        log.error("Cell declares picklist but picklist not configured on configuration"
                                + configuration.getName());
                    } else {
                        picklist.addAll(list.get(0).getPickList());
                    }
                } else {
                    if (list.size() > 1) {
                        log.error("error retrieving picklist: expected one cell, found " + list.size());
                    }
                }
            }
        } catch (final ClientProtocolException e) {
            log.error("error getting picklist values", e);
        } catch (final IOException e) {
            log.error("error getting picklist values", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final IOException e) {
                    log.error("error closing response", e);
                }
            }
        }
        return picklist;
    }

    @Override
    public Table produceTable(@NonNull final Configuration configuration, final Query filteredQuery, String cellSetId,
            final boolean attributesData, final Cookie aCookie, Double timeout) {

        if (attributesData) {
            log.debug("retrieving attributes data (no $ sign)");
        } else {
            log.debug("calculating grid on server");
        }
        Cookie cookie = aCookie;
        if (cookie == null) {
            cookie = this.cookieRepository.getCookie(configuration.getProfile());
        }
        Table table = null;
        if (timeout == null) {
            timeout = this.applicationConfiguration.getConnectionTimeout();
        }
        CloseableHttpResponse response = null;
        if (!this.httpUtils.isHostReachable(configuration.getProfile().getValidationHost())) {
            table = new Table();
            table.setError(true);
            table.setErrorMessage("Host  unreachable [" + configuration.getProfile().getHost() + "]");
            return table;
        }

        MuiToken muiToken = null;
        try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile())) {
            iface.setCookie(cookie);
            final Command command = configuration.getCommand();
            Command _command = command.getCube();
            final StringBuffer uriRequest = new StringBuffer();
            String commandString = _command.generateRequest(uriRequest).toString();
            if (cellSetId == null) {
                log.debug("generating cellset");
                Query mdx = filteredQuery; // if has filters use it
                if (mdx == null) {
                    // no filter passed: use utility
                    mdx = configuration.getMdx();
                }

                // to make sure that the session is closed, we should create a muitoken

                muiToken = this.generateCellset(configuration, mdx, cookie);
                if (muiToken == null) {
                    table = new Table();
                    table.setError(true);
                    table.setErrorMessage("There was an error in the request sent to TM1 server");
                    return table;
                } else if (MuiToken.SERVER_BUSY.equals(muiToken.getUuid())) {
                    table = new Table();
                    table.setError(true);
                    table.setErrorMessage("TM1 server did not respond");
                    return table;
                } else {
                    cellSetId = muiToken.getCellsetId();
                }
            } else {
                log.warn("this should not be called ... you will not close the tm1 session !");
            }

            // now we have the cube, read the axes
            _command = command.getAxes();
            uriRequest.setLength(0);
            commandString = _command.generateRequestForCellset(uriRequest, cellSetId).toString();
            log.debug("retrieving axes");
            response = iface.executeRequestAsHttpResponse("GET", commandString, null, timeout,
                    muiToken != null ? muiToken.getCookie() : null);
            if (!(this.httpUtils.checkResponse(response))) {
                log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                        + configuration.getMdx().generateMDX());
                return table;
            }
            final Tm1JsonReader reader = new Tm1JsonReader();
            table = reader.readAxes(response.getEntity().getContent(), attributesData);

            table.setCellSetId(cellSetId);
            if (table.canQueryForCells()) {
                _command = command.getCells(true);
                uriRequest.setLength(0);
                commandString = _command.generateRequestForCellset(uriRequest, cellSetId).toString();
                log.debug(commandString);
                response.close();
                log.debug("retrieving cells");
                response = iface.executeRequestAsHttpResponse("GET", commandString, null, timeout,
                        muiToken != null ? muiToken.getCookie() : null);
                final String message = this.httpUtils.getResponseString(response);
                if (message != null) {
                    table.setError(true);
                    table.setErrorMessage(message);
                    return table;
                }
                reader.readCellSetData(table, response.getEntity().getContent());
            } else {
                log.debug("saving a callback to the server as the dataset will be empty");
            }

            if ((cellSetId != null)) {
                log.debug("deleting named query " + cellSetId);
                response.close();
                response = iface.executeRequestAsHttpResponse("DELETE", "Cellsets('" + cellSetId + "')", null, timeout,
                        muiToken != null ? muiToken.getCookie() : null);
                response.close();
            }
        } catch (final ClientProtocolException e) {
            log.error("Error connecting to remote server ", e);
            table.setError(true);
            table.setErrorMessage("Error producing table: " + e.getMessage());
            try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile())) {
            } catch (final Exception ex) {
                log.error("unable to close tm1 sessions on error", ex);
            }
        } catch (final IOException e) {
            log.error("IO error during client server communication", e);
            table.setError(true);
            table.setErrorMessage("Error producing table: " + e.getMessage());
        } catch (final URISyntaxException e) {
            log.error("Error generating URI ", e);
            table.setError(true);
            table.setErrorMessage("Error producing table: " + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final IOException e) {
                    log.warn("Error closing response ", e);

                }
            }
        }
        return table;
    }

    @Override
    public CloseableHttpResponse produceTableContentReader(@NonNull final Configuration configuration,
    		final String cellSetId, final Cookie cookie) {
    	CloseableHttpResponse response = null;
    	final Double timeout = this.applicationConfiguration.getConnectionTimeout();
    	try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile())) {
    		iface.setCookie(cookie);
    		final Command command = configuration.getCommand();
    		Command _command = command.getCube();
    		final StringBuffer uriRequest = new StringBuffer();
    		String commandString = _command.generateRequest(uriRequest).toString();
    		if (cellSetId == null) {
    			log.error("cannot operate without cellsetId");
    			return response;
    		}
    		_command = command.getCells(true);
    		uriRequest.setLength(0);
    		commandString = _command.generateRequestForCellset(uriRequest, cellSetId).toString();
    		log.debug(commandString);
    		log.debug("retrieving cells");
    		response = iface.executeRequestAsHttpResponse("GET", commandString, null, timeout, cookie);
    		if (!this.httpUtils.checkResponse(response)) {
    			log.error("could not get data from tm1 end point;");
    			response.close();
    			response = null;
    		}
    	} catch (final ClientProtocolException e) {
    		log.error("Error connecting to remote server ", e);
    	} catch (final IOException e) {
    		log.error("IO error during client server communication", e);
    	} catch (final URISyntaxException e) {
    		log.error("Error generating URI ", e);
    	} catch (NullPointerException e) {
    		log.error("Error with some null data", e);
    	}
    	finally {
    		
    	}
    	return response;
    }

    protected Row<Cell> produceAxis(@NonNull final Configuration configuration, final Query filteredQuery,
            String cellSetId, final boolean attributesData, @NonNull final Cookie cookie) {
        if (attributesData) {
            log.debug("retrieving attributes data (no $ sign)");
        } else {
            log.debug("calculating grid on server");
        }

        Row<Cell> table = null;
        CloseableHttpResponse response = null;
        final Double connectionTimeout = this.applicationConfiguration.getConnectionTimeout();
        final Integer socketTimeout = this.applicationConfiguration.getSocketTimeout();
        final Integer connectionManagerTimeout = this.applicationConfiguration.getConnectionRequestTimeout();

        try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile())) {
            iface.setCookie(cookie);
            final Command command = configuration.getCommand();
            Command _command = command.getCube();
            final StringBuffer uriRequest = new StringBuffer();
            String commandString = _command.generateRequest(uriRequest).toString();
            if (cellSetId == null) {
                log.debug("generating cellset");
                Query mdx = filteredQuery; // if has filters use it
                if (mdx == null) {
                    // no filter passed: use utility
                    mdx = configuration.getMdx();
                }
                response = iface.executeMDXasHttpResponse(commandString, mdx.generateMDX(), false, connectionTimeout,
                        socketTimeout, connectionManagerTimeout, cookie);
                if (!this.httpUtils.checkResponse(response)) {
                    log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                            + configuration.getMdx().generateMDX());
                    return table;
                }

                try (JsonParser parser = JsonUtils.getFactory().createParser(response.getEntity().getContent());) {
                    JsonToken jsonToken = parser.getCurrentToken();
                    while (jsonToken != JsonToken.END_OBJECT) {
                        if ((JsonToken.FIELD_NAME == jsonToken) && "ID".equals(parser.getCurrentName())) {
                            log.debug("found id");
                            parser.nextToken();
                            cellSetId = parser.getText();
                            break;
                        }
                        jsonToken = parser.nextToken();
                    }
                }
                EntityUtils.consume(response.getEntity());

                if (response != null) {
                    response.close();
                }
            }
            // now we have the cube, read the axes
            _command = command.getAxes();
            uriRequest.setLength(0);
            commandString = _command.generateRequestForCellset(uriRequest, cellSetId).toString();
            log.debug("retrieving axes");
            response = iface.executeRequestAsHttpResponse("GET", commandString, null, connectionTimeout, cookie);
            if (!(this.httpUtils.checkResponse(response))) {
                log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                        + configuration.getMdx().generateMDX());
                return table;
            }
            final Tm1JsonDynaConfReader reader = new Tm1JsonDynaConfReader();
            table = reader.getHeaders(response.getEntity().getContent(), configuration.getDynamicColumnsSettings());

        } catch (final ClientProtocolException e) {
            log.error("Error connecting to remote server ", e);
        } catch (final IOException e) {
            log.error("IO error during client server communication", e);
        } catch (final URISyntaxException e) {
            log.error("Error generating URI ", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final IOException e) {
                    log.warn("Error closing response ", e);
                }
            }
        }
        return table;
    }

    @Override
    public MuiToken generateCellset(@NonNull final Configuration configuration, @NonNull final Query query,
            final Cookie cookie) {
        MuiToken muiToken = null;
        CloseableHttpResponse response = null;
        log.debug("generating mui token/cellset");
        try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile())) {
            iface.setCookie(cookie);
            final Command command = configuration.getCommand();
            final Command _command = command.getCube();
            final StringBuffer uriRequest = new StringBuffer();
            final String commandString = _command.generateRequest(uriRequest).toString();
            final String passport = null;

            final Double connectionTimeout = this.applicationConfiguration.getConnectionTimeout();
            final Integer socketTimeout = this.applicationConfiguration.getSocketTimeout();
            final Integer connectionManagerTimeout = this.applicationConfiguration.getConnectionRequestTimeout();

            try {
                log.debug("generating cellset " + (connectionTimeout == null ? "without connectionTimeout"
                        : "with connectionTimeout of " + connectionTimeout + " seconds"));
                try (CloseableHttpResponse res = iface.getVersion(connectionTimeout, socketTimeout,
                        connectionManagerTimeout, cookie)) {
                    if (!this.httpUtils.checkResponse(res)) {
                        if (res.getStatusLine().getStatusCode() == 401) {
                            return null;
                        } else {
                            log.debug("error during verification : [" + res.getStatusLine().getStatusCode() + "] "
                                    + res.getStatusLine().getReasonPhrase());
                        }
                    }
                } catch (final Exception e) {
                    log.error("error during cookie verification", e);
                }
                response = iface.executeMDXasHttpResponse(commandString,
                        query.generateMDX(
                                configuration.getDynamicColumns() != null && configuration.getDynamicColumns()),
                        false, connectionTimeout, socketTimeout, connectionManagerTimeout, cookie);
            } catch (SocketTimeoutException | ClientProtocolException
                    | org.apache.http.conn.ConnectTimeoutException e) {
                log.warn("TM1 server is busy as it did not respond after " + connectionTimeout + " seconds", e);
                log.debug("returning a busy mui token", e);
                log.error("Exception generating cellset ", e);
                muiToken = this.cellSetCache.registerBusy();
                return muiToken;
            } catch (final IOException e) {
                // the response was not what we expected
                log.error("error reading server's response", e);
                return muiToken;
            } catch (final Exception e) {
                log.error("Unexpected error creating token", e);
                muiToken = this.cellSetCache.registerBusy();
                return muiToken;
            }

            if (!this.httpUtils.checkResponse(response)) {
                log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                        + (configuration.getMdx() != null ? configuration.getMdx().generateMDX() : "empty"));
                EntityUtils.consume(response.getEntity());
                response.close();
                return muiToken;
            }
            String ID = null;
            try (JsonParser parser = JsonUtils.getFactory().createParser(response.getEntity().getContent());) {
                JsonToken jsonToken = parser.getCurrentToken();
                while (jsonToken != JsonToken.END_OBJECT) {
                    if ((JsonToken.FIELD_NAME == jsonToken) && "ID".equals(parser.getCurrentName())) {
                        log.debug("found id");
                        parser.nextToken();
                        ID = parser.getText();
                        break;
                    }
                    jsonToken = parser.nextToken();
                }
            }
            if (response != null) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
            Row<Cell> t = null;
            if (configuration.getDynamicColumns() != null && configuration.getDynamicColumns()) {
                t = this.produceAxis(configuration, null, ID, false, cookie);
            } else {
                t = this.convertToHeaders(configuration.getColumnDefs());
            }
            final int totalCells = this.calculateTotalCells(configuration, ID, cookie);
            muiToken = this.cellSetCache.register();
            if (muiToken == null) {
                log.error("returned null token from cache manager");
            } else {
                muiToken.setCellsetId(ID);
                muiToken.setConfiguration(configuration);
                muiToken.setPassport(passport);
                muiToken.setTotalCells(totalCells);
                if (t != null) {
                    muiToken.setHeaders(t);
                    muiToken.setActualDataIndex(0);
                }
                if (cookie != null) {
                    muiToken.setCookie(cookie);
                }
            }
            log.debug("using muitoken " + muiToken.getUuid() + " for tm1 session " + muiToken.getCookie());
        } catch (final ClientProtocolException e) {
            log.error("Error connecting to remote server ", e);
        } catch (final IOException e) {
            log.error("IO error during client server communication", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final IOException e) {
                    log.warn("error closing response", e);
                }
            }
        }
        return muiToken;
    }

    /**
     *
     * @param json
     * @return
     */

    protected Row<Cell> convertToHeaders(final String json) {
        if (json == null) {
            return null;
        }
        Row<Cell> headers = new Row<>();
        try {
            final StaticColumnDef[] defs = JsonUtils.getValidationMapper().readValue(json, StaticColumnDef[].class);
            if ((defs != null) && (defs.length > 0)) {
                final List<Cell> list = new ArrayList<>();
                for (final StaticColumnDef def : defs) {
                    if (def != null) {
                        list.addAll(this.mapToCell(def));
                    }
                }
                if ((list != null) && (list.size() > 0)) {
                    headers.addAll(list);
                } else {
                    headers = null;
                }
            } else {
                headers = null;
            }
        } catch (final IOException e) {
            log.error("error unmarshalling column defs", e);
            headers = null;
        }
        return headers;
    }

    protected List<Cell> mapToCell(final StaticColumnDef def) {
        final List<Cell> list = new ArrayList<>();
        if (def != null) {
            if (def.getChildren() != null) {
                for (final StaticColumnDef d : def.getChildren()) {
                    list.addAll(this.mapToCell(d));
                }
            } else {
                list.add(Cell.createHeaderFromConfiguration(def.getField()));
            }
        }
        return list;
    }

    /**
     * queries a cellset and retrieves axes only (no hierarchies or tuples) in order
     * to calculate the total number of cells returned when a "Cells" command is
     * issued
     *
     * @return
     */
    private int calculateTotalCells(@NonNull final Configuration configuration, @NonNull final String cellSetId,
            final Cookie cookie) {
        int axes0 = 0;
        int axes1 = 0;

        CloseableHttpResponse response = null;
        try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile())) {
            iface.setCookie(cookie);
            final Command command = configuration.getCommand();
            Command _command = command.getCube();
            final StringBuffer uriRequest = new StringBuffer();
            String commandString = _command.generateRequest(uriRequest).toString();

            // now we have the cube, read the axes
            _command = command.getAxes(false);
            uriRequest.setLength(0);
            commandString = _command.generateRequestForCellset(uriRequest, cellSetId).toString();
            log.debug("retrieving axes");
            final Double connectionTimeout = this.applicationConfiguration.getConnectionTimeout();
            response = iface.executeRequestAsHttpResponse("GET", commandString, null, connectionTimeout, cookie);
            if (!(this.httpUtils.checkResponse(response))) {
                log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                        + configuration.getMdx().generateMDX());
                return -1;
            }
            final Tm1JsonReader reader = new Tm1JsonReader();
            final Integer[] values = reader.readAxesCardinality(response.getEntity().getContent());
            if (values != null) {
                axes0 = values[0];
                axes1 = values[1];
            }
        } catch (final ClientProtocolException e) {
            log.error("Error connecting to remote server ", e);
        } catch (final IOException e) {
            log.error("IO error during client server communication", e);
        } catch (final URISyntaxException e) {
            log.error("Error generating URI ", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final IOException e) {
                    log.warn("Error closing response ", e);
                }
            }
        }

        return axes0 * axes1;
    }

    /**
     * produces the "boundaries" of a table
     */
    @Override
    public Table produceTableSkelton(@NonNull final MuiToken muiToken, final Query filteredQuery, final String passport,
            String cellSetId) {
        log.debug("calculating grid on server");
        Table table = null;
        final Configuration configuration = muiToken.getConfiguration();
        CloseableHttpResponse response = null;

        muiToken.addConnectionClosingListener(this);
        final Double connectionTimeout = this.applicationConfiguration.getConnectionTimeout();
        final Integer socketTimeout = this.applicationConfiguration.getSocketTimeout();
        final Integer connectionManagerTimeout = this.applicationConfiguration.getConnectionRequestTimeout();

        try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile());) {
            iface.setCookie(this.cookieRepository.getCookie(configuration.getProfile()));
            final Command command = configuration.getCommand();
            Command _command = command.getCube();
            final StringBuffer uriRequest = new StringBuffer();
            String commandString = _command.generateRequest(uriRequest).toString();

            if (cellSetId == null) {
                log.debug("generating cellset");
                Query mdx = filteredQuery; // if has filters use it
                if (mdx == null) {
                    // no filter passed: use utility
                    mdx = configuration.getMdx();
                }
                response = iface.executeMDXasHttpResponse(commandString, mdx.generateMDX(), false, connectionTimeout,
                        socketTimeout, connectionManagerTimeout, muiToken.getCookie());
                if (!this.httpUtils.checkResponse(response)) {
                    log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                            + configuration.getMdx().generateMDX());
                    return table;
                }
                if (!this.connectionClosing) {
                    try (JsonParser parser = JsonUtils.getFactory().createParser(response.getEntity().getContent());) {
                        JsonToken jsonToken = parser.getCurrentToken();
                        while (jsonToken != JsonToken.END_OBJECT) {
                            if ((JsonToken.FIELD_NAME == jsonToken) && "ID".equals(parser.getCurrentName())) {
                                log.debug("found id");
                                parser.nextToken();
                                cellSetId = parser.getText();
                                break;
                            }
                            jsonToken = parser.nextToken();
                        }
                        EntityUtils.consume(response.getEntity());
                    }
                }
                if (response != null) {
                    response.close();
                }
            }
            if (!this.connectionClosing) {
                // now we have the cube, read the axes
                _command = command.getAxes();
                uriRequest.setLength(0);
                commandString = _command.generateRequestForCellset(uriRequest, cellSetId).toString();
                log.debug("retrieving axes");
                response = iface.executeRequestAsHttpResponse("GET", commandString, null, connectionTimeout,
                        muiToken.getCookie());
                if (!(this.httpUtils.checkResponse(response))) {
                    log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                            + configuration.getMdx().generateMDX());
                    return table;
                }
                final Tm1JsonReader reader = new Tm1JsonReader();
                muiToken.addConnectionClosingListener(reader);
                table = reader.readAxes(response.getEntity().getContent());
                table.setCellSetId(cellSetId);
                EntityUtils.consume(response.getEntity());
                muiToken.removeConnectionClosingListener(reader);
            } else {
                log.warn("mui token expired while creating skelton");

            }
        } catch (final ClientProtocolException e) {
            log.error("Error connecting to remote server ", e);
        } catch (final IOException e) {
            log.error("IO error during client server communication", e);
        } catch (final URISyntaxException e) {
            log.error("Error generating URI ", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final Exception e) {
                    log.warn("error closing response, might lead to memory leak", e);
                }
            }
            muiToken.removeConnectionClosingListener(this);
        }
        return table;
    }

    @Override
    public Table produceTableHeaders(@NonNull final MuiToken muiToken, final Query filteredQuery, String cellSetId) {
        log.debug("calculating grid on server");
        Table table = null;
        CloseableHttpResponse response = null;
        final Configuration configuration = muiToken.getConfiguration();
        muiToken.addConnectionClosingListener(this);
        final Double connectionTimeout = this.applicationConfiguration.getConnectionTimeout();
        final Integer socketTimeout = this.applicationConfiguration.getSocketTimeout();
        final Integer connectionManagerTimeout = this.applicationConfiguration.getConnectionRequestTimeout();

        try (Tm1Tunnel iface = new Tm1Tunnel(configuration.getProfile());) {
            iface.setCookie(this.cookieRepository.getCookie(muiToken.getConfiguration().getProfile()));
            final Command command = configuration.getCommand();
            Command _command = command.getCube();
            final StringBuffer uriRequest = new StringBuffer();
            String commandString = _command.generateRequest(uriRequest).toString();
            if (cellSetId == null) {
                log.debug("generating cellset");
                Query mdx = filteredQuery; // if has filters use it
                if (mdx == null) {
                    // no filter passed: use utility
                    mdx = configuration.getMdx();
                }
                response = iface.executeMDXasHttpResponse(commandString, mdx.generateMDX(), false, connectionTimeout,
                        socketTimeout, connectionManagerTimeout, muiToken.getCookie());
                if (this.connectionClosing) {
                    return table;
                }
                if (!this.httpUtils.checkResponse(response)) {
                    log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                            + configuration.getMdx().generateMDX());
                    return table;
                }

                if (!this.connectionClosing) {
                    try (JsonParser parser = JsonUtils.getFactory().createParser(response.getEntity().getContent());) {
                        JsonToken jsonToken = parser.getCurrentToken();
                        while (jsonToken != JsonToken.END_OBJECT) {
                            if ((JsonToken.FIELD_NAME == jsonToken) && "ID".equals(parser.getCurrentName())) {
                                log.debug("found id");
                                parser.nextToken();
                                cellSetId = parser.getText();
                                break;
                            }
                            jsonToken = parser.nextToken();
                        }
                        EntityUtils.consume(response.getEntity());
                    }
                }
                if (response != null) {
                    response.close();
                }
            }
            ConnectionData data = null;
            if (!this.connectionClosing) {
                // now we have the cube, read the axes
                _command = command.getAxes();
                uriRequest.setLength(0);
                commandString = _command.generateRequestForCellset(uriRequest, cellSetId, Command.HEADERONLYLIST)
                        .toString();
                log.debug("retrieving axes");
                // response = iface.executeRequestAsHttpResponse("GET", commandString, passport,
                // null, connectionTimeout, muiToken.getCookie());
                data = iface.executeRequestAsConnectionData("GET", commandString, null, null, connectionTimeout,
                        muiToken.getCookie());
                if (data != null) {
                    response = data.getResponse();
                    if (!(this.httpUtils.checkResponse(response))) {
                        log.error("values that generated error : \n\tcommand string:" + commandString + "\n\tmdx:"
                                + configuration.getMdx().generateMDX());
                        data.close();
                        return table;
                    }
                }
            }
            final Tm1JsonReader reader = new Tm1JsonReader();
            muiToken.addConnectionClosingListener(reader);
            table = reader.readAxes(response.getEntity().getContent(), false, true);
            table.setCellSetId(cellSetId);
            muiToken.removeConnectionClosingListener(reader);
            data.close();
        } catch (final ClientProtocolException e) {
            log.error("Error connecting to remote server ", e);
        } catch (final IOException e) {
            log.error("IO error during client server communication", e);
        } catch (final URISyntaxException e) {
            log.error("Error generating URI ", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (final Exception e) {
                    log.warn("error closing response, might lead to memory leak", e);
                }
            }
            muiToken.removeConnectionClosingListener(this);
        }
        return table;
    }

    @Override
    public void connectionClosing() {
        log.debug("connection to tm1 closed by consuelo on " + this.hashCode());
        this.connectionClosing = true;
    }

    @Override
    public boolean forceCloseSession(@NonNull final Cookie cookie, @NonNull final ConnectionProfile profile) {
        final Double connectionTimeout = this.applicationConfiguration.getConnectionTimeout();
        try (final Tm1Tunnel iface = new Tm1Tunnel(null);) {
            iface.setProfile(profile);
            log.debug("closing session with cookie " + cookie);
            return iface.closeSession(cookie, connectionTimeout);
        }

    }
}
