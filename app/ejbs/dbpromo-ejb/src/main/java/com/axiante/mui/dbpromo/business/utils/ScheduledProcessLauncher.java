package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.dao.PromoPubblicazioneTestataDAO;
import java.io.Serializable;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ScheduledProcessLauncher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -825826708877926026L;
	@Inject
	PromoPubblicazioneTestataDAO dao;

	@Schedule(second = "19", minute = "*/5", hour = "*", persistent = false)
	public void run() {
		if (!dao.runUpdateEsitoPubblicazioni()) {
			log.warn("process failed");
		}
	}

}
