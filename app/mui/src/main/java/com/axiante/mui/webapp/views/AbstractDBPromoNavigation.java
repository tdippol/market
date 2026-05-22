package com.axiante.mui.webapp.views;

import com.axiante.mui.webapp.views.content.dbpromo.BasePromoView;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.menu.MenuItem;

public abstract class AbstractDBPromoNavigation extends BasePromoView implements DBPromoNavigationEnabled {
	private static final long serialVersionUID = 5681685717884725632L;
	@Setter
	protected Object idPromozioneCorrente;
	@Setter
	@Getter
	protected Integer landingPage = 0;
	@Setter
	@Getter
	protected Integer landingActionPage = 0;
	@Override
	public void setNode(final MenuItem node) {
		super.setNode(node);
		setLandingPage(navigateTo != null ? navigateTo : 0);
		setLandingActionPage(landingId != null ? landingId : 0);
	}
}
