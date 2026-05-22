package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSovrapposizioneMeccanicheEntity;
import java.util.List;

public interface CfgSovrapposizioneMeccanicheDAO extends DbPromoDAO<CfgSovrapposizioneMeccanicheEntity> {

    List<CfgSovrapposizioneMeccanicheEntity> findByCanaleMeccanica(CfgAbilitaMeccCanaleEntity meccanicaCanale);
}
