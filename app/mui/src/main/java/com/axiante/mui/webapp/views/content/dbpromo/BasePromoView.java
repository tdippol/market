package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.actions.ActionTypeEnum;
import com.axiante.mui.dbpromo.actions.ElementFieldEnum;
import com.axiante.mui.dbpromo.actions.FormEnum;
import com.axiante.mui.dbpromo.business.enumeration.DBPromoUserFilterEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.filter.ConfigurationElement;
import com.axiante.mui.filter.FilterAttribute;
import com.axiante.mui.filter.IngridFilter;
import com.axiante.mui.filter.utils.FilterUtils;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.ActionService;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.RoleMenuUtil;
import com.axiante.mui.webapp.views.MuiViewInterface;
import com.axiante.mui.webapp.views.ViewType;
import com.axiante.mui.webapp.views.filter.postfilterselection.FilterSelected;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

@Slf4j
public abstract class BasePromoView implements MuiViewInterface {
    private static final long serialVersionUID = -4985183703092027769L;

    @Inject
    UserService userService;

    @Getter
    private String viewFile;

    @Getter
    @Setter
    private String name;

    @Getter
    private final String contentPath = "content/";

    @Getter
    private boolean disableButtonFilters = false;

    @Getter
    @Setter
    private String jsonFilter = "";

    @Getter
    private String currentJsonFilter = "";

    @Getter
    @Setter
    private String adminJsonFilter = "";

    @Getter
    @Setter
    private List<ConfigurationElement> availableFilters;

    @Getter
    @Setter
    private List<IngridFilter> currentIngridJsonFilter;

    @Getter
    MenuItem node;

    @Getter
    @Setter
    List<String> availableGrids = new ArrayList<>();

    @Getter
    @Setter
    private Map<String, Configuration> configurationMap;

    @Inject
    @Getter
    transient private ApplicationFilterCatalogProducer catalogProducer;

    @Inject
    private PromoService promoService;

    @Inject
    @Getter(value = AccessLevel.PRIVATE)
    MuiService muiService;

    @Inject
    transient private RoleMenuUtil roleMenuUtil;

    @Inject
    FilterUtils filterUtils;

    @Inject
    private Instance<ActionService> actionServiceInstance;

    protected Integer navigateTo = null;

    @Getter
    protected Integer landingId = null;

    @Getter
    protected String contesto = null;

    protected void setViewFile(final String url) {
        this.viewFile = new StringBuilder(this.getContentPath()).append(url).toString();
    }

    @Override
    public void setNode(final MenuItem node) {
        this.node = node;
        try {
            this.name = node.getValue().toString();
            this.setViewFile(node.getParams().get("url").get(0));
            final String idMenu = node.getParams().get("idMenu") != null ? node.getParams().get("idMenu").get(0) : null;
            final MenuEntity e = getMuiService().findMenu(new Integer(idMenu));
            contesto = "''";

            if (e != null) {
                final ConfigurationEntity conf = e.getConfigurations().stream().filter(Objects::nonNull)
                        .filter(c -> c.getType().equals(ConfigurationTypes.GRID) && (c.getRevisionDate() == null))
                        .findFirst().orElse(null);
                if ((conf != null) && (conf.getJson() != null)) {
                    final JsonNode json = JsonUtils.getMapper().readTree(conf.getJson());
                    navigateTo = null;
                    landingId = null;
                    if (json.get("configurations") != null) {
                        final ArrayNode aa = (ArrayNode) json.get("configurations");
                        if (aa.get(0) != null) {
                            if (aa.get(0).get("navigationId") != null) {
                                navigateTo = aa.get(0).get("navigationId").asInt();
                                final MenuEntity menu = getMuiService().findMenu(navigateTo);
                                if (!roleMenuUtil.canViewMenu(getCurrentUser().getRoles(), menu)) {
                                    navigateTo = RoleMenuUtil.FORBIDDEN_MENU;
                                }
                            }
                            if (aa.get(0).get("landingId") != null) {
                                landingId = aa.get(0).get("landingId").asInt();
                                final MenuEntity menu = getMuiService().findMenu(navigateTo);
                                if (!roleMenuUtil.canViewMenu(getCurrentUser().getRoles(), menu)) {
                                    landingId = RoleMenuUtil.FORBIDDEN_MENU;
                                }
                            }
                        }

                        // gestione del contesto
                        if ((aa.get(0) != null) && (aa.get(0).get("contesto") != null)) {
                            contesto = String.format("'%s'", aa.get(0).get("contesto").asText());
                        }
                        log.debug("contesto settato a " + contesto);
                    }
                }
            }
        } catch (final NullPointerException e) {
            log.error(
                    "the node " + node.getValue() + " is not properly configured as it is missing the url declaration",
                    e);
        } catch (final Exception e) {
            log.error("error reading configuration", e);
        }
    }

