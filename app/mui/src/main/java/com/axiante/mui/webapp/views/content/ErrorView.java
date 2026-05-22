package com.axiante.mui.webapp.views.content;

import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.webapp.views.AbstractMuiView;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.tm1.mdx.objects.Query;
import lombok.extern.slf4j.Slf4j;

@MuiViewModel("error")
@Slf4j
public class ErrorView extends AbstractMuiView {
	private static final long serialVersionUID = 1L;

	@Override
	public void updateView() {
		log.debug("nothing to do here");
	}

	@Override
	public void updateView(String grid){
		log.debug("nothing to do here");
	}

	@Override
	protected ApplicationFilterCatalogProducer getCatalogProducer() {
		log.debug("nothing to do here: no filter available for error page");
		return null;
	}

	@Override
	protected CatalogReducer getCatalogReducer() {
		log.debug("nothing to do here: no filter available for error page");
		return null;
	}

	@Override
	public Query prepareFilteredQuery(String grid) {
		return null;
	}
}
