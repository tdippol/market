package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import java.util.Date;
import java.util.List;

public interface PianoMediaPromoDbpromoService {
    List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThanAndCanali(Date date, List<CanalePromozioneEntity> canali);

    PianoMediaPromoDbpromoEntity findByCodicePromo(String codicePromo);

    List<PianoMediaPromoDbpromoEntity> findAllByCodiciPromo(List<String> codiciPromo);

    List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThan(Date date);

}
