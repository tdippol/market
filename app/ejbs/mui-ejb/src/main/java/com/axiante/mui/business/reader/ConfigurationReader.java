package com.axiante.mui.business.reader;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.ConfigurationFilterCatalog;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.tm1.mdx.Command;
import com.axiante.tm1.mdx.QueryParser;
import com.axiante.utility.Constants;
import com.axiante.utility.configuration.Configuration;
import com.axiante.utility.configuration.DynamicColumnsSettings;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@SessionScoped
@Slf4j
public class ConfigurationReader implements Serializable {
	private static final long serialVersionUID = 1351718445124084614L;

	@Getter
	private final Map<String, Configuration> configurations = new HashMap<>();
	@Getter
	private final List<String> gridList = new ArrayList<>();

	@Inject
	transient ConnectionCatalog connectionCatalog;

	@Inject
	transient ApplicationConfiguration applicationConfiguration;

	@Inject
	transient ConfigurationFilterCatalog filterCatalog;

	/**
	 * reads the available configurations from a configuration file
	 *
	 * @param json
	 * @throws IOException
	 */
	public ConfigurationReader read(@NonNull final String json) throws IOException {
		this.getConfigurations().clear();
		this.getGridList().clear();
		ConnectionProfile profile = null;
		Map<String, Integer> pageLinks = null;
		try (JsonParser parser = JsonUtils.getFactory().createParser(json)) {
			JsonToken current = parser.getCurrentToken();
			boolean showVersion = false;
			while (current != JsonToken.END_OBJECT) {
				if (JsonToken.FIELD_NAME == current) {
					switch (parser.getCurrentName()) {
					case "connection":
						showVersion = false;
						profile = this.connectionCatalog.getProfile(parser.nextTextValue());
						break;
					case "pageLinks":
						pageLinks = this.readPageLinks(parser);
						break;
					case "configurations":
						this.readConfigurations(parser, profile, showVersion, pageLinks);
						break;
					case "show-version":
						showVersion = JsonUtils.readBoolean(parser);
						break;
					}
				}
				current = parser.nextToken();
				if (current == null) {
					log.error("Unexpected end of input reading JSON: \n" + json);
					return this;
				}
			}
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Integer> readPageLinks(@NonNull final JsonParser parser) throws IOException {
		JsonToken current = parser.nextToken();
		Map<String, Integer> map = null;
		if (current == JsonToken.START_OBJECT) {
			map = JsonUtils.getMapper().readValue(parser, Map.class);
		} else {
			log.error("Error reading configuration");
		}
		return map;
	}

	protected void readConfigurations(@NonNull final JsonParser parser, final ConnectionProfile profile,
			final boolean showVersion, final Map<String, Integer> pageLinks) throws IOException {
		JsonToken current = parser.nextToken();
		if (current != JsonToken.START_ARRAY) {
			log.error("error reading configuration: expected start array for token configurations");
		} else {
			while (current != JsonToken.END_ARRAY) {
				if (current == JsonToken.START_OBJECT) {
					final Configuration configuration = this.readConfiguration(parser);
					if (configuration == null) {
						throw new IOException("Error parsing file");
					}
					configuration.setProfile(profile);
					configuration.setShowVersion(showVersion);
					configuration.setPageLinks(pageLinks);
					if (configuration.getGridFilters() != null) {
						this.validateGridFilters(configuration);
					} else {
						log.debug("no grid filter configured");
					}
					this.getConfigurations().put(configuration.getName(), configuration);
					this.getGridList().add(configuration.getName());
					if (parser.getCurrentToken() == JsonToken.END_OBJECT) {
						current = parser.nextToken();
					}
				} else {
					current = parser.nextToken();
				}
			}
		}
	}

	private void validateGridFilters(@NonNull final Configuration configuration) {

		if (configuration.getGridFilters() != null) {
			final Iterator<Entry<String, String>> iterator = configuration.getGridFilters().entrySet().iterator();
			Entry<String, String> entry;
			ConfigurationElement entity = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
				entity = this.filterCatalog.findByCodeAndAttribute(entry.getKey(), entry.getValue());
				if (entity == null) {
					log.debug("error looking up " + entry.getKey() + "::" + entry.getValue()
					+ " => removing from current configuration ");
					iterator.remove();
				} else {
					// TODO:maybe make sure you have the correct data looked up ?
				}
			}
		}
	}

	public Configuration readConfiguration(@NonNull final JsonParser parser) throws IOException {
		final Configuration configuration = Configuration.builder().build();
		final QueryParser mdxParser = new QueryParser();
		JsonToken current = parser.getCurrentToken();
		if (current != JsonToken.START_OBJECT) {
			current = parser.nextToken();
		}
		if (current != JsonToken.START_OBJECT) {
			log.error("expected start object for configurations array");
			return null;
		} else {
			while (current != JsonToken.END_OBJECT) {
				if (current == JsonToken.FIELD_NAME) {
					switch (parser.getCurrentName()) {
					case "name":
						configuration.setName(parser.nextTextValue());
						break;
					case "maxCells":
						configuration.setMaxCells(parser.nextIntValue(0));
						break;
					case "maxCellsMessage":
						configuration.setMaxCellsMessage(parser.nextTextValue());
						break;
					case "ExecuteMDX":
						configuration.setCommand(this.readCommand(parser));
						break;
					case "command":
						break;
					case "MDX":
						configuration.setMdx(mdxParser.read(parser));
						break;
					case "logMemory":
						configuration.setLogMemory(parser.nextBooleanValue());
						break;
					case "logTime":
						configuration.setLogTime(parser.nextBooleanValue());
						break;
					case "skip":
						configuration.setSkip(parser.nextBooleanValue());
						break;
					case "autoGroupColumnDef":
						configuration.setAutoGroupColumnDef(JsonUtils.readRawObject(parser));
						break;
					case "columnDefs":
						configuration.setColumnDefs(JsonUtils.readRawObject(parser));
						break;
					case "rowClassRules":
						configuration.setRowClassRules(JsonUtils.readRawObject(parser));
						break;
					case "groupRowAggNodes":
						configuration.setGroupRowAggNodes(JsonUtils.readRawObject(parser));
						break;
					case "DynamicColumns":
						configuration.setDynamicColumns(JsonUtils.readBoolean(parser));
						break;
					case "DynamicColumnsSettings":
						parser.nextToken();
						configuration.setDynamicColumnsSettings(
								JsonUtils.getMapper().readValue(parser, DynamicColumnsSettings.class));
						break;
					case "rowSuppressionEnabled":
						configuration.setRowSuppressionEnabled(JsonUtils.readBoolean(parser));
						break;
					case "colSuppressionEnabled":
						configuration.setColSuppressionEnabled(JsonUtils.readBoolean(parser));
						break;
					case "suppressRowClickSelection":
						configuration.setSuppressRowClickSelection(JsonUtils.readBoolean(parser));
						break;
					case "pagination":
						configuration.setPagination(JsonUtils.readBoolean(parser));
						break;
					case "preSelections":
						configuration.setPreSelections(JsonUtils.readRawObject(parser));
						break;
					case "selections":
						configuration.setSelections(JsonUtils.readRawObject(parser));
						break;
					case "actions":
						configuration.setActions(JsonUtils.readRawObject(parser));
						break;
					case "alertNoDataFound":
						configuration.setAlertNoDataFound(JsonUtils.readBoolean(parser));
						break;
					case "forceSuppression":
						configuration.setForceSuppression(JsonUtils.readBoolean(parser));
						break;
					case "height":
						configuration.setHeight(JsonUtils.readInt(parser));
						break;
					case "title":
						configuration.setTitle(JsonUtils.readString(parser));
						break;
					case "css":
						configuration.setCss(JsonUtils.readString(parser));
						break;
					case "compulsoryFilters":
						configuration.setCompulsoryFilters(JsonUtils.readStringList(parser));
						break;
					case "compulsoryFiltersMessage":
						configuration.setCompulsoryFiltersMessage(JsonUtils.readRawObject(parser));
						break;
					case "gridFilters": {
						configuration.setGridFilters(JsonUtils.parseData(parser));
						break;
					}

					default:
						// we're skiping what we don't read
						JsonUtils.readRawObject(parser);
						break;
					}
				}
				current = parser.nextToken();
			}
		}
		if (configuration.getMaxCells() == null) {
			configuration.setMaxCells(this.applicationConfiguration.getGlobalMaxCell());
		} else {
			if (configuration.getMaxCells() == 0) {
				configuration.setMaxCells(this.applicationConfiguration.getGlobalMaxCell());
			}
		}
		if (configuration.getHeight() == null) {
			configuration.setHeight(this.applicationConfiguration.getDefaultGridHeight());
		}
		if (configuration.getTitle() == null) {
			configuration.setTitle(configuration.getName());
		}

		return configuration;
	}

	private Command readCommand(@NonNull final JsonParser parser) throws IOException {
		final Command root = Command.createRootContainer();
		root.addChildContainer(Constants.CUBE_COMMAND, Constants.OPERATION_EXPAND); // $expand=Cube
		root.addChild(this.initializeCommand()); // $expand=Cube,Axes($expand=Hierarchies($select=Name))
		JsonToken current = parser.nextToken();
		if (current != JsonToken.START_OBJECT) {
			log.error("error reading ExecuteMDX command: expected start object, found " + current.asString());
		} else {
			while (current != JsonToken.END_OBJECT) {
				if (current == JsonToken.FIELD_NAME) {
					switch (parser.getCurrentName()) {
					case "Members":
						final Command tuples = Command.createRootContainer();
						tuples.setName(Constants.TUPLES_COMMAND);
						final Command members = Command.createRootContainer();
						members.setName(parser.getCurrentName());
						current = parser.nextToken();
						if (current != JsonToken.START_ARRAY) {
							log.error("error reading members: expected array, found " + current.asString());
						} else {
							while ((current = parser.nextToken()) != JsonToken.END_ARRAY) {
								members.addChildContainer(parser.getText());
							}
						}
						if (members.getChildren().size() > 0) {
							members.getChildren().get(0).setOperation(Constants.OPERATION_SELECT);
						} // Members($select=[field], [field], ..., [field])
						members.setOperation(Constants.OPERATION_EXPAND);// $expand=Members($select=[field], [field],
						// ...,
						// [field])
						tuples.addChild(members);// Tuples($expand=Members($select=[field], [field], ..., [field]))

						root.getCommand(Constants.AXES_COMMAND, false).addChild(tuples);// $expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($select=[field],
						// [field], ..., [field]))
						break;
					case "Cells":
						final Command cells = Command.createRootContainer();
						cells.setName(Constants.CELLS_COMMAND);// Cells()
						current = parser.nextToken();
						if (current != JsonToken.START_ARRAY) {
							log.error("error reading members: expected array, found " + current.asString());
						} else {
							while ((current = parser.nextToken()) != JsonToken.END_ARRAY) {
								cells.addChildContainer(parser.getText());
							} // Cells(property1,property2,...,propertyn)
						}
						if (cells.getChildren().size() > 0) {
							cells.getChildren().get(0).setOperation(Constants.OPERATION_SELECT);
						} // Cells($select=property1,property2,...,propertyn)
						root.addChild(cells);// $expand=Cube,Axes($expand=Hierarchies($select=Name),Tuples($select=[field],
						// [field], ...,
						// [field])),Cells($select=property1,property2,...,propertyn)
						break;
					default:
						break;
					}
				}
				current = parser.nextToken();
			}
		}

		return root;
	}

	private Command initializeCommand() {
		final Command hierarchies = Command.createRootContainer();
		hierarchies.setName(Constants.HIERARCHIES_COMMAND);
		final Command container = Command.createRootContainer();
		container.setName("Name"); // Name
		container.setOperation(Constants.OPERATION_SELECT);
		hierarchies.addChild(container); // Hierarchies($select=Name)
		hierarchies.setOperation(Constants.OPERATION_EXPAND); // $expand=Hierarchies($select=Name)
		final Command axes = Command.createRootContainer();
		axes.setName(Constants.AXES_COMMAND);
		axes.addChild(hierarchies);// Axes($expand=Hierarchies($select=Name))
		return axes;
	}

	public Configuration getConfiguration(@NonNull final String name) {
		return this.getConfigurations().get(name);
	}

}
