package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.ClusterClienteEntity;
import java.util.Date;
import java.util.List;

public interface ClusterClienteService {
    List<ClusterClienteEntity> findAll();
    List<ClusterClienteEntity> findAllByIdCluster(String idPlano);
    List<ClusterClienteEntity> findByDataInizioAndDataFine(Date dataInizio, Date dataFine);

}
