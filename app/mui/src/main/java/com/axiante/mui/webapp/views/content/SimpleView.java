package com.axiante.mui.webapp.views.content;

import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.webapp.views.AbstractMuiView;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.tm1.mdx.objects.Query;

@MuiViewModel("simple")
public class SimpleView extends AbstractMuiView {
    private static final long serialVersionUID = 1L;

    @Override
    public void updateView() {

    }
    @Override
    public void updateView(final String grid){
    }

    @Override
    protected ApplicationFilterCatalogProducer getCatalogProducer() {
        return null;
    }

    @Override
    protected CatalogReducer getCatalogReducer() {
        return null;
    }

    @Override
    public Query prepareFilteredQuery(final String grid) {
        return null;
    }

}
