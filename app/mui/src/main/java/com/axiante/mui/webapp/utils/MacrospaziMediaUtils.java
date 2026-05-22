package com.axiante.mui.webapp.utils;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiEventoRetailService;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.math.BigDecimal;

@Dependent
public class MacrospaziMediaUtils {

    final DateTimeUtils utils = new DateTimeUtils();
    final MacrospaziDateUtils dateUtils = new MacrospaziDateUtils();

    @Inject
    Instance<MuiEventoRetailService> muiEventoRetailServiceInstance;

    public boolean isValid(MuiMacrospazioMediaEntity entity, boolean editMode) {
        return dateUtils.isValidForMacrospazio(entity.getDataInizio(), entity.getDataFine(), editMode)
                && entity.getListino() != null && entity.getListino().compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean canDelete(MuiMacrospazioMediaEntity macrospazio) {
        return muiEventoRetailServiceInstance.get().countByIdMacrospazio(macrospazio.getId()) == 0;
    }

    public boolean canAdd(MuiMacrospazioMediaEntity macrospazio) {
        if (macrospazio == null) {
            return false;
        }
        if (macrospazio.getId() != null) {
            return false;
        }
        if (StringUtils.isBlank(macrospazio.getCodice())
                || StringUtils.isBlank(macrospazio.getDescrizione())) {
            return false;
        }
        return isValid(macrospazio, false);
    }

    public boolean canEdit(MuiMacrospazioMediaEntity macrospazio) {
        if (macrospazio == null) {
            return false;
        }
        if (macrospazio.getId() == null) {
            return false;
        }
        if (StringUtils.isBlank(macrospazio.getCodice())
                || StringUtils.isBlank(macrospazio.getDescrizione())) {
            return false;
        }
        return isValid(macrospazio, true);
    }
}
