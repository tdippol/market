package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
import java.util.List;

@DbPromoJpaDao
public interface MuiFlagDominioDAO extends DbPromoDAO<MuiFlagDominioEntity>{
    List<MuiFlagDominioEntity> findAllAttiviByFlag(Long idFlag);
    List<MuiFlagDominioEntity> findAllAttiviAndDefaultByFlag(Long idFlag) ;
}
