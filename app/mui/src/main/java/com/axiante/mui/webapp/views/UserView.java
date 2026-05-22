package com.axiante.mui.webapp.views;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named

public class UserView implements Serializable, FacesContextAware {
	/**
	 *
	 */
	private static final long serialVersionUID = -7399556263761567816L;
	private String logoutPage = null;

	@PostConstruct
	public void init() {
		this.logoutPage = this.getRequestContextPath() + "/views/logout.xhtml";
	}

	public void logout() throws IOException {
		this.getExternalContext().invalidateSession();
		this.getExternalContext().redirect(logoutPage);
	}
}
