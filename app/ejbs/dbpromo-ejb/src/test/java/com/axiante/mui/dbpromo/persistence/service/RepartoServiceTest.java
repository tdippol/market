package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dao.RepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.RepartoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepartoServiceTest {
    @Mock
    private RepartoDAO repartoDAO;
    @Spy
    @InjectMocks
    RepartoServiceImpl service;

    @Test
    public void testPersist() throws Exception {
        RepartoEntity e = Mockito.mock(RepartoEntity.class);

        service.persist(e);
        Mockito.verify(repartoDAO).persist(e);
    }

    @Test
    public void findByCode() {
        String code = "some";
        service.findByCode(code);
        Mockito.verify(repartoDAO).findByCode(code);
    }

}
