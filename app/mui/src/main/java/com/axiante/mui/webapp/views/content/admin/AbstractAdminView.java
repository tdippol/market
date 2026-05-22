package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.webapp.views.AbstractMuiView;
import com.axiante.mui.webapp.views.MuiAdminView;
import com.axiante.mui.webapp.views.ViewType;

public abstract class AbstractAdminView extends AbstractMuiView implements MuiAdminView {

	private static final long serialVersionUID = -6254696436674425560L;

	public boolean needsFilter(){
		return false;
	}
	@Override
	public ViewType getViewType() {
		return ViewType.ADMIN;
	}

	@Override
	public String getCurrentJsonFilter() {
		return "{}";
	}

	@Override
	public synchronized void prepareJsonFilter() {
		if ( needsFilter() ){
			super.prepareJsonFilter();
		}
	}


	private boolean viewChanged = false;
	@Override
	/*
	 * la prima volta e' false e poi e' sempre true finche non viene
	 * ricreato il view bean
	 */
	public boolean isViewChanged(){
		if ( !viewChanged){
			viewChanged = true;
			return false;
		}
		return viewChanged;
	}
}
