package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.VMuiSpRepartoCompratoreEntity;

import java.util.List;

public interface VMuiSpRepartoCompratoreDAO extends DbPromoDAO<VMuiSpRepartoCompratoreEntity> {

    List<VMuiSpRepartoCompratoreEntity> findAllOrdered();
}