package com.axiante.mui.webapp.views;

import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.business.reader.ViewFilterConfigurationReader;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.filter.utils.FilterUtils;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.utility.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.MenuItem;

@Slf4j
public abstract class AbstractMuiView implements MuiViewInterface {
	private static final long serialVersionUID = -3833754618673558842L;

	@Inject
	UserService userService;

	@Inject
	@Getter(value = AccessLevel.PRIVATE)
	MuiService muiService;

	@Inject
	CookieRepository cookieRepository;

	@Inject
	FilterUtils filterUtils;
	@Getter
	private String viewFile;

	@Getter
	@Setter
	Map<String, Configuration> configurationMap;

	private MenuItem node;

	protected String jsonFilter;

	@Getter
	private final String contentPath = "content/";

	@Getter
	private List<ConfigurationElement> availableFilters;

	@Setter
	private String currentJsonFilter;

	@Setter
	private String adminJsonFilter;

	@Setter
	// @Getter
	private List<IngridFilter> currentIngridJsonFilter;

	@Setter
	@Getter
	private boolean disableButtonFilters = true;

	String name;

	abstract protected ApplicationFilterCatalogProducer getCatalogProducer();

	abstract protected CatalogReducer getCatalogReducer();

	// @Inject
	// private PromoService promoService;

	@Getter
	@Setter
	List<String> availableGrids;

	// @Setter
	private List<String> enabledConnections;

	@Override
	public void setEnabledConnections(List<String> enabledConnections) {
		if (this.enabledConnections == null) {
			this.enabledConnections = new ArrayList<>();
		} else {
			this.enabledConnections.clear();
		}
		if (Objects.nonNull(enabledConnections)) {
			this.enabledConnections.addAll(enabledConnections);
		}
	}

	@Override
	public List<String> getEnabledConnections() {
		return this.enabledConnections;
	}
	// private String viewConfigurationFile;

	@Getter(value = AccessLevel.PRIVATE)
	@Setter(value = AccessLevel.PRIVATE)
	private MenuEntity menu;

	@Override
	public Configuration getConfiguration(final String name) {
		return this.getConfigurationMap().get(name);
	}

	protected void setViewFile(final String url) {
		this.viewFile = new StringBuilder(this.getContentPath()).append(url).toString();
	}

	protected UserDTO getUserDto() {
		return getCurrentUser() != null ? userService.asDto(getCurrentUser()) : null;
	}

	@Override
	public String getName() {
		if (this.node != null) {
			if (this.node.getTitle() != null) {
				return this.node.getTitle();
			}
			return name;
		}
		log.warn("Node is null!");
		return "";

	}

