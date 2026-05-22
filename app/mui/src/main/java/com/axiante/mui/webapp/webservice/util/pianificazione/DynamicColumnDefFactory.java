package com.axiante.mui.webapp.webservice.util.pianificazione;

import com.axiante.mui.webapp.webservice.util.pianificazione.util.DynamicColumnEnum;
import java.util.Objects;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class DynamicColumnDefFactory {

    @Inject
    private ArticleColumnDef articleColumnDef;

    @Inject
    private GrmColumnDef grmColumnDef;

    @Inject
    private DepartmentColumnDef departmentColumnDef;

    @Inject
    private AttributeColumnDef attributeColumnDef;

    @Inject
    private PlanningColumnDef planningColumnDef;

    @Inject
    private CopyPasteColumnDef copyPasteColumnDef;

    @Inject
    private ComplementaryColumnDef complementaryColumnDef;

    public DynamicColumnDefFactory() {
    }

    public DynamicColumnDef getDynamicColumnDef(String field) {
        if (Objects.isNull(field)) {
            return null;
        }
        DynamicColumnDef dynamicColumnDef = null;

        DynamicColumnEnum dynamicColumnEnum = DynamicColumnEnum.from(field);

        if (dynamicColumnEnum != null) {
            switch (dynamicColumnEnum) {
                case ARTICOLO:
                    dynamicColumnDef = articleColumnDef;
                    break;
                case GRM:
                    dynamicColumnDef = grmColumnDef;
                    break;
                case REPARTO:
                    dynamicColumnDef = departmentColumnDef;
                    break;
                case PIANIFICAZIONE:
                    dynamicColumnDef = planningColumnDef;
                    break;
                case COPIA_INCOLLA:
                    dynamicColumnDef = copyPasteColumnDef;
                    break;
                case COMPLEMENTARI:
                    dynamicColumnDef = complementaryColumnDef;
                    break;
                case TOTALE:
                    break;
                case ATTRIBUTO:
                    dynamicColumnDef = attributeColumnDef;
                    break;
            }
        } else {
            log.debug(String.format("A columnDef definition for %s not exist", field));
        }
        return dynamicColumnDef;
    }

    public String getDynamicColumnDefGridName(String field) {
        if (Objects.isNull(field)) {
            return null;
        }

        DynamicColumnEnum dynamicColumnEnum = DynamicColumnEnum.from(field);

        if (dynamicColumnEnum != null) {
            switch (dynamicColumnEnum) {
                case ARTICOLO:
                case GRM:
                case REPARTO:
                case ATTRIBUTO:
                    return "pianificazione_dialog_inserisci_selezione";
                case PIANIFICAZIONE:
                    return "db_pianificazione";
                case COPIA_INCOLLA:
                    return "db_pianificazione_dialog_copiaIncollaTab";
                case COMPLEMENTARI:
                    return "db_pianificazione_complementari";
                default:
                    break;
            }
        } else {
            log.debug(String.format("A columnDef definition for %s not exist", field));
        }
        return "";
    }

}
