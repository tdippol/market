package com.axiante.mui.utils;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources implements Serializable{

    @Produces
    @PersistenceContext(unitName = "muipromoPU")
    private EntityManager em;
    // This is to make an instance of the cellset cleaner
    // @Inject
    // CacheConsuelo consuelo;
}
