package com.axiante.mui.webapp.utils.cells;

import com.axiante.Tm1Tunnel;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.utils.FilterUtils;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.mui.webapp.utils.mdx.ColumnDimensionProducer;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.MuiViewAcionInterface;
import com.axiante.mui.webapp.views.MuiViewInterface;
import com.axiante.tm1.json.AgGridInMemoryInputStream;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import com.axiante.tm1.mdx.objects.MdxEntry;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

@Slf4j
@Dependent
public class CellUtils implements FacesContextAware, Serializable {
	private static final long serialVersionUID = 293744541842676641L;
	@Inject
	FilterUtils filterUtils;
	@Inject
	ApplicationConfiguration configuration;
	@Inject
	CookieRepository cookieRepository;
	@Inject
	TableProducer tableProducer;
	@Inject
	ColumnDimensionProducer columnDimensionProducer;

	public void getPicklistValues(final Map<String, String> params, final MuiViewInterface currentView) {
		final String data = params.get("data");
		final ArrayList<String> buffer = new ArrayList<>();
		try {
			if ((data != null) && (data.trim().length() > 0)) {
				Configuration gridConfiguration = null;
				final Map<String, String> mappa = JsonUtils.parseData(data);
				String rowKey = null;
				String grid = null;
				String ordinal = null;
				rowKey = mappa.get("row");
				grid = mappa.get("grid");
				ordinal = mappa.get("ordinal");
				if (gridConfiguration == null) {
					gridConfiguration = currentView.getConfiguration(grid);
				}
				if (gridConfiguration != null) {
					final List<Dimension> rows = filterUtils.unpackRowsToQueuedDimension(rowKey);

					final Query query = currentView.prepareFilteredQuery(grid).copy();
					final MdxEntry rowsEntry = new MdxEntry();
					rowsEntry.addAllDimension(rows);
					rowsEntry.setNonEmpty(false);
					rowsEntry.addDimension(new Dimension("", " , ", Type.SEPARATOR));
					query.setRows(rowsEntry);
					query.setWhere(null);
					final Configuration actualConfiguration = gridConfiguration.toBuilder().build();
					if (query.getCols().isNonEmpty()) {
						if (currentView instanceof MuiViewAcionInterface) {
							final Row<Cell> activeHeaders = ((MuiViewAcionInterface) currentView)
									.getGridCurrentHeaders(grid);
							if ((activeHeaders != null) && (activeHeaders.size() > 0)) {
								final List<Dimension> columnsForQuery = new ArrayList<>();
								activeHeaders.forEach(header -> {
									columnsForQuery.addAll(
											columnDimensionProducer.getFromStringConfiguration(header.getName()));
								});
								final MdxEntry columns = new MdxEntry();
								columns.addAllDimension(
										columnsForQuery.parallelStream().distinct().collect(Collectors.toList()));
								columnsForQuery.clear();
								query.setCols(columns);
								query.setCartesianColumns(false); // this fires different generation
							}
						}
					}
					// Fix CAMID value replacing //
					query.getRows().getDimensions().stream().filter(d -> Objects.nonNull(d.getValue()))
							.filter(d -> d.getValue().contains("CAMID")).forEach(d -> {
								d.setValue(d.getValue().replaceAll(Pattern.quote("\\"), ""));
							});
					query.getCols().getDimensions().stream().filter(d -> Objects.nonNull(d.getValue()))
							.filter(d -> d.getValue().contains("CAMID")).forEach(d -> {
								d.setValue(d.getValue().replaceAll(Pattern.quote("\\"), ""));
							});
					actualConfiguration.setMdx(query);
					log.debug("generating updated table set from tm1");

					buffer.addAll(tableProducer.producePicklist(actualConfiguration, query, new Integer(ordinal),
							configuration.getConnectionTimeout(), configuration.getSocketTimeout(),
							configuration.getConnectionRequestTimeout(),
							cookieRepository.getCookie(gridConfiguration.getProfile())));
				}
			}
		} catch (final Exception e) {
			log.error("error retrieving picklist values ", e);
		}
		try {
			returnJsCallbackResponse("picklist", JsonUtils.getMapper().writeValueAsString(buffer));
		} catch (final JsonProcessingException e) {
			log.error("Error returning picklist values ", e);
			returnJsCallbackResponse("picklist", "[]");
		}
	}

