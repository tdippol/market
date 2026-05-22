package com.axiante.mui.webapp.utils;

import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSottoclasseService;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Dependent
public class SottoclasseUtil {
    @Inject
    private Instance<MuiSottoclasseService> sottoclasseServiceInstance;

    public List<MuiSottoclasseEntity> getSottoclassiByStatoPromozione(String codiceStatoPromozione) {
        return Arrays.asList("400", "410", "500").contains(codiceStatoPromozione)
                ? sottoclasseServiceInstance.get().findAll()
                : sottoclasseServiceInstance.get().findAllAttive();
    }

    public MuiSottoclasseEntity getSottoclasseByCodiceAndStatoPromozione(String codice, String codiceStatoPromozione) {
        return Arrays.asList("400", "410", "500").contains(codiceStatoPromozione)
                ? sottoclasseServiceInstance.get().findByCode(codice)
                : sottoclasseServiceInstance.get().findActiveByCode(codice);
    }
}
