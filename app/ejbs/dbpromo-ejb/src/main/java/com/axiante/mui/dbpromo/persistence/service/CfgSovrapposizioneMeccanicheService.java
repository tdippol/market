package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSovrapposizioneMeccanicheEntity;
import java.util.List;

public interface CfgSovrapposizioneMeccanicheService extends DbPromoService<CfgSovrapposizioneMeccanicheEntity>{

    List<CfgSovrapposizioneMeccanicheEntity> findByCanaleMeccanica(CfgAbilitaMeccCanaleEntity meccanicaCanale);
}
