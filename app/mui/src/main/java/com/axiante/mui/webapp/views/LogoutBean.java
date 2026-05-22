package com.axiante.mui.webapp.views;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Named(value = "logoutBean")
@Slf4j
public class LogoutBean implements FacesContextAware{

	public void restart() {
		getExternalContext().getSession(true);
		try {
			getExternalContext().redirect("index.xhtml?faces-redirect=true");
		} catch (IOException e) {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore durante la redirect", "Contattare l'assistenza"));
			log.error("error redirecting to the login page", e);
		}
	}
}