	@Override
	public synchronized void prepareJsonFilter() {
		/**
		 * 1. Filtrare catalogo filtri con valori del filtro per la view (getFilterConfigurationFile) 
		 * 2. Per ogni dimensione del catalogo filtrato 
		 * 2.1 Generare mdx per ottenere json data (-> tabella delle possibili combinazioni dei filtri) 
		 * 2.2 Generare json per filtro 
		 * 2.2.1 Generare configurazione per dimensione (dimensione + attributi) 
		 * 2.2.2 Chiamare tm1tunnel con mdx generato in 2.1 
		 * 2.2.2.1 trasformare risposta in tabella json 
		 * 2.2.3 aggiungere tabella json in 2.2.2.1 in coda a configurazione dimensione con nome jsondata 
		 * 3 Accodare dimensioni generate in stringa di ritorno 
		 * 4 Abilitare bottone filtri
		 * 5 Fine
		 */
		final String filterJson = this.getFilterConfigurationFile();

		if (filterJson != null) {
			try {
				if (this.getCatalogReducer() == null) {
					log.debug("no catalog reducer implemented in current view: persistent filters disabled");
				} else {
					this.availableFilters = this.getCatalogReducer()
							.filterCatalog(new ViewFilterConfigurationReader().readFilters(filterJson));
					if ( this.availableFilters == null ){
						this.availableFilters = new ArrayList<>();
						log.warn("Nessun filtro abilitato per l'utente " + getCurrentUser().getName());
					}
					// adesso marca non available tutti i filtri che insistono su connessioni
					// non abilitate

					if (getEnabledConnections() != null) {
						try {
							this.availableFilters.stream().filter(Objects::nonNull).forEach(
									filter -> {
										if ( filter.getServer() != null && filter.getServer().getName() != null ) {
											filter.setAvailable(getEnabledConnections().contains(filter.getServer().getName()));
										} else {
											if ( log.isWarnEnabled()) {
												String buf = null;
												if (filter.getServer() == null) {
													buf = "Profilo di connessione non valido ";
												} else {
													buf = "Server name non valido ";
												}
												log.warn(String.format("Errore di configurazione per il filtro %s : %s", filter.getCode(), buf));
											}
										}
									});
						} catch (NullPointerException npe){
							StringBuffer buf = new StringBuffer();
							if ( availableFilters == null ){
								buf.append("availableFilters is null ! \n");
							}

							log.error("Errore durante l'abilitazione dei filtri", npe);
						}
					} else {
						log.warn("Nessun server TM1 abilitato per l'utente " + getCurrentUser().getName());
					}
				}
			} catch (final Exception e) {
				log.warn("there was an error retrieving the filters", e);
				this.availableFilters = null;
			}
			if ((this.availableFilters != null) && (this.availableFilters.size() > 0)) {
				if (!getViewType().equals(ViewType.WELCOME)) {
					this.jsonFilter = this.getCatalogProducer().toJson(this.availableFilters);
				}
			} else {
				log.warn("there are no available filters for the current configuration");
			}
		} else {
			log.warn("the configuration for the filters of the current view is null");
		}
		this.updateFilterButtonDisabled();
	}

	private String getFilterConfigurationFile() {
		try {
			final String filterPath = this.node.getParams().get("path").get(0).replaceAll(" ", "_");
			log.debug("filter -> " + filterPath);
			final Integer idMenu = Integer.parseInt(this.node.getParams().get("idMenu").get(0));
			ConfigurationEntity configurationEntity = null;
			if (idMenu <= 0) {
				// these are special menu items not linked with the menu table
				final List<ConfigurationEntity> configurations = this.muiService.findConfigurationByIdMenu(idMenu);
				if ((configurations != null) && (configurations.size() > 0)) {
					configurationEntity = configurations.stream()
							.filter(c -> c.getType().equals(ConfigurationTypes.FILTER))
							.filter(c -> c.getRevisionDate() == null).findFirst().orElse(null);
				}
			} else {
				final MenuEntity menu = this.muiService.findMenu(idMenu);
				if (menu != null) {
					configurationEntity = menu.getConfigurations().stream()
							.filter(c -> c.getType().equals(ConfigurationTypes.FILTER))
							.filter(c -> c.getRevisionDate() == null).findFirst().orElse(null);
				} else {
					log.error("error reading filters from " + filterPath);
				}
			}
			if (configurationEntity != null) {
				return configurationEntity.getJson();
			} else {
				log.error("error reading filters from " + filterPath);
			}

		} catch (final Exception e) {
			log.error("error reading configuration for menu " + this.node.getValue(), e);
		}
		return null;
	}

	private void updateFilterButtonDisabled() {
		if ((this.getAvailableFilters() != null) && (this.getAvailableFilters().size() > 0)) {
			this.setDisableButtonFilters(false);
		} else {
			this.setDisableButtonFilters(true);
		}
	}

	@Override
	public void setNode(final MenuItem node) {
		this.name = node.getValue().toString();
		this.node = node;
		try {
			this.setViewFile(node.getParams().get("url").get(0));
		} catch (final NullPointerException e) {
			log.error(
					"the node " + node.getValue() + " is not properly configured as it is missing the url declaration",
					e);
		}
	}

	@Override
	public String getCurrentJsonFilter() {
		return this.currentJsonFilter == null ? "''" : this.currentJsonFilter;
	}

	@Override
	public String getAdminJsonFilter() {
		return this.adminJsonFilter == null ? "''" : this.adminJsonFilter;
	}

	@Override
	public String getJsonFilter() {
		return this.jsonFilter == null ? "''" : this.jsonFilter;
	}

