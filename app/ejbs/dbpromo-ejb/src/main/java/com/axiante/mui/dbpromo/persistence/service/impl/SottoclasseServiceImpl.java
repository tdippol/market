package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.SottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import com.axiante.mui.dbpromo.persistence.service.SottoclasseService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class SottoclasseServiceImpl extends AbstractDbPromoService<SottoclasseEntity> implements SottoclasseService {
    private static final long serialVersionUID = 4810032264502457801L;

    @Inject
    @Getter
    private SottoclasseDAO dao;

    @Override
    public List<SottoclasseEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public SottoclasseEntity findById(@NonNull Long id) {
        return dao.findById(id);
    }

    @Override
    public boolean existsByCodeOrDescription(@NonNull String code, @NonNull String description) {
        return dao.existsByCodeOrDescription(code, description);
    }

    @Override
    public boolean runFunctionPubblicaSottoclassi(@NonNull String username) throws Exception {
        return dao.runFunctionPubblicaSottoclassi(username);
    }

    @Override
    public Long countSottoclassiNonScaricate() {
        return dao.countSottoclassiNonScaricate();
    }
}
