package com.axiante.mui.webapp.views;

import com.axiante.Tm1Tunnel;
import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.business.reader.dinamicColumns.DinamicConfigurationProducer;
import com.axiante.mui.common.utility.CellNameMapper;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.FilterAttribute;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.mui.webapp.template.TemplateView;
import com.axiante.mui.webapp.utils.message.MessageUtils;
import com.axiante.mui.webapp.utils.silentActions.SilentParam;
import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedAction;
import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedParam;
import com.axiante.mui.webapp.views.content.aggrid.links.PageLink;
import com.axiante.mui.webapp.views.content.aggrid.params.GridParam;
import com.axiante.mui.webapp.views.content.aggrid.preselections.PreSelection;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import com.axiante.tm1.mdx.filter.Filter;
import com.axiante.tm1.mdx.objects.FilterImpl;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.ColumnDef;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.primefaces.PrimeFaces;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

@Slf4j
public abstract class AbstractMuiActionView extends AbstractMuiView implements MuiViewAcionInterface {
    private static final long serialVersionUID = 8559476486697276950L;
    @Inject
    @Getter
    transient TemplateView templateView;
    @Getter
    protected HashMap<String, SelectedAction> availableActionsMap = new HashMap<>();
    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    protected List<PageLink> availableLinksMap = new ArrayList<>();

    @Getter
    @Setter
    protected SelectedAction selectedAction;
    @Getter
    private final HashMap<String, String> selectionsMap = new HashMap<>();
    @Getter
    protected HashMap<String, HashMap<String, Filter>> selectedTemporaryFilters = new HashMap<>();
    @Getter(value = AccessLevel.PROTECTED)
    private final Map<String, Row<Cell>> gridCurrentHeaders = new HashMap<>();

    @Getter(value = AccessLevel.PROTECTED)
    private transient HttpUtils httpUtils = new HttpUtils();


    @Inject
    @Getter
    ApplicationConfiguration applicationConfiguration;

    List<IngridFilter> ingridFilters;

    @Override
    public Query prepareFilteredQuery(final String grid) {
        final Configuration configuration = this.getConfiguration(grid).toBuilder().build(); // copy the utility
        // se ho un filtro, devo aggiornare l'mdx
        if (((this.getCurrentJsonFilter() != null) && (this.getCurrentJsonFilter().trim().length() > 0)
                && !this.getCurrentJsonFilter().trim().equals("''"))
                || ((this.getAdminJsonFilter() != null) && (this.getAdminJsonFilter().trim().length() > 0)
                && !this.getAdminJsonFilter().trim().equals("''"))
                || (this.selectedTemporaryFilters.get(grid) != null)) {
            log.debug("setting filters to original MDX of grid " + grid);
            /*
             * aggiorna mdx aggiungendo il filtro.
             */

            final Query q = configuration.getMdx().copy();
            @SuppressWarnings("unchecked")
            List<Filter> list = (List<Filter>) q.getFilters();
            if (list != null) {
                // threre are already filters in the original utility so
                // we need to add them
                log.debug("adding filters");
                list.addAll(this.getFilterProducer().getFilters(this.getCurrentJsonFilter()));
            } else {
                // these filters are the only ones used
                log.debug("creating filters");
                list = this.getFilterProducer().getFilters(this.getCurrentJsonFilter());
            }
            // set admin filters
            list.addAll(this.getFilterProducer().getFilters(this.getAdminJsonFilter()));
            // set temporary filters and made them visible
            list.addAll(
                    this.selectedTemporaryFilters.get(grid) != null ? this.selectedTemporaryFilters.get(grid).values()
                            : new ArrayList<>());

            // registered ingrid filters

            final Map<String, List<IngridFilter>> ingridFiltersMap = this.getCurrentUser().getIngridFilterAsMap();
            if (ingridFiltersMap.get(grid) != null) {
                list.addAll(
                        ingridFiltersMap.get(grid).stream().map(IngridFilter::asFilter).collect(Collectors.toList()));
            }
            // set filters in the query
            q.setFilters(list);
            // update the utility
            return q;
        } else {
            return configuration.getMdx();
        }
    }

