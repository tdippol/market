package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;

import java.util.List;

public interface MuiSpTestataService extends DbPromoService<MuiSpTestataEntity> {

    List<MuiSpTestataEntity> findAllForVisualizza();

    void save(MuiSpTestataEntity entity);

    String calculatePromoCode(String anno, CanalePromozioneEntity canale);

    void deactivateOtherActive(Long id);

    MuiSpTestataEntity findActive();

    MuiSpTestataEntity findById(Long id);

    void deleteById(Long id);

    MuiSpTestataEntity update(MuiSpTestataEntity entity);

    MuiSpTestataEntity findActiveForHeader();

    MuiSpTestataEntity findByIdForHeader(Long id);

    void flush();

    void runProcedureAssociaReparti(Long idPromozione);
}