    @Override
    public void updateView() {

    }

    @Override
    public void updateView(String grid) {

    }

    @Override
    public Configuration getConfiguration(String name) {
        return null;
    }

    @Override
    public void prepareJsonFilter() {
    }

    @Override
    public Boolean getSuppressStatus(String gridId, String type) {
        return Boolean.FALSE;
    }

    @Override
    public Query prepareFilteredQuery(String grid) {
        return null;
    }

    @Override
    public void removeSpinner(@NonNull String grid) {

    }

    @Override
    public void clearGridData(@NonNull String grid) {

    }

    @Override
    public boolean canLoadData() {
        return Boolean.FALSE;
    }

    @Override
    public boolean canLoadData(String grid) {
        return Boolean.FALSE;

    }

    @Override
    public void persistIngridFilter(String grid) {

    }

    @Override
    public ViewType getViewType() {
        return ViewType.DBPROMO;
    }

    @Override
    public Boolean isMuiView() {
        return getViewType().equals(ViewType.MUI);
    }

    @Override
    public void prepareDBPromoJsonFilter() {
        final FilterAttribute anno = new FilterAttribute();
        anno.setCode("anno");
        anno.setColumnName("Anno");
        anno.setDesc("Anno");
        anno.setValues(null);
        final FilterAttribute canale = new FilterAttribute();
        canale.setCode("canale");
        canale.setColumnName("Canale");
        canale.setDesc("Tipo Promozione");
        canale.setValues(null);
        final FilterAttribute gruppo = new FilterAttribute();
        gruppo.setCode("gruppo");
        gruppo.setColumnName("Gruppo");
        gruppo.setDesc("Gruppo");
        gruppo.setValues(null);
        final FilterAttribute promozione = new FilterAttribute();
        promozione.setCode("promozione");
        promozione.setColumnName("Promozione");
        promozione.setDesc("Promozione");
        promozione.setValues(null);
        final FilterAttribute semestre = new FilterAttribute();
        semestre.setCode("semestre");
        semestre.setColumnName("Semestre");
        semestre.setDesc("Semestre");
        semestre.setValues(null);
        final FilterAttribute utente = new FilterAttribute();
        utente.setCode("utente");
        utente.setColumnName("Utente");
        utente.setDesc("Utente");
        utente.setValues(null);
        final FilterAttribute stato = new FilterAttribute();
        stato.setCode("stato");
        stato.setColumnName("Stato");
        stato.setDesc("Stato");
        stato.setValues(null);
        final FilterAttribute dataInizio = new FilterAttribute();
        dataInizio.setCode("dataInizio");
        dataInizio.setColumnName(DBPromoUserFilterEnum.DATA_INIZIO.toString());
        dataInizio.setDesc("Data Inizio Promozione");
        dataInizio.setValues(null);
        dataInizio.setType("datePicker");
        final FilterAttribute dataFine = new FilterAttribute();
        dataFine.setCode("dataFine");
        dataFine.setColumnName(DBPromoUserFilterEnum.DATA_FINE.toString());
        dataFine.setDesc("Data Fine Promozione");
        dataFine.setValues(null);
        dataFine.setType("datePicker");
        final ConfigurationElement element = ConfigurationElement.builder().code("promozioneDBPromo")
                .columnName(FilterSelected.DB_PROMO_DIMENSION_NAME).description("DB Promozione").server(null).mdx(null)
                .attributes(
                        Arrays.asList(anno, canale, gruppo, promozione, semestre, utente, stato, dataInizio, dataFine))
                .build();

        this.availableFilters = new ArrayList<>();
        this.availableFilters.add(element);

        if ((this.availableFilters != null) && (this.availableFilters.size() > 0)) {
            this.jsonFilter = this.getCatalogProducer().toDBPromoJson(this.availableFilters,
                    createPickListFilterDBPromoJson());
        } else {
            log.warn("there are no available DBPromo filters for the current configuration");
        }
    }