	public void updateCellsByOrdinal(final Map<String, String> params, final MuiViewInterface currentView) {
		final String data = params.get("data");
		String rowKey = null;
		String value = null;
		String column = null;
		String grid = null;
		String oldValue = null;
		String nodeId = null;

		final ArrayList<String> nodes = new ArrayList<>();
		Configuration gridConfiguration = null;

		if (data == null) {
			log.error("no data map in request parameter");
			returnJsCallbackResponse("updateResult", false);
			return;
		}
		try {
			final ArrayList<Map<String, String>> mapList = JsonUtils.parseDataMap(data);
			boolean updateStatus = true;
			final List<Dimension> rows = new ArrayList<>();
			final HttpUtils httpUtils = new HttpUtils();
			for (final Map<String, String> mappa : mapList) {
				rowKey = mappa.get("rows");
				value = mappa.get("newValue");
				column = mappa.get("columns");
				grid = mappa.get("grid");
				oldValue = mappa.get("oldValue");
				nodeId = mappa.get("nodeId");
				if (gridConfiguration == null) {
					gridConfiguration = currentView.getConfiguration(grid);
				}

				if ((rowKey == null) || (column == null) || (value == null)) {
					setUpdateErrorData(grid, column, oldValue, nodeId);
					return;
				}
				// now, use the dimension object as a fake dimension
				final List<String> tuple = filterUtils.unpack(rowKey, column);
				if (tuple == null) {
					// error already logged
					setUpdateErrorData(grid, column, oldValue, nodeId);
					return;
				}
				if (!oldValue.equals(value)) {
					nodes.add(nodeId);
					IntStream.range(0, tuple.size()).filter(i -> tuple.get(i).contains("CAMID")).forEach(i -> {
						tuple.set(i, tuple.get(i).replaceAll(Pattern.quote("\\"), ""));
					});
					try (Tm1Tunnel tunnel = new Tm1Tunnel(gridConfiguration.getProfile())) {
						// single grid view
						try (CloseableHttpResponse res = tunnel.cellPut(
								gridConfiguration.getMdx().getFrom().getCubeName(), tuple, value,
								configuration.getConnectionTimeout(), configuration.getSocketTimeout(),
								configuration.getConnectionRequestTimeout(),
								cookieRepository.getCookie(gridConfiguration.getProfile()))) {
							updateStatus &= ((res != null) && httpUtils.checkResponse(res, true));
						}
						rows.addAll(filterUtils.unpackRowsToQueuedDimension(rowKey));
					}
				}
			}
			log.debug("update result " + updateStatus);
			returnJsCallbackResponse("updateResult", updateStatus);

			if (!updateStatus) {
				setUpdateErrorData(grid, column, oldValue, nodeId);
				return;
			}
			if ((gridConfiguration != null)) {
				if ((rows != null) && (rows.size() == 0)) {
					// nothing to reload
					returnJsCallbackResponse("data", "{}");
					returnJsCallbackResponse("grid", grid);
					returnJsCallbackResponse("nodeId", "[]");
				} else {
					log.debug("starting calculation of the rows to return");
					for (final int index : IntStream.range(0, rows.size())
							.filter(i -> rows.get(i).getValue().contains("CAMID")).toArray()) {
						rows.get(index).setValue(rows.get(index).getValue().replaceAll(Pattern.quote("\\"), ""));
					}

					final List<Dimension> temp = rows.stream().distinct().collect(Collectors.toList());
					rows.clear();
					rows.addAll(temp);
					temp.clear();

					final Query query = currentView.prepareFilteredQuery(grid).copy();
					final MdxEntry rowsEntry = new MdxEntry();
					rowsEntry.addAllDimension(rows);
					rowsEntry.setNonEmpty(false);
					rowsEntry.addDimension(new Dimension("", " , ", Type.SEPARATOR));
					query.setRows(rowsEntry);
					query.setWhere(null);
					final Configuration actualConfiguration = gridConfiguration.toBuilder().build();
					if (query.getCols().isNonEmpty()) {
						boolean showMessage = false;
						if (currentView instanceof MuiViewAcionInterface) {
							final Row<Cell> activeHeaders = ((MuiViewAcionInterface) currentView)
									.getGridCurrentHeaders(grid);
							if ((activeHeaders != null) && (activeHeaders.size() > 0)) {
								final ColumnDimensionProducer producer = new ColumnDimensionProducer();
								final List<Dimension> columnsForQuery = new ArrayList<>();
								activeHeaders.forEach(header -> {
									columnsForQuery.addAll(producer.getFromStringConfiguration(header.getName()));
								});
								final MdxEntry columns = new MdxEntry();
								columns.addAllDimension(
										columnsForQuery.parallelStream().distinct().collect(Collectors.toList()));
								columnsForQuery.clear();
								query.setCols(columns);
								query.setCartesianColumns(false); // this fires different generation
							} else {
								showMessage = true;
							}
						}
						if (showMessage) {
							addGrowl("Attenzione",
									"Soppressione delle colonne attive, la riga modificata potrebbe contenere colonne non aggiornate.\n Ricaricare manualmente la griglia.",
									FacesMessage.SEVERITY_WARN);
						}
					}
					actualConfiguration.setMdx(query);
					log.debug("generating updated table set from tm1");
					final Table table = tableProducer.produceTable(actualConfiguration, null, null, false,
							cookieRepository.getCookie(actualConfiguration.getProfile()),
							configuration.getConnectionTimeout());
					final StringWriter writer = new StringWriter();
					if (table.isError()) {
						addGrowl("Errore", "Errore durante il ricaricamento dei dati: " + table.getErrorMessage()
								+ "\n ricaricare manualmente la griglia", FacesMessage.SEVERITY_ERROR);
						returnJsCallbackResponse("updateResult", false);
					} else {
						try (AgGridInMemoryInputStream in = new AgGridInMemoryInputStream(table, false)) {
							IOUtils.copy(in, writer, "ISO-8859-15");
							returnJsCallbackResponse("data", writer.toString());
							returnJsCallbackResponse("grid", grid);
							returnJsCallbackResponse("nodeId",
									nodes.size() > 0
											? "[" + nodes.stream().distinct().collect(Collectors.joining(",")) + "]"
											: "");
						}
					}
					log.debug("done");
				}
			}
		} catch (final Exception e) {
			log.error("error parsing data from javascript callback", e);
			setUpdateErrorData(grid, column, oldValue, nodeId);
			return;
		}
	}

	private void returnJsCallbackResponse(final String paramName, final Object paramValue) {
		getAjax().addCallbackParam(paramName, paramValue);
	}

	private void setUpdateErrorData(final String grid, final String column, final String oldValue, String nodeId) {
		returnJsCallbackResponse("grid", grid);
		returnJsCallbackResponse("columns", column);
		returnJsCallbackResponse("oldValue", oldValue);
		returnJsCallbackResponse("updateResult", false);
		returnJsCallbackResponse("nodeId", nodeId != null ? "[" + nodeId + "]" : "[]");
	}

	protected void addGrowl(final String title, final String message, final FacesMessage.Severity type) {
		addMessage(null, new FacesMessage(type, title, message));
	}

	public boolean checkStringLenght(final String string) {
		if (string == null) {
			throw new RuntimeException("test exception");
		}
		return string.length() > 10;
	}
}
