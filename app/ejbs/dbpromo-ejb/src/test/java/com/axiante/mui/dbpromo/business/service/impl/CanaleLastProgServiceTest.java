package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.CanaleLastProgEntityDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.CanaleLastProgServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CanaleLastProgServiceTest {
    @Mock
    private CanaleLastProgEntityDAO dao;
    @Spy
    @InjectMocks
    private CanaleLastProgServiceImpl service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFindByCanaleThrowsNullPointerExceptionWhenCanaleIsNull(){
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("canale is marked non-null but is null");
        service.findByCanale(null);
    }

    @Test
    public void testFindByCanale(){
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        service.findByCanale(canale);
        verify(dao).findByChannel(canale);
    }
}
