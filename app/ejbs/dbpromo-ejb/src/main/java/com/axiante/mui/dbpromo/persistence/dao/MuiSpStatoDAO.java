package com.axiante.mui.dbpromo.persistence.dao;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

import java.util.List;

public interface MuiSpStatoDAO extends DbPromoDAO<MuiSpStatoEntity> {

    void save(MuiSpStatoEntity entity);


    StatoPromozioneEntity findStatoPromozioneByCode(String code);


    MuiSpStatoEntity findCurrentByPromozioneId(Long idPromozione);
    StatoPromozioneEntity findStatoPromozioneById(Long id);
    MuiSpStatoEntity update(MuiSpStatoEntity entity);

    List<MuiSpStatoEntity> findAllByPromozioneIdOrderByDataInizio(Long idPromozione);

    void runProcedureAssociaCompratori(Long idPromozione, String username);
}