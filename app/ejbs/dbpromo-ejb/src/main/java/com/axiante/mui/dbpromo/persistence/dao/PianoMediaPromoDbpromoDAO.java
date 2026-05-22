package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import java.util.Date;
import java.util.List;

public interface PianoMediaPromoDbpromoDAO {
    List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThanAndCanali(Date date, List<CanalePromozioneEntity> canali);

    List<PianoMediaPromoDbpromoEntity> findByDataFineGreaterThan(Date date);

    PianoMediaPromoDbpromoEntity findByCodicePromo(String codicePromo);

    List<PianoMediaPromoDbpromoEntity> findAllByCodiciPromo(List<String> codiciPromo);
}
