package com.axiante.mui.dbpromo.persistence.dao;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import java.util.List;

public interface PianificazioneTotalizzatoriDAO extends DbPromoDAO<PianificazioneTotalizzatoriEntity> {
    List<PianificazioneTotalizzatoriEntity> findAllWithParentByIdPianificazione(Long idIniziativa);
}