	@Override
	public Boolean getSuppressStatus(final String gridId, final String type) {
		if (this.getConfiguration(gridId) != null) {
			switch (type) {
			case "columns":
				return this.getConfiguration(gridId).getMdx().getCols().isNonEmpty();
			case "rows":
				return this.getConfiguration(gridId).getMdx().getRows().isNonEmpty();
			default:
				log.warn("Illegal type call during suppress data check.");
			}

		}
		return null;
	}

	@Override
	public void removeSpinner(@NonNull final String grid) {
		PrimeFaces.current().executeScript("$(\"#loading_" + grid + "\").hide();$(\"." + grid + "\").show();");
	}

	@Override
	public void clearGridData(@NonNull final String grid) {
		this.removeSpinner(grid);
		PrimeFaces.current().executeScript("clearGridData('" + grid + "');");
	}

	@Override
	public boolean canLoadData() {
		return true;
	}

	@Override
	public boolean canLoadData(final String grid) {
		return true;
	}

	@Override
	public UsersEntity getCurrentUser() {
		return (UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE);
	}

	@Override
	public List<IngridFilter> getCurrentIngridJsonFilter() {
		log.debug("returning a list of "
				+ (((this.currentIngridJsonFilter != null) && (this.currentIngridJsonFilter.size() > 0))
						? this.currentIngridJsonFilter.toString()
								: "0")
				+ " elements");
		return this.currentIngridJsonFilter;
	}

	@Override
	public void persistIngridFilter(final String grid) {
		// per ogni utente e griglia
		// getCurrentUser()
		// salvo lo stato dei filtri correnti
		// getCurrentIngridJsonFilter()
	}

	@Override
	public synchronized void prepareDBPromoJsonFilter() {
		/**
		 * M.F.: i filtri db promo non devono comparire nelle maschere mui
		 */
	}

	@Override
	public void free() {
		this.adminJsonFilter = null;
		if (this.availableGrids != null) {
			try {
				this.availableGrids.clear();
			} catch (Exception e) {
				log.error("Error cleaning available grids", e);
			} finally {
				this.availableGrids = null;
			}
		}
		this.viewFile = null;
		if (this.configurationMap != null) {
			try {
				this.configurationMap.clear();
			} catch (Exception e) {
				log.error("Error cleaning configuration map", e);
			} finally {
				this.configurationMap = null;
			}
		}
		this.node = null;
		this.jsonFilter = null;
		if (this.availableFilters != null) {
			try {
				this.availableFilters.clear();
			} catch (Exception e) {
				log.error("Error cleaning available filters", e);
			} finally {
				this.availableFilters = null;
			}
		}
		this.currentJsonFilter = null;
		this.adminJsonFilter = null;
		if (this.currentIngridJsonFilter != null) {
			try {
				this.currentIngridJsonFilter.clear();
			} catch (Exception e) {
				log.error("Error cleaning ingrid json filter", e);
			} finally {
				this.currentIngridJsonFilter = null;
			}
		}
		this.name = null;
		if (getEnabledConnections() != null) {
			try {
				this.getEnabledConnections().clear();
			} catch (Exception e) {
				log.warn(String.format(
						"Error cleaning the list of the enabled connections : %s \nCreating new object...",
						e.getMessage()), e);
			} finally {
				this.setEnabledConnections(null);
			}
		}
	}

	@Override
	public ConfigurationEntity getActiveConfiguration(ConfigurationTypes type) {
		if ((this.node != null) && (this.node.getParams() != null) && (this.node.getParams().get("idMenu") != null)) {
			final Integer idMenu = Integer.parseInt(this.node.getParams().get("idMenu").get(0));
			if (idMenu != null) {
				List<ConfigurationEntity> configurations = getMuiService().findConfigurationByIdMenu(idMenu);
				if ((configurations != null) && (configurations.size() > 0)) {
					return configurations.stream()
							.filter(c -> type.equals(c.getType()) && (c.getRevisionDate() == null)).findFirst()
							.orElse(null);
				}
			}
			log.error("error reading filters from menu id " + idMenu);
			return null;
		} else {
			log.error("Menu node for current view is not set !");
			return null;
		}
	}

	@Override
	public String getContesto() {
		return "''";
	}

}
