package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CausaliRetailEntity;

import java.util.List;

public interface CausaliRetailDAO {
    CausaliRetailEntity getCausalRetail(String id);
    List<CausaliRetailEntity> getAllCausalRetail();
    CausaliRetailEntity getCausalRetailByDescription(String description);

    List<CausaliRetailEntity> getCausaleRetailByIdLike(String id);
    List<CausaliRetailEntity> getCausaleRetailDescrizioneLike(String id);
}
