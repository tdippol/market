package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.TotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TotalizzatoriDAOImplTest extends AbstractDaoTest {
    @Inject
    private TotalizzatoriDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    TotalizzatoriDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        IniziativaEntity iniziativa = createIniziativaEntity(1L, "INIZIATIVA 1", new Date(), new Date());
        TotalizzatoriEntity totalizzatore1 = createTotalizzatoreEntity(1L, iniziativa);
        TotalizzatoriEntity totalizzatore2 = createTotalizzatoreEntity(2L, iniziativa, 1L);
        persist(iniziativa, totalizzatore1, totalizzatore2);
    }

    @Test
    public void findAllWithParentByIdIniziativa() {
        final List<TotalizzatoriEntity> result = dao.findAllWithParentByIdIniziativa(1L);
        assertEquals(1, result.size());
    }

    private TotalizzatoriEntity createTotalizzatoreEntity(Long id, IniziativaEntity iniziativaEntity) {
        return createTotalizzatoreEntity(id, iniziativaEntity, null);
    }

    private TotalizzatoriEntity createTotalizzatoreEntity(Long id, IniziativaEntity iniziativa, Long idParent) {
        TotalizzatoriEntity e = new TotalizzatoriEntity();
        e.setId(id);
        e.setIniziativa(iniziativa);
        e.setIdParent(idParent);
        return e;
    }

    private IniziativaEntity createIniziativaEntity(Long id, String descrizione, Date dataInizio, Date dataFine) {
        IniziativaEntity e = new IniziativaEntity();
        e.setId(id);
        e.setDescrizione(descrizione);
        e.setDataInizio(dataInizio);
        e.setDataFine(dataFine);
        return e;
    }
}