package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import java.util.List;

public interface PromoRepartoMarchioPrivatoDAO extends DbPromoDAO<PromoRepartoMarchioPrivato>{
    List<PromoRepartoMarchioPrivato> findByIdPromozione(Long idPromozione);
}
