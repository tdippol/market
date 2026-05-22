package com.axiante.mui.webapp.listener;

import lombok.extern.slf4j.Slf4j;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LogoutPhaseListener implements PhaseListener {
    private static final long serialVersionUID = -4718067912006923856L;

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        if ("/views/logout.xhtml".equals(facesContext.getExternalContext().getRequestServletPath())) {
            log.info("Performing logout specific actions");
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            // Stronger according to blog comment below that references HTTP spec
            response.addHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "must-revalidate");
            // some date in the past
            response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");

            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
    }
}
