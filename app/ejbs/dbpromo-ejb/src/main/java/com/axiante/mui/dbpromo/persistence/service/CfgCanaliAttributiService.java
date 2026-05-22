package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgCanaliAttributiEntity;
import java.util.List;

public interface CfgCanaliAttributiService extends DbPromoService<CfgCanaliAttributiEntity> {
    String getListaByCanaleAndAttributo(Long idCanale, Long idAttributo);
    List<CfgCanaliAttributiEntity> getAllByCanale(Long idCanale);

}
