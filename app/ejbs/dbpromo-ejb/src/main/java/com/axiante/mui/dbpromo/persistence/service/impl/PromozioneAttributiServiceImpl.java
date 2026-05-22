package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneAttributiDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttributiEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneAttributiService;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Default
@Transactional
public class PromozioneAttributiServiceImpl extends AbstractDbPromoService<PromozioneAttributiEntity>
        implements PromozioneAttributiService {
    private static final long serialVersionUID = 8660692209909081164L;

    @Inject
    @Getter
    PromozioneAttributiDAO dao;
}
