package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.FornitoriRetailEntity;

import java.util.Date;
import java.util.List;

public interface FornitoriRetailDAO {
    FornitoriRetailEntity getFornitoriRetailById(String codice);

    FornitoriRetailEntity getFornitoriRetailByDescrizione(String descrizione);

    List<FornitoriRetailEntity> getAllFornitoriRetail();

    List<FornitoriRetailEntity> getAllFornitoriRetailNotDeleted(Date date);

    List<FornitoriRetailEntity> getAllFornitoriRetailByCodiceLike(String codice);

    List<FornitoriRetailEntity> getFornitoriRetailByDescrizioneLike(String descrizione);
}
