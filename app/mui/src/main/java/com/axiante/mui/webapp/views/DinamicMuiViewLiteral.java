package com.axiante.mui.webapp.views;

import javax.enterprise.util.AnnotationLiteral;

@SuppressWarnings("all")
public class DinamicMuiViewLiteral extends AnnotationLiteral<MuiViewModel> implements MuiViewModel{
	private static final long serialVersionUID = 1L;

	private String type;
	@Override
	public String value() {
		return this.type;
	}

	public DinamicMuiViewLiteral(String type) {
		this.type = type;
	}

}
