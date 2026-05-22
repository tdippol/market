package com.axiante.mui.webapp.views.login;

import com.axiante.mui.common.utility.SessionParams;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.template.TemplateView;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class LoginView implements Serializable, FacesContextAware {
    /**
     * 
     */
    private static final long serialVersionUID = -4658826057531788426L;
    @Getter
    @Setter
    String username;
    @Getter
    @Setter
    String password;
    @Inject
    transient LoginProducer loginProducer;
    @Inject
    @Getter
    @Setter
    TemplateView template;
    @PostConstruct
    public void init() {
        addMessage(null, new FacesMessage("LoginView initialised. "));
    }

    public void login() throws Exception {
        final UsersEntity user = loginProducer.executeUserLogin(username);
        if ( user != null ) {
            getSessionMap().put(SessionParams.USER_ATTRIBUTE, user);
            template.reloadMenu(user);
            getExternalContext().redirect(getRequestContextPath()+"/views/index.xhtml");
        } else {
            addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));
        }
    }

    public void logout() throws IOException {
        getExternalContext().invalidateSession();
        getExternalContext().redirect(getRequestContextPath()+"/views/index.xhtml");
    }
}
