package com.axiante.mui.webapp.utils.preselections;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.CookieRepository;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.mui.webapp.views.content.aggrid.preselections.PreSelection;
import com.axiante.utility.configuration.Configuration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.primefaces.PrimeFaces;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

@Slf4j
@Dependent
public class PreselectionUtils implements Serializable {
	private static final long serialVersionUID = -5648619792308901968L;
	@Inject
	CookieRepository cookieRepository;
	@Inject
	ApplicationConfiguration configuration;
	@Getter
	UsersEntity user;

	public PreselectionUtils setUser(final UsersEntity user) {
		this.user = user;
		return this;
	}

	public void performPreSelection(final Map<String, String> params,
			final HashMap<String, PreSelection> availablePreselectionsMap) {

		final String mapKey = params != null ? params.get("id") : null;
		final String selectedValue = params != null ? params.get("selectedValue") : null;
		if ((mapKey == null) || (selectedValue == null) || availablePreselectionsMap == null
				|| !availablePreselectionsMap.containsKey(mapKey) || (availablePreselectionsMap.get(mapKey) == null)) {
			final StringBuffer errorMessage = new StringBuffer("preselection error").append("\n\tmapKey: ")
					.append(mapKey == null ? "null" : mapKey).append("\n\tselectedValue: ")
					.append(selectedValue == null ? "null" : selectedValue).append("\n\tavailablePreselectionsMap: ")
					.append(availablePreselectionsMap == null ? "null" : "exists").append("\n\tkey in Map: ")
					.append(availablePreselectionsMap!= null ? availablePreselectionsMap.containsKey(mapKey):"no map available").append("\n\tkey in value: ")
					.append(availablePreselectionsMap != null ? availablePreselectionsMap.get(mapKey) == null ? "null"
							: availablePreselectionsMap.get(mapKey) : "no map available");
			log.error(errorMessage.toString());
			final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Parametri incompleti",
					"Impossibile eseguire la preselections, contattare l'assistenza tecnica.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else {
			final PreSelection preSelection = availablePreselectionsMap.get(mapKey);
			preSelection.setSelectedValue(selectedValue);
			// set spinner
			if (preSelection.getRefresh() != null) {
				for (final String target : preSelection.getRefresh()) {
					PrimeFaces.current().executeScript("showSpinner('" + target + "');");
				}
			}
			executePreselectionProcess(preSelection);
			// Set temporary filters and update view
			if (preSelection.getRefresh() != null) {
				for (final String target : preSelection.getRefresh()) {
					final String temporaryFiltersJSON = "[{ " + " \"grid\" : \"" + target + "\"," + " \"visible\" : "
							+ false + ", " + " \"values\" : [\"" + preSelection.getSelectedValue() + "\"],"
							+ " \"dimension\" : \"" + preSelection.getDimColumnName() + "\"," + " \"attribute\" : \""
							+ preSelection.getAttrColumnName() + "\"" + "}]";
					PrimeFaces.current()
							.executeScript("updateRemoteView([{name:'grid', value: '" + target
									+ "'},{name:'temporaryFilters', value: '"
									+ temporaryFiltersJSON.replaceAll("'", "\\\\'") + "'}]);");
				}
			}
		}
	}

	public void executePreselectionProcess(final PreSelection preSelection) {
		if ((preSelection.getProcess() != null) && !preSelection.getProcess().equals("")) {
			boolean error = false;
			boolean timeout = false;
			try {
				try (Tm1Tunnel tunnel = new Tm1Tunnel(preSelection.getProfile())) {
					tunnel.setCookie(cookieRepository.getCookie(preSelection.getProfile()));
					final Map<String, String> parameters = new HashMap<>();
					parameters.put(preSelection.getParamName(), preSelection.getSelectedValue());
					final JSONObject filters = new JSONObject(getUser().getAdminFilters());
					JSONArray clients;
					for (final Iterator<String> iterator = filters.keys(); iterator.hasNext();) {
						final String key = iterator.next();
						final JSONObject filter = (JSONObject) filters.get(key);
						if ("}Clients".equals(filter.getString("Dimension"))) {
							clients = filter.getJSONArray("selectedValues");
							if ((clients != null) && (clients.length() > 0)) {
								final String client = clients.getString(0);
								parameters.put("inClient", client);
								break;
							}
						}
					}
					final CloseableHttpResponse res = tunnel.executeProcess(preSelection.getProcess(), parameters,
							configuration.getConnectionTimeout(), configuration.getSocketTimeout(),
							configuration.getConnectionRequestTimeout(),
							cookieRepository.getCookie(preSelection.getProfile()));
					if (!new HttpUtils().checkResponse(res, true)) {
						log.error("Error executing '" + preSelection.getProcess() + "'");
						error = true;
						timeout = (res == null);
					}
					if (res != null) {
						res.close();
					}
				}
			} catch (final Exception e) {
				log.error("error executing PreselectionProcess :\n", e);
				error = true;
			}
			if (error && !timeout) {
				final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
						"Impossibile eseguire la preselezione, contattare l'assistenza tecnica.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
	}

	public void readAvailablePreselectionsMap(final HashMap<String, PreSelection> availablePreselectionsMap,
			final Map<String, Configuration> preSelectionsConf, final HashMap<String, String> preselectedGridMap) {
		// remove previous preselections
		availablePreselectionsMap.clear();
		// create preSelections
		for (final Map.Entry<String, Configuration> configurationEntry : preSelectionsConf.entrySet()) {
			final Configuration conf = configurationEntry.getValue();
			final String preSelections = conf.getPreSelections();
			final ConnectionProfile profile = conf.getProfile();
			if ((preSelections != null) && !preSelections.equals("")) {
				final JSONArray jsonArr = new JSONArray(preSelections);
				for (int i = 0; i < jsonArr.length(); i++) {
					final JSONObject jsonObj = jsonArr.getJSONObject(i);
					final PreSelection preSelection = new PreSelection();
					preSelection.setDimension(jsonObj.getString("dimension"));
					preSelection.setDimCode(jsonObj.getString("dimCode"));
					preSelection.setDimColumnName(jsonObj.getString("dimColumnName"));
					preSelection.setAttribute(jsonObj.getString("attribute"));
					preSelection.setAttrCode(jsonObj.getString("attrCode"));
					preSelection.setAttrColumnName(jsonObj.getString("attrColumnName"));
					preSelection.setProcess(jsonObj.getString("process"));
					preSelection.setParamName(jsonObj.getString("paramName"));
					preSelection.setVisible(true);
					preSelection.setProfile(profile);
					preSelection.setId("preselection_" + preSelection.getDimCode() + "_" + preSelection.getAttrCode());
					if (jsonObj.has("refresh")) {
						final List<String> targets = new ArrayList<>();
						for (final Object targetObj : jsonObj.getJSONArray("refresh")) {
							final String target = (String) targetObj;
							targets.add(target);
							preselectedGridMap.put(target, target);
						}
						preSelection.setRefresh(targets);
					}
					availablePreselectionsMap.put(preSelection.getId(), preSelection);
				}
			}
		}
	}
}
