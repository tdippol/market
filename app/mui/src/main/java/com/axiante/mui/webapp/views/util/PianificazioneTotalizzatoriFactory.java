package com.axiante.mui.webapp.views.util;

import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.webapp.webservice.dto.RAFResponse;
import com.axiante.mui.webapp.webservice.dto.RafContent;
import java.util.HashSet;
import java.util.Set;

public class PianificazioneTotalizzatoriFactory {


    public static Set<PianificazioneTotalizzatoriEntity> createPianificazioneTotalizzatoriEntitySet(RAFResponse response) {
        final Set<PianificazioneTotalizzatoriEntity> set = new HashSet<>();
        set.add(response.getParent().toPianificazioneTotalizzatoriEntity());
        for (RafContent rafContent : response.getFigli()) {
            set.add(rafContent.toPianificazioneTotalizzatoriEntity());
        }
        return set;
    }

}
