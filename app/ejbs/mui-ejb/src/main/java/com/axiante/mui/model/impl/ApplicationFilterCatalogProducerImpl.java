package com.axiante.mui.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.Cookie;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.ConnectionCatalog;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.CatalogFilterValues;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.DimensionWatcher;
import com.axiante.mui.filter.FilterAttribute;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.Command;
import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.From;
import com.axiante.tm1.mdx.objects.MdxEntry;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class ApplicationFilterCatalogProducerImpl implements ApplicationFilterCatalogProducer {
	private static final long serialVersionUID = 2279044731511171059L;
	@Getter
	List<ConfigurationElement> configurations = new ArrayList<>();

	@Inject
	@Setter
	transient TableProducer tableProducer;

	@Inject
	Instance<CookieRepository> cookieRepositoryInstance;

	CookieRepository cookieRepository;

	@Inject
	DimensionWatcher dimensionWatcher;

	@Inject
	transient ConnectionCatalog connectionCatalog;

	@Inject
	ApplicationConfiguration applicationConfiguration;

	private transient HttpUtils httpUtils = new HttpUtils();

	private static final Command PICKLIST_COMMAND = Command.createRootContainer().parse(
			"Cube,Axes($expand=Hierarchies($select=Name),Tuples($expand=Members($select=Name))),Cells($select=Ordinal,Value)");

	private static final String DBPROMO_JSONFILTER_CODE = "promozioneDBPromo";

	protected transient Function<FilterAttribute, String> filterAttributeToJson = (
			final FilterAttribute attribute) -> new StringBuilder("{").append("\"ATTR_code\":").append("\"")
			.append(attribute.getCode()).append("\"").append(",").append("\"ATTR_columnName\":").append("\"")
			.append(attribute.getColumnName()).append("\"").append(",")

			.append("\"ATTR_desc\":").append("\"").append(attribute.getDesc()).append("\"").append(",")
			.append("\"ATTR_type\":").append("\"").append(attribute.getType()).append("\"")
			.append("}")

			.toString();

			@PostConstruct
			public void post() {
			}

			@Override
			public ApplicationFilterCatalogProducer read(final String json) {
				try (JsonParser parser = JsonUtils.getFactory().createParser(json)) {
					return this.read(parser);
				} catch (final IOException e) {
					log.error("error closing input strean: this could lead to memory/resource leak", e);
					return this;
				}
			}

			protected ApplicationFilterCatalogProducer read(@NonNull final JsonParser parser) {
				try {
					this.configurations.clear();
					JsonToken current = parser.nextToken();
					ConfigurationElement element = null;
					while (current != JsonToken.END_ARRAY) {
						if (current == JsonToken.START_OBJECT) {
							element = ConfigurationElement.builder().build();
							element.setAttributes(new ArrayList<>());
							current = parser.nextToken();
							while (current != JsonToken.END_OBJECT) {
								if (current == JsonToken.FIELD_NAME) {
									switch (parser.getCurrentName()) {
									case CatalogFilterValues.DIM_CODE:
										element.setCode(parser.nextTextValue());
										break;
									case CatalogFilterValues.DIM_COLUMN_NAME:
										element.setColumnName(parser.nextTextValue());
										break;
									case CatalogFilterValues.DIM_DESCRIPTION:
										element.setDescription(parser.nextTextValue());
										break;
									case CatalogFilterValues.ENDPOINT_SERVER_NAME:
										final String serverName = parser.nextTextValue();
										final ConnectionProfile server = this.connectionCatalog.getProfile(serverName);
										if (server == null) {
											log.error("invalid utility set: found " + serverName + " expected any of "
													+ this.connectionCatalog.avaliableConfigurations());
										} else {
											element.setServer(server);
										}
										break;
									case CatalogFilterValues.MDX_JSON_SOURCE:
										element.setMdx(parser.nextTextValue());
										break;
									case CatalogFilterValues.LIST_ATTR:
										current = parser.nextToken();
										FilterAttribute att = null;
										while (current != JsonToken.END_ARRAY) {
											if (current == JsonToken.START_OBJECT) {
												att = new FilterAttribute();
											} else if (current == JsonToken.END_OBJECT) {
												element.getAttributes().add(att);
											} else {
												if (current == JsonToken.FIELD_NAME) {
													switch (parser.getCurrentName()) {
													case "ATTR_code":
														att.setCode(parser.nextTextValue());
														break;
													case "ATTR_columnName":
														att.setColumnName(parser.nextTextValue());
														break;
													case "ATTR_desc":
														att.setDesc(parser.nextTextValue());
														break;
													case "ATTR_type":
														att.setType(parser.nextTextValue());
														break;
													default:
													}
												}

											}

											current = parser.nextToken();
											if (current == null) {
												log.error("Unexpected end of input reading JSON filter configuration");
												return this;
											}
										}

										break;

									default:
										break;
									}

								}
								current = parser.nextToken();
								if (current == null) {
									log.error("Unexpected end of input reading JSON filter configuration");
									return this;
								}
							}
							this.configurations.add(element);
						}
						current = parser.nextToken();
						if (current == null) {
							log.error("Unexpected end of input reading JSON filter configuration");
							return this;
						}
					}
				} catch (final IOException e) {
					log.error("error reading utility file", e);
				} catch (final Exception e) {
					log.error("unexpected error reading utility file", e);
				}

				return this;
			}

			@Override
			public String toJson(@NonNull final List<ConfigurationElement> configurations) {
				final Function<ConfigurationElement, String> configurationToJson = (final ConfigurationElement config) -> {
					if ((config != null) && this.httpUtils.isHostReachable(config.getServer().getValidationHost())) {
						final Cookie cookie = getCookieRepository().getCookie(config.getServer());
						if (cookie == null) {
							log.error("Cannot access TM1 without a session cookie");
						}
						// now you have a cookie for your connection
						final Configuration configuration = Configuration.builder().build();
						configuration.setProfile(config.getServer());
						configuration.setCommand(PICKLIST_COMMAND);
						configuration.setLogMemory(Boolean.FALSE);
						configuration.setLogTime(Boolean.FALSE);

						final Double timeout = this.applicationConfiguration.getConnectionTimeout();
						final StringBuilder result = new StringBuilder("{").append("\"DIM_code\":").append("\"")
								.append(config.getCode()).append("\"").append(",").append("\"DIM_columnName\":").append("\"")
								.append(config.getColumnName()).append("\"").append(",").append("\"DIM_description\":")
								.append("\"").append(config.getDescription()).append("\"").append(",")
								.append("\"ENDPOINT_serverName\":").append("\"").append(config.getServer().getName())
								.append("\"").append(",").append("\"MDX_jsonSource\":").append("\"").append(config.getMdx())
								.append("\"").append(",").append("\"list_ATTR\":[").append(config.getAttributes().stream()
										.map(this.filterAttributeToJson).collect(Collectors.joining(",")))
								.append("]").append(",\"jsonData\":");
						if (this.dimensionWatcher.check(config.getColumnName(), cookie, config.getServer())) {
							result.append("{}");
						} else {
							result.append(this.tableToJson(this.tableProducer.produceTable(configuration,
									this.createPickListQuery(config.getMdx(), config.getColumnName()), null, true, cookie,
									timeout)));
						}
						result.append("}");
						return result.toString();
					} else {
						return null;
					}
				};
				if (getCookieRepository() != null) {
					final String filterJsonResult = new StringBuilder("[").append(configurations.stream()
							.map(configurationToJson).filter(Objects::nonNull).collect(Collectors.joining(","))).append("]")
							.toString();
					return filterJsonResult;
				} else {
					return null;
				}
			}

			@Override
			public List<IngridFilter> inGridPicklistValues(final Configuration gridconfiguration,
					final List<ConfigurationElement> inGridFilters) {
				final Function<ConfigurationElement, IngridFilter> configurationToIngridFilter = (
						final ConfigurationElement config) -> {
							IngridFilter filter = null;
							if ((config != null) && this.httpUtils.isHostReachable(config.getServer().getValidationHost())) {
								final Cookie cookie = getCookieRepository().getCookie(config.getServer());
								if (cookie == null) {
									log.error("Cannot access TM1 without a session cookie");
								}
								// now you have a cookie for your connection
								final Configuration configuration = Configuration.builder().build();
								configuration.setProfile(config.getServer());
								configuration.setCommand(PICKLIST_COMMAND);
								configuration.setLogMemory(Boolean.FALSE);
								configuration.setLogTime(Boolean.FALSE);

								final Double timeout = this.applicationConfiguration.getConnectionTimeout();

								final Query query = this.createIngridPicklistQuery(gridconfiguration, config);

								if (query == null) {

								} else {
									filter = new IngridFilter();
									filter.setDimension(config.getColumnName());
									filter.setLabel(config.getDescription() + "::" + config.getSelectedAttribute().getDesc());
									filter.setAttribute(config.getSelectedAttribute().getColumnName());
									filter = this.tableToFilter(
											this.tableProducer.produceTable(configuration, query, null, true, cookie, timeout), filter);
								}
							} else {
							}
							return filter;
						};
						if (getCookieRepository() != null) {
							return inGridFilters.stream().map(configurationToIngridFilter).filter(Objects::nonNull)
									.collect(Collectors.toList());
						} else {
							return null;
						}

			}

			private String tableToJson(final Table table) {
				final StringBuilder builder = new StringBuilder("[");
				if (!table.isError()) {
					final List<String> headers = table.map(table.getHeaders());
					table.getData().forEach(row -> {
						builder.append(this.getCell(headers, table.map(row)).toString());

					});
					builder.deleteCharAt(builder.length() - 1);
				}
				builder.append("]");
				return builder.toString();
			}

			private IngridFilter tableToFilter(final Table table, final IngridFilter filter) {
				if (!table.isError()) {
					final List<String> headers = table.map(table.getHeaders());
					final int column = headers.lastIndexOf(filter.getAttribute());
					if (column > -1) {
						filter.setValues(table.getData() // only data: this skips headers
								.map(row -> row.get(column)) // map to single column
								.filter(Objects::nonNull) // only non null cells
								.map(Cell::getValue) // map to cell value
								.distinct() // exclude duplicates
								.collect(Collectors.toList()) // convert to list
								);
					}
				}
				return filter;
			}

			private Query createPickListQuery(final String mdx, final String dimension) {
				final Query query = Query.builder().build();
				final MdxEntry rows = new MdxEntry();
				rows.setNonEmpty(false);
				rows.addDimension(new Dimension("", mdx, Dimension.Type.ROWS));
				final MdxEntry cols = new MdxEntry();
				rows.setNonEmpty(false);
				cols.addDimension(new Dimension("",
						" {TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_" + dimension + "] )}, 0)}, ASC)}",
						Dimension.Type.COLUMNS));
				query.setRows(rows);
				query.setCols(cols);
				query.setFrom(new From("[}ElementAttributes_" + dimension + "]"));
				return query;
			}

			private Query createIngridPicklistQuery(final Configuration configuration, final ConfigurationElement element) {
				Query query = null;

				if (element.getSelectedAttribute() != null) {
					final MdxEntry cols = new MdxEntry();
					cols.addDimension(new Dimension("", " {[}ElementAttributes_" + element.getColumnName() + "].["
							+ element.getSelectedAttribute().getColumnName() + "]}", Dimension.Type.COLUMNS));
					// copio dalla query della griglia
					final Query q = configuration.getMdx().copy();
					MdxEntry mdxEntry = q.getRows();
					Iterator<Dimension> dimensionIterator = mdxEntry.getDimensions().iterator();
					// mantengo solo quelli della dimensione che sto cercando
					while (dimensionIterator.hasNext()) {
						final Dimension d = dimensionIterator.next();
						if (!d.getColumn().equals(element.getColumnName())) {
							dimensionIterator.remove();
						}
					}
					if (mdxEntry.getDimensions().size() > 0) {
						log.debug("found dimension on rows");
						query = Query.builder().build();
						query.setRows(mdxEntry);
						query.setCols(cols);
						query.setFrom(new From("[}ElementAttributes_" + element.getColumnName() + "]"));
					} else {
						// devo cercare on cols
						mdxEntry = q.getCols();
						dimensionIterator = mdxEntry.getDimensions().iterator();
						// mantengo solo quelli della dimensione che sto cercando
						while (dimensionIterator.hasNext()) {
							final Dimension d = dimensionIterator.next();
							if (!d.getColumn().equals(element.getColumnName())) {
								dimensionIterator.remove();
							}
						}
						if (mdxEntry.getDimensions().size() > 0) {
							log.debug("found dimension on cols");
							query = Query.builder().build();
							query.setRows(mdxEntry);
							query.setCols(cols);
							query.setFrom(new From("[}ElementAttributes_" + element.getColumnName() + "]"));
						} else {
							log.error("errore di configurazione dei filtri in griglia (" + configuration.getName()
							+ "): la dimensione " + element.getColumnName() + " non compare in riga");
						}
					}
				} else {
					log.error("could not find attribute " + element.getSelectedAttribute().getCode() + " for dimension "
							+ element.getColumnName() + " in the filter catalog");
				}
				if (log.isDebugEnabled() && (query != null)) {
					log.debug("generated mdx for in grid : " + query.generateMDX());
				}
				return query;
			}

			private StringBuilder getCell(final List<String> headers, final List<String> data) {
				final ObjectMapper mapper = new ObjectMapper();
				final StringBuilder stringBuilder = new StringBuilder();
				final StringBuilder builder = new StringBuilder("{");
				builder.append(IntStream.range(0, headers.size())
						.mapToObj(i -> this.getCellElement(stringBuilder, mapper, headers.get(i), data.get(i)))
						.collect(Collectors.joining(",")));
				builder.append("},");
				return builder;
			}

			protected String getCellElement(final StringBuilder stringBuilder, final ObjectMapper mapper, final String name,
					final String value) {
				try {
					return stringBuilder.delete(0, stringBuilder.length()).append(mapper.writeValueAsString(name)).append(":")
							.append(this.toJsonStringValue(mapper, value)).toString();
				} catch (final JsonProcessingException e) {
					log.error("Error converting " + name + " to json ", e);
					return "\"name\":\"contains error\"";
				}
			}

			protected String toJsonStringValue(@NonNull final ObjectMapper mapper, final String string) {
				if (string == null) {
					return "null";
				}
				try {
					return mapper.writeValueAsString(string);
				} catch (final JsonProcessingException e) {
					log.error("Error converting " + string + " to json ", e);
				}
				return null;
			}

			@Override
			public String toDBPromoJson(List<ConfigurationElement> configurations, String pickListJson) {
				final String jsonDataPicklist = (pickListJson != null) && StringUtils.isBlank(pickListJson) ? "[]"
						: pickListJson;

				final Function<ConfigurationElement, String> configurationToJson = (final ConfigurationElement config) -> {

					if (DBPROMO_JSONFILTER_CODE.equals(config.getCode())) {
						final StringBuilder result = new StringBuilder("{").append("\"DIM_code\":").append("\"")
								.append(config.getCode()).append("\"").append(",").append("\"DIM_columnName\":").append("\"")
								.append(config.getColumnName()).append("\"").append(",").append("\"DIM_description\":")
								.append("\"").append(config.getDescription()).append("\"").append(",")
								.append("\"ENDPOINT_serverName\":").append("\"").append(config.getServer()) // Il server è null
								// per DBPromo
								.append("\"").append(",").append("\"MDX_jsonSource\":").append("\"").append(config.getMdx())
								.append("\"").append(",").append("\"list_ATTR\":[")
								.append(config.getAttributes().stream().map(this.filterAttributeToJson)
										.collect(Collectors.joining(",")))
								.append("]").append(",\"jsonData\":").append(jsonDataPicklist).append("}");

						return result.toString();
					} else {
						return (null);
					}

				};

				final String filterJsonResult = new StringBuilder("[").append(configurations.stream().map(configurationToJson)
						.filter(Objects::nonNull).collect(Collectors.joining(","))).append("]").toString();
				return filterJsonResult;
			}

			CookieRepository getCookieRepository() {
				if (cookieRepository == null) {
					if (cookieRepositoryInstance != null) {
						cookieRepository = cookieRepositoryInstance.get();
					} else {
						log.error("cookie repository instance not injected");
					}
				}
				return cookieRepository;
			}
}
