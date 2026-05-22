package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import java.util.List;

public interface CfgCanaleRepartoService extends DbPromoService<CfgCanaleReparto> {
    List<CfgCanaleReparto> findByCanale(CanalePromozioneEntity canale);
    CfgCanaleReparto findByCanaleAndReparto(CanalePromozioneEntity canale, RepartoEntity reparto);
}
