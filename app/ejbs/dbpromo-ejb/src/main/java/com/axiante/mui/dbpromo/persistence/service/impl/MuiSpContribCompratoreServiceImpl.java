package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSpContribCompratoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreHistEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpContribCompratoreHistService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpContribCompratoreService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatoService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
@Transactional
public class MuiSpContribCompratoreServiceImpl extends AbstractDbPromoService<MuiSpContribCompratoreEntity> implements MuiSpContribCompratoreService {

    private static final long serialVersionUID = 1L;

    @Inject
    private MuiSpStatoService muiSpStatoService;

    @Inject
    private MuiSpContribCompratoreDAO dao;

    @Inject
    private MuiSpContribCompratoreHistService muiSpContribCompratoreHistService;

    @Override
    public MuiSpContribCompratoreDAO getDao() {
        return dao;
    }

    @Override
    public List<MuiSpContribCompratoreEntity> findByPromozioneId(Long idPromozione) {
        return getDao().findByPromozioneId(idPromozione);
    }

    @Override
    public MuiSpContribCompratoreEntity findByPromozioneIdAndCompratoreId(Long idPromozione, Long idCompratore) {
        return getDao().findByPromozioneIdAndCompratoreId(idPromozione, idCompratore);
    }

    @Override
    public Integer toggleContribuzione(Long idPromozione, Long idCompratore, String username) {
        MuiSpContribCompratoreEntity entity =
                getDao().findByPromozioneIdAndCompratoreId(idPromozione, idCompratore);

        if (entity == null) {
            throw new IllegalStateException(
                    "Contribuzione compratore non trovata per promozione " + idPromozione
                            + " e compratore " + idCompratore);
        }

        Integer flAttuale = entity.getFlContribuzione() == null ? 0 : entity.getFlContribuzione();
        Integer nuovoValore = flAttuale.intValue() == 1 ? 0 : 1;

        entity.setFlContribuzione(nuovoValore);

        MuiSpContribCompratoreHistEntity storico = new MuiSpContribCompratoreHistEntity();
        storico.setSpTestataEntity(entity.getSpTestataEntity());
        storico.setCompratoreEntity(entity.getCompratoreEntity());
        storico.setFlContribuzione(nuovoValore);
        storico.setDataInserimento(new Date());
        storico.setCodiceUtenteInserimento(username);

        muiSpContribCompratoreHistService.persist(storico);

        List<MuiSpContribCompratoreEntity> tutti = getDao().findByPromozioneId(idPromozione);

        boolean tuttiVerdi = true;

        for (MuiSpContribCompratoreEntity c : tutti) {
            if (c.getFlContribuzione() == null || c.getFlContribuzione().intValue() != 1) {
                tuttiVerdi = false;
                break;
            }
        }

        String codiceNuovoStato = tuttiVerdi ? "310" : "30";

        MuiSpStatoEntity statoAttuale = muiSpStatoService.findCurrentByPromozioneId(idPromozione);

        if (statoAttuale == null || statoAttuale.getStatoPromozioneEntity() == null) {
            throw new IllegalStateException("Stato attuale non trovato per la special promotion " + idPromozione);
        }

        String codiceStatoAttuale = statoAttuale.getStatoPromozioneEntity().getCodiceStato();

        if (!codiceNuovoStato.equals(codiceStatoAttuale)) {
            StatoPromozioneEntity nuovoStato =
                    muiSpStatoService.findStatoPromozioneByCode(codiceNuovoStato);

            if (nuovoStato == null || nuovoStato.getId() == null) {
                throw new IllegalStateException("Stato promozione non trovato per codice " + codiceNuovoStato);
            }

            muiSpStatoService.closeCurrentAndInsertNewState(
                    idPromozione,
                    nuovoStato.getId(),
                    username
            );
        }

        return nuovoValore;
    }

    @Override
    public Map<Long, Integer> findContribuzioneByCompratore(Long idPromozione) {
        List<MuiSpContribCompratoreEntity> list = getDao().findByPromozioneId(idPromozione);

        Map<Long, Integer> result = new HashMap<Long, Integer>();

        if (list != null) {
            for (MuiSpContribCompratoreEntity c : list) {
                if (c.getCompratoreEntity() != null && c.getCompratoreEntity().getId() != null) {
                    result.put(c.getCompratoreEntity().getId(), c.getFlContribuzione());
                }
            }
        }

        return result;
    }

}