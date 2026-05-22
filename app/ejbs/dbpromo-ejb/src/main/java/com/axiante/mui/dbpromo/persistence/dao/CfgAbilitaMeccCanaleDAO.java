package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import java.util.List;

public interface CfgAbilitaMeccCanaleDAO extends DbPromoDAO<CfgAbilitaMeccCanaleEntity> {
    List<CfgAbilitaMeccCanaleEntity> findAllByIdCanale(CanalePromozioneEntity entity) throws Exception;
    CfgAbilitaMeccCanaleEntity findByMeccanicaAndCanale(Long idMeccanica, Long idCanale) ;
}
