package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.*;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MuiFontEntitiesDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaMuiFontEntitesDAOTest extends AbstractDaoTest {

    @Inject
    private MuiFontEntitiesDAO dao;


    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaMuiFontEntitiesDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();



    @Rule
    public ExpectedException ex = ExpectedException.none();

    MuiFontEntities fontEntities1 = null;
    MuiFontEntities fontEntities2 = null;

    @Before
    public void setUp() throws Exception {
        fontEntities1 = new MuiFontEntities();
        fontEntities1.setId("ID1");
        fontEntities1.setDescrizione("DESCRIZIONE 1");
        fontEntities2 = new MuiFontEntities();
        fontEntities2.setId("ID2");
        fontEntities2.setDescrizione("DESCRIZIONE 2");

        persist(fontEntities1, fontEntities2);

    }
    @Test
    public void findAll() {
        List<MuiFontEntities> result = dao.findAll();
        assertEquals(2, result.size());
        assertEquals(
                Arrays.asList("ID1", "ID2"),
        result.stream().map(MuiFontEntities::getId).sorted().collect(Collectors.toList())
        );
        assertEquals(
                Arrays.asList("DESCRIZIONE 1", "DESCRIZIONE 2"),
                result.stream().map(MuiFontEntities::getDescrizione).sorted().collect(Collectors.toList())
        );
    }

    @Test
    public void findById(){
        MuiFontEntities entities = dao.findById("ID1");
        assertNotNull(entities);
        assertEquals(fontEntities1, entities);
    }

}