package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.webapp.views.FacesContextAware;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.menu.MenuModel;

public interface NegoziPromoBackinInterface extends FacesContextAware {
    MenuModel getMenuModelB();

    MenuModel getMenuModelF();
    
    MenuModel getMenuModelCB();

    MenuModel getMenuModelCF();

    Long getIdPromo();

    void setIdPromo(Long idPromo);

    void menuItemClicked(final ActionEvent event);

    void resetDefault();

    String getVisualizzaStatus();

    void setVisualizzaStatus(String visualizzaStatus);

    void handleFileUpload(final FileUploadEvent event);

}
