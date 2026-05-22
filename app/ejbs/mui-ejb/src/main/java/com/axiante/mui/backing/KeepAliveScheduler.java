package com.axiante.mui.backing;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.io.Serializable;
import java.util.HashSet;

@Singleton
@Slf4j
public class KeepAliveScheduler implements Serializable {
    HashSet<CookieRepository> cookies;

    @Schedule(second="13",  minute = "*/15", hour = "*", persistent=false)
    public void run() {
        // ogni 15 minuti
        if ( cookies != null ){
            cookies.forEach(c->c.keepAlive());
        }
    }

    public void addRepository(@NonNull CookieRepository cookie){
        if ( cookies == null ){
            cookies = new HashSet<>();
        }
        cookies.add(cookie);
    }

    public void removeRepository(@NonNull CookieRepository cookie){
        if ( cookies != null ) {
            cookies.remove(cookie);
        } else {
            log.warn("repository list empty");
        }
    }

}
