package com.axiante.mui.dbpromo.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProducer {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory create() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test-dbpromo");
        return factory;
    }

    public void destroy(@Disposes EntityManagerFactory factory) {
        factory.close();
    }

}
