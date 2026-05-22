package com.axiante.mui.webapp.utils.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import javax.faces.context.FacesContext;

public class AxExceptionHandlerFactory extends ExceptionHandlerFactory {
    private static final String CONTEXT = "com.axiante.mui.webapp.utils.exception.AxExceptionHandlerFactory";
    private static final String ACTIVE = "ACTIVE";
    private ExceptionHandlerFactory parent;

    public AxExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler result = null;
        String activeParam = FacesContext.getCurrentInstance().getExternalContext().getInitParameter(CONTEXT);
        if (ACTIVE.equalsIgnoreCase(activeParam))
            result = new AxExceptionHandler(parent.getExceptionHandler());
        else
            result = parent.getExceptionHandler();

        return result;
    }
}
