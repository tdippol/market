package com.axiante.mui.backing;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class CacheConsuelo {

	@Inject
	@Getter
	CellSetCache cellCache;

	@Schedule(second="27",  minute = "*", hour = "*", persistent=false)
    public void run() {
        // al secondo 27 di ogni minuto
		log.debug("cleaning the box named ["+ this.getCellCache().hashCode() +"]");
		this.getCellCache().closeForTimeout();
		log.debug("cleaning done");
    }
}
