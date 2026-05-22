package com.axiante.mui.webapp.views.content.aggrid;

import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.model.FilterProducer;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.webapp.views.AbstractMuiActionView;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.ViewType;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import lombok.Getter;

@Dependent
@MuiViewModel("welcome")
public class WelcomeView extends AbstractMuiActionView {
	private static final long serialVersionUID = -7845751138146058766L;
	@Inject
	@Getter
	private ApplicationFilterCatalogProducer catalogProducer;
	@Inject
	@Getter
	private CatalogReducer catalogReducer;
	@Inject
	@Getter
	private FilterProducer filterProducer;
	@Inject
	@Getter
	private TableProducer utils;

	@Override
	public ViewType getViewType() {
		return ViewType.WELCOME;
	}
}
