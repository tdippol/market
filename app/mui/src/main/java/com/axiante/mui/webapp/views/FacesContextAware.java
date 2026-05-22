package com.axiante.mui.webapp.views;

import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import lombok.NonNull;
import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;
import java.util.Objects;

public interface FacesContextAware {

    default void addErrorMessage(final String summary, final String detail) {
        FacesMessage message =
                new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        summary,
                        detail);
        addMessage(null, message);
    }

    default void addInfoMessage(final String summary, final String detail) {
        FacesMessage message =
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        summary,
                        detail);
        addMessage(null, message);
    }

    default void addWarningMessage(final String summary, final String detail) {
        FacesMessage message =
                new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        summary,
                        detail);
        addMessage(null, message);
    }
    default void addMessage(final String s, final FacesMessage facesMessage) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            facesContext.addMessage(s, facesMessage);
            getAjax().update("formGrowl:growl");
        }
    }

    default void executeScript(String script) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            PrimeFaces.current().executeScript(script);
        }
    }

    default ExternalContext getExternalContext() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext != null ? facesContext.getExternalContext() : null;
    }

    default Map<String, Object> getSessionMap() {
        final ExternalContext externalContext = getExternalContext();
        return externalContext != null ? externalContext.getSessionMap() : null;
    }

    default String getRequestContextPath() {
        final ExternalContext externalContext = getExternalContext();
        return externalContext != null ? externalContext.getRequestContextPath() : null;
    }

    default Map<String, String> getRequestParameterMap() {
        final ExternalContext externalContext = getExternalContext();
        return externalContext != null ? externalContext.getRequestParameterMap() : null;
    }

    default void updateComponent(@NonNull final String componentId) {

        getAjax().update(componentId);
    }

    default UIComponent getComponentById(String idComponente) {
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent component = view.findComponent(idComponente);
        return component;
    }

    default UsersEntity getCurrentUser() {
        if ((getSessionMap() != null) && (getSessionMap().get(UsersEntity.USER_ATTRIBUTE) != null)) {
            return (UsersEntity) getSessionMap().get(UsersEntity.USER_ATTRIBUTE);
        }
        return null;
    }

    default Ajax getAjax() {
        return PrimeFaces.current().ajax();
    }

    default boolean isUserAdmin() {
        return (getCurrentUser() != null) && (getCurrentUser().getRoles() != null) && getCurrentUser().getRoles()
                .stream().filter(Objects::nonNull).anyMatch(RolesEntity::isAdmin);
    }

}
