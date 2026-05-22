package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.ClusterClienteEntity;
import java.util.Date;
import java.util.List;

public interface ClusterEntityDAO {
    List<ClusterClienteEntity> findAll();

    List<ClusterClienteEntity> findAllByIdCluster(String idCluster);
    List<ClusterClienteEntity> findByDataInizioAndDataFine(Date dataInizio, Date dataFine);
}
