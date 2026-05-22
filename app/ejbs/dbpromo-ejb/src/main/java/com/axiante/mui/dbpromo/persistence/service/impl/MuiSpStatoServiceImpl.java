package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSpStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatiTransizioneService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatoService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
public class MuiSpStatoServiceImpl extends AbstractDbPromoService<MuiSpStatoEntity> implements MuiSpStatoService {
    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    private MuiSpStatoDAO dao;

    @Inject
    private MuiSpStatiTransizioneService muiSpStatiTransizioneService;

    @Override
    public void save(MuiSpStatoEntity entity) {
        getDao().save(entity);
    }


    @Override
    public MuiSpStatoEntity findCurrentByPromozioneId(Long idPromozione) {
        return getDao().findCurrentByPromozioneId(idPromozione);
    }

    @Override
    public MuiSpStatoEntity update(MuiSpStatoEntity entity) {
        return getDao().update(entity);
    }


    //da usare in vari flussi
    @Override
    public void closeCurrentAndInsertNewState(Long idPromozione, Long idStato, String username) {
        Date now = new Date();

        MuiSpStatoEntity statoAttuale = getDao().findCurrentByPromozioneId(idPromozione);
        if (statoAttuale == null) {
            throw new IllegalStateException("Stato attuale non trovato per la special promotion " + idPromozione);
        }

        //gestione procedura
        Long idNuovoStatoEffettivo = idStato;

        if (Long.valueOf(1L).equals(statoAttuale.getStatoPromozioneEntity().getId())
                && Long.valueOf(2L).equals(idStato)) {
            try {
                runProcedureAssociaCompratori(idPromozione, username);
            } catch (Exception ex) {
                List<MuiSpStatiTransizioneEntity> transizioni =
                        muiSpStatiTransizioneService.findByPromozioneAndFromStato(
                                idPromozione,
                                statoAttuale.getStatoPromozioneEntity().getId());

                MuiSpStatiTransizioneEntity transizioneRichiesta = transizioni.stream()
                        .filter(t -> t.getStatoTransizione() != null
                                && Long.valueOf(2L).equals(t.getStatoTransizione().getId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException(
                                "Transizione stato non trovata per promozione " + idPromozione
                                        + ", stato corrente 1 e stato transizione 2"));

                if (transizioneRichiesta.getStatoErrore() == null
                        || transizioneRichiesta.getStatoErrore().getId() == null) {
                    throw new IllegalStateException(
                            "Stato errore non configurato per la transizione della promozione " + idPromozione);
                }

                idNuovoStatoEffettivo = transizioneRichiesta.getStatoErrore().getId();
            }
        }

        // chiude stato corrente
        statoAttuale.setDataFineStato(now);
        statoAttuale.setDataAggiornamento(now);
        statoAttuale.setCodiceUtenteAggiornamento(username);
        getDao().update(statoAttuale);

        StatoPromozioneEntity nuovoStatoEntity = getDao().findStatoPromozioneById(idNuovoStatoEffettivo);
        if (nuovoStatoEntity == null) {
            throw new IllegalStateException("Stato promozione con id " + idNuovoStatoEffettivo + " non trovato");
        }

        MuiSpStatoEntity nuovoStato = new MuiSpStatoEntity();
        nuovoStato.setSpTestataEntity(statoAttuale.getSpTestataEntity());
        nuovoStato.setStatoPromozioneEntity(nuovoStatoEntity);
        nuovoStato.setDataInizioStato(now);
        nuovoStato.setDataFineStato(null);
        nuovoStato.setDataInserimento(now);
        nuovoStato.setCodiceUtenteInserimento(username);
        nuovoStato.setUuid(AxUUID.randomUUID().toString());

        getDao().save(nuovoStato);
    }

    //da usare in vari flussi
    @Override
    public void setAttivazioneTestata(Long idPromozione, int attiva, String username) {
        Date now = new Date();

        MuiSpStatoEntity statoAttuale = getDao().findCurrentByPromozioneId(idPromozione);
        if (statoAttuale == null) {
            throw new IllegalStateException("Stato attuale non trovato per la special promotion " + idPromozione);
        }

        MuiSpTestataEntity testata = statoAttuale.getSpTestataEntity();

        testata.setAttiva(attiva);
        testata.setDataAggiornamento(now);
        testata.setCodiceUtenteAggiornamento(username);
    }


    @Override
    public StatoPromozioneEntity findStatoPromozioneById(Long id) {
        return getDao().findStatoPromozioneById(id);
    }

    @Override
    public List<MuiSpStatoEntity> findAllByPromozioneIdOrderByDataInizio(Long idPromozione) {
        return getDao().findAllByPromozioneIdOrderByDataInizio(idPromozione);
    }

    @Override
    public void runProcedureAssociaCompratori(Long idPromozione, String username) {
        getDao().runProcedureAssociaCompratori(idPromozione, username);
    }

    @Override
    public StatoPromozioneEntity findStatoPromozioneByCode(String code) {
        return getDao().findStatoPromozioneByCode(code);
    }

}