    private String createPickListFilterDBPromoJson() {

        final StringBuilder stringBuilder = new StringBuilder("[");
        final List<PromozioneTestataEntity> promozioni = readPromotions();

        if ((promozioni == null) || promozioni.isEmpty()) {

            stringBuilder.append("]");

        } else {

            promozioni.forEach(promozione -> {

                final String canale = promozione.getCanalePromozioneEntity() != null
                        ? promozione.getCanalePromozioneEntity().getDescrizione()
                        : null;
                final String gruppo = (canale != null)
                        && (promozione.getCanalePromozioneEntity().getGruppoPromozioneEntity() != null)
                                ? promozione.getCanalePromozioneEntity().getGruppoPromozioneEntity().getDescrizione()
                                : null;

                final StatoPromozioneEntity statoPromozioneEntity = promozione.getPromozioneStatoEntities().stream()
                        .filter(s -> s.getDataFineStato() == null).findFirst().orElse(null).getStatoPromozioneEntity();
                final String stato = statoPromozioneEntity.getCodiceStato() + "-" + statoPromozioneEntity.getLabel();
                stringBuilder.append("{").append("\"Anno\":").append("\"").append(promozione.getAnno()).append("\"")
                        .append(",").append("\"Canale\":").append("\"").append(canale).append("\"").append(",")
                        .append("\"Gruppo\":").append("\"").append(gruppo).append("\"").append(",")
                        .append("\"Promozione\":").append("\"").append(promozione.getDescrizioneEstesa()).append("\"")
                        .append(",").append("\"Semestre\":").append("\"").append(promozione.getSemestre()).append("\"")
                        .append(",").append("\"Utente\":").append("\"").append(promozione.getCodiceUtenteInserimento())
                        .append("\"").append(",").append("\"Stato\":").append("\"").append(stato).append("\"")
                        .append("},");
            });

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }

    protected UserDTO getUserDto() {
        return getCurrentUser() != null ? userService.asDto(getCurrentUser(), getParsedContesto()) : null;
    }

    private List<PromozioneTestataEntity> readPromotions() {
        try {
            log.debug("promozioni recuperate ed ordinate");
            final UserDTO userDTO = getUserDto();
            return promoService.findAllNotCancelled(userDTO.getCanali()).stream()
                    .sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio)).collect(Collectors.toList());
        } catch (final Exception e) {
            log.error("Error reading promozioni", e);
        }
        return null;
    }

    @Override
    public void setEnabledConnections(List<String> enabledConnections) {
        log.debug("Not dealing with TM1 connections");
    }

    @Override
    public List<String> getEnabledConnections() {
        log.debug("Not dealing with TM1 connections");
        return null;
    }

    @Override
    public void free() {
        this.viewFile = null;
        this.name = null;
        this.jsonFilter = null;
        this.currentJsonFilter = null;
        this.adminJsonFilter = null;
        if (this.availableFilters != null) {
            try {
                this.availableFilters.clear();
            } catch (final Exception e) {
                log.error("Error cleaning available filters", e);
            } finally {
                this.availableFilters = null;
            }
        }

        if (this.currentIngridJsonFilter != null) {
            try {
                this.currentIngridJsonFilter.clear();
            } catch (final Exception e) {
                log.error("Error cleaning ingrid json filter", e);
            } finally {
                this.currentIngridJsonFilter = null;
            }
        }
        this.node = null;
        if (this.availableGrids != null) {
            try {
                this.availableGrids.clear();
            } catch (final Exception e) {
                log.error("Error cleaning available grids", e);
            } finally {
                this.availableGrids = null;
            }
        }
        if (this.configurationMap != null) {
            try {
                this.configurationMap.clear();
            } catch (final Exception e) {
                log.error("Error cleaning configuration map", e);
            } finally {
                this.configurationMap = null;
            }
        }
    }

    @Override
    public void setDisableButtonFilters(boolean value) {
        disableButtonFilters = value;
    }

    @Override
    public ConfigurationEntity getActiveConfiguration(ConfigurationTypes type) {
        final Integer idMenu = Integer.parseInt(this.node.getParams().get("idMenu").get(0));
        if (idMenu != null) {
            List<ConfigurationEntity> configurations = getMuiService().findConfigurationByIdMenu(idMenu);
            if ((configurations != null) && (configurations.size() > 0)) {
                return configurations.stream().filter(c -> type.equals(c.getType()) && (c.getRevisionDate() == null))
                        .findFirst().orElse(null);
            }
        }
        log.error("error reading filters from menu id " + idMenu);
        return null;
    }

    @Override
    public void setCurrentJsonFilter(String currentJsonFilter) {
        this.currentJsonFilter = currentJsonFilter;
    }

    abstract public void applyRules();

    abstract public void applyDefaultRules();

    protected boolean applyRule(ActionEnum rule, CanalePromozioneEntity canale, StatoPromozioneEntity stato,
            ActionTypeEnum type, FormEnum form, ElementFieldEnum element, boolean defaultState) {
        return actionServiceInstance.get().applyRule(rule, canale, stato, type, form, element, defaultState);
    }
}
