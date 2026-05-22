package com.axiante.mui.webapp.webservice;

import com.axiante.mui.persistence.entity.UsersEntity;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
/**
 * fitlers out every request to a webservice.
 * Used for logging purposes
 */
public class ResourceFilter implements ContainerRequestFilter {
    @Context
    HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        checkUser();
    }

    UsersEntity getCurrentUser(HttpSession session) {
        if (session != null && session.getAttribute(UsersEntity.USER_ATTRIBUTE) != null) {
            return (UsersEntity) session.getAttribute(UsersEntity.USER_ATTRIBUTE);
        }
        return null;
    }

     void checkUser() {
        if (request != null) {
            UsersEntity user = getCurrentUser(request.getSession());
            final String msg = String.format("%s is asking permission to go to: %s",
                    user != null ? "User " + user.getName() : "Anonymous", request.getRequestURI());
            log.debug(msg);
        }
    }

}
