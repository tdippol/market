package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dao.PromoPubblicazioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.PromoPubblicazioneTestataServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoPubblicazioneTestataServiceTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PromoPubblicazioneTestataDAO promoPubblicazioneTestataDAO;

    @Spy
    @InjectMocks
    PromoPubblicazioneTestataServiceImpl service;

    @Test
    public void testFindAll() throws Exception {
        service.findAll();
        Mockito.verify(promoPubblicazioneTestataDAO).findAll();
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 123L;
        service.findById(id);
        Mockito.verify(promoPubblicazioneTestataDAO).findById(id);
    }

    @Test
    public void testFindByPromoID() throws Exception {
        Long promoID = 123L;
        service.findByPromoID(promoID);
        Mockito.verify(promoPubblicazioneTestataDAO).findByPromozioneID(promoID);
    }

    @Test
    public void testPersist() {
        PromoPubblicazioneTestataEntity entity = Mockito.mock(PromoPubblicazioneTestataEntity.class);
        service.persist(entity, "JUNIT TEST");
        Mockito.verify(promoPubblicazioneTestataDAO).persistWithAuditLog(entity, "JUNIT TEST");
    }

    @Test
    public void persist_givenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.persist(null, "JUNIT TEST");
    }
}
