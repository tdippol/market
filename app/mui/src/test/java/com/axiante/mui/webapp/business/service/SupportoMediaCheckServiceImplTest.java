package com.axiante.mui.webapp.business.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import java.util.List;
import org.junit.Test;

public class SupportoMediaCheckServiceImplTest {

    private SupportoMediaCheckServiceImpl service = new SupportoMediaCheckServiceImpl();

    @Test
    public void readAll() {
        final List<SupportoMediaCheckEnum> checks = service.readAll();
        assertEquals(9, checks.size());
        assertTrue(checks.contains(SupportoMediaCheckEnum.DATA_INIZIO_COINCIDE_DATA_INIZIO_CAMPAGNA));
        assertTrue(checks.contains(SupportoMediaCheckEnum.DURATA_MINORE_3GG));
        assertTrue(checks.contains(SupportoMediaCheckEnum.DURATA_MAGGIORE_5GG));
        assertTrue(checks.contains(SupportoMediaCheckEnum.CHIUSURA_1GG_PRIMA_DATA_FINE));
        assertTrue(checks.contains(SupportoMediaCheckEnum.DURATA_1GG));
        assertTrue(checks.contains(SupportoMediaCheckEnum.DURATA_4GG));
        assertTrue(checks.contains(SupportoMediaCheckEnum.DURATA_5GG));
        assertTrue(checks.contains(SupportoMediaCheckEnum.DATA_INIZIO_3_GIORNI_PRIMA_DATA_INIZIO_CAMPAGNA));
        assertTrue(checks.contains(SupportoMediaCheckEnum.DATA_FINE_COINCIDE_CON_DATA_FINE_CAMPAGNA));
    }
}