package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.VMuiSpRepartoCompratoreEntity;

import java.util.List;

public interface VMuiSpRepartoCompratoreService extends DbPromoService<VMuiSpRepartoCompratoreEntity> {

    List<VMuiSpRepartoCompratoreEntity> findAllOrdered();
}