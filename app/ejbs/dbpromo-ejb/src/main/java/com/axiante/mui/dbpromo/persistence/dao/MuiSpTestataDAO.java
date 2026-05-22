package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;

import java.util.List;

public interface MuiSpTestataDAO extends DbPromoDAO<MuiSpTestataEntity> {

    List<MuiSpTestataEntity> findAllForVisualizza();

    void save(MuiSpTestataEntity entity);

    void deactivateOtherActive(Long id);

    MuiSpTestataEntity findActive();
    MuiSpTestataEntity findById(Long id);
    void deleteById(Long id);


    MuiSpTestataEntity findActiveForHeader();
    MuiSpTestataEntity findByIdForHeader(Long id);

    void flush();
    void runProcedureAssociaReparti(Long idPromozione);


    MuiSpTestataEntity update(MuiSpTestataEntity entity);

}