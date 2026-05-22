package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import java.util.Map;
import javax.faces.event.ActionEvent;
import lombok.Getter;

@MuiViewModel("creaPianoMedia")
public class CreaPianoMediaView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -2909430766228813299L;

    @Getter
    private Boolean confirmPromoRifBtnDisabled = true;

    public void promoRifSelected(ActionEvent event) {
        Map<String, String[]> paramValues = getExternalContext().getRequestParameterValuesMap();
        String[] x = paramValues.get("codePromoRifSelected");
        confirmPromoRifBtnDisabled = x == null || x.length == 0;
    }

    public void resetDialog() {
        confirmPromoRifBtnDisabled = true;
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