    @Override
    public void getIngridPicklistValues(final String grid) {
        if (grid != null) {

            final Configuration configuration = this.getConfiguration(grid).copy();
            final Map<String, String> map = configuration.getGridFilters();
            final List<ConfigurationElement> filterList = new ArrayList<>();
            if ((map != null) && (map.size() > 0)) {
                final Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    final Entry<String, String> entry = iterator.next();

                    final ConfigurationElement element = this.getCatalogReducer().getCatalog()
                            .findByCodeAndAttribute(entry.getKey(), entry.getValue());
                    final FilterAttribute attribute = element.getAttributes().stream()
                            .filter(a -> a.getCode().equals(entry.getValue())).findAny().orElse(null);
                    if (attribute != null) {
                        element.setSelectedAttribute(attribute);
                        filterList.add(element);
                    } else {
                        log.error("could not find attribute " + entry.getValue() + " for dimension " + entry.getKey()
                                + " in the filter catalog");
                    }
                }
            }
            if (filterList.size() > 0) {
                this.setCurrentIngridJsonFilter(
                        this.getCatalogProducer().inGridPicklistValues(configuration, filterList));

                Map<String, List<IngridFilter>> filterMap = this.getCurrentUser().getIngridFilterAsMap();
                if (!filterMap.isEmpty() && (filterMap.get(grid) != null)) {
                    List<IngridFilter> persistedFilters = filterMap.get(grid);
                    this.getCurrentIngridJsonFilter().forEach(f -> {
                        int index = persistedFilters.indexOf(f);
                        if (index != -1) {
                            log.debug("grid " + grid + ": setting selected values for filter " + f.getDimension() + "::"
                                    + f.getAttribute() + " to " + persistedFilters.get(index).getSelectedValues());
                            f.setSelectedValues(persistedFilters.get(index).getSelectedValues());
                        }
                    });
                }
            } else {
                log.error("configuration error! no valid dimension.attribute found in gridFilters for grid " + grid);
                this.setCurrentIngridJsonFilter(null);
            }
        } else {
            log.error("no grid parameter found");
            this.setCurrentIngridJsonFilter(null);
        }
    }

    public void applyIngridFilters(String grid) {
        final List<IngridFilter> newFilters = new ArrayList<>();
        this.getCurrentIngridJsonFilter().forEach(f -> {
            if (f.getSelectedValues().size() > 0) {
                newFilters.add(f);
            }
        });
        Map<String, List<IngridFilter>> map = this.getCurrentUser().getIngridFilterAsMap();
        if (newFilters.size() > 0) {
            map.put(grid, newFilters);
        } else {
            map.remove(grid);
        }
        this.getCurrentUser().updateIngridFilters(map);
        try {
            this.muiService.persistUser(this.getCurrentUser());
            // TODO: aggiorna griglia ?
        } catch (Exception e) {
            log.error("error updating ingrid filters", e);
            this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Errore durante il salvataggio dei filtri", "non é stato possibile salvare i filtri"));
        }
    }

    @Override
    public void updateView() {
        final String grid = this.getRequestParameterMap().get("grid");
        final String reload = this.getRequestParameterMap().get("reload");
        Boolean reload_parameter = null;
        if (reload != null) {
            reload_parameter = Boolean.valueOf(reload);
        }
        this.updateView(grid, reload_parameter);
    }

    @Override
    public void updateView(final String grid) {
        //		this.getRequestParameterMap().put("grid", grid);
        //		final String grid = this.getRequestParameterMap().get("grid");
        final String reload = this.getRequestParameterMap().get("reload");
        Boolean reload_parameter = null;
        if (reload != null) {
            reload_parameter = Boolean.valueOf(reload);
        }
        this.updateView(grid, reload_parameter);
    }

    public void updateView(String grid, final Boolean reload) {
        Cookie cookie = null;
        if ((grid != null) && grid.startsWith("javax.faces.event.ActionEvent")) {
            grid = this.getRequestParameterMap().get("grid");
        }
        final String temporaryFiltersJSON = this.getRequestParameterMap().get("temporaryFilters");
        if (temporaryFiltersJSON != null) {
            final HashMap<String, Filter> temporaryFilters = this.produceTemporaryFilters(temporaryFiltersJSON, grid);
            if (this.getSelectedTemporaryFilters().get(grid) == null) {
                this.selectedTemporaryFilters.put(grid, new HashMap<>());
                log.warn("filtri temporanei per la griglia " + grid + "non esistono !!!!");
            }
            this.getSelectedTemporaryFilters().get(grid).putAll(temporaryFilters);
            this.addSelectedFilterToMap(new ArrayList<>(temporaryFilters.values()));
        }
        if ((grid != null) && (this.getConfiguration(grid) != null)) {
            boolean stopExecution = false;
            final Configuration configuration = this.getConfiguration(grid).toBuilder().build(); // copy the utility
            if ((configuration.getPreSelections() != null) && !configuration.getPreSelections().equals("")
                    && (this.templateView.getAvailablePreSelections() != null)) {
                for (final PreSelection preSelection : this.templateView.getAvailablePreSelections()) {
                    if (this.getSelectedTemporaryFilters().containsKey(grid)) {
                        if (preSelection.getVisible() && !this.getSelectedTemporaryFilters().get(grid).containsKey(
                                preSelection.getDimColumnName() + "." + preSelection.getAttrColumnName())) {
                            log.debug("preSelection not applied in temporary filters: "
                                    + preSelection.getDimColumnName() + "." + preSelection.getAttrColumnName()
                                    + "\n Temporary Filters:\n" + this.getSelectedTemporaryFilters().get(grid));
                            stopExecution = true;
                        }
                    } else {
                        log.debug("temporary filter missing for preSelection: " + preSelection.getDimColumnName() + "."
                                + preSelection.getAttrColumnName() + "\n Grid:" + grid);
                        stopExecution = true;
                    }
                }
            }
            if (!this.getTemplateView().getRenderCumpulsoryFilters()) {
                stopExecution = true;
            }
            if (stopExecution) {
                getAjax().addCallbackParam("jsonFilter", this.getCurrentJsonFilter());
                this.clearGridData(grid);
                return;
            }
            cookie = this.cookieRepository.getCookie(configuration.getProfile());
            final Query q = this.prepareFilteredQuery(grid);

            configuration.setMdx(q);
            log.debug("generating cellset");
            final MuiToken muiToken = (this.getHttpUtils().isHostReachable(configuration.getProfile().getValidationHost())
                    ? this.getUtils().generateCellset(configuration, configuration.getMdx(), cookie)
                    : null);
            if (muiToken != null) {
                if (MuiToken.SERVER_BUSY.equals(muiToken.getUuid())) {
                    // server busy !
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "TM1 Server Busy",
                            "Il server TM1 non ha generato la risposta entro il timeout configurato. Aggiungere filtri alla selezione attuale."));
                    this.clearGridData(grid);
                    return;
                } else {
                    log.debug("generated a muitoken for tm1 session " + muiToken.getCookie());
                }
                // Read available actions
                this.readAvailableActionsMap(configuration.getActions(), grid);
                this.readAvailableLinksMap(configuration.getPageLinks());
                if ((configuration.getMaxCells() > 0) && (muiToken.getTotalCells() > configuration.getMaxCells())) {
                    getAjax().addCallbackParam("jsonFilter", this.getCurrentJsonFilter());
                    String message = configuration.getMaxCellsMessage();
                    if (message == null) {
                        message = "Aggiungere filtri alla selezione attuale.";
                    }
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Raggiunto limite massimo di records!", message));
                    this.clearGridData(grid);

                    this.clearGridData(grid);
                    return;
                }

                getAjax().addCallbackParam("grid", grid);
                if (reload != null) {
                    getAjax().addCallbackParam("reload", reload);
                }

                getAjax().addCallbackParam("rowClassRules",
                        configuration.getRowClassRules() != null ? configuration.getRowClassRules() : "");
                getAjax().addCallbackParam("pagination",
                        configuration.getPagination() != null ? configuration.getPagination() : false);
                getAjax().addCallbackParam("groupRowAggNodes",
                        configuration.getGroupRowAggNodes() != null ? configuration.getGroupRowAggNodes() : "");
                getAjax().addCallbackParam("suppressRowClickSelection",
                        configuration.getSuppressRowClickSelection() != null
                                ? configuration.getSuppressRowClickSelection()
                                : "");
                getAjax().addCallbackParam("selections",
                        configuration.getSelections() != null ? configuration.getSelections() : "[]");
                getAjax().addCallbackParam("axremote", muiToken.getUuid());
                getAjax().addCallbackParam("contextPath",
                        ((ServletContext) this.getExternalContext().getContext()).getContextPath());

                // columndefs are created dinamically
                final StringBuilder columnDefs = new StringBuilder();
                if ((configuration.getDynamicColumns() != null) && configuration.getDynamicColumns()) {
                    final DinamicConfigurationProducer producer = new DinamicConfigurationProducer();
                    final ColumnDef tree = producer.getDinamicConfigurationTree(muiToken,
                            configuration.getDynamicColumnsSettings());
                    // if there are no dynamic cols
                    if ((tree == null) || (tree.getChildren() == null) || (tree.getChildren().size() == 0)) {
                        this.reactToNoDataInGrid(grid);
                        return;
                    }
                    if ((configuration.getColumnDefs() != null) && configuration.getColumnDefs().endsWith("]")) {
                        columnDefs.append(configuration.getColumnDefs()).deleteCharAt(columnDefs.length() - 1) // delete
                                // ]
                                .append(",") // append ,
                                .append(producer.generateConfiguration(tree, 0,
                                        configuration.getDynamicColumnsSettings()))
                                .append("]");
                    } else {
                        // create columndefs
                        columnDefs.append("\"columnDefs\":[").append(
                                        producer.generateConfiguration(tree, 0, configuration.getDynamicColumnsSettings()))
                                .append("]");
                    }
                } else {
                    if ((muiToken.getTotalCells() == 0) && configuration.getAlertNoDataFound()) {
                        this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Attenzione",
                                "Nessun dato disponibile per i filtri selezionati."));
                        this.clearGridData(grid);
                    }
                    // Suppress empty columnDefs
                    columnDefs.append(this.suppressEmptyCols(muiToken));
                }

                // Set subtitle in grid
                if (this.templateView.getGridParamHashMap().get(grid) == null) {
                    log.warn("parametri griglia per " + grid + " sono null !");
                    this.templateView.getGridParamHashMap().put(grid, new GridParam());
                }
                this.templateView.getGridParamHashMap().get(grid).setSubTitle(muiToken.getVersion());

                // Set columns hidden by user
                final String columDefsJson = this.applyHiddenColumns(grid, columnDefs.toString(),
                        (UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
                this.getGridCurrentHeaders().put(grid, muiToken.getHeaders());
                getAjax().addCallbackParam("columnDefs", columDefsJson);
                getAjax().addCallbackParam("autoGroupColumnDef",
                        configuration.getAutoGroupColumnDef());
            } else {
                this.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore di comunicazione con il server",
                                "Non e' stato possibile comunicare con il server remoto, contattare l'assistenza"));
            }
        } else {
            if (grid == null) {
                log.debug("Grid parameter is not set !");
            } else if (!grid.equals("none")) {
                log.error("Missing configuration for grid " + grid + " : please check for typos");
                this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rilevato errore di configurazione",
                        "Contattare l'assistenza"));

            }
        }
        getAjax().addCallbackParam("jsonFilter", this.getCurrentJsonFilter());
        log.debug("streamed view update finished");
    }

    protected void reactToNoDataInGrid(final String grid) {
        this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Attenzione",
                "Nessun dato disponibile per i filtri selezionati."));
        this.clearGridData(grid);
    }

    public HashMap<String, Filter> produceTemporaryFilters(final String temporaryFiltersJSON, final String grid) {
        String temporaryFilters = "{";
        final JSONArray jsonArr = new JSONArray(temporaryFiltersJSON);
        for (int i = 0; i < jsonArr.length(); i++) {
            final JSONObject jsonObj = jsonArr.getJSONObject(i);
            if (jsonObj.getString("grid").equals(grid)) {
                temporaryFilters = temporaryFilters.concat("\"" + i + "\" : { " + "\"Dimension\": \""
                        + jsonObj.getString("dimension") + "\"," + "\"Attribute\": \"" + jsonObj.getString("attribute")
                        + "\"," + "\"selectedValues\": " + jsonObj.getJSONArray("values").toString() + "},");
            }
        }
        if (temporaryFilters.endsWith(",")) {
            temporaryFilters = temporaryFilters.substring(0, temporaryFilters.length() - 1);
        }
        temporaryFilters = temporaryFilters.concat("}");
        // produce temporary filters
        final HashMap<String, Filter> temporaryFiltersMap = new HashMap<>();
        for (final Filter filter : this.getFilterProducer().getFilters(temporaryFilters)) {
            final FilterImpl f = ((FilterImpl) filter);
            final String key = f.getDimension() + "." + f.getAttribute();
            temporaryFiltersMap.put(key, filter);
        }
        if (this.selectedTemporaryFilters.containsKey(grid)) {
            this.selectedTemporaryFilters.get(grid).putAll(temporaryFiltersMap);
        } else {
            this.selectedTemporaryFilters.put(grid, temporaryFiltersMap);
        }
        return temporaryFiltersMap;
    }

    private void setValuesFromSelections(final HashMap<String, String> selectionsMap) {
        for (final SelectedAction action : this.getAvailableActions()) {
            if (action.getParams() != null) {
                for (final SelectedParam param : action.getParams()) {
                    if (selectionsMap != null) {
                        final String key = param.getDimension() + "." + param.getAttribute();
                        if (selectionsMap.containsKey(key)) {
                            param.setSelectedValue(selectionsMap.get(key));
                        }
                    }
                }
            }
        }
    }

    private void readAvailableLinksMap(final Map<String, Integer> availableLinks) {
        this.getAvailableLinksMap().clear();
        if (availableLinks != null) {
            setAvailableLinksMap(availableLinks.entrySet().stream().map(entry -> new PageLink(entry.getKey(), entry.getValue())).filter(Objects::nonNull).collect(Collectors.toList()));
        }
    }

    private void readAvailableActionsMap(final String actions, final String grid) {
        if ((actions != null) && !actions.equals("")) {
            final JSONArray jsonArr = new JSONArray(actions);
            for (int i = 0; i < jsonArr.length(); i++) {
                final JSONObject jsonObj = jsonArr.getJSONObject(i);
                final SelectedAction selectedAction = new SelectedAction();
                selectedAction.setSource(grid);
                selectedAction.setComponentId(jsonObj.getString("componentId"));
                selectedAction.setLabel(jsonObj.getString("componentLabel"));
                selectedAction.setProcess(jsonObj.getString("process"));
                boolean suppressDialog = false;
                boolean silent = false;
                try {
                    suppressDialog = jsonObj.getBoolean("suppress_dialog");
                } catch (Exception e) {
                    // use default if not present
                }
                selectedAction.setSuppressDialog(suppressDialog);

                try {
                    silent = jsonObj.getBoolean("silent");
                } catch (Exception e) {
                    // use default if not present
                }
                selectedAction.setSilent(silent);
                try {
                    selectedAction.setCustomMessage(jsonObj.getString("custom_message"));
                } catch (Exception e) {
                    // use default if not present
                }

                try {
                    selectedAction.setCustomMessageTitle(jsonObj.getString("custom_message_title"));
                } catch (Exception e) {
                    // use default if not present
                }

                try {
                    selectedAction.setCustomMessageLevel(jsonObj.getString("custom_message_level"));
                } catch (Exception e) {
                    // use default if not present
                }

                if (jsonObj.has("refresh")) {
                    final List<String> targets = new ArrayList<>();
                    for (final Object target : jsonObj.getJSONArray("refresh")) {
                        targets.add((String) target);
                    }
                    selectedAction.setRefresh(targets);
                }
                selectedAction.setTimeout(jsonObj.has("timeout") ? jsonObj.getInt("timeout") : -1);
                final List<SelectedParam> selectedParams = new ArrayList<>();
                if (jsonObj.getJSONArray("params") != null) {
                    final JSONArray params = jsonObj.getJSONArray("params");
                    for (int k = 0; k < params.length(); k++) {
                        final SelectedParam selectedParam = new SelectedParam();
                        selectedParam.setDimension(params.getJSONObject(k).has("dimension")
                                ? params.getJSONObject(k).getString("dimension")
                                : null);
                        selectedParam.setAttribute(params.getJSONObject(k).has("attribute")
                                ? params.getJSONObject(k).getString("attribute")
                                : null);
                        selectedParam.setParamName(params.getJSONObject(k).has("paramName")
                                ? params.getJSONObject(k).getString("paramName")
                                : null);
                        selectedParam.setLabel(params.getJSONObject(k).has("label")
                                ? params.getJSONObject(k).getString("label")
                                : null);
                        selectedParam.setDefaultValue(params.getJSONObject(k).has("defaultValue")
                                ? params.getJSONObject(k).getString("defaultValue")
                                : null);
                        selectedParam.setHasPicklist(params.getJSONObject(k).has("hasPicklist")
                                && params.getJSONObject(k).getBoolean("hasPicklist"));
                        selectedParam.setDisabled(params.getJSONObject(k).has("disabled")
                                && params.getJSONObject(k).getBoolean("disabled"));
                        selectedParam.setVisible(!params.getJSONObject(k).has("visible")
                                || params.getJSONObject(k).getBoolean("visible"));
                        selectedParams.add(selectedParam);
                    }
                }
                selectedAction.setParams(selectedParams);
                this.getAvailableActionsMap().put(selectedAction.getComponentId(), selectedAction);
            }
        }
    }

    private String suppressEmptyCols(final @NonNull MuiToken muiToken) {
        final List<String> headerCols = this.getUtils()
                .produceTableHeaders(muiToken, muiToken.getConfiguration().getMdx(), muiToken.getCellsetId())
                .getHeaders().parallelStream().map(Cell::getName).map(CellNameMapper::map2agGrid)
                .collect(Collectors.toList());
        final JSONArray colDefs = new JSONArray(muiToken.getConfiguration().getColumnDefs());
        for (int i = 0; i < colDefs.length(); i++) {
            final boolean deletable = false;
            i = this.removeFields(headerCols, colDefs, i, deletable);
        }
        return colDefs.toString();
    }

    protected int removeFields(final List<String> headerCols, final JSONArray colDefs, int count,
                               final boolean deletable) {
        if (colDefs.getJSONObject(count).has("children")) {
            final JSONArray children = colDefs.getJSONObject(count).getJSONArray("children");
            for (int j = 0; j < children.length(); j++) {
                j = this.removeFields(headerCols, children, j, true);
            }
        } else {
            if (deletable && !headerCols.contains(colDefs.getJSONObject(count).getString("field"))) {
                colDefs.remove(count);
                count--;
            }
        }
        return count;
    }

    private String applyHiddenColumns(final String colDefGridId, final String columDefsJson,
                                      final UsersEntity usersEntity) {
        if (usersEntity != null) {
            final JSONArray colDefs = new JSONArray(columDefsJson);
            final JSONArray userHiddenCols = new JSONArray(usersEntity.getHiddenCols());
            for (int i = 0; i < userHiddenCols.length(); i++) {
                final JSONObject userHiddenCol = userHiddenCols.getJSONObject(i);
                final String gridId = userHiddenCol.getString("grid");
                if (gridId.equals(colDefGridId)) {
                    for (int k = 0; k < colDefs.length(); k++) {
                        final JSONObject colDef = colDefs.getJSONObject(k);
                        final JSONArray colIds = userHiddenCol.getJSONArray("colId");
                        for (int j = 0; j < colIds.length(); j++) {
                            final JSONObject column = colIds.getJSONObject(j);
                            if (this.hideFields(column, colDef)) {
                                break;
                            }
                        }
                    }
                }
            }
            return colDefs.toString();
        }
        log.error("Error applying user hidden columns: User is null!");
        return columDefsJson;
    }

    private boolean hideFields(final JSONObject column, final JSONObject colDef) {
        if (!colDef.has("field")) {
            if (colDef.has("children")) {
                final JSONArray children = colDef.getJSONArray("children");
                for (int j = 0; j < children.length(); j++) {
                    if (this.hideFields(column, children.getJSONObject(j))) {
                        break;
                    }
                }
            }
        } else {
            if (column.getString("name").equals(colDef.getString("field"))) {
                colDef.put("hide", !column.getBoolean("visible"));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SelectedAction> getAvailableActions() {
        return new ArrayList<>(new ArrayList<>(this.availableActionsMap.values()));
    }

    @Override
    public List<PageLink> getAvailableLinks() {
        return this.availableLinksMap;
    }

    public void addSelectedFilterToMap(final List<Filter> filters) {
        if (filters != null) {
            for (final Object o : filters) {
                final FilterImpl filter = (FilterImpl) o;
                this.selectionsMap.put(filter.getColumn() + "." + filter.getAttribute(),
                        filter.getValues().size() == 1 ? filter.getValues().get(0) : null);
            }
        }
    }

    /**
     * launches a process in a separate "mui session"
     */
    @Override
    public void performAction() {
        // do not auto launch the process again
        this.selectedAction.setAutoLaunchAction(false);
        // set spinner
        if (this.selectedAction.getRefresh() != null) {
            for (final String target : this.selectedAction.getRefresh()) {
                PrimeFaces.current().executeScript("showSpinner('" + target + "');");
            }
        }
        boolean error = false;
        try {
            final Configuration gridConfiguration = this.getConfiguration(this.selectedAction.getSource());
            try (Tm1Tunnel tunnel = new Tm1Tunnel(gridConfiguration.getProfile());) {
                final Map<String, String> parameters = new HashMap<>();
                for (final SelectedParam param : this.selectedAction.getParams()) {
                    if (param.getParamName() != null) {
                        if (this.selectedAction.isSilent()) {
                            param.setSelectedValue(param.getValues().stream().collect(Collectors.joining("|")) + "|");
                        }
                        parameters.put(param.getParamName(),
                                param.getSelectedValue() != null ? param.getSelectedValue() : "");
                    }
                }
                Integer socketTimeout = 0;
                if (this.selectedAction.getTimeout() > 0) {
                    socketTimeout = this.selectedAction.getTimeout();
                }
                final CloseableHttpResponse res = tunnel.executeProcess(this.selectedAction.getProcess(), parameters,
                        (double) this.selectedAction.getTimeout(), socketTimeout,
                        this.applicationConfiguration.getConnectionRequestTimeout(),
                        this.cookieRepository.getCookie(gridConfiguration.getProfile()));
                if (!this.httpUtils.checkResponse(res)) {
                    log.error("Error executing '" + this.selectedAction.getProcess() + "'");
                    error = true;
                }
                res.close();
            }
        } catch (final Exception e) {
            log.error("error executing process in TM1", e);
            error = true;
        }
        if (error) {
            PrimeFaces.current().dialog().showMessageDynamic(MessageUtils.toFacesMessage(selectedAction));
        } else {
            PrimeFaces.current().executeScript("PF('dlgSelezioniActions').hide();");
            this.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo!", "processo eseguito correttamente."));
        }
        if (this.selectedAction.getRefresh() != null) {
            for (final String target : this.selectedAction.getRefresh()) {
                PrimeFaces.current().executeScript("updateRemoteView([{name:'grid', value: '" + target + "'}]);");
            }
        }
    }

    public void performSilentAction() {
        Map<String, String> params = getRequestParameterMap();
        final String componentId = params.get("componentId");
        final SelectedAction selectedAction = this.availableActionsMap.get(componentId);
        if (selectedAction != null) {
            try {
                final List<SilentParam> actionParameters = JsonUtils.getMapper().readValue(params.get("silence"),
                        new TypeReference<List<SilentParam>>() {
                        });
                selectedAction.getParams().forEach(p -> {
                    SilentParam param = actionParameters.stream().filter(a -> (a.getDimColumnName().equals(p.getDimension())
                            && a.getAttributeColumnName().equals(p.getAttribute()))).findAny().orElse(null);
                    if (param != null) {
                        p.setValues(Arrays.asList(param.getAvailableValues()));
                    }
                });
                this.setSelectedAction(selectedAction);
                performAction();
            } catch (IOException e) {
                log.error("error deserializing parameters", e);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
                        "Errore durante la lettura dei parametri"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
                    "Errore di configurazione: impossibile recuperare la configurazione del processo"));
        }
    }

    @Override
    public void prepareSelections() {
        // Set value to available actions from grid selections
        if (this.selectionsMap != null) {
            this.setValuesFromSelections(this.selectionsMap);
        }
        final Map<String, String> params = this.getRequestParameterMap();
        final String componentId = params.get("componentId");
        final SelectedAction selectedAction = this.availableActionsMap.get(componentId);
        JSONArray availableFilters = null;
        if (selectedAction != null) {
            for (final SelectedParam param : selectedAction.getParams()) {
                final String key = param.getDimension() + "." + param.getAttribute();
                if ((this.selectionsMap != null) && this.selectionsMap.containsKey(key)) {
                    param.setSelectedValue(this.selectionsMap.get(key));
                }
                if (param.getDefaultValue() != null) {
                    param.setSelectedValue(param.getDefaultValue());
                }
                if (param.isHasPicklist()) {
                    if (this.getJsonFilter() != null) {
                        if (availableFilters == null) {
                            availableFilters = new JSONArray(this.getJsonFilter());
                        }
                        for (int i = 0; i < availableFilters.length(); i++) {
                            final JSONObject jsonObject = availableFilters.getJSONObject(i);
                            if (param.getDimension().equals(jsonObject.getString("DIM_columnName"))) {
                                final Map<String, String> values = new LinkedHashMap<>();
                                final JSONArray jsonDatas = jsonObject.getJSONArray("jsonData");
                                for (int k = 0; k < jsonDatas.length(); k++) {
                                    final JSONObject jsonData = jsonDatas.getJSONObject(k);
                                    values.put(jsonData.getString(param.getAttribute()),
                                            jsonData.getString(param.getAttribute()));
                                }
                                param.setValues(new ArrayList<>(values.keySet()));
                            }
                        }
                    } else {
                        log.error("Wrong configuration! no available filters for picklist's values");
                    }
                    //					}
                }
            }
            // autorun del processo :
            selectedAction.setAutoLaunchAction(selectedAction.isSuppressDialog());
            this.setSelectedAction(selectedAction);
        }
    }

    @Override
    public boolean canLoadData() {
        final String grid = this.getRequestParameterMap().get("grid");
        return this.canLoadData(grid);
    }

    @Override
    public boolean canLoadData(String grid) {
        Cookie cookie = null;
        boolean ret = true;
        if ((grid != null) && grid.startsWith("javax.faces.event.ActionEvent")) {
            grid = this.getRequestParameterMap().get("grid");
        }

        final String temporaryFiltersJSON = this.getRequestParameterMap().get("temporaryFilters");
        if (temporaryFiltersJSON != null) {
            this.addSelectedFilterToMap(
                    (List<Filter>) this.produceTemporaryFilters(temporaryFiltersJSON, grid).values());
        }

        if ((grid != null) && (this.getConfiguration(grid) != null)) {
            final Configuration configuration = this.getConfiguration(grid).toBuilder().build(); // copy the utility
            cookie = this.cookieRepository.getCookie(configuration.getProfile());
            final Query q = this.prepareFilteredQuery(grid);
            configuration.setMdx(q);
            log.debug("\t@@@@@@@@@@@@@@ generating cellset");
            final MuiToken muiToken = this.getUtils().generateCellset(configuration, configuration.getMdx(), cookie);
            if (muiToken != null) {
                if (MuiToken.SERVER_BUSY.equals(muiToken.getUuid())) {
                    // server busy !
                    this.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "TM1 Server Busy",
                            "Il server TM1 non ha generato la risposta entro il timeout configurato. Aggiungere filtri alla selezione attuale."));
                    return true;
                } else {
                    log.debug("generated a muitoken for tm1 session " + muiToken.getCookie());
                }

                if ((configuration.getMaxCells() > 0) && (muiToken.getTotalCells() > configuration.getMaxCells())) {
                    ret = false;
                }
            }
        }
        return ret;
    }

    @Override
    public Row<Cell> getGridCurrentHeaders(final String grid) {
        return this.gridCurrentHeaders.get(grid);
    }
}
