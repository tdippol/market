package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@MuiViewModel("editPromo")
@Slf4j
public class EditPromoView extends AbstractDBPromoNavigation {

    private static final long serialVersionUID = -4985183993092027769L;

    @Getter
    public String descrizioneEstesaPromozione;

    public void eliminaPromozione() {
        Map<String, String> params = getRequestParameterMap();
        String descrizioneEstesa = params.get("promoDescriptionToBeDeleted");

        if (descrizioneEstesa != null) {
            descrizioneEstesaPromozione = descrizioneEstesa;
        } else {
            log.warn("Description of promo to delete is null");
            descrizioneEstesaPromozione = "";
        }

        executeScript("PF('dbPromoGlobalConfirmDialog').show();");
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
