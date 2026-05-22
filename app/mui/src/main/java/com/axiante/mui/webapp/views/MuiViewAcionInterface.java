package com.axiante.mui.webapp.views;


import com.axiante.mui.model.FilterProducer;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedAction;
import com.axiante.mui.webapp.views.content.aggrid.links.PageLink;
import com.axiante.tm1.json.objects.Cell;
import com.axiante.tm1.json.objects.Row;
import java.util.List;

public interface MuiViewAcionInterface extends MuiViewInterface {
    FilterProducer getFilterProducer();
    TableProducer getUtils();
    SelectedAction getSelectedAction();
    void setSelectedAction(SelectedAction selectedAction);
    void performAction();
    void prepareSelections();
    ApplicationConfiguration getApplicationConfiguration();
    Row<Cell> getGridCurrentHeaders(String grid);
    List<SelectedAction> getAvailableActions();
    void getIngridPicklistValues(final String grid);
    List<PageLink> getAvailableLinks();
}
