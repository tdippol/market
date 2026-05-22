package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.webapp.business.data.ArticoloInPromoEnum;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.ReportArticoliCercaDialogBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.pojo.ArticoloInPromoPojo;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("reportArticoliPromo")
@Dependent
@Slf4j
public class ReportArticoliPromoView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -8839845577507771610L;

    private static final String TAB_SELEZIONE = "tab-selezione";
    private static final String TAB_COPIA_INCOLLA = "tab-copia-incolla";

    @Getter
    @Setter
    private String codiceArticolo;

    @Getter
    @Setter
    private String descrizioneArticolo;

    @Getter
    @Setter
    private String searchType;

    @Getter
    private boolean cercaArticoloDialogConfirmBtnDisabled = true;

    @Getter
    private boolean searchButtonDisabled = true;

    @Getter
    String currentActiveTab;

    @Getter
    private ArticoloInPromoPojo itemSelected;

    @Getter
    @Inject
    private ReportArticoliCercaDialogBackingBean cercaDialogBackingBean;

    private void init() {
        this.currentActiveTab = TAB_COPIA_INCOLLA;
        resetAll();
    }

    public void cercaArticoloAction() {
        resetCercaArticoloDialogAction();
    }

    public void resetCercaArticoloDialogAction() {
        this.currentActiveTab = TAB_COPIA_INCOLLA;
        resetAll();
        cercaDialogBackingBean.prepare();
    }

    public void cercaArticoloConfirmBtnAction() {
        if (itemSelected != null) {
            codiceArticolo = itemSelected.getCodiceArticolo();
            descrizioneArticolo = itemSelected.getDescrizioneArticolo();
            searchButtonDisabled = false;
            searchType = null;
            executeScript("clearGridReportArticoliInPromo()");
        }
    }

    public void onTabChange(TabChangeEvent event) {
        this.currentActiveTab = event.getTab().getId();
        resetAll();
    }

    public void selezionaItemAction() {
        final Map<String, String> params = getRequestParameterMap();
        if (params == null) {
            log.error("Cannot find request parameters");
            return;
        }
        final String itemSelectedJsonArray = params.get("itemSelected");
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemSelectedJsonArray);
            if (jsonNode != null) {
                switch (currentActiveTab) {
                    case TAB_SELEZIONE:
                        handleSingleSelection(jsonNode);
                        break;
                    case TAB_COPIA_INCOLLA:
                        handleCopyPaste(jsonNode);
                        break;
                }
            }
            this.cercaArticoloDialogConfirmBtnDisabled = itemSelected == null;
        } catch (Exception ex) {
            log.error("Error reading request parameters for 'itemSelected'", ex);
            this.cercaArticoloDialogConfirmBtnDisabled = true;
        }
    }

    public void cercaInCorsoFuturiAction() {
        cercaArticolo(ArticoloInPromoEnum.IN_CORSO_FUTURI);
    }

    public void cercaCompletatiAction() {
        cercaArticolo(ArticoloInPromoEnum.COMPLETATI);
    }

    private void cercaArticolo(ArticoloInPromoEnum searchType) {
        this.searchType = "";
        switch (searchType) {
            case IN_CORSO_FUTURI:
                this.searchType = "in corso (righe con fondo bianco) e future";
                break;
            case COMPLETATI:
                this.searchType = "completate";
                break;
        }
        executeScript(String.format("loadGridReportArticoliInPromo('%s', %d)", searchType, itemSelected.getItemId()));
    }

    private void handleSingleSelection(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            JsonNode node = jsonNode.get(0);
            itemSelected = ArticoloInPromoPojo.builder().itemId(node.get("idItem").asLong())
                    .codiceArticolo(node.get("codice").asText())
                    .descrizioneArticolo(node.get("descrizione").asText())
                    .build();
        }
    }

    private void handleCopyPaste(final JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            JsonNode node = jsonNode.get(0);
            if (node.get("isValidCode") != null && node.get("isValidCode").asBoolean()) {
                itemSelected = ArticoloInPromoPojo.builder().itemId(node.get("idItem").asLong())
                        .codiceArticolo(node.get("codice").get("value").asText())
                        .descrizioneArticolo(node.get("descrizione").get("value").asText())
                        .build();
            }
        }
    }

    private void resetAll() {
        this.cercaArticoloDialogConfirmBtnDisabled = true;
        this.itemSelected = null;
        this.cercaDialogBackingBean.resetSelections();
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }
}
