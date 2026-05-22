package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiFlagDominioDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiFlagDominioService;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Default
@Transactional
public class MuiFlagDominioServiceImpl extends AbstractDbPromoService<MuiFlagDominioEntity>
        implements MuiFlagDominioService {
    private static final long serialVersionUID = -6245620303688256439L;

    @Inject
    @Getter
    MuiFlagDominioDAO dao;

    public List<MuiFlagDominioEntity> findAllAttiviByFlag(@NonNull Long idFlag) {
        return getDao().findAllAttiviByFlag(idFlag);
    }

    public List<MuiFlagDominioEntity> findAllAttiviAndDefaultByFlag(@NonNull Long idFlag) {
        return getDao().findAllAttiviAndDefaultByFlag(idFlag);
    }
}
