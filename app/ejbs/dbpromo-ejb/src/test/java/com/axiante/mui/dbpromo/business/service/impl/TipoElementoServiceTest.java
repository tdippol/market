package com.axiante.mui.dbpromo.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.TipoElementoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.service.TipoElementoService;
import com.axiante.mui.dbpromo.persistence.service.impl.TipoElementoServiceImpl;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TipoElementoServiceTest {

    @Spy
    private TipoElementoDAO dao;

    @Spy
    @InjectMocks
    private TipoElementoService service = new TipoElementoServiceImpl();

    @Test
    public void findTipoElemento_givenMeccanicaAndSetPromozione_shouldReturnListOfAvailableTipoElemento() {
        final CfgTipoElementoEntity entity = createTipoElementoEntity();
        when(dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
                .thenReturn(entity);
        final List<String> result = service.findTipoElemento(1L, 1L);
        verify(dao, times(1))
                .findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
        assertEquals(2, result.size());
        assertEquals("ARTICOLO", result.get(0));
        assertEquals("GRM", result.get(1));
    }

    @Test(expected = NonUniqueResultException.class)
    public void findTipoElemento_givenMeccanicaAndSetPromozione_shouldThrowException_whenMultipleEntities() {
        when(dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
                .thenThrow(NonUniqueResultException.class);
        service.findTipoElemento(1L, 1L);
        verify(dao, times(1))
                .findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
    }

    private CfgTipoElementoEntity createTipoElementoEntity() {
        final CfgTipoElementoEntity entity = new CfgTipoElementoEntity();
        entity.setArticolo(1);
        entity.setGrm(1);
        entity.setOmogeneo(1);
        entity.setReparto(0);
        entity.setTotale(0);
        entity.setAttributo(0);
        return entity;
    }
}
