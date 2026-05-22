package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiPlanoDbPromoService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
@Transactional
public class MuiPlanoDbPromoServiceImpl implements MuiPlanoDbPromoService, Serializable {
    private static final long serialVersionUID = 7533131067172258750L;

    @Inject
    @Getter
    MuiPlanoDbPromoDAO dao;

    @Override
    public List<MuiPlanoDbPromoEntity> findAll() {
        return getDao().findAll();
    }

    @Override
    public MuiPlanoDbPromoEntity findByIdPlano(@NonNull String idPlano) {
        return dao.findByIdPlano(idPlano);
    }

    @Override
    public List<MuiPlanoDbPromoEntity> findByPromo(@NonNull PromozioneTestataEntity promo) {
        if (promo.getPlanogrammi() != null) {
            return new ArrayList<>(promo.getPlanogrammi());
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> findIdPlanoByPromo(@NonNull PromozioneTestataEntity promo) {
        return promo.getPlanogrammi().stream().map(MuiPlanoDbPromoEntity::getIdPlano).collect(Collectors.toList());
    }
}
