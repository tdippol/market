package com.axiante.mui.webapp.views.content.dbpromo;

import static com.axiante.mui.webapp.business.data.BuonoInPromoEnum.COMPLETATI;
import static com.axiante.mui.webapp.business.data.BuonoInPromoEnum.FUTURI;
import static com.axiante.mui.webapp.business.data.BuonoInPromoEnum.IN_CORSO;

import com.axiante.mui.webapp.business.data.BuonoInPromoEnum;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("reportBuoniBS")
@Dependent
@Slf4j
public class ReportBuoniBSView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -5144123058756676711L;

    @Getter
    @Setter
    private String codiceBuono;

    @Getter
    @Setter
    private String searchType;

    private void init() {
        resetAll();
    }

    public void cercaInCorsoAction() {
        cercaBuono(IN_CORSO);
    }

    public void cercaFuturiAction() {
        cercaBuono(FUTURI);
    }

    public void cercaCompletatiAction() {
        cercaBuono(COMPLETATI);
    }

    private void cercaBuono(BuonoInPromoEnum buonoInPromoEnum) {
        this.searchType = "";
        switch (buonoInPromoEnum) {
            case IN_CORSO:
                this.searchType = "in corso";
                break;
            case FUTURI:
                this.searchType = "future";
                break;
            case COMPLETATI:
                this.searchType = "completate";
                break;
        }
        if (StringUtils.isBlank(codiceBuono)) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Inserire un valore per il Codice Buono da ricercare."));
        } else {
            executeScript(String.format("loadGridReportBuoniBS('%s', '%s')", buonoInPromoEnum, codiceBuono));
        }
    }

    private void resetAll() {
        codiceBuono = null;
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    @Override
    public void applyRules() {
        // NOOP
    }

    @Override
    public void applyDefaultRules() {
        // NOOP
    }
}
