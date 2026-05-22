package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.FornitoriRetailEntity;

import java.util.Date;
import java.util.List;

public interface FornitoriRetailService {
    FornitoriRetailEntity getFornitoriRetail(String codice);

    List<FornitoriRetailEntity> getAllFornitoriRetail();

    List<FornitoriRetailEntity> getAllFornitoriRetailNotDeleted(Date date);

    FornitoriRetailEntity getFornitoriRetailByDescription(String description);

    List<FornitoriRetailEntity> getFornitoriRetailByCodeLike(String code);

    List<FornitoriRetailEntity> getFornitoriRetailByDescriptionLike(String description);
}
