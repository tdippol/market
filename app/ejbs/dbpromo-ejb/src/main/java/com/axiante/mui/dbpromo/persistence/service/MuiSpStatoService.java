package com.axiante.mui.dbpromo.persistence.service;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

import java.util.List;

public interface MuiSpStatoService extends DbPromoService<MuiSpStatoEntity> {

    void save(MuiSpStatoEntity entity);

    MuiSpStatoEntity findCurrentByPromozioneId(Long idPromozione);

    StatoPromozioneEntity findStatoPromozioneByCode(String code);

    MuiSpStatoEntity update(MuiSpStatoEntity entity);
    StatoPromozioneEntity findStatoPromozioneById(Long id);

    List<MuiSpStatoEntity> findAllByPromozioneIdOrderByDataInizio(Long idPromozione);

    void closeCurrentAndInsertNewState(Long idPromozione, Long idStato, String username);
    void setAttivazioneTestata(Long idPromozione, int attiva, String username);

    void runProcedureAssociaCompratori(Long idPromozione, String username);
}