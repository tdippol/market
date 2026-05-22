package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CausaliRetailEntity;

import java.util.List;

public interface CausaliRetailService {
    CausaliRetailEntity getCausaliRetail(String code);
    List<CausaliRetailEntity> getCausaliRetail();

    List<CausaliRetailEntity> getCausaliRetailByCode(String code);
    List<CausaliRetailEntity>  getCausaliRetailByDescription(String description);
}
