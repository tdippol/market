package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.dao.PromoPubblicazioneTestataDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledProcessLauncherTest {
    @Mock
    PromoPubblicazioneTestataDAO dao;

    @InjectMocks
    ScheduledProcessLauncher launcher;

    @Test
    public void run_success() {
        when(dao.runUpdateEsitoPubblicazioni()).thenReturn(true);
        launcher.run();
        verify(dao, times(1)).runUpdateEsitoPubblicazioni();
    }

    @Test
    public void run_failed() {
        when(dao.runUpdateEsitoPubblicazioni()).thenReturn(false);
        launcher.run();
        verify(dao, times(1)).runUpdateEsitoPubblicazioni();
    }
}