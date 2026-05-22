package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.business.enumeration.PromoShopsRadioEnum;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RadioBackingBean {

    private String value;
//    private String optionViewAll;
//    private String optionViewModified;

    public String getPromoShops() {
        return this.value = PromoShopsRadioEnum.VISUALIZZA_TUTTO.getValue();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOptionViewAll() {
        return PromoShopsRadioEnum.VISUALIZZA_TUTTO.getValue();
    }

    public String getOptionViewModified() {
        return PromoShopsRadioEnum.VISUALIZZA_MODIFICHE.getValue();
    }

}
