package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import java.util.List;

public interface PromozioneMarchioPrivatoDAO extends DbPromoDAO<PromozioneMarchioPrivatoEntity>{
    List<PromozioneMarchioPrivatoEntity> findByIdPromozione(Long idPromozione);
}
