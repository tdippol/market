package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import java.util.Date;
import java.util.List;

public interface CreaPromozioneDAO extends DbPromoDAO<CreaPromozioneEntity> {

    List<CreaPromozioneEntity> findAllByUserId(String userId) throws Exception;

    CreaPromozioneEntity findByUserIdAndSlotId(String userId, String slotId) throws Exception;

    boolean runFunctionCountTestate(Long idUser, Long idCanale, Date dataInizio, Date dataFine) throws Exception ;

    boolean runFunctionCountTestate(Long idCanale, Date dataInizio, Date dataFine, Long maxTestate) throws Exception ;
}
