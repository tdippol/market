package com.axiante.mui.webapp.business;

import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.actions.ActionTypeEnum;
import com.axiante.mui.dbpromo.actions.ElementFieldEnum;
import com.axiante.mui.dbpromo.actions.FormEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

public interface ActionService {
    boolean applyRule(ActionEnum rule, CanalePromozioneEntity canale, StatoPromozioneEntity stato,
                      ActionTypeEnum type, FormEnum form, ElementFieldEnum element, boolean defaultState);
    boolean applyRule(ActionEnum rule, CanalePromozioneEntity canale, StatoPromozioneEntity stato,
                      ActionTypeEnum type, FormEnum form, ElementFieldEnum element);
}
