package com.axiante.mui.webapp.business.service;

import com.axiante.mui.dbpromo.actions.Action;
import com.axiante.mui.dbpromo.actions.ActionEnum;
import com.axiante.mui.dbpromo.actions.ActionProducer;
import com.axiante.mui.dbpromo.actions.ActionTypeEnum;
import com.axiante.mui.dbpromo.actions.ElementFieldEnum;
import com.axiante.mui.dbpromo.actions.FormEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgStatiCanaleConsentitiService;
import com.axiante.mui.webapp.business.ActionService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
public class ActionServiceImpl implements ActionService {

    @Inject
    private Instance<CfgStatiCanaleConsentitiService> statiCanaleConsentitiServiceInstance;

    @Inject
    private Instance<ActionProducer> actionProducerInstance;

    @Override
    public boolean applyRule(ActionEnum rule, CanalePromozioneEntity canale, StatoPromozioneEntity stato,
                             ActionTypeEnum type, FormEnum form, ElementFieldEnum element, boolean defaultState) {
        return defaultState || checkRule(rule, canale, stato, type, form, element);
    }

    @Override
    public boolean applyRule(ActionEnum rule, CanalePromozioneEntity canale, StatoPromozioneEntity stato,
                             ActionTypeEnum type, FormEnum form, ElementFieldEnum element) {
        return checkRule(rule, canale, stato, type, form, element);
    }

    private boolean checkRule(ActionEnum rule, CanalePromozioneEntity canale, StatoPromozioneEntity stato,
                              ActionTypeEnum type, FormEnum form, ElementFieldEnum element) {
        // Con canale e stato recupero le regole attive
        final List<String> regoleAttive = statiCanaleConsentitiServiceInstance.get()
                .findCodiciAzioniConsentiteByCanaleAndStato(canale, stato);
        // Se non ho regole oppure tra le regole attive non ho quella di mio interesse, ritorno FALSE
        if (regoleAttive == null || regoleAttive.isEmpty() || !regoleAttive.contains(rule.getName())) {
            return false;
        }
        // Cerco se la regola che mi interessa e' tra quelle mappate
        final Action action = actionProducerInstance.get().find(rule.getName());
        // Se la regola e' mappata, e' del tipo che mi interessa ed agisce sul form ed elemento di mio interesse
        // ritorno TRUE, altrimenti FALSE
        return action != null
                && type.equals(action.getType())
                && action.getForms().contains(form)
                && action.getFields().contains(element);
    }
}
