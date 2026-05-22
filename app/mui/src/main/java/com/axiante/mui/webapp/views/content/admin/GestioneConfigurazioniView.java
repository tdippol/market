package com.axiante.mui.webapp.views.content.admin;


import com.axiante.mui.backing.ConfigurationCatalog;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@MuiViewModel("gestioneConfigurazioni")
@Named("gestioneConfigurazioni")
@SessionScoped
@Slf4j
public class GestioneConfigurazioniView extends AbstractAdminView implements Serializable{
	private static final long serialVersionUID = 2719428584938793637L;
	@Getter
	@Setter
	private List<ConfigurationEntity> configurationEntityList;
	@Getter
	@Setter
	private String selectedType;
	@Getter
	private ConfigurationEntity selectedConfiguration;

	@Inject
	@Getter
	transient private ApplicationFilterCatalogProducer catalogProducer;
	@Inject
	@Getter
	transient private CatalogReducer catalogReducer;
	@Inject
	transient private MuiService muiService;
	@Inject
	transient ConfigurationCatalog configurationCatalog;
	//    @Inject
	//    ConfigurationFilterCatalog configurationFilterCatalog;
	@Override
	public void updateView(final String grid) {
		updateView();
	}

	@Override
	public void updateView() {
		readConfigurations(ConfigurationTypes.valueOf(selectedType));
	}

	private void readConfigurations(final ConfigurationTypes type){
		if(type != null){
			try {
				configurationEntityList = muiService.readConfigurations(type);
			} catch (final Exception ignored) {
				log.error("errore durante la lattura delle configurazioni da DB.");
			}
		}else{
			selectedConfiguration = null;
			configurationEntityList = new ArrayList<>();
		}

	}

	public void onSelectedFolderChange() {
		selectedConfiguration = null;
		readConfigurations(ConfigurationTypes.valueOf(selectedType));
	}

	private void format() {
		if(selectedConfiguration!=null){
			final ObjectMapper mapper = JsonUtils.getValidationMapper();
			try {
				final Object json = mapper.readValue(selectedConfiguration.getJson(), Object.class);
				final String indented = JsonUtils.format(json);
				if ( indented != null ) {
					selectedConfiguration.setJson(indented);
				}
			} catch (final IOException e) {
				log.error("error formatting json ", e);
			}

		}
	}

	public void saveConfiguration() {
		if(selectedConfiguration!=null){
			final String result = JsonUtils.validate(selectedConfiguration.getJson());
			if (result == null) {
				if ( checkConfiguration() ) {
					try {
						muiService.persistConfigurations(selectedConfiguration);
						addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Configurazione salvata correttamente.", ""));
						switch (ConfigurationTypes.valueOf(selectedType)) {
						case GLOBAL:
							//                            configurationFilterCatalog.update();
							break;
						case GRID:
							break;
						case FILTER:
							break;
						default:
							break;
						}
					} catch (final Exception e) {
						log.error("Error saving configuration", e);
						addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di salvataggio",
								"Impossibile salvare la configurazione."));
					}
				}
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"JSON non valido: " + result));
			}
		}

	}

	private boolean checkConfiguration() {
		return validate() && checkMandatoryConfigurationSettins();
	}

	private boolean validate() {
		if (ConfigurationTypes.GRID.equals(ConfigurationTypes.valueOf(selectedType)) ) {
			final ObjectMapper mapper = JsonUtils.getValidationMapper();
			try {
				final JsonNode contentNode = mapper.readTree(selectedConfiguration.getJson());
				final JsonNode configurationsNode = contentNode.get("configurations");
				final List<JsonNode> members = configurationsNode.findValues("Members");
				for ( final JsonNode member : members) {
					if ( (member != null) && (member.size()>0)) {
						JsonNode node = null;
						boolean nodeResult = false;
						for ( int i = 0 ; i < member.size(); ++i) {
							node = member.get(i);
							if (!nodeResult && (node != null)) {
								nodeResult = "UniqueName".equals(node.asText());
							}
						}
						if ( !nodeResult ) {
							addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione", "Manca l'attributo \"UniqueName\" all'interno di \"Members\": la funzione \"CellPut\" non puo' funzionare"));
							return false;
						}
					}
				}
			} catch (final IOException e) {
				log.error("error checking attributes", e);
			}
		}
		return true;
	}

	private boolean checkMandatoryConfigurationSettins() {
		final AtomicBoolean result = new AtomicBoolean(true);
		if (ConfigurationTypes.GRID.equals(ConfigurationTypes.valueOf(selectedType)) ) {
			final Map<String, Configuration> configurations = configurationCatalog.readConfiguration(selectedConfiguration.getJson());
			configurations.entrySet().stream().forEach(e->{
				final Configuration con = e.getValue();
				if (StringUtils.isEmpty(StringUtils.stripToEmpty(con.getName()))) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione", "Manca il campo name in configurazione"));
					result.set(false);
				}
				if ( con.getHeight() == null ) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione", "Manca il campo height in configurazione"));
					result.set(false);
				} else {
					if ( (con.getHeight() < 0) || (con.getHeight() > 100) ) {
						addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di configurazione", "Il campo height deve essere intero tra 0 e 100"));
						result.set(false);
					}
				}
			});
		}
		return result.get();
	}
	@Override
	public Query prepareFilteredQuery(final String grid) {
		return null;
	}

	public void setSelectedConfiguration(final ConfigurationEntity selectedConfiguration) {
		this.selectedConfiguration = selectedConfiguration;
		format();
	}

	@Override
	public void init(){

	}
}
