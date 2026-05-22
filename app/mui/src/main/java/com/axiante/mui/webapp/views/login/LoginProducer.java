package com.axiante.mui.webapp.views.login;

import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class LoginProducer implements Serializable {
	private static final long serialVersionUID = 8245665211318385997L;
	@Inject
	transient private MuiService muiService;

	public UsersEntity executeUserLogin(final String name) {
		try {
			UsersEntity user = this.muiService.findUser(name);
			if (user != null) {
				user.setLastAccess(new Date(System.currentTimeMillis()));
				user = this.muiService.persistUser(user);
			}
			return user;
		} catch (final Exception e) {
			log.error("Error retrieving user: ", e);
		}
		return null;
	}

	public String getTm1AuthString(final HttpServletRequest request, final String biServer) {
		String connectionString = "localhost";
		try {
			final String login = "?b_action=xts.run&m=portal/bridge.xts&c_env=portal/variables_TM1.xml&c_cmd=../tm1/web/tm1web.html";
			final String basePath = request.getRequestURI().split("/")[1];
			String path = request.getRequestURL().toString();
			path = path.substring(0, path.lastIndexOf(basePath) + basePath.length());
			final String ps = path + "&pg=cam.html&host=localhost";
			connectionString = biServer + login + "&ps=" + ps;
		} catch (final Exception e) {
			log.error("error determining TM1 SSO Auth end point :", e);
		}
		return connectionString;

	}
}
