package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ClusterEntityDAO;
import com.axiante.mui.dbpromo.persistence.entities.ClusterClienteEntity;
import com.axiante.mui.dbpromo.persistence.service.ClusterClienteService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
public class ClusterClienteServiceImpl implements ClusterClienteService, Serializable {
    private static final long serialVersionUID = -4072725028804152414L;

    @Inject
    @Getter
    ClusterEntityDAO dao;

    public List<ClusterClienteEntity> findAll(){
        return getDao().findAll();
    }

    public List<ClusterClienteEntity> findAllByIdCluster(String idPlano){
        return getDao().findAllByIdCluster(idPlano);
    }

    public List<ClusterClienteEntity> findByDataInizioAndDataFine(@NonNull Date dataInizio, @NonNull Date dataFine){
        return getDao().findByDataInizioAndDataFine(dataInizio, dataFine);
    }
}
